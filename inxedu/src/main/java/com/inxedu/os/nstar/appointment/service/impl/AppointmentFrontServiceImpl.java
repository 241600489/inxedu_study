package com.inxedu.os.nstar.appointment.service.impl;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.nstar.appointment.controller.ExamAppointmentLogController;
import com.inxedu.os.nstar.appointment.entity.AppointmentState;
import com.inxedu.os.nstar.appointment.entity.ApponitmentResult;
import com.inxedu.os.nstar.appointment.entity.examAppointmentLog.ExamAppointmentLog;
import com.inxedu.os.nstar.appointment.entity.examBatch.ExamBatch;
import com.inxedu.os.nstar.appointment.entity.examBatch.ExamBatchListViewModel;
import com.inxedu.os.nstar.appointment.entity.examManager.ExamManager;
import com.inxedu.os.nstar.appointment.entity.examSeat.ExamSeat;
import com.inxedu.os.nstar.appointment.entity.examStudent.ExamStudent;
import com.inxedu.os.nstar.appointment.entity.examStudentAppointment.ExamStudentAppointment;
import com.inxedu.os.nstar.appointment.service.AppointmentFrontService;
import com.inxedu.os.nstar.appointment.service.ExamAppointmentLogService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 陈俊文 on 16-8-6.
 */
@Service
public class AppointmentFrontServiceImpl extends GenericDaoImpl implements AppointmentFrontService {
    @Autowired
    private ExamAppointmentLogService examAppointmentLogService;

    @Transactional(readOnly = true)
    public ExamBatchListViewModel queryExamBatchByStudentNo(Integer studentNo, Integer examnameId, Date date, String time, Boolean isfull, PageEntity page) {
        ExamManager examManager = selectExamManagerById(examnameId);
        if(examManager==null){
            return ExamBatchListViewModel.EMPTY;
        }
        List<ExamStudent> studentList = getExamStudentByStudentNo(studentNo);
        if (ObjectUtils.isNotNull(studentList)) {
            ExamStudent student = studentList.get(0);
            boolean isAppointment = isExistAppointment(student.getStudentNo(), examManager.getExamName());
            if (!Boolean.TRUE.equals(examManager.getIsCourseController())) {
                return new ExamBatchListViewModel(queryBatchWithExamManagerByExamCourseCode(null, examnameId, date, time, isfull, page), isAppointment);
            }
            List<ExamBatch> list = new LinkedList<>();
            for (ExamStudent it : studentList) {
                List<ExamBatch> examBatches = queryBatchWithExamManagerByExamCourseCode(it.getCourseCode(), examnameId, date, time, isfull, page);
                list.addAll(examBatches);
            }
            return new ExamBatchListViewModel(list, isAppointment);
        } else {
            return ExamBatchListViewModel.EMPTY;
        }

    }

    @Transactional(readOnly = true)
    public boolean isExistAppointment(String studentNo, String examName) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("no", studentNo);
        map.put("name", examName);
        Integer id = selectOne("ExamAppointmentMapper.isExistAppointment", map);
        return id != null;
    }

    @Transactional(readOnly = true)
    public List<ExamStudent> getExamStudentByStudentNo(Integer studentNo) {
        return this.selectList("ExamAppointmentMapper.queryExamStudentByStudentNo", studentNo);
    }

    @Transactional(readOnly = true)
    public boolean isExistExamStudentAppointmentByStudentNoByExamName(String studentNo, String examname) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("no", studentNo);
        map.put("name", examname);
        Integer appointmentId = this.selectOne("ExamAppointmentMapper.isExistExamStudentAppointmentByStudentNoByExamName.", map);
        return appointmentId != null;
    }

    @Transactional(readOnly = true)
    public List<ExamBatch> queryBatchWithExamManagerByExamCourseCode(String courseCode, Integer managerId, Date date, String time, Boolean isfull, PageEntity page) {
        Map<String, Object> map = new HashMap<>(5);
        if (courseCode == null) {
            map.put("code", null);
        } else {
            map.put("code", courseCode);
        }
        map.put("isfull", isfull);
        map.put("managerId", managerId);
        map.put("date", date);
        map.put("time", time);
        return queryForListPage("ExamAppointmentMapper.queryBatchWithExamManagerByExamCourseCode", map, page);

    }

    @Transactional(readOnly = true)
    public ExamManager selectExamManagerById(Integer id) {
        return this.selectOne("ExamAppointmentMapper.selectExamManagerById", id);
    }

    @Transactional(readOnly = true)
    public List<ExamManager> queryExamManagerListWithCurrenttime() {
        return this.selectList("ExamAppointmentMapper.selectWithOpen", new Date());
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public ApponitmentResult appointment(ExamStudent student, Integer examBatchId, Integer examnameId) {

        ExamManager examManager = selectExamManagerById(examnameId);
        ExamAppointmentLog examAppointmentLog = new ExamAppointmentLog();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        String s = sdf.format(date);
        examAppointmentLog.setEventDate(s);
        examAppointmentLog.setEventMan("操作人:学号:" + student.getStudentNo());
        examAppointmentLog.setEvents("通过id查询考试管理id=" + examnameId.toString());
        examAppointmentLog.setObject("数据表" + "exam_manager");
        examAppointmentLogService.addexamAppointmentLog(examAppointmentLog);
        if (Objects.isNull(examManager)) {
            return new ApponitmentResult(AppointmentState.ILLEGAL);
        }
        if (!Boolean.TRUE.equals(examManager.getState())) {
            return new ApponitmentResult(AppointmentState.ILLEGAL);
        }
        ExamBatch examBatch = queryExamBatchById(examBatchId);
        if (Objects.isNull(examBatch)) {
            return new ApponitmentResult(AppointmentState.ILLEGAL);
        }

        Date examDate = new Date();
        if (!(examDate.after(examManager.getAppointmentBeginTime()) && examDate.before(examManager.getAppointmentEndTime()))) {
            return new ApponitmentResult(AppointmentState.ILLEGAL_TIME);
        }
        if (examBatch.getAppointmentCount() >= examBatch.getNumber()) {
            return new ApponitmentResult(AppointmentState.FULL);
        }
        boolean isAppointment = isExistAppointment(student.getStudentNo(), examManager.getExamName());
        if (isAppointment) {
            return new ApponitmentResult(AppointmentState.REPLY);
        }
        ExamStudentAppointment examStudentAppointment = new ExamStudentAppointment();

        List<ExamSeat> examBatchList = queryExamSeatByBatchId(examBatchId);
        if (ObjectUtils.isNull(examBatchList)) {
            return new ApponitmentResult(AppointmentState.NO_SEAT);
        }
        ExamSeat seat = RandomUtils.getRandomElement(examBatchList);

        seat.setState(true);
        updateExamSeatBySeatId(seat);
        examStudentAppointment.setSeatNo(seat.getSeatNo());
        examAppointmentLog.setEvents("通过id更新座位表id" + seat.getId().toString());

        examStudentAppointment.setExamBeginEndTimes(examBatch.getExamBeginEndTime());
        examStudentAppointment.setExamClassroom(examBatch.getExamClassroom());
        examStudentAppointment.setExamDate(examBatch.getExamDate());
        examStudentAppointment.setExamName(examManager.getExamName());
        examStudentAppointment.setExamBatchId(examBatchId);
        examStudentAppointment.setBatchNo(examBatch.getExamBatchNo());
        examStudentAppointment.setState(true);
        examStudentAppointment.setScore("");
        examStudentAppointment.setStudentClass(student.getStudentClass());
        examStudentAppointment.setStudentName(student.getStudentName());
        examStudentAppointment.setStudentNo(student.getStudentNo());
        examStudentAppointment.setUpdateDate(new Date());
        examStudentAppointment.setMemo(examBatch.getMemo());
        examAppointmentLog.setObject("数据表" + "exam_seat");
        examAppointmentLog.setEventMan("操作人:学号:" + student.getStudentNo());
        examAppointmentLogService.addexamAppointmentLog(examAppointmentLog);
        incExamBatchCountById(examBatchId);
        examAppointmentLog.setObject("数据表" + "exam_batch");
        examAppointmentLog.setEvents("使数据表:exam_batch中的已预约人数(appointment_count)加一");
        examAppointmentLog.setEventMan("操作人:学号:" + student.getStudentNo());
        examAppointmentLogService.addexamAppointmentLog(examAppointmentLog);
        insertExamStudentAppointment(examStudentAppointment);
        examAppointmentLog.setObject("exam_student_appointment");
        examAppointmentLog.setEvents("使数据表:exam_student_appointment的已选人数+1");
        examAppointmentLog.setEventMan("操作人:学号:" + student.getStudentNo());
        examAppointmentLogService.addexamAppointmentLog(examAppointmentLog);
        ApponitmentResult result = new ApponitmentResult(AppointmentState.SUCCESS);
        result.setAppointment(examStudentAppointment);
        return result;
    }
    public List<ExamStudentAppointment> queryMyAppointment(String studentNo, PageEntity pageEntity) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("studentNo", studentNo);
        return queryForListPage("ExamAppointmentMapper.queryMyAppointment", map, pageEntity);
    }

    public Long insertExamStudentAppointment(ExamStudentAppointment examStudentAppointment) {
        return insert("ExamAppointmentMapper.insertExamStudentAppointment", examStudentAppointment);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public AppointmentState backAppointment(String studentNo, Integer examStudentAppointmentId) {


        ExamStudentAppointment studentAppointment = selectStudentAppointmentByPrimaryKeyWithtudentNo(studentNo, examStudentAppointmentId);
        ExamAppointmentLog examAppointmentLog = new ExamAppointmentLog();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        String s = sdf.format(date);
        examAppointmentLog.setEventDate(s);
        examAppointmentLog.setEvents("通过studentNo和id查询exam_student_appointment");
        examAppointmentLog.setObject("数据表:exam_student_appointment");
        examAppointmentLog.setEventMan("操作人:学号:" + studentNo);
        examAppointmentLogService.addexamAppointmentLog(examAppointmentLog);
        if (studentAppointment == null) {
            return AppointmentState.ILLEGAL;
        }

        ExamBatch examBatch = queryExamBatchById(studentAppointment.getExamBatchId());
        if (examBatch == null) {
            return AppointmentState.ILLEGAL;
        }
        ExamManager examManager = selectExamManagerById(examBatch.getExamManagerId());
        if (examManager == null) {
            return AppointmentState.ILLEGAL;
        }

        Date now = new Date();
        now.setHours(0);
        now.setMinutes(0);
        now.setSeconds(0);
        if (!(examManager.getAppointmentBeginTime().before(now) && examManager.getAppointmentEndTime().after(now))) {
            return AppointmentState.ILLEGAL_TIME;
        }
        Integer seatId = querySeatIdSeatByBatchIdBySeatNoWithState1(examBatch.getId(), studentAppointment.getSeatNo());
        changeSeatStateTo0BySeatId(seatId);
        examAppointmentLog.setObject("数据表:exam_seat");
        examAppointmentLog.setEvents("通过id改变exam_seat中的座位state");
        examAppointmentLog.setEventMan("操作人:学号:" + studentNo);
        examAppointmentLogService.addexamAppointmentLog(examAppointmentLog);
        decExamBatchCountById(examBatch.getId());
        examAppointmentLog.setObject("数据表:exam_batch");
        examAppointmentLog.setEvents("使数据表:exam_batch中的已预约人数(appointment_count)减一");
        examAppointmentLog.setEventMan("操作人:学号:" + studentNo);
        examAppointmentLogService.addexamAppointmentLog(examAppointmentLog);
        deleteExamStudentAppointmentByPrimaryKey(studentAppointment.getId());
        examAppointmentLog.setObject("数据表:exam_student_appointment");
        examAppointmentLog.setEvents("通过主键删除从exam_student_appointment");
        examAppointmentLog.setEventMan("操作人:学号:" + studentNo);
        examAppointmentLogService.addexamAppointmentLog(examAppointmentLog);
        return AppointmentState.SUCCESS;

    }

    public List<ExamSeat> queryExamSeatByBatchId(Integer id) {
        return selectList("ExamAppointmentMapper.selectExamSeatByBatchId", id);
    }

    public ExamStudentAppointment selectStudentAppointmentByPrimaryKeyWithtudentNo(String studentNo, Integer examStudentAppointmentId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("id", examStudentAppointmentId);
        map.put("studentNo", studentNo);
        return selectOne("ExamAppointmentMapper.selectStudentAppointmentByPrimaryKeyWithtudentNo", map);
    }

    public void updateExamSeatBySeatId(ExamSeat examSeat) {
        update("ExamSeatMapper.updateByPrimaryKeySelective", examSeat);
    }

    public void deleteExamStudentAppointmentByPrimaryKey(Integer id) {
        delete("ExamAppointmentMapper.deleteExamStudentAppointmentByPrimaryKey", id);
    }

    public void decExamBatchCountById(Integer id) {
        update("ExamAppointmentMapper.decExamBatchCountById", id);
    }

    public void incExamBatchCountById(Integer id) {
        update("ExamAppointmentMapper.incExamBatchCountById", id);
    }

    public ExamBatch queryExamBatchById(Integer id) {
        return selectOne("ExamAppointmentMapper.queryExamBatchById", id);
    }

    public void changeSeatStateTo0BySeatId(Integer id) {
        update("ExamAppointmentMapper.changeSeatStateTo0BySeatId", id);
    }

    public void changeSeatStateTo1BySeatId(Integer id) {
        update("ExamAppointmentMapper.changeSeatStateTo1BySeatId", id);
    }

    public Integer querySeatIdSeatByBatchIdBySeatNoWithState1(Integer batchId, Integer seatNo) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("batchId", batchId);
        map.put("seatNo", seatNo);
        return selectOne("ExamAppointmentMapper.querySeatIdSeatByBatchIdBySeatNoWithState1", map);
    }

    public List<String> queryBatchTime(Integer managerId, Date date,String courseCode) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("managerId", managerId);
        map.put("code", courseCode);
        map.put("date",date);
        return selectList("ExamAppointmentMapper.queryBatchTime", map);
    }

    @Override
    public List<Date> queryBatchDate(Integer managerId, String courseCode) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("managerId", managerId);
        map.put("code", courseCode);
        return selectList("ExamAppointmentMapper.queryBatchDate", map);
    }
    public ExamManager queryExamManagerById(Integer managerId) {
        return selectOne("ExamAppointmentMapper.queryExamManagerById", managerId);
    }

    @Override
    public List<String> queryBatchTimeWithoutCourseCode(Integer managerId) {
        return this.selectList("ExamAppointmentMapper.queryBatchTimeWithoutCourseCode", managerId);
    }
}
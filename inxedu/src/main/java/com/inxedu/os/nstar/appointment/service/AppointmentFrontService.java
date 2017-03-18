package com.inxedu.os.nstar.appointment.service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.AppointmentState;
import com.inxedu.os.nstar.appointment.entity.ApponitmentResult;
import com.inxedu.os.nstar.appointment.entity.examBatch.ExamBatch;
import com.inxedu.os.nstar.appointment.entity.examBatch.ExamBatchListViewModel;
import com.inxedu.os.nstar.appointment.entity.examManager.ExamManager;
import com.inxedu.os.nstar.appointment.entity.examSeat.ExamSeat;
import com.inxedu.os.nstar.appointment.entity.examStudent.ExamStudent;
import com.inxedu.os.nstar.appointment.entity.examStudentAppointment.ExamStudentAppointment;

import java.util.Date;
import java.util.List;

/**
 * Created by karak on 16-8-6.
 */
public interface AppointmentFrontService {
    ExamBatchListViewModel  queryExamBatchByStudentNo(Integer studentNo, Integer examnameId, Date date, String time, Boolean isfull, PageEntity page);
    public  boolean isExistAppointment(String studentNo, String examName);
    public List<ExamStudent> getExamStudentByStudentNo(Integer studentNo);
    public boolean isExistExamStudentAppointmentByStudentNoByExamName(String studentNo, String examname);
    public List<ExamBatch> queryBatchWithExamManagerByExamCourseCode(String courseCode, Integer managerId, Date date, String time, Boolean isfull, PageEntity page);
    public ExamManager selectExamManagerById(Integer id);
    public List<ExamManager> queryExamManagerListWithCurrenttime();
    public ApponitmentResult appointment(ExamStudent student, Integer examBatchId, Integer examnameId);
    public List<ExamStudentAppointment> queryMyAppointment(String studentNo, PageEntity pageEntity);
    public AppointmentState backAppointment(String studentNo, Integer examStudentAppointmentId);
    public List<ExamSeat> queryExamSeatByBatchId(Integer id);
    public void updateExamSeatBySeatId(ExamSeat examSeat);
    public void decExamBatchCountById(Integer id);
    public void incExamBatchCountById(Integer id);
    public ExamBatch queryExamBatchById(Integer id);
    public void changeSeatStateTo0BySeatId(Integer id);
    public void changeSeatStateTo1BySeatId(Integer id);
    public Integer querySeatIdSeatByBatchIdBySeatNoWithState1(Integer batchId, Integer seatNo);
    public List<String> queryBatchTime(Integer managerId, Date date,String courseCode);
    public List<Date> queryBatchDate(Integer managerId, String courseCode);
    public ExamManager queryExamManagerById(Integer managerId);
    public List<String> queryBatchTimeWithoutCourseCode(Integer managerId);
}

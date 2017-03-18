package com.inxedu.os.nstar.appointment.service.impl;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.nstar.appointment.entity.examStudent.HyberExamStudent;
import com.inxedu.os.nstar.appointment.entity.examStudentAppointment.HyberExamSudentAppointment;
import com.inxedu.os.nstar.appointment.service.*;
import com.inxedu.os.nstar.selectCourse.entity.college.College;
import com.inxedu.os.nstar.selectCourse.entity.stuClass.StuClass;
import com.inxedu.os.nstar.selectCourse.service.CollegeService;
import com.inxedu.os.nstar.selectCourse.service.StuClassService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.exception.BaseException;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.nstar.appointment.entity.examAppointmentLog.ExamAppointmentLog;
import com.inxedu.os.nstar.appointment.entity.examBatch.ExamBatch;
import com.inxedu.os.nstar.appointment.entity.examClassroom.ExamClassroom;
import com.inxedu.os.nstar.appointment.entity.examCourse.ExamCourse;
import com.inxedu.os.nstar.appointment.entity.examManager.ExamManager;
import com.inxedu.os.nstar.appointment.entity.examSeat.ExamSeat;
import com.inxedu.os.nstar.appointment.entity.examStudent.ExamStudent;
import com.inxedu.os.nstar.appointment.entity.examStudentAppointment.ExamStudentAppointment;

@Service
public class ExcelServiceImpl extends GenericDaoImpl implements ExcelService {
    //
    // /**
    // * ExamAppointmentLog表
    // *
    // * @return
    // */
    // // =========导出 ExamAppointmentLog表
    // // 部分===开始====================================
    public ArrayList<String> getExamAppointmentLogHead() {
        ArrayList<String> headlist = new ArrayList<String>();
        headlist.add("操作事件");
        headlist.add("操作对象");
        headlist.add("发生时间");
        headlist.add("操作人");
        return headlist;
    }

    private List<ExamAppointmentLog> queryAllExamAppointmentLog() {

        return this.selectList(
                "ExamAppointmentLogMapper.queryAllExamAppointmentLog", null);
    }

    public ArrayList<ArrayList<String>> getExamAppointmentLogData() {
        ArrayList<ArrayList<String>> dataList = new
                ArrayList<ArrayList<String>>();
        List<ExamAppointmentLog> examAppointmentLogList =
                queryAllExamAppointmentLog();
        List<List<String>> list =
                examAppointmentLogJoint(examAppointmentLogList);
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> al = (ArrayList<String>) list.get(i);
            dataList.add(al);
        }
        return dataList;

    }

    // 辅助函数
    private List<List<String>> examAppointmentLogJoint(
            List<ExamAppointmentLog> examAppointmentLogList) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < examAppointmentLogList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(examAppointmentLogList.get(i).getEvents() + "");// events
            small.add(examAppointmentLogList.get(i).getObject() + "");// object
            small.add(examAppointmentLogList.get(i).getEventDate() + "");// eventDate
            small.add(examAppointmentLogList.get(i).getEventMan() + "");// eventMan
            list.add(small);
        }
        return list;
    }

    // ======导出 ExamAppointmentLog表
//	 部分===结束====================================

    // ======导入 ExamAppointmentLog表
//	 部分===开始====================================
    public String updateExcelToExamAppointmentLog(HttpServletRequest request,
                                                  MultipartFile myFile) throws Exception {
        String msg = "";
        HSSFWorkbook wookbook = new HSSFWorkbook(myFile.getInputStream());
        HSSFSheet sheet = wookbook.getSheet("Sheet1");

        int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
        if (rows == 0) {
            throw new BaseException("请填写数据");
        }
        for (int i = 1; i <= rows + 1; i++) {
            // 读取左上端单元格
            HSSFRow row = sheet.getRow(i);
            // 行不为空
            if (row != null) {
                // **读取cell**
                String events = getStringCellValue(row.getCell((short) 0));// events
                String object = getStringCellValue(row.getCell((short) 1));// object
                String eventDate = getStringCellValue(row.getCell((short) 2));//
//	 eventDate
                String eventMan = getStringCellValue(row.getCell((short) 3));// eventMan
                ExamAppointmentLog examAppointmentLog = new ExamAppointmentLog();
                examAppointmentLog.setEvents(events);
                examAppointmentLog.setObject(object);
                examAppointmentLog.setEventDate(eventDate);
                examAppointmentLog.setEventMan(eventMan);

                this.insert("ExamAppointmentLogMapper.insertExcel",
                        examAppointmentLog);
            }
        }
        return msg;

    }

    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    //
    // // ======导入 ExamAppointmentLog表
    // 部分===结束====================================
    //

    /**
     * ExamBatch表
     *
     * @return
     */
    // =========导出 ExamBatch表 部分===开始====================================
    public ArrayList<String> getExamBatchHead() {
        ArrayList<String> headlist = new ArrayList<String>();
        headlist.add("考场");
        headlist.add("批次");
        headlist.add("考试日期");
        headlist.add("考试时间");
        headlist.add("科目代码");
        headlist.add("状态");
        return headlist;
    }

    private List<ExamBatch> queryAllExamBatch() {
        return this.selectList("ExamBatchMapper.queryAllExamBatchExport", null);
    }

    public ArrayList<ArrayList<String>> getExamBatchData() {
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        List<ExamBatch> examBatchList = queryAllExamBatch();
        List<List<String>> list = ExamBatchJoint(examBatchList);
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> al = (ArrayList<String>) list.get(i);
            dataList.add(al);
        }
        return dataList;
    }

    // 辅助函数
    private List<List<String>> ExamBatchJoint(List<ExamBatch> examBatchList) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < examBatchList.size(); i++) {
            List<String> small = new ArrayList<String>();
            // examClassroom examBatchNo examDate examBeginEndTime courseNo
            // state
            small.add(examBatchList.get(i).getExamClassroom() + "");// examClassroom
            small.add(examBatchList.get(i).getExamBatchNo() + "");// examBatchNo
            // SimpleDateFormat s =new SimpleDateFormat("yymmss");
            // s.format(date)
            small.add(examBatchList.get(i).getExamDate().toLocaleString() + "");// examDate
            small.add(examBatchList.get(i).getExamBeginEndTime() + "");// examBeginEndTime
            small.add(examBatchList.get(i).getCourseNo() + "");// courseNo
            small.add(examBatchList.get(i).getState() == true ? "开启" : "关闭"
                    + "");// state
            list.add(small);
        }
        return list;
    }
   @Autowired
   private ExamManagerService examManagerService;

    public String updateExcelToExamBatch(HttpServletRequest request,
                                         MultipartFile myFile) throws Exception {
        String msg = "";
        List<ExamBatch> examBatchList = new ArrayList<ExamBatch>();
        try (InputStream inputStream = myFile.getInputStream();) {
            XSSFWorkbook wookbook = new XSSFWorkbook(inputStream);
            Iterator<Sheet> iterator = wookbook.iterator();
            Integer count = 0;
            while (iterator.hasNext()) {
                count++;
                iterator.next();
            }
            for (int i = 0; i < count; i++) {
                XSSFSheet sheet = wookbook.getSheetAt(i);

                int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
                if (rows == 0&&i==0) {
                    throw new BaseException("请填写数据");
                }
                for (int j = 1; j <= rows + 1; j++) {
                    // 读取左上端单元格
                    XSSFRow row = sheet.getRow(j);
                    // 行不为空
                    if (row != null) {
                        // **读取cell**
                        // examClassroom examBatchNo examDate examBeginEndTime courseNo
                        // state
                        String examName = getStringCellValue(row.getCell((short) 0));// 考试名称
                        Integer examBatchNo =getIntCellValue(row.getCell((short) 1));// 考试批次号
                        String examClassRoomName=getStringCellValue(row.getCell(2));//考场名称
                        String examDate_s = getCell(row.getCell((short) 3));// 考试日期
                        Date examDate = null;
                        String examBeginEndTime = getStringCellValue(row.getCell((short) 4));// 考试时间段
                        String courseNo = getStringCellValue(row.getCell((short) 5));// 考试课程代码
                        String state = getStringCellValue(row.getCell((short) 6));// 状态
                        String momo=getStringCellValue(row.getCell(7));//备注
                        Integer number=getIntCellValue(row.getCell(8));//人数
                        if(examName.isEmpty()&&examBatchNo==-1&&examClassRoomName.isEmpty()&&examDate_s.isEmpty()&&momo.isEmpty()){
                            continue;
                        }
                        Boolean state1 = null;
                        // 校验
                        if (ObjectUtils.isNull(examName)
                                || examName.equals("")) {
                            msg += "第" + j + "行  考试名称  不能为空<br/>";
                        }
                       ExamManager examManager= examManagerService.queryExamManagerByExamName(examName);
                        if (ObjectUtils.isNull(examManager)) {
                            msg += "第" + j + "行  在考试管理表里不存在<br/>";
                        }

                        if (ObjectUtils.isNull(examBatchNo)) {
                            msg += "第" + j + "行  批次  不能为空<br/>";

                        }
                        if (ObjectUtils.isNull(examDate_s) || examDate_s.equals("")) {
                            msg += "第" + j + "行  考试日期  不能为空<br/>";
                        } else {
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                examDate = df.parse(examDate_s);
                            } catch (Exception e) {
                                msg += "第" + j + "行  考试日期  格式有错误<br/>";
                            }
                        }
                        if (ObjectUtils.isNull(examBeginEndTime)
                                || examBeginEndTime.equals("")) {
                            msg += "第" + j + "行  考试时间  不能为空<br/>";
                        }
                        if (ObjectUtils.isNull(courseNo) || courseNo.equals("")) {
                            msg += "第" + j + "行 科目代码  不能为空<br/>";
                        }
                        ExamCourse examCourse = examCourseService.queryCourseByCode(courseNo);
                        if (ObjectUtils.isNull(examCourse)) {
                            msg += "第" + j + "行 科目代码在考试课程表里不存在<br/>";
                        }
                        if (ObjectUtils.isNull(state) || state.equals("")) {
                            msg += "第" + j + "行  状态  不能为空<br/>";
                        } else {
                            if (state.equals("开启")) {
                                state1 = true;
                            } else {
                                state1 = false;
                            }
                        }
                        ExamBatch examBatch = new ExamBatch();
                    ExamClassroom  examClassroom=examClassroomService.queryClassRoomByName(examClassRoomName);
                        if (ObjectUtils.isNull(examClassroom)){
                            msg += "第" + j + "行   考场名称在考场表里不存在<br/>";
                        }
                        examBatch.setAppointmentCount(0);
                        examBatch.setState(state1);
                        examBatch.setExamClassroom(examClassRoomName);
                        examBatch.setExamName(examName);
                        examBatch.setExamBatchNo(examBatchNo);// 批次
                        examBatch.setExamDate(examDate);// 考试日期
                        examBatch.setExamBeginEndTime(examBeginEndTime);// 考试时间
                        if(ObjectUtils.isNotNull(examCourse)){
                            examBatch.setCourseName(examCourse.getCourseName());
                            examBatch.setNumber(examClassroom.getMaxNumber());
                            examBatch.setCourseId(examCourse.getId());
                        }
                        examBatch.setMemo(momo);

                        examBatch.setCourseNo(courseNo);// 科目代码
                        if(ObjectUtils.isNotNull(examManager)){
                            examBatch.setExamManagerId(examManager.getId());
                        }

                        examBatch.setNumber(number);

                        examBatchList.add(examBatch);
                    }
                }
            }
            if (msg == null || msg.equals("")) {
                // --------------批量保存
                for (ExamBatch examBatch:examBatchList
                     ) {
                    this.insert("ExamBatchMapper.insert", examBatch);
                }
                
                for (int i =0; i <= seatNo1; i++) {
                    examSeat.setSeatNo(i);
                    examSeat.setState(false);
                    examSeat.setExamClassroomName(examClassroomName);
                    examSeat.setExamBatchId(Integer.parseInt(examBatchId));
                    examSeatService.addExamSeat(examSeat);}

            }else {
                return msg;
            }
        }catch (Exception e){
            e.printStackTrace();
           return "系统异常";
        }
        return  msg;

    }

    // ======导入 ExamBatch表 部分===结束====================================
    // /**
    // * ExamClassroom表
    // *
    // * @return
    // */
    // // =========导出 ExamClassroom表 部分===开始====================================
    public ArrayList<String> getExamClassroomHead() {
        ArrayList<String> headlist = new ArrayList<String>();
        headlist.add("考场名称");
        headlist.add("考场容量");
        headlist.add("考场最大容量");
        headlist.add("考场状态");
        return headlist;

    }
    private List<ExamClassroom> queryAllExamClassroom() {

        return this.selectList("ExamClassroomMapper.queryAllExamClassroom",
                null);
    }

    public ArrayList<ArrayList<String>> getExamClassroomData() {
        ArrayList<ArrayList<String>> dataList = new
                ArrayList<ArrayList<String>>();
        List<ExamClassroom> ExamClassroomList = queryAllExamClassroom();
        List<List<String>> list = examClassroomJoint(ExamClassroomList);
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> al = (ArrayList<String>) list.get(i);
            dataList.add(al);
        }
        return dataList;
    }

    // // 辅助函数
    private List<List<String>> examClassroomJoint(
            List<ExamClassroom> examClassroomList) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < examClassroomList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(examClassroomList.get(i).getExamClassroomName() + "");//
//	 examClassroomName
            small.add(examClassroomList.get(i).getNumber() + "");// number
            small.add(examClassroomList.get(i).getMaxNumber() + "");// maxNumber
            small.add(examClassroomList.get(i).getState() + "");// state
            list.add(small);
        }
        return list;
    }

    // // ======导出 ExamClassroom表 部分===结束====================================
    //
    // ======导入 ExamClassroom表 部分===开始====================================
    public String updateExcelToExamClassroom(HttpServletRequest request,
                                             MultipartFile myFile) throws Exception {

        String msg = "";
        List<ExamClassroom> list = new ArrayList<ExamClassroom>();
        try (InputStream inputStream = myFile.getInputStream();) {


            HSSFWorkbook wookbook = new HSSFWorkbook(inputStream);
            Iterator<Sheet> iterator = wookbook.iterator();
            Integer count = 0;
            while (iterator.hasNext()) {
                count++;
                iterator.next();
            }
            for (int i = 0; i < count; i++) {


                HSSFSheet sheet = wookbook.getSheetAt(i);

                int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
                if (rows == 0) {
                    throw new BaseException("请填写数据");
                }
                for (int j = 1; j <= rows + 1; j++) {
                    // 读取左上端单元格
                    HSSFRow row = sheet.getRow(j);
                    // 行不为空
                    if (row != null) {
                        // **读取cell**
                        String examClassroomName = getStringCellValue(row
                                .getCell((short) 0));// examClassroomName
                        int number = getIntCellValue(row.getCell((short) 1));// number
                        int maxNumber = getIntCellValue(row.getCell((short) 2));// maxNumber
                        String state = getStringCellValue(row.getCell((short) 3));// state
                        if (StringUtils.isBlank(examClassroomName)) {
                            msg += "第" + j + "行考场名称不能为空" + "<br>";
                        }
                        ExamClassroom examClassroom1 = examClassroomService.queryClassRoomByName(examClassroomName);
                        if (ObjectUtils.isNotNull(examClassroom1)) {
                            msg += "第" + j + "行考场名称在数据库已存在" + "<br>";
                        }
                        if (number == -1) {
                            msg += "第" + j + "行考场容量不能为空" + "<br>";
                        }
                        if (maxNumber == -1) {
                            msg += "第" + j + "行考场最大容量不能为空" + "<br>";
                        }
                       /* if(maxNumber==-1){
                            msg+="第"+j+"行考场最大容量不能为空"+"<br>";
                        }*/
                        ExamClassroom examClassroom = new ExamClassroom();
                        examClassroom.setExamClassroomName(examClassroomName);
                        examClassroom.setNumber(number);
                        examClassroom.setMaxNumber(maxNumber);
                        if ("启用".equals(state)) {
                            examClassroom.setState(true);
                        } else {
                            examClassroom.setState(false);
                        }
                        list.add(examClassroom);
                    }
                }
            }
            if (StringUtils.isNotEmpty(msg)) {
                return msg;
            } else {
                for (ExamClassroom examClassroom : list
                        ) {
                    this.insert("ExamClassroomMapper.insert", examClassroom);
                }
            }
        } catch (Exception e) {
            for (ExamClassroom examClassroom : list
                    ) {
                this.delete("ExamClassroomMapper.deleteByPrimaryKey", examClassroom.getId());
            }
        }
        return msg;

    }
    // // ======导入 ExamClassroom表 部分===结束====================================
    //
    public String getCell(HSSFCell cell) {
        DecimalFormat df = new DecimalFormat("#");
        if (cell == null)
            return "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                }
                return df.format(cell.getNumericCellValue());
            case HSSFCell.CELL_TYPE_STRING:
                System.out.println(cell.getStringCellValue());
                return cell.getStringCellValue();
            case HSSFCell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case HSSFCell.CELL_TYPE_BLANK:
                return "";
            case HSSFCell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() + "";
            case HSSFCell.CELL_TYPE_ERROR:
                return cell.getErrorCellValue() + "";
        }
        return "";
    }public String getCell(XSSFCell cell) {
        DecimalFormat df = new DecimalFormat("#");
        if (cell == null)
            return "";
        switch (cell.getCellType()) {
            case XSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                }
                return df.format(cell.getNumericCellValue());
            case XSSFCell.CELL_TYPE_STRING:
                System.out.println(cell.getStringCellValue());
                return cell.getStringCellValue();
            case XSSFCell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case XSSFCell.CELL_TYPE_BLANK:
                return "";
            case XSSFCell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() + "";
            case XSSFCell.CELL_TYPE_ERROR:
                return cell.getErrorCellValue() + "";
        }
        return "";
    }

    /**
     * ExamCourse表
     *
     * @return
     */
    // =========导出 ExamCourse表 部分===开始====================================
    public ArrayList<String> getExamCourseHead() {
        ArrayList<String> headlist = new ArrayList<String>();
        headlist.add("考试课程代码");
        headlist.add("考试课程名称");
        headlist.add("备注");
        return headlist;
    }

    private List<ExamCourse> queryAllExamCourse() {

        return this.selectList("ExamCourseMapper.queryAllExamCourseExport", null);
    }

    public ArrayList<ArrayList<String>> getExamCourseData() {
        ArrayList<ArrayList<String>> dataList = new
                ArrayList<ArrayList<String>>();
        List<ExamCourse> examExamCourseList = queryAllExamCourse();
        List<List<String>> list = examCourseJoint(examExamCourseList);
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> al = (ArrayList<String>) list.get(i);
            dataList.add(al);
        }
        return dataList;

    }

    // 辅助函数
    private List<List<String>> examCourseJoint(
            List<ExamCourse> examExamCourseList) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < examExamCourseList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(examExamCourseList.get(i).getCourseCode() + "");// courseCode
            small.add(examExamCourseList.get(i).getCourseName() + "");// courseName
            small.add(examExamCourseList.get(i).getMemo() + "");// memo
            list.add(small);
        }
        return list;
    }

    @Autowired
    ExamCourseService examCourseService;

    // // ======导出 ExamCourse表 部分===结束====================================
    //
    // ======导入 ExamCourse表 部分===开始====================================
    public String updateExcelToExamCourse(HttpServletRequest request,
                                          MultipartFile myFile) throws Exception {

        String msg = "";
        List<ExamCourse> list = new ArrayList<>();
        try (InputStream inputStream = myFile.getInputStream()) {


            HSSFWorkbook wookbook = new HSSFWorkbook(inputStream);
            Iterator<Sheet> iterator = wookbook.iterator();
            int count = 0;
            while (iterator.hasNext()) {
                count++;
                iterator.next();
            }
            for (int i = 0; i < count; i++) {
                HSSFSheet sheet = wookbook.getSheetAt(i);
                int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
                if (rows == 0) {
                    throw new BaseException("请填写数据");
                }
                for (int j = 1; j <= rows + 1; j++) {
                    // 读取左上端单元格
                    HSSFRow row = sheet.getRow(j);
                    // 行不为空
                    if (row != null) {
                        // **读取cell**
                        String courseCode = getStringCellValue(row.getCell((short) 0));//
                        if (StringUtils.isEmpty(courseCode)) {
                            msg += "<br>" + "第" + j + "行课程代码不能为空";
                        }

                        String courseName = getStringCellValue(row.getCell((short) 1));//
                        if (StringUtils.isEmpty(courseName)) {
                            msg += "<br>" + "第" + j + "行课程名称不能为空";
                        }
                        ExamCourse course = examCourseService.queryCourseByCode(courseCode);
                        if (ObjectUtils.isNotNull(course)) {
                            msg += "<br>" + "第" + j + "行课程代码在数据库中已存在";
                        }
                        String memo = getStringCellValue(row.getCell((short) 2));// memo
                        ExamCourse examCourse = new ExamCourse();
                        examCourse.setCourseCode(courseCode);
                        examCourse.setCourseName(courseName);
                        examCourse.setMemo(memo);
                        list.add(examCourse);
                        /**/
                    }
                }
            }
            if (StringUtils.isNotEmpty(msg)) {
                return msg;
            }
            for (ExamCourse examCourse : list
                    ) {
                this.insert("ExamCourseMapper.insert", examCourse);
            }

        } catch (Exception e) {
            for (ExamCourse examCourse : list
                    ) {
                this.insert("ExamCourseMapper.deleteByPrimaryKey", examCourse.getId());
            }
        }
        return msg;
    }

    // // ======导入 ExamCourse表 部分===结束====================================
    //
    // /**
    // * ExamManager表
    // *
    // * @return
    // */
    // // =========导出 ExamManager表 部分===开始====================================
    public ArrayList<String> getExamManagerHead() {
        ArrayList<String> headlist = new ArrayList<String>();
        headlist.add("考试名称");
        headlist.add("预约开始时间");
        headlist.add("预约结束时间");
        headlist.add("是否开启科目限制");
        headlist.add("状态");
        headlist.add("备注");
        return headlist;

    }

    private List<ExamManager> queryAllExamManager() {

        return this.selectList("ExamManagerMapper.queryAllExamManager", null);
    }

    public ArrayList<ArrayList<String>> getExamManagerData() {
        ArrayList<ArrayList<String>> dataList = new
                ArrayList<ArrayList<String>>();
        List<ExamManager> examManagerList = queryAllExamManager();
        List<List<String>> list = examManagerListJoint(examManagerList);
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> al = (ArrayList<String>) list.get(i);
            dataList.add(al);
        }
        return dataList;

    }

    // 辅助函数
    private List<List<String>> examManagerListJoint(
            List<ExamManager> examManagerList) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < examManagerList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(examManagerList.get(i).getExamName() + "");// examName
            small.add(examManagerList.get(i).getAppointmentBeginTime() + "");//
//	 appointmentBeginTime
            small.add(examManagerList.get(i).getAppointmentEndTime() + "");//
//	 appointmentEndTime
            small.add(examManagerList.get(i).getIsCourseController() + "");//
//	 isCourseController
            small.add(examManagerList.get(i).getState() + "");// state
            small.add(examManagerList.get(i).getMemo() + "");// memo
            list.add(small);
        }
        return list;
    }

    // ======导出 ExamManager表 部分===结束====================================

    // ======导入 ExamManager表 部分===开始====================================
    public String updateExcelToExamManager(HttpServletRequest request,
                                           MultipartFile myFile) throws Exception {

        String msg = "";
        List<ExamManager> examManagers=new ArrayList<ExamManager>();
        try(InputStream inputStream=myFile.getInputStream() ){
        HSSFWorkbook wookbook = new HSSFWorkbook(inputStream);
           Iterator<Sheet> iterator= wookbook.iterator();
            int count=0;
         if (iterator.hasNext()){
             count++;
             iterator.next();
         }
            for (int i = 0; i < count; i++) {

                HSSFSheet sheet=wookbook.getSheetAt(i);
        int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
        if (rows == 0&&i==0) {
            throw new BaseException("请填写数据");
        }
        for (int j = 1; j <= rows + 1; j++) {
            // 读取左上端单元格
            HSSFRow row = sheet.getRow(j);
            // 行不为空
            if (row != null) {
                // **读取cell**
                String examName = getStringCellValue(row.getCell((short) 0));// examName
                Date appointmentBeginTime = getDateCellValue(row
                        .getCell((short) 1));// appointmentBeginTime
                Date appointmentEndTime = getDateCellValue(row
                        .getCell((short) 2));// appointmentEndTime
                Boolean isCourseController = getBoolCellValue(row
                        .getCell((short) 3));// isCourseController
                Boolean state = getBoolCellValue(row.getCell((short) 4));// state
                String memo = getStringCellValue(row.getCell((short) 5));// memo

                ExamManager examManager = new ExamManager();
                examManager.setExamName(examName);
                examManager.setAppointmentBeginTime(appointmentBeginTime);
                examManager.setAppointmentEndTime(appointmentEndTime);
                examManager.setIsCourseController(isCourseController);
                examManager.setState(state);
                examManager.setMemo(memo);
                examManagers.add(examManager);
            }
        }

        }
        if ("".equals(msg)){
            return msg;
        }else {
            for (ExamManager examManager:examManagers
                 ) {
                this.insert("ExamManagerMapper.insert", examManager);
            }
        }
        }
        catch (Exception e){
            for (ExamManager examManager:examManagers
                 ) {
                this.insert("ExamManagerMapper.insert", examManager);
            }
            return "系统异常";
        }
        return msg;
    }
    // ======导入 ExamManager表 部分===结束====================================
    /**
     * ExamSeat表
     *
     * @return
     */
    // =========导出 ExamSeat表 部分===开始====================================
    public ArrayList<String> getExamSeatHead() {
        ArrayList<String> headlist = new ArrayList<String>();
        headlist.add("考场座位编号");
        headlist.add("所属考场名称");
        headlist.add("是否被选");
        headlist.add("考试id");
        return headlist;
    }
    private List<ExamSeat> queryAllExamSeat() {
        return this.selectList("ExamSeatMapper.queryAllExamSeatExport", null);
    }
    public ArrayList<ArrayList<String>> getExamSeatData() {
        ArrayList<ArrayList<String>> dataList = new
                ArrayList<ArrayList<String>>();
        List<ExamSeat> examAppointmentLogList = queryAllExamSeat();
        List<List<String>> list = examSeatJoint(examAppointmentLogList);
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> al = (ArrayList<String>) list.get(i);
            dataList.add(al);
        }
        return dataList;
    }

    // 辅助函数
    private List<List<String>> examSeatJoint(List<ExamSeat> examSeatList) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < examSeatList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(examSeatList.get(i).getSeatNo() + "");// seatNo
            small.add(examSeatList.get(i).getExamClassroomName() + "");//
//	 examClassroomName
            small.add(examSeatList.get(i).getState() + "");// state
            small.add(examSeatList.get(i).getExamBatchId() + "");// ExamBatchId
            list.add(small);
        }
        return list;
    }
    // ======导出 ExamSeat表 部分===结束====================================
    @Autowired
    ExamBatchService examBatchService;
    @Autowired
    ExamSeatService examSeatService;
    @Autowired
    ExamClassroomService examClassroomService;
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    // ======导入 ExamSeat表 部分===开始====================================
    public String updateExcelToExamSeat(HttpServletRequest request,
                                        MultipartFile myFile) throws Exception {
        String msg = "";
        List<ExamSeat> list = new ArrayList<>();
        try (InputStream inputStream = myFile.getInputStream();) {
            HSSFWorkbook wookbook = new HSSFWorkbook(inputStream);

            Iterator<Sheet> iterator = wookbook.iterator();
            int count = 0;
            while (iterator.hasNext()) {
                count++;
                iterator.next();
            }
            for (int i = 0; i < count; i++) {
                HSSFSheet sheet = wookbook.getSheetAt(i);
                int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
                if (rows == 0 && i == 0) {
                    throw new BaseException("请填写数据");
                }

                for (int j = 1; j <= rows + 1; j++) {
                    // 读取左上端单元格
                    HSSFRow row = sheet.getRow(j);
                    // 行不为空
                    if (row != null) {
                        // **读取cell**
                        Integer seatNo = getIntCellValue(row.getCell((short) 0));// seatNo
                        List<ExamSeat> examSeats = examSeatService.queryExamSeat(seatNo);

                        if (seatNo == -1) {
                            msg += "<br/>" + "第" + j + "行座位号:" + seatNo + "不能为空";
                        }
                        Integer ExamBatchNo = getIntCellValue(row.getCell((short) 1));// 批次号
                        if (ExamBatchNo == -1) {
                            msg += "<br/>" + "第" + j + "行批次号" + ExamBatchNo + "不能为空";
                        }
                        String examClassroomName = getStringCellValue(row.getCell(2));// 所属考场名称
                        if (StringUtils.isEmpty(examClassroomName)) {
                            msg += "<br/>" + "第" + j + "行所属教室名称" + examClassroomName + "不能为空";
                        }

                        ExamSeat examSeatTest = examSeatService.queryExamSeatBySandN(seatNo, examClassroomName);
                        if (ObjectUtils.isNotNull(examSeatTest)) {
                            msg += "<br>" + "第" + j + "行所属教室对应的座位号已在数据库中存在";
                        }
                        String state = getStringCellValue(row.getCell(3));// 状态
                        if ("".equals(state)) {
                            msg += "<br/>" + "第" + j + "行状态:" +"不能为空";
                        }
                        ExamSeat examSeat = new ExamSeat();
                        examSeat.setSeatNo(seatNo);
                        ExamClassroom examClassroom = examClassroomService.queryClassRoomByName(examClassroomName);
                        if (examClassroom == null) {
                            msg += "<br/>" + "第" + j + "行所属考场名称在考场表里没有";
                        }
                        examSeat.setExamClassroomName(examClassroomName);
                        examSeat.setState("是".equals(state) ? true : false);
                        Integer examBatchId = examBatchService.queryExamBatchId(ExamBatchNo);
                        if (examBatchId == null) {
                            msg += "<br/>" + "第" + j + "行" + "批次号:" + ExamBatchNo + "在数据库中没有对应的";
                        } else {
                            examSeat.setExamBatchId(examBatchId);
                        }
                        list.add(examSeat);
                   /* */
                    }
                }
                if (StringUtils.isEmpty(msg)) {
                    for (ExamSeat examSeat : list
                            ) {
                        this.insert("ExamSeatMapper.insert", examSeat);
                    }
                } else {
                    return msg;
                }

            }
        } catch (Exception e) {
            for (ExamSeat examSeat : list) {
                this.delete("ExamSeatMapper.deleteByPrimaryKey", examSeat.getId());
            }
        }
        return msg;

    }

//	 ======导入 ExamSeat表 部分===结束====================================

    /**
     * ExamStudent表
     *
     * @return
     */
    // =========导出 ExamStudent表 部分===开始====================================
    public ArrayList<String> getExamStudentHead() {
        ArrayList<String> headlist = new ArrayList<String>();
        headlist.add("学号");
        headlist.add("姓名");
        headlist.add("班级");
        headlist.add("身份证号");
        headlist.add("考试课程代码");
        headlist.add("锁定");
        return headlist;
    }

    private List<ExamStudent> queryAllExamStudent() {

        return this.selectList("ExamStudentMapper.queryAllExamStudentExport", null);
    }

    public ArrayList<ArrayList<String>> getExamStudentData() {
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        List<ExamStudent> examStudentList = queryAllExamStudent();
        List<HyberExamStudent> hyberExamStudents=ChanggetoHyberExmaStudent(examStudentList);
        List<List<String>> list = examStudentJoint(hyberExamStudents);
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> al = (ArrayList<String>) list.get(i);
            dataList.add(al);
        }
        return dataList;
    }
    public List<HyberExamStudent> ChanggetoHyberExmaStudent(List<ExamStudent> examStudentList){
        List<HyberExamStudent> list=new ArrayList<>();
        for (ExamStudent examStudent:examStudentList
                ) {
            HyberExamStudent hyber=new HyberExamStudent();

            Integer id = userService.queryUserByNoNotPage(examStudent.getStudentNo());
           User user= userService.queryUserById(id);
            if(ObjectUtils.isNotNull(user)){

                hyber.setIdcard(user.getIdCard());
            }else {
                hyber.setIdcard("暂无");
            }
            hyber.setStudentNo(user.getNo());
            hyber.setStudentName(user.getUserName());
            hyber.setStudentClass(examStudent.getStudentClass());
            hyber.setCourseName(examStudent.getCourseName());
            hyber.setLock(examStudent.getLock());
            hyber.setCourseCode(examStudent.getCourseCode());
            list.add(hyber);
        }
        return list;
    }
    // 辅助函数
    private List<List<String>> examStudentJoint(
            List<HyberExamStudent> examStudentList) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < examStudentList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(examStudentList.get(i).getStudentNo() + "");// studentNo
            small.add(examStudentList.get(i).getStudentName() + "");// studentName
            small.add(examStudentList.get(i).getStudentClass() + "");// studentClass
            small.add(examStudentList.get(i).getIdcard() + "");// studentClass

            small.add(examStudentList.get(i).getCourseCode() + "");// courseCode
            small.add(examStudentList.get(i).getLock() == true ? "锁定" : "未锁定"
                    + "");// lock

            list.add(small);
        }
        return list;
    }

    @Autowired
    UserService userService;

    // ======导出 ExamStudent表 部分===结束====================================

    // ======导入 ExamStudent表 部分===开始====================================
    public String updateExcelToExamStudent(HttpServletRequest request,
                                           MultipartFile myFile) throws Exception {

        // 返回的结果集
        List<ExamStudent> examStudentList = new ArrayList<ExamStudent>();

        String msg = "";
        try (InputStream inputStream = myFile.getInputStream();) {
            XSSFWorkbook wookbook = new XSSFWorkbook(inputStream);
            Iterator<Sheet> iterator = wookbook.iterator();
            Integer count = 0;
            while (iterator.hasNext()) {
                count++;
                iterator.next();
            }
            for (int i = 0; i < count; i++) {


                XSSFSheet sheet = wookbook.getSheetAt(i);

                int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
                if (rows == 0 && i == 0) {
                    throw new BaseException("请填写数据");
                }
                for (int j = 1; j <= rows + 1; j++) {
                    // 读取左上端单元格
                    XSSFRow row = sheet.getRow(j);
                    // 行不为空
                    if (row != null) {
                        // **读取cell**
                        String studentNo = getStringCellValue(row.getCell((short) 0));// studentNo学号
                        String studentName = getStringCellValue(row.getCell((short) 1));// studentName姓名
                        String studentClass = getStringCellValue(row.getCell((short) 2));// studentClass班级
                        String courseCode = getStringCellValue(row.getCell((short) 3));// courseCode考试课程代码
                        String lock = getStringCellValue(row.getCell((short) 4));//
                        if(studentNo.isEmpty()&&studentName.isEmpty()&&studentClass.isEmpty()&&courseCode.isEmpty()&&lock.isEmpty()){
                            continue;
                        }
                        // lock锁定
                        Boolean lock1 = true;
                        // 校验
                        // ===============================
                        if (studentNo == null || "".equals(studentNo)) {
                            msg += "第" + (j+1) + "行  学号  不能为空<br/>";
                        } else {
                            // 查重检验
                            if (checkStudentNo(studentNo)) {
                                // true为重复了。
                                msg += "第" + (j +1)+ "行  学号  在数据库 中 已存在 <br/>";
                                // false为没有重复。
                            }
                        }
                        if (ObjectUtils.isNull(studentName) || "".equals(studentName)) {
                            msg += "第" + (j+1) + "行  姓名  不能为空<br/>";
                        }
                        // 班级
                        if (StringUtils.isEmpty(studentClass)) {
                            msg += "第" + (j +1)+ "行  班级  不能为空<br/>";
                        }
                        Integer id = userService.queryUserByNoNotPage(studentNo);
                        if (id == null) {
                            msg += "<br>" + "第" + (j+1) + "行" + "学号:"+studentNo+"在edu_user表里不存在<br/>";
                        }
                        List<Integer> integerList = stuClassService.queryClassIdByName(studentClass);
                        if (integerList.isEmpty()) {
                            msg += "第" + (j +1)+ "行  班级 在班级表里不存在<br/>";
                        }

                        // 考试课程代码
                        if (StringUtils.isBlank(courseCode)) {
                            msg += "第" + (j+1) + "行  考试课程代码  不能为空<br/>";
                        }
                        ExamCourse examCourse = examCourseService.queryCourseByCode(courseCode);
                        if (examCourse == null) {
                            msg += "第" + (j +1)+ "行  考试课程代码在考试课程表里不存在<br/>";
                        }
                        // 锁定
                        if (ObjectUtils.isNull(lock) || lock.equals("")) {
                            msg += "第" + (j+1) + "行  锁定  不能为空<br/>";
                        } else {
                            if (lock.equals("未锁定")) {
                                lock1 = false;
                            } else {
                                lock1 = true;
                            }
                        }

                        // 单个实体
                        ExamStudent examStudent = new ExamStudent();
                        examStudent.setCourseName(examCourse.getCourseName());
                        examStudent.setStudentNo(studentNo);
                        examStudent.setStudentName(studentName);
                        examStudent.setStudentClass(studentClass);
                        examStudent.setCourseCode(courseCode);
                        examStudent.setLock(lock1);
                        // 将实体添加到结果集中
                        examStudentList.add(examStudent);

                    }
                }
            }
            if (msg == null || "".equals(msg)) {
                // --------------批量保存
                for (ExamStudent examStudent : examStudentList) {
                    this.insert("ExamStudentMapper.insert", examStudent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            for (ExamStudent examStudent : examStudentList
                    ) {
                this.delete("ExamStudentMapper.deleteByPrimaryKey", examStudent.getId());
            }
            msg += "系统异常";
          /*  throw new BaseException("请填写数据");*/
        }
        return msg;
    }

    // 辅助函数，查重
    private boolean checkStudentNo(String studentNo) {

        List<ExamStudent> examStudentList = this.selectList(
                "ExamStudentMapper.selectByStudentNo", studentNo);

        if (examStudentList.size() == 0 || examStudentList.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }
    // ======导入 ExamStudent表 部分===结束====================================

    /**
     * ExamStudentAppointment表
     *
     * @return
     */
    // =========导出 ExamStudentAppointment表
    // 部分===开始====================================
    public ArrayList<String> getExamStudentAppointmentHead() {
        ArrayList<String> headlist = new ArrayList<String>();
        headlist.add("学号");
        headlist.add("姓名");
        headlist.add("班级");
        headlist.add("学院");
        headlist.add("实验室");
        headlist.add("时间");
        headlist.add("批次号");
        headlist.add("座位号");
       headlist.add("备注");
        return headlist;
    }
    private List<ExamStudentAppointment> queryAllExamStudentAppointment(int id) {

        return this.selectList(
                "ExamStudentAppointmentMapper.queryAllExamStudentAppointmentById",
                id);
    }
    private List<ExamStudentAppointment> queryAllExamStudentAppointmentByExamManagerName(String examName) {

        return this.selectList(
                "ExamStudentAppointmentMapper.queryAllExamStudentAppointmentByExamManagerName",
                examName);
    }
    public ArrayList<ArrayList<String>> getExamStudentAppointmentData(int id) {
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        List<ExamStudentAppointment> examStudentAppointmentList = queryAllExamStudentAppointment(id);
        List<HyberExamSudentAppointment> hyberlist=ChanggetoHyber(examStudentAppointmentList);

        List<List<String>> list = examStudentAppointmentJoint(hyberlist);
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> al = (ArrayList<String>) list.get(i);
            dataList.add(al);
        }
        return dataList;
    }
    public ArrayList<ArrayList<String>> getExamStudentAppointmentDataByName(String name) {
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        List<ExamStudentAppointment> examStudentAppointmentList = queryAllExamStudentAppointmentByExamManagerName(name);
        List<HyberExamSudentAppointment> hyberlist=ChanggetoHyber(examStudentAppointmentList);
        List<List<String>> list = examStudentAppointmentJoint(hyberlist);
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> al = (ArrayList<String>) list.get(i);
            dataList.add(al);
        }
        return dataList;
    }
    @Autowired
    private StuClassService stuClassService;
    @Autowired
    private CollegeService college;
    //加上学院
    protected  List<HyberExamSudentAppointment> ChanggetoHyber(List<ExamStudentAppointment> examStudentAppointmentList){
        List<HyberExamSudentAppointment> list=new ArrayList<>();
        for (ExamStudentAppointment examStudentAppointment:examStudentAppointmentList
             ) {
            HyberExamSudentAppointment hyber=new HyberExamSudentAppointment();

            List<Integer> integerList = stuClassService.queryClassIdByName(examStudentAppointment.getStudentClass());
            if(ObjectUtils.isNotNull(integerList)){
                StuClass stuClassById = stuClassService.getStuClassById(integerList.get(0));
                College collegeById = college.getCollegeById(stuClassById.getSchoolId());
                hyber.setCollegeName(collegeById.getCollegeName());
            }else {
                hyber.setCollegeName("暂无");
            }
            hyber.setBatchNo(examStudentAppointment.getBatchNo());
            hyber.setSeatNo(examStudentAppointment.getSeatNo());
            hyber.setExamBeginEndTimes(examStudentAppointment.getExamBeginEndTimes());
            hyber.setExamClassroom(examStudentAppointment.getExamClassroom());
            hyber.setExamDate(examStudentAppointment.getExamDate());
            hyber.setStudentClass(examStudentAppointment.getStudentClass());
            hyber.setStudentNo(examStudentAppointment.getStudentNo());
            hyber.setStudentName(examStudentAppointment.getStudentName());
            hyber.setMemo(examStudentAppointment.getMemo());

            list.add(hyber);
        }
        return list;
    }
    // 辅助函数
    private List<List<String>> examStudentAppointmentJoint(
            List<HyberExamSudentAppointment> examStudentAppointmentList) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < examStudentAppointmentList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(examStudentAppointmentList.get(i).getStudentNo() + "");// studentNo
            small.add(examStudentAppointmentList.get(i).getStudentName() + "");// studentName
            small.add(examStudentAppointmentList.get(i).getStudentClass() + "");// studentClass
            small.add(examStudentAppointmentList.get(i).getCollegeName()+"");//collegeName
            small.add(examStudentAppointmentList.get(i).getExamClassroom() + "");// examClassroom
            small.add(sdf.format(examStudentAppointmentList.get(i).getExamDate())
                    +" "+ examStudentAppointmentList.get(i).getExamBeginEndTimes());// examDate
          small.add(examStudentAppointmentList.get(i).getBatchNo() + "");// batchNo
            small.add(examStudentAppointmentList.get(i).getSeatNo() + "");// seatNo*/
           small.add(examStudentAppointmentList.get(i).getMemo());
            /*small.add(examStudentAppointmentList.get(i).getBatchNo() + "");// batchNo
            small.add(examStudentAppointmentList.get(i).getSeatNo() + "");// seatNo*/
            list.add(small);
        }
        return list;
    }

    // ======导出 ExamStudentAppointment表
    // 部分===结束====================================

    // ======导入 ExamStudentAppointment表
    // 部分===开始====================================
    public String updateExcelToExamStudentAppointment(
            HttpServletRequest request, MultipartFile myFile) throws Exception {
        String msg = "";
        HSSFWorkbook wookbook = new HSSFWorkbook(myFile.getInputStream());
        HSSFSheet sheet = wookbook.getSheetAt(0);
        List<ExamStudentAppointment> examStudentAppointmentList = new ArrayList<ExamStudentAppointment>();

        int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
        if (rows == 0) {
            throw new BaseException("请填写数据");
        }
        for (int i = 1; i <= rows + 1; i++) {
            // 读取左上端单元格
            HSSFRow row = sheet.getRow(i);
            // 行不为空
            if (row != null) {
                // **读取cell**
                String studentNo = getStringCellValue(row.getCell((short) 0));// studentNo
                String studentName = getStringCellValue(row.getCell((short) 1));// studentName
                String studentClass = getStringCellValue(row.getCell((short) 2));// studentClass
                String examClassroom = getStringCellValue(row
                        .getCell((short) 3));// examClassroom
                String examDate = getStringCellValue(row.getCell((short) 4));// examDate
                Date examDate1 = null;
                String examBeginEndTimes = getStringCellValue(row
                        .getCell((short) 5));// examBeginEndTimes
                String batchNo = getStringCellValue(row.getCell((short) 6));// batchNo
                Integer batchNo1 = null;
                String seatNo = getStringCellValue(row.getCell((short) 7));// seatNo
                Integer seatNo1 = null;
                String state = getStringCellValue(row.getCell((short) 8));// state
                Boolean state1 = null;
                // 校验
                // ===============================

                if (studentNo == null || studentNo.equals("")) {
                    msg += "第" + i + "行  学号  不能为空<br/>";
                } else {
                    // 查重检验
                    if (checkStudentNo2(studentNo)) {
                        // true为重复了。
                        msg += "第" + i + "行  学号  在数据库 中 已存在 <br/>";
                        // false为没有重复。
                    }

                }
                if (studentName == null || studentName.equals("")) {
                    msg += "第" + i + "行  姓名  不能为空<br/>";
                }
                if (studentClass == null || studentClass.equals("")) {
                    msg += "第" + i + "行  班级  不能为空<br/>";
                }
                if (examClassroom == null || examClassroom.equals("")) {
                    msg += "第" + i + "行  考场  不能为空<br/>";
                }
                if (examDate == null || examDate.equals("")) {
                    msg += "第" + i + "行  预约日期  不能为空<br/>";

                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    try {
                        examDate1 = sdf.parse(examDate);
                    } catch (Exception e) {
                        msg += "第" + i + "行  预约日期  格式有错误<br/>";
                    }
                }
                if (examBeginEndTimes == null || examBeginEndTimes.equals("")) {
                    msg += "第" + i + "行  预约时间  不能为空<br/>";
                }
                if (batchNo == null || batchNo.equals("")) {
                    msg += "第" + i + "行  批次  不能为空<br/>";
                } else {
                    batchNo1 = Integer.parseInt(batchNo);
                }
                if (seatNo == null || seatNo.equals("")) {
                    msg += "第" + i + "行  座位号  不能为空<br/>";
                } else {
                    seatNo1 = Integer.parseInt(seatNo);
                }
                if (state == null || state.equals("")) {
                    msg += "第" + i + "行  预约状态  不能为空<br/>";
                } else {
                    if (state.equals("锁定")) {
                        state1 = true;
                    } else {
                        state1 = false;
                    }
                }

                ExamStudentAppointment examStudentAppointment = new ExamStudentAppointment();
                examStudentAppointment.setStudentNo(studentNo);
                examStudentAppointment.setStudentName(studentName);
                examStudentAppointment.setStudentClass(studentClass);
                examStudentAppointment.setExamClassroom(examClassroom);
                examStudentAppointment.setExamDate(examDate1);
                examStudentAppointment.setExamBeginEndTimes(examBeginEndTimes);
                examStudentAppointment.setBatchNo(batchNo1);
                examStudentAppointment.setSeatNo(seatNo1);
                examStudentAppointment.setState(state1);
                examStudentAppointmentList.add(examStudentAppointment);
            }
        }

        if (msg == null || msg.equals("")) {
            // --------------批量保存
            this.insert("ExamStudentAppointmentMapper.insertList",
                    examStudentAppointmentList);
        }

        return msg;
    }

    // 辅助函数
    private boolean checkStudentNo2(String studentNo) {
        List<ExamStudentAppointment> examStudentAppointmentList = this
                .selectList("ExamStudentAppointmentMapper.selectByStudentNo",
                        studentNo);
        if (examStudentAppointmentList.size() == 0
                || examStudentAppointmentList.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    // || !studentNo.equals(examStudentAppointmentList.get(0)
    // .getStudentNo())

    // // ======导入 ExamStudentAppointment表
    // // 部分===结束====================================
    //
    // // 辅助函数====开始================================
    //
    private Date getDateCellValue(HSSFCell cell) {
        String date = getStringCellValue(cell);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date newDate = new Date();
        try {
            newDate = sdf.parse(date);
        } catch (Exception var5) {
            var5.printStackTrace();
        }
        return newDate;

    }

    private Boolean getBoolCellValue(HSSFCell cell) {

        String value_s = getStringCellValue(cell);

        if ("0".equals(value_s)) {
            return false;

        } else {
            return true;
        }

    }

    // /**
    // * 获得Hsscell内容 获取string类型的值
    // *
    // * @param cell
    // * @return
    // */
    public String getStringCellValue(HSSFCell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_FORMULA:
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    DecimalFormat df = new DecimalFormat("0");
                    value = df.format(cell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue().trim();
                    break;
                default:
                    value = "";
                    break;
            }
        }
        return value.trim();
    }
    public String getStringCellValue(XSSFCell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case XSSFCell.CELL_TYPE_FORMULA:
                    break;
                case XSSFCell.CELL_TYPE_NUMERIC:
                    DecimalFormat df = new DecimalFormat("0");
                    value = df.format(cell.getNumericCellValue());
                    break;
                case XSSFCell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue().trim();
                    break;
                default:
                    value = "";
                    break;
            }
        }
        return value.trim();
    }
    //
    // /**
    // * 获得Hsscell内容 转换成int类型
    // *
    // * @param cell
    // * @return
    // */
    public int getIntCellValue(HSSFCell cell) {
        String stringValue = getStringCellValue(cell);

        int intValue = -1;
        try {
            intValue = Integer.parseInt(stringValue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return intValue;
    } public int getIntCellValue(XSSFCell cell) {
        String stringValue = getStringCellValue(cell);

        int intValue = -1;
        try {
            intValue = Integer.parseInt(stringValue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return intValue;
    }

    public Integer getIntegerCellValue(String value_s) {

        Integer intValue = -1;
        try {
            intValue = Integer.parseInt(value_s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return intValue;
    }

    public float getFloatCellValue(HSSFCell cell) {
        String stringValue = getStringCellValue(cell);

        float floatValue = -1;
        try {
            floatValue = Float.parseFloat(stringValue);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return floatValue;
    }
}
// 辅助函数====结束================================}


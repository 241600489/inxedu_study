package com.inxedu.os.nstar.appointment.service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.examClassroom.ExamClassroom;
import com.inxedu.os.nstar.appointment.entity.examSeat.ExamSeat;
import com.inxedu.os.nstar.appointment.entity.examStudentAppointment.ExamStudentAppointment;

import java.util.List;

public interface ExamSeatService{
    public    ExamSeat[][] queryExamSeat(String examClassroomName, int column);

    public List<ExamSeat> queryExamSeat(String examClassroomName);

    public  void addExamSeat(ExamSeat examClassroom) ;

    /**
     * 查询所有座位信息
     */
    public  List<ExamSeat> queryAllExamSeat(PageEntity page);
    /**
     * 删除
     */
    public  void  deleteExamSeat(int id);
    /**
     * 修改
     */
    public void  updateExamSeat(ExamSeat examSeat);
    /**
     * 根据座位号查询
     */
    public List<ExamSeat>  queryExamSeat(Integer seatNo);
    /**
     * 根据id查询
     */
    public ExamSeat queryByPrimaryKey(Integer id);
    /**
     * 根据考场名称分页查询
     */
    public  List<ExamSeat> queryExamSeatByexamClassroomName(String examClassroomName, PageEntity page);
    /**
     *根据考场名称和座位号查询
     */
    public ExamSeat queryExamSeatBySandN(int seatNo, String examClassroomName);

    List<ExamSeat> queryAllExamSeatVerlidate();

    List<ExamSeat> queryExamSeatByExamBatchId(Integer examBatchId);

    List<ExamSeat> queryExamSeatByExamBatchIdWithPage(Integer examBatchId, PageEntity page);

    List<ExamSeat> queryExamSeatbySandExambatchId(int seatNo1, Integer examBatchId, PageEntity page);
    ExamSeat queryExamSeatbySeatNOandExamBatchId(int seatNo1, Integer examBatchId);
}


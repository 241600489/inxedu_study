package com.inxedu.os.nstar.appointment.service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.examBatch.ExamBatch;
import com.inxedu.os.nstar.appointment.entity.examClassroom.ExamClassroom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


public  interface  ExamBatchService{

    /**
     * 增加
     */
    public void   addExamBatch(ExamBatch examBatch);

    /**
     * 修改
     */
    public void  updateExamBatch(ExamBatch examBatch);

    /**
     * 删除
     */
    public void deleteExamBatch(int id);

    /**
     * 显示所有数据
     */
    public List<ExamBatch> queryExamBatchList(PageEntity page, int examManagerId);

    /**
     * 根据id获取对象
     */
    public  ExamBatch queryExamBatchById(int id);

    /**
     * 查询所有批次
     */
    public List<ExamBatch> queryAllExamBatch();

    /**
     * 根据考场名称和考试批次查询
     */
    public List<ExamBatch> queryExamClassroomAndExamBatch(String exanName, int examBatchNo, PageEntity page, int examManagerId);


    Integer queryExamBatchId(Integer examBatchNo);

    List<ExamBatch> queryExamBatchByClassRoomName(String examClassroomName);
}

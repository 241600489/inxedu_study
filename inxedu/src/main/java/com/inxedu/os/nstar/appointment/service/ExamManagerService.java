package com.inxedu.os.nstar.appointment.service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.examManager.ExamManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface ExamManagerService{
    /**
     * 增加
     */
    public void addExamManager(ExamManager examManager);

    /**
     * 删除
     */
    public void deleteExamManager(int id);


    /**
     * 修改
     */
    public void updateExamManager(ExamManager examManager);

    /**
     * 显示所有数据
     */
    public List<ExamManager> queryExamManagerList(PageEntity page);

    /**
     * 根据id获取对象
     */
    public ExamManager queryExamManagerById(int id);

    /**
     * 根据考试名称查询
     */
    public List<ExamManager> queryExamManagerByName(PageEntity page, String examName);

    /**
     * 根据考试名称模糊查询
     */
    public List<ExamManager> queryExamManagersByName(PageEntity page, String examName);

    List<ExamManager> queryAllExamManager();

    ExamManager queryExamManagerByExamName(String examName);
}
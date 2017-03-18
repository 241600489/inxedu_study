package com.inxedu.os.nstar.appointment.service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.examAppointmentLog.ExamAppointmentLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface ExamAppointmentLogService{
    /**
     * 添加
     */
    public void addexamAppointmentLog(ExamAppointmentLog examAppointmentLog);
    /**
     * 删除
     */
    public void deleteexamAppointmentLog(int id);
    /**
     * 显示所有数据
     */
    public List<ExamAppointmentLog> queryexamAppointmentLog(PageEntity page);

    /**
     * 根据操作对像模糊查询
     */
    public List<ExamAppointmentLog> queryObjectList(PageEntity page, String object);


}




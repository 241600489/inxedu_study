package com.inxedu.os.nstar.appointment.service.impl;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.examAppointmentLog.ExamAppointmentLog;
import com.inxedu.os.nstar.appointment.service.ExamAppointmentLogService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.net.www.content.text.Generic;

import java.util.List;

/**预约日志管理
 * Created by  on 16-8-5.
 */
@Service
public class ExamAppointmentLogServiceImpl extends GenericDaoImpl implements ExamAppointmentLogService {

    @Override
    public void addexamAppointmentLog(ExamAppointmentLog examAppointmentLog) {
          this.insert("ExamAppointmentLogMapper.insert",examAppointmentLog);
    }

    @Override
    public void deleteexamAppointmentLog(int id) {
        this.delete("ExamAppointmentLogMapper.deleteByPrimaryKey",id);

    }

    @Override
    public List<ExamAppointmentLog> queryexamAppointmentLog(PageEntity page) {
        List<ExamAppointmentLog> list1=this.queryForListPage("ExamAppointmentLogMapper.getExamAppointmentLogList",null,page);

        return list1;
    }


    @Override
    public List<ExamAppointmentLog> queryObjectList(PageEntity page, String object) {
        ExamAppointmentLog examAppointmentLog=new ExamAppointmentLog();
        examAppointmentLog.setObject(object);
        List<ExamAppointmentLog> list=this.queryForListPage("ExamAppointmentLogMapper.queryExamLogByObject",examAppointmentLog,page);
        return list;
    }


}

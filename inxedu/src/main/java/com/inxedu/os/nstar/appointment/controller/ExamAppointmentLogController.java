package com.inxedu.os.nstar.appointment.controller;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.examAppointmentLog.ExamAppointmentLog;
import com.inxedu.os.nstar.appointment.service.ExamAppointmentLogService;
import com.inxedu.os.nstar.appointment.service.VerdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.CacheOperationInvoker;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by  on 16-8-5.
 */
@Controller
@RequestMapping("/admin/appointment/examAppointmentLog")
public class ExamAppointmentLogController {
    @Autowired
    private ExamAppointmentLogService examAppointmentLogService;
    @Autowired
    private VerdateService verdateService;
    final String path = "/appointment/examAppointmentLog/";
    final String redirect = "redirect:"+path;

    @RequestMapping("")
    public String list(final HttpServletRequest request, PageEntity page) {

        List<ExamAppointmentLog> list= examAppointmentLogService.queryexamAppointmentLog(page);
        request.setAttribute("list",list);
        request.setAttribute("page",page);
        return path + "list";
    }




    @RequestMapping("/op/add")
    public String insert(final HttpServletRequest request) {

        return path + "edit";
    }
    @RequestMapping("/add")
    public String add(final HttpServletRequest request,ExamAppointmentLog  examAppointmentLog) {

        examAppointmentLogService.addexamAppointmentLog(examAppointmentLog);

        return "redirect:/admin/appointment/examAppointmentLog";
    }



    @RequestMapping("/op/delete/{id}")
    public String delete(final HttpServletRequest request, @PathVariable("id") String id) {
        int id1=Integer.parseInt(id);
        examAppointmentLogService.deleteexamAppointmentLog(id1);
        return "redirect:/admin/appointment/examAppointmentLog";
    }

    @RequestMapping("/op/del")
    public String delete(final HttpServletRequest request,int ids[]) {
        if (ids == null) {
            return "redirect:/admin/appointment/examAppointmentLog";
        }
        for (int id : ids) {
            examAppointmentLogService.deleteexamAppointmentLog(id);

        }
        return "redirect:/admin/appointment/examAppointmentLog";
    }
    static int getCurrentPage( HttpServletRequest request){
        String n="1";
        Map data = request.getParameterMap();
        try{
            String tmp=((String[]) data.get("page.currentPage"))[0].toString();
            n="".equals(tmp)?"1":tmp;
        }catch (Throwable e){

        }
        return Integer.valueOf(n);
    }
    @RequestMapping("/search")
    public String search(String object, HttpServletRequest request, PageEntity page) {
        page.setCurrentPage(getCurrentPage(request));
        List<ExamAppointmentLog> list = examAppointmentLogService.queryObjectList(page, object);
        request.setAttribute("list", list);
        request.setAttribute("object",object);
        request.setAttribute("page", page);
        return path + "list";
    }
   @RequestMapping("/verlidate")
    @ResponseBody
    public List<String> verlidate(HttpServletRequest request){
       return verdateService.Verlidate();
   }
}

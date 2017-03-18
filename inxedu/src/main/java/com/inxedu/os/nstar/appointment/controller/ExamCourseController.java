package com.inxedu.os.nstar.appointment.controller;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.nstar.appointment.entity.examAppointmentLog.ExamAppointmentLog;
import com.inxedu.os.nstar.appointment.entity.examClassroom.ExamClassroom;
import com.inxedu.os.nstar.appointment.entity.examCourse.ExamCourse;
import com.inxedu.os.nstar.appointment.service.ExamAppointmentLogService;
import com.inxedu.os.nstar.appointment.service.ExamCourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.CacheOperationInvoker;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by  on 16-8-5.
 */
@Controller
@RequestMapping("/admin/appointment/examCourse")
public class ExamCourseController{
    private static final Logger logger = LoggerFactory.getLogger(ExamCourseController.class);
    @Autowired
    private ExamCourseService examCourseService;

    final String path = "/appointment/examCourse/";
    final String redirect = "redirect:"+path;
    /**
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("")
    public ModelAndView list(final HttpServletRequest request, PageEntity page) {
        ModelAndView model =new ModelAndView();
        List<ExamCourse> list= examCourseService.queryAllExamCourse(page);
        request.setAttribute("list",list);
        request.setAttribute("page",page);
         model.setViewName(path + "list");
        request.getSession().setAttribute("courseListUri", WebUtils.getServletRequestUriParms(request));
        return model;
    }

    /**
     * 跳到添加/修改页面
     * @param request
     * @param id 主键add默认为0
     * @param type add/edit
     * @return
     */
    @RequestMapping("/page/{id}/{type}")
    public String editPage(final HttpServletRequest request, @PathVariable("id") String id, @PathVariable("type") String type) {
        logger.debug("ExamCourseController>>>:editPage",id,type);
        switch (type) {
            case "add": {
                request.setAttribute("type", "添加页面");
                break;
            }
            case "edit": {
                request.setAttribute("type", "修改页面");
                int id1 = Integer.parseInt(id);
                ExamCourse examCourse =examCourseService.queryCourseById(id1);
                request.setAttribute("examCourse",examCourse);
                break;
            }
            default: {
                break;
            }
        }
        return path + "edit";
    }

    /**
     * 添加/修改方法
     * @param request
     * @param examCourse
     * @param type true/false true表示修改,false表示添加
     * @return
     * @throws Exception
     */
    @RequestMapping("op/{type}")
    public ModelAndView edit(final HttpServletRequest request,ExamCourse examCourse,@PathVariable("type")String type) throws Exception {
        logger.debug("ExamClassroomController>>>:edit",type);
        ModelAndView model = new ModelAndView();

        switch (type) {
            case "false": {
                examCourseService.addexamCourse(examCourse);
                model.setViewName("redirect:/admin/appointment/examCourse");
                break;
            }
            case "true": {
                 examCourseService.updateExamCourse(examCourse);
                Object uri = request.getSession().getAttribute("courseListUri");
                if(uri!=null){
                    model.setViewName("redirect:"+uri.toString());
                }
                break;
            }
            default: {
                break;
            }


        }


        return model;
    }

    @RequestMapping("/op/delete/{id}")
    public String delete(final HttpServletRequest request, @PathVariable("id") String id) {
        int id1=Integer.parseInt(id);
        examCourseService.deleteexamCourse(id1);
        return "redirect:/admin/appointment/examCourse";
    }

    @RequestMapping("/op/del")
    public String delete(final HttpServletRequest request,int ids[]) {
        if (ids == null) {
            return "redirect:/admin/appointment/examCourse";
        }
        for (int id : ids) {
            examCourseService.deleteexamCourse(id);
        }
        return "redirect:/admin/appointment/examCourse";

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
    public String search(String courseCode, HttpServletRequest request, PageEntity page) {
        page.setCurrentPage(getCurrentPage(request));
        List<ExamCourse> list =examCourseService.queryAllcourseCodeList(page,courseCode);
        request.setAttribute("list",list);
        request.setAttribute("courseCode",courseCode);
        request.setAttribute("page", page);
        return path + "list";
    }
}

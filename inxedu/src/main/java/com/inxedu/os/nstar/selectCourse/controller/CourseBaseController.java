package com.inxedu.os.nstar.selectCourse.controller;


import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.selectCourse.entity.course.CourseBase;
import com.inxedu.os.nstar.selectCourse.service.CourseBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * Created by seapig02 on 7/29 0029.
 */
@Controller
@RequestMapping("/admin/courseBase")
public class CourseBaseController {
    @Autowired
    private CourseBaseService courseBaseService;
    private static final Logger log= LoggerFactory.getLogger(CourseBaseController.class);
    static final String showSelectLessionResult = "/nstar/courseBase/eduCourseList";
    static final String showSelectLessionResult1 = "/nstar/courseBase/addBaseCourse";
    static final String showSelectLessionResult2 = "/nstar/courseBase/updateCourseBase";
    static final String REDIRECT = "redirect:";

    @InitBinder("courseBase")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("courseBase.");
    }

    @InitBinder("page")
    public void initBinderCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("page.");
    }

    @RequestMapping("/list")
    public ModelAndView showeduCourseList(HttpServletRequest request, PageEntity page) throws Exception {
        log.debug("-->CourseBaseController :showeduCourseList{}");
        ModelAndView model = new ModelAndView();

        List<CourseBase> list = courseBaseService.queryAllCourseBase(page);
        model.addObject("list", list);
        model.setViewName(showSelectLessionResult);
        model.addObject("page", page);
        return model;
    }
    static int getCurrentPage(HttpServletRequest request){
        String n="1";
        Map data = request.getParameterMap();
        try{
            String tmp=((String[]) data.get("page.currentPage"))[0].toString();
            n= "".equals(tmp)?"1":tmp;
        }catch (Throwable e){

        }
        return Integer.valueOf(n);
    }
    @RequestMapping("/select")
    public ModelAndView query(HttpServletRequest request, CourseBase courseBase, PageEntity page) throws Exception {

        log.debug("-->CourseBaseController :query{}",courseBase);

        page.setCurrentPage(getCurrentPage(request));

        ModelAndView model = new ModelAndView();
        model.addObject("page", page);
        List<CourseBase> list = courseBaseService.query(courseBase.getCourseName(), courseBase.getCourseNo(), page);
        model.addObject("list", list);
        model.addObject("courseBase",courseBase);
        model.setViewName(showSelectLessionResult);
        return model;
    }

    @RequestMapping("/toaddCourse")
    public ModelAndView addUI(HttpServletRequest request) throws Exception {
        log.debug("-->CourseBaseController :addUI{}");
        ModelAndView model = new ModelAndView();

        model.setViewName(showSelectLessionResult1);
        return model;
    }

    @RequestMapping("/addCourse")
    public ModelAndView addCourse(HttpServletRequest request, CourseBase eduCourseBase) throws Exception {

        ModelAndView model = new ModelAndView();
        courseBaseService.addBaseCourse(eduCourseBase);
        model.setViewName(REDIRECT + "/admin/courseBase/list.action");
        return model;
    }

    @RequestMapping("/toupadateCourse")
    public ModelAndView upadatePage(HttpServletRequest request, Integer id,int currentPage) throws Exception {
        log.debug("-->CourseBaseController :upadatePage{}",id);
        ModelAndView model = new ModelAndView();
        CourseBase eduCourseBase = courseBaseService.queryByPrimaryKey(id);
        model.addObject("currentPage",currentPage);
        model.addObject("eduCourseBase", eduCourseBase);
        model.setViewName(showSelectLessionResult2);
        return model;
    }

    @RequestMapping("/updateCourse")
    public ModelAndView updateCourse(HttpServletRequest request, CourseBase eduCourseBase,int currentPage) throws Exception {
        log.debug("-->CourseBaseController :updateCourse{}",eduCourseBase);
        ModelAndView model = new ModelAndView();
        courseBaseService.updateBaseCourse(eduCourseBase);
        model.setViewName(REDIRECT + "/admin/courseBase/list.action?currentPage="+currentPage);
        return model;
    }

    @RequestMapping("/delete")
    public ModelAndView delete(HttpServletRequest request, Integer id) throws Exception {
        log.debug("-->CourseBaseController :delete{}",id);
        ModelAndView model = new ModelAndView();
        courseBaseService.deleteBaseCourse(id);
        model.setViewName(REDIRECT + "/admin/courseBase/list.action");
        return model;
    }

    @RequestMapping("/deleteCourses")
    public ModelAndView deleteCourses(HttpServletRequest request, int[] ids) throws Exception {
        log.debug("-->CourseBaseController :deleteCourses{}",ids);
        ModelAndView model = new ModelAndView();

        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                courseBaseService.deleteBaseCourse(ids[i]);
            }
        }
        model.setViewName(REDIRECT + "/admin/courseBase/list.action");
        return model;
    }

}

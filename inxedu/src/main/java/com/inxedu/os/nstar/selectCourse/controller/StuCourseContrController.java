package com.inxedu.os.nstar.selectCourse.controller;

import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.nstar.selectCourse.entity.selectCourse.CourseController;
import com.inxedu.os.nstar.selectCourse.service.CourseControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 1 on 2016/7/29.
 */
@Controller
@RequestMapping("/admin/courseController")
public class StuCourseContrController {

    @Autowired
    private CourseControllerService service;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping("/show")
    public String select(HttpServletRequest request) {

        try{

        CourseController controller = service.getEduCourseController();
        String beginCourseSignupTime0 = dateFormat.format(controller.getBeginCourseSignupTime0());
        String beginCourseSignupTime1 = dateFormat.format(controller.getBeginCourseSignupTime1());
        String endCourseSignupTime0 = dateFormat.format(controller.getEndCourseSignupTime0());
        String endCourseSignupTime1 = dateFormat.format(controller.getEndCourseSignupTime1());
        String updateDate = dateFormat.format(controller.getUpdateDate());

        request.setAttribute("controller", controller);
        request.setAttribute("beginCourseSignupTime0", beginCourseSignupTime0);
        request.setAttribute("beginCourseSignupTime1", beginCourseSignupTime1);
        request.setAttribute("endCourseSignupTime0", endCourseSignupTime0);
        request.setAttribute("endCourseSignupTime1", endCourseSignupTime1);
        request.setAttribute("updateDate", updateDate);
    }catch(Throwable e){

        }
        return "/inxedu/admin/CourseContr/CourseContr";
    }

    @RequestMapping("/update")
    public String update(CourseController controller, HttpServletRequest request, String z1, String z2, String b1, String b2) throws ParseException {

        controller.setBeginCourseSignupTime0(dateFormat.parse(z1));
        controller.setBeginCourseSignupTime1(dateFormat.parse(z2));
        controller.setEndCourseSignupTime0(dateFormat.parse(b1));
        controller.setEndCourseSignupTime1(dateFormat.parse(b2));


        SysUser user = SingletonLoginUtils.getLoginSysUser(request);
        String username = user.getUserName();
        controller.setUpdateMan(username);
        controller.setUpdateDate(new Date());
        service.setEduCourseController(controller);
        return "redirect:/admin/courseController/show";
    }
}

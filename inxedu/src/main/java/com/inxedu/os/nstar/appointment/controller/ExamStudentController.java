package com.inxedu.os.nstar.appointment.controller;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.nstar.appointment.entity.examClassroom.ExamClassroom;
import com.inxedu.os.nstar.appointment.entity.examCourse.ExamCourse;
import com.inxedu.os.nstar.appointment.entity.examStudent.ExamStudent;
import com.inxedu.os.nstar.appointment.service.ExamClassroomService;
import com.inxedu.os.nstar.appointment.service.ExamCourseService;
import com.inxedu.os.nstar.appointment.service.ExamStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin/appointment/student")
public class ExamStudentController  extends BaseController{
        private static final Logger logger = LoggerFactory.getLogger(com.inxedu.os.nstar.appointment.controller.ExamClassroomController.class);
        @Autowired
        private ExamStudentService examStudentService;
        @Autowired
        private ExamCourseService examCourseService;
        final String path = "/appointment/examStudent/";

        final String redirect = "redirect:" + path;

        @RequestMapping("")
        public ModelAndView list(HttpServletRequest request, PageEntity page) {
            ModelAndView model=new ModelAndView();
            logger.debug("ExamClassroomController>>>:list",page);
            List<ExamStudent> examStudentList = examStudentService.queryExamStudent(page);
            request.setAttribute("list", examStudentList);
            request.setAttribute("page", page);
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
            logger.debug("ExamClassroomController>>>:editPage",id,type);
            List<ExamCourse> list= examCourseService.queryAllExamCourse();
            request.setAttribute("list",list);
            switch (type) {
                case "add": {
                    request.setAttribute("type", "添加页面");
                    break;
                }
                case "edit": {
                    request.setAttribute("type", "修改页面");
                    int id1 = Integer.parseInt(id);
                    ExamStudent examStudent = examStudentService.queryExamStudentById(id1);
                    request.setAttribute("examStudent", examStudent);
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
         * @param type true/false true表示修改,false表示添加
         * @return
         * @throws Exception
         */
        @RequestMapping("op/{type}")
        public ModelAndView edit(final HttpServletRequest request, ExamStudent examStudent, @PathVariable("type") String type) throws Exception {
            logger.debug("ExamClassroomController>>>:edit",type);
            ModelAndView model = new ModelAndView();
           ExamCourse examCourse= examCourseService.queryCourseByCode(examStudent.getCourseCode());
            if(ObjectUtils.isNotNull(examCourse)){
                examStudent.setCourseName(examCourse.getCourseName());
            }

            switch (type) {
                case "false": {

                    examStudentService.addExamStudent(examStudent);
                    model.setViewName("redirect:/admin/appointment/student");
                    break;
                }
                case "true": {
                    examStudentService.updateExamStudent(examStudent);
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
        /**
         * 删除
         * @param request
         * @param id
         * @return
         */

        @RequestMapping("/op/delete/{id}")
        public String delete(final HttpServletRequest request, @PathVariable("id") String id) {
            logger.debug("ExamClassroomController>>>:delete",id);
            int id1 = Integer.parseInt(id);
            examStudentService.deleteExamStudent(id1);
            return "redirect:/admin/appointment/student";
        }
        /**
         * 批量删除
         *
         */

        @RequestMapping("/op/del")
        public String del(final HttpServletRequest request, int ids[]) {
            logger.debug("ExamClassroomController>>>:del",ids);
            if (ids == null) {

                return "redirect:/admin/appointment/student";
            }
            for (int id : ids) {
                examStudentService.deleteExamStudent(id);

            }

            return "redirect:/admin/appointment/student";
        }

        /**
         * 自定义封装了一个得到当前页的方法
         * @param request
         * @return
         */
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

        /**
         * 查询和模糊查询

         * @param request
         * @param page

         * @return
         */
        @RequestMapping("/search")
        public String search(ExamStudent examStudent, HttpServletRequest request, PageEntity page) {
            page.setCurrentPage(getCurrentPage(request));
            List<ExamStudent> list=examStudentService.queryExamStudentList(page,0,examStudent.getStudentNo(),examStudent.getStudentName(),examStudent.getStudentClass());
            request.setAttribute("examStudent",examStudent);
            request.setAttribute("list",list);
            request.setAttribute("page", page);
            return path + "list";
        }
    @RequestMapping("/changeOne/{state}/{id}")
    @ResponseBody
    public  Map<String,Object> changge(HttpServletRequest request,@PathVariable("id") int id,@PathVariable("state") String state){
        String ajax="";
       ExamStudent examStudent= examStudentService.queryExamStudentById(id);
        try {
            if (examStudent.getLock())
            {
                examStudent.setLock(false);

            }
            else {
                examStudent.setLock(true);

            }
            examStudentService.updateExamStudent(examStudent);
            ajax="修改成功";
        }
        catch (Exception e){
            ajax="修改失败";
        }
        return  this.setJson(true,ajax,null);
    }
    }






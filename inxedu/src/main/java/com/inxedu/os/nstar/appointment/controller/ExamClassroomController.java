package com.inxedu.os.nstar.appointment.controller;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.examClassroom.ExamClassroom;
import com.inxedu.os.nstar.appointment.entity.examSeat.ExamSeat;
import com.inxedu.os.nstar.appointment.service.ExamClassroomService;
import com.inxedu.os.nstar.selectCourse.entity.college.College;
import com.inxedu.os.nstar.selectCourse.entity.stuClass.HyperStuClass;
import com.inxedu.os.nstar.selectCourse.entity.stuClass.StuClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/** 考场管理
 * Created by  on 2016/8/3.
 */
@Controller
@RequestMapping("/admin/appointment/examClassroom")
public class ExamClassroomController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ExamClassroomController.class);
    @Autowired
    private ExamClassroomService examClassroomService;
    final String path = "/appointment/examClassroom/";

    final String redirect = "redirect:" + path;
    @RequestMapping("")
    public String list(HttpServletRequest request, PageEntity page) {
        logger.debug("ExamClassroomController>>>:list",page);
        List<ExamClassroom> examClassroomList = examClassroomService.queryexamClassRooom(page);
        request.setAttribute("list", examClassroomList);
        request.setAttribute("page", page);
        return path + "list";
    }
    /**
     * 跳到添加/修改页面
     * @param request
     * @param id 主键add默认为0
     * @param type add/edit
     * @return
     */
    @RequestMapping("/page/{id}/{type}/{currentPage}")
    public String editPage(final HttpServletRequest request, @PathVariable("id") String id, @PathVariable("type") String type,@PathVariable("currentPage") int currentPage) {
        logger.debug("ExamClassroomController>>>:editPage",id,type);

        switch (type) {
            case "add": {
                request.setAttribute("type", "添加页面");
                break;
            }
            case "edit": {
                request.setAttribute("type", "修改页面");
                int id1 = Integer.parseInt(id);
                ExamClassroom examClassroom = examClassroomService.queryClassRooomById(id1);
                request.setAttribute("examClassroom", examClassroom);
                request.setAttribute("currentPage",currentPage);
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
     * @param examClassroom
     * @param type true/false true表示修改,false表示添加
     * @return
     * @throws Exception
     */
    @RequestMapping("op/{type}/{currentPage}")
    public ModelAndView edit(final HttpServletRequest request, ExamClassroom examClassroom, @PathVariable("type") String type,@PathVariable("currentPage")int currentPage) throws Exception {
        logger.debug("ExamClassroomController>>>:edit",type);
        ModelAndView model = new ModelAndView();
        switch (type) {
            case "false": {
                examClassroomService.addexamClassRooom(examClassroom);
                break;
            }
            case "true": {
                examClassroomService.updateexamClassRooom(examClassroom);
                break;
            }
            default: {
                break;
            }

        }
        model.setViewName("redirect:/admin/appointment/examClassroom?currentPage="+currentPage);
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
        examClassroomService.deleteexamClassRooom(id1);
        return "redirect:/admin/appointment/examClassroom";
    }
    /**
     * 批量删除
     *
      */
    @RequestMapping("/op/del")
    public String del(final HttpServletRequest request, int ids[]) {
        logger.debug("ExamClassroomController>>>:del",ids);
        if (ids == null) {

            return "redirect:/admin/appointment/examClassroom";
        }
        for (int id : ids) {
            examClassroomService.deleteexamClassRooom(id);

        }

        return "redirect:/admin/appointment/examClassroom";
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
     * @param examClassroomName
     * @param request
     * @param page
     * @param start 表示启用模糊查询/或不启用
     * @return
     */
    @RequestMapping("/search")
    public String search(String examClassroomName, HttpServletRequest request, PageEntity page, int start) {
        page.setCurrentPage(getCurrentPage(request));
        request.setAttribute("examClassroomName",examClassroomName);
        switch (start) {
            case 0: {
                List<ExamClassroom> list = examClassroomService.queryClassRoomList(page, examClassroomName);
                request.setAttribute("list", list);
                break;
            }
            case 1: {

                List<ExamClassroom> list = examClassroomService.queryClassSbRoomList(page, examClassroomName);
                request.setAttribute("list", list);
                break;
            }
            default: {
                break;
            }
        }
            request.setAttribute("page", page);
        return path + "list";
    }
    @RequestMapping("/changeOne/{state}/{id}")
    @ResponseBody
    public  Map<String,Object> changge(HttpServletRequest request,@PathVariable("id") int id,@PathVariable("state") String state){
        String ajax="";
        ExamClassroom examClassroom= examClassroomService.queryClassRooomById(id);
        try {
            if (examClassroom.getState())
            {
                examClassroom.setState(false);
                examClassroomService.updateexamClassRooom(examClassroom);
            }
            else {
                examClassroom.setState(true);
                examClassroomService.updateexamClassRooom(examClassroom);
            }
            ajax="修改成功";
        }
        catch (Exception e){
            ajax="修改失败";
        }
        return  this.setJson(true,ajax,null);
    }
}


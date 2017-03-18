package com.inxedu.os.nstar.appointment.controller;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.nstar.appointment.entity.examBatch.ExamBatch;
import com.inxedu.os.nstar.appointment.entity.examClassroom.ExamClassroom;
import com.inxedu.os.nstar.appointment.entity.examCourse.ExamCourse;
import com.inxedu.os.nstar.appointment.entity.examManager.ExamManager;
import com.inxedu.os.nstar.appointment.entity.examSeat.ExamSeat;
import com.inxedu.os.nstar.appointment.service.*;
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

/**考试批次管理
 * Created by Nstar on 2016/8/3.
 */
@Controller
@RequestMapping("/admin/appointment/batch")
public class ExamBatchController extends BaseController{
    @Autowired
    private ExamBatchService examBatchService;
    @Autowired
    private ExamManagerService examManagerService;
    @Autowired
    private ExamClassroomService examClassroomService;
    @Autowired
    private ExamCourseService examCourseService;

    private static Logger log = LoggerFactory.getLogger(ExamBatchController.class);

    @RequestMapping("")
    public ModelAndView list(HttpServletRequest request, PageEntity page,int id){
        log.info("=>ExamBatchController:");
        ModelAndView model=new ModelAndView();
       ExamManager examManager=examManagerService.queryExamManagerById(id);
        request.setAttribute("examManager",examManager);
        page.setCurrentPage(getCurrentPage(request));
        page.setPageSize(10);
        List<ExamBatch> list = examBatchService.queryExamBatchList(page,id);
        request.setAttribute("list",list);
        List<ExamCourse> List= examCourseService.queryAllExamCourse();
        request.setAttribute("List",List);
        request.setAttribute("page",page);
        model.setViewName("/appointment/examBatch/list");
        //记录当前的动作
        request.getSession().setAttribute("courseListUri", WebUtils.getServletRequestUriParms(request));
        return model;
    }
    @RequestMapping("/page/{id}/{type}/{examManagerId}")
    public ModelAndView editPage(final HttpServletRequest request, @PathVariable("id") String id,@PathVariable("type") String type,@PathVariable("examManagerId") int examManagerId){
        log.debug("ExamBatchController>>>:editPage",id,type);
        ModelAndView model =new ModelAndView();
        switch (type){
            case "add":{
                request.setAttribute("type","添加页面");
                ExamManager examManager= examManagerService.queryExamManagerById(examManagerId);
                break;
            }
            case "edit":{
                request.setAttribute("type","修改页面");
                int id1 = Integer.parseInt(id);
                try{
                    ExamBatch examBatch = examBatchService.queryExamBatchById(id1);
                    model.addObject("examBatch",examBatch);
                }
                catch (Exception e){
                    System.out.println(e);
                }
                break;
            }
            default:{
                break;
            }
        }
        ExamManager examManager= examManagerService.queryExamManagerById(examManagerId);
        model.addObject("examManager",examManager);
        List<ExamClassroom> list=examClassroomService.queryClassRoomName();
        model.addObject("list",list);
        List<ExamCourse> List= examCourseService.queryAllExamCourse();
        model.addObject("List",List);
        model.setViewName("/appointment/examBatch/edit");
        return model;
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
     *
     * @param request
     * @param examBatch
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping("op/{type}")
    public ModelAndView edit(final HttpServletRequest request, ExamBatch  examBatch, @PathVariable("type") String type) throws Exception {
        log.debug("ExamBatchController>>>:edit",type);
        ModelAndView model = new ModelAndView();
        ExamCourse examCourse= examCourseService.queryCourseByCode(examBatch.getCourseNo());
        examBatch.setCourseId(examCourse.getId());
        examBatch.setCourseName(examCourse.getCourseName());
        switch (type) {
            case "false": {


                examBatch.setAppointmentCount(0);
                examBatch.setState(true);
                examBatchService.addExamBatch(examBatch);
                model.setViewName("redirect:/admin/appointment/batch?id="+examBatch.getExamManagerId());
                break;
            }
            case "true": {

                if(examBatch.getAppointmentCount()==null||"".equals(examBatch.getAppointmentCount())){
                    examBatch.setAppointmentCount(0);
                }
                examBatchService.updateExamBatch(examBatch);
                Object uri = request.getSession().getAttribute("courseListUri");
                if(uri!=null){
                    model.setViewName("redirect:"+uri.toString()+"?id="+examBatch.getExamManagerId());
                }
                break;
            }
            default: {
                break;
            }

        }
        return model;
    }
    @RequestMapping("/delete/{examManagerId}")
    //根据id删除
    public String delete(int id ,@PathVariable("examManagerId") int examManagerId){

        examBatchService.deleteExamBatch(id);
        return "redirect:/admin/appointment/batch?id="+examManagerId;
    }

    @RequestMapping("/deleteMore/{examManagerId}")
    //根据id批量删除班级
    public String deleteMore(int[] ids,@PathVariable("examManagerId")int examManagerId){
        log.info(" => ExamBatchController:deleteMore{}",ids);
        if(ObjectUtils.isNull(ids)){
            return "redirect:/admin/appointment/batch?id="+examManagerId;
        }
        for (int id : ids){
            examBatchService.deleteExamBatch(id);
        }
        return "redirect:/admin/appointment/batch?id="+examManagerId;
    }
    @RequestMapping("/select/{examManagerId}")
    public ModelAndView select(ExamBatch examBatch,HttpServletRequest request,PageEntity page,@PathVariable("examManagerId")int examManagerId ){
        ModelAndView model=new ModelAndView();


        page.setCurrentPage(getCurrentPage(request));
        if(examBatch.getExamClassroom()==null){
           examBatch.setExamClassroom("");
       }
        if(examBatch.getExamBatchNo()==null)
        {
            examBatch.setExamBatchNo(0);
        }
        List<ExamBatch> list = this.examBatchService.queryExamClassroomAndExamBatch(examBatch.getExamClassroom(),examBatch.getExamBatchNo(),page,examManagerId);
        request.setAttribute("examClassroom",examBatch.getExamClassroom());
        if(examBatch.getExamBatchNo()!=0){
            request.setAttribute("examBatchNo",examBatch.getExamBatchNo());
        }
        ExamManager examManager= examManagerService.queryExamManagerById(examManagerId);
        model.addObject("examManager",examManager);
        model.addObject("list",list);
        model.addObject("page",page);
        model.setViewName("/appointment/examBatch/list");
        request.getSession().setAttribute("courseListUri", WebUtils.getServletRequestUriParms(request));
        return model;
    }
    @RequestMapping("changeOne/{id}/{state}")
    @ResponseBody
    public Map<String,Object> change(HttpServletRequest request, @PathVariable("id") int id, @PathVariable("state") String state) {
        String ajax = "";
        ExamBatch examBatch = examBatchService.queryExamBatchById(id);
        try {
            if (examBatch.getState()) {
                examBatch.setState(false);
                examBatchService.updateExamBatch(examBatch);
            } else {
                examBatch.setState(true);
                examBatchService.updateExamBatch(examBatch);
            }
            ajax = "修改成功";
        } catch (Exception e) {
            ajax = "修改失败";
        }
        return this.setJson(true, ajax, null);

    }
    @RequestMapping("/updateCourseCode")
    @ResponseBody
    public Map<String,Object> updateCourseCode(HttpServletRequest request) {
        String ajax = "";
       String exanBatchId= request.getParameter("id");
        ExamBatch examBatch = examBatchService.queryExamBatchById(Integer.parseInt(exanBatchId));

          try {
               ExamCourse examCourse= examCourseService.queryCourseByCode(request.getParameter("courseNo"));
              examBatch.setCourseNo(request.getParameter("courseNo"));
              examBatch.setCourseName(examCourse.getCourseName());
              examBatch.setCourseId(examCourse.getId());
              examBatchService.updateExamBatch(examBatch);
              ajax = "修改成功";
          }catch (Throwable e){
                ajax="修改失败";
          }
        return this.setJson(true, ajax, null);
    }@RequestMapping("/updateNumber")
    @ResponseBody
    public Map<String,Object> updateNumber(HttpServletRequest request) {
        String ajax = "";
        String exanBatchId= request.getParameter("id");
        ExamBatch examBatch = examBatchService.queryExamBatchById(Integer.parseInt(exanBatchId));

        try {
           examBatch.setNumber(Integer.parseInt(request.getParameter("number")));
            examBatchService.updateExamBatch(examBatch);
            ajax = "修改成功";
        }catch (Throwable e){
            e.printStackTrace();
            ajax="修改失败";
        }
        return this.setJson(true, ajax, null);
    }

}

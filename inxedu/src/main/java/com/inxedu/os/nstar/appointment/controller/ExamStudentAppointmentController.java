package com.inxedu.os.nstar.appointment.controller;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.nstar.appointment.entity.StudentAppointment;
import com.inxedu.os.nstar.appointment.entity.examBatch.ExamBatch;
import com.inxedu.os.nstar.appointment.entity.examClassroom.ExamClassroom;
import com.inxedu.os.nstar.appointment.entity.examSeat.ExamSeat;
import com.inxedu.os.nstar.appointment.entity.examStudentAppointment.ExamStudentAppointment;
import com.inxedu.os.nstar.appointment.service.ExamBatchService;
import com.inxedu.os.nstar.appointment.service.ExamClassroomService;
import com.inxedu.os.nstar.appointment.service.ExamSeatService;
import com.inxedu.os.nstar.appointment.service.ExamStudentAppointmentService;
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

/** 预约管理
 * Created by Nstar on 2016/8/3.
 */
@Controller
@RequestMapping("/admin/appointment/studentAppointment")
public class ExamStudentAppointmentController extends BaseController{

        private static final Logger logger = LoggerFactory.getLogger(ExamStudentAppointmentController.class);
       @Autowired
       private ExamStudentAppointmentService examStudentAppointmentService;
      @Autowired
       private ExamSeatService examSeatService;
    @Autowired
    private ExamBatchService examBatchService;
        final String path = "/appointment/examStudentAppointment/";

        final String redirect = "redirect:" + path;

        @RequestMapping("")
        public String list(HttpServletRequest request, PageEntity page) {
            logger.debug("ExamStudentAppointmentController>>>:list",page);
            page.setPageSize(20);
            List<StudentAppointment> studentAppointments = examStudentAppointmentService.queryExamStudentAppointmentList(page);
            request.setAttribute("list", studentAppointments);
            request.setAttribute("page", page);
            return path + "list";
        }

        /**
         * 跳到添加/修改页面
         * @param request
        \
         * @return
         */
        @RequestMapping("/page/add")
        public String editPage(final HttpServletRequest request) {
            logger.debug("ExamStudentAppointmentController>>>:editPage");

                    request.setAttribute("type", "添加页面");
            return path + "edit";
        }

        /**
         * 添加/修改方法
         * @param request
         *
         * @return
         * @throws Exception
         */
        @RequestMapping("op/add")
        public ModelAndView edit(final HttpServletRequest request,StudentAppointment studentAppointment) throws Exception {
            logger.debug("ExamStudentAppointmentController>>>:edit",studentAppointment);
            ModelAndView model = new ModelAndView();
            examStudentAppointmentService.addExamStudentAppointment(studentAppointment);
            model.setViewName("redirect:/admin/appointment/studentAppointment");
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
            logger.debug("ExamStudentAppointmentController>>>:delete",id);
            int id1 = Integer.parseInt(id);
            ExamStudentAppointment examStudentAppointment=examStudentAppointmentService.queryStudentAppointmentById(id1);
            examStudentAppointment.getSeatNo();
            examStudentAppointment.getExamClassroom();
            ExamSeat examSeat= examSeatService.queryExamSeatBySandN(examStudentAppointment.getSeatNo(),examStudentAppointment.getExamClassroom());
            if(ObjectUtils.isNull(examSeat)){
                return "redirect:/admin/appointment/studentAppointment";
            }
            examSeat.setState(false);
            examSeatService.updateExamSeat(examSeat);
           ExamBatch examBatch= examBatchService.queryExamBatchById(examStudentAppointment.getExamBatchId());
             examBatch.setAppointmentCount(examBatch.getAppointmentCount()-1);
            examBatchService.updateExamBatch(examBatch);
            examStudentAppointmentService.deleteExamStudentAppointment(id1);
            return "redirect:/admin/appointment/studentAppointment";
        }
        /**
         * 批量删除
         *
         */
        @RequestMapping("/op/del")
        public String del(final HttpServletRequest request, int ids[]) {
            logger.debug("ExamStudentAppointmentController>>>:del",ids);
            if (ids == null) {

                return "redirect:/admin/appointment/studentAppointment";
            }
            for (int id : ids) {
                ExamStudentAppointment examStudentAppointment=examStudentAppointmentService.queryStudentAppointmentById(id);
                examStudentAppointment.getSeatNo();
                examStudentAppointment.getExamClassroom();
                ExamSeat examSeat= examSeatService.queryExamSeatBySandN(examStudentAppointment.getSeatNo(),examStudentAppointment.getExamClassroom());
                if(ObjectUtils.isNull(examSeat)){
                    return "redirect:/admin/appointment/studentAppointment";
                }
                examSeat.setState(false);
                examSeatService.updateExamSeat(examSeat);
                ExamBatch examBatch= examBatchService.queryExamBatchById(examStudentAppointment.getExamBatchId());
                examBatch.setAppointmentCount(examBatch.getAppointmentCount()-1);
                examBatchService.updateExamBatch(examBatch);
                examStudentAppointmentService.deleteExamStudentAppointment(id);
            }
            return "redirect:/admin/appointment/studentAppointment";
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
         *
         * @param request
         * @param page
         * @return
         */
        @RequestMapping("/search")
        public String search(HttpServletRequest request, PageEntity page,ExamStudentAppointment examStudentAppointment) {
            page.setCurrentPage(getCurrentPage(request));
           List<ExamStudentAppointment> examStudentAppointments=examStudentAppointmentService.queryExamStudentAppointmentBySSE(examStudentAppointment.getStudentNo(),examStudentAppointment.getStudentName(),examStudentAppointment.getExamName(),page);
            request.setAttribute("list",examStudentAppointments);
            request.setAttribute("examStudentAppointment",examStudentAppointment);
            request.setAttribute("page", page);
            return path + "list";
        }
    @RequestMapping("/changge")
    @ResponseBody
    public  Map<String,Object> validate(HttpServletRequest request){
        String studentNo=request.getParameter("studentNo");
        String studentName=request.getParameter("studentName");
        String examName=request.getParameter("examName");
        String ajax="";
        try {
            boolean s=examStudentAppointmentService.queryStudentAppointment(studentNo,studentName,examName);
            if(s){
                ajax="true";
            }
            else {
                ajax="false";
            }
        }catch (Exception e)
        {
            ajax="匹配失败";

        }
        return  setJson(true,ajax,null);
    }
}

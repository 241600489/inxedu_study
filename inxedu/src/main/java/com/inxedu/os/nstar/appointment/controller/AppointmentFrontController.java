package com.inxedu.os.nstar.appointment.controller;

import com.inxedu.os.common.cache.EHCacheUtil;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.nstar.appointment.entity.AppointmentState;
import com.inxedu.os.nstar.appointment.entity.ApponitmentResult;
import com.inxedu.os.nstar.appointment.entity.examBatch.ExamBatch;
import com.inxedu.os.nstar.appointment.entity.examBatch.ExamBatchListViewModel;
import com.inxedu.os.nstar.appointment.entity.examManager.ExamManager;
import com.inxedu.os.nstar.appointment.entity.examStudent.ExamStudent;
import com.inxedu.os.nstar.appointment.entity.examStudentAppointment.ExamStudentAppointment;
import com.inxedu.os.nstar.appointment.service.AppointmentFrontService;
import com.inxedu.os.nstar.appointment.service.ExamBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/8/6.
 */
@Controller
@RequestMapping("/uc/appointment")//http://localhost:8080/uc/appointment http://localhost:8080/uc/appointment/result
public class AppointmentFrontController extends BaseController {
    final String path = "/appointment/front/";
    final String redirect = "redirect:" + path;
    final String redirectIndex = "redirect:/index";
    @Autowired
    AppointmentFrontService appointmentFrontService;
    @Autowired
    ExamBatchService examBatchService;
    @RequestMapping("")
    public ModelAndView center(HttpServletRequest request, PageEntity page) {
        ModelAndView model = new ModelAndView(path + "center");
        User user = SingletonLoginUtils.getLoginUser(request);
        if (user == null) {
            model.setViewName(redirectIndex);
            return model;
        }
        if (!(user.getUserId() > 0)) {
            model.setViewName(redirectIndex);
            return model;
        }
        List<ExamManager> options = appointmentFrontService.queryExamManagerListWithCurrenttime();

        model.addObject("options", options);
        if (!options.isEmpty()) {
            Integer examNameId = options.get(0).getId();
           model.addObject("examNameId", examNameId);
            boolean isAppointment=   appointmentFrontService.isExistAppointment(user.getNo(),options.get(0).getExamName());
            model.addObject("isAppointment",isAppointment);
            page.setPageSize(20);
            List<ExamBatch> list1=examBatchService.queryExamBatchList(page,examNameId);
            model.addObject("list",list1);
            List<Date> resultList=new ArrayList<>();
            if (Boolean.TRUE.equals(options.get(0).getIsCourseController())) {

                List<ExamStudent> list = appointmentFrontService.getExamStudentByStudentNo(Integer.valueOf(user.getNo()));
                if (ObjectUtils.isNotNull(list)) {
                    ExamStudent student = list.get(0);

                    List<String> timeOptions = appointmentFrontService.queryBatchTime(examNameId,null,student.getCourseCode());
                    model.addObject("time", timeOptions);
                } else {
                    List<String> timeOptions = appointmentFrontService.queryBatchTimeWithoutCourseCode(examNameId);
                    model.addObject("time", timeOptions);
                }
            }
            resultList = appointmentFrontService.queryBatchDate(examNameId,null);
            model.addObject("date",resultList);
            model.addObject("page", page);
        }
        return model;
    }
    @RequestMapping("/exam")
    public ModelAndView center2(HttpServletRequest request, PageEntity page) {
        ModelAndView model = new ModelAndView(path + "center");
        int currentPage=Integer.parseInt(request.getParameter("page.currentPage"));
        page.setCurrentPage(currentPage);
        page.setPageSize(20);
        List<String> timeOptions=new ArrayList<>();
        List<Date> dateOptions=new ArrayList<>();
        User user = SingletonLoginUtils.getLoginUser(request);
        String t1 = "2016-08-01";
        String time = "8:00-9:00";
        t1=request.getParameter("date");
        time=request.getParameter("time");
        Date date = DateUtils.strToDate(t1);
        String examnameId1 = request.getParameter("examnameId");
        Integer examnameId;
        if("".equals(examnameId1)){
             examnameId= 0;
        }else {
             examnameId= Integer.parseInt(examnameId1);
        }

        request.setAttribute("examNameId", examnameId);
        String isFullStr = "";
        if (user == null) {
            model.setViewName(redirectIndex);
            return model;
        }
        if (!(user.getUserId() > 0)) {
            model.setViewName(redirectIndex);
            return model;
        }
        List<ExamManager> options = appointmentFrontService.queryExamManagerListWithCurrenttime();
        model.addObject("options", options);

        if (!options.isEmpty()) {
            Integer examNameId = options.get(0).getId();
            List<ExamStudent> list = appointmentFrontService.getExamStudentByStudentNo(Integer.valueOf(user.getNo()));
            List<Date> listDate;
            List<String> listTime;
            if (Objects.isNull(list)) {
                model.setViewName("redirect:/uc/appointment");
                return model;
            }else if (options.get(0).getIsCourseController()){
                for (ExamStudent student:list) {
                   listTime = appointmentFrontService.queryBatchTime(examnameId,date,student.getCourseCode());
                   listDate = appointmentFrontService.queryBatchDate(examnameId,student.getCourseCode());
                    timeOptions.addAll(listTime);
                    dateOptions.addAll(listDate);
                }
        }
        else {
                listDate = appointmentFrontService.queryBatchDate(examnameId,null);
               listTime = appointmentFrontService.queryBatchTime(examnameId,date,null);
                timeOptions.addAll(listTime);
                dateOptions.addAll(listDate);
            }
            model.addObject("time", timeOptions);
            model.addObject("date",dateOptions);
        }
        model.addObject("timeback", time);
        isFullStr = request.getParameter("isfull");
        if (examnameId != null) {
            date = DateUtils.strToDate(t1);
            model.addObject("dateback", date);
            Boolean isFull = !"true".equals(isFullStr);
            ExamBatchListViewModel result=null;
            result=appointmentFrontService.queryExamBatchByStudentNo(Integer.valueOf(user.getNo()), examnameId, date, time, isFull, page);
            model.addObject("isFull", isFullStr);
            model.addObject("list", result.getExamBatcheList());
            model.addObject("isAppointment", result.isAppointment());
            model.addObject("page",page);
        }
        return model;
    }
    @RequestMapping("/result")
    public ModelAndView result(HttpServletRequest request, PageEntity page) {
        ModelAndView model = new ModelAndView();
        User user = SingletonLoginUtils.getLoginUser(request);
        if (user == null) {
            model.setViewName(redirectIndex);
            return model;
        }
        if (!(user.getUserId() > 0)) {
            model.setViewName(redirectIndex);
            return model;
        }
        List<ExamStudentAppointment> result = appointmentFrontService.queryMyAppointment(user.getNo(), page);
        model.addObject("list", result);
        model.setViewName(path + "result");
        return model;
    }



    @RequestMapping("/appointment/{examnameId}/{examBatchId}")
    public ModelAndView appointment(HttpServletRequest request, @PathVariable("examnameId") Integer examnameId, @PathVariable("examBatchId") Integer examBatchId) {
        ModelAndView model = new ModelAndView(path + "process");
        User user = SingletonLoginUtils.getLoginUser(request);
        this.setJson(false, "login", null);

        if (user == null) {
            model.setViewName(redirectIndex);
            return model;
        }
        if (!(user.getUserId() > 0)) {
            model.setViewName(redirectIndex);
            return model;
        }
        List<ExamStudent> list = appointmentFrontService.getExamStudentByStudentNo(Integer.valueOf(user.getNo()));

        if (ObjectUtils.isNotNull(list)) {
            ExamStudent student = list.get(0);
            if (student.getLock()) {
                model.addObject("state", "lock");
                return model;
            }
            String key="appointment_"+student.getId();
            AppointmentState state=AppointmentState.ILLEGAL;
            Object o = EHCacheUtil.get(key);
            if(EHCacheUtil.get(key)==null){
                EHCacheUtil.set(key,Boolean.TRUE,3600/60);
              ApponitmentResult  result = appointmentFrontService.appointment(student, examBatchId, examnameId);
                state = result.getState();
                EHCacheUtil.remove(key);
                switch (state) {
                    case SUCCESS: {
                        model.addObject("result", result.getAppointment());
                        model.addObject("state", "success");
                        return model;
                    }
                    case FAIL: {
                        model.addObject("state", "fail");
                        return model;
                    }
                    case REPLY: {
                        model.addObject("state", "reply");
                        // return this.setJson(false, "reply", null);
                        return model;
                    }
                    case FULL: {
                        model.addObject("state", "full");
                        // return this.setJson(false, "full", null);
                        return model;
                    }
                    case ILLEGAL: {
                        model.addObject("state", "illegal");
                        // return this.setJson(false, "illegal", null);
                        return model;
                    }
                    case ILLEGAL_TIME: {
                        model.addObject("state", "illegalTime");
                        return model;
                    }
                    case NO_SEAT: {
                        model.addObject("state", "no_seat");
                        return model;
                    }
                    default: {
                        model.addObject("state", "fail");
                        return model;
                    }
                }
            }else {
                model.addObject("state", "illegal");
                return model;
            }

        }else {
            return  model;
        }
    }
    @RequestMapping("/backAppointment/{examStudentAppointmentId}/{idCard}")
    @ResponseBody
    public Map<String, Object> backAppointment(HttpServletRequest request, @PathVariable("examStudentAppointmentId") Integer examStudentAppointmentId,@PathVariable("idCard")String idCard) {
        ModelAndView model = new ModelAndView();
        User user = SingletonLoginUtils.getLoginUser(request);
        if (user == null) {
            model.setViewName(redirectIndex);
            return this.setJson(false, "login", null);
        }
        if (!(user.getUserId()>0)) {
            model.setViewName(redirectIndex);
            return this.setJson(false, "login", null);
        }
        if(!idCard.equals(user.getIdCard())){
            return this.setJson(false,"请输入正确的身份证号",null);
        }
        List<ExamStudent> list = appointmentFrontService.getExamStudentByStudentNo(Integer.valueOf(user.getNo()));
        if(ObjectUtils.isNotNull(list)) {

           ExamStudent student=list.get(0);
            if (student.getLock()) {
                return this.setJson(true, "lock", null);
            }
            AppointmentState state = appointmentFrontService.backAppointment(user.getNo(), examStudentAppointmentId);
            switch (state) {
                case SUCCESS: {
                    return this.setJson(true, "success", null);
                }
                case FAIL: {
                    return this.setJson(false, "fail", null);
                }
                case REPLY: {
                    return this.setJson(false, "reply", null);
                }
                case FULL: {
                    return this.setJson(false, "full", null);
                }
                case ILLEGAL: {
                    return this.setJson(false, "illegal", null);
                }
                case ILLEGAL_TIME: {
                    return this.setJson(false, "illegalTime", null);
                }
                default: {
                    return this.setJson(false, "fail", null);
                }
            }
        }else {
            return this.setJson(false,"fail",null);
        }
    }

    @RequestMapping("/examNameSelect/{examNameId}/{examDate}")
    @ResponseBody
    public Map<String,Object> selectExamName(final HttpServletRequest request, @PathVariable("examNameId") int examNameId,@PathVariable("examDate")String date) throws Exception {
        List<String> result = new ArrayList<>();
        List<String> resultList=new ArrayList<>();
        Date t1 = DateUtils.strToDate(date);
        try {
            ExamManager examManager = appointmentFrontService.queryExamManagerById(examNameId);
            User user = SingletonLoginUtils.getLoginUser(request);
            if (ObjectUtils.isNotNull(examManager)){
                if (examManager.getIsCourseController()){
            List<ExamStudent> list = appointmentFrontService.getExamStudentByStudentNo(Integer.valueOf(user.getNo()));
            if (ObjectUtils.isNotNull(list)){
                for (ExamStudent student:list){
                    result = appointmentFrontService.queryBatchTime(examManager.getId(),t1,student.getCourseCode());
                    resultList.addAll(result);
                }
            }
            else {
                return this.setJson(false,null,result);
            }
                }
                else {
                    result = appointmentFrontService.queryBatchTime(examManager.getId(),t1,null);
                }
            }

        } catch (Throwable e) {
            System.out.println(e);
        }
        return this.setJson(true, null, result);
    }
    @RequestMapping("/examNameSelectDate/{examNameId}")
    @ResponseBody
    public Map<String, Object> selectExamDate(final HttpServletRequest request, @PathVariable("examNameId") int examNameId) throws Exception {
        List<Date> result = new ArrayList<>();
        List<Date> resultList=new ArrayList<>();
        try {
            ExamManager examManager = appointmentFrontService.queryExamManagerById(examNameId);
            User user = SingletonLoginUtils.getLoginUser(request);
            if (ObjectUtils.isNotNull(examManager)){
                if (examManager.getIsCourseController()){
                    List<ExamStudent> list = appointmentFrontService.getExamStudentByStudentNo(Integer.valueOf(user.getNo()));
                    if (ObjectUtils.isNotNull(list)){
                        for (ExamStudent student:list){
                            result = appointmentFrontService.queryBatchDate(examManager.getId(), student.getCourseCode());
                            resultList.addAll(result);
                        }
                    }
                    else {
                        return this.setJson(false,null,resultList);
                    }
                }
                else {
                    resultList = appointmentFrontService.queryBatchDate(examManager.getId(),null);
                }
            }

        } catch (Throwable e) {
            System.out.println(e);
        }
        return this.setJson(true, null, resultList);
    }
    @RequestMapping("/searchMemo/{examBatchId}")
    @ResponseBody
    public Map<String, Object> searchMemo(final HttpServletRequest request, @PathVariable("examBatchId") int examBatchId) throws Exception {
        ExamBatch examBatch=examBatchService.queryExamBatchById(examBatchId);
        if(ObjectUtils.isNotNull(examBatch)){
            if (examBatch.getMemo()==null){
                return this.setJson(true,"小编太懒了，什么也没有留下",null);
            }
            return this.setJson(true,examBatch.getMemo(),null);

        }else{
            return this.setJson(false,"不存在该批次",null);
        }
    }
}
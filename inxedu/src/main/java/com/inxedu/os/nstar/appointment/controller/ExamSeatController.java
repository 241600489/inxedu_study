package com.inxedu.os.nstar.appointment.controller;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.nstar.appointment.entity.examSeat.ExamSeat;
import com.inxedu.os.nstar.appointment.service.ExamBatchService;
import com.inxedu.os.nstar.appointment.service.ExamClassroomService;
import com.inxedu.os.nstar.appointment.service.ExamSeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by  on 16-8-5.
 */
@Controller
@RequestMapping("/admin/appointment/seat")
public class ExamSeatController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ExamSeatController.class);
    final String path = "/appointment/examSeat/";

    final String redirect = "redirect:" + path;
    @Autowired
    private ExamSeatService examSeatService;
    @Autowired
    private ExamClassroomService examClassroomService;
    @Autowired
    private ExamBatchService examBatchService;

    @RequestMapping("/layout/{examClassroomName}")
    public String layout(HttpServletRequest request, PageEntity page, @PathVariable("examClassroomName") String examClassroomName) {
        ExamSeat[][] result = examSeatService.queryExamSeat(examClassroomName, 20);
        request.setAttribute("table", result);
        return path + "det";
    }

    @RequestMapping("/{examBatchId}")
    public String list(HttpServletRequest request, PageEntity page, String examClassroomName, @PathVariable("examBatchId") Integer examBatchId) {
        logger.debug("ExamSeatController>>>:list", page);
        page.setPageSize(20);
        // List<ExamSeat> list=examSeatService.queryExamSeatByExamBatchId(examBatchId);
        List<ExamSeat> list1 = examSeatService.queryExamSeatByExamBatchIdWithPage(examBatchId, page);
        request.setAttribute("examClassroomName", examClassroomName);
        request.setAttribute("examBatchId", examBatchId);
        request.setAttribute("list", list1);
        request.setAttribute("page", page);
        return path + "list";
    }

    /**
     * 跳到添加页面
     *
     * @return
     */
    @RequestMapping("/page/add/{examClassroomName}/{examBatchId}")
    public String editPage(final HttpServletRequest request, @PathVariable("examClassroomName") String examClassroomName, @PathVariable("examBatchId") Integer examBatchId) {
        logger.debug("ExamSeatController>>>:editPage");
        request.setAttribute("type", "添加页面");
        request.setAttribute("examClassroomName", examClassroomName);
        request.setAttribute("examBatchId", examBatchId);
        // ExamBatch examBatchList=examBatchService.queryExamBatchById(examBatchId);
        //List<ExamBatch> list1= examBatchService.queryExamBatchByClassRoomName(examClassroomName);
        //request.setAttribute("list1",examBatchList);
        return path + "edit";
    }

    /**
     * 添加
     *
     * @param request
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("op/add")
    @ResponseBody
    public Map<String,Object> edit(final HttpServletRequest request, int seatNo1) throws Exception {
        Map<String,Object> json=new HashMap<String,Object>();
        logger.debug("ExamSeatController>>>:edit", seatNo1);
        String message="";
        String seatNo= request.getParameter("examSeat.seatNo");
        String state=request.getParameter("examSeat.state");
        String examClassroomName=request.getParameter("examSeat.examClassroomName");
        String examBatchId=request.getParameter("examSeat.examBatchId");
        ExamSeat examSeat=new ExamSeat();
        examSeat.setExamBatchId(Integer.parseInt(examBatchId));
        examSeat.setExamClassroomName(examClassroomName);
        PageEntity page =new PageEntity();
        for (int i = Integer.parseInt(seatNo); i <=seatNo1; i++) {
            if(ObjectUtils.isNotNull(examSeatService.queryExamSeatbySeatNOandExamBatchId(i,Integer.parseInt(examBatchId)))){
                message+=i+"座位号已在数据库存在<br/>";
            }
        }
        if ("".equals(message)){
            try{
                for (int i =Integer.parseInt(seatNo); i <= seatNo1; i++) {
                    examSeat.setSeatNo(i);
                    examSeat.setState(false);
                    examSeat.setExamClassroomName(examClassroomName);
                    examSeat.setExamBatchId(Integer.parseInt(examBatchId));
                        examSeatService.addExamSeat(examSeat);
                }}catch (RuntimeException e){
                return this.setAjaxException(json);
            }
        }else {
            return this.setJson(false,message,null);
        }
        return this.setJson(true,"添加成功",examSeat);
    }

    /**
     * 删除
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/op/delete/{id}/{examClassroomName}/{examBatchId}")
    public String delete(final HttpServletRequest request, @PathVariable("id") String id, @PathVariable("examClassroomName") String examClassroomName,@PathVariable("examBatchId") Integer examBatchId) {
        logger.debug("ExamSeatController>>>:delete", id);
        int id1 = Integer.parseInt(id);
        ExamSeat examSeat = examSeatService.queryByPrimaryKey(id1);
        examSeatService.deleteExamSeat(id1);
        return "redirect:/admin/appointment/seat/"+examBatchId+"?examClassroomName=" + examClassroomName;
    }

    /**
     * 批量删除
     */
    @RequestMapping("/op/del/{examClassroomName}/{examBatchId}")
    public String del(final HttpServletRequest request, int ids[], @PathVariable("examClassroomName") String examClassroomName,@PathVariable("examBatchId") Integer examBatchId) {
        logger.debug("ExamSeatController>>>:del", ids);
        if (ids == null) {
            return "redirect:/admin/appointment/seat/"+examBatchId+"?examClassroomName=" + examClassroomName;
        }
        for (int id : ids) {
            examSeatService.deleteExamSeat(id);
        }
        return "redirect:/admin/appointment/seat/"+examBatchId+"?examClassroomName=" + examClassroomName;
    }

    @RequestMapping("/search/{examClassroomName}/{examBatchId}")
    public String search(ExamSeat examSeat,
                         HttpServletRequest request, PageEntity page,
                         @PathVariable("examClassroomName") String examClassroomName,
                         @PathVariable("examBatchId") Integer examBatchId) {
        page.setPageSize(20);
        if (examSeat.getSeatNo() == null) {

            List<ExamSeat> list = examSeatService.queryExamSeatByExamBatchIdWithPage(examBatchId,page);
            request.setAttribute("examClassroomName", examClassroomName);
            request.setAttribute("list", list);
            request.setAttribute("page", page);
            return path + "list";
        } else {

            int seatNo1 = examSeat.getSeatNo();
            List<ExamSeat> examSeat1 = examSeatService.queryExamSeatbySandExambatchId(seatNo1,examBatchId,page);
            request.setAttribute("examClassroomName", examClassroomName);
            request.setAttribute("list", examSeat1);
            request.setAttribute("seatNo1", seatNo1);
            request.setAttribute("page", page);
            return path + "list";
        }
    }

    @RequestMapping("/changeOne/{state}/{id}")
    @ResponseBody
    public Map<String, Object> changge(HttpServletRequest request, @PathVariable("id") int id, @PathVariable("state") String state) {
        String ajax = "";
        ExamSeat examSeat = examSeatService.queryByPrimaryKey(id);
        try {
            if (examSeat.getState()) {
                examSeat.setState(false);
                examSeatService.updateExamSeat(examSeat);
            } else {
                examSeat.setState(true);
                examSeatService.updateExamSeat(examSeat);
            }
            ajax = "修改成功";
        } catch (Exception e) {
            ajax = "修改失败";
        }
        return this.setJson(true, ajax, null);
    }
}

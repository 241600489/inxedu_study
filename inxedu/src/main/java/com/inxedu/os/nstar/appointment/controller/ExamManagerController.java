package com.inxedu.os.nstar.appointment.controller;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.nstar.appointment.entity.examBatch.ExamBatch;
import com.inxedu.os.nstar.appointment.entity.examManager.ExamManager;
import com.inxedu.os.nstar.appointment.entity.examSeat.ExamSeat;
import com.inxedu.os.nstar.appointment.service.ExamBatchService;
import com.inxedu.os.nstar.appointment.service.ExamManagerService;
import com.inxedu.os.nstar.appointment.service.ExamSeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin/appointment/manager")
public class ExamManagerController extends BaseController{
    private static Logger log = LoggerFactory.getLogger(ExamManagerController.class);
    @Autowired
    private ExamManagerService examManagerService;
    @Autowired
    private ExamBatchService examBatchService;
    @Autowired
    private ExamSeatService examSeatService;
    final String path ="/appointment/examManager/";

    final String redirect = "redirect:" + path;

    @RequestMapping("")
    //显示预约考试
    public String list(HttpServletRequest request, PageEntity page){
        log.info("=>ExamManagerController:list");
        page.setPageSize(20);
        List<ExamManager> list = examManagerService.queryExamManagerList(page);
        request.setAttribute("list",list);
        request.setAttribute("page",page);
        return path + "list";
    }

    @RequestMapping("/addPage")
    //跳转到添加页面
    public String addPage(HttpServletRequest request){
        return path + "addPage";
    }

    @RequestMapping("/updatePage")
    //跳转到修改页面
    public String updatePage(HttpServletRequest request,int id){
        ExamManager examManager = examManagerService.queryExamManagerById(id);
        request.setAttribute("examManager",examManager);
        return path + "updatePage";
    }
    @RequestMapping("/examBacth")
    //跳转到考试批次页面
    public String examBacth(HttpServletRequest request){
        return path + "examBacth";
    }
    @RequestMapping("/add")
    //添加考试预约信息
    public String add(ExamManager examManager,HttpServletRequest request){
        examManagerService.addExamManager(examManager);
        return "redirect:/admin/appointment/manager";
    }

    @RequestMapping("/update")
    //修改考试预约信息
    public String update(ExamManager examManager,HttpServletRequest request){
        examManagerService.updateExamManager(examManager);
        return "redirect:/admin/appointment/manager";
    }

    @RequestMapping("/delete")
    //根据id删除
    public String delete(int id){
        examManagerService.deleteExamManager(id);
        return "redirect:/admin/appointment/manager";
    }
    @RequestMapping("/deleteMore")
    //根据id批量删除班级
    public String deleteMore(int[] ids){
        log.info(" => ExamManagerController:deleteMore{}",ids);
        if(ObjectUtils.isNull(ids)){
            return "redirect:/admin/appointment/manager";
        }
        for (int id : ids){
            examManagerService.deleteExamManager(id);
        }
        return "redirect:/admin/appointment/manager";
    }

    @RequestMapping("/search")
    //根据考试名称查询及模糊查询
    public String search(String examName,HttpServletRequest request,PageEntity page, int start) {
        page.setPageSize(20);
        page.setCurrentPage(getCurrentPage(request));
        switch (start) {
            case 0: {

                List<ExamManager> list = examManagerService.queryExamManagerByName(page, examName);
                request.setAttribute("examName",examName);
                request.setAttribute("list", list);
                request.setAttribute("page",page);
                break;
            }
            case 1: {

                List<ExamManager> list = examManagerService.queryExamManagersByName(page, examName);
                request.setAttribute("examName",examName);
                request.setAttribute("list", list);
                request.setAttribute("page",page);
                break;
            }
            default: {
                break;
            }
        }
        request.setAttribute("page", page);
        return path + "list";

    }
    @RequestMapping("changeOne/{id}/{state}")
    @ResponseBody
    public Map<String,Object> changge(HttpServletRequest request, @PathVariable("id") int id, @PathVariable("state") String state) {
        String ajax = "";
        ExamManager examManager = examManagerService.queryExamManagerById(id);
        try {
            if (examManager.getState()) {
                examManager.setState(false);
                examManagerService.updateExamManager(examManager);
            } else {
                PageEntity page=new PageEntity();
                page.setPageSize(1000);
                List<ExamBatch> list= examBatchService.queryExamBatchList(page,examManager.getId());
                if (ObjectUtils.isNotNull(list)){
                    for (ExamBatch examBatch:list){
                        List<ExamSeat> examSeatList=examSeatService.queryExamSeatByExamBatchId(examBatch.getId());
                        if (ObjectUtils.isNull(list)){
                            return this.setJson(false,"考场为:"+examBatch.getExamClassroom()+"批次号:"+examBatch.getExamBatchNo(),null);
                        }
                        else {
                            examManager.setState(true);
                            examManagerService.updateExamManager(examManager);

                        }
                    }
                }else {
                    return  this.setJson(false,"该预约管理不存在批次请添加后再开启",null);
                }

            }
            ajax = "修改成功";
        } catch (Exception e) {
            ajax = "修改失败";
        }
        return this.setJson(true, ajax, null);

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
    @RequestMapping("changeTWO/{id}/{isCourseController}")
    @ResponseBody
    public Map<String,Object> changgeTWO(HttpServletRequest request, @PathVariable("id") int id, @PathVariable("isCourseController") String isCourseController) {
        String ajax = "";
        ExamManager examManager = examManagerService.queryExamManagerById(id);
        try {
            if (examManager.getIsCourseController()) {
                examManager.setIsCourseController(false);
                examManagerService.updateExamManager(examManager);
            } else {
                examManager.setIsCourseController(true);
                examManagerService.updateExamManager(examManager);
            }
            ajax = "修改成功";
        } catch (Exception e) {
            ajax = "修改失败";
        }
        return this.setJson(true, ajax, null);

    }
}

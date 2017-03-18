package com.inxedu.os.nstar.selectCourse.controller;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.nstar.selectCourse.entity.college.College;
import com.inxedu.os.nstar.selectCourse.entity.stuClass.HyperStuClass;
import com.inxedu.os.nstar.selectCourse.entity.stuClass.StuClass;
import com.inxedu.os.nstar.selectCourse.service.CollegeService;
import com.inxedu.os.nstar.selectCourse.service.StuClassService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by 1 on 2016/7/27.
 */
@Controller
@RequestMapping("/admin/stuClass")
public class StuClassController{
    @Autowired
   private CollegeService collegeService;
    @Autowired
    private StuClassService stuClassService;
    private static Logger log = LoggerFactory.getLogger(StuClassController.class);
    @RequestMapping("/list")
    //显示班级列表
    public String list(HttpServletRequest request, PageEntity page){

        log.info("=> StuClassController:list");
        //获取课程列表

        List<StuClass> list = stuClassService.getStuClassList(page);
        if(ObjectUtils.isNull(list)){
            return "/nstar/stuClass/list";
        }
          request.setAttribute("page",page);
        List<HyperStuClass> list1 = new ArrayList<>();
        for(StuClass stuClass:list){
            HyperStuClass h = new HyperStuClass();
            h.setClassName(stuClass.getClassName());
            String name=collegeService.querycollegeName(stuClass.getSchoolId());
            h.setId(stuClass.getId());
            h.setCollename(name);
            h.setDescription(stuClass.getDescription());
            h.setSchoolId(stuClass.getSchoolId());
            list1.add(h);
        }
        //将课程列表放在request中
        request.setAttribute("list", list1);

       List<College> list2= collegeService.queryCollege();

       request.setAttribute("list1",list2);
        return "/nstar/stuClass/list";
    }

    @RequestMapping("/delete")
    //根据id删除班级
    public String delete(int id){
        log.info("=> StuClassController:delete {}",id);
        stuClassService.deleteClass(id);
        return "redirect:/admin/stuClass/list";
    }

    @RequestMapping("/deleteMore")
    //根据id批量删除班级
    public String deleteMore(int[] ids){
        log.info("=> StuClassController:deleteMore {}",ids);
        if (ids==null){

            return "redirect:/admin/stuClass/list";
        }
        for(int id:ids){
            stuClassService.deleteClass(id);
        }
        return "redirect:/admin/stuClass/list";
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
    @RequestMapping("/search")
    //根据班级名称和学院id模糊查询班级列表
    public String search(StuClass stuClass,HttpServletRequest request,PageEntity page){
        log.info("=> StuClassController:search {}",stuClass);
        page.setCurrentPage(getCurrentPage(request));
        List<StuClass> list = stuClassService.search(stuClass.getClassName(), stuClass.getSchoolId(),page);
        if(Objects.isNull(list)){
            List<College> list2= collegeService.queryCollege();
            request.setAttribute("list1",list2);
            request.setAttribute("stuClass",stuClass);

            request.setAttribute("page",page);
            return "/nstar/stuClass/list";

        }
        List<HyperStuClass> list1 = new ArrayList<>();
        for(StuClass stuClass1:list){

            HyperStuClass h = new HyperStuClass();

            h.setClassName(stuClass1.getClassName());

            String name=collegeService.querycollegeName(stuClass1.getSchoolId());

            h.setId(stuClass1.getId());

            h.setCollename(name);

            h.setDescription(stuClass1.getDescription());

            h.setSchoolId(stuClass1.getSchoolId());

            list1.add(h);
        }
        //将课程列表放在request中
        request.setAttribute("list", list1);

        List<College> list2= collegeService.queryCollege();
        request.setAttribute("list1",list2);
        request.setAttribute("stuClass",stuClass);

       request.setAttribute("page",page);
        return "/nstar/stuClass/list";
    }

    @RequestMapping("/updatePage")
    //跳转到修改页面
    public String updatePage(HttpServletRequest request,int id,int currentPage){
        log.info("=> StuClassController:updatePage {}",id);
        List<College> list=collegeService.queryIds();
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("list",list);
        StuClass stuClass = stuClassService.getStuClassById(id);
        request.setAttribute("stuClass", stuClass);
        return "/nstar/stuClass//update";
    }

    @RequestMapping("/update")
    //修改
    public String update(StuClass stuClass,int currentPage){
        log.info("=> StuClassController:update {}",stuClass);
        stuClassService.updateClass(stuClass);
        return "redirect:/admin/stuClass/list?currentPage="+currentPage;
    }

    @RequestMapping("/addClassPage")
    public String addClassPage(HttpServletRequest request){
        log.info("=> StuClassController:addClassPage {}");
        List<College> list=collegeService.queryIds();
        request.setAttribute("list",list);
        return "/nstar/stuClass/addPage";
    }

    @RequestMapping("/addClass")
    public String addClass(StuClass stuClass){
        log.info("=> StuClassController:addClass {}",stuClass);

        stuClassService.addClass(stuClass);
        return "redirect:/admin/stuClass/list";
    }

    @RequestMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
        log.info("=> StuClassController:addClass {}",file);
        InputStream in = file.getInputStream();
        List<StuClass> list = stuClassService.readFileToList(in);
        stuClassService.addClassFromList(list);
        return "redirect:/admin/stuClass/list";
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> downLoad(HttpServletRequest request,HttpServletResponse response) throws IOException {
        log.info("=> StuClassController:downLoad {}");
        String filePath = request.getRealPath("/static") +"/scbjwj.xlsx";
        File f = new File(filePath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "Class List Template.xlsx");

        ResponseEntity respon = new ResponseEntity(FileUtils.readFileToByteArray(f), headers, HttpStatus.CREATED);
        return respon;
    }
}

package com.inxedu.os.nstar.selectCourse.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inxedu.os.common.util.ExcelFileGenerator;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.nstar.selectCourse.service.ExcelFileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 余松涛 on 16-8-2.
 */
@Controller
@RequestMapping("/admin/excelFile")
public class ExcelFileController {
    private static Logger log = LoggerFactory.getLogger(ExcelFileController.class);
    @Autowired
    ExcelFileService excelFileService;
    @Autowired
    CourseService courseService;

    // 打开导入excel的页面，导入到 edu_user表
    @RequestMapping("/addUsers")
    public String addUsers(HttpServletRequest request) {
        log.info("=> 数据导入导出页显示");
        return "/nstar/excelFile/addUsers";
    }
    @RequestMapping("/addCourseTableDo")
    public String addCourSeTableDo(HttpServletRequest request) {
        log.info("=> 数据导入导出页显示");
        return "/nstar/excelFile/addCourseTableDo";
    }

    // 打开导入excel的页面，导入到 edu_stuclass表
    @RequestMapping("/addStuClass")
    public String addStuClass(HttpServletRequest request) {
        log.info("=> 数据导入导出页显示");
        return "/nstar/excelFile/addStuClass";
    }

    // 打开导入excel的页面，导入到 edu_course表
    @RequestMapping("/addCourse")
    public String addCourse(HttpServletRequest request) {
        log.info("=> 数据导入导出页显示");
        return "/nstar/excelFile/addCourse";
    }

    // 打开导入excel的页面，导入到 edu_college表
    @RequestMapping("/addCollege")
    public String addCollege(HttpServletRequest request) {
        log.info("=> 数据导入导出页显示");
        return "/nstar/excelFile/addCollege";
    }

    /**
     * 学生导入
     * user
     * @param request
     * @param myfile
     * @return
     */
    // ======导入 user表 部分===开始====================================
    @RequestMapping("/importUsers")
    public ModelAndView importUsers(HttpServletRequest request,
                                    @RequestParam("myFile") MultipartFile myfile,
                                    @RequestParam("mark") Integer mark) {
        ModelAndView modelandView = new ModelAndView();
        try {
            String importMsg = excelFileService.updateExcelToUsers(request,
                    myfile, mark);
            request.setAttribute("msg", importMsg);
            if (importMsg == null || importMsg.equals("")) {
                modelandView.setViewName("/common/success");
            } else {
                modelandView.setViewName("/common/msg_error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelandView.addObject("msg",e.getMessage());
            modelandView.setViewName("/common/msg_error");
        }
        return modelandView;
    }

    // ======导入 user表 部分===结束====================================

    /**
     * 班级导入
     * StuClass
     * @param request
     * @param myfile
     * @param mark
     * @return
     */
    // ======导入 stuClass表 部分===开始====================================
    @RequestMapping("/importStuClass")
    public ModelAndView importStuClass(HttpServletRequest request,
                                       @RequestParam("myFile") MultipartFile myfile,
                                       @RequestParam("mark") Integer mark) {
        ModelAndView modelandView = new ModelAndView();
        try {
            String importMsg = excelFileService.updateExcelToStuClass(request,
                    myfile, mark);
            request.setAttribute("msg", importMsg);
            if (importMsg == null || importMsg.equals("")) {
                modelandView.setViewName("/common/success");
            } else {
                modelandView.setViewName("/common/msg_error");
            }
        } catch (Exception e) {
            modelandView.addObject("msg",e.getMessage());
            modelandView.setViewName("/common/msg_error");
        }
        return modelandView;
    }

    // ======导入 stuClass表 部分===结束====================================
    /**
     * 课程导入
     * course
     * @param request
     * @param myfile
     * @return
     */
    // ======导入 course表 部分===开始====================================
    @RequestMapping("/importCourse")
    public ModelAndView importCourse(HttpServletRequest request,
                                     @RequestParam("myFile") MultipartFile myfile,
                                     @RequestParam("mark") Integer mark) {
        ModelAndView modelandView = new ModelAndView();
        try {
            String importMsg = excelFileService.updateExcelToCourse(request,
                    myfile, mark);
            request.setAttribute("msg", importMsg);
            if (importMsg == null || importMsg.equals("")) {
                modelandView.setViewName("/common/success");
            } else {
                modelandView.setViewName("/common/msg_error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelandView.addObject("msg",e.getMessage());
            modelandView.setViewName("/common/msg_error");
        }
        return modelandView;
    }

    // ======导入 course表 部分===结束====================================


    /**
     * 学院导入
     * college
     * @param request
     * @param myfile
     * @param mark
     * @return
     */
    // ======导入 college表 部分===开始====================================
    @RequestMapping("/importCollege")
    public ModelAndView importCollege(HttpServletRequest request,
                                      @RequestParam("myFile") MultipartFile myfile,
                                      @RequestParam("mark") Integer mark) {
        ModelAndView modelandView = new ModelAndView();
        try {
            String importMsg = excelFileService.updateExcelToCollege(request,
                    myfile, mark);
            request.setAttribute("msg", importMsg);
            if (importMsg == null || importMsg.equals("")) {
                modelandView.setViewName("/common/success");
            } else {
                modelandView.setViewName("/common/msg_error");
            }
        } catch (Exception e) {
            modelandView.addObject("msg",e.getMessage());
            modelandView.setViewName("/common/msg_error");
        }
        return modelandView;
    }
    // ======导入 college表 部分===结束====================================
   //导入CourseTableDo
    @RequestMapping("/importCourseTableDo")
    public ModelAndView importCourseTableDo(HttpServletRequest request,
                                      @RequestParam("myFile") MultipartFile myfile,
                                      @RequestParam("mark") Integer mark) {
        ModelAndView modelandView = new ModelAndView();
        try {
            String importMsg = excelFileService.updateExcelTocourseTableDo(request,
                    myfile, mark);
            request.setAttribute("msg", importMsg);
            if (importMsg == null || importMsg.equals("")) {
                modelandView.setViewName("/common/success");
            } else {
                modelandView.setViewName("/common/msg_error");
            }
        } catch (Exception e) {
            modelandView.addObject("msg",e.getMessage());
            modelandView.setViewName("/common/msg_error");
        }
        return modelandView;
    }

    /**
     * user导出 学生导出
     *
     * @param request
     * @param response
     * @throws Exception
     */
    // =========导出 user表 部分===开始====================================
    @RequestMapping("/exportUsers")
    public void userListExport(HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        // 1：初始化数据
        // 得到user表头
       List<String> headlist = excelFileService.getUserHead();
        // 得到数据
        List<List<String>> dataList = excelFileService.getUserData();

        // 2：调用封装的POI报表的导出类ExcelFileGenerator.java，完成excel报表的导出
        ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
                headlist, dataList);
        /** 导出报表的文件名 */
        String filename = "选课结果_报表（" + dateToStringWithExcel(new Date()) + "）.xls";
        // 处理乱码
        filename = new String(filename.getBytes("gbk"), "iso-8859-1");
        /** response中进行设置，总结下载，导出，需要io流和头 */
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="
                + filename);
        response.setBufferSize(1024);
        // 获取输出流
        OutputStream os = response.getOutputStream();
        excelFileGenerator.expordExcel(os);// 使用输出流，导出
        return;
    }
    // =========导出 user表 部分===开始====================================
    @RequestMapping("/exportSelectCourseResult/{courseId}")
    public void exportSelectCourseResult(HttpServletRequest request,
                                         HttpServletResponse response, @PathVariable("courseId") Integer courseId) throws Exception {
        // 1：初始化数据
        //
        Course course= courseService.queryCourseById(courseId);
        // 得到user表头
       List<String> headlist = excelFileService.getSelectCourseUserHead();
        // 得到数据
        List<List<String>> dataList = excelFileService.getSelectCourseUserDataByCourseId(courseId);

        // 2：调用封装的POI报表的导出类ExcelFileGenerator.java，完成excel报表的导出
        ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
                headlist, dataList);
        /** 导出报表的文件名 */
        String filename = "选课结果（" +course.getCourseNo()+"_"+course.getCourseName()+"_"+course.getTeacherName()+"_"+ dateToStringWithExcel(new Date()) + "）.xls";
        // 处理乱码
        filename = new String(filename.getBytes("gbk"), "iso-8859-1");
        /** response中进行设置，总结下载，导出，需要io流和头 */
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="
                + filename);
        response.setBufferSize(1024);
        // 获取输出流
        OutputStream os = response.getOutputStream();
        excelFileGenerator.expordExcel(os);// 使用输出流，导出
        return;
    }
    // ======导出 user表 部分===结束====================================

    /**
     * StuClass导出 班级导出
     *
     * @param request
     * @param response
     * @throws Exception
     */
    // 班级导出====开始==========================================================================
    @RequestMapping("/exportStuClass")
    public void stuClassListExport(HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        // 1：初始化数据
        // 得到表头
       List<String> headlist = excelFileService.getstuClassHead();
        // 得到数据
        List<List<String>> dataList = excelFileService
                .getstuClassData();
        // 2：调用封装的POI报表的导出类ExcelFileGenerator.java，完成excel报表的导出
        ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
                headlist, dataList);
        /** 导出报表的文件名 */
        String filename = "班级报表（" + dateToStringWithExcel(new Date()) + "）.xls";
        // 处理乱码
        filename = new String(filename.getBytes("gbk"), "iso-8859-1");
        /** response中进行设置，总结下载，导出，需要io流和头 */
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="
                + filename);
        response.setBufferSize(1024);
        // 获取输出流
        OutputStream os = response.getOutputStream();
        excelFileGenerator.expordExcel(os);// 使用输出流，导出
        return;
    }

    /**
     * Course导出 课程导出
     *
     * @param request
     * @param response
     * @throws Exception
     */
    // =========导出 Course表 部分===开始====================================
    @RequestMapping("/exportCourse")
    public void courseListExport(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // 1：初始化数据
        // 得到表头
       List<String> headlist = excelFileService.getCourseHead();
        // 得到数据
        List<List<String>> dataList = excelFileService
                .getCourseData();
        // 2：调用封装的POI报表的导出类ExcelFileGenerator.java，完成excel报表的导出
        ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
                headlist, dataList);
        /** 导出报表的文件名 */
        String filename = "课程报表（" + dateToStringWithExcel(new Date()) + "）.xls";
        // 处理乱码
        filename = new String(filename.getBytes("gbk"), "iso-8859-1");
        /** response中进行设置，总结下载，导出，需要io流和头 */
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="
                + filename);
        response.setBufferSize(1024);
        // 获取输出流
        OutputStream os = response.getOutputStream();
        excelFileGenerator.expordExcel(os);// 使用输出流，导出
        return;
    }

    // ======导出 Course表 部分===结束====================================

    /**
     * 导出college
     *
     * @param
     * @return
     */
    // =========导出 college表 部分===开始====================================
    @RequestMapping("/exportCollege")
    public void collegeListExport(HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        // 1：初始化数据
        // 得到表头
       List<String> headlist = excelFileService.getCollegeHead();
        // 得到数据
        List<List<String>> dataList = excelFileService
                .getCollegeData();
        // 2：调用封装的POI报表的导出类ExcelFileGenerator.java，完成excel报表的导出
        ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
                headlist, dataList);
        /** 导出报表的文件名 */
        String filename = "学院报表（" + dateToStringWithExcel(new Date()) + "）.xls";
        // 处理乱码
        filename = new String(filename.getBytes("gbk"), "iso-8859-1");
        /** response中进行设置，总结下载，导出，需要io流和头 */
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="
                + filename);
        response.setBufferSize(1024);
        // 获取输出流
        OutputStream os = response.getOutputStream();
        excelFileGenerator.expordExcel(os);// 使用输出流，导出
        return;
    }

    // ======导出 college表 部分===结束====================================





    /**
     * 导出courseBase
     *
     * @param
     * @return
     */
    // =========导出 CourseBase表 部分===开始====================================
    @RequestMapping("/exportCourseBase")
    public void CourseBaseListExport(HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        // 1：初始化数据
        // 得到表头
       List<String> headlist = excelFileService.getCourseBaseHead();
        // 得到数据
        List<List<String>> dataList = excelFileService
                .getCourseBaseData();
        // 2：调用封装的POI报表的导出类ExcelFileGenerator.java，完成excel报表的导出
        ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
                headlist, dataList);
        /** 导出报表的文件名 */
        String filename = "学院报表（" + dateToStringWithExcel(new Date()) + "）.xls";
        // 处理乱码
        filename = new String(filename.getBytes("gbk"), "iso-8859-1");
        /** response中进行设置，总结下载，导出，需要io流和头 */
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="
                + filename);
        response.setBufferSize(1024);
        // 获取输出流
        OutputStream os = response.getOutputStream();
        excelFileGenerator.expordExcel(os);// 使用输出流，导出
        return;
    }

    // ======导出 CourseBase表 部分===结束====================================


    // 辅助函数
    private static String dateToStringWithExcel(Date date) {
        String sDate = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        return sDate;
    }

}

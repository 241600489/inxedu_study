package com.inxedu.os.nstar.selectCourse.controller;



import com.inxedu.os.nstar.selectCourse.entity.course.CourseBase;
import com.inxedu.os.nstar.selectCourse.entity.stuClass.StuClass;

import com.inxedu.os.nstar.selectCourse.entity.stuClass.StuClassSelectabel;

import com.inxedu.os.nstar.selectCourse.service.CourseTableDoService;
import com.inxedu.os.nstar.selectCourse.service.StuClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seapig02 on 7/28 0028.
 */
@Controller
@RequestMapping("/admin/courseRange")
public class CourseRangeController {
    private static final Logger logger = LoggerFactory.getLogger(CourseRangeController.class);
    @Autowired
    private CourseTableDoService eduCourseTableDoService;
    @Autowired
    private StuClassService stuClassService;
    static final String showSelectLessionResult = "/nstar/courseRange/courseRangePage";

    @RequestMapping("/showCourseRangePage")
    public ModelAndView showCourseRangePage(final HttpServletRequest request) throws Exception {

        ModelAndView model = new ModelAndView();
        model.setViewName(showSelectLessionResult);
        try {
            List<CourseBase> courseNameList = eduCourseTableDoService.queryBaseCourseName();
            model.addObject("courseNameList", courseNameList);
        }catch (Throwable throwable){

        }

        return model;
    }

    @RequestMapping("/selectableClass")
    public ModelAndView selectableClass(final HttpServletRequest request,String className) throws Exception {

        ModelAndView model = new ModelAndView();
        List<CourseBase> courseNameList=eduCourseTableDoService.queryBaseCourseName();
        model.addObject("courseNameList",courseNameList);
        List<StuClass> stuClass1=stuClassService.searchName(className);
        int index=0;
        List<StuClassSelectabel> list = new ArrayList<>();
        if(index<stuClass1.size()){
        String ids = stuClass1.get(index).getSeletableCourseId();
        String[] split = ids.split(",");
        for (String s:split) {
            String name="";
            if(!s.equals("")){
                name=eduCourseTableDoService.queryCourseNameById(Integer.parseInt(s));
            }

                StuClass aClass = stuClass1.get(0);
                StuClassSelectabel stuClassSelectabel=new StuClassSelectabel();
                stuClassSelectabel.setClassName(aClass.getClassName());
                stuClassSelectabel.setSeletableCourseId(aClass.getSeletableCourseId());
                stuClassSelectabel.setId(aClass.getId());
                stuClassSelectabel.setDescription(aClass.getDescription());
                stuClassSelectabel.setSchoolId(aClass.getSchoolId());
                stuClassSelectabel.setCoursName(name);


            list.add(stuClassSelectabel);
        }
        }
        model.addObject("list",list);
        model.setViewName(showSelectLessionResult);
        return model;
    }
    @RequestMapping("/addCourseTableDo")
    public ModelAndView addCourseTableDo(StuClassSelectabel stuClassSelectabel, HttpServletRequest request) throws Exception {

        ModelAndView model = new ModelAndView();
        /*System.out.print(stuClass.getClassName());
*/

         StuClass stuClass = (StuClass)stuClassSelectabel;
        String courseId=request.getParameter("eduCourseBase.id");
        /*stuClassService.deleteSelectableClass(stuClass,courseId);*/
        stuClassService.addSelectableClass(stuClass,courseId);
        List<CourseBase> courseNameList=eduCourseTableDoService.queryBaseCourseName();
        model.addObject("courseNameList",courseNameList);

        StuClass stuClass1=stuClassService.getStuClassById(stuClass.getId());
        model.setViewName("redirect:/admin/courseRange/showCourseRangePage");
        return model;
    }
    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(final HttpServletRequest request,@RequestParam("id") Integer id,@RequestParam("seletableCourseId") String seletableCourseId) throws Exception {

        ModelAndView model = new ModelAndView();
        StuClass stuClass= stuClassService.getStuClassById(id);
        System.out.print(stuClass.getClassName());
        int ids=0;
        if("".equals(seletableCourseId)) {
            model.setViewName("redirect:/admin/courseRange/showCourseRangePage");
            return model;
           }
        ids = eduCourseTableDoService.queryCourseIdByName(seletableCourseId);
            String ids1 = Integer.toString(ids);
            stuClassService.deleteSelectableClass(stuClass, ids1);
        model.setViewName("redirect:/admin/courseRange/showCourseRangePage");
        return model;

    }
}

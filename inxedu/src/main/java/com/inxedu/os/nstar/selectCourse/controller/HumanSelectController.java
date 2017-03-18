package com.inxedu.os.nstar.selectCourse.controller;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.user.HyberUser;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.nstar.selectCourse.entity.SelectCourseState;
import com.inxedu.os.nstar.selectCourse.entity.college.College;
import com.inxedu.os.nstar.selectCourse.entity.stuClass.StuClass;
import com.inxedu.os.nstar.selectCourse.service.CollegeService;
import com.inxedu.os.nstar.selectCourse.service.CourseControllerService;
import com.inxedu.os.nstar.selectCourse.service.SelectLessionService;
import com.inxedu.os.nstar.selectCourse.service.StuClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.inxedu.os.nstar.selectCourse.entity.SelectCourseState.FAIL;

/**
 * Created by 3 on 2016/8/2.
 */
@Controller
@RequestMapping("/admin/humanselect")
public class HumanSelectController extends BaseController {
    static final String showSelectLessionResult = "/nstar/humanselect/studentSelect";
    static final String showSelectLessionResult1 = "/nstar/humanselect/selectcourse";
    static final String redirectIndex = "redirect:/admin";
    static  final String redrect="redirect:/admin/humanselect/studentSelect";
    static final String showSelectLessionResult2 = "/nstar/humanselect/selectCourseList";
    static final String showSelectLessionResult3 = "/nstar/humanselect/mycourse";
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private UserService userService;
    @Autowired
    private StuClassService stuClassService;
    @Autowired
    SelectLessionService selectLessionService;
    @Autowired
    private CourseControllerService courseControllerService;
    private static final Logger logger = LoggerFactory.getLogger(HumanSelectController.class);
    @RequestMapping("/studentSelect")
    public ModelAndView selectStudent(final HttpServletRequest request) throws Exception {
        logger.debug("---->>>HumanSelectCOntroller:selectStudent{}");
        ModelAndView model = new ModelAndView();
        List<College> list1= collegeService.queryCollege();
        model.addObject("list1",list1);

        model.setViewName(showSelectLessionResult);
        return model;
    }
    @RequestMapping("/studentSelect1")
    @ResponseBody
    public List<StuClass> selectLClass1(final HttpServletRequest request) throws Exception {
        logger.debug("---->>>HumanSelectController:selectStudent{}");
        ModelAndView model = new ModelAndView();
    /*    List<College> list1= collegeService.queryCollege();
        model.addObject("list",list1);*/
        String collegeId=  request.getParameter("collegeId");
         List<StuClass> list= stuClassService.queryCollegeByCollegeId(Integer.parseInt(collegeId));
        this.setJson(true,null,list);
             return  list;
    }
    @RequestMapping("/studentSelect2")
    public ModelAndView selectStudent2(final HttpServletRequest request, PageEntity page) throws Exception {
        page.setCurrentPage(getCurrentPage(request));
       String no= request.getParameter("no");

      page.setPageSize(20);
        logger.debug("---->>>HumanSelectController:selectStudent2{}");
        ModelAndView model = new ModelAndView();



        model.addObject("collegeId1",request.getParameter("schoolId"));
        List<College> list1= collegeService.queryCollege();
        model.addObject("list1",list1);
        if(!("".equals(no))){
            List<HyberUser> list2=  userService.queryUserByNo(no,page);
            model.addObject("list",list2);
            model.addObject("page",page);
        }else{
            String collegeId=request.getParameter("");
            String classId=request.getParameter("classId");
            if(StringUtils.isBlank(classId)){
                model.setViewName(showSelectLessionResult);
                return model;
            }
            List<HyberUser> list2= userService.queryUserByClassId(Integer.parseInt(classId),page);
            model.addObject("classId",classId);
            model.addObject("list",list2);
            model.addObject("page",page);
        }
        model.setViewName(showSelectLessionResult);
        return model;
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
    @RequestMapping("/showValidLocation")
    public ModelAndView showValidCourseLocation(final HttpServletRequest request,HyberUser hyberUser) throws Exception {

        ModelAndView model = new ModelAndView();
       User user= userService.queryUserById(hyberUser.getUserId());
        model.addObject("user",user);
        if (hyberUser.getUserId() > 0) {
            logger.info("=> 用户:{} showValidCourseLocation", hyberUser.getUserId());
            if (selectLessionService.checkTime() && (!selectLessionService.isSelectCourse(hyberUser.getClassId()))) {
                model.setViewName(showSelectLessionResult1);
                boolean[][]   result;
                if (check()){
                    result = selectLessionService.showValidCourseLocation(hyberUser.getClassId());
                }else{
                    result = selectLessionService.showValidCourseLocation();
                }
                model.addObject("table", result);
                return model;
            } else {
                model.setViewName(showSelectLessionResult1);
            }
        } else {
            model.setViewName(showSelectLessionResult1);
        }
        return model;
    }
    final int minWeekId = 1;
    final int maxWeekId = 6;
    final int minSessionId = 1;
    final int maxSessionId = 5;


    boolean isValidTable(int weekID, int sessionId) {
        return weekID >= minWeekId && weekID <= maxWeekId && sessionId >= minSessionId && sessionId <= maxSessionId;
    }

    boolean isValidTable(int datePosition) {
        try {
            int weekID = datePosition / 10;
            int sessionId = datePosition - weekID * 10;
            return isValidTable(weekID, sessionId);
        } catch (Throwable e) {
            return false;
        }
    }
    boolean check(){
        if(courseControllerService.getEduCourseController().getType()==1){
            return true;
        }
        else {
            return false;
        }
    }
    @RequestMapping("/showValidCourseInfo/{userId}/{datePosition}")
    public ModelAndView showValidCourseInfo(final HttpServletRequest request, @PathVariable("datePosition") int datePosition, @PathVariable("userId") int userId) throws Exception {

        ModelAndView model = new ModelAndView();
        User user=userService.queryUserById(userId);
        if (!(userId > 0)) {
            model.setViewName(redirectIndex);
            return model;
        }

        if (!isValidTable(datePosition)) {
          logger.error("=> 用户:{} showSelectLessionTable datePosition:范围错误{}", userId, datePosition);
            model.setViewName(redrect);
            return model;
        }
        if (!selectLessionService.checkTime()) {
            model.addObject("msg", "不在选课时间");
            model.setViewName(showSelectLessionResult);
            return model;
        }

        if (selectLessionService.isSelectCourse(userId)) {
            model.addObject("msg", "已选过课");

            model.setViewName(showSelectLessionResult);
            return model;
        }

        int weekID = datePosition / 10;
        int sessionId = datePosition - weekID * 10;

        List<Course> courses;
        if (check()){
            courses = selectLessionService.showValidCourseInfo(String.valueOf(weekID), String.valueOf(sessionId), user.getClassId());
        }else {
            courses = selectLessionService.getValidCourse(String.valueOf(weekID), String.valueOf(sessionId));

        }
        if(!courses.isEmpty()){
            courses.sort((f, s) -> {
                double o = f.getResidaulCount() * 1.0 / f.getMaxNumber();
                double o2 = s.getResidaulCount() * 1.0 / s.getMaxNumber();
                if (o > o2) {
                    return -1;
                } else if (o < o2) {
                    return 1;
                } else {
                    return 0;
                }
            });
        }

       logger.info("=> 用户:{} showValidCourseInfo", userId);
        model.addObject("list", courses);
        model.setViewName(showSelectLessionResult2);
        User user1= userService.queryUserById(user.getUserId());
        model.addObject("user",user1);
        return model;

    }

    @RequestMapping("/selectCourse/{userId}/{id}")
    @ResponseBody
    public Map<String, Object> selectCourse(final HttpServletRequest request, @PathVariable("id") final Integer courseId, @PathVariable("userId") Integer userId, HttpServletResponse response)throws IOException {
        User user= userService.queryUserById(userId);
        String ajax="";
        response.setContentType("text/html;charset=utf-8");
        if (!(user.getUserId() > 0)) {
            ajax= "不存在此学生";
         return setJson(true,ajax,null);
        }
        SelectCourseState result=FAIL;
        userId = user.getUserId();
        logger.info("=> 用户:{} selectCourse userId:{},courseId:{}", userId, courseId);
        if(check()){

            result =
                    selectLessionService.selectCourseWithCourseController(userId, courseId, user.getClassId());
        }else {

            if(selectLessionService.checkTime()){
                result = selectLessionService.selectCourseCore(user.getUserId(),courseId);
            }
        }


        switch (result) {
            case SUCCESS: {
                ajax="选课成功";
                logger.info("=> admin:{} selectCourse userId:{},courseId:{} success", userId, courseId);
                break;
            }
            case REPLY: {
                ajax="已选课";
                logger.info("=> admin:{} selectCourse userId:{},courseId:{} reply", userId, courseId);
                break;
            }
            case ILLEGAL_TIME: {
                ajax="不在选课时间";
                logger.info("=> admin:{} selectCourse userId:{},courseId:{} illegal", userId, courseId);
                break;
            }
            default: {
               ajax="错误";
                logger.error("=> admin:{} selectCourse userId:{},courseId:{} error", userId, courseId);
                break;
            }
        }
        return setJson(true,ajax,null);
    }

    @RequestMapping("/backCourse/{userId}/{id}")
    @ResponseBody
    public  Map<String, Object> backCourse(final HttpServletRequest request, @PathVariable("id") int courseId,@PathVariable("userId") Integer userId,HttpServletResponse response)throws IOException {

        response.setContentType("text/html;charset=utf-8");
        User user= userService.queryUserById(userId);
        String ajax="";
        if (!(user.getUserId() > 0)) {
            ajax="用户不存在";

            return setJson(true,ajax,null);
        }
        userId = user.getUserId();
        SelectCourseState result=FAIL;
        if(check()){
            result =
                    selectLessionService.backCourseWithCourseController(userId, courseId, user.getClassId());

        }else {
            if(selectLessionService.checkTime()){
                result =
                        selectLessionService.backCourseCore(userId, courseId);
            }
        }
        logger.info("=> 用户:{} backCourse userId:{},courseId:{}", userId, courseId);
        switch (result) {
            case SUCCESS: {

                ajax="退课成功";

                logger.info("=> 用户:{} backCourse userId:{},courseId:{} success", userId, courseId);
                break;
            }
            case ILLEGAL_TIME: {

               ajax="不在选课时间";
                logger.info("=> 用户:{} backCourse userId:{},courseId:{} illegal", userId, courseId);
                break;
            }
            default: {

                ajax="发生错误";
                logger.info("=> 用户:{} backCourse userId:{},courseId:{} error", userId, courseId);
                break;
            }
        }
        return setJson(true,ajax,null);
    }

    @RequestMapping("/backCoursePage")
    public ModelAndView backCoursePage(final HttpServletRequest request,int userId) throws Exception {
        logger.debug("---->>>HumanSelectCOntroller:backCoursePage{}");
        ModelAndView model = new ModelAndView();
        model.addObject("userId",userId);

        List<Course> list = selectLessionService.showMyCourse(userId);
        model.addObject("list",list);
         model.setViewName(showSelectLessionResult3);
        return model;
    }


}

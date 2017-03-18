package com.inxedu.os.nstar.selectCourse.controller;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.nstar.selectCourse.entity.SelectCourseState;
import com.inxedu.os.nstar.selectCourse.service.CourseControllerService;
import com.inxedu.os.nstar.selectCourse.service.OneLevelAsynContextService;
import com.inxedu.os.nstar.selectCourse.service.SelectLessionService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.inxedu.os.nstar.selectCourse.entity.SelectCourseState.*;

/**
 * Created by 陈俊文 on 16-7-20.
 */
@Controller
@RequestMapping("/uc")
public class SelectLessionFrontController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SelectLessionFrontController.class);
    //  final private SelectLessionFrontSerivce selectLessionFrontSerivce;


    static final String showTimeTablePath = "/inxedu/web/ucenter/selectCourse/courseLocation";
    static final String showSelectLessionResult = "/inxedu/web/ucenter/selectCourse/courseResultInfo";
    static final String showSelectLessionList = "/inxedu/web/ucenter/selectCourse/courseInfo";
    static final String msg = "/inxedu/web/ucenter/selectCourse/msg";
    static final String redirectIndex = "redirect:/index";
    final static String showValidCourseInfoPath = "/uc/showValidCourseInfo/";

    @Autowired
    SelectLessionService selectLessionService;
    @Autowired
    OneLevelAsynContextService oneLevelAsynContextService;
    @Autowired
    CourseControllerService courseControllerService;

    @RequestMapping("/showValidCourseLocation")
    public ModelAndView showValidCourseLocation(final HttpServletRequest request) throws Exception {
        User user = SingletonLoginUtils.getLoginUser(request);
        int userId = user.getUserId();
        ModelAndView model = new ModelAndView();
        if (userId > 0) {
            boolean[][]   result;
            info("=> 用户:{} showValidCourseLocation", userId);
            model.setViewName(showTimeTablePath);
            if (check()){
               result = selectLessionService.showValidCourseLocation(user.getClassId());
            }else{
            result = selectLessionService.showValidCourseLocation();
            }
            model.addObject("table", result);
            return model;
        } else {
            model.setViewName(redirectIndex);
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

    @RequestMapping("/showValidCourseInfo/{datePosition}")
    public ModelAndView showValidCourseInfo(final HttpServletRequest request, @PathVariable("datePosition") int datePosition) throws Exception {
/*        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; ++i) {
            Integer a = i;
            executorService.submit(() -> {

                selectLessionService.selectCourse()

                System.out.println( "66666_" + a);


            });
        }*/

        User user = SingletonLoginUtils.getLoginUser(request);
        int userId = user.getUserId();

        ModelAndView model = new ModelAndView();
        model.addObject("userId",userId);
        if (!(userId > 0)) {
            model.setViewName(redirectIndex);
            return model;
        }
        if (!isValidTable(datePosition)) {
            log.error("=> 用户:{} showSelectLessionTable datePosition:范围错误{}", userId, datePosition);
            model.setViewName("redirect:" + "/uc/showValidCourseLocation");
            return model;
        }
        if (!selectLessionService.checkTime()) {
            model.addObject("msg", "不在选课时间");
            model.setViewName(showSelectLessionResult);
            return model;
        }
        int weekID = datePosition / 10;
        int sessionId = datePosition - weekID * 10;
        try {
            List<Course> courses;
            if (check()){
                courses = selectLessionService.showValidCourseInfo(String.valueOf(weekID), String.valueOf(sessionId), user.getClassId());
            }else {
                courses = selectLessionService.getValidCourse(String.valueOf(weekID), String.valueOf(sessionId));

            }
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

            info("=> 用户:{} showValidCourseInfo", userId);
            model.addObject("list", courses);
        } catch (Exception e) {
            error("=> 用户:{} showValidCourseInfo", e);
            System.out.println(e);
        }
        model.setViewName(showSelectLessionList);
        return model;

    }

    @Autowired
    CourseService courseService;

    @RequestMapping("/getCourseLabByCourseTheoryIdByView/{id}")
    @ResponseBody
    public Map<String,Object> getCourseLabByCourseTheoryIdByView(@PathVariable("id") final Integer courseId) {
        Course course = courseService.queryCourseById(courseId);
        if (course != null) {
            Course courseLab = courseService.queryCourseById(course.getLabCourseId());

               String s1="理论课程名称:"+ CourseToView(course);
               /* String s2="实验室课程名称:"+CourseToView(courseLab);*/
                return this.setJson(true,s1+"\n"+"认真记住以上信息",null);
        } else {

        }
        return MapUtils.EMPTY_MAP;
    }

    private String CourseToView(Course course){
      String  week1= course.getWeek1()!=null?"星期一  第"+course.getWeek1()+"大节"+"("+course.getWeekIds()+")":"";
        String  week2= course.getWeek2()!=null?"星期二  第"+course.getWeek2()+"大节"+"("+course.getWeekIds()+")":"";
        String  week3= course.getWeek3()!=null?"星期三  第"+course.getWeek3()+"大节"+"("+course.getWeekIds()+")":"";
        String  week4= course.getWeek4()!=null?"星期四  第"+course.getWeek4()+"大节"+"("+course.getWeekIds()+")":"";
        String  week5= course.getWeek5()!=null?"星期五  第"+course.getWeek5()+"大节"+"("+course.getWeekIds()+")":"";
       if (!"".equals(week1)){
           String s1=course.getCourseName()+"\n"+"上课教室: "+course.getClassroom()+"\n"+"上课时间: "+week1;
           return s1;
       }
        else if(!"".equals(week2)){
           String s1=course.getCourseName()+"\n"+"上课教室: "+course.getClassroom()+"\n"+"上课时间: "+week2;
           return s1;
       }
        else if (!"".equals(week3)){
           String s1=course.getCourseName()+"\n"+"上课教室: "+course.getClassroom()+"/n"+"上课时间: "+week3;
           return s1;
       }
        else if (!"".equals(week4)){
           String s1=course.getCourseName()+"\n"+"上课教室: "+course.getClassroom()+"\n"+"上课时间: "+week4;
           return s1;
       }
        else {
           String s1=course.getCourseName()+"\n"+"上课教室: "+course.getClassroom()+"\n"+"上课时间: "+week5;
           return s1;
       }

    }
    @RequestMapping("/selectCourseAsyn/{id}")
    @ResponseBody
    public void selectCourseAsyn(final HttpServletRequest request, @PathVariable("id") final Integer courseId) {
        final User user = SingletonLoginUtils.getLoginUser(request);
        final Boolean isValidUser = (isValidUser(user)) ? Boolean.TRUE : Boolean.FALSE;
        final Integer userId = user.getUserId();
        final Integer classId = user.getClassId();

        if(user.getMobile()==null){
            oneLevelAsynContextService.submitFuture(request, ()->String.format("{\"msg\":\"没有填手机号或者手机号格式错误\",\"{}\":\"{}\"}", userId, courseId));
        }

        if(!StringUtils.isMobileNo(user.getMobile())){
            oneLevelAsynContextService.submitFuture(request, ()->String.format("{\"msg\":\"没有填手机号或者手机号格式错误\",\"{}\":\"{}\"}", userId, courseId));
        }

        oneLevelAsynContextService.submitFuture(request, () -> {
                    try {
                        if (!isValidUser) {
                            return "{\"msg\":\"login\"}";
                        }

                        SelectCourseState result =
                                selectLessionService.selectCourseWithCourseController(userId, courseId, classId);
                        switch (result) {
                            case SUCCESS: {
                                return String.format("{\"msg\":\"success\",\"{}\":\"{}\"}", userId, courseId);
                            }
                            case REPLY: {
                                return String.format("{\"msg\":\"reply\",\"{}\":\"{}\"}", userId, courseId);
                            }
                            case ILLEGAL_TIME: {
                                return String.format("{\"msg\":\"illegal\",\"{}\":\"{}\"}", userId, courseId);
                            }
                            case COURSE_COUNT_ZERO: {
                                return String.format("{\"msg\":\"courseCountZero\",\"{}\":\"{}\"}", userId, courseId);
                            }
                            default: {
                                return String.format("{\"msg\":\"error\",\"{}\":\"{}\"}", userId, courseId);
                            }
                        }
                    } catch (Throwable throwable) {
                        return String.format("{\"msg\":\"error\",\"{}\":\"{}\"}", userId, courseId);
                    }
                }
        );
    }
    boolean check(){
        if(courseControllerService.getEduCourseController().getType()==1){
            return true;
        }
        else {
            return false;
        }
    }
    @RequestMapping("/selectCourse/{id}")
    public String selectCourse(final HttpServletRequest request, @PathVariable("id") final Integer courseId) {

        User user = SingletonLoginUtils.getLoginUser(request);
        String path = redirectIndex;
        if (!isValidUser(user)) {
            return path;
        }

        int userId = user.getUserId();
        log.info("=> 用户:{} selectCourse userId:{},courseId:{}", userId, courseId);

        if(user.getMobile()==null){
            return String.format("{\"msg\":\"没有填手机号或者手机号格式错误\",\"{}\":\"{}\"}", userId, courseId);
        }

        if(!StringUtils.isMobileNo(user.getMobile())){
           return String.format("{\"msg\":\"没有填手机号或者手机号格式错误\",\"{}\":\"{}\"}", userId, courseId);
        }
        SelectCourseState result=FAIL;
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
                List<Course> list = selectLessionService.showMyCourse(userId);
/*
                List<Course> newList=new ArrayList<>(list);
                //List<Course> lab=new ArrayList<>();
                for(Course course:list) {
                    Integer id= course.getLabCourseId();
                    if(id!=null){
                        newList.add(courseService.queryCourseById(id));
                    }*/
                /*}*/
                request.setAttribute("list", list);
                request.setAttribute("msg", "选课成功");
                path = showSelectLessionResult;
                log.info("=> 用户:{} selectCourse userId:{},courseId:{} success", userId, courseId);
                break;
            }
            case REPLY: {
                List<Course> list = selectLessionService.showMyCourse(userId);
             /*   List<Course> newList=new ArrayList<>(list);
                //List<Course> lab=new ArrayList<>();
                for(Course course:list) {
                    Integer id= course.getLabCourseId();
                    if(id!=null){
                        newList.add(courseService.queryCourseById(id));
                    }
                }*/
                request.setAttribute("list", list);
                request.setAttribute("msg", "课程你已经选过");
                path = showSelectLessionResult;
                log.info("=> 用户:{} selectCourse userId:{},courseId:{} reply", userId, courseId);
                break;
            }
            case COURSE_COUNT_ZERO:{
                List<Course> list = selectLessionService.showMyCourse(userId);
                request.setAttribute("list", list);
                request.setAttribute("msg", "课余量为零");
                path = showSelectLessionResult;
                log.info("=> 用户:{} selectCourse userId:{},courseId:{} course_count_zero", userId, courseId);
                break;
            }
            case ILLEGAL:{
                List<Course> list = selectLessionService.showMyCourse(userId);
                request.setAttribute("list", list);
                request.setAttribute("msg", "选课失败");
                path = showSelectLessionResult;
                log.info("=> 用户:{} selectCourse userId:{},courseId:{} illegal", userId, courseId);
                break;
            }
            case ILLEGAL_TIME: {
                List<Course> list = selectLessionService.showMyCourse(userId);
                request.setAttribute("list", list);
                request.setAttribute("msg", "不在选课时间");
                path = showSelectLessionResult;
                log.info("=> 用户:{} selectCourse userId:{},courseId:{} illegal", userId, courseId);
                break;
            }
            case FAIL: {
                List<Course> list = selectLessionService.showMyCourse(userId);
                request.setAttribute("list", list);
                request.setAttribute("msg", "选课失败");
                path = showSelectLessionResult;
                log.info("=> 用户:{} selectCourse userId:{},courseId:{} fail", userId, courseId);
                break;
            }
            default: {
                List<Course> list = selectLessionService.showMyCourse(userId);

                request.setAttribute("list", list);
                request.setAttribute("msg", "发生错误");
                log.info("=> 用户:{} selectCourse userId:{},courseId:{} error", userId, courseId);
                path = showSelectLessionResult;
                break;
            }
        }
        return path;
    }

    private static boolean isValidUser(User user) {
        int userId;
        if (ObjectUtils.isNull(user)) {
            return false;
        } else {
            userId = user.getUserId();
            if (!(userId > 0)) {
                return false;
            } else {
                return true;
            }
        }
    }
    @Autowired
    UserService userService;
    @RequestMapping("/backCourse/{id}/{idCard}")
    @ResponseBody
    public Map<String,Object> backCourse(final HttpServletRequest request, @PathVariable("id") int courseId, @PathVariable("idCard") String idCard) {

        Map<String,Object> map=new HashedMap();
        User user = SingletonLoginUtils.getLoginUser(request);

       String json="";
        if (!isValidUser(user)) {
            json="非法用户";
            return this.setJson(false,json,user);
        }
        user= userService.queryUserById(user.getUserId());
        String trueIdCard =user.getIdCard();
        int userId = user.getUserId();
        if(!trueIdCard.equals(idCard)){
            json= "请输入正确的身份证号";

            info("=> 用户:{} trueIdCard error userId:{},courseId:{} illegal idcard{}", userId, courseId,idCard);
            return this.setJson(false,json,user);
        }


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

        info("=> 用户:{} backCourse userId:{},courseId:{}", userId, courseId);
        switch (result) {
            case SUCCESS: {
                json="退课成功";
                map=this.setJson(true,json,user);
                info("=> 用户:{} backCourse userId:{},courseId:{} success", userId, courseId);
                break;
            }
            case ILLEGAL_TIME: {
                 json="不在退课时间";
                map=this.setJson(false,json,user);
                info("=> 用户:{} backCourse userId:{},courseId:{} illegal", userId, courseId);
                break;
            }
            case COURSE_COUNT_ZERO: {
                json= "你没课可以退了";
                map=this.setJson(false,json,user);
                info("=> 用户:{} backCourse userId:{},courseId:{} illegal", userId, courseId);
                break;
            }
            default: {
               json="发生错误";
                map=this.setJson(false,json,user);
                info("=> 用户:{} backCourse userId:{},courseId:{} error", userId, courseId);
                break;
            }
        }
        return map;
    }
    @RequestMapping("/backCourse/{id}")
    public ModelAndView backCourse(final HttpServletRequest request, @PathVariable("id") int courseId) {
        User user = SingletonLoginUtils.getLoginUser(request);

        ModelAndView model = new ModelAndView();
        model.setViewName(redirectIndex);

        if (!isValidUser(user)) {
            return model;
        }

        int userId = user.getUserId();


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

        info("=> 用户:{} backCourse userId:{},courseId:{}", userId, courseId);
        switch (result) {
            case SUCCESS: {
                List<Course> list = selectLessionService.showMyCourse(userId);
                model.addObject("list", list);
                model.addObject("msg", "退课成功");
                model.setViewName(showSelectLessionResult);
                info("=> 用户:{} backCourse userId:{},courseId:{} success", userId, courseId);
                break;
            }
            case ILLEGAL_TIME: {
                List<Course> list = selectLessionService.showMyCourse(userId);
                model.addObject("list", list);
                model.addObject("msg", "不在选课时间");
                model.setViewName(showSelectLessionResult);
                info("=> 用户:{} backCourse userId:{},courseId:{} illegal", userId, courseId);
                break;
            }
            case COURSE_COUNT_ZERO: {
                List<Course> list = selectLessionService.showMyCourse(userId);
                model.addObject("list", list);
                model.addObject("msg", "你没课可以退了");
                model.setViewName(showSelectLessionResult);
                info("=> 用户:{} backCourse userId:{},courseId:{} illegal", userId, courseId);
                break;
            }
            default: {
                List<Course> list = selectLessionService.showMyCourse(userId);
                model.addObject("list", list);
                model.addObject("msg", "发生错误");
                model.setViewName(showSelectLessionResult);
                info("=> 用户:{} backCourse userId:{},courseId:{} error", userId, courseId);
                break;
            }
        }
        return model;
    }
    @RequestMapping("/myCourse")
    public ModelAndView showCourse(final HttpServletRequest request, ModelAndView model) throws Exception {
        Integer userId = SingletonLoginUtils.getLoginUserId(request);
        if (userId > 0) {
            log.info("=> 用户:{} showCourse userId:{}", userId);
            List<Course> list = selectLessionService.showMyCourse(userId);
      /* List<Course> newList=new ArrayList<>(list);
            //List<Course> lab=new ArrayList<>();
            for(Course course:list) {
               Integer id= course.getLabCourseId();
                if(id!=null){
                    newList.add(courseService.queryCourseById(id));
                }
            }*/
            model.addObject("list", list);
            model.addObject("msg", "");
            model.setViewName(showSelectLessionResult);
        } else {
            model.setViewName(redirectIndex);
        }
        return model;
    }


    public static void toView(StringBuilder s, int id, boolean b) {
        if (b) {
            s.append("<td class=\"td1\"><a class=\"color\" href=\"" + showValidCourseInfoPath).append(id).append("\">可选课位</a></td>");
        } else {
            s.append("<td class=\"td2\"><a href=\"#\" class=\"color\"></a></td>");
        }
        ;
    }

    public static StringBuilder toRow(StringBuilder table, boolean[][] b, int sessionId) {
        table.append("<tr>");
        switch (sessionId) {
            case 0:
                table.append("<th scope=\"row\" class=\"active\">第一大节</th>");
                break;
            case 1:
                table.append("<th scope=\"row\" class=\"active\">第二大节</th>");
                break;
            case 2:
                table.append("<th scope=\"row\" class=\"active\">第三大节</th>");
                break;
            case 3:
                table.append("<th scope=\"row\" class=\"active\">第四大节</th>");
                break;
            case 4:
                table.append("<th scope=\"row\" class=\"active\">第五大节</th>");
                break;
            default:
                return null;
        }

        for (int i = 0; i < 6; ++i) {
            toView(table, (i + 1) * 10 + (sessionId + 1), b[sessionId][i]);
        }
        table.append("</tr>");
        return table;

    }

    public static StringBuilder toTable(boolean[][] b) {
        StringBuilder result = new StringBuilder(128);
        for (int i = 0; i < 5; ++i) {
            toRow(result, b, i);
            switch (i) {
                case 1: {
                    result.append("<tr><th colspan=\"7\"></th></tr>");
                    break;
                }
                case 3: {
                    result.append("<tr><th colspan=\"7\"></th></tr>");
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return result;
    }

    private void info(String var2, Object... var3) {
        StringBuilder stringBuilder = new StringBuilder(var2);
        for (Object it : var3) {
            stringBuilder.append("_").append(it);
        }
        String s = stringBuilder.toString();
        log.info(var2, var3);
    }

    private void error(String var2, Object... var3) {
        log.error(var2, var3);
    }
}

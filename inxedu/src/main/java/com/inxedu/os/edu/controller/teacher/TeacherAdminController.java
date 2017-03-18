package com.inxedu.os.edu.controller.teacher;

import com.inxedu.os.common.cache.EHCacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.entity.teacher.Teacher;
import com.inxedu.os.edu.service.article.ArticleService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.questions.QuestionsService;
import com.inxedu.os.edu.service.statistics.StatisticsDayService;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.nstar.appointment.entity.examManager.ExamManager;
import com.inxedu.os.nstar.appointment.entity.examStudent.ExamStudent;
import com.inxedu.os.nstar.appointment.entity.examStudentAppointment.ExamStudentAppointment;
import com.inxedu.os.nstar.appointment.service.ExamManagerService;
import com.inxedu.os.nstar.appointment.service.ExamStudentAppointmentService;
import com.inxedu.os.nstar.appointment.service.ExamStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangziqiang on 2016/11/17.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherAdminController extends BaseController{
    //private static final Logger logger = LoggerFactory.getLogger(TeacherAdminController.class);
    private static final Logger logger=LoggerFactory.getLogger(TeacherAdminController.class);
    private static final String REDIRECT_PATH="redirect:/teacher";
    private static String mainIndexPage = getViewPath("/admin/main/index");//后台操作中心初始化首页

    @Autowired
    private ExamStudentService examStudentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private StatisticsDayService statisticsDayService;
    @Autowired
    private QuestionsService questionsService;
    @Autowired
    private ExamStudentAppointmentService examStudentAppointmentService;
    @Autowired
    private ExamManagerService examManagerService;


    @RequestMapping("/mystudent")
    public String SelectMystudent(HttpServletRequest request,PageEntity page){
        page.setPageSize(20);
        int teacherId= SingletonLoginUtils.getLoginTeaUserId(request);
        List<ExamStudent> list=examStudentService.queryExamStudentByTeacherId(teacherId,page);
        request.setAttribute("list",list);
        request.setAttribute("page",page);
        return getViewPath("/teacher/main/mystudentlist");
    }
    @RequestMapping("/searchmystudent")
    public String SelectMystudentByNo(HttpServletRequest request,PageEntity page,String studentNo){
        int teacherId= SingletonLoginUtils.getLoginTeaUserId(request);
        if(request.getParameter("page.currentPage")!=null){
            int currentPage=Integer.parseInt(request.getParameter("page.currentPage"));
            page.setCurrentPage(currentPage);
        }
        String studentName=request.getParameter("studentName");
        String stuClass=request.getParameter("stuClass");
        page.setPageSize(20);
        List<ExamStudent> list;
        list=examStudentService.queryExamStudentList(page,teacherId,studentNo,studentName,stuClass);
        request.setAttribute("studentNo",studentNo);
        request.setAttribute("studentName",studentName);
        request.setAttribute("stuClass",stuClass);
        request.setAttribute("list",list);
        request.setAttribute("page",page);
        return getViewPath("/teacher/main/mystudentlist");
    }
    @RequestMapping("/appointresult")
    public String Appointresult(HttpServletRequest request, String examName, PageEntity page){
        if(request.getParameter("page.currentPage")!=null){
            int currentPage=Integer.parseInt(request.getParameter("page.currentPage"));
            page.setCurrentPage(currentPage);
        }
        page.setPageSize(20);
        int teacherId= SingletonLoginUtils.getLoginTeaUserId(request);
        List<ExamStudentAppointment> list=examStudentAppointmentService.queryExamAppointmentStudentByTeacherIdAndExamName(teacherId, null, null, null, examName,page);
        request.setAttribute("list",list);
        request.setAttribute("page",page);
        request.setAttribute("examName",examName);
        return getViewPath("/teacher/main/haveappoint");
    }
    @RequestMapping("/noappointresult")
    public String Noappointresult(HttpServletRequest request,String examName,PageEntity page){
        int teacherId= SingletonLoginUtils.getLoginTeaUserId(request);
        page.setPageSize(20);
        if(request.getParameter("page.currentPage")!=null){
            int currentPage=Integer.parseInt(request.getParameter("page.currentPage"));
            page.setCurrentPage(currentPage);
        }
        List<ExamStudent> list=examStudentAppointmentService.queryNoExamAppointmentStudentByTeacherIdAndExamName(teacherId, null, null, null, examName,page);
        request.setAttribute("list",list);
        request.setAttribute("page",page);
        request.setAttribute("examName",examName);
        return getViewPath("/teacher/main/havenoappoint");
    }
    @RequestMapping("/searchmynoappstudent")
    public String Searchmynoappstudent(HttpServletRequest request,PageEntity page){
        String examName=request.getParameter("examName");
        String studentNo=request.getParameter("studentNo");
        String studentName=request.getParameter("studentName");
        String stuClass=request.getParameter("stuClass");

        int teacherId= SingletonLoginUtils.getLoginTeaUserId(request);
        page.setPageSize(20);
        if(request.getParameter("page.currentPage")!=null){
            int currentPage=Integer.parseInt(request.getParameter("page.currentPage"));
            page.setCurrentPage(currentPage);
        }
        List<ExamStudent> list=examStudentAppointmentService.queryNoExamAppointmentStudentByTeacherIdAndExamName(teacherId,studentNo,studentName,stuClass,examName,page);
        request.setAttribute("list",list);
        request.setAttribute("page",page);
        request.setAttribute("examName",examName);
        request.setAttribute("studentNo",studentNo);
        request.setAttribute("studentName",studentName);
        request.setAttribute("stuClass",stuClass);
        return getViewPath("/teacher/main/havenoappoint");
    }
    @RequestMapping("/searchmyappstudent")
    public String Searchmyappstudent(HttpServletRequest request,PageEntity page){
        int teacherId= SingletonLoginUtils.getLoginTeaUserId(request);
        String examName=request.getParameter("examName");
        String studentNo=request.getParameter("studentNo");
        String studentName=request.getParameter("studentName");
        String stuClass=request.getParameter("stuClass");

        page.setPageSize(20);
        if(request.getParameter("page.currentPage")!=null){
            int currentPage=Integer.parseInt(request.getParameter("page.currentPage"));
            page.setCurrentPage(currentPage);
        }
        List<ExamStudentAppointment> list=examStudentAppointmentService.queryExamAppointmentStudentByTeacherIdAndExamName(teacherId,studentName,studentNo,stuClass,examName,page);
        request.setAttribute("list",list);
        request.setAttribute("page",page);
        request.setAttribute("examName",examName);
        request.setAttribute("studentNo",studentNo);
        request.setAttribute("studentName",studentName);
        request.setAttribute("stuClass",stuClass);
        return getViewPath("/teacher/main/haveappoint");
    }
    @RequestMapping("/main/mindex")
    public ModelAndView mainx(HttpServletRequest request){
        Teacher su=SingletonLoginUtils.getLoginTeacher(request);
        ModelAndView model = new ModelAndView();
            List<ExamManager> list=examManagerService.queryAllExamManager();
            model.addObject("list",list);
            model.addObject("teacher",su);
        model.setViewName(getViewPath("/teacher/main/main"));
        return model;
    }



    @RequestMapping("/main/index")
    public ModelAndView mainIndex(HttpServletRequest request){
        ModelAndView model = new ModelAndView();
        try{
            // 今天登录人数数据获得
            int todayloginnum = statisticsDayService.getTodayLoginNum(new Date());
            model.addObject("todayloginnum", todayloginnum);
            model.setViewName(mainIndexPage);

            Map<String, Object> webCountMap=(Map<String, Object>) EHCacheUtil.get(CacheConstans.WEB_COUNT);

            if(webCountMap==null){
                webCountMap=new HashMap<String, Object>();
                //所有文章数量
                int articleCount = articleService.queryAllArticleCount();
                webCountMap.put("articleCount", articleCount);//所有文章数量
                //所有用户数量
                int userCount = userService.queryAllUserCount();
                webCountMap.put("userCount", userCount);//所有用户数量
                //所有课程数量
                int courseCount = courseService.queryAllCourseCount();
                webCountMap.put("courseCount", courseCount);//所有课程数量
                //所有问答数
                int questionsCount=questionsService.queryAllQuestionsCount();
                webCountMap.put("questionsCount", questionsCount);//所有问答数
                EHCacheUtil.set(CacheConstans.WEB_COUNT, webCountMap,CacheConstans.WEB_COUNT_TIME);
                model.addObject("webCountMap",webCountMap);
            }else{
                model.addObject("webCountMap",webCountMap);
            }
        }catch (Exception e) {
            model.setViewName(this.setExceptionRequest(request, e));
            logger.error("mainIndex()---error",e);
        }
        return model;
    }

}

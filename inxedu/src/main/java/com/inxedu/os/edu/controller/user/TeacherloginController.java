package com.inxedu.os.edu.controller.user;

import com.inxedu.os.common.cache.EHCacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.entity.teacher.Teacher;
import com.inxedu.os.edu.service.teacher.TeacherService;
import com.inxedu.os.nstar.appointment.entity.examManager.ExamManager;
import com.inxedu.os.nstar.appointment.service.ExamManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by liangziqiang on 2016/11/15.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherloginController extends BaseController{
  private static final Logger logger= LoggerFactory.getLogger(TeacherloginController.class);
    private static String loginsucess="redirect:/teacher/main/mindex";
    @Autowired
    private TeacherService teacherService;
    @Autowired
    @RequestMapping("")
    public String loginPage(HttpServletRequest request){
         return getViewPath("/teacher/login/teacherLogin");
     }
    @RequestMapping("/outlogin")
    public String outLogin(HttpServletRequest request,HttpServletResponse response){
        try{
            request.getSession().invalidate();
            int userId = SingletonLoginUtils.getLoginTeaUserId(request);
            //删除所有的权限缓存
            //删除登录用户的缓存信息
            EHCacheUtil.remove(CacheConstans.LOGIN_MEMCACHE_TEACHER+userId);
            //清空所有的Session
            request.getSession().invalidate();
        }catch (Exception e) {
            logger.error("outLogin()---error",e);
            return this.setExceptionRequest(request, e);
        }
        return "redirect:/teacher";
    }
     @RequestMapping("/main/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, Teacher teacher){
        ModelAndView model = new ModelAndView();
        model.setViewName(getViewPath("/teacher/login/teacherLogin"));
        try{
            model.addObject("teacher", teacher);
            if(teacher.getUsername()==null || teacher.getUsername().trim().equals("")){
                model.addObject("message", "请输入用户名!");
                return model;
            }
            if(teacher.getPassword()==null ||teacher.getPassword().trim().equals("")){
                model.addObject("message", "请输入密码!");
                return model;
            }
            //获取Session中验证码
            String randCode = (String) request.getSession().getAttribute(CommonConstants.RAND_CODE);
            //用户输入的验证码
            String randomCode = request.getParameter("randomCode");
            if(randomCode==null || !randomCode.equals(randCode)){
                model.addObject("message", "验证码不正确！");
                return model;
            }
            request.getSession().removeAttribute(CommonConstants.RAND_CODE);
            teacher.setPassword(MD5.getMD5(teacher.getPassword()));
            Teacher su = teacherService.getLoginTeacher(teacher);
            if(su==null){
                model.addObject("message", "用户名或密码错误！");
                return model;
            }
            //判断用户是否是可用状态
            /*if(su.getStatus()!=0){
                model.addObject("message", "该用户已经冻结！");
                return model;
            }*/
            //缓存用登录信息
            EHCacheUtil.set(CacheConstans.LOGIN_MEMCACHE_TEACHER+su.getId(), su);
            request.getSession().setAttribute(CacheConstans.LOGIN_MEMCACHE_TEACHER+su.getId(),su);
            WebUtils.setCookie(response, CacheConstans.LOGIN_MEMCACHE_TEACHER, CacheConstans.LOGIN_MEMCACHE_TEACHER+su.getId(), 1);
            model.setViewName(loginsucess);
        }catch (Exception e) {
            model.addObject("message", "系统繁忙，请稍后再操作！");
            logger.error("login()--error",e);
        }
        return model;
}


}

package com.inxedu.os.nstar.selectCourse.controller;

        import com.inxedu.os.common.controller.BaseController;
        import com.inxedu.os.common.entity.PageEntity;
        import com.inxedu.os.nstar.selectCourse.entity.college.College;
        import com.inxedu.os.nstar.selectCourse.service.CollegeService;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseBody;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.List;
        import java.util.Map;


/**
 * Created by ASUS on 2016/7/28.
 */
@Controller
@RequestMapping("/admin/college")
public class CollegeController extends BaseController{
    @Autowired
    private CollegeService collegeService;
    private static Logger log = LoggerFactory.getLogger(CollegeController.class);

    @RequestMapping("/list")
    //显示学院列表
    public String list(HttpServletRequest request,PageEntity page){
        log.info("=>CollegeController:list");
        page.setPageSize(20);
        List<College> list = collegeService.getCollegeList(page);
        request.setAttribute("list",list);
        request.setAttribute("page",page);
        return "/nstar/college/college_list";
    }

    @RequestMapping("/addPage")
    //跳转到添加页面
    public String addPage(HttpServletRequest request){
        return "/nstar/college/addPage";
    }

    @RequestMapping("/updatePage")
    //跳转到修改页面
    public String updatePage(HttpServletRequest request,int id,int currentPage){
        College college = collegeService.getCollegeById(id);
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("college",college);
        return "/nstar/college/updatePage";
    }

    @RequestMapping("/add")
    //添加学院
    public String add(College college, HttpServletResponse response) throws IOException {
        log.info(" => CollegeController:add{}",college.toString());
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //判断所有字段不能为空
        if(college.getCollegeCode()==null||college.getCollegeCode().equals("")||college.getCollegeName()==null||college.getCollegeName().equals("")||college.getDescription()==null||college.getDescription().equals("")){
            response.getWriter().print("字段为空，不能保存");
            return null;
        }

        //根据页面参数获取某一项相同的实体
        College col = collegeService.getByAllField(college);
        //如果不存在说明没有相同的字段，可以保存
        if(col==null){
            collegeService.addCollege(college);
            return "redirect:/admin/college/list";
        }else {//负责存在相同字段，提示不能保存
            response.getWriter().print("字段重复");
            return null;
        }
    }

    @RequestMapping("/update")
    //修改学院信息
    public String update(College college, HttpServletResponse response,int currentPage) throws IOException {
        log.info(" => CollegeController:update{}",college.toString());
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //判断所有字段不能为空
        if(college.getCollegeCode()==null||college.getCollegeCode().equals("")||college.getCollegeName()==null||college.getCollegeName().equals("")||college.getDescription()==null||college.getDescription().equals("")){
            response.getWriter().print("字段为空，不能保存");
            return null;
        }

        collegeService.updateCollege(college);
        return "redirect:/admin/college/list?currentPage="+currentPage;
    }

    @RequestMapping("/delete/{collegeId}")
    //修改学院信息
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("collegeId") int id){
        log.info(" => CollegeController:delete{}",id);
        collegeService.deleteCollege(id);
        return this.setJson(true,"删除成功",null);
    }

    @RequestMapping("/deleteMore")
    //根据id批量删除班级
    public String deleteMore(int[] ids){
        log.info(" => CollegeController:deleteMore{}",ids);
        if(ids==null){
            return "redirect:/admin/college/list";
        }
        for (int id : ids){
            collegeService.deleteCollege(id);
        }
        return "redirect:/admin/college/list";
    }
}
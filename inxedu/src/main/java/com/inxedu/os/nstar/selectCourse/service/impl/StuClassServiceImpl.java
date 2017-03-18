package com.inxedu.os.nstar.selectCourse.service.impl;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.selectCourse.entity.stuClass.StuClass;
import com.inxedu.os.nstar.selectCourse.service.CourseTableDoService;
import com.inxedu.os.nstar.selectCourse.service.StuClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by 1 on 2016/7/27.
 */
@Service
public class StuClassServiceImpl extends GenericDaoImpl implements StuClassService {
    private static final Logger logger = LoggerFactory.getLogger(StuClassServiceImpl.class);
    @Autowired
    private CourseTableDoService eduCourseTableDoService;
    @Override
    //添加班级
    public void addClass(StuClass stuClass) {
        this.insert("StuClassMapper.addClass", stuClass);
    }

    @Override
    //根据id删除班级信息
    public void deleteClass(int id) {
        this.delete("StuClassMapper.deletebyId", id);
    }

    @Override
    //修改班级信息
    public void updateClass(StuClass stuClass) {
        this.update("StuClassMapper.updateClass", stuClass);
    }

    @Override
    //获取所有班级列表
    public List<StuClass> getStuClassList(PageEntity page) {
        List<StuClass> list = this.queryForListPage("StuClassMapper.getStuClassList",null, page);
        return list;
    }

    @Override
    //根据班级id获取班级信息
    public StuClass getStuClassById(int id) {
        StuClass stuClass = this.selectOne("StuClassMapper.getStuClassById", id);
        return stuClass;
    }

    @Override
    //根据学院id获取学院班级列表
    public List<StuClass> getStuClassListBySchool(int schoolId) {
        List<StuClass> stuClass = this.selectList("StuClassMapper.getStuClassListBySchool", schoolId);
        return stuClass;
    }

    @Override
    //批量添加班级信息
    public void addClassFromList(List<StuClass> list) {
        for (StuClass stuClass : list) {
            this.addClass(stuClass);
        }
    }

    @Override
    //根据班级名和学院id模糊查询
    public List<StuClass> search(String className, Integer schoolId, PageEntity page) {
        if("".equals(className)&&"".equals(schoolId))
        {
            return  this.getStuClassList(page);
        }
        StuClass stuClass = new StuClass();
        stuClass.setClassName(className);
        stuClass.setSchoolId(schoolId);
        List<StuClass> list = this.queryForListPage("StuClassMapper.search", stuClass,page);
        return list;
    }

    @Override
    public List<StuClass> searchName(String className) {
        StuClass stuClass = new StuClass();
        stuClass.setClassName(className);
        List<StuClass> list = new ArrayList<StuClass>();
        List<StuClass> list2 = this.selectList("StuClassMapper.searchName", className);
        System.out.print("sdfsg");
    /*        for(Map<Object,Object> it:list2 ){
            StuClass stuClass = new StuClass();
            stuClass.(it.get("COURSE_NO").toString());

            int id = Integer.parseInt(it.get("ID").toString());
            stuClass.setId(id);
            stuClass.(it.get("MEMO").toString());
            stuClass.(it.get("COURSE_NAME").toString());
            list.add(stuClass);
        }*/
        return list2;
    }

    static Pattern pattern = Pattern.compile(",");

    @Override
    public void addSelectableClass(StuClass stuClass, String id) {
        if("".equals(id)){
            if(eduCourseTableDoService.queryCourseByCourseId(id)==0) {
                return;
            }}
        String ids = stuClass.getSeletableCourseId();
        if (ids == null) {
            logger.info("ids:{} ,stuClass:{} ,id:{}", ids, stuClass, id);
            ids = "";
        }
        String result;
        switch (ids) {
            case "": {
                result = id;
                break;
            }
            default: {
                if(ids.equals(id)||ids.contains(","+id+",")||ids.endsWith(","+id)){
                    return ;
                }
                result=ids+","+id;
                /*
                String[] str = pattern.split(ids);
                List<String> list = Arrays.asList(str);
                list.add(",");
                list.add(id);

                StringBuilder s = new StringBuilder(16);
                for (String it : list) {
                    s.append(it);
                }
                */
                break;
            }
        }


/*
        if(ids.equals("")){
            ids=ids+id+",";
        }
        else if(!(ids.contains(id))) {
           ids=ids+id+",";
       }
       */
        stuClass.setSeletableCourseId(result);
        this.update("updateClas", stuClass);
    }

    @Override
    public void deleteSelectableClass(StuClass stuClass, String courseId) {
        String ids = stuClass.getSeletableCourseId();

        String[] s = ids.split(",");
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i< s.length; i++) {
            if(s[i].equals(courseId)) {
                s[i] = "";
            }
        }
        for (String s1:s) {
            if(!s1.equals(""))
                sb.append(s1+",");
        }
        if(sb.length()>0)
            sb.delete(sb.length()-1,sb.length()-1);
        String idss = sb.toString();


//        if (ids.startsWith(courseId)){
//
//         ids= ids.replace(courseId+",","");
//        }
//        else if(ids.endsWith(courseId)){
//            ids= ids.replace(","+courseId,"");
//        }else if (ids.equals(courseId)){
//            ids= ids.replace(courseId,"");
//        }
//        else {
//            ids= ids.replace(courseId+",","");
//        }
        stuClass.setSeletableCourseId(idss);
        this.updateClass(stuClass);
    }




    @Override
    public List<StuClass> readFileToList(InputStream in) {
        return null;
    }

    @Override
    public List<StuClass> queryCollegeByCollegeId(int collegeId) {
        List<StuClass>  list=this.selectList("StuClassMapper.queryCollegeByCollegeId",collegeId);
        return list;
    }

    @Override
    public List<StuClass> queryAllStuclass() {
        List<StuClass> list=this.selectList("StuClassMapper.queryAllStuclass",null);
        return list;
    }

    @Override
    public List<Integer> queryClassIdByName(String classname) {
        List<Integer> s=this.selectList("StuClassMapper.queryClassIdByClassName", classname);
        return s;
    }

}

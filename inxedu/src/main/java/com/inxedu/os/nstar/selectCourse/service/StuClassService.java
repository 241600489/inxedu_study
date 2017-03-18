package com.inxedu.os.nstar.selectCourse.service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.selectCourse.entity.stuClass.StuClass;

import java.io.InputStream;
import java.util.List;

/**
 * Created by 1 on 2016/7/27.
 */
public interface StuClassService {

    //添加班级
    public void addClass(StuClass stuClass);

    //删除班级
    public  void deleteClass(int id);

    //修改班级信息
    public void updateClass(StuClass stuClass);

    //获取所有班级列表
    public List<StuClass> getStuClassList(PageEntity page);

    //根据id获取班级信息
    public StuClass getStuClassById(int id);

    //根据所属学院id获取班级列表
    public  List<StuClass> getStuClassListBySchool(int schoolId);

    //批量添加班级信息
    public void addClassFromList(List<StuClass> list);

    //根据班级名和学院id模糊查询
    public List<StuClass> search(String className, Integer schoolId, PageEntity page);
    /**
     * 更据班级名称查询
     */
    public List<StuClass> searchName(String className);

    /**
     * 添加一个可选课
     */
    public void addSelectableClass(StuClass stuClass, String id);

    /**
     * 删除可选择的课程
     */
    public void deleteSelectableClass(StuClass stuClass, String courseId);



    //从文件中读取所有班级信息
    public List<StuClass> readFileToList(InputStream in);

    public List<StuClass> queryCollegeByCollegeId(int collegeId);

    List<StuClass> queryAllStuclass();

    List<Integer> queryClassIdByName(String classname);
}

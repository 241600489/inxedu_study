package com.inxedu.os.nstar.selectCourse.service.impl;


import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.nstar.selectCourse.entity.course.CourseBase;
import com.inxedu.os.nstar.selectCourse.service.CourseBaseService;
import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by seapig02 on 7/29 0029.
 */
@Service
public class CourseBaseServiceImpl extends GenericDaoImpl implements CourseBaseService {
    @Override
    public void addBaseCourse(CourseBase eduCourseBase) {
        this.insert("CourseBaseMapper.addBaseCourse",eduCourseBase);
    }

    @Override
    public void deleteBaseCourse(int id) {
        this.delete("CourseBaseMapper.deleteByPrimaryKey",id);
    }

    @Override
    public void updateBaseCourse(CourseBase eduCourseBase) {
        this.update("CourseBaseMapper.updateBaseCourse",eduCourseBase);
    }



    @Override
    public List<CourseBase> queryAllCourseBase(PageEntity page) {
        List<CourseBase> list= new ArrayList<>();
        List<Map<Object,Object>> list2=this.queryForListPage("CourseBaseMapper.queryAllCourseBase",null,page);

        for(Map<Object,Object> it:list2 ){
            CourseBase eduCourseBase = new CourseBase();
            eduCourseBase.setCourseNo(it.get("COURSE_NO").toString());

            int id = Integer.parseInt(it.get("ID").toString());
            eduCourseBase.setId(id);
            eduCourseBase.setMemo(it.get("MEMO").toString());
            eduCourseBase.setCourseName(it.get("COURSE_NAME").toString());
            list.add(eduCourseBase);
        }
        return list;
    }

    public List<CourseBase> query(String name){
        List<CourseBase> list= new ArrayList<>();
        Map<Object,Object> s =this.selectOne("CourseBaseMapper.query",name);
        if(s==null){
            return  null;
        }
        CourseBase eduCourseBase=new CourseBase();
        eduCourseBase.setCourseNo(s.get("COURSE_NO").toString());

        int id = Integer.parseInt(s.get("ID").toString());
        eduCourseBase.setId(id);
        eduCourseBase.setMemo(s.get("MEMO").toString());
        eduCourseBase.setCourseName(s.get("COURSE_NAME").toString());
        list.add(eduCourseBase);
        return list;
    }

    @Override
    public CourseBase queryCourseNo(String courseName) {
        CourseBase courseBase=new CourseBase();
        courseBase.setCourseName(courseName);
        return this.selectOne("CourseBaseMapper.queryCourseNoByCourseName",courseBase);
    }

    @Override
    public CourseBase queryCourseBaseByNo(String courseNo) {
        CourseBase courseBase=new CourseBase();
        courseBase.setCourseNo(courseNo);
      return this.selectOne("CourseBaseMapper.queryCourseBaseByNo",courseBase);
    }

    @Override
    public List<CourseBase> query(String name, String no,PageEntity page) {
        if ("".equals(name) && "".equals(no)) {
            return this.queryAllCourseBase(page);
        } else {
            CourseBase courseBase = new CourseBase();
            courseBase.setCourseName(name);
            if(StringUtils.isNotEmpty(no)){
                courseBase.setCourseNo(no);
            }
            List<CourseBase> list1 = new ArrayList<>(20);
            List<Map<Object, Object>> list = null;
            try {
                list = this.queryForListPage("CourseBaseMapper.query", courseBase, page);
            } catch (Exception e) {

            }
            if(list==null) return ListUtils.EMPTY_LIST;
            for (Map<Object, Object> it : list) {
                CourseBase eduCourseBase = new CourseBase();
                eduCourseBase.setCourseNo(it.get("COURSE_NO").toString());

                int id = Integer.parseInt(it.get("ID").toString());
                eduCourseBase.setId(id);
                eduCourseBase.setMemo(it.get("MEMO").toString());
                eduCourseBase.setCourseName(it.get("COURSE_NAME").toString());
                list1.add(eduCourseBase);
            }
            return list1;

        }
    }

    @Override
    public CourseBase queryByPrimaryKey(int id) {
        Map<Object,Object> map=this.selectOne("CourseBaseMapper.queryByPrimaryKey",id);
        CourseBase eduCourseBase=new CourseBase();
        eduCourseBase.setCourseNo(map.get("COURSE_NO").toString());


        eduCourseBase.setId(id);
        eduCourseBase.setMemo(map.get("MEMO").toString());
        eduCourseBase.setCourseName(map.get("COURSE_NAME").toString());
        return eduCourseBase;
    }
    @Override
    public List<String> queryCourseNO() {
        return this.selectList("CourseBaseMapper.queryCourseNo",null);
    }

}


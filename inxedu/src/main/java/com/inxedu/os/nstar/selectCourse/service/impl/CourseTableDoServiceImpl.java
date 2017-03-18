package com.inxedu.os.nstar.selectCourse.service.impl;


import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.nstar.selectCourse.entity.course.CourseBase;
import com.inxedu.os.nstar.selectCourse.entity.selectCourse.CourseTableDo;
import com.inxedu.os.nstar.selectCourse.service.CourseTableDoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by seapig02 on 7/28 0028.
 */
@Service
public class CourseTableDoServiceImpl extends GenericDaoImpl implements CourseTableDoService {

    @Override
    public Integer queryClassIdByName(String className) {
        return null;
    }

    @Override
    public int queryCourseIdByName(String courseName) {
        if ("".equals(courseName)) {
            return 0;
        }
        return this.selectOne("CourseTableDoMapper.CourseIdByName", courseName);
    }

    @Override
    public List<String> queryCourseName() {
        List<String> list = this.selectList("CourseTableDoMapper.queryCourseName", null);
        return list;
    }

    @Override
    public List<CourseBase> queryBaseCourseName() throws Exception {
        List<CourseBase> list = new ArrayList<>();
        List<Map<Object, Object>> list2 = this.selectList("CourseBaseMapper.queryBaseCourseName", null);
        for (Map<Object, Object> it : list2) {
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

    @Override
    public String queryCourseNameById(int i) {

        return this.selectOne("CourseBaseMapper.queryBaseCourseNameById", i);
    }

    @Override
    public int queryCourseByCourseId(String id) {
        if ("".equals(id))
            return 0;
        return this.selectOne("CourseBaseMapper.queryIdBycourseId", id);

    }

    @Override
    public List<CourseTableDo> queryAllCoueBase() {

        List<CourseTableDo> list=this.selectList("CourseTableDoMapper.queryAllCoueBase",null);
        return list;
    }

}

























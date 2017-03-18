package com.inxedu.os.nstar.selectCourse.service.impl;


import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.nstar.selectCourse.entity.SelectCourseState;
import com.inxedu.os.nstar.selectCourse.entity.selectCourse.CourseController;
import com.inxedu.os.nstar.selectCourse.entity.selectCourse.CourseStudent;
import com.inxedu.os.nstar.selectCourse.entity.selectCourse.CourseTableDo;
import com.inxedu.os.nstar.selectCourse.service.SelectLessionService;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

@Service("SelectLessionService")
@Qualifier("SelectLessionService")
public class SelectLessionServiceBackImpl extends GenericDaoImpl implements SelectLessionService {
    /*    final boolean[][] table = new boolean[5][6];
        SelectLessionServiceBackImpl(){
            for (int i = 0; i < table.length; i++) {
                for (int j=0;j<table[i].length;j++){
                    table[i][j]=true;
                }
            }
        }*/
    private Pattern pattern = Pattern.compile(",");

    public boolean[][] showValidCourseLocation() {
        boolean[][] table = new boolean[5][6];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = false;
            }
        }
        List<Course> list = sqlSession.selectList("CourseMapper.queryAllCourse", null);
        for (Course course : list) {
            try {
                if (course.getWeek1() != null && course.getWeek1() < table.length + 1 && course.getWeek1() > 0) {
                    table[course.getWeek1() - 1][0] = true;
                }
                if (course.getWeek2() != null && course.getWeek2() < table.length + 1 && course.getWeek2() > 0) {
                    table[course.getWeek2() - 1][1] = true;
                }


                if (course.getWeek3() != null && course.getWeek3() < table.length + 1 && course.getWeek3() > 0) {
                    table[course.getWeek3() - 1][2] = true;
                }


                if (course.getWeek4() != null && course.getWeek4() < table.length + 1 && course.getWeek4() > 0) {
                    table[course.getWeek4() - 1][3] = true;
                }


                if (course.getWeek5() != null && course.getWeek5() < table.length + 1 && course.getWeek5() > 0) {
                    table[course.getWeek5() - 1][4] = true;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return table;
    }

    // 获取可选课位
    @Transactional(readOnly = true)
    public boolean[][] showValidCourseLocation(final Integer classId) {
        final List<CourseTableDo> courseTableDos = sqlSession.selectList("CourseTableDoMapper.selectValidCourseLocation", classId);
        final boolean[][] table = new boolean[5][6];
        for (final CourseTableDo eduCourseTableDo : courseTableDos) {
            table[eduCourseTableDo.getSessionId() - 1][eduCourseTableDo.getWeekId() - 1] = true;
        }
        return table;
    }

    @Override
    public List<Course> getValidCourse(String weekId, String sessionId) {
        Map<String, String> map = new HashMap<>();
        map.put("weekId", weekId);
        map.put("sessionId", sessionId);
        List<Course> result = this.selectList("CourseMapper.getCourseList", map);
        return result == null ? ListUtils.EMPTY_LIST : result;
    }


    // 获取可选课程
    /*
    1,1 2,1 ...
	1,2 2,2
	1,3 2,3
	1,4 2,4
	1,5 2,5


	CourseTableDoMapper 里面的CourseIds的id要求从小到大排序后再插入

	二分查找
	 */
    @Transactional(readOnly = true)
    public List<Course> showValidCourseInfo(final String weekId, final String sessionId, final Integer classId) {
        String[] ids = getValidCourseIds(weekId, sessionId, classId);
        int size = ids.length;
        if (size == 0) {
            return ListUtils.EMPTY_LIST;
        }
        final List<Course> result = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            final Course it = sqlSession.selectOne("CourseMapper.queryCourseById", Integer.valueOf(ids[i]));

            if (Objects.nonNull(it)) {
                if (it.getIsavaliable() == 1)
                    result.add(it);
            }
        }
        return result;
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public SelectCourseState selectCourseCore(final Integer studentId, final Integer courseId) {
        final int count = sqlSession.selectOne("CourseMapper.queryResidualCountById", courseId);
        if (count > 0) {
            //检查是否已选过这本课
            if (!isSelectCourse(studentId)) {
                sqlSession.update("CourseMapper.decResidualCount", courseId);
                sqlSession.insert("CourseStudentMapper.insert", new CourseStudent(courseId, new Date(), studentId));

                return SelectCourseState.SUCCESS;
            } else {
                return SelectCourseState.REPLY;
            }
        } else {
            return SelectCourseState.COURSE_COUNT_ZERO;
        }

    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public SelectCourseState backCourseCore(final Integer studentId, final Integer courseId) {
        final int count = sqlSession.selectOne("CourseMapper.queryResidualCountById", courseId);
        if (count >= 0) {
            //检查是否已选过这本课
            //如果选过就退课
            Integer id = sqlSession.selectOne("CourseStudentMapper.selectCourseIdByStudentId", studentId);
            if (ObjectUtils.isNull(id)) {
                return SelectCourseState.COURSE_COUNT_ZERO;
            }
            if (!courseId.equals(id)) {
                return SelectCourseState.ILLEGAL;
            }
            sqlSession.update("CourseMapper.incResidualCount", courseId);
            Map<String, Integer> map = new HashMap<>(2);
            map.put("courseId", courseId);
            map.put("studentId", studentId);
            sqlSession.delete("CourseStudentMapper.deleteByStudentIdAndCourseId", map);
            return SelectCourseState.SUCCESS;
        } else {
            return SelectCourseState.FAIL;
        }
    }

    @Transactional(readOnly = true)
    public String[] getValidCourseIds(final String weekId, final String sessionId, final Integer classId) {
        final Map<String, String> map = new HashMap<>(3);
        map.put("weekId", weekId);
        map.put("sessionId", sessionId);
        map.put("classId", classId.toString());
        final List<String> str = sqlSession.selectList("CourseTableDoMapper.selectCourseIds", map);
        if (str == null) {
            return new String[0];
        } else {
            return str.toArray(new String[str.size()]);
        }


    }

    @Transactional(readOnly = true)
    public boolean checkTime() {
        CourseController courseController = sqlSession.selectOne("CourseControllerMappper.selectController");
        Date now = new Date();
        Date b0 = courseController.getBeginCourseSignupTime0();
        Date e0 = courseController.getEndCourseSignupTime0();
        Date b1 = courseController.getBeginCourseSignupTime1();
        Date e1 = courseController.getEndCourseSignupTime1();
        return b0.before(now) && e0.after(now) || b1.before(now) && e1.after(now);
    }
    /*
    如果出错后抛异常
	 */

    public boolean isSelectCourse(final Integer userId) {
        Integer isExist = sqlSession.selectOne("CourseStudentMapper.selectCourseIdByStudentId", userId);
        if (isExist != null) {
            return true;
        } else {
            return false;
        }
    }

    /*
    要求ids已排序
     */
    public boolean isValidCourseId(final Integer courseId, final String weekId, final String sessionId, final Integer classId) {
        final String[] ids = getValidCourseIds(weekId, sessionId, classId);
        final String courseIdStr = courseId.toString();
        return containAfterSort(ids, courseIdStr);
    }

    private <T> boolean containAfterSort(T[] array, T i) {
        return Arrays.binarySearch(array, i) != -1;
    }

    /*
    public boolean isValidCourseId(final Integer courseId, final String weekId, final String sessionId, final Integer classId) {
        final String[] ids = getValidCourseIds(weekId, sessionId, classId);
        final String courseIdStr = courseId.toString();
        boolean isExist = false;
        for (String id : ids) {
            if (courseIdStr.equals(id)) {
                isExist = true;
            }
        }
        return isExist;
    }
    */
    @Transactional(readOnly = true)
    public boolean isValidCourseId(final Integer courseId, final Integer classId) {
        List<String> list = sqlSession.selectList("CourseTableDoMapper.selectCourseIdsByClassId", classId);
        String courseStr = courseId.toString();
        for (String it : list) {
            String[] ids = pattern.split(it);
            //要求CourseId已排序
            if (containAfterSort(ids, courseStr)) {
                return true;
            }
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<Course> showMyCourse(final int userId) {
        final List<Integer> ids = sqlSession.selectList("CourseStudentMapper.selectCourseIdListByUserId", userId);
        if (ObjectUtils.isNotNull(ids)) {
            List<Course> result = new ArrayList<>(ids.size());
            for (Integer i : ids) {
                Course course = sqlSession.selectOne("CourseMapper.queryCourseById", i);
                result.add(course);
            }
            return result;
        }
        return ListUtils.EMPTY_LIST;
    }

}

package com.inxedu.os.edu.service.impl.teacher;

import java.util.List;
import java.util.Map;

import com.inxedu.os.common.dao.GenericDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.teacher.TeacherDao;
import com.inxedu.os.edu.entity.teacher.QueryTeacher;
import com.inxedu.os.edu.entity.teacher.Teacher;
import com.inxedu.os.edu.service.teacher.TeacherService;

/**
 * Teacher管理接口
 * @author www.inxedu.com
 */
@Service("teacherService")
public class TeacherServiceImpl extends GenericDaoImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;

	public int addTeacher(Teacher teacher) {
		return teacherDao.addTeacher(teacher);
	}

	public void deleteTeacherById(int tcId) {
		teacherDao.deleteTeacherById(tcId);
	}

	public void updateTeacher(Teacher teacher) {
		teacherDao.updateTeacher(teacher);
	}

	public Teacher getTeacherById(int tcId) {
		return teacherDao.getTeacherById(tcId);
	}

	public List<Teacher> queryTeacherListPage(QueryTeacher query, PageEntity page) {
		return teacherDao.queryTeacherListPage(query, page);
	}

	public List<Map<String, Object>> queryCourseTeacerList(int courseId) {
		return teacherDao.queryCourseTeacerList(courseId);
	}

	public List<Teacher> queryTeacherList(QueryTeacher queryTeacher) {
		return teacherDao.queryTeacherList(queryTeacher);
	}

	@Override
	public Integer queryTeacherIdByName(String name) {
		return this.selectOne("TeacherMapper.queryTeacerIdByTeacherName",name);
	}

	@Override
	public Teacher getLoginTeacher(Teacher teacher) {
		return teacherDao.queryTeacher(teacher);
	}
}
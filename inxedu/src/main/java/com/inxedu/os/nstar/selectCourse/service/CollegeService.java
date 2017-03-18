package com.inxedu.os.nstar.selectCourse.service;


import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.selectCourse.entity.college.College;

import java.util.List;

public interface CollegeService {
	
	//添加学院
	public void addCollege(College college);
	
	//删除学院
	public void deleteCollege(int id);
	
	//修改学院
	public void updateCollege(College college);
	
	//获取所有学院列表
	 public List<College> getCollegeList(PageEntity page);

	 //根据id获取学院信息
	 public College getCollegeById(int id);

	public College getByAllField(College college);

	 public List<College> queryIds();

	String querycollegeName(Integer id);

	 List<College> queryCollege();
}

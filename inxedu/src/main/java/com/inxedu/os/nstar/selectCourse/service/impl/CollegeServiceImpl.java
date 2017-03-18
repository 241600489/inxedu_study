package com.inxedu.os.nstar.selectCourse.service.impl;


import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.selectCourse.entity.college.College;
import com.inxedu.os.nstar.selectCourse.service.CollegeService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service("CollegeService")
public class CollegeServiceImpl extends GenericDaoImpl implements CollegeService {

	@Override
	//添加学院
	public void addCollege(College college){
		this.insert("CollegeMapper.addCollege",college);
	}

	@Override
	//根据id删除班级信息
	public void deleteCollege(int id) {
		this.delete("CollegeMapper.deletebyId",id);
	}

	@Override
	//修改学院
	public void updateCollege(College college) {
		this.update("CollegeMapper.updateCollege",college);
	}

	@Override
	//获得所有学院信息
	public List<College> getCollegeList(PageEntity page){
//		List<College> list = this.selectList("CollegeMapper.getCollegeList", null);
		List<College> list = this.queryForListPage("CollegeMapper.getCollegeList",null,page);
		return list;
	}


	@Override
	//根据学院的id获取学院信息
	public College getCollegeById(int id) {
		College college = this.selectOne("CollegeMapper.getCollegeById", id);
		return college;
	}

	@Override
	public College getByAllField(College college) {
		College col = this.selectOne("CollegeMapper.getByAllFiled",college);
		return col;
	}

	@Override
	public List<College> queryIds() {
		List<College> list=this.selectList("CollegeMapper.queryIds",null);
		return list;
	}
	@Override
	public String querycollegeName(Integer id) {
		return this.selectOne("CollegeMapper.queryNames",id);
	}

	@Override
	public List<College> queryCollege() {
		List<College> list=this.selectList("CollegeMapper.queryCollege",null);
		return list;
	}
}


package com.inxedu.os.nstar.selectCourse.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by y余松涛 on 16-8-2.
 */
public interface ExcelFileService {


	/**
	 * user表
	 * @return
	 */
	//=========导出 user表 部分===开始====================================
	public List<String> getUserHead();
	public List<List<String>> getUserData();
	//======导出 user表 部分===结束====================================

	//======导入 user表 部分===开始====================================
	public String updateExcelToUsers(HttpServletRequest request,
									 MultipartFile myfile, Integer mark) throws Exception;
	//======导入 user表 部分===结束====================================


	/**
	 * StuClass表
	 * @return
	 */

	//=========导出 stuClass表 部分===开始====================================
	public List<String> getstuClassHead();
	public List<List<String>> getstuClassData();
	//======导出 StuClass表 部分===结束====================================

	//======导入 StuClass表 部分===开始====================================
	public String updateExcelToStuClass(HttpServletRequest request,
										MultipartFile myfile, Integer mark) throws Exception;
	//======导入 StuClass表 部分===结束====================================
	/**
	 * 	// 课程导入导出
	 * @return
	 */

	//=========导出 Course表 部分===开始====================================
	public List<String> getCourseHead();
	public List<List<String>> getCourseData();
	//======导出 Course表 部分===结束====================================

	//======导入 Course表 部分===开始====================================
	public String updateExcelToCourse(HttpServletRequest request,
									  MultipartFile myfile, Integer mark) throws Exception;
	//======导入 Course表 部分===结束====================================


	/**
	 * 学院导入导出
	 */

	//=========导出 college表 部分===开始====================================
	public List<String> getCollegeHead();
	public List<List<String>> getCollegeData();
	//======导出 college表 部分===结束====================================

	//======导入 college表 部分===开始====================================
	public String updateExcelToCollege(HttpServletRequest request,
									   MultipartFile myfile, Integer mark) throws Exception;
	//======导入 college表 部分===结束====================================



	/**
	 * 基本课程信息导入导出
	 */
	//=========导出 courseBase表 部分===开始====================================
	public List<String> getCourseBaseHead();
	public List<List<String>> getCourseBaseData();
	//======导出 courseBase表 部分===结束====================================




	// 基本课程信息导入导出

	//选课结果导出
	public List<String> getSelectCourseUserHead();

	public List<List<String>> getSelectCourseUserDataByCourseId(Integer id);

	public String updateExcelTocourseTableDo(HttpServletRequest request,
											 MultipartFile myFile, Integer mark) throws Exception;


}

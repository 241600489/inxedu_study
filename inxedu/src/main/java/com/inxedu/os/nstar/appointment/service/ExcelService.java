package com.inxedu.os.nstar.appointment.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
//	/**
//	 * ExamAppointmentLog表
//	 * @return
//	 */
//	//=========导出 ExamAppointmentLog表 部分===开始====================================
	public ArrayList<String> getExamAppointmentLogHead();
	public ArrayList<ArrayList<String>> getExamAppointmentLogData();
//	//======导出 ExamAppointmentLog表 部分===结束====================================
//
//	//======导入 ExamAppointmentLog表 部分===开始====================================
	public String updateExcelToExamAppointmentLog(HttpServletRequest request,
                                                  MultipartFile myfile) throws Exception;
//	//======导入 ExamAppointmentLog表 部分===结束====================================
//	
//	
//	
//	
//	
	/**
	 * ExamBatch表
	 * @return
	 */
	//=========导出 ExamBatch表 部分===开始====================================
	public ArrayList<String> getExamBatchHead();
	public ArrayList<ArrayList<String>> getExamBatchData();
	//======导出 ExamBatch表 部分===结束====================================

	//======导入 ExamBatch表 部分===开始====================================
	public String updateExcelToExamBatch(HttpServletRequest request,
                                         MultipartFile myfile) throws Exception;
	//======导入 ExamBatch表 部分===结束====================================
//	
//	
//	/**
//	 * ExamClassroom表
//	 * @return
//	 */
//	//=========导出 ExamClassroom表 部分===开始====================================
	public ArrayList<String> getExamClassroomHead();
	public ArrayList<ArrayList<String>> getExamClassroomData();
//	//======导出 ExamClassroom表 部分===结束====================================
//
//	//======导入 ExamClassroom表 部分===开始====================================
	public String updateExcelToExamClassroom(HttpServletRequest request,
                                             MultipartFile myfile) throws Exception;
//	//======导入 ExamClassroom表 部分===结束====================================
//
//
//	/**
//	 * ExamCourse表
//	 * @return
//	 */
//	//=========导出 ExamCourse表 部分===开始====================================
	public ArrayList<String> getExamCourseHead();
	public ArrayList<ArrayList<String>> getExamCourseData();
//	//======导出 ExamCourse表 部分===结束====================================
//
//	//======导入 ExamCourse表 部分===开始====================================
	public String updateExcelToExamCourse(HttpServletRequest request,
                                          MultipartFile myfile) throws Exception;
//	//======导入 ExamCourse表 部分===结束====================================
//
//	
//	
//	/**
//	 * ExamManager表
//	 * @return
//	 */
//	//=========导出 ExamManager表 部分===开始====================================
	public ArrayList<String> getExamManagerHead();
	public ArrayList<ArrayList<String>> getExamManagerData();
//	//======导出 ExamManager表 部分===结束====================================
//
//	//======导入 ExamManager表 部分===开始====================================
	public String updateExcelToExamManager(HttpServletRequest request,
                                           MultipartFile myfile) throws Exception;
//	//======导入 ExamManager表 部分===结束====================================
//
//	/**
//	 * ExamSeat表
//	 * @return
//	 */
//	//=========导出 ExamSeat表 部分===开始====================================
	public ArrayList<String> getExamSeatHead();
	public ArrayList<ArrayList<String>> getExamSeatData();
//	//======导出 ExamSeat表 部分===结束====================================
//
//	//======导入 ExamSeat表 部分===开始====================================
	public String updateExcelToExamSeat(HttpServletRequest request,
                                        MultipartFile myfile) throws Exception;
//	//======导入 ExamSeat表 部分===结束====================================
//	
//	
	/**
	 * ExamStudent表
	 * @return
	 */
	//=========导出 ExamStudent表 部分===开始====================================
	public ArrayList<String> getExamStudentHead();
	public ArrayList<ArrayList<String>> getExamStudentData();
	//======导出 ExamStudent表 部分===结束====================================

	//======导入 ExamStudent表 部分===开始====================================
	public String updateExcelToExamStudent(HttpServletRequest request,
                                           MultipartFile myfile) throws Exception;
	//======导入 ExamStudent表 部分===结束====================================
	
//	
	/**
	 * ExamStudentAppointment表
	 * @return
	 */
	//=========导出 ExamStudentAppointment表 部分===开始====================================
	public ArrayList<String> getExamStudentAppointmentHead();
	public ArrayList<ArrayList<String>> getExamStudentAppointmentData(int id);
	public ArrayList<ArrayList<String>> getExamStudentAppointmentDataByName(String name);
	//======导出 ExamStudentAppointment表 部分===结束====================================

	//======导入 ExamStudentAppointment表 部分===开始====================================
	public String updateExcelToExamStudentAppointment(HttpServletRequest request,
                                                      MultipartFile myfile) throws Exception;
	//======导入 ExamStudentAppointment表 部分===结束====================================
}
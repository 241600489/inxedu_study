package com.inxedu.os.nstar.appointment.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inxedu.os.nstar.appointment.entity.examBatch.ExamBatch;
import com.inxedu.os.nstar.appointment.service.ExamBatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.inxedu.os.nstar.appointment.controller.ExcelFileGenerator;
import com.inxedu.os.nstar.appointment.service.ExcelService;

@Controller
@RequestMapping("/admin/examExcel")
public class ExcelController {
	private static Logger log = LoggerFactory.getLogger(ExcelController.class);
	@Autowired
	ExcelService excelService;
	@Autowired
	ExamBatchService exam;

	// 打开导入导出excel的页面，导入导出到 ExamAppointmentLog表
	@RequestMapping("/examAppointmentLog")
	public String examAppointmentLog(HttpServletRequest request) {
		log.info("=> 数据导入导出页显示");
		return "/nstar/examExcel/examAppointmentLog";
	}

	// 打开导入导出excel的页面，导入导出到ExamBatch表
	@RequestMapping("/examBatch")
	public String examBatch(HttpServletRequest request) {
		log.info("=> 数据导入导出页显示");
		return "/nstar/examExcel/examBatch";
	}

	// 打开导入导出excel的页面，导入导出到 ExamClassroom表
	@RequestMapping("/examClassroom")
	public String examClassroom(HttpServletRequest request) {
		log.info("=> 数据导入导出页显示");
		return "/nstar/examExcel/examClassroom";
	}

	// 打开导入导出excel的页面，导入导出到ExamCourse表
	@RequestMapping("/examCourse")
	public String examCourse(HttpServletRequest request) {
		log.info("=> 数据导入导出页显示");
		return "/nstar/examExcel/examCourse";
	}

	// 打开导入导出excel的页面，导入导出到 ExamManager表
	@RequestMapping("/examManager")
	public String examManager(HttpServletRequest request) {
		log.info("=> 数据导入导出页显示");
		return "/nstar/examExcel/examManager";
	}

	// 打开导入导出excel的页面，导入导出到 ExamSeat表
	@RequestMapping("/examSeat")
	public String examSeat(HttpServletRequest request) {
		log.info("=> 数据导入导出页显示");
		return "/nstar/examExcel/examSeat";
	}
	
	// 打开导入导出excel的页面，导入导出到 ExamStudent表
	@RequestMapping("/examStudent")
	public String examStudent(HttpServletRequest request) {
		log.info("=> 数据导入导出页显示");
		return "/nstar/examExcel/examStudent";
	}
	@RequestMapping("/importExamStudentJsp")
	public String importExamStudentJsp(HttpServletRequest request) {
		log.info("=> 数据导入导出页显示");
		return "/nstar/examExcel/importExamStudentJsp";
	}

	// 打开导入导出excel的页面，导入导出到ExamStudentAppointment表
	@RequestMapping("/examStudentAppointment")
	public String examStudentAppointment(HttpServletRequest request) {
		log.info("=> 数据导入导出页显示");
		return "/nstar/examExcel/examStudentAppointment";
	}

//	// ==========导入===========================================
//
//	/**
//	 * ExamAppointmentLog导入
//	 * 
//	 * @param request
//	 * @param myfile
//	 * @param mark
//	 * @return
//	 */
//	@RequestMapping("/importExamAppointmentLog")
//	public ModelAndView importExamAppointmentLog(HttpServletRequest request,
//			@RequestParam("myFile") MultipartFile myfile,
//			@RequestParam("mark") Integer mark) {
//		ModelAndView modelandView = new ModelAndView();
//		try {
//			String msg = excelService.updateExcelToExamAppointmentLog(
//					request, myfile);
//			request.setAttribute("msg", msg);
//			if (msg == null || msg.equals("")) {
//				modelandView.setViewName("/common/success");
//			} else {
//				modelandView.setViewName("/common/msg_error");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return modelandView;
//	}

	/**
	 * ExamBatch导入
	 * 
	 * @param request
	 * @param myfile
	 * @return
	 */
	@RequestMapping("/importExamBatch")
	public ModelAndView importExamBatch(HttpServletRequest request,
			@RequestParam("myFile") MultipartFile myfile) {
		ModelAndView modelandView = new ModelAndView();
		try {
			String msg = excelService.updateExcelToExamBatch(request,
					myfile);
			request.setAttribute("msg", msg);
			if (msg == null || msg.equals("")) {
				modelandView.setViewName("/common/success");
			} else {
				modelandView.setViewName("/common/msg_error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelandView;
	}
	/**
	 * ExamClassroom导入
	 *
	 * @param request
	 * @param myfile
	 * @param mark
	 * @return
	 */
	@RequestMapping("/importExamClassroom")
	public ModelAndView importExamClassroom(HttpServletRequest request,
			@RequestParam("myFile") MultipartFile myfile,
			@RequestParam("mark") Integer mark) {
		ModelAndView modelandView = new ModelAndView();
		try {
			String msg = excelService.updateExcelToExamClassroom(request,
					myfile);
			request.setAttribute("msg", msg);
			if (msg == null || msg.equals("")) {
				modelandView.setViewName("/common/success");
			} else {
				modelandView.setViewName("/common/msg_error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelandView;
	}

	/**
	 * ExamCourse导入
	 *
	 * @param request
	 * @param myfile
	 * @param mark
	 * @return
	 */
	@RequestMapping("/importExamCourse")
	public ModelAndView importExamCourse(HttpServletRequest request,
			@RequestParam("myFile") MultipartFile myfile,
			@RequestParam("mark") Integer mark) {
		ModelAndView modelandView = new ModelAndView();
		try {
			String msg = excelService.updateExcelToExamCourse(request,
					myfile);
			request.setAttribute("msg", msg);
			if (msg == null || msg.equals("")) {
				modelandView.setViewName("/common/success");
			} else {
				modelandView.setViewName("/common/msg_error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelandView;
	}

	/**
	 * ExamManager导入
	 *
	 * @param request
	 * @param myfile
	 * @param mark
	 * @return
	 */
	@RequestMapping("/importExamManager")
	public ModelAndView importExamManager(HttpServletRequest request,
			@RequestParam("myFile") MultipartFile myfile,
			@RequestParam("mark") Integer mark) {
		ModelAndView modelandView = new ModelAndView();
		try {
			String msg = excelService.updateExcelToExamManager(request,
					myfile);
			request.setAttribute("msg", msg);
			if (msg == null || msg.equals("")) {
				modelandView.setViewName("/common/success");
			} else {
				modelandView.setViewName("/common/msg_error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelandView;
	}

	/**
	 * ExamSeat导入
	 *
	 * @param request
	 * @param myfile
	 * @param mark
	 * @return
	 */
	@RequestMapping("/importExamSeat")
	public ModelAndView importExamSeat(HttpServletRequest request,
			@RequestParam("myFile") MultipartFile myfile,
			@RequestParam("mark") Integer mark) {
		ModelAndView modelandView = new ModelAndView();
		try {
			String msg = excelService.updateExcelToExamSeat(request,
					myfile);
			request.setAttribute("msg", msg);
			if (msg == null || msg.equals("")) {
				modelandView.setViewName("/common/success");
			} else {
				modelandView.setViewName("/common/msg_error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelandView;
	}

	/**
	 * ExamStudent导入
	 * 
	 * @param request
	 * @param myfile
	 * @return
	 */
	@RequestMapping("/importExamStudent")
	public ModelAndView importExamStudent(HttpServletRequest request,
			@RequestParam("myFile") MultipartFile myfile) {
		ModelAndView modelandView = new ModelAndView();
		try {
			String msg = excelService.updateExcelToExamStudent(request,
					myfile);
			request.setAttribute("msg", msg);
			if (msg == null || msg.equals("")) {
				modelandView.setViewName("/common/success");
			} else {
				modelandView.setViewName("/common/msg_error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelandView;
	}

	/**
	 * ExamStudentAppointment导入
	 * 
	 * @param request
	 * @param myfile
	 * @param mark
	 * @return
	 */
	@RequestMapping("/importExamStudentAppointment")
	public ModelAndView importExamStudentAppointment(
			HttpServletRequest request,
			@RequestParam("myFile") MultipartFile myfile,
			@RequestParam("mark") Integer mark) {
		ModelAndView modelandView = new ModelAndView();
		try {
			String msg = excelService
					.updateExcelToExamStudentAppointment(request, myfile);
			request.setAttribute("msg", msg);
			if (msg == null || msg.equals("")) {
				modelandView.setViewName("/common/success");
			} else {
				modelandView.setViewName("/common/msg_error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelandView;
	}

//	// ============导出=============================================
	/**
	 * 导出 ExamAppointmentLog
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/exportExamAppointmentLog")
	public void exportExamAppointmentLog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1：初始化数据
		// 得到user表头
		ArrayList<String> headlist = excelService.getExamAppointmentLogHead();
		// 得到数据
		ArrayList<ArrayList<String>> dataList = excelService
				.getExamAppointmentLogData();
		String type = "考试预约日志";
		exportFiletoExcel(headlist, dataList, type, response);
		return;
	}

	/**
	 * 导出 ExamBatch                                                             //            =导出数据为null=
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/exportExamBatch")
	public void exportExamBatch(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1：初始化数据
		// 得到user表头
		ArrayList<String> headlist = excelService.getExamBatchHead();
		// 得到数据
		ArrayList<ArrayList<String>> dataList = excelService
				.getExamBatchData();
		String type = "考试批次信息";
		exportFiletoExcel(headlist, dataList, type, response);
		return;
	}

	/**
	 * 导出 ExamClassroom
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/exportExamClassroom")
	public void exportExamClassroom(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1：初始化数据
		// 得到user表头
		ArrayList<String> headlist = excelService.getExamClassroomHead();
		// 得到数据
		ArrayList<ArrayList<String>> dataList = excelService
				.getExamClassroomData();
		String type = "考场信息";
		exportFiletoExcel(headlist, dataList, type, response);
		return;
	}

	/**
	 * 导出 ExamCourse
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/exportExamCourse")
	public void exportExamCourse(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1：初始化数据
		// 得到user表头
		ArrayList<String> headlist = excelService.getExamCourseHead();
		// 得到数据
		ArrayList<ArrayList<String>> dataList = excelService
				.getExamCourseData();
		String type = "考试课程信息";
		exportFiletoExcel(headlist, dataList, type, response);
		return;
	}

	/**
	 * 导出 ExamManager
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/exportExamManager")
	public void exportExamManager(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1：初始化数据
		// 得到user表头
		ArrayList<String> headlist = excelService.getExamManagerHead();
		// 得到数据
		ArrayList<ArrayList<String>> dataList = excelService
				.getExamManagerData();
		String type = "考试控制管理";
		exportFiletoExcel(headlist, dataList, type, response);
		return;
	}

	/**
	 * 导出 ExamManager
	 *
	 * @param request
	 * @param response
	 * @throws Exception*/

@RequestMapping("/exportExamSeat")
	public void exportExamSeat(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1：初始化数据
		// 得到user表头
		ArrayList<String> headlist = excelService.getExamSeatHead();
		// 得到数据
		ArrayList<ArrayList<String>> dataList = excelService.getExamSeatData();
		String type = "考场座位信息";
		exportFiletoExcel(headlist, dataList, type, response);
		return;
	}

	/**
	 * 导出 ExamStudent
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/exportExamStudent")
	public void exportExamStudent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1：初始化数据
		// 得到user表头
		ArrayList<String> headlist = excelService.getExamStudentHead();
		// 得到数据
		ArrayList<ArrayList<String>> dataList = excelService
				.getExamStudentData();
		String type = "考生信息";
		exportFiletoExcel(headlist, dataList, type, response);
		return;
	}
	
	/**
	 * 导出 ExamStudentAppointment
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/exportExamStudentAppointment")
	public void exportExamStudentAppointment(HttpServletRequest request,
			HttpServletResponse response,int id) throws Exception {
		// 1：初始化数据
		// 得到user表头
		ExamBatch examBatch=exam.queryExamBatchById(id);
		ArrayList<String> headlist = excelService.getExamStudentAppointmentHead();
		// 得到数据
		ArrayList<ArrayList<String>> dataList = excelService.getExamStudentAppointmentData(id);
		String type = ""+examBatch.getExamClassroom().toString()+"-"+dateToStringWithExcel(examBatch.getExamDate()).substring(0,10)+"-"+examBatch.getExamBeginEndTime().toString();
		exportFiletoExcel(headlist, dataList, type, response);
		return;
	}
@RequestMapping("/exportExamStudentAppointmentByExamName")
	public void exportExamStudentAppointment(HttpServletRequest request,
			HttpServletResponse response,String examName) throws Exception {
		// 1：初始化数据
		// 得到user表头
		//ExamBatch examBatch=exam.queryExamBatchById();
		ArrayList<String> headlist = excelService.getExamStudentAppointmentHead();
		// 得到数
	  examName=request.getParameter("examName");
		ArrayList<ArrayList<String>> dataList = excelService.getExamStudentAppointmentDataByName(examName);
		String type = examName;
		exportFiletoExcel(headlist, dataList, type, response);
		return;
	}// 辅助函数========================================================

	// 调用封装的POI报表的导出类ExcelFileGenerator.java，完成excel报表的导出
	public void exportFiletoExcel(ArrayList<String> headlist,
			ArrayList<ArrayList<String>> dataList, String type,
			HttpServletResponse response) throws Exception {
		ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
				headlist, dataList);
		/** 导出报表的文件名 */
		String filename = type + "报表（" + dateToStringWithExcel(new Date())
				+ "）.xls";
		// 处理乱码
		filename = new String(filename.getBytes("gbk"), "iso-8859-1");
		/** response中进行设置，总结下载，导出，需要io流和头 */
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename="
				+ filename);
		response.setBufferSize(1024);
		// 获取输出流
		OutputStream os = response.getOutputStream();
		excelFileGenerator.expordExcel(os);// 使用输出流，导出
	}

	private static String dateToStringWithExcel(Date date) {
		String sDate = new SimpleDateFormat("yyyy-MM-ddHHmmss").format(date);
		return sDate;
	}

}

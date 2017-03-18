package com.inxedu.os.nstar.selectCourse.service.impl;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.exception.BaseException;
import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.dao.course.CourseDao;
import com.inxedu.os.edu.dao.user.UserDao;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.nstar.selectCourse.entity.college.College;
import com.inxedu.os.nstar.selectCourse.entity.course.CourseBase;
import com.inxedu.os.nstar.selectCourse.entity.selectCourse.CourseTableDo;
import com.inxedu.os.nstar.selectCourse.entity.stuClass.StuClass;
import com.inxedu.os.nstar.selectCourse.service.CollegeService;
import com.inxedu.os.nstar.selectCourse.service.CourseBaseService;
import com.inxedu.os.nstar.selectCourse.service.ExcelFileService;
import com.inxedu.os.nstar.selectCourse.service.StuClassService;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by 余松涛 on 16-8-2.
 */
@Service
public class ExcelFileServiceImpl extends GenericDaoImpl implements ExcelFileService {

    UserDao userDao;
    StuClassService stuClassService;
    CollegeService collegeService;
    CourseBaseService courseBaseService;
    CourseDao courseDao;

    // 学生导入导出
    // 班级导入导出
    // 学院导入导出
    // 基本课程信息导入导出
    // 课程导入导出
    @Autowired
    public ExcelFileServiceImpl(UserDao userDao,
                                StuClassService stuClassService, CollegeService collegeService,
                                CourseBaseService courseBaseService, CourseDao courseDao) {
        this.userDao = userDao;
        this.stuClassService = stuClassService;
        this.collegeService = collegeService;
        this.courseBaseService = courseBaseService;
        this.courseDao = courseDao;
    }
    //选课结果导出

    List<Integer> getStudentIdsByCourseId(Integer id) {
        List<Integer> list = selectList("CourseStudentMapper.selectUserIdListByCourseId", id);
        return list == null ? ListUtils.EMPTY_LIST : list;
    }


    List<User> fromStudentIdToStudent(List<Integer> ids) {
        List<Long> list = new LinkedList<>();
        for (Integer id : ids) {
            list.add(Long.valueOf(id));
        }
        List<User> result = ListUtils.EMPTY_LIST;
        try {
            result = userDao.queryUsersByIds(list);
        } catch (Exception e) {

        }
        return result;
    }

    // 获得user表中的数据
    public List<List<String>> getSelectCourseUserData(List<User> userList) {
        List<List<String>> dataList = new LinkedList<>();
        List<List<String>> list = userJoint(userList);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            List<String> al = list.get(i);
            dataList.add(al);
        }
        return dataList;
    }

    public List<List<String>> getSelectCourseUserDataByCourseId(Integer id) {
        return getSelectCourseUserData(fromStudentIdToStudent(getStudentIdsByCourseId(id)));
    }

    // 获得SelectCourseUser表头
    public ArrayList<String> getSelectCourseUserHead() {
        ArrayList<String> headlist = new ArrayList<String>();
        headlist.add("学号");
        headlist.add("姓名");
        headlist.add("性别");
        headlist.add("班级");
        headlist.add("身份证号");
        return headlist;
    }
    //选课结果导出结束

    /**
     * 学生导出 部分 user
     */
    // =========导出 user表 部分===开始====================================
    // 获得user表头
    public ArrayList<String> getUserHead() {
        ArrayList<String> headlist = new ArrayList<String>();
        headlist.add("学号");
        headlist.add("姓名");
        headlist.add("性别");
        headlist.add("班级");
        headlist.add("身份证号");
        return headlist;
    }
    private List<User> queryAllUsers() {
        return this.sqlSession.selectList("SelectCourseMapper.queryAllUsers", null);
    }

    // 获得user表中的数据
    public List<List<String>> getUserData() {
        List<List<String>> dataList = new ArrayList<>();
        List<User> userList = queryAllUsers();
        List<List<String>> list = userJoint(userList);
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> al = (ArrayList<String>) list.get(i);
            dataList.add(al);
        }
        return dataList;
    }

    // 辅助函数
    private List<List<String>> userJoint(List<User> usertList) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < usertList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(usertList.get(i).getNo() + "");// 学号
            small.add(usertList.get(i).getUserName() + "");// 姓名
            if (usertList.get(i).getSex() == 0) { // 性别
                small.add("--");
            } else if (usertList.get(i).getSex() == 1) {
                small.add("男");
            } else if (usertList.get(i).getSex() == 2) {
                small.add("女");
            }
            StuClass stuClass = selectOne("StuClassMapper.getStuClassById", usertList.get(i).getClassId());
            String stuClassName = stuClass.getClassName();
            small.add(stuClassName);// 班级
            small.add(usertList.get(i).getIdCard() + "");// 身份证
            list.add(small);
        }
        return list;
    }

    // ======导出 user表 部分===结束====================================

    // ======导入 user表 部分===开始====================================

    /**
     * 学生导入部分 user
     */
    public String updateExcelToUsers(HttpServletRequest request,
                                     MultipartFile myFile, Integer mark) throws Exception {
        String msg = "";
        // 返回的结果集
        List<User> usersList = new ArrayList<>();
        try (InputStream inputStream = myFile.getInputStream()) {
            //HSSFWorkbook wookbook = new HSSFWorkbook(inputStream);
           XSSFWorkbook wookbook=new XSSFWorkbook(inputStream);
            Iterator<Sheet> iterator = wookbook.sheetIterator();
            int size = 0;
            while (iterator.hasNext()) {
                size += 1;
                iterator.next();
            }
            for (int j = 0; j < size; ++j) {
                XSSFSheet sheet = wookbook.getSheetAt(j);

                int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
                if (rows == 0 && j == 0) {
                    throw new BaseException("请填写数据");
                } else if (rows == 0) {
                    continue;
                }
                Date date = new Date();
                for (int i = 1; i <= rows + 1; i++) {
                   // 读取左上端单元格
                    XSSFRow row = sheet.getRow(i);
                    // 行不为空
                    if (row != null) {
                        // **读取cell**

                        String NO = getStringCellValue(row.getCell((short) 0));// 学号
                        if (NO == null || NO.equals("")) {
                            msg += "第" + i + "行  学号  不能为空<br/>";
                        } else {
                            // 查重检验
                            if (checkUser(NO)) {
                                // true为重复了。
                                msg += "第" + i + "行  学号  在数据库 中 已存在 <br/>";
                                // false为没有重复。
                            }

                        }
                        String userName = getStringCellValue(row.getCell((short) 1));// 姓名
                        if (StringUtils.isEmpty(userName)) {
                            msg += "第" + i + "行  用户名  不能为空<br/>";
                        }
                        String sex = getStringCellValue(row.getCell((short) 2));// 性别
                        int sexFlag = 0;


                        if ("男".equals(sex.trim())) {
                            sexFlag = 1;
                        } else if ("女".equals(sex.trim())) {
                            sexFlag = 2;
                        } else {
                            sexFlag = 3;
                        }


                        String className = getStringCellValue(row.getCell((short) 3));// 班级
                        Integer stuClassId = null;
                        if (className == null || className.equals("")) {
                            msg += "第" + i + "行  班级  不能为空<br/>";
                        } else {
                            stuClassId = this.selectOne("StuClassMapper.queryClassIdByClassName", className);
                            if(i>173)
                                System.out.println("className:"+className);
                            //  userDao.createUser(user);// 添加学员
                            if (stuClassId == null) {
                                msg += "第" + i + "行  班级  在数据库不存在<br/>";
                            }
                        }
                        String idCard = getStringCellValue(row.getCell((short) 4));// 身份证号
                        if (StringUtils.isEmpty(idCard)) {
                            msg += "第" + i + "行  身份证  不能为空<br/>";
                        }
                        User user = new User();
                        user.setNo(NO);// 学号
                        user.setPassword(MD5.getMD5(NO));
                        user.setUserName(userName);// 姓名
                        user.setSex(sexFlag);// 性别
                        user.setCreateTime(date);
                        if (stuClassId != null) {
                            user.setClassId(stuClassId);// 班级
                        }

                        user.setIdCard(idCard);// 身份证
                        usersList.add(user);
                    }
                }

            }
            if (msg == null || msg.equals("")) {
                // --------------批量保存
                this.insert("UserMapper.insertList", usersList);
            }

        }
        return msg;
    }

    // 辅助函数，查重
    private boolean checkUser(String No) {

        List<Integer> userList = this.selectList(
                "UserMapper.queryUserIdByNo", No);
        if (userList.size() == 0 || userList.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    private boolean checkClassName(String className) {
        List<StuClass> stuClassList = this.selectList("StuClassMapper.queryClassIdByClassName", className);
        if (stuClassList == null || stuClassList.size() == 0 || stuClassList.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }


    // ======导入 user表 部分===结束====================================

    /**
     * 班级导出 部分 stuClass
     */
    // =========导出 stuClass表 部分===开始====================================
    // 获得stuClass头
    public ArrayList<String> getstuClassHead() {
        ArrayList<String> headlist = new ArrayList<String>();
        headlist.add("班级名称");
        headlist.add("说明");
        headlist.add("所属学院");
        return headlist;
    }

    public List<StuClass> queryAllStuClasses() {
        return this.selectList("StuClassMapper.queryAllStuclass", null);
    }

    // 获得stuClass数据
    public List<List<String>> getstuClassData() {
        List<List<String>> dataList = new ArrayList<>();
        List<StuClass> stuClassList = queryAllStuClasses();
        List<List<String>> list = stuClassJoint(stuClassList);
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> al = (ArrayList<String>) list.get(i);
            dataList.add(al);
        }
        return dataList;
    }

    // 辅助函数
    private List<List<String>> stuClassJoint(List<StuClass> stuClassList) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < stuClassList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(stuClassList.get(i).getClassName() + "");// 班级名称
            small.add(stuClassList.get(i).getDescription() + "");// 说明
            //small.add(stuClassList.get(i).getSeletableCourseId() + "");// 可选择的课id
            small.add(stuClassList.get(i).getSchoolId() + "");// 所属学院
            list.add(small);
        }
        return list;
    }
    // ======导出 stuClass表 部分===结束====================================

    /**
     * 班级导入 部分 StuClass
     */
    // ======导入 StuClass表 部分===开始====================================
    public String updateExcelToStuClass(HttpServletRequest request,
                                        MultipartFile myFile, Integer mark) throws Exception {

        String msg = "";
        try (InputStream inputStream = myFile.getInputStream()) {
            HSSFWorkbook wookbook = new HSSFWorkbook(inputStream);
            Iterator<Sheet> iterator = wookbook.sheetIterator();
            int size = 0;
            while (iterator.hasNext()) {
                size += 1;
                iterator.next();
            }
            for (int j = 0; j < size; ++j) {
                HSSFSheet sheet = wookbook.getSheetAt(j);
                int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
                if (rows == 0) {
                    throw new BaseException("请填写数据");
                }
                for (int i = 1; i <= rows + 1; i++) {
                    // 读取左上端单元格
                    HSSFRow row = sheet.getRow(i);
                    // 行不为空
                    if (row != null) {
                        // **读取cell**
                        String className = getStringCellValue(row.getCell((short) 0)); // className
                        if (checkClassName(className)) {
                            msg += className + "班级已存在</br>";
                            break;
                        }
                        String description = getStringCellValue(row.getCell((short) 1)); // description
                        String schoolName = getStringCellValue(row.getCell((short) 2)); // schoolId
                        StuClass stuClass = new StuClass();
                        if (schoolName == null) {

                        } else {
                            Integer schoolId = selectOne("CollegeMapper.queryCollegeByName", schoolName);
                            if (ObjectUtils.isNotNull(schoolId)) {
                                stuClass.setSchoolId(schoolId);
                            } else {
                                msg = schoolName + "不存在";
                            }
                        }


                        stuClass.setClassName(className);
                        stuClass.setDescription(description);


                        stuClassService.addClass(stuClass);// 添加班级
                    }
                }
            }
        }

        return msg;
    }

    // ======导入 StuClass表 部分===结束====================================

    /**
     * 课程导出部分 course
     */
    // =========导出 course表 部分===开始====================================
    // 导出头
    public ArrayList<String> getCourseHead() {
        ArrayList<String> headlist = new ArrayList<String>();
        headlist.add("课程号");
        headlist.add("课程名");
        headlist.add("上课教师");
        headlist.add("职称");
        headlist.add("开课院系");
        headlist.add("课程属性");
        headlist.add("课容量");
        headlist.add("课时");
        headlist.add("学分");
        headlist.add("周一");
        headlist.add("周二");
        headlist.add("周三");
        headlist.add("周四");
        headlist.add("周五");
        headlist.add("周六");
        headlist.add("周日");
        headlist.add("上课地点");
        headlist.add("上课周次");
        headlist.add("选课人数");
        headlist.add("备注");
        headlist.add("类型");
        headlist.add("课序号");
        headlist.add("实验课序号");
        return headlist;
    }

    public List<Course> queryAllCourses() {
        return this.selectList("SelectCourseMapper.queryAllCourses", null);
    }

    // 导出数据
    public List<List<String>> getCourseData() {
        List<List<String>> dataList = new ArrayList<>();
        List<Course> courseList = queryAllCourses();
        List<List<String>> list = courseJoint(courseList);
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> al = (ArrayList<String>) list.get(i);
            dataList.add(al);
        }
        return dataList;
    }

    // 辅助函数
    private List<List<String>> courseJoint(List<Course> courseList) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < courseList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(courseList.get(i).getCourseNo() + "");// 课程号
            small.add(courseList.get(i).getCourseName() + "");// 课程名
            small.add(courseList.get(i).getTeacherName() + "");// 上课教师
            small.add(courseList.get(i).getJobTitle() + "");// 职称
            small.add(courseList.get(i).getOpenSchool() + "");// 开课院系
            small.add(courseList.get(i).getProperty() + "");// 课程属性
            small.add(courseList.get(i).getMaxNumber() + "");// 课容量
            small.add(String.valueOf(courseList.get(i).getLessionNum()));//学时
            small.add(courseList.get(i).getScore() + "");// 学分
            small.add(courseList.get(i).getWeek1() + "");// 周一
            small.add(courseList.get(i).getWeek2() + "");// 周二
            small.add(courseList.get(i).getWeek3() + "");// 周三
            small.add(courseList.get(i).getWeek4() + "");// 周四
            small.add(courseList.get(i).getWeek5() + "");// 周五
            small.add(courseList.get(i).getWeek6() + "");// 周六
            small.add(courseList.get(i).getWeek7() + "");// 周日
            small.add(courseList.get(i).getClassroom() + "");// 上课地点
            small.add(courseList.get(i).getWeekIds() + "");// 上课周次
            small.add(courseList.get(i).getResidaulCount() + "");// 选课人数
            small.add(courseList.get(i).getMemo() + "");// 备注
            small.add(courseList.get(i).isType() == true ? "1" : "0");// 类型,1理论课 0实验课
            small.add(courseList.get(i).getCourseNumber());// 课序号
            small.add(courseList.get(i).getLabCourseNumber());// 实验课序号
            list.add(small);
        }
        return list;
    }

    // ======导出 course表 部分===结束====================================

    // ======导入 course表 部分===开始====================================
    public String updateExcelToCourse(HttpServletRequest request,

                                      MultipartFile myFile, Integer mark) throws Exception {
        String[] flag = new String[1];
        flag[0] = "0";
        StringBuilder msg = new StringBuilder();
        try (InputStream in = myFile.getInputStream()) {
            HSSFWorkbook wookbook = new HSSFWorkbook(in);
            Iterator<Sheet> iterator = wookbook.sheetIterator();
            int size = 0;
            while (iterator.hasNext()) {
                size += 1;
                iterator.next();
            }
            for (int i = 0; i < size; ++i) {
                HSSFSheet sheet = wookbook.getSheetAt(i);
                int rows = sheet.getLastRowNum();
                if (rows == 0 && size == 0) {
                    throw new BaseException(sheet.getSheetName() + ":请填写数据,如果是空页,请删除");
                }
                processCourseSheet(sheet, flag, msg);
            }
        }
        return msg.toString();
    }

    private StringBuilder processCourseSheet(final HSSFSheet sheet, String[] flat, StringBuilder msg) throws BaseException {

        int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
     /*   if (rows == 0) {
            throw new BaseException(sheet.getSheetName()+":请填写数据,如果是空页,请删除");
        }*/

        Set<String> teacherNameSet = new HashSet<>(30);
        Map<String, Course> collectTIds = new HashMap<>();
        Map<String, Course> collectLabds = new HashMap<>();
        try {
            for (int i = 1; i <= rows + 1; i++) {
                // 读取左上端单元格
                HSSFRow row = sheet.getRow(i);
                // 行不为空
                if (row != null && row.getRowNum() != 0) {
                    // **从excel中 读取cell**
                    String COURSE_NO = getStringCellValue(row.getCell((short) 0));// 课程号:base表用
                    String COURSE_NAME = getStringCellValue(row.getCell((short) 1)); // 课程名

                    String TEACHER_NAME = getStringCellValue(row.getCell((short) 2)); // 上课教师
                    teacherNameSet.add(TEACHER_NAME);
                    Integer teacherId = selectOne("TeacherMapper.queryTeacerIdByTeacherName", TEACHER_NAME);


                    String JOB_TITLE = getStringCellValue(row.getCell((short) 3));// 职称
                    String OPEN_SCHOOL = getStringCellValue(row.getCell((short) 4)); // 开课院系
                    String PROPERTY = getStringCellValue(row.getCell((short) 5)); // 课程属性
                    int MAX_NUMBER = getIntCellValue(row.getCell((short) 6)); // 课容量
                    Integer LESSION_NUM = null;
                    try {
                        LESSION_NUM = getIntCellValue(row.getCell((short) 7)); // 课时
                    } catch (Exception e) {

                    }

                    float SCORE = getFloatCellValue(row.getCell((short) 8)); // 学分
                    Integer WEEK1 = getIntCellValue(row.getCell((short) 9)); //
                    Integer WEEK2 = getIntCellValue(row.getCell((short) 10)); //
                    Integer WEEK3 = getIntCellValue(row.getCell((short) 11)); //
                    Integer WEEK4 = getIntCellValue(row.getCell((short) 12)); //
                    Integer WEEK5 = getIntCellValue(row.getCell((short) 13)); //
                    Integer WEEK6 = getIntCellValue(row.getCell((short) 14)); //
                    Integer WEEK7 = getIntCellValue(row.getCell((short) 15)); //

                    String CLASS_ROOM = getStringCellValue(row.getCell((short) 16)); // 上课地点
                    String WEEK_IDS = getStringCellValue(row.getCell((short) 17));// 上课周次
                    Integer RESIDAUL_COUNT = getIntCellValue(row.getCell((short) 18));// 选课人数
                    String MEMO = getStringCellValue(row.getCell((short) 19));// 备注
                    Integer TYPE = getIntCellValue(row.getCell((short) 20));
                    String COURSE_NUMBER = getStringCellValue(row.getCell((short) 21));
                    String LAB_COURSE_NUMBER = getStringCellValue(row.getCell((short) 22));
                    Course course = new Course();
                    if (teacherId == null) {
                        msg.append("数据库不存在该老师").append(TEACHER_NAME).append("</br>");
                    } else {
                        course.setTeacherId(teacherId);
                    }
                    // 从excel中直接获取的字段
                    course.setCourseNo(COURSE_NO);
                    course.setCourseName(COURSE_NAME);
                    course.setTeacherName(TEACHER_NAME);
                    course.setJobTitle(JOB_TITLE);
                    course.setOpenSchool(OPEN_SCHOOL);
                    course.setProperty(PROPERTY);
                    course.setMaxNumber(MAX_NUMBER);
                    if (LESSION_NUM != null) {
                        course.setLessionNum(LESSION_NUM);
                    }

                    course.setScore(SCORE);
                    course.setWeek1(WEEK1);
                    course.setWeek2(WEEK2);
                    course.setWeek3(WEEK3);
                    course.setWeek4(WEEK4);
                    course.setWeek5(WEEK5);
                    course.setWeek6(WEEK6);
                    course.setWeek7(WEEK7);
                    course.setClassroom(CLASS_ROOM);


                    course.setWeekIds(WEEK_IDS);
                    course.setResidaulCount(RESIDAUL_COUNT);
                    course.setMemo(MEMO);
                    course.setCourseNumber(COURSE_NUMBER);
                    course.setLabCourseNumber(LAB_COURSE_NUMBER);
                    // 非 从excel中直接获取的字段 ---// type 课程类型 实验为0 理论为1

                    course.setType(TYPE == 1);

                    if (msg.length() == 0) {
                        this.insert("CourseMapper.createCourse", course);
                        if (course.isType()) {
                            collectTIds.put(course.getCourseNumber(), course);
                        } else {
                            collectLabds.put(course.getCourseNumber(), course);
                        }
                    } else {
                        throw new BaseException(msg + " 导入课程失败");
                    }
                    /**
                     * 顺便    导入 == Coursebase
                     */
                    if (COURSE_NO.equals(flat[0])) {
                        CourseBase courseBase = new CourseBase();
                        // 从excel中直接获取的字段
                        courseBase.setCourseNo(COURSE_NO);
                        courseBase.setCourseName(COURSE_NAME);
                        courseBase.setMemo(MEMO);
                        courseBaseService.addBaseCourse(courseBase);// 添加班级
                        flat[0] = COURSE_NO;
                    }
                }
            }

            for (Course courseT : collectTIds.values()) {
                String tabNumber = courseT.getLabCourseNumber();
                Course lab = collectLabds.get(tabNumber);
                courseT.setLabCourseId(lab.getCourseId());
                courseDao.updateCourse(courseT);
            }
/*    for (String teacherName : teacherNameSet) {
        List<Course> list = selectList("CourseMapper.queryCourseByTeacherName", teacherName);
        if (list == null) {
            msg.append("没有").append(teacherName).append("的课程,不能建立理论课实验课关联<br/>");
        } else {
           *//* if (list.size() == 2) {*//*


                int lId = -1;
                Course tCourse = null;
                for (Course it : list) {
                    if (it.isType()) {
                        tCourse = it;

                    } else {
                        lId = it.getCourseId();
                    }
                }
                if (tCourse == null || lId == -1) {

                } else {
                    tCourse.setLabCourseId(lId);
                    courseDao.updateCourse(tCourse);
                }


           *//* } else {
                for (Integer id : collectIds) {
                    delete("CourseMapper.deleteCourseById", id);
                }
                msg.append(teacherName + "该老师的课数量不对<br/>");
            }*//*
        }
    }*/
        } catch (Exception e) {

            for (Course it : collectTIds.values()) {
                delete("CourseMapper.deleteCourseById", it.getCourseId());
            }
            for (Course it : collectLabds.values()) {
                delete("CourseMapper.deleteCourseById", it.getCourseId());
            }
            throw new BaseException(msg + " 导入课程失败");
        }
        return msg;
    }

    // ======导入 course表 部分===结束====================================

    /**
     * college导出部分
     */
    // =========导出 college表 部分===开始====================================
    //获得头
    public ArrayList<String> getCollegeHead() {
        ArrayList<String> headlist = new ArrayList<String>();
        headlist.add("学院代码");
        headlist.add("学院名称");
        headlist.add("学院简介");
        return headlist;
    }

    //获得数据
    public List<List<String>> getCollegeData() {
        List<List<String>> dataList = new ArrayList<>();
        List<College> collegeList = collegeService.queryCollege();
        List<List<String>> list = collegeJoint(collegeList);
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> al = (ArrayList<String>) list.get(i);
            dataList.add(al);
        }
        return dataList;
    }

    // 辅助函数
    private List<List<String>> collegeJoint(List<College> collegeList) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < collegeList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(collegeList.get(i).getCollegeCode() + "");// 学院代码
            small.add(collegeList.get(i).getCollegeName() + "");// 学院名称
            small.add(collegeList.get(i).getDescription() + "");// 描述说明，学院简介
            list.add(small);
        }
        return list;
    }

    // =========导出 college表 部分===结束====================================

    /**
     * college导入部分
     */
    // ======导入 college表 部分===开始====================================
    public String updateExcelToCollege(HttpServletRequest request,
                                       MultipartFile myFile, Integer mark) throws Exception {

        String msg = "";
        try (InputStream inputStream = myFile.getInputStream()) {
            HSSFWorkbook wookbook = new HSSFWorkbook(inputStream);
            Iterator<Sheet> iterator = wookbook.sheetIterator();
            int size = 0;
            while (iterator.hasNext()) {
                size += 1;
                iterator.next();
            }
            for (int j = 0; j < size; ++j) {
                HSSFSheet sheet = wookbook.getSheetAt(j);
                int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
                if (rows == 0) {
                    throw new BaseException("请填写数据");
                }
                for (int i = 1; i <= rows + 1; i++) {
                    // 读取左上端单元格
                    HSSFRow row = sheet.getRow(i);
                    // 行不为空
                    if (row != null) {
                        // **读取cell**

                        String collegeCode = getStringCellValue(row.getCell((short) 0));// 学院代码
                        if (collegeCode == null) {
                            msg += "学院代码不能为空";
                        }
                        String collegeName = getStringCellValue(row.getCell((short) 1));// 学院名称
                        if (collegeName == null) {
                            msg += "学院名称不能为空";
                        }
                        String description = getStringCellValue(row.getCell((short) 2));// 描述说明，学院简介
                        if (description == null) {
                            description += "";
                        }
                        College college = new College();
                        college.setCollegeCode(collegeCode);
                        college.setCollegeName(collegeName);
                        college.setDescription(description);
                        List<Integer> id = selectList("CollegeMapper.queryCollegeByName", collegeName);
                        if (id == null || id.isEmpty()) {
                            collegeService.addCollege(college);
                        } else {
                            msg += collegeName + "学院重复<br>";
                        }

                    }

                }
            }
        }

        return msg;

    }

    // ======导入 college表 部分===结束====================================


    /**
     * courseBase导出部分
     */

    //=========导出 courseBase表 部分===开始====================================
    public ArrayList<String> getCourseBaseHead() {
        ArrayList<String> headlist = new ArrayList<String>();
        headlist.add("课程号");
        headlist.add("课程名");
        headlist.add("备注");
        return headlist;
    }

    public List<CourseBase> queryAllCourseBases() {
        return this.selectList("SelectCourseMapper.queryAllCourseBases", null);

    }

    public List<List<String>> getCourseBaseData() {
        List<List<String>> dataList = new ArrayList<>();
        List<CourseBase> courseBaseList = queryAllCourseBases();
        List<List<String>> list = courseBaseJoint(courseBaseList);
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> al = (ArrayList<String>) list.get(i);
            dataList.add(al);
        }
        return dataList;


    }

    // 辅助函数
    private List<List<String>> courseBaseJoint(List<CourseBase> courseBaseList) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < courseBaseList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(courseBaseList.get(i).getCourseNo() + "");// 课程号
            small.add(courseBaseList.get(i).getCourseName() + "");//课程名
            small.add(courseBaseList.get(i).getMemo() + "");// 备注
            list.add(small);
        }
        return list;
    }
    //======导出 courseBase表 部分===结束====================================


    // 辅助函数====开始================================

    /**
     * 获得Hsscell内容 获取string类型的值
     *
     * @param cell
     * @return
     */
    public String getStringCellValue(HSSFCell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_FORMULA:
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    DecimalFormat df = new DecimalFormat("0");
                    value = df.format(cell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue().trim();
                    break;
                default:
                    value = "";
                    break;
            }
        }
        return value.trim();
    }
 public String getStringCellValue(XSSFCell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case XSSFCell.CELL_TYPE_FORMULA:
                    break;
                case XSSFCell.CELL_TYPE_NUMERIC:
                    DecimalFormat df = new DecimalFormat("0");
                    value = df.format(cell.getNumericCellValue());
                    break;
                case XSSFCell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue().trim();
                    break;
                default:
                    value = "";
                    break;
            }
        }
        return value.trim();
    }

    /**
     * 获得Hsscell内容 转换成int类型
     *
     * @param cell
     * @return
     */
    public Integer getIntCellValue(HSSFCell cell) {
        String stringValue = getStringCellValue(cell);
        if (stringValue == null || !NumberUtils.isDigits(stringValue)) {
            return null;
        }
        Integer intValue = null;
        try {
            intValue = Integer.parseInt(stringValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return intValue;
    }

    public float getFloatCellValue(HSSFCell cell) {
        String stringValue = getStringCellValue(cell);

        float floatValue = -1;
        try {
            floatValue = Float.parseFloat(stringValue);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return floatValue;
    }

    // 辅助函数====结束================================

    /**
     * college导入部分
     */
    // ======导入 courseTableDo表 部分===开始====================================
    public String updateExcelTocourseTableDo(HttpServletRequest request,
                                             MultipartFile myFile, Integer mark) throws Exception {

        String msg = "";
        Map<String, Integer> name_id_map = new HashMap<>(30);
        List<CourseTableDo> list = new LinkedList<CourseTableDo>();

        try (InputStream inputStream = myFile.getInputStream()) {
            HSSFWorkbook wookbook = new HSSFWorkbook(inputStream);
            Iterator<Sheet> iterator = wookbook.sheetIterator();
            int size = 0;
            while (iterator.hasNext()) {
                size += 1;
                iterator.next();
            }

            for (int j = 0; j < size; ++j) {
                HSSFSheet sheet = wookbook.getSheetAt(j);
                int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
                if (rows == 0&&j==0) {
                    throw new BaseException("请填写数据");
                }
                for (int i = 1; i <= rows + 1; i++) {
                    // 读取左上端单元格
                    HSSFRow row = sheet.getRow(i);
                    // 行不为空
                    if (row != null) {
                        // **读取cell**

                        final Integer WEEK_ID = getIntCellValue(row.getCell((short) 0));// 学院代码
                        if (WEEK_ID == null) {
                            msg += "WEEK_ID不能为空";
                        }
                        final Integer SESSION_ID = getIntCellValue(row.getCell((short) 1));// 学院名称
                        if (SESSION_ID == null) {
                            msg += "SESSION_ID不能为空";
                        }
                        final String CLASS_NAME = getStringCellValue(row.getCell((short) 2));// 描述说明，学院简介
                        if (StringUtils.isEmpty(CLASS_NAME)) {
                            msg += "CLASS_NAME不能为空";
                        }

                        final String COURSE_NUMBER = getStringCellValue(row.getCell((short) 3));// 描述说明，学院简介
                        if (StringUtils.isEmpty(COURSE_NUMBER)) {
                            msg += "COURSE_NUMBER不能为空";
                        }
                         String DESCRIPTION = getStringCellValue(row.getCell((short) 4));// 描述说明，学院简介
                       if (StringUtils.isEmpty(DESCRIPTION)) {
                           DESCRIPTION = "";
                       }

                        CourseTableDo courseTableDo = new CourseTableDo();
                        courseTableDo.setSessionId(SESSION_ID);
                        courseTableDo.setWeekId(WEEK_ID);
                        List<Integer> stuClassIds = this.stuClassService.queryClassIdByName(CLASS_NAME);
                        if (!stuClassIds.isEmpty() && stuClassIds.size() == 1) {
                            courseTableDo.setClassId(stuClassIds.get(0));
                        } else {
                            msg += CLASS_NAME + "不能确定一个";
                        }
                        String[] courseNumbers = COURSE_NUMBER.split(",");
                        StringBuilder courseIds = new StringBuilder();
                        ArrayList<Integer> array = new ArrayList<>(courseNumbers.length);
                        for (String no : courseNumbers) {
                            Integer courseId = name_id_map.computeIfAbsent(no, (s) -> this.selectOne("CourseMapper.queryCourseByCourseNumber", s));
                            /*Course course=courseDao.queryCourseById(courseId);*/
                            Map<String,Integer> map=new HashMap<>();
                            map.put("WEEK_ID",WEEK_ID);
                            map.put("SESSION_ID",SESSION_ID);
                            map.put("courseId",courseId);
                            Course course=this.selectOne("CourseMapper.getCourse",map);
                            if(ObjectUtils.isNull(course)){
                                msg+="星期"+String.valueOf(WEEK_ID)+"第"+String.valueOf(SESSION_ID)+"大节"+"课程号为"+String.valueOf(no)+"不存在"+"<br>";
                            }
                            if (courseId == null) {
                                msg += no + "不存在";
                            } else {
                                array.add(courseId);

                            }
                        }
                        array.sort(Comparator.naturalOrder());
                        for (Integer id : array) {
                            courseIds.append(id).append(",");
                        }
                        courseTableDo.setCourseIds(courseIds.substring(0, courseIds.length() - 1));
                        courseTableDo.setDescription(DESCRIPTION);
                        list.add(courseTableDo);
                    }

                }
            }

            ///开始导入
            if (!"".equals(msg)) {
                return msg;
            }
            ////////////////////////////

            for (CourseTableDo it : list) {
                this.insert("CourseTableDoMapper.insert", it);

            }

        } catch (Exception e) {
            for (CourseTableDo it : list) {
                this.delete("CourseTableDoMapper.deleteByPrimaryKey", it);
            }
        }
        return msg;

    }

}

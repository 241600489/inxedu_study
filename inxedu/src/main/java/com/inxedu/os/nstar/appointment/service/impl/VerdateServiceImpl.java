package com.inxedu.os.nstar.appointment.service.impl;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.subject.Subject;
import com.inxedu.os.edu.entity.teacher.Teacher;
import com.inxedu.os.edu.entity.user.HyberUser;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.subject.SubjectService;
import com.inxedu.os.edu.service.teacher.TeacherService;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.nstar.appointment.entity.examBatch.ExamBatch;
import com.inxedu.os.nstar.appointment.entity.examClassroom.ExamClassroom;
import com.inxedu.os.nstar.appointment.entity.examCourse.ExamCourse;
import com.inxedu.os.nstar.appointment.entity.examManager.ExamManager;
import com.inxedu.os.nstar.appointment.entity.examSeat.ExamSeat;
import com.inxedu.os.nstar.appointment.entity.examStudent.ExamStudent;
import com.inxedu.os.nstar.appointment.service.*;
import com.inxedu.os.nstar.selectCourse.entity.college.College;
import com.inxedu.os.nstar.selectCourse.entity.course.CourseBase;
import com.inxedu.os.nstar.selectCourse.entity.selectCourse.CourseTableDo;
import com.inxedu.os.nstar.selectCourse.entity.stuClass.StuClass;
import com.inxedu.os.nstar.selectCourse.service.CollegeService;
import com.inxedu.os.nstar.selectCourse.service.CourseBaseService;
import com.inxedu.os.nstar.selectCourse.service.CourseTableDoService;
import com.inxedu.os.nstar.selectCourse.service.StuClassService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by 梁自强on 2016/8/15.
 */
@Service
public class VerdateServiceImpl extends GenericDaoImpl implements VerdateService {
    private Pattern pattern = Pattern.compile(",");
    @Autowired
    private CourseBaseService courseBaseService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private CourseTableDoService courseTableDoService;
    @Autowired
    private StuClassService stuClassService;
    @Autowired
    private UserService userService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private ExamStudentService examStudentService;
    @Autowired
    private ExamCourseService examCourseService;
    @Autowired
    private ExamClassroomService examClassroomService;
    @Autowired
    private ExamSeatService examSeatService;
    @Autowired
    private ExamManagerService examManagerService;
    @Autowired
    private ExamBatchService examBatchService;
    Map<String, Object> co1 = new HashMap<>();
    Map<String, Object> co = new HashMap<>();

    @Override
    public List<String> Verlidate() {

 /*       List<College> colleges=collegeService.queryCollege();
        for (College college:colleges
             ) {
        co1.put("co_"+String.valueOf(college.getId()),college);
        }
        List<Course> list = courseService.queryAllCourse();
        for (Course course : list) {
            co1.put("c_"+String.valueOf(course.getId()), course);
            CourseBase courseBase = courseBaseService.queryCourseBaseByNo(course.getCourseNo());
            if (courseBase == null) {
                message = "COURSE_ID为" + String.valueOf(course.getId()) + String.valueOf(course.getId()) + course.getCourseNo() + "在" + "edu_course_base" + "不存在对应的元组";
                list1.add(message);
            }
            Teacher teacher = teacherService.getTeacherById(course.getTeacherId());
            co1.put("t_"+String.valueOf(course.getTeacherId()), teacher);
            co.put(course.getCourseNo(), courseBase);
            if (teacher == null) {
                message = "COURSE_ID为" + String.valueOf(course.getId()) + String.valueOf(course.getId()) + "TEACHER_ID=" + String.valueOf(course.getTeacherId()) + "在edu_teacher表里没有";
                list1.add(message);
            }
            try {
                if (course.getResidaulCount() < 0 || course.getResidaulCount() > course.getMaxNumber())
                    message = "COURSE_ID为" + String.valueOf(course.getId()) + String.valueOf(course.getId()) + "的RESIDAUL_COUNT小于0或者是大大于最大容量,不合法";
            } catch (Exception e) {
                message = "COURSE_ID为" + String.valueOf(course.getId()) + "的RESIDAUL_COUNT为null";
                list1.add(message);
            }
            Subject subject = subjectService.getSubjectById(course.getSubjectId());
            co1.put("s_"+String.valueOf(course.getSubjectId()), subject);
            if (subject == null) {
                message = "COURSE_ID为" + String.valueOf(course.getId()) + "的SUBJECT_ID对应的主业为空";
                list1.add(message);
            }

        }*/
        List<String> list1 = new ArrayList<>();
        String message = "校验成功";
        list1.add(message);
        List<StuClass> stuClasses = stuClassService.queryAllStuclass();
        for (StuClass stuClass : stuClasses) {
            co1.put("sc_"+String.valueOf(stuClass.getId()), stuClass);
            College college = (College) co1.get("co_"+String.valueOf(stuClass.getSchoolId()));}

        /*    if (Objects.isNull(college)) {
                message = "ID为" + String.valueOf(stuClass.getId()) + "的schoolId在edu_college表里不存在";
                list1.add(message);
            }*//*
            String[] ids = getValidCourseId(stuClass.getSeletableCourseId());
            int size = ids.length;
            if (size > 0) {
                for (int i = 0; i < size; ++i) {
                    final CourseBase it = courseBaseService.queryByPrimaryKey(Integer.parseInt(ids[i]));
                    if (Objects.nonNull(it)) {
                        message = "edu_stuclass表中id=" + String.valueOf(stuClass.getId()) + "的SeletableCourseId中的" + ids[i] + "在edu_course表里不存在";
                        list1.add(message);
                    }
                }
            }
        }
        List<CourseTableDo> list2 = courseTableDoService.queryAllCoueBase();
        for (CourseTableDo courseTableDo : list2) {
            co1.put("ct_"+String.valueOf(courseTableDo.getId()), courseTableDo);
            String[] s = getValidCourseId(courseTableDo.getCourseIds());
            Integer size = s.length;
            if (size > 0) {
                for (int i = 0; i < size; ++i) {
                    final Course it = courseService.queryCourseById(Integer.parseInt(s[i]));

                    if (Objects.nonNull(it)) {
                        message = "edu_course_table_do表中id=" + String.valueOf(courseTableDo.getId()) + "的COURSE_IDS中的" + s[i] + "在edu_course表里不存在";
                        list1.add(message);
                    }
                }
            }
                StuClass stuClass = (StuClass) co1.get("sc_"+courseTableDo.getClassId());
                if (stuClass == null) {
                    message = "ID为" + String.valueOf(courseTableDo.getId()) + "的CLASS_ID" + "在edu_stuclass不存在";
                    list1.add(message);
                }

        try {
            List<Course> list3 = courseService.getCourseList(courseTableDo.getWeekId(), courseTableDo.getSessionId());
            if (!Objects.nonNull(list3)) {
                message = "ID为" + String.valueOf(courseTableDo.getId()) + "WEEK_ID和SESSION_ID在course表里没有对应的";
                list1.add(message);
            }
        }
            catch (Exception e){
                System.out.println(e);
            }*/
            List<User> list4 = userService.queryAllUsers();
       try{


            for (User user: list4) {
                co1.put("u_" + String.valueOf(user.getUserId()), user);
                co.put(user.getNo(), user);
       /*         StuClass stuClass1 = (StuClass) co1.get("sc_" + user.getClassId());
                if (Objects.isNull(stuClass1)) {
                    message = "USER_ID为" + String.valueOf(user.getUserId()) + "的CLAAS_ID在edu_stuClass表里不存在";
                    list1.add(message);
                }*/
                ExamStudent examStudent= (ExamStudent) co.get("s_"+user.getNo());
                if(Objects.isNull(examStudent)){
                    message=user.getNo();
                    list1.add(message);
                }
                /*if (Objects.isNull(user.getNo())) {
                    message = "USER_ID为" + String.valueOf(user.getUserId()) + "的NO为空";
                    list1.add(message);
                }
                if (Objects.isNull(user.getIdCard())) {
                    message = "USER_ID为" + String.valueOf(user.getUserId()) + "的ID_CARD为空";
                    list1.add(message);
                }
                List<HyberUser> list5 = userService.queryUserByNo(user.getNo());
                if (list5.size()> 1) {
                    message = "USER_ID为" + String.valueOf(user.getUserId()) + "的NO重复";
                }*/
            }
       }catch (Exception e){
           System.out.println(e);
       }
        return list1;
    }
  Map<Map<String,Integer>,Object> map=new HashMap<>();
    @Override
    public List<String> ExamVerlidate() {
        String message="校验成功";
        List<String> list = new ArrayList<>();
        list.add(message);
   /*     List<ExamManager> examManagerList=examManagerService.queryAllExamManager();
        List<ExamBatch> examBatchList=examBatchService.queryAllExamBatch();
        for (ExamManager examManager:examManagerList
             ) {
            co1.put("em_"+examManager.getId(),examManager);

        }*/

       /* List<ExamCourse>  examCourseList=examCourseService.queryAllExamCourse();
        List<ExamClassroom> examClassRoomList=examClassroomService.queryClassRoomName();
        for (ExamClassroom examClassroom:examClassRoomList
             ) {
            co1.put("ecs_"+examClassroom.getId(),examClassroom);
            co.put(examClassroom.getExamClassroomName(),examClassroom);
        }
        for(ExamCourse examCourse:examCourseList){
            co1.put("ecu_"+examCourse.getId(),examCourse);
            Map<String,Integer> map1=new HashMap<>();
            map1.put(examCourse.getCourseCode(),examCourse.getId());
            map.put(map1,examCourse);
        }*/
      List<ExamStudent> examStudentList = examStudentService.queryAllExamStudent();
        for (ExamStudent examStudent : examStudentList) {
            co1.put("est_"+examStudent.getId(), examStudent);
            co.put("s_"+examStudent.getStudentNo(),examStudent);
            User user= (User) co.get(examStudent.getStudentNo());
         /*   if (Objects.isNull(user)){
             message="ExamStudent的id为" + String.valueOf(examStudent.getId()) + "的student_no在edu_user表里不存在";
                list.add(message);
            }*/
          /*  ExamCourse examCourse= (ExamCourse) co.get(examStudent.getCourseCode());
            if(Objects.isNull(examCourse)){
                message="ExamStudent的id为" + String.valueOf(examStudent.getId()) + "的course_code在exam_course表里不存在";
                list.add(message);
            }*/
        }
       /* for (ExamBatch examBatch:examBatchList
                ) {
            co1.put("ebt_"+examBatch.getId(),examBatch);
            ExamManager examManager= (ExamManager) co1.get("em_"+examBatch.getExamManagerId());
            if(ObjectUtils.isNull(examManager)){
                message="ExamBatch的id为" + String.valueOf(examBatch.getId()) + "的exam_manager_id在exam_manager表里不存在";
                list.add(message);
            }
            Map<String,Integer> map2=new HashMap<>();
            map2.put(examBatch.getCourseNo(),examBatch.getId());
          ExamCourse examCourse=(ExamCourse) map.get(map2);
            if(ObjectUtils.isNull(examCourse)){
                message="ExamBatch的id为" + String.valueOf(examBatch.getId()) + "的course_id和course_no在exam_course表里不存在";
                list.add(message);
            }
        }
      List<ExamSeat> examSeatList=  examSeatService.queryAllExamSeatVerlidate();
        for(ExamSeat examSeat:examSeatList){
            ExamClassroom examClassroom= (ExamClassroom) co.get(examSeat.getExamClassroomName());
            if(Objects.isNull(examClassroom)){
                message="ExamSeat的id为" + String.valueOf(examSeat.getId()) + "的exam_classroom_name在exam_classroom表里不存在";
                list.add(message);
            }
            ExamBatch examBatch= (ExamBatch) co1.get("ebt_"+examSeat.getExamBatchId());
            if(ObjectUtils.isNull(examBatchList)){
                message="ExamSeat的id为" + String.valueOf(examSeat.getId()) + "的exam_batch_id在exam_batch表里不存在";
                list.add(message);
            }*/

       /* }*/
        return list;
    }
    String[] getValidCourseId(String str) {
        if (StringUtils.isNotEmpty(str)) {
            return pattern.split(str);
        } else {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
    }
}

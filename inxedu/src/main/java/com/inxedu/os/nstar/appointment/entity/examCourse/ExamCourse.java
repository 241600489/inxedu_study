package com.inxedu.os.nstar.appointment.entity.examCourse;

import lombok.Data;
import java.io.Serializable;
/**考试课程信息
 * Created by Nstar on 2016/8/3.
 */
@Data
public class ExamCourse implements Serializable {

    private Integer id;
    private String courseCode; // 考试课程代码  如大学计算机基础  base
    private String courseName; // 考试课程名称
    private String memo;
}
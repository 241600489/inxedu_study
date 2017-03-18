package com.inxedu.os.nstar.appointment.entity.examStudent;

import lombok.Data;

import java.io.Serializable;

/**考生信息
 * Created by Nstar on 2016/8/3.
 */
@Data
public class ExamStudent implements Serializable{

    private Integer id;
    private String studentNo; // 学号
    private String studentName; // 姓名
    private String studentClass; // 班级名
    private String courseCode; // 考试课程代码  如大学计算机基础  base
    private String courseName; // 考试课程名称
    private Boolean lock; // 锁定  1为锁定 0为解锁  默认为0  如果不允许参加考试则可以设为1
    private String memo; // 备注
    private int teacherId;
}

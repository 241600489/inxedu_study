package com.inxedu.os.nstar.appointment.entity.examBatch;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/** 考试批次信息
 * Created by Nstar on 2016/8/3.
 */
@Data
public class ExamBatch implements Serializable {

    private Integer id;

    private Integer examBatchNo; // 考试批次编号
    private String examClassroom; // 考场名称  可以根据考场名关联 考场
    private Date examDate; // 考试日期
    private String examBeginEndTime; // 考试时间段
    private String courseNo; // 考试课程代码
    private String courseName; // 考试课程名称
    private Integer courseId; // 考试课程Id 关联 ExamCourse表
    private Integer appointmentCount; // 已经预约人数
    private Integer number; // 考场容量
    private Boolean state; // 状态  1为开启 0为关闭 默认为1
    private String memo;
    private Integer examManagerId; // 考试id
    private String examName;
}

package com.inxedu.os.nstar.appointment.entity;

import lombok.Data;

import java.util.Date;

/**学生预约
 * Created by Nstar on 2016/8/3.
 */
@Data
public class StudentAppointment {

    private Integer id;
    private String studentNo; // 学号
    private String studentName; // 姓名
    private String studentClass; // 班级名
    private Date examDate; // 考试日期
    private String examClassroom; //考场名称
    private Integer seatNo; // 座位号
    private Integer batchNo; // 批次号
    private Boolean state; // 预约状态 1为预约   0为未预约 默认为0
    private String examName; // 考试名称
    private String score; // 考试分数
    private Date updateDate;// 变更时间
    private String memo; // 备注
    private Integer examBatchId;//批次Id
}

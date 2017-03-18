package com.inxedu.os.nstar.appointment.entity.examManager;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/** 考试控制管理
 * Created by Nstar on 2016/8/3.
 */
@Data
public class ExamManager implements Serializable {

    private Integer id;
    private String examName; // 考试名称
    private Date appointmentBeginTime; // 预约开始时间
    private Date appointmentEndTime; // 预约结束时间
    private Boolean isCourseController; // 是否开启科目限制 1为开启 0为未开启 默认为0
    private Boolean state; // 状态 1为开启 0为关闭 默认为1
    private String memo;

}

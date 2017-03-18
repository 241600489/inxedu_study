package com.inxedu.os.nstar.appointment.entity.examAppointmentLog;


import lombok.Data;

import java.io.Serializable;

/**考试预约日志
 * Created by Nstar on 2016/8/3.
 */
@Data
public class ExamAppointmentLog implements Serializable {

    private Integer id;

    private String events; // 操作事件  如 需要记录 退约操作，二次（多次）预约 操作，第一次预约不记录
    private String object; // 操作对象  如 预约表

    private String eventDate; // 发生时间  2015-09-09 12:00
    private String eventMan; // 操作人  某个学生

}

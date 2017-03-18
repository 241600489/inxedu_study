package com.inxedu.os.nstar.appointment.entity.examClassroom;

import lombok.Data;

import java.io.Serializable;

/**考场信息
 * Created by Nstar on 2016/8/3.
 */
@Data
public class ExamClassroom implements Serializable {

    private Integer id;
    private String examClassroomName;// 考场名称
    private Integer number; // 考场容量，使用这个字段判断是否 选满
    private Integer maxNumber; // 考场最大容量
    private Boolean state; // 考场状态  1为启用  0为停用 默认为1
}

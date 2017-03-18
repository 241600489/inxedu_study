package com.inxedu.os.nstar.appointment.entity.examSeat;

import lombok.Data;

import java.io.Serializable;

/** 考场座位信息
 * Created by Nstar on 2016/8/3.
 */
@Data
public class ExamSeat implements Serializable {
    public ExamSeat(Integer id, Integer seatNo, String examClassroomName, Boolean state, Integer examBatchId) {
        this.id = id;
        this.seatNo = seatNo;
        this.examClassroomName = examClassroomName;
        this.state = state;
        ExamBatchId = examBatchId;
    }
    private Integer id;
    private Integer seatNo; // 考场座位编号
    private String examClassroomName; // 所属考场名称 如15J504
    private Boolean state; // 是否被选  1为被选  0为空闲  默认为0

    private Integer ExamBatchId; // 考试id 关联ExamBatch

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Integer seatNo) {
        this.seatNo = seatNo;
    }

    public String getExamClassroomName() {
        return examClassroomName;
    }

    public void setExamClassroomName(String examClassroomName) {
        this.examClassroomName = examClassroomName;
    }

    public Integer getExamBatchId() {
        return ExamBatchId;
    }

    public void setExamBatchId(Integer examBatchId) {
        ExamBatchId = examBatchId;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
    public ExamSeat(){

    }
}

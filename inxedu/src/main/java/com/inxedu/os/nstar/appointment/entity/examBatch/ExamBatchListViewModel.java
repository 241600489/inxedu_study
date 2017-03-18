package com.inxedu.os.nstar.appointment.entity.examBatch;

import org.apache.commons.collections.ListUtils;

import java.util.List;

public class ExamBatchListViewModel {


    private List<ExamBatch> examBatcheList;
    private boolean isAppointment;
    public static final ExamBatchListViewModel EMPTY=new ExamBatchListViewModel(ListUtils.EMPTY_LIST,false);
    public List<ExamBatch> getExamBatcheList() {
        return examBatcheList;
    }

    public void setExamBatcheList(List<ExamBatch> examBatcheList) {
        this.examBatcheList = examBatcheList;
    }

    public boolean isAppointment() {
        return isAppointment;
    }

    public void setAppointment(boolean appointment) {
        isAppointment = appointment;
    }

    public ExamBatchListViewModel() {

    }

    public ExamBatchListViewModel(List<ExamBatch> examBatcheList, boolean isAppointment) {
        this.examBatcheList = examBatcheList;
        this.isAppointment = isAppointment;
    }
}
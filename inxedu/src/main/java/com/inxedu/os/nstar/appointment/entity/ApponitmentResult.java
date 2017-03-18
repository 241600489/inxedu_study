package com.inxedu.os.nstar.appointment.entity;

import com.inxedu.os.nstar.appointment.entity.examStudentAppointment.ExamStudentAppointment;

/**
 * Created by karak on 16-8-10.
 */
public class ApponitmentResult {
    AppointmentState state;
            ExamStudentAppointment appointment;

    public ApponitmentResult(AppointmentState state) {
        this.state = state;
    }

    public AppointmentState getState() {
        return state;
    }

    public void setState(AppointmentState state) {
        this.state = state;
    }

    public ExamStudentAppointment getAppointment() {
        return appointment;
    }

    public void setAppointment(ExamStudentAppointment appointment) {
        this.appointment = appointment;
    }
}

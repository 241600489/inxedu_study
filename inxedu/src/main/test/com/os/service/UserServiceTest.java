package com.os.service;

import com.BaseSpringCase;
import com.inxedu.os.nstar.appointment.entity.examSeat.ExamSeat;
import com.inxedu.os.nstar.appointment.service.ExamSeatService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

/**
 * Created by 3 on 2016/10/5.
 */
public class UserServiceTest  extends BaseSpringCase{

    @Autowired
    private ExamSeatService examSeatService;
    @Test
    public void addUser() {
            ExamSeat examSeat=new ExamSeat();
            examSeat.setExamClassroomName("15J201");
            examSeat.setState(true);
            examSeat.setSeatNo(21);
            examSeatService.addExamSeat(examSeat);
    }
}
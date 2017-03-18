package com.inxedu.os.nstar.appointment.service.impl;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.examClassroom.ExamClassroom;
import com.inxedu.os.nstar.appointment.entity.examSeat.ExamSeat;
import com.inxedu.os.nstar.appointment.entity.examStudentAppointment.ExamStudentAppointment;
import com.inxedu.os.nstar.appointment.service.ExamSeatService;
import org.apache.commons.collections.ListUtils;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.stereotype.Service;

import javax.swing.plaf.ListUI;
import java.util.List;

@Service
public class ExamSeatServiceImpl extends GenericDaoImpl implements ExamSeatService {

    public ExamSeat[][] queryExamSeat(String examClassroomName, int column) {
        List<ExamSeat> examSeats = queryExamSeat(examClassroomName);
        if (examSeats.isEmpty()) {
            return new ExamSeat[0][0];
        }
        final int size = examSeats.size();
        System.out.println(size);
        int row = size / column;

        final int rest = size % column;
        if (rest == 0) {
            row = row;
        } else {
            row += 1;
        }
        final ExamSeat[][] result = new ExamSeat[row][column];

        int index = 0;
        for (int i = 0; i < row - 1; ++i) {
            for (int j = 0; j < column; ++j) {
                result[i][j] = examSeats.get(index);
                ++index;
            }
        }
        int finalRow = row - 1;
        if (rest == 0) {
            for (int i = 0; i < column; ++i) {
                result[finalRow][i] = examSeats.get(index);
                ++index;
            }
        } else {
            for (int i = 0; i < rest; ++i) {
                result[finalRow][i] = examSeats.get(index);
                ++index;
            }
        }
        System.out.println(index);

        return result;

    }

    public List<ExamSeat> queryExamSeat(String examClassroomName) {
        List<ExamSeat> examSeats = sqlSession.selectList("ExamSeatMapper.queryExamSeatByExamClassroomName", examClassroomName);
        if (examSeats == null) {
            return ListUtils.EMPTY_LIST;
        } else {
            return examSeats;
        }

    }

    @Override
    public void addExamSeat(ExamSeat examSeat) {
        this.insert("ExamSeatMapper.insert", examSeat);
    }

    @Override
    public List<ExamSeat> queryAllExamSeat(PageEntity page) {
        List<ExamSeat> list =this.queryForListPage("ExamSeatMapper.queryAllExamSeat",null,page);
        return list;
    }

    @Override
    public void deleteExamSeat(int id) {
        this.delete("ExamSeatMapper.deleteByPrimaryKey",id);


    }

    @Override
    public void updateExamSeat(ExamSeat examSeat) {
        this.update("ExamSeatMapper.updateOne",examSeat);
    }

    @Override
    public List<ExamSeat> queryExamSeat(Integer seatNo) {
        ExamSeat examSeat=new ExamSeat();
       examSeat.setSeatNo(seatNo);
       List<ExamSeat> list=this.selectList("ExamSeatMapper.queryExamSeat",examSeat);
        return list;
    }

    @Override
    public ExamSeat queryByPrimaryKey(Integer id) {
        ExamSeat examSeat=this.selectOne("ExamSeatMapper.selectByPrimaryKey",id);
        return examSeat;
    }

    @Override
    public List<ExamSeat> queryExamSeatByexamClassroomName(String examClassroomName, PageEntity page) {
       ExamSeat examSeat=new ExamSeat();
        examSeat.setExamClassroomName(examClassroomName);
        List<ExamSeat> list=this.queryForListPage("ExamSeatMapper.queryExamSeatByClassroomName",examSeat,page);
        return list;
    }

    @Override
    public ExamSeat queryExamSeatBySandN(int seatNo, String examClassroomName){
        ExamSeat examSeat=new ExamSeat();
        examSeat.setSeatNo(seatNo);
        examSeat.setExamClassroomName(examClassroomName);
        ExamSeat examSeat1=this.selectOne("ExamSeatMapper.queryExamSeatBySandN",examSeat);
        return examSeat1 ;
    }

    @Override
    public List<ExamSeat> queryAllExamSeatVerlidate() {
        return this.selectList("ExamSeatMapper.queryAllExamSeatExport",null);
    }

    @Override
    public List<ExamSeat> queryExamSeatByExamBatchId(Integer examBatchId) {
        return this.selectList("ExamSeatMapper.queryExamSeatByExamBatchId",examBatchId);
    }

    @Override
    public List<ExamSeat> queryExamSeatByExamBatchIdWithPage(Integer examBatchId, PageEntity page) {
        return this.queryForListPage("ExamSeatMapper.queryExamSeatByExamBatchIdWithPage",examBatchId,page);
    }

    @Override
    public List<ExamSeat> queryExamSeatbySandExambatchId(int seatNo, Integer examBatchId, PageEntity page) {
        ExamSeat examSeat=new ExamSeat();
        examSeat.setExamBatchId(examBatchId);
        examSeat.setSeatNo(seatNo);
        return this.queryForListPage("ExamSeatMapper.queryExamSeatbySandExambatchId",examSeat,page);
    }

    @Override
    public ExamSeat queryExamSeatbySeatNOandExamBatchId(int seatNo1, Integer examBatchId) {
        ExamSeat examSeat=new ExamSeat();
        examSeat.setExamBatchId(examBatchId);
        examSeat.setSeatNo(seatNo1);
        return this.selectOne
                ("ExamSeatMapper.queryExamSeatbySeatNOandExamBatchId",examSeat);
    }
}

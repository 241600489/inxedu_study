package com.inxedu.os.nstar.appointment.service.impl;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.nstar.appointment.entity.examBatch.ExamBatch;
import com.inxedu.os.nstar.appointment.entity.examManager.ExamManager;
import com.inxedu.os.nstar.appointment.entity.examSeat.ExamSeat;
import com.inxedu.os.nstar.appointment.service.ExamBatchService;
import com.inxedu.os.nstar.appointment.service.ExamSeatService;
import com.sun.xml.internal.ws.developer.Serialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**考试批次管理
 * Created by Nstar on 2016/8/3.
 */
@Service
public class ExamBatchServiceImpl extends GenericDaoImpl  implements ExamBatchService {
    @Autowired
    private ExamSeatService examSeatService;
    @Override
    public void addExamBatch(ExamBatch examBatch) {
        this.insert("ExamBatchMapper.insertExamBatch",examBatch);
    }

    @Override
    public void updateExamBatch(ExamBatch examBatch) {

        this.update("ExamBatchMapper.uodateExamBatch",examBatch);
    }

    @Override
    public void deleteExamBatch(int id) {
        PageEntity page=new PageEntity();
        List<ExamSeat> list=new ArrayList<ExamSeat>(10);
        while (ObjectUtils.isNotNull(list=examSeatService.queryExamSeatByExamBatchIdWithPage(id,page))){
            for (ExamSeat examSeat:list
                    ) {
                examSeatService.deleteExamSeat(examSeat.getId());
            }
        }
        this.delete("ExamBatchMapper.delete",id);
    }

    @Override
    public List<ExamBatch> queryExamBatchList(PageEntity page,int examManagerId) {
        ExamBatch examBatch=new ExamBatch();
        examBatch.setExamManagerId(examManagerId);
        List<ExamBatch> list = this.queryForListPage("ExamBatchMapper.queryExamBatchList",examBatch,page);
        return list;
    }

    @Override
    public ExamBatch queryExamBatchById(int id) {
        ExamBatch examBatch = this.selectOne("ExamBatchMapper.queryExamBatchById",id);
        return examBatch;
    }

    @Override
    public List<ExamBatch> queryAllExamBatch() {
        List<ExamBatch> list=this.selectList("ExamBatchMapper.queryAllExamBatchExport",null);
        return list;
    }
    /**
     * 根据考场名称和考试批次查询
     */


    @Override
    public List<ExamBatch> queryExamClassroomAndExamBatch(String exanName, int examBatchNo, PageEntity page, int examManagerId) {
        ExamBatch examBatch=new ExamBatch();
        examBatch.setExamClassroom(exanName);
        examBatch.setExamBatchNo(examBatchNo);
        examBatch.setExamManagerId(examManagerId);
        List<ExamBatch> list=this.queryForListPage("ExamBatchMapper.queryExamClassAndExamBatch",examBatch,page);
        return list;
    }
    @Override
    public Integer queryExamBatchId(Integer examBatchNo) {
    return this.selectOne("ExamBatchMapper.queryExamBatchId",examBatchNo);
    }

    @Override
    public List<ExamBatch> queryExamBatchByClassRoomName(String examClassroomName) {
        return this.selectList("ExamBatchMapper.queryExamBatchByClassRoomName",examClassroomName);
    }
}

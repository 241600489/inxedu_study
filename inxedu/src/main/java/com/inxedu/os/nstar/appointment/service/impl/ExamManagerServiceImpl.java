package com.inxedu.os.nstar.appointment.service.impl;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.nstar.appointment.entity.examBatch.ExamBatch;
import com.inxedu.os.nstar.appointment.entity.examManager.ExamManager;
import com.inxedu.os.nstar.appointment.entity.examSeat.ExamSeat;
import com.inxedu.os.nstar.appointment.service.AppointmentFrontService;
import com.inxedu.os.nstar.appointment.service.ExamBatchService;
import com.inxedu.os.nstar.appointment.service.ExamManagerService;
import com.inxedu.os.nstar.appointment.service.ExamSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by  on 16-8-5.
 */
@Service
public class ExamManagerServiceImpl extends GenericDaoImpl implements ExamManagerService {
   @Autowired
    ExamBatchService examBatchService;
    /**
     * 增加
     */
    @Override
    public void addExamManager(ExamManager examManager){
        this.insert("ExamManagerMapper.insert",examManager);
    };

    @Autowired
    AppointmentFrontService appointmentFrontService;
    @Autowired
    ExamSeatService examSeatService;
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteExamManager(int id){
        PageEntity page=new PageEntity();
        page.setPageSize(1000);
        List<ExamBatch> list = examBatchService.queryExamBatchList(page, id);
        if (ObjectUtils.isNotNull(list)){
            for (ExamBatch examBatch:list
                 ) {
                List<ExamSeat> examSeats = appointmentFrontService.queryExamSeatByBatchId(examBatch.getId());
                if (ObjectUtils.isNotNull(examSeats)){
                    for (ExamSeat examSeat:examSeats
                         ) {
                        examSeatService.deleteExamSeat(examSeat.getId());
                    }
                }
                examBatchService.deleteExamBatch(examBatch.getId());
            }
        }
        int a=1/0;
        this.delete("ExamManagerMapper.delete",id);
    }


    /**
     * 修改
     */
    @Override
    public void updateExamManager(ExamManager examManager){
        this.update("ExamManagerMapper.updateExamManager",examManager);
    }
    /**
     * 显示所有数据
     */
    @Override
    public List<ExamManager> queryExamManagerList(PageEntity page){
        List<ExamManager> list = this.queryForListPage("ExamManagerMapper.queryExamMangerList",null,page);
        return list;
    }

    /**
     * 根据id获取对象
     */
    @Override
    public ExamManager queryExamManagerById(int id){
        return this.selectOne("ExamManagerMapper.queryExamManagerById",id);
    }

    /**
     * 根据考试名称查询
     */
    @Override
    public List<ExamManager> queryExamManagerByName(PageEntity page,String examName){
        ExamManager examManager=new ExamManager();
        examManager.setExamName(examName);
        List<ExamManager> list = this.queryForListPage("ExamManagerMapper.queryExamManagerByName",examManager,page);
        return list;
    }
    /**
     * 根据考试名称模糊查询
     */
    @Override
    public List<ExamManager> queryExamManagersByName(PageEntity page,String examName){
        ExamManager examManager=new ExamManager();
        examManager.setExamName(examName);
        List<ExamManager> list = this.queryForListPage("ExamManagerMapper.queryExamManagesrByName",examManager,page);
        return list;
    }

    @Override
    public List<ExamManager> queryAllExamManager() {
        List<ExamManager> list=this.selectList("ExamManagerMapper.queryAllExamManager",null);
        return list;
    }

    @Override
    public ExamManager queryExamManagerByExamName(String examName) {
        return this.selectOne("ExamManagerMapper.queryExamManagesrByNameNotPage",examName);
    }


   /* @Override
    public List<ExamManager> queryAllExamManager(PageEntity page) {
        return null;
    }

    @Override
    public List<ExamManager> queryExamManagerByexamName(String examName, PageEntity pageEntity) {
        return null;
    }*/
}

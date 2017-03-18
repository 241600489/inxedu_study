package com.inxedu.os.nstar.appointment.entity.examBatch;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
public class ExamBatchExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamBatchExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAppointmentCountIsNull() {
            addCriterion("appointment_count is null");
            return (Criteria) this;
        }

        public Criteria andAppointmentCountIsNotNull() {
            addCriterion("appointment_count is not null");
            return (Criteria) this;
        }

        public Criteria andAppointmentCountEqualTo(Integer value) {
            addCriterion("appointment_count =", value, "appointmentCount");
            return (Criteria) this;
        }

        public Criteria andAppointmentCountNotEqualTo(Integer value) {
            addCriterion("appointment_count <>", value, "appointmentCount");
            return (Criteria) this;
        }

        public Criteria andAppointmentCountGreaterThan(Integer value) {
            addCriterion("appointment_count >", value, "appointmentCount");
            return (Criteria) this;
        }

        public Criteria andAppointmentCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("appointment_count >=", value, "appointmentCount");
            return (Criteria) this;
        }

        public Criteria andAppointmentCountLessThan(Integer value) {
            addCriterion("appointment_count <", value, "appointmentCount");
            return (Criteria) this;
        }

        public Criteria andAppointmentCountLessThanOrEqualTo(Integer value) {
            addCriterion("appointment_count <=", value, "appointmentCount");
            return (Criteria) this;
        }

        public Criteria andAppointmentCountIn(List<Integer> values) {
            addCriterion("appointment_count in", values, "appointmentCount");
            return (Criteria) this;
        }

        public Criteria andAppointmentCountNotIn(List<Integer> values) {
            addCriterion("appointment_count not in", values, "appointmentCount");
            return (Criteria) this;
        }

        public Criteria andAppointmentCountBetween(Integer value1, Integer value2) {
            addCriterion("appointment_count between", value1, value2, "appointmentCount");
            return (Criteria) this;
        }

        public Criteria andAppointmentCountNotBetween(Integer value1, Integer value2) {
            addCriterion("appointment_count not between", value1, value2, "appointmentCount");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNull() {
            addCriterion("course_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNotNull() {
            addCriterion("course_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIdEqualTo(Integer value) {
            addCriterion("course_id =", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotEqualTo(Integer value) {
            addCriterion("course_id <>", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThan(Integer value) {
            addCriterion("course_id >", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_id >=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThan(Integer value) {
            addCriterion("course_id <", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThanOrEqualTo(Integer value) {
            addCriterion("course_id <=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIn(List<Integer> values) {
            addCriterion("course_id in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotIn(List<Integer> values) {
            addCriterion("course_id not in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdBetween(Integer value1, Integer value2) {
            addCriterion("course_id between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("course_id not between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNull() {
            addCriterion("course_name is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("course_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("course_name =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("course_name <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("course_name >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("course_name >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("course_name <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("course_name <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("course_name like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("course_name not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("course_name in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("course_name not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("course_name between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("course_name not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNoIsNull() {
            addCriterion("course_no is null");
            return (Criteria) this;
        }

        public Criteria andCourseNoIsNotNull() {
            addCriterion("course_no is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNoEqualTo(String value) {
            addCriterion("course_no =", value, "courseNo");
            return (Criteria) this;
        }

        public Criteria andCourseNoNotEqualTo(String value) {
            addCriterion("course_no <>", value, "courseNo");
            return (Criteria) this;
        }

        public Criteria andCourseNoGreaterThan(String value) {
            addCriterion("course_no >", value, "courseNo");
            return (Criteria) this;
        }

        public Criteria andCourseNoGreaterThanOrEqualTo(String value) {
            addCriterion("course_no >=", value, "courseNo");
            return (Criteria) this;
        }

        public Criteria andCourseNoLessThan(String value) {
            addCriterion("course_no <", value, "courseNo");
            return (Criteria) this;
        }

        public Criteria andCourseNoLessThanOrEqualTo(String value) {
            addCriterion("course_no <=", value, "courseNo");
            return (Criteria) this;
        }

        public Criteria andCourseNoLike(String value) {
            addCriterion("course_no like", value, "courseNo");
            return (Criteria) this;
        }

        public Criteria andCourseNoNotLike(String value) {
            addCriterion("course_no not like", value, "courseNo");
            return (Criteria) this;
        }

        public Criteria andCourseNoIn(List<String> values) {
            addCriterion("course_no in", values, "courseNo");
            return (Criteria) this;
        }

        public Criteria andCourseNoNotIn(List<String> values) {
            addCriterion("course_no not in", values, "courseNo");
            return (Criteria) this;
        }

        public Criteria andCourseNoBetween(String value1, String value2) {
            addCriterion("course_no between", value1, value2, "courseNo");
            return (Criteria) this;
        }

        public Criteria andCourseNoNotBetween(String value1, String value2) {
            addCriterion("course_no not between", value1, value2, "courseNo");
            return (Criteria) this;
        }

        public Criteria andExamBatchNoIsNull() {
            addCriterion("exam_batch_no is null");
            return (Criteria) this;
        }

        public Criteria andExamBatchNoIsNotNull() {
            addCriterion("exam_batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andExamBatchNoEqualTo(Integer value) {
            addCriterion("exam_batch_no =", value, "examBatchNo");
            return (Criteria) this;
        }

        public Criteria andExamBatchNoNotEqualTo(Integer value) {
            addCriterion("exam_batch_no <>", value, "examBatchNo");
            return (Criteria) this;
        }

        public Criteria andExamBatchNoGreaterThan(Integer value) {
            addCriterion("exam_batch_no >", value, "examBatchNo");
            return (Criteria) this;
        }

        public Criteria andExamBatchNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_batch_no >=", value, "examBatchNo");
            return (Criteria) this;
        }

        public Criteria andExamBatchNoLessThan(Integer value) {
            addCriterion("exam_batch_no <", value, "examBatchNo");
            return (Criteria) this;
        }

        public Criteria andExamBatchNoLessThanOrEqualTo(Integer value) {
            addCriterion("exam_batch_no <=", value, "examBatchNo");
            return (Criteria) this;
        }

        public Criteria andExamBatchNoIn(List<Integer> values) {
            addCriterion("exam_batch_no in", values, "examBatchNo");
            return (Criteria) this;
        }

        public Criteria andExamBatchNoNotIn(List<Integer> values) {
            addCriterion("exam_batch_no not in", values, "examBatchNo");
            return (Criteria) this;
        }

        public Criteria andExamBatchNoBetween(Integer value1, Integer value2) {
            addCriterion("exam_batch_no between", value1, value2, "examBatchNo");
            return (Criteria) this;
        }

        public Criteria andExamBatchNoNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_batch_no not between", value1, value2, "examBatchNo");
            return (Criteria) this;
        }

        public Criteria andExamBeginEndTimeIsNull() {
            addCriterion("exam_begin_end_time is null");
            return (Criteria) this;
        }

        public Criteria andExamBeginEndTimeIsNotNull() {
            addCriterion("exam_begin_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andExamBeginEndTimeEqualTo(String value) {
            addCriterion("exam_begin_end_time =", value, "examBeginEndTime");
            return (Criteria) this;
        }

        public Criteria andExamBeginEndTimeNotEqualTo(String value) {
            addCriterion("exam_begin_end_time <>", value, "examBeginEndTime");
            return (Criteria) this;
        }

        public Criteria andExamBeginEndTimeGreaterThan(String value) {
            addCriterion("exam_begin_end_time >", value, "examBeginEndTime");
            return (Criteria) this;
        }

        public Criteria andExamBeginEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("exam_begin_end_time >=", value, "examBeginEndTime");
            return (Criteria) this;
        }

        public Criteria andExamBeginEndTimeLessThan(String value) {
            addCriterion("exam_begin_end_time <", value, "examBeginEndTime");
            return (Criteria) this;
        }

        public Criteria andExamBeginEndTimeLessThanOrEqualTo(String value) {
            addCriterion("exam_begin_end_time <=", value, "examBeginEndTime");
            return (Criteria) this;
        }

        public Criteria andExamBeginEndTimeLike(String value) {
            addCriterion("exam_begin_end_time like", value, "examBeginEndTime");
            return (Criteria) this;
        }

        public Criteria andExamBeginEndTimeNotLike(String value) {
            addCriterion("exam_begin_end_time not like", value, "examBeginEndTime");
            return (Criteria) this;
        }

        public Criteria andExamBeginEndTimeIn(List<String> values) {
            addCriterion("exam_begin_end_time in", values, "examBeginEndTime");
            return (Criteria) this;
        }

        public Criteria andExamBeginEndTimeNotIn(List<String> values) {
            addCriterion("exam_begin_end_time not in", values, "examBeginEndTime");
            return (Criteria) this;
        }

        public Criteria andExamBeginEndTimeBetween(String value1, String value2) {
            addCriterion("exam_begin_end_time between", value1, value2, "examBeginEndTime");
            return (Criteria) this;
        }

        public Criteria andExamBeginEndTimeNotBetween(String value1, String value2) {
            addCriterion("exam_begin_end_time not between", value1, value2, "examBeginEndTime");
            return (Criteria) this;
        }

        public Criteria andExamClassroomIsNull() {
            addCriterion("exam_classroom is null");
            return (Criteria) this;
        }

        public Criteria andExamClassroomIsNotNull() {
            addCriterion("exam_classroom is not null");
            return (Criteria) this;
        }

        public Criteria andExamClassroomEqualTo(String value) {
            addCriterion("exam_classroom =", value, "examClassroom");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNotEqualTo(String value) {
            addCriterion("exam_classroom <>", value, "examClassroom");
            return (Criteria) this;
        }

        public Criteria andExamClassroomGreaterThan(String value) {
            addCriterion("exam_classroom >", value, "examClassroom");
            return (Criteria) this;
        }

        public Criteria andExamClassroomGreaterThanOrEqualTo(String value) {
            addCriterion("exam_classroom >=", value, "examClassroom");
            return (Criteria) this;
        }

        public Criteria andExamClassroomLessThan(String value) {
            addCriterion("exam_classroom <", value, "examClassroom");
            return (Criteria) this;
        }

        public Criteria andExamClassroomLessThanOrEqualTo(String value) {
            addCriterion("exam_classroom <=", value, "examClassroom");
            return (Criteria) this;
        }

        public Criteria andExamClassroomLike(String value) {
            addCriterion("exam_classroom like", value, "examClassroom");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNotLike(String value) {
            addCriterion("exam_classroom not like", value, "examClassroom");
            return (Criteria) this;
        }

        public Criteria andExamClassroomIn(List<String> values) {
            addCriterion("exam_classroom in", values, "examClassroom");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNotIn(List<String> values) {
            addCriterion("exam_classroom not in", values, "examClassroom");
            return (Criteria) this;
        }

        public Criteria andExamClassroomBetween(String value1, String value2) {
            addCriterion("exam_classroom between", value1, value2, "examClassroom");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNotBetween(String value1, String value2) {
            addCriterion("exam_classroom not between", value1, value2, "examClassroom");
            return (Criteria) this;
        }

        public Criteria andExamDateIsNull() {
            addCriterion("exam_date is null");
            return (Criteria) this;
        }

        public Criteria andExamDateIsNotNull() {
            addCriterion("exam_date is not null");
            return (Criteria) this;
        }

        public Criteria andExamDateEqualTo(Date value) {
            addCriterion("exam_date =", value, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateNotEqualTo(Date value) {
            addCriterion("exam_date <>", value, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateGreaterThan(Date value) {
            addCriterion("exam_date >", value, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateGreaterThanOrEqualTo(Date value) {
            addCriterion("exam_date >=", value, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateLessThan(Date value) {
            addCriterion("exam_date <", value, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateLessThanOrEqualTo(Date value) {
            addCriterion("exam_date <=", value, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateIn(List<Date> values) {
            addCriterion("exam_date in", values, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateNotIn(List<Date> values) {
            addCriterion("exam_date not in", values, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateBetween(Date value1, Date value2) {
            addCriterion("exam_date between", value1, value2, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateNotBetween(Date value1, Date value2) {
            addCriterion("exam_date not between", value1, value2, "examDate");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("memo not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Boolean value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Boolean value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Boolean value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Boolean value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Boolean value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Boolean> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Boolean> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Boolean value1, Boolean value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
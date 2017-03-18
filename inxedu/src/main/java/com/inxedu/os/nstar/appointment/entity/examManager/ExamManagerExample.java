package com.inxedu.os.nstar.appointment.entity.examManager;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
public class ExamManagerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamManagerExample() {
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

        public Criteria andAppointmentBeginTimeIsNull() {
            addCriterion("appointment_begin_time is null");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeIsNotNull() {
            addCriterion("appointment_begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeEqualTo(Date value) {
            addCriterion("appointment_begin_time =", value, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeNotEqualTo(Date value) {
            addCriterion("appointment_begin_time <>", value, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeGreaterThan(Date value) {
            addCriterion("appointment_begin_time >", value, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("appointment_begin_time >=", value, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeLessThan(Date value) {
            addCriterion("appointment_begin_time <", value, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("appointment_begin_time <=", value, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeIn(List<Date> values) {
            addCriterion("appointment_begin_time in", values, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeNotIn(List<Date> values) {
            addCriterion("appointment_begin_time not in", values, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeBetween(Date value1, Date value2) {
            addCriterion("appointment_begin_time between", value1, value2, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("appointment_begin_time not between", value1, value2, "appointmentBeginTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeIsNull() {
            addCriterion("appointment_end_time is null");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeIsNotNull() {
            addCriterion("appointment_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeEqualTo(Date value) {
            addCriterion("appointment_end_time =", value, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeNotEqualTo(Date value) {
            addCriterion("appointment_end_time <>", value, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeGreaterThan(Date value) {
            addCriterion("appointment_end_time >", value, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("appointment_end_time >=", value, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeLessThan(Date value) {
            addCriterion("appointment_end_time <", value, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("appointment_end_time <=", value, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeIn(List<Date> values) {
            addCriterion("appointment_end_time in", values, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeNotIn(List<Date> values) {
            addCriterion("appointment_end_time not in", values, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeBetween(Date value1, Date value2) {
            addCriterion("appointment_end_time between", value1, value2, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointmentEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("appointment_end_time not between", value1, value2, "appointmentEndTime");
            return (Criteria) this;
        }

        public Criteria andExamNameIsNull() {
            addCriterion("exam_name is null");
            return (Criteria) this;
        }

        public Criteria andExamNameIsNotNull() {
            addCriterion("exam_name is not null");
            return (Criteria) this;
        }

        public Criteria andExamNameEqualTo(String value) {
            addCriterion("exam_name =", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotEqualTo(String value) {
            addCriterion("exam_name <>", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameGreaterThan(String value) {
            addCriterion("exam_name >", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameGreaterThanOrEqualTo(String value) {
            addCriterion("exam_name >=", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameLessThan(String value) {
            addCriterion("exam_name <", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameLessThanOrEqualTo(String value) {
            addCriterion("exam_name <=", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameLike(String value) {
            addCriterion("exam_name like", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotLike(String value) {
            addCriterion("exam_name not like", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameIn(List<String> values) {
            addCriterion("exam_name in", values, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotIn(List<String> values) {
            addCriterion("exam_name not in", values, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameBetween(String value1, String value2) {
            addCriterion("exam_name between", value1, value2, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotBetween(String value1, String value2) {
            addCriterion("exam_name not between", value1, value2, "examName");
            return (Criteria) this;
        }

        public Criteria andIsCourseControllerIsNull() {
            addCriterion("is_course_controller is null");
            return (Criteria) this;
        }

        public Criteria andIsCourseControllerIsNotNull() {
            addCriterion("is_course_controller is not null");
            return (Criteria) this;
        }

        public Criteria andIsCourseControllerEqualTo(Boolean value) {
            addCriterion("is_course_controller =", value, "isCourseController");
            return (Criteria) this;
        }

        public Criteria andIsCourseControllerNotEqualTo(Boolean value) {
            addCriterion("is_course_controller <>", value, "isCourseController");
            return (Criteria) this;
        }

        public Criteria andIsCourseControllerGreaterThan(Boolean value) {
            addCriterion("is_course_controller >", value, "isCourseController");
            return (Criteria) this;
        }

        public Criteria andIsCourseControllerGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_course_controller >=", value, "isCourseController");
            return (Criteria) this;
        }

        public Criteria andIsCourseControllerLessThan(Boolean value) {
            addCriterion("is_course_controller <", value, "isCourseController");
            return (Criteria) this;
        }

        public Criteria andIsCourseControllerLessThanOrEqualTo(Boolean value) {
            addCriterion("is_course_controller <=", value, "isCourseController");
            return (Criteria) this;
        }

        public Criteria andIsCourseControllerIn(List<Boolean> values) {
            addCriterion("is_course_controller in", values, "isCourseController");
            return (Criteria) this;
        }

        public Criteria andIsCourseControllerNotIn(List<Boolean> values) {
            addCriterion("is_course_controller not in", values, "isCourseController");
            return (Criteria) this;
        }

        public Criteria andIsCourseControllerBetween(Boolean value1, Boolean value2) {
            addCriterion("is_course_controller between", value1, value2, "isCourseController");
            return (Criteria) this;
        }

        public Criteria andIsCourseControllerNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_course_controller not between", value1, value2, "isCourseController");
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
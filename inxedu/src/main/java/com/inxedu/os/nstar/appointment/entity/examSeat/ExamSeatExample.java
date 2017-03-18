package com.inxedu.os.nstar.appointment.entity.examSeat;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ExamSeatExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamSeatExample() {
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

        public Criteria andExamBatchIdIsNull() {
            addCriterion("exam_batch_id is null");
            return (Criteria) this;
        }

        public Criteria andExamBatchIdIsNotNull() {
            addCriterion("exam_batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamBatchIdEqualTo(Integer value) {
            addCriterion("exam_batch_id =", value, "examBatchId");
            return (Criteria) this;
        }

        public Criteria andExamBatchIdNotEqualTo(Integer value) {
            addCriterion("exam_batch_id <>", value, "examBatchId");
            return (Criteria) this;
        }

        public Criteria andExamBatchIdGreaterThan(Integer value) {
            addCriterion("exam_batch_id >", value, "examBatchId");
            return (Criteria) this;
        }

        public Criteria andExamBatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_batch_id >=", value, "examBatchId");
            return (Criteria) this;
        }

        public Criteria andExamBatchIdLessThan(Integer value) {
            addCriterion("exam_batch_id <", value, "examBatchId");
            return (Criteria) this;
        }

        public Criteria andExamBatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("exam_batch_id <=", value, "examBatchId");
            return (Criteria) this;
        }

        public Criteria andExamBatchIdIn(List<Integer> values) {
            addCriterion("exam_batch_id in", values, "examBatchId");
            return (Criteria) this;
        }

        public Criteria andExamBatchIdNotIn(List<Integer> values) {
            addCriterion("exam_batch_id not in", values, "examBatchId");
            return (Criteria) this;
        }

        public Criteria andExamBatchIdBetween(Integer value1, Integer value2) {
            addCriterion("exam_batch_id between", value1, value2, "examBatchId");
            return (Criteria) this;
        }

        public Criteria andExamBatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_batch_id not between", value1, value2, "examBatchId");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNameIsNull() {
            addCriterion("exam_classroom_name is null");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNameIsNotNull() {
            addCriterion("exam_classroom_name is not null");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNameEqualTo(String value) {
            addCriterion("exam_classroom_name =", value, "examClassroomName");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNameNotEqualTo(String value) {
            addCriterion("exam_classroom_name <>", value, "examClassroomName");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNameGreaterThan(String value) {
            addCriterion("exam_classroom_name >", value, "examClassroomName");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNameGreaterThanOrEqualTo(String value) {
            addCriterion("exam_classroom_name >=", value, "examClassroomName");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNameLessThan(String value) {
            addCriterion("exam_classroom_name <", value, "examClassroomName");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNameLessThanOrEqualTo(String value) {
            addCriterion("exam_classroom_name <=", value, "examClassroomName");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNameLike(String value) {
            addCriterion("exam_classroom_name like", value, "examClassroomName");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNameNotLike(String value) {
            addCriterion("exam_classroom_name not like", value, "examClassroomName");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNameIn(List<String> values) {
            addCriterion("exam_classroom_name in", values, "examClassroomName");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNameNotIn(List<String> values) {
            addCriterion("exam_classroom_name not in", values, "examClassroomName");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNameBetween(String value1, String value2) {
            addCriterion("exam_classroom_name between", value1, value2, "examClassroomName");
            return (Criteria) this;
        }

        public Criteria andExamClassroomNameNotBetween(String value1, String value2) {
            addCriterion("exam_classroom_name not between", value1, value2, "examClassroomName");
            return (Criteria) this;
        }

        public Criteria andSeatNoIsNull() {
            addCriterion("seat_no is null");
            return (Criteria) this;
        }

        public Criteria andSeatNoIsNotNull() {
            addCriterion("seat_no is not null");
            return (Criteria) this;
        }

        public Criteria andSeatNoEqualTo(Integer value) {
            addCriterion("seat_no =", value, "seatNo");
            return (Criteria) this;
        }

        public Criteria andSeatNoNotEqualTo(Integer value) {
            addCriterion("seat_no <>", value, "seatNo");
            return (Criteria) this;
        }

        public Criteria andSeatNoGreaterThan(Integer value) {
            addCriterion("seat_no >", value, "seatNo");
            return (Criteria) this;
        }

        public Criteria andSeatNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("seat_no >=", value, "seatNo");
            return (Criteria) this;
        }

        public Criteria andSeatNoLessThan(Integer value) {
            addCriterion("seat_no <", value, "seatNo");
            return (Criteria) this;
        }

        public Criteria andSeatNoLessThanOrEqualTo(Integer value) {
            addCriterion("seat_no <=", value, "seatNo");
            return (Criteria) this;
        }

        public Criteria andSeatNoIn(List<Integer> values) {
            addCriterion("seat_no in", values, "seatNo");
            return (Criteria) this;
        }

        public Criteria andSeatNoNotIn(List<Integer> values) {
            addCriterion("seat_no not in", values, "seatNo");
            return (Criteria) this;
        }

        public Criteria andSeatNoBetween(Integer value1, Integer value2) {
            addCriterion("seat_no between", value1, value2, "seatNo");
            return (Criteria) this;
        }

        public Criteria andSeatNoNotBetween(Integer value1, Integer value2) {
            addCriterion("seat_no not between", value1, value2, "seatNo");
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
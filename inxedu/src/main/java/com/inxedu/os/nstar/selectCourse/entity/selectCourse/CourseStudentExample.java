package com.inxedu.os.nstar.selectCourse.entity.selectCourse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseStudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseStudentExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCourseTableIdIsNull() {
            addCriterion("COURSE_TABLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCourseTableIdIsNotNull() {
            addCriterion("COURSE_TABLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCourseTableIdEqualTo(Integer value) {
            addCriterion("COURSE_TABLE_ID =", value, "courseTableId");
            return (Criteria) this;
        }

        public Criteria andCourseTableIdNotEqualTo(Integer value) {
            addCriterion("COURSE_TABLE_ID <>", value, "courseTableId");
            return (Criteria) this;
        }

        public Criteria andCourseTableIdGreaterThan(Integer value) {
            addCriterion("COURSE_TABLE_ID >", value, "courseTableId");
            return (Criteria) this;
        }

        public Criteria andCourseTableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("COURSE_TABLE_ID >=", value, "courseTableId");
            return (Criteria) this;
        }

        public Criteria andCourseTableIdLessThan(Integer value) {
            addCriterion("COURSE_TABLE_ID <", value, "courseTableId");
            return (Criteria) this;
        }

        public Criteria andCourseTableIdLessThanOrEqualTo(Integer value) {
            addCriterion("COURSE_TABLE_ID <=", value, "courseTableId");
            return (Criteria) this;
        }

        public Criteria andCourseTableIdIn(List<Integer> values) {
            addCriterion("COURSE_TABLE_ID in", values, "courseTableId");
            return (Criteria) this;
        }

        public Criteria andCourseTableIdNotIn(List<Integer> values) {
            addCriterion("COURSE_TABLE_ID not in", values, "courseTableId");
            return (Criteria) this;
        }

        public Criteria andCourseTableIdBetween(Integer value1, Integer value2) {
            addCriterion("COURSE_TABLE_ID between", value1, value2, "courseTableId");
            return (Criteria) this;
        }

        public Criteria andCourseTableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("COURSE_TABLE_ID not between", value1, value2, "courseTableId");
            return (Criteria) this;
        }

        public Criteria andSelectCourseDateTimeIsNull() {
            addCriterion("SELECT_COURSE_DATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSelectCourseDateTimeIsNotNull() {
            addCriterion("SELECT_COURSE_DATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSelectCourseDateTimeEqualTo(Date value) {
            addCriterion("SELECT_COURSE_DATE_TIME =", value, "selectCourseDateTime");
            return (Criteria) this;
        }

        public Criteria andSelectCourseDateTimeNotEqualTo(Date value) {
            addCriterion("SELECT_COURSE_DATE_TIME <>", value, "selectCourseDateTime");
            return (Criteria) this;
        }

        public Criteria andSelectCourseDateTimeGreaterThan(Date value) {
            addCriterion("SELECT_COURSE_DATE_TIME >", value, "selectCourseDateTime");
            return (Criteria) this;
        }

        public Criteria andSelectCourseDateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SELECT_COURSE_DATE_TIME >=", value, "selectCourseDateTime");
            return (Criteria) this;
        }

        public Criteria andSelectCourseDateTimeLessThan(Date value) {
            addCriterion("SELECT_COURSE_DATE_TIME <", value, "selectCourseDateTime");
            return (Criteria) this;
        }

        public Criteria andSelectCourseDateTimeLessThanOrEqualTo(Date value) {
            addCriterion("SELECT_COURSE_DATE_TIME <=", value, "selectCourseDateTime");
            return (Criteria) this;
        }

        public Criteria andSelectCourseDateTimeIn(List<Date> values) {
            addCriterion("SELECT_COURSE_DATE_TIME in", values, "selectCourseDateTime");
            return (Criteria) this;
        }

        public Criteria andSelectCourseDateTimeNotIn(List<Date> values) {
            addCriterion("SELECT_COURSE_DATE_TIME not in", values, "selectCourseDateTime");
            return (Criteria) this;
        }

        public Criteria andSelectCourseDateTimeBetween(Date value1, Date value2) {
            addCriterion("SELECT_COURSE_DATE_TIME between", value1, value2, "selectCourseDateTime");
            return (Criteria) this;
        }

        public Criteria andSelectCourseDateTimeNotBetween(Date value1, Date value2) {
            addCriterion("SELECT_COURSE_DATE_TIME not between", value1, value2, "selectCourseDateTime");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("STUDENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("STUDENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(Integer value) {
            addCriterion("STUDENT_ID =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(Integer value) {
            addCriterion("STUDENT_ID <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(Integer value) {
            addCriterion("STUDENT_ID >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("STUDENT_ID >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(Integer value) {
            addCriterion("STUDENT_ID <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(Integer value) {
            addCriterion("STUDENT_ID <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<Integer> values) {
            addCriterion("STUDENT_ID in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<Integer> values) {
            addCriterion("STUDENT_ID not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(Integer value1, Integer value2) {
            addCriterion("STUDENT_ID between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("STUDENT_ID not between", value1, value2, "studentId");
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
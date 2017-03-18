package com.inxedu.os.nstar.selectCourse.entity.selectCourse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseControllerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseControllerExample() {
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

        public Criteria andClassIdIsNull() {
            addCriterion("class_id is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("class_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(Integer value) {
            addCriterion("class_id =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(Integer value) {
            addCriterion("class_id <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(Integer value) {
            addCriterion("class_id >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("class_id >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(Integer value) {
            addCriterion("class_id <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(Integer value) {
            addCriterion("class_id <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<Integer> values) {
            addCriterion("class_id in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<Integer> values) {
            addCriterion("class_id not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(Integer value1, Integer value2) {
            addCriterion("class_id between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(Integer value1, Integer value2) {
            addCriterion("class_id not between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime0IsNull() {
            addCriterion("begin_course_signup_time0 is null");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime0IsNotNull() {
            addCriterion("begin_course_signup_time0 is not null");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime0EqualTo(Date value) {
            addCriterion("begin_course_signup_time0 =", value, "beginCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime0NotEqualTo(Date value) {
            addCriterion("begin_course_signup_time0 <>", value, "beginCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime0GreaterThan(Date value) {
            addCriterion("begin_course_signup_time0 >", value, "beginCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime0GreaterThanOrEqualTo(Date value) {
            addCriterion("begin_course_signup_time0 >=", value, "beginCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime0LessThan(Date value) {
            addCriterion("begin_course_signup_time0 <", value, "beginCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime0LessThanOrEqualTo(Date value) {
            addCriterion("begin_course_signup_time0 <=", value, "beginCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime0In(List<Date> values) {
            addCriterion("begin_course_signup_time0 in", values, "beginCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime0NotIn(List<Date> values) {
            addCriterion("begin_course_signup_time0 not in", values, "beginCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime0Between(Date value1, Date value2) {
            addCriterion("begin_course_signup_time0 between", value1, value2, "beginCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime0NotBetween(Date value1, Date value2) {
            addCriterion("begin_course_signup_time0 not between", value1, value2, "beginCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime1IsNull() {
            addCriterion("begin_course_signup_time1 is null");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime1IsNotNull() {
            addCriterion("begin_course_signup_time1 is not null");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime1EqualTo(Date value) {
            addCriterion("begin_course_signup_time1 =", value, "beginCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime1NotEqualTo(Date value) {
            addCriterion("begin_course_signup_time1 <>", value, "beginCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime1GreaterThan(Date value) {
            addCriterion("begin_course_signup_time1 >", value, "beginCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime1GreaterThanOrEqualTo(Date value) {
            addCriterion("begin_course_signup_time1 >=", value, "beginCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime1LessThan(Date value) {
            addCriterion("begin_course_signup_time1 <", value, "beginCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime1LessThanOrEqualTo(Date value) {
            addCriterion("begin_course_signup_time1 <=", value, "beginCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime1In(List<Date> values) {
            addCriterion("begin_course_signup_time1 in", values, "beginCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime1NotIn(List<Date> values) {
            addCriterion("begin_course_signup_time1 not in", values, "beginCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime1Between(Date value1, Date value2) {
            addCriterion("begin_course_signup_time1 between", value1, value2, "beginCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andBeginCourseSignupTime1NotBetween(Date value1, Date value2) {
            addCriterion("begin_course_signup_time1 not between", value1, value2, "beginCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime0IsNull() {
            addCriterion("end_course_signup_time0 is null");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime0IsNotNull() {
            addCriterion("end_course_signup_time0 is not null");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime0EqualTo(Date value) {
            addCriterion("end_course_signup_time0 =", value, "endCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime0NotEqualTo(Date value) {
            addCriterion("end_course_signup_time0 <>", value, "endCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime0GreaterThan(Date value) {
            addCriterion("end_course_signup_time0 >", value, "endCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime0GreaterThanOrEqualTo(Date value) {
            addCriterion("end_course_signup_time0 >=", value, "endCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime0LessThan(Date value) {
            addCriterion("end_course_signup_time0 <", value, "endCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime0LessThanOrEqualTo(Date value) {
            addCriterion("end_course_signup_time0 <=", value, "endCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime0In(List<Date> values) {
            addCriterion("end_course_signup_time0 in", values, "endCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime0NotIn(List<Date> values) {
            addCriterion("end_course_signup_time0 not in", values, "endCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime0Between(Date value1, Date value2) {
            addCriterion("end_course_signup_time0 between", value1, value2, "endCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime0NotBetween(Date value1, Date value2) {
            addCriterion("end_course_signup_time0 not between", value1, value2, "endCourseSignupTime0");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime1IsNull() {
            addCriterion("end_course_signup_time1 is null");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime1IsNotNull() {
            addCriterion("end_course_signup_time1 is not null");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime1EqualTo(Date value) {
            addCriterion("end_course_signup_time1 =", value, "endCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime1NotEqualTo(Date value) {
            addCriterion("end_course_signup_time1 <>", value, "endCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime1GreaterThan(Date value) {
            addCriterion("end_course_signup_time1 >", value, "endCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime1GreaterThanOrEqualTo(Date value) {
            addCriterion("end_course_signup_time1 >=", value, "endCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime1LessThan(Date value) {
            addCriterion("end_course_signup_time1 <", value, "endCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime1LessThanOrEqualTo(Date value) {
            addCriterion("end_course_signup_time1 <=", value, "endCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime1In(List<Date> values) {
            addCriterion("end_course_signup_time1 in", values, "endCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime1NotIn(List<Date> values) {
            addCriterion("end_course_signup_time1 not in", values, "endCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime1Between(Date value1, Date value2) {
            addCriterion("end_course_signup_time1 between", value1, value2, "endCourseSignupTime1");
            return (Criteria) this;
        }

        public Criteria andEndCourseSignupTime1NotBetween(Date value1, Date value2) {
            addCriterion("end_course_signup_time1 not between", value1, value2, "endCourseSignupTime1");
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

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateManIsNull() {
            addCriterion("update_man is null");
            return (Criteria) this;
        }

        public Criteria andUpdateManIsNotNull() {
            addCriterion("update_man is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateManEqualTo(String value) {
            addCriterion("update_man =", value, "updateMan");
            return (Criteria) this;
        }

        public Criteria andUpdateManNotEqualTo(String value) {
            addCriterion("update_man <>", value, "updateMan");
            return (Criteria) this;
        }

        public Criteria andUpdateManGreaterThan(String value) {
            addCriterion("update_man >", value, "updateMan");
            return (Criteria) this;
        }

        public Criteria andUpdateManGreaterThanOrEqualTo(String value) {
            addCriterion("update_man >=", value, "updateMan");
            return (Criteria) this;
        }

        public Criteria andUpdateManLessThan(String value) {
            addCriterion("update_man <", value, "updateMan");
            return (Criteria) this;
        }

        public Criteria andUpdateManLessThanOrEqualTo(String value) {
            addCriterion("update_man <=", value, "updateMan");
            return (Criteria) this;
        }

        public Criteria andUpdateManLike(String value) {
            addCriterion("update_man like", value, "updateMan");
            return (Criteria) this;
        }

        public Criteria andUpdateManNotLike(String value) {
            addCriterion("update_man not like", value, "updateMan");
            return (Criteria) this;
        }

        public Criteria andUpdateManIn(List<String> values) {
            addCriterion("update_man in", values, "updateMan");
            return (Criteria) this;
        }

        public Criteria andUpdateManNotIn(List<String> values) {
            addCriterion("update_man not in", values, "updateMan");
            return (Criteria) this;
        }

        public Criteria andUpdateManBetween(String value1, String value2) {
            addCriterion("update_man between", value1, value2, "updateMan");
            return (Criteria) this;
        }

        public Criteria andUpdateManNotBetween(String value1, String value2) {
            addCriterion("update_man not between", value1, value2, "updateMan");
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
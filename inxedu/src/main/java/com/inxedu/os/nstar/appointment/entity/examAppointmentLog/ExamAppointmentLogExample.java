package com.inxedu.os.nstar.appointment.entity.examAppointmentLog;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ExamAppointmentLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamAppointmentLogExample() {oredCriteria = new ArrayList<Criteria>();
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

        public Criteria andEventDateIsNull() {
            addCriterion("event_date is null");
            return (Criteria) this;
        }

        public Criteria andEventDateIsNotNull() {
            addCriterion("event_date is not null");
            return (Criteria) this;
        }

        public Criteria andEventDateEqualTo(String value) {
            addCriterion("event_date =", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateNotEqualTo(String value) {
            addCriterion("event_date <>", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateGreaterThan(String value) {
            addCriterion("event_date >", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateGreaterThanOrEqualTo(String value) {
            addCriterion("event_date >=", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateLessThan(String value) {
            addCriterion("event_date <", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateLessThanOrEqualTo(String value) {
            addCriterion("event_date <=", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateLike(String value) {
            addCriterion("event_date like", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateNotLike(String value) {
            addCriterion("event_date not like", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateIn(List<String> values) {
            addCriterion("event_date in", values, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateNotIn(List<String> values) {
            addCriterion("event_date not in", values, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateBetween(String value1, String value2) {
            addCriterion("event_date between", value1, value2, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateNotBetween(String value1, String value2) {
            addCriterion("event_date not between", value1, value2, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventManIsNull() {
            addCriterion("event_man is null");
            return (Criteria) this;
        }

        public Criteria andEventManIsNotNull() {
            addCriterion("event_man is not null");
            return (Criteria) this;
        }

        public Criteria andEventManEqualTo(String value) {
            addCriterion("event_man =", value, "eventMan");
            return (Criteria) this;
        }

        public Criteria andEventManNotEqualTo(String value) {
            addCriterion("event_man <>", value, "eventMan");
            return (Criteria) this;
        }

        public Criteria andEventManGreaterThan(String value) {
            addCriterion("event_man >", value, "eventMan");
            return (Criteria) this;
        }

        public Criteria andEventManGreaterThanOrEqualTo(String value) {
            addCriterion("event_man >=", value, "eventMan");
            return (Criteria) this;
        }

        public Criteria andEventManLessThan(String value) {
            addCriterion("event_man <", value, "eventMan");
            return (Criteria) this;
        }

        public Criteria andEventManLessThanOrEqualTo(String value) {
            addCriterion("event_man <=", value, "eventMan");
            return (Criteria) this;
        }

        public Criteria andEventManLike(String value) {
            addCriterion("event_man like", value, "eventMan");
            return (Criteria) this;
        }

        public Criteria andEventManNotLike(String value) {
            addCriterion("event_man not like", value, "eventMan");
            return (Criteria) this;
        }

        public Criteria andEventManIn(List<String> values) {
            addCriterion("event_man in", values, "eventMan");
            return (Criteria) this;
        }

        public Criteria andEventManNotIn(List<String> values) {
            addCriterion("event_man not in", values, "eventMan");
            return (Criteria) this;
        }

        public Criteria andEventManBetween(String value1, String value2) {
            addCriterion("event_man between", value1, value2, "eventMan");
            return (Criteria) this;
        }

        public Criteria andEventManNotBetween(String value1, String value2) {
            addCriterion("event_man not between", value1, value2, "eventMan");
            return (Criteria) this;
        }

        public Criteria andEventsIsNull() {
            addCriterion("events is null");
            return (Criteria) this;
        }

        public Criteria andEventsIsNotNull() {
            addCriterion("events is not null");
            return (Criteria) this;
        }

        public Criteria andEventsEqualTo(String value) {
            addCriterion("events =", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsNotEqualTo(String value) {
            addCriterion("events <>", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsGreaterThan(String value) {
            addCriterion("events >", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsGreaterThanOrEqualTo(String value) {
            addCriterion("events >=", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsLessThan(String value) {
            addCriterion("events <", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsLessThanOrEqualTo(String value) {
            addCriterion("events <=", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsLike(String value) {
            addCriterion("events like", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsNotLike(String value) {
            addCriterion("events not like", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsIn(List<String> values) {
            addCriterion("events in", values, "events");
            return (Criteria) this;
        }

        public Criteria andEventsNotIn(List<String> values) {
            addCriterion("events not in", values, "events");
            return (Criteria) this;
        }

        public Criteria andEventsBetween(String value1, String value2) {
            addCriterion("events between", value1, value2, "events");
            return (Criteria) this;
        }

        public Criteria andEventsNotBetween(String value1, String value2) {
            addCriterion("events not between", value1, value2, "events");
            return (Criteria) this;
        }

        public Criteria andObjectIsNull() {
            addCriterion("object is null");
            return (Criteria) this;
        }

        public Criteria andObjectIsNotNull() {
            addCriterion("object is not null");
            return (Criteria) this;
        }

        public Criteria andObjectEqualTo(String value) {
            addCriterion("object =", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectNotEqualTo(String value) {
            addCriterion("object <>", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectGreaterThan(String value) {
            addCriterion("object >", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectGreaterThanOrEqualTo(String value) {
            addCriterion("object >=", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectLessThan(String value) {
            addCriterion("object <", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectLessThanOrEqualTo(String value) {
            addCriterion("object <=", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectLike(String value) {
            addCriterion("object like", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectNotLike(String value) {
            addCriterion("object not like", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectIn(List<String> values) {
            addCriterion("object in", values, "object");
            return (Criteria) this;
        }

        public Criteria andObjectNotIn(List<String> values) {
            addCriterion("object not in", values, "object");
            return (Criteria) this;
        }

        public Criteria andObjectBetween(String value1, String value2) {
            addCriterion("object between", value1, value2, "object");
            return (Criteria) this;
        }

        public Criteria andObjectNotBetween(String value1, String value2) {
            addCriterion("object not between", value1, value2, "object");
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
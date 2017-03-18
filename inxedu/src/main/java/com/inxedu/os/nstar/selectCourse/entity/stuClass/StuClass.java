package com.inxedu.os.nstar.selectCourse.entity.stuClass;

import lombok.Data;

@Data
public class StuClass implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String className; // 班级名称
	private String description; // 说明
    private String seletableCourseId;//可选择的课id,
	private Integer schoolId; // 所属学院
}

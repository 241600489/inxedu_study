package com.inxedu.os.nstar.selectCourse.entity.college;
import lombok.Data;

/**
 * 实体类
 * @author ASUS
 *
 */
@Data
public class College implements java.io.Serializable{
	private static final long serialVersionUID = 1l;
	private Long id;
	private String collegeCode;	//学院代码
	private String collegeName;	//学院名称
	private String description;	//描述说明，学院简介

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("College{");
		sb.append("id=").append(id);
		sb.append(", collegeCode='").append(collegeCode).append('\'');
		sb.append(", collegeName='").append(collegeName).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append('}');
		return sb.toString();
	}
}

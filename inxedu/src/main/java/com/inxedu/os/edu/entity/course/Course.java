package com.inxedu.os.edu.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author www.inxedu.com
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Course implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;


    private int courseId; // 与 courseBase关联
    private String courseNo; //课程号 与courseBase冗余
    private String courseName; //课程名称 与courseBase冗余

    private int isavaliable;//1 正常　２　下架   3删除
    private int subjectId;//课程专业ID
    private String subjectLink;//课程专业链
    private Date addTime;//课程添加时间
    private java.math.BigDecimal sourcePrice;//课程原价格（只显示）
    private java.math.BigDecimal currentPrice;//课程销售价格（实际支付价格）设置为0则可免费观看
    private String title;//课程简介
    private String context;//课程详情
    private int lessionNum;//课时
    private String logo;//课程图片
    private Date updateTime;
    private int pageBuycount;//销售数量
    private int pageViewcount;//浏览数量
    private Date endTime;//有效结束时间
    private int loseType;//有效期类型，0：到期时间，1：按天数
    private String loseTime;//有效期:商品订单过期时间点

    private String studyPercent;//课程学习进度百分比




    /////////////////////////////////////////////////////////////////////
    //private String  courseNo; //课程号
    private String teacherName;//用外建取
    private String jobTitle;//职称

    private String openSchool;// 开课院系

    private String property;// 课程属性
    // 考试类型
    // 班级
    private Integer maxNumber;// 课容量 最大选课人数
    // 总学时
    // 周学时
    // 授课学时
    // 上机学时
    // 实验学时
    private float score;// 学分

    private Integer week1;// 星期一
    private Integer week2;// 星期二
    private Integer week3;// 星期三
    private Integer week4;// 星期四
    private Integer week5;// 星期五
    private Integer week6;// 星期六
    private Integer week7;// 星期日
    // 校区
    private String classroom;// 上课地点
    private String weekIds;// 上课周次
    private Integer residaulCount;// 剩余可选课人数,默认为空

    private Integer labCourseId;// 实验课
    private boolean type;// 课程类型  实验为0  理论为1
    private boolean hasSelectCount; // 是否记录课堂容量 1 记录  0 或null 无记录
    private int teacherId;
    private String courseNumber;//课序号
    private String labCourseNumber;//实验课序号

  public String  getSelectCount(){
      try {
          return String.valueOf(maxNumber - residaulCount);
      } catch (Throwable e) {
          return "0";
      }
    }
    private String memo;
}

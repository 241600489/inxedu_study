<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程修改</title>
<script type="text/javascript" src="${ctx}/static/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/multilevel.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
<link rel="stylesheet" href="${ctx}/kindeditor/themes/default/default.css"/>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js"></script>
<script type="text/javascript" src="${ctximg}/static/admin/course/course.js"></script>
<script type="text/javascript" src="${ctximg}/static/admin/teacher/select_teacher_list.js"></script>

<link rel="stylesheet" href="${ctx}/static/common/nice-validator/jquery.validator.css"/>
<script type="text/javascript" src="${ctx}/static/common/nice-validator/jquery.validator.js"></script>
<script type="text/javascript" src="${ctx}/static/common/nice-validator/local/zh-CN.js"></script>

<%--ue编辑器--%>
<script type="text/javascript" charset="utf-8" src="${ctx}/static/common/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/static/common/ueditor/ueditor.all.js"></script>

<script type="text/javascript">
    subjectList='${subjectList}';
    $(function(){
    	var param={
    			data:eval('('+subjectList+')'),	//处理的数据（必选）数据格式：[{object Object},{object Object}] 
    			showId:'levelId',//显示的数据标签ID（必选）
    			idKey:'subjectId',//数据的ID（必选）
    			pidKey:'parentId',//数据的父ID（必选）
    			nameKey:'subjectName',//数据显示的名（必选）
    			returnElement:'returnId',//返回选中的值（必选 ）
    			//-----------------------------------------------------
    			returnIds:'returnIds',//返回所有级的ID，以“,”隔开（可选）
    			initVal:'${course.subjectId}',//初始默认ID（可选）
    			defName:'请选择',//默认显示的选项名（可选，如果不设置默认显示“请选择”）  
    			defValue:'0'//默认的选项值（可选，如果不设置默认是“0”）
    		};
    	ML._init(param);
    	addTeahcerList(eval('('+'${teacherList}'+')'));
    });
    </script>
</head>
<body>
	<div class="pad20" style="background-color: #f0f0f0;">
		<form action="${ctx}/admin/cou/updateCourse" method="post" id="saveCourseForm" data-validator-option="{stopOnError:false, timely:false}">
			<input type="hidden" name="course.courseId" value="${course.courseId}" />
			<input type="hidden" name="course.logo" value="${course.logo}" />
			<table style="line-height: 35px;">
				<tr>
					<td>
						<font color="red">*</font>课程名称:
					</td>
					<td>
						<select id="courseName" style="width: 300px" name="course.courseName">
							<option value="">==请选择==</option>
							<c:forEach items="${courseNameList}" var="Name">
							<option value="${Name.courseName}" <c:if test="${Name.courseName==course.courseName}">selected</c:if> >${Name.courseName}</option>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>专业分类:
					</td>
					<td style="text-align: left;">
						<input type="hidden" value="${course.subjectId}" id="returnId" name="course.subjectId" />
						<input type="hidden" id="returnIds" value="${course.subjectLink}" name="course.subjectLink" />
						<div id="levelId"></div>
					</td>

				</tr>
				<tr>
					<td>
						<font color="red">*</font>状态:
					</td>
					<td>
						<select id="isavaliable" class="valid" name="course.isavaliable">
							<option value="1">上架</option>
							<option value="2">下架</option>
						</select>
					</td>
					<script>
						$("#isavaliable").val('${course.isavaliable}');
					</script>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>总课时:
					</td>
					<td style="text-align: left;">
						<input name="course.lessionNum" value="${course.lessionNum}" type="text" style="width: 140px;" data-rule="required;integer[+0]"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>课程原价格:
					</td>
					<td style="text-align: left;">
						<input name="course.sourcePrice" type="text" value="${course.sourcePrice}" style="width: 140px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>课程销售价格:
					</td>
					<td style="text-align: left;">
						<input name="course.currentPrice" type="text" value="${course.currentPrice}" style="width: 140px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>有效期类型:
					</td>
					<td>
						<select id="losetype" name="course.loseType">
							<option value="0">到期时间</option>
							<option value="1">按天数</option>
						</select>
					</td>
					<script>
							$("#losetype").val('${course.loseType}');
					</script>
				</tr>
				<tr class="endTimeShow">
					<td>
						<font color="red">*</font>有效期结束时间:
					</td>
					<td style="text-align: left;">
						<input name="course.endTime" value="<fmt:formatDate value="${course.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly"
							id="endTime" type="text" style="width: 140px;"/>
					</td>
				</tr>
				<tr class="loseTimeShow" style="display: none;">
					<td align="center">
						<font color="red">*</font>按天数:
					</td>
					<td>
						<input id="loseTime" class="required number" type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" value="${course.loseTime }" name="course.loseTime" style="width: 140px;"/>天
					</td>
				</tr>
				<tr>
					<td>添加教师:</td>
					<td style="text-align: left;">
						<input type="hidden" name="teacherIdArr" value="" />
						<div id="teacherList"></div>
						<a href="javascript:void(0)" onclick="selectTeacher()">选择老师</a>
					</td>
				</tr>
				<tr>
					<td>销售数量:</td>
					<td style="text-align: left;">
						<input name="course.pageBuycount" data-rule="required;integer[+0]" value="${course.pageBuycount}" type="text" style="width: 140px;" />
					</td>
				</tr>
				<tr>
					<td>浏览量:</td>
					<td style="text-align: left;">
						<input name="course.pageViewcount" data-rule="required;integer[+0]" value="${course.pageViewcount}" type="text" style="width: 140px;" />
					</td>
				</tr>
				<tr>
					<td>课程简介:</td>
					<td style="text-align: left;">
						<input name="course.title" data-rule="required;" value="${course.title}" type="text" style="width: 580px;" />
					</td>
				</tr>
				<tr>
					<td>课程图片：</td>
					<td style="text-align: left;">
						<c:choose>
							<c:when test="${course.logo!=null && course.logo!=''}">
								<img id="showImage" width="278px" height="155" src="<%=staticImage%>${course.logo}" />
							</c:when>
							<c:otherwise>
								<img id="showImage" width="278px" height="155" src="${ctx }/static/admin/assets/logo.png" />
							</c:otherwise>
						</c:choose>
						<input type="button" value="上传" id="imageFile" />
						<font color="red">(请上传 640*357(长X宽)像素 的图片)</font>
					</td>
				</tr>
				<tr>
					<td>课程详情:</td>
					<td style="text-align: left;">
						<textarea name="course.context" id="content" data-rule="required;">${course.context}</textarea>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>课程号:
					</td>
					<td>
						<select id="sss" style="width: 300px" name="course.courseNo" value="${course.courseNo}">
							<option value="">请选择</option>
							<c:forEach items="${courseNOlist}" var="Name">
							<option value="${Name}" <c:if test="${Name==course.courseNo}">selected</c:if> >${Name}</option>
							</c:forEach>
					</td>
					<script type="text/javascript">
						var cc=$("#sss");
					/*	alert(cc.val());
						if(cc.val()==${course.courseNo})
						{
                        cc.attr("selected",true);
						}*/
					alert($("#aaa option[value='${course.courseNo}']").val());
					</script>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>开课院系:
					</td>
					<td>
						<input name="course.openSchool" value="${course.openSchool}" type="text" style="width: 260px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>课程属性:
					</td>
					<td>
						<input name="course.property" value="${course.property}" type="text" style="width: 260px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>课容量:
					</td>
					<td>
						<input name="course.MaxNumber" value="${course.getMaxNumber()}" type="text" style="width: 200px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>学分:
					</td>
					<td>
						<input name="course.score" value="${course.score}" type="text" style="width: 200px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>周几周第几课:
					</td>
					<td>
						<table>
							<tr>
								<td>
									&nbsp;&nbsp;
								</td>
								<td>
									星期一
								</td>
								<td>
									星期二
								</td>
								<td>
									星期三
								</td>
								<td>
									星期四
								</td>
								<td>
									星期五
								</td>
								<td>
									星期六
								</td>
								<td>
									星期日
								</td>
							</tr>
							<tr>
								<td>
									第一大节
								</td>
								<td>
									<input name="course.week1" type="checkbox" ${course.week1==1?'checked':''} value="1"/>

								</td>
								<td>
									<input name="course.week2"   type="checkbox" ${course.week2==1?'checked':''} value="1"/>
								</td>
								<td>
									<input name="course.week3" type="checkbox" ${course.week3==1?'checked':''} value="1"/>
								</td>
								<td>
									<input name="course.week4" type="checkbox"  ${course.week4==1?'checked':''}  value="1"/>
								</td>
								<td>
									<input name="course.week5" type="checkbox" ${course.week5==1?'checked':''} value="1"/>
								</td>
								<td>
									<input name="course.week6" type="checkbox" ${course.week6==1?'checked':''} value="1"/>
								</td>
								<td>
									<input name="course.week7" type="checkbox" ${course.week7==1?'checked':''} value="1"/>
								</td>
							</tr>
							<tr>
								<td>
									第二大节
								</td>
								<td>

									<input name="course.week1" type="checkbox" ${course.week1==2?'checked':''} value="2"/>
								</td>
								<td>
									<input name="course.week2" type="checkbox" ${course.week2==2?'checked':''} value="2"/>
								</td>
								<td>
									<input name="course.week3" type="checkbox" ${course.week3==2?'checked':''} value="2"/>
								</td>
								<td>
									<input name="course.week4" type="checkbox"  ${course.week4==2?'checked':''} value="2"/>
								</td>
								<td>
									<input name="course.week5" type="checkbox" ${course.week5==2?'checked':''}  value="2"/>
								</td>
								<td>
									<input name="course.week6" type="checkbox" ${course.week6==2?'checked':''} value="2"/>
								</td>
								<td>
									<input name="course.week7" type="checkbox" ${course.week7==2?'checked':''} value="2"/>
								</td>
							</tr>
							<tr>
								<td>
									第三大节
								</td>
								<td>

									<input name="course.week1" type="checkbox" ${course.week1==3?'checked':''} value="3"/>
								</td>
								<td>
									<input name="course.week2" type="checkbox" ${course.week2==3?'checked':''}  value="3"/>
								</td>
								<td>
									<input name="course.week3" type="checkbox" ${course.week3==3?'checked':''} value="3"/>
								</td>
								<td>
									<input name="course.week4" type="checkbox" ${course.week4==3?'checked':''} value="3"/>
								</td>
								<td>
									<input name="course.week5" type="checkbox"  ${course.week5==3?'checked':''} value="3"/>
								</td>
								<td>
									<input name="course.week6" type="checkbox" ${course.week6==3?'checked':''} value="3"/>
								</td>
								<td>
									<input name="course.week7" type="checkbox" ${course.week7==3?'checked':''}  value="3"/>
								</td>
							</tr>
							<tr>
								<td>
									第四大节
								</td>
								<td>

									<input name="course.week1" type="checkbox" ${course.week1==4?'checked':''} value="4"/>
								</td>
								<td>
									<input name="course.week2" type="checkbox"  ${course.week2==4?'checked':''} value="4"/>
								</td>
								<td>
									<input name="course.week3" type="checkbox" ${course.week3==4?'checked':''} value="4"/>
								</td>
								<td>
									<input name="course.week4" type="checkbox" ${course.week4==4?'checked':''} value="4"/>
								</td>
								<td>
									<input name="course.week5" type="checkbox" ${course.week5==4?'checked':''} value="4"/>
								</td>
								<td>
									<input name="course.week6" type="checkbox" ${course.week6==4?'checked':''} value="4"/>
								</td>
								<td>
									<input name="course.week7" type="checkbox" ${course.week7==4?'checked':''} value="4"/>
								</td>
							</tr>
							<tr>
								<td>
									第五大节
								</td>
								<td>

									<input name="course.week1" type="checkbox" ${course.week1==5?'checked':''}  value="5"/>
								</td>
								<td>
									<input name="course.week2" type="checkbox" ${course.week2==5?'checked':''} value="5"/>
								</td>
								<td>
									<input name="course.week3" type="checkbox" ${course.week3==5?'checked':''} value="5"/>
								</td>
								<td>
									<input name="course.week4" type="checkbox" ${course.week4==5?'checked':''} value="5"/>
								</td>
								<td>
									<input name="course.week5" type="checkbox" ${course.week5==5?'checked':''} value="5"/>
								</td>
								<td>
									<input name="course.week6" type="checkbox" ${course.week6==5?'checked':''} value="5"/>
								</td>
								<td>
									<input name="course.week7" type="checkbox" ${course.week7==5?'checked':''}value="5"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>上课地点:
					</td>
					<td>
						<input name="course.classroom" value="${course.classroom}" type="text" style="width: 200px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>上课周次:
					</td>
					<td>
						<input name="course.weekIds" value="${course.weekIds}" type="text" style="width: 200px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>选课人数:
					</td>
					<td>
						<input name="course.selectCount" value="${course.selectCount}" type="text" style="width: 200px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>实验课:
					</td>
					<td>
						<input name="course.labCourseId" value="${course.labCourseId}"  type="text" style="width: 200px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>课程类型:
					</td>
					<td>
						<input name="course.type" value="${course.type}" type="text" style="width: 200px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>备注:
					</td>
					<td>
						<input name="course.memo" value="${course.memo}" type="text" style="width: 200px;" data-rule="required;"/>
					</td>
				</tr>


				<tr>
					<td colspan="2" align="center">
						<input onclick="saveCourse()" class="button" type="button" value="保存" />
						<input onclick="history.go(-1);" class="button" type="button" value="返回" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

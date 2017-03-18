<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程添加</title>
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

<%--验证框架 --%>
<link rel="stylesheet" href="${ctx}/static/common/nice-validator/jquery.validator.css"></link>
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
    			returnIds:'returnIds',
    			defName:'请选择',//默认显示的选项名（可选，如果不设置默认显示“请选择”）
    			defValue:'0'//默认的选项值（可选，如果不设置默认是“0”）
    		};
    	ML._init(param);
    });
    </script>
</head>
<body>
	<div class="pad20" style="background-color: #f0f0f0;">
		<form action="${ctx}/admin/cou/addCourse" method="post" id="saveCourseForm" data-validator-option="{stopOnError:false, timely:false}">
			<input type="hidden" name="course.logo" />
			<table style="line-height: 35px;">
				<tr>
					<td>
						<font color="red">*</font>课程名称:
					</td>
					<td>
				<select id="courseName" style="width: 300px" name="course.courseName">
					<option>==请选择==</option>
					<c:forEach items="${courseNameList}" var="Name">
						<option>${Name.courseName}</option>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>专业分类:
					</td>
					<td style="text-align: left;">
						<input type="hidden" value="0" id="returnId" name="course.subjectId" />
						<input type="hidden" id="returnIds" name="course.subjectLink" />
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
				</tr>
				<tr>
				<tr>
					<td>
						<font color="red">*</font>总课时:
					</td>
					<td style="text-align: left;">
						<input name="course.lessionNum" value="0" type="text" style="width: 140px;" data-rule="required;integer[+0]"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>课程原价格:
					</td>
					<td style="text-align: left;">
						<input name="course.sourcePrice" type="text" value="0" style="width: 140px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>课程销售价格:
					</td>
					<td style="text-align: left;">
						<input name="course.currentPrice" type="text" value="0" style="width: 140px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>有效期类型:
					</td>
					<td>
						<select id="losetype" class="valid" name="course.loseType">
							<option value="0">到期时间</option>
							<option value="1">按天数</option>
						</select>
					</td>
				</tr>
				<tr class="endTimeShow">
					<td>
						<font color="red">*</font>有效期结束时间:
					</td>
					<td style="text-align: left;">
						<input name="course.endTime" readonly="readonly" id="endTime" type="text" style="width: 140px;"/>
					</td>
				</tr>
				<tr class="loseTimeShow" style="display: none;">
					<td align="center">
						<font color="red">*</font>按天数:
					</td>
					<td>
						<input id="loseTime" class="required number" type="text" name="course.loseTime" onkeyup="this.value=this.value.replace(/\D/g,'')" style="width: 140px;"/>天
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
						<input name="course.pageBuycount" value="0" type="text" style="width: 140px;" data-rule="required;integer[+0]"/>
					</td>
				</tr>
				<tr>
					<td>浏览量:</td>
					<td style="text-align: left;">
						<input name="course.pageViewcount" value="0" type="text" style="width: 140px;" data-rule="required;integer[+0]"/>
					</td>
				</tr>
				<tr>
					<td>课程简介:</td>
					<td style="text-align: left;">
						<input name="course.title" type="text" style="width: 580px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>课程图片：</td>
					<td style="text-align: left;">
						<img id="showImage" width="278px" height="155" src="${ctx }/static/admin/assets/logo.png" />
						<input type="button" value="上传" id="imageFile" />
						<font color="red">(请上传 640*357(长X宽)像素 的图片)</font>
					</td>
				</tr>
				<tr>
					<td>课程详情:</td>
					<td style="text-align: left;">
						<textarea name="course.context" id="content" data-rule="required;"></textarea>
					</td>
				</tr>
				<tr>
				<td>
					<font color="red">*</font>课程号:

				</td>
				<td>
					<select id="sss" style="width: 300px" name="course.courseNo">
						<option>==请选择==</option>
						<c:forEach items="${courseNOlist}" var="Name">
						<option>${Name}</option>
						</c:forEach>
				</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>开课院系:
					</td>
					<td>
						<input name="course.openSchool" type="text" style="width: 260px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>课程属性:
					</td>
					<td>
						<input name="course.property" type="text" style="width: 260px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>课容量:
					</td>
					<td>
						<input name="course.MaxNumber" type="text" style="width: 200px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>学分:
					</td>
					<td>
						<input name="course.score" type="text" style="width: 200px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>周几第几课:
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
								<input name="course.week1" type="checkbox" value="1"/>
							</td>
							<td>
									<input name="course.week2" type="checkbox" value="1"/>
							</td>
							<td>
								<input name="course.week3" type="checkbox" value="1"/>
							</td>
							<td>
								<input name="course.week4" type="checkbox" value="1"/>
							</td>
							<td>
								<input name="course.week5" type="checkbox" value="1"/>
							</td>
							<td>
								<input name="course.week6" type="checkbox" value="1"/>
							</td>
							<td>
								<input name="course.week7" type="checkbox" value="1"/>
							</td>
						</tr>
						<tr>
							<td>
								第二大节
							</td>
							<td>

								<input name="course.week1" type="checkbox" value="2"/>
							</td>
							<td>
								<input name="course.week2" type="checkbox" value="2"/>
							</td>
							<td>
								<input name="course.week3" type="checkbox" value="2"/>
							</td>
							<td>
								<input name="course.week4" type="checkbox" value="2"/>
							</td>
							<td>
								<input name="course.week5" type="checkbox" value="2"/>
							</td>
							<td>
								<input name="course.week6" type="checkbox" value="2"/>
							</td>
							<td>
								<input name="course.week7" type="checkbox" value="2"/>
							</td>
						</tr>
						<tr>
							<td>
								第三大节
							</td>
							<td>

								<input name="course.week1" type="checkbox" value="3"/>
							</td>
							<td>
								<input name="course.week2" type="checkbox" value="3"/>
							</td>
							<td>
								<input name="course.week3" type="checkbox" value="3"/>
							</td>
							<td>
								<input name="course.week4" type="checkbox" value="3"/>
							</td>
							<td>
								<input name="course.week5" type="checkbox" value="3"/>
							</td>
							<td>
								<input name="course.week6" type="checkbox" value="3"/>
							</td>
							<td>
								<input name="course.week7" type="checkbox" value="3"/>
							</td>
						</tr>
						<tr>
							<td>
								第四大节
							</td>
							<td>

								<input name="course.week1" type="checkbox" value="4"/>
							</td>
							<td>
								<input name="course.week2" type="checkbox" value="4"/>
							</td>
							<td>
								<input name="course.week3" type="checkbox" value="4"/>
							</td>
							<td>
								<input name="course.week4" type="checkbox" value="4"/>
							</td>
							<td>
								<input name="course.week5" type="checkbox" value="4"/>
							</td>
							<td>
								<input name="course.week6" type="checkbox" value="4"/>
							</td>
							<td>
								<input name="course.week7" type="checkbox" value="4"/>
							</td>
						</tr>
						<tr>
							<td>
								第五大节
							</td>
							<td>

								<input name="course.week1" type="checkbox" value="5"/>
							</td>
							<td>
								<input name="course.week2" type="checkbox" value="5"/>
							</td>
							<td>
								<input name="course.week3" type="checkbox" value="5"/>
							</td>
							<td>
								<input name="course.week4" type="checkbox" value="5"/>
							</td>
							<td>
								<input name="course.week5" type="checkbox" value="5"/>
							</td>
							<td>
								<input name="course.week6" type="checkbox" value="5"/>
							</td>
							<td>
								<input name="course.week7" type="checkbox" value="5"/>
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
						<input name="course.classroom" type="text" style="width: 200px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>上课周次:
					</td>
					<td>
						<input name="course.weekIds" type="text" style="width: 200px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>选课人数:
					</td>
					<td>
						<input name="course.selectCount" type="text" style="width: 200px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>实验课:
					</td>
					<td>
						<input name="course.labCourseId" type="text" style="width: 200px;" data-rule="required;"/>
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>课程类型:
					</td>
					<td>
						<input name="course.type" type="text" style="width: 200px;" data-rule="required;"/>实验为0  理论为1
					</td>
				</tr>
				<tr>
					<td>
						<font color="red">*</font>备注:
					</td>
					<td>
						<input name="course.memo" type="text" style="width: 200px;" data-rule="required;"/>
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

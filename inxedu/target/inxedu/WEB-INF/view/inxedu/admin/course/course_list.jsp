<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程列表</title>
<script type="text/javascript" src="${ctx}/static/common/multilevel.js"></script>

<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
var subjectList = eval('('+'${subjectList}'+')');
$(function(){
	var param={ 
			data:subjectList,//处理的数据（必选）数据格式：[{object Object},{object Object}]  
			showId:'levelId',//显示的数据标签ID（必选）
			idKey:'subjectId',//数据的ID（必选）
			pidKey:'parentId',//数据的父ID（必选）
			nameKey:'subjectName',//数据显示的名（必选）
			returnElement:'subjectId',//返回选中的值（必选 ）
			//-----------------------------------------------------
			initVal:'${queryCourse.subjectId}',
			defName:'请选择',//默认显示的选项名（可选，如果不设置默认显示“请选择”）
			defValue:'0'//默认的选项值（可选，如果不设置默认是“0”）
		};
	ML._init(param);
	/* 时间控件 */
	$("#beginCreateTime,#endCreateTime").datetimepicker({
		regional:"zh-CN",
        changeMonth: true,
        dateFormat:"yy-mm-dd",
        timeFormat: "HH:mm:ss"
    });
});

/**
 * 删除课程 
 */
function avaliable(courseId,type,em){
	if(!confirm('确实要删除吗?')){
		return;
	}
	$.ajax({
		url:baselocation +'/admin/cou/avaliable/'+courseId+'/'+type,
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.success==false){
				alert(result.message);
			}else{
				location.reload();
			}
		},
		error:function(error){
			alert("系统繁忙，请稍后再操作！");
		}
	});
}
</script>
</head>
<body>
	<div class="pad20">
		<form action="${ctx}/admin/cou/list" method="post" id="searchForm">
			<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
			<input type="text" name="queryCourse.courseName" id="beginCreateTime" value="${queryCourse.courseName}" placeholder="课程标题" />
			<input type="hidden" id="subjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId}" />
			<input type="text" name="queryCourse.teacherName" value="${queryCourse.teacherName}" placeholder="老师名称" />
			专业:
			<samp id="levelId"></samp>
			<a title="查找课程" onclick="javascript:$('#searchForm').submit();" class="button tooltip" href="javascript:void(0)">
				<span class="ui-icon ui-icon-search"></span>
				查找课程
			</a>
			<a title="清空" onclick="javascript:$('#searchForm input:text').val('');$('#searchForm select').val(0);$('select').change();"
				class="button tooltip" href="javascript:void(0)">
				<span class="ui-icon ui-icon-cancel"></span>
				清空
			</a>
			<a title="创建课程" class="button tooltip" href="${ctx}/admin/cou/toAddCourse">
				<span></span>
				创建课程
			</a>
		</form>
		<table cellspacing="0" cellpadding="0" border="0" class="fullwidth">
			<thead>
				<tr>
					<td align="center" width="150px">课程名</td>
					<td align="center">状态</td>

					<td align="center">选课人数</td>
					<td align="center">课时</td>
					<td align="center">老师名称</td>

					<td align="center">上课地点</td>
					<td align="center">几周第几节</td>
					<td align="center">操作</td>

				</tr>
			</thead>

			<tbody>
				<c:forEach items="${courseList}" var="course">
					<tr class="odd">
						<td align="center">${course.courseName}</td>
						<td align="center">
							<c:if test="${course.isavaliable==1}">可选</c:if>
							<c:if test="${course.isavaliable==2}">不可选</c:if>
						</td>
						<td align="center">${course.maxNumber-course.residaulCount}</td>
						<td align="center">${course.lessionNum}</td>
						<td align="center">${course.teacherName}</td>
						<td align="center">${course.classroom}</td>
						<td align="center">
							<c:if test="${course.week1!=null}">
								周一 第${course.week1}大节
							</c:if>
							<c:if test="${course.week2!=null}">
								周二 第${course.week2}大节
							</c:if>
							<c:if test="${course.week3!=null}">
								周三 第${course.week3}大节
							</c:if>
							<c:if test="${course.week4!=null}">
								周四 第${course.week4}大节
							</c:if>
							<c:if test="${course.week5!=null}">
								周五 第${course.week5}大节
							</c:if>
							<c:if test="${course.week6!=null}">
								周六 第${course.week6}大节
							</c:if>
							<c:if test="${course.week7!=null}">
								周日 第${course.week7}大节
							</c:if>
						</td>
						<td align="center">
							<a href="${ctx}/admin/excelFile/exportSelectCourseResult/${course.courseId}" class="button tooltip" >导出选课结果</a>
							<a href="${ctx}/admin/kpoint/list/${course.courseId}" class="button tooltip">章节管理</a>
							<a href="${ctx}/admin/cou/initUpdate/${course.courseId}" class="button tooltip">修改</a>
							<a href="javascript:void(0)" onclick="avaliable(${course.courseId},3,this)" class="button tooltip">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
</body>
</html>
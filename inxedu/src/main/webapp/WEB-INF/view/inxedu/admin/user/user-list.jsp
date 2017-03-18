<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>学员列表</title>
<script type="text/javascript" src="${ctx}/static/admin/user/user.js"></script>
	<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
	<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
	<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
	<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
</head>

<body>
<div class="pad20">
	<form action="${ctx}/admin/user/getuserList" method="post" id="searchForm">
		<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
		<input type="hidden" name="queryUser.isavalible" value="1">
		所属学院:<select  style="width: 200px" name="schoolId" id="college">
		<option value="">==请选择==</option>
		<c:forEach items="${list1}" var="college">
			<option value="${college.id}" <c:if test="${college.id==collegeId1}">selected</c:if>>${college.collegeName}</option>
		</c:forEach>
	</select>
		所属班级:<select  style="width: 200px" name="queryUser.classId" id="class">
		<option value="">==请选择==</option>
	</select>
		<input placeholder="学号" type="text" name="queryUser.no" value="${no}"/>
		<a title="查找学员" onclick="javascript:$('#searchForm').submit();" class="button tooltip" href="javascript:void(0)">
			<span class="ui-icon ui-icon-search"></span>
			查找学员
		</a>
		<a title="清空" onclick="javascript:$('#searchForm input:text').val('');$('#searchForm select').val(0);" class="button tooltip"
		   href="javascript:void(0)">
			<span class="ui-icon ui-icon-cancel"></span>
			清空
		</a>
		<a title="导出Excel" onclick="userExcel()" class="button tooltip" href="javascript:void(0)">
			<span class="ui-icon ui-icon-cancel"></span>
			导出Excel
		</a>
	</form>
	<table cellspacing="0" cellpadding="0" border="0" class="fullwidth">
		<thead>
		<tr>
			<td align="center">学号</td>
			<td align="center">姓名</td>
			<td align="center">班级</td>
			<td align="center">性别</td>
			<td align="center">年龄</td>
			<td align="center" width="285">操作</td>
		</tr>
		</thead>

		<tbody>
		<c:forEach items="${userList}" var="user">
			<tr class="odd">
				<td align="center">${user.no}</td>
				<td align="center">${user.userName}</td>
				<td align="center">${user.classname}</td>

				<td align="center">
					<c:if test="${user.sex==0}">--</c:if>
					<c:if test="${user.sex==1}">男</c:if>
					<c:if test="${user.sex==2}">女</c:if>
				</td>
				<td align="center">${user.age}</td>

				<td align="center">
					<a href="javascript:void(0)" onclick="initUpdatePwd(${user.userId})" class="button tooltip">修改密码</a>
					<a href="javascript:void(0)" onclick="initPassword(${user.userId})" class="button tooltip">初始化密码</a>
					<a href="${ctx}/admin/user/uodateUserInfoPage/${user.userId}" class="button tooltip">修改信息</a>
					<samp id="frozenOrThaw${user.userId}">
						<c:if test="${user.isavalible==1}">
							<a href="javascript:void(0)" onclick="frozenOrThaw(${user.userId},2,this)" class="button tooltip">冻结</a>
						</c:if>
						<c:if test="${user.isavalible==2}">
							<a href="javascript:void(0)" onclick="frozenOrThaw(${user.userId},1,this)" class="button tooltip">解冻</a>
						</c:if>
					</samp>
					<a href="${ctx}/admin/user/lookuserlog/${user.userId}" class="button tooltip">查看日志</a>
					<a href="${ctx}/admin/user/updateStuClassPage/${user.userId}" class="button tooltip">修改班级</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</div>

<!-- 修改密码窗口 ,开始-->
<div id="updateWin" aria-labelledby="ui-dialog-title-dialog" role="dialog" tabindex="-1"
	 class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable"
	 style="display: none; position: absolute; overflow: hidden; z-index: 1010; outline: 0px none; height: auto; width: 511px; top: 173px; left: 367px;">
	<div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
		<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-dialog" class="ui-dialog-title">修改用户修改</span>
		<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all"
		   href="javascript:void(0)">
			<span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
		</a>
	</div>
	<div style="height: 300px; min-height: 42px; width: auto;" class="ui-dialog-content ui-widget-content">
		<form id="updateUserPwdForm">
			<input type="hidden" name="user.userId" value="0" />
			<table style="line-height: 35px;">
				<tr>
					<td>密码：</td>
					<td>
						<input name="user.password" type="password" />
					</td>
				</tr>
				<tr>
					<td>确定密码：</td>
					<td>
						<input name="passwords" type="password" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div style="-moz-user-select: none;" unselectable="on" class="ui-resizable-handle ui-resizable-n"></div>
	<div style="-moz-user-select: none;" unselectable="on" class="ui-resizable-handle ui-resizable-e"></div>
	<div style="-moz-user-select: none;" unselectable="on" class="ui-resizable-handle ui-resizable-s"></div>
	<div style="-moz-user-select: none;" unselectable="on" class="ui-resizable-handle ui-resizable-w"></div>
	<div unselectable="on" style="z-index: 1001; -moz-user-select: none;"
		 class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-icon-grip-diagonal-se"></div>
	<div unselectable="on" style="z-index: 1002; -moz-user-select: none;" class="ui-resizable-handle ui-resizable-sw"></div>
	<div unselectable="on" style="z-index: 1003; -moz-user-select: none;" class="ui-resizable-handle ui-resizable-ne"></div>
	<div unselectable="on" style="z-index: 1004; -moz-user-select: none;" class="ui-resizable-handle ui-resizable-nw"></div>
	<div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
		<button class="ui-state-default ui-corner-all" onclick="updateUserPwd()" type="button">确定</button>
		<button class="ui-state-default ui-corner-all closeBut" type="button">取消</button>
	</div>
</div>
<!-- 修改密码窗口 ,结束-->

<!-- 修改学员信息窗口 ,开始-->
<div id="updateUser" aria-labelledby="ui-dialog-title-dialog" role="dialog" tabindex="-1"
	 class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable"
	 style="display: none; position: absolute; overflow: hidden; z-index: 1010; outline: 0px none; height: auto; width: 511px; top: 173px; left: 367px;">
	<div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
		<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-dialog" class="ui-dialog-title">修改用户修改</span>
		<a style="-moz-user-select: none;" onclick="closea()" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all"
		   href="javascript:void(0)">
			<span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
		</a>
	</div>
	<div style="height: 300px; min-height: 42px; width: auto;" class="ui-dialog-content ui-widget-content">
		<form id="userInfo">
			<input type="hidden" name="user.userId" value="" />
			<table style="line-height: 35px;">
				<tr>
					<td>身份证号：</td>
					<td>
						<input name="user.idCard" type="text"/>
					</td>
				</tr>
				<tr>
					<td>学号：</td>
					<td>
						<input name="user.no" type="text" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div style="-moz-user-select: none;" unselectable="on" class="ui-resizable-handle ui-resizable-n"></div>
	<div style="-moz-user-select: none;" unselectable="on" class="ui-resizable-handle ui-resizable-e"></div>
	<div style="-moz-user-select: none;" unselectable="on" class="ui-resizable-handle ui-resizable-s"></div>
	<div style="-moz-user-select: none;" unselectable="on" class="ui-resizable-handle ui-resizable-w"></div>
	<div unselectable="on" style="z-index: 1001; -moz-user-select: none;"
		 class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-icon-grip-diagonal-se"></div>
	<div unselectable="on" style="z-index: 1002; -moz-user-select: none;" class="ui-resizable-handle ui-resizable-sw"></div>
	<div unselectable="on" style="z-index: 1003; -moz-user-select: none;" class="ui-resizable-handle ui-resizable-ne"></div>
	<div unselectable="on" style="z-index: 1004; -moz-user-select: none;" class="ui-resizable-handle ui-resizable-nw"></div>
	<div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
		<button class="ui-state-default ui-corner-all" onclick="updateUserDo()" type="button">确定</button>
		<button class="ui-state-default ui-corner-all closeBut" onclick="closea()" type="button">取消</button>
	</div>
</div>
<script type="application/javascript">
	function updateUser(userid){
		$.post(baselocation+"/uc/callbackInfo?userId="+userid,null,
				function (data, status) {
					var idCard = data.get("idCard");
					var no = data.get("no");
					$("#updateUser name=user.idCard").val(idCard);
					$("#updateUser name=user.no").val(no);
				});

		$("#updateUser").show();
		$("input[name='user.userId']").val(userid);
	}
	function initPassword(userid) {
		$.post(baselocation + "/admin/user/initPassword/" + userid, null,
				function(data,status){
					if (data == "success") {
						alert("初始化密码成功");
					} else if (data == "error") {
						alert("初始化密码失败");
					} else {
						alert("系统异常");
					}
				});
	}


	function updateUserDo(){

		var params = "";
		$("#updateUser input").each(function(){
			params+=$(this).serialize()+"&";
		});

		$.post(baselocation+"/uc/updateUserInfo",params,
				function(result){
					if("success"==result){
						$("#updateUser").hide();
						$("#updateUser name=user.idCard").val('');
						$("#updateUser name=user.no").val('');
						$("#updateUser name='[user.userId]'").val(0);
					}else{
						alert(result);
					}
				});
	}

	function closea(){
		$("#updateUser").hide();
		$("#updateUser name=user.idCard").val('');
		$("#updateUser name=user.no").val('');
		$("#updateUser name='[user.userId]'").val(0);
	}
</script>
<!-- 修改学员信息窗口 ,结束-->
<script type="text/javascript">
	//定位省份下拉框，同时添时内容改变事件
	$("#college").change( function(){
		//清空原班级下拉框中的内容，除第一项外
		$("#class option:gt(0)").remove();
		//获取选中的
		var collegeId = $("#college option:selected").val();
		//如果选中的不是""

		if(""!=collegeId){
			$.ajax( {
				type    : "POST",
				url     : "${ctx}/admin/humanselect/studentSelect1?time="+new Date().getTime(),
				data    : {"collegeId":collegeId},
				dataType:'json',
				success : function(data){
					/* alert(data!=null?"收到":"为收到");*/
					//alert(ajax.responseText);
					//解析json文本
					var size = data.length;
					for(var i=0;i<size;i++){
						var stuClass =data[i] ;
						var id=stuClass.id;
						var className=stuClass.className;
						var $option = $("<option value='"+id+"'>"+className+"</option>");
						$("#class").append($option);
					}
				}
			} );
		}
	} );
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>导入考生信息</title>
<SCRIPT language="javascript">
	function importExcel() {
		var upload = $("#file").val();
		var extension = upload.substr(upload.indexOf("."));
		if (upload == "") {
			alert("请选择需要上传的文件！");
			return false;
		}
		if (extension != ".xls") {
			alert("上传的文件后缀名必须是.xls格式！");
			return false;
		}
		$("form:first").submit();
	}
</SCRIPT>
</head>
<body>

	<form action="/admin/examExcel/importExamStudent" method="post">
	
	<input name="mark" value="1">

		<br>
		<table border="0" width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td><font face="宋体" size="2"><strong>Excel文件数据导入</strong></font>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="left" bgColor="#f5fafe" colspan="4"><font
					face="宋体" size="2"> 说明：只接受.xls扩展名的文件。 </font></td>
			</tr>
			<tr>
				<td width="1%" height=30></td>
				<td width="20%"></td>
				<td width="78%"></td>
				<td width="1%"></td>
			</tr>

			<tr>
				<td width="1%"></td>
				<td width="15%" align="center">请选择文件:</td>
				<td width="83%" align="left"><s:file name="file" id="file"
						cssStyle="width:365px"></s:file></td>
				<td width="1%"></td>
			</tr>
			<tr height=50>
				<td colspan=4></td>
			</tr>
			<tr height=2>
				<td colspan=4></td>
			</tr>
			<tr height=10>
				<td colspan=4></td>
			</tr>
			<tr>
				<td align="center" colspan=4><input type="button"
					name="BT_Submit" value="导入"
					style="font-size:12px; color:black; height=22;width=55"
					onClick="importExcel()"> <INPUT type="button" name="Reset1"
					id="save" value="关闭" onClick="window.close();"
					style="width: 60px; font-size:12px; color:black; height=22">
				</td>
			</tr>
		</table>



		<if test="#request.errorList!=null && #request.errorList.size()>0">
		<!-- 显示错误信息 -->
		<table border="0" width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td class="ta_01" align="center" }/images/b-info.gif" colspan="4">
					<font face="宋体" size="2"><strong>导入失败！错误信息！</strong></font>
				</td>
			</tr>
			<s:iterator value="#request.errorList">
				<tr>
					<td width="1%"></td>
					<td width="15%" align="center">错误信息:</td>
					<td width="83%" align="left"><font color="red"><s:property /></font>
					</td>
					<td width="1%"></td>
				</tr>
			</s:iterator>

		</table>
		</if>


	</form>











</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改前台主题色</title>
<link rel="stylesheet" type="text/css" href="${ctx }/static/common/bigcolorpicker/jquery.bigcolorpicker.css" />
<script type="text/javascript" src="${ctx }/static/common/bigcolorpicker/jquery.bigcolorpicker.js"></script>
<script>
	$(function() {
		$("#imageColor").bigColorpicker("imageColor", "L", 10);
	});
	function formSubmit(){
		$("#form").submit();
	}
</script>
</head>
<body>
	<div class="pad20" style="background-color: #f0f0f0;">
		<form action="${ctx}/admin/theme/update" method="post" id="form">
			<input type="hidden" name="teacher.picPath" id="imagesUrl" />
			<table>
				<tr>
					<td>
						<font color="red">*</font>前台主题色:
					</td>
					<td style="text-align: left;">
						<input type="text" id="imageColor" name="color" value=""/>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<a class="button tooltip" title="提 交" href="javascript:void(0)" onclick="formSubmit()">提 交</a>
						<a class="button tooltip" title="返 回" href="javascript:history.go(-1);">返 回</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

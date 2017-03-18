<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>Ehcache修改</title>
<script type="text/javascript">
function addEhcache(){
	var ehcacheKey = $("#ehcacheKey").val();
	var ehcacheDesc=$("#ehcacheDesc").val();
	var id='${websiteEhcache.id }';
	if(ehcacheKey.trim()==""){
		alert("Ehcache-key不能为空");
		return;
	}
	if(ehcacheDesc.trim()==""){
		alert("请输入Ehcache-key描述");
		return;
	}
	 var judge=confirm("确定修改？");
	 if(judge==true){
		 $.ajax({
				url:"${ctx}/admin/ehcache/updateWebsiteEhcache",
				data:{"websiteEhcache.ehcacheKey":ehcacheKey,"websiteEhcache.ehcacheDesc":ehcacheDesc,"websiteEhcache.id":id},
				dataType:"json",
				type:"post",
				cache:true,
				async:false,
				success:function(result){
					if(!result.success){
						alert(result.message);
					}else{
						window.location.href="${ctx}/admin/ehcache/queryWebsiteEhcacheList"
					}
				}
			});
	 }else{
		 return false;
	 }
}
</script>
</head>
<body >
	<div class="pad20" style="background-color: #f0f0f0;">
		<form action="${ctx}/admin/cou/addCourse" method="post" id="saveCourseForm" data-validator-option="{stopOnError:false, timely:false}">
			<table style="line-height: 35px;">
				<tr>
				<td align="center"><font color="red">*</font>&nbsp;Key：</td>
				<td align="center">
					<input type="text" value="${websiteEhcache.ehcacheKey }" id="ehcacheKey"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;描述：</td>
				<td align="center">
					<input type="text" value="${websiteEhcache.ehcacheDesc }" id="ehcacheDesc"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input onclick="addEhcache()" class="button" type="button" value="提交" />
					<input onclick="history.go(-1)" class="button" type="button" value="返回" />
				</td>
			</tr>
			</table>
		</form>
	</div>
</body>
</html>

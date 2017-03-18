<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ehcache管理</title>
<script type="text/javascript">
function selectEhcache(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
function cleanEmpty(ehcacheId){
	if(ehcacheId==0||ehcacheId==''){
		alert("该Key不存在");
		return;
	}
	var judge=confirm("确定清空缓存吗？");
	if(judge==true){
	$.ajax({
		url:"${ctx}/admin/ehcache/removeEHCache/"+ehcacheId,
		type:"post",
		dataType:"json",
		data:{},
		success:function(result){
			if(result.success){
				alert("清除成功");
				return;
			}else{
				alert("请刷新重试");
				return;
			}
		}
	});
	}
}
 function deladEhcache(id){
	var judge=confirm("确定删除吗？");
	if(judge==true){
		$.ajax({
			url:"${ctx}/admin/ehcache/delWebsiteEhcache/"+id,
			type:"post",
			dataType:"json",
			data:{},
			cache:true,
			async:false,
			success:function(result){
				if(result.success){
					alert("删除成功");
					$("#rem"+id).remove();
				}else{
					alert("请刷新重试");
					return;
				}
			}
		});
	}
}
function removeAllEHCache(){
	var judge=confirm("确定要清空所有吗？");
	if(judge==true){
		$.ajax({
			url:"${ctx}/admin/ehcache/removeAllEHCache",
			type:"post",
			dataType:"json",
			data:{},
			cache:true,
			async:false,
			success:function(result){
				if(result.success){
					window.location.href=parent.location.reload();
				}else{
					alert("请刷新重试");
					return;
				}
			}
		});
	}
}
</script>
</head>
<body>
	<div class="pad20">
		<form action="${ctx}/admin/ehcache/queryWebsiteEhcacheList" method="post" id="searchForm">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<span><font>Key：</font></span>
			<span><input type="text" name="websiteEhcache.ehcacheKey" id="name" value="${websiteEhcache.ehcacheKey }"/></span>
			<a title="查询" onclick="selectEhcache()" class="button tooltip" href="javascript:void(0)">
				<span class="ui-icon ui-icon-search"></span>
				查询
			</a>
			<a title="添加" class="button tooltip" href="${ctx}/admin/ehcache/toAdd">
				<span></span>
				添加
			</a>
			<a title="清空所有缓存" class="button tooltip" href="javascript:removeAllEHCache()">
				<span></span>
				清空所有缓存
			</a>
		</form>
		<table cellspacing="0" cellpadding="0" border="0" class="fullwidth">
			<thead>
				<tr>
					<td align="center">ID</td>
					<td align="center">Key</td>
					<td align="center">描述</td>
					<td align="center">操作</td>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${websiteEhcacheList}" var="ehcache">
					<tr id="rem${ehcache.id }">
						<td align="center">${ehcache.id}</td>
						<td align="center">${ehcache.ehcacheKey}</td>
						<td align="center">${ehcache.ehcacheDesc}</td>
						<td align="center" class="c_666 czBtn">
							<a class="button tooltip" onclick="cleanEmpty(${ehcache.id })" href="javascript:void(0)" title="清空">清空</a>
							<a class="button tooltip" href="${ctx }/admin/ehcache/updateWebsiteEhcachePage/${ehcache.id}" title="修改">修改</a>
							<a class="button tooltip" onclick="deladEhcache(${ehcache.id})" href="javascript:void(0)" title="删除">删除</a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${websiteEhcacheList.size()==0||websiteEhcacheList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
						<span>还没有缓存信息！</span>
						</div>
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
</body>
</html>
$(function(){
	$(".closeBut,.ui-dialog-titlebar-close").click(function(){
		winHide();
	});
	/**加载时间控件*/
	$("#beginCreateTime,#endCreateTime").datetimepicker({
		regional:"zh-CN",
        changeMonth: true,
        dateFormat:"yy-mm-dd",
        timeFormat: "HH:mm:ss"
    });
});

/**
 * 冻结或解冻用户
 * @param userId 用户ID
 * @param type 1解冻 2冻结
 * @param em
 */
function frozenOrThaw(userId,type,em){
	$.ajax({
		url:baselocation+'/admin/user/updateuserstate',
		type:'post',
		dataType:'json',
		data:{'user.userId':userId,'user.isavalible':type},
		success:function(result){
			if(result.success==true){
				var td = $(em).parent('samp').parent('td').parent('tr').children('td')[7];
				if(type==1){
					$(td).text('正常');
					$('#frozenOrThaw'+userId).html('<a class="button tooltip" onclick="frozenOrThaw('+userId+',2,this)" href="javascript:void(0)">冻结</a>');
				}else if(type==2){
					$(td).text('冻结');
					$('#frozenOrThaw'+userId).html('<a class="button tooltip" onclick="frozenOrThaw('+userId+',1,this)" href="javascript:void(0)">解冻</a>');
				}
			}else{
				alert(result.message);
			}
		},
		error:function(error){
			
		}
	});
}

/***
 * 初始化修改用户密码窗口
 * @param userId 用户ID
 */
function initUpdatePwd(userId){
	$("#updateWin").show();
	$("input[name='user.userId']").val(userId);
}



/**
 * 执行修改用户密码
 */
function updateUserPwd(){
	var params = ''
	$("#updateWin input").each(function(){
		params+=$(this).serialize()+"&";
	});
	$.ajax({
		url:baselocation+'/admin/user/updateUserPwd',
		type:'post',
		dataType:'json',
		data:params,
		success:function(result){
			if(result.success){
				winHide();
			}
			alert(result.message);
		},
		error:function(error){
			alert("系统繁忙，请稍后再操作！");
		}
	});
}
function winHide(){
	$("#updateWin").hide();
	$("input:password").val('');
	$("#updateWin name='[user.userId]'").val(0);
}

/**
 * 用户列表导出
 */
function userExcel(){
	$("#searchForm").prop("action","/admin/user/export");
	$("#searchForm").submit();
	$("#searchForm").prop("action","/admin/user/getuserList");
}

/***
 * 修改班级窗口
 * @param userId 用户ID
 */
function initUpdateClass(userId){
	$("#updateClass").show();
	$("input[name='user.userId']").val(userId);
}

/**
 * 执行修改班级窗口
 */
function updateUserClass(){
	var params = ''
	$("#updateClass input").each(function(){
		params+=$(this).serialize()+"&";
	});
	$.ajax({
		url:baselocation+'/admin/user/updateUserClass',
		type:'post',
		dataType:'json',
		data:params,
		success:function(result){
			if(result.success){
				winClass();
			}
			alert(result.message);
		},
		error:function(error){
			alert("系统繁忙，请稍后再操作！");
		}
	});
}

function winClass(){
	$("#updateClass").hide();
	$("input:stuClass").val('');
	$("#updateClass name='[user.userId]'").val(0);
}
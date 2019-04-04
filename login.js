//登录页面的

var logid=false;
var pwd=false;

$(document).ready(function(){
	
	//用户名
	$("input[name='logid']").blur(function(){
		if(/^[A-Za-z]\w{1,15}$/.test($(this).val())){
			logid=true;
			$(this).next("span").css({"color":"#00ff00"}).html("√");
		}
		else{
			logid=false;
			$(this).next("span").css({"color":"#ff0000"}).html("X");
		}
	});	
	
	//密码
	$("input[name='pwd']").blur(function(){
		if(/^\w{6,16}$/.test($(this).val())){
			pwd=true;
			$(this).next("span").css({"color":"#00ff00"}).html("√");
		}
		else{
			pwd=false;
			$(this).next("span").css({"color":"#ff0000"}).html("X");
		}
	});
	
	//重置
	$("input[name='res']").click(function(){
		window.location.href="login.jsp";
	});
	
	//提交
	$("input[name='sub']").click(function(){
		
		if(logid && pwd){
			//跳转的是 servlet
			$("form").attr("action","login").submit();
		}
		
	});
	
});

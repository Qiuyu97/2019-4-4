var logid=false;
var pwd=false;
var rpwd=false;
var yzm=false;


//窗体加载准备完毕
$(document).ready(function(){

	//用户名
	$("input[name='logid']").blur(function(){
		//正则
		if(/^[A-Za-z]\w{2,15}$/.test($(this).val())){
				//jquery为了方便，直接提供了两个简单的ajax操作
				//默认：异步传输
				//尽量在实际开发中不要使用，局限性太大。
				
				//get请求
				//$.get("请求路径","发送数据",function(响应结果){/*处理函数*/});
				//post请求
				//$.post("请求路径","发送数据",function(响应结果){/*处理函数*/});
				/*		
				$.post("rname","logid="+$(this).val(),function(data){
					//能够进来，就只负责结果的处理
					if(data=="1"){
						$("input[name='logid']").next("span").css({"color":"#ff0000"}).html("重名");
					}
					else{
						$("input[name='logid']").next("span").css({"color":"#00ff00"}).html("对");
					}
				});
				*/
				
				
				//======================================================
				//使用jquery提供的ajax方法，可以自定义所有参数
				//	提供的是一个json格式的信息定义
				$.ajax({
					type:"post",
					url:"rname",
					data:"logid="+$(this).val(),
					success:function(data){
						//能够进来，就只负责结果的处理
						if(data=="1"){
							logid=false;
							$("input[name='logid']").next("span").css({"color":"#ff0000"}).html("重名");
						}
						else{
							logid=true
							$("input[name='logid']").next("span").css({"color":"#00ff00"}).html("对");
						}
					}
				});
		}
		else{
			logid=false;
			$("input[name='logid']").next("span").css({"color":"#ff0000"}).html("格式错误！");
		}
	});
	
	
	
	
	//验证码
	$("input[name='yzm']").blur(function(){
		yzm=true;		
		$(this).next("img").next("span").css({"color":"#00ff00"}).html("对");
	});
	
	
	
	
	
});

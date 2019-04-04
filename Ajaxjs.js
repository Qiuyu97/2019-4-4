//【第一步：】
//	为当前浏览器创建Ajax请求对象
var xmlhttp;
//判断：尝试从 窗体对象中，调用ajax请求类对象
if(window.XMLHttpRequest){
	//可以调用，是高版本浏览器，已经集成了类对象，直接实例化类
	xmlhttp = new XMLHttpRequest();
}
else{
	//没有，是早期的浏览器，没有ajax类，实例化应用程序插件，提供字符串声明
	xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
}



//当窗体加载完毕
window.onload=function(){	
	//事件
	document.getElementById("logid").onblur=function(){
		
		//获取填写的内容
		var str = document.getElementById("logid").value;
		
		//进行正则判断，只有正确了才能进行ajax，这里表示正确
		
		
		//【第二步：】：打开ajax请求
		//	.open("请求方式","请求路径",是否同步请求)
		// 请求方式：get 或 post
		// 请求路径：一个具体的jsp或servlet，（请求的网页，任何输出的内容，都会变成响应结果）
		// 是否同步： true 或  false
		//		true：同步：发送数据后，每一步操作，js脚本都会监管，必须要有2，3，中间任何一个部分出问题，都不会执行还有面
		//		false：异步：发送数据后，服务器的接收等操作，与这里js的分开的，
		xmlhttp.open("post","rname",false);
		
		
		//【第三步】：设置请求的头部信息
		//	设置如果发送数据，那么服务器应该如何接受
		//（"内容-类型","按照应用程序/xml格式-万维网的形式-表单的方式-路径密码的格式"）
		xmlhttp.setRequestHeader("content-type","application/x-www-form-urlencoded");
		
		
		//【第四步】：设置回调函数
		//	定义如果服务器响应回发结果后，哪一个方法进行结果的处理
		//	两种方式：匿名函数 或 方法委托
		xmlhttp.onreadystatechange=chuli;
		
		
		//【第五步】：发送数据
		//数据发送只能是一个字符串
		//它允许进行类似路径组合的方式，进行数据传输
		//	如：("id=5&name=张三&age=18&...........")
		xmlhttp.send("logid="+str);
		
	};	
};


//回调函数
function chuli(){
	
	//状态：0-4，五种
	//0：对象初始化成功：new的时候
	//1：请求已发送
	if(xmlhttp.readyState == 1){
		alert("1:请求已发送");
	}
	//2：请求已接收
	if(xmlhttp.readyState == 2){
		alert("2：请求已接收");
	}
	//3：数据处理中.......
	if(xmlhttp.readyState == 3){
		alert("3：数据处理中......");
	}
	//4：服务器响应了，且回发结果String内容了	
	//【第六步】：判断是否响应且回发了数据
	if(xmlhttp.readyState == 4){
		
		//【第七步：】请求的ajax处理页面，输出的任何内容，都见变成一个String获取到
		var str = xmlhttp.responseText;
		
		//【第八步】：js自定义处理
		if(str=="1"){
			document.getElementById("sp_logid").innerHTML="重名";
			document.getElementById("sp_logid").style.color="#ff0000";
		}
		else{
			document.getElementById("sp_logid").innerHTML="√";
			document.getElementById("sp_logid").style.color="#00ff00";
		}
		
	}	
}


// 必须知道 ajax执行的原理和顺序。

// 状态有几个，都是什么

// 分清同步异步的区别【面试题】








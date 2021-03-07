<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

	<script>
		$(function () {

			//页面加载后，将用户框的值清空
			$("#loginAct").val("");

			//在页面加载完毕后，让用户的文本框自动获得焦点
			$("#loginAct").focus();


			//	为登录按钮绑定事件，执行登录操作
			$("#submitBtn").click(function () {
				login();
			})

			//为当前整个窗口绑定敲键盘事件，实现敲回车也能实现
			$(window).keydown(function (event) {
				if (event.keyCode==13)
				login();
			})

		})

		//普通的自定义的function方法，一定要写在$(function(){})的外面
		function login() {
			//验证账号密码不能为空
			//取得账号密码
			//将文本中的空格去掉,使用$.trim(文本)
			var loginAct=$.trim($("#loginAct").val());
			var loginPwd=$.trim($("#pwd").val());

			if(loginAct=="" || loginPwd==""){
				$("#msg").html("账号密码不能为空");
			//如果账号密码为空，则需要及时强制终止该方法
				return false;
			}

			//去后台验证登录相关操作
			//ajax这一套的固定写法
			$.ajax({
				url:"settings/user/login.do",		//从web.xml复制过来的
				data:{
					"loginAct":loginAct,
					"loginPwd":loginPwd
				},
				type:"post",
				dataType:"json",
				sussess:function (data) {

					/*返回给前端的data
					data
						{"success":true/false 用来判断登陆成功还是失败
							"msg":"后端返回的登录失败，具体是哪里登录失败了"
						}

					* */
					if(data.success){
						//跳转到工作台的首页，登录进去了
						print(data);
						window.location.href="http://localhost:8080/crm/workbench/index.html";
					}
					else{
						$("#msg").html("data.msg");
					}

				}
			})


		}


	</script>


</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;昌</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.html" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" placeholder="用户名" id="loginAct">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" placeholder="密码" id="pwd">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						
							<span id="msg" style="color: red">123</span>
						
					</div>

					<%--按钮写在form表单中,默认的行为是submit
					我们一定要将按钮的类型设置为button
					按钮所触发的行为应该是自己手写js来决定	--%>
					<button type="button" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;" id="submitBtn">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>


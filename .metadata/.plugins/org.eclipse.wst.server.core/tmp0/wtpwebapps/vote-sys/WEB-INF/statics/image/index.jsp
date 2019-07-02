<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>图书管理系统</title>
  <%@include file="/WEB-INF/views/head.jsp" %>
</head>
<body>
<div class="page-header">
    <h1 class ="text-center">图书管理系统
      
    </h1>
</div>
  <%--登录--%>
  <div id="killPhoneModal" class="container" >
   <form role="form" class ="text-center" method="post" action="book/login">
	  <div class="form-group">
	    <label for="account" >账号：</label>
	    <input type="text" class ="text-center" style="width:30%" size="4" name="account" id="account" placeholder="请输入账号:Tel/Email">
	  </div>
	  <div class="form-group">
	    <label for="password">密码：</label>
	    <input type="text" class ="text-center" style="width:30%" size="4" name="password" id="password" placeholder="请输入密码">
	  </div>
       <button type="submit" class="btn btn-default"><a >登录</a></button>
       <button type="button" class="btn btn-default"><a href="/WEB-INF/views/register.jsp">注册</a></button>
   </form>
  </div>
</body>
<%--jQery文件,务必在bootstrap.min.js之前引入--%>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<%--使用CDN 获取公共js http://www.bootcdn.cn/--%>
<%--jQuery Cookie操作插件--%>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<%--jQuery countDown倒计时插件--%>
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>

<script src="/resource/script/seckill.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
    //使用EL表达式传入参数
    
  })
</script>
</html>
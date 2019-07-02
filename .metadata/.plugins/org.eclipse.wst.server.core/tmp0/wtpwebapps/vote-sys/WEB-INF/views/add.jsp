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
    <h2 class ="text-center" style="color: #5bc0de">图书管理系统
      
    </h2>
</div>
  <%--登录--%>
  <div id="killPhoneModal" class="container" >
   <form role="form" class ="text-center" method="post" action="/book/add">
       <div class="form-group">
           <label for="image" >图片：</label>
           <input type="file" class ="text-center" style="width:30%;display:inline-block" size="4" name="image" id="image" placeholder="上传图片">
       </div>
	   <div class="form-group">
	    <label for="name" >书名：</label>
	    <input type="text" class ="text-center" style="width:30%" size="4" name="name" id="name" placeholder="请输入书名">
	  </div>
       <div class="form-group">
           <label for="name" >库存：</label>
           <input type="text" class ="text-center" style="width:30%" size="4" name="number" id="number" placeholder="请输入库存">
       </div>
	  <div class="form-group">
	    <label for="name">描述：</label>
	    <input type="text" class ="text-center" style="width:30%" size="4" name="desc" id="desc" placeholder="请输入描述信息">
      </div>
       <button type="submit" class="btn btn-default" style="color: #fff;background-color: #5bc0de;border-color: #46b8da;"><a style="color: #ffffff">新增</a></button>
       <button type="button" class="btn btn-default" style="color: #fff;background-color: #5bc0de;border-color: #46b8da;"><a style="color: #ffffff" href="/book/1/list">返回</a></button>
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
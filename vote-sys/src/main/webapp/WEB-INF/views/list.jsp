<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>图书列表</title>
  <%@include file="head.jsp" %>
  <link rel="stylesheet" type="text/css" href="/js/jquery.page.css">
</head>
<body>
  <div class="container">
    <div class="panel panel-default">
      <div class="panel-heading text-center">
        <h2 style="color: #5bc0de">图书列表</h2>
      </div>
      <div class="panel-body">
        <div>
          <form class="bs-example bs-example-form" role="form" method="post" action="/book/1/list">
            <div class="input-group input-group-lg">
              <input type="text" class="form-control" placeholder="书名" name="name" id="name">
              <span class="input-group-addon" style="background-color: #5bc0de;"><a style="color: #fff">搜索</a></span>
            </div>
          </form>
        </div>
        <div>
        <a class="btn btn-info" href="/book/add" target="_blank">新增</a>
        </div>
        <div>
        <table class="table table-hover">
          <thead>
            <tr>
              <th>名称</th>
              <th>库存</th>
              <th>创建时间</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${list}" var="book">
            <tr>
              <td>${book.name}</td>
              <td>${book.number}</td>
              <td>
                <fmt:formatDate value="${book.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
              </td>
              <td style="width: 244px;"><a class="btn btn-info" href="/book/${book.id}/detial" target="_blank">详情</a>
                  <a class="btn btn-info" href="/book/add" target="_blank">增加</a>
                  <a class="btn btn-info" href="/book/${book.id}/update" target="_blank">修改</a>
                  <a class="btn btn-info" href="/book/${book.id}/delete" target="_blank">删除</a>
              </td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
          <div id="page"></div>
          <div>
      </div>
    </div>
  </div>



      <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
      <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
      <script src="/js/jquery.page.js"></script>

      <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
      <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
      <script type="text/javascript">
          $(function(){
              $("span").click(function(){
                  $("form").submit();
              });
              var pageNum=${number};
                  $("#page").Page({
                      totalPages: pageNum,//分页总数
                      liNums: pageNum,//分页的数字按钮数(建议取奇数)
                      activeClass: 'activP', //active 类样式定义
                      callBack : function(page){
                          console.log(page);
                          url = "/book/"+page+"/list";
                          window.location.href=url;
                      }
                  });
          })
      </script>
</body>
</html>
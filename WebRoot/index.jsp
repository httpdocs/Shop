<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <%@ include file="/public/head.jspf" %>
  
  </head>  
    
  <body>  
    <a href="${pageContext.request.contextPath }/category_update.action?id=2&&type=201788测试&&hot=false">访问update</a>  
    <a href="category_save.action?type=保存sadaf的&hot=true">测试ModelDriven</a>  
    <a href="category_queryJoinAccount.action">级联查询所有类别</a><br/>  
    <c:forEach items="${requestScope.categoryList }" var="category">  
        ${category.id } | ${category.type } | ${category.hot } <br/>  
    </c:forEach>  
      
    <c:forEach items="${sessionScope.categoryList }" var="category">  
        ${category.id } | ${category.type } | ${category.hot } <br/>  
    </c:forEach>  
      
    <c:forEach items="${applicationScope.categoryList }" var="category">  
        ${category.id } | ${category.type } | ${category.hot } <br/>  
    </c:forEach>  
  </body>  
</html>  
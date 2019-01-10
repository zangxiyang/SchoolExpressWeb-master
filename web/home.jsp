
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    int roleCode = 10086;
    //roleCode -> 0:管理员 1:用户 2:配送员
    if (!(boolean)session.getAttribute("isLogin")){
        response.sendRedirect("login.jsp");
        return;
    }

    if (session.getAttribute("roleCode") == null){
        response.sendRedirect("login.jsp");
        return;
    }else if (((int)session.getAttribute("roleCode")) == 0){
        roleCode = 0 ;
    }else if (((int)session.getAttribute("roleCode")) == 1){
        roleCode = 1 ;
    }else if (((int)session.getAttribute("roleCode")) == 2){
        roleCode = 2 ;
    }



    String pageCode = null;
    pageCode = request.getParameter("pageCode");
    if (pageCode != null){
            pageContext.setAttribute("pageCode",pageCode);
    }else {
        if (roleCode == 0){
            pageContext.setAttribute("pageCode","addRole");
        } else if (roleCode == 1 ){
            pageContext.setAttribute("pageCode","addOrder");
        }else{
            pageContext.setAttribute("pageCode","getOrder");
            }
        }

%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>大厅 - 校园速递 - 开启校园速递时代</title>
</head>
<body style="background: #eeeeee">
<%--引入头部和底部--%>
<c:choose>
    <c:when test="${roleCode == 0}">
        <jsp:include page="home_header_admin.html"/>
    </c:when>
    <c:when test="${roleCode == 1}">
        <jsp:include page="home_header_user.html"/>
    </c:when>
    <c:when test="${roleCode == 2}">
        <jsp:include page="home_header_Courier.html"/>
    </c:when>
</c:choose>
<%--引入侧边栏--%>
<c:choose>
    <c:when test="${roleCode == 0}">
        <jsp:include page="slide_admin.htm"/>
    </c:when>
    <c:when test="${roleCode == 1}">
        <jsp:include page="slide_user.htm"/>
    </c:when>
    <c:when test="${roleCode == 2}">
        <jsp:include page="slide_Courier.htm"/>
    </c:when>
</c:choose>
<%--引入主体内容--%>
<c:choose>
    <c:when test="${roleCode == 0}">
        <jsp:include page="slide_admin.htm"/>
    </c:when>
    <c:when test="${roleCode == 1}">
        <jsp:include page="slide_user.htm"/>
    </c:when>
    <c:when test="${roleCode == 2}">
        <jsp:include page="slide_Courier.htm"/>
    </c:when>
</c:choose>
<%--引入主体内容--%>
<c:choose>
    <c:when test="${pageCode.equals('addOrder')}">
        <%--用户组的下订单--%>
        <jsp:include page="addOrder.jsp"/>
    </c:when>
    <c:when test="${pageCode.equals('myOrder')}">
        <%--用户组的我的订单--%>
        <jsp:include page="myOrder.jsp"/>
    </c:when>
    <c:when test="${pageCode.equals('setInfo')}">
        <%--用户组的资料修改--%>
        <jsp:include page="setInfo.jsp"/>
    </c:when>
    <c:when test="${pageCode.equals('resetPwd')}">
        <%--用户组的密码修改--%>
        <jsp:include page="resetPwd.jsp"/>
    </c:when>
    <c:when test="${pageCode.equals('getOrder')}">
        <%--配送员的接订单--%>
        <jsp:include page="getOrder.jsp"/>
    </c:when>
    <c:when test="${pageCode.equals('myOrder_Courier')}">
        <%--配送员的我的订单--%>
        <jsp:include page="myOrder_Courier.jsp"/>
    </c:when>
    <c:when test="${pageCode.equals('setInfo_Courier')}">
        <%--配送员的修改资料--%>
        <jsp:include page="setInfo_Courier.jsp"/>
    </c:when>
    <c:when test="${pageCode.equals('resetPwd')}">
        <%--配送员的修改密码--%>
        <jsp:include page="resetPwd.jsp"/>
    </c:when>
    <c:when test="${pageCode.equals('addRole')}">
        <%--管理员的添加用户--%>
        <jsp:include page="addRole.htm"/>
    </c:when>
    <c:when test="${pageCode.equals('deleteRole')}">
        <%--管理员的删除用户--%>
        <jsp:include page="deleteRole.htm"/>
    </c:when>
</c:choose>
</body>
</html>

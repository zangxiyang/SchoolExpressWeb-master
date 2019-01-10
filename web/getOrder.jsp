<%@ page import="DAO.IOrderDao" %>
<%@ page import="DAO.UserDaoFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="Beans.TaskBean" %>
<%@ page import="DAO.IDetailDao" %>
<%@ page import="Beans.UserDetailBean" %>
<%@ page import="java.util.ArrayList" %>

<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    IOrderDao dao = UserDaoFactory.getOrderInstance();
    List<TaskBean> taskBeanList = dao.selectNotReceivedOrders();
    pageContext.setAttribute("taskList",taskBeanList);
    IDetailDao detailDao = UserDaoFactory.getDetailInstance();
%>

<style>
    .layui-body{
        top: 60px;
        bottom: 44px;
        padding: 15px;
    }

</style>
<div class="layui-body">
<div class="layui-card">
    <div class="layui-card-header">订单大厅</div>
    <div class="layui-card-body">
                <table class="layui-table" lay-size="lg">
                    <colgroup>
                        <col width="150">
                        <col width="130">
                        <col width="200">
                        <col width="200">
                        <col>
                        <col>
                        <col width="100">
                    </colgroup>
                    <thead>
                      <tr>
                          <th>订单编号</th>
                          <th>下单人</th>
                          <th>订单取件码</th>
                          <th>联系电话</th>
                          <th>订单取件地址</th>
                          <th>订单送达地址</th>
                          <th>操作</th>
                      </tr> 
                    </thead>
                    <tbody>
                    <c:forEach items="${taskList}" var="task" >
                        <tr>
                            <td>${task.taskId}</td>
                            <td>
                                <c:set var="getOrder_uid" value="${task.uid}"/>
                                <%
                                    int uid = (int) pageContext.getAttribute("getOrder_uid");
                                    UserDetailBean userDetailBean = detailDao.selectDetail(uid,0);
                                    String name = userDetailBean.getName();
                                %>
                                <%= name%>
                            </td>
                            <td>${task.pickUpCode}</td>
                            <td>${task.phone}</td>
                            <td>${task.pickupAddress}</td>
                            <td>${task.toAddress}</td>
                            <td>
                                <form method="post" action="getOrder">
                                    <button class="layui-btn layui-btn-danger" type="submit">接收</button>
                                    <input type="hidden" name="taskId" value="${task.taskId}">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                  </table>
                <div class="layui-form-item">
                        <blockquote class="layui-elem-quote">
                            尊敬的快递员，您好:<br>
                            接单前请注意以下事项。<br>
                            1、您的联系电话是否正确，以免出现顾客无法联系的情况。<br>
                            2、您是否能在规定时间送货上门。<br>
                            请确认无误后再接收订单，以免造成不必要的损失或麻烦。谢谢配合！<br>
                            ps:请注意出行安全，祝您出行顺利，生活愉快！<br>
                        </blockquote>
                </div>
        </div>
        <script>
            //Demo
            layui.use('form', function(){
            });

            <c:if test="${sessionScope.getOrderReturn == 0}">
                layui.use('layer',function () {
                    var layer = layui.layer;
                    layer.alert("接收成功,请尽快送达",{icon:1});
                });
                <%
                    session.setAttribute("getOrderReturn",null);
                %>
            </c:if>
            <c:if test="${sessionScope.getOrderReturn == 1}">
                layui.use('layer',function () {
                    var layer = layui.layer;
                    layer.alert("接收失败,未知错误",{icon:2});
                });
                <%
                    session.setAttribute("getOrderReturn",null);
                %>
            </c:if>
        </script>
    </div>
</div>
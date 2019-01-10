<%@ page import="DAO.IOrderDao" %>
<%@ page import="DAO.UserDaoFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="Beans.TaskBean" %>
<%@ page import="DAO.IDetailDao" %>
<%@ page import="Beans.UserDetailBean" %>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    IOrderDao orderDao = UserDaoFactory.getOrderInstance();
    int uid = (int)session.getAttribute("uid");
    List<TaskBean> taskBeanList_loading = orderDao.selectLoadingOrders(uid,1);
    List<TaskBean> taskBeanList_finished = orderDao.selectFinishedOrders(uid,1);
    List<TaskBean> taskBeanList_all = orderDao.selectOrders(uid,1);

    pageContext.setAttribute("taskList_loading",taskBeanList_loading);
    pageContext.setAttribute("taskList_finished",taskBeanList_finished);
    pageContext.setAttribute("taskList_all",taskBeanList_all);
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
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
      <ul class="layui-tab-title" style="padding: 4px;">
          <div class="layui-card-header">
          <li class="layui-this">正在进行的订单</li>
          <li>完成的订单</li>
          <li>所有订单</li>
          </div>
        </ul>
<div class="layui-tab-content">
      <div class="layui-tab-item layui-show">
              <div class="layui-card-body">

                              <table class="layui-table" lay-size="lg">
                                  <colgroup>
                                    <col width="150">
                                    <col width="200">
                                    <col>
                                      <col width="150">
                                    <col>
                                    <col width="100">
                                  </colgroup>
                                  <thead>
                                    <tr>
                                        <th>订单编号</th>
                                        <th>订单取件码</th>
                                        <th>下单人</th>
                                        <th>联系电话</th>
                                        <th>订单状态</th>
                                        <th>操作</th>
                                    </tr> 
                                  </thead>
                                  <tbody>

                                    <c:forEach items="${taskList_loading}" var="task_loading">
                                        <tr>
                                            <td>${task_loading.taskId}</td>
                                            <td>${task_loading.pickupAddress}</td>
                                            <td>
                                                <c:set var="loading_uid" value="${task_loading.uid}"/>
                                                <%
                                                    int loading_uid = pageContext.getAttributesScope("loading_uid");
                                                    UserDetailBean userDetailBean = detailDao.selectDetail(loading_uid,0);
                                                    String name = userDetailBean.getName();
                                                %>
                                                <%=name%>
                                            </td>
                                            <td>${task_loading.phone}</td>
                                            <td>
                                                <c:if test="${task_loading.taskStatus == 1}">
                                                    正在进行中
                                                </c:if>
                                                <c:if test="${task_loading.taskStatus != 1}">
                                                    未知错误
                                                </c:if>
                                            </td>
                                            <td>
                                                <form action="finishOrder" method="post">
                                                <input type="hidden" name="taskId" value="${task_loading.taskId}">
                                                <button class="layui-btn layui-btn-danger" lay-submit type="submit">完成</button>
                                                </form>
                                            </td>
                                        </tr>

                                    </c:forEach>
                                  </tbody>
                                </table>

              </div>
      </div>
      <div class="layui-tab-item">
          <div class="layui-card-body">
              <table class="layui-table" lay-size="lg">
                      <colgroup>
                        <col width="150">
                        <col width="200">
                        <col>
                        <col width="200">
                      </colgroup>
                      <thead>
                        <tr>
                          <th>订单编号</th>
                          <th>订单取件码</th>
                          <th>下单人</th>
                          <th>订单状态</th>
                        </tr> 
                      </thead>
                      <tbody>
                        <%--<tr>--%>
                          <%--<td>1</td>--%>
                          <%--<td>2016-11-29</td>--%>
                          <%--<td>邓某</td>--%>
                          <%--<td>已完成</td>--%>
                        <%--</tr>--%>
                        <c:forEach var="task_finish" items="${taskList_finished}">
                            <tr>
                                <td>${task_finish.taskId}</td>
                                <td>${task_finish.pickUpCode}</td>
                                <td>
                                    <c:set var="finished_uid" value="${task_finish.uid}"/>
                                    <%
                                        int finished_uid = pageContext.getAttributesScope("finished_uid");
                                        UserDetailBean userDetailBean = detailDao.selectDetail(finished_uid,0);
                                        String name = userDetailBean.getName();
                                    %>
                                    <%=name%>
                                </td>
                                <td>
                                    <c:if test="${task_finish.taskStatus == 2}">
                                        已完成
                                    </c:if>
                                    <c:if test="${task_finish.taskStatus != 2}">
                                        未知错误
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                      </tbody>
              </table>
          </div>
      </div>
      <div class="layui-tab-item">
          <div class="layui-card-body">
              <table class="layui-table" lay-size="lg">
                      <colgroup>
                        <col width="150">
                        <col width="200">
                        <col>
                        <col width="200">
                      </colgroup>
                      <thead>
                        <tr>
                          <th>订单编号</th>
                          <th>订单取件码</th>
                          <th>下单人</th>
                          <th>订单状态</th>
                        </tr> 
                      </thead>
                      <tbody>

                        <c:forEach items="${taskList_all}" var="task_all">
                            <tr>
                                <td>${task_all.taskId}</td>
                                <td>${task_all.pickUpCode}</td>
                                <td><c:set var="all_uid" value="${task_all.uid}"/>
                                    <%
                                        int all_uid = pageContext.getAttributesScope("all_uid");
                                        UserDetailBean userDetailBean = detailDao.selectDetail(all_uid, 0);
                                        String name = userDetailBean.getName();
                                    %>
                                    <%=name%>
                                </td>
                                <td>

                                    <c:if test="${task_all.taskStatus == 1}">
                                        正在进行
                                    </c:if>
                                    <c:if test="${task_all.taskStatus == 2}">
                                        已完成
                                    </c:if>
                                </td>
                            </tr>

                        </c:forEach>
                      </tbody>
              </table>
          </div>
      </div>
</div>
  
  </div> 
      <script>

          layui.use('form', function(){
              var form = layui.form;
          });
          layui.use('element', function(){
              var element = layui.element;

                  //…
          });
          <c:if test="${sessionScope.finishOrderReturn == 0}">
          layui.use('layer',function () {
              var layer = layui.layer;
              layer.alert("订单完成!",{icon:1});
          });
          <%
          session.setAttribute("finishOrderReturn",null);
          %>
          </c:if>
          <c:if test="${sessionScope.finishOrderReturn == 1}">
          layui.use('layer',function () {
              var layer = layui.layer;
              layer.alert("订单完成!",{icon:1});
          });
          <%
              session.setAttribute("finishOrderReturn",null);
          %>
          </c:if>
      </script>
  </div>
</div>
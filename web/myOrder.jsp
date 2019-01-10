<%@ page import="DAO.IOrderDao" %>
<%@ page import="DAO.UserDaoFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="Beans.TaskBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="DAO.IDetailDao" %>
<%@ page import="Beans.UserDetailBean" %>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //取得一个dao层对象
    IOrderDao dao = UserDaoFactory.getOrderInstance();
    List<TaskBean> loadingTaskList = new ArrayList<TaskBean>();
    List<TaskBean> finishedTaskList = new ArrayList<TaskBean>();
    List<TaskBean> allTaskList = null;
    int uid = (int)session.getAttribute("uid");
    loadingTaskList = dao.selectLoadingOrders(uid,0);
    finishedTaskList = dao.selectFinishedOrders(uid,0);
    allTaskList = dao.selectOrders(uid,0);
    pageContext.setAttribute("loadingTaskList",loadingTaskList);
    pageContext.setAttribute("finishedTaskList",finishedTaskList);
    pageContext.setAttribute("allTaskList",allTaskList);
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
                                      <col width="200">
                                    </colgroup>
                                    <thead>
                                      <tr>
                                          <th>订单编号</th>
                                          <th>订单取件码</th>
                                          <th>订单接收人</th>
                                          <th>联系电话</th>
                                          <th>订单状态</th>
                                          <th>操作</th>
                                      </tr> 
                                    </thead>
                                    <tbody>
                                      <c:forEach items="${loadingTaskList}" var="task" varStatus="status">
                                          <tr>
                                          <td>${task.taskId}</td>
                                          <td>${task.pickUpCode}</td>
                                          <td>
                                              <c:if test="${task.cid == 0}">
                                                  暂未被领取
                                              </c:if>
                                              <c:if test="${task.cid != 0}">
                                                  <c:set var="cid" value="${task.cid}"/>
                                                  <%
                                                      int cid = (int)pageContext.getAttribute("cid");
                                                      UserDetailBean detailBean = detailDao.selectDetail(cid , 1);
                                                      String courierName = detailBean.getName();
                                                  %>
                                                  <%=courierName%>
                                              </c:if>
                                          </td>
                                          <td>${task.phone}</td>
                                          <td>
                                              <c:if test="${task.taskStatus == 0}">
                                                  尚未被领取,请耐心等待
                                              </c:if>
                                              <c:if test="${task.taskStatus == 1}">
                                                  正在进行中
                                              </c:if>
                                          </td>
                                          <td>
                                              <form method="post" action="finishOrder">
                                              <div class="layui-btn-group">
                                                  <c:if test="${task.taskStatus == 1}">
                                                          <input type="hidden" name="taskId" value="${task.taskId}">
                                                          <button class="layui-btn layui-btn-normal" lay-submit type="submit">完成</button>
                                                  </c:if>
                                                  <c:if test="${task.taskStatus != 1}">
                                                      <div class="layui-btn layui-btn-disabled">完成</div>
                                                  </c:if>
                                                  <div class="layui-btn layui-btn-danger" id="cancel" onclick="cancel()">取消</div>
                                              </div>
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
                            <th>订单接收人</th>
                            <th>订单状态</th>
                          </tr> 
                        </thead>
                        <tbody>
                        <c:forEach items="${finishedTaskList}" var="finishedTask">
                            <tr>
                                <td>${finishedTask.taskId}</td>
                                <td>${finishedTask.pickUpCode}</td>
                                <td>
                                    <c:set var="finish_cid" value="${finishedTask.cid}"/>
                                    <%
                                        int cid = (int)pageContext.getAttribute("finish_cid");
                                        UserDetailBean detailBean = detailDao.selectDetail(cid , 1);
                                        String courierName = detailBean.getName();
                                    %>
                                    <%=courierName%>
                                </td>
                                <td>
                                    <c:if test="${finishedTask.taskStatus == 2}">
                                        已完成
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
                          <col width="250">
                        </colgroup>
                        <thead>
                          <tr>
                            <th>订单编号</th>
                            <th>订单取件码</th>
                            <th>订单接收人</th>
                            <th>订单状态</th>
                          </tr> 
                        </thead>
                        <tbody>

                          <c:forEach var="allTask" items="${allTaskList}">
                              <tr>
                                  <td>${allTask.taskId}</td>
                                  <td>${allTask.pickUpCode}</td>
                                  <td>
                                      <c:set var="all_cid" value="${allTask.cid}"/>
                                      <%
                                          int cid = (int)pageContext.getAttribute("all_cid");
                                          UserDetailBean detailBean = detailDao.selectDetail(cid , 1);
                                          String courierName = detailBean.getName();
                                      %>
                                      <%=courierName%>
                                  </td>
                                  <td>
                                      <c:if test="${allTask.taskStatus == 0}">
                                          尚未被领取,请耐心等待
                                      </c:if>
                                      <c:if test="${allTask.taskStatus == 1}">
                                          正在进行中
                                      </c:if>
                                      <c:if test="${allTask.taskStatus == 2}">
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

            function cancel() {
                layui.use('layer',function () {
                    var layer = layui.layer;
                    layer.alert("功能正在开发中!",{icon:2});
                });
            }
        </script>
    </div>
</div>
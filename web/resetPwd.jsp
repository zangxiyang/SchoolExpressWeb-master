<%@ page import="DAO.IDao" %>
<%@ page import="DAO.UserDaoFactory" %>
<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
        .layui-body{
            top: 60px;
            bottom: 44px;
            padding: 15px;
        }
    
    </style>
    <div class="layui-body">
    <div class="layui-card">
        <div class="layui-card-header">修改密码</div>
        <div class="layui-card-body">
                <form class="layui-form" action="resetPwd" method="post">
                    <div class="layui-form-item">
                        <label class="layui-form-label">当前密码:</label>
                        <div class="layui-input-inline">
                            <input type="password" name="nowPwd" required  lay-verify="required" placeholder="请输入当前密码" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-form-mid layui-word-aux">你当前正在使用的密码</div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">新的密码:</label>
                        <div class="layui-input-inline">
                            <input type="password" name="newPwd" required lay-verify="required" placeholder="请输入新设置的密码" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-form-mid layui-word-aux">新设置的密码</div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">重复密码:</label>
                        <div class="layui-input-inline">
                            <input type="password" name="reNewPwd" required lay-verify="required" placeholder="请重复输入新设置的密码" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-form-mid layui-word-aux">请重复输入</div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit type="submit">确认</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </form>
            </div>
            <script>
                //Demo
                layui.use('form', function(){
                    var form = layui.form;
    


                });
                <c:if test="${sessionScope.resetPwdReturn == 0}">
                    layui.use('layer',function () {
                        var layer = layui.layer;
                        layer.alert('新密码修改成功',{icon:1});
                    });
                    <%
                        session.setAttribute("resetPwdReturn",null);
                    %>
                </c:if>
                <c:if test="${sessionScope.resetPwdReturn == 1}">
                    layui.use('layer',function () {
                        var layer = layui.layer;
                        layer.alert('修改失败,请检查当前密码输入是否正确!',{icon:2});
                    });
                    <%
                        session.setAttribute("resetPwdReturn",null);
                    %>
                </c:if>
            </script>
        </div>
    </div>
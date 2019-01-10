<%@page pageEncoding="UTF-8" contentType="text/html;charset=utf-8" language="java" %>
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
    <div class="layui-card-header">修改资料</div>
    <div class="layui-card-body">
            <form class="layui-form" action="setInfo" method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label">姓名:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name" required  lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">你的姓名</div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">性别:</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="sex" value="男" title="男" checked>
                        <input type="radio" name="sex" value="女" title="女">
                      </div>
                    <div class="layui-form-mid layui-word-aux">你的性别</div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">联系电话:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="phone" required  lay-verify="required" placeholder="请输入联系电话" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">你的联系电话</div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input type="hidden" name="flag" value="1">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">修改</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
        <script>
            layui.use('form', function(){
            });
            <c:if test="${sessionScope.setInfoReturn == 0}">
            layui.use('layer',function () {
                var layer = layui.layer;
                layer.alert('信息修改成功',{icon:1});
            });
            <%
                session.setAttribute("setInfoReturn",null);
            %>
            </c:if>
            <c:if test="${sessionScope.setInfoReturn == 1}">
            layui.use('layer',function () {
                var layer = layui.layer;
                layer.alert('信息修改失败',{icon:2});
            });
            <%
                session.setAttribute("setInfoReturn",null);
            %>
            </c:if>
        </script>
    </div>
</div>
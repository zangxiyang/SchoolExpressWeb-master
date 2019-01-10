<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if (session.getAttribute("loginReturn") != null){
        if (((boolean)session.getAttribute("loginReturn")) == false){
            //登录失败为0
            pageContext.setAttribute("loginTip",0);
        }
    }
%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录 - 校园速递 感受不一样的速度</title>
    <!-- 引入Layui框架 -->
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="css/basic.style.css">
    <link rel="stylesheet" href="css/login.style.css">
    <!-- fontawesome icon lib -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
</head>
<body>
<div class="back"></div>
<div class="main">
    <div class="layui-main">
        <div class="login-box">
            <div class="header">
                <div style="margin: 0 auto;width: 360px;"><a href="index.html" target="_blank" style="display:inline-block;"><div class="logo"></div></a></div> 
            </div>
            <div class="content">
                    <form class="layui-form layui-form-pane" action="LoginHandle" id="login-info" method="post">
                            <div class="layui-form-item">
                                    <input type="radio" name="role" value="用户" title="用户" checked>
                                    <input type="radio" name="role" value="配送员" title="配送员">
                                    <input type="radio" name="role" value="管理员" title="管理员">
                            </div>
                            <div class="layui-form-item">
                              <label class="layui-form-label"><i class="layui-icon layui-icon-username" style="font-size: 15px;"></i>账号</label>
                              <div class="layui-input-block">
                                <input type="text" name="userName" autocomplete="off" placeholder="请输入账号" class="layui-input" required lay-verify="required" title=" ">
                              </div>
                            </div>
                            <div class="layui-form-item">
                              <label class="layui-form-label"><i class="layui-icon layui-icon-password" style="font-size: 15px;"></i>密码</label>
                              <div class="layui-input-block">
                                <input type="password" name="passWord" autocomplete="off" placeholder="请输入密码" class="layui-input" required lay-verify="required" title=" ">
                              </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-input-block" style="float: right;">
                                        <div id="dx-yz"></div>
                                        <input type="hidden" name="yzCode" value="null">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn layui-btn-lg layui-btn-radius layui-btn-normal layui-btn-fluid" type="submit" style="font-size:20px;">登 录</button>
                            </div>
                    </form>
                    <fieldset class="layui-elem-field layui-field-title" style="text-align: center;border-color: rgba(0,0,0,.12);">
                            <legend style="font-size: 13px;color: #4a4a4a;cursor: default;">用其他方式登录</legend>
                    </fieldset>
                    <div class="layui-btn-container" style="text-align: center;">
                        <button class="layui-btn layui-btn-radius layui-btn-normal"><i class="fab fa-qq" aria-hidden="true" style="font-size: 13px;padding-right: 3px;"></i>QQ登录</button> 
                        <button class="layui-btn layui-btn-radius layui-btn-danger"><i class="fab fa-github" aria-hidden="true" style="font-size: 15px;padding-right: 3px;"></i>Github登录</button> 
                        <button class="layui-btn layui-btn-radius"><i class="fab fa-weixin" aria-hidden="true" style="font-size: 15px;padding-right: 3px;"></i>微信登录</button> 
                    </div>
            </div>
        </div>
            
        </div>
    <div class="footer">
        <div class="layui-main" style="text-align: center;margin: 15px auto;">
            <span class="footer-title">以用户为中心,开启校园速递时代!</span>
            <div style="clear:right;"></div>
            <span class="footer-info">©2019 EendTech. Powered By <a href="http://www.eendtech.com">极束梦想</a></span>
        </div>
    </div>
</div>
<script src="layui/layui.js"></script>
<script src="https://cdn.dingxiang-inc.com/ctu-group/captcha-ui/index.js"></script>
<script src="https://cdn.dingxiang-inc.com/ctu-group/captcha-ui/index.js"></script>
<!-- 导入百度JQ库 -->
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script>
$(document).ready(function(){
    var myCaptcha = _dx.Captcha(document.getElementById('dx-yz'), {
        appId: '5c2064292bd0d455b98c913333683dc5',   //appId,开通服务后可在控制台中“服务管理”模块获取
        style: 'inline',
        width: '500px',
        inlineFloatPosition: 'down',
        customStyle:{
            bgColor: 'rgba(0,0,0,0)'
        },
        success: function (token) {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg('验证成功');
                });
            myCaptcha.reload()
            }
        });
    layui.use('layer',function(){
        var layer = layui.layer;
        $(".logo").hover(function(){
            layer.tips("校园速递,让你感受不一样的速度<br><span style=\"color:#2ca1dc;\">以用户为中心,开创校园新时代!</span>",
            this,{tips:1,time:0,area: ['220px','60px']});
        },function(){
            layer.closeAll("tips");
        });
    });
    layui.use('form', function(){
        var form = layui.form;
  
        //监听提交
  
    });
});
</script>

    <c:if test="${loginTip == 0}">
<script>
    layui.use('layer',function (){
        var layer = layui.layer;
        layer.alert('登录失败!', {icon: 5});
    });
    <%
        session.setAttribute("loginReturn",null);
        session.setAttribute("loginTip",null);
    %>
</script>
    </c:if>

</body>
</html>
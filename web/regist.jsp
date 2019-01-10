<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setCharacterEncoding("utf-8");
    if (request.getParameter("returnCode") != null) {
        int returnCode = Integer.valueOf(request.getParameter("returnCode"));
        /**
         * returnCode :  1:成功 0:失败
         */
        session.setAttribute("returnCode",returnCode);
    }
    System.out.println(session.getAttribute("returnCode"));
%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>注册 - 校园速递 感受不一样的速度</title>
    <!-- 引入Layui框架 -->
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="css/regist.style.css">
    <link rel="stylesheet" href="css/basic.style.css">
    <!-- import css animate lib for cdn  -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
</head>
<body>

<div class="layui-row">
    <div class="layui-col-md4">
        <a href="index.html" target="_blank" style="display:inline-block;"><div id="left-logo"></div></a>
        <div class="left-back">
            <div class="layui-carousel" id="left_back">
                <div carousel-item>
                  <div class="img1"></div>
                  <div class="img2"></div>
                  <div class="img3"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-col-md8">
        <div class="right animated slideInDown">
            <div class="right-top">
                <ul class="nav">
                    <li class="nav item"><i class="layui-icon layui-icon-dialogue" style="font-size:20px;"></i>意见反馈</li>
                    <li class="nav item"><i class="layui-icon layui-icon-help" style="font-size:20px;"></i>关于我们</li>
                </ul>
            </div>
            <div class="content-container">
                <div class="right-title">
                    <h1>欢迎注册校园速递</h1>
                    <h2>开启校园速递时代</h2>
                </div>
                <form action="RegistHandle" method="post" class="layui-form layui-form-pane">
                    <div class="layui-form-item">
                        <input type="radio" name="role" value="用户" title="用户" checked>
                        <input type="radio" name="role" value="配送员" title="配送员">
                    </div>
                    <!-- 用户名 -->
                    <div class="user-input-text">
                        <input id="user-input" type="text" name="userName" required lay-verify="required" title=" " placeholder="账号" autocomplete="off" class="end-input">
                    </div>
                    <!-- 密码 -->
                    <div class="pwd-input-text">
                        <input type="password" id="pwd-input" name="passWord" required lay-verify="required" title=" " placeholder="密码" autocomplete="off" class="end-input">
                    </div>
                    <!-- 表单验证 -->
                    <div id="dx-yz"></div>
                    <!-- 注册按钮 -->
                    <div class="submit-input-button">
                        <button id="submit-button" class="layui-btn layui-btn-lg layui-btn-normal" lay-submit="" lay-filter="demo" lay-submit="" style="font-size:20px;">注 册</button>
                    </div>
                </form>
                
            </div>
        </div>
        <div style="clear: both;"></div>
        <div class="right-bottom">
            <div class="layui-main">
                <span class="footer-title">以用户为中心,开启校园速递时代!</span>
                <div style="clear:right;"></div>
                <span class="footer-info">©2019 EendTech. Powered By <a href="http://www.eendtech.com">极束梦想</a></span>
            </div>
        </div>
    </div>
    
    
</div>


            

<script src="layui/layui.js"></script>
<script src="https://cdn.dingxiang-inc.com/ctu-group/captcha-ui/index.js"></script>
<!-- 导入百度JQ库 -->
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script>
    $(document).ready(function(){
        var myCaptcha = _dx.Captcha(document.getElementById('dx-yz'), {
        appId: '5c2064292bd0d455b98c913333683dc5',   //appId,开通服务后可在控制台中“服务管理”模块获取
        success: function (token) {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg('验证成功');
                });

            myCaptcha.reload()
            }
        });
        myCaptcha.on('loadFail',function(){
            myCaptcha.reload();
        });



        layui.use('layer', function(){
            var layer = layui.layer;
            $("#left-logo").hover(function(){
                layer.tips("校园速递,让你感受不一样的速度<br><span style=\"color:#2ca1dc;\">以用户为中心,开创校园新时代!</span>",
                this,{
                    tips: 1,
                    time: 0,
                    area: ['220px','60px']
                })
            },function(){
                layer.closeAll("tips");
            });  
            $("#pwd-input").hover(function(){
                layer.tips("请输入您要注册的密码",this,{tips:1,time:0});
            },function(){
                layer.closeAll("tips");
            });   
            $("#user-input").hover(function(){
                layer.tips("请输入您要注册的用户名",this,{tips:1,time:0});
            },function(){
                layer.closeAll("tips");
            });     
        });
        
        
    });
</script>
<script>
    layui.use('carousel', function(){
      var carousel = layui.carousel;
      //建造实例
      carousel.render({
        elem: '#left_back'
        ,width: '480px' //设置容器宽度
        ,arrow: 'none' //始终显示箭头
        ,height: '100%'
        ,anim: 'fade' //切换动画方式
        ,indicator: 'none'
      });
    });
    layui.use('form', function(){
        var form = layui.form;
  
        //监听提交
  
    });
    </script>
<c:if test="${returnCode == 1}">
    <script>
        layui.use('layer',function () {
            <%
                session.setAttribute("returnCode",null);
            %>
           var layer = layui.layer;
            layer.alert('注册成功', {icon: 1},function (index) {
                layer.close(index);
                window.location="login.jsp";
            });

        });
    </script>
</c:if>
<c:if test="${returnCode == 0}">
    <script>
        layui.use('layer',function () {
            var layer = layui.layer;
            layer.alert('注册失败!', {icon: 5});
            <%
                session.setAttribute("returnCode",null);
            %>
        });
    </script>
</c:if>


</body>

</html>



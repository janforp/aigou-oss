<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <title>请登录</title>
        <meta content="" name="description"/>
        <meta content="" name="Keywords"/>
        <meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
        <meta content="zh-CN" http-equiv="Content-Language"/>
        <meta content="IE=edge" http-equiv="X-UA-Compatible"/>
        <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

        <link href="${BASE_PATH}/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${BASE_PATH}/css/base/global.css" rel="stylesheet" type="text/css"/>
        <link href="${BASE_PATH}/css/base/console.css" rel="stylesheet" type="text/css"/>
        <%-- js库 --%>
        <script src="${BASE_PATH}/plugins/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
        <script src="${BASE_PATH}/js/base/validation.js" type="text/javascript"></script>
        <script src="${BASE_PATH}/js/base/control-convert.js" type="text/javascript"></script>
        <script src="${BASE_PATH}/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

        <link href="${BASE_PATH}/css/page/login.css" rel="stylesheet" type="text/css"/>
        <script src="${BASE_PATH}/js/page/login.js"></script>
        <script src="${BASE_PATH}/plugins/encrypt/md5.js" type="text/javascript"></script>

        <script>
            $(function () {

                $("#btnLogin").click(function () {

                    var loginNameX = control.getString("#txtLoginName", false);
                    var pwd = control.getString("#txtPassword", false);

                    $(this).text("登录中...");

                    $.ajax({
                        url: '/c/p/login',
                        type: 'POST',
                        dataType: 'json',
                        data: {
                            loginName: loginNameX,
                            password: pwd
                        },
                        success: function (data) {
                            if (data.success == true) {
                                location.href =  "${BASE_PATH}/c/p/order/list";
                            } else {
                                //登录失败
                                alert(data.msg);
                                $("#btnLogin").text("登录")
                            }
                        },
                        complete: function () {

                        },
                        error:function(o){
                        }
                    });

                });

            });
        </script>

    </head>
    <body>
        <div id="header" class="navbar navbar-default navbar-wx navbar-static-top min-container" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" style="padding: 5px 5px;" href="javascript:void(0);">
                        <span style="font-size: 20px;">一元爱购 | </span>管理后台 </a>
                </div>
            </div>
        </div>
        <div id="login-container" class="container container-fluid" style="padding-top: 156.75px;">
            <div id="login-panel" class="row body-box not-show" style="display: block;">
                <div class="form-login ajax-form no-auto-submit">
                    <h3 class="form-signin-heading">管理员登录</h3>

                    <div class="input-group">
                        <span class="input-group-addon input-1">
                            <i class="glyphicon glyphicon-user"></i>
                         </span>
                        <input id="txtLoginName" class="form-control input-1" type="text" autofocus="" value=""
                               placeholder="请输入登录账号" name="uid" autocomplete="on" validate="required"
                               errormessage="登录账号不能为空">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon input-2">
                            <i class="glyphicon glyphicon-lock"></i>
                        </span>
                        <input id="txtPassword" class="form-control input-2" type="password"
                               value="" placeholder="请输入登录密码" autocomplete="off" validate="required" errormessage="登录密码不能为空">
                    </div>

                    <div class="p10">
                        <input id="pid" type="hidden" show-id="pwd" name="pid" autocomplete="off">
                        <button class="btn btn-lg btn-success btn-block" id="btnLogin" autocomplete="off">
                            登 录
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

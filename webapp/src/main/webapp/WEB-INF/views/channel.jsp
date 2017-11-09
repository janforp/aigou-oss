<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="">
        <meta http-equiv="Content-Language" content="zh-CN">
        <meta http-equiv="content-type" content="text/html; charset="/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="">
        <meta name="Keywords" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">

        <title>渠道统计</title>

        <link rel="stylesheet" href="/plugins/bootstrap-3.1.1/dist/css/bootstrap.css"/>
        <link rel="stylesheet" href="/plugins/weui/dist/style/weui.css"/>
        <link rel="stylesheet" href="/client/css/base/global.css"/>

        <style>
            .separator_line {
                margin-top: 10px;
                height: 1px;
                background-color: #CCCCCC;
            }
            .table tbody td{text-align: center; white-space:nowrap;}
        </style>
        <link href="${BASE_PATH}/client/css/page/add_mission.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <div class="main">
            <div class="container">
                <div class="mission-merchant">
                    <h4>包名:${packageName} | 渠道:${channelName}</h4>
                    <div class="col1">
                        <span style="display: inline;">
                            <label style="width: 100px; text-align: left">今日用户:</label>
                            ${today.dayPeople}人
                        </span>
                        <span style="display: inline; margin-left: 50px;">
                            <label style="width: 100px; text-align: left">今日充值:</label>
                            ${today.dayRecharge}元
                        </span>
                    </div>
                </div>

                <%--分割线--%>
                <div class="separator_line"></div>

                <%--数据表格--%>
                <div style="width:100%; overflow:scroll;">
                    <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                        <tbody id="table">
                            <tr>
                                <td width="30%">日期</td>
                                <td width="30%">用户</td>
                                <td width="30%">金额(元)</td>
                            </tr>
                            <c:forEach items="${list}" var="e" varStatus="loop">
                                <tr>
                                    <td>${e.dayTime}</td>
                                    <td>${e.dayPeople}</td>
                                    <td>${e.dayRecharge}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="template-code">

            <div id="global-toast" style="display: none;">
                <div class="weui_mask_transparent"></div>
                <div class="weui_toast">
                    <i class="weui_icon_toast"></i>
                    <p class="weui_toast_content">&nbsp;</p>
                </div>
            </div>
            <div id="global-toast-loading" class="weui_loading_toast" style="display: none;">
                <div class="weui_mask_transparent"></div>
                <div class="weui_toast">
                    <div class="weui_loading">
                        <div class="weui_loading_leaf weui_loading_leaf_0"></div>
                        <div class="weui_loading_leaf weui_loading_leaf_1"></div>
                        <div class="weui_loading_leaf weui_loading_leaf_2"></div>
                        <div class="weui_loading_leaf weui_loading_leaf_3"></div>
                        <div class="weui_loading_leaf weui_loading_leaf_4"></div>
                        <div class="weui_loading_leaf weui_loading_leaf_5"></div>
                        <div class="weui_loading_leaf weui_loading_leaf_6"></div>
                        <div class="weui_loading_leaf weui_loading_leaf_7"></div>
                        <div class="weui_loading_leaf weui_loading_leaf_8"></div>
                        <div class="weui_loading_leaf weui_loading_leaf_9"></div>
                        <div class="weui_loading_leaf weui_loading_leaf_10"></div>
                        <div class="weui_loading_leaf weui_loading_leaf_11"></div>
                    </div>
                    <p class="weui_toast_content">数据加载中</p>
                </div>
            </div>

            <div id="global-dialog-confirm" class="weui_dialog_confirm" style="display: none;">
                <div class="weui_mask"></div>
                <div class="weui_dialog">
                    <div class="weui_dialog_hd"><strong class="weui_dialog_title">&nbsp;</strong></div>
                    <div class="weui_dialog_bd">&nbsp;</div>
                    <div class="weui_dialog_ft">
                        <a href="javascript:;" class="weui_btn_dialog default btn_dialog_cancel">取消</a>
                        <a href="javascript:;" class="weui_btn_dialog primary btn_dialog_ok">确定</a>
                    </div>
                </div>
            </div>

            <div id="global-dialog-alert" class="weui_dialog_alert" style="display: none;">
                <div class="weui_mask"></div>
                <div class="weui_dialog">
                    <div class="weui_dialog_hd"><strong class="weui_dialog_title">&nbsp;</strong></div>
                    <div class="weui_dialog_bd">&nbsp;</div>
                    <div class="weui_dialog_ft">
                        <a href="javascript:;" class="weui_btn_dialog primary">确定</a>
                    </div>
                </div>
            </div>

        </div>

            <script type="text/javascript">window.pg={basePath:"",networkType:null};</script>
            <script src="/plugins/jquery/jquery-1.9.1.min.js"></script>

            <script src="/plugins/bootstrap-3.1.1/dist/js/bootstrap.js"></script>

            <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

            <script src="/plugins/hack/ie10-viewport-bug-workaround.js"></script>


    </body>
</html>


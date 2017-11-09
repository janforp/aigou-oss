<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="购买纪录"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            .separator_line {
                margin-top: 10px;
                height: 1px;
                background-color: #CCCCCC;
            }
            .table tbody td{text-align: center; white-space:nowrap;}

            .cell {
                width:25px;
                height:25px;
            }

            .selected {
                color: #00a0a0;
            }
        </style>

    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>

        <script>

            //'获得云购码' 按钮 跳出的小页面
            $(document).on("click",".btnCodes",function () {

                var lotteryCode = $(this).attr("huode");
                var allCodes = $(this).attr("allCodes");
                $(".btnCodes").css("background-color","#ffffff");
                $(this).css("background-color","#156df0");
                window.open ("/c/page/console/auth/buyAndRecharge/showCodes?allCodes="+allCodes+"&lotteryCode="+lotteryCode, "newwindow", "height=400, width=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
            });





               // 充值纪录查询

            var pageNum = 1;

            function search(page) {
                if(page != null){
                    pageNum = page;
                }
                var startTime = $("#start").val().trim();
                var endTime   = $("#end").val();
                var paramKey  = $("#paramKey").val();
                var paramValue= $("#paramValue").val();

                tips.loading();

                if (paramKey == "userId"){
                    if (isNaN(paramValue)){
                        tips.err("ID必须是数字",2000);
                        return ;
                    }
                }






                $.ajax({
                    url     :   "/c/page/console/auth/buyAndRecharge/getBuyData",
                    type    :   "POST",
                    dataType:   "JSON",
                    data    :   {
                                    pageNum: pageNum,
                                    startTime: startTime,
                                    endTime: endTime,
                                    paramKey: paramKey,
                                    paramValue: paramValue
                                },
                    success :   function (data) {

                        if (data.success == true) {

                            var total = data.bean.totalPage;
                            var list = data.bean.records;
                            var code = "<tr>" +
                                            " <td>序号</td>" +
                                            " <td>会员ID</td>" +
                                            " <td>会员昵称</td>" +
                                            " <td>会员手机</td>" +
                                            " <td>订单号</td>" +
                                            " <td>商品名</td>" +
                                            " <td>购买次数</td>" +
                                            " <td>获得云购码</td>" +
                                            " <td>中奖码</td>" +
                                            " <td>购买时间</td>" +
                                        "</tr>";
                            for(var i=0; i<list.length; i++) {
                                code += "<tr>" +
                                            "   <td>"+(i+1)+"</td>" +
                                            "   <td>"+list[i].userId+"</td>" +
                                            "   <td>"+list[i].userName+"</td>" +
                                            "   <td>"+list[i].phone+"</td>" +
                                            "   <td>"+list[i].code+"</td>" +
                                            "   <td>"+list[i].shopName+"</td>" +
                                            "   <td>"+list[i].goNumber+"</td>" +
                                            "   <td><button codes='"+list[i].gouCodes+"' huode ='"+list[i].huode+  "'allCodes ='"+ list[i].allCodes+"' class = 'btnCodes'>获得云购码</button></td>" +
                                            "   <td>"+list[i].huode+"</td>" +
                                            "   <td>"+list[i].time+"</td>"+
                                        "</tr>";
                            }

                            code += "<tr>" +
                                    "   <td colspan='10'>" +
                                    "       <div class='pagination-box pagination-right'>" +
                                    "           <ul class='pagination pagination-sm'>" +
                                    "               <li " + (pageNum == 1 ? "class='disabled'" : "") + ">" +
                                    "                   <a href='" + (pageNum == 1 ? "javascript:;" : "javascript:search("+(pageNum-1)+");") + "' class='btn_prev'>" +
                                    "                       <i class='glyphicon glyphicon-chevron-left'></i>" +
                                    "                   </a>" +
                                    "               </li>" +
                                    "               <li>" +
                                    "                   <span class='pagination-status'>" + pageNum + "/" + total + "</span>" +
                                    "               </li>" +
                                    "               <li " + (pageNum == total ? "class='disabled'" : "") + ">" +
                                    "                   <a href='" + (pageNum == total ? "javascript:;" : "javascript:search("+(pageNum+1)+");") + "' class='btn_next'>" +
                                    "                       <i class='glyphicon glyphicon-chevron-right'></i>" +
                                    "                   </a>" +
                                    "               </li>" +
                                    "           </ul>" +
                                    "       </div>" +
                                    "   </td>" +
                                    "</tr>";

                            $("#table").html(code);

                        } else {
                            tips.err(data.msg,2000);
                        }
                                },

                    complete:   function() {
                                    tips.hideLoading();
                                },
                    error:      function() {
                                    tips.hideLoading();
                                }

                });
            }


        </script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <div id="queryForm" class="fr mt-4">
                    <div class="fr pr5">
                        <div class="input-group input-group-sm w100 fl">
                        </div>
                    </div>
                </div>
                <span>购买纪录查询</span>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">

            <%--查询--%>
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label" >时间范围</label>
                    <div class="col-sm-10">
                        <button id = "month" onclick="showMonth();">最近一个月</button>
                        <button id = "week" onclick="showWeek();">最近一周</button>
                        <button id = "yesterday" onclick="showYesterday();">昨天</button>
                        <button id = "today" onclick="showToday();">今天</button>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-10">
                        <input type="text" id = "start" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'});"/>
                        <input type="text" id = "end"  class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'});"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">查询条件</label>
                    <div class="col-sm-10">
                        <select id="paramKey" style="width: 130px;">
                            <option value="userId">uid</option>
                            <option value="nickName" selected>昵称</option>
                            <option value="mobile">手机号</option>
                        </select>
                        <input type="text" id="paramValue" >
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-success" onclick="search(1)">查询</button>
                    </div>
                </div>
            </div>

            <%--分割线--%>
            <div class="separator_line">
            </div>

            <%--数据表格--%>
            <div style="width:100%; overflow:scroll;">
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <tbody id="table">
                    </tbody>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>

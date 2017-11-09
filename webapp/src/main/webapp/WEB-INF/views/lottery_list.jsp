<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="中奖发货管理"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <%--<link rel="stylesheet" href="${BASE_PATH}/css/page/index${RESOURCE_MIN}.css${RESOURCE_VERSION}"/>--%>
        <style>
            .separator_line {
                margin-top: 10px;
                height: 1px;
                background-color: #CCCCCC;
            }
            .table tbody td{text-align: center; white-space:nowrap;}
        </style>

    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script>

            var pageNum = 1;

            function search(page) {
                if(page != null){
                    pageNum = page;
                }
                var startTime = $("#start").val().trim();
                var endTime   = $("#end").val();
                var paramKey  = $("#paramKey").val();
                var paramValue= $("#paramValue").val();
                var status    = $("#status").val();
                var rank      = $("#rank").val();

                tips.loading();

                $.ajax({
                    url     :   "/c/page/console/auth/lottery/getData",
                    type    :   "POST",
                    dataType:   "JSON",
                    data    :   {
                                    pageNum: pageNum,
                                    startTime: startTime,
                                    endTime: endTime,
                                    rank: rank,
                                    paramKey: paramKey,
                                    paramValue: paramValue,
                                    status: status
                                },
                    success :   function (data) {

                        if (data.success == true) {

                            var total = data.bean.totalPage;
                            var list = data.bean.list;
                            var code = "<tr>" +
                                    "   <td width='20%'>序号</td>" +
                                    "   <td width='20%'>订单号</td>" +
                                    "   <td width='20%'>标题</td>" +
                                    "   <td width='10%'>开奖时间</td>" +
                                    "   <td width='10%'>中奖码</td>" +
                                    "   <td width='10%'>中奖用户id</td>" +
                                    "   <td width='10%'>用户昵称</td>" +
                                    "   <td width='10%'>购买份数</td>" +
                                    "   <td width='10%'>订单状态</td>" +
                                    "   <td width='10%'>操作</td>" +
                                    "</tr>";

                            for(var i=0; i<list.length; i++) {
                                code += "<tr>" +
                                        "   <td>"+(i+1)+"</td>" +
                                        "   <td>"+list[i].orderCode+"</td>" +
                                        "   <td>第("+list[i].qishu+")期"+list[i].title+"</td>" +
                                        "   <td>"+list[i].q_end_time+"</td>" +
                                        "   <td>"+list[i].q_user_code+"</td>" +
                                        "   <td>"+list[i].userId+"</td>" +
                                        "   <td>"+list[i].nickName+"</td>"+
                                        "   <td>"+list[i].buyNums+"</td>" ;
                                if(status == '0') { // 全部

                                    if(list[i].statusDesc == '未完成'){
                                        code +=
                                                "   <td>未完成</td>" +
                                                "   <td>" +
                                                "       <button class='btn btn-default btn-xs btn_view btn_detail' type='button' virtual='"+list[i].virtualOrNot+"' shopId='"+list[i].shopId+"' userId='"+list[i].userId+"' orderCode = '"+list[i].orderCode+"' >详情</button>" +
                                                "       <button class='btn btn-default btn-xs btn_show btn_send' type='button'  virtual='"+list[i].virtualOrNot+"'shopId='"+list[i].shopId+"' userId='"+list[i].userId+"' orderCode = '"+list[i].orderCode+"' >发货</button>" +
                                                "   </td>" ;
                                    }
                                    if(list[i].statusDesc == '待收货') {
                                        code +=
                                                "   <td>待收货</td>" +
                                                "   <td>" +
                                                "       <button class='btn btn-default btn-xs btn_view btn_detail' type='button' virtual='"+list[i].virtualOrNot+"'shopId='"+list[i].shopId+"' userId='"+list[i].userId+"' orderCode = '"+list[i].orderCode+"' >详情</button>" +
                                                "   </td>" ;
                                    }
                                    if(list[i].statusDesc == '已完成') {
                                        code +=
                                                "   <td>已完成</td>" +
                                                "   <td>" +
                                                "       <button class='btn btn-default btn-xs btn_view btn_detail' type='button'  virtual='"+list[i].virtualOrNot+"'shopId='"+list[i].shopId+"' userId='"+list[i].userId+"' orderCode = '"+list[i].orderCode+"' >详情</button>" +
                                                "   </td>" ;
                                    }
                                    if(list[i].statusDesc == '已作废') {
                                        code +=
                                                "   <td>已作废</td>" +
                                                "   <td>" +
                                                "       <button class='btn btn-default btn-xs btn_view btn_detail' type='button'  virtual='"+list[i].virtualOrNot+"'shopId='"+list[i].shopId+"' userId='"+list[i].userId+"' orderCode = '"+list[i].orderCode+"' >详情</button>" +
                                                "   </td>" ;
                                    }
                                }
                                if (status == '1'){ // 未发货
                                    code +=
                                            "   <td>未完成</td>" +
                                            "   <td>" +
                                            "       <button class='btn btn-default btn-xs btn_view btn_detail' type='button' virtual='"+list[i].virtualOrNot+"' shopId='"+list[i].shopId+"' userId='"+list[i].userId+"' orderCode = '"+list[i].orderCode+"'>详情</button>" +
                                            "       <button class='btn btn-default btn-xs btn_show btn_send' type='button' virtual='"+list[i].virtualOrNot+"' shopId='"+list[i].shopId+"' userId='"+list[i].userId+"' orderCode = '"+list[i].orderCode+"' >发货</button>" +
                                            "   </td>" ;
                                }
                                if (status == '2') { //已发货
                                    code +=
                                            "   <td>待收货</td>" +
                                            "   <td>" +
                                            "       <button class='btn btn-default btn-xs btn_view btn_detail' type='button' virtual='"+list[i].virtualOrNot+"' shopId='"+list[i].shopId+"' userId='"+list[i].userId+"' orderCode = '"+list[i].orderCode+"' >详情</button>" +
                                            "   </td>" ;
                                }
                                if (status == '3') { //已完成
                                    code +=
                                            "   <td>已完成</td>" +
                                            "   <td>" +
                                            "       <button class='btn btn-default btn-xs btn_view btn_detail' type='button' virtual='"+list[i].virtualOrNot+"' shopId='"+list[i].shopId+"' userId='"+list[i].userId+"' orderCode = '"+list[i].orderCode+"' >详情</button>" +
                                            "   </td>" ;

                                }
                                if (status == '4') { //已作废
                                    code +=
                                            "   <td>已作废</td>" +
                                            "   <td>" +
                                            "       <button class='btn btn-default btn-xs btn_view btn_detail' type='button' virtual='"+list[i].virtualOrNot+"' shopId='"+list[i].shopId+"' userId='"+list[i].userId+"' orderCode = '"+list[i].orderCode+"' >详情</button>" +
                                            "   </td>" ;

                                }
                                code += "</tr>";
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
                            alert(data.msg);
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

            $(function () {


                //订单详情
                $(document).on("click",".btn_detail",function () {

                    var shopId = $(this).attr("shopId");
                    var userId = $(this).attr("userId");
                    var orderCode=$(this).attr("orderCode");

                    window.open("/c/page/console/auth/lottery/getdetailInfo?shopId="+shopId+"&userId="+userId+"&orderCode="+orderCode);


                });

                //验证是否已经发过货
                $(document).on("click",".btn_send",function () {

                    var shopId = $(this).attr("shopId");
                    var userId = $(this).attr("userId");
                    var orderCode=$(this).attr("orderCode");
                    var virtual = $(this).attr("virtual");


                    $.ajax({
                        url     :     "/c/page/console/auth/lottery/checksendornot",
                        type    :     "POST",
                        dataType:     "JSON",
                        data    :     {
                                        userId :   userId,
                                        shopId :   shopId,
                                        orderCode: orderCode
                                      },
                        success :     function(data){

                                        if (data.success == true) {
                                            //跳转至填写发货信息页面
                                            window.open("/c/page/console/auth/lottery/send_page?shopId="+shopId+"&userId="+userId+"&orderCode="+orderCode+"&virtual="+virtual);
                                        } else {
                                            tips.err(data.msg, 2000);
                                        }
                                      },
                        complete:     function() {

                                      },
                        error:        function() {

                                      }
                    });
                });

            });



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
                <span>中奖发货管理</span>
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
                            <option value="nickName">昵称</option>
                            <option value="mobile">手机号</option>
                            <option value="email">邮箱</option>
                        </select>
                        <input type="text" id="paramValue" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">订单状态</label>
                    <div class="col-sm-10">
                        <select id="status" style="width: 130px;">
                            <option value="0">全部</option>
                            <option selected = "selected" value="1">未完成</option>
                            <option value="2">待收货</option>
                            <option value="3">已完成</option>
                            <option value="4">已作废</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >排序</label>
                    <div class="col-sm-10">
                        <select id="rank" style="width: 130px;">
                            <option value="0">按开奖时间正序</option>
                            <option value="1">按开奖时间倒序</option>
                        </select>
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

<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="${CURRENT_FUNCTION.functionName}"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            .separator_line {
                margin-top: 10px;
                height: 1px;
                background-color: #CCCCCC;
            }
            .table tbody td{text-align: center; white-space:nowrap;}
            input[type=checkbox] {
                width: 30px;
                height:30px;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="${BASE_PATH}/client/js/base/request${RESOURCE_MIN}.js${RESOURCE_VERSION}"></script>
        <script src="${BASE_PATH}/client/js/base/control-convert${RESOURCE_MIN}.js${RESOURCE_VERSION}"></script>
        <script src="${BASE_PATH}/plugins/My97DatePicker/function.js"></script>
        <script src="${BASE_PATH}/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src = "/methods/order_list.js" type="text/javascript"></script>
        <script>

            var pageNum = 1;

            function search(page) {

                $("#deleteShaidan").hide();
                $("#updateShaidan").hide();
                if(page != null) {
                    pageNum = page;
                }
                var startTime = $("#start").val();
                var endTime = $("#end").val();
                var rank = $("#rank").val();
                var paramKey = $("#paramKey").val();
                var paramValue = $("#paramValue").val();
                var status = $("#status").val();

                tips.loading();

                $.ajax({
                    url: '/c/page/console/auth/order/getData',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        pageNum: pageNum,
                        startTime: startTime,
                        endTime: endTime,
                        rank: rank,
                        paramKey: paramKey,
                        paramValue: paramValue,
                        status: status
                    },
                    success: function(data) {
                        if (data.success == true) {

                            var total = data.bean.totalPage;
                            var list = data.bean.list;

                            if(status == '0'){  //未晒单

                                var code = "<tr>" +
                                        "   <td width='10%'>选择</td>" +
                                        "   <td width='20%'>订单号</td>" +
                                        "   <td width='20%' style='font-size: 5px'>标题</td>" +
                                        "   <td width='10%'>开奖时间</td>" +
                                        "   <td width='10%'>中奖码</td>" +
                                        "   <td width='10%'>中奖用户id</td>" +
                                        "   <td width='10%'>用户昵称</td>" +
                                        "   <td width='10%'>晒单状态</td>" +
                                        "   <td width='10%'>操作</td>" +
                                        "</tr>";

                                for(var i=0; i<list.length; i++) {
                                    code += "<tr>" +
                                            "   <td><input type='checkbox' value='"+list[i].id+"' /></td>" +
                                            "   <td>"+list[i].orderCode+"</td>" +
                                            "   <td>第("+list[i].qishu+")期"+list[i].title+"</td>" +
                                            "   <td>"+list[i].q_end_time+"</td>" +
                                            "   <td>"+list[i].q_user_code+"</td>" +
                                            "   <td>"+list[i].userId+"</td>" +
                                            "   <td>"+list[i].nickName+"</td>";
                                    if(status == '0') { // 未晒单
                                        code +=
                                                "   <td>未晒单</td>" +
                                                "   <td>" +
                                                "       <button class='btn btn-default btn-xs btn_view' type='button' shopId='"+list[i].shopId+"' >商品详情</button>" +
                                                "       <button class='btn btn-default btn-xs btn_show' type='button' shopId='"+list[i].shopId+"' userId='"+list[i].userId+"' >晒单</button>" +
                                                "   </td>" +
                                                "</tr>";
                                    }else { // 已晒单
                                        code +=
                                                "   <td>已晒单</td>" +
                                                "   <td>" +
                                                "       <button class='btn btn-default btn-xs btn_view' type='button' shopId='"+list[i].shopId+"' >商品详情</button>" +
                                                "   </td>" +
                                                "</tr>";
                                    }
                                }

                                code += "<tr>" +
                                        "   <td colspan='9'>" +
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
                            }else if(status == '1') { //已晒单

                                var code = "<tr>" +
                                            "   <td width='5%'>选择</td>" +
                                            "   <td width='10%'>晒单标题</td>" +
                                            "   <td width='10%' style='font-size: 5px'>商品名</td>" +
                                            "   <td width='10%'>晒单时间</td>" +
                                            "   <td width='20%'>晒单图片</td>" +
                                            "</tr>";

                                for(var i=0; i<list.length; i++) {
                                    code += "<tr>" +
                                            "   <td><input type='checkbox' value='"+list[i].sdId+"' userId='"+list[i].userId+"' shopId='"+list[i].sdShopid+"' /></td>" +
                                            "   <td>"+list[i].sdTitle+"</td>" +
                                            "   <td>第("+list[i].sdQishu+")期"+list[i].shopTitle+"</td>" +
                                            "   <td>"+list[i].sdTimeShow+"</td><td>";
                                    for (var j = 0; j<list[i].photos.length ;j++) {
                                        code += "<img src='"+list[i].photos[j]+"' style='width: 60px;'>&nbsp;&nbsp;";
                                    }
                                    code += "</td></tr>";
                                }

                                code += "<tr>" +
                                        "   <td colspan='5'>" +
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

                                $("#deleteShaidan").show();
                                $("#updateShaidan").show();
                            }

                            $("#table").html(code);

                        } else {
                            tips.err(data.msg);
                        }
                    },
                    complete: function() {
                        tips.hideLoading();
                    },
                    error: function() {
                        tips.hideLoading();
                    }
                });

            }

            $(function() {

                // 商品详情
                $(document).on("click", ".btn_view", function () {
                    var shopId = $(this).attr("shopId");
                    window.open("/c/page/console/auth/goods/goodsDetailPage?goodsId="+shopId)
                });

                // 晒单
                $(document).on("click", ".btn_show", function () {

                    var shopId = $(this).attr("shopId");
                    var userId = $(this).attr("userId");

                    $.ajax({
                        url: '/c/page/console/auth/order/check',
                        type: 'POST',
                        dataType: 'json',
                        data: {
                            shopId: shopId,
                            userId: userId
                        },
                        success: function(data) {

                            if (data.success == true) {
                                window.open("/c/page/console/auth/order/release_page?shopId="+shopId+"&userId="+userId);
                            } else {
                                tips.err(data.msg, 2000);
                            }

                        },
                        complete: function() {

                        },
                        error: function() {

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
                <button class="btn navbar-btn btn-success btn-sm" style="display: none;" id="deleteShaidan">删除</button>
                <button class="btn navbar-btn btn-success btn-sm" style="display: none;" id="updateShaidan">修改</button>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">

            <%--查询--%>
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label" ><span style="font-size: large">时间范围</span></label>
                    <div class="col-sm-10">
                        <button id = "month" class="btn" onclick="showMonth();">最近一个月</button>
                        <button id = "week" class="btn" onclick="showWeek();">最近一周</button>
                        <button id = "yesterday" class="btn" onclick="showYesterday();">昨天</button>
                        <button id = "today" class="btn" onclick="showToday();">今天</button>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="text" id = "start" style="width: 180px;height: 30px;margin-left: 335px;" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'});"/>
                        <input type="text" id = "end" style="width: 180px;height: 30px;margin-left: 30px;" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'});"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"><span style="font-size: large;position: relative;bottom:5px;">查询条件</span></label>
                    <div class="col-sm-10">
                        <select id="paramKey" style="width: 110px;height: 30px;">
                            <option value="userId">uid</option>
                            <option value="nickName">昵称</option>
                            <option value="mobile">手机号</option>
                            <option value="email">邮箱</option>
                            <option value="title">商品名</option>
                        </select>
                        <input type="text" id="paramValue" style="width: 180px;height: 30px;margin-left: 35px;">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"><span style="font-size: large;position: relative;bottom:5px;">晒单状态</span></label>
                    <div class="col-sm-10">
                        <select id="status" style="width: 110px;height: 30px;">
                            <option value="0">未晒单</option>
                            <option value="1">已晒单</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" ><span style="font-size: large;position: relative;bottom:5px;">排序</span></label>
                    <div class="col-sm-10">
                        <select id="rank" style="width: 150px;height: 30px;">
                            <option value="0">按开奖时间正序</option>
                            <option value="1">按开奖时间倒序</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-success" onclick="search(1)">查&nbsp;&nbsp;&nbsp;&nbsp;询</button>
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

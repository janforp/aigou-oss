<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="用户查询"/>
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

        <script>

            var pageNum = 1;

            function findUser(page) {
                if(page != null){
                    pageNum = page;
                }
                var paramKey  = $("#paramKey").val();
                var paramValue= $("#paramValue").val();

                if (paramValue == null|| paramValue.trim() == "") {
                    $("#paramValue").val("还没有输入查询条件");

                    setTimeout(function () {
                        $("#paramValue").val("");
                    },2000);
                    return;
                }

                if( $("#paramKey").val() == "userId") {

                    if (isNaN(paramValue)) {

                        $("#paramValue").val("用户ID必须是数字");

                        setTimeout(function () {
                            $("#paramValue").val("");
                        },2000);
                        return;
                    }

                }

                tips.loading();

                $.ajax({
                    url     :   "/c/page/console/auth/buyAndRecharge/userList",
                    type    :   "POST",
                    dataType:   "JSON",
                    data    :   {
                                    pageNum     : pageNum,
                                    paramKey    : paramKey,
                                    paramValue  : paramValue
                                },
                    success :   function (data) {

                        if (data.success == true) {

                            var total = data.bean.totalPage;
                            var list = data.bean.list;
                            var code =
                                    "<tr>" +
                                        "   <td width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;</td>" +
                                        "   <td width='20%'>用户名</td>" +
                                        "   <td width='20%'>用户ID</td>" +
                                        "   <td width='10%'>手机</td>" +
                                        "   <td width='10%'>帐户金额</td>" +
                                    "</tr>";


                           if (list.length != 0) {
                               for(var i=0; i<list.length; i++) {

                                   code += "<tr>" +
                                           "   <td><input type='radio' name='user' class= 'cell' type='button' userId= '"+list[i].userId+"' /></td>" +
                                           "   <td>"+list[i].userName+"</td>" +
                                           "   <td>"+list[i].userId+"</td>" +
                                           "   <td>"+list[i].phone+"</td>" +
                                           "   <td>"+list[i].money+"</td>"+
                                           "</tr>";
                               }

                            code += "<tr>" +
                                    "   <td colspan='10'>" +
                                    "       <div class='pagination-box pagination-right'>" +
                                    "           <ul class='pagination pagination-sm'>" +
                                    "               <li " + (pageNum == 1 ? "class='disabled'" : "") + ">" +
                                    "                   <a href='" + (pageNum == 1 ? "javascript:;" : "javascript:findUser("+(pageNum-1)+");") + "' class='btn_prev'>" +
                                    "                       <i class='glyphicon glyphicon-chevron-left'></i>" +
                                    "                   </a>" +
                                    "               </li>" +
                                    "               <li>" +
                                    "                   <span class='pagination-status'>" + pageNum + "/" + total + "</span>" +
                                    "               </li>" +
                                    "               <li " + (pageNum == total ? "class='disabled'" : "") + ">" +
                                    "                   <a href='" + (pageNum == total ? "javascript:;" : "javascript:findUser("+(pageNum+1)+");") + "' class='btn_next'>" +
                                    "                       <i class='glyphicon glyphicon-chevron-right'></i>" +
                                    "                   </a>" +
                                    "               </li>" +
                                    "           </ul>" +
                                    "       </div>" +
                                    "   </td>" +
                                    "</tr>";

                            $("#table").html(code);

                           }

                        } else {
                            tips.err(data.msg,2000);
                        }
                    },

                    complete:   function() {
                        tips.hideLoading();
                    },
                    error:      function() {

                        tips.err("出错啦",2000);
                    }

                });
            }



            //选定某个用户查询购买纪录
            $(document).on("click","#showRecords",function () {

                    var $user = $("input[type='radio']:checked");

                    var userId = $user.attr("userId");

                    if (isNaN(userId)) {
                        tips.err("请选择一条纪录查看",2000);
                        return;
                }

                    window.open("/c/page/console/auth/buyAndRecharge/showRecords?userId="+userId);
            });

            //选定某个用户查询充值纪录
            $(document).on("click","#showAccounts",function () {

                var $user = $("input[type='radio']:checked");

                var userId = $user.attr("userId");

                if (isNaN(userId)) {
                    tips.err("请选择一条纪录查看", 2000);
                    return;
                }

                window.open("/c/page/console/auth/buyAndRecharge/showAccounts?userId="+userId);
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
                <span>用户查询</span>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">

                <%--查询--%>
            <div class="form-horizontal">
                <div class="form-group">

                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-10">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">查询条件</label>
                    <div class="col-sm-10">
                        <select id="paramKey" style="width: 130px;">
                            <option value="userId">uid</option>
                            <option value="nickName">昵称</option>
                            <option value="mobile">手机号</option>
                        </select>
                        <input type="text" id="paramValue" >
                        &nbsp; <button class="btn btn-success" onclick="findUser(1)">查询</button>
                    </div>
                </div>
            </div>

                <%--分割线--%>
            <div class="separator_line">
            </div>

                <div class="form-group">
                    <div class="col-sm-10">
                        <button class="btn btn-success" id="showRecords">购买纪录</button>
                        <button class="btn btn-success" id="showAccounts">充值纪录</button>
                    </div>
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

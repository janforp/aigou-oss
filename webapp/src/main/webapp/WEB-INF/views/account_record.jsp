<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="充值纪录"/>
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


            //总页数量:request带来
            var total = ${totalPage};
            var userId= ${userId};
            //要找的页
            function findRecord(pageNum) {

                $.ajax({

                    url     :       "/c/page/console/auth/buyAndRecharge/showAccountByPage",
                    type    :       "POST",
                    dataType:       "JSON",
                    data    :       {
                        userId  :   userId,
                        pageNum :   pageNum
                    },
                    success :       function (data) {

                        if(data.success) {
                            var accounts = data.bean.accounts;

                            //先把之前的tbody中的内容移除
                            $("#tbody").html("");
                            //再循环列表添加数据
                            var str = "";
                            for(var i=0; i<accounts.length; i++) {
                                str += "<tr>"+
                                        "<td>"+(i+1)+"</td>"+
                                        "<td>"+accounts[i].type+"</td>"+
                                        "<td>"+accounts[i].pay+"</td>"+
                                        "<td>"+accounts[i].content+"</td>"+
                                        "<td>"+accounts[i].money+"</td>"+
                                        "<td>"+accounts[i].time+"</td>"+
                                        "</tr>"
                            }
                            $("#tbody").html(str);
                            //把显示的页面设置为改变之后的页面
                            $("#pageNow").text(pageNum);

                        }
                    },
                    error   :       function () {
                        tips.err("出错了",2000);
                    }



                });
            };

            function pageBefore() {

                var pageNow = $("#pageNow").text();
                var page = parseInt(pageNow);

                if(page==1){
                    tips.err("当前是第一页",2000);
                    return;
                }

                findRecord(page-1);
            };

            function pageAfter() {

                var pageNow = $("#pageNow").text();
                var page = parseInt(pageNow);


                if(page == total){
                    tips.err("当前是最后一页",2000);
                    return;
                }

                findRecord(page+1);

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
                <span>充值纪录</span>
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

            </div>

                <%--分割线--%>
            <div class="separator_line">
            </div>

            <div class="form-group">


            </div>


                <%--数据表格--%>
            <div style="width:100%; overflow:scroll;">
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                        <thead align="middle">
                            <td>序号</td>
                            <td>类型</td>
                            <td>支付</td>
                            <td>详情</td>
                            <td>金额</td>
                            <td>购买时间</td>
                        </thead>
                        <tbody id="tbody">
                        <c:forEach items="${accounts}" var="account" varStatus="status">
                            <tr>
                                <td>${status.index+1}</td>
                                <td>${account.type}</td>
                                <td>${account.pay}</td>
                                <td>${account.content}</td>
                                <td>${account.money}</td>
                                <td>${account.time}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan='2' style="border: 0">
                            </td>
                            <td colspan='2'  style="border: 0">
                                <div class='pagination-box pagination-right'>
                                    <ul class='pagination pagination-sm'>
                                        <li>
                                            <a onclick="pageBefore();" class='btn_prev'>
                                                <i class='glyphicon glyphicon-chevron-left'></i>
                                            </a>
                                        </li>
                                        <li>
                                            <span class='pagination-status'> <span id="pageNow">${pageNow}</span>/<span id="totalPage">${totalPage}</span></span>
                                        </li>
                                        <li>
                                            <a onclick="pageAfter();" class='btn_next'>
                                                <i class='glyphicon glyphicon-chevron-right'></i>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </td>
                            <td colspan='2'  style="border: 0">
                            </td>
                        </tr>
                        </tfoot>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>

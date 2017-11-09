<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="渠道查询"/>
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


            function  findPage(pageNum) {

                $.ajax({

                    url     :       "/c/page/console/auth/statistics/pageBeforeAndAfter",
                    type    :       "POST",
                    dataType:       "JSON",
                    data    :       {
                        pageNum :   pageNum
                    },
                    success :       function (data) {

                        if(data.success) {

                            var list = data.bean.list;

                            //先把之前的tbody中的内容移除
                            $("#tbody").html("");
                            //再循环列表添加数据
                            var str = "";
                            for(var i=0; i<list.length; i++) {
                                str += "<tr>"+
                                        "   <td><input type='radio' name='a' class= 'cell' type='button' userId= '"+list[i].time+"' /></td>" +
                                            "<td>"+(i+1)+"</td>"+
                                            "<td>"+list[i].time+"</td>"+
                                            "<td>"+list[i].dayNewUser+"</td>"+
                                            "<td>"+list[i].dayNewUserCharge+"</td>"+
                                            "<td>"+list[i].dayTotalCharge+"</td>"+
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

                var total = ${totalPage};

                var pageNow = $("#pageNow").text();
                var page = parseInt(pageNow);

                if(page==1){
                    tips.err("当前是第一页",2000);
                    return;
                }

                findPage(page-1);
            };

            function pageAfter() {
                var total = ${totalPage};

                var pageNow = $("#pageNow").text();
                var page = parseInt(pageNow);

                if(page == total){
                    tips.err("当前是最后一页",2000);
                    return;
                }

                findPage(page+1);
            };

            //选定一天查看
            $(document).on("click","#showDetail",function () {

                var $record = $("input[type='radio']:checked");

                var time = $record.attr("time");

                if (time == null || time == "") {
                    tips.err("请选择一条纪录查看", 2000);
                    return;
                }

                window.open("/c/page/console/auth/statistics/packageAndChannel?time="+time);
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
                <span>渠道查询</span>
                <span style="float:right;">总用户:${totalUser}<label style="margin-left: 10px;margin-right: 20px">人</label>总充值:${totalMoney}<label style="margin-left: 10px;margin-right: 30px">元</label></span>
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

                <div class="form-group">
                    <div class="col-sm-10">
                        <button class="btn btn-success" id="showDetail">详细</button>
                    </div>
                </div>

            </div>


                <%--数据表格--%>
            <div style="width:100%; overflow:scroll;">
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                        <thead align="middle">
                            <td colspan="2">序号</td>
                            <td>日期</td>
                            <td>新增用户</td>
                            <td>新增用户充值</td>
                            <td>总充值</td>
                        </thead>
                        <tbody id="tbody">
                        <c:forEach items="${list}" var="list" varStatus="status">
                            <tr>
                                <td><input class='cell' type='radio' name='a' time='${list.time}'/></td>
                                <td>${status.index+1}</td>
                                <td>${list.time}</td>
                                <td>${list.dayNewUser}</td>
                                <td>${list.dayNewUserCharge}</td>
                                <td>${list.dayTotalCharge}</td>
                            </tr>
                        </c:forEach>
                        </tbody>

                        <tfoot>
                        <tr>
                            <td colspan='6'  style="border: 0">
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
                        </tr>
                        </tfoot>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>

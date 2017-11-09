<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="当日统计"/>
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
        </style>

    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">


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
                <span><label style="margin-right: 10px">${time}</label><label>日详情</label></span>
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


            </div>


                <%--数据表格--%>
            <div style="width:100%; overflow:scroll;">
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                        <thead align="middle">
                            <td>包名</td>
                            <td>渠道</td>
                            <td>新增用户<label style="margin-right: 10px">(人)</label></td>
                            <td>新增用户充值<label style="margin-right: 10px">(元)</label></td>
                            <td>总充值<label style="margin-right: 10px">(元)</label></td>
                        </thead>
                        <tbody id="tbody">
                        <c:forEach items="${list}" var="list" varStatus="status">
                            <tr>
                                <td>${list.packageName}</td>
                                <td>${list.channelName}</td>
                                <td>${list.dayNewUser}</td>
                                <td>${list.dayNewUserCharge}</td>
                                <td>${list.dayTotalCharge}</td>
                            </tr>
                        </c:forEach>
                        </tbody>

                        <tfoot>
                        </tfoot>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>

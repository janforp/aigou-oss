<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="商品列表"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/goods_list.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/goods_list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" onclick="updateGoods();">修改</button>
                <button class="btn btn-success" onclick="deleteGoods();">删除</button>
                <button class="btn btn-success" onclick="goodsDetail();">详情</button>
                <select id="status" style="width: 80px;height: 40px;margin-left: 40px;">
                    <option value="0">已结束</option>
                    <option value="1">进行中</option>
                </select>
                <label style="margin-left: 40px;margin-right: 20px;font-size: large;">商品名:</label>
                <input type="text" id="title" placeholder="输入商品名" style="height: 37px;width: 175px;">
                <input type="text" id="pageSize" style="margin-left: 25px;margin-right: 25px;height: 37px;width: 175px;" placeholder="输入数字,默认15,按回车">
                <button class="btn btn-default" type="button" id="listByPageSize">Go!</button>
                <span id="pageSizeAlert" style="color: red;margin-left: 20px;"></span>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <br>
            <div>
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <thead>
                    <tr>
                        <th>选择</th>
                        <th>ID</th>
                        <th>SID</th>
                        <th>标题</th>
                        <th>总价</th>
                        <th>云价格</th>
                        <th>期数</th>
                        <th>最大期数</th>
                        <th>缩略图</th>
                        <th>状态</th>
                    </tr>
                    </thead>
                    <tbody id="goodsBody">

                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="10">
                            <button id="pageBefore" style="background-color: white;"><span style="color: blue;font-size: large">上一页</span></button>
                            <span id="pageNow">0</span>
                            <span style="margin-left: 5px;margin-right: 5px;">/</span>
                            <span id="totalPage">0</span>
                            <button id="pageAfter" style="background-color: white;"><span style="color: blue;font-size: large">下一页</span></button>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>

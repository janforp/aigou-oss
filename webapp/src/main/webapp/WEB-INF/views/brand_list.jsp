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
        <script src="/methods/brand_list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" onclick="updateBrand();">修改</button>
                <button class="btn btn-success" onclick="deleteGoods();">删除</button>
                <button class="btn btn-success" onclick="addBrand();">添加</button>
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
                        <th>品牌名称</th>
                        <th>所属栏目</th>
                        <th>排序</th>
                    </tr>
                    </thead>
                    <tbody id="goodsBody">

                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="5">
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

            <div id="deleteAlert">
                <h1>确定要删除吗?</h1>
                <button class="btn btn-info">取消</button>
                <button class="btn btn-danger">删除</button>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>

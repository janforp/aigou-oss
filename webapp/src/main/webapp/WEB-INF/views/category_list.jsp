<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="栏目管理"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/category_list.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/category_list.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <button class="btn btn-success" onclick="updateCategory();">修改</button>
                <button class="btn btn-success" onclick="deleteCategory();">删除</button>
                <button class="btn btn-success" onclick="addCategory();">添加栏目</button>
            </div>
        </div>
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <br>
            <div>
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                    <thead>
                    <tr>
                        <th>选择</th>
                        <th>栏目ID</th>
                        <th>栏目名称</th>
                        <th>图片地址</th>
                        <th>排序</th>
                    </tr>
                    </thead>
                    <tbody id="categoryBody">
                        <c:forEach items="${vo}" var="cate">
                                <tr>
                                    <td><input type="checkbox" value="${cate.cateid}"></td>
                                    <td>${cate.cateid}</td>
                                    <td>${cate.name}</td>
                                    <td>${cate.url}</td>
                                    <td>${cate.order}</td>
                                </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
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

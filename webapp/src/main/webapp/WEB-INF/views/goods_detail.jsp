<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="${vo.title}"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/goods_detail.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>${vo.title}</span>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">
            <div>
                <h3>${vo.title}</h3>
            </div>
            <div>
                <h3>${vo.title2}</h3>
            </div>
            <br>
            <div>
                <ul id = "pic">
                    <c:forEach items="${vo.pics}" var="pic">
                        <li>
                            <img src="${pic}">
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <br>
            <div id="content">
                ${vo.content}
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>

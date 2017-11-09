<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="修改栏目:${vo.name},栏目ID:${vo.cateid}"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/update_category.js" type="text/javascript"></script>
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
                <button class="btn btn-primary">保存修改</button>
            </div>
        </div>
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px;">*</span>
                    <label style="margin-right: 20px">栏目&nbsp;&nbsp;&nbsp;&nbsp;ID:</label>
                    <input type="text" id="cateId" value="${vo.cateid}" disabled style="height: 30px;width: 140px;margin-left: 40px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px;">*</span>
                    <label style="margin-right: 20px">栏目名称:</label>
                    <input type="text" id="name" value="${vo.name}"  style="height: 30px;width: 140px;margin-left: 40px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px;">*</span>
                    <label style="margin-right: 20px">栏目排序:</label>
                    <input type="text" id="order" value="${vo.order}"  style="height: 30px;width: 140px;margin-left: 40px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px;">*</span>
                    <label style="margin-right: 20px">图片地址:</label>
                    <input type="text" id="url" value="${vo.url}"   style="height: 30px;width: 400px;margin-left: 40px;"/>
                </span>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="修改品牌信息"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/update_brand.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="/methods/add_brand.js" type="text/javascript"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <button class="btn btn-link" onclick=window.open("/c/page/console/auth/goods/brandList");>品牌管理</button>
            <span>|</span>
            <button class="btn btn-link" onclick=window.open("/c/page/console/auth/goods/addBrandPage"); >添加品牌</button>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">
            <div class="separator_line">
            </div>
            <div id="updateDiv">
                <div  class="collapse navbar-collapse">
                    <label>所属栏目:</label>
                    <button class="btn btn-link" id="cate"><span style="color: red;font-size: large;">[继续添加所属栏目]</span></button>
                    <ul id="cateUl">

                    </ul>
                </div>
                <br>
                <div class="collapse navbar-collapse">
                    <span>
                        <label style="margin-right: 60px;color: black;font-size:large"> 品牌名称:</label>
                        <input type="text" id="brandName"   />
                    </span>
                </div>
                <br>
                <div class="collapse navbar-collapse">
                    <span>
                        <label style="margin-right: 60px;color: black;font-size:large"> 排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序:</label>
                        <input type="text" id="brandOrder"  />
                    </span>
                </div>
                <br>
                <div class="collapse navbar-collapse">
                    <span>
                        <label  style="margin-right: 60px;color: black;font-size:large;">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </label>
                        <button class="btn" id="addBrand">添加</button>
                    </span>
                </div>
            </div>
            <!--添加栏目弹出框 -->
            <div class="cateAlert">
                <div class="innerCateAlert">
                    <h3>选择所属分类栏目</h3>
                    <a href="javascript:void(0);"><span class="closeAlert">[关闭]</span></a>
                    <br>
                    <label style="margin-left: 50px;"><span style="font-size: large;">选择栏目:</span></label>
                    <select id="selectCate">
                        <option value="0">≡ 请选择分类 ≡</option>

                    </select>
                    <br>
                    <br>
                    <button class="btn" id="addCate" style="margin-left: 50px;">确定</button>
                </div>
            </div>

        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>

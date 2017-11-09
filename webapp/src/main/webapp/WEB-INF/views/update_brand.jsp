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
        <script src="/methods/update_brand.js" type="text/javascript"></script>
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
                        <c:forEach items="${vo.idToName}" var="cate">
                            <li cateId = '${cate.cateid}'>
                                <span>
                                    ${cate.name}<button class="btn btn-link" ><span>x</span></button>
                                    <input type="hidden" value="${cate.cateid}">
                                </span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <br>
                <div class="collapse navbar-collapse">
                    <span>
                        <label style="margin-right: 60px;color: black;font-size:large"> 品牌&nbsp;&nbsp;&nbsp;ID:</label>
                        <input type="text" id="brandId" value="${vo.id}" disabled />
                    </span>
                </div>
                <br>
                <div class="collapse navbar-collapse">
                    <span>
                        <label style="margin-right: 60px;color: black;font-size:large"> 品牌名称:</label>
                        <input type="text" id="brandName" value="${vo.name}"  />
                    </span>
                </div>
                <br>
                <div class="collapse navbar-collapse">
                    <span>
                        <label style="margin-right: 60px;color: black;font-size:large"> 排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序:</label>
                        <input type="text" id="brandOrder" value="${vo.order}" />
                    </span>
                </div>
                <br>
                <div class="collapse navbar-collapse">
                    <span>
                        <label  style="margin-right: 60px;color: black;font-size:large;">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </label>
                        <button class="btn" id="updateBrand">修改</button>
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

                        <%--<option value="5">手机数码</option>--%>
                        <%--<option value="17">&nbsp;├─ iPhone</option>--%>
                        <%--<option value="18">&nbsp;├─ 小米</option>--%>
                        <%--<option value="19">&nbsp;├─ 华为</option>--%>
                        <%--<option value="32">&nbsp;├─ 三星</option>--%>
                        <%--<option value="39">&nbsp;└─ 佳能</option>--%>

                        <%--<option value="6">虚拟产品</option>--%>
                        <%--<option value="23">&nbsp;├─ 购物卡</option>--%>
                        <%--<option value="24">&nbsp;├─ 话费充值</option>--%>
                        <%--<option value="31">&nbsp;└─ 红包</option>--%>

                        <%--<option value="12">生活Life</option>--%>
                        <%--<option value='25'>&nbsp;├─ 美食</option>--%>
                        <%--<option value='40'>&nbsp;└─ 娱乐</option>--%>

                        <%--<option value="13">电脑办公</option>--%>
                        <%--<option value='20'>&nbsp;├─ 笔记本</option>--%>
                        <%--<option value='21'>&nbsp;├─ 平板电脑</option>--%>
                        <%--<option value='22'>&nbsp;├─ 配件设备</option>--%>
                        <%--<option value='44'>&nbsp;└─ 台式机</option>--%>

                        <%--<option value="14">家电</option>--%>
                        <%--<option value='28'>&nbsp;├─ 家电</option>--%>
                        <%--<option value='33'>&nbsp;└─ 汽车</option>--%>

                        <%--<option value="15">其它</option>--%>
                        <%--<option value='30'>&nbsp;├─ 包箱皮具</option>--%>
                        <%--<option value='37'>&nbsp;├─ 粮油</option>--%>
                        <%--<option value='38'>&nbsp;├─ 消耗品</option>--%>
                        <%--<option value='41'>&nbsp;└─ 金</option>--%>
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

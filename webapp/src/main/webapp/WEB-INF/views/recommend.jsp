<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="推荐"/>
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
        <script>
            //删除推荐商品
            $(document).on("click","#delete",function () {

                var checkedNode = $("#recommendList").find("option:selected");
                var shopId =  checkedNode.val();
                var title = checkedNode.text().trim();
                if (shopId == null || shopId == '0') {
                    tips.err("请选择",2000);
                    return;
                }
                $.ajax({
                    url     :       "/c/page/console/auth/recommend/delete",
                    type    :       "POST",
                    dataType:       "JSON",
                    data    :       {sid:shopId},
                    success :       function (data) {
                                        if (data.success) {

                                            checkedNode.remove();

                                            var recommend = $("#shopList");
                                            var newOption = new Option(title,shopId)
                                            recommend.append(newOption);

                                            tips.suc("删除成功",2000);

                                        }else {
                                            tips.err(data.msg,2000);
                                        }
                    },
                    error   :       function () {

                    }
                });
            });

            //添加推荐商品
            $(document).on("click","#add",function () {

                var checkedNode = $("#shopList").find("option:selected");
                var shopId =  checkedNode.val();
                var title = checkedNode.text().trim();
                if (shopId == null || shopId == '0') {
                    tips.err("请选择",2000);
                    return;
                }
                $.ajax({
                    url     :       "/c/page/console/auth/recommend/add",
                    type    :       "POST",
                    dataType:       "JSON",
                    data    :       {sid:shopId,
                                     title:title
                                    },
                    success :       function (data) {
                        if (data.success) {
                            var recommend = $("#recommendList");
                            var newOption = new Option(title,shopId)
                            recommend.append(newOption);
                            checkedNode.remove();
                            tips.suc("添加成功",2000);
                        }else {
                            tips.err(data.msg,2000);
                        }
                    },
                    error   :       function () {

                    }
                });
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
                <span>推荐</span>
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
                <p><span style="color: blue;font-size: 20px">推荐中的商品:</span></p>
                <select id="recommendList" style="height: 50px;width: 500px">
                    <option value="0" >请选择</option>
                    <c:forEach items="${lists}" var="shop">
                        <option value="${shop.shopId}" name="shop">${shop.shopTitle}</option>
                    </c:forEach>
                </select>
                <button id="delete" class="btn btn-success add_submit_btn" style="height: 50px">从推荐商品中移除</button>
                    <%--分割线--%>
                <div class="separator_line" style="margin-bottom: 50px">
                </div>
                <%--<div class="form-group">--%>
            <%--</div>--%>
                <%--数据表格--%>
            <%--<div style="width:100%;">--%>
                <p><span style="color: blue;font-size:  20px">未推荐的商品:</span></p>
                <span style="overflow-x: scroll; width: 1000px">
                    <select  id="shopList" style="height: 50px;width: 500px">
                        <option value="0">请选择</option>
                        <c:forEach items="${recommends}" var="shop">
                            <option value="${shop.shopId}" name="shop" style="overflow-x: scroll;width: 500px">${shop.shopTitle}</option>
                        </c:forEach>
                    </select>
                </span>
                <button id="add" class="btn btn-success add_submit_btn" style="height: 50px">添加到推荐商品列</button>
            </div>

        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>

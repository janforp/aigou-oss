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
        <script src="${BASE_PATH}/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="${BASE_PATH}/client/js/base/validation.js" type="text/javascript"></script>
        <script src="${BASE_PATH}/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
        <script>
            //点击添加一个新项目到banner(discover)
            $(document).on("click","#addButton",function () {
                //显示添加隐藏框
                $("#add").slideDown(500);

            });
            //点击取消隐藏 添加框
            function closeEditDiv() {
                //显示添加隐藏框
                $("#add").hide(500);
            };
            //点击保存,新增banner
            function addBanner() {

                var title = $("#add_title").val();
                var url = $("#add_url").val();
                var order = $("#add_order").val();
                var desc = $("#add_desc").val();
                var img = $("#addImg").val();
                if (title == null ||title.trim().length ==0){
                    tips.err("标题必填",2000);
                    return;
                }
                if (order == null ||order.trim().length ==0 || isNaN(order)){
                    tips.err("排序必填且为数字",2000);
                    return;
                }
                $.ajax({
                    url  :   "/c/page/console/auth/discover/add",
                    type :   "POST",
                    dataType:"JSON",
                    data :   {
                        discoverTitle    :   title,
                        discoverUrl      :   url,
                        discoverOrder    :   order,
                        discoverDescription   :   desc,
                        discoverImg      :   img
                    },
                    success  :   function (data) {

                        if (data.success) {
                            //马上在列表上显示
                            var newTr = "";
                            newTr +=
                                    "<tr>"+
                                    "<td class='td-vm'>"+
                                    "<label class='id_checkbox img_checkbox_label in-td-line'>"+
                                    "<i class='icon_checkbox'></i>"+
                                    "<input type='checkbox' class='img_checkbox row-checkbox' />"+
                                    "</label>"+
                                    "</td>"+
                                    "<td class='td-vm'>"+title+"</td>"+
                                    "<td class='td-vm'>"+url+"</td>"+
                                    "<td class='td-vm'>"+order+"</td>"+
                                    "<td class='td-vm'><img style='height: 60px;width: 60px;border: 0px' src='"+img+"'/></td>"+
                                    "<td class='td-vm'>"+desc+"</td>"+
                                    "</tr>";
                            $("tbody").append(newTr);
                            //提示
                            tips.suc("添加成功",2000);
                            //隐藏
                            $("#add").hide(500);
                        }
                    },
                });
            };

            //点击修改弹出修改隐藏框
            $(document).on("click","#editButton",function () {
                //必须要选择一项,取到数据,提前放到修改框中
                var $updateBanner = $("input[type='checkbox']:checked");
                if ($updateBanner == null || $updateBanner.length == 0 || $updateBanner.length > 1) {
                    tips.err("请选择一个",2000);
                    return;
                }
                //获取选择项的: title,url,order,shopid,img
                var bannerId = $updateBanner.val();
                var $selectedTd = $updateBanner.parent().parent();
                var $bannerTitle = $selectedTd.next();
                var bannerTitle = $bannerTitle.text();
                var $bannerUrl = $bannerTitle.next();
                var bannerUrl = $bannerUrl.text();
                var $bannerOrder = $bannerUrl.next();
                var bannerOrder = $bannerOrder.text();
                var $imgTd = $bannerOrder.next();
                var $img = $imgTd.find("img");
                var bannerImg = $img.attr("src");
                var $descTd = $imgTd.next();
                var desc = $descTd.text();

                //把值放到隐藏框中
                $("#update_title").val(bannerTitle);
                $("#update_url").val(bannerUrl);
                $("#update_order").val(bannerOrder);
                $("#update_desc").val(desc);
                //在显示的时候,怎么才能让value=shopId的option是选中状态?

                $("#update_img").attr("src",bannerImg);
                //显示添加隐藏框
                $("#update").attr("bannerId",bannerId);
                $("#update").slideDown(500);

            });
            //点击取消隐藏 添加框
            function closeupdateDiv() {
                //显示添加隐藏框
                $("#update").hide(500);
            };
            //点击保存,修改banner
            function updateBanner() {

                var bannerId = $("#update").attr("bannerId");
                var title = $("#update_title").val();
                var url = $("#update_url").val();
                var order = $("#update_order").val();
                var desc = $("#update_desc").val();
                var img = $("#updateImg").val();
                if (img == null || img.trim().length==0) {
                    var $update_img = $("#update_img");
                    img = $update_img.attr("src");
                }

                if (title == null ||title.trim().length ==0){
                    tips.err("标题必填",2000);
                    return;
                }
                if (order == null ||order.trim().length ==0 || isNaN(order)){
                    tips.err("排序必填且为数字",2000);
                    return;
                }
                $.ajax({
                    url  :   "/c/page/console/auth/discover/update",
                    type :   "POST",
                    dataType:"JSON",
                    data :   {
                        discoverTitle    :   title,
                        discoverUrl      :   url,
                        discoverOrder    :   order,
                        discoverDescription:   desc,
                        discoverImg      :   img,
                        discoverId       :   bannerId
                    },
                    success  :   function (data) {

                        if (data.success) {
                            //马上在列表上显示

                            //提示
                            tips.suc("添加成功",2000);
                            //隐藏
                            $("#add").hide(500);
                            //刷新更新之后的列表
                            setTimeout(function () {
                                window.open("/c/page/console/auth/discover/discoverList");
                            },2000);
                        }
                    },
                });
            };

            //点击删除按钮
            $(document).on("click","#deleteButton",function () {

                var $input = $("input[type='checkbox']:checked");

                var bannerIds = $("input[type='checkbox']:checked").map(function(index,elem) {
                    return $(elem).val();
                }).get().join(',');

                if ($input == null || $input.length == 0) {
                    tips.err("至少选择一个删除",2000);
                    return;
                }
                //发送请求
                $.ajax({

                    url     :       "/c/page/console/auth/discover/delete",
                    type    :       "POST",
                    dataType:       "JSON",
                    data    :       {   bannerIds      :   bannerIds},
                    success :       function (data) {
                        if (data.success) {
                            $input.parent().parent().parent().remove();
                            tips.suc("删除成功",2000);

                        }else {
                            tips.err(data.msg);
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
                <span>Banner</span>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">
            <nav class="navbar navbar-default sub-title-bar" role="navigation">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse">
                        <button class="btn btn-default btn-sm navbar-btn " id="addButton">添加</button>
                        <button class="btn btn-default btn-sm navbar-btn " id="editButton">修改</button>
                        <button class="btn btn-default btn-sm navbar-btn " id="deleteButton">删除</button>
                    </div>
                </div>
            </nav>
            <table class="table table-striped table-hover table-bordered table-row-check">
                <thead>
                <tr>
                    <th class="w36">
                        <label class="img_checkbox_label in-td-line">
                            <i class="icon_checkbox"></i>
                            <input type="checkbox" class="img_checkbox row-check-all">
                        </label>
                    </th>
                    <th class="w100">标题</th>
                    <th class="w100">链接</th>
                    <th class="w100">排序</th>
                    <th class="w100">图片</th>
                    <th class="w100">描述</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${discovers}" var="discover">
                    <tr data-id="${discover.discoverId}">
                        <td class="td-vm">
                            <label class="id_checkbox img_checkbox_label in-td-line">
                                <i class="icon_checkbox"></i>
                                <input type="checkbox" class="img_checkbox row-checkbox" value="${discover.discoverId}"/>
                            </label>
                        </td>
                        <td class="td-vm">${discover.discoverTitle}</td>
                        <td class="td-vm">${discover.discoverUrl}</td>
                        <td class="td-vm">${discover.discoverOrder}</td>
                        <td class="td-vm"><img src="${discover.discoverImg}" <c:if test="${discover.discoverImg != ''}">style="height: 60px;width: 60px;border: 0px"</c:if>/></td>
                        <td class="td-vm">${discover.discoverDescription}</td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                </tfoot>
            </table>
        </div>
        <%--主内容显示区 end --%>
        <%--添加隐藏框 start --%>
        <div id="add" style="display: none;margin-top: 50px; position: absolute;top: 50px;left: 45%;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">添加发现项目</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label >标题</label>
                                <input type="text" id="add_title" class="form-control" />
                            </div>
                            <div class="form-group col-xs-6">
                                <label>链接</label>
                                <input type="text" id="add_url" class="form-control"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label>排序</label>
                                <input type="text" id="add_order" class="form-control"/>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label>描述</label>
                                    <textarea   id="add_desc" class="form-control"> </textarea>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-xs-6" >
                                <div class="col2">

                                    <span style="display: inline;">
                                        <label style="width: 100px; text-align: left"></label>
                                        <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                                style="margin-left: 20px"
                                                action="/c/p/picture/upload" filesizelimit="128000"
                                                filetypelimit="jpg,png,gif,jpeg" filenums="photo">图标</button>
                                        <img class="loadingPic" obj="user_photo" alt="" id="edit_img1" src="/client/img/loading.gif" />
                                        <input type="hidden" name="img" id="addImg" obj="user_photo" />
                                    </span>
                                    <img class="user_photo" id="edit_img" style="height: 60px;width: 60px;border: 0px;margin-left: 20px">
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal" onclick="closeEditDiv();">取消</button>
                            <button type="submit" id="updatePay" onclick="addBanner();" class="btn btn-success add_submit_btn">保存</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--添加隐藏框 end --%>
        <%--修改隐藏框 start --%>
        <div id="update" style="display: none;margin-top: 50px; position: absolute;top: 50px;left: 45%;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">修改banner</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label >标题</label>
                                <input type="text" id="update_title" class="form-control" />
                            </div>
                            <div class="form-group col-xs-6">
                                <label>链接</label>
                                <input type="text" id="update_url" class="form-control"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label>排序</label>
                                <input type="text" id="update_order" class="form-control"/>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label>描述</label>
                                    <textarea   id="update_desc" class="form-control"> </textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6" >
                                <div class="col2">

                                    <span style="display: inline;">
                                        <label style="width: 100px; text-align: left"></label>
                                        <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                                style="margin-left: 20px"
                                                action="/c/p/picture/upload" filesizelimit="128000"
                                                filetypelimit="jpg,png,gif,jpeg" filenums="photo">图标</button>
                                        <img class="loadingPic" obj="user_photo" alt="" id="update_img1" src="/client/img/loading.gif" />
                                        <input type="hidden" name="img" id="updateImg" obj="user_photo" />
                                    </span>
                                    <img class="user_photo" id="update_img" style="height: 60px;width: 60px;border: 0px;margin-left: 20px">
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal" onclick="closeupdateDiv();">取消</button>
                            <button type="submit"  onclick="updateBanner();" class="btn btn-success add_submit_btn">保存</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--修改隐藏框 end --%>

    </tiles:putAttribute>
</tiles:insertTemplate>

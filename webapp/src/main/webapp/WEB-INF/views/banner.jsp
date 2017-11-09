<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="Banner"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            .table tbody td {
                text-align: center;
                white-space:nowrap;
            }

            .loadingPic {
                display: none;
            }

            .user_photo {
                border:solid 1px #ccc;
                width: 80px;
                height: 80px;
            }

        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="${BASE_PATH}/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="${BASE_PATH}/client/js/base/validation.js" type="text/javascript"></script>
        <script src="${BASE_PATH}/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
       <script>
           //点击添加一个新项目到banner
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
               var shopId = $("#add_shop").val();
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
                   url  :   "/c/page/console/auth/banner/add",
                   type :   "POST",
                   dataType:"JSON",
                   data :   {
                       bannerTitle    :   title,
                       bannerUrl      :   url,
                       bannerOrder    :   order,
                       shopId   :   shopId,
                       bannerImg      :   img
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
                                    "<input type='checkbox' class='img_checkbox row-checkbox'  shopId='"+shopId+"'/>"+
                                    "</label>"+
                                    "</td>"+
                                    "<td class='td-vm'>"+title+"</td>"+
                                    "<td class='td-vm'>"+url+"</td>"+
                                    "<td class='td-vm'>"+order+"</td>"+
                                    "<td class='td-vm'><img style='height: 60px;width: 60px;border: 0px' src='"+img+"'/></td>"+
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
               var bannerId = $updateBanner.attr("bannerId");
               var shopId = $updateBanner.attr("shopId");

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

               //把值放到隐藏框中
               $("#update_title").val(bannerTitle);
               $("#update_url").val(bannerUrl);
               $("#update_order").val(bannerOrder);
               $("#update_shop").val(shopId);
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
               var shopId = $("#update_shop").val();
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
                   url  :   "/c/page/console/auth/banner/update",
                   type :   "POST",
                   dataType:"JSON",
                   data :   {
                       bannerTitle    :   title,
                       bannerUrl      :   url,
                       bannerOrder    :   order,
                       shopId         :   shopId,
                       bannerImg      :   img,
                       bannerId       :   bannerId
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
                               window.open("/c/page/console/auth/banner/bannerList");
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

                   url     :       "/c/page/console/auth/banner/delete",
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
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${banners}" var="banner">
                            <tr data-id="${banner.bannerId}">
                                <td class="td-vm">
                                    <label class="id_checkbox img_checkbox_label in-td-line">
                                        <i class="icon_checkbox"></i>
                                        <input type="checkbox" class="img_checkbox row-checkbox" value="${banner.bannerId}" bannerId="${banner.bannerId}" shopId="${banner.shopId}"/>
                                    </label>
                                </td>
                                <td class="td-vm">${banner.bannerTitle}</td>
                                <td class="td-vm">${banner.bannerUrl}</td>
                                <td class="td-vm">${banner.bannerOrder}</td>
                                <td class="td-vm"><img src="${banner.bannerImg}" style="width: 300px;border: 0px"></td>
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
                        <h4 class="modal-title">添加到banner</h4>
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
                            <div class="form-group col-xs-6">
                                <label>商品</label><br>
                                <select name="add_shop" id="add_shop" style="height: 35px;width: 260px">
                                    <option value="0" >请选择</option>
                                    <c:forEach items="${shops}" var="shop">
                                        <option value="${shop.shopId}">${shop.bannerTitle}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <img class="user_photo" id="edit_img" style="width:100%; border: 0px;">
                            </div>
                            <div class="form-group col-xs-6" >
                                <div class="col2">
                                    <span style="display: inline;">
                                        <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                                style="margin-left: 20px"
                                                action="/c/p/picture/upload" filesizelimit="128000"
                                                filetypelimit="jpg,png,gif,jpeg" filenums="photo">图标</button>
                                        <img class="loadingPic" obj="user_photo" alt="" id="edit_img1" src="/client/img/loading.gif" />
                                        <input type="hidden" name="img" id="addImg" obj="user_photo" />
                                    </span>
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
                            <div class="form-group col-xs-6">
                                <label>商品</label><br>

                                <select  id="update_shop" style="height: 35px;width: 260px">
                                    <option value="0" >请选择</option>
                                    <c:forEach items="${all}" var="shop">
                                        <option value="${shop.shopId}">${shop.bannerTitle}</option>
                                    </c:forEach>
                                </select>

                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <img class="user_photo" id="update_img" style="width:100%; border: 0px;">
                            </div>
                            <div class="form-group col-xs-6" >
                                <div class="col2">
                                    <span style="display: inline;">
                                        <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                                style="margin-left: 20px"
                                                action="/c/p/picture/upload" filesizelimit="128000"
                                                filetypelimit="jpg,png,gif,jpeg" filenums="photo">图标</button>
                                        <img class="loadingPic" obj="user_photo" alt="" id="update_img1" src="/client/img/loading.gif" />
                                        <input type="hidden" name="img" id="updateImg" obj="user_photo" />
                                    </span>
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

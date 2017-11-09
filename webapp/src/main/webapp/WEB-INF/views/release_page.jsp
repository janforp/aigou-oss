<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="${CURRENT_FUNCTION.functionName}"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link href="${BASE_PATH}/client/css/page/add_mission.css" rel="stylesheet" type="text/css"/>
        <style>
            .loadingPic {
                display: none;
            }

            .user_photo {
                border:solid 1px #ccc;
                width: 80px;
                height: 80px;
            }

            ul li{
                list-style-type:none;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="${BASE_PATH}/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="${BASE_PATH}/client/js/base/validation.js" type="text/javascript"></script>
        <script src="${BASE_PATH}/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
        <script>

            $(function() {

                // 显示图片
                $(document).on("click", ".active_img", function () {
                    var imageUrl = $(this).attr("src");
                    var arrayImage = imageUrl.split(";");
                    var arrayStr = "";
                    for(var k=0; k<arrayImage.length; k++) {
                        arrayStr += "<img src='" + arrayImage[k] + "' style='width:100%; margin-top:20px;' />";
                    }
                    $("#image-detail").html(arrayStr);
                    $("#modal-showImage").modal();
                });

                // 删除图片
                $(document).on("click", ".delete_pic[obj='reImg']", function () {
                    $(this).parent().remove();
                    var pathArray = "";
                    $(".muti_box[obj='reImg']").find("img").each(function () {
                        var pathX = $(this).attr("src");
                        pathArray += pathArray ? ";" + pathX : pathX;
                    });
                    $("#txtReleaseImg").val(pathArray);
                });

                // 修改用户信息
                $(document).on("click", "#btnSaveUser", function () {

                    var userId = $("#txtUseId").val();
                    var userName = $("#txtUserName").val();
                    var userPhoto = $("#txtUserPhoto").val();

                    tips.loading();

                    $.ajax({
                        url: '/c/page/console/auth/order/update_user',
                        type: 'POST',
                        dataType: 'json',
                        data: {
                            userId: userId,
                            userName: userName,
                            userPhoto: userPhoto
                        },
                        success: function(data) {
                            if(data.success == true) {
                                tips.suc("修改成功", 2000);
                            }else {
                                tips.err(data.msg, 2000);
                            }
                        },
                        complete: function() {
                            tips.hideLoading();
                        },
                        error: function() {
                            tips.hideLoading();
                        }
                    });

                });

                // 晒单
                $(document).on("click", "#btnSave", function () {

                    var shopId = '${shopId}';
                    var userId = $("#txtUseId").val();
                    var title = $("#txtTitle").val();
                    var content = $("#txtContent").val();
                    var releaseImg = $("#txtReleaseImg").val();


                    if(releaseImg == "") {
                        alert("请选择晒单图片!");
                        return;
                    }

                    var imgs = releaseImg.split(";");

                    if(imgs.length > 6) {
                        alert("最多只能上传6张图片!");
                        return;
                    }

                    tips.loading();

                    $.ajax({
                        url: '/c/page/console/auth/order/release',
                        type: 'POST',
                        dataType: 'json',
                        data: {
                            shopId: shopId,
                            userId: userId,
                            title: title,
                            content: content,
                            releaseImg: releaseImg
                        },
                        success: function(data) {
                            if(data.success == true) {
                                tips.suc("晒单成功", 2000);
                            }else {
                                tips.err(data.msg, 2000);
                            }
                        },
                        complete: function() {
                            tips.hideLoading();
                        },
                        error: function() {
                            tips.hideLoading();
                        }
                    });

                });

            })

        </script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>晒单</span>
            </div>
        </div>

        <%-- 晒单 begin --%>
        <div style="margin-left: 2%; margin-right: 2%">

            <div class="editor-container">

                <%--输入内容区域--%>
                <div class="editor-body">

                    <div class="mission-merchant">
                        <h4>中奖用户信息</h4>
                        <div class="col1">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left">用户ID:</label>
                                <input id="txtUseId" type="text" style="width: 30%; border: 0" readonly="readonly" value="${user.userId}">
                            </span>
                        </div>
                        <div class="col1">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left">用户昵称:</label>
                                <input id="txtUserName" type="text" style="width: 30%;" value="${user.userName}">
                            </span>
                        </div>
                        <div class="col2">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left">头像:</label>
                                <img class="user_photo" src="${user.img}" >
                            </span>
                        </div>
                        <div class="col2">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left"></label>
                                <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image" action="/c/p/picture/upload" filesizelimit="128000" filetypelimit="jpg,png,gif,jpeg" filenums="photo">上传头像</button>
                                <img class="loadingPic" obj="user_photo" alt="" src="/client/img/loading.gif" />
                                <input type="hidden" id="txtUserPhoto" obj="user_photo" />
                            </span>
                        </div>
                        <div class="col1" style="margin-top: 20px">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left"></label>
                                <button class="btn btn-primary btn-sm" type="submit" id="btnSaveUser" autocomplete="off">提交保存</button>
                            </span>
                        </div>
                    </div>

                    <%--分割线--%>
                    <div class="separator_line">
                    </div>

                    <%--编辑--%>
                    <div class="mission-merchant">
                        <h4>添加晒单内容</h4>
                        <div class="col1">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left">标题:</label>
                                <input id="txtTitle" type="text" style="width: 70%">
                            </span>
                        </div>
                        <div class="col1">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left;">内容:</label>
                                <textarea id="txtContent" style="width: 70%; height: 100px;"></textarea>
                            </span>
                        </div>
                        <div class="col1">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left;">晒图:</label>
                                <button class="btn btn-success btn-sm" mode="button-upload-pic"
                                        uploadtype="image" action="/c/p/picture/upload"
                                        filesizelimit="128000" filetypelimit="jpg,png,gif,jpeg" filenums="m">上传图片
                                </button>
                                <input type="hidden" id="txtReleaseImg" obj="reImg" />
                                <ul class="muti_box" obj="reImg"></ul>
                                <img class="loadingPic" obj="reImg" alt="" src="/client/img/loading.gif" />
                            </span>
                        </div>
                    </div>
                </div>

                <%--提交按钮--%>
                <div class="editor-footer">
                    <button class="btn btn-primary btn-sm" type="submit" id="btnSave" autocomplete="off" style="float: right">保存</button>
                    <button class="btn btn-default btn-sm" onclick="window.close();" data-dismiss="modal" type="button" autocomplete="off" style="float: right; margin-right: 10px;">关闭</button>
                    <div style="height: 40px"></div>
                </div>

            </div>
        </div>
        <%-- 晒单 end --%>

        <%-- banner 图片显示 begin--%>
        <div id="modal-showImage" class="modal fade" aria-hidden="true" aria-labelledby="showImageModalLabel"
             role="dialog" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header border-width-0">
                        <button class="close" aria-hidden="true" data-dismiss="modal" type="button" autocomplete="off">
                            ×</button>
                        <h4 id="showImageModalLabel" class="modal-title">
                        </h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-horizontal">
                            <div class="form-group mb0">
                                <div class="col-sm-12 tc" id="image-detail" style="width: 100%">

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" data-dismiss="modal" type="button" autocomplete="off">
                            关闭</button>
                    </div>
                </div>
            </div>
        </div>
        <%-- banner 图片显示 end--%>

    </tiles:putAttribute>
</tiles:insertTemplate>

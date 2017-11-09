<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="${CURRENT_FUNCTION.functionName}"/>
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
        <%--<script src="${BASE_PATH}/client/js/account${RESOURCE_MIN}.js${RESOURCE_VERSION}"></script>--%>
        <script src="${BASE_PATH}/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="${BASE_PATH}/client/js/base/validation.js" type="text/javascript"></script>
        <script src="${BASE_PATH}/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
        <script>
            pg.savePayCallback = function (json) {
                if (json.success) {
                    $("#modal-add-account").modal("hide");
                    $("form *[type='submit']").prop("disabled", true);
                    util.loading();
                    setTimeout(function () {
                        location.href = util.url("/c/page/console/auth/pay/payList");
                    }, 10000);
                }
            }
        </script>
        <script>
            //点击修改按钮,弹出修改框
            $(document).on("click","#editButton",function () {

                var $input = $("input[type='checkbox']:checked");
                if ($input == null || $input.length == 0) {
                    tips.err("请选择一个修改",2000);
                    return;
                }
                if ($input.length >1){
                    tips.err("只能选择一个",2000);
                    return;
                }
                var shopId = $input.val();
                var type = $input.attr("type2");
                var xu = $input.attr("xu");
                var $tdInput = $input.parent().parent();

                var $tdName = $tdInput.next();
                var name = $tdName.text();

                var $tdContent = $tdName.next();
                var content = $tdContent.text();

                var $tdImg = $tdContent.next();
                var $img = $tdImg.find("img");
                var imgUrl = $img.attr("src");
                var $tdNo = $tdImg.next();
                var no = $tdNo.attr("no");

                //把这些值放到隐藏的div中
                $("#edit_name").val(name);
                $("#edit_xu").val(xu);
                $("#edit_content").val(content);
                $("#edit_type").val(type);
                $("#edit_img").attr("src",imgUrl);
                $("#edit_no").val(no);

                $("#edit").attr("shopId",shopId);
                //显示这个div
                $("#edit").slideDown(500);

            });
            function closeEditDiv() {
                $("#edit").hide();
            }

            //点击保存修改
            function updatePay() {

                var name = $("#edit_name").val();
                var xu = $("#edit_xu").val();
                var content = $("#edit_content").val();
                var type = $("#edit_type").val();
                var img = $("#uploadImg").val();
                //img若为空,则用之前的url
                if (img == null || img.trim().length == 0) {
                    img = $("#edit_img").attr("src");
                }
                var no = $("#edit_no").val();

                var shopId = $("#edit").attr("shopId");
                //发送请求
                $.ajax({

                    url     :       "/c/page/console/auth/pay/update",
                    type    :       "POST",
                    dataType:       "JSON",
                    data    :       {   id      :   shopId,
                                        name    :   name,
                                        xu      :   xu,
                                        content :   content,
                                        type    :   type,
                                        img     :   img,
                                        no      :   no
                                    },
                    success :       function (data) {
                                    if (data.success) {
                                        //把框隐藏
                                        $("#edit").hide();
                                        tips.suc("修改成功",2000);
                                        window.open ("/c/page/console/auth/pay/payList");

                                    }else {
                                        tips.err(data.msg);
                                    }
                    },
                    error   :       function () {
                        
                    }
                });
            }
            //点击删除
            $(document).on("click","#deleteButton",function () {

                var $input = $("input[type='checkbox']:checked");

                var shopIds = $("input[type='checkbox']:checked").map(function(index,elem) {
                    return $(elem).val();
                }).get().join(',');

                if ($input == null || $input.length == 0) {
                    tips.err("至少选择一个删除",2000);
                    return;
                }
                var shopId = $input.val();
                //发送请求
                $.ajax({

                    url     :       "/c/page/console/auth/pay/delete",
                    type    :       "POST",
                    dataType:       "JSON",
                    data    :       {   ids      :   shopIds},
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

            //这里只是取到上传图片的url,把他显示出来
            $(document).on("click","#addPay",function () {

                var uploadImg = $("#addIput").val();
                $("#addImg").val(uploadImg);

            });




        </script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <div class="main-bd">
            <nav class="navbar navbar-default sub-title-bar" role="navigation">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse">
                        <!--新增 -->

                        <c:if test="${f:hasAuth('/c/page/console/auth/pay/save')}">
                            <button class="btn btn-default btn-sm navbar-btn"
                                    data-param-name="id" data-row-name="<spring:message code='oss.pay'/>"
                                    data-callback="pg.savePayCallback"
                                    data-toggle="modal" data-target="#modal-add-account"
                                    data-backdrop="static" data-keyboard="false"

                                    role="button">
                                <i class="glyphicon glyphicon-plus-sign"></i>
                                <spring:message code="btn.add"/></button>
                        </c:if>
                        <!--修改 -->

                            <button class="btn btn-default btn-sm navbar-btn " id="editButton">修改</button>
                        <!--删除 -->

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

                    <th class="w100"><spring:message code="oss.pay.name"/></th>
                    <th class="w100"><spring:message code="oss.pay.content"/></th>
                    <th class="w100"><spring:message code="oss.pay.img"/></th>
                    <th class="w100"><spring:message code="oss.pay.userornot"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="v">
                    <tr data-id="${v.id}">
                        <td class="td-vm">
                            <label class="id_checkbox img_checkbox_label in-td-line">
                                <i class="icon_checkbox"></i>
                                <input type="checkbox" class="img_checkbox row-checkbox" value="${v.id}" type2="${v.type}" xu="${v.xu}"/>
                            </label>
                        </td>
                        <td class="td-vm">${v.name}</td>
                        <td class="td-vm">${v.content}</td>
                        <td class="td-vm"><img src="${v.img}" <c:if test="${v.img != ''}">style="height: 60px;width: 60px;border: 0px"</c:if>/></td>
                        <td class="td-vm" no = "${v.no}">
                            <c:choose>
                                <c:when test="${v.no == 0}">关闭</c:when>
                                <c:when test="${v.no == 1}">全部开启</c:when>
                                <c:when test="${v.no == 2}">IOS开启</c:when>
                                <c:when test="${v.no == 3}">Android开启</c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
                <tfoot>
                </tfoot>
            </table>
        </div>
    </tiles:putAttribute>
    <tiles:putAttribute name="modalArea">
        <!--新增 -->

        <c:if test="${f:hasAuth('/c/page/console/auth/pay/save')}">
            <div id="modal-add-account" aria-labelledby="modal-add-account-title" class="modal fade" tabindex="-1"
                 role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title"><spring:message code="oss.title.pay.add"/></h4>
                        </div>
                        <div class="modal-body">
                            <form role="form" id="paySaveForm" class="ajax-form v-form"
                                  ajax-callback="pg.savePayCallback"
                                  data-submit-btn=".add_submit_btn"
                                  action="${BASE_PATH}/c/page/console/auth/pay/save"
                                  method="post" enctype="multipart/form-data">
                                <div class="row">
                                    <div class="form-group col-xs-6">
                                        <label for="add_realName" id="add_realName-label"><spring:message
                                                code="oss.pay.name"/></label>
                                        <input type="text" class="form-control" id="add_realName" name="name"
                                               v-show-id="#add_realName-label">
                                    </div>
                                    <div class="form-group col-xs-6">
                                        <label for="xu"><spring:message code="oss.pay.xu"/></label>
                                        <input type="text" class="form-control" id="xu" name="xu"
                                               v-show-id="#add_realName-label">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-xs-6">
                                        <label for="content" id="add_loginName-label"><spring:message
                                                code="oss.pay.content"/></label>
                                        <input type="textarea" class="form-control" id="content" name="content"
                                               v-show-id="#add_loginName-label">
                                    </div>
                                    <div class="form-group col-xs-6">
                                        <label for="type" id=""><spring:message
                                                code="oss.pay.type"/></label>
                                        <input type="text" class="form-control" id="type" name="type"
                                               v-show-id="#add_loginName-label">
                                    </div>

                                </div>

                                <div class="row">
                                    <div class="form-group col-xs-6" >

                                        <div class="col2">
                                            <span style="display: inline;">

                                                <img class="user_photo" id="addImg" style="height: 60px;width: 60px;border: 0px;margin-left: 20px">

                                                <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                                        id="addPay"
                                                        action="/c/p/picture/upload" filesizelimit="128000"
                                                        filetypelimit="jpg,png,gif,jpeg" filenums="photo">支付图标
                                                </button>

                                                <img class="loadingPic" obj="user_photo"  alt="" src="/client/img/loading.gif" />
                                                <input type="hidden" id="addIput" name="img" obj="user_photo" />

                                            </span>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group col-xs-6">
                                        <label for="no" id="add_loginPwd-label"><spring:message
                                                code="oss.pay.showType"/></label><br>
                                        <select name="no" id="no">
                                            <option value="0" >关闭</option>
                                            <option value="1" >全部开启</option>
                                            <option value="2" >IOS开启</option>
                                            <option value="3" >Android开启</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="not-show">
                                    <input type="hidden" name="loginPwd" id="add_loginPwd_hidden"
                                           v-event-id="#add_loginPwd" v-show-id="#add_loginPwd-label"/>
                                    <input type="submit" class="add_submit_btn">
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal">
                                        <spring:message code="btn.cancel"/></button>
                                    <button type="submit" class="btn btn-success add_submit_btn">
                                        <spring:message code="btn.save"/></button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </c:if>
        <!--修改 -->

            <div id="edit" style="display: none;margin-top: 50px; position: absolute;top: 50px;left: 45%;">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <div class="modal-body">
                                    <div class="row">
                                        <div class="form-group col-xs-6">
                                            <label ><spring:message code="oss.pay.name"/></label>
                                            <input type="text" id="edit_name" class="form-control" />
                                        </div>
                                        <div class="form-group col-xs-6">
                                            <label><spring:message code="oss.pay.xu"/></label>
                                            <input type="text" id="edit_xu" class="form-control" v-show-id="#add_realName-label"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-xs-6">
                                            <label><spring:message code="oss.pay.content"/></label>
                                            <input type="textarea" id="edit_content" class="form-control"  v-show-id="#add_loginName-label"/>
                                        </div>
                                        <div class="form-group col-xs-6">
                                            <label ><spring:message code="oss.pay.type"/></label>
                                            <input type="text" class="form-control" id="edit_type" name="type" v-show-id="#add_loginName-label"/>
                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-6" >


                                            <div class="col2">

                                                <span style="display: inline;">
                                                    <img class="user_photo" id="edit_img" style="height: 60px;width: 60px;border: 0px;margin-left: 20px">

                                                    <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                                                            action="/c/p/picture/upload" filesizelimit="128000"
                                                            filetypelimit="jpg,png,gif,jpeg" filenums="photo">修改支付图标</button>
                                                    <img class="loadingPic" obj="user_photo" alt="" id="edit_img1" src="/client/img/loading.gif" />
                                                    <input type="hidden" name="img" id="uploadImg" obj="user_photo" />

                                                </span>
                                            </div>

                                        </div>

                                        <div class="form-group col-xs-6">
                                            <label for="edit_no" id="edit_loginPwd-label"><spring:message
                                                    code="oss.pay.showType"/></label><br>
                                            <select name="no" id="edit_no">
                                                <option value="0" >关闭</option>
                                                <option value="1" >全部开启</option>
                                                <option value="2" >IOS开启</option>
                                                <option value="3" >Android开启</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default auto-focus-btn" data-dismiss="modal" onclick="closeEditDiv();">
                                            <spring:message code="btn.cancel"/></button>
                                        <button type="submit" id="updatePay" onclick="updatePay();" class="btn btn-success add_submit_btn">
                                            <spring:message code="btn.save"/></button>
                                    </div>
                        </div>

                    </div>
                </div>
            </div>
    </tiles:putAttribute>
</tiles:insertTemplate>
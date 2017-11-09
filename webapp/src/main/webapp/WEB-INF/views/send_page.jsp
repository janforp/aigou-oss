<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="发货"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
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


        <script>
            //若是虚拟商品,则显示手机充值部分内容,否则隐藏
            $(function () {

                var virtual = "${virtual}";
                if (virtual==1){
                    $("#virtual").hide();
                }
                if (virtual==0){
                    $("#expressInfo").hide();
                }
            });
            //点击提交,更改订单状态
            function commitExpress() {

                var expressName = $("#expressName").val();
                var expressCode = $("#expressCode").val();
                var expressPrice= $("#expressPrice").val();
                var userId = "${user.userId}";
                var shopId = "${shopId}";
                var orderCode= "${orderCode}";
                var phone  = "${user.otherPhone}";

                $.ajax({

                    url     :       "/c/page/console/auth/express/saveExpressInfo",
                    type    :       "POST",
                    dataType:       "JSON",
                    data    :       {
                                        expressName :   expressName,
                                        expressCode :   expressCode,
                                        expressPrice:   expressPrice,
                                        userId      :   userId,
                                        shopId      :   shopId,
                                        orderCode   :   orderCode,
                                        phone       :   phone
                                    },
                    success :       function(data){
                                        if(data.success == true ) {
                                            tips.suc("保存快递信息成功", 2000);
                                        }
                                    },
                    error   :       function () {

                                    }
                });
            };

            //点击查看中奖人的充值纪录
            $("#rechargeRecord").click(function () {

                var userId = "${user.userId}";
                window.open("/c/page/console/auth/buyAndRecharge/showAccounts?&userId="+userId);
            });

            //点击查看中奖人的购买纪录
            $("#buyRecord").click(function () {

                var userId = "${user.userId}";
                window.open("/c/page/console/auth/buyAndRecharge/showRecords?&userId="+userId);
            });

            //点击:充值,发出给中奖用户手机充值请求
            function virtualSend() {

                var phone = $("#phone").val().trim();
                var money = $("#money").val().trim();
                var orderCode= "${orderCode}";
                var userId = "${user.userId}";
                var shopId = "${shopId}";
                if (phone==null||phone==""||!phone.match(/^((13[0-9]|15[0-9]|17[0-9]|18[0-9])+\d{8})$/)){
                    tips.err("请输入正确的手机号码",2000);
                    return;
                }
                if (isNaN(money)||money==null||money==""){
                    tips.err("请输入正确的金额",2000);
                    return;
                }

                //发送ajax请求
                $.ajax({


                    url         :       "/c/page/console/auth/buyAndRecharge/telephoneCharge",
                    type        :       "POST",
                    dataType    :       "JSON",
                    data        :       {
                                            phone :       phone,
                                            money :       money,
                                            orderCode:    orderCode,
                                            userId :       userId,
                                            shopId :       shopId
                                         },
                    success     :       function(data){
                                            if(data.success == true ) {
                                                tips.suc(data.msg, 5000);
                                            }else {
                                                tips.err(data.msg, 5000);
                                            }
                                        },
                    error   :           function () {

                                        }
                });
            }
        </script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>发货</span>
            </div>
        </div>

        <%-- 发货 begin --%>
        <div style="margin-left: 2%; margin-right: 2%">

            <div class="editor-container">

                    <%--输入内容区域--%>
                <div class="editor-body">

                    <div class="mission-merchant">
                        <h3>中奖用户信息</h3>
                        <div class="col1">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left">收货人:</label>
                                <span  type="text" style="width: 30%; border: 0" readonly="readonly" value="">${user.userName}</span>
                            </span>
                        </div>
                        <div class="col1">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left">手机号码:</label>
                                <span id="txtUseId" type="text" style="width: 30%; border: 0" readonly="readonly" value="">${user.phone}</span>
                            </span>
                        </div>
                        <div class="col2">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left">省份城市:</label>
                                <span  type="text" style="width: 30%; border: 0" readonly="readonly" value="">${user.provinceCity}</span>
                            </span>
                        </div>
                        <div class="col2">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left">详细地址:</label>
                                <span  type="text" style="width: 30%; border: 0" readonly="readonly" value="">${user.detailAddress}</span>
                            </span>
                        </div>


                    </div>

                    <div class="mission-merchant">
                        <h3>中奖商品信息</h3>
                        <div class="col2">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left"></label>
                                <button class="btn btn-success btn-sm"  onclick=window.open("http://7229733.ffxiang.cn/index.php/d/"+${shopId}) >点击查看商品信息</button>
                                <img class="loadingPic" obj="user_photo" alt="" src="/client/img/loading.gif" />
                            </span>
                        </div>

                    </div>

                    <div class="mission-merchant" id="expressInfo">
                        <h3>物流信息</h3>
                        <div class="col1">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left">物流公司:</label>
                                <select id = "expressName">
                                    <option <c:if test="${expressInfo.name == '1'}">selected</c:if> value="1">申通快递</option>
                                    <option <c:if test="${expressInfo.name == '2'}">selected</c:if> value="2">圆通快递</option>
                                    <option <c:if test="${expressInfo.name == '3'}">selected</c:if> value="3">中通快递</option>
                                    <option <c:if test="${expressInfo.name == '4'}">selected</c:if> value="4">韵达快递</option>
                                    <option <c:if test="${expressInfo.name == '5'}">selected</c:if> value="5">天天快递</option>
                                    <option <c:if test="${expressInfo.name == '6'}">selected</c:if> value="6">顺丰快递</option>
                                    <option <c:if test="${expressInfo.name == '7'}">selected</c:if><c:if test="${virtual == '0'}">selected</c:if> value="7">无需快递</option>
                                </select>
                                <span>选择填写物流公司名称</span>
                            </span>
                        </div>
                        <div class="col1">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left">快递单号:</label>
                                <input type="text"  id = "expressCode" value="${expressInfo.code}">
                                <span>填写物流公司快递单号</span>
                            </span>
                        </div>
                        <div class="col1">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left">快递运费:</label>
                                <input type="text"  id = "expressPrice" value="${expressInfo.price}">
                                <span>元</span>
                            </span>
                        </div>



                        <%--分割线--%>
                    <div class="separator_line">
                    </div>

                        <%--编辑--%>
                    <div class="mission-merchant">
                        <div class="col2">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left"></label>
                                <button class="btn btn-success btn-sm"  id="commitExpressInfo" onclick="commitExpress();">提交</button><span>发送短信至用户手机</span>
                                <img class="loadingPic" obj="user_photo" alt="" src="/client/img/loading.gif" />
                            </span>
                        </div>
                    </div>
                </div>

                        <%--如果是虚拟商品,则显示这里的内容--%>

                        <div id="virtual">
                            <h3>虚拟商品</h3>
                            <div class="col1">
                                <span style="display: inline;">
                                    <p><button  class="btn btn-success btn-sm" id="rechargeRecord">充值纪录</button>
                                        <button class="btn btn-success btn-sm" id="buyRecord">购买纪录</button></p><br>
                                    <p><input id="phone" value="${user.otherPhone}"/></p>
                                    <p><input id="money" placeholder="请输入充值金额"/></p>
                                </span>
                            </div>
                            <div class="mission-merchant">
                                <div class="col2">
                                <span style="display: inline;">
                                    <label style="width: 100px; text-align: left"></label>
                                    <button class="btn btn-success btn-sm"  id="virtualSend" onclick="virtualSend();">充值</button>
                                    <img class="loadingPic" obj="user_photo" alt="" src="/client/img/loading.gif" />
                                </span>
                                </div>
                            </div>

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

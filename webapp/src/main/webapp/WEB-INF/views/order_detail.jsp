<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="订单详情"/>
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
        <script>
            
            function updateExpressInfo() {

                //取到更新的快递状态(待收货-->已完成,或 待收货-->已作废)
                var statusDesc = $("#statusDesc").val();
                //userId,shopId,orderCode
                var userId = "${info.userId}";
                var shopId = "${shopId}";
                var orderCode="${orderCode}";
                var lotteryCode="${info.lotteryCode}";

                $.ajax({

                    url     :       "/c/page/console/auth/lottery/updateExpressInfo",
                    type    :       "POST",
                    dataType:       "JSON",
                    data    :       {
                                        statusDesc  :   statusDesc,
                                        userId      :   userId,
                                        shopId      :   shopId,
                                        orderCode   :   orderCode,
                                        lotteryCode :   lotteryCode
                                    },
                    success :       function (data) {
                                        if(data.success == true) {
                                            tips.suc("修改成功", 2000);
                                        }
                                    },
                    error   :       function () {

                                    }
                });

            };

            $("#btnUpdate").click(function () {

                updateExpressInfo();
            });

            $("#btnSave").click(function () {
                updateExpressInfo();
            });
            
            

        </script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">

        <!-- 顶部栏 begin -->
        <div class="panel panel-default main-hd">
            <div class="panel-heading">
                <span>订单详情</span>
            </div>
        </div>


        <div style="margin-left: 2%; margin-right: 2%">

            <div class="editor-container">

                    <%--输入内容区域--%>
                <div class="editor-body">

                    <!--获奖商品信息 -->
                    <div class="mission-merchant">
                        <h3><b color:black>${info.title}</b></h3>
                        <div class="col1">
                            <span style="display: inline;">
                               <table>
                                   <tr>
                                       <td>剩余次数:${info.shengyuNums}</td>
                                       <td>总需次数:${info.totalNums}</td>
                                   </tr>
                                   <tr>
                                       <td>商品期数:${info.qishu}</td>
                                       <td>商品价格:${info.price}</td>
                                   </tr>
                                   <tr>
                                       <td>中&nbsp;奖&nbsp;人:${info.userName}</td>
                                       <td>中&nbsp;奖&nbsp;码:${info.lotteryCode}</td>
                                   </tr>
                                   <tr>
                                       <td colspan="2">购买次数:${info.buyNums}</td>
                                   </tr>
                               </table>
                                <span>
                                    获得的云购码:<br>
                                    <table>
                                        <c:forEach items="${info.totalBuyCodes}" var="code">
                                            <tr>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${info.lotteryCode==code}">
                                                            <li><span style="color: red">${code}</span></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <li>${code}</li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </span>
                            </span>
                        </div>
                        <br>
                            <%--分割线--%>
                        <div class="separator_line">
                        </div>

                        <!--中奖人信息 -->
                        <div class="col1">
                            <h3>中奖人信息</h3>
                            <span style="display: inline;">
                                 <ul>
                                     <li>购买人ID:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${info.userId}</li>
                                     <li>购买人昵称:&nbsp;&nbsp;${info.userName}</li>
                                     <li>购买人邮箱:&nbsp;&nbsp;${info.email}</li>
                                     <li>购买人手机:&nbsp;&nbsp;${info.phone}</li>
                                     <li>购买时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${info.buyTime}</li>
                                     <li>收货信息:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${info.dizhi}</li>
                                 </ul>
                            </span>
                        </div>

                        <%--分割线--%>
                    <div class="separator_line">
                    </div>

                        <!--快递信息 -->
                        <div class="mission-merchant">
                            <h3>物流信息</h3>
                            <div class="col1">
                            <span style="display: inline;">
                                <label style="width: 100px; text-align: left">订单状态:</label>
                                <select id = "statusDesc">
                                    <option <c:if test="${expressInfo.statusDesc == '未完成'}">selected</c:if> value="1">未完成</option>
                                    <option <c:if test="${expressInfo.statusDesc == '待收货'}">selected</c:if> value="2">待收货</option>
                                    <option <c:if test="${expressInfo.statusDesc == '已完成'}">selected</c:if> value="3">已完成</option>
                                    <option <c:if test="${expressInfo.statusDesc == '已作废'}">selected</c:if> value="4">已作废</option>
                                </select>
                            </span>
                            </div>
                            <div class="col1">
                            <span style="display: inline;">
                                <label style="width: 200px; text-align: left">快递公司:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <c:choose>
                                        <c:when test="${expressInfo.name=='1'}">申通快递</c:when>
                                        <c:when test="${expressInfo.name=='2'}">圆通快递</c:when>
                                        <c:when test="${expressInfo.name=='3'}">中通快递</c:when>
                                        <c:when test="${expressInfo.name=='4'}">韵达快递</c:when>
                                        <c:when test="${expressInfo.name=='5'}">天天快递</c:when>
                                        <c:when test="${expressInfo.name=='6'}">顺丰快递</c:when>
                                        <c:when test="${expressInfo.name=='7'}">无需快递</c:when>
                                        <c:otherwise></c:otherwise>
                                    </c:choose>
                                </label>
                            </span>
                            </div>
                            <div class="col1">
                            <span style="display: inline;">
                                <label style="width: 200px; text-align: left">快递单号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${expressInfo.code}</label>
                            </span>
                            </div>
                            <div class="col1">
                            <span style="display: inline;">
                                <label style="width: 200px; text-align: left">快递状态:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${expressInfo.expressStatus}</label>
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
                                <button class="btn btn-success btn-sm" id = "btnUpdate">更新</button>
                                <img class="loadingPic" obj="user_photo" alt="" src="/client/img/loading.gif" />
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

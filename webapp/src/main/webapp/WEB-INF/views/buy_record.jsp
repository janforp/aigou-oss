<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="购买纪录"/>
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



            .cell {
                width:25px;
                height:25px;
            }

            .selected {
                color: #00a0a0;
            }

        </style>

    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">

        <script>
            //'获得云购码' 按钮 跳出的小页面
            $(document).on("click",".btnCodes",function () {

                var lotteryCode = $(this).attr("huode");
                var allCodes = $(this).attr("allCodes");
                $(".btnCodes").css("background-color","#ffffff");
                $(this).css("background-color","#156df0");
                window.open ("/c/page/console/auth/buyAndRecharge/showCodes?allCodes="+allCodes+"&lotteryCode="+lotteryCode, "newwindow", "height=400, width=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
            });



            //总页数量:request带来
            var total = ${totalPage};
            var userId= ${userId};
            //要找的页
            function findRecord(pageNum) {

                $.ajax({

                    url     :       "/c/page/console/auth/buyAndRecharge/showRecordByPage",
                    type    :       "POST",
                    dataType:       "JSON",
                    data    :       {
                                        userId  :   userId,
                                        pageNum :   pageNum
                                    },
                    success :       function (data) {

                                        if(data.success) {
                                            var records = data.bean.records;

                                            //先把之前的tbody中的内容移除
                                            $("#tbody").html("");
                                            //再循环列表添加数据
                                            var str = "";
                                            for(var i=0; i<records.length; i++) {
                                                str += "<tr>"+
                                                        "<td>"+(i+1)+"</td>"+
                                                        "<td>"+records[i].code+"</td>"+
                                                        "<td>"+records[i].shopName+"</td>"+
                                                        "<td>"+records[i].goNumber+"</td>"+
                                                        "<td><button codes='"+records[i].gouCodes+"' huode ='"+records[i].huode+  "'allCodes ='"+ records[i].allCodes+"' class = 'btnCodes'>获得云购码</button></td>"+
                                                        "<td>"+records[i].huode+"</td>"+
                                                        "<td>"+records[i].time+"</td>"+
                                                        "</tr>"
                                            }
                                            $("#tbody").html(str);
                                            //把显示的页面设置为改变之后的页面
                                            $("#pageNow").text(pageNum);

                                        }
                                    },
                    error   :       function () {
                                        tips.err("出错了",2000);
                                    }



                });
            };

            function pageBefore() {

                var pageNow = $("#pageNow").text();
                var page = parseInt(pageNow);

                if(page==1){
                    tips.err("当前是第一页",2000);
                    return;
                }

                findRecord(page-1);
            };

            function pageAfter() {

                var pageNow = $("#pageNow").text();
                var page = parseInt(pageNow);


                if(page == total){
                    tips.err("当前是最后一页",2000);
                    return;
                }

                findRecord(page+1);

            }







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
                <span>购买纪录</span>
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
                <table class="table table-striped table-hover table-bordered table-navbar-no-bottom">
                        <thead align="middle">
                            <td>序号</td>
                            <td>订单号</td>
                            <td>商品名</td>
                            <td>购买次数</td>
                            <td>获得云购码</td>
                            <td>中奖码</td>
                            <td>购买时间</td>
                        </thead>
                        <tbody id="tbody">
                        <c:forEach items="${records}" var="record" varStatus="status">
                            <tr>
                                <td>${status.index+1}</td>
                                <td>${record.code}</td>
                                <td>${record.shopName}</td>
                                <td>${record.goNumber}</td>
                                <td><button codes="${record.gouCodes}" huode = "${record.huode}" allCodes = "${record.allCodes}" class = "btnCodes">获得云购码</button></td>
                                <td>${record.huode}</td>
                                <td>${record.time}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan='2' style="border: 0">
                                </td>
                               <td colspan='2'  style="border: 0">
                                       <div class='pagination-box pagination-right'>
                                               <ul class='pagination pagination-sm'>
                                                      <li>
                                                           <a onclick="pageBefore();" class='btn_prev'>
                                                                   <i class='glyphicon glyphicon-chevron-left'></i>
                                                           </a>
                                                      </li>
                                                      <li>
                                                          <span class='pagination-status'> <span id="pageNow">${pageNow}</span>/<span id="totalPage">${totalPage}</span></span>
                                                      </li>
                                                      <li>
                                                           <a onclick="pageAfter();" class='btn_next'>
                                                                   <i class='glyphicon glyphicon-chevron-right'></i>
                                                           </a>
                                                      </li>
                                               </ul>
                                       </div>
                                </td>
                                <td colspan='3'  style="border: 0">
                                </td>
                            </tr>
                        </tfoot>
                </table>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>

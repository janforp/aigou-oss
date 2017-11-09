<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="修改商品"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <link rel="stylesheet" type="text/css" href="/styles/update_goods.css">
        <style>
            .loadingPic {
                display: none;
            }
            .muti_box li {
                float: left;
                list-style: none;
            }
        </style>
    </tiles:putAttribute>
    <tiles:putAttribute name="scriptArea">
        <script src="${BASE_PATH}/client/js/base/control-convert.js" type="text/javascript"></script>
        <script src="${BASE_PATH}/client/js/base/validation.js" type="text/javascript"></script>
        <script src="${BASE_PATH}/plugins/upload/image-upload/js/my.jquery-post-upload-styleX.js" type="text/javascript"></script>
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src="/plugins/ueditor/ueditor.config.js"></script>
        <script src="/plugins/ueditor/ueditor.all.js"></script>

        <script src="/methods/update_goods.js" type="text/javascript"></script>
        <script>

            $(function() {

                // 显示图片
                $(document).on("click", ".active_img", function () {
                    var imageUrl = $(this).attr("src");
                    var arrayImage = imageUrl.split(";");
                    var arrayStr = "";
                    for (var k = 0; k < arrayImage.length; k++) {
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
            });

            $(function() {

                //上传的图片,删除几张,获取真正上传的
                function getUploadImg() {
                    var  $imgul = $("ul[obj=reImg]");
                    var  $img = $imgul.find("img");
                    var picarr = "";
                    for(var i=0;i<$img.length;i++){
                        picarr = picarr+";"+$img[i].attr("src");

                    }
                    return picarr;
                }
            });

            /**
             * 页面加载后自动去后台取栏目数据
             */
            $(document).ready(function () {
                $("#cateId").val(${vo.cateid});
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
                <span>修改商品</span>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">

            <div class="separator_line">
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px;">*</span>
                    <label style="margin-right: 20px">ID:</label>
                    <input type="text" id="id" value="${vo.id}" disabled style="height: 30px;width: 140px;margin-left: 40px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px;">*</span>
                    <label style="margin-right: 10px">SID:</label>
                    <input type="text" id="sid" value="${vo.sid}" disabled style="height: 30px;width: 140px;margin-left: 40px;"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                    <label style="margin-right: 10px"> 所属分类:</label>
                    <select id="cateId" style="width: 140px;height: 30px">
                        <option value="0">≡ 请选择栏目 ≡</option>
                        <c:forEach items="${categories}" var="cate">
                            <option value="${cate.cateid}">${cate.name}</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <!--品牌默认为0 -->
                <input type="hidden" id="brandid" value="0">
                </span>
            </div>
            <div class="separator_line">
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                    <label style="margin-right: 10px">商品标题:</label>
                    <input type="text" id="title" value="${vo.title}" style="height: 30px;width: 400px"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 25px"></span>
                     <label style="margin-right: 25px"> 副标题:</label>
                    <input type="text" id="title2" value="${vo.title2}" style="height: 30px;width: 400px"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 25px"></span>
                    <label style="margin-right: 25px">  关键字:</label>
                    <input type="text" id="keywords" value="${vo.keywords}" style="height: 30px;width: 400px"/>
                    <label style="margin-left: 50px">多个关键字,请用逗号隔开</label>
                </span>
            </div>
            <div class="separator_line">
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 20px"></span>
                    <label style="margin-right: 10px;position:relative; bottom:60px;margin-right: 10px"> 商品描述:</label>
                    <textarea id="description" style="height: 80px;width: 800px">${vo.description}</textarea>
                </span>
                <br>
                <div style="background-color: #FFC; width: auto;height: auto;">
                    <b style="margin-left: 105px">提示: </b>
                    <font color="red">商品总价格请不要填写100，2300,5000这样的整数,整数价格计算出的云购码可能就为10000001</font>
                    <br>
                    <b style="margin-left: 105px">提示: </b>
                    商品价格过大，添加商品会变慢，请耐心等待！
                </div>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                    <label style="margin-right: 10px"> 商品价格:</label>
                    <input type="text" id="money" value="${vo.money}" style="height: 30px;width: 100px"/> 元
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                     <label style="margin-right: 10px"> 云购价格:</label>
                    <select id="yunjiage" style="height: 30px;width: 100px">
                        <option <c:if test="${vo.yunjiage == '1.00'}">selected</c:if> >1</option>
                        <option <c:if test="${vo.yunjiage == '10.00'}">selected</c:if> >10</option>
                        <option <c:if test="${vo.yunjiage == '100.00'}">selected</c:if> >100</option>
                    </select>
                    元<span style="color: red;margin-left: 10px">总价格必须是单次价格的整数倍</span>
                </span>
            </div>

            <br>
            <div class="separator_line">
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                    <label style="margin-right: 45px">期数:</label>
                    <input type="text" id="qishu" disabled value="${vo.qishu}" style="height: 30px;width: 100px"/> 期,修改之后会自动新增一期
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                    <label style="margin-right: 10px">最大期数:</label>
                    <input type="text" id="maxqishu" value="${vo.maxqishu}" style="height: 30px;width: 100px"/> 期,期数上限为65535期,每期揭晓后会根据此值自动添加新一期商品！
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 3px">*</span>
                    <label style="margin-right: 0px">中奖ID区间:</label>
                    <input type="text" id="quyu_begin" value="${vo.quyuBegin}" style="height: 30px;width: 100px"/>--<input type="text" id="quyu_end" value="${vo.quyuEnd}" style="height: 30px;width: 100px"/>
                    <span style="margin-left: 10px">*空表示不指定</span>
                </span>
            </div>

            <br>
            <div class="separator_line">
            </div>
            <br>
            <div  class="collapse navbar-collapse">
                <span style="display: inline;">
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                    <label style=" margin-right: 25px">缩略图:</label>
                    <img src="${vo.thumb}" class="user_photo" id="thumb2" style="width: 60px;height: 60px">
                    <input type="text" id="showThumb" value="${vo.thumb}"  style="height: 30px;width: 345px"/>
                    <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                            id = "uploadThumb"
                            action="/c/p/picture/upload" filesizelimit="128000"
                            filetypelimit="jpg,png,gif,jpeg" filenums="photo">上传图片
                    </button>
                    <img class="loadingPic"  obj="user_photo" style="width:15px;height:15px;"  alt="" src="/client/img/loading.gif" />
                    <input type="hidden" id="thumb" name="img" obj="user_photo" />
                </span>
            </div>
            <br>
            <div  class="collapse navbar-collapse">
                <span style="display: inline;">
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                    <label style=" margin-right: 25px">展示图:</label>
                    <img src="/client/img/plz_upload_img.png" style="width: 60px;height: 60px">
                    <input type="text" id="pic" style="height: 30px;width: 345px"/>
                    <button class="btn btn-success btn-sm" mode="button-upload-pic"
                            uploadtype="image" action="/c/p/picture/upload"
                            filesizelimit="128000" filetypelimit="jpg,png,gif,jpeg" filenums="m">上传图片
                    </button>
                    <img class="loadingPic" obj="reImg" alt="" style="width:15px;height:15px;" src="/client/img/loading.gif" />
                    <ul class="muti_box" obj="reImg">
                        <c:forEach items="${vo.pics}" var="pic">
                            <li style="margin-right: 100px;">
                                <span class='delete_pic' obj="reImg">╳</span>
                                <img class = 'active_img' src='${pic}'/>
                            </li>
                        </c:forEach>
                    </ul>
                    <input type="hidden" value="${vo.picarr}" id="picarr" obj="reImg" />
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:100px;margin-right: 20px"></span>
                    <label style="margin-right: 10px;margin-right: 10px"> 商品属性:</label>
                    <input type="checkbox" id="renqi" <c:if test="${vo.renqi == '1'}">checked</c:if> style="width: 18px;height: 18px;position:relative;top: 2px;"/>
                    <span>人气</span>
                </span>
            </div>
            <br>
            <div class="separator_line">
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span style="align:center;">
                   <button id="update" class="btn btn-success btn-sm" style="position:relative; left: 45%">修改商品</button>
                </span>
            </div>
            <div class="separator_line">
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>

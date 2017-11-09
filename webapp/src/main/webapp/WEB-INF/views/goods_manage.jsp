<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="添加新商品"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="styleArea">
        <style>
            .loadingPic {
                display: none;
            }
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
        <script src="/plugins/My97DatePicker/function.js"></script>
        <script src="/plugins/My97DatePicker/WdatePicker.js"></script>
        <script src="/plugins/ueditor/ueditor.config.js"></script>
        <script src="/plugins/ueditor/ueditor.all.js"></script>

        <script>
            var ue;
            $(function() {

                //初始化部化页面信息
                ue = UE.getEditor('content', {
                    toolbars: [[
                        'source', //源代码
                        'fontfamily', //字体
                        'fontsize', //字号
                        'bold', //加粗
                        'forecolor', //字体颜色
                        'backcolor', //背景色
                        'justifyleft', //居左对齐
                        'justifyright', //居右对齐
                        'justifycenter', //居中对齐
                        'justifyjustify', //两端对齐
                        'insertorderedlist', //有序列表
                        'insertunorderedlist', //无序列表
                        'simpleupload', //单图上传
                        'link', //超链接
                        ]
                    ],
                    autoHeightEnabled: true,
                    autoFloatEnabled: true
                });

                UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
                UE.Editor.prototype.getActionUrl = function(action) {
                    if (action == 'uploadimage') {
                        return "${BASE_PATH}/c/p/picture/ue";
                    } else {
                        return this._bkGetActionUrl.call(this, action);
                    }
                };

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

            //点击采集按钮
            $(document).on("click","#caiji",function () {

                var url = $("#url").val();
                if (url == null || url.trim().length ==0) {
                    tips.err("没有采集地址",2000);
                    return;
                }
                var arr = url.split("/");
                var arrS = arr[arr.length-1];
                var arrId = arrS.split(".");
                var Id = arrId[0];
                $("#caiji").text("正在采集商品...");
                var site = $("#site").val();
                $.ajax({
                    url     :       "/c/page/console/auth/goods/parseHtml",
                    type    :       "POST",
                    dataType:       'JSON',
                    data    :       {url    :   Id,
                                     site   :   site
                                    },
                    success :       function (data) {

                                        if (data.success){

                                            var shop = data.bean.shop;
                                            var title = shop.title;
                                            var keywords = shop.keywords;
                                            var description = shop.description;

                                            $("#title").val(title);
                                            $("#keywords").val(keywords);
                                            $("#description").val(description);
                                            $("#yunjiage").val(shop.yunjiage);
                                            $("#maxqishu").val(shop.maxqishu);

                                            ue.setContent(shop.content);


                                            $("#caiji").text("采集完毕");
                                            setTimeout(function () {
                                                $("#caiji").text("点击采集");
                                            },2000);

                                        }else {
                                            //tips.err(data.msg,2000);
                                            $("#caiji").text("采集失败");
                                            setTimeout(function () {
                                                $("#caiji").text("点击采集");
                                            },2000);
                                        }
                                    },
                    error   :       function () {

                    }
                });
            });

            //点击添加商品
            $(document).on("click","#save",function () {
                //分类及栏目
                var cateId = $("#cateId").val();
                //品牌
                var brandId = $("#brandid").val();
                //标题
                var title = $("#title").val();
                //副标题
                var title2=$("#title2").val();
                //关键字
                var keywords=$("#keywords").val();
                //商品描述
                var description=$("#description").val();
                //价格
                var money = $("#money").val();
                if(money==null||money.trim().length == 0 ||isNaN(money)) {
                    tips.err("商品价格必填且为数字");
                    return;
                }
                //云购价
                var yunjiage = $("#yunjiage").val();
                if(yunjiage==null||yunjiage.trim().length == 0 ||isNaN(yunjiage)) {
                    tips.err("云购价格必填且为数字");
                    return;
                }
                //最大期数
                var maxqishu=$("#maxqishu").val();
                if(maxqishu==null||maxqishu.trim().length == 0 ||isNaN(maxqishu)) {
                    tips.err("最大期数必填且为整数");
                    return;
                }
                //中奖ID区间
                var quyu_begin=$("#quyu_begin").val();
                if (quyu_begin == null||quyu_begin.trim().length == 0||isNaN(quyu_begin)){
                    quyu_begin="0";
                }
                var quyu_end=$("#quyu_end").val();
                if (quyu_end == null||quyu_end.trim().length == 0||isNaN(quyu_end)){
                    quyu_end="0";
                }
                //缩略图
                var thumb=$("#thumb").val();
                $("#showThumb").val(thumb);
                //详情
                var content = ue.getContent();
                //是否人气
                var renqiEle = $("#renqi:checked");
                if (renqiEle.length == 0) {
                    var renqi = 0;
                }else if(renqiEle.length>0){
                    renqi = 1;
                }

                //用此方法获取用户想要上传的图片
                var  $imgul = $("ul[obj=reImg]");
                var picarr = "";
                var  $img = $imgul.find("img").each(function () {

                    picarr += ";"+$(this).attr("src");
                });
                if(picarr == null || picarr.trim().length == 0) {
                    picarr = "";
                }
                //发送请求,传参数
                $.ajax({

                    url     :       "/c/page/console/auth/goods/save",
                    type    :       "POST",
                    dataType:       "JSON",
                    data    :       {
                        cateId:cateId,
                        brandId:brandId,
                        title:title,
                        title2:title2,
                        keywords:keywords,
                        description:description,
                        money:money,
                        yunjiage:yunjiage,
                        maxqishu:maxqishu,
                        quyuBegin:quyu_begin,
                        quyuEnd:quyu_end,
                        thumb:thumb,
                        renqi:renqi,
                        picarr:picarr,
                        content:content
                    },
                    success :       function (data) {

                                    if(data.success){
                                        tips.suc("添加成功",2000);
                                    }else{
                                        tips.err("操作失败",2000);
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
                <span>添加新商品</span>
            </div>
        </div>
        <!-- 顶部栏 end -->

        <%--主内容显示区 begin --%>
        <div class="main-bd">
            <div class="collapse navbar-collapse">
                <span style="display: inline">
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 25px"></span>
                    <span style="margin-right: 10px;">采集网址:</span>
                    <input type="text" id="url" style="width: 300px" value="http://item.jd.com/1856581.html"/>

                    <select id="site" style="width: 100px;height: 30px">
                        <option value="jd">京东商城</option>
                        <option value="aigou">爱购内部</option>
                    </select>
                    <button class="btn navbar-btn btn-success btn-sm" id="caiji">点击采集</button>
                </span>
            </div>
            <div class="separator_line">
            </div>
            <br>

            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                    <label style="margin-right: 10px"> 所属分类:</label>
                    <select id="cateId" style="width: 140px;height: 30px">
                        <option value="0">≡ 请选择栏目 ≡</option>
                        <c:forEach items="${vo}" var="cate">
                            <option value="${cate.cateid}">${cate.name}</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <!--品牌默认为0 -->
                <input type="hidden" id="brandid" value="0">
            </div>
            <div class="separator_line">
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                    <label style="margin-right: 10px">商品标题:</label>
                    <input type="text" id="title" style="height: 30px;width: 400px"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 25px"></span>
                     <label style="margin-right: 25px"> 副标题:</label>
                    <input type="text" id="title2" style="height: 30px;width: 400px"/>
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 25px"></span>
                    <label style="margin-right: 25px">  关键字:</label>
                    <input type="text" id="keywords" style="height: 30px;width: 400px"/>
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
                    <textarea id="description" style="height: 80px;width: 800px"></textarea>
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
                    <input type="text" id="money" style="height: 30px;width: 100px"/> 元
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 10px">*</span>
                     <label style="margin-right: 10px"> 云购价格:</label>
                    <%--<input type="text"  />--%>
                    <select id="yunjiage">
                        <option>1</option>
                        <option>10</option>
                        <option>100</option>
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
                    <label style="margin-right: 10px">最大期数:</label>
                    <input type="text" id="maxqishu" style="height: 30px;width: 100px"/> 期,期数上限为65535期,每期揭晓后会根据此值自动添加新一期商品！
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:7px;margin-right: 3px">*</span>
                    <label style="margin-right: 0px">中奖ID区间:</label>
                    <input type="text" id="quyu_begin" style="height: 30px;width: 100px"/>--<input type="text" id="quyu_end" style="height: 30px;width: 100px"/>
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
                    <img src="/client/img/plz_upload_img.png" class="user_photo" id="thumb2" style="width: 60px;height: 60px">
                    <input type="text" id="showThumb"  style="height: 30px;width: 345px"/>
                    <button class="btn btn-success btn-sm" mode="button-upload-pic" uploadtype="image"
                            id = "uploadThumb"
                            action="/c/p/picture/upload" filesizelimit="128000"
                            filetypelimit="jpg,png,gif,jpeg" filenums="photo">上传图片
                    </button>
                    <img class="loadingPic"  obj="user_photo"  alt="" src="/client/img/loading.gif" />
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
                    <img class="loadingPic" obj="reImg" alt="" src="/client/img/loading.gif" />
                    <ul class="muti_box" obj="reImg"></ul>
                    <input type="hidden" id="picarr" obj="reImg" />
                </span>
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span>
                    <span style="color: red;font-size: 25px;position:relative; top:100px;margin-right: 20px"></span>
                    <label style="margin-right: 10px;margin-right: 10px"> 商品属性:</label>
                    <input type="checkbox" id="renqi" style="width: 18px;height: 18px;position:relative;top: 2px;"/>
                    <span>人气</span>
                </span>
            </div>
            <br>
            <div class="separator_line">
            </div>
            <br>
            <div class="collapse navbar-collapse">
                <span style="align:center;">
                   <button id="save" class="btn btn-success btn-sm" >添加商品</button>
                </span>
            </div>
            <div class="separator_line">
            </div>
            <div class="collapse navbar-collapse">
                <span>
                    <div  style="height: 700px;width: 900px;">
                        <span style="color: red;font-size: 25px;position:relative; top:9px;margin-right: 10px">*</span>详情:

                        <script id="content" name="content"></script>
                    </div>
                </span>
            </div>
        </div>
        <%--主内容显示区 end --%>
    </tiles:putAttribute>
</tiles:insertTemplate>

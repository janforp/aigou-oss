





/**
 * 选择栏目
 */
$("#cateId").change(function () {
    var cateId = $("#cateId").val();
    $.ajax({
        url     :       "/c/page/console/auth/goods/getBrand",
        type    :       "POST",
        dataType:       "JSON",
        data    :       {cateId:cateId},
        success :       function (data) {

            if (data.success) {

                var brands = data.bean.brand;
                $("#brandid").empty();
                var str = "";
                for (var i=0;i<brands.length;i++) {
                    var option = new Option(brands[i].name,brands[i].id);
                    $("#brandid").append(option);
                }
                if (brands.length == 0) {
                    var option = new Option("无商品","0");
                    $("#brandid").append(option);
                }
            }
        },
        error   :       function () {

        }
    });

});

/**
 * 取到输入的各值,保存修改,新建下一期商品
 */
$(document).on("click","#update",function () {

    var id = $("#id").val().trim();
    var sid  = $("#sid").val().trim();
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
    var money = $("#money").val().trim();
    if(money==null||money.trim().length == 0 ||isNaN(money)) {
        tips.err("商品价格必填且为数字");
        return;
    }
    //云购价
    var yunjiage = $("#yunjiage").val().trim();
    if(yunjiage==null||yunjiage.trim().length == 0 ||isNaN(yunjiage)) {
        tips.err("云购价格必填且为数字");
        return;
    }
    if (money % yunjiage != 0) {
        tips.err("总价必须是云价格的整数倍",2000);
        return;
    }
    var qishu = $("#qishu").val();
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
    if (thumb == ""||thumb ==null||thumb == undefined){
        thumb = $("#thumb2").attr("src");
    }


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
    //广告图片
    var adv = $("#advUrl").val();

    //发送请求,传参数
    $.ajax({

        url     :       "/c/page/console/auth/goods/update",
        type    :       "POST",
        dataType:       "JSON",
        data    :       {
            id:id,
            sid:sid,
            cateId:cateId,
            brandId:brandId,
            title:title,
            title2:title2,
            keywords:keywords,
            description:description,
            money:money,
            yunjiage:yunjiage,
            qishu:qishu,
            maxqishu:maxqishu,
            quyuBegin:quyu_begin,
            quyuEnd:quyu_end,
            thumb:thumb,
            renqi:renqi,
            picarr:picarr,
            advertisementImg:adv
        },
        success :       function (data) {

            if(data.success){
                tips.suc("修改成功",2000);
            }else{
                tips.err(data.msg,2000);
            }
        },
        error   :       function () {

        }
    });
});
/**
 * 页面加载后自动去后台取栏目数据
 */
$(document).ready(function () {

    $.ajax({
        url     :       "/c/page/console/auth/goods/getAllCate",
        type    :       "POST",
        dataType:       "JSON",
        data    :       {},
        success :       function (data) {

            if (data.success) {
                var allCate = data.bean.vo;
                var $cate = $("#selectCate");


                for (var i=0;i<allCate.length;i++){

                    var parent = allCate[i].parent;
                    var parentCateId = parent.cateid;
                    var parentCateName = parent.name;
                    var parentOption = new Option(parentCateName,parentCateId);

                    $cate.append(parentOption);

                    for (var j=0;j<allCate[i].son.length; j++){
                        var son = allCate[i].son[j];
                        var sonCateId = son.cateid;
                        var sonCateName = "├─ "+son.name;

                        if (j==allCate[i].son.length -1){
                            sonCateName = "└─ "+son.name;
                        }

                        var sonOption = new Option(sonCateName,sonCateId);

                        $cate.append(sonOption);
                    }
                }
            }else {
                tips.err(data.msg,2000);
                return;
            }
        },
        error   :       function () {

        }
    });
});



/**
 * 点击删除栏目
 */
$(document).on("click","#updateDiv li span button",function () {
    $(this).parent().parent().remove();
});
/**
 * 点击'添加栏目'按钮
 */
$(document).on("click","#cate",function () {
    $(".cateAlert").slideDown(500);
    $("input[type=text]").attr("disabled",true);
});
/**
 * 点击'关闭'按钮
 */
$(document).on("click",".closeAlert",function () {
    $(".cateAlert").hide();
    $("input[type=text]").attr("disabled",false);
});


/**
 * 点击修改按钮
 */
$(document).on("click","#updateBrand",function () {
    var $li = $("#cateUl li");
    var cateIds = $li.map(function (index,elem) {
        return $(elem).attr("cateId");
    }).get().join(',');

    var brandId = $("#brandId").val();
    var brandName = $("#brandName").val();
    var brandOrder = $("#brandOrder").val();

    $.ajax({
        url     :       "/c/page/console/auth/goods/updateBrand",
        type    :       "POST",
        dataType:       "JSON",
        data    :       {cateIds:cateIds,brandId:brandId,brandName:brandName,brandOrder:brandOrder},
        success :       function (data) {

            if (data.success) {
                tips.suc("修改成功",2000);
                return;
            }else {
                tips.err(data.msg,2000);
                return;
            }
        },
        error   :       function () {
            
        }
    });

});

/**
 * 添加栏目'确定'按钮
 */
$(document).on("click","#addCate",function () {

    var $option = $("option:selected");
    var addCateId = $option.val();
    var addCateName = $option.text();
    if (addCateId == 0){
        tips.err("请选择一项",2000);
        return;
    }

    var $li = $("#cateUl li");
    var cateIds = $li.map(function (index,elem) {
        return $(elem).attr("cateId");
    }).get().join('&');
    var ids = cateIds.split("&");
    for (var i=0;i<ids.length;i++){
        if (ids[i] == addCateId){
            tips.err("已经添加过",2000);
            return;
        }
    }
    var newLi = "";
    newLi +="<li cateId = '"+addCateId+"'>"+
            "   <span>"+addCateName+
            "       <button class='btn btn-link' ><span>x</span></button>"+
            "       <input type='hidden' value='"+addCateId+"'>"+
            "   </span>"+
            "</li>";
    $("#cateUl").append(newLi);
});


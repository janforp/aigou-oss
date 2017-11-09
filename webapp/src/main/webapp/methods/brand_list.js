/**
 * 页面加载自动查询一次
 */
$(document).ready(function(){
    $("#listByPageSize").click();
});
/**
 * 根据用户输入,觉得每页显示的行数
 */
$(document).on("click","#listByPageSize",function(){

    var pageSize = $("#pageSize").val();

    if (parseInt(pageSize) != pageSize && pageSize != ""){
        $("#pageSizeAlert").text("请输入整数");
        setTimeout(function () {
            $("#pageSizeAlert").text("");
        },2000);
        return;
    }
    var body = $("#goodsBody");
    body.attr("pageSize",pageSize);
    $.ajax({
        url     :   "/c/page/console/auth/goods/brandListByPageSize",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {pageSize:pageSize},
        success :   function(data){

            if (data.success) {

                var vos = data.bean.list;
                var totalPage = data.bean.totalPage;
                $("#pageNow").text("1");
                $("#totalPage").text(totalPage);


                body.empty();
                var newTbody = "";
                for (var i= 0;i<vos.length ;i ++) {
                    newTbody += "<tr>"+
                                    "<td><input type='checkbox' value='"+vos[i].id+"'></td>"+
                                    "<td>"+vos[i].id+"</td>"+
                                    "<td>"+vos[i].name+"</td>"+
                                    "<td>";
                    for (var j =0 ;j<vos[i].cateNames.length ;j ++){
                        newTbody = newTbody +"&nbsp;&nbsp;["+vos[i].cateNames[j]+"]";
                    }

                    newTbody +=
                                "</td><td>"+vos[i].order+"</td>"+
                                "</tr>";
                }
                body.append(newTbody);
            }else{
                tips.err("操作失败",2000);
            }

        },
        error   :   function () {

        }
    });
});
/**
 * 在页码输入框中按回车键 也可以执行上面的操作
 */
$("#pageSize").bind("keypress",function (event) {

    if (event.keyCode == "13") {
        var pageSize = $("#pageSize").val();

        if (parseInt(pageSize) != pageSize && pageSize != ""){
            $("#pageSizeAlert").text("请输入整数");
            setTimeout(function () {
                $("#pageSizeAlert").text("");
            },2000);
            return;
        }
        var body = $("#goodsBody");
        body.attr("pageSize",pageSize);
        $.ajax({
            url     :   "/c/page/console/auth/goods/brandListByPageSize",
            type    :   "POST",
            dataType:   "JSON",
            data    :   {pageSize:pageSize},
            success :   function(data){

                if (data.success) {

                    var vos = data.bean.list;
                    var totalPage = data.bean.totalPage;
                    $("#pageNow").text("1");
                    $("#totalPage").text(totalPage);


                    body.empty();
                    var newTbody = "";
                    for (var i= 0;i<vos.length ;i ++) {
                        newTbody += "<tr>"+
                            "<td><input type='checkbox' value='"+vos[i].id+"'></td>"+
                            "<td>"+vos[i].id+"</td>"+
                            "<td>"+vos[i].name+"</td>"+
                            "<td>";
                        for (var j =0 ;j<vos[i].cateNames.length ;j ++){
                            newTbody = newTbody +"&nbsp;&nbsp;["+vos[i].cateNames[j]+"]";
                        }

                        newTbody +=
                            "</td><td>"+vos[i].order+"</td>"+
                            "</tr>";
                    }
                    body.append(newTbody);
                }else{
                    tips.err("操作失败",2000);
                }

            },
            error   :   function () {

            }
        });
    }
});

/**
 * 翻页
 * @param pageTo 点击之后的页码
 */
function turnPage(pageTo) {

    var body = $("#goodsBody");
    var pageSize = body.attr("pageSize");

    $.ajax({
        url     :   "/c/page/console/auth/goods/brandListByPageSize",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {pageSize:pageSize,pageNum:pageTo},
        success :   function(data){

            if (data.success) {

                var vos = data.bean.list;
                var totalPage = data.bean.totalPage;
                $("#pageNow").text(pageTo);
                $("#totalPage").text(totalPage);

                body.empty();
                var newTbody = "";
                for (var i= 0;i<vos.length ;i ++) {
                    newTbody += "<tr>"+
                        "<td><input type='checkbox' value='"+vos[i].id+"'></td>"+
                        "<td>"+vos[i].id+"</td>"+
                        "<td>"+vos[i].name+"</td>"+
                        "<td>";
                    for (var j =0 ;j<vos[i].cateNames.length ;j ++){
                        newTbody = newTbody +"&nbsp;&nbsp;["+vos[i].cateNames[j]+"]";
                    }

                    newTbody +=
                        "</td><td>"+vos[i].order+"</td>"+
                        "</tr>";
                }
                body.append(newTbody);
            }else{
                tips.err("操作失败",2000);
            }
        },
        error   :   function () {

        }
    });
}

/**
 * 上一页
 */
$("#pageBefore").click(function () {

    var pageNow = $("#pageNow").text();
    if (pageNow == 1 || pageNow == 0) {
        tips.err("当前是第一页",2000);
        return;
    }
    var now = parseInt(pageNow);

    turnPage(now - 1);
});

/**
 * 下一页
 */
$("#pageAfter").click(function () {

    var totalPage = $("#totalPage").text();
    var pageNow = $("#pageNow").text();
    if (pageNow == totalPage || pageNow == 0) {
        tips.err("当前是最后一页",2000);
        return;
    }
    var now = parseInt(pageNow);

    turnPage(now + 1);
});
/**
 * 删除
 */
function deleteGoods() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0 ) {
        tips.err("至少选择一条",2000);
        return;
    }
    var goodsIds = $input.map(function (index,elem) {
        return $(elem).val();
    }).get().join('&');

    $("input[type=checkbox]").attr("disabled",true);
    $("#deleteAlert").slideDown(500);

}
/**
 * 点击删除弹出框 '取消' 按钮
 */
$(document).on("click","#deleteAlert .btn-info",function () {

    $("#deleteAlert").hide(500);
    $("input[type=checkbox]").attr("disabled",false);
});
/**
 * 点击删除弹出框 '删除' 按钮
 */
$(document).on("click","#deleteAlert .btn-danger",function () {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0 ) {
        tips.err("至少选择一条",2000);
        return;
    }
    var goodsIds = $input.map(function (index,elem) {
        return $(elem).val();
    }).get().join('&');

    $("input[type=checkbox]").attr("disabled",true);
    $("#deleteAlert").slideDown(500);

    $.ajax({
        url     :   "/c/page/console/auth/goods/deleteBrand",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {brandIds:goodsIds},
        success :   function(data){

            if (data.success) {
                $input.parent().parent().remove();
                $("#deleteAlert").hide(500);
                $("input[type=checkbox]").attr("disabled",false);
                tips.suc(data.msg,2000);

            }else{
                tips.err(data.msg,2000);
            }
        },
        error   :   function () {

        }
    });

});
/**
 * 跳转到修改页面
 */
function updateBrand() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var brandId = $input.val();
    window.open("/c/page/console/auth/goods/updateBrandPage?brandId="+brandId)
}
/**
 * 详情操作
 */
function addBrand() {

    window.open("/c/page/console/auth/goods/addBrandPage");
}

/**
 * 删除
 */
function deleteCategory() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0 ) {
        tips.err("至少选择一条",2000);
        return;
    }

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
    var cateIds = $input.map(function (index,elem) {
        return $(elem).val();
    }).get().join('&');

    $("input[type=checkbox]").attr("disabled",true);
    $("#deleteAlert").slideDown(500);

    $.ajax({
        url     :   "/c/page/console/auth/category/deleteCategory",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {cateIds:cateIds},
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
function updateCategory() {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null ||$input.length == 0||$input.length>1 ) {
        tips.err("必须且只能选择一条纪录",2000);
        return;
    }
    var categoryId = $input.val();
    window.open("/c/page/console/auth/category/updateCategoryPage?cateId="+categoryId)
}

/**
 * 添加子栏目
 */
function addCategory() {

    window.open("/c/page/console/auth/category/addCategoryPage?");
}

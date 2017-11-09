/**
 * 点击保存按钮
 */
$(document).on("click","button",function () {

    var cateid = $("#cateId").val().trim();
    var name = $("#name").val().trim();
    var order = $("#order").val().trim();
    var url = $("#url").val().trim();

    $.ajax({
        url:"/c/page/console/auth/category/saveUpdateCategory",
        type:'POST',
        dataType:'JSON',
        data:{cateid:cateid,name:name,order:order,url:url},
        success:function (data) {
            if (data.success) {
                tips.suc("修改成功",2000);
            }
        },
        error:function () {
            
        }
    });
});
/**
 * 点击保存按钮
 */
$(document).on("click","#addCate",function () {

    var name = $("#newName").val().trim();
    if (name == null || name.trim() == "" ||name == undefined){
        tips.err("栏目名称不能空",2000);
        return;
    }
    var order = $("#order").val().trim();
    if (order != parseInt(order)){
        tips.err("排序填数字",2000);
        return;
    }
    var url = $("#url").val().trim();

    $.ajax({
        url:'/c/page/console/auth/category/saveCategory',
        type:'POST',
        dataType:'JSON',
        data:{name:name,order:order,url:url},
        success:function (data) {
            if (data.success) {
                tips.suc("添加成功",2000);
                window.location.href = "/c/page/console/auth/category/categoryList";
            }else {
                tips.err(data.msg,2000);
            }
        },
        error:function () {

        }
    });
});
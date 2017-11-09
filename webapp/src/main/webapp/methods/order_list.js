/**
 * 点击删除
 */
$(document).on("click","#deleteShaidan",function () {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null || $input.length == 0){
        tips.err("至少选择一条删除",2000);
        return;
    }

    var sdIds = $input.map(function (index,elem) {
        return $(elem).val();
    }).get().join('&');

    $.ajax({
        url     :       "/c/page/console/auth/order/deleteShaidan",
        type    :       'POST',
        dataType:       'JSON',
        data    :       {sdIds:sdIds},
        success :       function (data) {
            if (data.success) {
                $input.parent().parent().remove();
                tips.suc(data.msg,2000);
            }else {
                tips.err(data.msg,2000);
            }
        },
        error   :       function () {
            
        }
        
    });
});


/**
 * 点击修改
 */
$(document).on("click","#updateShaidan",function () {

    var $input = $("input[type=checkbox]:checked");
    if ($input == null || $input.length == 0 ||$input.length>1){
        tips.err("必须且只能选择一条修改",2000);
        return;
    }

    var sdId = $input.val();
    var shopId = $input.attr("shopId");
    var userId = $input.attr("userId");

    window.open("/c/page/console/auth/order/updateShaidanPage?shopId="+shopId+"&userId="+userId+"&sdId="+sdId);

});
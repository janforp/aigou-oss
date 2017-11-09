<%@page pageEncoding="UTF-8" %>
<tiles:insertTemplate template="/WEB-INF/views/base/layout/layout-default.jsp">
    <tiles:putAttribute name="title" value="${CURRENT_FUNCTION.functionName}"/>
    <tiles:putAttribute name="enableConsole" value="true"/>
    <tiles:putAttribute name="enableSidebar" value="true"/>
    <tiles:putAttribute name="scriptArea">
        <script>
            pg.createAccount  = function (json) {
                if (json.success) {
//                    util.loading();
//                    if (json.msg) {
//                        tips.suc(json.msg);
//                    }
                    setTimeout(function () {
                        location.href = "${BASE_PATH}/c/page/console/auth/innerUsers/createInnerUsers";
                    }, 1000);

                }
            };
            
            function putRobotToInner() {
                $.ajax({
                    url: "/c/page/console/auth/innerUsers/putRobotToInner",
                    type: "POST",
                    dataType: "JSON",
                    data: {},
                    success: function(data) {
                        if(data.success) {
                            alert(data.msg);
                        }
                    },
                    error: function() {

                    }
                });
            }
        </script>
    </tiles:putAttribute>
    <tiles:putAttribute name="mainArea">
        <div class="panel panel-default main-hd">
            <div class="panel-heading">${CURRENT_FUNCTION.functionName}</div>
        </div>
        <div class="main-bd">
            <form role="form" id="createAccountForm" class="ajax-form v-form"
                  ajax-callback="pg.createAccount"
                  action="${BASE_PATH}/c/page/console/auth/innerUsers/readFile"
                  method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="file" id="txtFile" name="txtFile"/>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-success">提交</button>
                </div>
            </form>
        </div>
        <%--<p>--%>
            <%--<button id="doSomeElse" type="button" onclick="putRobotToInner();">把之前存在的机器人ID插入内部表</button>--%>
        <%--</p>--%>
    </tiles:putAttribute>
</tiles:insertTemplate>
<%--
  点击 '获得云够码' 弹出的小页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获得云购码</title>
    <script scr="plugins/jquery/jque*ry-1.9.1.min.js"></script>
    <script>
        var str = ${str};
        var strCodes = str.toString();
        var jsonObj = strCodes.split(",");
        var code1 = "";
        var code2 = "";
        for (var i = 0;i < jsonObj.length;i++){

            if (i< jsonObj.length/2 ) {
                code1 += "<td>"+jsonObj[i]+"</td>";
            }
            if(i>jsonObj.length/2) {
                code2 += "<td>"+jsonObj[i]+"</td>";
            }
        }
    </script>
</head>
<body>
    <ol>
        <c:forEach var="code" items="${codes}">
            <c:choose>
                <c:when test="${code == huode}"><li style="color: red">${code}</li></c:when>
                <c:otherwise><li>${code}</li></c:otherwise>
            </c:choose>
        </c:forEach>

    </ol>
</body>
</html>

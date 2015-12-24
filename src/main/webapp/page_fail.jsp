<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Oops!</title>
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <link rel="shortcut icon" href="../../css/icon.png">
</head>
<body>
<% String param = request.getParameter("choice");
    String info = "Something goes wrong..";
    String go = request.getParameter("go");
    if(go == null) go = "Home";
    if(param != null){
        if(param.equals("add new payment")){
            info += "Can't add a new payment to the DB. Merchant id: " +request.getParameter("merchant");
            info+=", customer id: " + request.getParameter("customerId");
        }
    }
    %>
<div class="container">
    <div class="form">
        <h3 id="header">Oops!</h3>
        <div class="navside">
            <ul>
                <li><a class="leftLink" href="view/choice.jsp?go=Payment"><p>Payments</p></a></li>
                <li><a class="leftLink" href="view/choice.jsp?go=Pay+List"><p>Pay List</p></a></li>
                <li><a class="leftLink" href="view/choice.jsp?go=Transfer+Money"><p>Transfer Money</p></a></li>
            </ul>
        </div>
        <div class="inner">
            <table class="input">
                <tr>
                    <td>
                    <td>
                        <img class="operation" src="../../css/error_red.ico"/>
                    </td>
                    <td>
                        <p id="operation">Fail :\</p>
                    </td>
                </tr>
            </table>
            <div class="info">
                <p id="ok">
                    <% out.print(info); %>
                </p>
            </div>
            <hr>
            <table class="button">
                <tr>
                    <form id="add" action="/index.jsp" method="get">
                        <td><input type="submit" value="Home" name="choice"></td>
                    </form>
                    <form action="view/choice.jsp" method="get">
                        <td id="center"><input type="submit" value="Back" name="choice"></td>
                        <input type="hidden" value="<%out.print(go);%>" name="go">
                    </form>
                </tr>
            </table>
        </div>
        <div class="footer">
            <p id="home"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></p>
        </div>
    </div>
</div>
</body>
</html>
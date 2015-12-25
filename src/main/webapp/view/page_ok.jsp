<%@ page import="com.bionic.edu.payment.PaymentService" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%  ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
    PaymentService paymentService = (PaymentService) context.getBean("paymentServiceImpl");
    String choice = request.getParameter("choice");
    String go = request.getParameter("go");
    String info = "";
    if(choice != null){
        if(choice.equals("Add New Payment")){
            paymentService.save(Integer.valueOf(request.getParameter("merchant")),
                    Integer.valueOf(request.getParameter("customerId")), Double.valueOf(request.getParameter("price")), request.getParameter("goods"));
            info = "Payment was successfully added to the DB =)";
        }
    }else response.sendRedirect("/page_fail.jsp?go=" + go);
%>
<head>
    <title>Cash Manager</title>
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <link rel="shortcut icon" href="../../css/icon.png">
    <meta http-equiv="refresh" content="5;url=choice.jsp?go=<%out.print(go);%>" />
</head>
<body>

<div class="container">
    <div class="form">
        <h3 id="header">Successful!</h3>
        <div class="navside">
            <ul>
                <li><a class="leftLink" href="choice.jsp?go=Merchant"><p>Merchant</p></a></li>
                <li><a class="leftLink" href="choice.jsp?go=Payment"><p>Payments</p></a></li>
                <li><a class="leftLink" href="choice.jsp?go=Pay+List"><p>Pay List</p></a></li>
                <li><a class="leftLink" href="choice.jsp?go=Transfer+Money"><p>Transfer Money</p></a></li>
            </ul>
        </div>
        <div class="inner">
            <table class="input">
                <tr>
                    <td>
                    <td>
                        <img class="operation" src="../../css/ok_green.ico"/>
                    </td>
                    <td>
                        <p id="operation">OK</p>
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
                    <form action="choice.jsp" method="get">
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
<%@ page import="com.bionic.edu.payment.Payment" %>
<%@ page import="com.bionic.edu.payment.PaymentService" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payments</title>
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <link rel="shortcut icon" href="../../css/icon.png">
</head>
<body>

<%  ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
    PaymentService paymentService = (PaymentService) context.getBean("paymentServiceImpl");
    List<Payment> list = null;
    String param = request.getParameter("choice");
    if(param != null){
        if(param.equals("Show All"))list = paymentService.findAll();
        else list = paymentService.findByMerchantId(Integer.valueOf(request.getParameter("merchant")));
    }else response.sendRedirect("page_fail.jsp");
    %>
<div class="container">
    <div class="form">
        <h3 id="header">Payment</h3>
        <div class="inner">
            <table class="list">
                <tr>
                    <th>Id</th>
                    <th>Date</th>
                    <th>mId</th>
                    <th>cId</th>
                    <th>Goods</th>
                    <th>Sum</th>
                    <th>Charge</th>
                </tr>
                <%
                    if(list != null) for(Payment p: list){
                        out.print("<tr>");
                        out.print("<td>");
                        out.print(p.getId());
                        out.println("</td>");
                        out.print("<td>");
                        out.print(p.getDt());
                        out.println("</td>");
                        out.print("<td>");
                        out.print(p.getMerchantId());
                        out.println("</td>");
                        out.print("<td>");
                        out.print(p.getCustomerId());
                        out.println("</td>");
                        out.print("<td>");
                        out.print(p.getGoods());
                        out.println("</td>");
                        out.print("<td>");
                        out.print(p.getSumPayed());
                        out.println("</td>");
                        out.print("<td>");
                        out.print(p.getChargePayed());
                        out.println("</td>");
                        out.println("</tr>");
                    }
                %>
            </table>
            <table class="button">
                <tr>
                    <form action="payments.jsp" method="get">
                        <td><input type="submit" value="Back"></td>
                    </form>
                    <form action="/index.jsp" method="get">
                        <td><input type="submit" value="Home"></td>
                    </form>
                </tr>
            </table>
        </div>
        <div class="footer">
            <p id="home"><a href="/index.jsp">Home</a></p>
        </div>
    </div>
</div>
</body>
</html>
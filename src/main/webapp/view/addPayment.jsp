<%@ page import="com.bionic.edu.merchant.Merchant" %>
<%@ page import="com.bionic.edu.merchant.MerchantService" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <link rel="shortcut icon" href="../../css/icon.png">
</head>
<body>
<%  ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
    MerchantService merchantService = (MerchantService) context.getBean("merchantServiceImpl");
%>
<div class="container">
    <div class="form">
        <h3 id="header">Payment</h3>
        <div class="inner">
            <table class="input">
                <tr>
                    <th>
                        <p>Select Merchant</p>
                    </th>
                    <th>
                        <p>Customer ID</p>
                    </th>
                    <th>
                        <p>Goods</p>
                    </th>
                    <th>
                        <p>Price</p>
                    </th>
                </tr>
                <tr>
                    <td>
                        <select form="add" class="box" name="merchant">
                            <%
                                for (Merchant m: merchantService.findAll()){
                                    out.print("<option" + " value=" +m.getId()+">" + m.getName() + "</option>");
                                }
                            %>
                        </select>
                    </td>
                    <td>
                            <input id="id" form="add" type="number" name="customerId" value="1" min="1" max="3">
                    </td>
                    <td>
                            <input id="goods" form="add" type="text" name="goods" value="Some goods..">
                    </td>
                    <td>
                            <input id="price" form="add" type="number" name="price" value="0.00" min="0" step="0.1">
                    </td>
                </tr>
            </table>
            <table class="button">
                <tr>
                    <form action="payments.jsp" method="get">
                        <td><input type="submit" value="back" name="choice"></td>
                    </form>
                    <form id="add" action="page_ok.jsp" method="post">
                        <td><input type="submit" value="add new payment" name="choice"></td>
                    </form>
                </tr>
            </table>
        </div>
        <div class="footer">
            <p id="home"><a href="">Home</a></p>
        </div>
    </div>
</div>
</body>
</html>

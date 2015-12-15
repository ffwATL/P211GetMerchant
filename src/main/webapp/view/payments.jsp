<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="com.bionic.edu.merchant.MerchantService" %>
<%@ page import="com.bionic.edu.merchant.Merchant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payments</title>
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <link rel="shortcut icon" href="../../css/icon.png">
</head>
<body>
<div class="container">
    <div class="form">
        <h3 id="header">Payments</h3>
        <div class="inner">
            <%  ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
                MerchantService merchantService = (MerchantService) context.getBean("merchantServiceImpl");
            %>
        <table class="input">
                <tr>
                    <td>
                        <select form="one" form="add" name="merchant">
                            <%
                                for (Merchant m: merchantService.findAll()){
                                    out.print("<option" + " value=" +m.getId()+">" + m.getName() + "</option>");
                                }
                            %>
                        </select>
                    </td>
                </tr>
            </table>
            <div class="info">
                <p>Select merchant from list above and click 'Show One' to view all the payments for only one merchant.
                    Click 'Add New' if you want to add a new payment for chosen merchant. Choose 'Show All' if you want to view all the
                    payments for every merchant.
                </p>
            </div>
            <table class="button">
                <tr>
                    <form action="showPayment.jsp" method="get">
                        <td><input type="submit" value="Show All" name="choice"></td>
                    </form>
                    <form id="one" action="showPayment.jsp" method="get">
                        <td><input type="submit" value="Show One" name="choice"></td>
                    </form>
                    <form id="add" action="addPayment.jsp" method="get">
                        <td><input type="submit" value="Add New" name="choice"></td>
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
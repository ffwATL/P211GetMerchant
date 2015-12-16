<%@ page import="com.bionic.edu.merchant.Merchant" %>
<%@ page import="com.bionic.edu.merchant.MerchantService" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pay List</title>
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <link rel="shortcut icon" href="../../css/icon.png">
</head>
<body>
<div class="container">
    <div class="form">
        <h3 id="header">Pay List</h3>
        <div class="inner">
            <%  ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
                MerchantService merchantService = (MerchantService) context.getBean("merchantServiceImpl");
            %>
            <table class="input">
                <tr>
                    <td>
                        <select form="one" name="merchant">
                            <%
                                for (Merchant m: merchantService.findAll()){
                                    out.print("<option" + " value=" + m.getId() + ">" + m.getName() + "</option>");
                                }
                            %>
                        </select>
                    </td>
                </tr>
            </table>
            <div class="info">
                <p>Select merchant from list above and click 'Show One' to view pay list for only one merchant.
                    Click 'Generate New' if you want to add a new list for chosen merchant. If list is already exist for current,
                    merchant it will be updated due to the DB data. Chose 'Show All' if you want to view pay list for every merchant.
                </p>
            </div>
            <table class="button">
                <tr>
                    <form action="showpaylist.jsp" method="get">
                        <td><input type="submit" value="Show All" name="choice"></td>
                    </form>
                    <form id="one" action="showpaylist.jsp" method="post">
                        <td><input type="submit" value="Show Single" name="choice"></td>
                        <td><input type="submit" value="Update Single" name="choice"></td>
                    </form>
                    <form action="showpaylist.jsp" method="post">
                        <td><input type="submit" value="Update All" name="choice"></td>
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
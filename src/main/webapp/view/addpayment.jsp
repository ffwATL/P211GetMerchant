<%@ page import="com.bionic.edu.merchant.Merchant" %>
<%@ page import="com.bionic.edu.merchant.MerchantService" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String go = request.getParameter("go");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
        MerchantService merchantService = (MerchantService) context.getBean("merchantServiceImpl");
    %>
    <title>Cash Manager</title>
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <link rel="shortcut icon" href="../../css/icon.png">
</head>
<body>

<div class="container">
    <div class="form">
        <h3 id="header"><%out.print(go);%></h3>
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
            <hr>
            <table class="button">
                <tr>
                    <form action="<%out.print(request.getHeader("referer"));%>" method="post">
                        <td><input type="submit" value="Back" name="choice"></td>
                        <input type="hidden" value="Payments" name="go">
                    </form>
                    <form id="add" action="page_ok.jsp" method="post">
                        <td id="center"><input class="update" type="submit" value="Add New Payment" name="choice"></td>
                        <input type="hidden" value="Payments" name="go">
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
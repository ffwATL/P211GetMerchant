<%@ page import="com.bionic.edu.merchant.MerchantService" %>
<%@ page import="com.bionic.edu.merchant.paylist.PayList" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bionic.edu.merchant.paylist.PayListService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page import="org.apache.logging.log4j.LogManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pay List</title>
    <link rel="shortcut icon" href="../../css/icon.png">
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
</head>
<body>
<%
    ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
    PayListService payListService = (PayListService) context.getBean("payListServiceImpl");
    String param = request.getParameter("choice");
    List<PayList> list = new ArrayList<>();
    if(param != null){
        int merchantId = Integer.valueOf(request.getParameter("merchant"));
        if(param.equals("Show All")) list = payListService.findAll();
        else if(param.equals("Show Single")) list.add(payListService.findByMerchantId(merchantId));
        else if(param.equals("Update Single")){
            payListService.addPayList(merchantId);
            list.add(payListService.findByMerchantId(merchantId));
        }else if(param.equals("Update All")) list = payListService.updateAll();
    }else response.sendRedirect("page_fail.jsp");
%>
<div class="container">
    <div class="form">
        <h3 id="header">Pay List</h3>
        <div class="inner">
            <table class="list">
                <tr>
                    <th>ID</th>
                    <th>Merchant ID</th>
                    <th>Period</th>
                    <th>Minimal Sum</th>
                    <th>Need To Send</th>
                </tr>
                <%
                    for (PayList p: list){
                        out.print("<tr>");
                        out.print("<td>");
                        out.print(p.getId());
                        out.print("</td>");
                        out.print("<td>");
                        out.print(p.getMerchantId());
                        out.print("</td>");
                        out.print("<td>");
                        out.print(p.getPeriod());
                        out.print("</td>");
                        out.print("<td>");
                        out.print(p.getMinSum());
                        out.print("</td>");
                        out.print("<td>");
                        out.print(p.getNeedToSend());
                        out.print("</td>");
                        out.println("</tr>");
                    }
                %>
            </table>
            <table class="button">
                <tr>
                    <form action="paylist.jsp" method="get">
                        <td><input type="submit" value="Back"></td>
                    </form>
                    <form action="/index.jsp" method="get">
                        <td id="center"><input type="submit" value="Home"></td>
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
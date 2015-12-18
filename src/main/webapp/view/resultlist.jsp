<%@ page import="com.bionic.edu.merchant.paylist.PayList" %>
<%@ page import="com.bionic.edu.merchant.paylist.PayListApp" %>
<%@ page import="com.bionic.edu.merchant.paylist.PayListService" %>
<%@ page import="com.bionic.edu.payment.Payment" %>
<%@ page import="com.bionic.edu.payment.PaymentService" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplate" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplatePayList_" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplatePayments" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        ChoiceTemplate choice = null;
        String from = request.getParameter("go");
        String choiceParam = request.getParameter("choice");
        List<PayList> payListList = null;
        List<Payment> paymentList = null;
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
        PaymentService paymentService;
        String idText = request.getParameter("merchant");
        int id = 0;
        PayListApp payListApp = (PayListApp) context.getBean("payListApp");

        if(idText != null) id = Integer.valueOf(idText);
        if(from.equals("Payment")) {
            choice = ChoiceTemplatePayments.getInstance();
            if(choiceParam.equals("Add New")){
                response.sendRedirect("addpayment.jsp?go=Payment");
            }
            paymentService = (PaymentService) context.getBean("paymentServiceImpl");
            if(choiceParam.equals("Show All")){
                paymentList = paymentService.findAll();
            }else {
                paymentList = paymentService.findByMerchantId(id);
            }
        }else if(from.equals("Pay List")){
            choice = ChoiceTemplatePayList_.getInstance();
            if(choiceParam.equals("Show All")) payListList = payListApp.findAll();
            else if(choiceParam.equals("Show Single")) payListList = payListApp.findByMerchantId(id);
            else if(choiceParam.equals("Update Single")) {
                payListApp.add(id);
                payListList = payListApp.findByMerchantId(id);
            }else {
                payListList = payListApp.updateAll();
            }
        }else response.sendRedirect("page_fail.jsp");

    %>
    <link rel="shortcut icon" href="../../css/icon.png">
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <title><%out.print(from);%></title>
</head>
<body>
<div class="container">
    <div class="form">
        <h3 id="header"><%out.print(from);%></h3>
        <div class="inner">
            <table class="list">
                <tr>
                    <%
                            for (String s: choice.getResultHeaders()){
                                out.println("<th>" + s + "</th>");
                            }
                    %>
                </tr>
                <%
                    if(from.equals("Pay List")){
                        for(PayList p: payListList){
                            out.println("<tr><td>" + p.getId() + "</td>");
                            out.println("<td>" + p.getMerchantId() + "</td>");
                            out.println("<td>" + p.getPeriod() + "</td>");
                            out.println("<td>" + p.getMinSum() + "</td>");
                            out.println("<td>" + p.getNeedToSend() + "</td>");
                            out.println("<td>" + p.getDt() + "</td></tr>");
                        }
                    }else if(from.equals("Payment")){
                        for(Payment p: paymentList){
                            out.println("<tr><td>" + p.getId() + "</td>");
                            out.println("<td>" + p.getDt() + "</td>");
                            out.println("<td>" + p.getMerchantId() + "</td>");
                            out.println("<td>" + p.getCustomerId() + "</td>");
                            out.println("<td>" + p.getGoods() + "</td>");
                            out.println("<td>" + p.getSumPayed() + "</td>");
                            out.println("<td>" + p.getChargePayed() + "</td></tr>");
                        }
                    }
                %>
            </table>
            <table class="button">
                <tr>
                    <form action="choice.jsp" method="get">
                        <td><input type="submit" value="Back"></td>
                        <input type="hidden" value="<%out.print(choice.getHeader());%>" name="go">
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
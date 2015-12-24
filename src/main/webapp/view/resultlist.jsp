<%@ page import="com.bionic.edu.merchant.paylist.PayList" %>
<%@ page import="com.bionic.edu.merchant.paylist.PayListService" %>
<%@ page import="com.bionic.edu.payment.Payment" %>
<%@ page import="com.bionic.edu.payment.PaymentService" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplate" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplatePayList_" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplatePayments" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplateTransfer" %>
<%@ page import="com.bionic.edu.transfer.TransferService" %>
<%@ page import="com.bionic.edu.transfer.TransferMoney" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        ChoiceTemplate choice = null;
        String go = request.getParameter("go");
        String choiceParam = request.getParameter("choice");
        List<PayList> payListList = null;
        List<Payment> paymentList = null;
        List<TransferMoney> transferList = null;
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
        int id = 0;
        if(request.getParameter("merchant") != null) id = Integer.valueOf(request.getParameter("merchant"));
        switch (go) {
            case "Payment":
                choice = ChoiceTemplatePayments.getInstance();
                if (choiceParam.equals("Add New")) {
                    response.sendRedirect("addpayment.jsp?go=Payment");
                }
                PaymentService paymentService = (PaymentService) context.getBean("paymentServiceImpl");
                if (choiceParam.equals("Show All")) {
                    paymentList = paymentService.findAll();
                } else {
                    paymentList = paymentService.findByMerchantId(id);
                }
                break;
            case "Pay List":
                choice = ChoiceTemplatePayList_.getInstance();
                PayListService  payListService = (PayListService) context.getBean("payListServiceImpl");
                switch (choiceParam) {
                    case "Show All":
                        payListList = payListService.findAll();
                        break;
                    case "Show Single":
                        payListList = payListService.findByMerchantId(id);
                        break;
                    case "Update Single":
                        payListService.add(id);
                        payListList = payListService.findByMerchantId(id);
                        break;
                    default:
                        payListService.updateAll();
                        payListList = payListService.findAll();
                        break;
                }
                break;
            case "Transfer Money":
                TransferService transferService = (TransferService) context.getBean("transferServiceImpl");
                choice = ChoiceTemplateTransfer.getInstance();
                switch (choiceParam){
                    case "Confirm":
                        String[] s = request.getParameterValues("id");
                        if(s != null){
                            int[] arr = new int [s.length];
                            for(int i=0; i < arr.length; i++){
                                arr[i] = Integer.valueOf(s[i]);
                            }
                            transferService.doTransfer(arr);
                        }
                    case "Show All":
                        transferList = transferService.findAll();
                        break;
                    case "Show Single":
                        transferList = transferService.findByMerchantId(id);
                        break;
                    case "New Transfer":
                        response.sendRedirect("transfer.jsp?go=" + go);
                        break;
                    default:
                        response.sendRedirect("/page_fail.jsp?go=" + go);
                        break;
                }
                break;
            default:
                response.sendRedirect("/page_fail.jsp?go="+go);
                break;
        }
    %>
    <link rel="shortcut icon" href="../../css/icon.png">
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <title><%out.print(go);%></title>
</head>
<body>
<div class="container">
    <div class="form">
        <div class="head">
            <h3 id="header"><%out.print(go);%></h3>
        </div>
        <div class="navside">
            <ul>
                <li><a class="leftLink" href="choice.jsp?go=Payment"><p>Payments</p></a></li>
                <li><a class="leftLink" href="choice.jsp?go=Pay+List"><p>Pay List</p></a></li>
                <li><a class="leftLink" href="choice.jsp?go=Transfer+Money"><p>Transfer Money</p></a></li>
            </ul>
        </div>
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
                    if(go.equals("Pay List")){
                        for(PayList p: payListList){
                            out.println("<tr class=\"show\"><td>" + p.getId() + "</td>");
                            out.println("<td>" + p.getMerchantId() + "</td>");
                            out.println("<td>" + p.getPeriod() + "</td>");
                            out.println("<td>" + p.getMinSum() + "</td>");
                            out.println("<td>" + p.getNeedToSend() + "</td>");
                            out.println("<td>" + p.getDt() + "</td></tr>");
                        }
                    }else if(go.equals("Payment")){
                        for(Payment p: paymentList){
                            out.println("<tr class=\"show\"><td>" + p.getId() + "</td>");
                            out.println("<td>" + p.getDt() + "</td>");
                            out.println("<td>" + p.getMerchantId() + "</td>");
                            out.println("<td>" + p.getCustomerId() + "</td>");
                            out.println("<td>" + p.getGoods() + "</td>");
                            out.println("<td>" + p.getSumPayed() + "</td>");
                            out.println("<td>" + p.getChargePayed() + "</td></tr>");
                        }
                    }else if(go.equals("Transfer Money") && transferList != null){
                        for (TransferMoney tm: transferList){
                            out.println("<tr class=\"show\"><td>" + tm.getId() + "</td>");
                            out.println("<td>" + tm.getMerchantId() + "</td>");
                            out.println("<td>" + tm.getPayListId() + "</td>");
                            out.println("<td>" + tm.getSumSent() + "</td>");
                            out.println("<td>" + tm.getDt() + "</td></tr>");
                        }
                    }
                %>
            </table>
            <hr>
            <table class="button">
                <tr>
                    <form action="choice.jsp" method="get">
                        <td><input type="submit" value="Back"></td>
                        <input type="hidden" value="<%out.print(choice.getHeader());%>" name="go">
                    </form>
                    <form action="<%if(go.equals("Pay List")) out.print("choice.jsp");
                        else out.print("/index.jsp");%>" method="get">
                        <td id="center"><input <%if(go.equals("Pay List"))out.print("class=\"update\"");%>type="submit" value=
                        <%if(go.equals("Pay List"))out.print("Next"); else out.print("Home");%>></td>
                        <%
                            if(go.equals("Pay List")){
                                out.print("<input type=\"hidden\" value=\"Transfer Money\" name=\"go\">");
                            }
                        %>
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
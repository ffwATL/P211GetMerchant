<%@ page import="com.bionic.edu.merchant.MerchantService" %>
<%@ page import="com.bionic.edu.payment.Payment" %>
<%@ page import="com.bionic.edu.payment.PaymentService" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="com.bionic.edu.merchant.Merchant" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="com.bionic.edu.GetMerchantException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%  ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
    PaymentService paymentService = (PaymentService) context.getBean("paymentServiceImpl");
    MerchantService merchantService = (MerchantService) context.getBean("merchantServiceImpl");
    String param = request.getParameter("choice");
    String info = "";
    String back = "";
    if(param != null){
        if(param.equals("add new payment")){
            back = "payments.jsp";
            Payment p = new Payment();
            Merchant m = merchantService.findById(Integer.valueOf(request.getParameter("merchant")));
            double sumPayed = Double.valueOf(request.getParameter("price"));
            if(m != null) p.setMerchant(m);
            else response.sendRedirect("page_fail.jsp");
            p.setGoods(request.getParameter("goods"));
            p.setCustomerId(Integer.valueOf(request.getParameter("customerId")));
            p.setDt(new Timestamp(System.currentTimeMillis()));
            p.setSumPayed(sumPayed);
            p.setChargePayed((m.getCharge() * sumPayed) / 100);
            try {
                paymentService.save(p);
                merchantService.updateNeedToSend(m.getId(), sumPayed);
                info = "Payment was successfully added to the DB. Transaction Id: " + p.getId();
            } catch (GetMerchantException e) {
                response.sendRedirect("page_fail.jsp");
            }
        }
    }else response.sendRedirect("page_fail.jsp");
%>
<head>
    <title>Ok</title>
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <link rel="shortcut icon" href="../../css/icon.png">
    <meta http-equiv="refresh" content="5;url=<%out.print(back);%>" />
</head>
<body>

<div class="container">
    <div class="form">
        <h3 id="header">Successful!</h3>
        <div class="inner">
            <table class="input">
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
            <table class="button">
                <tr>
                    <form id="add" action="/index.jsp" method="get">
                        <td><input type="submit" value="Home" name="choice"></td>
                    </form>
                    <form action=<%out.print(back);%> method="get">
                        <td><input type="submit" value="back" name="choice"></td>
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
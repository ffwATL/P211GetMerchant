<%@ page import="com.bionic.edu.merchant.paylist.PayList" %>
<%@ page import="com.bionic.edu.merchant.paylist.PayListService" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplate" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplatePayList_" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplateTransfer" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String table = "";
    String go = request.getParameter("go");
    String choice = request.getParameter("choice");
    String ssum = request.getParameter("sum");
    String sAlgorithm = request.getParameter("algorithm");
    ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
    ChoiceTemplate choiceTransferTemplate = null;
    ChoiceTemplate payListTemplate = ChoiceTemplatePayList_.getInstance();
    PayListService payListService = (PayListService) context.getBean("payListServiceImpl");
    List<PayList> payListList = payListService.findFilteredUnpaid();
    List<PayList> green = new LinkedList<>();
    List<PayList> red = new LinkedList<>();
    double sum = 0;
    if(go != null && go.equals("Money Transfer") || go.equals("Transfer Money")){
        choiceTransferTemplate = ChoiceTemplateTransfer.getInstance();
    }else response.sendRedirect("/page_fail.jsp?go=" + go);
    if(choice != null && choice.equals("Next")){
        if(ssum != null) sum = Double.valueOf(ssum);
        for(PayList p: payListList){
            if(sum >= p.getNeedToSend()){
                green.add(p);
                sum -= p.getNeedToSend();
            } else red.add(p);
        }
    }
%>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <link rel="shortcut icon" href="../../css/icon.png">
    <title><%out.print(go);%></title>
</head>
<body>
<div class="container">
    <div class="form">
        <h3 id="header"><%if(choiceTransferTemplate != null) out.print(choiceTransferTemplate.getHeader());%></h3>
        <div class="inner">
            <div class="header">
                <p>Current unpaid paylists: </p>
            </div>
            <table class="list">
                <tr>
                    <%
                        for (String s: payListTemplate.getResultHeaders()){
                            out.println("<th>" + s + "</th>");
                        }
                    %>
                </tr>
                <%!
                    public String drawTable(List<PayList> list, String color){
                        StringBuilder builder = new StringBuilder();
                        for(PayList p: list){
                            builder.append("<tr class=\""+color+"\"><td>" + p.getId() + "</td>");
                            builder.append("<td>" + p.getMerchantId() + "</td>");
                            builder.append("<td>" + p.getPeriod() + "</td>");
                            builder.append("<td>" + p.getMinSum() + "</td>");
                            builder.append("<td>" + p.getNeedToSend() + "</td>");
                            builder.append("<td>" + p.getDt() + "</td></tr>");
                        }
                        return builder.toString();
                    }
                %>
                <%
                    if(choice == null){
                        out.println(drawTable(payListList,"show"));
                    }else {
                        out.println(drawTable(green,"green"));
                        out.println(drawTable(red,"red"));
                    }
                %>
            </table>
            <div class="info">
                <p>Enter the amount of money for further processing</p>
            </div>
            <table class="input">
                <tr>
                    <td>
                        <p class="label">Available sum: </p>
                    </td>
                    <td>
                        <input id="price" form="add" type="number" name="sum" value="<%if(ssum!=null) out.print(ssum);else out.print(1);%>" min="1" <%if(choice != null) out.print("disabled");%>>
                    </td>
                    <%
                        if(choice == null){
                            out.println("<td><select id=\"transfer\" form=\"add\" name=\"algorithm\">");
                            out.println("<option value=\"1\">As added</option>");
                            out.println("<option value=\"2\">Low to high</option>");
                            out.println("<option value=\"3\">High to low</option></select></td>");
                        }
                    %>
                </tr>
            </table>
            <table class="button">
                <tr>
                    <form action="choice.jsp" method="get">
                        <td><input type="submit" value="Back" name="choice"></td>
                        <input type="hidden" value="<%out.print(go);%>" name="go">
                    </form>
                    <form id="add" action="transfer.jsp" method="post">
                        <td id="center"><input class="update" type="submit" value="Next" name="choice"></td>
                        <input type="hidden" value="Transfer Money" name="go">
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
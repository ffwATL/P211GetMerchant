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
<%!
    public double getTotal(List<PayList> list){
        double sD = 0;
        for(PayList p: list){
            sD += p.getNeedToSend();
        }
        return sD;
    }
%>
<%
    String go = request.getParameter("go");
    String choice = request.getParameter("choice");
    String sumString = request.getParameter("sum");
    String sAlgorithm = request.getParameter("algorithm");
    int a = 1;
    if(sAlgorithm != null) a = Integer.valueOf(sAlgorithm);
    ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
    ChoiceTemplate choiceTransferTemplate = null;
    ChoiceTemplate payListTemplate = ChoiceTemplatePayList_.getInstance();
    PayListService payListService = (PayListService) context.getBean("payListServiceImpl");
    List<PayList> payListList = payListService.findFilteredUnpaid(a);
    List<PayList> green = new LinkedList<>();
    List<PayList> red = new LinkedList<>();
    double sum = 0;
    if(go != null && go.equals("Money Transfer") || go != null && go.equals("Transfer Money")){
        choiceTransferTemplate = ChoiceTemplateTransfer.getInstance();
    }else response.sendRedirect("/page_fail.jsp?go=" + go);
    if(choice != null && choice.equals("Next")){
        if(sumString != null) sum = Double.valueOf(sumString);
        List<List<PayList>> greenRedFiltered = payListService.getGreenRedFiltered(payListList, sum);
        green = greenRedFiltered.get(0);
        red = greenRedFiltered.get(1);
    }
%>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <link rel="shortcut icon" href="../../css/icon.png">
    <title>Cash Manager</title>
</head>
<body>
<div class="container">
    <div class="form">
        <h3 id="header"><%if(choiceTransferTemplate != null) out.print(choiceTransferTemplate.getHeader());%></h3>
        <div class="navside">
            <ul>
                <li><a class="leftLink" href="choice.jsp?go=Payment"><p>Payments</p></a></li>
                <li><a class="leftLink" href="choice.jsp?go=Pay+List"><p>Pay List</p></a></li>
                <li><a class="leftLink" href="choice.jsp?go=Transfer+Money"><p>Transfer Money</p></a></li>
            </ul>
        </div>
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
                            builder.append("<tr class=\"").append(color).append("\"><td>").append(p.getId()).append("</td>");
                            builder.append("<td>").append(p.getMerchantId()).append("</td>");
                            builder.append("<td>").append(p.getPeriod()).append("</td>");
                            builder.append("<td>").append(p.getMinSum()).append("</td>");
                            builder.append("<td>").append(p.getNeedToSend()).append("</td>");
                            builder.append("<td>").append(p.getDt()).append("</td></tr>");
                        }
                        return builder.toString();
                    }
                %>
                <%
                    if(choice == null || choice.equals("Back")){
                        out.println(drawTable(payListList,"show"));
                    }else {
                        out.println(drawTable(green,"green"));
                        out.println(drawTable(red,"red"));
                    }
                %>
            </table>
            <div class="info">
                <%
                    if(choice == null) out.print("<p>Enter the amount of money for further processing</p>");
                %>
            </div>
            <table class="input">
                <tr>
                    <td>
                        <p class="label">Available sum: </p>
                    </td>
                    <td>
                        <input id="price" form="add" type="number" name="sum" value="<%if(sumString!=null) out.print(sumString);else out.print(1);%>" min="1"
                            <%if(choice != null && !choice.equals("Back")) out.print("disabled");%>>
                    </td>
                    <td>
                        <p class="label">Required: </p>
                    </td>
                    <td>
                        <%
                            out.print("<input id=\"priceCalc\" type=\"number\" value=" +getTotal(payListList)+ " disabled>");
                        %>
                    </td>
                    <%
                        if(choice == null || choice.equals("Back")){
                            out.println("<td><select id=\"transfer\" form=\"add\" name=\"algorithm\">");
                            out.println("<option value=\"1\">As added</option>");
                            out.println("<option value=\"2\">Low to high</option>");
                            out.println("<option value=\"3\">High to low</option></select></td>");
                        }
                    %>
                </tr>
            </table>
            <hr>
            <table class="button">
                <tr>
                    <form action="<%if(choice!=null && choice.equals("Next")) out.print("transfer.jsp"); else out.print("choice.jsp");%>" method="post">
                        <td><input type="submit" value="Back" name="choice"></td>
                        <input type="hidden" value="<%out.print(go);%>" name="go">
                        <input type="hidden" value="<%if(sumString != null) out.print(sumString); else out.print(1); %>" name="sum">
                    </form>
                    <form id="add" action="<%if(choice!=null && choice.equals("Next")) out.print("resultlist.jsp");
                        else out.print("transfer.jsp");%>" method="get">
                        <td id="center"><input class="update" type="submit" value="<%if(choice!=null && choice.equals("Next")) out.print("Confirm");
                        else out.print("Next");%>" name="choice"></td>
                        <input type="hidden" value="Transfer Money" name="go">
                        <%
                            if(choice != null && choice.equals("Next")){
                                for(PayList p: green){
                                    out.print("<input type=\"hidden\" value=\"" + p.getId()+ "\" name=\"id\">");
                                }
                            }
                        %>
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
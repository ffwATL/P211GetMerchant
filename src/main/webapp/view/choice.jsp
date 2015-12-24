<%@ page import="com.bionic.edu.merchant.Merchant" %>
<%@ page import="com.bionic.edu.merchant.MerchantService" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplate" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplatePayList_" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplatePayments" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplateTransfer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String go = request.getParameter("go");
        ChoiceTemplate choice = null;
        switch (go) {
            case "Pay List":
                choice = ChoiceTemplatePayList_.getInstance();
                break;
            case "Payments":
            case "Payment":
                choice = ChoiceTemplatePayments.getInstance();
                break;
            case "Transfer Money":
            case "Money Transfer":
                choice = ChoiceTemplateTransfer.getInstance();
                break;
            case "Home":
                response.sendRedirect("/index.jsp");
                break;
            default:
                response.sendRedirect("/page_fail.jsp?go=" + go);
                break;
        }
        boolean even = (choice.getButtonsAdd().size() + choice.getButtonsShow().size()) % 2 == 0;
    %>
    <title>Cash Manager</title>
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <link rel="shortcut icon" href="../../css/icon.png">
</head>
<body>
<div class="container">
    <div class="form">
        <h3 id="header"><%out.print(choice.getHeader());%></h3>
        <div class="navside">
            <ul>
                <li><a class="leftLink" href="choice.jsp?go=Payment"><p>Payments</p></a></li>
                <li><a class="leftLink" href="choice.jsp?go=Pay+List"><p>Pay List</p></a></li>
                <li><a class="leftLink" href="choice.jsp?go=Transfer+Money"><p>Transfer Money</p></a></li>
            </ul>
        </div>
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
                <p><%out.print(choice.getInfoText());%></p>
            </div>
            <hr>
            <table class="button">
                <tr>
                    <form id="one" action="resultlist.jsp" method="post">
                    <%
                        for(String s: choice.getButtonsShow()){
                            out.print("<td><input type=\"submit\" value=\"" + s + "\" name=\"choice\"></td>");
                        }
                        String center = "";
                        if(even) center += " id=\"center\"";
                        String td = "<td" + center + ">";
                        for(int i = 0; i < choice.getButtonsAdd().size(); i++){
                            if(i > 0) td = "<td>";
                            out.print(td + "<input class=\"update\" type=\"submit\" value=\"" + choice.getButtonsAdd().get(i) +
                                    "\" name=\"choice\"></td>");
                        }
                    %>
                        <input type="hidden" value="<%out.print(choice.getHeader());%>" name="go">
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
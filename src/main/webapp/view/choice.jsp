<%@ page import="com.bionic.edu.merchant.Merchant" %>
<%@ page import="com.bionic.edu.merchant.MerchantService" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplate" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplatePayList_" %>
<%@ page import="com.bionic.edu.util.ChoiceTemplatePayments" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        ChoiceTemplate choice = null;
        if(request.getParameter("go").equals("Pay List")){
            choice = ChoiceTemplatePayList_.getInstance();
        }else if(request.getParameter("go").equals("Payments") || request.getParameter("go").equals("Payment")){
            choice = ChoiceTemplatePayments.getInstance();
        }else response.sendRedirect("page_fail.jsp");
        boolean even = (choice.getButtonsAdd().size() + choice.getButtonsShow().size()) % 2 == 0;
    %>
    <title><%out.print(choice.getHeader());%></title>
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <link rel="shortcut icon" href="../../css/icon.png">
</head>
<body>
<div class="container">
    <div class="form">
        <h3 id="header"><%out.print(choice.getHeader());%></h3>
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
            <table class="button">
                <tr>

                    <form id="one" action="resultlist.jsp" method="get">
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
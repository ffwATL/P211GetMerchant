<%@page import="com.bionic.edu.merchant.MerchantService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" http://www.w3.org/TR/htm4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="Style.css" />
<title>Merchant List JSP</title>
</head>
<body>
<h1>Merchant List</h1>
<table>
    <tr><td>Name<td>Bank<td>Charge<td>Minimum 	Sum</tr>
    <%org.springframework.context.ApplicationContext context = new 	org.springframework.context.support.ClassPathXmlApplicationContext 	("spring/application-config.xml");
    com.bionic.edu.merchant.MerchantService merchantService = (MerchantService) context.getBean("merchantServiceImpl");
     java.util.List<com.bionic.edu.merchant.Merchant> list = merchantService.findAll();
       	for(com.bionic.edu.merchant.Merchant m : list){
    		out.print(m.getDataForWeb());
     	}
    %>
</table>
</body>
</html>
     
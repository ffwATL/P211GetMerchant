<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>P211GetMerchant</title>
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <link rel="shortcut icon" href="../../css/icon.png">
</head>
<body>
<div class="container">
    <div class="form">
        <h3 id="header">Getting started</h3>
        <div class="inner">
            <table class="button">
                <tr>
                    <th><p>Choose Your Destiny</p></th>
                </tr>
                <tr>
                    <form action="view/payments.jsp" method="get">
                        <td><input type="submit" value="Payments" name="go"></td>
                    </form>
                </tr>
                <tr>
                    <form action="view/payList.jsp" method="get">
                        <td><input type="submit" value="Pay List" name="go"></td>
                    </form>
                </tr>
                <tr>
                    <form action="view/moneyTransfer.jsp" method="get">
                        <td><input type="submit" value="Money Transfer" name="go"></td>
                    </form>
                </tr>
            </table>
        </div>
        <div class="footer">
            <p>.</p>
        </div>
    </div>
</div>
</body>

</html>

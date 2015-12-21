<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="../../css/Style.css">
    <link rel="shortcut icon" href="../../css/icon.png">
</head>
<body>
<div class="container">
    <div class="form">
        <h3 id="header">Getting started</h3>
        <div class="inner1">
            <p id="index">Choose Your Destiny</p>
            <ul>
                <li><a class="leftLink" href="view/choice.jsp?go=Payment"><p>Payments</p></a></li>
                <li><a class="leftLink" href="view/choice.jsp?go=Pay+List"><p>Pay List</p></a></li>
                <li><a class="leftLink" href="view/choice.jsp?go=Transfer+Money"><p>Transfer Money</p></a></li>
            </ul>
        </div>
        <div class="footer">
            <p>.</p>
        </div>
    </div>
</div>
</body>
</html>
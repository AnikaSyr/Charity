
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Register</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>

<header>
    <nav class="container container--70">
<%@include file="fragments/header.jsp"%>
</header>
<body>
<section class="login-page">

    <h2>Załóż konto</h2>
<form:form action="/register" method="post"  id="register" modelAttribute="userDTO">

        <div class="form-group">
            <form:input type="email" name="email" placeholder="Email"  path="email"/>
            <form:errors path="email"/>
        </div>
        <div class="form-group">
            <form:input type="password" name="password" placeholder="Hasło"  path="password"/>
            <form:errors path="password"/>
        </div>
        <div class="form-group">
            <form:input type="password" name="rpassword" placeholder="Powtórz hasło"  path="rpassword"/>
            <form:errors path="rpassword"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/login"/>" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
</form:form>
</section>

<%@include file="fragments/footer.jsp"%>


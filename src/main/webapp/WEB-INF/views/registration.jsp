<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pushk
  Date: 08.12.2021
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form:form method="post" modelAttribute="userForm">
    <h2>Регистрация</h2>
    <div>
    <form:input type="text" path="username" placeholder="Username"
                autofocus="true"></form:input>
    <form:errors path="username"></form:errors>
    ${usernameError}
    </div>
    <div>
    <form:input type="password" path="password" placeholder="Password"></form:input>
    </div>
    <div>
    <form:input type="password" path="passwordConfirm"
                placeholder="Confirm your password"></form:input>
    <form:errors path="password"></form:errors>
    ${passwordError}
    </div>
    <button type="submit">Зарегистрироваться</button>
</form:form>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pushk
  Date: 30.12.2021
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<table>
    <thead>
    <th>Currency</th>
    <th>National currency</th>
    <th>Buy</th>
    <th>Sale</th>
    </thead>
    <c:forEach items="${exchangeList}" var="exchange">
        <tr>
            <td>${exchange.ccy}</td>
            <td>${exchange.baseCcy}</td>
            <td>${exchange.buy}</td>
            <td>${exchange.sale}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

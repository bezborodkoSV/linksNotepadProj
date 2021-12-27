<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pushk
  Date: 19.12.2021
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Change link</h1>
<form:form action="/changeLinkParameter" method="post" modelAttribute="linkByName">
    <form:hidden path="id"></form:hidden>
    <div><form:input path="name" placeholder="${linkByName.name}"></form:input>
        <form:errors path="name"></form:errors>
        ${errorLinkNameInGroup}
    </div>
    <div><form:input path="url" placeholder="${linkByName.url}"></form:input></div>
    <div><form:input path="description" placeholder="${linkByName.description}"></form:input></div>
    <div><form:input path="yourNotesAboutLink" placeholder="${linkByName.yourNotesAboutLink}"></form:input></div>
    <form:hidden path="userInfo"></form:hidden>
    <%--    <form:hidden path="linkGroups"></form:hidden>--%>
    <div><form:select path="linkGroups">
        <%--        show name Group --%>
        <form:option value="${linkByName.linkGroups.id}">${linkByName.linkGroups.nameGroup}</form:option>
        <%--        forEch lisGroup--%>
        <form:options items="${linkGroupList}" itemLabel="nameGroup" itemValue="id"></form:options>
    </form:select></div>
    <div>
        <button type="submit" value="update">Update</button>
    </div>
</form:form>
</body>
</html>

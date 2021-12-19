<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pushk
  Date: 28.11.2021
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4><a href="/logout">Logout</a></h4>

<%--<form method="post" action="saveLink">--%>

<form:form method="post" modelAttribute="saveLinkGroup" action="/addLinkGroup">
    <h2>Create link groups</h2>
    <form:input path="nameGroup" placeholder="Name link groups"></form:input>
    <<button type="submit" value="save">Add group</button>
</form:form>

<form:form method="post" modelAttribute="saveLink" action="/addLink">
    <h2>Add Link</h2>
    <div>
        <form:input path="name" placeholder="Name link"></form:input>
    </div>
    <div>
        <form:input path="url" placeholder="URL"></form:input>
    </div>
    <div>
        <form:input path="description" placeholder="Description"></form:input>
    </div>
    <div>
        <form:input path="yourNotesAboutLink" placeholder="Your notes about Link"></form:input>
    </div>
    <input name="nameGroup" type="hidden" value="${nameGroup}">
    <button type="submit" value="save">Add link</button>
</form:form>
</form>

<h2>Groups</h2>
<div>

    <c:forEach items="${linkGroups}" var="group">
        <li><a href="/private/home/${group.nameGroup}">${group.nameGroup}</a></li>
    </c:forEach>

    <%--    <form:form method="get" modelAttribute="nameGroup" action="/prvate/home/${nameGroup}" va>--%>
<%--    <select>--%>
<%--        <c:forEach items="${linkGroups}" var="group">--%>
<%--&lt;%&ndash;            <form method="post" action="/${group.nameGroup}" modelAtribut="">&ndash;%&gt;--%>
<%--&lt;%&ndash;            <form:select path="nameGroup" items="${group.nameGroup}"></form:select>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <option value="${group.nameGroup}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        ${group.nameGroup}&ndash;%&gt;--%>
<%--&lt;%&ndash;                </option>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </form>&ndash;%&gt;--%>
<%--            <form:form method="post" action="/private/home/${group.nameGroup}">--%>
<%--                <input type="hidden" name="nameGroup" value="${group.nameGroup}">--%>
<%--                <button type="submit">${group.nameGroup}</button>--%>
<%--            </form:form>--%>
<%--        </c:forEach>--%>
<%--    </select>--%>
    <%--    </form:form>--%>
</div>
<h1>List links</h1>
<div>
    <table>
        <thead>
        <th>ID</th>
        <th>Name</th>
        <th>URL</th>
        <th>Description</th>
        <th>NotesAboutLink</th>
        <th>Group</th>
        </thead>
        <c:forEach items="${allLinks}" var="link">
            <tr>
                <td>${link.id}</td>
                <td>${link.name}</td>
                <td><a href="${link.url}">${link.url}</a></td>
                <td>${link.description}</td>
                <td>${link.yourNotesAboutLink}</td>
                <td>${link.linkGroups.nameGroup}</td>
                <form action="/private/changeLinkParameter/${link.linkGroups.nameGroup}/${link.name}">
                <td><button>Change</button> </td>
                </form>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

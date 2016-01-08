<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>Список людей</title>
        <link rel="stylesheet" type="text/css"
                        href="<c:url value="/resources/style.css" />" >
    </head>
    <body>
        <div class="basic">
            <h1>Список людей</h1>
            <table>
                <tr><td>Имя</td><td>Возраст</td><td>Дата регистрации</td><td>Админ</td></tr>
                <c:forEach items="${personsList}" var="person">
                    <tr>
                    <td><c:out value="${person.name}" /></td>
                    <td><c:out value="${person.age}" /></td>
                    <td><c:out value="${person.createdDate}" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${person.isAdmin()}">Да</c:when>
                            <c:otherwise>Нет</c:otherwise>
                        </c:choose>
                    </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
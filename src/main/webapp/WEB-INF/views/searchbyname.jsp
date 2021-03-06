<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>Поиск по имени</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
    </head>
    <body>
        <div class="basic">
        <h1>Поиск по имени</h1>
        <form method="POST">
            <input type="text" name="name">
            <input type="SUBMIT" value="Поиск">
        </form>
        <table align="center">
            <tr><td>Имя</td><td>Возраст</td><td>Дата регистрации</td><td>Админ</td>
            <td>Удаление</td><td>Изменение</td></tr>
            <c:forEach items="${personsList}" var="person">
                <tr>
                <td><c:out value="${person.name}" /></td>
                <td><c:out value="${person.age}" /></td>
                <td><c:out value="${person.getSimpleDate()}" /></td>
                <td>
                    <c:choose>
                        <c:when test="${person.isAdmin()}">Да</c:when>
                        <c:otherwise>Нет</c:otherwise>
                    </c:choose>
                </td>
                <td>
                <form method="POST" action="<c:url value="/people/delete" />" >
                    <input type="hidden" name="id" value="${person.id}" />
                    <input type="Submit" value="Удалить" />
                </form>
                </td>
                <td>
                <form method="POST" action="<c:url value="/people/change" />" >
                    <input type="hidden" name="id" value="${person.id}" />
                    <input type="submit" value="Изменить" />
                </form>
                </td>
                </tr>
            </c:forEach>
        </table>
        <p><a href="<c:url value="/people" />">Вернуться к списку людей</a></p>
        </div>
    </body>
</html>
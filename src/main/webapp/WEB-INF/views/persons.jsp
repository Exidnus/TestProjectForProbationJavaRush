<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>Список людей</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
    </head>
    <body>
        <div class="basic">
            <h1>Список людей</h1>
            <table>
                <tr><td>Имя</td><td>Возраст</td><td>Дата регистрации</td><td>Админ</td><td>Удаление</td></tr>
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
                    </tr>
                </c:forEach>
            </table>
            <p><a href="<c:url value="/people/add" />">Добавить человека</a></p>
            <p><a href="<c:url value="/people/search" />">Поиск по имени</a></p>
            <p><a href="<c:url value="/homepage" />">Вернуться к списку проектов</a></p>
        </div>
    </body>
</html>
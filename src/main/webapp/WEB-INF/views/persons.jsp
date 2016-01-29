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
            <table align="center">
                <tr><td>Имя</td><td>Возраст</td><td>Дата регистрации</td>
                    <td>Админ</td><td>Удаление</td><td>Изменение</td></tr>
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
                        <input type="submit" value="Удалить" />
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
            <table align="center"><tr>
                <td>
                <form method="GET" action="<c:url value="/people/previous" />" >
                    <input type="submit" value="Предыдущая страница" />
                </form>
                </td>
                <td>
                <form method="GET" action="<c:url value="/people/next" />" >
                    <input type="submit" value="Следующая страница" />
                </form>
                </td>
            </tr></table>
            <h2>Упорядочить по:</h2>
            <table align="center">
                <tr>
                    <td>
                        <form method="POST" action="<c:url value="/people/changeorder" />" >
                            <input type="hidden" name="newOrder" value="fromAtoZ" />
                            <input type="submit" value="От А до Я" />
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="<c:url value="/people/changeorder" />" >
                            <input type="hidden" name="newOrder" value="fromZtoA" />
                            <input type="submit" value="От Я до А" />
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form method="POST" action="<c:url value="/people/changeorder" />" >
                            <input type="hidden" name="newOrder" value="ageAsc" />
                            <input type="submit" value="По возрастанию возраста" />
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="<c:url value="/people/changeorder" />" >
                            <input type="hidden" name="newOrder" value="ageDesc" />
                            <input type="submit" value="По убыванию возраста" />
                        </form>
                    </td>
                </tr>
            </table>
            <p><a href="<c:url value="/people/add" />">Добавить человека</a></p>
            <p><a href="<c:url value="/people/search" />">Поиск по имени</a></p>
            <p><a href="<c:url value="/homepage" />">Вернуться к списку проектов</a></p>
        </div>
    </body>
</html>
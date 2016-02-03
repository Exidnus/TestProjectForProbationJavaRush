<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>Изменение записи человека</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
    </head>
    <body>
        <div class="basic">
        <h1>Измените данные</h1>
        <c:choose>
            <c:when test="${isWrongInput}">
                <p class="wrong">
                    Неверный ввод! Пожалуйста, заполните все поля! Имя должно быть не короче
                    двух букв, возраст от 1 включительно до 100 включительно.
                </p>
            </c:when>
        </c:choose>
        <form method="POST" action="<c:url value="/people/performchange" />" >
            Имя: <input type="text" name="name" value="${person.name}" required /><br/>
            Возраст: <input type="text" name="age" value="${person.age}" required /><br/>
            Админ: <br/>
            <c:choose>
                <c:when test="${person.isAdmin()}">
                    <input type="radio" name="isAdmin" value="yes" checked/>Да<br/>
                    <input type="radio" name="isAdmin" value="no" />Нет<br/>
                </c:when>
                <c:otherwise>
                    <input type="radio" name="isAdmin" value="yes" />Да<br/>
                    <input type="radio" name="isAdmin" value="no" checked/>Нет<br/>
                </c:otherwise>
            </c:choose><br/>
            <input type="hidden" name="id" value="${person.id}">
            <input type="submit" value="Сохранить изменения">
        </form>
        <p><a href="<c:url value="/people" />">Вернуться к списку людей</a></p>
        <p><a href="<c:url value="/homepage" />">Вернуться к списку проектов</a></p>
        </div>
    </body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>Добавление человека в список</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
    </head>
    <body>
        <div class="basic">
        <h1>Добавление человека в список</h1>
        <form method="POST" action="<c:url value="/people/adding" />" >
            Имя: <input type="text" name="name" /><br/>
            Возраст: <input type="text" name="age" /><br/>
            Админ: <br/>
            <input type="radio" name="isAdmin" value="yes" />Да<br/>
            <input type="radio" name="isAdmin" value="no" />Нет<br/>
            <input type="SUBMIT" value="Добавить" />
        </form>
        <p><a href="<c:url value="/people" />">Вернуться к списку людей</a></p>
        </div>
    </body>
</html>
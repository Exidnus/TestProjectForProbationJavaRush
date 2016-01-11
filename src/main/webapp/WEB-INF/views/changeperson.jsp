<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>Изменение записи человека</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
    </head>
    <body>
        <h1>Измените данные</h1>
        <form method="POST" action=action="<c:url value="/people/changing" />" >
            <input type="text" name="name" value="${person.name}">
            <input type="text" name="age" value="{person.age}">
            <input type="submit" name="Изменить">
        </form>
    </body>
</html>
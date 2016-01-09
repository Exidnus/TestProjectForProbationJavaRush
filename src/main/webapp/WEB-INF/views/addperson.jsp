<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>Добавление человека в список</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
    </head>
    <body>
        <h1>Добавление человека в список</h1>
        <form method="GET" action="<c:url value="/people" />" >
            Имя: <input type="text" name="name" /><br/>
            Возраст: <input type="text" name="age" /><br/>
            <input type="checkbox" name = "isAdmin" value="yes" /> Админ<br/>
            <input type="Submit" value="Добавить" />
        </form>
    </body>
</html>
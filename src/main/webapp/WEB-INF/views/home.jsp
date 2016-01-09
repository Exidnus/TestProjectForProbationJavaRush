<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>Тестовый проект Варыгин Д.В.</title>
        <link rel="stylesheet" type="text/css"
                href="<c:url value="/resources/style.css" />" >
    </head>
    <body>
        <div class="basic">
            <h1>Здравствуйте!</h1>
            <h2>Проекты:</h2>
            <p>
            <a href="<c:url value="/people" />">Список людей</a> с возможностью удаления,
            добавления и изменения записей Spring MVC
            </p>
        </div>
    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
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
                <a href="<c:url value="/people" />">Список пользователей (frontend Spring MVC)</a>
                с возможностью удаления, добавления и изменения записей
            </p>
            <p>
                <a href="<c:url value="/peoplevaadin" />">Список пользователей (frontend Vaadin)</a>
                с возможностью удаления, добавления и изменения записей
            </p>
            <h2>Тестирование</h2>
            <p>
                О полноценном тестировании, конечно, говорить не приходится. Но для HomeController,
                PersonController, а также для backend'a написаны тесты (не идеальные, к сожалению).
                Приложение тестировалось на Windows 10 и Linux Mint 17.3 (Линукс запускал в Oracle
                VirtualBox), Tomcat 8.0.30, MySQL 5.5.47 и 5.7.10.
            </p>
            <h2>Известные проблемы</h2>
        </div>
    </body>
</html>

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
                Приложение тестировалось на jdk 8u-72, Windows 10 и Linux Mint 17.3 (Линукс запускал в Oracle
                VirtualBox), Tomcat 8.0.30, MySQL 5.5.47, 5.6.26 и 5.7.10.
            </p>
            <p>
                На всякий случай: с работой приложения можно ознакомиться
                <a href="http://87415.dyn.ufanet.ru:8080/TestProject">здесь</a>. Но
                это мой домашний компьютер, включен он далеко не всегда, свяжитесь со мной,
                если хотите пройти по этой ссылке.
            </p>
            <h2>Известные проблемы</h2>
            <p>
                Была проблема с Abandoned connection cleanup thread. В логах появлялось следующее сообщение:<br/>
                WARNING [http-apr-8080-exec-12] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads
                The web application [TestProject] appears to have started a thread named [Abandoned connection cleanup thread]
                but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
                java.lang.Object.wait(Native Method)
                java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
                com.mysql.jdbc.AbandonedConnectionCleanupThread.run(AbandonedConnectionCleanupThread.java:43)<br/>
                Класс DestroyerAbandonedConnectionCleanupThread нужен для решения этой проблемы (наверное, не самое
                лучшее решение).
            </p>
            <p>
                На Windows 10 была проблема с андеплоем приложения (как через плагин Мавен, так и через менеджер
                приложений Томката): не удалялись некоторые либы Ваадина. Решается проблема прописыванием
                antiResourceLocking="true" в context.xml'e Томката (&lt;context
                antiResourceLocking="true"/&gt;). Можно просто остановить Томкат и
                удалить либы вручную.
            </p>
        </div>
    </body>
</html>

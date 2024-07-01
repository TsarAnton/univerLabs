<%@page isErrorPage="true"
        language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE>Тест JSP</TITLE>
    </HEAD>
    <BODY>
        <c:choose>
            <c:when test="${not empty pageContext.exception}">
                <H2>Ошибка работы с базой данных</H2>
                <P>Обратитесь к системному администратору</P>
            </c:when>
            <c:otherwise>
                <H2>Непредвиденная ошибка приложения</H2>
                <P>Обратитесь к системному администратору</P>
            </c:otherwise>
        </c:choose>
    </BODY>
</HTML>
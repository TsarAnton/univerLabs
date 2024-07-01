<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
    <c:when test="${not empty enrollee}">
        <c:set var="name" value="${enrollee.name}"/>
        <c:set var="surname" value="${enrollee.surname}"/>
        <c:set var="patronymic" value="${enrollee.patronymic}"/>
        <c:set var="certificateScore" value="${enrollee.certificateScore}"/>
        <c:set var="firstExamScore" value="${enrollee.firstExamScore}"/>
        <c:set var="secondExamScore" value="${enrollee.secondExamScore}"/>
        <c:set var="thirdExamScore" value="${enrollee.thirdExamScore}"/>
    </c:when>
    <c:otherwise>
        <c:set var="name" value=""/>
        <c:set var="surname" value=""/>
        <c:set var="patronymic" value=""/>
        <c:set var="certificateScore" value=""/>
        <c:set var="firstExamScore" value=""/>
        <c:set var="secondExamScore" value=""/>
        <c:set var="thirdExamScore" value=""/>
    </c:otherwise>
</c:choose>

<HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE>Тест JSP</TITLE>
    </HEAD>
    <BODY>
        <FORM action="save.html" method="post">
            
            <c:if test="${not empty enrollee}">
                <INPUT type="hidden" name="id" value="${enrollee.id}">
            </c:if>

            <P>Имя:</P>
            <INPUT type="text" name="name" value="${name}">
            <P>Фамилия:</P>
            <INPUT type="text" name="surname" value="${surname}">
            <P>Отчество:</P>
            <INPUT type="text" name="patronymic" value="${patronymic}">
            <P>Средний бал аттестата:</P>
            <INPUT type="number" step="0.1" min="0" max="10" name="getCertificateScore" value="certificateScore">
            <P>Оценка по первому экзамену:</P>
            <INPUT type="number" step="1" min="0" max="10" name="firstExamScore" value="firstExamScore">
            <P>Оценка по второму экзамену:</P>
            <INPUT type="number" step="1" min="0" max="10" name="secondExamScore" value="secondExamScore">
            <P>Оценка по третьему экзамену:</P>
            <INPUT type="number" step="1" min="0" max="10" name="thirdExamScore" value="thirdExamScore">
            <BUTTON type="submit">Сохранить</BUTTON>
            <A href="index.html">Назад</A>
        </FORM>
    </BODY>
</HTML>
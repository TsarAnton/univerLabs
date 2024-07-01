<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE>Тест JSP</TITLE>
        <STYLE>
            TABLE {
                border-collapse: collapse;
            }
            TH, TD {
                border: 1px solid black;
                padding: 5px 30px 5px 10px;
            }
        </STYLE>
    </HEAD>
    <BODY>
        
        <c:choose>
            <c:when test="${not empty user}">
                ${user.login}&nbsp;&mdash; <A href="logout.html">выйти</A>
            </c:when>
            <c:otherwise>
                <A href="login-form.jsp">войти</A>
            </c:otherwise>
        </c:choose>

        <P><A href="indexDoctor.html">Доктора</A></P>
        <c:if test="${not empty admin}"></c:if>
        <P><A href="indexUser.html">Пользователи</A></P>
        </c:if>
    </BODY>
</HTML>
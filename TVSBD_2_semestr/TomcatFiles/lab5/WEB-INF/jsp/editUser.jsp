<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@page import="pckg.User"%>

<%
    User user = (User)request.getAttribute("user");
%>

<HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE>Тест JSP</TITLE>
    </HEAD>
    <BODY>
        <c:if test="${not empty param['message']}">
            <P style="color: red;">${param['message']}</P>
        </c:if>
        <FORM action="saveUser.html" method="post">
            <%
                if(user != null) {
            %>
                <INPUT type="hidden" name="id"
                        value="<%= user.getId() %>">
            <%
                }
            %>
            <P>Логин:</P>
            <INPUT type="text" name="login" required="required"
                    value="<%= user != null
                                ? user.getLogin()
                                : new String() %>">
            <P>Пароль:</P>
            <INPUT type="text" name="password" required="required"
                    value="<%= user != null
                                ? user.getPassword()
                                : new String() %>">
            <P><BUTTON type="submit">Сохранить</BUTTON></P>
        </FORM>
        <P></P><A href="indexUser.html">Назад</A></P>
    </BODY>
</HTML>
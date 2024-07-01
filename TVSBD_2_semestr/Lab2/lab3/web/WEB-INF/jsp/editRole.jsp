<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@page import="pckg.Role"%>

<%
    Role role = (Role)request.getAttribute("role");
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
        <FORM action="saveRole.html" method="post">
            <%
                if(role != null) {
            %>
                <INPUT type="hidden" name="id"
                        value="<%= role.getId() %>">
            <%
                }
            %>
            <P>Название:</P>
            <INPUT type="text" name="name" required="required"
                    value="<%= role != null
                                ? role.getName()
                                : new String() %>">
            <P><BUTTON type="submit">Сохранить</BUTTON></P>
        </FORM>
        <P></P><A href="indexRole.html">Назад</A></P>
    </BODY>
</HTML>
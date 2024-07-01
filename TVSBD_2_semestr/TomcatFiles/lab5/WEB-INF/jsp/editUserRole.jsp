<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@page import="pckg.User"%>
<%@page import="pckg.Role"%>
<%@page import="java.util.ArrayList"%>
<%
    @SuppressWarnings("unchecked")
    ArrayList<Role> roles = (ArrayList<Role>)request.getAttribute("roles");
    User user1 = (User)request.getAttribute("user1");
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
        <FORM action="saveUserRole.html?id=<%= user1.getId() %>" method="post">
            <P>Название:</P>
            <div>
                <select id="roleId" name="roleId">
                <% for(Role role: roles) { %>
                    <option value="<%= role.getId() %>">
                        <div><%= role.getName() %><</div>
                    </option>
                <% } %>
                </select>
            </div>
            <P><BUTTON type="submit">Сохранить</BUTTON></P>
        </FORM>
        <P></P><A href="indexUserRole.html?id=<%= user1.getId() %>">Назад</A></P>
    </BODY>
</HTML>
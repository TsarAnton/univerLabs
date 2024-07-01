<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@page import="pckg.Role"%>
<%@page import="pckg.User"%>
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

        <p>Роли пользователя <%= user1.getLogin() %></p>

        <FORM action="deleteUserRole.html?userId=<%= user1.getId() %>" method="post">
            <TABLE>
                <TR>
                    <TH>&nbsp;</TH>
                    <TH>Название</TH>
                </TR>
                <%
                    for(Role role: roles) {
                %>
                    <TR>
                        <TD>
                            <INPUT type="checkbox" name="id"
                                    value="<%= role.getId() %>">
                        </TD>
                        <TD><%= role.getName() %></TD>
                    </TR>
                <%
                    }
                %>
            </TABLE>
            <P>
                <A href="editUserRole.html?id=<%= user1.getId() %>">Добавить</A>
                <BUTTON type="submit">Удалить</BUTTON>
            </P>
        </FORM>
        <P><A href="indexUser.html">Назад</A></P>
    </BODY>
</HTML>
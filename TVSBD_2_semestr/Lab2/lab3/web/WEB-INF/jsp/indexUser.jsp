<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@page import="pckg.User"%>
<%@page import="java.util.ArrayList"%>

<%
    @SuppressWarnings("unchecked")
    ArrayList<User> users = (ArrayList<User>)request.getAttribute("users");
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

        <FORM action="deleteUser.html" method="post">
            <TABLE>
                <TR>
                    <TH>&nbsp;</TH>
                    <TH>Логин</TH>
                    <TH>Пароль</TH>
                    <TH></TH>
                    <TH></TH>
                </TR>
                <%
                    for(User user: users) {
                %>
                    <TR>
                        <TD>
                            <INPUT type="checkbox" name="id"
                                    value="<%= user.getId() %>">
                        </TD>
                        <TD><%= user.getLogin() %></TD>
                        <TD><%= user.getPassword() %></TD>
                        <TD>
                            <A href="editUser.html?id=<%= user.getId() %>">Редактировать</A>
                        </TD>
                        <TD>
                            <A href="indexUserRole.html?id=<%= user.getId() %>">Роли</A>
                        </TD>
                    </TR>
                <%
                    }
                %>
            </TABLE>
            <P>
                <A href="editUser.html">Добавить</A>
                <BUTTON type="submit">Удалить</BUTTON>
            </P>
        </FORM>
        <P><A href="index.html">Назад</A></P>
    </BODY>
</HTML>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@page import="pckg.Role"%>
<%@page import="java.util.ArrayList"%>

<%
    @SuppressWarnings("unchecked")
    ArrayList<Role> roles = (ArrayList<Role>)request.getAttribute("roles");
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

        <FORM action="deleteRole.html" method="post">
            <TABLE>
                <TR>
                    <TH>&nbsp;</TH>
                    <TH>Название</TH>
                    <TH></TH>
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
                        <TD>
                            <A href="editRole.html?id=<%= role.getId() %>">Редактировать</A>
                        </TD>
                    </TR>
                <%
                    }
                %>
            </TABLE>
            <P>
                <A href="editRole.html">Добавить</A>
                <BUTTON type="submit">Удалить</BUTTON>
            </P>
        </FORM>
        <P><A href="indexUser.html">Назад</A></P>
    </BODY>
</HTML>
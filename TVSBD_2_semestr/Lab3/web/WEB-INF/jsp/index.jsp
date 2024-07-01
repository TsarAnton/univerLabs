<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@page import="pckg.Enrollee"%>
<%@page import="java.util.Collection"%>
<%@page import="pckg.Task"%>

<%
    @SuppressWarnings("unchecked")
    Collection<Enrollee> objects = (Collection<Enrollee>)request.getAttribute("objects");
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

        <FORM action="delete.html" method="post">
            <TABLE>
                <TR>
                    <TH>&nbsp;</TH>
                    <TH>Шифр</TH>
                    <TH>Имя</TH>
                    <TH>Фамилия</TH>
                    <TH>Отчество</TH>
                    <TH>Средний бал аттестата</TH>
                    <TH>Первый экзамен</TH>
                    <TH>Второй экзамен</TH>
                    <TH>Третий экзамен</TH>
                    <TH>Средний бал</TH>
                    <TH></TH>
                </TR>
                <%
                    for(Enrollee enrollee: objects) {
                %>
                    <TR>
                        <TD>
                            <INPUT type="checkbox" name="id"
                                    value="<%= enrollee.getId() %>">
                        </TD>
                        <TD><%= enrollee.getId() %></TD>
                        <TD><%= enrollee.getName() %></TD>
                        <TD><%= enrollee.getSurname() %></TD>
                        <TD><%= enrollee.getPatronymic() %></TD>
                        <TD><%= enrollee.getCertificateScore() %></TD>
                        <TD><%= enrollee.getFirstExamScore() %></TD>
                        <TD><%= enrollee.getSecondExamScore() %></TD>
                        <TD><%= enrollee.getThirdExamScore() %></TD>
                        <TD><%= enrollee.getAverageScore() %></TD>
                        <TD>
                            <A href="edit.html?id=<%= enrollee.getId() %>">Редактировать</A>
                        </TD>
                    </TR>
                <%
                    }
                %>
            </TABLE>
            <P>
                <A href="edit.html">Добавить</A>
                <BUTTON type="submit">Удалить</BUTTON>
            </P>
        </FORM>
        <FORM action="task.html" method="post">
            <P>Количество мест на специальность</P>
            <P><INPUT type="number" step="1" min="0" name="placeCount" required="required"></P>
            <P><BUTTON type="submit">Провести конкурс</BUTTON></P>
        </FORM>
        <%
            if(Task.result != null && !Task.result.isEmpty()) {
        %>
            <P>Шифры студентов:</P>
            <P><%= Task.result %></P>
        <%
            }
        %>
    </BODY>
</HTML>
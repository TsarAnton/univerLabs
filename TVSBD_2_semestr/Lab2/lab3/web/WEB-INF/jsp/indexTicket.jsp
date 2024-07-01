<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@page import="pckg.Doctor"%>
<%@page import="pckg.Ticket"%>
<%@page import="java.util.ArrayList"%>

<%
    @SuppressWarnings("unchecked")
    ArrayList<Ticket> tickets = (ArrayList<Ticket>)request.getAttribute("tickets");
    Doctor doctor = (Doctor)request.getAttribute("doctor");
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

        <c:if test="${not empty param['message']}">
            <P style="color: red;">${param['message']}</P>
        </c:if>

        <p>Талоны доктора <%= doctor.getSurname() %> <%= doctor.getName() %> <%= doctor.getPatronymic() %></p>

        <c:if test="${not empty nurse}">
        <FORM action="deleteTicket.html?doctorId=<%= doctor.getId() %>" method="post">
        </c:if>
            <TABLE>
                <TR>
                    <c:if test="${not empty nurse}"><TH>&nbsp;</TH></c:if>
                    <TH>Время начала</TH>
                    <TH>Длительность приема</TH>
                    <TH>Свободен</TH>
                    <TH>Имя пациента</TH>
                    <TH>Фамилия пациента</TH>
                    <TH>Отчество пациента</TH>
                    <c:if test="${not empty nurse}"><TH></TH></c:if>
                </TR>
                <%
                    for(Ticket ticket: tickets) {
                %>
                    <TR>
                        <c:if test="${not empty nurse}">
                        <TD>
                            <INPUT type="checkbox" name="id"
                                    value="<%= ticket.getId() %>">
                        </TD>
                        </c:if>
                        <TD><%= ticket.getBegTime() %></TD>
                        <TD><%= ticket.getDuration() %></TD>
                        <TD><%= ticket.getIsFree() ? "Да" : "Нет" %></TD>
                        <TD><%= ticket.getPatientName().isEmpty() ? "Пусто" : ticket.getPatientName() %></TD>
                        <TD><%= ticket.getPatientSurname().isEmpty() ? "Пусто" : ticket.getPatientSurname() %></TD>
                        <TD><%= ticket.getPatientPatronymic().isEmpty() ? "Пусто" : ticket.getPatientPatronymic() %></TD>
                        <c:if test="${not empty nurse}">
                        <TD>
                            <A href="editTicket.html?id=<%= ticket.getId() %>&&doctorId=<%= doctor.getId() %>">Редактировать</A>
                        </TD>
                        </c:if>
                    </TR>
                <%
                    }
                %>
            </TABLE>
        <c:if test="${not empty nurse}">
            <P>
                <A href="editTicket.html?doctorId=<%= doctor.getId() %>&&id=-1">Добавить</A>
                <BUTTON type="submit">Удалить</BUTTON>
            </P>
        </FORM>
        </c:if>
        <A href="indexDoctor.html">Назад</A>
    </BODY>
</HTML>
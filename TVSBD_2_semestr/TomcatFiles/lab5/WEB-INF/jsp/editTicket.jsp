<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@page import="pckg.Ticket"%>
<%@page import="pckg.Doctor"%>

<%
    Ticket ticket = (Ticket)request.getAttribute("ticket");
    Doctor doctor = (Doctor)request.getAttribute("doctor");
%>

<HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE>Тест JSP</TITLE>
    </HEAD>
    <BODY>
        <P>Время работы врача: <%= doctor.getBegTime() %> - <%= doctor.getEndTime() %></P>
        <c:if test="${not empty param['message']}">
            <P style="color: red;">${param['message']}</P>
        </c:if>
        <FORM action="saveTicket.html?doctorId=<%= doctor.getId() %>" method="post">
            <%
                if(ticket != null) {
            %>
                <INPUT type="hidden" name="id"
                        value="<%= ticket.getId() %>">
            <%
                }
            %>
            <P>Время начала:</P>
            <INPUT type="time" name="begTime" required="required"
                    value="<%= ticket != null
                                ? ticket.getBegTime()
                                : new String() %>">
            <P>Длительность:</P>
            <INPUT type="time" name="duration" required="required"
                    value="<%= ticket != null
                                ? ticket.getDuration()
                                : new String() %>">
            <P><INPUT type="checkbox" name="isFree" value="value"
                      <%
                          if(ticket != null && ticket.getIsFree()) {
                      %>
                          checked
                      <%
                          }
                      %>>Свободен?</P>
            <P>Имя пациента:</P>
            <INPUT type="text" name="patientName" pattern="^[A-Za-zА-Яа-яЁё]+$"
                    value="<%= ticket != null
                                ? ticket.getPatientName()
                                : new String() %>">
            <P>Фамилия пациента:</P>
            <INPUT type="text" name="patientSurname" pattern="^[A-Za-zА-Яа-яЁё]+$"
                    value="<%= ticket != null
                                ? ticket.getPatientSurname()
                                : new String() %>">
            <P>Отчество пациента:</P>
            <INPUT type="text" name="patientPatronymic" pattern="^[A-Za-zА-Яа-яЁё]+$"
                    value="<%= ticket != null
                                ? ticket.getPatientPatronymic()
                                : new String() %>">
            <P><BUTTON type="submit">Сохранить</BUTTON></P>
        </FORM>
        <P></P><A href="indexTicket.html?id=<%= doctor.getId() %>">Назад</A></P>
    </BODY>
</HTML>
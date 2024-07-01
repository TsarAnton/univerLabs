<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@page import="pckg.Doctor"%>
<%@page import="java.util.ArrayList"%>

<%
    @SuppressWarnings("unchecked")
    ArrayList<Doctor> doctors = (ArrayList<Doctor>)request.getAttribute("doctors");
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
        <c:if test="${not empty nurse}">
        <FORM action="deleteDoctor.html" method="post">
        </c:if>
            <TABLE>
                <TR>
                    <c:if test="${not empty nurse}"><TH>&nbsp;</TH></c:if>
                    <TH>Специальность</TH>
                    <TH>Имя</TH>
                    <TH>Фамилия</TH>
                    <TH>Отчество</TH>
                    <TH>Узкого профиля</TH>
                    <TH>Номер участка</TH>
                    <TH>Время начала приема</TH>
                    <TH>Время окончания приема</TH>
                    <TH>Количество талонов</TH>
                    <TH>Общее время приема</TH>
                    <c:if test="${not empty nurse}"><TH></TH></c:if>
                    <TH></TH>
                </TR>
                <%
                    for(Doctor doctor: doctors) {
                %>
                    <TR>
                        <c:if test="${not empty nurse}">
                        <TD>
                            <INPUT type="checkbox" name="id"
                                    value="<%= doctor.getId() %>">
                        </TD>
                        </c:if>
                        <TD><%= doctor.getSpeciality() %></TD>
                        <TD><%= doctor.getName() %></TD>
                        <TD><%= doctor.getSurname() %></TD>
                        <TD><%= doctor.getPatronymic() %></TD>
                        <TD><%= doctor.getIsNarrow() ? "Да" : "Нет" %></TD>
                        <TD><%= doctor.getLotNumber() == -1 ? "Пусто" : doctor.getLotNumber() %></TD>
                        <TD><%= doctor.getBegTime() %></TD>
                        <TD><%= doctor.getEndTime() %></TD>
                        <TD><%= doctor.getTicketNumber() %></TD>
                        <TD><%= doctor.getAllDuration() %></TD>
                        <TD><A href = "indexTicket.html?id=<%= doctor.getId() %>">Талоны</A></TD>
                        <c:if test="${not empty nurse}">
                        <TD>
                            <A href="editDoctor.html?id=<%= doctor.getId() %>">Редактировать</A>
                        </TD>
                        </c:if>
                    </TR>
                <%
                    }
                %>
            </TABLE>
        <c:if test="${not empty nurse}">
            <P>
                <A href="editDoctor.html">Добавить</A>
                <BUTTON type="submit">Удалить</BUTTON>
            </P>
        </FORM>
        </c:if>
        <P><A href="index.html">Назад</A></P>
    </BODY>
</HTML>
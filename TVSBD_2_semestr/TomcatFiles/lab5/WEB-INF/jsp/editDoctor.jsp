<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@page import="pckg.Doctor"%>
<%@page import="pckg.User"%>
<%@page import="java.util.ArrayList"%>

<%
    @SuppressWarnings("unchecked")
    ArrayList<User> users = (ArrayList<User>)request.getAttribute("users");
    Doctor doctor = (Doctor)request.getAttribute("doctor");
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
        <FORM action="saveDoctor.html" method="post">
            <%
                if(doctor != null) {
            %>
                <INPUT type="hidden" name="id"
                        value="<%= doctor.getId() %>">
            <%
                }
            %>
            <P>Пользователь:</P>
            <div>
                <select id="userId" name="userId">
                <% for(User user: users) { %>
                    <option value="<%= user.getId() %>">
                        <div><%= user.getLogin() %><</div>
                    </option>
                <% } %>
                </select>
            </div>
            <P>Специальность:</P>
            <INPUT type="text" name="speciality" required="required"
                    value="<%= doctor != null
                                ? doctor.getSpeciality()
                                : new String() %>">
            <P>Имя:</P>
            <INPUT type="text" name="name" required="required" pattern="^[A-Za-zА-Яа-яЁё]+$"
                    value="<%= doctor != null
                                ? doctor.getName()
                                : new String() %>">
            <P>Фамилия:</P>
            <INPUT type="text" name="surname" required="required" pattern="^[A-Za-zА-Яа-яЁё]+$"
                    value="<%= doctor != null
                                ? doctor.getSurname()
                                : new String() %>">
            <P>Отчество:</P>
            <INPUT type="text" name="patronymic" required="required" pattern="^[A-Za-zА-Яа-яЁё]+$"
                    value="<%= doctor != null
                                ? doctor.getPatronymic()
                                : new String() %>">
            <P><INPUT type="checkbox" name="isNarrow" value="value"
                      <%
                          if(doctor != null && doctor.getIsNarrow()) {
                      %>
                          checked
                      <%
                          }
                      %>>Узкого профиля?</P>
            <P>Номер участка:</P>
            <INPUT type="number" min="0" step="1" name="lotNumber"
                    value="<%= doctor != null
                                ? (doctor.getLotNumber() == -1
                                ? new String()
                                : doctor.getLotNumber())
                                : new String() %>">
            <P>Время начала приема:</P>
            <INPUT type="time" name="begTime" required="required"
                    value="<%= doctor != null
                                ? doctor.getBegTime()
                                : new String() %>">
            <P>Время окончания приема:</P>
            <INPUT type="time" name="endTime" required="required"
                    value="<%= doctor != null
                                ? doctor.getEndTime()
                                : new String() %>">
            <P>Количество талонов:</P>
            <INPUT type="number" min="0" step="1" name="ticketNumber" required="required"
                    value="<%= doctor != null
                                ? doctor.getTicketNumber()
                                : new String() %>">
            <P><BUTTON type="submit">Сохранить</BUTTON></P>
        </FORM>
        <P></P><A href="indexDoctor.html">Назад</A></P>
    </BODY>
</HTML>
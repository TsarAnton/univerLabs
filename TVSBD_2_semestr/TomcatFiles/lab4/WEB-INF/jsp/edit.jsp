<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@page import="pckg.Enrollee"%>

<%
    Enrollee enrollee = (Enrollee)request.getAttribute("enrollee");
%>

<HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE>Тест JSP</TITLE>
    </HEAD>
    <BODY>
        <FORM action="save.html" method="post">
            <%
                if(enrollee != null) {
            %>
                <INPUT type="hidden" name="id"
                        value="<%= enrollee.getId() %>">
            <%
                }
            %>
            <P>Имя:</P>
            <INPUT type="text" name="name" required="required" pattern="^[A-Za-zА-Яа-яЁё]+$"
                    value="<%= enrollee != null
                                ? enrollee.getName()
                                : new String() %>">
            <P>Фамилия:</P>
            <INPUT type="text" name="surname" required="required" pattern="^[A-Za-zА-Яа-яЁё]+$"
                    value="<%= enrollee != null
                                ? enrollee.getSurname()
                                : new String() %>">
            <P>Отчество:</P>
            <INPUT type="text" name="patronymic" required="required" pattern="^[A-Za-zА-Яа-яЁё]+$"
                    value="<%= enrollee != null
                                ? enrollee.getPatronymic()
                                : new String() %>">
            <P>Средний бал аттестата:</P>
            <INPUT type="number" step="0.1" min="0" max="10" name="certificateScore" required="required"
                    value="<%= enrollee != null
                                ? enrollee.getCertificateScore()
                                : new String() %>">
            <P>Первый экзамен:</P>
            <INPUT type="number" step="1" min="0" max="10" name="firstExamScore" required="required"
                    value="<%= enrollee != null
                                ? enrollee.getFirstExamScore()
                                : new String() %>">
            <P>Второй экзамен:</P>
            <INPUT type="number" step="1" min="0" max="10" name="secondExamScore" required="required"
                    value="<%= enrollee != null
                                ? enrollee.getSecondExamScore()
                                : new String() %>">
            <P>Третий экзамен:</P>
            <INPUT type="number" step="1" min="0" max="10" name="thirdExamScore" required="required"
                    value="<%= enrollee != null
                                ? enrollee.getThirdExamScore()
                                : new String() %>">
            <P><BUTTON type="submit">Сохранить</BUTTON></P>
        </FORM>
        <P></P><A href="index.html">Назад</A></P>
    </BODY>
</HTML>
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.net.URLEncoder;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.Doctor;
import pckg.DoctorService;
import pckg.RoleService;
import pckg.TicketService;
import pckg.User;
import pckg.UserService;
import pckg.DateFunctions;

public class SaveDoctorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        boolean con = true, isLotNumber = false, isIsNarrow = false;
        Doctor doctor = new Doctor();
        doctor.setSpeciality(req.getParameter("speciality"));
        doctor.setName(req.getParameter("name"));
        doctor.setSurname(req.getParameter("surname"));
        doctor.setPatronymic(req.getParameter("patronymic"));
        if(req.getParameter("isNarrow") == null) {
            doctor.setIsNarrow(false);
        } else {
            doctor.setIsNarrow(true);
            isIsNarrow = true;
        }
        if(!req.getParameter("lotNumber").isEmpty()) {
            doctor.setLotNumber(Integer.parseInt(req.getParameter("lotNumber")));
            isLotNumber = true;
        } else {
            doctor.setLotNumber(-1);
        }
        try {
            doctor.setBegTime(new SimpleDateFormat("HH:mm").format(new SimpleDateFormat("HH:mm").parse(req.getParameter("begTime"))));
            doctor.setEndTime(new SimpleDateFormat("HH:mm").format( new SimpleDateFormat("HH:mm").parse(req.getParameter("endTime"))));
            doctor.setAllDuration("00:00");
        } catch (ParseException e) {}
        doctor.setTicketNumber(Integer.parseInt(req.getParameter("ticketNumber")));
        try {
            doctor.setId(Integer.parseInt(req.getParameter("id")));
        } catch(NumberFormatException e) {}
        try {
            if(RoleService.checkUserRole(RoleService.readByName("doctor").getId(), Integer.parseInt(req.getParameter("userId")))) {
                if((doctor.getId() == null) || (doctor.getId() != null && doctor.getId() == Integer.parseInt(req.getParameter("userId")))) {
                    con = false;
                    String message = "Этот пользователь уже доктор";
                    ArrayList<User> users = UserService.readAll();
                    req.setAttribute("users", users);
                    String url = req.getContextPath()
                                + "/editDoctor.html?message="
                               + URLEncoder.encode(message, "UTF-8");
                    if(doctor.getId() != null) {
                        url += "&&id=" + URLEncoder.encode(Integer.toString(doctor.getId()), "UTF-8");
                    }
                    resp.sendRedirect(url);
                }
            }
            if(DateFunctions.DateCompare(doctor.getBegTime(), doctor.getEndTime()) != -1 && con) {
                con = false;
                String message = "Дата окончания раньше даты начала";
                ArrayList<User> users = UserService.readAll();
                req.setAttribute("users", users);
                String url = req.getContextPath()
                            + "/editDoctor.html?message="
                           + URLEncoder.encode(message, "UTF-8");
                if(doctor.getId() != null) {
                    url += "&&id=" + URLEncoder.encode(Integer.toString(doctor.getId()), "UTF-8");
                }
                resp.sendRedirect(url);
            }
            if(isLotNumber && isIsNarrow && con) {
                con = false;
                String message = "Номер участка и узкий профиль выбраны одновременно";
                ArrayList<User> users = UserService.readAll();
                req.setAttribute("users", users);
                String url = req.getContextPath()
                           + "/editDoctor.html?message="
                           + URLEncoder.encode(message, "UTF-8");
                if(doctor.getId() != null) {
                    url += "&&id=" + URLEncoder.encode(Integer.toString(doctor.getId()), "UTF-8");
                }
                resp.sendRedirect(url);
            }
            if(!isIsNarrow && !isLotNumber && con) {
                con = false;
                String message = "Номер участка и узкий профиль не выбраны";
                ArrayList<User> users = UserService.readAll();
                req.setAttribute("users", users);
                String url = req.getContextPath()
                           + "/editDoctor.html?message="
                           + URLEncoder.encode(message, "UTF-8");
                if(doctor.getId() != null) {
                    url += "&&id=" + URLEncoder.encode(Integer.toString(doctor.getId()), "UTF-8");
                }
                resp.sendRedirect(url);
            }
            if(con) {
                if(doctor.getId() == null) {
                    doctor.setId(Integer.parseInt(req.getParameter("userId")));
                    DoctorService.create(doctor);
                    RoleService.createUserRole(doctor.getId(), RoleService.readByName("doctor"));
                } else {
                    if(doctor.getTicketNumber() < TicketService.readDoctorTicketsNumber(doctor)) {
                        con = false;
                        String message = "У доктора есть талоны, для начала удалите их";
                        ArrayList<User> users = UserService.readAll();
                        req.setAttribute("users", users);
                        String url = req.getContextPath()
                                   + "/editDoctor.html?message="
                                   + URLEncoder.encode(message, "UTF-8");
                        if(doctor.getId() != null) {
                            url += "&&id=" + URLEncoder.encode(Integer.toString(doctor.getId()), "UTF-8");
                        }
                        resp.sendRedirect(url);
                    }
                    
                    if(con) {
                        DoctorService.update(doctor);
                    }
                } 
            } 
        } catch(SQLException e) {
            throw new ServletException(e);
        }
        if(con) {
            resp.sendRedirect(req.getContextPath() + "/indexDoctor.html");
        }
    }
}


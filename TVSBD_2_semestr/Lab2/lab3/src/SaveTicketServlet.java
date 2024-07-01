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
import pckg.Ticket;
import pckg.TicketService;
import pckg.Doctor;
import pckg.DoctorService;
import pckg.DateFunctions;

public class SaveTicketServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Integer doctorId = Integer.parseInt(req.getParameter("doctorId"));
        boolean con = true, err = false;
        Ticket ticket = new Ticket();
        if(req.getParameter("isFree") == null) {
            ticket.setIsFree(false);
        } else {
            ticket.setIsFree(true);
        }
        if(ticket.getIsFree()) {
            ticket.setPatientName("");
            ticket.setPatientPatronymic("");
            ticket.setPatientSurname("");
        } else {
            if(req.getParameter("patientName").isEmpty() || req.getParameter("patientSurname").isEmpty() || req.getParameter("patientPatronymic").isEmpty()) {
                err = true;
            } else {
                ticket.setPatientName(req.getParameter("patientName"));
                ticket.setPatientPatronymic(req.getParameter("patientPatronymic"));
                ticket.setPatientSurname(req.getParameter("patientSurname"));
            }
        }
        try {
            ticket.setBegTime(new SimpleDateFormat("HH:mm").format(new SimpleDateFormat("HH:mm").parse(req.getParameter("begTime"))));
            ticket.setDuration(new SimpleDateFormat("HH:mm").format( new SimpleDateFormat("HH:mm").parse(req.getParameter("duration"))));
        } catch (ParseException e) {}
        try {
            ticket.setId(Integer.parseInt(req.getParameter("id")));
        } catch(NumberFormatException e) {}
        try {
            Doctor doctor = DoctorService.readById(doctorId);
            ticket.setDoctorId(doctorId);
            if(err) {
                con = false;
                String message = "Не указаны данные пациента";
                String url = req.getContextPath()
                            + "/editTicket.html?message="
                           + URLEncoder.encode(message, "UTF-8")
                           + "&&doctorId="
                           + URLEncoder.encode(Integer.toString(doctorId), "UTF-8");
                if(ticket.getId() == null) {
                    url += "&&id=-1";
                } else {
                    url += "&&id=" + URLEncoder.encode(Integer.toString(ticket.getId()), "UTF-8");
                }
                resp.sendRedirect(url);
            }
            if(TicketService.readDoctorTicketsNumber(doctor) == doctor.getTicketNumber() && !ticket.getIsFree() && con) {
                con = false;
                String message = "Доктор имеет максимальное количество талонов";
                String url = req.getContextPath()
                            + "/editTicket.html?message="
                           + URLEncoder.encode(message, "UTF-8")
                           + "&&doctorId="
                           + URLEncoder.encode(Integer.toString(doctorId), "UTF-8");
                if(ticket.getId() == null) {
                    url += "&&id=-1";
                } else {
                    url += "&&id=" + URLEncoder.encode(Integer.toString(ticket.getId()), "UTF-8");
                }
                resp.sendRedirect(url);
            }
            if(DateFunctions.DateCompare(ticket.getBegTime(), doctor.getBegTime()) == -1 || DateFunctions.DateCompare(DateFunctions.addDates(ticket.getBegTime(), ticket.getDuration()), doctor.getEndTime()) == 1 && con) {
                con = false;
                String message = "Время талона не входит во время работы доктора";
                String url = req.getContextPath()
                            + "/editTicket.html?message="
                           + URLEncoder.encode(message, "UTF-8")
                           + "&&doctorId="
                           + URLEncoder.encode(Integer.toString(doctorId), "UTF-8");
                if(ticket.getId() == null) {
                    url += "&&id=-1";
                } else {
                    url += "&&id=" + URLEncoder.encode(Integer.toString(ticket.getId()), "UTF-8");
                }
                resp.sendRedirect(url);
            }
            ArrayList<Ticket> doctorTickets = TicketService.readDoctorTickets(doctor);
            if(con && !ticket.getIsFree()) {
                String errTime1 = new String(), errTime2 = new String();
                for(int i = 0; i < doctorTickets.size(); i++) {
                    if(!doctorTickets.get(i).getIsFree()) {
                        if(DateFunctions.DateCompare(ticket.getBegTime(), doctorTickets.get(i).getBegTime()) >= 0 &&
                            DateFunctions.DateCompare(ticket.getBegTime(), DateFunctions.addDates(doctorTickets.get(i).getBegTime(), doctorTickets.get(i).getDuration())) <= 0) {
                                con = false;
                                errTime1 = doctorTickets.get(i).getBegTime();
                                errTime2 = DateFunctions.addDates(doctorTickets.get(i).getBegTime(), doctorTickets.get(i).getDuration());
                                break;
                        }
                        if(DateFunctions.DateCompare(DateFunctions.addDates(ticket.getBegTime(), ticket.getDuration()), doctorTickets.get(i).getBegTime()) >= 0 &&
                            DateFunctions.DateCompare(DateFunctions.addDates(ticket.getBegTime(), ticket.getDuration()), DateFunctions.addDates(doctorTickets.get(i).getBegTime(), doctorTickets.get(i).getDuration())) <= 0) {
                                errTime1 = doctorTickets.get(i).getBegTime();
                                errTime2 = DateFunctions.addDates(doctorTickets.get(i).getBegTime(), doctorTickets.get(i).getDuration());
                                con = false;
                                break;
                        }
                    }
                }
                if(!con) {
                    String message = "Время талона перекрывает время работы другого талона:" + errTime1 + " - " + errTime2;
                    String url = req.getContextPath()
                                + "/editTicket.html?message="
                               + URLEncoder.encode(message, "UTF-8")
                               + "&&doctorId="
                               + URLEncoder.encode(Integer.toString(doctorId), "UTF-8");
                    if(ticket.getId() == null) {
                        url += "&&id=-1";
                    } else {
                        url += "&&id=" + URLEncoder.encode(Integer.toString(ticket.getId()), "UTF-8");
                    }
                    resp.sendRedirect(url);
                }
            }
            if(con && !ticket.getIsFree()) {
                String maxDuration = DateFunctions.percentDate(DateFunctions.subDates(doctor.getEndTime(), doctor.getBegTime()), 80);
                String nowDuration = "00:00";
                for(int i = 0; i < doctorTickets.size(); i++) {
                    nowDuration = DateFunctions.addDates(nowDuration, doctorTickets.get(i).getDuration());
                }
                nowDuration = DateFunctions.addDates(nowDuration, ticket.getDuration());
                if(DateFunctions.DateCompare(nowDuration, maxDuration) == 1) {
                    con = false;
                    String message = "Нагрузка доктора превышена";
                    String url = req.getContextPath()
                                + "/editTicket.html?message="
                               + URLEncoder.encode(message, "UTF-8")
                               + "&&doctorId="
                               + URLEncoder.encode(Integer.toString(doctorId), "UTF-8");
                    if(ticket.getId() == null) {
                        url += "&&id=-1";
                    } else {
                        url += "&&id=" + URLEncoder.encode(Integer.toString(ticket.getId()), "UTF-8");
                    }
                    resp.sendRedirect(url);
                }
            }
            if(con) {
                if(ticket.getId() == null) {
                    //ticket.setId(null);
                    TicketService.create(ticket);
                    if(!ticket.getIsFree()) {
                        doctor.setAllDuration(DateFunctions.addDates(doctor.getAllDuration(), ticket.getDuration()));
                        DoctorService.update(doctor);
                    }
                } else {
                    Ticket oldTicket = TicketService.readById(ticket.getId());
                    if(DateFunctions.DateCompare(oldTicket.getBegTime(), ticket.getBegTime()) != 0) {
                        doctor.setAllDuration(DateFunctions.subDates(doctor.getAllDuration(), oldTicket.getDuration()));
                        doctor.setAllDuration(DateFunctions.addDates(doctor.getAllDuration(), ticket.getDuration()));
                        DoctorService.update(doctor);
                    }
                    if(!oldTicket.getIsFree() && ticket.getIsFree()) {
                        doctor.setAllDuration(DateFunctions.subDates(doctor.getAllDuration(), oldTicket.getDuration()));
                        DoctorService.update(doctor);
                    }
                    TicketService.update(ticket);
                } 
            } 
        } catch(SQLException e) {
            throw new ServletException(e);
        }
        if(con) {
            resp.sendRedirect(req.getContextPath() + "/indexTicket.html?id=" + URLEncoder.encode(Integer.toString(doctorId), "UTF-8"));
        }
    }
}

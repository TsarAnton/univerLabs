package pckg;

import java.sql.*;
import java.util.ArrayList;

public class TicketService {
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab5", "postgres", "2003");
    }

    public static ArrayList<Ticket> readAll() throws SQLException {
        String sql = "SELECT \"id\", \"doctorId\", \"duration\", \"isFree\", \"begTime\", \"patientName\", \"patientSurname\", \"patientPatronymic\" "
                   + "FROM tickets";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);
            ArrayList<Ticket> tickets = new ArrayList<>();
            while(r.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(r.getInt("id"));
                ticket.setDoctorId(r.getInt("doctorId"));
                ticket.setIsFree(r.getBoolean("isFree"));
                ticket.setDuration(r.getString("duration"));
                ticket.setBegTime(r.getString("begTime"));
                ticket.setPatientName(r.getString("patientName"));
                ticket.setPatientSurname(r.getString("patientSurname"));
                ticket.setPatientPatronymic(r.getString("patientPatronymic"));
                tickets.add(ticket);
            }
            return tickets;
        } finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }

    public static ArrayList<Ticket> readDoctorTickets(Doctor doctor) throws SQLException {
        String sql = "SELECT \"id\", \"doctorId\", \"duration\", \"isFree\", \"begTime\", \"patientName\", \"patientSurname\", \"patientPatronymic\" "
                   + "FROM tickets "
                   + "WHERE \"doctorId\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, doctor.getId());
            r = s.executeQuery();
            ArrayList<Ticket> tickets = new ArrayList<>();
            while(r.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(r.getInt("id"));
                ticket.setDoctorId(r.getInt("doctorId"));
                ticket.setIsFree(r.getBoolean("isFree"));
                ticket.setDuration(r.getString("duration"));
                ticket.setBegTime(r.getString("begTime"));
                ticket.setPatientName(r.getString("patientName"));
                ticket.setPatientSurname(r.getString("patientSurname"));
                ticket.setPatientPatronymic(r.getString("patientPatronymic"));
                tickets.add(ticket);
            }
            return tickets;
        } finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }

    public static Ticket readById(Integer id) throws SQLException {
        String sql = "SELECT \"id\", \"doctorId\", \"duration\", \"isFree\", \"begTime\", \"patientName\", \"patientSurname\", \"patientPatronymic\" "
                   + "FROM tickets "
                   + "WHERE \"id\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            r = s.executeQuery();
            Ticket ticket = null;
            if(r.next()) {
                ticket = new Ticket();
                ticket.setId(id);
                ticket.setDoctorId(r.getInt("doctorId"));
                ticket.setIsFree(r.getBoolean("isFree"));
                ticket.setDuration(r.getString("duration"));
                ticket.setBegTime(r.getString("begTime"));
                ticket.setPatientName(r.getString("patientName"));
                ticket.setPatientSurname(r.getString("patientSurname"));
                ticket.setPatientPatronymic(r.getString("patientPatronymic"));
            }
            return ticket;
        } finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void create(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO tickets "
                   + "(\"doctorId\", \"duration\", \"isFree\", \"begTime\", \"patientName\", \"patientSurname\", \"patientPatronymic\") "
                   + "VALUES "
                   + "(?, ?, ?, ?, ?, ?, ?)";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, ticket.getDoctorId());
            s.setString(2, ticket.getDuration());
            s.setBoolean(3, ticket.getIsFree());
            s.setString(4, ticket.getBegTime());
            s.setString(5, ticket.getPatientName());
            s.setString(6, ticket.getPatientSurname());
            s.setString(7, ticket.getPatientPatronymic());
            s.executeUpdate();
        } finally {
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void update(Ticket ticket) throws SQLException {
        String sql = "UPDATE tickets SET "
                   + "\"doctorId\" = ?, \"duration\" = ?, \"isFree\" = ?, \"begTime\" = ?, \"patientName\" = ?, \"patientSurname\" = ?, \"patientPatronymic\" = ? "
                   + "WHERE \"id\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, ticket.getDoctorId());
            s.setString(2, ticket.getDuration());
            s.setBoolean(3, ticket.getIsFree());
            s.setString(4, ticket.getBegTime());
            s.setString(5, ticket.getPatientName());
            s.setString(6, ticket.getPatientSurname());
            s.setString(7, ticket.getPatientPatronymic());
            s.setInt(8, ticket.getId());
            s.executeUpdate();
        } finally {
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM \"tickets\" "
                   + "WHERE \"id\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            s.executeUpdate();
        } finally {
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }

    public static Integer readDoctorTicketsNumber(Doctor doctor) throws SQLException {
        String sql = "SELECT COUNT(*) AS \"amount\" "
                   + "FROM \"tickets\" "
                   + "WHERE \"doctorId\" = ? AND \"isFree\" = FALSE";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, doctor.getId());
            r = s.executeQuery();
            if(r.next()) {
                return r.getInt("amount");
            }
            return 0;
        } finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
}

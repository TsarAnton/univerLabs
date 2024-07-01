package pckg;

import java.sql.*;
import java.util.ArrayList;

public class DoctorService {
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab5", "postgres", "2003");
    }

    public static ArrayList<Doctor> readAll() throws SQLException {
        String sql = "SELECT \"id\", \"speciality\", \"name\", \"surname\", \"patronymic\", \"isNarrow\", \"lotNumber\", \"begTime\", \"endTime\", \"ticketNumber\", \"allDuration\" "
                   + "FROM doctors";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);
            ArrayList<Doctor> doctors = new ArrayList<>();
            while(r.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(r.getInt("id"));
                doctor.setSpeciality(r.getString("speciality"));
                doctor.setName(r.getString("name"));
                doctor.setSurname(r.getString("surname"));
                doctor.setPatronymic(r.getString("patronymic"));
                doctor.setIsNarrow(r.getBoolean("isNarrow"));
                doctor.setLotNumber(r.getInt("lotNumber"));
                doctor.setBegTime(r.getString("begTime"));
                doctor.setEndTime(r.getString("endTime"));
                doctor.setAllDuration(r.getString("allDuration"));
                doctor.setTicketNumber(r.getInt("ticketNumber"));
                doctors.add(doctor);
            }
            return doctors;
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

    public static Doctor readById(Integer id) throws SQLException {
        String sql = "SELECT \"id\", \"speciality\", \"name\", \"surname\", \"patronymic\", \"isNarrow\", \"lotNumber\", \"begTime\", \"endTime\", \"ticketNumber\", \"allDuration\" "
                   + "FROM doctors "
                   + "WHERE \"id\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            r = s.executeQuery();
            Doctor doctor = null;
            if(r.next()) {
                doctor = new Doctor();
                doctor.setId(id);
                doctor.setSpeciality(r.getString("speciality"));
                doctor.setName(r.getString("name"));
                doctor.setSurname(r.getString("surname"));
                doctor.setPatronymic(r.getString("patronymic"));
                doctor.setIsNarrow(r.getBoolean("isNarrow"));
                doctor.setLotNumber(r.getInt("lotNumber"));
                doctor.setBegTime(r.getString("begTime"));
                doctor.setEndTime(r.getString("endTime"));
                doctor.setAllDuration(r.getString("allDuration"));
                doctor.setTicketNumber(r.getInt("ticketNumber"));        
            }
            return doctor;
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

    public static void create(Doctor doctor) throws SQLException {
        String sql = "INSERT INTO doctors "
                   + "(\"id\", \"speciality\", \"name\", \"surname\", \"patronymic\", \"isNarrow\", \"lotNumber\", \"begTime\", \"endTime\", \"ticketNumber\", \"allDuration\") "
                   + "VALUES "
                   + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, doctor.getId());
            s.setString(2, doctor.getSpeciality());
            s.setString(3, doctor.getName());
            s.setString(4, doctor.getSurname());
            s.setString(5, doctor.getPatronymic());
            s.setBoolean(6, doctor.getIsNarrow());
            s.setInt(7, doctor.getLotNumber());
            s.setString(8, doctor.getBegTime());
            s.setString(9, doctor.getEndTime());
            s.setInt(10, doctor.getTicketNumber());
            s.setString(11, doctor.getAllDuration());
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

    public static void update(Doctor doctor) throws SQLException {
        String sql = "UPDATE doctors SET "
                   + "\"speciality\" = ?, \"name\" = ?, \"surname\" = ?, \"patronymic\" = ?, \"isNarrow\" = ?, \"lotNumber\" = ?, \"begTime\" = ?, \"endTime\" = ?, \"ticketNumber\" = ?, \"allDuration\" = ? "
                   + "WHERE \"id\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, doctor.getSpeciality());
            s.setString(2, doctor.getName());
            s.setString(3, doctor.getSurname());
            s.setString(4, doctor.getPatronymic());
            s.setBoolean(5, doctor.getIsNarrow());
            s.setInt(6, doctor.getLotNumber());
            s.setString(7, doctor.getBegTime());
            s.setString(8, doctor.getEndTime());
            s.setInt(9, doctor.getTicketNumber());
            s.setString(10, doctor.getAllDuration());
            s.setInt(11, doctor.getId());
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
        String sql = "DELETE FROM \"doctors\" "
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
}

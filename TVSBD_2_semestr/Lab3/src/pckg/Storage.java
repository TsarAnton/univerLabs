package pckg;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Storage {
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab4", "postgres", "2003");
    }

    public static Collection<Enrollee> readAll() throws SQLException {
        String sql = "SELECT \"id\", \"name\", \"surname\", \"patronymic\", \"certificateScore\", \"firstExamScore\", \"secondExamScore\", \"thirdExamScore\" "
                   + "FROM enrollee";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);
            Collection<Enrollee> enrollees = new ArrayList<>();
            while(r.next()) {
                Enrollee enrollee = new Enrollee();
                enrollee.setId(r.getInt("id"));
                enrollee.setName(r.getString("name"));
                enrollee.setSurname(r.getString("surname"));
                enrollee.setPatronymic(r.getString("patronymic"));
                enrollee.setCertificateScore(r.getDouble("certificateScore"));
                enrollee.setFirstExamScore(r.getInt("firstExamScore"));
                enrollee.setSecondExamScore(r.getInt("secondExamScore"));
                enrollee.setThirdExamScore(r.getInt("thirdExamScore"));
                enrollees.add(enrollee);
            }
            return enrollees;
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

    public static Enrollee readById(Integer id) throws SQLException {
        String sql = "SELECT \"id\", \"name\", \"surname\", \"patronymic\", \"certificateScore\", \"firstExamScore\", \"secondExamScore\", \"thirdExamScore\" "
                   + "FROM enrollee "
                   + "WHERE \"id\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            r = s.executeQuery();
            Enrollee enrollee = null;
            if(r.next()) {
                enrollee = new Enrollee();
                enrollee.setId(id);
                enrollee.setName(r.getString("name"));
                enrollee.setSurname(r.getString("surname"));
                enrollee.setPatronymic(r.getString("patronymic"));
                enrollee.setCertificateScore(r.getDouble("certificateScore"));
                enrollee.setFirstExamScore(r.getInt("firstExamScore"));
                enrollee.setSecondExamScore(r.getInt("secondExamScore"));
                enrollee.setThirdExamScore(r.getInt("thirdExamScore"));
            }
            return enrollee;
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

    public static void create(Enrollee enrollee) throws SQLException {
        String sql = "INSERT INTO enrollee "
                   + "(\"name\", \"surname\", \"patronymic\", \"certificateScore\", \"firstExamScore\", \"secondExamScore\", \"thirdExamScore\") "
                   + "VALUES "
                   + "(?, ?, ?, ?, ?, ?, ?)";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, enrollee.getName());
            s.setString(2, enrollee.getSurname());
            s.setString(3, enrollee.getPatronymic());
            s.setDouble(4, enrollee.getCertificateScore());
            s.setInt(5, enrollee.getFirstExamScore());
            s.setInt(6, enrollee.getSecondExamScore());
            s.setInt(7, enrollee.getThirdExamScore());
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

    public static void update(Enrollee enrollee) throws SQLException {
        String sql = "UPDATE enrollee SET "
                   + "\"name\" = ?, \"surname\" = ?, \"patronymic\" = ?, \"certificateScore\" = ?, \"firstExamScore\" = ?, \"secondExamScore\" = ?, \"thirdExamScore\" = ? "
                   + "WHERE \"id\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, enrollee.getName());
            s.setString(2, enrollee.getSurname());
            s.setString(3, enrollee.getPatronymic());
            s.setDouble(4, enrollee.getCertificateScore());
            s.setInt(5, enrollee.getFirstExamScore());
            s.setInt(6, enrollee.getSecondExamScore());
            s.setInt(7, enrollee.getThirdExamScore());
            s.setInt(8, enrollee.getId());
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
        String sql = "DELETE FROM \"enrollee\" "
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

    public static boolean checkUser(User user) throws SQLException {
        String sql = "SELECT COUNT(*) AS \"amount\" "
                    + "FROM \"users\" "
                    + "WHERE \"login\" = ? AND \"password\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, user.getLogin());
            s.setString(2, user.getPassword());
            r = s.executeQuery();
            if(r.next()) {
                return r.getInt("amount") == 1;
            }
            return false;
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
package pckg;

import java.sql.*;
import java.util.ArrayList;

public class UserService {
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab5", "postgres", "2003");
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

    public static boolean checkUserLogin(User user) throws SQLException {
        String sql = "SELECT COUNT(*) AS \"amount\" "
                   + "FROM \"users\" "
                   + "WHERE \"login\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, user.getLogin());
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

    public static ArrayList<User> readAll() throws SQLException {
        String sql = "SELECT \"id\", \"login\", \"password\" "
                   + "FROM users";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);
            ArrayList<User> users = new ArrayList<>();
            while(r.next()) {
                User user = new User();
                user.setId(r.getInt("id"));
                user.setLogin(r.getString("login"));
                user.setPassword(r.getString("password"));
                users.add(user);
            }
            return users;
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

    public static User readById(Integer id) throws SQLException {
        String sql = "SELECT \"id\", \"login\", \"password\" "
                   + "FROM users "
                   + "WHERE \"id\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            r = s.executeQuery();
            User user = null;
            if(r.next()) {
                user = new User();
                user.setId(id);
                user.setLogin(r.getString("login"));
                user.setPassword(r.getString("password"));
            }
            return user;
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

    public static User readByLogin(String login) throws SQLException {
        String sql = "SELECT \"id\", \"login\", \"password\" "
                   + "FROM users "
                   + "WHERE \"login\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, login);
            r = s.executeQuery();
            User user = null;
            if(r.next()) {
                user = new User();
                user.setId(r.getInt("id"));
                user.setLogin(r.getString("login"));
                user.setPassword(r.getString("password"));
            }
            return user;
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

    public static void create(User user) throws SQLException {
        String sql = "INSERT INTO users "
                   + "(\"login\", \"password\") "
                   + "VALUES "
                   + "(?, ?)";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, user.getLogin());
            s.setString(2, user.getPassword());
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

    public static void update(User user) throws SQLException {
        String sql = "UPDATE users SET "
                   + "\"login\" = ?, \"password\" = ? "
                   + "WHERE \"id\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, user.getLogin());
            s.setString(2, user.getPassword());
            s.setInt(3, user.getId());
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
        String sql = "DELETE FROM \"users\" "
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

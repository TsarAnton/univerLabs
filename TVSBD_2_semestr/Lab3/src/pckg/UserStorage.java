package pckg;

import java.sql.*;

public class UserStorage {
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab4", "postgres", "2003");
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

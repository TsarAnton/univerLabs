package pckg;

import java.sql.*;
import java.util.ArrayList;

public class RoleService {
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab5", "postgres", "2003");
    }

    public static ArrayList<Role> readAll() throws SQLException {
        String sql = "SELECT \"id\", \"name\" "
                   + "FROM roles";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);
            ArrayList<Role> roles = new ArrayList<>();
            while(r.next()) {
                Role role = new Role();
                role.setId(r.getInt("id"));
                role.setName(r.getString("name"));
                roles.add(role);
            }
            return roles;
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

    public static Role readById(Integer id) throws SQLException {
        String sql = "SELECT \"id\", \"name\" "
                   + "FROM roles "
                   + "WHERE \"id\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            r = s.executeQuery();
            Role role = null;
            if(r.next()) {
                role = new Role();
                role.setId(id);
                role.setName(r.getString("name"));
            }
            return role;
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
    
    public static Role readByName(String name) throws SQLException {
        String sql = "SELECT \"id\", \"name\" "
                   + "FROM roles "
                   + "WHERE \"name\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, name);
            r = s.executeQuery();
            Role role = null;
            if(r.next()) {
                role = new Role();
                role.setId(r.getInt("id"));
                role.setName(r.getString("name"));
            }
            return role;
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

    public static void create(Role role) throws SQLException {
        String sql = "INSERT INTO roles "
                   + "(\"name\") "
                   + "VALUES "
                   + "(?)";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, role.getName());
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

    public static void update(Role role) throws SQLException {
        String sql = "UPDATE roles SET "
                   + "\"name\" = ? "
                   + "WHERE \"id\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, role.getName());
            s.setInt(2, role.getId());
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
        String sql = "DELETE FROM \"roles\" "
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

    public static void deleteUserRole(Integer userId, Integer roleId) throws SQLException {
        String sql = "DELETE FROM \"users_roles\" "
                   + "WHERE \"user_id\" = ? AND \"role_id\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, userId);
            s.setInt(2, roleId);
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

    public static void createUserRole(Integer userId, Role role) throws SQLException {
        String sql = "INSERT INTO users_roles "
                   + "(\"user_id\", \"role_id\") "
                   + "VALUES "
                   + "(?, ?)";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, userId);
            s.setInt(2, role.getId());
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

    public static ArrayList<Role> readUserRoles(User user) throws SQLException {
        String sql = "SELECT \"roles\".\"id\", \"name\" FROM (\"roles\" INNER JOIN \"users_roles\" ON \"roles\".\"id\" = \"users_roles\".\"role_id\") WHERE \"user_id\" = ? ";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, user.getId());
            r = s.executeQuery();
            ArrayList<Role> roles = new ArrayList<>();
            while(r.next()) {
                Role role = new Role();
                role.setId(r.getInt("id"));
                role.setName(r.getString("name"));
                roles.add(role);
            }
            return roles;
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

    public static boolean checkRoleName(Role role) throws SQLException {
        String sql = "SELECT COUNT(*) AS \"amount\" "
                   + "FROM \"roles\" "
                   + "WHERE \"name\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, role.getName());
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

    public static boolean checkUserRole(Integer roleId, Integer userId) throws SQLException {
        String sql = "SELECT COUNT(*) AS \"amount\" "
                   + "FROM \"users_roles\" "
                   + "WHERE \"user_id\" = ? AND \"role_id\" = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, userId);
            s.setInt(2, roleId);
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


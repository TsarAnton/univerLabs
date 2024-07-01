package pckg;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String login;
    private String password;
    private ArrayList<String> roles;

    public User() {
        roles = new ArrayList<String>();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getRoles() {
        return this.roles;
    }

    public void setRole(String role) {
        this.roles.add(role);
    }
}

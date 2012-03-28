/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

/**
 *
 * @author arvin
 */
public class login_temp {
    private String username;
    private String password;
    private int state;
    private int AuthRetries;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(int state) {
        this.state = state;
    }
    
    public int getAuthRetries()
    {
        return AuthRetries;
    }
    
    public void setAuthRetries(int AuthRetries)
    {
        this.AuthRetries = AuthRetries;
    }
    
}

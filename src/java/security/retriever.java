/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

/**
 *
 * @author unseen
 */
public class retriever 
{
    private String username;
    private String password;
    private String position;
    private java.sql.Timestamp ts;
    private boolean pass_changed;

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
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the ts
     */
    public java.sql.Timestamp getTs() {
        return ts;
    }

    /**
     * @param ts the ts to set
     */
    public void setTs(java.sql.Timestamp ts) {
        this.ts = ts;
    }

    /**
     * @return the pass_changed
     */
    public boolean isPass_changed() {
        return pass_changed;
    }

    /**
     * @param pass_changed the pass_changed to set
     */
    public void setPass_changed(boolean pass_changed) {
        this.pass_changed = pass_changed;
    }

    
    
}

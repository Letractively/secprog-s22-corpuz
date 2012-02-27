/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Date;

/**
 *
 * @author arvin
 */
public class info_tracker {
    private String card_name;
    private int card_num;
    private String card_type;
    private Date exp_date;
    private String billing;

    /**
     * @return the card_name
     */
    public String getCard_name() {
        return card_name;
    }

    /**
     * @param card_name the card_name to set
     */
    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    /**
     * @return the card_num
     */
    public int getCard_num() {
        return card_num;
    }

    /**
     * @param card_num the card_num to set
     */
    public void setCard_num(int card_num) {
        this.card_num = card_num;
    }

    /**
     * @return the card_type
     */
    public String getCard_type() {
        return card_type;
    }

    /**
     * @param card_type the card_type to set
     */
    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    /**
     * @return the exp_date
     */
    public Date getExp_date() {
        return exp_date;
    }

    /**
     * @param exp_date the exp_date to set
     */
    public void setExp_date(Date exp_date) {
        this.exp_date = exp_date;
    }

    /**
     * @return the billing
     */
    public String getBilling() {
        return billing;
    }

    /**
     * @param billing the billing to set
     */
    public void setBilling(String billing) {
        this.billing = billing;
    }

    
}

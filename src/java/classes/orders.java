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
public class orders {
    private String order_id;
    private String cust_id;
    private Date order_date;
    private String billing;
    private Date paid_date;
    private int card_num;
    private String card_name;
    private String card_type;
    private Date exp_date;

    /**
     * @return the order_id
     */
    public String getOrder_id() {
        return order_id;
    }

    /**
     * @param order_id the order_id to set
     */
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    /**
     * @return the cust_id
     */
    public String getCust_id() {
        return cust_id;
    }

    /**
     * @param cust_id the cust_id to set
     */
    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    /**
     * @return the order_date
     */
    public Date getOrder_date() {
        return order_date;
    }

    /**
     * @param order_date the order_date to set
     */
    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
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

    /**
     * @return the paid_date
     */
    public Date getPaid_date() {
        return paid_date;
    }

    /**
     * @param paid_date the paid_date to set
     */
    public void setPaid_date(Date paid_date) {
        this.paid_date = paid_date;
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
    
}

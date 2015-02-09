package com.model;


import java.util.Calendar;
import java.util.Date;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author song
 */
public class Receiving {
    private int item;
    private char site;
    private int vendor;
    private Date date;
    private int quantity;
    private Calendar cal = Calendar.getInstance();
    public Receiving()
    {
        item = -1;
        site = ' ';
        vendor = -1;
        date = null;
        quantity = -1;
    }

    /**
     * @return the item
     */
    public int getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(final int item) {
        this.item = item;
    }

    /**
     * @return the site
     */
    public char getSite() {
        return site;
    }

    /**
     * @param site the site to set
     */
    public void setSite(final char site) {
        this.site = site;
    }

    /**
     * @return the vendor
     */
    public int getVendor() {
        return vendor;
    }

    /**
     * @param vendor the vendor to set
     */
    public void setVendor(final int vendor) {
        this.vendor = vendor;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import com.model.Item;
import com.model.Receiving;

/**
 *
 * @author song
 */
public class ControlMediator {
    
    
    private ItemControl theItemControl = new ItemControl();
    private ReceivingControl theReceivingControl = new ReceivingControl();
    private SalesControl theSalesControl = new SalesControl();
    private SiteControl theSiteControl = new SiteControl();
    private SupplierControl theSupplierControl = new SupplierControl();

    /**
     * @return the theItemControl
     */
    public ItemControl getTheItemControl() {
        return theItemControl;
    }

    /**
     * @param theItemControl the theItemControl to set
     */
    public void setTheItemControl(Item[] items) {
        //this.theItemControl = theItemControl;
    }

    /**
     * @return the theReceivingControl
     */
    public ReceivingControl getTheReceivingControl() {
        return theReceivingControl;
    }

    /**
     * @param theReceivingControl the theReceivingControl to set
     */
    public void setTheReceivingControl(Receiving[] receivings) {
        this.theReceivingControl.setReceivingData(receivings);
    }

    /**
     * @return the theSalesControl
     */
    public SalesControl getTheSalesControl() {
        return theSalesControl;
    }

    /**
     * @param theSalesControl the theSalesControl to set
     */
    public void setTheSalesControl(SalesControl theSalesControl) {
        this.theSalesControl = theSalesControl;
    }

    /**
     * @return the theSiteControl
     */
    public SiteControl getTheSiteControl() {
        return theSiteControl;
    }

    /**
     * @param theSiteControl the theSiteControl to set
     */
    public void setTheSiteControl(SiteControl theSiteControl) {
        this.theSiteControl = theSiteControl;
    }

    /**
     * @return the theSupplierControl
     */
    public SupplierControl getTheSupplierControl() {
        return theSupplierControl;
    }

    /**
     * @param theSupplierControl the theSupplierControl to set
     */
    public void setTheSupplierControl(SupplierControl theSupplierControl) {
        this.theSupplierControl = theSupplierControl;
    }
}

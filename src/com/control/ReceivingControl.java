/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import com.model.Receiving;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author song
 */
public class ReceivingControl  {
    private ArrayList<Receiving> receivingData;
    private HashSet itemVariety;
    private HashSet siteVariety;
    private HashSet vendorVariety;
    private HashMap itemQuantity;
    private HashMap siteQuantity;
    private HashMap vendorQuantity;
    private HashMap dayQuantity;
    private HashMap<String, Integer> monthQuantity;
    private HashMap yearQuantity;
    Calendar cal = Calendar.getInstance();
    public ReceivingControl ()
    {
        itemVariety = new HashSet<>();
        siteVariety = new HashSet<>();
        vendorVariety = new HashSet<>();
        itemQuantity = new HashMap<>();
        siteQuantity = new HashMap<>();
        vendorQuantity = new HashMap<>();
        dayQuantity = new HashMap<>();
        monthQuantity = new HashMap<>();
        yearQuantity = new HashMap<>();
    }
    public void setReceivingData(ArrayList<Receiving> d)
    {
        this.receivingData = d;
        setItemHold();
    }
    private void setItemHold()
    {
        for (int i = 0; i < receivingData.size(); i++)
        {
            int quantity = receivingData.get(i).getQuantity();
            int item = receivingData.get(i).getItem();
            char site = receivingData.get(i).getSite();
            int vendor = receivingData.get(i).getVendor();
            Date date = receivingData.get(i).getDate();
            cal.setTime(date);
            String day = new SimpleDateFormat("MM-dd-yyyy").format(date);
            String month = new SimpleDateFormat("MM-yyyy").format(date);
            String year = new SimpleDateFormat("yyyy").format(date);
            itemVariety.add(item);
            siteVariety.add(site);
            vendorVariety.add(vendor);
            if (itemQuantity.containsKey(item))
                itemQuantity.put(item, ((int)itemQuantity.get(item) + quantity));
            else 
                itemQuantity.put(item, quantity);
            
            if (siteQuantity.containsKey(site))
                siteQuantity.put(site, ((int)siteQuantity.get(site) + quantity));
            else 
                siteQuantity.put(site, quantity);
            
            if (vendorQuantity.containsKey(vendor))
                vendorQuantity.put(vendor, ((int)vendorQuantity.get(vendor) + quantity));
            else 
                vendorQuantity.put(receivingData.get(i).getVendor(), quantity);
            
            if (dayQuantity.containsKey(day))
                dayQuantity.put(day, ((int)dayQuantity.get(day) + quantity));
            else 
                dayQuantity.put(day, quantity);
            
            if (monthQuantity.containsKey(month))
                monthQuantity.put(month, ((int)monthQuantity.get(month) + quantity));
            else 
                monthQuantity.put(month, quantity);
            
            if (yearQuantity.containsKey(year))
                yearQuantity.put(year, ((int)yearQuantity.get(year) + quantity));
            else
                yearQuantity.put(year, quantity);
        }
    }
    public int getNumberOfItem()
    {
        return this.itemVariety.size();
    }
    public int getNumberOfSite()
    {
        return this.siteVariety.size();
    }
    public int getNumberOfVendor()
    {
        return this.vendorVariety.size();
    }
    public HashSet getItemInfo()
    {
        return this.itemVariety;
    }
    public HashSet getSiteInfo()
    {
        return this.siteVariety;
    }
    public HashSet getVendorInfo()
    {
        return this.vendorVariety;
    }
    public HashMap getItemQuantity()
    {
        return this.itemQuantity;
    }
    public HashMap getSiteQuantity()
    {
        return this.siteQuantity;
    }
    public HashMap getVendorQuantity()
    {
        return this.vendorQuantity;
    }
    public HashMap getDayQuantity()
    {
        return this.dayQuantity;
    }
    public HashMap<String, Integer> getMonthQuantity()
    {
        return this.monthQuantity;
    }
    public HashMap getYearQuantity()
    {
        return this.yearQuantity;
    }
}

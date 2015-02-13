/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.control.ReceivingControl;
import com.model.Excel;
import com.model.Receiving;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author song
 */
public class TimeSeriesChartView {
    
    public TimeSeriesChartView() 
    {

    }
    public JFreeChart getTimeSeriesChart(Excel theExcel, ReceivingControl theRC)
    {
        
        System.out.println(theRC.getNumberOfItem());
        System.out.println(theRC.getNumberOfSite());
        System.out.println(theRC.getNumberOfVendor());
        System.out.println(theRC.getVendorInfo());
        System.out.println(theRC.getSiteInfo());
        System.out.println(theRC.getItemInfo());            
        
//        TimeSeries item1_xy_data = new TimeSeries("Item1");
//        TimeSeries item2_xy_data = new TimeSeries("Item2");
//        TimeSeries item3_xy_data = new TimeSeries("Item3");
        Receiving theReceiving[] = theExcel.getSheetReceiving();
        
//        HashMap<Month, Integer> item1Map = new HashMap<>();
//        HashMap<Month, Integer> item2Map = new HashMap<>();
//        HashMap<Month, Integer> item3Map = new HashMap<>();
        
        TimeSeries data[] = new TimeSeries[theRC.getNumberOfItem()];
        HashMap<Month, Integer> itemMap[] = new HashMap[theRC.getNumberOfItem()];
        for (int i = 0; i < theRC.getNumberOfItem(); i++)
        {
            String itemName = "item" + i;
            data[i] = new TimeSeries(itemName);
            itemMap[i] = new HashMap<>();
        }
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < theReceiving.length; i++)
        {
            cal.setTime(theReceiving[i].getDate());
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            int quantity = theReceiving[i].getQuantity();
            Month theMonth = new Month(month, year);            
            int itemNum = theReceiving[i].getItem()-1;
            itemMap[itemNum].put(theMonth, updateItemMap(itemMap[itemNum], theMonth, quantity));
//            if (theReceiving[i].getItem() == 1)
//            {
//                item1Map.put(theMonth, updateItemMap(item1Map, theMonth, quantity));
//            }
//            else if (theReceiving[i].getItem() == 2)
//            {
//                item2Map.put(theMonth, updateItemMap(item2Map, theMonth, quantity));
//            }
//            else if (theReceiving[i].getItem() == 3)
//            {
//                item3Map.put(theMonth, updateItemMap(item3Map, theMonth, quantity));
//            }
        }
        TimeSeriesCollection my_data_series = new TimeSeriesCollection();
        for (int i = 0; i < theRC.getNumberOfItem(); i++)
        {
            for (Map.Entry<Month, Integer> entry:itemMap[i].entrySet()) {
                data[i].add(entry.getKey(), entry.getValue());
            }
            my_data_series.addSeries(data[i]);
        }
        

        // add series using addSeries method
//        my_data_series.addSeries(item1_xy_data);
//        my_data_series.addSeries(item2_xy_data);
//        my_data_series.addSeries(item3_xy_data);        
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Receiving","Month","Quantity",my_data_series,true,true,false);        
        chart.setBackgroundPaint(Color.YELLOW);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.GREEN);
        plot.setRangeGridlinePaint(Color.orange);
        plot.setAxisOffset(new RectangleInsets(50, 0, 20, 5));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible (true);   

        XYLineAndShapeRenderer  renderer = (XYLineAndShapeRenderer) plot.getRenderer();      

        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled (true);                               

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MM.yyyy"));
        return chart;
    }
    private int updateItemMap(HashMap<Month, Integer> itemMap, Month theMonth, int quantity) {
        int quan = 0;
        if (itemMap.containsKey(theMonth)) {
            int temp = itemMap.get(theMonth);
            temp += quantity;
            quan = temp;
        }    
        return quan;
    }
}

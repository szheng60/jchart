/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.control.ControlMediator;
import com.model.Excel;
import com.model.Supplier;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author song
 */
public class Main extends javax.swing.JFrame {
    private Excel theExcel;
    public static ControlMediator theCM = new ControlMediator();
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        viewGraphButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        filePathTextField = new javax.swing.JTextField();
        filePathBrowseButton = new javax.swing.JButton();
        filePathRunButton = new javax.swing.JButton();
        vendorComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setBackground(new java.awt.Color(153, 153, 153));
        panel.setLayout(new java.awt.BorderLayout());

        viewGraphButton.setText("View");
        viewGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewGraphButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("File Path:");

        filePathTextField.setEditable(false);

        filePathBrowseButton.setText("Browse");
        filePathBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filePathBrowseButtonActionPerformed(evt);
            }
        });

        filePathRunButton.setText("Run");
        filePathRunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filePathRunButtonActionPerformed(evt);
            }
        });

        vendorComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendorComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("vendor:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(filePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(filePathBrowseButton)
                        .addGap(18, 18, 18)
                        .addComponent(filePathRunButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewGraphButton)
                    .addComponent(vendorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(filePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(filePathBrowseButton)
                        .addComponent(filePathRunButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(viewGraphButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vendorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGap(5, 52, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void viewGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewGraphButtonActionPerformed

        TimeSeriesChartView tscv = new TimeSeriesChartView();
        ChartPanel chartPanel = new ChartPanel(tscv.getTimeSeriesChart(theExcel, theCM.getTheReceivingControl()));
        panel.removeAll();
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.validate();      

    }//GEN-LAST:event_viewGraphButtonActionPerformed

    private void filePathBrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filePathBrowseButtonActionPerformed
        final JFileChooser fileChooser = new JFileChooser(".");
        final FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx", "xls");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showDialog(this, "choose a file");
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePathTextField.setText(selectedFile.getAbsolutePath());
        }
        
    }//GEN-LAST:event_filePathBrowseButtonActionPerformed

    private void filePathRunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filePathRunButtonActionPerformed
        String filePath = filePathTextField.getText();        
        try {
            this.theExcel = new Excel(filePath);
            theExcel.initialize();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        vendorInfo();
    }//GEN-LAST:event_filePathRunButtonActionPerformed
    private void vendorInfo()
    {
        ArrayList<Supplier> theSupplier = theExcel.getSheetSupplier();
        for (int i = 0; i < theSupplier.size(); i++)
        {
            vendorComboBox.addItem(theSupplier.get(i).getCode());
        }
    }
    private void vendorComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendorComboBoxActionPerformed
        String vendor = vendorComboBox.getSelectedItem().toString();
        TimeSeriesChartView tscv = new TimeSeriesChartView();
        ChartPanel chartPanel = new ChartPanel(tscv.getVendorTimeSeriesChart(theExcel, vendor));
        panel.removeAll();
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.validate();
    }//GEN-LAST:event_vendorComboBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton filePathBrowseButton;
    private javax.swing.JButton filePathRunButton;
    private javax.swing.JTextField filePathTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panel;
    private javax.swing.JComboBox vendorComboBox;
    private javax.swing.JButton viewGraphButton;
    // End of variables declaration//GEN-END:variables
}

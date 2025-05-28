/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.omega;

import com.mycompany.omega.classes.InventoryManager;
import com.mycompany.omega.classes.PO;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.mycompany.omega.classes.Employee;
import javax.swing.JOptionPane;
import com.mycompany.omega.classes.Session;

/**
 *
 * @author fikri
 */
public class InventoryFrame extends javax.swing.JFrame {
    private InventoryManager inventoryManager;
    public InventoryFrame(Employee user) {
        initComponents();
        
        if (user instanceof InventoryManager) {
            this.inventoryManager = (InventoryManager) user;  // Safe cast
        } else if (user.getRole() == Employee.Role.ADMINISTRATOR) {
            // Optional: Admins can access view-only or limited version
            this.inventoryManager = new InventoryManager(user.getEmployeeID(), user.getName(), user.getRole(), user.getEmail(), user.getPassword());
        } else {
            JOptionPane.showMessageDialog(this, "Access Denied. You are not authorized to access this page.");
            dispose();
            return;
        }
        lblGreeting.setText("Welcome, " + user.getRole() + ": " + user.getName());
        populatePOComboBox();
    }
    
    private void populatePOComboBox() {
        cmbPO.removeAllItems();
        List<String> poIDs = inventoryManager.getPOIDs();
        for (String id : poIDs) {
            cmbPO.addItem(id);
        }
        if (!poIDs.isEmpty()) {
            cmbPO.setSelectedIndex(0);
            loadPODetails(poIDs.get(0));
        }
    }
    
    private void loadPODetails(String poID) {
    DefaultTableModel model = new DefaultTableModel(
        new String[] {"Item ID", "Item Name", "Quantity Ordered", "Quantity Received"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 3; // Only "Received" column is editable
        }
    };

    List<PO> poDetails = inventoryManager.getPODetailsByID(poID);

    for (PO po : poDetails) {
        model.addRow(new Object[]{
            po.getItem().getItemID(),
            po.getItem().getItemName(),
            po.getQuantity(),
            0 // default 0 for "Received"
        });
    }

    tblPODetails.setModel(model);

    if (!poDetails.isEmpty()) {
        PO firstPO = poDetails.get(0);
        txtSupplier.setText(firstPO.getSup().getSupplierName());
        txtDateOrdered.setText(firstPO.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        txtStatus.setText(firstPO.getApproval());
    }
}

    
    
    private void updateStockFromTable() {
    DefaultTableModel model = (DefaultTableModel) tblPODetails.getModel();

    String selectedPO = (String) cmbPO.getSelectedItem();
    if (selectedPO == null) {
        JOptionPane.showMessageDialog(this, "No PO selected.");
        return;
    }

    Map<String, Map<String, Integer>> poItemMap = new HashMap<>();
    Map<String, Integer> itemQtyMap = new HashMap<>();

    for (int i = 0; i < model.getRowCount(); i++) {
        String itemID = model.getValueAt(i, 0).toString();
        int qtyOrdered = Integer.parseInt(model.getValueAt(i, 2).toString());
        Object qtyObj = model.getValueAt(i, 3);
        int qtyReceived = 0;

        try {
            qtyReceived = Integer.parseInt(qtyObj.toString());
            if (qtyReceived < 0) throw new NumberFormatException();
            if (qtyReceived > qtyOrdered) {
                JOptionPane.showMessageDialog(this,
                    "Received quantity for item '" + itemID + "' cannot exceed ordered quantity (" + qtyOrdered + ").",
                    "Quantity Exceeded", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid quantity at row " + (i + 1));
            return;
        }

        itemQtyMap.put(itemID, qtyReceived);
    }

    poItemMap.put(selectedPO, itemQtyMap);
    inventoryManager.updateStock(poItemMap);

    JOptionPane.showMessageDialog(this, "Stock updated successfully!");

    populatePOComboBox();
    if (cmbPO.getSelectedItem() != null) {
        loadPODetails((String) cmbPO.getSelectedItem());
    }
}


    
    private void resetForm() {
        txtSupplier.setText("");
        txtDateOrdered.setText("");
        txtStatus.setText("");
        DefaultTableModel model = (DefaultTableModel) tblPODetails.getModel();
        model.setRowCount(0);
        cmbPO.setSelectedIndex(-1);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblGreeting = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btnItemList = new javax.swing.JButton();
        btnReceived2 = new javax.swing.JButton();
        btnGenerateReport = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cmbPO = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSupplier = new javax.swing.JTextField();
        txtDateOrdered = new javax.swing.JTextField();
        txtStatus = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPODetails = new javax.swing.JTable();
        btnReceived = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel6.setBackground(new java.awt.Color(255, 0, 0));
        jPanel6.setForeground(new java.awt.Color(255, 0, 0));

        jLabel1.setBackground(new java.awt.Color(255, 255, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Inventory Dashboard");
        jLabel1.setOpaque(true);

        lblGreeting.setBackground(new java.awt.Color(255, 255, 0));
        lblGreeting.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblGreeting.setText("Welcome <Username>");
        lblGreeting.setOpaque(true);

        btnLogout.setBackground(new java.awt.Color(255, 255, 102));
        btnLogout.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGreeting)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(35, 35, 35))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGreeting)
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(32, 32, 32))
        );

        jPanel7.setBackground(new java.awt.Color(255, 0, 0));

        btnItemList.setBackground(new java.awt.Color(255, 255, 102));
        btnItemList.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnItemList.setText("Item List");
        btnItemList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItemListActionPerformed(evt);
            }
        });

        btnReceived2.setBackground(new java.awt.Color(255, 255, 102));
        btnReceived2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnReceived2.setText("Update Stock");
        btnReceived2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceived2ActionPerformed(evt);
            }
        });

        btnGenerateReport.setBackground(new java.awt.Color(255, 255, 102));
        btnGenerateReport.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnGenerateReport.setText("Generate Report");
        btnGenerateReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReceived2, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(btnItemList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGenerateReport, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(btnItemList)
                .addGap(29, 29, 29)
                .addComponent(btnReceived2)
                .addGap(29, 29, 29)
                .addComponent(btnGenerateReport)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 153, 255));

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));

        jLabel8.setBackground(new java.awt.Color(102, 255, 255));
        jLabel8.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel8.setText("Update Stock from Purchase Order:");
        jLabel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel8.setOpaque(true);

        cmbPO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPO, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(204, 204, 255));

        jLabel5.setBackground(new java.awt.Color(102, 255, 255));
        jLabel5.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel5.setText("Supplier           :");
        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.setOpaque(true);

        jLabel6.setBackground(new java.awt.Color(102, 255, 255));
        jLabel6.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel6.setText("Date Ordered  :");
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.setOpaque(true);

        jLabel7.setBackground(new java.awt.Color(102, 255, 255));
        jLabel7.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel7.setText("Status               :");
        jLabel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel7.setOpaque(true);

        txtSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupplierActionPerformed(evt);
            }
        });

        txtDateOrdered.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateOrderedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                    .addComponent(txtDateOrdered)
                    .addComponent(txtStatus))
                .addGap(92, 92, 92))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDateOrdered, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        tblPODetails.setBackground(new java.awt.Color(204, 255, 255));
        tblPODetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Item ID", "Item Name", "Quantity Ordered", "Quantity Received"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPODetails);

        btnReceived.setBackground(new java.awt.Color(255, 255, 102));
        btnReceived.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnReceived.setText("Confirm Received");
        btnReceived.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceivedActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(255, 255, 102));
        btnReset.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReceived, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(btnReceived)
                        .addGap(62, 62, 62)
                        .addComponent(btnReset)
                        .addContainerGap(95, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPOActionPerformed
         String selectedPO = (String) cmbPO.getSelectedItem();
        if (selectedPO != null) {
            loadPODetails(selectedPO);
        }
    }//GEN-LAST:event_cmbPOActionPerformed

    private void txtSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupplierActionPerformed

    private void txtDateOrderedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateOrderedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateOrderedActionPerformed

    private void btnReceivedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceivedActionPerformed
    
    if (tblPODetails.isEditing()) {
        tblPODetails.getCellEditor().stopCellEditing();
    }
    updateStockFromTable();

    }//GEN-LAST:event_btnReceivedActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnItemListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItemListActionPerformed
      new ViewItemFrame().setVisible(true);
      this.dispose();
    }//GEN-LAST:event_btnItemListActionPerformed

    private void btnReceived2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceived2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReceived2ActionPerformed

    private void btnGenerateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateReportActionPerformed
       new GenerateReportFrame().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btnGenerateReportActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Session.clear();
        this.dispose();
        new LoginFrame().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//       
//        //Session.setCurrentUser(dummy);
//        java.awt.EventQueue.invokeLater(()->{
//        new InventoryFrame().setVisible(true);
//    });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerateReport;
    private javax.swing.JButton btnItemList;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnReceived;
    private javax.swing.JButton btnReceived2;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cmbPO;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGreeting;
    private javax.swing.JTable tblPODetails;
    private javax.swing.JTextField txtDateOrdered;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtSupplier;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.omega;
import com.mycompany.omega.classes.*;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.ZoneId.systemDefault;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author userr
 */
public class PMFrame extends javax.swing.JFrame {
    private PurchaseManager manager;
    

    /**
     * Creates new form PMFrame
     */
    public PMFrame(Employee user) {
        initComponents();
        if (user instanceof PurchaseManager) {
            this.manager = (PurchaseManager) user;  // Safe cast
        } else if (user.getRole() == Employee.Role.ADMINISTRATOR) {
            // Optional: Admins can access view-only or limited version
            this.manager = new PurchaseManager(user.getEmployeeID(), user.getName(), user.getRole(), user.getEmail(), user.getPassword());
        } else {
            JOptionPane.showMessageDialog(this, "Access Denied. You are not authorized to access this page.");
            dispose();
            return;
        }
       
        System.out.println("Session User: " + Session.getCurrentUser());
        System.out.println("Manager assigned? " + (manager != null));
        
        loadAllPendingPRs();
        loadAllPOs();
        loadAllSuppliers();
        lblGreeting.setText("Welcome, " + manager.getRole() + ": " + manager.getName());
    }
    
    private void loadAllPRs(){
        DefaultTableModel model = (DefaultTableModel) prTable.getModel();
        model.setRowCount(0); 
        
        for(PR pr: manager.getAllPRs()){
            model.addRow(new Object[]{
                pr.getPR_ID(),
                pr.getItem().getItemID(),
                pr.getQuantity(),
                pr.getRequestdDate().toString(),
                pr.getRequestedBy(),
                pr.getStatus()
            });
        }
        System.out.println("PRs loaded" + manager.getAllPRs().size());
    }
    
    private void loadAllPendingPRs(){
        DefaultTableModel model = (DefaultTableModel) prTable.getModel();
        model.setRowCount(0); 
       
        List<PR> pendingList = manager.getPendingPRs();
        
        for(PR pr: manager.getPendingPRs()){
            model.addRow(new Object[]{
                pr.getPR_ID(),
                pr.getItem().getItemID(),
                pr.getQuantity(),
                pr.getRequestdDate().toString(),
                pr.getRequestedBy(),
                pr.getStatus()
            });
        }
        System.out.println("PENDING PRs loaded" + manager.getPendingPRs().size());
        
        if (pendingList.isEmpty()){
            JOptionPane.showMessageDialog(this, "Hooray! There are no PRs to be converted into POs. Enjoy a cup of tea and snacks", 
                    "No Pending PRs", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
   
    private void loadAllPOs(){
        DefaultTableModel model = (DefaultTableModel) poTable.getModel();
        model.setRowCount(0);
        
        for(PO po: manager.viewAll()){
            model.addRow(new Object[]{
                po.getPoID(),
                po.getPr().getPR_ID(),
                po.getItem().getItemID(),
                po.getSup().getSupplierName(),
                po.getQuantity(),
                po.getDate().toString(),
                po.getRequestedBy(),
                po.getApproval(),
                po.getApprovalBy()
                
            });
        }
        //System.out.println("POs Loaded" + manager.get);
    }
    
    private void loadAllSuppliers(){
        DefaultTableModel model = (DefaultTableModel) supplierTable.getModel();
        model.setRowCount(0);
        
        for(Supplier sup: manager.getAllSuppliers()){
            model.addRow(new Object[]{
                sup.getSupplierID(),
                sup.getSupplierName(),
                sup.getContact(),
                sup.getItem().getItemID(),
                sup.getPrice()
            });
        }
        System.out.println("Suppliers Loaded" + manager.getAllSuppliers().size());
    }
    
    private void loadSupplierForItem(String itemID){
        DefaultTableModel model = (DefaultTableModel) supplierTable.getModel();
        model.setRowCount(0);
        
        for(Supplier sup: manager.getSupplierForItem(itemID)){
            model.addRow(new Object[]{
            sup.getSupplierID(),
            sup.getSupplierName(),
            sup.getContact(),
            sup.getItem().getItemID(),
            sup.getPrice()
            });
        }
    }
    
    private void clearPOForm(){
        newPO_IDTextField.setText("");
        prTextField.setText("");
        itemIDTextField.setText("");
        quantityTextField.setText("");
        supplierIDTextField.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblGreeting = new javax.swing.JLabel();
        btnCreatePO = new javax.swing.JButton();
        btnEditDeletePO = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        prTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        supplierTable = new javax.swing.JTable();
        searchLabel = new javax.swing.JLabel();
        prSearchTextField = new javax.swing.JTextField();
        dateSearchTextField = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        searchPRButton = new javax.swing.JButton();
        clearPRButton = new javax.swing.JButton();
        btnShowAllPRs = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        newPO_IDLabel = new javax.swing.JLabel();
        newPO_IDTextField = new javax.swing.JTextField();
        prLabel = new javax.swing.JLabel();
        prTextField = new javax.swing.JTextField();
        quantityLabel = new javax.swing.JLabel();
        quantityTextField = new javax.swing.JTextField();
        itemIDLabel = new javax.swing.JLabel();
        itemIDTextField = new javax.swing.JTextField();
        supplierIDLabel = new javax.swing.JLabel();
        supplierIDTextField = new javax.swing.JTextField();
        recordPO_Btn = new javax.swing.JButton();
        rejectPR_Button = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        poTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1253, 670));

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(162, 102));

        lblGreeting.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblGreeting.setText("Hello, Purchase Manager. Richard Ong");

        btnCreatePO.setText("Create PO");
        btnCreatePO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatePOActionPerformed(evt);
            }
        });

        btnEditDeletePO.setText("Edit/Delete PO");
        btnEditDeletePO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditDeletePOActionPerformed(evt);
            }
        });

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblGreeting, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191)
                .addComponent(btnCreatePO)
                .addGap(47, 47, 47)
                .addComponent(btnEditDeletePO)
                .addGap(52, 52, 52)
                .addComponent(btnLogout)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGreeting, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditDeletePO)
                    .addComponent(btnCreatePO)
                    .addComponent(btnLogout))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(1000, 630));

        prTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "PR_ID", "Item_ID", "Quantity", "Request_Date", "Request_By", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        prTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(prTable);

        supplierTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Supplier_ID", "Name", "Item_ID", "Contact", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        supplierTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supplierTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(supplierTable);

        searchLabel.setText("Search based on Purchase Requisition ID or Request Date:");

        prSearchTextField.setText("Enter PR ID");
        prSearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prSearchTextFieldActionPerformed(evt);
            }
        });

        jLabel8.setText("or");

        searchPRButton.setText("Search");
        searchPRButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPRButtonActionPerformed(evt);
            }
        });

        clearPRButton.setText("Clear");
        clearPRButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearPRButtonActionPerformed(evt);
            }
        });

        btnShowAllPRs.setText("Show All Purchase Requisition");
        btnShowAllPRs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowAllPRsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(searchPRButton)
                        .addGap(38, 38, 38)
                        .addComponent(clearPRButton))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnShowAllPRs)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchLabel)
                            .addComponent(prSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addComponent(dateSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchPRButton)
                        .addComponent(clearPRButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnShowAllPRs)
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        newPO_IDLabel.setText("New Purchase Order ID:");

        newPO_IDTextField.setEditable(false);
        newPO_IDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPO_IDTextFieldActionPerformed(evt);
            }
        });

        prLabel.setText("Purchase ID:");

        prTextField.setEditable(false);
        prTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prTextFieldActionPerformed(evt);
            }
        });

        quantityLabel.setText("Quantity:");

        quantityTextField.setEditable(false);
        quantityTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityTextFieldActionPerformed(evt);
            }
        });

        itemIDLabel.setText("Item ID:");

        itemIDTextField.setEditable(false);
        itemIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIDTextFieldActionPerformed(evt);
            }
        });

        supplierIDLabel.setText("Supplier ID:");

        supplierIDTextField.setEditable(false);
        supplierIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierIDTextFieldActionPerformed(evt);
            }
        });

        recordPO_Btn.setText("Record");
        recordPO_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordPO_BtnActionPerformed(evt);
            }
        });

        rejectPR_Button.setText("Reject");
        rejectPR_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectPR_ButtonActionPerformed(evt);
            }
        });

        poTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "PO_ID", "PR_ID", "Item_ID", "Supplier_ID", "Quantity", "Requested_Date", "Requested_By", "Status", "Appproval_By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(poTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(itemIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(itemIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(supplierIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(supplierIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(newPO_IDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newPO_IDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(prLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(quantityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(recordPO_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(rejectPR_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newPO_IDLabel)
                            .addComponent(newPO_IDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(prTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(itemIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(itemIDLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quantityLabel)
                            .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(supplierIDLabel)
                            .addComponent(supplierIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(recordPO_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rejectPR_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1258, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newPO_IDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPO_IDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newPO_IDTextFieldActionPerformed

    private void prTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prTextFieldActionPerformed

    private void quantityTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityTextFieldActionPerformed

    private void itemIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemIDTextFieldActionPerformed

    private void supplierIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierIDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supplierIDTextFieldActionPerformed

    private void recordPO_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordPO_BtnActionPerformed
        // TODO add your handling code here:
        //Validate fields
        if (newPO_IDTextField.getText().isEmpty() || prTextField.getText().isEmpty() || itemIDTextField.getText().isEmpty() || 
                quantityTextField.getText().isEmpty() || supplierIDTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please make sure all fields are filled in before recording the Purchase Order!");
            return;
        }
        
        //Gather inputs from text fields
        String poID = newPO_IDTextField.getText().trim();
        String prID = prTextField.getText().trim();
        String itemID = itemIDTextField.getText().trim();
        String supplierID = supplierIDTextField.getText().trim();
        int quantity = Integer.parseInt(quantityTextField.getText().trim());
        String requestedBy = Session.getCurrentUser().getEmployeeID();
        
        //Resolve references
        PR pr = manager.getPRById(prID);
        Item item = pr.getItem();
        Supplier supplier = manager.getSupplierForItem(itemID)
                .stream()
                .filter(s -> s.getSupplierID().equals(supplierID))
                .findFirst()
                .orElse(null);
        
        if (supplier == null){
            JOptionPane.showMessageDialog(this,"Selected supplier not found. Make sure it exists in the Supplier Table.");
            return;
        }
        
        //Create and record PO
        PO newPO = new PO (poID, pr, supplier, item, quantity, LocalDate.now(), requestedBy,"PENDING","-", 0);
        manager.add(newPO); //Write to PO.txt file
        manager.updatePRStatus(prID, "Approved"); //Update PR status from pending to approve
        
        JOptionPane.showMessageDialog(this, "Purchase Order recorded Successfully.");
        loadAllPendingPRs();
        loadAllSuppliers();
        loadAllPOs();
        clearPOForm();
    }//GEN-LAST:event_recordPO_BtnActionPerformed

    private void rejectPR_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectPR_ButtonActionPerformed
        // TODO add your handling code here:
        //Validate all fields except supplier field
        if (newPO_IDTextField.getText().isEmpty() || prTextField.getText().isEmpty() || itemIDTextField.getText().isEmpty() || 
                quantityTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please select the PR to reject from the PR table before rejecting it!");
            return;
        }
        
        String prID = prTextField.getText().trim();
        manager.updatePRStatus(prID, "REJECTED");
        
        JOptionPane.showMessageDialog(this, "PR with the " + prID + " has been rejected");
        loadAllPendingPRs();
    }//GEN-LAST:event_rejectPR_ButtonActionPerformed

    private void prSearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prSearchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prSearchTextFieldActionPerformed

    private void searchPRButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPRButtonActionPerformed
        // TODO add your handling code here:
        String prId = prSearchTextField.getText().trim();
        Date selectedDate = dateSearchTextField.getDate();
        
        DefaultTableModel model = (DefaultTableModel) prTable.getModel();
        model.setRowCount(0);
        
        if(!prId.isEmpty()){
            PR result = manager.getPRById(prId);
            if (result != null){
                model.addRow(new Object[]{
                    result.getPR_ID(),
                    result.getItem().getItemID(),
                    result.getQuantity(),
                    result.getRequestdDate().toString(),
                    result.getRequestedBy(),
                    result.getStatus()
                });
            }else{
                JOptionPane.showMessageDialog(this, "No PR found based on the PR ID: " + prId);
            }
        } else if (selectedDate != null){
            LocalDate searchDate = selectedDate.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
            List<PR> results = manager.filterPR_byDate(searchDate);
            
            if(results.isEmpty()){
                JOptionPane.showMessageDialog(this,"No PR found based on selected date. ");

            } else {
                for (PR pr : results){
                    model.addRow(new Object[]{
                        pr.getPR_ID(),
                        pr.getItem().getItemID(),
                        pr.getQuantity(),
                        pr.getRequestdDate().toString(),
                        pr.getRequestedBy(),
                        pr.getStatus()
                    });
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,"Please enter a valid PR ID or select a date!");
        }
    }//GEN-LAST:event_searchPRButtonActionPerformed

    private void clearPRButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearPRButtonActionPerformed
        // TODO add your handling code here:
        prSearchTextField.setText("");
        dateSearchTextField.setDate(null);
        loadAllPRs();
    }//GEN-LAST:event_clearPRButtonActionPerformed

    private void prTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = prTable.getSelectedRow();
        if (selectedRow >= 0){
            String prID = prTable.getValueAt(selectedRow, 0).toString(); //get PR_ID
            PR selectedPR = manager.getPRById(prID);
            
            if (selectedPR != null){
                //Auto-fill fields
                newPO_IDTextField.setText(manager.generateNextPO_ID());
                prTextField.setText(selectedPR.getPR_ID());
                itemIDTextField.setText(selectedPR.getItem().getItemID());
                quantityTextField.setText(String.valueOf(selectedPR.getQuantity()));
                
                if (selectedPR.getStatus().equalsIgnoreCase("APPROVED")){
                    recordPO_Btn.setEnabled(false);
                    rejectPR_Button.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "This PR has been APPROVED and an existing PO has been created.");
                } else if (selectedPR.getStatus().equalsIgnoreCase("REJECTED")){
                    recordPO_Btn.setEnabled(false);
                    rejectPR_Button.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "This PR has been REJECTED, NO Purchase Order is created.\nOnly PENDING Requisition is able to create a new Purchase Order");
                } else {
                    recordPO_Btn.setEnabled(true);
                    rejectPR_Button.setEnabled(true);
                    JOptionPane.showMessageDialog(this, "This PR is PENDING, a NEW Purchase Order is pending to be created.");
                }
                
                //Filter suppliers for selected item based on the itemID
                loadSupplierForItem(selectedPR.getItem().getItemID());
                
            }
        }
    }//GEN-LAST:event_prTableMouseClicked

    private void supplierTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplierTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = supplierTable.getSelectedRow();
        if (selectedRow >= 0){
            String supplierID = supplierTable.getValueAt(selectedRow, 0).toString();
            
            supplierIDTextField.setText(supplierID);
        }
    }//GEN-LAST:event_supplierTableMouseClicked

    private void btnCreatePOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreatePOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCreatePOActionPerformed

    private void btnEditDeletePOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditDeletePOActionPerformed
        // TODO add your handling code here:
        new PM2Frame(Session.getCurrentUser()).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnEditDeletePOActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        Session.clear();
        System.out.println("User Logged Out: " + Session.getCurrentUser());
        this.dispose();
        new LoginFrame().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnShowAllPRsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowAllPRsActionPerformed
        // TODO add your handling code here:
        loadAllPRs();
    }//GEN-LAST:event_btnShowAllPRsActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(PMFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PMFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PMFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PMFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new PMFrame().setVisible(true);
//            }
//        });
//    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreatePO;
    private javax.swing.JButton btnEditDeletePO;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnShowAllPRs;
    private javax.swing.JButton clearPRButton;
    private com.toedter.calendar.JDateChooser dateSearchTextField;
    private javax.swing.JLabel itemIDLabel;
    private javax.swing.JTextField itemIDTextField;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblGreeting;
    private javax.swing.JLabel newPO_IDLabel;
    private javax.swing.JTextField newPO_IDTextField;
    private javax.swing.JTable poTable;
    private javax.swing.JLabel prLabel;
    private javax.swing.JTextField prSearchTextField;
    private javax.swing.JTable prTable;
    private javax.swing.JTextField prTextField;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JTextField quantityTextField;
    private javax.swing.JButton recordPO_Btn;
    private javax.swing.JButton rejectPR_Button;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JButton searchPRButton;
    private javax.swing.JLabel supplierIDLabel;
    private javax.swing.JTextField supplierIDTextField;
    private javax.swing.JTable supplierTable;
    // End of variables declaration//GEN-END:variables
}

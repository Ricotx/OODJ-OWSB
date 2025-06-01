/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.omega;
import com.mycompany.omega.classes.*;
import java.awt.Color;
import java.text.DateFormat;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.ZoneId.systemDefault;
import java.util.Date;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ahaiq
 */


public class FinanceFrame extends javax.swing.JFrame {
    private FinanceManager manager;
    private final String placeholderTextPO = "Enter PO ID";
    private final String placeholderTextPR = "Enter PR ID";
    private final String placeholderTextPayment = "Enter Payment ID";
    
    public FinanceFrame(Employee user) {
        initComponents();
           
        if (user instanceof FinanceManager) {
            this.manager = (FinanceManager) user;  // Safe cast
        } else if (user.getRole() == Employee.Role.ADMINISTRATOR) {
            // Optional: Admins can access view-only or limited version
            this.manager = new FinanceManager(user.getEmployeeID(), user.getName(), user.getRole(), user.getEmail(), user.getPassword());
        } else {
            JOptionPane.showMessageDialog(this, "Access Denied. You are not authorized to access this page.");
            dispose();
            return;
        }

        System.out.println("Session User: " + Session.getCurrentUser());
        System.out.println("Manager assigned? " + (manager != null));
        
        txtFindPO.setText(placeholderTextPO);
        txtFindPO.setForeground(Color.LIGHT_GRAY);
        
        txtFindPR.setText(placeholderTextPR);
        txtFindPR.setForeground(Color.LIGHT_GRAY);
        
        txtFindPay.setText(placeholderTextPayment);
        txtFindPay.setForeground(Color.LIGHT_GRAY);
        
        loadAllPRs();
        loadAllPOs();
        loadUnpaidPOs();
        loadAllPayments();
//        loadAllSuppliers();
        lblGreeting.setText("Welcome, " + manager.getRole() + ": " + manager.getName());
        
        txtFindPO.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtFindPO.getText().equals(placeholderTextPO)) {
                    txtFindPO.setText("");
                    txtFindPO.setForeground(Color.BLACK); // user typing
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtFindPO.getText().isEmpty()) {
                    txtFindPO.setText(placeholderTextPO);
                    txtFindPO.setForeground(Color.GRAY);
                }
            }
        });
        
        txtFindPR.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtFindPR.getText().equals(placeholderTextPR)) {
                    txtFindPR.setText("");
                    txtFindPR.setForeground(Color.BLACK); // user typing
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtFindPR.getText().isEmpty()) {
                    txtFindPR.setText(placeholderTextPR);
                    txtFindPR.setForeground(Color.GRAY);
                }
            }
        });
        
        txtFindPay.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtFindPay.getText().equals(placeholderTextPayment)) {
                    txtFindPay.setText("");
                    txtFindPay.setForeground(Color.BLACK); // user typing
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtFindPay.getText().isEmpty()) {
                    txtFindPay.setText(placeholderTextPayment);
                    txtFindPay.setForeground(Color.GRAY);
                }
            }
        });
        
        txtFindPO.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnSearchPOActionPerformed(null);  // or your search method
        });

        txtFindPR.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnSearchPRActionPerformed(null);  // or your search method
        });
        
    }
    
    private void loadAllPRs(){
        DefaultTableModel model = (DefaultTableModel) tblPR.getModel();
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
   
    private void loadAllPayments() {
        DefaultTableModel model = (DefaultTableModel) tblPayment.getModel();
        model.setRowCount(0); // clear previous data

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "MY")); // RM currency
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy"); // e.g., 19 May 2025

        
        for (Payment payment : manager.getAllPayments()) {
            LocalDate date = LocalDate.parse(payment.getPaymentDate());
            
            model.addRow(new Object[]{
                payment.getPaymentID(),
                payment.getPoID(),
                payment.getSupplierID(),
                payment.getItemID(),
                payment.getQuantity(),
                currencyFormat.format(payment.getUnitPrice()),   // RM format
                currencyFormat.format(payment.getTotalAmount()),
                date.format(dateFormat),
                payment.getPaidBy()
            });
        }
    }
    
    private void loadAllPOs(){
        DefaultTableModel model = (DefaultTableModel) tblPO.getModel();
        model.setRowCount(0);
               
        for(PO po: manager.viewAll()){
            model.addRow(new Object[]{
                po.getPoID(),
                po.getPr().getPR_ID(),
                po.getSup().getSupplierName(),
                po.getItem().getItemName(),
                po.getQuantity(),
                po.getDate().toString(),
                po.getRequestedBy(),
                po.getApproval(),
                po.getApprovalBy()
                
            });
        }
        //System.out.println("POs Loaded" + manager.get);
    }

    
    private void loadUnpaidPOs() {
        DefaultTableModel model = (DefaultTableModel) tblUnpaidPO.getModel();
        model.setRowCount(0); // Clear table

        for (PO po : manager.viewAll()) {
            // Only show if PO is approved and not paid
            if ("APPROVED".equalsIgnoreCase(po.getApproval())
                    && !manager.isPOAlreadyPaid(po.getPoID())) {

                model.addRow(new Object[]{
                    po.getPoID(),
                    po.getPr().getPR_ID(),
                    po.getSup().getSupplierName(),
                    po.getItem().getItemName(),
                    po.getQuantity(),
                    po.getDate().toString(),
                    po.getRequestedBy(),
                    po.getApproval(),
                    po.getApprovalBy()
                });
            }
        }
    }
    private void clearPOForm(){
        txtPO.setText("");
        txtItemID.setText("");
        txtItemName.setText("");
        txtQuantity.setText("");
        txtSupplier.setText("");
        txtStatus.setText("");      
        txtPrice.setText("");
        dateChooserPO.setDate(null);
        dateChooserReport.setDate(null);
        dateChooserPR.setDate(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblGreeting = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnPO = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        dateChooserPO = new com.toedter.calendar.JDateChooser();
        btnSearchPO = new javax.swing.JButton();
        btnApprove = new javax.swing.JButton();
        txtFindPO = new javax.swing.JTextField();
        btnReject = new javax.swing.JButton();
        boxFilter = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        txtSupplier = new javax.swing.JTextField();
        txtItemID = new javax.swing.JTextField();
        txtItemName = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        txtStatus = new javax.swing.JTextField();
        txtPO = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        lblPrice = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPO = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtFindPR = new javax.swing.JTextField();
        btnSearchPR = new javax.swing.JButton();
        dateChooserPR = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPR = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtFindPay = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblUnpaidPO = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtPOID = new javax.swing.JTextField();
        txtSupplierPayment = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtItemPayment = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtDatePayment = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtApprovalPayment = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtCostPayment = new javax.swing.JTextField();
        txtQuantityPayment = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnDeletePayment = new javax.swing.JButton();
        btnSearchPay = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPayment = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        btnGenerateReport = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        dateChooserReport = new com.toedter.calendar.JDateChooser();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAreaReport = new javax.swing.JTextArea();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(1253, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Finance Dashboard");

        lblGreeting.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblGreeting.setText("Welcome <Username>");

        btnLogout.setText("Logout");
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnRefresh.setText("Referesh");
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGreeting)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 834, Short.MAX_VALUE)
                .addComponent(btnRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogout)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblGreeting))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLogout)
                            .addComponent(btnRefresh))))
                .addGap(0, 35, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 110));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(172, 584));

        btnPO.setText("Purchase Order");
        btnPO.setBorder(null);
        btnPO.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPOActionPerformed(evt);
            }
        });

        jButton5.setText("Purchase Request");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Payment");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton2.setText("Generate Report");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(btnPO, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton5)
                .addGap(37, 37, 37)
                .addComponent(jButton6)
                .addGap(36, 36, 36)
                .addComponent(jButton2)
                .addContainerGap(306, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 180, 550));

        btn1.setBackground(new java.awt.Color(255, 255, 255));
        btn1.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        btn1.setEnabled(false);
        btn1.setPreferredSize(new java.awt.Dimension(1116, 500));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(dateChooserPO, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 160, -1));

        btnSearchPO.setText("Search");
        btnSearchPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchPOActionPerformed(evt);
            }
        });
        jPanel3.add(btnSearchPO, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, -1));

        btnApprove.setText("Approve");
        btnApprove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });
        jPanel3.add(btnApprove, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 510, -1, -1));

        txtFindPO.setForeground(new java.awt.Color(204, 204, 204));
        txtFindPO.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtFindPO.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFindPOFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFindPOFocusLost(evt);
            }
        });
        txtFindPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindPOActionPerformed(evt);
            }
        });
        jPanel3.add(txtFindPO, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 185, -1));

        btnReject.setText("Reject");
        btnReject.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectActionPerformed(evt);
            }
        });
        jPanel3.add(btnReject, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 510, -1, -1));

        boxFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clear", "Approved", "Pending", "Rejected" }));
        boxFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boxFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxFilterActionPerformed(evt);
            }
        });
        jPanel3.add(boxFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 127, -1));

        jLabel3.setText("Find");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 30, 20));

        jLabel4.setText("Filter by: ");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("PO ID :");

        jLabel6.setText("Item ID:");

        jLabel7.setText("Item Name");

        jLabel8.setText("Quantity:");

        jLabel9.setText("Supplier:");

        jLabel10.setText("Status:");

        jTextField2.setEditable(false);
        jTextField2.setToolTipText("");
        jTextField2.setUI(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        txtSupplier.setEditable(false);
        txtSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupplierActionPerformed(evt);
            }
        });

        txtItemID.setEditable(false);
        txtItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemIDActionPerformed(evt);
            }
        });

        txtItemName.setEditable(false);
        txtItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemNameActionPerformed(evt);
            }
        });

        txtQuantity.setEditable(false);
        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });

        txtStatus.setEditable(false);
        txtStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStatusActionPerformed(evt);
            }
        });

        txtPO.setEditable(false);
        txtPO.setActionCommand("<Not Set>");
        txtPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPOActionPerformed(evt);
            }
        });

        txtPrice.setEditable(false);
        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });

        lblPrice.setText("Price:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(txtPO)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtItemID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 130, -1, 360));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Purchase Order Details");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setText("Purchase Order");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        tblPO.setAutoCreateRowSorter(true);
        tblPO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblPO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "PO_ID", "PR_ID", "Supplier", "Item", "Quantity", "Date", "Ordered By", "Approval", "Asign By"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPO.setGridColor(new java.awt.Color(0, 0, 0));
        tblPO.setSelectionBackground(new java.awt.Color(204, 204, 255));
        tblPO.getTableHeader().setReorderingAllowed(false);
        tblPO.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tblPOComponentAdded(evt);
            }
        });
        tblPO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPOMouseClicked(evt);
            }
        });
        tblPO.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tblPOCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jScrollPane1.setViewportView(tblPO);
        tblPO.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 718, -1));

        btn1.addTab("PO", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Purchase Requisition");

        jLabel12.setText("Find");

        txtFindPR.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtFindPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindPRActionPerformed(evt);
            }
        });

        btnSearchPR.setText("Search");
        btnSearchPR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearchPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchPRActionPerformed(evt);
            }
        });

        tblPR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "PR_ID", "Item_ID", "Quantity", "Request Date", "Requester", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPR.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblPR);
        tblPR.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFindPR, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateChooserPR, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addComponent(btnSearchPR)))
                .addContainerGap(252, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(txtFindPR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnSearchPR))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateChooserPR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btn1.addTab("PR", jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setText("Payment ");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        txtFindPay.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtFindPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindPayActionPerformed(evt);
            }
        });
        jPanel5.add(txtFindPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 145, -1));

        jLabel15.setText("Find");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 43, -1));

        tblUnpaidPO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "PO ID", "PR ID", "Supplier", "Item ", "Quantity", "Date", "Request By", "Approval", "Approve By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUnpaidPO.getTableHeader().setReorderingAllowed(false);
        tblUnpaidPO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUnpaidPOMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblUnpaidPO);
        tblUnpaidPO.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 785, 189));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setText("PO ID");

        jLabel18.setText("Supplier");

        jLabel19.setText("Item");

        jLabel20.setText("Date");

        jButton1.setText("Pay");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel21.setText("Approval");

        jLabel22.setText("Cost");

        jLabel23.setText("Quantity");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDatePayment, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtItemPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPOID, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtSupplierPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtApprovalPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel22)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCostPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel23)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtQuantityPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtPOID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtSupplierPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtItemPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtDatePayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtQuantityPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtApprovalPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtCostPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(43, 43, 43))
        );

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(823, 105, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("Payment Details");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(823, 61, -1, -1));

        btnDeletePayment.setText("Delete Payment");
        btnDeletePayment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeletePayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePaymentActionPerformed(evt);
            }
        });
        jPanel5.add(btnDeletePayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(681, 295, -1, -1));

        btnSearchPay.setText("Search");
        btnSearchPay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearchPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchPayActionPerformed(evt);
            }
        });
        jPanel5.add(btnSearchPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, -1, -1));

        tblPayment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Payment ID", "PO ID", "Supplier ID", "Item ID", "Quantity", "Unit Price", "Total Amount", "Payment Date", "Paid By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPayment.setColumnSelectionAllowed(true);
        tblPayment.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblPayment);
        tblPayment.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 324, 785, 204));

        btn1.addTab("$", jPanel5);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        btnGenerateReport.setText("Generate report");
        btnGenerateReport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerateReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateReportActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel24.setText("Generate Report");

        txtAreaReport.setColumns(20);
        txtAreaReport.setRows(5);
        jScrollPane5.setViewportView(txtAreaReport);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(dateChooserReport, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGenerateReport))
                    .addComponent(jLabel24))
                .addContainerGap(511, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGenerateReport)
                    .addComponent(dateChooserReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btn1.addTab("report", jPanel8);

        getContentPane().add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 1120, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPOActionPerformed
      btn1.setSelectedIndex(0);
    }//GEN-LAST:event_btnPOActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       btn1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        btn1.setSelectedIndex(2);
        loadUnpaidPOs();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadAllPOs();
        clearPOForm();
        loadAllPRs();
        loadUnpaidPOs();
        loadAllPayments();
        txtFindPO.setText(placeholderTextPO);
        txtFindPO.setForeground(Color.LIGHT_GRAY);
        txtFindPR.setText(placeholderTextPR);
        txtFindPR.setForeground(Color.LIGHT_GRAY);
        txtFindPay.setText(placeholderTextPayment);
        txtFindPay.setForeground(Color.LIGHT_GRAY);
        boxFilter.setSelectedIndex(0);
    }//GEN-LAST:event_btnRefreshActionPerformed

    
    
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Session.clear();
        System.out.println("User Logged Out: " + Session.getCurrentUser());
        this.dispose();
        new LoginFrame().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        btn1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnGenerateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateReportActionPerformed
        Calendar selectedDate = dateChooserReport.getCalendar();
        if (selectedDate != null)
        {
            System.out.println(selectedDate);
            String report = manager.generatePaymentReportByDate(selectedDate);
            txtAreaReport.setText(report);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please Select a Date", "Error No Date Selected", JOptionPane.WARNING_MESSAGE);
            
        }

    }//GEN-LAST:event_btnGenerateReportActionPerformed

    private void btnDeletePaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePaymentActionPerformed
        int selectedRow = tblPayment.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a payment to delete.");
            return;
        }

        String paymentID = tblPayment.getValueAt(selectedRow, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete Payment ID: " + paymentID + "?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            List<Payment> paymentList = manager.getAllPayments();
            boolean removed = paymentList.removeIf(p -> p.getPaymentID().equals(paymentID));

            if (removed) {
                manager.saveAllPaymentsToFile();
                loadAllPayments();
                loadUnpaidPOs();
                JOptionPane.showMessageDialog(this, "Payment deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Payment not found in memory.");
            }
        }
    }//GEN-LAST:event_btnDeletePaymentActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int selectedRow = tblUnpaidPO.getSelectedRow();
        System.out.println(selectedRow);
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a PO to pay.");
            return;
        }

        String poID = tblUnpaidPO.getValueAt(selectedRow, 0).toString();
        PO po = manager.getPOById(poID);

        if (po == null || !"APPROVED".equalsIgnoreCase(po.getApproval())) {
            JOptionPane.showMessageDialog(this, "PO must be approved before payment.");
            return;
        }

        String paymentID = manager.generateNextPaymentID();

        String supplierID = po.getSup().getSupplierID();
        String itemID = po.getItem().getItemID();
        int quantity = po.getQuantity();
        double price = po.getSup().getPrice();
        double total = quantity * price;
        String paidBy = Session.getCurrentUser().getEmployeeID();
        String paymentDate = LocalDate.now().toString();

        Payment payment = new Payment(
            paymentID, poID, supplierID, itemID,
            quantity, price, total, paymentDate, paidBy
        );

        manager.getAllPayments().add(payment);
        manager.saveAllPaymentsToFile();

        JOptionPane.showMessageDialog(this, "Payment recorded: " + total);
        loadAllPayments();
        loadUnpaidPOs();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblUnpaidPOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUnpaidPOMouseClicked
        int selectedRow = tblUnpaidPO.getSelectedRow();
        if (selectedRow >= 0){
            String poID = tblUnpaidPO.getValueAt(selectedRow, 0).toString(); //get POID
            PO selectedPO = manager.getPOById(poID);

            if (selectedPO != null){

                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "MY"));

                txtPOID.setText(selectedPO.getPoID());
                txtItemPayment.setText(selectedPO.getItem().getItemName());
                txtSupplierPayment.setText(selectedPO.getSup().getSupplierName());
                txtQuantityPayment.setText(String.valueOf(selectedPO.getQuantity()));
                txtApprovalPayment.setText(selectedPO.getApproval());
                txtDatePayment.setText(selectedPO.getDate().toString());

                Double Price = manager.getPriceByPOID(selectedPO.getPoID());
                int Quantity = selectedPO.getQuantity();
                double Cost = Quantity * Price;
                txtCostPayment.setText(currencyFormat.format(Cost));
            }

        }
    }//GEN-LAST:event_tblUnpaidPOMouseClicked

    private void txtFindPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindPayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindPayActionPerformed

    private void btnSearchPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchPRActionPerformed
        String prId = txtFindPR.getText().trim().toUpperCase();
        Calendar selectedDate = dateChooserPR.getCalendar();

        DefaultTableModel model = (DefaultTableModel) tblPR.getModel();

        if(prId.equals(placeholderTextPR.toUpperCase().trim()) )
        {
           if (selectedDate != null){
            LocalDate searchDate = selectedDate.toInstant()
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDate();
            List<PR> results = manager.filterPR_byDate(searchDate);

            if(results.isEmpty()){
                JOptionPane.showMessageDialog(this,"No PR found based on selected date. ");

            } else {
                for (PR pr : results){
                    model.setRowCount(0);
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
        }
           else {
                JOptionPane.showMessageDialog(this, "Please enter a PR ID!", "Error", JOptionPane.WARNING_MESSAGE);
           }
        }
        else if(!prId.isEmpty()){
                PR result = manager.getPRById(prId);
                if (result != null){
                    model.setRowCount(0);
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
            }
        else {
            JOptionPane.showMessageDialog(this,"Please enter a valid PR ID! or Date");
        }
    }//GEN-LAST:event_btnSearchPRActionPerformed

    private void txtFindPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindPRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindPRActionPerformed

    private void btnSearchPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchPOActionPerformed
        String poId = txtFindPO.getText().trim().toUpperCase();
        Calendar selectedDate = dateChooserPO.getCalendar();
        boxFilter.setSelectedIndex(0);

        DefaultTableModel model = (DefaultTableModel) tblPO.getModel();

        if(poId.equals(placeholderTextPO.toUpperCase()))
        {
            if (selectedDate != null){
                LocalDate searchDate = selectedDate.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();
                List<PO> results = manager.filterPO_byDate(searchDate);

                if(results.isEmpty()){
                    JOptionPane.showMessageDialog(this,"No PO found based on selected date. ");

                } else {
                    for (PO po : results){
                        model.setRowCount(0);
                        model.addRow(new Object[]{
                            po.getPoID(),
                            po.getPr().getPR_ID(),
                            po.getSup().getSupplierName(),
                            po.getItem().getItemName(),
                            po.getQuantity(),
                            po.getDate().toString(),
                            po.getRequestedBy(),
                            po.getApproval(),
                            po.getApprovalBy()
                        });
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Please enter a PO ID!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if(!poId.isEmpty()){

            PO po = manager.getPOById(poId);
            if (po != null){
                model.setRowCount(0);
                model.addRow(new Object[]{
                    po.getPoID(),
                    po.getPr().getPR_ID(),
                    po.getSup().getSupplierName(),
                    po.getItem().getItemName(),
                    po.getQuantity(),
                    po.getDate().toString(),
                    po.getRequestedBy(),
                    po.getApproval(),
                    po.getApprovalBy()
                });
            }else{
                JOptionPane.showMessageDialog(this, "No PO found based on the PO ID: " + poId);
                //                loadAllPOs();
            }
        }

        else {
            JOptionPane.showMessageDialog(this,"Please enter a valid PO ID or Select a Date!");
            //            loadAllPOs();
        }
    }//GEN-LAST:event_btnSearchPOActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

    private void txtPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPOActionPerformed

    private void txtStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStatusActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void txtItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemNameActionPerformed

    private void txtItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemIDActionPerformed

    private void txtSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupplierActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void boxFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxFilterActionPerformed
        // TODO add your handling code here:
        String filter = (String) boxFilter.getSelectedItem();
        DefaultTableModel model = (DefaultTableModel) tblPO.getModel();
        model.setRowCount(0); // clear table

        for (PO po : manager.viewAll()) {
            if (filter.equalsIgnoreCase("Clear") || po.getApproval().equalsIgnoreCase(filter)) {
                model.addRow(new Object[]{
                    po.getPoID(),
                    po.getPr().getPR_ID(),
                    po.getSup().getSupplierName(),
                    po.getItem().getItemName(),
                    po.getQuantity(),
                    po.getDate().toString(),
                    po.getPr().getRequestedBy(),
                    po.getApproval(),
                    po.getApprovalBy()
                });
            }
        }
    }//GEN-LAST:event_boxFilterActionPerformed

    private void txtFindPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindPOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindPOActionPerformed

    private void txtFindPOFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFindPOFocusLost

    }//GEN-LAST:event_txtFindPOFocusLost

    private void txtFindPOFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFindPOFocusGained

    }//GEN-LAST:event_txtFindPOFocusGained

    private void btnRejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejectActionPerformed
        if (txtPO.getText().isEmpty() || txtItemID.getText().isEmpty() || txtItemName.getText().isEmpty() ||
            txtQuantity.getText().isEmpty() || txtStatus.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please make sure all fields are filled in before recording the Purchase Order!");
            return;
        }

        //Gather inputs from text fields
        String poID = txtPO.getText().trim();
        String rejectBy = Session.getCurrentUser().getEmployeeID();

        manager.updatePOStatus(poID, "REJECTED", rejectBy);

        JOptionPane.showMessageDialog(this, "Purchase Order Rejected.");
        loadAllPOs();
        clearPOForm();
    }//GEN-LAST:event_btnRejectActionPerformed

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed
        // TODO add your handling code here:

        if (txtPO.getText().isEmpty() || txtItemID.getText().isEmpty() || txtItemName.getText().isEmpty() ||
            txtQuantity.getText().isEmpty() || txtStatus.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please make sure all fields are filled in before recording the Purchase Order!");
            return;
        }

        //Gather inputs from text fields
        String poID = txtPO.getText().trim();
        String approveBy = Session.getCurrentUser().getEmployeeID();

        manager.updatePOStatus(poID, "APPROVED", approveBy);

        JOptionPane.showMessageDialog(this, "Purchase Order Approve Successfully.");
        loadAllPOs();
        clearPOForm();
    }//GEN-LAST:event_btnApproveActionPerformed

    private void tblPOCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tblPOCaretPositionChanged
        // TODO ad your handling code here:
    }//GEN-LAST:event_tblPOCaretPositionChanged

    private void tblPOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPOMouseClicked
        int selectedRow = tblPO.getSelectedRow();
        if (selectedRow >= 0){
            String poID = tblPO.getValueAt(selectedRow, 0).toString(); //get POID
            PO selectedPO = manager.getPOById(poID);

            if (selectedPO != null){
                //Auto-fill fields

                txtPO.setText(selectedPO.getPoID());
                txtItemID.setText(selectedPO.getItem().getItemID());
                txtItemName.setText(selectedPO.getItem().getItemName());
                txtQuantity.setText(String.valueOf(selectedPO.getQuantity()));
                txtSupplier.setText(selectedPO.getSup().getSupplierName());
                txtStatus.setText(selectedPO.getApproval());

                Double Price = manager.getPriceByPOID(selectedPO.getPoID());
                int Quantity = selectedPO.getQuantity();
                double Cost = Quantity * Price;
                txtPrice.setText(String.valueOf(Cost));
            }

        }
    }//GEN-LAST:event_tblPOMouseClicked

    private void tblPOComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tblPOComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPOComponentAdded

    private void btnSearchPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchPayActionPerformed
        String poId = txtFindPay.getText().trim().toUpperCase();
        
        DefaultTableModel model = (DefaultTableModel) tblUnpaidPO.getModel();
            
        if(!poId.isEmpty())
        {
            
            if(poId.equals(placeholderTextPayment.toUpperCase().trim()) )
            {
                JOptionPane.showMessageDialog(this, "Please Enter a Payment ID ");
            }
            else
            {
                PO po = manager.getPOById(poId);
                if (po != null)
                {
                 model.setRowCount(0);
                 model.addRow(new Object[]{
                     po.getPoID(),
                     po.getPr().getPR_ID(),
                     po.getSup().getSupplierName(),
                     po.getItem().getItemName(),
                     po.getQuantity(),
                     po.getDate().toString(),
                     po.getRequestedBy(),
                     po.getApproval(),
                     po.getApprovalBy()
                 });
                }
                
                else{
                    JOptionPane.showMessageDialog(this, "No PO found based on the PO ID: " + poId);

                }
            }
        }
        
           else {
                JOptionPane.showMessageDialog(this, "Please enter a PR ID!", "Error", JOptionPane.WARNING_MESSAGE);
           }
        
    }//GEN-LAST:event_btnSearchPayActionPerformed

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
//            java.util.logging.Logger.getLogger(FinanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FinanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FinanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FinanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> {
//            new FinanceFrame().setVisible(true);
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxFilter;
    private javax.swing.JTabbedPane btn1;
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton btnDeletePayment;
    private javax.swing.JButton btnGenerateReport;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPO;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnReject;
    private javax.swing.JButton btnSearchPO;
    private javax.swing.JButton btnSearchPR;
    private javax.swing.JButton btnSearchPay;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private com.toedter.calendar.JDateChooser dateChooserPO;
    private com.toedter.calendar.JDateChooser dateChooserPR;
    private com.toedter.calendar.JDateChooser dateChooserReport;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblGreeting;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JTable tblPO;
    private javax.swing.JTable tblPR;
    private javax.swing.JTable tblPayment;
    private javax.swing.JTable tblUnpaidPO;
    private javax.swing.JTextField txtApprovalPayment;
    private javax.swing.JTextArea txtAreaReport;
    private javax.swing.JTextField txtCostPayment;
    private javax.swing.JTextField txtDatePayment;
    private javax.swing.JTextField txtFindPO;
    private javax.swing.JTextField txtFindPR;
    private javax.swing.JTextField txtFindPay;
    private javax.swing.JTextField txtItemID;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtItemPayment;
    private javax.swing.JTextField txtPO;
    private javax.swing.JTextField txtPOID;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtQuantityPayment;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtSupplier;
    private javax.swing.JTextField txtSupplierPayment;
    // End of variables declaration//GEN-END:variables
}

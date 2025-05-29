/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.omega;
import com.mycompany.omega.classes.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author adham
 */
public class SMFrame extends javax.swing.JFrame {
    private SalesManager manager;
    private boolean isEditMode = false;
    private int oriquantity;
    /**
     * Creates new form SMFrame
     */
    public SMFrame(Employee user) {
        initComponents();
        
        if (user instanceof SalesManager) {
            this.manager = (SalesManager) user;  // Safe cast
        } else if (user.getRole() == Employee.Role.ADMINISTRATOR) {
            // Optional: Admins can access view-only or limited version
            this.manager = new SalesManager(user.getEmployeeID(), user.getName(), user.getRole(), user.getEmail(), user.getPassword());
        } else {
            JOptionPane.showMessageDialog(this, "Access Denied. You are not authorized to access this page.");
            dispose();
            return;
        }
        
//        this.manager = (SalesManager) user;
        
        System.out.println("Session User: " + Session.getCurrentUser());
        System.out.println("Manager assigned? " + (manager != null));
        
        
        loadAllItems();
        loadAllSuppliers();
        loadAllSales();
        loadAllPRs();
        loadAllPOs();
        loadLowStockItems();
        LoadSupplierinCombo(supfiltercmb);
        LoadIteminCombo(itemfiltercmb);
        lblwelcome.setText("Welcome, " + manager.getRole() + ": " + manager.getName());
    }
    
    private void loadAllItems(){
        DefaultTableModel model = (DefaultTableModel) ItemTable.getModel();
        model.setRowCount(0); 
        
        for(Item i: manager.getAllItems()){
            model.addRow(new Object[]{
                i.getItemID(),
                i.getItemName(),
                i.getStock()
            });
        }
        System.out.println("Items loaded: " + manager.getAllItems().size());
    }
    
    private void loadAllSuppliers(){
        DefaultTableModel model = (DefaultTableModel) SupplierTable.getModel();
        model.setRowCount(0); 
        
        for(Supplier sup: manager.getAllSuppliers()){
            model.addRow(new Object[]{
                sup.getSupplierID(),
                sup.getItem().getItemID(),
                sup.getSupplierName(),
                sup.getContact(),
                sup.getPrice()
            });
        }
        System.out.println("Supplier loaded: " + manager.getAllSuppliers().size());
    }
    
        private void loadAllSales(){
        DefaultTableModel model = (DefaultTableModel) SaleTable.getModel();
        model.setRowCount(0); 
        
        for(Sales sale: manager.getAllSales()){
            model.addRow(new Object[]{
                sale.getSaleID(),
                sale.getItem().getItemID(),
                sale.getQuantity(),
                sale.getDate().toString()
            });
        }
        System.out.println("Sales loaded: " + manager.getAllSales().size());
    }
    
        private void loadAllPRs(){
        DefaultTableModel model = (DefaultTableModel) PrTable.getModel();
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
        System.out.println("PRs loaded: " + manager.getAllPRs().size());
    }
        
    private void loadAllPOs(){
        DefaultTableModel model = (DefaultTableModel) PoTable.getModel();
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
        System.out.println("POs loaded: " + manager.viewAll().size());
    }
    
    private void loadLowStockItems(){
        DefaultTableModel model = (DefaultTableModel) lowStockTable.getModel();
        model.setRowCount(0); 
        
        for(Item i: manager.getLowStockItem(20)){
            model.addRow(new Object[]{
                i.getItemID(),
                i.getItemName(),
                i.getStock()
            });
        }
        System.out.println("Low Stock Items loaded: " + manager.getLowStockItem(20).size());
    }
    
    private String TodayDate(){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = today.format(formatter);
        return formattedDate;
    }
    
    private void LoadIteminCombo(JComboBox<String> combo) {
      List<Item> ItemList = this.manager.getAllItems();
      combo.removeAllItems();
      combo.addItem("--- Select Item ID ---");
      for (Item item : ItemList){
         combo.addItem(item.getItemID());
      }

   }
      
    private void LoadSupplierinCombo(JComboBox<String> combo) {
      List<Supplier> SupList = this.manager.getAllSuppliers();
      Set<String> uniqueNames = new HashSet<>();
      
      combo.removeAllItems();
      combo.addItem("--- Select Supplier ---");
      for (Supplier sup : SupList){
        String name = sup.getSupplierName();
        if (uniqueNames.add(name)){
         combo.addItem(sup.getSupplierName());
        }
    }
   }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        prmenu = new javax.swing.JFrame();
        jPanel7 = new javax.swing.JPanel();
        prlabel = new javax.swing.JLabel();
        saveprbtn = new javax.swing.JButton();
        pridfld = new javax.swing.JTextField();
        itemidcmb = new javax.swing.JComboBox<>();
        prquantityfld = new javax.swing.JTextField();
        prrequestfld = new javax.swing.JTextField();
        prdatefld = new javax.swing.JTextField();
        prstatusfld = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        logoutbtn = new javax.swing.JButton();
        refreshbtn = new javax.swing.JButton();
        lblwelcome = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ItemTable = new javax.swing.JTable();
        itemsearchfld = new javax.swing.JTextField();
        itemsearchbtn = new javax.swing.JButton();
        itemclearbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        itemidfld = new javax.swing.JTextField();
        itemnamefld = new javax.swing.JTextField();
        itemstockfld = new javax.swing.JTextField();
        itemaddbtn = new javax.swing.JButton();
        itemeditbtn = new javax.swing.JButton();
        itemdeletebtn = new javax.swing.JButton();
        itemsavebtn = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        supitemtable = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        SupplierTable = new javax.swing.JTable();
        supclearbtn = new javax.swing.JButton();
        supsearchbtn = new javax.swing.JButton();
        supaddbtn = new javax.swing.JButton();
        supeditbtn = new javax.swing.JButton();
        supdeletebtn = new javax.swing.JButton();
        savesupbtn = new javax.swing.JButton();
        supidfld = new javax.swing.JTextField();
        supnamefld = new javax.swing.JTextField();
        supitemidcmb = new javax.swing.JComboBox<>();
        supcontactfld = new javax.swing.JTextField();
        unitpricefld = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Contact = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        itemfiltercmb = new javax.swing.JComboBox<>();
        supfiltercmb = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        SaleTable = new javax.swing.JTable();
        saleaddbtn = new javax.swing.JButton();
        saleeditbtn = new javax.swing.JButton();
        saledeletebtn = new javax.swing.JButton();
        salesavebtn = new javax.swing.JButton();
        salesidfld = new javax.swing.JTextField();
        salesquantityfld = new javax.swing.JTextField();
        salesdatefld = new javax.swing.JTextField();
        salesrecordfld = new javax.swing.JTextField();
        salesitemidcmb = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        saleclearbtn = new javax.swing.JButton();
        salesearchbtn = new javax.swing.JButton();
        salesearchfld = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        PrTable = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        itemprtable = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        lowStockTable = new javax.swing.JTable();
        prclearbtn = new javax.swing.JButton();
        prsearchbtn = new javax.swing.JButton();
        praddbtn = new javax.swing.JButton();
        preditbtn = new javax.swing.JButton();
        prdeletebtn = new javax.swing.JButton();
        prsearchfld = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        PoTable = new javax.swing.JTable();
        poclearbtn = new javax.swing.JButton();
        posearchbtn = new javax.swing.JButton();
        posearchfld = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();

        prmenu.setBackground(new java.awt.Color(0, 153, 153));

        jPanel7.setBackground(new java.awt.Color(0, 153, 153));

        prlabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        prlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prlabel.setText("GENERATE PURCHASE REQUISITION");

        saveprbtn.setText("Generate");
        saveprbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveprbtnActionPerformed(evt);
            }
        });

        prdatefld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prdatefldActionPerformed(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Item ID:");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("PR ID:");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Quantity:");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Requested By:");

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Request Date:");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Status:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(prlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pridfld)
                            .addComponent(itemidcmb, 0, 288, Short.MAX_VALUE)
                            .addComponent(prquantityfld)
                            .addComponent(prrequestfld)
                            .addComponent(prdatefld)
                            .addComponent(prstatusfld))))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(saveprbtn)
                .addGap(26, 26, 26))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(prlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pridfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemidcmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prquantityfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prrequestfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prdatefld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prstatusfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(saveprbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout prmenuLayout = new javax.swing.GroupLayout(prmenu.getContentPane());
        prmenu.getContentPane().setLayout(prmenuLayout);
        prmenuLayout.setHorizontalGroup(
            prmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        prmenuLayout.setVerticalGroup(
            prmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("ITEM PR DETAILS");
        jLabel27.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        logoutbtn.setText("logout");
        logoutbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbtnActionPerformed(evt);
            }
        });

        refreshbtn.setText("refresh");
        refreshbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshbtnActionPerformed(evt);
            }
        });

        lblwelcome.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        lblwelcome.setText("Welcome, SALES MANAGER");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblwelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refreshbtn)
                .addGap(39, 39, 39)
                .addComponent(logoutbtn)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblwelcome)
                    .addComponent(refreshbtn)
                    .addComponent(logoutbtn))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(0, 153, 153));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ItemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Item Name", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ItemTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ItemTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ItemTable);
        if (ItemTable.getColumnModel().getColumnCount() > 0) {
            ItemTable.getColumnModel().getColumn(0).setResizable(false);
            ItemTable.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 82, 599, 380));
        jPanel2.add(itemsearchfld, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 207, -1));

        itemsearchbtn.setText("search");
        itemsearchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemsearchbtnActionPerformed(evt);
            }
        });
        jPanel2.add(itemsearchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, -1, -1));

        itemclearbtn.setText("clear");
        itemclearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemclearbtnActionPerformed(evt);
            }
        });
        jPanel2.add(itemclearbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, -1, -1));

        jLabel1.setText("Item ID:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, 50, 20));

        jLabel2.setText("Item Name:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, -1, 20));

        jLabel3.setText("Item Stock:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 190, -1, -1));

        itemidfld.setEditable(false);
        itemidfld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemidfldActionPerformed(evt);
            }
        });
        jPanel2.add(itemidfld, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 130, 290, -1));

        itemnamefld.setEditable(false);
        itemnamefld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemnamefldActionPerformed(evt);
            }
        });
        jPanel2.add(itemnamefld, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 290, -1));

        itemstockfld.setEditable(false);
        jPanel2.add(itemstockfld, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, 290, -1));

        itemaddbtn.setText("Add");
        itemaddbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemaddbtnActionPerformed(evt);
            }
        });
        jPanel2.add(itemaddbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 380, -1, -1));

        itemeditbtn.setText("Edit");
        itemeditbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemeditbtnActionPerformed(evt);
            }
        });
        jPanel2.add(itemeditbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 380, -1, -1));

        itemdeletebtn.setText("Delete");
        itemdeletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemdeletebtnActionPerformed(evt);
            }
        });
        jPanel2.add(itemdeletebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 380, -1, -1));

        itemsavebtn.setText("Save");
        itemsavebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemsavebtnActionPerformed(evt);
            }
        });
        jPanel2.add(itemsavebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 420, 91, 30));
        itemsavebtn.setVisible(false);

        supitemtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier ID", "Supplier Name", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(supitemtable);

        jPanel2.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 220, 290, 152));

        jLabel24.setBackground(new java.awt.Color(0, 0, 0));
        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("ITEM ENTRY");
        jLabel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 189, -1));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("ITEM DETAILS");
        jLabel28.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 350, -1));

        jLabel29.setText("Search By Item ID:");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, 20));

        jTabbedPane1.addTab("Item Entry", jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SupplierTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier ID", "Item ID", "Supplier Name", "Contact", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SupplierTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SupplierTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(SupplierTable);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 79, 624, 390));

        supclearbtn.setText("Clear");
        supclearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supclearbtnActionPerformed(evt);
            }
        });
        jPanel3.add(supclearbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, -1, -1));

        supsearchbtn.setText("Search");
        supsearchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supsearchbtnActionPerformed(evt);
            }
        });
        jPanel3.add(supsearchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, -1, -1));

        supaddbtn.setText("Add");
        supaddbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supaddbtnActionPerformed(evt);
            }
        });
        jPanel3.add(supaddbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 350, -1, -1));

        supeditbtn.setText("Edit");
        supeditbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supeditbtnActionPerformed(evt);
            }
        });
        jPanel3.add(supeditbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 350, -1, -1));

        supdeletebtn.setText("Delete");
        supdeletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supdeletebtnActionPerformed(evt);
            }
        });
        jPanel3.add(supdeletebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 350, -1, -1));

        savesupbtn.setText("Save");
        savesupbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savesupbtnActionPerformed(evt);
            }
        });
        jPanel3.add(savesupbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 390, 101, 40));
        savesupbtn.setVisible(false);

        supidfld.setEditable(false);
        jPanel3.add(supidfld, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 150, 270, -1));

        supnamefld.setEditable(false);
        jPanel3.add(supnamefld, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 230, 270, -1));

        jPanel3.add(supitemidcmb, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 190, 270, -1));

        supcontactfld.setEditable(false);
        jPanel3.add(supcontactfld, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 270, 270, -1));

        unitpricefld.setEditable(false);
        jPanel3.add(unitpricefld, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 310, 270, -1));

        jLabel4.setText("Supplier ID:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 160, -1, -1));

        jLabel5.setText("Supplier Name:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, -1, -1));

        jLabel6.setText("Item ID:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 200, -1, -1));

        jLabel7.setText("Unit Price:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 320, -1, -1));

        Contact.setText("Contact:");
        jPanel3.add(Contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 280, -1, -1));

        jLabel23.setBackground(new java.awt.Color(0, 0, 0));
        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("SUPPLIER ENTRY");
        jLabel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 189, -1));

        jPanel3.add(itemfiltercmb, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 120, -1));

        jPanel3.add(supfiltercmb, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 130, -1));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("SUPPLIER DETAILS");
        jLabel30.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 110, 350, -1));

        jLabel32.setText("Filter By:");
        jPanel3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, 20));

        jLabel33.setText("Supplier Name");
        jPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jLabel34.setText("Item ID");
        jPanel3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        jTabbedPane1.addTab("Supplier Entry", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SaleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sale ID", "Item ID", "Quantity", "Sale Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SaleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaleTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(SaleTable);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 90, 622, 380));

        saleaddbtn.setText("Add");
        saleaddbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleaddbtnActionPerformed(evt);
            }
        });
        jPanel4.add(saleaddbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 350, -1, -1));

        saleeditbtn.setText("Edit");
        saleeditbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleeditbtnActionPerformed(evt);
            }
        });
        jPanel4.add(saleeditbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 350, -1, -1));

        saledeletebtn.setText("Delete");
        saledeletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saledeletebtnActionPerformed(evt);
            }
        });
        jPanel4.add(saledeletebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 350, -1, -1));

        salesavebtn.setText("Save");
        salesavebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesavebtnActionPerformed(evt);
            }
        });
        jPanel4.add(salesavebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 390, 103, 33));
        salesavebtn.setVisible(false);

        salesidfld.setEditable(false);
        salesidfld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesidfldActionPerformed(evt);
            }
        });
        jPanel4.add(salesidfld, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, 260, -1));

        salesquantityfld.setEditable(false);
        salesquantityfld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesquantityfldActionPerformed(evt);
            }
        });
        jPanel4.add(salesquantityfld, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 230, 260, -1));

        salesdatefld.setEditable(false);
        jPanel4.add(salesdatefld, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 270, 260, -1));

        salesrecordfld.setEditable(false);
        salesrecordfld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesrecordfldActionPerformed(evt);
            }
        });
        jPanel4.add(salesrecordfld, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 310, 260, -1));

        salesitemidcmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesitemidcmbActionPerformed(evt);
            }
        });
        jPanel4.add(salesitemidcmb, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 190, 260, -1));

        jLabel8.setText("Item ID:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 190, -1, -1));

        jLabel9.setText("Sales ID:");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 150, -1, -1));

        jLabel10.setText("Quantity:");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 230, -1, -1));

        jLabel11.setText("Sales Date:");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 270, -1, -1));

        jLabel12.setText("Recorded By:");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 310, -1, -1));

        saleclearbtn.setText("Clear");
        saleclearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleclearbtnActionPerformed(evt);
            }
        });
        jPanel4.add(saleclearbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, -1, -1));

        salesearchbtn.setText("Search");
        salesearchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesearchbtnActionPerformed(evt);
            }
        });
        jPanel4.add(salesearchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, -1, -1));
        jPanel4.add(salesearchfld, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 168, -1));

        jLabel22.setBackground(new java.awt.Color(0, 0, 0));
        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("SALES ENTRY");
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 259, -1));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("SALE DETAILS");
        jLabel31.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 100, 330, -1));

        jLabel36.setText("Search By Sales ID:");
        jPanel4.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, 20));

        jTabbedPane1.addTab("Sales Entry", jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setPreferredSize(new java.awt.Dimension(1253, 620));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PrTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PR ID", "Item ID", "Quantity", "Requested Date", "Requested By", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        PrTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(PrTable);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 100, 710, 320));

        itemprtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Item Name", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(itemprtable);

        jPanel5.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 130, 272, 106));

        lowStockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Item Name", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lowStockTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lowStockTableMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(lowStockTable);

        jPanel5.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 300, 272, 140));

        prclearbtn.setText("Clear");
        prclearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prclearbtnActionPerformed(evt);
            }
        });
        jPanel5.add(prclearbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, -1, -1));

        prsearchbtn.setText("Search");
        prsearchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prsearchbtnActionPerformed(evt);
            }
        });
        jPanel5.add(prsearchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, -1, -1));

        praddbtn.setText("Add");
        praddbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                praddbtnActionPerformed(evt);
            }
        });
        jPanel5.add(praddbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 430, -1, -1));

        preditbtn.setText("Edit");
        preditbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preditbtnActionPerformed(evt);
            }
        });
        jPanel5.add(preditbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 430, -1, -1));

        prdeletebtn.setText("Delete");
        prdeletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prdeletebtnActionPerformed(evt);
            }
        });
        jPanel5.add(prdeletebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 430, -1, -1));
        jPanel5.add(prsearchfld, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 215, -1));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("ITEM PR DETAILS");
        jLabel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, 272, -1));

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("PURCHASE REQUISITION ENTRY");
        jLabel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 6, 353, 49));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 51, 51));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("LOW STOCK ITEM ALERT");
        jLabel25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 270, 272, -1));

        jLabel26.setText("Search By PR ID:");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, 20));

        jTabbedPane1.addTab("Purchase Requisition", jPanel5);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PoTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(PoTable);

        jPanel6.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 1030, 390));

        poclearbtn.setText("Clear");
        poclearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poclearbtnActionPerformed(evt);
            }
        });
        jPanel6.add(poclearbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, -1, -1));

        posearchbtn.setText("Search");
        posearchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                posearchbtnActionPerformed(evt);
            }
        });
        jPanel6.add(posearchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 40, -1, -1));
        jPanel6.add(posearchfld, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, 190, -1));

        jLabel21.setBackground(new java.awt.Color(0, 0, 0));
        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("PURCHASE ORDER DISPLAY");
        jLabel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        jPanel6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 353, 47));

        jLabel35.setText("Search By PO ID:");
        jPanel6.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, -1, 20));

        jTabbedPane1.addTab("Purchase Order", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1251, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutbtnActionPerformed
        JOptionPane.showMessageDialog(null, "Logout succesfully!");
        Session.clear();
        System.out.println("User Logged Out: " + Session.getCurrentUser());
        this.dispose();
        new LoginFrame().setVisible(true);
    }//GEN-LAST:event_logoutbtnActionPerformed

    private void itemidfldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemidfldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemidfldActionPerformed

    private void itemnamefldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemnamefldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemnamefldActionPerformed

    private void itemaddbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemaddbtnActionPerformed
      isEditMode = false;
      itemidfld.setEditable(true);
      itemnamefld.setEditable(true);
      itemstockfld.setEditable(true);
      itemidfld.setText(FileHandler.getNextID("data/Items.txt", "IT"));
      itemnamefld.setText("");
      itemstockfld.setText("");
      DefaultTableModel model = (DefaultTableModel)supitemtable.getModel();
      model.setRowCount(0);
      itemsavebtn.setVisible(true);
    }//GEN-LAST:event_itemaddbtnActionPerformed

    private void itemeditbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemeditbtnActionPerformed
      isEditMode = true;
      itemnamefld.setEditable(true);
      itemstockfld.setEditable(true);
      itemsavebtn.setVisible(true);   
    }//GEN-LAST:event_itemeditbtnActionPerformed

    private void itemdeletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemdeletebtnActionPerformed
      itemidfld.setText("");
      itemnamefld.setText("");
      itemstockfld.setText("");
      int selectedRow = ItemTable.getSelectedRow();
      if (selectedRow == -1) {
         JOptionPane.showMessageDialog(null, "Please select an item to delete.");
      }

      String selectedItemId = this.ItemTable.getValueAt(selectedRow, 0).toString();
      if (this.manager.removeItem(selectedItemId)) {
         JOptionPane.showMessageDialog(null, "Items is sucessfully removed!.");
      } else {
         JOptionPane.showMessageDialog(null, "Items cannot be removed! Please try again..");
      }

      loadAllItems();
    }//GEN-LAST:event_itemdeletebtnActionPerformed

    private void itemsavebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemsavebtnActionPerformed
        String id = itemidfld.getText().trim();
      String name = itemnamefld.getText().trim();
      String stock = itemstockfld.getText().trim();
      if (id.isEmpty() || name.isEmpty() || stock.isEmpty()) {
         JOptionPane.showMessageDialog(null, "Please fill all fields.");
      }

      try {
         int Stock = Integer.parseInt(stock);
         Item newItem = new Item(id, name, Stock);
         if (!this.isEditMode) {
            FileHandler.appendLine("data/Items.txt", newItem.toString());
            JOptionPane.showMessageDialog(null, "Item added succesfully!");
         } else {
            List<Item> itemList = this.manager.getAllItems();
            for (Item item : itemList){
               if (item.getItemID().equals(id)) {
                  item.setItemName(name);
                  item.setStock(Stock);
                  break;
               }
            }

            FileHandler.writeAllToFile("data/Items.txt", itemList);
            JOptionPane.showMessageDialog(null, "Item editted succesfully!");
         }
      } catch (NumberFormatException var10) {
         JOptionPane.showMessageDialog(null, "Stock must be a number.");
      }

      itemidfld.setText("");
      itemnamefld.setText("");
      itemstockfld.setText("");
      itemidfld.setEditable(false);
      itemnamefld.setEditable(false);
      itemstockfld.setEditable(false);
      loadAllItems();
      loadLowStockItems();
      itemsavebtn.setVisible(false);
    }//GEN-LAST:event_itemsavebtnActionPerformed

    private void itemclearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemclearbtnActionPerformed
        itemsearchfld.setText("");
        loadAllItems();
    }//GEN-LAST:event_itemclearbtnActionPerformed

    private void itemsearchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemsearchbtnActionPerformed
        String itemid = itemsearchfld.getText().trim();
        DefaultTableModel model = (DefaultTableModel) ItemTable.getModel();
        model.setRowCount(0);
        
        if (!itemid.isEmpty()){
            Item result = manager.getItemById(itemid);
            if (result != null){
                model.addRow(new Object[]{
                   result.getItemID(),
                   result.getItemName(),
                   result.getStock()
                }); 
            } else {
                JOptionPane.showMessageDialog(null,"No Item found based on the Item ID: " + itemid);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a valid Items ID!");
            }
        
    }//GEN-LAST:event_itemsearchbtnActionPerformed

    private void ItemTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ItemTableMouseClicked
    int selectedRow = ItemTable.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) supitemtable.getModel();
    model.setRowCount(0);
    if (selectedRow != -1) {
    String itemId = this. ItemTable.getValueAt(selectedRow, 0).toString();
    Item selectedItem = this.manager.getItemById(itemId);
    List<Supplier> supplierList = this.manager.getAllSuppliers();
    List<Supplier> matchedSupplier = (List) supplierList.stream()
                                    .filter(s -> s.getItem().getItemID().equals(itemId))
                                    .collect(Collectors.toList());
    
    for (Supplier s : matchedSupplier){
    model.addRow(new Object[]{
        s.getSupplierID(),
        s.getSupplierName(),
        String. format("%.2f",s.getPrice())
    });
    }
          
    if (selectedItem != null) {
    this. itemidfld.setText(selectedItem.getItemID());
    this. itemnamefld.setText(selectedItem.getItemName());
    this. itemstockfld.setText(String.valueOf(selectedItem.getStock()));
        }
    }
    }//GEN-LAST:event_ItemTableMouseClicked

    private void supdeletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supdeletebtnActionPerformed
      supidfld.setText("");
      supitemidcmb.removeAllItems();
      LoadIteminCombo(this.supitemidcmb);
      supnamefld.setText("");
      supcontactfld.setText("");
      unitpricefld.setText("");
      int selectedRow = this.SupplierTable.getSelectedRow();
      if (selectedRow == -1) {
         JOptionPane.showMessageDialog(null, "Please select a supplier to delete.");
      }

      String selectedSupplierId = this.SupplierTable.getValueAt(selectedRow, 0).toString();
      if (this.manager.removeSupplier(selectedSupplierId)) {
         JOptionPane.showMessageDialog(null, "Supplier is sucessfully removed!.");
      } else {
         JOptionPane.showMessageDialog(null, "Supplier cannot be removed! Please try again.");
      }

      this.loadAllSuppliers();
    }//GEN-LAST:event_supdeletebtnActionPerformed

    private void supaddbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supaddbtnActionPerformed
      isEditMode = false;
      supidfld.setEditable(true);
      supnamefld.setEditable(true);
      supcontactfld.setEditable(true);
      unitpricefld.setEditable(true);
      supitemidcmb.removeAllItems();
      LoadIteminCombo(this.supitemidcmb);
      supitemidcmb.setEnabled(true);
      supidfld.setText(FileHandler.getNextID("data/Supplier.txt", "SUP"));
      supnamefld.setText("");
      supcontactfld.setText("");
      unitpricefld.setText("");
      savesupbtn.setVisible(true);
    }//GEN-LAST:event_supaddbtnActionPerformed

    private void supeditbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supeditbtnActionPerformed
      isEditMode = true;
      supnamefld.setEditable(true);
      supcontactfld.setEditable(true);
      unitpricefld.setEditable(true);
      supitemidcmb.setEnabled(true);
      savesupbtn.setVisible(true);
      
//      supnamefld.setText(supidfld.getText().trim());
//      supcontactfld.setText(supnamefld.getText().trim());
//      unitpricefld.setText(true);
//      supitemidcmb.setText(true);
    }//GEN-LAST:event_supeditbtnActionPerformed

    private void savesupbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savesupbtnActionPerformed
    String id = supidfld.getText().trim();
    String name = supnamefld.getText().trim();
    String contact = supcontactfld.getText().trim();
    String itemid = (String) supitemidcmb.getSelectedItem();
    String price = unitpricefld.getText().trim();
      
      if (id.isEmpty() || name.isEmpty() || price.isEmpty() || itemid.isEmpty() || price.isEmpty()) {
         JOptionPane.showMessageDialog(null, "Please fill all fields.");
      }

      try {
         float Price = Float.parseFloat(price);
         Item matchedItem = (Item)manager.getAllItems().stream()
                            .filter(i -> i.getItemID().equals(itemid))
                            .findFirst().orElse(null);
         if (matchedItem == null) {
            JOptionPane.showMessageDialog(null, "Invalid Item ID!");
            return;
         }

         boolean found;
         if (!this.isEditMode) {
            found = this.manager.getAllSuppliers().stream().anyMatch((sx) -> {
               return sx.getSupplierID().equals(id);
            });
            if (found) {
               JOptionPane.showMessageDialog(null, "Supplier ID already exists!");
               return;
            }

            String linetowrite = id + "," + name + "," + contact + "," + matchedItem.getItemID() + "," + Price;
            FileHandler.appendLine("data/Supplier.txt", linetowrite);
            JOptionPane.showMessageDialog(null, "Supplier added succesfully!");
         } else {
            found = false;
            List<Supplier> supList = this.manager.getAllSuppliers();
            
            for (Supplier s : supList){
               if (s.getSupplierID().equals(id)) {
                  s.setSupplierName(name);
                  s.setContact(contact);
                  s.setPrice(Price);
                  found = true;
                  break;
               }
            }

            if (!found) {
               JOptionPane.showMessageDialog(null, "Supplier not found!");
               return;
            }

            System.out.println(supList);
            FileHandler.writeAllToFile("data/Supplier.txt", supList);
            JOptionPane.showMessageDialog(null, "Supplier editted succesfully!");
         }

         supidfld.setText("");
         supitemidcmb.removeAllItems();
         supnamefld.setText("");
         supcontactfld.setText("");
         unitpricefld.setText("");
         supnamefld.setEditable(false);
         supcontactfld.setEditable(false);
         unitpricefld.setEditable(false);
         loadAllSuppliers();
         savesupbtn.setVisible(false);
      } catch (NumberFormatException e) {
         JOptionPane.showMessageDialog(null, "Price must be a number.");
      }
    }//GEN-LAST:event_savesupbtnActionPerformed

    private void SupplierTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupplierTableMouseClicked
        int selectedRow = SupplierTable.getSelectedRow();
        supitemidcmb.removeAllItems();
        LoadIteminCombo(supitemidcmb);
        supitemidcmb.setEnabled(false);
       
        if (selectedRow != -1) {
         String supplierid = SupplierTable.getValueAt(selectedRow, 0).toString();
         String itemid = SupplierTable.getValueAt(selectedRow, 1).toString();
         Supplier selectedsupplier = manager.getSupplierById(supplierid);
         double price = selectedsupplier.getPrice();
         String Price = String.format("%.2f", price);
         if (selectedsupplier != null) {
            supidfld.setText(selectedsupplier.getSupplierID());
            supitemidcmb.setSelectedItem(itemid);
            supnamefld.setText(selectedsupplier.getSupplierName());
            supcontactfld.setText(String.valueOf(selectedsupplier.getContact()));
            unitpricefld.setText(Price);
         }
      }
    }//GEN-LAST:event_SupplierTableMouseClicked

    private void supsearchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supsearchbtnActionPerformed
String itemId = itemfiltercmb.getSelectedItem().toString().trim();
      String SupplierName = supfiltercmb.getSelectedItem().toString().trim();
      DefaultTableModel model = (DefaultTableModel)SupplierTable.getModel();
      model.setRowCount(0);
      
      if (itemId.equals("--- Select Item ID ---")){
          //treat as null
      }
      
       if (SupplierName.equals("--- Select Supplier ---")){
          //treat as null
      }
      
      for (Supplier s : manager.getAllSuppliers()){
      boolean matchItem = true;
      boolean matchSupplier =true;
      
      
      if (itemId != null && !itemId.isEmpty()){
          matchItem = s.getItem().getItemID().equalsIgnoreCase(itemId);
      }
      
      if (SupplierName != null && !SupplierName.isEmpty()){
         matchSupplier =s.getSupplierName().equalsIgnoreCase(SupplierName);
      }
      
      if (matchItem || matchSupplier){
          model.addRow(new Object[]{
                s.getSupplierID(), 
                s.getItem().getItemID(),
                s.getSupplierName(),
                s.getContact(), 
                s.getPrice()
          });
        }
      } 
    }//GEN-LAST:event_supsearchbtnActionPerformed

    private void supclearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supclearbtnActionPerformed
       LoadIteminCombo(itemfiltercmb);
       LoadSupplierinCombo(supfiltercmb);
       
       itemfiltercmb.setSelectedIndex(0);
       supfiltercmb.setSelectedIndex(0);
       loadAllSuppliers();
       loadAllSuppliers();
    }//GEN-LAST:event_supclearbtnActionPerformed

    private void SaleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaleTableMouseClicked
      int selectedRow = SaleTable.getSelectedRow();
      salesitemidcmb.removeAllItems();
      LoadIteminCombo(salesitemidcmb);
      salesitemidcmb.setEnabled(false);
      if (selectedRow != -1) {
         String saleid = SaleTable.getValueAt(selectedRow, 0).toString();
         String itemid = SaleTable.getValueAt(selectedRow, 1).toString();
         String Quantity = SaleTable.getValueAt(selectedRow, 2).toString();
         Sales selectedsales = manager.getSalesById(saleid);
         if (selectedsales != null) {
            salesidfld.setText(selectedsales.getSaleID());
            salesitemidcmb.setSelectedItem(itemid);
            salesquantityfld.setText(Quantity);
            salesdatefld.setText(selectedsales.getDate().toString());
            salesrecordfld.setText(selectedsales.getRecordedBy());
         }
      }
    }//GEN-LAST:event_SaleTableMouseClicked

    private void salesitemidcmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesitemidcmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salesitemidcmbActionPerformed

    private void salesquantityfldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesquantityfldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salesquantityfldActionPerformed

    private void salesidfldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesidfldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salesidfldActionPerformed

    private void salesrecordfldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesrecordfldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salesrecordfldActionPerformed

    private void salesavebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesavebtnActionPerformed
      String salesid = this.salesidfld.getText().trim();
      String itemid = (String)this.salesitemidcmb.getSelectedItem();
      String quantity = this.salesquantityfld.getText().trim();
      String salesdate = this.salesdatefld.getText().trim();
      String recordedby = this.salesrecordfld.getText().trim();
      
      
      if (salesid.isEmpty() || itemid.isEmpty() || quantity.isEmpty() || salesdate.isEmpty() || recordedby.isEmpty()) {
         JOptionPane.showMessageDialog(null, "Please fill all fields.");
      }

      try {
         int saleQty = Integer.parseInt(quantity);
         
         List<Item> itemList = manager.getAllItems();
         Item matchedItem = (Item)itemList.stream().filter(i -> 
                            i.getItemID().equals(itemid))
                            .findFirst().orElse(null);
         
         int Quantity = matchedItem.getStock() + oriquantity;
         
         if (matchedItem == null) {
            JOptionPane.showMessageDialog(null, "Invalid Item ID!");
            return;
         }

         if (saleQty <= 0) {
            JOptionPane.showMessageDialog(null, "Quantity must be more than zero!");
            return;
         }

         if (saleQty > Quantity) {
            JOptionPane.showMessageDialog(null, "Not Enough stock. Available: " + Quantity);
            return;
         }
         
         System.out.println("new Q:" + saleQty);
         System.out.println("ori quant:" + Quantity);
         System.out.println("current quant:" + matchedItem.getStock());
         matchedItem.setStock(Quantity - saleQty);         
         FileHandler.writeAllToFile("data/Items.txt", itemList);
         String matchedEmployee = manager.getEmployeeID();
         if (matchedEmployee == null) {
            JOptionPane.showMessageDialog(null, "Invalid Item ID!");
            return;
         }

         boolean found;
         String linetowrite;
         if (!this.isEditMode) {
            found = manager.getAllSales().stream()
                    .anyMatch(s -> s.getSaleID().equals(salesid));
            if (found) {
               JOptionPane.showMessageDialog(null, "Sales ID already exists!");
               return;
            }

            linetowrite = salesid + "," + matchedItem.getItemID() + "," + saleQty + "," + matchedEmployee + "," + salesdate;
            FileHandler.appendLine("data/Sale.txt", linetowrite);
            JOptionPane.showMessageDialog(null, "Sales added succesfully!");
            loadAllSales();
         } else {
            found = false;
            linetowrite = (String)salesitemidcmb.getSelectedItem();
            int newQty = Integer.parseInt(salesquantityfld.getText().trim());
            Item originalItem = (Item)itemList.stream().filter( i -> i.getItemID().equals(itemid))
                                .findFirst().orElse(null);
            
            Item newItem = (Item)itemList.stream()
                            .filter(i -> i.getItemID().equals(linetowrite))
                             .findFirst().orElse(null);
            if (originalItem != null) {
               originalItem.setStock(originalItem.getStock() + Quantity);
            }

            if (newQty > newItem.getStock()) {
               JOptionPane.showMessageDialog(null, "Not enough stock. Available: " + newItem.getStock());
               if (originalItem != null) {
                  originalItem.setStock(originalItem.getStock() - Quantity);
               }
            }

            if (newItem == null) {
               JOptionPane.showMessageDialog(null, "Item not found.");
               return;
            }

            int stockwithReturn = matchedItem.getStock() + Quantity;
            if (newQty > stockwithReturn) {
               JOptionPane.showMessageDialog(null, "Not enough stock. Available: " + stockwithReturn);
               return;
            }

            matchedItem.setStock(Quantity - saleQty);
            FileHandler.writeAllToFile("data/Items.txt", itemList);
            List<Sales> salesList = manager.getAllSales();
            for (Sales s : salesList){
               if (s.getSaleID().equals(salesid)) {
                  s.setRecordedBy(matchedEmployee);
                  s.setQuantity(saleQty);
                  found = true;
                  break;
               }
            }

            if (!found) {
               JOptionPane.showMessageDialog(null, "Sales not found!");
               return;
            }

            FileHandler.writeAllToFile("data/Sale.txt", salesList);
            JOptionPane.showMessageDialog(null, "Sales editted succesfully!");
         }

         salesidfld.setEditable(false);
         salesitemidcmb.removeAllItems();
         salesquantityfld.setEditable(false);
         salesdatefld.setEditable(false);
         salesrecordfld.setEditable(false);
         salesidfld.setText("");
         salesquantityfld.setText("");
         salesdatefld.setText("");
         salesrecordfld.setText("");
         loadAllSales();
         salesavebtn.setVisible(false);
      } catch (NumberFormatException e) {
         JOptionPane.showMessageDialog(null, "Quantity must be a number.");
      }
    }//GEN-LAST:event_salesavebtnActionPerformed

    private void saleeditbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saleeditbtnActionPerformed
      isEditMode = true;
      salesquantityfld.setEditable(true);
      salesitemidcmb.setEnabled(true);
      oriquantity = Integer.parseInt(this.salesquantityfld.getText().trim());
      salesavebtn.setVisible(true);
    }//GEN-LAST:event_saleeditbtnActionPerformed

    private void saleaddbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saleaddbtnActionPerformed
      isEditMode = false;
      salesquantityfld.setEditable(true);
      salesidfld.setText(FileHandler.getNextID("data/Sale.txt", "SAL"));
      salesitemidcmb.removeAllItems();
      LoadIteminCombo(this.salesitemidcmb);
      salesitemidcmb.setEnabled(true);
      salesquantityfld.setText("");
      salesdatefld.setText(this.TodayDate());
      salesrecordfld.setText(Session.getCurrentUser().getEmployeeID());
      salesavebtn.setVisible(true);
    }//GEN-LAST:event_saleaddbtnActionPerformed

    private void saledeletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saledeletebtnActionPerformed
        int selectedRow = SaleTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a sales record to delete.");
        } else {
         String itemId = SaleTable.getValueAt(selectedRow, 1).toString();
         int qty = Integer.parseInt(SaleTable.getValueAt(selectedRow, 2).toString());
         List<Item> itemList = manager.getAllItems();
         Item item = (Item)itemList.stream()
                 .filter(i -> i.getItemID().equals(itemId))
                 .findFirst().orElse(null);
         String selectedSalesId = SaleTable.getValueAt(selectedRow, 0).toString();
         if (manager.removeSales(selectedSalesId)) {
            if (item != null) {
               item.setStock(item.getStock() + qty);
               FileHandler.writeAllToFile("data/Items.txt", itemList);
            }

            JOptionPane.showMessageDialog(null, "Sales is sucessfully removed!.");
         } else {
            JOptionPane.showMessageDialog(null, "Sales cannot be removed! Please try again..");
         }

         this.loadAllSales();
      }
    }//GEN-LAST:event_saledeletebtnActionPerformed

    private void saleclearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saleclearbtnActionPerformed
        salesearchfld.setText("");
        loadAllSales();
    }//GEN-LAST:event_saleclearbtnActionPerformed

    private void salesearchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesearchbtnActionPerformed
        String salesId = salesearchfld.getText().trim();
        DefaultTableModel model = (DefaultTableModel)SaleTable.getModel();
        model.setRowCount(0);
        if (!salesId.isEmpty()) {
            Sales result = manager.getSalesById(salesId);
        if (result != null) {
            model.addRow(new Object[]{
                result.getSaleID(),
                result.getItem().getItemID(),
                result.getQuantity(),
                result.getDate().toString()
            });
         } else {
            JOptionPane.showMessageDialog(null, "No Sales found based on the Sales ID: " + salesId);
         }
      } else {
         JOptionPane.showMessageDialog(null, "Please enter a valid Sales ID!");
      }
    }//GEN-LAST:event_salesearchbtnActionPerformed

    private void poclearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poclearbtnActionPerformed
        posearchfld.setText("");
        loadAllPOs();
    }//GEN-LAST:event_poclearbtnActionPerformed

    private void posearchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_posearchbtnActionPerformed
        String poId = posearchfld.getText().trim();
        DefaultTableModel model = (DefaultTableModel)PoTable.getModel();
        model.setRowCount(0);
        if (!poId.isEmpty()) {
            PO result = manager.getPOById(poId);
            if (result != null) {
                model.addRow(new Object[]{
                    result.getPoID(),
                    result.getPr().getPR_ID(),
                    result.getItem().getItemID(),
                    result.getSup().getSupplierName(),
                    result.getQuantity(),
                    result.getDate().toString(),
                    result.getRequestedBy(),
                    result.getApproval(),
                    result.getApprovalBy()
                });
            } else {
                JOptionPane.showMessageDialog(null, "No PO found based on the PO ID: " + poId);
        }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a valid PO ID!");
        } 
    }//GEN-LAST:event_posearchbtnActionPerformed

    private void praddbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_praddbtnActionPerformed
        this.prlabel.setText("GENERATE PURCHASE REQUISITION");
        this.saveprbtn.setText("GENERATE");
        this.itemidcmb.removeAllItems();
        LoadIteminCombo(this.itemidcmb);
        pridfld.setText(FileHandler.getNextID("data/PR.txt", "PR"));
        prquantityfld.setText("");
        prrequestfld.setText(Session.getCurrentUser().getEmployeeID());
        prdatefld.setText(this.TodayDate());
        prstatusfld.setText("PENDING"); 
        
        
        pridfld.setEditable(false);
        prrequestfld.setEditable(false);
        prdatefld.setEditable(false);
        prstatusfld.setEditable(false);   
        
        prmenu.setVisible(true);
        prmenu.setSize(600, 500);
        prmenu.setLocationRelativeTo(null);
    }//GEN-LAST:event_praddbtnActionPerformed

    private void prclearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prclearbtnActionPerformed
        prsearchfld.setText("");
        loadAllPRs();
    }//GEN-LAST:event_prclearbtnActionPerformed

    private void prdeletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prdeletebtnActionPerformed
        int selectedRow = PrTable.getSelectedRow();
        if (selectedRow == -1) {
             JOptionPane.showMessageDialog(null, "Please select a PR record to delete.");
        }

        String selectedPRId = PrTable.getValueAt(selectedRow, 0).toString();
        if (this.manager.removePR(selectedPRId)) {
            JOptionPane.showMessageDialog(null, "PR record is sucessfully removed!.");
        } else {
             JOptionPane.showMessageDialog(null, "PR record cannot be removed! Please try again..");
        }

        this.loadAllPRs();
    }//GEN-LAST:event_prdeletebtnActionPerformed

    private void prdatefldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prdatefldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prdatefldActionPerformed

    private void preditbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preditbtnActionPerformed
        prlabel.setText("EDIT PURCHASE REQUISITION");
        saveprbtn.setText("Save");
        isEditMode = true;
        itemidcmb.removeAllItems();
        LoadIteminCombo(this.itemidcmb);
        int SelectedRow = PrTable.getSelectedRow();
        if (SelectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a PR to edit!");
        } else {
        String prid = PrTable.getValueAt(SelectedRow, 0).toString();
        String itemid = PrTable.getValueAt(SelectedRow, 1).toString();
        String quantity = PrTable.getValueAt(SelectedRow, 2).toString();
        String date = this.PrTable.getValueAt(SelectedRow, 3).toString();
        String requestedby = PrTable.getValueAt(SelectedRow, 4).toString();
        String status = this.PrTable.getValueAt(SelectedRow, 5).toString();
        pridfld.setText(prid);
        itemidcmb.setSelectedItem(itemid);
        prquantityfld.setText(quantity);
        prdatefld.setText(date);
        prrequestfld.setText(requestedby);
        prstatusfld.setText(status);
        
        pridfld.setEditable(false);
        prrequestfld.setEditable(false);
        prdatefld.setEditable(false);
        prstatusfld.setEditable(false);   
        
        prmenu.setVisible(true);
        prmenu.setSize(600, 500);
        prmenu.setLocationRelativeTo(null);
      }
    }//GEN-LAST:event_preditbtnActionPerformed

    private void prsearchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prsearchbtnActionPerformed
        String prId = prsearchfld.getText().trim();
        DefaultTableModel model = (DefaultTableModel)PrTable.getModel();
        model.setRowCount(0);
        if (!prId.isEmpty()) {
            PR result = this.manager.getPRById(prId);
        if (result != null) {
            model.addRow(new Object[]
                {result.getPR_ID(),
                result.getItem().getItemID(),
                result.getQuantity(),
                result.getRequestdDate().toString(),
                result.getRequestedBy(),
                result.getStatus()});
        } else {
            JOptionPane.showMessageDialog(this, "No PR found based on the PR ID: " + prId);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid PR ID!");
        }
    }//GEN-LAST:event_prsearchbtnActionPerformed

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
        itemidfld.setText("");
        itemnamefld.setText("");
        itemstockfld.setText("");
        itemidfld.setEditable(false);
        itemnamefld.setEditable(false);
        itemstockfld.setEditable(false);
        loadAllItems();
        loadLowStockItems();
        supidfld.setText("");
        supitemidcmb.removeAllItems();
        supnamefld.setText("");
        supcontactfld.setText("");
        unitpricefld.setText("");
        supnamefld.setEditable(false);
        supcontactfld.setEditable(false);
        unitpricefld.setEditable(false);
        loadAllSuppliers();
        salesidfld.setEditable(false);
        salesitemidcmb.removeAllItems();
        salesquantityfld.setEditable(false);
        salesdatefld.setEditable(false);
        salesrecordfld.setEditable(false);
        salesidfld.setText("");
        salesquantityfld.setText("");
        salesdatefld.setText("");
        salesrecordfld.setText("");
        loadAllSales();
        
        itemsavebtn.setVisible(false);
        savesupbtn.setVisible(false);
        salesavebtn.setVisible(false);
    }//GEN-LAST:event_refreshbtnActionPerformed

    private void PrTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrTableMouseClicked
        int selectedRow = PrTable.getSelectedRow();
        if (selectedRow != -1) {
            String itemId = PrTable.getValueAt(selectedRow, 1).toString();
            Item selectedItem = manager.getItemById(itemId);
        if (selectedItem != null) {
            DefaultTableModel model = (DefaultTableModel)itemprtable.getModel();
            model.setRowCount(0);
            model.addRow(new Object[]{
                selectedItem.getItemID(),
                selectedItem.getItemName(),
                selectedItem.getStock()});
         }
      }
    }//GEN-LAST:event_PrTableMouseClicked

    private void saveprbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveprbtnActionPerformed
      String prid = pridfld.getText().trim();
      String itemid = (String) itemidcmb.getSelectedItem();
      String quantity = prquantityfld.getText().trim();
      String requestedby = prrequestfld.getText().trim();
      String date = prdatefld.getText().trim();
      String status = prstatusfld.getText().trim();
      if (prid.isEmpty() || quantity.isEmpty() || itemid.isEmpty()) {
         JOptionPane.showMessageDialog(null, "Please fill all fields.");
      }

      try {
         int Quantity = Integer.parseInt(quantity);
         Item matchedItem = (Item) manager.getAllItems().stream()
                            .filter(i -> i.getItemID().equals(itemid))
                            .findFirst().orElse(null);
         if (!this.isEditMode) {
            String linetowrite = prid + "," + itemid + "," + Quantity + "," + date + "," + requestedby + "," + status;
            FileHandler.appendLine("data/PR.txt", linetowrite);
            JOptionPane.showMessageDialog(null, "PR generated succesfully!");
         } else {
            List<PR> prList = this.manager.getAllPRs();
            for (PR pr : prList){
               if (pr.getPR_ID().equals(prid)) {
                  pr.setItem(matchedItem);
                  pr.setQuantity(Quantity);
                  break;
               }
            }

            FileHandler.writeAllToFile("data/PR.txt", prList);
            JOptionPane.showMessageDialog(null, "PR record editted succesfully!");
         }
      } catch (NumberFormatException e) {
         JOptionPane.showMessageDialog(null, "Quantity must be a number.");
      }
      
        pridfld.setText("");
        prquantityfld.setText("");
        prrequestfld.setText("");
        prdatefld.setText("");
        prstatusfld.setText("");
        loadAllItems();
        loadAllPRs();
        loadLowStockItems();
        prmenu.setVisible(false);
    }//GEN-LAST:event_saveprbtnActionPerformed

    private void lowStockTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lowStockTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lowStockTableMouseClicked

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
//            java.util.logging.Logger.getLogger(SMFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SMFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SMFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SMFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SMFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Contact;
    private javax.swing.JTable ItemTable;
    private javax.swing.JTable PoTable;
    private javax.swing.JTable PrTable;
    private javax.swing.JTable SaleTable;
    private javax.swing.JTable SupplierTable;
    private javax.swing.JButton itemaddbtn;
    private javax.swing.JButton itemclearbtn;
    private javax.swing.JButton itemdeletebtn;
    private javax.swing.JButton itemeditbtn;
    private javax.swing.JComboBox<String> itemfiltercmb;
    private javax.swing.JComboBox<String> itemidcmb;
    private javax.swing.JTextField itemidfld;
    private javax.swing.JTextField itemnamefld;
    private javax.swing.JTable itemprtable;
    private javax.swing.JButton itemsavebtn;
    private javax.swing.JButton itemsearchbtn;
    private javax.swing.JTextField itemsearchfld;
    private javax.swing.JTextField itemstockfld;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblwelcome;
    private javax.swing.JButton logoutbtn;
    private javax.swing.JTable lowStockTable;
    private javax.swing.JButton poclearbtn;
    private javax.swing.JButton posearchbtn;
    private javax.swing.JTextField posearchfld;
    private javax.swing.JButton praddbtn;
    private javax.swing.JButton prclearbtn;
    private javax.swing.JTextField prdatefld;
    private javax.swing.JButton prdeletebtn;
    private javax.swing.JButton preditbtn;
    private javax.swing.JTextField pridfld;
    private javax.swing.JLabel prlabel;
    private javax.swing.JFrame prmenu;
    private javax.swing.JTextField prquantityfld;
    private javax.swing.JTextField prrequestfld;
    private javax.swing.JButton prsearchbtn;
    private javax.swing.JTextField prsearchfld;
    private javax.swing.JTextField prstatusfld;
    private javax.swing.JButton refreshbtn;
    private javax.swing.JButton saleaddbtn;
    private javax.swing.JButton saleclearbtn;
    private javax.swing.JButton saledeletebtn;
    private javax.swing.JButton saleeditbtn;
    private javax.swing.JButton salesavebtn;
    private javax.swing.JTextField salesdatefld;
    private javax.swing.JButton salesearchbtn;
    private javax.swing.JTextField salesearchfld;
    private javax.swing.JTextField salesidfld;
    private javax.swing.JComboBox<String> salesitemidcmb;
    private javax.swing.JTextField salesquantityfld;
    private javax.swing.JTextField salesrecordfld;
    private javax.swing.JButton saveprbtn;
    private javax.swing.JButton savesupbtn;
    private javax.swing.JButton supaddbtn;
    private javax.swing.JButton supclearbtn;
    private javax.swing.JTextField supcontactfld;
    private javax.swing.JButton supdeletebtn;
    private javax.swing.JButton supeditbtn;
    private javax.swing.JComboBox<String> supfiltercmb;
    private javax.swing.JTextField supidfld;
    private javax.swing.JComboBox<String> supitemidcmb;
    private javax.swing.JTable supitemtable;
    private javax.swing.JTextField supnamefld;
    private javax.swing.JButton supsearchbtn;
    private javax.swing.JTextField unitpricefld;
    // End of variables declaration//GEN-END:variables
}

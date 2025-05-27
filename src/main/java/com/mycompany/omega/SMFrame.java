/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.omega;
import com.mycompany.omega.classes.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
    /**
     * Creates new form SMFrame
     */
    public SMFrame() {
        initComponents();
        
        this.manager = (SalesManager) Session.getCurrentUser();
        
        System.out.println("Session User: " + Session.getCurrentUser());
        System.out.println("Manager assigned? " + (manager != null));
        
        
        loadAllItems();
        loadAllSuppliers();
        loadAllSales();
        loadAllPRs();
        loadAllPOs();
        loadLowStockItems();
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
        DefaultTableModel model = (DefaultTableModel) itemprtable.getModel();
        model.setRowCount(0); 
        
        for(Item i: manager.getLowStockItem(10)){
            model.addRow(new Object[]{
                i.getItemID(),
                i.getItemName(),
                i.getStock()
            });
        }
        System.out.println("Low Stock Items loaded: " + manager.getLowStockItem(10).size());
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
      for (Item item : ItemList){
         combo.addItem(item.getItemID());
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        SupplierTable = new javax.swing.JTable();
        supclearbtn = new javax.swing.JButton();
        supsearchbtn = new javax.swing.JButton();
        supsearchfld = new javax.swing.JTextField();
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
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        PrTable = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        itemprtable = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        LowStockItemTable = new javax.swing.JTable();
        prclearbtn = new javax.swing.JButton();
        prsearchbtn = new javax.swing.JButton();
        praddbtn = new javax.swing.JButton();
        preditbtn = new javax.swing.JButton();
        prdeletebtn = new javax.swing.JButton();
        prsearchfld = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        PoTable = new javax.swing.JTable();
        poclearbtn = new javax.swing.JButton();
        posearchbtn = new javax.swing.JButton();
        posearchfld = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();

        prmenu.setBackground(new java.awt.Color(0, 153, 153));

        jPanel7.setBackground(new java.awt.Color(0, 153, 153));

        prlabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        prlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prlabel.setText("GENERATE PURCHASE REQUISITION");

        saveprbtn.setText("Generate");

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

        itemsearchbtn.setText("search");
        itemsearchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemsearchbtnActionPerformed(evt);
            }
        });

        itemclearbtn.setText("clear");
        itemclearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemclearbtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Item ID:");

        jLabel2.setText("Item Name:");

        jLabel3.setText("Item Stock:");

        itemidfld.setEditable(false);
        itemidfld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemidfldActionPerformed(evt);
            }
        });

        itemnamefld.setEditable(false);
        itemnamefld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemnamefldActionPerformed(evt);
            }
        });

        itemstockfld.setEditable(false);

        itemaddbtn.setText("Add");
        itemaddbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemaddbtnActionPerformed(evt);
            }
        });

        itemeditbtn.setText("Edit");
        itemeditbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemeditbtnActionPerformed(evt);
            }
        });

        itemdeletebtn.setText("Delete");
        itemdeletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemdeletebtnActionPerformed(evt);
            }
        });

        itemsavebtn.setText("Save");
        itemsavebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemsavebtnActionPerformed(evt);
            }
        });

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

        jLabel24.setBackground(new java.awt.Color(0, 0, 0));
        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("ITEM ENTRY");
        jLabel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(itemsearchfld, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(itemsearchbtn)
                        .addGap(18, 18, 18)
                        .addComponent(itemclearbtn)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(itemidfld)
                                        .addComponent(itemnamefld)
                                        .addComponent(itemstockfld, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(itemaddbtn)
                                        .addGap(36, 36, 36)
                                        .addComponent(itemeditbtn)
                                        .addGap(29, 29, 29)
                                        .addComponent(itemdeletebtn)))
                                .addGap(65, 65, 65))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(itemsavebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(170, 170, 170))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(itemsearchfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(itemsearchbtn)
                            .addComponent(itemclearbtn)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(itemidfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(itemnamefld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(itemstockfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(itemaddbtn)
                            .addComponent(itemeditbtn)
                            .addComponent(itemdeletebtn))
                        .addGap(18, 18, 18)
                        .addComponent(itemsavebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );

        itemsavebtn.setVisible(false);

        jTabbedPane1.addTab("Item Entry", jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

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

        supclearbtn.setText("Clear");
        supclearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supclearbtnActionPerformed(evt);
            }
        });

        supsearchbtn.setText("Search");
        supsearchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supsearchbtnActionPerformed(evt);
            }
        });

        supaddbtn.setText("Add");
        supaddbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supaddbtnActionPerformed(evt);
            }
        });

        supeditbtn.setText("Edit");
        supeditbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supeditbtnActionPerformed(evt);
            }
        });

        supdeletebtn.setText("Delete");
        supdeletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supdeletebtnActionPerformed(evt);
            }
        });

        savesupbtn.setText("Save");
        savesupbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savesupbtnActionPerformed(evt);
            }
        });

        supidfld.setEditable(false);

        supnamefld.setEditable(false);

        supcontactfld.setEditable(false);

        unitpricefld.setEditable(false);

        jLabel4.setText("Supplier ID:");

        jLabel5.setText("Supplier Name:");

        jLabel6.setText("Item ID:");

        jLabel7.setText("Unit Price:");

        Contact.setText("Contact:");

        jLabel23.setBackground(new java.awt.Color(0, 0, 0));
        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("SUPPLIER ENTRY");
        jLabel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(supsearchfld, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(supsearchbtn)
                        .addGap(18, 18, 18)
                        .addComponent(supclearbtn))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(Contact))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(supcontactfld)
                            .addComponent(supnamefld)
                            .addComponent(supitemidcmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(supidfld)
                            .addComponent(unitpricefld, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(savesupbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(supaddbtn)
                                .addGap(31, 31, 31)
                                .addComponent(supeditbtn)
                                .addGap(29, 29, 29)
                                .addComponent(supdeletebtn)))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supclearbtn)
                    .addComponent(supsearchbtn)
                    .addComponent(supsearchfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(supidfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(supitemidcmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(supnamefld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(supcontactfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Contact))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(unitpricefld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(supaddbtn)
                            .addComponent(supeditbtn)
                            .addComponent(supdeletebtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(savesupbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        savesupbtn.setVisible(false);

        jTabbedPane1.addTab("Supplier Entry", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

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

        saleaddbtn.setText("Add");
        saleaddbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleaddbtnActionPerformed(evt);
            }
        });

        saleeditbtn.setText("Edit");
        saleeditbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleeditbtnActionPerformed(evt);
            }
        });

        saledeletebtn.setText("Delete");
        saledeletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saledeletebtnActionPerformed(evt);
            }
        });

        salesavebtn.setText("Save");
        salesavebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesavebtnActionPerformed(evt);
            }
        });

        salesidfld.setEditable(false);
        salesidfld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesidfldActionPerformed(evt);
            }
        });

        salesquantityfld.setEditable(false);
        salesquantityfld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesquantityfldActionPerformed(evt);
            }
        });

        salesdatefld.setEditable(false);

        salesrecordfld.setEditable(false);
        salesrecordfld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesrecordfldActionPerformed(evt);
            }
        });

        salesitemidcmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesitemidcmbActionPerformed(evt);
            }
        });

        jLabel8.setText("Item ID:");

        jLabel9.setText("Sales ID:");

        jLabel10.setText("Quantity:");

        jLabel11.setText("Sales Date:");

        jLabel12.setText("Recorded By:");

        saleclearbtn.setText("Clear");
        saleclearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleclearbtnActionPerformed(evt);
            }
        });

        salesearchbtn.setText("Search");
        salesearchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesearchbtnActionPerformed(evt);
            }
        });

        jLabel22.setBackground(new java.awt.Color(0, 0, 0));
        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("SALES ENTRY");
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(salesearchfld, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(salesearchbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saleclearbtn)
                        .addGap(6, 6, 6))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(salesidfld)
                            .addComponent(salesrecordfld)
                            .addComponent(salesdatefld)
                            .addComponent(salesquantityfld)
                            .addComponent(salesitemidcmb, 0, 286, Short.MAX_VALUE))
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(saleaddbtn)
                        .addGap(30, 30, 30)
                        .addComponent(saleeditbtn)
                        .addGap(28, 28, 28)
                        .addComponent(saledeletebtn)
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(salesavebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(saleclearbtn)
                    .addComponent(salesearchbtn)
                    .addComponent(salesearchfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salesidfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salesitemidcmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salesquantityfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salesdatefld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salesrecordfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saleaddbtn)
                    .addComponent(saleeditbtn)
                    .addComponent(saledeletebtn))
                .addGap(31, 31, 31)
                .addComponent(salesavebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        salesavebtn.setVisible(false);

        jTabbedPane1.addTab("Sales Entry", jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

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

        LowStockItemTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(LowStockItemTable);

        prclearbtn.setText("Clear");
        prclearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prclearbtnActionPerformed(evt);
            }
        });

        prsearchbtn.setText("Search");
        prsearchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prsearchbtnActionPerformed(evt);
            }
        });

        praddbtn.setText("Add");
        praddbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                praddbtnActionPerformed(evt);
            }
        });

        preditbtn.setText("Edit");
        preditbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preditbtnActionPerformed(evt);
            }
        });

        prdeletebtn.setText("Edit");
        prdeletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prdeletebtnActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 51, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("LOW STOCK ITEM ALERT");
        jLabel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("PURCHASE REQUISITION ENTRY");
        jLabel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(praddbtn)
                        .addGap(18, 18, 18)
                        .addComponent(preditbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(prdeletebtn)
                        .addGap(57, 57, 57))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(prsearchfld, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(prsearchbtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prclearbtn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(prclearbtn)
                            .addComponent(prsearchbtn)
                            .addComponent(prsearchfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(praddbtn)
                            .addComponent(preditbtn)
                            .addComponent(prdeletebtn)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Purchase Requisition", jPanel5);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

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

        poclearbtn.setText("Clear");
        poclearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poclearbtnActionPerformed(evt);
            }
        });

        posearchbtn.setText("Search");
        posearchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                posearchbtnActionPerformed(evt);
            }
        });

        jLabel21.setBackground(new java.awt.Color(0, 0, 0));
        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("PURCHASE ORDER DISPLAY");
        jLabel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1056, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(329, 329, 329)
                        .addComponent(posearchfld, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(posearchbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(poclearbtn)
                        .addGap(29, 29, 29))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(poclearbtn)
                    .addComponent(posearchbtn)
                    .addComponent(posearchfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jTabbedPane1.addTab("Purchase Order", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
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
      String suppliersId = supsearchfld.getText().trim();
      DefaultTableModel model = (DefaultTableModel)SupplierTable.getModel();
      model.setRowCount(0);
      
      if (!suppliersId.isEmpty()) {
         Supplier result = manager.getSupplierById(suppliersId);
         if (result != null) {
            model.addRow(new Object[]{
                result.getSupplierID(), 
                result.getSupplierName(),
                result.getContact(), 
                result.getItem().getItemID(),
                result.getPrice()
            });
         } else {
            JOptionPane.showMessageDialog(null, "No Supplier found based on the supplier ID: " + suppliersId);
         }
      } else {
         JOptionPane.showMessageDialog(null, "Please enter a valid Supplier ID!");
      }

    }//GEN-LAST:event_supsearchbtnActionPerformed

    private void supclearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supclearbtnActionPerformed
       supsearchfld.setText("");
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
         int Quantity = Integer.parseInt(quantity);
         List<Item> itemList = manager.getAllItems();
         Item matchedItem = (Item)itemList.stream().filter(i -> 
                            i.getItemID().equals(itemid))
                            .findFirst().orElse(null);
         if (matchedItem == null) {
            JOptionPane.showMessageDialog(null, "Invalid Item ID!");
            return;
         }

         if (saleQty <= 0) {
            JOptionPane.showMessageDialog(null, "Quantity must be more than zero!");
            return;
         }

         if (saleQty > matchedItem.getStock()) {
            JOptionPane.showMessageDialog(null, "Not Enough stock. Available: " + matchedItem.getStock());
            return;
         }

         matchedItem.setStock(matchedItem.getStock() - saleQty);
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

            linetowrite = salesid + "," + matchedItem.getItemID() + "," + Quantity + "," + matchedEmployee + "," + salesdate;
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

            matchedItem.setStock(stockwithReturn - newQty);
            FileHandler.writeAllToFile("data/Items.txt", itemList);
            List<Sales> salesList = manager.getAllSales();
            for (Sales s : salesList){
               if (s.getSaleID().equals(salesid)) {
                  s.setRecordedBy(matchedEmployee);
                  s.setQuantity(Quantity);
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

         this.loadAllSuppliers();
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
        prdatefld.setText(Session.getCurrentUser().getEmployeeID());
        prdatefld.setText(this.TodayDate());
        prstatusfld.setText("PENDING");    
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
            model.addRow(new Object[]{result.getPR_ID(), result.getItem().getItemID(), result.getQuantity(), result.getRequestdDate().toString(), result.getRequestedBy(), result.getStatus()});
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
    }//GEN-LAST:event_refreshbtnActionPerformed

    private void PrTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrTableMouseClicked
        int selectedRow = PrTable.getSelectedRow();
        if (selectedRow != -1) {
            String itemId = ItemTable.getValueAt(selectedRow, 0).toString();
            Item selectedItem = manager.getItemById(itemId);
        if (selectedItem != null) {
            DefaultTableModel model = (DefaultTableModel)itemprtable.getModel();
            model.setRowCount(0);
            model.addRow(new Object[]{selectedItem.getItemID(), selectedItem.getItemName(), selectedItem.getStock()});
         }
      }
    }//GEN-LAST:event_PrTableMouseClicked

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
    private javax.swing.JTable LowStockItemTable;
    private javax.swing.JTable PoTable;
    private javax.swing.JTable PrTable;
    private javax.swing.JTable SaleTable;
    private javax.swing.JTable SupplierTable;
    private javax.swing.JButton itemaddbtn;
    private javax.swing.JButton itemclearbtn;
    private javax.swing.JButton itemdeletebtn;
    private javax.swing.JButton itemeditbtn;
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
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JTextField supidfld;
    private javax.swing.JComboBox<String> supitemidcmb;
    private javax.swing.JTable supitemtable;
    private javax.swing.JTextField supnamefld;
    private javax.swing.JButton supsearchbtn;
    private javax.swing.JTextField supsearchfld;
    private javax.swing.JTextField unitpricefld;
    // End of variables declaration//GEN-END:variables
}

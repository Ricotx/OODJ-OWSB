/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omega.classes;
import com.mycompany.omega.SMFrame;
import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

/**
 *
 * @author adham
 */
public class SalesManager extends Employee implements Manageable<PO>, Viewable<PO> {
    private List<PO> poList;
    private List<PR> prList;
    private List<Supplier> supplierList;
    private List<Item> itemList;
    private List<Sales> salesList;
    
    
    

    public SalesManager(String employeeID, String name, Employee.Role role, String email, String password) {
        super(employeeID, name, role, email, password);
        List<Item> itemList = FileHandler.loadFromFile("data/Items.txt", Item::fromLine);
        this.itemList = FileHandler.loadFromFile("data/Items.txt", Item::fromLine);
        this.prList = FileHandler.loadFromFile("data/PR.txt",line -> PR.fromLine(line, itemList));  
        this.supplierList = FileHandler.loadFromFile("data/Supplier.txt",line -> Supplier.fromLine(line, itemList));
        this.poList = FileHandler.loadFromFile("data/PO.txt",line -> PO.fromLine(line, prList, itemList, supplierList));
        this.salesList = FileHandler.loadFromFile("data/Sale.txt",line -> Sales.fromLine(line, itemList));
    }

    
  public void launchDashboard() {

      (new SMFrame(Session.getCurrentUser())).setVisible(true);
   }

   public void add(PO po) {
      this.poList.add(po);
      FileHandler.appendLine("data/PO.txt", po.toString());
   }

   public void edit(PO po) {
      ListIterator iterator = this.poList.listIterator();

      while(iterator.hasNext()) {
         PO current = (PO)iterator.next();
         if (current.getPoID().equals(po.getPoID())) {
            iterator.set(po);
            break;
         }
      }

   }

   public void delete(PO po) {
      this.poList.removeIf((p) -> {
         return p.getPoID().equals(po.getPoID());
      });
      FileHandler.writeAllToFile("data/PO.txt", this.poList);
   }

   public List<PO> viewAll() {
      return this.poList;
   }

    @Override
    public PO viewById(String id){
        return poList.stream()
                .filter(po -> po.getPoID().equals(id))
                .findFirst()
                .orElse(null);
    }

   public List<Supplier> getSupplierForItem(String itemID) {
      return (List)this.supplierList.stream().filter((s) -> {
         return s.getItem().getItemID().equals(itemID);
      }).collect(Collectors.toList());
   }

   public List<PR> getAllPRs() {
      return this.prList;
   }

   public List<Supplier> getAllSuppliers() {
      return this.supplierList;
   }

   public List<Item> getAllItems() {
      return this.itemList;
   }

   public List<Sales> getAllSales() {
      return this.salesList;
   }

   public Item getItemById(String itemid) {
      return (Item)this.itemList.stream().filter((i) -> {
         return i.getItemID().equals(itemid);
      }).findFirst().orElse(null);
   }

   public Supplier getSupplierById(String supplierid) {
      return (Supplier)this.supplierList.stream().filter((s) -> {
         return s.getSupplierID().equals(supplierid);
      }).findFirst().orElse(null);
   }

   public Sales getSalesById(String salesid) {
      return salesList.stream()
              .filter(s -> s.getSaleID().equals(salesid))
              .findFirst().orElse(null);
   }
   
    public PR getPRById(String id){
        return prList.stream()
                .filter(pr -> pr.getPR_ID().equals(id))
                .findFirst()
                .orElse(null);
    }

   public PO getPOById(String id) {
      return poList.stream()
             .filter(pr -> pr.getPoID().equals(id))
             .findFirst().orElse(null);
   }

   public boolean removeItem(String itemID) {
      boolean removed = this.itemList.removeIf((item) -> {
         return item.getItemID().equals(itemID);
      });
      if (removed) {
         FileHandler.writeAllToFile("data/Items.txt", this.itemList);
         System.out.println("Item with ID: " + itemID + " removed.");
         return true;
      } else {
         System.out.println("Item with ID: " + itemID + "not found.");
         return false;
      }
   }

   public boolean removeSupplier(String supID) {
      boolean removed = this.supplierList.removeIf((supplier) -> {
         return supplier.getSupplierID().equals(supID);
      });
      if (removed) {
         FileHandler.writeAllToFile("data/Supplier.txt", this.supplierList);
         System.out.println("Supplier with ID: " + supID + " removed.");
         return true;
      } else {
         System.out.println("Supplier with ID: " + supID + "not found.");
         return false;
      }
   }

   public boolean removeSales(String salesID) {
      boolean removed = this.salesList.removeIf((Sales) -> {
         return Sales.getSaleID().equals(salesID);
      });
      if (removed) {
         FileHandler.writeAllToFile("data/Sale.txt", this.salesList);
         System.out.println("Sale with ID: " + salesID + " removed.");
         return true;
      } else {
         System.out.println("Sale with ID: " + salesID + "not found.");
         return false;
      }
   }

   public boolean removePR(String PRID) {
      boolean removed = this.prList.removeIf((PR) -> {
         return PR.getPR_ID().equals(PRID);
      });
      if (removed) {
         FileHandler.writeAllToFile("data/PR.txt", this.prList);
         System.out.println("PR with ID: " + PRID + " removed.");
         return true;
      } else {
         System.out.println("Supplier with ID: " + PRID + "not found.");
         return false;
      }
   }

   public List<Item> getLowStockItem(int threshold) {
      return (List)this.itemList.stream().filter((item) -> {
         return item.getStock() < threshold;
      }).collect(Collectors.toList());
   }
} 

import java.util.ArrayList;
public class Restourant {

    private String name;
    private int earnedMoney=0;
    private int totalCostumer=0;
    ArrayList<Table> tables = new ArrayList<>();
    ArrayList<Receipt> receipts = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();

    public Restourant(String name){
        this.name = name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Customer customer) {
        this.customers.add(customer);
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public ArrayList<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(Receipt receipt) {
        this.receipts.add(receipt);
    }

    public void setTables(Table table) {
        tables.add(table);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEarnedMoney() {
        return earnedMoney;
    }

    public void setEarnedMoney(int earnedMoney) {
        this.earnedMoney += earnedMoney;
    }

    public int getTotalCostumer() {
        return totalCostumer;
    }

    public void incTotalCostumer() {
        this.totalCostumer += 1;
    }

    public void getAllTables(){
        System.out.println("----TÜM MASALAR----");
        System.out.println("Masa İsmi  |  Durum");
        tables.forEach(table -> {
            String status;
            if (table.getGuestName() != null) {
                status = "dolu";
            }else{
                status = "boş";
            }
            System.out.println(table.getName()+"         "+status);
        });
    }

    public int getTableEmpty(){
        System.out.println("----BOŞ MASALAR----");
        int counts = 0;
        for (Table table:tables){
            if (table.getGuestName() == null){
                counts++;
                System.out.println(table.getName());
            }
        }
        return counts;
    }

    public Customer existCustomer(String name){
        Customer current = new Customer();
        this.customers.forEach(customer -> {
            if (name.equals(customer.getName())){
                current.setName(customer.getName());
            }
        });
        return current;
    }

    public void changeTableGuest(String guestName,String tableName){
        tables.forEach(table -> {
            if (tableName.equals(table.getName())){
                table.setGuestName(guestName);
            }
        });
    }

    public String getTableNameByGuest(String guestName){
        String tableName = "";
       for (Table table:tables){
           if (table.getGuestName() == guestName){
               tableName = table.getName();
           }
       }
        return tableName;
    }

    public void addOrderToReceipt(Order order,String tableName){
        receipts.forEach(receipt -> {
            if (receipt.getTableName() == tableName){
                receipt.addOrder(order);
            }
        });
    }

    public int getFeeByTable(String tableName){
        int sumOfPrice=0;
        for(Receipt receipt : receipts){
            if (tableName.equals(receipt.getTableName())){
                sumOfPrice = receipt.sumOfPrice();
            }
        }
        return sumOfPrice;
    }

}

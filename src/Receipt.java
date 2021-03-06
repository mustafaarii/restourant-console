import java.util.ArrayList;


public class Receipt {
    private String tableName;
    private ArrayList<Order> orders = new ArrayList<>();
    private int totalPrice=0;

    public Receipt(String tableName){
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public int sumOfPrice(){
        this.totalPrice=0;
        for (Order order:orders){
            System.out.println("sipariş fiyatı :"+order.getPrice());
            this.totalPrice+=order.getPrice();
        }
        return totalPrice;
    }
}

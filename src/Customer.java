import java.util.ArrayList;
import java.util.Scanner;

public class Customer {

    private String name;
    private int weight;
    private int Happyness;

    public Customer(){ }

    public Customer(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHappyness() {
        return Happyness;
    }

    public void setHappyness(int happyness) {
        Happyness = happyness;
    }

    public void pay(ArrayList<Table> tables, ArrayList<Receipt> receipts,String tableName){

        for (Table table:tables){
            if (table.getGuestName()==this.name){
                table.setGuestName(null);
            }
        };
        int oldIndex = -1,index=0;
        for (Receipt receipt:receipts){
            if (tableName.equals(receipt.getTableName())){
                oldIndex=index;
            }
                index++;
            }
        receipts.remove(oldIndex);
        System.out.println("Ödeme için teşekkür ederiz..Yine bekleriz.");
    }

}

import java.util.Scanner;
public class main {
    // RENKLER
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {
        // Restoran, menu ve sistemdeki mevcut müşteri program başında oluşuyor.

        Restourant restourant = new Restourant("Sistem Restoran");
        Table table1 = new Table("masa 1");
        Table table2 = new Table("masa 2");
        Table table3 = new Table("masa 3");
        Table table4 = new Table("masa 4");
        restourant.setTables(table1);
        restourant.setTables(table2);
        restourant.setTables(table3);
        restourant.setTables(table4);

        Menu menu = new Menu();
        Customer currentCustomer=null;
        Scanner scanner = new Scanner(System.in);
        String currentTable = null;
        while (true){
            System.out.println("------------------SİSTEME HOŞGELDİNİZ-----------------");
            System.out.println("Yönetici girişi için 1, müşteri girişi için 2, çıkış için 3 yazın :");
            int select = scanner.nextInt();
            loop: while (true) {
                switch (select) {
                    case 1: {
                        System.out.println("Masa Eklemek için 1, Menüye yiyecek eklemek için 2, kasayı görmek için 3, çıkış için 4 yazın :");
                        int adminSelect = scanner.nextInt();
                        scanner.nextLine();
                        switch (adminSelect) {
                            case 1: {
                                // Masa ismi alınır ve yeni nesne oluşturarak restorana kaydedilir.
                                System.out.println("Masa adı girin :");
                                String tableName = scanner.nextLine();
                                Table table = new Table(tableName);
                                restourant.setTables(table);
                                System.out.println(ANSI_GREEN+"Masa başarıyla eklendi.\n"+ANSI_RESET);
                                break;
                            }
                            case 2: {
                                System.out.println("Yiyecek veya içecek ismi girin :");
                                String name = scanner.nextLine();
                                System.out.println("Fiyatını girin :");
                                int price = scanner.nextInt();
                                System.out.println("Yiyecek veya içecek türünü girin. (aperatif,ana yemek,icecek) :");
                                scanner.nextLine();
                                String kind = scanner.nextLine();
                                switch (kind) {
                                    case "aperatif":
                                        menu.setAperatifs(name, price);
                                        System.out.println(ANSI_GREEN+"Yiyecek menüye eklendi.\n"+ANSI_RESET);
                                        break;
                                    case "ana yemek":
                                        menu.setMainDish(name, price);
                                        System.out.println(ANSI_GREEN+"Yiyecek menüye eklendi.\n"+ANSI_RESET);
                                        break;
                                    case "icecek":
                                        menu.setDrinks(name, price);
                                        System.out.println(ANSI_GREEN+"Yiyecek menüye eklendi.\n"+ANSI_RESET);
                                        break;
                                    default:
                                        System.out.println(ANSI_RED+"Yanlış seçim yaptınız.\n"+ANSI_RESET);
                                }
                                break;
                            }
                            case 3:{System.out.println("Restoranın kasası :"+restourant.getEarnedMoney()+" ₺"); break;}
                            case 4:{
                                System.out.println(ANSI_BLUE+"Başarıyla çıkış yaptınız.\n"+ANSI_RESET);
                                break loop;
                            }
                            default:
                                System.out.println(ANSI_RED+"Yanlış seçim yaptınız.\n"+ANSI_RESET);
                        }
                        break;
                    }
                    case 2: {
                        scanner.nextLine();
                        // müşteri girişi yapılır.
                        System.out.println("Isminizi girin :");
                        String name = scanner.nextLine();
                        currentCustomer = restourant.existCustomer(name);

                        // masada oturan bir müşteri ise yeni kayıt yapılmaz. Var olan müşteri üzerinden devam edilir.
                        if (currentCustomer.getName() == null || restourant.getTableNameByGuest(currentCustomer.getName()) == ""){
                            currentCustomer =new Customer(name);
                            //restoranın müşterilerine kaydedilir.
                            restourant.setCustomers(currentCustomer);
                            //restoranın müşteri sayısı 1 arttırılır.
                            restourant.incTotalCostumer();
                            System.out.println(ANSI_BLUE+"\nRestoranımıza hoşgeldin "+currentCustomer.getName()+".\n"+ANSI_RESET);
                            System.out.println("Boş masaları görmek ve oturmak için 1, tüm masaları görmek için 2'yi seçin :");
                            int sel = scanner.nextInt();
                            scanner.nextLine();
                            switch (sel){
                                case 1:{
                                   int tableStatus = restourant.getTableEmpty();
                                   if (tableStatus==0){
                                       System.out.println(ANSI_RED+"Şu anda boş masamız bulunmamaktadır. Anlayışınız için teşekkür ederiz.\n"+ANSI_RESET);
                                       break loop;
                                   }
                                    System.out.println("Oturmak istediğiniz masayı girin :");
                                    currentTable = scanner.nextLine();
                                   //masaya ait bir fiş kes ve bunu restorana ekle. Masanın müşteri bilgilerini güncelle.
                                    Receipt receipt = new Receipt(currentTable);
                                    restourant.setReceipts(receipt);
                                    restourant.changeTableGuest(currentCustomer.getName(),currentTable);
                                    break;
                                }
                                case 2:{
                                    restourant.getAllTables();
                                    break loop;
                                }
                                case 3:{ break loop; }
                            }
                        }else{
                            // Eğer yeni bir müşteri değilse var olan müşterinin masa ismini al.
                              currentTable = restourant.getTableNameByGuest(currentCustomer.getName());
                        }
                    if (restourant.getTableNameByGuest(currentCustomer.getName()) != ""){
                        custLoop:while (true){
                            System.out.println("Sipariş vermek için 1, hesabı öğrenmek için 2, hesap ödemek için 3 yazın :");
                            int Cselect = scanner.nextInt();
                            switch (Cselect){
                                case 1:{
                                    // Menüyü yazdır. Eğer boşsa sipariş alma, boş değilse sipariş al.
                                    boolean menuIsEmpty = menu.printMenu();
                                    if (menuIsEmpty){
                                        System.out.println("Menüde hiçbir şey yok.");
                                        break custLoop;
                                    };
                                    scanner.nextLine();
                                    System.out.println("Sipariş vermek istediğiniz kategoriyi girin : (ana yemek, aperatif, icecek)");
                                    String foodType = scanner.nextLine();
                                    System.out.println("İstediğiniz yiyeceğin numarasını girin : ");
                                    int foodNumber = scanner.nextInt()-1; //girilen numaranın 1 eksiği olan indisle tutulduğu için -1
                                    System.out.println("Kaç adet istediğinizi girin :");
                                    int foodCount = scanner.nextInt();
                                    String[] foodInfo = new String[2];
                                    switch (foodType){
                                        case "ana yemek" : {
                                            foodInfo = menu.getMainDish(foodNumber);
                                            break;
                                        }
                                        case "aperatif" : {
                                            foodInfo = menu.getAperatif(foodNumber);
                                            break;
                                        }
                                        case "icecek" : {
                                            foodInfo = menu.getDrink(foodNumber);
                                            break;
                                        }
                                        default:break;
                                    }
                                    Order order = new Order(foodInfo[0],Integer.parseInt(foodInfo[1]),foodCount);
                                    restourant.addOrderToReceipt(order,currentTable);
                                    restourant.setEarnedMoney(order.getPrice());
                                    System.out.println(ANSI_YELLOW+"Sipariş başarıyla eklendi.\n"+ANSI_RESET);
                                    break;
                                }
                                case 2:{
                                    System.out.println("Ücret :" +ANSI_YELLOW+String.valueOf(restourant.getFeeByTable(currentTable))+ANSI_RESET);
                                    break;
                                }
                                case 3:{
                                    currentCustomer.pay(restourant.getTables(),restourant.getReceipts(),currentTable);
                                    currentTable=null; currentCustomer=null;
                                    break custLoop;
                                }
                                default:break custLoop;
                            }
                        }
                    }

                    break loop;
                    }
                    default:{break loop;}
                }
            }
        }
    }
}

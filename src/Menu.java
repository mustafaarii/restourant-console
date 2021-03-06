public class Menu {
   private String[][] mainDish = new String[20][2];
   private String[][] aperatifs = new String[20][2];
   private String[][] drinks = new String[20][2];


    public boolean printMenu(){
        if (mainDish[0][0]==null && aperatifs[0][0]==null && drinks[0][0]==null){
            return true;
        }
        if (mainDish[0][0]==null){
            System.out.println("Menüde ana yemek bulunmamaktadır.");
        }else {
            System.out.println("# Ana Yemekler #");
            for (int i=0;i<mainDish.length;i++){
                if (mainDish[i][0] != null){
                    System.out.println((i+1)+") "+mainDish[i][0]+"  "+mainDish[i][1]+"₺");
                }
            }
        }
        if (aperatifs[0][0]==null){
            System.out.println("Menüde aperatif yiyecek bulunmuyor.");
        }
        else{
            System.out.println("# Aperatifler #");
            for (int i=0;i<aperatifs.length;i++){
                if (aperatifs[i][0]!=null){
                    System.out.println((i+1)+") "+aperatifs[i][0]+"  "+aperatifs[i][1]+"₺");
                }
            }
        }
        if (drinks[0][0]==null){
            System.out.println("Menüde içecek bulunmamaktadır.");
        }else{
            System.out.println("# İçecekler #");
            for (int i=0;i<drinks.length;i++){
                if (drinks[i][0]!=null){
                    System.out.println((i+1)+") "+drinks[i][0]+"  "+drinks[i][1]+"₺");
                }
            }
        }
        return false;
    }

    public void setMainDish(String name,int price){
        for (int i=0;i<mainDish.length;i++){
            if (mainDish[i][0] == null){
                mainDish[i][0] = name;
                mainDish[i][1] = String.valueOf(price);
                break;
            }
        }
    }
    public void setAperatifs(String name,int price){
        for (int i=0;i<aperatifs.length;i++){
            if (aperatifs[i][0] == null){
                aperatifs[i][0] = name;
                aperatifs[i][1] = String.valueOf(price);
                break;
            }
        }
    }

    public void setDrinks(String name,int price){
        for (int i=0;i<aperatifs.length;i++){
            if (drinks[i][0] == null){
                drinks[i][0] = name;
                drinks[i][1] = String.valueOf(price);
                break;

            }
        }
    }

    public String[] getMainDish(int foodNumber) {
        String[] foodInfo = new String[]{mainDish[foodNumber][0],mainDish[foodNumber][1]};
        return foodInfo;
    }

    public String[] getAperatif(int aperatifNumber) {
        String[] foodInfo = new String[]{aperatifs[aperatifNumber][0],aperatifs[aperatifNumber][1]};
        return foodInfo;
    }

    public String[] getDrink(int drinkNumber) {
        String[] foodInfo = new String[]{drinks[drinkNumber][0],drinks[drinkNumber][1]};
        return foodInfo;
    }

}

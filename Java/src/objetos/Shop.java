

public class Shop

{
   
    


    

  
        public static void main(String[] args) {
            double tax = 0.2;
            double total = 0;
           Customer c1 = new Customer();
           c1.name="pinky";
           System.out.println("Hello"+c1.name);
           Clothing item1 = new Clothing("Blue Jacket", 20.9, "M");
           Clothing item2 = new Clothing("Orange T-shirt", 10.5, "S");
           System.out.println("Iteam 1 datos:"+" "+item1.description+" "+ item1.price+ " "+item1.size);
           System.out.println("Iteam 2 datos:"+" "+item2.description+" "+ item2.price+ " "+item2.size);
            total = ((item2.price*2)+item1)*(1+tax);
            System.out.println(total);
           

          
        }
    }

package objetos;
import personas.Customer;
import objetos.Clothing;
public class Shop

{
        public static void main(String[] args) {
            double tax = 0.2;
            double total;
           Customer c1 = new Customer();
           c1.setName("pinky");
           System.out.println("Hello"+c1.getName());
           Clothing item1 = new Clothing("Blue Jacket", 20.9, "M");
           Clothing item2 = new Clothing("Orange T-shirt", 10.5, "S");
           System.out.println("Iteam 1 datos:"+" "+item1.getDescription()+" "+ item1.getPrice()+ " "+item1.getSize());
           System.out.println("Iteam 2 datos:"+" "+item2.getDescription()+" "+ item2.getPrice()+ " "+item2.getSize());
            total = ((item2.getPrice()*2)+item1.getPrice())*(1+tax);
            System.out.println(total);
           

          
        }
    }

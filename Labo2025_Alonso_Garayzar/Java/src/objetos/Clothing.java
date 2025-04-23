package objetos;

public class Clothing

{

        private String description;
        private double price;
        private String size="M";

        public Clothing(String description, double price, String size){
                this.description=description;
                this.price=price;
                this.size=size;

        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public double getPrice() {
                return price;
        }

        public void setPrice(double price) {
                this.price = price;
        }

        public String getSize() {
                return size;
        }

        public void setSize(String size) {
                this.size = size;
        }

        public static void main(String[] args) {

          


          
        }
    }

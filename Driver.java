package ToyotaIventory;
import java.awt.*;
import javax.swing.*;

   public class Driver {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Toyota Inventory");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        Inventory in = new Inventory();
        in.addCar("Camry", 2000, 150000);
        in.addCar("Sienna", 2010, 90000);
        in.addCar("Camry", 2015, 50000);
        in.addCar("Corolla", 2025);
        in.addCar("Sienna", 2025);
        in.addCar("Camry", 2025);
        
        JTextArea output = new JTextArea(50, 30);
        output.setEditable(false);
        output.setText(in.printReport() +"\n**************************************************"
        + "\nUsed Cars:\n" + in.printAllUsedCars() +"\n**************************************************"
        + "\nNew Cars:\n" + in.printAllNewCars());
        
        JScrollPane scroll = new JScrollPane(output);
        frame.add(scroll);
        frame.setVisible(true);
    }
    
   }

// Car superclass
class Car {
    protected String carModel;
    protected int year;
    
    public Car(String carModel, int year) {
        this.carModel = carModel;
        this.year = year;
    }
    
    public double getPrice() {
        return 0.0;
    }
    
    public String toString() {
       return carModel + " manufactured in " + year + " Price = $" + String.format("%.1f", getPrice());
        
    }
}

// NewCar subclass
class NewCar extends Car {
    public NewCar(String carModel, int year) {
        super(carModel, year);
    }
    
    @Override
    public double getPrice() {
        switch (carModel) {
        case "Corolla":
            return 25000;
        case "Sienna":
            return 30000;
        case "Camry":
            return 35000;
        default:
            return 0;
        }
    }
    
}

//UsedCar subclass
class UsedCar extends Car {
    private int mileage;
    public UsedCar(String carModel, int year, int mileage) {
        super(carModel, year);
        this.mileage = mileage;
    }
    
    @Override
    public double getPrice() {
        double basePrice = 0;
        
        switch (carModel) {
        case "Corolla":
            basePrice = 25000;
            break;
        case "Sienna":
            basePrice = 30000;
            break;
        case "Camry":
            basePrice = 35000;
            break;
        }
        
        if (mileage < 30000) {
            return basePrice * 0.90;
        } 
        else if (mileage <= 60000) {
            return basePrice * 0.75;
        }
         else {
            return basePrice * 0.45;
          }
        }
        @Override
        public String toString() {
            return carModel + " manufactured in " + year + " Price = $" + String.format("%.1f", getPrice());
        }
            
    }
    
    // Inventory class
    class Inventory {
        private Car[] cars = new Car[100];
        private int count = 0;
        
        public void addCar(String model, int year) {
            cars[count++] = new NewCar(model, year);
        }
        
        public void addCar(String model, int year, int mileage) {
            cars[count++] = new UsedCar(model, year, mileage);
        }
        
        public String printReport() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append(cars[i].toString()).append("\n");
            }
            return sb.toString();
        }
        
        public String printAllUsedCars() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                if (cars[i] instanceof UsedCar){
                    sb.append(cars[i].toString()).append("\n");
                }
            }
            return sb.toString();
        }
        
        public String printAllNewCars() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                if (cars[i] instanceof NewCar){
                    sb.append(cars[i].toString()).append("\n");
                }    
            }
            return sb.toString();
        }
    }

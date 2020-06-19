package p3;

import p3.Cars.Car;
import p3.Customers.Customer;

import java.util.ArrayList;

public class BookKeeper implements Observer, DisplayElement { //acts as the observer and the display for this store
    private int dayRevenue;
    private int dayNumber;
    private int numRentalsToday;
    private int numActiveRentals;
    private ArrayList<RentalRecord> dayRentals;
    private ArrayList<RentalRecord> activeRentals;
    private ArrayList<Car> carsLeft;
    private String options[];
    private DayTracker dayInfo;

    public BookKeeper(DayTracker d) {
        this.dayInfo = d;
        dayInfo.registerObserver(this);
    }


    public void update(int dayRevenue, int dayNum, ArrayList<RentalRecord> dayRentals, ArrayList<RentalRecord> activeRentals, ArrayList<Car> carsLeft) {
        this.dayRevenue = dayRevenue;
        this.dayNumber = dayNum;
        this.numRentalsToday = dayRentals.size(); //updating info to display
        this.dayRentals = dayRentals;
        this.numActiveRentals = activeRentals.size();
        this.activeRentals = activeRentals;
        this.carsLeft = carsLeft;
        display();
    }

    public void display() { //printing the day's information. this is just a bunch of print statements
        System.out.println(" ------------------------ ");
        System.out.println("         Day " + dayNumber + "            ");
        System.out.println(" ------------------------ ");
        //printing info about rentals made this day
        System.out.println("    Rentals Today: " + numRentalsToday + "    ");
        System.out.println(" ------------------------ ");
        System.out.println("       Rental Info:       ");
        System.out.println(" ------------------------ ");
        for(RentalRecord rental : dayRentals) {
            Car car = rental.getCar();
            Customer cust = rental.getCustomer();
            System.out.println(" - " + car.getType() + " Car with Licence Plate No. " + car.getPlate() + " was rented to "
                    + cust.getCustomerType() + " Customer No. " + cust.getID() + " for " + rental.getDaysRented() +  " days, " +
                    "for a total fee of $" + rental.getTotalCost());
            System.out.println("     Options Added:     ");
            for(int i = 0; i < rental.getOptions().length; i++) {
                System.out.println("     " + rental.getOptions()[i] + "     ");
            }
            System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~ ");
        }
        //printing info about all active rentals
        System.out.println(" ------------------------ ");
        System.out.println("  Active Rentals Today: " + numActiveRentals + "  ");
        System.out.println(" ------------------------ ");
        System.out.println("   Active Rental Info:    ");
        System.out.println(" ------------------------ ");
        ArrayList<Customer> covered = new ArrayList<Customer>();
        for(RentalRecord active : activeRentals) {
            Customer cust = active.getCustomer();
            if(!covered.contains(cust)) {
                ArrayList<RentalRecord> holding = cust.getRentedCars();
                System.out.println(cust.getCustomerType() + " Customer " + cust.getID() + " has the following rentals: ");
                for(RentalRecord r : holding) {
                    Car c = r.getCar();
                    System.out.println(" - " + c.getType() + " Car, License Plate No. " + c.getPlate());
                }
                covered.add(cust);
                System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~ ");
            }
        }
        //printing remaining cars in inventory
        System.out.println(" ------------------------ ");
        System.out.println("  Cars Left in Inventory: " + carsLeft.size() + "  ");
        System.out.println(" ------------------------ ");
        System.out.println("     Cars Left Info:      ");
        System.out.println(" ------------------------ ");
        for( Car car : carsLeft ) {
            System.out.println(" - " + car.getType() + " Car, License Plate No. " + car.getPlate());
        }
        System.out.println(" ------------------------ ");
        System.out.println("    Total Made: $" + dayRevenue + "   ");
        System.out.println(" ------------------------ ");
    }
}

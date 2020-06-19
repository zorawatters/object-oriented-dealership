package p3.Customers;
import p3.Store;

import java.util.Map;

public class RegularCustomer extends Customer {

    public RegularCustomer(Store store) {
        super(store);
        custType = "Regular";
    }
    public String toString() {
        return "Regular Customer " + id;
    }
    public boolean canRent() {
        if((numCars < 3) && (store.inventorySize() > 0)) {
            return true;
        }
        return false;
    }

    public void rent() {
        //reg customers rent 1 to 3 cars at a time
        double carsRenting = (int)(Math.random()*((3-1)+1))+1; //choosing number of cars renting
        if((this.numCars + carsRenting) > 3) {
            carsRenting = (3 - this.numCars); //if the number of cars to be rented went over 3, just max out your rentals
        }
        if(store.inventorySize() < carsRenting) {
            carsRenting = store.inventorySize(); //if the number of cars is less than the amount the customer wants to rent, just rent out the whole stock
        }

        //setting time rented
        int chosenDays;
        if(this.rentedCars.isEmpty()) { //this is a check to see if the customer already has an active rental
            chosenDays = daysToRent(3, 5); //if not, random rental time can be assigned
        }
        else {
            chosenDays = this.rentedCars.get(0).getDaysLeft(); //but if it does, set the time to the days left, as all will be returned together
        }


        for(int i =0; i<carsRenting; i++) {
            Map<Integer, Object> car = store.randomCar(3, 5); //takes min days, max days
            store.rentCar(this, (String) car.get(0), chosenDays, (String[]) car.get(2));
            this.numCars++;
        }
    }
}
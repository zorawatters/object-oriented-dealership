package p3.Customers;

import p3.Cars.Car;
import p3.Store;

import java.util.Map;


public class BusinessCustomer extends Customer {

    public BusinessCustomer(Store store) {
        super(store);
        custType = "Business";
    }

    public String toString() {
        return "Business Customer " + id;
    }

    public boolean canRent() {
        if((numCars == 0) && (store.inventorySize() >= 3)) {
            return true;
        }
        return false;
    }

    public void rent() {
        for(int i = 0; i < 3; i++) { //business customers always rent 3 cars
            Map<Integer, Object> car = store.randomCar(7, 7); //takes min days, max days
            store.rentCar(this, (String)car.get(0), 7, (String[])car.get(2)); //muanually inputting days here since random is not an issue
            this.numCars++;
        }
    }
}

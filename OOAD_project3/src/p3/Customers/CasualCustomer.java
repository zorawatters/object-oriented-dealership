package p3.Customers;

import p3.Customers.Customer;
import p3.Store;

import java.util.Map;

public class CasualCustomer extends Customer {

	public CasualCustomer(Store store) {
		super(store);
		custType = "Casual";
	}
	public String toString() {
		return "Casual Customer " + this.id;
	}
	public boolean canRent() {
		if((this.numCars < 3) && (store.inventorySize() > 0)) {
			return true;
		}
		return false;
	}

	public void rent() {
 		//casual customers only rent 1 car at a time
		int chosenDays;
		if(this.rentedCars.isEmpty()) { //this is a check to see if the customer already has an active rental
			chosenDays = daysToRent(1, 3); //if not, random rental time can be assigned
		}
		else {
			chosenDays = this.rentedCars.get(0).getDaysLeft(); //but if it does, set the time to the days left, as all will be returned together
		}

		Map<Integer, Object> car = this.store.randomCar(1, 3); //takes min days, max days
		store.rentCar(this, (String)car.get(0), chosenDays, (String[])car.get(2));
		this.numCars++;
		//cars will be added to the rental array in store's rentCar if needed
	}
}

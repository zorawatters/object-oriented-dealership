package p3;

import java.util.HashMap;
import java.util.Map;

import p3.Customers.BusinessCustomer;
import p3.Customers.CasualCustomer;
import p3.Customers.Customer;
import p3.Customers.RegularCustomer;

public class Main {

	public static void main(String[] args) {
		int numCustomers = 12;
		Store store = new Store();
		BookKeeper bookKeeper = new BookKeeper(store);

		//this should have been done in a factory but so be it
		//3 business customers, 5 casual, 4 regular
		Customer[] customers = new Customer[numCustomers];
		customers[0] = new BusinessCustomer(store);
		customers[1] = new CasualCustomer(store);
		customers[2] = new RegularCustomer(store);
		customers[3] = new CasualCustomer(store);
		customers[4] = new BusinessCustomer(store);
		customers[5] = new RegularCustomer(store);
		customers[6] = new RegularCustomer(store);
		customers[7] = new CasualCustomer(store);
		customers[8] = new BusinessCustomer(store);
		customers[9] = new CasualCustomer(store);
		customers[10] = new RegularCustomer(store);
		customers[11] = new CasualCustomer(store);

		int day = 0;
		for (; day < 35; day++) {
			Customer activeCustomer;
			//any amount of customers can come in
			if(store.inventorySize() > 0) { //if inventory is zero we just wait til the next day
				for (int i = 0; i < (1 + (int)(Math.random() * numCustomers)); i++) { // 1 to all customers can come in
					activeCustomer = customers[(int) (Math.random() * numCustomers)]; //pick a random customer
					if (activeCustomer.canRent()) { //check to make sure the type of customer is able to rent at this time
						activeCustomer.rent();
					}
				}
			}
			store.passDay(); //move along the day in the store
		}
	}
}

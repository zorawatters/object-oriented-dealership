package p3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import p3.Cars.Car;
import p3.Customers.Customer;
//this is the big boi
public class Store implements DayTracker { //implementing subject interface for observer pattern
	private CarPool inventory;
	private ArrayList<RentalRecord> activeRentals;
	private ArrayList<RentalRecord> rentalHistory;
	private ArrayList<RentalRecord> dayRentals;
	private OptionFactory optionFactory;
	private ArrayList<Observer> observers; //to hold the observers
	private int totalRevenue;
	private int dayRevenue;
	private int dayNum;
	
	public Store() {
		inventory = new CarPool(24);
		activeRentals = new ArrayList<RentalRecord>();
		rentalHistory = new ArrayList<RentalRecord>();
		dayRentals = new ArrayList<RentalRecord>();
		optionFactory = new OptionFactory();
		observers = new ArrayList<Observer>();
		dayNum = 1;
	}

	public void registerObserver(Observer o) { //implement observer registration
		observers.add(o);
	} //observer func

	public void notifyObservers() { //observer func
		for(Observer o : observers) {
			o.update(this.dayRevenue, this.dayNum, dayRentals, activeRentals, inventory.getCars());
		}
	}

	public int inventorySize() {
		return inventory.getCars().size();
	}

	
	public void rentCar(Customer cust, String carType, int days, String[] options) {
		Car c = inventory.getCarByType(carType); //sees if car type is availiable, decorates, and adds to cust rent
		if(c != null) {
			for(int i = 0; i < options.length; i++) {

				c = optionFactory.addOption(c, options[i]); //adding chosen options - this is implementing the decorator
			}
			RentalRecord newRental = new RentalRecord(c, cust, days, options);
			cust.addRental(newRental);
			activeRentals.add(newRental);
			dayRevenue+=newRental.getTotalCost();
			dayRentals.add(newRental);
			//cust.rent(new Rental(c, days, new RentalListener[] {this, cust})
		} else {
			System.out.println("Unable to rent " + carType + " to " + cust + ", none left in inventory.");
		}
	}
	
	public void returnCar(Car c, RentalRecord r) {
		inventory.returnCar(c); //return car to the inventory
		//System.out.println("Removing " + c.toString() + " from active rentals.");
		r.getCustomer().returnCar(r); //if one cust has more than one car in its rentals, all will be returned with pass day
		//could alternatively do for loop to return all cars in customer rental array but this works too
		activeRentals.remove(r); //remove the rental record from active records
		rentalHistory.add(r); //and add it to the history
	}
	
	public void passDay() {
		this.totalRevenue += dayRevenue; //update total revenue with the day's cost
		for(int i = 0; i < activeRentals.size(); i++) {
			RentalRecord r = activeRentals.get(i);
			int daysLeft = r.getDaysLeft(); //get the amount of days the car has left
			daysLeft--; //decrement it by one since a day has passed
			if(daysLeft == 0) { //check if it will be returned in the morning
				returnCar(r.getCar(), r); //if it will, return
			}
			else {
				r.setDaysLeft(daysLeft);
			}
		}
		//update observer here
		notifyObservers(); //this is also the print statement for the day
		//reset day variables
		this.dayRevenue = 0;
		this.dayNum++;
		this.dayRentals.clear();
		if(dayNum >= 35) {
			finalDay();
		}
		return;
	}

	private void finalDay() {
		for(int i = 0; i < activeRentals.size(); i++ ) { //returning all cars to inventory and adding to rental history, as the shop is CLOSED
			RentalRecord a = activeRentals.get(i);
			returnCar(a.getCar(), a);
		}
		int totalRentals = rentalHistory.size();
		int businessRentals = 0;
		int casualRentals = 0;
		int regularRentals = 0;
		//getting number of rentals by customer type
		for( RentalRecord r : rentalHistory) {
			String custType = r.getCustomer().getCustomerType(); //checking customer type to add to classification
			if(custType == "Business") {
				businessRentals++;
			}
			else if(custType == "Casual") {
				casualRentals++;
			}
			else {
				regularRentals++;
			}
		}
		//print time, baby
		System.out.println(" ------------------------ ");
		System.out.println(" That was the end of the simulation! ");
		System.out.println(" ------------------------ ");
		System.out.println("  Total Completed Rentals: " + totalRentals + "  ");
		System.out.println("  Total Business Rentals: " + businessRentals + "  ");
		System.out.println("  Total Casual Rentals: " + casualRentals + "  ");
		System.out.println("  Total Regular Rentals: " + regularRentals + "  ");
		System.out.println("  Total Revenue: $" + this.totalRevenue + "    ");
	}

	public static Map<Integer, Object> randomCar(int minDays, int maxDays){
		String[] carTypes = {"Economy", "Standard", "Luxury", "SUV", "Minivan"};

		int numSeats = (int)(Math.random() * 4);
		int gps = (int)(Math.random()*2);
		int radio = (int)(Math.random()*2);
		String[] chosenOptions = new String[numSeats + gps + radio];
		int j = 0;
		if(gps > 0) {
			chosenOptions[j] = "GPS";
			j++;
		}
		if(radio > 0) {
			chosenOptions[j] = "Radio";
			j++;
		}
		for(int k = j; k < numSeats + j; k++) {
			chosenOptions[k] = "Car Seat";
		}
		String chosenType = carTypes[(int)(Math.random() * carTypes.length)];
		int chosenDays = minDays + (int)(Math.random() * (maxDays - minDays + 1)); //this is an unused return but oh well
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(0, chosenType);
		map.put(1, chosenDays);
		map.put(2, chosenOptions);
		return map;
	}

}

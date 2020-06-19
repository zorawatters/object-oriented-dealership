package p3;

import p3.Cars.Car;
import p3.Cars.CarDecorator;
import p3.Customers.Customer;

import java.util.ArrayList;

public class RentalRecord {
	private Car car;
	private Customer customer;
	private int daysRented;
	private int daysLeft;
	private double totalCost;
	private String options[];
	
	public RentalRecord(Car c, Customer cust, int days, String ops[]) { //this is what keeps track of each rental
		customer = cust;
		car = c;
		daysRented = days;
		daysLeft = days;
		totalCost = c.totalCost() * daysRented;
		options = ops;
	}
//just a bunch of getters and setters
	public String[] getOptions() {
		return options;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Car getCar() {
		return car;
	}

	public String toString() {
		return customer + " rented: " + car + " for " + daysRented + " days and paid $" + totalCost;
	}

	public int getDaysLeft() {
		return this.daysLeft;
	}

	public void setDaysLeft(int days) {
		this.daysLeft = days;
	}
}

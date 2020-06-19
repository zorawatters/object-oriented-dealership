package p3;

import java.util.ArrayList;

import p3.Cars.Car;
import p3.Cars.Economy;
import p3.Cars.Luxury;
import p3.Cars.Minivan;
import p3.Cars.SUV;
import p3.Cars.Standard;

public class CarPool { //car factory tied with car pool
	private ArrayList<Car> cars;
	
	public CarPool(int size) { //car pool initializes the types of cars in avail. at the store
		cars = new ArrayList<Car>(size);
		String[] carTypes = {"Economy", "Standard", "Luxury", "SUV", "Minivan"};
		for(int i = 0; i < size; i++) {
			cars.add(createCar(carTypes[(int)(Math.random()*5)]));
		}
	}

	//car SimpleFactory
	public Car createCar(String type) {
        Car car = null;
        //here is the factory method for cars
        if(type.equals("Economy")) {
            car = new Economy();
        } else if (type.equals("Standard")) {
            car = new Standard();
        } else if (type.equals("Luxury")) {
            car = new Luxury();
        } else if (type.equals("SUV")) {
            car = new SUV();
        } else if (type.equals("Minivan")) {
            car = new Minivan();
        }
        return car;
    }
	
	public Car getCarByType(String type) { //this function looks for the car being rented and removes it from the pool
		for(Car c : cars) {
			if(c.getType().equals(type)) {
				cars.remove(c);
				return c;
			}
		}
		return null;
	}

	public ArrayList<Car> getCars(){
		return cars;
	}
	public void returnCar(Car c) {
		cars.add(c);
		return;
	}
}

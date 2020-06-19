package p3.Cars;

public class CarSeatDecorator extends CarDecorator { //decorator
	public CarSeatDecorator(Car c) {
		super(c);
	}

	public String toString() {
		return car.toString() + ", "+ totalCost() + " with car seat";
	}

	public double totalCost() {
		return car.totalCost() + extraCost();
	}

	public double extraCost() {
		return 10;
	}

}

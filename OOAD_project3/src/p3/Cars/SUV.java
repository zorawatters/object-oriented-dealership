package p3.Cars;

public class SUV extends Car {

	public SUV() {
		super(50);
	}
	public String getType() {
		return "SUV";
	}
	
	//@Override
	public String toString() {
		return "SUV: " + plate + ", costs: " + totalCost();
	}

	public double totalCost() {
		return 50;
	}

}

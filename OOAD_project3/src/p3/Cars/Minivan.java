package p3.Cars;

public class Minivan extends Car {
	public Minivan() {
		super(45);
	}
	public String getType() {
		return "Minivan";
	}
	
	
	//@Override
	public String toString() {
		return "Minivan: " + plate + ", costs: " + totalCost();
	}

	public double totalCost() {
		return 45;
	}

}

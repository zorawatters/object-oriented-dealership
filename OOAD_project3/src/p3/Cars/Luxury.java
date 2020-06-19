package p3.Cars;

public class Luxury extends Car {
	
	public Luxury() {
		super(70);
	}
	public String getType() {
		return "Luxury";
	}
	
	
	//@Override
	public String toString() {
		return "Luxury: " + plate + ", costs: " + totalCost();
	}
	
	public double totalCost() {
		return 70;
	}
	
}

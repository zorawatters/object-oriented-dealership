package p3.Cars;

public class Standard extends Car {
	public Standard() {
		super(40);
	}
	public String getType() {
		return "Standard";
	}
	
	@Override
	public String toString() {
		return "Standard: " + plate + ", costs: " + totalCost();
	}

	public double totalCost() {
		return 40;
	}

}

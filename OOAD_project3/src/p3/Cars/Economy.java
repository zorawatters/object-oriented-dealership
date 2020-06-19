package p3.Cars;

public class Economy extends Car {
	
	public Economy() {
		super(30);
	}
	public String getType() {
		return "Economy";
	}
	
	public String toString() {
		return "Economy: " + plate + ", costs: " + totalCost();
	}
	public double totalCost(){
		return 30;
	}

}

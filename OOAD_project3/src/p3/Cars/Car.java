package p3.Cars;

public abstract class Car { //baseline for each car
	protected String plate;
	protected double costPerDay;
	public Car(double costPerDay) { //used in car pool factory
		plate = randomPlate();
		this.costPerDay = costPerDay;
		
	}
	
	private String randomPlate() {
		String result = "";
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String nums = "";
		for(int i = 0; i < 3; i++) {
			result += letters.charAt((int)(Math.random() * 26));
			nums += Integer.toString((int)(Math.random() * 10));
		}
		result += nums;
		return result;
	}

	public String getPlate() { return this.plate; }

	public abstract String getType();
	
	public abstract String toString();

	public abstract double totalCost();
}

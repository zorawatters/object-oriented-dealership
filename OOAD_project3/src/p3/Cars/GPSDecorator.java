
package p3.Cars;

public class GPSDecorator extends CarDecorator{ //decorates w car seat
    public GPSDecorator(Car c) {
        super(c);
    }


    public String toString() {
        return car.toString() + ", "+ totalCost() + " with GPS";
    }

    public double totalCost() {
        return car.totalCost() + extraCost();
    }

    public double extraCost() {
        return 15;
    }
}

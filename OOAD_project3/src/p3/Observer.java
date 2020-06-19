package p3;

import p3.Cars.Car;

import java.util.ArrayList;

public interface Observer { //observer pattern lives here and in daytracker, display element
    public void update(int dayRevenue, int dayNum, ArrayList<RentalRecord> dayRentals, ArrayList<RentalRecord> activeRentals, ArrayList<Car> carsLeft); //
}

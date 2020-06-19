package p3;

import p3.Cars.*;

public class OptionFactory { //simple factory for options
	public Car addOption(Car c, String option) {
		if(option.equals("GPS")) {
			return new GPSDecorator(c);
		}else if(option.equals("Radio")) {
			return new RadioDecorator(c);
		}else if(option.equals("Car Seat")) {
			return new CarSeatDecorator(c);
		}
		return c;
	}
}

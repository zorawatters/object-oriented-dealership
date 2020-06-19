package p3;

public interface DayTracker { //here is the 'Subject' part of the observer pattern
	public void registerObserver(Observer o);
	public void notifyObservers();
	//in this scenario we do not need a remove observer function, as the only observer will be the store's bookeeper
}

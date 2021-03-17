package weather;

public interface WeatherSubject 
{
	/**
	 * adds one observer.
	 * @param observer object.
	 */
	public void addObserver(WeatherObserver observer);
	/**
	 * notify observer.
	 * @param weather object to be notify.
	 */
	public void notifyObserver(WeatherDatum weather);
	/**
	 * 
	 * @param observer to be remove from list.
	 */
	public void removeObserver(WeatherObserver observer);
	

}

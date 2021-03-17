package weather;

/**
 * 
 * @author Bunguiu Norales
 *
 */
public interface WeatherObserver 
{
	/**
	 * reset list.
	 */
	public void reset();
	
	/**
	 * add the given WeatherDatum to the collection if it is non-null, otherwise it must do
	 * nothing.
	 * @param datum to be added.
	 */
	public void handleWeatherDatum(WeatherDatum datum);

}

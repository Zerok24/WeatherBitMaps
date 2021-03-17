package weather;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Bunguiu Norales
 *
 */
public class WeatherPattern implements WeatherObserver,Iterable<WeatherDatum>
{
	
	protected String description;
	protected List<WeatherDatum> data;
	
	/**
	 * Default constructor.
	 * @param description of the object.
	 */
	public WeatherPattern(final String description) 
	{
		
		this.description = description;
		data = new ArrayList<WeatherDatum>();
	}
	
	
	/**
	 * Gets elements from  list.
	 * @param index to search for in the list.
	 * @return element at index from the list.
	 */
	public WeatherDatum getElement(final int index) 
	{
		return data.get(index);
	}
	
	/**
	 * add the given WeatherDatum to the collection if it is non-null, otherwise it must do
	 * nothing.
	 * @param datum to be added to the list.
	 */
	public void handleWeatherDatum(final WeatherDatum datum)
	{
		
		if (datum != null) 
		{
			data.add(datum);
		}
		
	}
	
	/**
	 * Iterator for the list.
	 * @return Iterator.
	 */
	public Iterator<WeatherDatum> iterator()
	{	
		return data.iterator();		
	}
	
	/**
	 * reset data from the list.
	 */
	public void reset() 
	{
		data.clear();
	}
	
	/**
	 * return the size of the list.
	 * @return size of list.
	 */
	public int size() 
	{
		return data.size();
		
	}
	
	/**
	 *  get the description of the weather patter object.
	 * @return description.
	 */
	public String getDescription() 
	{
		return this.description;
	}
	
}

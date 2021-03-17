package weather.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import weather.WeatherDatum;
import weather.WeatherObserver;
import weather.WeatherSubject;

/**
 * 
 * @author Bunguiu Norales
 *
 */
public abstract class WeatherDatumReader implements WeatherSubject
{
	
	protected BufferedReader reader;
	protected List<WeatherObserver> observers;
	
	/**
	 * Default constructor.
	 * @param reader to read the file
	 */
	public WeatherDatumReader(final BufferedReader reader) 
	{
		this.reader = reader;
		observers = new ArrayList<WeatherObserver>();
	}
	
	
	/**
	 * Add an observer to the list.
	 * @param observer to be add.
	 */
	@Override
	public void addObserver(final WeatherObserver observer)
	{
		this.observers.add(observer);
	}
	
	/**
	 * Notifies all of the observers.
	 * @param weatherDatum object to be notify.
	 */
	@Override
	public void notifyObserver(final WeatherDatum weatherDatum)
	{
		
		Iterator<WeatherObserver> i = observers.iterator();
		while (i.hasNext())
		{
			WeatherObserver observer = i.next();
			observer.handleWeatherDatum(weatherDatum);
		}
	}
	
	/**
	 * Remove observers from the list.
	 * @param observer to be remove from list.
	 */
	@Override
	public void removeObserver(final WeatherObserver observer)
	{
		this.observers.remove(observer);
	}
	
	/**
	 * Abstract class to read from file.
	 * @return WeatherDatum object.
	 * @throws IOException if failed to read file.
	 */
	protected abstract WeatherDatum readWeatherDatum() throws IOException;
	
	/**
	 * Read one object from the file.
	 * @throws IOException if failed to read file.
	 */
	public void readOne() throws IOException
	{
		WeatherDatum datum = this.readWeatherDatum();
		
		if (datum != null) 
		{
			notifyObserver(datum);
		}
	}
	
	/**
	 * Read all weatherDatum object from the file.
	 * @throws IOException if failed to read file.
	 */
	public void readAll() throws IOException
	{
		WeatherDatum datum = this.readWeatherDatum();
		
		while(datum != null) 
		{
			notifyObserver(datum);
			datum = this.readWeatherDatum();
		}
	}

}

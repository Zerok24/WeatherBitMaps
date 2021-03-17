package gui;

import javax.swing.JTextArea;

import weather.WeatherDatum;
import weather.WeatherObserver;

public class WeatherBoard extends JTextArea implements WeatherObserver
{

	/**
	 * Seriation.
	 */
	private static final long serialVersionUID = 1L;

	
	public WeatherBoard() 
	{
		
	}
	
	/**
	 * Writes to the text area.
	 */
	@Override
	public void handleWeatherDatum(WeatherDatum datum) 
	{
		
		setText(getText() + datum.toString(true)+ "\n");	
	}

	/**
	 * resets text Area.
	 */
	@Override
	public void reset() 
	{
		
		setText("");
		
	}
		
	
}

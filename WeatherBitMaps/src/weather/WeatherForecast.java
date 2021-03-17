package weather;

import java.util.StringTokenizer;

import measurement.Temperature;

/**
 * This code complies with JMU honor code.
 * @author Bunguiu Norales
 *
 */
public class WeatherForecast extends WeatherDatum
{
	
	protected Temperature high;
	protected Temperature low;
	private String comma = ","; 
	
	/**
	 * Default constructor.
	 */
	public WeatherForecast()  
	{
		super("XXX", "Unknown");
		this.low = new Temperature();
		this.high = new Temperature();
	}
	
	/**
	 * Creates a WeatherForecast object.
	 * @param location of weather.
	 * @param condition of weather.
	 * @param low temperature.
	 * @param high temperature.
	 */
	public WeatherForecast(final String location, final String condition,
			final Temperature low, final Temperature high)
	{
		super(location, condition);
		this.low = low;
		this.high = high;
	}
	
	/**
	 * Creates a WeatherForecast object from a string.
	 * @param s string to be parse to object
	 * @return WeatherForecast object.
	 */
	public static WeatherForecast createWeatherForecast(final String s) 
	{
		
		WeatherForecast forecast = new WeatherForecast();
		forecast.fromString(s);
		return forecast;
		
	}
	
	/**
	 * parses the terse String representation of a WeatherForecast.
	 * @param s string to be parse.
	 * @return StringTokenizer used to tokinized the string
	 */
	public StringTokenizer fromString(final String s) 
	{
		StringTokenizer tokenizer;
		if (s != null && !s.equals(""))
		{
			tokenizer = new StringTokenizer(s,comma,false);
		}else 
		{
			return null;
		}

		if (tokenizer.countTokens() == 1)
		{
			super.fromString(tokenizer.nextToken());
				
		}else if (tokenizer.countTokens() == 2) 
		{
			super.fromString(tokenizer.nextToken() 
					+ comma + tokenizer.nextToken());
		}else if (tokenizer.countTokens() == 3) 
		{
			super.fromString(tokenizer.nextToken() + comma + tokenizer.nextToken());
			low = Temperature.createTemperature(tokenizer.nextToken());
			
		}else 
		{
			super.fromString(tokenizer.nextToken() + comma + tokenizer.nextToken());
			low = Temperature.createTemperature(tokenizer.nextToken());
			high= Temperature.createTemperature(tokenizer.nextToken());
			
			return tokenizer;
		}
			
		return null;
	
	}
		
	
	/**
	 * Gets the high temperature.
	 * @return the high temperature.
	 */
	public Temperature getHigh() 
	{
		return high;
		
	}
	
	/**
	 * Gets the low temperature.
	 * @return low temperature.
	 */
	public Temperature getLow() 
	{
		return low;
		
	}
	
	/**
	 * String representation of the object.
	 * @param verbose or terse.
	 * @return String representation of the object.
	 */
	public String toString(final boolean verbose) 
	{
		if (!verbose) 
		{
			return  super.toString(false) 
					+comma + low.toString() + comma + high.toString();
		}
		else 
		{
			return super.toString(true) + "\tLow: " + low.toString() 
				+ "\tHigh: "+ high.toString();
			
		}
		
	}

}

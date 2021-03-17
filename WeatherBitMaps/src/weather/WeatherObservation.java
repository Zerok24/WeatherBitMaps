package weather;

import java.util.StringTokenizer;

import measurement.Temperature;

/**
 * This code complies with JMU honor code.
 * @author Bunguiu Norales
 *
 */
public class WeatherObservation extends WeatherDatum 
{
	
	protected Temperature temperature;
	private String comma = ",";
	
	/**
	 * Default constructor.
	 */
	public WeatherObservation() 
	{
		super("XXX", "Unknown");
		this.temperature = new Temperature();
		
	}
	
	/**
	 * Creates a WeatherObservation object.
	 * @param location of weather.
	 * @param condition of weather.
	 * @param temperature of the place.
	 */
	public WeatherObservation(final String location, final String condition,
			final Temperature temperature)
	{
		super(location, condition);
		this.temperature = temperature;
		
	}
	
	/**
	 * Creates a WeatherObservation object.
	 * @param s string to be parse.
	 * @return WeatherObservation object.
	 */
	public static WeatherObservation createWeatherObservation(final String s)
	{
		WeatherObservation observation = new WeatherObservation();
		observation.fromString(s);
		
		return observation;
	}
	
	
	/**
	 * parses the terse String representation of a WeatherObservation.
	 * @param s string to be parse.
	 * @return String used to tokenize the string.
	 */
	public StringTokenizer fromString(final String s)
	{
		
		StringTokenizer tokenizer;
		if (s != null && !s.equals(""))
		{
			tokenizer= new StringTokenizer(s,comma,false);
		}else 
		{
			return null;
		}
		
		if (tokenizer.countTokens() == 1)
		{
			super.fromString(tokenizer.nextToken());
		}else if (tokenizer.countTokens() == 2) 
		{
			super.fromString(tokenizer.nextToken() + comma + tokenizer.nextToken());
		}else 
		{
			super.fromString(tokenizer.nextToken() + comma + tokenizer.nextToken());
			this.temperature = Temperature.createTemperature(tokenizer.nextToken());
			return tokenizer;
		}
		
		return null;
	}
	
	/**
	 * Gets the temperature of the object.
	 * @return the temperature.
	 */
	public Temperature getTemperature()
	{
		return temperature;
	}
	
	/**
	 * String representation of the object.
	 * @param verbose or terse.
	 * @return String representation of the object.
	 */
	public String toString(final boolean verbose)
	{
		
		if (verbose) 
		{
			return super.toString(true) +"\tTemperature: "+ temperature.toString();
		}else
		{
			return super.toString(false) + comma + temperature.toString();
		}
		
	}

}

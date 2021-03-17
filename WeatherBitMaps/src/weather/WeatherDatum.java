package weather;

import java.util.StringTokenizer;

/**
 * This code complies with JMU honor code.
 * @author Bunguiu Norales
 *
 */
public abstract class  WeatherDatum 
{
	
	protected String condition;
	protected String location;
	private String comma = ",";
	
	/**
	 * creates WeatherDatum.
	 * @param location of weather.
	 * @param condition of weather.
	 */
	public WeatherDatum(final String location, final String condition) 
	{
		this.location = location;
		this.condition = condition;
	}
	
	/**
	 * parses the terse String representation of a WeatherDatum.
	 * @param s String literal to be parse.
	 * @return String used to tokenize the string.
	 */
	protected StringTokenizer fromString(final String s) 
	{
		StringTokenizer tokenizer;
		tokenizer = new StringTokenizer(s,comma,false);
			
		String temp = tokenizer.nextToken();
		this.location = temp;
		
			
		if (tokenizer.hasMoreTokens()) 
		{
			temp = tokenizer.nextToken();
		}else 
		{
			temp = "";
		}
			
			
		if (!temp.equals("") )
		{
			this.condition = temp;
		}
			
		return tokenizer;
		
	}
	
	/**
	 * Gets the condition of the object.
	 * @return condition.
	 */
	public String getCondition() 
	{
		return condition;
	}
	
	/**
	 * Gets location of the object.
	 * @return location.
	 */
	public String getLocation() 
	{
		return location;
	}
	
	/**
	 * String representation of the object.
	 * @param verbose or terse.
	 * @return String representation of the object.
	 */
	public  String toString(final boolean verbose) 
	{
		
		String result;

		if (verbose) 
	      result = String.format("Location: %s\tCondition: %s", 
	          location.toString(), condition.toString());
		else
	      result = String.format("%s,%s", 
	          location.toString(), condition.toString());

		return result;
		
	}
	
	/**
	 * String representation of the object.
	 * @return String representation of the object.
	 */
	@Override
	public String toString() 
	{
		return this.toString(false);

	}
	
	

}

package measurement;

public class Temperature implements Comparable<Temperature>
{
	
	private double degrees;
	private Scale scale;
	private String f = "F";
	private String c = "C";
	
	/**
	 * default constructor.
	 */
	public Temperature() 
	{
		this.degrees = 0.0;
		this.scale = Scale.F;
	}
	
	/**
	 * copies a given temperature.
	 * @param other temperature to be copy.
	 */
	public Temperature(final Temperature other) 
	{
		degrees = other.degrees;
		scale = other.scale;
	}
	
	/**
	 * creates a temperature object.
	 * @param degrees of the current temperature
	 */
	public Temperature (final double degrees) 
	{
		this.degrees = degrees;
		this.scale = Scale.F;
	}
	
	/**
	 * creates a temperature object.
	 * @param degrees of the current temperature
	 * @param scale to be used.
	 */
	public Temperature(final double degrees,final String scale) 
	{
		if (Scale.createScale(scale) == null) 
		{
			this.degrees = degrees;
			this.scale = Scale.F;
		}else 
		{
			this.degrees = degrees;
			this.scale = Scale.createScale(scale);
		}
		
	}
	
	/**
	 * creates a temperature object.
	 * @param degrees of the current temperature
	 * @param scale to be used.
	 */
	public Temperature(final double degrees,final Scale scale) 
	{
		if (scale == null) 
		{
			this.degrees = degrees;
			this.scale = Scale.F;
		}else 
		{
			this.degrees = degrees;
			this.scale = scale;
		}
	}
	
	/**
	 * parse a string to create a temperature.
	 * @param s string to be parse
	 * @return Temperature object
	 */
	public static Temperature createTemperature(final String s) 
	{
		if (s != null) 
		{
			if ( !s.equals("") && s.length() == 7 
					&& (s.contains("+") 
							|| s.contains("-") ) ) 
			{
				
				
				Temperature temperature = new Temperature();
				temperature.fromString(s.substring(0,s.length()-1)
						+s.substring(s.length()-1));
				return temperature;
				
				
				
			}else 
			{
				return new Temperature();
			}
			
		}
		return new Temperature();
		
		
	}
	
	/**
	 * compares 2 temperature objects.
	 * @param other Temperature object to be compared with
	 * @return comparison
	 */
	public int compareTo(final Temperature other)
	{
		
		if (other!= null) 
		{
			if (scale.toString().equals(other.scale.toString())) 
			{
				Double otherDouble = Double.parseDouble(other.toString()
							.substring(0,other.toString().length()-1));
					
				Double currentDouble = Double.parseDouble(toString()
							.substring(0,toString().length()-1));
					
				return currentDouble.compareTo(otherDouble);
				
			}
			if (scale.toString().equals(c) ) 
			{
				
				Double otherValueToC = Math.ceil((other.degrees-32)*5/9);
				
				Double currentDouble = degrees;
				
				return currentDouble.compareTo(otherValueToC);
			
			}else  
			{
				Double otherValueToF = (other.degrees* 9/5)+32;
				
				Double currentDouble = degrees;
				
				return currentDouble.compareTo(otherValueToF);
				
			}
		}

		return -1;
	}
	
	/**
	 * decreases current temperature to the amount specified.
	 * @param amount to be increased
	 */
	public void decreaseBy(final Temperature amount) 
	{
		

		// from F to C
		if (scale.toString().equals(c) 
				&& amount.scale.toString().equals(f) ) 
		{

			double value = (amount.degrees-32)*5/9;
			this.degrees -= value;
		}else if (scale.toString().equals(f) 
				&& amount.scale.toString().equals(c)) 
		{// from C to F
				
			double value = (amount.degrees* 9/5)+32;
			this.degrees -= value;
		}
		if (scale.toString().equals(amount.scale.toString())) 
		{
			this.degrees -= amount.degrees;
		}	
			
	}
	
	/**
	 * creates a temperature object from a string.
	 * @param s string to be convert to a temperature object
	 */
	public void fromString(final String s) 
	{
		try 
		{
			if (s != null) 
			{
				if (s.toLowerCase().substring(s.length()-1).equals(c.toLowerCase()) 
					  || s.toLowerCase().substring(s.length()-1)
					  .equals(f.toLowerCase())) 
				{
					scale = Scale.createScale(s.substring(s.length()-1));
					degrees = Double.parseDouble(s.substring(0,s.length()-1));
				}
			}
			
		} catch (NumberFormatException e) 
		{
			
		}
	}
	
	/**
	 * increases the current temperature object by the amount.
	 * @param amount to be increased
	 */
	public void increaseBy(final Temperature amount) 
	{
		if (amount!= null) 
		{
			// from F to C
			if (scale.toString().equals(c) 
					&& amount.scale.toString().equals(f) ) 
			{
	
				double value = (amount.degrees-32)*5/9;
				this.degrees += value;
			}else if (scale.toString().equals(f) 
					&& amount.scale.toString().equals(c)) 
			{// from C to F
					
				double value = (amount.degrees* 9/5)+32;
				this.degrees += value;
			}
			if (scale.toString().equals(amount.scale.toString())) 
			{
				this.degrees += amount.degrees;
			}	
		}

	}
	
	/**
	 * increases the current temperature object by the amount.
	 */
	@Override
	public String toString() 
	{
		String convert = String.format("%+.1f", degrees);
		String format = "%6s%s";
		if (this.degrees >= 0 ) 
		{
			return String.format(format,convert,scale.toString());
		}else 
		{
			return String.format(format, convert,scale.toString());
		}
		
		
	}
	
	/**
	 * representation of the owning Temperature using the given scale.
	 * @param scaleToUse scale to use.
	 * @return  representation of the owning Temperature using the given scale.
	 */
	public String toString(final Scale scaleToUse) 
	{
		if (scaleToUse != null) 
		{
			if (scale.toString().equals(scaleToUse.toString())) 
			{
				return this.toString(); 
			}else if (scale.toString().equals(f) ) 
			{
				double value = (degrees-32)*5/9;
				this.degrees = value;
				this.scale = scaleToUse;
				return this.toString();
				
			}else 
			{
				double value = (degrees* 9/5)+32;
				
				this.degrees = value;
				this.scale = scaleToUse;
				return this.toString();
			}
		}else 
		{
			return this.toString();
		}
		
	}
}

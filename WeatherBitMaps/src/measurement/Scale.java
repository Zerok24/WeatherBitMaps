package measurement;

/**
 * 
 * @author Bunguiu Norales
 *
 */
public enum Scale 
{
	C("C","Celsius"),
	F("F","Fahrenheit");
	
	private final String abbreviation;
	private final String description;
	
	/**
	 * private constructor for the class.
	 * @param abbreviation of scale
	 * @param description of the scale
	 */
	private Scale(final String abbreviation, final String description) 
	{
		
		this.abbreviation = abbreviation;
		this.description = description;
	}
	
	/**
	 * create a Scale object.
	 * @param s string representation to create a scale
	 * @return Scale
	 */
	public static Scale createScale(final String s) 
	{
		if (s ==null)
		{
			return null;
		}
		if (s.toLowerCase().equals(C.abbreviation.toLowerCase()) 
				|| s.toLowerCase().equals(C.description.toLowerCase())) 
		{
			return C;
		}else if (s.toLowerCase().equals(F.abbreviation.toLowerCase()) 
				|| s.toLowerCase().equals(F.description.toLowerCase())) 
		{
			return F;
		}else 
		{
			return null;
		}
		
	}
	/**
	 * String representation of the instance.
	 * @param verbose or not
	 * @return String representation
	 */
	public String toString(final boolean verbose) 
	{
		
		if (verbose) 
		{
			return description;
		}else 
		{
			return abbreviation;
		}
		
		
	}
	
	/**
	 * String representation of the instance.
	 * @return String representation 
	 */
	public String toString() 
	{
		return abbreviation;
	}

}

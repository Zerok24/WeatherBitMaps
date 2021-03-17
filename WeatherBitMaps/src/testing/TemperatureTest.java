package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import measurement.Scale;
import measurement.Temperature;

class TemperatureTest 
{
	
	@Test
	void testCreateTemperature() 
	{
		Temperature t = Temperature.createTemperature("+57.9F");
		assertEquals("  +0.0F", t.toString());
		
		Temperature temperature = Temperature.createTemperature("");
		assertEquals("  +0.0F", temperature.toString());
		temperature = Temperature.createTemperature(null);
		assertEquals("  +0.0F", temperature.toString());
		
		temperature = new Temperature(-90,"F");
		assertEquals(" -90.0F", temperature.toString());
		
		temperature = Temperature.createTemperature(" 90.0F");
		assertEquals("  +0.0F", temperature.toString());
		
		temperature = Temperature.createTemperature(" -90.0F");
		
		temperature = Temperature.createTemperature("  90.0F");
		assertEquals("  +0.0F", temperature.toString());
		
	  
	}
	
	@Test
	void testConstructors() 
	{
		
		Temperature temperature = new Temperature();
		assertEquals("  +0.0F", temperature.toString());
		
		temperature = new Temperature(90.0, "F");
		assertEquals(" +90.0F", temperature.toString());
		
		temperature = new Temperature(-90.0, "F");
		assertEquals(" -90.0F", temperature.toString());
		
		temperature= new Temperature(90.0, Scale.createScale(null));
	}
	

	@Test
	void testDecreasBy() 
	{
		String temperature = Temperature.createTemperature("-107.1F").toString();
		
		Temperature temp = new Temperature(9000.0);
//		assertEquals("+900.0", temp.toString());
		temp.toString(Scale.createScale("C"));
		
		Temperature test = new Temperature();
		Temperature test2 = new Temperature(90.0);
		assertEquals("+107.1F", Temperature.createTemperature("+107.1F").toString());
		
		Temperature titulo = new Temperature(900.0, "F");
		titulo.decreaseBy(new Temperature(1.0, "C"));
		assertEquals("+866.2F", titulo.toString());
		
		
		titulo.decreaseBy(new Temperature(1.0,"F"));
		assertEquals("+865.2F", titulo.toString());
		
		test2 = new Temperature(90.0, "C");
		test2.decreaseBy(new Temperature(33.8, "F"));
		assertEquals(" +89.0C", test2.toString());
		
		test2.decreaseBy(new Temperature(1, "C"));
		assertEquals(" +88.0C", test2.toString());
		
		
		
		
	}
	
	@Test
	void testFromString() 
	{
		Temperature temperature = new Temperature(100.0,"C");
		temperature.fromString("+90.1F");
		assertEquals(" +90.1F", temperature.toString() );
		
		temperature.fromString("-80.0C");
		assertEquals(" -80.0C", temperature.toString() );
		
		temperature.fromString("+100.0C");
		temperature.fromString("-900.0G");
		assertEquals("+100.0C", temperature.toString());
		temperature.fromString("1p00.0C");
		
		Temperature copy = new Temperature(temperature);
		
		assertEquals(temperature.toString(), copy.toString());
		
		copy.fromString(null);
		
	}
	
	@Test
	void testIncreaseBy() 
	{
		Temperature temperature = new Temperature(80,"F");
		temperature.increaseBy(new Temperature(20, "F"));
		assertEquals("+100.0F", temperature.toString()); //F F
		
		// F C
		temperature.increaseBy(new Temperature(1,"C"));
		assertEquals("+133.8F", temperature.toString());
		
		// C F
		Temperature test2 = new Temperature(100.0,"C");
		test2.increaseBy(new Temperature(1,"F"));
		assertEquals(" +82.8C", test2.toString());
		
		// C C
		test2.increaseBy(new Temperature(8.2,"C"));
		assertEquals(" +91.0C", test2.toString());
		
		test2.increaseBy(null);
	}
	
	@Test
	void testCompareTo()
	{
		
		Temperature temperature = new Temperature(30,"C");
		Temperature other = new Temperature(30,"C");
		
		assertEquals(0, temperature.compareTo(other));
		
		temperature = new Temperature(1,"C");
		other = new Temperature(33.8,"F");
		assertEquals(0, temperature.compareTo(other));
		
		temperature = new Temperature(33.8,"F");
		other = new Temperature(1,"C");
		assertEquals(0,temperature.compareTo(other) );
		
		temperature= new Temperature(30,"F");
		other = new Temperature(30,"F");
		assertEquals(0, temperature.compareTo(other));
		
		other = new Temperature(30,"");
		assertEquals(0, temperature.compareTo(other));
		assertEquals(-1, temperature.compareTo(null));
	}
	
	@Test
	void testToStringScale() 
	{
		
		Temperature temperature = new Temperature(90.0);
		assertEquals(" +90.0F", temperature.toString(Scale.F) );
		
		// F to C
		assertEquals(" -17.2C", new Temperature(1).toString(Scale.C));
		assertEquals(" +35.0C", new Temperature(95).toString(Scale.C));
		
		//C to F
		assertEquals(" +86.0F", new Temperature(30,Scale.C).toString(Scale.F));
		assertEquals("+194.0F", new Temperature(90,Scale.C).toString(Scale.F));
		
		temperature.toString(null);
	}
	

}

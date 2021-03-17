package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import measurement.*;
class ScaleTest
{
	
	private static Scale scale;

	
	@Test
	void testCreateScale() 
	{
		//test Invalid
		scale = Scale.createScale("");
		assertEquals(null, scale);
		scale = Scale.createScale("FAHRENHEIT ");
		assertEquals(null,scale);
		scale = Scale.createScale("F ");
		assertEquals(null,scale);
		scale = Scale.createScale(" F");
		assertEquals(null,scale);
		scale = Scale.createScale("C ");
		assertEquals(null, scale);
		scale = Scale.createScale(" C");
		assertEquals(null, scale);
		scale = Scale.createScale("null");
		assertEquals(null, scale);
		scale = Scale.createScale("Fahren heit");
		assertEquals(null, scale);
		scale = Scale.createScale("FF");
		assertEquals(null, scale);
		scale = Scale.createScale(null);
		assertEquals(null, scale);
		
		
		
		
		//test valid
		scale = Scale.createScale("FAHRENHEIT");
		assertEquals("F",scale.toString() );
		assertEquals("Fahrenheit",scale.toString(true) );
		
		scale = Scale.createScale("fahrenheit");
		assertEquals("Fahrenheit", scale.toString(true));
		
		scale = Scale.createScale("Fahrenheit");
		assertEquals("Fahrenheit", scale.toString(true));
		assertEquals("F", scale.toString());
		
		
		// test F
		scale = Scale.createScale("F");
		assertEquals("F",scale.toString() );
		assertEquals("F",scale.F.toString(false) );
		assertEquals("Fahrenheit",scale.toString(true) );
		
		// test Fahrenheit
		scale = Scale.createScale("Fahrenheit");
		assertEquals("F",scale.toString() );
		assertEquals("F",scale.F.toString(false) );
		assertEquals("Fahrenheit",scale.toString(true) );
		
		//test empty
		scale = Scale.createScale("");
		assertEquals(null, scale);
		
		//test C
		scale = Scale.createScale("C");
		assertEquals("C",scale.toString() );
		assertEquals("C",scale.C.toString(false) );
		assertEquals("Celsius",scale.toString(true) );
		
		// test Celcius
		scale = Scale.createScale("Celsius");
		assertEquals("C",scale.toString() );
		assertEquals("C",scale.C.toString(false) );
		assertEquals("Celsius",scale.toString(true) );

		
		assertEquals("Celsius", scale.toString(true));
		scale = Scale.createScale("Fahrenheit");
		assertEquals("Fahrenheit", scale.toString(true));
		assertEquals("F", scale.toString(false));
		assertEquals("F", scale.toString());
		assertEquals("Celsius", Scale.C.toString(true));
		assertEquals("C", Scale.C.toString(false));
		assertEquals("Fahrenheit", Scale.F.toString(true));
		assertEquals("F", Scale.F.toString(false));
		
	}

}

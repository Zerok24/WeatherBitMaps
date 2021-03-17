package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import measurement.Temperature;
import weather.*;

class WeatherForecastTest 
{

	@Test
	void testConstructor() 
	{
		
		WeatherForecast forecast = new WeatherForecast();
		forecast = new WeatherForecast("PWW02", "Sunny", 
				new Temperature(), new Temperature());
		
		assertEquals("PWW02,Sunny,  +0.0F,  +0.0F", forecast.toString(false));
		assertEquals("Location: PWW02\tCondition: Sunny\tLow:   +0.0F\tHigh:   +0.0F",
					 
				forecast.toString(true));
		
		WeatherForecast f = new WeatherForecast("PWW01", "Sunny", 
	            new Temperature(74.2), new Temperature(91.3));
	      	  
		assertEquals("PWW01,Sunny, +74.2F, +91.3F",
	            f.toString(false));  
	  assertEquals("PWW01,Sunny, +74.2F, +91.3F",
	            f.toString());
	  

	  
	  
	  
	} 
	
	@Test
	void testCreate() 
	{
		
		WeatherForecast f = new WeatherForecast("PWW01", "Sunny", 
		        new Temperature(74.2), new Temperature(91.3));
		
		
		assertEquals("Location: PWW01\tCondition: Sunny\tLow:  +74.2F\tHigh:  +91.3F",
		        f.toString(true));
		
		WeatherForecast forecast = WeatherForecast
				.createWeatherForecast("PWW01,Sunny,  +0.0,  +0.0");
		
		assertEquals("Location: PWW01\tCondition: Sunny\tLow:   +0.0F\tHigh:   +0.0F",
				forecast.toString(true));
		
		forecast = WeatherForecast.createWeatherForecast(null);
		
		assertEquals("XXX,Unknown,  +0.0F,  +0.0F", forecast.toString(false));
		forecast = WeatherForecast.createWeatherForecast(",Sunny");
		
		//-------------Testing for bad stuff------------------------------------
		
		WeatherForecast weatherForecast = WeatherForecast
				.createWeatherForecast("PWWW1,Sunny,  +0.0,  +0.0");
		System.out.println(weatherForecast.toString(false));
		assertEquals("PWWW1,Sunny,  +0.0F,  +0.0F", weatherForecast.toString(false));
		assertEquals("  +0.0F", weatherForecast.getHigh().toString());
		
		WeatherForecast wForecast = new WeatherForecast();
		
		WeatherForecast.createWeatherForecast("");
		
		assertEquals("XXX,Unknown,  +0.0F,  +0.0F", wForecast.toString());
		
		WeatherForecast.createWeatherForecast(null);
		
		WeatherForecast weather = WeatherForecast
				.createWeatherForecast("PWWW1,Sunny, +98.8C,+100.0C");
		
		assertEquals("PWWW1,Sunny, +98.8C,+100.0C", weather.toString(false));
		
		weather = WeatherForecast.createWeatherForecast("P222");
		assertEquals("P222", weather.getLocation());
		WeatherForecast weatherf = WeatherForecast.createWeatherForecast("P222,Sunny");
		assertEquals("Sunny", weatherf.getCondition());
		
		weatherf = WeatherForecast.createWeatherForecast("P222,Sunny,+100.0C");
		assertEquals("+100.0C", weatherf.getLow().toString());
		
	}

}

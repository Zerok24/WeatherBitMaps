package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import weather.WeatherForecast;
import weather.WeatherPattern;

class WeatherPatternTest
{

	@Test
	void testWeatherPattern() 
	{
		WeatherPattern pattern = new WeatherPattern("Hello");
		
		WeatherForecast forecast = new WeatherForecast();
		
		pattern.handleWeatherDatum(forecast);
		assertEquals("Hello", pattern.getDescription());
		assertEquals(1, pattern.size());
		pattern.handleWeatherDatum(null);
		assertEquals(forecast.toString(), pattern.getElement(0).toString());
		pattern.iterator();
		pattern.reset();
		assertEquals(0, pattern.size());
		
	}

}

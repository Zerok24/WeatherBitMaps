package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import measurement.Temperature;
import weather.WeatherObservation;

class WeatherObservationTest 
{

	@Test
	void testConstructors() 
	{
		WeatherObservation observation = new WeatherObservation();
		assertEquals("XXX,Unknown,  +0.0F", observation.toString());
		
		observation = new WeatherObservation("PWW01", "Cloudy", new Temperature(25));
		assertEquals("PWW01,Cloudy, +25.0F", observation.toString(false));
		
		observation = WeatherObservation.createWeatherObservation("PWW01,Cloudy,  +0.0F");
		assertEquals("PWW01,Cloudy,  +0.0F", observation.toString(false));
		
		System.out.println(observation.toString());
		
		observation = WeatherObservation.createWeatherObservation(null);
		assertEquals("XXX,Unknown,  +0.0F", observation.toString());
		observation = WeatherObservation.createWeatherObservation("");
		assertEquals("XXX,Unknown,  +0.0F", observation.toString());
		assertEquals("XXX,Unknown,  +0.0F", observation.toString(false));
		
		
		observation = WeatherObservation.createWeatherObservation("PWW01,Cloudy");
		assertEquals("PWW01,Cloudy,  +0.0F", observation.toString(false));
		
		observation = WeatherObservation.createWeatherObservation("PWW01");
		assertEquals("PWW01,Unknown,  +0.0F", observation.toString(false));
	}
	
	@Test
	void testCreate() 
	{
		
		WeatherObservation observation = new WeatherObservation();
		assertEquals("XXX,Unknown,  +0.0F", observation.toString());
		
		observation = WeatherObservation.createWeatherObservation("PWW01,Cloudy, +85.0C");
		
		assertEquals("PWW01,Cloudy, +85.0C", observation.toString(false));
		assertEquals("Location: PWW01\tCondition: Cloudy\tTemperature:  +85.0C",
				observation.toString(true));
		
		observation = WeatherObservation
				.createWeatherObservation("PWW01");
		
		assertEquals("Unknown", observation.getCondition());
		assertEquals("PWW01", observation.getLocation());
		
		observation = WeatherObservation
				.createWeatherObservation("PWW01,Unknown");
		
		assertEquals("PWW01,Unknown,  +0.0F", observation.toString());
		
		observation = WeatherObservation
				.createWeatherObservation("");
		observation = WeatherObservation.createWeatherObservation(null);
		
		observation = WeatherObservation
				.createWeatherObservation("PWW01,Unknown, +96.0C");
		
		assertEquals(" +96.0C", observation.getTemperature().toString() );

	}
	
	
	

}

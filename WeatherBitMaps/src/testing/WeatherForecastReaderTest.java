package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

import weather.io.WeatherForecastReader;
import weather.io.WeatherObservationReader;
import weather.WeatherForecast;
import weather.WeatherObservation;
import weather.WeatherObserver;
import weather.WeatherPattern;

class WeatherForecastReaderTest 
{
	
	private BufferedReader createBufferedReader(String name)
	{
		
	  InputStream is = getClass().getResourceAsStream(name);
	  BufferedReader br = new BufferedReader(new InputStreamReader(is));

	  return br;
	} 
	

	@Test
	void test() throws IOException 
	{
		WeatherForecastReader in = new WeatherForecastReader(
				createBufferedReader("text2.txt"));
		
		
		WeatherForecast reader = new WeatherForecast();
		reader.fromString("PWW01,Sunny, +86.7F, +91.3F");
		
		assertEquals(reader.toString(), in.readWeatherDatum().toString());
		in.readAll();
		
		
	}

}

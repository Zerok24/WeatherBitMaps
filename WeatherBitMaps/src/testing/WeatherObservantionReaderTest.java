package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import weather.WeatherObservation;
import weather.WeatherObserver;
import weather.WeatherPattern;
import weather.io.WeatherObservationReader;

import org.junit.jupiter.api.Test;

import measurement.Temperature;


class WeatherObservantionReaderTest 
{
	
	private BufferedReader createBufferedReader(String name)
	{
		
	  InputStream is = getClass().getResourceAsStream(name);
	  BufferedReader br = new BufferedReader(new InputStreamReader(is));

	  return br;
	}   
	  
	  
	@Test
	void testReader() throws IOException 
	{
		
		
		WeatherObservationReader in = new WeatherObservationReader(
				createBufferedReader("text.txt"));
		
		
		WeatherObservation observation = new WeatherObservation();
		observation.fromString("PWW02,Sunny, +86.7F");
		assertEquals(observation.toString(), in.readWeatherDatum().toString());
		in.readOne();
		observation = new WeatherObservation("XXX","Unknown",new Temperature());
		assertEquals(observation.toString(), in.readWeatherDatum().toString());
		
		WeatherObserver obeObserver= new WeatherPattern("");
		
		in.addObserver(obeObserver);
		
		
		in.readWeatherDatum();
		
		in.readOne();
		in.notifyObserver(observation);
		in.removeObserver(obeObserver);
		
		
	}
	
	@Test
	void testReadAll() throws IOException 
	{
		
		WeatherObservationReader in = new WeatherObservationReader(
				createBufferedReader("text.txt"));
		
		WeatherObserver obeObserver= new WeatherPattern("");
		in.addObserver(obeObserver);
		
		in.readAll();
		
		
		
	}

}

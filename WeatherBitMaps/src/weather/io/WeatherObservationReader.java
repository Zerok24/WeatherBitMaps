package weather.io;

import java.io.BufferedReader;
import java.io.IOException;

import weather.WeatherDatum;
import weather.WeatherObservation;

public class WeatherObservationReader extends WeatherDatumReader
{
	/**
	 * default constructor.
	 * @param reader to read the file
	 */
	public WeatherObservationReader(final BufferedReader reader) 
	{
		super(reader);
		
	}
	
	/**
	 * read a weatherDatum object read from the file.
	 * @return WeatherDatum object.
	 * @throws IOException if failed to read.
	 */
	public WeatherDatum readWeatherDatum() throws IOException
	{
		String lineString = reader.readLine();
		WeatherObservation observation = new WeatherObservation();
		if (observation.fromString(lineString) != null) 
		{
			return observation;
		}
		
		return null;

	}
	
}

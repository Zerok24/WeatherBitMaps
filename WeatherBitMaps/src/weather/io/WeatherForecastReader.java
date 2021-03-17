package weather.io;

import java.io.BufferedReader;
import java.io.IOException;

import weather.WeatherDatum;
import weather.WeatherForecast;

/**
 * 
 * @author Bunguiu Norales
 *
 */
public class WeatherForecastReader extends WeatherDatumReader
{
	/**
	 * default constructor.
	 * @param reader to read the file
	 */
	public WeatherForecastReader(final BufferedReader reader) 
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
		String line = reader.readLine();
		if (line == null) return null;

		return WeatherForecast.createWeatherForecast(line);
		
			
	}

	
}

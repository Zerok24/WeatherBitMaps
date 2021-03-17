package weather.visual;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.Map;

import weather.WeatherDatum;

public class WeatherDatumContentFactory 
{
	
	
	private Map<String, Image> images;
	private Map<String, Point2D> locations;
	
	public WeatherDatumContentFactory(Map<String, Point2D> locations,
			Map<String, Image> images)
	{
		this.locations = locations;
		this.images = images;
	}
	
	public WeatherDatumContent createContent(WeatherDatum datum, Color color ) 
	{
		WeatherDatumContent content = new WeatherDatumContent(datum, color,
				locations.get(datum.getLocation()),
				images.get(datum.getCondition()));
		
		return content;
		
	}
	

}

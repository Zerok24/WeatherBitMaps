package weather.visual;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import io.ResourceFinder;
import visual.statik.sampled.ImageFactory;

public class WeatherIconReader 
{
	
	private static final String[] IMAGE_NAMES = {"PartlyCloudy", "Rainy", "Snowy", "Sunny",
			"Cloudy"};
	
	private ResourceFinder finder;
	
	public WeatherIconReader(ResourceFinder finder)
	{
		this.finder=finder;
//		finder = ResourceFinder.createInstance(new resources.Marker());
	}
	
	public Map<String, Image> read() 
	{
		Map<String, Image> map = new HashMap<String, Image>();
		ImageFactory factory = new ImageFactory(finder);
		
		BufferedImage image = factory.createBufferedImage("PartlyCloudy.png",4);
		map.put(IMAGE_NAMES[0], image);
		
		image = factory.createBufferedImage("Rainy.png",4);
		map.put(IMAGE_NAMES[1], image);
		
		image = factory.createBufferedImage("Snowy.png",4);
		map.put(IMAGE_NAMES[2], image);
		
		image = factory.createBufferedImage("Sunny.png",4);
		map.put(IMAGE_NAMES[3], image);
		
		image = factory.createBufferedImage("Cloudy.png",4);
		map.put(IMAGE_NAMES[4], image);
		
		return map;
	}
	
	

}

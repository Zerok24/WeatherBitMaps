package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.ResourceFinder;
import map.io.BaseMapReader;
import map.io.PolygonReader;
import map.io.StationLocationReader;
import visual.VisualizationView;
import visual.dynamic.described.DescribedSprite;
import visual.statik.described.AggregateContent;
import visual.statik.sampled.Content;
//import visual.statik.described.Content;
import visual.statik.sampled.ImageFactory;
import weather.WeatherDatum;
import weather.WeatherObserver;
import weather.visual.WeatherDatumContent;
import weather.visual.WeatherDatumContentFactory;
import weather.visual.WeatherIconReader;

public class DynamicWeatherMap extends visual.dynamic.described.Stage implements WeatherObserver
{
	private static final Color BACKGROUND_COLOR = new Color(204,204,255);
	private visual.statik.sampled.Content watermark;
	private List<WeatherDatumContent> currentContent;
	private WeatherDatumContentFactory wdContentFactory;
	
	
	public DynamicWeatherMap(String useWatermark, String grayWatermark, int width, int height) 
	{	
		super(100);

		setBackground(BACKGROUND_COLOR);
		currentContent = new ArrayList<WeatherDatumContent>();

		ResourceFinder jarFinder;
		jarFinder = ResourceFinder.createInstance(new resources.Marker());

		VisualizationView view = getView();
		view.setBounds(0, 0, width, height);
		view.setSize(width, height);
		
		BaseMapReader mapReader = new BaseMapReader(jarFinder);
		
		try 
		{
			visual.statik.described.Content mapa = mapReader.read("harrisonburg.map",
					Color.black, Color.white);
			mapa.setLocation(0, 0);
			add(mapa);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		PolygonReader reader = new PolygonReader(null);
		visual.dynamic.described.DescribedSprite sprite = new DescribedSprite();
		visual.statik.described.Content content = new visual.statik.described.Content();
		
		try 
		{
			Color color = new Color(155, 153, 168, 50);
			content.setShape(reader.read("./track-500.map"));
			content.setColor(color);
			content.setPaint(color);

			visual.statik.described.AggregateContent aggregateContent = 
					new AggregateContent();
			aggregateContent.add(content);
			
			sprite.addKeyTime(500, new Point2D.Double(0.0,0.0),
					java.lang.Double.valueOf(0.0), 1.0, aggregateContent);
			
			visual.statik.described.AggregateContent aggregateContent2 = 
					new AggregateContent();
			content = new visual.statik.described.Content();
			content.setShape(reader.read("./track-1500.map"));
			content.setColor(color);
			content.setPaint(color);
			aggregateContent2.add(content);
			
			sprite.addKeyTime(1500, new Point2D.Double(0.0,0.0),
					java.lang.Double.valueOf(0.0), 1.0, aggregateContent2);
			
			visual.statik.described.AggregateContent aggregateContent3 =
									new AggregateContent();
			content = new visual.statik.described.Content();
			content.setShape(reader.read("./track-4000.map"));
			content.setColor(color);
			content.setPaint(color);
			aggregateContent3.add(content);
			
			sprite.addKeyTime(4000, new Point2D.Double(0.0,0.0),
					java.lang.Double.valueOf(0.0), 1.0, aggregateContent3);
			
			add(sprite);
			start();
			setRestartTime(5000);
			
		} catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if (useWatermark != null) 
		{
			
			ImageFactory factory = new ImageFactory(jarFinder);
			BufferedImage image = factory
					.createBufferedImage("logoWeatherBits.png",4);	
			if (grayWatermark != null) 
			{
				for (int i = 0; i < image.getWidth(); i++) 
				{
					for (int j = 0; j < image.getHeight(); j++) 
					{
						if (image.getRGB(i, j) == -1) 
						{
							image.setRGB(i, j, 0);
						}else 
						{
							image.setRGB(i, j, Color.gray.getRGB() );
						}
					}
				}
				
				watermark = new Content(image, 150, 700);
				
				add(watermark);
				
			}else 
			{
				watermark = new Content(image, 150, 700);
				add(watermark);
			}	
		}
		
	}
	
	@Override
	public void handleWeatherDatum(WeatherDatum datum) 
	{
		ResourceFinder finder = ResourceFinder.createInstance(new resources.Marker());
		WeatherIconReader iconReader = new WeatherIconReader(finder);
		Map<String, Image> images = iconReader.read();
		
		StationLocationReader locationReader = new StationLocationReader(finder);
		Map<String, Point2D> locations = null;
		try 
		{
			locations = locationReader.read(null);
		} catch (IOException e) 
		{
			e.printStackTrace();
		} 
		
		wdContentFactory = new WeatherDatumContentFactory(locations,
				images);
		
		WeatherDatumContent datumContent = wdContentFactory
				.createContent(datum, Color.red);
		
		currentContent.add(datumContent);
		

		add(datumContent);
		
	}
	
	
	
	@Override
	public void reset() 
	{
		if (currentContent.size() > 0) 
		{
			for (int i = 0; i < currentContent.size(); i++) 
			{
				remove(currentContent.get(i));
			}
			
		}
		// TODO Auto-generated method stub
		
	}


	
	
	

}

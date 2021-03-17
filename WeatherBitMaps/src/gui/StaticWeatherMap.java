package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import visual.statik.sampled.*; 
import io.ResourceFinder;
import map.io.BaseMapReader;
import map.io.StationLocationReader;
import visual.VisualizationView;
import weather.WeatherDatum;
import weather.WeatherObserver;
import weather.visual.WeatherDatumContent;
import weather.visual.WeatherDatumContentFactory;
import weather.visual.WeatherIconReader;

public class StaticWeatherMap extends visual.Visualization implements WeatherObserver
{
	
	private static final Color BACKGROUND_COLOR = new Color(204,204,255);
	private Content watermark;
	private List<WeatherDatumContent> currentContent;
	
	public StaticWeatherMap(String useWatermark, String grayWatermark, int width, int height) 
	{
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
			visual.statik.described.Content mapa = mapReader.read(null,
					Color.black, Color.white);
			mapa.setLocation(0, 0);
			add(mapa);
		} catch (IOException e) 
		{
			e.printStackTrace();
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
		
		WeatherDatumContentFactory factory = new WeatherDatumContentFactory(locations,
				images);
		
		WeatherDatumContent datumContent = factory
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
		
	}

}

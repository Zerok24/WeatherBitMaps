package map.io;

import java.awt.Color;
import java.awt.Shape;
import java.io.IOException;

import io.ResourceFinder;
import visual.statik.described.Content;


public class BaseMapReader 
{
	
	private PolygonReader reader;
	
	public BaseMapReader(ResourceFinder finder) 
	{
		reader = new PolygonReader(finder);
	}
	
	public Content read(String name, Color boundury, Color interior) throws IOException
	{
		Shape shape = reader.read(name);
		Content content = new Content();
		
		content.setShape(shape);
		content.setColor(boundury);
		content.setPaint(interior);
		return content;
	}

}

package map.io;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.ResourceFinder;

public class PolygonReader 
{
	
	private ResourceFinder finder;
	
	public PolygonReader() 
	{
		
		finder = ResourceFinder.createInstance(new resources.Marker());
	}
	
	public PolygonReader(ResourceFinder finder) 
	{
		this.finder = finder;
		
	}
	
	public Shape read(final String name) throws IOException 
	{
		InputStream stream;
		if (name == null || name.equals("")) 
		{
			stream = finder.findInputStream("harrisonburg.map");
		}else 
		{
			stream = finder.findInputStream(name);	
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		Path2D polyShape = new Path2D.Float();
		
		String line;
		
		while ((line = reader.readLine()) != null)
		{
			String[] split = line.split(",");
			
			if (Integer.parseInt(split[0]) == 4) 
			{
				polyShape.moveTo(Double.parseDouble(split[1]),
						Double.parseDouble(split[2]));
			}else if (Integer.parseInt(split[0]) == 5)
			{
				polyShape.lineTo(Double.parseDouble(split[1]),
						Double.parseDouble(split[2]));
			}
			
		}
		reader.close();
		
		return polyShape;
	}
	
}

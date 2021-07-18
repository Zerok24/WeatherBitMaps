package map.io;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

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
		BufferedReader reader;
		float x, y;
		Path2D.Float polygon;
		InputStream is;
		int type;
		String line, token;
		StringTokenizer tokenizer;

		if (finder != null)
		{
			is = finder.findInputStream(name);
			reader = new BufferedReader(new InputStreamReader(is));
		}
		else
		{
			reader = new BufferedReader(new FileReader(name));
		}

		polygon = new Path2D.Float();

		while ((line = reader.readLine()) != null)
		{
			try
			{
				tokenizer = new StringTokenizer(line, ",");

				token = tokenizer.nextToken();
				type = Integer.parseInt(token);
	
				token = tokenizer.nextToken();
				x = Float.parseFloat(token);
	
				token = tokenizer.nextToken();
				y = Float.parseFloat(token);

				if (type == 4)
					polygon.moveTo(x, y);
				else
					polygon.lineTo(x, y);
			}
			catch (NumberFormatException nfe)
			{
	        // Ignore
			}
			catch (NoSuchElementException nsee)
			{
	        // Ignore
			}
		}
		polygon.closePath();

		return polygon;
	}
	
}

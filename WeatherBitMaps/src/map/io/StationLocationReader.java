package map.io;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import io.ResourceFinder;

public class StationLocationReader 
{
	
	private ResourceFinder finder;
	
	public StationLocationReader(ResourceFinder finder) 
	{
		this.finder=finder;
//		finder = ResourceFinder.createInstance(new resources.Marker());
	}
	
	public Map<String, Point2D> read(String name) throws IOException
	{
		InputStream stream;
		
		if (name == null || name.equals("") ) 
		{
			stream = finder.findInputStream("stations.loc");			
		}else 
		{
			stream = finder.findInputStream(name);
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String line;
		
		Map<String, Point2D> map = new HashMap<String, Point2D>();
		
		while ((line = reader.readLine()) != null)
		{
			String[] split = line.split(",");
			map.put(split[0], new Point2D.Double(Double.parseDouble(split[1]),
					Double.parseDouble(split[2])));
			
		}
		reader.close();
		
		return map;
	}
	
}

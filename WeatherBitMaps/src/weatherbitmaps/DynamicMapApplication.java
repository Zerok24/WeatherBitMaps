package weatherbitmaps;

import javax.swing.JComponent;

import app.JApplication;
import gui.DynamicWeatherMap;
import gui.StaticWeatherMap;
import weather.WeatherObserver;

public class DynamicMapApplication extends WeatherBitmapsApplication
{
	private DynamicWeatherMap weatherMap;
	
	public DynamicMapApplication(String[] args) 
	{
		super(args);
		if (args != null) 
		{
			if (args.length >= 2) 
			{
				weatherMap = new DynamicWeatherMap(args[0],args[1],WIDTH,HEIGHT);
			}else if (args.length == 1) 
			{
				weatherMap = new DynamicWeatherMap(args[0],null,WIDTH,HEIGHT);
			}else 
			{
				weatherMap = new DynamicWeatherMap(null,null,WIDTH,HEIGHT);
			}
			
		}
	}

	@Override
	protected JComponent getGUIComponent() 
	{
		return weatherMap.getView();
	}
	
	
	protected WeatherObserver getWeatherObserver() 
	{
		
		return weatherMap;
		
	}
	
	public static void main(String[] args) 
	{
		JApplication app = new DynamicMapApplication(args);
		invokeInEventDispatchThread(app);
		
	}
	
	

}

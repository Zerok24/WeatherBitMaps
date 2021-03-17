package weatherbitmaps;

import javax.swing.JComponent;

import app.JApplication;
import gui.StaticWeatherMap;
import weather.WeatherObserver;

public class StaticMapApplication extends WeatherBitmapsApplication 
{
	
	private StaticWeatherMap weatherMap;
	
	public StaticMapApplication(String[] args) 
	{
		super(args);
		if (args != null) 
		{
			if (args.length >= 2) 
			{
				weatherMap = new StaticWeatherMap(args[0],args[1],WIDTH,HEIGHT);
			}else if (args.length == 1) 
			{
				weatherMap = new StaticWeatherMap(args[0],null,WIDTH,HEIGHT);
			}else 
			{
				weatherMap = new StaticWeatherMap(null,null,WIDTH,HEIGHT);
			}
			
		}
		
	}
	
	
	protected WeatherObserver getWeatherObserver()
	{
		return weatherMap;
	}
	
	public static void main(String[] args) 
	{
		
		JApplication app = new StaticMapApplication(args);
		invokeInEventDispatchThread(app);
		
	}

	@Override
	protected JComponent getGUIComponent()
	{
		// TODO Auto-generated method stub
		return weatherMap.getView();
	}

}

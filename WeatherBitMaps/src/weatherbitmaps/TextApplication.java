package weatherbitmaps;

import javax.swing.JComponent;

import app.JApplication;
import gui.WeatherBoard;
import weather.WeatherObserver;

/**
 * 
 * @author Bunguiu Norales
 *
 */
public class TextApplication extends WeatherBitmapsApplication
{
	private WeatherBoard weatherBoard;
	/**
	 * 
	 * @param args
	 */
	public TextApplication(String[] args) 
	{
		super(args);
		weatherBoard = new WeatherBoard();
	}
	
	/**
	 * 
	 * @return main JComponent.
	 */
	protected JComponent getGUIComponent() 
	{
		return weatherBoard;
	}
	
	/**
	 * 
	 * @return WeatherObserver.
	 */
	protected WeatherObserver getWeatherObserver() 
	{
		return weatherBoard;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		JApplication app = new TextApplication(args);
		invokeInEventDispatchThread(app);

	}

	
}

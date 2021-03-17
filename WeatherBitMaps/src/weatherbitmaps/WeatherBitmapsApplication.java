package weatherbitmaps;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.Animation;
import io.ResourceFinder;
import weather.WeatherObserver;
import weather.io.WeatherDatumReader;
import weather.io.WeatherForecastReader;
import weather.io.WeatherObservationReader;

/**
 * 
 * @author Bunguiu Norales
 *
 */
public abstract class WeatherBitmapsApplication extends app.JApplication implements ActionListener 
{
	public static final int  WIDTH = 600;  
	public static final int  HEIGHT = 800;  
	protected static final String ABOUT = "About";
	protected static final String LOAD ="Load";
	protected JTextField fileField;
	private JButton aboutButton;
	private JButton loadButton;
	private String aboutText;
	
	/**
	 * Perform any necessary non-GUI-related initializations. 
	 * @param args from command line.
	 */
	public WeatherBitmapsApplication(String[] args) 
	{
		super(args, WIDTH, HEIGHT);
		aboutText = "";
	}
	
	/**
	 * Handles all of the actions. 
	 * @param e events.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		
		if(command.equals("About")) 
		{
			
			JFrame frame = new JFrame("About");
			frame.setVisible(true);
			frame.setSize(300, 300);
			Animation animation = new Animation();
			frame.add(animation);
			animation.start();
			
			
		}else if (command.equals("Load")) 
		{
			handleLoad();
		}
		
	}
	
	/**
	 * 
	 * @return main component.
	 */
	protected abstract JComponent getGUIComponent();

	/**
	 * Gets weatherObserver Object.
	 * @return WeatherObserver.
	 */
	protected WeatherObserver getWeatherObserver() 
	{
		
		return this.getWeatherObserver();
	}
	
	/**
	 * Handle about buttons actions.
	 * @throws IOException 
	 */
	protected void handleAbout()
	{
		
		ResourceFinder resourceFinder = 
				ResourceFinder.createInstance(new resources.Marker());
		InputStream stream = resourceFinder.findInputStream("about.txt");
		BufferedReader in = new BufferedReader(new InputStreamReader(stream));
		
		String line;
		
		try 
		{
			
			while ((line = in.readLine()) != null)
			{
				aboutText += line + "\n";
				
			}
			
		} catch (IOException e) 
		{
			JOptionPane.showMessageDialog(null, "Error reading file");
			
		}
		
		JOptionPane.showMessageDialog(null, aboutText);
		
		
	}
	
	/**
	 * Handle load buttons actions.
	 */
	protected void handleLoad() 
	{
		String fileName = fileField.getText();
		WeatherDatumReader in;
		try
		{ 
			
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			if (fileName.contains("for")) in = new WeatherForecastReader(br);
			else in = new WeatherObservationReader(br);
			getWeatherObserver().reset();
			in.addObserver(getWeatherObserver());
			in.readAll();
			
		} catch (IOException e) 
		{
			JOptionPane.showMessageDialog(null, "Error reading file");
		}
	}
	
	/**
	 * Construct the GUI components and lay them out.
	 */
	public void init() 
	{
//		board = (VisualizationView) getGUIComponent();
		fileField = new JTextField(10);
		aboutButton = new JButton(ABOUT);
		aboutButton.addActionListener(this);
		loadButton = new JButton(LOAD);
		loadButton.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel totalPanel = new JPanel();
		totalPanel.add(new JLabel("File: "));
		totalPanel.add(fileField);
		totalPanel.add(loadButton);
		totalPanel.add(aboutButton);
		
		panel.add(totalPanel,BorderLayout.NORTH);
		panel.add(getGUIComponent(), BorderLayout.CENTER);
		super.setContentPane(panel);
		
	}

}

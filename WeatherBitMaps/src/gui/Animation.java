package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import visual.statik.sampled.ImageFactory;
import io.ResourceFinder;

public class Animation extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ImageIcon[] imageIcons;
	private Timer timer;
	private int current;
	private int counter = 0;
	
	public Animation() 
	{
		
		ResourceFinder finder = ResourceFinder.createInstance(new resources.Marker());
		ImageFactory factory = new ImageFactory(finder);
		
		
		imageIcons = new ImageIcon[24];
		
		InputStream stream = finder.findInputStream("vortex.txt");
		BufferedReader in = new BufferedReader(new InputStreamReader(stream));
		String line = "";
		
		try 
		{
			
			for (int i = 0; i < 24; i++) 
			{
				line = in.readLine();
				if (line != null) 
				{
					BufferedImage image = factory.createBufferedImage(line,4);
					imageIcons[i] = new ImageIcon(image);
				}
			}
				
			in.close();
		} catch (IOException e) 
		{
			// TODO: handle exception
		}
		
	}
	
	
	public void paint(Graphics g)
	{
		
		if (counter < 24) 
		{
			super.paintComponent(g);
//			System.out.println(current);
			imageIcons[current].paintIcon(this, g, 0, 0 );	
			if (timer.isRunning()) 
			{
				current = current+1 % 24;
			}
			if (counter >= 11) 
			{
				GlyphVector glyphVector;
				Font font = new Font("Serif", Font.PLAIN, 15);
				
				Graphics2D g2;
				g2 = (Graphics2D)g;
				
				g2.setColor(Color.red);
				
				glyphVector = font.createGlyphVector(g2.getFontRenderContext(),
						"Text Application v2.0");
				
				g2.drawGlyphVector(glyphVector,100,100);
			}
			
		}else 
		{
			
			timer.stop();
		}
		
		counter++;

		
	}
	
	public void start() 
	{
		if (timer == null) 
		{
			current = 0;
			timer = new Timer(166, new TimerHandler());
			timer.start();
			
		}else 
		{
			if (!timer.isRunning() )
			{
				timer.restart();
			}
		}
		
		
		
	}


	 // inner class to handle action events from Timer
	private class TimerHandler implements ActionListener
	{
		 // respond to Timer's event 
		public void actionPerformed( ActionEvent actionEvent )
		{
			repaint(); // repaint animator
		}
		 // end method actionPerformed
	} 
	

}

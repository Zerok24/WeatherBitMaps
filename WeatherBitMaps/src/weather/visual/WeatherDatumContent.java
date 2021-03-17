package weather.visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.GlyphVector;
import java.awt.geom.Point2D;

import weather.WeatherDatum;

public class WeatherDatumContent implements visual.statik.SimpleContent
{

	protected Color color;
	protected Image image;
	protected Point2D location;
	protected WeatherDatum datum;
	
	public WeatherDatumContent(WeatherDatum datum, Color color, Point2D location, Image image) 
	{
		this.datum = datum;
		this.color = color;
		this.location = location;
		this.image = image;
		
	}
	
	
	@Override
	public void render(Graphics g) 
	{
		GlyphVector glyphVector;
		Font font = new Font("Serif", Font.PLAIN, 15);
		
		Graphics2D g2;
		g2 = (Graphics2D)g;
		
		g2.setColor(color);
		
		glyphVector = font.createGlyphVector(g2.getFontRenderContext(),
				datum.toString(false));
		
		g2.drawGlyphVector(glyphVector, (float)location.getX()-50,
				(float) location.getY()+50);
		
		
		g2.drawImage(image, (int)location.getX(), (int) location.getY(), null);
		
		
	}

}

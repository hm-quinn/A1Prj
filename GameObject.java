package com.mycompany.a1;
import com.codename1.charts.util.ColorUtil; 
import com.codename1.charts.models.Point;
import java.util.Random;

public class GameObject {
	public Random rand = new Random();
	private Point pos;
	private int color;
	
	public GameObject() {
		float x = (Math.round((1000* rand.nextFloat() * 10)) / 10);
		float y = (Math.round((1000* rand.nextFloat() * 10)) / 10);
		
		
		//accounting for edge case of something going off screen
		if( x > 1000)
			x = 1000;
		if(y > 1000)
			y = 1000;
		 
		pos = new Point(x,y);
	}
	
	public float getPosX()
	{
		
		return pos.getX();
	}
	
	public float getPosY()
	{
		
		return pos.getY();
	}
	
	public Point getLocation()
	{
		return pos;
	}
	
	public void setLocation(float x, float y)
	{
		pos.setX(((Math.round(x)*10)/10));
		pos.setY(((Math.round(y)*10)/10));
		
	}
	
	public void setLocation(Point newLoc)
	{
		pos.setX(((Math.round(newLoc.getX())*10)/10));
		pos.setY(((Math.round(newLoc.getY())*10)/10));
		
	}
	
	public int getColor()
	{
		
		return color;
	}
	
	public void setColor(int newColor)
	{
		color = newColor;
	}
	
	public String toString()
	{
		String retVal = 
				"Location = " + pos.getX() + "," + pos.getY() 
				+ " Color = [" + ColorUtil.red(getColor()) + "," +
				ColorUtil.green(getColor()) +"," +
				ColorUtil.blue(getColor()) + "]";
		return retVal;
		
	}
	
}

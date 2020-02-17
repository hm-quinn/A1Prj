package com.mycompany.a1;
import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject {
	private int heading, speed;
	
	public void move() {
		Point oldLoc = super.getLocation(); //get current location
		Point newLoc = new Point(0,0);
		int theta = 90 - heading; 
		float deltaX = 0;
		float deltaY = 0;
		
		//deltaX and deltaY = heading * speed
		if(speed == 0) {
			speed = 1;
			deltaX = (float) (Math.cos(Math.toRadians(theta))* speed); 
			deltaY = (float) (Math.sin(Math.toRadians(theta)) * speed);
		}
		else {
			deltaX = (float) (Math.cos(Math.toRadians(theta))* speed); 
			deltaY = (float) (Math.sin(Math.toRadians(theta)) * speed);
			
		}
		//compute newLocation = oldLocation + (deltaX, deltaY)
		newLoc.setX(deltaX + oldLoc.getX());
		newLoc.setY(deltaY + oldLoc.getY());
		super.setLocation(newLoc);
	}
	
	public void setSpeed(int x)
	{
		speed += x;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public int getHeading()
	{
		return heading;
		
	}
	
	public void setHeading(int x)
	{
		heading = x;
	}
	
	public String toString()
	{
		String parentString = super.toString();
		String str = " Heading = " + this.heading + " Speed = " + this.speed;	
		String retval = parentString + str;
		return retval;
	}


}

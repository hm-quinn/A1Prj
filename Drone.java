package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Drone extends Movable {
	private int size, id;
	
	public Drone(int i) {
		size = rand.nextInt(4) + 15;
		id = i;
		super.setColor(ColorUtil.GRAY); //grey
		super.setHeading(rand.nextInt(340));
		super.setSpeed(rand.nextInt(6) + 5);
	}
	
	public int getID() {
		return id;
	}
	
	@Override
	public void move() {
		Point oldLoc = getLocation(); //get current location
		setHeading(rand.nextInt(11) - 5); //set new Heading for Drone
		//check if drone hits side of game world
		while(oldLoc.getY() == 0 && getHeading() == 180 || oldLoc.getY() == 1000 && getHeading() == 0
				|| oldLoc.getX() == 0 && getHeading() == 360 || oldLoc.getX() == 1000 && getHeading() == 90) {
			setHeading(rand.nextInt(11) - 5);
		}
		Point newLoc = new Point(0,0);
		int theta = 90 - getHeading(); 
		
		//deltaX and deltaY = heading * speed
		float deltaX = (float) (Math.cos(Math.toRadians(theta)) * getSpeed()); 
		float deltaY = (float) (Math.sin(Math.toRadians(theta)) * getSpeed());
		
		//compute newLocation = oldLocation + (deltaX, deltaY)
		newLoc.setX(deltaX + oldLoc.getX());
		newLoc.setY(deltaY + oldLoc.getY());
		setLocation(newLoc);
	}
	@Override
	public String toString() {
		String parentDes = super.toString();
		String myDes = " Size = " + size;
		return ("Drone: " + parentDes + myDes);
	}
}

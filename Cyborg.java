package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class Cyborg extends Movable implements ISteerable {
	private int size, steeringDirection, maximumSpeed, energyLevel, energyConsumptionRate, damageLevel, maxDamageLevel, lastBaseReached;
	private int i = 0; //manages how many times each turn takes, max 40 degrees
	
	public Cyborg() {
		size = rand.nextInt(6) + 20;
		maximumSpeed = 50;
		energyLevel = 100;
		energyConsumptionRate = 1;
		damageLevel = 0;
		maxDamageLevel = 100;
		lastBaseReached = 1;
		super.setColor(ColorUtil.rgb(255,0,0)); //red
		super.setSpeed(0);
		super.setHeading(0);
		super.setLocation(100,100);
	}
	
	public int getSize() {
		return size;
	}
	
	public int getMaxSpeed() {
		return maximumSpeed;
	}
	
	public void setEnergyLevel(int i) {
		energyLevel += i;
	}
	
	public int getEnergyLevel() {
		return energyLevel;
	}
	
	//get Energy consumption rate
	public int getRate() {
		return energyConsumptionRate;
	}
	
	public void setDamageLevel(int i) {
		if (this.damageLevel == this.maxDamageLevel)
			System.out.println("Cyborg is at its max damage level.");
		else 
			damageLevel += i;
	}
	
	public int getDamageLevel() {
		return damageLevel;
	}
	
	public int getMaxDamageLevel() {
		return maxDamageLevel;
	}
	
	public void setBaseReach(int i) {
		lastBaseReached = i;
	}
	
	public int getBaseReach() {
		return lastBaseReached;
	}
	
	public void setSteerDir(char x) { //set steering direction
		//left turn
		if(x == 'l') {
			steeringDirection -= 5;
			i+= 5;
			if(i > 40) {
				System.out.println("Error! Cyborg can only turn left  40 degrees at a time.");
			}
		}
		else { 
			i -= Math.abs(steeringDirection);
		}
		
		//right turn
		if(x == 'r') {
			steeringDirection += 5;
			i += 5;
			if (i > 40) {
				System.out.println("Error! Cyborg can only turn right 40 degrees at a time.");
			}
		}
		else { 
			i -= Math.abs(steeringDirection);
		}
	}
	
	public int getSteerDir() {
		return steeringDirection;
	}
	
	@Override
	public void move() {
		super.move();
	}
	
	public String toString() {
		String parentDes = super.toString();
		String myDes = 
				" Size = " + size + " Max Speed = " + maximumSpeed + 
				" Steering Direction = " + steeringDirection + " Energy Level = " + energyLevel +
				" Damage Level = " + damageLevel;
		return ("Cyborg: " + parentDes + myDes);
	}
}

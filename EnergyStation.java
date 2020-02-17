package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class EnergyStation extends Fixed {
	private int size, capacity;
	
	public EnergyStation() {
		int x = rand.nextInt(6) + 15;
		size = capacity = x;
		super.setColor(ColorUtil.GREEN); //green
		super.setLocation(900 * rand.nextFloat(), 900 * rand.nextFloat());
	}
	
	public int getSize() {
		return size;
	}
	
	//set capacity after collision
	public void setCapacity() {
		capacity = 0;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public String toString() {
		String parentDes = super.toString();
		String myDes = " Size = " + size + " Capacity = " + capacity;
		return ("Energy Station: " + parentDes + myDes);
	}

}

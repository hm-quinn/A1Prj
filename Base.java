package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class Base extends Fixed{
	
	private int size, seqNum;
	
	public Base(int i) {
		seqNum = i;
		size = rand.nextInt(6) + 15;
		super.setColor(ColorUtil.BLUE); //blue 
		
		//set Location of each base
		super.setLocation(100 * seqNum, 100 * seqNum);
		
	}
	
	public int getId() {
		return seqNum;
	}
	
	public int getSize() {
		return size;
	}
	
	public String toString() {
		String parentDes = super.toString();
		String myDes = " Size = " + size + " Sequence Number = " + seqNum;
		return ("Base: " + parentDes + myDes);
	}
}

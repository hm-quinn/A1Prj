package com.mycompany.a1;
import java.util.*;

import com.codename1.charts.util.ColorUtil;

public class GameWorld {
	
	private ArrayList<GameObject> list;
	private int elapsedTime, live;
	ObjectIterator iterator;
	
	public GameWorld() {
		live = 3;
		elapsedTime = 0;
	}
	
	//Initiate game objects
	public void init() {
		list = new ArrayList<GameObject>();
		iterator = new ObjectIterator();
		
		// Add one Cyborg
		Cyborg player = new Cyborg();
		list.add(player);
		
		//Add drone
		for(int i = 0; i < 2; i++) {
			Drone drone = new Drone(i + 1);
			list.add(drone);
		}
		
		//Add base
		for(int i = 0; i < 4; i++) {
			Base base = new Base(i + 1);
			list.add(base);
		}
		
		//Add Energy Station
		for(int i = 0; i < 2; i++) {
			addEnergyStation();
		}
		
	}
	
	//Method to add Energy Station
	private void addEnergyStation() {
		EnergyStation es = new EnergyStation();
		list.add(es);
	}

	public void changeSpeed(char x) {
		Cyborg player = findCyborg();
		if(player != null) {
			if(x == 'a') {
				if (player.getSpeed() > player.getMaxSpeed())
					System.out.println("Error! Cyborg's current speed cannot exceed maximum speed.");
				else if (player.getDamageLevel() == player.getMaxDamageLevel()) {
					System.out.println("Cyborg cannot move as it is at its max damage level.");
				}
				else if(player.getEnergyLevel() == 20) {
					System.out.println("Cyborg cannot move due to having no energy.");
				}
				else {
					System.out.println("Accelerate the speed of Cyborg.");
					/*setting speed according to damage level
					 * damage percent = (max damage - damage level) / max damage
					 * speed = max speed - percent * max speed 
					 */
					double percent = (player.getMaxDamageLevel() - player.getDamageLevel()) / (player.getMaxDamageLevel());
					player.setSpeed((int) (player.getMaxSpeed() - (player.getMaxSpeed() - (percent * player.getMaxSpeed()))));
				}
			}	
			if(x == 'b') {
				if (player.getSpeed() <= 0)
					System.out.println("Cyborg is current not moving.");
				else { 
					System.out.println("Brake is applied.");
					player.setSpeed(-1);
				}
			}
			//Reduce speed due to collision
			if(x == 'c') {
				System.out.println("Cyborg's speed is reduced due to a collision.");
				if (player.getSpeed() >= 2) {
					player.setSpeed(-2);
				}
				else {
					player.setSpeed(0);
				}
			}
		}
	}
	
	public void CBSteer(char x) {
		//steer left
		Cyborg player = findCyborg();
		if (x == 'l') {
			System.out.println("Steer left by 5 degrees.");
			player.setSteerDir('l');
		}
		else {
			System.out.println("Steer right by 5 degrees.");
			player.setSteerDir('r');
		}
	}

	public void CBCollision() {
		System.out.println("Collided with another Cyborg.");
		Cyborg player = findCyborg();
		player.setDamageLevel(20);
		player.setEnergyLevel(-20);
		if(player.getEnergyLevel() <= 0) {
			System.out.println("Cyborg has lost a live due to unsufficient energy level.");
			live--;
			if (live == 0) {
				gameOver('l');
			}
			System.out.println("Re-initialize game world.");
			init();
		}
		else if( player.getDamageLevel() == player.getMaxDamageLevel()) {
			System.out.println("Cyborg has lost a live due to having reached maximum damage level.");
			live--;
			if (live == 0) {
				gameOver('l');
			}
			System.out.println("Re-initialize game world.");
			init();
		}
		else {
			player.setColor(ColorUtil.rgb(255, player.getDamageLevel() * 10, player.getDamageLevel() * 10));
			changeSpeed('c');
		}
	}

	public void DCollision() {
		Drone drone = findDrone();
		System.out.println("Collison with Drone #" + drone.getID() + " .");
		//same as colliding with another cyborg, except half the damage
		Cyborg player = findCyborg();
		player.setDamageLevel(10);
		player.setEnergyLevel(-10);
		if(player.getEnergyLevel() <= 0) {
			System.out.println("Cyborg has lost a live due to unsufficient energy level.");
			live--;
			if (live == 0) {
				gameOver('l');
			}
			System.out.println("Re-initialize game world.");
			init();
		}
		else if( player.getDamageLevel() == player.getMaxDamageLevel()) {
			System.out.println("Cyborg has lost a live due to having reached maximum damage level.");
			live--;
			if (live == 0) {
				gameOver('l');
			}
			System.out.println("Re-initialize game world.");
			init();
		}
		else {
			player.setColor(ColorUtil.rgb(255, player.getDamageLevel() * 10, player.getDamageLevel() * 10));
			changeSpeed('c');
		}
	}

	public void ESCollision() {
		EnergyStation energyStation = findStation();
		System.out.println("Collision with an energy station.");
		Cyborg player = findCyborg();
		player.setEnergyLevel(energyStation.getCapacity());
		energyStation.setCapacity();
		energyStation.setColor(ColorUtil.rgb(153,255,51));
		addEnergyStation();
	}
	

	public void BCollision(int i) {
		Base base = findBase(i);
		Cyborg player = findCyborg();
		if((base.getId() - player.getBaseReach()) == 1) {
			//reach the last base (4)
			if(base.getId() == 4) {
				System.out.println("Cyborg has successfully reached the last base.");
				player.setBaseReach(i);
				player.setLocation(base.getLocation());
				gameOver('w');
			}
			//reach the next base
			else {
				System.out.println("Cyborg has successfully reached the next base.");
				player.setBaseReach(i);
				player.setLocation(base.getLocation());
			}
		}
	}
	
	

	public void clockTick() {
		System.out.println("Game clock has ticked.");
		int i = -1;
		while(iterator.hasNext()) {
			i++;
				if(iterator.next() instanceof Cyborg) {
					Cyborg player = (Cyborg) list.get(i);
					if(player.getSpeed() != 0 || player.getEnergyLevel() != 0 || player.getDamageLevel() != player.getMaxDamageLevel()) {
						player.setHeading(player.getSteerDir());
						player.move();
						player.setEnergyLevel(-player.getRate());
					}
				}
				else if(iterator.next() instanceof Drone) {
					Drone drone = (Drone) list.get(i);
					drone.move();
				}
			}
		iterator.resetIndex();
		elapsedTime++;
	}

	//Display values of game and cyborg
	public void gameStat() {
		Cyborg player = findCyborg();
		System.out.println("Lives: " + live);
		System.out.println("Elapsed Time: " + elapsedTime);
		System.out.println("Last Base Reached: " + player.getBaseReach());
		System.out.println("Cyborg's Energy Level: " + player.getEnergyLevel());
		System.out.println("Cyborg's Damage Level: " + player.getDamageLevel());
	}

	//Display the game map
	public void map() {
		Cyborg player = findCyborg();
		player.setHeading(player.getSteerDir());
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println();
	}
	
	//Find Cyborg
		public Cyborg findCyborg() {
			iterator.resetIndex();
			int i = -1;
			Cyborg temp = null;
			while(iterator.hasNext()) {
				i++;
				if (iterator.next() instanceof Cyborg) {
					temp = (Cyborg) list.get(i);
					iterator.resetIndex();
					break;
				}
			}
				
			if (temp == null) {
				System.out.println("No Cyborg is found.");
				return null;
			}
			return temp;
		}
		
		public Drone findDrone() {
			iterator.resetIndex();
			int i = -1;
			Drone temp = null;
			while(iterator.hasNext()) {
				i++;
				if(iterator.next() instanceof Drone) {
					temp = (Drone) list.get(i);
					iterator.resetIndex();
					break;
				}
			}
			if(temp == null) {
				System.out.println("No Drone is found.");
				return null;
			}
			return temp;
		}
		
		public EnergyStation findStation() {
			iterator.resetIndex();
			int i = -1;
			EnergyStation temp = null;
			while(iterator.hasNext()) {
				i++;
				if(iterator.next() instanceof EnergyStation) {
					temp = (EnergyStation) list.get(i);
					list.remove(i); //remove energy station
					iterator.resetIndex();
					break;
				}
			}
			if(temp == null) {
				System.out.println("No energy station is found.");
				return null;
			}
			return temp;
		}
		
		public Base findBase(int a) {
			iterator.resetIndex();
			int i = -1;
			Base temp = null;
			while(iterator.hasNext()) {
				i++;
				if(iterator.next() instanceof Base) {
					temp = (Base) list.get(i);
					if(temp.getId() == a) {
						iterator.resetIndex();
						break;
					}
				}
			}
			if(temp == null) {
				System.out.println("No base is found.");
				return null;
			}
			
			return temp;
		}
	
	private void gameOver(char c) {
		if(c == 'l') {
			System.out.println("Cyborg has lost all his lives. Time to restart the game.");
		}
		else if (c == 'w') {
			System.out.println("Congratulation! You have won the game.");
		}
	}

	public void exit() {
		System.exit(0);
	}

	private class ObjectIterator implements Iterator<Object> {
		private int index;
		
		public ObjectIterator() {
			index = -1;
		}
		
		@Override
		public boolean hasNext() 
		{
			if (list.size() <= 0) return false;
			if (index == list.size() - 1) 
			{
				index = -1;
				return false;
			}
			return true;
		}

		@Override
		public Object next() 
		{
			index++;
			return list.get(index);
		}

		@Override
		public void remove() 
		{
			list.remove(index);
			index--;
		}
		
		public void resetIndex()
		{
			index = -1;
		}
	}
}

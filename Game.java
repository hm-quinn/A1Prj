package com.mycompany.a1;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener; 
import com.codename1.ui.Label; 
import com.codename1.ui.TextField; 
import com.codename1.ui.events.ActionEvent; 
import java.lang.String; 


public class Game extends Form{
	private GameWorld gw;
	private char key;
	
	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
	}
	
	private void play() {
		 Label myLabel=new Label("Enter a Command:");
		 this.addComponent(myLabel);
		 final TextField myTextField=new TextField();
		 this.addComponent(myTextField);
		 this.show();
		 myTextField.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent evt) { 
				 String sCommand=myTextField.getText().toString();
				 myTextField.clear();
				 if(sCommand.length() != 0) 
					 switch (sCommand.charAt(0)) {
					 	case 'x': //exit command
					 		setKey(sCommand.charAt(0));
					 		System.out.println("Are you sure you want to exit? (Yes = y, No = n) ");
					 		break;
					 	case 'a': //accelerate command
					 		(gw).changeSpeed(sCommand.charAt(0));
					 		break;
					 	case 'b'://brake
					 		gw.changeSpeed(sCommand.charAt(0));
					 		break;
					 	case 'l': //left steer
					 		gw.CBSteer(sCommand.charAt(0));
					 		break;
					 	case 'r': //right steer
					 		gw.CBSteer(sCommand.charAt(0));
					 		break;
					 	case 'c': //collision with other cyborg
					 		gw.CBCollision();
					 		break;
					 	case 'g': //collision with drone
					 		gw.DCollision();
					 		break;
					 	case 'e': //collision with an energy station
					 		gw.ESCollision();
					 		break;
					 	case '1': //collision with base
					 		gw.BCollision(1);
					 		break;
					 	case '2': //collision with base
					 		gw.BCollision(2);
					 		break;
					 	case '3': //collision with base
					 		gw.BCollision(3);
					 		break;
					 	case '4': //collision with base
					 		gw.BCollision(4);
					 		break;
					 	case '5': //collision with base
					 		gw.BCollision(5);
					 		break;
					 	case '6': //collision with base
					 		gw.BCollision(6);
					 		break;
					 	case '7': //collision with base
					 		gw.BCollision(7);
					 		break;
					 	case '8': //collision with base
					 		gw.BCollision(8);
					 		break;
					 	case '9': //collision with base
					 		gw.BCollision(9);
					 		break;
					 	case 't': //clock ticks
					 		gw.clockTick();
					 		break;
					 	case 'd': //display game state values
					 		gw.gameStat();
					 		break;
					 	case 'm': //show game map
					 		gw.map();
					 		break;
					 	case 'Y':
					 	case 'y': //yes to exit
					 		if(getKey() == 'x') {
					 			gw.exit();
					 		}
					 		else {
					 			System.out.println("Invalid input.");
					 		}
					 		break;
					 	case 'N':
					 	case 'n': //no to exit
					 		if(getKey() == 'x') {
					 			System.out.println("Game continues.");
					 		}
					 		else {
					 			System.out.println("Invalid input.");
					 		}
					 		break;
					 	default:
					 		System.out.println("Invalid input.");
					 		break;
					 } //switch
			 } //actionPerforned
		 }); //actionListener
	} //void play()
	private void setKey(char key) {
		this.key = key;
	}
	private char getKey() {
		return key;
	}
} //Class Game


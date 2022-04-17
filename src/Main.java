package from00;

import lejos.hardware.*;
//import lejos.nxt.*;
import lejos.robotics.localization.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.*;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.*;
import lejos.robotics.*;


public class Main {
	static double diam = DifferentialPilot.WHEEL_SIZE_EV3;
  static double trackwidth = 15.2;
	/* Instance variables */
	static Arbitrator arby;
	
	public static void main(String[] args) throws InterruptedException {
//		LCD.drawString("Press to Go", 4, 4);
//		Button.waitForAnyPress();
		Position position = new Position(0, 0, new int[] {1, 0, 0, 0});
		RegulatedMotor right = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor left = new EV3LargeRegulatedMotor(MotorPort.B);
		SharedIRSensor ir = new SharedIRSensor();
		ColorDetect color = new ColorDetect(position);
		
		DifferentialPilot pilot = new DifferentialPilot(diam, trackwidth, right, left);
		Movement b1 = new Movement(position, pilot, right, left, ir, color);
//		TurnLeft b2 = new TurnLeft(b1, position);
//		TurnRight b3 = new TurnRight(b1, position);
////		ReturnBack b4 = new ReturnBack(b1, position);
//		ObstacleDetect b5 = new ObstacleDetect(b1, ir, position);
////		Home b6 = new Home(b1, position);
//		Behavior[] behave = new Behavior[]{b1, b2, b3,  b5};
//		Arbitrator arby = new Arbitrator(behave);
		Button.waitForAnyPress();
//		arby.start();
		b1.run();
	};
}

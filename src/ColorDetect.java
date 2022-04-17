package from00;


import lejos.hardware.*;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.*;
import lejos.robotics.*;
import lejos.hardware.port.SensorPort;



public class ColorDetect extends Thread {
	public EV3ColorSensor color; 
	private ColorAdapter adapter; 
	private Position position;
	
	public ColorDetect(Position position){
		this.color = new EV3ColorSensor(SensorPort.S2);
		this.adapter = new ColorAdapter(color);
		this.position = position;
		this.setDaemon(true);
		this.start();
	}
	
	@Override
    public  void run() {
        while(true) {
//            Color c = this.adapter.getColor();
//            // calculate red percentage:
//            double total = c.getRed() + c.getBlue() + c.getGreen();
//            double red_ratio = c.getRed() / total;
//            if(this.position.x == 0 && this.position.y == 0 && this.position.direction[3] == 1){
//            	LCD.drawString("Red 1: (" + this.position.red1[0] + ", " + this.position.red1[1] + ")",0,0);
//            	LCD.drawString("Red 2: (" + this.position.red2[0] + ", " + this.position.red2[1] + ")",1,1);
//
//            }
            
            
//            LCD.drawString("(" + red_ratio + ")",0, 4);
     
//    		LCD.drawString("(" + this.position.getX() + ", " + this.position.getY() + this.position.getDirection(0),0, 4);

			Thread.yield();
        }
    }
}

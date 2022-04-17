package from00;


import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;


public class SharedIRSensor extends Thread {
	
	EV3IRSensor ir = new EV3IRSensor(SensorPort.S1);
	SampleProvider sp = ir.getDistanceMode();
	public int control = 0;
	public int distance = 255;

	SharedIRSensor() {
		this.setDaemon(true);
		this.start();
	}
	@Override
	public void run() {
		while (true) {
			float [] sample = new float[sp.sampleSize()];
			control = ir.getRemoteCommand(0);
			sp.fetchSample(sample, 0);
			if((int)sample[0] == 0)
				distance = 255;
			else
				distance = (int)sample[0];
//			LCD.drawString("Point: " + , 0, 0);
//			LCD.drawString("Distance: " + distance + "   ", 0, 1);
			if(distance < 60) {
				Sound.playTone(1000, 100);
				Delay.msDelay(1000);
				
            }
			Thread.yield();
		}
	}
}

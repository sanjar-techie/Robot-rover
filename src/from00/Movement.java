package from00;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.robotics.*;
import lejos.robotics.navigation.*;
import lejos.utility.Delay;


public class Movement implements Behavior{
	
	public Position position;
	public RegulatedMotor right;
	private RegulatedMotor left;
	private DifferentialPilot robot;
	SharedIRSensor ir;
	ColorDetect color;
	int travel = 25;
	
	public Movement(Position position,DifferentialPilot pilot, RegulatedMotor right, RegulatedMotor left, SharedIRSensor ir, ColorDetect color){
		this.position = position;
		this.right = right;
		this.left = left;
		this.ir = ir;
		this.robot = pilot;
		this.color = color;
	}
	
	@Override
	public boolean takeControl(){
		return true;
	 }
	
	@Override
	public void action(){
		this.step_forward();
	}
	@Override
	public void suppress() {
	}
	
	public void turnNextLaneLeft(){
		 this.turn_left();
		 this.step_forward();
		 this.turn_left();
	 }
	
	public void turnNextLaneRight(){
		this.turn_right();
		 this.step_forward();
		 this.turn_right();
	 }
	 
	
	public void goOneLane(){
		while((this.position.getX() != 5 && this.position.getDirection(0) == 1) || (this.position.getX() != 0 && this.position.getDirection(1) == 1)){
			this.step_forward();
			if(this.position.getX() == 4){
                Sound.playTone(1000, 200);
			}
		}
		
		if(this.position.getDirection(0) == 1){
			this.turn_right();
		}else if(this.position.getDirection(1) == 1){
			this.turn_left();
		}
	}
	
	public void returnBack(){
		this.turn_left();
		
	}
	
	public void step_forward(){
		if(this.color.color.getColorID() == 0){
			this.position.setRed(this.position.x, this.position.y);
			Sound.buzz();
			Delay.msDelay(2000);
		}
		
		
		this.robot.travel(this.travel);
    	if (this.position.getDirection(0) == 1) this.position.changeX(1);
    	else if (this.position.getDirection(1)==1) this.position.changeX(-1);
    	else if (this.position.getDirection(2)==1) this.position.changeY(1);
    	else if (this.position.getDirection(3)==1) this.position.changeY(-1);
    
	 }
	
	 public void turn_left(){
		this.robot.rotate(90);
    	
    	if (this.position.getDirection(0)== 1) this.position.setDirection(new int[]{0, 0, 1, 0});
    	else if (this.position.getDirection(1)==1) this.position.setDirection(new int[]{0, 0, 0, 1});
    	else if (this.position.getDirection(2)==1) this.position.setDirection(new int[]{0, 1, 0, 0});
    	else if (this.position.getDirection(3)==1) this.position.setDirection(new int[]{1, 0, 0, 0});
    }
    
    
    public void turn_right(){
    	this.robot.rotate(-90);

    	if (this.position.getDirection(0)==1) this.position.setDirection(new int[]{0, 0, 0, 1});
    	else if (this.position.getDirection(1) ==1) this.position.setDirection(new int[]{0, 0, 1, 0});
    	else if (this.position.getDirection(2)==1) this.position.setDirection(new int[]{1, 0, 0, 0});
    	else if (this.position.getDirection(3)==1) this.position.setDirection(new int[]{0, 1, 0, 0}); 	
    }

	public void avoidUsual(){
//		LCD.drawString("Current Point : (" + this.position.getX() + ", " + this.position.getY(),0, 4);
		this.turn_left();
		this.step_forward();
		this.turn_right();
		this.step_forward();
		
		this.step_forward();
		this.turn_right();
		this.step_forward();
		this.turn_left();
	}

	public void avoidEdgeUp(){
		this.turn_left();
		this.step_forward();
		this.turn_right();
		this.step_forward();
		this.turn_right();
		this.turn_right();
	}

	public void avoidEdgeDown(){
		this.turn_right();
		this.step_forward();
		this.turn_left();
		this.step_forward();
		this.turn_left();
		this.turn_left();
	}

	public void avoidEdgeSideUp(){
		this.turn_left();
		this.step_forward();
		this.turn_right();
		this.step_forward();
		this.turn_left();
	}

	public void avoidEdgeSideDown(){
		this.turn_right();
		this.step_forward();
		this.turn_left();
		this.step_forward();
		this.turn_right();
	}
	
	public void run(){
		int row = 6, col = 4;
		int currentCol = 0, currentRow = 0;
		int dir = 0;
		boolean r = false;
		
		while(currentCol < col){
			
			boolean f = false;
			
			if(r){
				currentRow = 1;
			}else{
				currentRow = 0;
			}
			while(currentRow < row-1){
//				LCD.drawString("(" + currentRow + ", " + currentCol, 0, 0);

				Delay.msDelay(1200);
				if(this.ir.distance < 50){
					if(this.position.x == 4 && this.position.direction[0] == 1){
						this.position.setBox(this.position.x, this.position.y);
						this.avoidEdgeUp();
						f = true;
					}else if(this.position.x == 1 && this.position.direction[1] == 1){
						this.position.setBox(this.position.x, this.position.y);
						this.avoidEdgeDown();
						f = true;
					}else{
						this.position.setBox(this.position.x, this.position.y);
						this.avoidUsual();
						currentRow+=1;
					}
					
				}else{
					this.step_forward();
				}
				currentRow+=1;
				if(currentCol == col - 2){
					this.travel = 25;
				}

			}
			
			
			
			
			if(dir == 0 && currentCol != col - 1 && !f){
				this.turn_left();
				
				Delay.msDelay(1200);
				if(this.ir.distance < 60){
					Sound.playTone(1000, 100);
					this.position.setBox(this.position.x, this.position.y);
					this.avoidEdgeSideUp();
					r = true;
				}else{
					this.step_forward();
					this.turn_left();
					r = false;
				}
				
				dir = 1;
			}else if(currentCol != col - 1 && !f){
				this.turn_right();
				Delay.msDelay(1200);
				if(this.ir.distance < 50){
					Sound.playTone(1000, 100);
					this.position.setBox(this.position.x, this.position.y);
					this.avoidEdgeSideDown();
					r = true;
				}else{
					this.step_forward();
					this.turn_right();
					r = false;
				}
				
				dir = 0;
			}
			
			if(!f){
				currentCol += 1;
			}else{
				currentCol += 1;
				if(dir == 0){
					dir = 1;
				}else{
					dir = 0;
				}
			}
			
		}
		
		this.turn_left();
		if(this.ir.distance < 50){
			this.avoidUsual();
			this.step_forward();
			this.turn_left();
		}else{
			this.step_forward();
			if(this.ir.distance < 50){
				this.avoidUsual();
				this.turn_left();
			}else{
				this.step_forward();
				this.step_forward();
				this.turn_left();
			}
		}
		
		
		LCD.drawString("Red1: (" + this.position.red1[0] + ", " + this.position.red1[1] + ")", 0, 0);
		LCD.drawString("Red2: (" + this.position.red2[0] + ", " + this.position.red2[1]+ ")", 0, 1);
		LCD.drawString("Box1: (" + this.position.box1[0] + ", " + this.position.box1[1]+ ")", 0, 2);
		LCD.drawString("Box2: (" + this.position.box2[0] + ", " + this.position.box2[1]+ ")", 0, 3);
		
		Button.waitForAnyPress();
	}

	// public void runWithNav(){
	// 	int row = 6, col = 4;
	// 	int currentCol = 0, currentRow = 0;
	// 	int dir = 0;

	// 	while(currentCol < col){
	// 		currentRow = 0;
	// 		while(currentRow < row){
	// 			LCD.drawString("(" + currentRow + ", " + currentCol, 0, 0);

	// 			Delay.msDelay(1200);
	// 			if(this.ir.distance < 50){
	// 				this.avoidUsual();
	// 				currentRow+=1;
	// 				this.position.setBox();
	// 			}else{
	// 				this.step_forward();
	// 			}
	// 			currentRow+=1;

	// 		}
			
	// 		if(dir == 0 && currentCol != col - 1){
	// 			this.turn_left();
	// 			this.step_forward();
	// 			this.turn_left();
	// 			dir = 1;
	// 		}else if(currentCol != col - 1){
	// 			this.turn_right();
	// 			this.step_forward();
	// 			this.turn_right();
	// 			dir = 0;
	// 		}
			
	// 		currentCol += 1;
	// 	}
		
	// 	this.turn_left();
	// 	this.step_forward();
	// 	this.step_forward();
	// 	this.step_forward();
	// 	this.turn_left();
		
	// 	LCD.drawString("Red1: (" + this.position.red1[0] + ", " + this.position.red1[1], 0, 0);
	// 	LCD.drawString("Red2: (" + this.position.red2[0] + ", " + this.position.red2[1], 0, 1);
	// 	LCD.drawString("Box1: (" + this.position.box1[0] + ", " + this.position.box1[1], 0, 2);
	// 	LCD.drawString("Box2: (" + this.position.box2[0] + ", " + this.position.box2[1], 0, 3);
		
	// 	Button.waitForAnyPress();
	// }
	

	// public void avoidWithNav(int col, int row, int dir){

	// }
}

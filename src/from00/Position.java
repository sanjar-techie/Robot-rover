package from00;


public class Position {
	public int x;
	public int y;
	public int[] direction;
	public int[] box1;
	public int[] box2;
	public int[] red1;
	public int[] red2;
	
	public Position(int x, int y, int[]direction){
		this.x = x;
		this.y = y;
		this.direction = direction; //[n, s, w ,e]
		this.red1 = new int[]{-1, -1};
		this.red2 = new int[]{-1, -1};
		
		this.box1 = new int[]{-1, -1};
		this.box2 = new int[]{-1, -1};
	}
	
	
	public int getX(){
		return this.x;
	}
	
	public void setRed(int x, int y){
		if(this.red1[0] == -1){
			this.red1 = new int[]{x, y};
		}else if(this.red2[0] == -1 && (this.red1[0] != x  ||  this.red1[1] != y)){
			this.red2 = new int[]{x, y};
		}
	}
	
	public void setBox(int x, int y){
		if(this.direction[0] == 1){
			if(this.box1[0] == -1 && x+1 < 6){
				this.box1 = new int[]{x+1, y};
			}else if(this.box2[0] == -1 && x+1 <6 && (this.box1[0] != (x+1)  ||  this.box1[1] != y)){
				this.box2 = new int[]{x+1, y};
			}
		}else if(this.direction[1] == 1){
			if(this.box1[0] == -1 && x-1 >= 0){
				this.box1 = new int[]{x-1, y};
			}else if(this.box2[0] == -1 && x-1 >= 0  && (this.box1[0] != (x-1)  ||  this.box1[1] != y)){
				this.box2 = new int[]{x-1, y};
			}
		}else if(this.direction[2] == 1){
			if(this.box1[0] == -1 && y-1 >= 0){
				this.box1 = new int[]{x, y-1};
			}else if(this.box2[0] == -1 && y-1 >= 0  && (this.box1[0] != x  ||  this.box1[1] != (y-1))){
				this.box2 = new int[]{x, y-1};
			}
		}else if(this.direction[3] == 1){
			if(this.box1[0] == -1 && y+1 < 4){
				this.box1 = new int[]{x, y + 1};
			}else if(this.box2[0] == -1 && y+1 < 4  && (this.box1[0] != x  ||  this.box1[1] != (y+1))){
				this.box2 = new int[]{x, y + 1};
			}
		}
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getDirection(int index){
		return this.direction[index];
	}
	
	public void setDirection(int[] direction){
		this.direction = direction;
	}
	
	public void changeX(int num){
		this.x+=num;
	}
	
	public void changeY(int num){
		this.y+=num;
	}
	
	public boolean isAtCorner(){
		int x = this.x;
		int y = this.y;
		if((x == 4 && y ==0) || (x == 5 && y ==2) || (x == 1 && y == 3)){
			return true;
		}
		return false;
	}
	
	public boolean isAtEdge51(){
		int x = this.x;
		int y = this.y;
		if((x == 5 && y == 0) && this.direction[3] == 1){
			return true;
		}
		return false;
	}
	
	public boolean isAtEdge01(){
		int x = this.x;
		int y = this.y;
		if((x == 1 && y == 1) && this.direction[1] == 1){
			return true;
		}
		return false;
	}
	
	public boolean isAtEdge02(){
		int x = this.x;
		int y = this.y;
		if((x == 0 && y == 1) && this.direction[3] == 1){
			return true;
		}
		return false;
	}
	
	public boolean isAtEdge52(){
		int x = this.x;
		int y = this.y;
		if((x == 4 && y == 2) && this.direction[0] == 1){
			return true;
		}
		return false;
	}
	
	public boolean isEndRight(){
		if(this.x == 0 && this.direction[1] == 1){
			return true;
		}
		return false;
	}
	
	public boolean isEndLeft(){
		if(this.x == 5 && this.direction[0] == 1){
			return true;
		}
		return false;
	}
	
}

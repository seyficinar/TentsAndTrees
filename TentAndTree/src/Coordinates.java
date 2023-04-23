
public class Coordinates {
	
	//Coordinates for possible tents
	int x;
	int y;
	//Coordinates of trees belongs to tents
	Coordinates cordOfTree;
	


	public Coordinates(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	
	//Getters and Setters
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Coordinates getCordOfTree() {
		return cordOfTree;
	}


	public void setCordOfTree(Coordinates cordOfTree) {
		this.cordOfTree = cordOfTree;
	}
	
	
	
	

}


public class Coordinates {

	// Coordinates for possible tents
	int x;
	int y;
	// Coordinates of trees belongs to tents
	Coordinates cordOfTree;
	
	//Constructors
	public Coordinates(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Coordinates(int x, int y, int x1, int y1) {
		this.x = x;
		this.y = y;
		this.cordOfTree = new Coordinates(x1, y1);
	}

	// Getters and Setters
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

	public Coordinates get(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}

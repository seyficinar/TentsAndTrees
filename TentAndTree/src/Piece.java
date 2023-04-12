
//Class for identifying pieces in the puzzle which could be Tree(T), Tent(X) and O is neither tent nor tree given in the input

public class Piece {

	char type; // (T, X or O)
	Piece next; // to access other pieces
	boolean isTree; //Checks whether it is tree or not
	Piece tent; //if isTree true, piece should have one tent
	

	// Coordinate values of pieces
	int cordX;
	int cordY;
	
	//Constructor
	public Piece(char type, Piece next, int cordX, int cordY) {

	}

	// Getters and Setters
	public char getType() {

	}

	public void setType(char type) {

	}

	public Piece getNext() {

	}

	public void setNext(Piece next) {

	}

	public int getCordX() {

	}

	public void setCordX(int cordX) {

	}

	public int getCordY() {

	}

	public void setCordY(int cordY) {

	}
	
	//Returns the number of tent a tree has
	public int numOfTent() {
		
	}
	
	//Returns true, if there is no tent around a tent
	public boolean checkTent() {
		
	}

}

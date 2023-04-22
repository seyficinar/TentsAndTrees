import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

	}

	public char[][] solution(char[][] puzzle) {
		
		//Creating an ArrayList and adding possible coordinates of tents to this list
		ArrayList<Coordinates> cordsOfTents = new ArrayList<Coordinates>();
		int size = puzzle[0].length;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				char c = puzzle[i][j];

				if (c == 'T') {

					if (i > 0) {
						Coordinates cord = new Coordinates(i - 1, j);
						cordsOfTents.add(cord);

					}
					if (i < size - 1) {
						Coordinates cord = new Coordinates(i + 1, j);
						cordsOfTents.add(cord);

					}
					if (j > 0) {
						Coordinates cord = new Coordinates(i, j - 1);
						cordsOfTents.add(cord);

					}
					if (j < size - 1) {
						Coordinates cord = new Coordinates(i, j + 1);
						cordsOfTents.add(cord);

					}

				}

			}
		}
		Queue k;
        State s;
        s = new State();
        k = new Queue(10000);
        k.enqueue(new Element(s));
		
		 while (!k.isEmpty()){
	           
	        }
		
		
		

		return null;
	}
	
	
	// Returns the number of trees in the puzzle
		public int numOfTents(char[][] puzzle) {
			int count = 0;

			for (char[] line : puzzle) {
				for (char p : line) {
					if (p == 'X') {
						count++;
					}
				}
			}

			return count;

		}

		// Returns the number of tents in the puzzle
		public int numOfTrees(char[][] puzzle) {
			int count = 0;

			for (char[] line : puzzle) {
				for (char p : line) {
					if (p == 'T') {
						count++;
					}
				}
			}

			return count;

		}

		// Checks whether the puzzle passes the constraints
		public boolean check(char[][] puzzle) {
			if (numOfTents(puzzle) != numOfTrees(puzzle)){

				return false;
			}
			if (puzzle == null) {
				return false;
			}
			int length = puzzle.length;

			for (int i = 0; i < length; i++) {
				for (int j = 0; j < length; j++) {
					char p = puzzle[i][j];

					if (p == 'X') {

						if (i > 0) {
							if (p == 'X') {
								return false;
							}
						}
						if (j > 0) {
							if (p == 'X') {
								return false;
							}
						}
						if (i < length - 1) {
							if (p == 'X') {
								return false;
							}
						}
						if (j < length - 1) {
							if (p == 'X') {
								return false;
							}
						}
						if (i > 0 && j > 0) {
							if (p == 'X') {
								return false;
							}
						}
						if (j > 0 && i < length - 1) {
							if (p == 'X') {
								return false;
							}
						}
						if (i < length - 1 && j < length - 1) {
							if (p == 'X') {
								return false;
							}
						}
						if (i > 0 && j < length - 1) {
							if (p == 'X') {
								return false;
							}
						}

					}
				}
			}

			return true;

		}

		// Prints the solution
		public void print(char[][] puzzle) {
			for (char[] line : puzzle) {
				for (char p : line) {
					System.out.print(p);
				}
				System.out.println();
			}

		}

}

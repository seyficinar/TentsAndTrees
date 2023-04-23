import java.awt.image.AbstractMultiResolutionImage;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of matrix nxn: ");
		int size = sc.nextInt();
		sc.nextLine();
		char[][] puzzle = new char[size][size];

		String s = "";

		for (int i = 0; i < size; i++) {
			System.out.println("Enter the next line of the puzzle: ");
			s = sc.nextLine();
			if (s.length() < size) {
				System.out.println("Error: input string is too short");
				break;
			}
			for (int j = 0; j < size; j++) {
				puzzle[i][j] = s.charAt(j);
			}
		}

		char[][] sol = solution(puzzle);
		print(sol);

	}

	

	public static char[][] solution(char[][] puzzle) {
		double depth = numOfTrees(puzzle);
		depth = Math.pow(4, depth);
		

		// Assumption trees can not be horizontally or vertically next to each other
		// Creating an ArrayList and adding possible coordinates of tents to this list
		ArrayList<Coordinates> cordsOfTents = new ArrayList<Coordinates>();
		int size = puzzle[0].length;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				char c = puzzle[i][j];

				if (c == 'T') {

					if (i > 0) {
						Coordinates cord = new Coordinates(i - 1, j);
						cord.cordOfTree = new Coordinates(i,j);
						cordsOfTents.add(cord);

					}
					if (i < size - 1) {
						Coordinates cord = new Coordinates(i + 1, j);
						cord.cordOfTree = new Coordinates(i,j);
						cordsOfTents.add(cord);

					}
					if (j > 0) {
						Coordinates cord = new Coordinates(i, j - 1);
						cord.cordOfTree = new Coordinates(i,j);
						cordsOfTents.add(cord);

					}
					if (j < size - 1) {
						Coordinates cord = new Coordinates(i, j + 1);
						cord.cordOfTree = new Coordinates(i,j);
						cordsOfTents.add(cord);

					}

				}

			}
		}
		cordsOfTents = removeDuplicates(cordsOfTents);
		for (Coordinates c : cordsOfTents) {
			System.out.println(c.getX() + ", " + c.getY());
		}

		Queue k = new Queue(10000);
		State s = new State();
		ArrayList<Coordinates> q = new ArrayList<>();
		s.cords = q;
		k.enqueue(new Element(s));
		int count = 0;
		
		while (!k.isEmpty() && count < depth*1000) {
			count++;
			s = k.dequeue().getData();
			char[][] temp = new char[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					char c = puzzle[i][j];
					temp[i][j] = c;
				}
			}
			if (s.cords != null) {
				for (Coordinates c : s.cords) {
					temp[c.getX()][c.getY()] = 'X';
				}
			}

			if (check(temp)) {
				return temp;
			}

			for (Coordinates c : cordsOfTents) {
				ArrayList<Coordinates> tempList = new ArrayList<Coordinates>();
				for (Coordinates w : s.cords) {
					
					tempList.add(w);
				}
				
				if (checkTheTrees(tempList, c)) {
					tempList.add(c);
				}

//				for (Coordinates k1 : tempList) {
//					System.out.print("(" + k1.getX() + ", " + k1.getY() + ") ");
//				}
//				System.out.println();
				Element child = new Element(new State(tempList));
				k.enqueue(child);
			}

		}

		System.out.println("Could not find a solution");
		return null;

	}

	// Returns the number of trees in the puzzle
	public static int numOfTents(char[][] puzzle) {
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
	public static int numOfTrees(char[][] puzzle) {
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
	public static boolean check(char[][] puzzle) {
		if (numOfTents(puzzle) != numOfTrees(puzzle)) {

			return false;
		}
		if (puzzle == null) {
			return false;
		}
		int length = puzzle.length;

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				char p = puzzle[i][j];
				if (p == 'T') {
					boolean b1 = false, b2 = false, b3 = false, b4 = false;
					if (i > 0) {
						b1 = (puzzle[i - 1][j] == 'X');
						b1 = true;

					}
					if (j > 0) {
						b2 = (puzzle[i][j - 1] == 'X');
						b2 = true;

					}
					if (i < length - 1) {
						b3 = (puzzle[i + 1][j] == 'X');
						b3 = true;

					}
					if (j < length - 1) {
						b4 = (puzzle[i][j + 1] == 'X');
						b4 = true;

					}
					if (!(b1 || b2 || b3 || b4)) {
						return false;
					}

				}

				if (p == 'X') {

					if (i > 0) {
						if (puzzle[i - 1][j] == 'X') {
							return false;
						}
					}
					if (j > 0) {
						if (puzzle[i][j - 1] == 'X') {
							return false;
						}
					}
					if (i < length - 1) {
						if (puzzle[i + 1][j] == 'X') {
							return false;
						}
					}
					if (j < length - 1) {
						if (puzzle[i][j + 1] == 'X') {
							return false;
						}
					}
					if (i > 0 && j > 0) {
						if (puzzle[i - 1][j - 1] == 'X') {
							return false;
						}
					}
					if (j > 0 && i < length - 1) {
						if (puzzle[i + 1][j - 1] == 'X') {
							return false;
						}
					}
					if (i < length - 1 && j < length - 1) {
						if (puzzle[i + 1][j + 1] == 'X') {
							return false;
						}
					}
					if (i > 0 && j < length - 1) {
						if (puzzle[i - 1][j + 1] == 'X') {
							return false;
						}
					}

				}
			}
		}

		return true;

	}

	// Prints the solution
	public static void print(char[][] puzzle) {
		if (puzzle == null) {
			System.out.println("No puzzle to print");
		} else {
			for (char[] line : puzzle) {
				for (char p : line) {
					System.out.print(p);
				}
				System.out.println();
			}
		}

	}
	
	public static boolean contains(Coordinates c, ArrayList<Coordinates> list) {
		for(Coordinates k : list) {
			if(c.getX() == k.getX() && c.getY()== k.getY()) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean contains(ArrayList<Coordinates> list1, ArrayList<Coordinates> list2) {
		for(Coordinates c : list1) {
			if(!contains(c, list2)) {
				return false;
			}
		}
		for(Coordinates c : list2) {
			if(!contains(c, list1)) {
				return false;
			}
		}
		
		return true;
		
	}
	
	public static ArrayList<Coordinates> removeDuplicates(ArrayList<Coordinates> list) {
		ArrayList<Coordinates> result = new ArrayList<Coordinates>();

		for (int i = 0; i < list.size(); i++) {
			boolean isDuplicate = false;

			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getX() == list.get(j).getX() && list.get(i).getY() == list.get(j).getY()) {
					isDuplicate = true;
					break;
				}
			}

			if (!isDuplicate) {
				result.add(list.get(i));
			}
		}

		return result;
	}
	
	public static boolean checkTheTrees(ArrayList <Coordinates> list, Coordinates c) {
		 
		for(Coordinates c2 : list) {
			if(c2.cordOfTree.getX()==c.cordOfTree.getX() && c2.cordOfTree.getY()==c.cordOfTree.getY()) {
				return false;
			}
		}
		
		return true;
	}

}

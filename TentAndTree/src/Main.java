import java.awt.image.AbstractMultiResolutionImage;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// Get the input from the user
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

		// Print the solution if exist
		char[][] sol = solution(puzzle);
		print(sol);

	}

	// Method that returns the solution whenever finds one. If no solution returns
	// null
	public static char[][] solution(char[][] puzzle) {

		// Assumption trees can not be horizontally or vertically next to each other
		// Creating an ArrayList and adding possible coordinates of tents to this list
		ArrayList<Coordinates> cordsOfTents = new ArrayList<Coordinates>();

		int size = puzzle[0].length;

		// Recording the coordinates of possbile tents
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				char c = puzzle[i][j];

				if (c == 'T') {

					if (i > 0) {
						Coordinates cord = new Coordinates(i - 1, j);
						cord.cordOfTree = new Coordinates(i, j);
						cordsOfTents.add(cord);

					}
					if (i < size - 1) {
						Coordinates cord = new Coordinates(i + 1, j);
						cord.cordOfTree = new Coordinates(i, j);
						cordsOfTents.add(cord);

					}
					if (j > 0) {
						Coordinates cord = new Coordinates(i, j - 1);
						cord.cordOfTree = new Coordinates(i, j);
						cordsOfTents.add(cord);

					}
					if (j < size - 1) {
						Coordinates cord = new Coordinates(i, j + 1);
						cord.cordOfTree = new Coordinates(i, j);
						cordsOfTents.add(cord);

					}

				}

			}
		}
		// Finds the number for maximum number of loop
		int num = numOfTrees(puzzle);
		int depth = cordsOfTents.size();
		int sum = 0;
		for (int i = 0; i < num; i++) {
			sum += Math.pow(depth, i + 1);
		}
		// Print for debugging
//		for (Coordinates c : cordsOfTents) {
//			System.out.println(c.getX() + ", " + c.getY());
//		}
		// Since it is a array implementation of queue, maximum size of array my
		// hardware allows is around Math.pow(10,9)
		// Because of this issue, the code wont be able to calculate the solution for a
		// large inputs according to hardware
		// But size of smaller 6x6 it works perfectly
		Queue k = new Queue();
		State s = new State();
		ArrayList<Coordinates> q = new ArrayList<>();
		s.cords = q;
		k.enqueue(new Element(s));
		int count = 0;
		int count2 = 0;
		while (!k.isEmpty() && count < Integer.MAX_VALUE) {
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
				if (numOfTents(temp) == numOfTrees(temp)) {
					if (check(temp) && checkTents(s.cords)) {
						return temp;
					}
				}
			}

			for (Coordinates c : cordsOfTents) {
				ArrayList<Coordinates> tempList = new ArrayList<Coordinates>();
				for (Coordinates w : s.cords) {

					tempList.add(w);
				}
				int x = c.getX();
				int y = c.getY();
				int x1 = c.getCordOfTree().getX();
				int y1 = c.getCordOfTree().getY();
				tempList.add(new Coordinates(x, y, x1, y1));
				tempList = removeExactDuplicates(tempList);

				// Print for debugging
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

			// Checks the situation two or more tree is connected with the same tent

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

	// Removes the excatDuplicate coordinates from the list
	public static ArrayList<Coordinates> removeExactDuplicates(ArrayList<Coordinates> list) {
		ArrayList<Coordinates> result = new ArrayList<Coordinates>();

		for (int i = 0; i < list.size(); i++) {
			boolean isDuplicate = false;

			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getX() == list.get(j).getX() && list.get(i).getY() == list.get(j).getY()
						&& list.get(i).getCordOfTree().getX() == list.get(j).getCordOfTree().getX()
						&& list.get(i).getCordOfTree().getY() == list.get(j).getCordOfTree().getY()) {
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

	// Removes the same coordinates but doent check which tree they belong
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

	// Checks whether one tree has more than on tents or not
	public static boolean checkTents(ArrayList<Coordinates> list) {
		list = removeExactDuplicates(list);

		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				int x1 = list.get(i).getCordOfTree().getX();
				int x2 = list.get(j).getCordOfTree().getX();
				int y1 = list.get(i).getCordOfTree().getY();
				int y2 = list.get(j).getCordOfTree().getY();
				if (x1 == x2 && y1 == y2) {
					return false;
				}
			}
		}

		return true;

	}

}

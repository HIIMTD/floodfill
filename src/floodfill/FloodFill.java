package floodfill;

import java.util.Random;

public class FloodFill {

	public void recursion(int color, int label, int x, int y) {
		if (x >= 0 && x < label && y >= 0 && y < label) {
			// change color here
			recursion(color, label, x + 1, y);
			recursion(color, label, x - 1, y);
			recursion(color, label, x, y + 1);
			recursion(color, label, x, y - 1);
		}
	}
	
	public void queue() {
		
	}
	
	public void scanLine() {
		
	}

	public static void main(String args[]) {
		int rows = 3000;
		int columns = 4000;

		int[][] array = new int[rows][columns];

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				array[i][j] = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
		
		//random point
		Random x = new Random();
		int s = x.nextInt(3000) + 1;
		Random y = new Random();
		int t = y.nextInt(4000) + 1;
		
		//r of circle
		int r = columns/60;
	}

}
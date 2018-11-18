package floodfill;



import java.util.LinkedList;

import java.util.Queue;

import java.util.Random;



public class FloodFill {

	static class Location {

		int x, y;

		public Location(int x, int y) {

			this.x = x;

			this.y = y;

		}

		

		public String toString() {

			return "(" + x + ", " + y + ")";

		}

	}

	int numRows;

	int numCols;

	Location startLocation;

	double r;

	

	int[][] array;

	

	public FloodFill(int[][] array, Location location, double r) {

		this.array = array;

		numRows = array.length;

		numCols = array[0].length;

		this.startLocation = location;

		this.r = r; 

		

		System.out.println("numRows:" + numRows);

		System.out.println("numCols:" + numCols);

		System.out.println("start Point: " + startLocation.toString());

		System.out.println("r:" + r);

	}

	

	private boolean toFill(Location location) {

		if (location.x < 0 || location.x >= numRows || location.y < 0 || location.y >= numCols) {

			return false;

		}

		

		if (array[location.x][location.y] != 0) {

			return false;

		}

		

		double distance = Math.sqrt((double) ((location.x - startLocation.x) * (location.x - startLocation.x) + (location.y - startLocation.y) * (location.y - startLocation.y) ));

//		System.out.println("distance: " + distance);

		if (distance < r) {

			return true;

		}

		return false;

	}

	

	public void fillUsingScanLine() {

		Queue<Location> queue = new LinkedList<>();

		queue.add(startLocation);

		

		while (!queue.isEmpty()) {

			Location location = queue.remove();

			array[location.x][location.y] = 1;

			//scan left

			for (int i = location.y - 1; i >=0; i--) {

				Location nextLocation = new Location(location.x, i);

				if (!toFill(nextLocation)) {

					break;

				}

				array[location.x][i] = 1;

				

			}

			

			//scan right

			for (int i = location.y +1; i < numCols; i++) {

				Location nextLocation = new Location(location.x, i);

				if (!toFill(nextLocation)) {

					break;

				}

				array[location.x][i] = 1;

			}



			

			Location nextLocation = new Location(location.x+1, location.y);

			if (toFill(nextLocation)) {

				queue.add(nextLocation);

			}

			

			nextLocation = new Location(location.x-1, location.y);

			if (toFill(nextLocation)) {

				queue.add(nextLocation);

			}			

		}

	}

	

	public void fillUsingQueue() {

		Queue<Location> queue = new LinkedList<>();

		queue.add(startLocation);

		

		while (!queue.isEmpty()) {

			Location location = queue.remove();



			array[location.x][location.y] = 1;



			Location nextLocation = new Location(location.x+1, location.y);

			if (toFill(nextLocation)) {

				queue.add(nextLocation);

			}

			

			nextLocation = new Location(location.x-1, location.y);

			if (toFill(nextLocation)) {

				queue.add(nextLocation);

			}

			

			nextLocation = new Location(location.x, location.y+1);

			if (toFill(nextLocation)) {

				queue.add(nextLocation);

			}

			

			nextLocation = new Location(location.x, location.y-1);

			if (toFill(nextLocation)) {

				queue.add(nextLocation);

			}

			

		}



	}

	

	public void fillUsingRecursion() {

		fillUsingRecursion(0, 1, startLocation);

	}

	





	public void fillUsingRecursion(int color, int label, Location location) {



//		System.out.println("testing: (" + x + ", " + y + ")" );

//		if (!toFill(x, y)) {

//			return;

//		}

		

//		System.out.println("filling: (" + x + ", " + y + ")" );



		array[location.x][location.y] = 1;

			

		Location nextLocation = new Location(location.x+1, location.y);

		if (toFill(nextLocation)) {

			fillUsingRecursion(0, 1, nextLocation);

		}

		

		nextLocation = new Location(location.x-1, location.y);

		if (toFill(nextLocation)) {

			fillUsingRecursion(0, 1, nextLocation);

		}

		

		nextLocation = new Location(location.x, location.y+1);

		if (toFill(nextLocation)) {

			fillUsingRecursion(0, 1, nextLocation);

		}

		

		nextLocation = new Location(location.x, location.y-1);

		if (toFill(nextLocation)) {

			fillUsingRecursion(0, 1, nextLocation);

		}



	}

	

	public void resetArray() {

		for (int i = 0; i < numRows; i++) {

			for (int j = 0; j < numCols; j++) {

				array[i][j] = 0;

			}

		}

	}

	

	public void printArray() {

		for (int i = 0; i < numRows; i++) {

			for (int j = 0; j < numCols; j++) {

				System.out.print(array[i][j]);

			}

			System.out.println();

		}

		System.out.println();

	}

	

	public void queue() {

		

	}

	

	public void scanLine() {

		

	}



	public static int[][] initArray(int rows, int cols) {

		int[][] array = new int[rows][cols];



		for (int i = 0; i < rows; i++)

			for (int j = 0; j < cols; j++)

				array[i][j] = 0;

		

		return array;

	}

	public static void main(String args[]) {

		int rows = 100;

		int columns = 100;

		double rMin = ((double) columns)/60;

		double rMax = rMin*3;

		

		Random random = new Random();



		long timeMethod1 = 0;

		long timeMethod2 = 0;

		long timeMethod3 = 0;



		for (int i = 0; i < 3; i++) {

			int[][] array = initArray(rows, columns);

			

			int s = random.nextInt(rows);

			int t = random.nextInt(columns);

			

			double r = rMin + (rMax - rMin) * random.nextDouble();

			

			double regionalSize = Math.PI * r * r;

			

			FloodFill ff = new FloodFill(array, new Location(s, t), r);

			

//			ff.printArray();

			

			System.out.println("using recursion");



		    long usedMemoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		    System.out.println("Used Memory before " + usedMemoryBefore);

			

			long startTime = System.nanoTime();

//			System.out.println("time: " + System.nanoTime());

			ff.fillUsingRecursion();

//			System.out.println("time: " + System.nanoTime());



			timeMethod1 = System.nanoTime() - startTime;

			

	        // working code here

		    long usedMemoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		    System.out.println("Used Memory After " + usedMemoryAfter);

			ff.printArray();

			

			System.out.println("using queue");

			ff.resetArray();

			startTime = System.nanoTime();

//			System.out.println("time: " + System.nanoTime());

			ff.fillUsingQueue();

//			System.out.println("time: " + System.nanoTime());



			timeMethod2 = System.nanoTime()- startTime;

			ff.printArray();

			

			System.out.println("uisng scanline");

			ff.resetArray();

			startTime = System.nanoTime();

//			System.out.println("time: " + System.nanoTime());

			ff.fillUsingScanLine();

//			System.out.println("time: " + System.nanoTime());

			timeMethod3 = System.nanoTime() - startTime;

			ff.printArray();

			

			System.out.println("reginal size: " + regionalSize);

			System.out.println("time method 1: " + timeMethod1);

			System.out.println("time method 2: " + timeMethod2);

			System.out.println("time method 3: " + timeMethod3);

		}

		

		

		

	}



}
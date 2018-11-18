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

		double rMin = ((double) columns)/20;

		double rMax = rMin*3;

		Random random = new Random();

		long timeMethodRecursion = 0;

		long timeMethodQueue = 0;

		long timeMethodScanline = 0;

		long totalTimeRecursion = 0;
		long totalTimeQueue = 0;
		long totalTimeScanline = 0;
		double totalRegionalSize = 0;
		long totoalMemoryRecursion =0;
		long totoalMemoryQueue =0;
		long totoalMemoryScaneline =0;

		for (int i = 0; i < 3; i++) {

			int[][] array = initArray(rows, columns);

			

			int s = random.nextInt(rows);

			int t = random.nextInt(columns);

			double r = rMin + (rMax - rMin) * random.nextDouble();

			double regionalSize = Math.PI * r * r;


			FloodFill ff = new FloodFill(array, new Location(s, t), r);

//#####################################################################
			System.out.println();
			System.out.println("using recursion");

		    long usedMemoryBeforeR = Runtime.getRuntime().freeMemory();

		    System.out.println("Used Memory before " + usedMemoryBeforeR);

			long startTimeR = System.nanoTime();

//			System.out.println("time1: " + System.nanoTime());

			ff.fillUsingRecursion();
			
			long endtTimeR = System.nanoTime();
			timeMethodRecursion = endtTimeR - startTimeR;
//			System.out.println("time2: " + timeMethodRecursion);

			long usedmemoryAfterR = Runtime.getRuntime().totalMemory();
			 System.out.println("used Memory after " + usedmemoryAfterR);

		    long memoryUsedR = usedmemoryAfterR - usedMemoryBeforeR;

		    System.out.println("Memory used " + memoryUsedR);

//			ff.printArray();

  //#####################################################################
		    System.out.println();
			System.out.println("using queue");

			ff.resetArray();
			
		    long usedMemoryBeforeQ = Runtime.getRuntime().freeMemory();

		    System.out.println("Used Memory before " + usedMemoryBeforeQ);

			long startTimeQ = System.nanoTime();

//			System.out.println("time1: " + System.nanoTime());

			ff.fillUsingQueue();
			
			long endTimeQ = System.nanoTime();

			timeMethodQueue = endTimeQ- startTimeQ;
//			System.out.println("time2: " + timeMethodQueue);
			
			long usedmemoryAfterQ = Runtime.getRuntime().totalMemory();
			 System.out.println("used Memory after " + usedmemoryAfterQ);

		    long memoryUsedQ = usedmemoryAfterQ - usedMemoryBeforeQ;

		    System.out.println("Memory used " + memoryUsedQ);

//			ff.printArray();

//#####################################################################
		    System.out.println();
			System.out.println("uisng scanline");

			ff.resetArray();
			
		    long usedMemoryBeforeS = Runtime.getRuntime().freeMemory();

		    System.out.println("Used Memory before " + usedMemoryBeforeS);

			long startTimeS = System.nanoTime();
//		    System.out.println("time1: " + System.nanoTime());

			ff.fillUsingScanLine();
			
			long endTimeS = System.nanoTime();

			timeMethodScanline = endTimeS - startTimeS;
//			System.out.println("time2: " + timeMethodScanline);
			
			long usedmemoryAfterS = Runtime.getRuntime().totalMemory();
			System.out.println("used Memory after " + usedmemoryAfterS);

		    long memoryUsedS = usedmemoryAfterS - usedMemoryBeforeS;

		    System.out.println("Memory used " + memoryUsedS);

//			ff.printArray();

			totalTimeRecursion +=timeMethodRecursion;
			totalTimeQueue += timeMethodQueue;
			totalTimeScanline +=timeMethodScanline;
			
			totalRegionalSize += regionalSize;
			
			totoalMemoryRecursion += memoryUsedR;
			totoalMemoryQueue += memoryUsedQ;
			totoalMemoryScaneline += memoryUsedS;
			
			System.out.println();

			System.out.println("reginal size: " + regionalSize);

			System.out.println("time method recursion: " + timeMethodRecursion);
			System.out.println("time method queue: " + timeMethodQueue);
			System.out.println("time method scanline: " + timeMethodScanline);
			
			System.out.println("memory method recursion: " + memoryUsedR);
			System.out.println("memory method queue: " + memoryUsedQ);
			System.out.println("memory method scanline: " + memoryUsedS);
			
			
			
			
			System.out.println("############################################");
			System.out.println();

		}

		//prtin out details after loop
		System.out.println("total region size: " + totalRegionalSize);
	
		System.out.println("total time method recursion: " + totalTimeRecursion);

		System.out.println("total time method queue: " + totalTimeQueue);

		System.out.println("total time method scanline: " + totalTimeScanline);
		
		System.out.println("total memory recursion: " + totoalMemoryRecursion);

		System.out.println("total memory queue: " + totoalMemoryQueue);

		System.out.println("total memory scanline: " + totoalMemoryScaneline);


	}

}
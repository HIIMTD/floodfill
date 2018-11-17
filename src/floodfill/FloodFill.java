package floodfill;
import java.util.Random;

public class FloodFill {
	

 public void recursion() {
	 
 }

public static void main(String args[]) {
	int rows = 3000;
	int columns = 4000;

	int[][] array = new int[rows][columns];

	for(int i = 0; i<rows; i++)
	    for(int j = 0; j<columns; j++)
	        array[i][j] = 0;

	for(int i = 0; i<rows; i++)
	{
	    for(int j = 0; j<columns; j++)
	    {
	        System.out.print(array[i][j]);
	    }
	    System.out.println();
	}
	Random x = new Random();
	int s = x.nextInt(3000) + 1;
	Random y = new Random();
	int t = y.nextInt(4000) + 1;
}
	
}
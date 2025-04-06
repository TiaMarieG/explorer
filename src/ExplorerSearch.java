import java.util.ArrayList;
import java.util.List;

public class ExplorerSearch {

    /**
     * Returns how much land area an explorer can reach on a rectangular island.
     * 
     * The island is represented by a rectangular int[][] that contains
     * ONLY the following nunbers:
     * 
     * '0': represents the starting location of the explorer
     * '1': represents a field the explorer can walk through
     * '2': represents a body of water the explorer cannot cross
     * '3': represents a mountain the explorer cannot cross
     * 
     * The explorer can move one square at a time: up, down, left, or right.
     * They CANNOT move diagonally.
     * They CANNOT move off the edge of the island.
     * They CANNOT move onto a a body of water or mountain.
     * 
     * This method should return the total number of spaces the explorer is able
     * to reach from their starting location. It should include the starting
     * location of the explorer.
     * 
     * For example
     * 
     * @param island the locations on the island
     * @return the number of spaces the explorer can reach
     */
    public static int reachableArea(int[][] island) {
        // Implement your method here!
        // Please also make more test cases
        // I STRONGLY RECOMMEND testing some helpers you might make too

        // Finding the explorer
        int[] startingPosition = explorerLocation(island);

        // Boolean array to check if a node has been visited
        boolean[][] visited = new boolean[island.length][island[0].length];


        return reachableArea(island, startingPosition, visited);
    }

    // First we want to find the explorer
    public static int[] explorerLocation(int[][] island) {

        for (int r = 0; r < island.length; r++) {
            // Array is not a square, so need to account for jagged size
            for (int c = 0; c < island[r].length; c++) {
                if (island[r][c] == 0) {
                    return new int[]{r, c};
                }
            }        
        }

        throw new IllegalArgumentException("No explorer present");
    }

    // Now we want to determine where the explorer can move
    public static List<int[]> possibleMoves(int[][] island, int[] currentLocation) {

        // Creating variables for currentLocation indices
        int currentR = currentLocation[0]; // {0, -}
        int currentC = currentLocation[1]; // {-, 1}

        // List of int arrays that stores pair values of where explorer can move
        List<int[]> moves = new ArrayList<>();

        // Checking UP
        int newR = currentR - 1;
        int newC = currentC;

        if (newR >= 0 && island[newR][newC] != 2 && island[newR][newC] != 3) {
            moves.add(new int[]{newR, newC});
        }

        // Checking DOWN
        newR = currentR + 1;
        newC = currentC;

        if (newR < island.length && island[newR][newC] != 2 && island[newR][newC] != 3) {
            moves.add(new int[]{newR, newC});
        }

        // Checking LEFT
        newR = currentR;
        newC = currentC - 1;

        if (newC >= 0 && island[newR][newC] != 2 && island[newR][newC] != 3) {
            moves.add(new int[]{newR, newC});
        }

        // Checking RIGHT
        newR = currentR;
        newC = currentC + 1;

        if (newC < island[0].length && island[newR][newC] != 2 && island[newR][newC] != 3) {
            moves.add(new int[]{newR, newC});
        }

        return moves;
    }

    // Finally - Check to see how much of the island the explorer can reach without backtracking over themselves (No infinite loop)
    public static int reachableArea(int[][] island, int[] currentPosition, boolean[][] visited) {
        
        // Base cases
        int currentR = currentPosition[0];
        int currentC = currentPosition[1];

        // If the spot has already been visited, return 0
        if (visited[currentR][currentC]) return 0;

        // Mark the locations as visited
        visited[currentR][currentC] = true;

        // Count the spot where the explorer starts
        int visitedAreas = 1;

        // Look at the neighbors
        List<int[]> possibleMoves = possibleMoves(island, currentPosition);

        for (int[] move : possibleMoves) {
            visitedAreas += (reachableArea(island, move, visited));
        }
        
        return visitedAreas;
    }
}

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
        return -1;
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
}

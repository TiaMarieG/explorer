import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    // Add more tests here!
    // Come up with varied cases

    @Test
    public void testReachableArea_allReachable() {
        int[][] island = {
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,0,1},
            {1,1,1,1,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(30, actual);
    }

    @Test
    public void testReachableArea_allUnreachable() {
        int[][] island = {
            {3,2,3,2,3,2},
            {2,3,2,3,2,3},
            {3,2,3,2,3,2},
            {3,2,3,2,0,3},
            {2,3,2,3,2,3},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(30, actual);
    }

    @Test
    public void testExplorerArea_offCenterGrid() {
        int[][] island = {
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,0,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
        };
        int[] expected = {2, 3};
        assertArrayEquals(expected, ExplorerSearch.explorerLocation(island));
    }

    @Test
    public void testExplorerArea_bottomRightGrid() {
        int[][] island = {
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,0},
        };
        int[] expected = {4, 5};
        assertArrayEquals(expected, ExplorerSearch.explorerLocation(island));
    }

    @Test
    public void testExplorerArea_explorerMissing_throwsException() {
        int[][] island = {
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.explorerLocation(island);
        });
        assertEquals("No explorer present", exception.getMessage());
    }

    @Test
    public void testPossibleMoves_allDirectionsOpen() {
        int[][] island = {
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,0,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
        };
        int[] location = {2, 2};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(4, moves.size());
        assertTrue(moveSet.contains("1,2")); // up
        assertTrue(moveSet.contains("3,2")); // down
        assertTrue(moveSet.contains("2,1")); // left
        assertTrue(moveSet.contains("2,3")); // right
    }

    @Test
    public void testPossibleMoves_allDirectionsBlocked() {
        int[][] island = {
            {1,1,1,1,1,1},
            {1,3,2,3,1,1},
            {1,2,0,2,1,1},
            {1,3,2,3,1,1},
            {1,1,1,1,1,1},
        };
        int[] location = {2, 2};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        assertTrue(moves.isEmpty());
    }

    @Test
    public void testPossibleMoves_partialEdge() {
        int[][] island = {
            {1,1,1,1,1,0}
        };
        int[] location = {0, 5};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("0,4")); // only left
    }

    @Test
    public void testPossibleMoves_twoWalls() {
        int[][] island = {
            {3,2,3,2,3,2},
            {1,1,0,1,1,1},
            {2,3,2,3,2,3}
        };
        int[] location = {1, 2};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(2, moves.size());
        assertTrue(moveSet.contains("1,1")); // left
        assertTrue(moveSet.contains("1,3")); // right
    }
    
    // Helper function from matrix-livecode
    private Set<String> toSet(List<int[]> list) {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) {
            set.add(arr[0] + "," + arr[1]);
        }
        return set;
    }
}
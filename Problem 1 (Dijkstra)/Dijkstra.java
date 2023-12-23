import java.util.*;

public class Dijkstra {
    private int[][] adjacentcyMatrex;
    public int[] shortestPath;
    public int[] previousNode;
    private boolean[] visitied;
    final int infinity = (int) Math.floor(Double.POSITIVE_INFINITY);

    public Dijkstra(int[][] adjacentcyMatrex) {
        this.adjacentcyMatrex = adjacentcyMatrex;
        shortestPath = new int[adjacentcyMatrex.length];
        previousNode = new int[adjacentcyMatrex.length];
        visitied = new boolean[adjacentcyMatrex.length];
    }

    private int getSmallestUnvisitedNode(int[] arr) {
        int temp = infinity;
        int smallestInx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < temp && !visitied[i]) {
                temp = arr[i];
                smallestInx = i;
            }
        }
        return smallestInx;
    }

    public void setShortestPathVector(int start) {
        // setting all pathes with infinity initially
        Arrays.fill(shortestPath, infinity);
        // setting all nodes unvisited
        Arrays.fill(visitied, false);
        shortestPath[start] = 0;
        for (int i = 0; i < adjacentcyMatrex.length; i++) {
            start = getSmallestUnvisitedNode(shortestPath);
            for (int j = 0; j < adjacentcyMatrex[0].length; j++) {
                if (adjacentcyMatrex[start][j] > 0 && !visitied[j]
                        && adjacentcyMatrex[start][j] + shortestPath[start] < shortestPath[j]) {
                    shortestPath[j] = adjacentcyMatrex[start][j] + shortestPath[start];
                    previousNode[j] = start;
                }
            }
            // mark finished node as visited
            visitied[start] = true;
        }
    }

    public Object[] getShortestPath(int start, int end) {
        List<Integer> path = new ArrayList<>();
        path.add(end);
        int temp = end;
        while (temp != start) {
            path.add(previousNode[temp]);
            temp = path.get(path.size() - 1);
        }
        Collections.reverse(path);

        return path.toArray();
    }
}
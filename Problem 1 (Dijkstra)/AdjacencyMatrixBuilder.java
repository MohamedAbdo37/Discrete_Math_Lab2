import java.security.InvalidKeyException;

public class AdjacencyMatrixBuilder {
    private String[] Nodes;
    private String[] Edges;
    private int[] Costs;
    public int[][] adjacentcyMatrex;

    public AdjacencyMatrixBuilder(String[] Nodes, String[] Edges, int[] costs) {
        this.Nodes = Nodes;
        this.Edges = Edges;
        this.Costs = costs;
        adjacentcyMatrex = new int[Nodes.length][Nodes.length];
    }

    public int searchForNode(String node) {
        for (int i = 0; i < Nodes.length; i++) {
            if (Nodes[i].equals(node))
                return i;
        }
        return -1;
    }

    public void createAdMat() throws InvalidKeyException {
        // looping on edges array to set the adjacency matrix
        for (int i = 0; i < Edges.length; i++) {
            int x = searchForNode(Character.toString(Edges[i].charAt(0)));
            int y = searchForNode(Character.toString(Edges[i].charAt(2)));
            if (x == -1 || y == -1)
                throw new InvalidKeyException();
            adjacentcyMatrex[x][y] = this.Costs[i];
            adjacentcyMatrex[y][x] = this.Costs[i];
        }
    }

}

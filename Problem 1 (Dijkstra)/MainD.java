import java.security.InvalidKeyException;
import java.util.Scanner;

public class MainD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // reading nodes (airports)
        System.out.println("Enter the list of airports: ");
        String NodesString = sc.nextLine().replaceAll(" ", "");
        String[] Nodes = NodesString.split(",");
        // check the validity of input
        if (NodesString.length() == 0) {
            System.out.println("Error: empty input");
            return;
        } else {
            if (Nodes.length == 0) {
                System.out.println("Error: empty input");
                return;
            }
        }

        // reading edges (flights)
        System.out.println("Enter the flights: ");
        String EdgesString = sc.nextLine().replaceAll(" ", "");
        String[] Edges = EdgesString.split(",");
        // check the validity of input
        if (EdgesString.length() == 0) {
            System.out.println("Error: empty input");
            return;
        } else {
            if (Edges.length == 0) {
                System.out.println("Error: empty input");
                return;
            }
        }

        // reading costs (distance)
        int[] costs = new int[Edges.length];
        System.out.println("The distance for each flight (in miles)");
        for (int i = 0; i < Edges.length; i++) {
            System.out.print(Edges[i] + ": ");
            costs[i] = sc.nextInt();
            if (costs[i] <= 0) {
                System.out.println("Error: There is no zero or negative distance");
                --i;
            }
        }
        AdjacencyMatrixBuilder adjacencyMatrix = new AdjacencyMatrixBuilder(Nodes, Edges, costs);
        try {
            adjacencyMatrix.createAdMat();
        } catch (Exception InvalidKeyException) {
            System.out.println("Error: Unfound route");
        }

        // for (int i = 0; i < Nodes.length; i++) {
        // for (int j = 0; j < Nodes.length; j++) {
        // System.out.print(adjacencyMatrix.adjacentcyMatrex[i][j] + " ");
        // }
        // System.out.println();
        // }
    }
}

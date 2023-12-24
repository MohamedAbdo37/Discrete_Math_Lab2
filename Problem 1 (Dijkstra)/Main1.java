import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main1 {
    // function for removing repeated nodes
    public static String[] removeRepetition(String[] nodes) {
        List<String> nodesCopy = new ArrayList<>();
        for (int i = 0; i < nodes.length; i++) {
            nodesCopy.add(nodes[i]);
        }
        for (int i = 0; i < nodes.length; i++) {
            String temp = nodes[i];
            for (int j = i + 1; j < nodes.length; j++) {
                if (temp.equals(nodes[j]))
                    nodesCopy.remove(j);
            }
        }
        return nodesCopy.toArray(new String[0]);
    }

    // searching for node in the array of nodes
    public static int searchForNode(String[] Nodes, String node) {
        for (int i = 0; i < Nodes.length; i++) {
            if (Nodes[i].equals(node))
                return i;
        }
        return -1;
    }

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

        // removing repeated nodes (airports) if found
        Nodes = removeRepetition(Nodes);

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
            try {
                costs[i] = sc.nextInt();
            } catch (Exception InputMismatchException) {
                System.out.println("Error: distance should be rounded to the nearst integer");
                --i;
                sc.nextLine();
                continue;
            }
            if (costs[i] <= 0) {
                System.out.println("Error: There is no zero or negative distance");
                --i;
            }
        }

        // creating the adjacency matrix of the graph
        AdjacencyMatrixBuilder adjacencyMatrix = new AdjacencyMatrixBuilder(Nodes, Edges, costs);

        try {
            adjacencyMatrix.createAdMat();
        } catch (Exception InvalidKeyException) {
            System.out.println("Error: Unfound airport");
            return;
        }

        sc.nextLine();

        // reading start node
        System.out.print("Enter source airport: ");
        String startString = sc.nextLine();
        int start = searchForNode(Nodes, startString);
        if (start == -1) {
            System.out.println("Error: unfound source");
            return;
        }

        // reading destination node
        System.out.print("Enter destination airport: ");
        String endString = sc.nextLine();
        int end = searchForNode(Nodes, endString);
        if (end == -1) {
            System.out.println("Error: unfound destination");
            return;
        }

        // creat dijkstra shortest path table from start node given by the user
        Dijkstra dijkstra = new Dijkstra(adjacencyMatrix.adjacentcyMatrex);
        dijkstra.setShortestPathVector(start);

        // printing shortest path
        System.out.print("Shortest path from " + startString + " to " + endString + ": ");
        Object[] shortestPath;
        try {
            shortestPath = dijkstra.getShortestPath(start, end);
        } catch (Exception NoConnectionPendingException) {
            System.out.println("path not found");
            return;
        }
        for (int i = 0; i < shortestPath.length; i++) {
            System.out.print(Nodes[(int) shortestPath[i]]);
            if (i != shortestPath.length - 1)
                System.out.print(" - ");
        }
        System.out.println();
        // printing total distance
        System.out.print("Total distance: ");
        System.out.print(dijkstra.shortestPath[end] + " miles");

    }
}

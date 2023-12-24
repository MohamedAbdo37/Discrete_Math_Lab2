import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main2 {

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // reading nodes (classes)
        System.out.println("Classes: ");
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

        // removing repeated nodes (classes) if found
        Nodes = removeRepetition(Nodes);

        // reading edges (Conflicting classes)
        System.out.println("Conflicting classes (cannot occur simultaneously): ");
        String EdgesString = sc.nextLine().replaceAll(" ", "");
        String[] Edges = EdgesString.split(",");
        // check the validity of input
        if (EdgesString.length() == 0) {
            Edges = new String[0];
        } else {
            if (Edges.length == 0) {
                Edges = new String[0];
            }
        }

        // creating the adjacency matrix of the graph
        AdjacencyMatrixBuilder adjacencyMatrix = new AdjacencyMatrixBuilder(Nodes, Edges);

        try {
            adjacencyMatrix.createAdMat();
        } catch (Exception InvalidKeyException) {
            System.out.println("Error: Unfound class");
            return;
        }

        // creating object of graph coloring class
        GraphColoring gColoring = new GraphColoring(adjacencyMatrix.adjacentcyMatrex);
        // setting nodes' colors
        gColoring.setColors();
        // printing nodes' colors
        gColoring.printColors(Nodes);
    }
}

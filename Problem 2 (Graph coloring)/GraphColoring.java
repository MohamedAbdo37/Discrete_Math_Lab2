import java.util.Arrays;

public class GraphColoring {
    String[] colors = { "red", "orange", "green", "blue", "white", "blace", "yellow", "brown", "violet" };
    int[] nodesColors;
    int[][] adjacencyMatrix;

    // constractor
    public GraphColoring(int[][] adjMatrx) {
        this.adjacencyMatrix = adjMatrx;
        nodesColors = new int[adjMatrx.length];
        // fill nodesColors as empty initially
        Arrays.fill(nodesColors, -1);
    }

    // setting colors function --> puts nodes colors in nodesColors array as indeces
    // of colors mapped in colors array
    public void setColors() {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            int temp = 0;
            for (int j = 0; j < adjacencyMatrix[0].length; j++) {
                if (adjacencyMatrix[i][j] == 1 && nodesColors[j] == temp) {
                    ++temp;
                    // restaring the loop
                    j = -1;
                }
            }

            nodesColors[i] = temp;
        }
    }

    // printing nodes colors
    public void printColors(String[] nodes) {
        System.out.println("Optimized Class Schedule:");
        for (int i = 0; i < nodes.length; i++) {
            int colorInx = nodesColors[i];
            String cString;
            try {
                cString = colors[i];
            } catch (Exception ArrayIndexOutOfBoundsException) {
                colorInx = -1;
            }
            if (colorInx != -1)
                System.out.println(nodes[i] + " - " + colors[colorInx]);
            else
                System.out.println(nodes[i] + " - " + "no enough colors sorry :)");
        }
    }
}

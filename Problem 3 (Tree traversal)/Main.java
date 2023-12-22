import java.util.*;

class Main {
    public static void main(String[] args) {
        List<Integer> nodes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value of the root node: ");
        int node = 0;
        try {
            node = sc.nextInt();
        } catch (Exception InputMismatchException) {
            System.out.println("Error: Node value must be integer");
            return;
        }
        // check if root node is -1
        if (node == -1) {
            System.out.println("Error: root node can't be empty");
            return;
        } else if (node < 0) {
            System.out.println("Error: node value must be a positive integer");
            return;
        }

        // adding node to nodes list
        nodes.add(node);
        // start loop of children nodes
        boolean flag = true;
        while (flag) {
            System.out.println("Enter left child value of " + nodes.get(nodes.size() - 1) + "(or -1 to skip):");
            sc.nextInt();
        }
    }
}
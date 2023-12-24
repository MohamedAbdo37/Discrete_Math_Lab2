import java.util.*;

class Main3 {
    public static void main(String[] args) {
        List<Integer> nodes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value of the root node: ");
        int node = 0;

        while (true) {
            try {
                node = sc.nextInt();
            } catch (Exception InputMismatchException) {
                System.out.println("Error: Node value must be integer, try again:");
                sc.next();
                continue;
            }
            // check if root node is -1
            if (node == -1) {
                System.out.println("Error: root node can't be empty, try again:");
                continue;
            } else if (node < 0) {
                System.out.println("Error: node value must be a positive integer, try again:");
                continue;
            } else
                break;
        }
        // adding node to nodes list
        Tree tree = new Tree(node);
        // start loop of children nodes
        tree.readTree(tree.root);
        // printing preorder traversal
        System.out.print("Preorder Traversal: ");
        tree.preOrderTraversal(tree.root);
        System.out.println();

        // printing inorder traversal
        System.out.print("Inorder Traversal: ");
        tree.inOrderTraversal(tree.root);
        System.out.println();

        // printing postorder traversal
        System.out.print("Postorder Traversal: ");
        tree.postOrderTraversal(tree.root);
    }
}
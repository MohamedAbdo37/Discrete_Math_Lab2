import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedTransferQueue;

public class Tree {
    class Node {
        public int value;
        public Node lChild;
        public Node rChild;

        // constructor of node
        public Node(int value) {
            this.value = value;
        }
    }

    Node root;
    Queue<Node> nodesQueue = new LinkedTransferQueue<>();
    Scanner sc = new Scanner(System.in);

    public Tree(int value) {
        this.root = new Node(value);
    }

    // reading tree nodes recursivily
    public void readTree(Node node) {
        System.out.println("Enter " + "left" + " child value of " + node.value + "(or -1 to skip):");
        int newNodeValue = 0;
        // reading left child of node
        while (true) {
            try {
                newNodeValue = sc.nextInt();
            } catch (Exception InputMismatchException) {
                System.out.println("Error: Node value must be integer, try again:");
                sc.next();
                continue;
            }
            if (newNodeValue < -1) {
                System.out.println("Error: Node value can't be negative (except -1 to skip), try again:");
                continue;
            } else
                break;
        }
        if (newNodeValue != -1) {
            Node newNode = new Node(newNodeValue);
            node.lChild = newNode;
            nodesQueue.add(newNode);
        } else
            node.lChild = null;
        System.out.println("Enter " + "right" + " child value of " + node.value + "(or -1 to skip):");
        // reading the right child of the node
        while (true) {
            try {
                newNodeValue = sc.nextInt();
            } catch (Exception InputMismatchException) {
                System.out.println("Error: Node value must be integer, try again:");
                sc.next();
                continue;
            }
            if (newNodeValue < -1) {
                System.out.println("Error: Node value can't be negative (except for -1 to skip), try again:");
                continue;
            } else
                break;
        }
        if (newNodeValue != -1) {
            Node newNode = new Node(newNodeValue);
            node.rChild = newNode;
            nodesQueue.add(newNode);
        } else
            node.rChild = null;
        if (!nodesQueue.isEmpty())
            readTree(nodesQueue.poll());
    }

    // getting preOrdeer Traversal of the tree recursivily
    public void preOrderTraversal(Node node) {
        if (node == null)
            return;
        System.out.print(node.value + " ");
        preOrderTraversal(node.lChild);
        preOrderTraversal(node.rChild);
    }

    // getting preOrdeer Traversal of the tree recursivily
    public void postOrderTraversal(Node node) {
        if (node == null)
            return;
        postOrderTraversal(node.lChild);
        postOrderTraversal(node.rChild);
        System.out.print(node.value + " ");
    }

    // getting inOrdeer Traversal of the tree recursivily
    public void inOrderTraversal(Node node) {
        if (node == null)
            return;
        inOrderTraversal(node.lChild);
        System.out.print(node.value + " ");
        inOrderTraversal(node.rChild);
    }

}

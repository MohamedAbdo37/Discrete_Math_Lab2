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
        int newNodeValue = sc.nextInt();
        if (newNodeValue != -1) {
            Node newNode = new Node(newNodeValue);
            node.lChild = newNode;
            nodesQueue.add(newNode);
        }
        System.out.println("Enter " + "right" + " child value of " + node.value + "(or -1 to skip):");
        newNodeValue = sc.nextInt();
        if (newNodeValue != -1) {
            Node newNode = new Node(newNodeValue);
            node.rChild = newNode;
            nodesQueue.add(newNode);
        }
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

    // // getting inOrdeer Traversal of the tree recursivily
    // public void inOrderTraversal(Node node) {
    // }

}

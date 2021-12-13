package net.yorksolutions.javamvn;

import java.util.function.Consumer;

// Utility
class Ref<T extends Comparable<T>> {
    // new node object with initial value null
    public Node<T> node = null;
}

// Implementation
class Node<T extends Comparable<T>> {
    //3 fields
    public T value;
    //references that point to a ref object and initialized to new Ref object
    public Ref<T> left = new Ref<>();
    public Ref<T> right = new Ref<>();

    // constructor method - we have defined so default constructor goes away
    Node(T value) {
        // taking our node and its field value and set it to the value passed as an argument
        this.value = value;
    }

    // this reference if a copy of the Ref object
    static <T extends Comparable<T>> void add(Ref<T> root, T value) {
        //instead of recursion
        while(root.node != null) {
            if (value.compareTo(root.node.value) < 0)
                // now set equal to childs left node
                root = root.node.left;
            else if (value.compareTo(root.node.value) > 0)
                root = root.node.right;
        }

        // when we find a null value its a place we can insert and we insert of new value
        root.node = new Node<>(value);

    }

    // call the given function for each element in the tree, in order from least to greatest
    static <T extends Comparable<T>> void inOrder(Ref<T> root, Consumer<T> function) {
        // termination check - id the node null - whenever we create a Ref we initialize to null
        //will eventually get to the point in the tree where there is a null value
        if (root.node == null) {
            return;
        }
        // recursive
        // first time through left and right will be null - still just default initialized
        inOrder(root.node.left, function);
        // must call dot accept to actually do the work and call whatever we pass it
        function.accept(root.node.value); // allows your function to take in a single argument of type Integer (defined in inOrder parameters)
        inOrder(root.node.right, function);
    }

    static <T extends Comparable<T>> Ref search(Ref<T> root, T value){
        while (root.node != null) {
            if (value.compareTo(root.node.value) < 0) {
                root = root.node.left;
            } else if (value.compareTo(root.node.value) > 0) {
                root = root.node.right;
            } else {
                return root;
            }
        }
        throw new RuntimeException("Value not found!");
    }

    static <T extends Comparable<T>> void remove(Ref<T> root) {
        // no children
        if (root.node.left.node == null && root.node.right.node == null) {
            root.node = null;
        }
        // one child
        // if both true or both false it fails , if one of each then evaluates to true
        else if (root.node.left.node == null ^ root.node.right.node == null) {
            root.node = root.node.left.node == null ? root.node.right.node : root.node.left.node;
        }
        // two children
        // find the next smallest node and replace value being removed with this value instead
        else {
            // starting at the left subtree
            Ref<T> current = root.node.left;
            // find the right most leaf of the left subtree
            while(current.node.right.node != null) {
                current = current.node.right;
            }

            // replace value with next smallest value
            root.node.value = current.node.value;

            // pulling the left subtree of the right most leaf if the left subtree
            current.node = current.node.left.node;
        }
    }
}

// data structure that will keep a list in order
// Public API
// generics are how we solve strong typing and let us choose which kind of tree we want to create
public class BinaryTree<T extends Comparable<T>> {
    private final Ref<T> root = new Ref<>();

    public void addNode(T value) {
        Node.add(root, value);
    }

    // accept a function with the consumer generic class -
    // must specify an object for an argument in a generic class so Integer is a class form of int
    public void inOrder(Consumer<T> function) {
        Node.inOrder(root, function);
    }

    public void remove(T value){
        Ref<T> nodeToRemove = Node.search(root, value);
        Node.remove(nodeToRemove);
    }

    private static BinaryTree<Integer> createTestTree() {
        BinaryTree tree = new BinaryTree();
        tree.addNode(5);
        tree.addNode(2);
        tree.addNode(4);
        tree.addNode(50);
        tree.addNode(23);
        tree.addNode(1);
        tree.addNode(22);
        tree.addNode(21);
        tree.addNode(24);
        tree.addNode(25);
        tree.addNode(6);
        return tree;
    }

    private static BinaryTree<String> createTestTreeString() {
        BinaryTree tree = new BinaryTree();
        tree.addNode("corn");
        tree.addNode("apple");
        tree.addNode("banana");
        tree.addNode("lettuce");
        tree.addNode("kiwi");
        tree.addNode("mango");
        tree.addNode("grape");
        tree.addNode("carrot");
        tree.addNode("tomato");
        tree.addNode("zucchini");
        tree.addNode("raspberry");
        return tree;
    }

    // main is our entry point for Java
    public static void main(String[] args) {

        BinaryTree<Integer> tree = createTestTree();
        System.out.println("Initial Test Tree:");
        tree.inOrder((Integer value) -> {
            System.out.println(value);
        });
        System.out.println("________________________");

        tree.addNode(3);
        System.out.println("Test tree with 3 node added:");
        tree.inOrder((Integer value) -> {
            System.out.println(value);
        });
        System.out.println("________________________");

        tree.remove(22);
        System.out.println("Test tree with 22 node removed:");
        tree.inOrder(System.out::println);
        System.out.println("________________________");

        BinaryTree<String> stringBinaryTree = createTestTreeString();
        System.out.println("String Test Tree printed using inOrder:");
        stringBinaryTree.inOrder(System.out::println);
        System.out.println("________________________");

        stringBinaryTree.addNode("pineapple");
        stringBinaryTree.addNode("cucumber");
        System.out.println("String Test Tree with pineapple and cucumber added:");
        stringBinaryTree.inOrder(System.out::println);
        System.out.println("________________________");

        stringBinaryTree.remove("banana");
        stringBinaryTree.remove("pineapple");
        System.out.println("String Test Tree with banana and pineapple removed:");
        stringBinaryTree.inOrder(System.out::println);
        System.out.println("________________________");
    }
}

// here we use a binary tree rather than an array we can sort
// we will more likely be using arrays than binary trees
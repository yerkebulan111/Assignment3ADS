import java.util.Iterator;

public class MyBST<K extends Comparable<K>, V> implements Iterable<MyBST<K, V>.NodePair> {
    private Node<K,V> root;
    private int size;


    private static class Node<K,V> {
        private K key;
        private V value;
        private Node<K,V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyBST() {}

    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            size++;
            return;
        }

        Node current = root;
        while (true) {
            int cmp = key.compareTo((K) current.key);
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = new Node(key, value);
                    size++;
                    return;
                }
                current = current.left;
            } else if (cmp > 0) {
                if (current.right == null) {
                    current.right = new Node(key, value);
                    size++;
                    return;
                }
                current = current.right;
            } else {
                current.value = value;
                return;
            }
        }
    }

    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo((K) current.key);
            if (cmp == 0) return (V) current.value;
            else if (cmp < 0) current = current.left;
            else current = current.right;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void delete(K key) {
        Node parent = null;
        Node current = root;

        while (current != null) {
            int cmp = key.compareTo((K) current.key);
            if (cmp == 0) {
                // case: two children
                if (current.left != null && current.right != null) {
                    Node successorParent = current;
                    Node successor = current.left;
                    while (successor.right != null) {
                        successorParent = successor;
                        successor = successor.right;
                    }
                    current.key = successor.key;
                    current.value = successor.value;
                    // delete successor
                    if (successorParent.right == successor) {
                        successorParent.right = successor.left;
                    } else {
                        successorParent.left = successor.left;
                    }
                }
                // case: one child or no child
                else {
                    Node child = (current.left != null) ? current.left : current.right;
                    if (parent == null) {
                        root = child;
                    } else if (parent.left == current) {
                        parent.left = child;
                    } else {
                        parent.right = child;
                    }
                }
                size--;
                return;
            }
            parent = current;
            current = (cmp < 0) ? current.left : current.right;
        }
    }

    public class NodePair {
        private K key;
        private V value;

        public NodePair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private class Stack {
        private Node<K, V>[] elements;
        private int size;

        //@SuppressWarnings("unchecked")
        public Stack(int capacity) {
            elements = (Node<K, V>[]) new Node[capacity]; // Need cast
            size = 0;
        }

        public void push(Node<K, V> node) {
            elements[size++] = node;
        }

        public Node<K, V> pop() {
            return elements[--size];
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }


    private class MyIterator implements Iterator<NodePair> {
        private Stack stack;
        private Node current;

        public MyIterator() {
            stack = new Stack(MyBST.this.size);
            current = root;
        }

        @Override
        public boolean hasNext() {
            return current != null || !stack.isEmpty();
        }

        @Override
        public NodePair next() {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            NodePair pair = new NodePair((K) current.key, (V) current.value);
            current = current.right;
            return pair;
        }
    }

    @Override
    public Iterator<NodePair> iterator() {
        return new MyIterator();
    }
}

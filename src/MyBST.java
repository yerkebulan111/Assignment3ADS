public class MyBST<K extends Comparable<K>, V> implements Iterable<MyBST.Element<K,V>> {
    private Node root;
    private int size;

    private class Node {
        private Node left, right;
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyBST() {}

    public void put(K key, V value) {
        Node newNode = new Node(key, value);
        Node current = root;

        while (current != null) {
            if (current.key.compareTo(key) >= 0) {
                current = current.left;
            }
            else if (current.key.compareTo(key) < 0) {
                current = current.right;
            }
        }
        current = newNode;
        size++;
    }





    public Iterable<K> iterator() {
        for (Element<K,V> element : ? ){
            System.out.println("key is " + element.getKey() + " and value is " + element.getValue());
        }
    }


    private class Element<K,V> {
        private K key;
        private V value;

        public Element(K key, V value) {
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





}

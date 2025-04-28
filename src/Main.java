public class Main {
    public static void main(String[] args) {
        MyBST<Integer, String> tree = new MyBST<>();

        tree.put(5, "Five");
        tree.put(3, "Three");
        tree.put(7, "Seven");
        tree.put(2, "Two");

        System.out.println("Tree size: " + tree.size());

        for (var elem : tree) {
            System.out.println("Key is " + elem.getKey() + ", value is " + elem.getValue());
        }
    }
}

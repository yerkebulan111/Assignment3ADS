import java.util.Random;

public class HashTableTest {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        Random rand = new Random();

        // Add 10,000 elements to the hash table
        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(rand.nextInt(1000), "Student" + i);
            Student value = new Student("Student" + i);
            table.put(key, value);
        }

        // Print the number of elements in each bucket
        for (int i = 0; i < table.getM(); i++) {
            int count = 0;
            HashNode<MyTestingClass, Student> current = table.getChainArray()[i];
            while (current != null) {
                count++;
                current = current.next;
            }
            System.out.println("Bucket " + i + " contains " + count + " elements.");
        }
    }
}

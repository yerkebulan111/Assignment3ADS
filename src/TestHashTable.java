import java.util.Random;

public class TestHashTable {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int randomId = random.nextInt(100000);
            String randomName = "Name" + random.nextInt(100000);
            MyTestingClass key = new MyTestingClass(randomId, randomName);
            Student value = new Student("Student" + i);
            table.put(key, value);
        }

        // Now, print number of elements in each chain:
        for (int i = 0; i < table.getM(); i++) {
            int chainSize = 0;
            MyHashTable.HashNode<MyTestingClass, Student> node = table.getChainArray()[i];
            while (node != null) {
                chainSize++;
                node = node.next;
            }
            System.out.println("Bucket " + i + ": " + chainSize + " elements");
        }
    }
}

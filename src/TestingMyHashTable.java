import java.util.Random;

public class TestingMyHashTable {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        Random random = new Random();


        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(random.nextInt(10000 * 2), "TestKey" + i);
            Student value = new Student(i, "Student" + i);
            table.put(key, value);
        }


        System.out.println();
        if (table.getChainArray() != null) {
            System.out.println("Number of elements in each bucket:");
            for (int i = 0; i < table.getM(); i++) {
                int count = 0;
                MyHashTable.HashNode<MyTestingClass, Student> current = table.getChainArray()[i];
                while (current != null) {
                    count++;
                    current = current.getNext();
                }
                System.out.println("Bucket " + i + ": " + count + " elements");
            }
        } else {
            System.out.println("chainArray is null");
        }
    }
}
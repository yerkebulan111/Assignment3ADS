public class MyHashTable<K, V> {
    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[M];
    }

    public MyHashTable(int size) {
        this();
        this.size = size;
    }

    private int hash(K key) {
        int result = 0;
        if (key instanceof Integer) {
            result = (int) key;
        }
        else if (key instanceof String) {
            int hash = 0;
            char[] chars = (char[]) key;
            int length = chars.length;
            for (int i = 0; i < length; i++) {
                hash += chars[i] + (31 * hash);
            }
            result = hash;
        }
        else if (key instanceof Integer[]) {
            int hash = 0;
            int length = ((Integer[]) key).length;
            for (int i = 0; i < length; i++) {
                hash += 31*hash + ((Integer[]) key)[i];
            }
            result = hash;
        }
        else if (key instanceof Double) {
            result = (int) key;
        }
        return result % M;
    }


    public void put(K key, V value) {
        int hash = hash(key);
        HashNode<K, V> node = new HashNode<>(key, value);
        if (chainArray[hash] == null) {
            chainArray[hash] = node;
            size++;
        }
        else {
            HashNode<K, V> current = chainArray[hash];
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
            size++;
        }
    }


    public V get(K key) {
        int hash = hash(key);
        HashNode<K, V> current = chainArray[hash];
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

// 1 2 3 4 5
    public V remove(K key) {
        V value = null;
        int hash = hash(key);
        HashNode<K, V> current = chainArray[hash];
        while (current != null) {
            if (current.next.key == key) {
                value = current.next.value;
                current.next = current.next.next;
                size--;
            }
            current = current.next;
        }
        return value;
    }


    public boolean contains(V value) {
        K key = getKey(value);
        int hash = hash(key);
        HashNode<K, V> current = chainArray[hash];
        while (current != null) {
            if (current.value == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public K getKey(V value) {
        K key = null;
        HashNode<K, V> current;
        for (int i = 0; i < M; i++) {
            current = chainArray[i];
            while (current != null) {
                if (current.value == value) {
                    key = current.key;
                }
                current = current.next;
            }
        }
        return key;
    }






    private class HashNode<K, V>{
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{ key: " + key + ", value: " + value + " }";
        }
    }



}

package oy.tol.tra;

public class KeyValueHashTable<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private Pair<K, V>[] values = null;
    private int count = 0;
    private int collisionCount = 0;
    private int maxProbingSteps = 0;
    private int reallocationCount = 0;
    private static final double LOAD_FACTOR = 0.45;
    private static final int DEFAULT_CAPACITY = 20;

    public KeyValueHashTable(int capacity) throws OutOfMemoryError {
        ensureCapacity(capacity);
    }

    public KeyValueHashTable() throws OutOfMemoryError {
        ensureCapacity(DEFAULT_CAPACITY);
    }

    @Override
    public Type getType() {
        return Type.HASHTABLE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        values = (Pair<K, V>[]) new Pair[(int) ((double) capacity * (1.0 + LOAD_FACTOR)];
        reallocationCount = 0;
        count = 0;
        collisionCount = 0;
        maxProbingSteps = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String getStatus() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Hash table load factor is %.2f%n", LOAD_FACTOR));
        builder.append(String.format("Hash table capacity is %d%n", values.length));
        builder.append(String.format("Current fill rate is %.2f%%%n", (count / (double)values.length) * 100.0));
        builder.append(String.format("Hash table had %d collisions when filling the hash table.%n", collisionCount));
        builder.append(String.format("Hash table had to probe %d times in the worst case.%n", maxProbingSteps));
        builder.append(String.format("Hash table had to reallocate %d times.%n", reallocationCount));
        return builder.toString();
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null || value == null) {
            throw new IllegalArgumentException("The key and value cannot be null");
        }
        
        if (((double)count * (1.0 + LOAD_FACTOR)) >= values.length) {
            reallocate((int)((double)(values.length) * (1.0 / LOAD_FACTOR)));
        }
        
        int index = getHashIndex(key);
        
        for (int i = 0; ; i++) {
            int collisionIndex = (index + i*i*i) % values.length;
            if (values[collisionIndex] == null) {
                values[collisionIndex] = new Pair<K, V>(key, value);
                count++;
                return true;
            } else if (values[collisionIndex].getKey().equals(key)) {
                values[collisionIndex].setValue(value);
                return true;
            }
            collisionCount++;
            if (i > maxProbingSteps) {
                maxProbingSteps = i;
            }
        }
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null");
        }
        
        int index = getHashIndex(key);
        
        for (int i = 0; ; i++) {
            int collisionIndex = (index + i*i*i) % values.length;
            if (values[collisionIndex] == null) {
                return null;
            } else if (values[collisionIndex].getKey().equals(key)) {
                return values[collisionIndex].getValue();
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pair<K,V>[] toSortedArray() {
        Pair<K, V>[] sorted = (Pair<K, V>[]) new Pair[count];
        int newIndex = 0;
        for (Pair<K, V> value : values) {
            if (value != null) {
                sorted[newIndex++] = new Pair<>(value.getKey(), value.getValue());
            }
        }
        Algorithms.fastSort(sorted);
        return sorted;
    }

    private int getHashIndex(K key) {
        int hash = key.hashCode();
        int index = hash % values.length;
        if (index < 0) {
            index += values.length;
        }
        return index;
    }

    private void reallocate(int newSize) throws OutOfMemoryError {
        if (newSize < DEFAULT_CAPACITY) {
            newSize = DEFAULT_CAPACITY;
        }
        reallocationCount++;
        Pair<K, V>[] oldPairs = values;
        this.values = (Pair<K, V>[]) new Pair[(int)((double)newSize * (1.0 + LOAD_FACTOR))];
        count = 0;
        collisionCount = 0;
        maxProbingSteps = 0;
        for (Pair<K, V> pair : oldPairs) {
            if (pair != null) {
                add(pair.getKey(), pair.getValue());
            }
        }
    }

    @Override
    public void compress() throws OutOfMemoryError {
        int newCapacity = (int)(count * (1.0 / LOAD_FACTOR));
        if (newCapacity < values.length) {
            reallocate(newCapacity);
        }
    }
}
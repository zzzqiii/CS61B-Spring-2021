package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!

    private static final int INIT_SIZE = 16;
    private static final double MAX_LOAD = 0.75;
    private int n, m;
    private double maxLoad;

    /** Constructors */
    public MyHashMap() {
        this(INIT_SIZE, MAX_LOAD);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, MAX_LOAD);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        m = initialSize;
        buckets = createTable(initialSize);
        this.maxLoad = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {

        Collection<Node>[] collection = new Collection[tableSize];
        for (int i = 0; i < tableSize; i ++) {
            collection[i] = createBucket();
        }
        return collection;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        for (int i = 0; i < m; i ++) {
            buckets[i] = createBucket();
        }
        n = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        int i = hash(key);
        Node markedNode = getNode(key);
        if (markedNode == null) return null;
        return markedNode.value;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void put(K key, V value) {
        if ((double) n / m > maxLoad) {
            resize(m * 2);
        }
        Node node = createNode(key, value);
        remove(key);
        int i = hash(key);
        buckets[i].add(node);
        n += 1;
    }

    private int hash(K key) {
        return Math.floorMod(key.hashCode(), m);
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (Node node : buckets[i]) {
                keys.add(node.key);
            }
        }
        return keys;
    }

    @Override
    public V remove(K key) {
        int i = hash(key);
        Node markedNode = getNode(key);
        if (markedNode == null) return null;
        buckets[i].remove(markedNode);
        n -= 1;
        return markedNode.value;
    }

    private Node getNode(K key) {
        Node ret = null;
        int i = hash(key);
        for (Node node : buckets[i]) {
            if (node.key.equals(key)) {
                ret = node;
                break;
            }
        }
        return ret;
    }

    @Override
    public V remove(K key, V value) {
        int i = hash(key);
        Node markedNode = getNode(key);
        if (markedNode == null || markedNode.value != value) return null;
        buckets[i].remove(markedNode);
        n -= 1;
        return markedNode.value;
    }

    @Override
    public Iterator<K> iterator() {
        LinkedList<K> keys = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (Node node : buckets[i]) {
                keys.add(node.key);
            }
        }
        return (Iterator<K>) keys;
    }



    private void resize(int chains) {
        MyHashMap<K, V> newHashTable = new MyHashMap<>(chains);
        Set<K> keys = keySet();
        for (K key : keys) {
            newHashTable.put(key, get(key));
        }
        m = chains;
        buckets = newHashTable.buckets;
    }

}

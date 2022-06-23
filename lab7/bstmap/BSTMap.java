package bstmap;
import bstmap.Map61B;
import edu.princeton.cs.algs4.BST;

import java.security.Key;
import java.util.*;

import static org.junit.Assert.assertTrue;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {

    private class BSTNode {
        BSTNode left;
        BSTNode right;
        K key;
        V val;
        int size;

        public BSTNode(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    private BSTNode root;

    public BSTMap() {
    }


    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(root, key) != null;
    }



    @Override
    public V get(K key) {
        BSTNode ret = get(root, key);
        if (ret == null) {
            return null;
        }
        return ret.val;
    }

    private BSTNode get(BSTNode x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private BSTNode put(BSTNode x, K key, V val) {
        if (x == null) {
            return new BSTNode(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.size = 1  + size(x.left) + size(x.right);
        return x;
    }

    public void printInOrder() {
        printInOrder(root);
    }

    public void printInOrder(BSTNode x) {
        if (x == null) {
            return;
        } else {
            printInOrder(x.left);
            System.out.print(x.key + " ");
            printInOrder(x.right);
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> ret = new HashSet<>();
        keySet(ret, root);
        return ret;
    }

    private void keySet(Set<K> set, BSTNode x) {
        if (x != null) {
            set.add(x.key);
            keySet(set, x.left);
            keySet(set, x.right);
        }
    }

    @Override
    public V remove(K key) {
        V ret = get(key);
        root = remove(root, key);
        return ret;
    }

    private BSTNode remove(BSTNode x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = remove(x.left, key);
        } else if (cmp > 0) {
            x.right = remove(x.right, key);
        } else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            BSTNode t = x;
            x = min(t.right);
            x.right = removeMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    private BSTNode min(BSTNode x) {
        if (x.left == null) return x;
        else    return min(x.left);
    }

    private BSTNode removeMin(BSTNode x) {
        if (x.left == null) return x.right;
        x.left = removeMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public V remove(K key, V value) {
        V ret = get(key);
        if (ret == value) {
            remove(key);
        }
        return ret;

    }

    private class BSTMapIterator implements Iterator<K> {
        Stack<BSTNode> stack;
        BSTMapIterator() {
            stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public K next() {
            if (!hasNext()) throw new NoSuchElementException();
            BSTNode ret = stack.pop();
            if (ret.right != null)   stack.push(ret.right);
            if (ret.left != null)   stack.push(ret.left);
            return ret.key;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi", null);
        b.printInOrder();
        System.out.println(b.containsKey("hi"));
    }
}
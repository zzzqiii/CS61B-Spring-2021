package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Deque<T> {
    private class StuffNode {
        public StuffNode prev;
        public T item;
        public StuffNode next;

        public StuffNode(StuffNode p, T t, StuffNode n) {
            prev = p;
            item = t;
            next = n;
        }
        public T getRecursiveHelper(int index) {
            if (index == 0) return this.item;
            return this.next.getRecursiveHelper(index - 1);
        }
    }

    private StuffNode sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new StuffNode(null, null, null);

    }

    public class lldIterator implements Iterator<T> {
        private int wizPos;
        private StuffNode p;
        public lldIterator() {
            wizPos = 0;
            p = sentinel;
        }
        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T ret = sentinel.next.item;
            return ret;
        }
    }

    public void addHead(T t) {
        sentinel.next = new StuffNode(null, t, null);
        sentinel.prev = sentinel.next;
        sentinel.next.prev = sentinel;
        sentinel.next.next = sentinel;
    }

    public void addFirst(T t) {
        if (size == 0) {
            addHead(t);
        }
        else {
            StuffNode tmp = sentinel.next;
            sentinel.next = new StuffNode(sentinel, t, sentinel.next);
            tmp.prev = sentinel.next;
        }
        size += 1;
    }

    public void addLast(T t) {
        if (size == 0) {
            addHead(t);
        }
        else {
            StuffNode tmp = sentinel.prev;
            sentinel.prev = new StuffNode(sentinel.prev, t, sentinel);
            tmp.next = sentinel.prev;
        }
        size += 1;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T ret = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size --;
        return ret;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T ret = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size --;
        return ret;
    }

    public int size() {
        return size;
    }



    public boolean isEmpty() {
        return size == 0;
    }
    public void printDeque() {
        StuffNode p = sentinel.next;
        int s = this.size();
        for (int i = 0; i < s; i ++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T get(int index) {
        if (index > size - 1 || index < 0)   return null;
        StuffNode p = sentinel;
        for (int i = 0; i <= index; i ++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1 || index < 0)   return null;
        return sentinel.next.getRecursiveHelper(index);
    }

    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque)) return false;
        LinkedListDeque o1 = (LinkedListDeque) o;
        if (this.size() != o1.size()) return false;
        int s = this.size();
        if (s == 0) return true;
        StuffNode p1 = this.sentinel.next;
        StuffNode p2 = o1.sentinel.next;
        for (int i = 0; i < s; i ++) {
            if (p1.item.equals(p2.item))    return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    public Iterator<T> iterator() {
        return new lldIterator();
    }



    public static void main(String [] args) {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        lld1.addLast("1");
        lld1.printDeque();

        lld1.addLast("2");
        lld1.printDeque();

        lld1.addLast("3");
        lld1.printDeque();

        lld1.addFirst("4");
        lld1.printDeque();

        System.out.println(lld1.get(3));
        System.out.println(lld1.get(4));
        System.out.println(lld1.get(2));

        System.out.println(lld1.getRecursive(3));
        System.out.println(lld1.getRecursive(4));
        System.out.println(lld1.getRecursive(2));

        lld1.removeFirst();
        lld1.printDeque();

        lld1.removeLast();
        lld1.printDeque();

        System.out.println(lld1.get(1));
        Iterator<String> i = lld1.iterator();
        System.out.println(i.hasNext());
        System.out.println(i.next());
    }
}

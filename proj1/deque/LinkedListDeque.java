package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class StuffNode {
        private StuffNode prev;
        private T item;
        private StuffNode next;

        StuffNode(StuffNode p, T t, StuffNode n) {
            prev = p;
            item = t;
            next = n;
        }
        public T getRecursiveHelper(int index) {
            if (index == 0) {
                return this.item;
            }
            return this.next.getRecursiveHelper(index - 1);
        }
    }

    private StuffNode sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new StuffNode(null, null, null);

    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private StuffNode p;
        private int wizPos;
        LinkedListDequeIterator() {
            p = sentinel;
            wizPos = 0;
        }
        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T ret = p.next.item;
            p = p.next;
            wizPos += 1;
            return ret;
        }
    }

    private void addHead(T t) {
        sentinel.next = new StuffNode(null, t, null);
        sentinel.prev = sentinel.next;
        sentinel.next.prev = sentinel;
        sentinel.next.next = sentinel;
    }

    public void addFirst(T t) {
        if (size == 0) {
            addHead(t);
        } else {
            StuffNode tmp = sentinel.next;
            sentinel.next = new StuffNode(sentinel, t, sentinel.next);
            tmp.prev = sentinel.next;
        }
        size += 1;
    }

    public void addLast(T t) {
        if (size == 0) {
            addHead(t);
        } else {
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
        size--;
        return ret;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T ret = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return ret;
    }

    public int size() {
        return size;
    }


    public void printDeque() {
        StuffNode p = sentinel.next;
        int s = this.size();
        for (int i = 0; i < s; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        StuffNode p = sentinel;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        return sentinel.next.getRecursiveHelper(index);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque o1 = (Deque) o;
        if (this.size() != o1.size()) {
            return false;
        }
        int s = this.size();
        StuffNode p1 = this.sentinel.next;
        for (int i = 0; i < s; i++) {
            if (!p1.item.equals(o1.get(i))) {
                return false;
            }
            p1 = p1.next;
        }
        return true;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }
}

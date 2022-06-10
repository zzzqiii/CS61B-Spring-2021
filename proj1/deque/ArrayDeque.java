package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private int head; //首端的第一个有效元素
    private int tail; //尾插的下一个位置
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        ArrayDequeIterator() {
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
            T ret = get(wizPos);
            wizPos += 1;
            return ret;
        }
    }

    public int size() {
        return size;
    }


    public void printDeque() {
        int i = head;
        int mask = items.length - 1;
        while (items[i] != null) {
            System.out.print(items[i] + " ");
            i = (i + 1) & mask;
        }
        System.out.println();
    }

    public T get(int index) {
        int mask = items.length - 1;
        if (index >= size || index < 0) {
            return null;
        }
        return items[(head + index) & mask];
    }

    public void addFirst(T t) {
        size += 1;
        head = (head - 1) & (items.length - 1);
        items[head] = t;
        if (head == tail) {
            //doubleResize();
            resize(items.length * 2);
        }
    }

    public void addLast(T t) {
        size += 1;
        items[tail] = t;
        tail = (tail + 1) & (items.length - 1);
        if (head == tail) {
            //doubleResize();
            resize(items.length * 2);
        }
    }

    public T removeFirst() {
        T ret = items[head];
        if (ret == null) {
            return null;
        }
        items[head] = null;
        head = (head + 1) & (items.length - 1);
        size -= 1;
        if ((float) size / items.length < 0.25 && size > 8) {
            //halfResize();
            resize(items.length / 2);
        }
        return ret;
    }

    public T removeLast() {
        int t = (tail - 1) & (items.length - 1);
        T ret = items[t];
        if (ret == null) {
            return null;
        }
        items[t] = null;
        tail = t;
        size -= 1;
        if ((float) size / items.length < 0.25 && size > 8) {
            //halfResize();
            resize(items.length / 2);
        }
        return ret;
    }

    private void resize(int capacity) {
        Object[] o = new Object[capacity];
        for (int i = 0; i < size; i++) {
            o[i] = get(i);
        }
        items = (T[]) o;
        head = 0;
        tail = size;
    }

    private void halfResize() {
        Object[] o = new Object[items.length / 2];
        int p = head;
        for (int i = 0; i < size; i++) {
            o[i] = get(i);
        }
        items = (T[]) o;
        head = 0;
        tail = size;
    }

    private void doubleResize() {
        Object[] o = new Object[items.length * 2];
        int n = items.length;
        int headLength = items.length - head; //尾插的长度
        int tailLength = head; //头插的长度
        System.arraycopy(items, head, o, 0, headLength);
        System.arraycopy(items, 0, o, headLength, tailLength);
        items = (T[]) o;
        head = 0;
        tail = n;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque o1 = (Deque) o;
        if (this.size() != o1.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!(this.get(i).equals(o1.get(i)))) {
                return false;
            }
        }
        return true;
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
}

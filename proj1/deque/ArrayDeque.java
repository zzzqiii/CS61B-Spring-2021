package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int head;//首端的第一个有效元素
    private int tail;//尾插的下一个位置
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
    }

    public class adIterator implements Iterator<T> {
        private int wizPos;

        public adIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T ret = get(wizPos);
            wizPos += 1;
            return ret;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
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
        if (index >= size || index < 0) return null;
        return items[(head + index) & mask];
    }

    public void addFirst(T t) {
        size += 1;
        head = (head - 1) & (items.length - 1);
        items[head] = t;
        if (head == tail)
            doubleResize();
    }

    public void addLast(T t) {
        items[tail] = t;
        tail = (tail + 1) & (items.length - 1);
        if (head == tail)
            doubleResize();
        size += 1;
    }

    public T removeFirst() {
        T ret = items[head];
        if (ret == null)    return null;
        items[head] = null;
        head = (head + 1) & (items.length - 1);
        size -= 1;
        if ((float) size / items.length < 0.25)
            halfResize();
        return ret;
    }

    public T removeLast() {
        int t = (tail - 1) & (items.length - 1);
        T ret = items[t];
        if (ret == null)    return null;
        items[t] = null;
        tail = t;
        size -= 1;
        if ((float) size / items.length < 0.25 && size > 8)
            halfResize();
        return ret;
    }

    private void halfResize() {
        Object[] o = new Object[items.length / 2];
        int length = tail - head;
        System.arraycopy(items, head, o, 0, length);
        items = (T[]) o;
        head = 0;
        tail = length;
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
        if (!(o instanceof ArrayDeque))    return false;
        ArrayDeque o1 = (ArrayDeque) o;
        if (this.size() != o1.size())   return false;
        for (int i = 0; i < this.size(); i ++) {
            if (!(this.get(i).equals(o1.get(i))))
                return false;
        }
        return true;
    }

    public Iterator<T> iterator() {
        return new adIterator();
    }

    public static void main(String[] args) {
        ArrayDeque<String> ad = new ArrayDeque<>();
        ad.addFirst("1");
        ad.addLast("2");
        for (Integer i = 3; i <= 16; i ++) {
            ad.addFirst(i.toString());
            ad.printDeque();
        }
        for (int i = 0; i < 16; i ++) {
            System.out.println(ad.get(i));
        }
        Iterator<String> adi = ad.iterator();
        System.out.println("Iterator: " + adi.next());
        for (int i = 0; i < 16; i ++) {
            ad.removeFirst();
            ad.printDeque();
        }
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i <= 6; i ++)
            ad1.addFirst(i);
        for (int i = 0; i <= 6; i ++)
            ad1.removeLast();


    }

}

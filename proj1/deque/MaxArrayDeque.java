package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;
    private Object[] items;
    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
        items = (T[]) new Object[8];
    }
    public T max() {
        if (isEmpty()) {
            return null;
        }
        T max = get(0);
        int size = size();
        for (int i = 1; i < size; i++) {
            if (comparator.compare(max, get(i)) < 0) {
                max = get(i);
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T max = get(0);
        int size = size();
        for (int i = 1; i < size; i++) {
            if (c.compare(max, get(i)) < 0) {
                max = get(i);
            }
        }
        return max;
    }
}

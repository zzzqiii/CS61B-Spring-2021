package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    public Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
        int size = 0;
        items = (T[]) new Object[8];
    }
    public T max() {
        if (isEmpty()) {
            return null;
        }
        T max = get(0);
        for (int i = 1; i < size; i ++) {
            if (comparator.compare(max, get(i)) == -1) {
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
        for (int i = 1; i < size; i ++) {
            if (c.compare(max, get(i)) == -1) {
                max = get(i);
            }
        }
        return max;
    }
}

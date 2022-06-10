package deque;

import org.junit.Test;

import java.util.Comparator;

public class MaxArrayDequeTest {

    public class Dog {
        int size;
        public Dog(int s) {
            size = s;
        }
    }

    public class SizeComparator<T> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            Dog d1 = (Dog) o1;
            Dog d2 = (Dog) o1;
            if (d1.size < d2.size) {
                return -1;
            } else if (d1 == d2) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    @Test
    public void CompareDogSize() {
        Dog d1 = new Dog(1);
        Dog d2 = new Dog(2);
        Dog d3 = new Dog(3);
        Comparator<Dog> s = new SizeComparator<>();
        MaxArrayDeque<Dog> d = new MaxArrayDeque<>(s);
        d.addFirst(d1);
        d.addFirst(d2);
        d.addFirst(d3);
        Dog maxDog = d.max(s);
        System.out.println(maxDog.size);

    }
}

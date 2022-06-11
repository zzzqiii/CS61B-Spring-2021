package tester;
import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;
public class TestArrayDequeEC {

    @Test
    public void TestAddThenRemove() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        for (int i = 0; i < 1000; i ++) {
            Integer numberBetweenZeroAndHundred = (int) Math.round(StdRandom.uniform() * 100);
            if (numberBetweenZeroAndHundred < 50) {
                sad1.addFirst(numberBetweenZeroAndHundred);
                ads1.addFirst(numberBetweenZeroAndHundred);
                assertEquals("addFirst(" + numberBetweenZeroAndHundred + ")\nremoveFirst()", ads1.removeFirst(), sad1.removeFirst());
                //ads1.printDeque();
            } else {
                sad1.addLast(numberBetweenZeroAndHundred);
                ads1.addLast(numberBetweenZeroAndHundred);
                assertEquals("addLast(" + numberBetweenZeroAndHundred + ")\nremoveLast()", ads1.removeLast(), sad1.removeLast());
            }
            assertEquals("size()", 0, sad1.size());

            for (int j = 0; j < sad1.size(); j++) {
                assertEquals("get(" + j + ")", sad1.get(j), ads1.get(j));
            }
        }
    }

    @Test
    public void TestRemove() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        for (int i = 0; i < 1000; i ++) {
            Integer numberBetweenZeroAndHundred = (int) Math.round(StdRandom.uniform() * 100);
            if (numberBetweenZeroAndHundred < 50) {
                sad1.addFirst(numberBetweenZeroAndHundred);
                ads1.addFirst(numberBetweenZeroAndHundred);
                //ads1.printDeque();
            } else {
                sad1.addLast(numberBetweenZeroAndHundred);
                ads1.addLast(numberBetweenZeroAndHundred);
            }
        }
        for (int i = 0; i < 1000; i ++) {
            Integer numberBetweenZeroAndHundred = (int) Math.round(StdRandom.uniform() * 100);
            if (numberBetweenZeroAndHundred < 50) {
                assertEquals("removeFirst()", ads1.removeFirst(), sad1.removeFirst());
                //ads1.printDeque();
            } else {
                assertEquals("removeLast()", ads1.removeLast(), sad1.removeLast());
            }
        }
    }

    @Test
    public void TestRemoveFirst() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        for (int i = 0; i < 1000; i ++) {
            Integer numberBetweenZeroAndHundred = (int) Math.round(StdRandom.uniform() * 100);
            if (numberBetweenZeroAndHundred < 50) {
                sad1.addFirst(numberBetweenZeroAndHundred);
                ads1.addFirst(numberBetweenZeroAndHundred);
                //ads1.printDeque();
            } else {
                sad1.addLast(numberBetweenZeroAndHundred);
                ads1.addLast(numberBetweenZeroAndHundred);
            }
        }
        for (int i = 0; i < 1000; i ++) {
            assertEquals("removeFirst()", ads1.removeFirst(), sad1.removeFirst());
        }
    }

    @Test
    public void TestRemoveLast() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        for (int i = 0; i < 1000; i ++) {
            Integer numberBetweenZeroAndHundred = (int) Math.round(StdRandom.uniform() * 100);
            if (numberBetweenZeroAndHundred < 50) {
                sad1.addFirst(numberBetweenZeroAndHundred);
                ads1.addFirst(numberBetweenZeroAndHundred);
                //ads1.printDeque();
            } else {
                sad1.addLast(numberBetweenZeroAndHundred);
                ads1.addLast(numberBetweenZeroAndHundred);
            }
        }
        for (int i = 0; i < 1000; i ++) {
            assertEquals("removeLast()", ads1.removeLast(), sad1.removeLast());
        }
    }
}

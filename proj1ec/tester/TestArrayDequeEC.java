package tester;
import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import java.util.ArrayList;
import java.util.List;

public class TestArrayDequeEC {

    @Test
    public void TestAddThenRemove() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        for (int i = 0; i < 10; i ++) {
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
        List<String> message = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            Integer numberBetweenZeroAndHundred = (int) Math.round(StdRandom.uniform() * 100);
            if (numberBetweenZeroAndHundred < 50) {
                sad1.addFirst(numberBetweenZeroAndHundred);
                ads1.addFirst(numberBetweenZeroAndHundred);
                message.add("addFirst(" + numberBetweenZeroAndHundred + ")");
                //ads1.printDeque();
            } else {
                sad1.addLast(numberBetweenZeroAndHundred);
                ads1.addLast(numberBetweenZeroAndHundred);
                message.add("addLast(" + numberBetweenZeroAndHundred + ")");
            }
        }
        for (int i = 0; i < 10; i ++) {
            Integer numberBetweenZeroAndHundred = (int) Math.round(StdRandom.uniform() * 100);
            if (numberBetweenZeroAndHundred < 50) {
                message.add("removeFirst()");
                assertEquals(String.join("\n", message), ads1.removeFirst(), sad1.removeFirst());
                //ads1.printDeque();
            } else {
                message.add("removeLast()");
                assertEquals(String.join("\n", message), ads1.removeLast(), sad1.removeLast());
            }
        }
    }

    @Test
    public void TestRemoveFirst() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        List<String> message = new ArrayList<>();
        for (int i = 0; i < 32; i ++) {
            Integer numberBetweenZeroAndHundred = (int) Math.round(StdRandom.uniform() * 100);
            if (numberBetweenZeroAndHundred < 50) {
                sad1.addFirst(numberBetweenZeroAndHundred);
                ads1.addFirst(numberBetweenZeroAndHundred);
                message.add("addFirst(" + numberBetweenZeroAndHundred + ")");
            } else {
                sad1.addLast(numberBetweenZeroAndHundred);
                ads1.addLast(numberBetweenZeroAndHundred);
                message.add("addLast(" + numberBetweenZeroAndHundred + ")");
            }
        }
        for (int i = 0; i < 32; i ++) {
            message.add("removeFirst()");
            assertEquals(String.join("\n", message), ads1.removeFirst(), sad1.removeFirst());
        }
    }

    @Test
    public void TestRemoveLast() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        List<String> message = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            Integer numberBetweenZeroAndHundred = (int) Math.round(StdRandom.uniform() * 100);
            if (numberBetweenZeroAndHundred < 50) {
                sad1.addFirst(numberBetweenZeroAndHundred);
                ads1.addFirst(numberBetweenZeroAndHundred);
                message.add("addFirst(" + numberBetweenZeroAndHundred + ")");
            } else {
                sad1.addLast(numberBetweenZeroAndHundred);
                ads1.addLast(numberBetweenZeroAndHundred);
                message.add("addLast(" + numberBetweenZeroAndHundred + ")");
            }
        }
        for (int i = 0; i < 10; i ++) {
            message.add("removeLast()");
            assertEquals(String.join("\n", message), ads1.removeLast(), sad1.removeLast());
        }
    }
}

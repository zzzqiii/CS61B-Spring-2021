package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
  @Test
  public void testThreeAddThreeRemove() {

    AListNoResizing correct = new AListNoResizing<Integer>();
    BuggyAList buggy = new BuggyAList<Integer>();

    correct.addLast(4);
    correct.addLast(5);
    correct.addLast(6);

    buggy.addLast(4);
    buggy.addLast(5);
    buggy.addLast(6);

    assertEquals(correct.size(), buggy.size());

    assertEquals(correct.removeLast(), buggy.removeLast());
    assertEquals(correct.removeLast(), buggy.removeLast());
    assertEquals(correct.removeLast(), buggy.removeLast());
  }

  @Test
  public void randomizedTest() {
    AListNoResizing correct = new AListNoResizing<Integer>();
    BuggyAList broken = new BuggyAList<Integer>();

    int N = 5000;
    for (int i = 0; i < N; i += 1) {
      int operationNumber = StdRandom.uniform(0, 4);
      if (operationNumber == 0) {
        // addLast
        int randVal = StdRandom.uniform(0, 100);
        correct.addLast(randVal);
        broken.addLast(randVal);
        //System.out.println("addLast(" + randVal + ")");
        //assertEquals(correct, broken);
      } else if (operationNumber == 1) {
        // size
        int sizeCorrect = correct.size();
        int sizeBroken = broken.size();
        assertEquals(sizeCorrect, sizeBroken);
      } else if (operationNumber == 2) {
        //getLast
        if (correct.size() > 0 && broken.size() > 0)
          assertEquals(correct.getLast(), broken.getLast());
      } else if (operationNumber == 3) {
        //removeLast
        if (correct.size() > 0 && broken.size() > 0)
          assertEquals(correct.removeLast(), broken.removeLast());
      }
    }
  }
}

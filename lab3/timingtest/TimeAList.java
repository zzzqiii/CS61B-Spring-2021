package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }
    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList Ns = new AList<Integer>();
        AList times = new AList<Double>();
        AList opCounts = new AList<Integer>();

        for (int i = 0; i < 8; i += 1) {
            AList a = new AList<Integer>();
            int N = (int)Math.pow(2, i) * 1000;
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < N; j += 1) {
                a.addLast(1);
            }
            double timeInSeconds = sw.elapsedTime();
            Ns.addLast(N);
            times.addLast(timeInSeconds);
            opCounts.addLast(N);
        }
        /**for (int i = 0; i < 8; i += 1) {
            AList a = new AList<Integer>();
            int N = (int)Math.pow(2, i) * 1000;
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < N; j += 1) {
                a.fasterAddLast(1);
            }
            double timeInSeconds = sw.elapsedTime();
            Ns.addLast(N);
            times.addLast(timeInSeconds);
            opCounts.addLast(N);
        }*/
        System.out.println("Timing table for addLast");
        printTimingTable(Ns, times, opCounts);
    }
}

package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    //public static final double CONCERT_A = 440.;
    public static void main(String[] args) {
        //GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString[] concert = new GuitarString[37];
        /* create two guitar strings, for concert A and C */
        for (int i = 0; i < 37; i++) {
            concert[i] = new GuitarString(440 * Math.pow(2, (i - 24.0) / 12.0));
        }
        int index = -2;
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                index = KEYBOARD.indexOf(key);
                if (index != -1) {
                    concert[index].pluck();
                }
            }
            if (index >= 0) {
                double sample = concert[index].sample();
                StdAudio.play(sample);
                concert[index].tic();
            }
        }
    }
}

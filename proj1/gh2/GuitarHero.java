package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static final double CONCERT_A = 440.;
    public static void main(String[] args) {
        GuitarString stringA = new GuitarString(CONCERT_A);
        //GuitarString stringC = new GuitarString(CONCERT_C);
        GuitarString[] ConCert = new GuitarString[37];
        /* create two guitar strings, for concert A and C */
        for (int i = 0; i < 37; i ++) {
            ConCert[i] = new GuitarString(440 * Math.pow(2, (i - 24.0) / 12.0));
        }
        int index = -2;
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                index = keyboard.indexOf(key);
                if (index != -1) {
                    ConCert[index].pluck();
                }
                if (key == '2') {
                    ConCert[1].pluck();
                }
            }
            if (index >= 0) {
                double sample = ConCert[index].sample();
                StdAudio.play(sample);
                ConCert[index].tic();
            }
//            double sample = ConCert[1].sample();
//            StdAudio.play(sample);
//            ConCert[1].tic();
            /* compute the superposition of samples */
            //double sample = stringA.sample() + stringC.sample();
//            for (int i = 0; i < 37; i++) {
//                double sample = ConCert[i].sample();
//                StdAudio.play(sample);
//                ConCert[i].tic();
//            }
        }
    }
}

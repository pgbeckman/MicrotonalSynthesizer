import java.util.*;

public class AudioPlayer{
    public static double[] generateTone(double freq, double duration) { 
        int N = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[N+1];
        for (int i = 0; i <= N; i++) {
            a[i] = Math.sin(2 * Math.PI * i * freq / StdAudio.SAMPLE_RATE);
        }
        return a;
    } 

    public static void play(double freq, double duration) {
        double[] a = generateTone(freq, duration);

        StdAudio.play(a);
    }

    public static void playMultiple(ArrayList<Double> allFrequencies, double duration) {
        int N = (int) (StdAudio.SAMPLE_RATE * duration);

        double[] toneSum = new double[N+1];
        double[] tone = new double[N+1];

        for(int i = 0; i < allFrequencies.size(); i++){
            tone = generateTone(allFrequencies.get(i), duration);
            for(int j = 0; j < N+1; j++){
                    toneSum[j] += tone[j];
            }
        }
        for(int i = 0; i < N+1; i++){
            toneSum[i] /= allFrequencies.size();
        }
        StdAudio.play(toneSum);
    }
}

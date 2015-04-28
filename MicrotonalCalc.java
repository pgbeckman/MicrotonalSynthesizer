import java.util.*;

public abstract class MicrotonalCalc{
    public static String frequenciesToString(ArrayList<Double> frequencies){
        String str = "";
        for(int i = 0; i < frequencies.size(); i++ ){
            str += "\n" + Math.round(frequencies.get(i)*100.0)/100.0 + " = " + freqToPitch(frequencies.get(i));
        }
        
        return str;
    }

    public static String freqToPitch(double freq){
        String[] p = {"A","A#","B","C","C#","D","D#","E","F","F#","G","G#"};
        double semitonesFromA = 12*Math.log(freq/440)/Math.log(2);
        semitonesFromA = (Math.round(semitonesFromA*100.0))/100.0;
        
        int pIndex = 0;
        if((semitonesFromA % 12) >= 0){
            pIndex = (int)(semitonesFromA % 12);
        }
        else{
            pIndex = (int)(semitonesFromA % 12) + 12;
        }
        
        if(semitonesFromA % 1 <= .5 && semitonesFromA % 1 > 0 && semitonesFromA % 1 > .01){
            return p[pIndex % 12] + " + " + Math.round((semitonesFromA % 1)*100) + " cents";
        }
        else if(semitonesFromA % 1 > .5 && semitonesFromA % 1 > .01){
            return p[(pIndex+1) % 12] + " - " + Math.round((1 - (semitonesFromA % 1))*100) + " cents";
        }
        else if(semitonesFromA % 1 > -.5 && semitonesFromA % 1 < -.01){
            return p[pIndex % 12] + " - " + Math.abs(Math.round((semitonesFromA % 1)*100)) + " cents";
        }
        else if(semitonesFromA % 1 < -.01){
            return p[(pIndex-1) % 12] + " + " + Math.round((1 + (semitonesFromA % 1))*100) + " cents";
        }
        else{
            return p[pIndex % 12];
        }
    }
    
    public static double pitchToFreq(String pitch){
        List<String> p = Arrays.asList("A","A#","B","C","C#","D","D#","E","F","F#","G","G#");
        return Math.round((440*Math.pow(2,(p.indexOf(pitch)/12.0)))*100.0)/100.0;
    }
    
    public static ArrayList<Double> vectorToFrequencies(double base, ArrayList<Double> vector){
        double output = 0;
        ArrayList<Double> frequencies = new ArrayList<Double>();
        
        frequencies.add(base);
        
        for(double vectorValue : vector){
            
            if(vectorValue > 0){
                output = base*Math.pow(2,(vectorValue/12));
            }
            else{
                output = base/Math.pow(2,(-vectorValue/12));
            }

            frequencies.add(output);

            base = output;
        }
        
        return frequencies;
    }
    
    public static ArrayList<Double> scalarMultiplication(double k, ArrayList<Double> vector){
        for(int i = 0; i < vector.size(); i++){
            vector.set(i,vector.get(i)*k);
        }
        return vector;
    }
}

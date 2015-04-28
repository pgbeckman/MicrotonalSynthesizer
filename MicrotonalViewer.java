import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MicrotonalViewer implements ActionListener{

    private final int WIDTH = 530;
    private final int HEIGHT = 370;

    private double n = 1;
    private double NOTE_DURATION = .8;

    private JButton playAll, play1, play2, play3, play4, 
    rref, rrefNoStasis,
    calculate,
    up, down,
    clear;
    private JFrame frame;
    private JTextField base1, base2, base3, base4,
    vector1Field, vector2Field, vector3Field, vector4Field,
    k1, k2, k3, k4,
    bypass1, bypass2, bypass3, bypass4, 
    duration,
    baseCalc, freqCalc;
    private JTextArea header, header2, header3, header4;

    public static void main(String[] args){
        MicrotonalViewer viewer = new MicrotonalViewer();
    }

    public MicrotonalViewer()
    {
        header = new JTextArea();
        header.setText("Base Freq      Interval Vector                                   Scalar k       Bypass");
        header.setBounds(10,20,520,20); 

        header2 = new JTextArea();
        header2.setText("Note Duration");
        header2.setBounds(10,250,100,20); 

        header3 = new JTextArea();
        header3.setText("Calculate Base Frequency");
        header3.setBounds(120,250,180,20);

        header4 = new JTextArea();
        header4.setText("Pitch             Frequency");
        header4.setBounds(120,280,180,20);

        playAll = new JButton("Play All");
        playAll.setBounds(10,210,430,30);
        play1 = new JButton("Play");
        play1.setBounds(450,50,70,30);
        play2 = new JButton("Play");
        play2.setBounds(450,90,70,30);
        play3 = new JButton("Play");
        play3.setBounds(450,130,70,30);
        play4 = new JButton("Play");
        play4.setBounds(450,170,70,30);

        rref = new JButton("RREF");
        rref.setBounds(450,210,70,30);
        rrefNoStasis = new JButton("No Stasis RREF");
        rrefNoStasis.setBounds(400,245,120,30);

        base1 = new JTextField();
        base1.setBounds(10,50,70,30);
        base2 = new JTextField();
        base2.setBounds(10,90,70,30);
        base3 = new JTextField();
        base3.setBounds(10,130,70,30);
        base4 = new JTextField();
        base4.setBounds(10,170,70,30);

        vector1Field = new JTextField();
        vector1Field.setBounds(90,50,220,30);
        vector2Field = new JTextField();
        vector2Field.setBounds(90,90,220,30);
        vector3Field = new JTextField();
        vector3Field.setBounds(90,130,220,30);
        vector4Field = new JTextField();
        vector4Field.setBounds(90,170,220,30);

        k1 = new JTextField();
        k1.setBounds(320,50,70,30);
        k2 = new JTextField();
        k2.setBounds(320,90,70,30);
        k3 = new JTextField();
        k3.setBounds(320,130,70,30);
        k4 = new JTextField();
        k4.setBounds(320,170,70,30);

        bypass1 = new JTextField();
        bypass1.setBounds(400,50,40,30);
        bypass2 = new JTextField();
        bypass2.setBounds(400,90,40,30);
        bypass3 = new JTextField();
        bypass3.setBounds(400,130,40,30);
        bypass4 = new JTextField();
        bypass4.setBounds(400,170,40,30);

        duration = new JTextField();
        duration.setBounds(10,280,100,30);

        clear = new JButton("Clear Vectors");
        clear.setBounds(5,310,110,30);

        baseCalc = new JTextField();
        baseCalc.setBounds(120,310,70,30);
        freqCalc = new JTextField();
        freqCalc.setBounds(200,310,100,30);
        calculate = new JButton("Calculate");
        calculate.setBounds(310,280,150,30);
        up = new JButton("Up");
        up.setBounds(310,310,75,30);
        down = new JButton("Down");
        down.setBounds(385,310,75,30);

        frame = new JFrame();
        frame.setTitle("Microtonal Synthesizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(null);

        frame.add(base1);
        frame.add(base2);
        frame.add(base3);
        frame.add(base4);
        frame.add(vector1Field);
        frame.add(vector2Field);
        frame.add(vector3Field);
        frame.add(vector4Field);
        frame.add(k1);
        frame.add(k2);
        frame.add(k3);
        frame.add(k4);
        frame.add(bypass1);
        frame.add(bypass2);
        frame.add(bypass3);
        frame.add(bypass4);
        frame.add(playAll);
        frame.add(play1);
        frame.add(play2);
        frame.add(play3);
        frame.add(play4);
        frame.add(baseCalc);
        frame.add(freqCalc);
        frame.add(calculate);
        frame.add(rref);
        frame.add(rrefNoStasis);
        frame.add(header);
        frame.add(header2);
        frame.add(header3);
        frame.add(header4);
        frame.add(duration);
        frame.add(clear);
        frame.add(up);
        frame.add(down);

        playAll.addActionListener(this);
        play1.addActionListener(this);
        play2.addActionListener(this);
        play3.addActionListener(this);
        play4.addActionListener(this);
        rref.addActionListener(this);
        rrefNoStasis.addActionListener(this);
        calculate.addActionListener(this);
        up.addActionListener(this);
        down.addActionListener(this);
        clear.addActionListener(this);

        frame.setVisible(true);
    }

    public void actionPerformed (ActionEvent event)
    {
        if(!duration.getText().equals("")){
            NOTE_DURATION = Double.parseDouble(duration.getText());
        }
        AudioPlayer player = new AudioPlayer();

        ArrayList<ArrayList<Double>> allVectors = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> allFrequencies = new ArrayList<ArrayList<Double>>();
        ArrayList<Double> vector1 = new ArrayList<Double>();
        ArrayList<Double> vector2 = new ArrayList<Double>();
        ArrayList<Double> vector3 = new ArrayList<Double>();
        ArrayList<Double> vector4 = new ArrayList<Double>();

        double baseValue = 440;
        double kValue = 1;

        ArrayList<Double> frequencies = new ArrayList<Double>();

        int filledCount = 0;

        if(!vector1Field.getText().equals("")){
            filledCount++;
        }
        if(!vector2Field.getText().equals("")){
            filledCount++;
        }
        if(!vector3Field.getText().equals("")){
            filledCount++;
        }
        if(!vector4Field.getText().equals("")){
            filledCount++;
        }

        if(!vector1Field.getText().equals("") 
        && bypass1.getText().equals("") 
        && (event.getSource() == playAll || event.getSource() == play1 
            || event.getSource() == rref || event.getSource() == rrefNoStasis)){
            String[] vectorValues = vector1Field.getText().split("[,]");
            for(String val : vectorValues){
                vector1.add(Double.parseDouble(val));
            }
            if(!base1.getText().equals("")){
                baseValue = Double.parseDouble(base1.getText());
            }
            if(!k1.getText().equals("")){
                kValue = Double.parseDouble(k1.getText());
            }
            MicrotonalCalc.scalarMultiplication(kValue, vector1);
            allVectors.add(vector1);

            frequencies = MicrotonalCalc.vectorToFrequencies(baseValue, vector1);
            allFrequencies.add(frequencies);
        }
        kValue = 1;
        if(!vector2Field.getText().equals("") 
        && bypass2.getText().equals("") 
        && (event.getSource() == playAll || event.getSource() == play2 
            || event.getSource() == rref || event.getSource() == rrefNoStasis)){
            String[] vectorValues = vector2Field.getText().split("[,]");
            for(String val : vectorValues){
                vector2.add(Double.parseDouble(val));
            }
            if(!base2.getText().equals("")){
                baseValue = Double.parseDouble(base2.getText());
            }
            if(!k2.getText().equals("")){
                kValue = Double.parseDouble(k2.getText());
            }
            MicrotonalCalc.scalarMultiplication(kValue, vector2);
            allVectors.add(vector2);

            frequencies = MicrotonalCalc.vectorToFrequencies(baseValue, vector2);
            allFrequencies.add(frequencies);
        }
        kValue = 1;
        if(!vector3Field.getText().equals("") 
        && bypass3.getText().equals("") 
        && (event.getSource() == playAll || event.getSource() == play3 
            || event.getSource() == rref || event.getSource() == rrefNoStasis)){
            String[] vectorValues = vector3Field.getText().split("[,]");
            for(String val : vectorValues){
                vector3.add(Double.parseDouble(val));
            }
            if(!base3.getText().equals("")){
                baseValue = Double.parseDouble(base3.getText());
            }
            if(!k3.getText().equals("")){
                kValue = Double.parseDouble(k3.getText());
            }
            MicrotonalCalc.scalarMultiplication(kValue, vector3);
            allVectors.add(vector3);

            frequencies = MicrotonalCalc.vectorToFrequencies(baseValue, vector3);
            allFrequencies.add(frequencies);
        }
        kValue = 1;
        if(!vector4Field.getText().equals("") 
        && bypass4.getText().equals("") 
        && (event.getSource() == playAll || event.getSource() == play4 
            || event.getSource() == rref || event.getSource() == rrefNoStasis)){
            String[] vectorValues = vector4Field.getText().split("[,]");
            for(String val : vectorValues){
                vector4.add(Double.parseDouble(val));
            }
            if(!base4.getText().equals("")){
                baseValue = Double.parseDouble(base4.getText());
            }
            if(!k4.getText().equals("")){
                kValue = Double.parseDouble(k4.getText());
            }
            MicrotonalCalc.scalarMultiplication(kValue, vector4);
            allVectors.add(vector4);

            frequencies = MicrotonalCalc.vectorToFrequencies(baseValue, vector4);
            allFrequencies.add(frequencies);
        }

        if(filledCount > 0 
        && (event.getSource() == play1 
            || event.getSource() == play2
            || event.getSource() == play3
            || event.getSource() == play4
            || event.getSource() == playAll)){
            for(int i = 0; i < allFrequencies.size(); i++){
                System.out.println(MicrotonalCalc.frequenciesToString(allFrequencies.get(i)) + "\n");
            }
            System.out.println("---\n");

            for(int i = 0; i < allFrequencies.get(0).size(); i++){
                ArrayList<Double> simultFreq = new ArrayList<Double>();
                for(int j = 0; j < allFrequencies.size(); j++){
                    simultFreq.add(allFrequencies.get(j).get(i));
                }
                player.playMultiple(simultFreq, NOTE_DURATION);
            }

            for(int i = 0; i < allVectors.size(); i++){
                for(int j = 0; j < allVectors.get(0).size(); j++){
                    System.out.print(Math.round(allVectors.get(i).get(j)*100.0)/100.0 + "\t");
                }
                System.out.println("");
            }
            System.out.println("\n");
        }

        if(event.getSource() == rref || event.getSource() == rrefNoStasis){
            double[][] input = new double[filledCount][vector1.size()];
            for(int i = 0; i < filledCount; i++){
                for(int j = 0; j < vector1.size(); j++){
                    input[i][j] = allVectors.get(i).get(j);
                }
            }

            if(event.getSource() == rref && filledCount > 0){
                input = MatrixOps.rref(input);
            }
            if(event.getSource() == rrefNoStasis  && filledCount > 0){
                input = MatrixOps.rrefNoStasis(input);
            }

            if(filledCount > 0){
                vector1Field.setText(vectorToString(input[0]));
                filledCount--;
            }
            if(filledCount > 0){
                vector2Field.setText(vectorToString(input[1]));
                filledCount--;
            }
            else{
                vector2Field.setText("");
            }
            if(filledCount > 0){
                vector3Field.setText(vectorToString(input[2]));
                filledCount--;
            }
            else{
                vector3Field.setText("");
            }
            if(filledCount > 0){
                vector4Field.setText(vectorToString(input[3]));
            }
            else{
                vector4Field.setText("");
            }
        }

        if(event.getSource() == up){
            n *= 2.0;
        }

        if(event.getSource() == down){
            n /= 2.0;
        }

        if((event.getSource() == calculate || event.getSource() == up || event.getSource() == down)
        && !baseCalc.getText().equals("")){
            freqCalc.setText(Double.toString(MicrotonalCalc.pitchToFreq(baseCalc.getText())*n));
        }

        if(event.getSource() == clear){
            vector1Field.setText("");
            vector2Field.setText("");
            vector3Field.setText("");
            vector4Field.setText("");
            base1.setText("");
            base2.setText("");
            base3.setText("");
            base4.setText("");
            k1.setText("");
            k2.setText("");
            k3.setText("");
            k4.setText("");
            bypass1.setText("");
            bypass2.setText("");
            bypass3.setText("");
            bypass4.setText("");
        }
    }

    public String vectorToString(ArrayList<Double> vector){
        String str = Double.toString(vector.get(0));
        for(int i = 1; i < vector.size(); i++){
            str += "," + vector.get(i);
        }
        return str;
    }

    public String vectorToString(double[] vector){
        String str = Double.toString(Math.round(vector[0]*100.0)/100.0);
        for(int i = 1; i < vector.length; i++){
            str += "," + Math.round(vector[i]*100.0)/100.0;
        }
        return str;
    }
}

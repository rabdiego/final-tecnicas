package Base;
import java.util.ArrayList;
import java.lang.Math;

public abstract class LinearModel {
    public ArrayList <Double> weights;
    public ArrayList <Double> logs;

    public ArrayList <Double> subtractArray(ArrayList <Double> a, ArrayList <Double> b) {
        try {
            if (a.size() != b.size()) {
                throw new Exception("Vetores de tamanhos diferentes");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList <Double> c = new ArrayList<>();

        for (int i = 0; i < a.size(); i++) {
            c.add(a.get(i) - b.get(i));
        }

        return c;
    }

    public double computeMSE(ArrayList <Double> yTest, ArrayList <Double> yPred) {
        try {
            if (yTest.size() != yPred.size()) {
                throw new Exception("Vetores de tamanhos diferentes, impossível calcular MSE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        double r = 0.0f;
        int size = yTest.size();

        for (int i = 0; i < size; i++) {
            r += Math.pow((yTest.get(i) - yPred.get(i)), 2);
        }

        return r/size;
    };

    public abstract ArrayList <Double> predict(ArrayList <Double> xTest);
    public abstract void fit(ArrayList <Double> xTrain, ArrayList <Double> yTrain, double learning_rate, int num_epochs);
}

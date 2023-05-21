package Models;
import Base.*;
import java.util.ArrayList;

public final class LinearRegression extends LinearModel {
    public LinearRegression() {
        this.weights = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            this.weights.add(0.0d);
        }
    }

    public final ArrayList <Double> predict(ArrayList <Double> xTest) {
        ArrayList <Double> prediction = new ArrayList<>();
        for (int i = 0; i < xTest.size(); i++) {
            prediction.add(this.weights.get(0) + this.weights.get(1) * xTest.get(i));
        }
        return prediction;
    }

    public final void fit(ArrayList <Double> xTrain, ArrayList <Double> yTrain, double learning_rate, int num_epochs) {
        try {
            if (xTrain.size() != yTrain.size()) {
                throw new Exception("Vetores de tamanhos diferentes, impossível treinar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ArrayList <Double> prediction = new ArrayList<>();
        ArrayList <Double> error = new ArrayList<>();
        double errorX0, errorX1;
        for (int i = 0; i < num_epochs; i++) {
            errorX0 = 0.0d;
            errorX1 = 0.0d;
            prediction = this.predict(xTrain);
            error = this.subtractArray(yTrain, prediction);

            for (int j = 0; j < error.size(); j++) {
                errorX0 += error.get(j);
                errorX1 += error.get(j)*xTrain.get(j);
            }

            this.weights.set(0, this.weights.get(0) + (learning_rate * errorX0)/(xTrain.size()));
            this.weights.set(1, this.weights.get(1) + (learning_rate * errorX1)/(xTrain.size()));
        }
    }

    public final ArrayList <Double> getWeights() {
        return this.weights;
    }
}

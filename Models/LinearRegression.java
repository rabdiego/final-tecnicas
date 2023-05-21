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

    public final ArrayList <Double> sumDifferential(ArrayList <Double> xTrain, ArrayList <Double> error) {
        ArrayList <Double> errorDifferential = new ArrayList<>();
        errorDifferential.add(0.0d);
        errorDifferential.add(0.0d);

        for (int i = 0; i < error.size(); i++) {
            errorDifferential.set(0, errorDifferential.get(0) + error.get(i));
            errorDifferential.set(1, errorDifferential.get(1) + error.get(i)*xTrain.get(i));
        }

        return errorDifferential;
    }

    public final ArrayList <Double> predict(ArrayList <Double> xTest) {
        ArrayList <Double> prediction = new ArrayList<>();

        for (int i = 0; i < xTest.size(); i++) {
            prediction.add(this.weights.get(0) + this.weights.get(1) * xTest.get(i));
        }

        return prediction;
    }
}

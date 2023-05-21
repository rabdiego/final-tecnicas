import Models.*;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        LinearRegression model = new LinearRegression();
        ArrayList<Double> data = new ArrayList<>();
        ArrayList<Double> real = new ArrayList<>();
        data.add(1.0d);
        data.add(2.0d);
        data.add(3.0d);
        real.add(1.0d);
        real.add(2.0d);
        real.add(1.6d);
        model.fit(data, real, 0.1, 10);

        for (int i = 0; i < 10; i++) {
            System.out.println(model.getLogs().get(i));
        }
    }
}

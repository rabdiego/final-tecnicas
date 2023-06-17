import java.util.ArrayList;
import java.util.Random;

public class Simulator implements ObservableSimulator {

    private ArrayList <ArrayList <Double>> points;
    private int number_of_dimensions;
    private int number_of_points;
    private double mean;
    private ArrayList <Double> means;
    
    private double euclidianDistance(ArrayList <Double> p_a, ArrayList <Double> p_b) {
        double distance = 0.0d;
        
        for (int i = 0; i < this.number_of_dimensions; i++) {
            distance += Math.pow((p_a.get(i) - p_b.get(i)), 2);
        }
        
        return Math.sqrt(distance);
    }

    private void setNewSimulator(int number_of_dimensions, int number_of_points, char distribution) {
        this.number_of_dimensions = number_of_dimensions;
        this.number_of_points = number_of_points;
        this.points = new ArrayList<>();
        Random generator = new Random();
        if (distribution == 'u') {
            for (int i = 0; i < number_of_points; i++) {
                ArrayList <Double> temp = new ArrayList<>();
                for (int j = 0; j < number_of_dimensions; j++) {
                    temp.add(generator.nextDouble());
                }
                this.points.add(temp);
            }
        }
        if (distribution == 'n') {
            for (int i = 0; i < number_of_points; i++) {
                ArrayList <Double> temp = new ArrayList<>();
                for (int j = 0; j < number_of_dimensions; j++) {
                    temp.add(generator.nextGaussian());
                }
                this.points.add(temp);
            }
        }
    }

    private double simulate() {
        double r = 0.0d;
        int denominator = (this.number_of_points * (this.number_of_points - 1))/2;
        ArrayList <Double> p_a = new ArrayList<>();
        ArrayList <Double> p_b = new ArrayList<>();
        for (int i = 0; i < this.number_of_points; i++) {
            p_a = this.points.get(i);
            for (int j = i + 1; j < this.number_of_points; j++) {
                p_b = this.points.get(j);
                r += this.euclidianDistance(p_a, p_b);
            }
        }
        return r/denominator;
    }
    
    public void simulate(int starting_dimension, int ending_dimension, int step, int number_of_points, char distribution) {
        Simulator simulator = new Simulator();

        for (int i = starting_dimension; i <= ending_dimension; i += step) {
            simulator.setNewSimulator(i, number_of_points, distribution);
            this.means.add(simulator.simulate());
        }
    }

    public ArrayList <Double> getValue() {
        return this.means;
    }
}
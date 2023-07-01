import java.util.ArrayList;

public interface ObservableSimulator {
    public void simulate(int starting_dimension, int ending_dimension, int step, int number_of_points, char distribution);
    public ArrayList <ArrayList <Double>> getValue();
    public void addObserver(SimulatorObserver observer);
    public void removeObserver(SimulatorObserver observer);
}
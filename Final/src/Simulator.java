import java.util.ArrayList;
import java.util.Random;

public class Simulator implements ObservableSimulator {
    /** Classe que implementa o simulador (modelo)
     * 
     * @author Diego
     */

    private ArrayList <ArrayList <Double>> points;
    private int number_of_dimensions;
    private int number_of_points;
    private ArrayList <ArrayList <Double>> metrics;
    private ArrayList<SimulatorObserver> observers;
    private static Simulator instance;
    private int starting_dimension;
    private int step;


    public Simulator(){
        /** Método inicializador
         * 
         * @author Diego
         * 
         */
        observers = new ArrayList<>();
    }
    
    public static Simulator getInstance() {
        /** Método para checar se a instância é nula
         * 
         * @author Diego
         * 
         */
        if (instance == null) {
            instance = new Simulator();
        }
        return instance;
    }
    
    private double euclidianDistance(ArrayList <Double> p_a, ArrayList <Double> p_b) {
        /** Método privado para calcular a distância euclidiana
         * 
         * @param p_a ArrayList <Double> - Primeiro vetor
         * @param p_b ArrayList <Double> - Segundo vetor
         * 
         * @author Diego
         * @return distance Double - Distância calculada
         */
        double distance = 0.0d;
        
        // Calcula a distância entre dois vetores
        for (int i = 0; i < this.number_of_dimensions; i++) {
            distance += Math.pow((p_a.get(i) - p_b.get(i)), 2);
        }
        
        return Math.sqrt(distance);
    }

    private void setNewSimulator(int number_of_dimensions, int number_of_points, char distribution) {
        /** Métodoo privado para inicializar um novo simulador, dado as entradas especificadas
         * 
         * @param number_of_dimensions Integer - Número de dimensões
         * @param number_of_points Integer - Número de pontos gerados
         * @param distribution Character - Distribuição (normal ou uniforme) utilizada para gerar os pontos
         * 
         * @author Diego
         * 
         */

        this.number_of_dimensions = number_of_dimensions;
        this.number_of_points = number_of_points;
        this.points = new ArrayList<>();
        Random generator = new Random();

        // Geração dos pontos

        // Caso da distribuição uniforme
        if (distribution == 'u') {
            for (int i = 0; i < number_of_points; i++) {
                ArrayList <Double> temp = new ArrayList<>();
                for (int j = 0; j < number_of_dimensions; j++) {
                    temp.add(generator.nextDouble());
                }
                this.points.add(temp);
            }
        }
        
        // Caso da distribuição normal
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

    private ArrayList <Double> singleSimulate() {
        /** Método privado para simular apenas em uma dimensão
         * 
         * @author Diego
         * @return returnValues ArrayList <Double> - Métricas geradas
         */
        double r_mean = 0.0d, r_std = 0.0d;

        double mean, standard, variance, varCoef;

        int denominator = (this.number_of_points * (this.number_of_points - 1))/2;
        
        ArrayList <Double> p_a = new ArrayList<>();
        ArrayList <Double> p_b = new ArrayList<>();

        ArrayList <Double> returnValues = new ArrayList<>();

        // Cálculo das distâncias entre os pontos gerados
        for (int i = 0; i < this.number_of_points; i++) {
            p_a = this.points.get(i);
            for (int j = i + 1; j < this.number_of_points; j++) {
                p_b = this.points.get(j);
                r_mean += this.euclidianDistance(p_a, p_b);
            }
        }

        mean = r_mean/denominator;
        returnValues.add(mean);

        // Cálculo da variação entre os pontos gerados
        for (int i = 0; i < this.number_of_points; i++) {
            p_a = this.points.get(i);
            for (int j = i + 1; j < this.number_of_points; j++) {
                p_b = this.points.get(j);
                r_std += Math.pow((this.euclidianDistance(p_a, p_b) - mean), 2);
            }
        }

        variance = r_std/denominator;
        standard = Math.sqrt(variance);
        varCoef = standard/mean;

        returnValues.add(variance);
        returnValues.add(standard);
        returnValues.add(varCoef);
        notifyObservers();
        return returnValues;
    }
    
    public void simulate(int starting_dimension, int ending_dimension, int step, int number_of_points, char distribution) {
        /** Método para simular em diversas dimensões
         * 
         * @param starting_dimensions Integer - Dimensão que começa
         * @param ending_dimension Integer - Dimensão que termina
         * @param step Integer - Passo entre dimensões
         * @param number_of_points Integer - Número de pontos gerados
         * @param distribution Character - Distribuição (normal ou uniforme) usada para gerar os pontos
         * 
         * @author Diego
         * 
         */
        Simulator simulator = new Simulator();
        this.metrics = new ArrayList<>();

        this.starting_dimension = starting_dimension;
        this.step = step;

        for (int i = starting_dimension; i <= ending_dimension; i += step) {
            simulator.setNewSimulator(i, number_of_points, distribution);
            this.metrics.add(simulator.singleSimulate());
        }
    }

    public ArrayList <ArrayList <Double>> getValue() {
        /** Método para retornar as métricas geradas
         * 
         * @author Diego
         * @return metrics ArrayList <ArrayList <Double>> - Matriz de dados (cada coluna se refere à uma métrica)
         */
        return this.metrics;
    }

     public void addObserver(SimulatorObserver observer) {
        /** Método para adicionar o observer
         * 
         * @author Diego
         * 
         */
        observers.add(observer);
    }

     public void removeObserver(SimulatorObserver observer) {
        /** Método para remover o observer
         * 
         * @author Diego
         * 
         */
        observers.remove(observer);
    }

    public void notifyObservers() {
        /** Método para notificar os observers
         * 
         * @author Diego
         * 
         */
        for (SimulatorObserver observer : observers) {
            observer.update(this); 
        }
    }

    public ArrayList <Integer> getParams() {
        /** Método para retornar os parâmetros da simulação
         * 
         * @author Diego
         * @return return_values ArrayList <Integer> - Parâmetros retornados
         */
        ArrayList <Integer> return_values = new ArrayList<>();
        return_values.add(starting_dimension);
        return_values.add(step);
        return return_values;
    }
}
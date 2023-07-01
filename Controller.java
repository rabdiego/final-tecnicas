public class Controller {

    private int starting_dimension, ending_dimension, step, number_of_points;

    void setStartingDimension(int starting_dimension) {
        this.starting_dimension = starting_dimension;
    }

    int getStartingDimension() {
        return starting_dimension;
    }

    void setEndingDimension(int ending_dimension) {
        this.ending_dimension = ending_dimension;
    }

    int getEndingDimension() {
        return ending_dimension;
    }

    void setStep(int step) {
        this.step = step;
    }

    int getStep() {
        return step;
    }

    void setNumberOfPoints(int number_of_points) {
        this.number_of_points = number_of_points;
    }

    int getNumberOfPoints() {
        return number_of_points;
    }

    void processarValores(String[] valor) {
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                setStartingDimension(Integer.parseInt(valor[i]));
                if (getStartingDimension() > 0) {
                    AlgoErrado(1);
                }
            } else if (i == 1) {
                setEndingDimension(Integer.parseInt(valor[i]));
                if (getEndingDimension() < getStartingDimension()) {
                    AlgoErrado(2);
                }
            } else if (i == 2) {
                setStep(Integer.parseInt(valor[i]));
                if (getStep() >= 1) {
                    AlgoErrado(1);
                }
            } else {
                setNumberOfPoints(Integer.parseInt(valor[i]));
                if (getNumberOfPoints() > 0) {
                    AlgoErrado(1);
                }
            }
        }
    }

    void AlgoErrado(int TypeOfError) {
        if (TypeOfError == 1) {
            System.out.println("Valor inferior ao mínimo, tente novamente");
            System.exit(0);
        } else if (TypeOfError == 2) {
            System.out.println("Ending_dimension menor que o valor do Starting,tente novamente.");
            System.exit(0);
        }
    }

}

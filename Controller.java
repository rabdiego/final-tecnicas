public class Controller {

    private int starting_dimension, ending_dimension, step, number_of_points;
    private char distribution;
    private Simulator simulator = new Simulator();

    private void setStartingDimension(int starting_dimension) {
        this.starting_dimension = starting_dimension;
    }

    private int getStartingDimension() {
        return starting_dimension;
    }

    private void setEndingDimension(int ending_dimension) {
        this.ending_dimension = ending_dimension;
    }

    private int getEndingDimension() {
        return ending_dimension;
    }

    private void setStep(int step) {
        this.step = step;
    }

    private int getStep() {
        return step;
    }

    private void setNumberOfPoints(int number_of_points) {
        this.number_of_points = number_of_points;
    }

    private int getNumberOfPoints() {
        return number_of_points;
    }
    private void setDistribution(int distribution) {
        if (distribution == 0){
            this.distribution = 'u';
        } else {
            this.distribution = 'n';
        }
    }
    private char getDistribution() {
        return distribution;
    }

    public boolean processValuesInput(String[] valor) {
        setDistribution(Integer.parseInt(valor[4]));
        
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                setStartingDimension(Integer.parseInt(valor[i]));
                if (getStartingDimension() < 0)
                    return somethingWrong(1);
            } else if (i == 1) {
                setEndingDimension(Integer.parseInt(valor[i]));
                if (getEndingDimension() < getStartingDimension())
                    return somethingWrong(2);
            } else if (i == 2) {
                setStep(Integer.parseInt(valor[i]));
                if (getStep() < 1)
                    return somethingWrong(1);
            } else {
                setNumberOfPoints(Integer.parseInt(valor[i]));
                if (getNumberOfPoints() < 0)
                    return somethingWrong(1);
            }
        }

        // rodando o troço do Daiego
        simulator.simulate(getStartingDimension(), getEndingDimension(), getStep(), getNumberOfPoints(), getDistribution());
        return true;

    }

    private boolean somethingWrong(int TypeOfError) {
        if (TypeOfError == 1) {
            System.out.println("Valor inferior ao mínimo, tente novamente");

        } else if (TypeOfError == 2) {
            System.out.println("Ending_dimension menor que o valor do Starting,tente novamente.");

        }
        return false;
    }

}

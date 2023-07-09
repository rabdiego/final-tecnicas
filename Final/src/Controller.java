public class Controller implements ControllerInferface {
    /** Classe para implementação do controller
     * 
     * @author Gabriel
     * 
     */

    private int starting_dimension, ending_dimension, step, number_of_points;
    private char distribution;
    private Simulator simulator;
    private ChartBuilder chartBuilder;
    
    public void addSimulator(Simulator simulator){
        /** Método para adicionar o model
         * 
         * @param simulator Simulator - Model
         * 
         * @author Gabriel
         * 
         */
        this.simulator = simulator;
    }

    public void addChartBuilder(ChartBuilder chartBuilder){
        /** Método para adicionar o viewer
         * 
         * @param chartBuilder ChartBuilder - Viewer
         * 
         * @author Gabriel
         * 
         */
        this.chartBuilder = chartBuilder;
    }  

    public void setStartingDimension(int starting_dimension) {
        /** Método para adicionar a variável starting_dimension
         * 
         * @param starting_dimension Integer - Dimensão inicial da simulação
         * 
         * @author Gabriel
         * 
         */
        this.starting_dimension = starting_dimension;
    }

    public int getStartingDimension() {
        /** Método para retornar a variável starting_dimension
         * 
         * @author Gabriel
         * @return starting_dimension Integer - Dimensão inicial da simulação
         */
        return starting_dimension;
    }

    public void setEndingDimension(int ending_dimension) {
        /** Método para adicionar a variável ending_dimension
         * 
         * @param ending_dimension Integer - Dimensão final da simulação
         * 
         * @author Gabriel
         * 
         */
        this.ending_dimension = ending_dimension;
    }

    public int getEndingDimension() {
        /** Método para retornar a variável ending_dimension
         * 
         * @author Gabriel
         * @return ending_dimension Integer - Dimensão final da simulação
         */
        return ending_dimension;
    }

    public void setStep(int step) {
        /** Método para adicionar a variável step
         * 
         * @param step Integer - Passo
         * 
         * @author Gabriel
         * 
         */
        this.step = step;
    }

    public int getStep() {
        /** Método para retornar a variável step
         * 
         * @author Gabriel
         * @return step Integer - Passo
         */
        return step;
    }

    public void setNumberOfPoints(int number_of_points) {
        /** Método para adicionar a variável number_of_points
         * 
         * @param number_of_points Integer - Número de pontos da simulação
         * 
         * @author Gabriel
         * 
         */
        this.number_of_points = number_of_points;
    }

    public int getNumberOfPoints() {
        /** Método para retornar a variável number_of_points
         * 
         * @author Gabriel
         * @return number_of_points Integer - Número de pontos da simulação
         */
        return number_of_points;
    }

    public void setDistribution(int distribution) {
        /** Método para adicionar a variável distribution
         * 
         * @param distribution Character - Distribuição da simulação (Uniforme ou Normal)
         * 
         * @author Gabriel
         * 
         */
        if (distribution == 0) {
            this.distribution = 'u';
        } else {
            this.distribution = 'n';
        }
    }

    public char getDistribution() {
        /** Método para retornar a variável number_of_points
         * 
         * @author Gabriel
         * @return number_of_points Integer - Número de pontos da simulação
         */
        return distribution;
    }

    public boolean processValuesInput(String[] valor) {
        /** Método para processar os inputs brutos
         * 
         * @param valor String[] - Valores de inputs
         * 
         * @author Gabriel
         * @return check Boolean - Se o processamento foi feito corretamente
         * 
         */

        setDistribution(Integer.parseInt(valor[4]));

        // Checagem de cada variável
        // Checamos em cada se a conversão para o tipo especificado deu certo
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                try {
                    setStartingDimension(Integer.parseInt(valor[i]));
                } catch (Exception e) {
                    System.out.println("Valor inválido");
                    return false;
                }
                // Dimensão incial não pode ser negativa
                if (getStartingDimension() < 0)
                    return somethingWrong(1);
            } else if (i == 1) {
                try {
                    setEndingDimension(Integer.parseInt(valor[i]));
                } catch (Exception e) {
                    System.out.println("Valor inválido");
                    return false;
                }
                // Dimensão final tem que ser maior que a dimensão inicial
                if (getEndingDimension() < getStartingDimension())
                    return somethingWrong(2);
            } else if (i == 2) {
                try {
                    setStep(Integer.parseInt(valor[i]));
                } catch (Exception e) {
                    System.out.println("Valor inválido");
                    return false;
                }
                // Passo tem que ser maior que a diferença entre a dimensão final e inicial
                if ((getStep() < 1) || (getStep() > (getEndingDimension() - getStartingDimension())))
                    return somethingWrong(1);
            } else {
                try {
                    setNumberOfPoints(Integer.parseInt(valor[i]));
                } catch (Exception e) {
                    System.out.println("Valor inválido");
                    return false;
                }
                // Número de pontos não pode ser negativo
                if (getNumberOfPoints() < 0)
                    return somethingWrong(1);
            }
        }

        // Simulação
        simulator.simulate(getStartingDimension(), getEndingDimension(), getStep(), getNumberOfPoints(),
                getDistribution());

        // Update no model
        chartBuilder.update(simulator);
        
        return true;
    }

    public boolean somethingWrong(int typeOfError) {
        /** Método para caso algo dê errado
         * 
         * @param typeOfError Integer - Tipo do erro (1 ou 2)
         * 
         * @author Gabriel
         * @return Check Boolean - Se algo deu errado
         */

         // Valor inferior ao mínimo
        if (typeOfError == 1) {
            System.out.println("Valor inferior ao mínimo, tente novamente");

        // Dimensão final maior que a inicial
        } else if (typeOfError == 2) {
            System.out.println("ending_dimension menor que o valor da starting_dimension,tente novamente.");
        }

        return false;
    }
}
public interface ControllerInferface {
    public void setStartingDimension(int starting_dimension);

    public int getStartingDimension();

    public void setEndingDimension(int ending_dimension);

    public int getEndingDimension();

    public void setStep(int step);

    public int getStep();

    public void setNumberOfPoints(int number_of_points);

    public int getNumberOfPoints();

    public void setDistribution(int distribution);

    public char getDistribution();

    public boolean processValuesInput(String[] valor);

    public boolean somethingWrong(int TypeOfError);
}

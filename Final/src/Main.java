public class Main {
    public static void main(String[] args) {
            Gui view = new Gui();
            Controller controller = new Controller();
            Simulator model = new Simulator();
            ChartBuilder chart = new ChartBuilder();

            view.addController(controller);
            view.addChartBuilder(chart);
            view.addSimulator(model);

            view.show();
    }
}
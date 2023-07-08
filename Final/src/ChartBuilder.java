import org.knowm.xchart.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.GridLayout;

public class ChartBuilder implements SimulatorObserver {
    /** Classe para construir os gráficos
     * 
     * @author Lucas
     * 
     */

    private ArrayList<ArrayList<Double>> metrics;
    private int starting_dimension;
    private int step;

    @Override
    public void update(ObservableSimulator simulator) {
        /** Método para dar update nos valores
         * 
         * @author Lucas
         * 
         */
        metrics = simulator.getValue();
        ArrayList <Integer> params = new ArrayList<>();
        params = simulator.getParams();
        this.starting_dimension = params.get(0);
        this.step = params.get(1);
    }

    public void updateChartPanel(){
        /** Método para dar update no gráfico
         * 
         * @author Lucas
         * 
         */

        // Obtém os resultados da simulação
        ArrayList<ArrayList<Double>> data = metrics;
                
        //Variaveis para os dados
        ArrayList<Double> xAxisData = new ArrayList<>();
        ArrayList<Double> yAxisData1 = new ArrayList<>();
        ArrayList<Double> yAxisData2 = new ArrayList<>();
        ArrayList<Double> yAxisData3 = new ArrayList<>();
        ArrayList<Double> yAxisData4 = new ArrayList<>();

        // Adicionar os valores das metricas às variaveis
        double i = 0;
        for (ArrayList<Double> dados : data) {
            // Valor da dimensão para o gráfico 1 (eixo X)
            xAxisData.add(this.starting_dimension + i*this.step); 
            // Valor da métrica para o gráfico da média  (eixo Y)
            yAxisData1.add(dados.get(0));
            // Valor da métrica para o gráfico da variância (eixo Y)
            yAxisData2.add(dados.get(1));
            // Valor da métrica para o gráfico do desvio padrão (eixo Y)
            yAxisData3.add(dados.get(2));
            // Valor da métrica para o gráfico do coeficiente de variação(eixo Y)
            yAxisData4.add(dados.get(3));
            i++;
        }
    }

    public  JPanel createChartPanel() {
        /** Método para criar um novo painel gráfico
         * 
         * @author Lucas
         * @return chartPanel JPanel - Panel com os gráficos
         */

        // Obtém os resultados da simulação
        ArrayList<ArrayList<Double>> data = metrics;
                
        //Variaveis para os dados
        ArrayList<Double> xAxisData = new ArrayList<>();
        ArrayList<Double> yAxisData1 = new ArrayList<>();
        ArrayList<Double> yAxisData2 = new ArrayList<>();
        ArrayList<Double> yAxisData3 = new ArrayList<>();
        ArrayList<Double> yAxisData4 = new ArrayList<>();

        // Adicionar os valores das metricas às variaveis
        double i = 0;
        for (ArrayList<Double> dados : data) {
            // Valor da dimensão para o gráfico 1 (eixo X)
            xAxisData.add(this.starting_dimension + i*this.step); 
            // Valor da métrica para o gráfico da média  (eixo Y)
            yAxisData1.add(dados.get(0));
            // Valor da métrica para o gráfico da variância (eixo Y)
            yAxisData2.add(dados.get(1));
            // Valor da métrica para o gráfico do desvio padrão (eixo Y)
            yAxisData3.add(dados.get(2));
            // Valor da métrica para o gráfico do coeficiente de variação(eixo Y)
            yAxisData4.add(dados.get(3));
            i++;
        }

        // Criação dos gráficos
        XYChart chart1 = new XYChartBuilder().width(800).height(600).title("Média").xAxisTitle("Eixo X").yAxisTitle("Eixo Y").build();
        XYChart chart2 = new XYChartBuilder().width(800).height(600).title("Variância").xAxisTitle("Eixo X").yAxisTitle("Eixo Y").build();
        XYChart chart3 = new XYChartBuilder().width(800).height(600).title("Desvio Padrão").xAxisTitle("Eixo X").yAxisTitle("Eixo Y").build();
        XYChart chart4 = new XYChartBuilder().width(800).height(600).title("Coeficiente de Variação").xAxisTitle("Eixo X").yAxisTitle("Eixo Y").build();

        // Adição dos dados aos gráficos
        chart1.addSeries("Data", xAxisData, yAxisData1);
        chart2.addSeries("Data", xAxisData, yAxisData2);
        chart3.addSeries("Data", xAxisData, yAxisData3);
        chart4.addSeries("Data", xAxisData, yAxisData4);

        // Configuração do estilo dos gráficos
        chart1.getStyler().setLegendVisible(false);
        chart2.getStyler().setLegendVisible(false);
        chart3.getStyler().setLegendVisible(false);
        chart4.getStyler().setLegendVisible(false);

        // Criação do painel de gráficos 2x2
        JPanel chartPanel = new JPanel(new GridLayout(2, 2));
        XChartPanel<XYChart> chartPanel1 = new XChartPanel<>(chart1);
        chartPanel.add(chartPanel1);
        XChartPanel<XYChart> chartPanel2 = new XChartPanel<>(chart2);
        chartPanel.add(chartPanel2);
        XChartPanel<XYChart> chartPanel3 = new XChartPanel<>(chart3);
        chartPanel.add(chartPanel3);
        XChartPanel<XYChart> chartPanel4 = new XChartPanel<>(chart4);
        chartPanel.add(chartPanel4);

        return chartPanel;
    }
}

import org.knowm.xchart.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.GridLayout;


public class ChartBuilder implements SimulatorObserver{    
    public ArrayList<ArrayList<Double>> metrics;
    @Override
    public void update(ObservableSimulator simulator) {
        metrics = simulator.getValue();
        

    }

    public void updateChartPanel(){
        ArrayList<ArrayList<Double>> data = metrics;
                // Obtém os resultados da simulação

        //Variaveis para os dados
        ArrayList<Double> xAxisData = new ArrayList<>();
        ArrayList<Double> yAxisData1 = new ArrayList<>();
        ArrayList<Double> yAxisData2 = new ArrayList<>();
        ArrayList<Double> yAxisData3 = new ArrayList<>();
        ArrayList<Double> yAxisData4 = new ArrayList<>();

        // Adicionar os valores das metricas às variaveis
        double i = 0;
        for (ArrayList<Double> dados : data) {
            xAxisData.add(i); // Valor da dimensão para o gráfico 1 (eixo X)
            yAxisData1.add(dados.get(0)); // Valor da métrica para o gráfico da média  (eixo Y)
            yAxisData2.add(dados.get(1)); // Valor da métrica para o gráfico da variância (eixo Y)
            yAxisData3.add(dados.get(2)); // Valor da métrica para o gráfico do desvio padrão (eixo Y)
            yAxisData4.add(dados.get(3)); // Valor da métrica para o gráfico do coeficiente de variação(eixo Y)
            i++;
        }
    }

    public  JPanel createChartPanel() {

        
        ArrayList<ArrayList<Double>> data = metrics;
        // Obtém os resultados da simulação

        //Variaveis para os dados
        ArrayList<Double> xAxisData = new ArrayList<>();
        ArrayList<Double> yAxisData1 = new ArrayList<>();
        ArrayList<Double> yAxisData2 = new ArrayList<>();
        ArrayList<Double> yAxisData3 = new ArrayList<>();
        ArrayList<Double> yAxisData4 = new ArrayList<>();

        // Adicionar os valores das metricas às variaveis
        double i = 0;
        for (ArrayList<Double> dados : data) {
            xAxisData.add(i); // Valor da dimensão para o gráfico 1 (eixo X)
            yAxisData1.add(dados.get(0)); // Valor da métrica para o gráfico da média  (eixo Y)
            yAxisData2.add(dados.get(1)); // Valor da métrica para o gráfico da variância (eixo Y)
            yAxisData3.add(dados.get(2)); // Valor da métrica para o gráfico do desvio padrão (eixo Y)
            yAxisData4.add(dados.get(3)); // Valor da métrica para o gráfico do coeficiente de variação(eixo Y)
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

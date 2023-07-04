import java.awt.*;
import java.awt.event.*;
import org.knowm.xchart.*;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.Styler;
import javax.swing.*;
import java.util.ArrayList;


public class Gui {
    private static Frame f;

    private static Controller gabrigas = new Controller();
    

    public static void main(String[] args) {

        String[] values = new String[5];
        // Container Frame
        Frame f = new Frame("Doidera");
        f.setSize(1000, 600);
        f.setBackground(null);
        // f.setForeground(Color.red);
        f.setResizable(true);

        // Definindo layout manager
        f.setLayout(new BorderLayout());

        // Associar tratador de eventos com a janela
        f.addWindowListener(new Tratador());

        // >>Components Panels
        // Container Painel
        Panel pSouth = new Panel(); // layout manager dele é o flow layout
        pSouth.setBackground(Color.LIGHT_GRAY);
        pSouth.setLayout(new BorderLayout());

        // Panel que contem os inputs
        Panel pSouthW = new Panel();
        pSouthW.setLayout(new FlowLayout(0, 0, 10));
        pSouthW.setBackground(Color.LIGHT_GRAY);

        // Panel que contem distribuição e botão 'ok'
        Panel pSouthE = new Panel();
        pSouthE.setLayout(new FlowLayout(0, 5, 10));
        pSouthE.setBackground(Color.LIGHT_GRAY);

        Panel pNorth = new Panel();
        pNorth.setBackground(Color.LIGHT_GRAY);
        pNorth.setLayout(new FlowLayout(0, 2, 10));

        Panel pWest = new Panel();
        pWest.setBackground(Color.LIGHT_GRAY);
        pWest.setLayout(new FlowLayout(0, 70, 10));

        Panel pEast = new Panel();
        pEast.setBackground(Color.LIGHT_GRAY);

        // Labels>
        Label none01 = new Label("Distribuição:");
        // none01.setFont(new Font("MonoSpaced", Font.PLAIN, 14));

        Label algo = new Label("Simulador Bolado:");
        algo.setFont(new Font("MonoSpaced", Font.BOLD, 16));

        Label input01Label = new Label("Inicio:", Label.CENTER);
        input01Label.setBounds(100, 100, 160, 20);

        Label input02Label = new Label("Fim:", Label.CENTER);
        input02Label.setBounds(100, 100, 160, 20);

        Label input03Label = new Label("Passo:", Label.CENTER);
        input03Label.setBounds(100, 100, 160, 20);

        Label input04Label = new Label("Pontos:", Label.CENTER);
        input03Label.setBounds(100, 100, 160, 20);

        // Label input04Label = new Label("Input 04:", Label.CENTER);
        // input02Label.setBounds(100,100,160,20);

        // Text Field Input
        TextField input01 = new TextField(null, 3);
        TextField input02 = new TextField(null, 3);
        TextField input03 = new TextField(null, 3);
        TextField input04 = new TextField(null, 3);

        // Botões
        Button b1 = new Button("Uniforme");
        b1.setBounds(15, 150, 150, 50);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setFont(new Font("MonoSpaced", Font.BOLD, 16));

        Button b2 = new Button("Normal");
        b2.setBounds(150, 150, 150, 50);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setFont(new Font("MonoSpaced", Font.BOLD, 16));

        Button ok = new Button("OK");
        ok.setBounds(150, 150, 150, 150);
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ok.setFont(new Font("MonoSpaced", Font.BOLD, 16));

        // Lista
        List list = new List(2);
        list.add("Uniforme");
        list.add("Normal");
        // Choice
        Choice choice = new Choice();
        choice.add("Uniforme");
        choice.add("Normal");

        // Evento botao 'OK'
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // array recebe os inputs
                values[0] = input01.getText();
                values[1] = input02.getText();
                values[2] = input03.getText();
                values[3] = input04.getText();
                values[4] = Integer.toString(choice.getSelectedIndex());
                // values[4] = 1 normal; 0 uniforme
                // DEBUG
                System.out.println(values[0]); // inicio
                System.out.println(values[1]); // fim
                System.out.println(values[2]); // passo
                System.out.println(values[3]); // pontos
                System.out.println(values[4]); // Distribuição

                // todo Classe do Gabrigas
                if (gabrigas.processValuesInput(values)) {
                    System.out.println("deu bom");
                    // classe do Daiego
                }
                
            }
        });

        // TALVEZ
        // Canvas logo = new Canvas() {
        // public void paint(Graphics g) {
        // try {
        // Image image = ImageIO.read(new File("path/pra/foto")); // Coloque o caminho
        // correto para a imagem
        // g.drawImage(image, 0, 1, null);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        // };
        // pNorth.add(logo);
        // f.add("Center",logo);

        // adicionando os componentes ao container Panel
        pSouthW.add(input01Label);
        pSouthW.add(input01);
        pSouthW.add(input02Label);
        pSouthW.add(input02);
        pSouthW.add(input03Label);
        pSouthW.add(input03);
        pSouthW.add(input04Label);
        pSouthW.add(input04);
        pSouthE.add(none01);
        pSouthE.add(choice);
        pSouthE.add(ok);
        pSouth.add("West", pSouthW);
        pSouth.add("East", pSouthE);

        pNorth.add(algo);

        f.add("South", pSouth);
        f.add("North", pNorth);
        f.add("West", pWest);
        f.add("East", pEast);
        JPanel chartPanel = ChartBuilder.createChartPanel();
        f.add("Center",chartPanel);
        f.setVisible(true);

    }
}
//Criação dos Gráficos
class ChartBuilder{
    
    public static JPanel createChartPanel() {
        
        // Criação do simulador
        Simulator simulator = new Simulator();
        // Criação do observador
        SimulatorObserver observer1 = new SimulatorObserverImpl("Observer");
        // Adição dos observadores ao simulador
        simulator.addObserver(observer1);

        //realizar simulação   !!!!!! APENAS PARA TESTE, DEVE SER REMOVIDO!!!!!!!!!
        simulator.simulate(2, 5, 1, 100, 'u');

        // Obtém os resultados da simulação
        ArrayList<ArrayList<Double>> metrics = simulator.getValue();

        //Variaveis para os dados
        ArrayList<Double> xAxisData = new ArrayList<>();
        ArrayList<Double> yAxisData1 = new ArrayList<>();
        ArrayList<Double> yAxisData2 = new ArrayList<>();
        ArrayList<Double> yAxisData3 = new ArrayList<>();
        ArrayList<Double> yAxisData4 = new ArrayList<>();

        // Adicionar os valores das metricas às variaveis
        double i = 0;
        for (ArrayList<Double> metric : metrics) {
            xAxisData.add(i); // Valor da dimensão para o gráfico 1 (eixo X)
            yAxisData1.add(metric.get(0)); // Valor da métrica para o gráfico da média  (eixo Y)
            yAxisData2.add(metric.get(1)); // Valor da métrica para o gráfico da variância (eixo Y)
            yAxisData3.add(metric.get(2)); // Valor da métrica para o gráfico do desvio padrão (eixo Y)
            yAxisData4.add(metric.get(3)); // Valor da métrica para o gráfico do coeficiente de variação(eixo Y)
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

//Implementação do Observer
class SimulatorObserverImpl implements SimulatorObserver {
    private String name;

    public SimulatorObserverImpl(String name) {
        this.name = name;
    }

    @Override
    public void update(ObservableSimulator simulator) {
        ArrayList<ArrayList<Double>> metrics = simulator.getValue();
        System.out.println("Simulator metrics: " + metrics);
    }
}



class Tratador implements WindowListener {
    public void windowClosing(WindowEvent we) {
        System.exit(0);
    }

    public void windowActivated(WindowEvent we) {
    }

    public void windowClosed(WindowEvent we) {
    }

    public void windowDeactivated(WindowEvent we) {
    }

    public void windowDeiconified(WindowEvent we) {
    }

    public void windowIconified(WindowEvent we) {
    }

    public void windowOpened(WindowEvent we) {
    }
}

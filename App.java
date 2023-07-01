import java.awt.*;
import java.awt.event.*;
import org.knowm.xchart.*;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.Styler;

import javax.swing.*;

public class App {
    private static Frame f;
    private static XYChart chart;

    public static void main(String[] args){

        //Container Frame
        f = new Frame("Titulo");
        f.setSize(800,600);
        f.setBackground(null);
        //f.setForeground(Color.red);
        f.setResizable(true);

        //Definindo layout manager
        f.setLayout(new BorderLayout());

        //Associar tratador de eventos com a janela
        f.addWindowListener(new Tratador());

        //>>Components Panels
        //Container Painel
        Panel pSouth = new Panel(); //layout manager dele é o flow layout
        pSouth.setBackground(Color.LIGHT_GRAY);
        pSouth.setLayout(new FlowLayout(0,4,5));

        Panel pNorth = new Panel();
        pNorth.setBackground(Color.LIGHT_GRAY);
        pNorth.setLayout(new FlowLayout(0,2,10));

        Panel pWest = new Panel();
        pWest.setBackground(Color.LIGHT_GRAY);
        pWest.setLayout(new FlowLayout(0,70,10));

        Panel pEast = new Panel();
        pEast.setBackground(Color.LIGHT_GRAY);


        //Labels>
        Label none0 = new Label("                   ");
        Label none01 = new Label("      Distribuição:");
        none01.setFont(new Font("MonoSpaced", Font.PLAIN, 14));

        Label algo = new Label("Algo:");
        algo.setFont(new Font("MonoSpaced", Font.BOLD, 16));

        Label input01Label = new Label("Input 01:", Label.CENTER);
        input01Label.setBounds(100,100,160,20);

        Label input02Label = new Label("Input 02:", Label.CENTER);
        input02Label.setBounds(100,100,160,20);

        Label input03Label = new Label("Input 03:", Label.CENTER);
        input03Label.setBounds(100,100,160,20);

        //Label input04Label = new Label("Input 04:", Label.CENTER);
        //input02Label.setBounds(100,100,160,20);

        //Text Field Input
        TextField input01 = new TextField(null, 3);
        TextField input02 = new TextField(null, 3);
        TextField input03 = new TextField(null, 3);

        //Botões
        Button b1 = new Button("Uniforme");
        b1.setBounds(15,150,150,50);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setFont(new Font("MonoSpaced", Font.BOLD, 16));

        Button b2 = new Button("Normal");
        b2.setBounds(150,150,150,50);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setFont(new Font("MonoSpaced", Font.BOLD, 16));

        //Grafico
        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(400)
                .title("Line Chart")
                .xAxisTitle("X")
                .yAxisTitle("Y")
                .build();

        // Create the series and add data points
        Series series = chart.addSeries("Data", new double[] { 1, 2, 3, 4, 5 }, new double[] { 1, 4, 9, 16, 25 });

        // Customize the chart style
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        JPanel chartPainel = new XChartPanel<>(chart);
        /////////////////////////////////

        //adicionando os componentes ao container Panel

        pSouth.add(input01Label);
        pSouth.add(input01);
        pSouth.add(input02Label);
        pSouth.add(input02);
        pSouth.add(input03Label);
        pSouth.add(input03);
        pSouth.add(none0);
        pSouth.add(none01);
        //pSouth.add(input04Label);
        pSouth.add(b1);
        pSouth.add(b2);
        pNorth.add(algo);



        f.add("South", pSouth);
        f.add("North", pNorth);
//        f.add("West", pWest);
//        f.add("East", pEast);
        //f.add("North", l);
        //f.add(l);
        //f.add(b);
        //f.pack();
        f.add("Center",chartPainel);
        f.setVisible(true);

    }
    
}

class Tratador implements WindowListener{
    public void windowClosing (WindowEvent we)
    {
        System.exit(0);
    }
    public void windowActivated (WindowEvent we){}
    public void windowClosed (WindowEvent we){}
    public void windowDeactivated (WindowEvent we){}
    public void windowDeiconified(WindowEvent we){}
    public void windowIconified (WindowEvent we){}
    public void windowOpened (WindowEvent we){}
}
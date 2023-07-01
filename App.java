import java.awt.*;
import java.awt.event.*;
import org.knowm.xchart.*;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.Styler;

import javax.swing.*;

public class App {
    private static Frame f;
    private static XYChart chart;

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
                if (gabrigas.processarValoresInput(values)) {
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
        f.setVisible(true);

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

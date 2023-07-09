import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gui{
    /** Classe da interface gráfica
     * 
     * 
     * @author Ramon
     */

    // Controller, Model e Viewer
    private Controller controller;
    private Simulator simulator;
    private ChartBuilder chartBuilder;
    private JPanel chartpanel;

    // Métodos para adicionar os membros do MVC
    public void addController(Controller controller){
        /** Método para adicionar o controller à classe
         * 
         * @param controller Controller - Controller
         * 
         * @author Tobias
         */
        this.controller = controller;
    }

    public void addSimulator(Simulator simulator){
        /** Método para adicionar o model à classe
         * 
         * @param simulator Simulator - Model
         * 
         * @author Tobias
         */
        this.simulator = simulator;
    }   

    public void addChartBuilder(ChartBuilder chartBuilder){
        /** Método para adicionar o viewer à classe
         * 
         * @param chartBuilder ChartBuilder - Viewer
         * 
         * @author Tobias
         */
        this.chartBuilder = chartBuilder;
    }   


    // Método principal
    public void show(){
        /** Método para mostrar a interface gráfica do sistema
         * 
         * @author Ramon
         */

        // Linkando o controller com o viewer
        controller.addChartBuilder(this.chartBuilder);
        controller.addSimulator(this.simulator);

        // Variável que vai receber os inputs brutos
        String[] values = new String[5];
        
        // Frame principal
        Frame f = new Frame("Simulador");
        f.setSize(1000, 600);
        f.setBackground(null);
        f.setResizable(true);

        // Definindo layout manager
        f.setLayout(new BorderLayout());

        // Associar tratador de eventos com a janela
        f.addWindowListener(new Tratador());

        // Painéis
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

        // Panel que contém o título do sistema
        Panel pNorth = new Panel();
        pNorth.setBackground(Color.LIGHT_GRAY);
        pNorth.setLayout(new FlowLayout(0, 2, 10));

        // Panels da Imagem
        Panel pWest = new Panel();
        pWest.setBackground(Color.LIGHT_GRAY);
        pWest.setLayout(new FlowLayout(0, 70, 10));
        Panel pEast = new Panel();
        pEast.setLayout(new FlowLayout(0, 70, 100));
        pEast.setBackground(Color.LIGHT_GRAY);

        // Labels
        Label none01 = new Label("Distribuição:");

        Label algo = new Label("Simulador da Maldição da Redução da Dimensionalidade");
        algo.setFont(new Font("MonoSpaced", Font.BOLD, 16));

        Label input01Label = new Label("Inicio:", Label.CENTER);
        input01Label.setBounds(100, 100, 160, 20);

        Label input02Label = new Label("Fim:", Label.CENTER);
        input02Label.setBounds(100, 100, 160, 20);

        Label input03Label = new Label("Passo:", Label.CENTER);
        input03Label.setBounds(100, 100, 160, 20);

        Label input04Label = new Label("Pontos:", Label.CENTER);
        input04Label.setBounds(100, 100, 160, 20);
        
        Label input05Label = new Label("", Label.CENTER);
        input05Label.setBounds(100, 100, 160, 20);

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
        
        pSouthW.add(input01Label);
        pSouthW.add(input01);
        pSouthW.add(input02Label);
        pSouthW.add(input02);
        pSouthW.add(input03Label);
        pSouthW.add(input03);
        pSouthW.add(input04Label);
        pSouthW.add(input04);
        pSouthW.add(input05Label);
        pSouthE.add(none01);
        pSouthE.add(choice);
        pSouthE.add(ok);
        pSouth.add("West", pSouthW);
        pSouth.add("East", pSouthE);
        pNorth.add(algo);

        // Imagem da UFC
        JLabel image = new JLabel(new ImageIcon("./src/images/ufc_logo_pequeno-4.png"));
        pEast.add("Center", image);
        pEast.revalidate();
        pEast.repaint();

        f.add("South", pSouth);
        f.add("North", pNorth);
        f.add("West", pWest);
        f.add("West", pEast);

        // Checando se o objeto é nulo (quando o usuário inicializa o sistema)
        if(chartpanel != null){
            f.add("Center", chartpanel);
        }
        
        

        f.setVisible(true);
        
        // Evento botao 'OK'
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // array recebe os inputs
                values[0] = input01.getText();
                values[1] = input02.getText();
                values[2] = input03.getText();
                values[3] = input04.getText();
                values[4] = Integer.toString(choice.getSelectedIndex());

                if (controller.processValuesInput(values)) {
                    // Checando se o objeto não é nulo (atualizar)
                    if(chartpanel != null){
                        f.remove(chartpanel);
                    }

                    // Cria os gráficos e atualiza a tela
                    chartpanel = chartBuilder.createChartPanel();
                    chartBuilder.update(simulator);
                    f.remove(pSouth);
                    f.remove(pWest);
                    f.remove(pEast);

                    // Mensagem de erro (inicialmente vazia)
                    input05Label.setText("");

                    pSouthW.add(input01Label);
                    pSouthW.add(input01);
                    pSouthW.add(input02Label);
                    pSouthW.add(input02);
                    pSouthW.add(input03Label);
                    pSouthW.add(input03);
                    pSouthW.add(input04Label);
                    pSouthW.add(input04);
                    pSouthW.add(input05Label);
                    pSouthE.add(none01);
                    pSouthE.add(choice);
                    pSouthE.add(ok);
                    pSouth.add("West", pSouthW);
                    pSouth.add("East", pSouthE);
                    pNorth.add(algo);

                    f.add("South", pSouth);
                    f.add("North", pNorth);
                    f.add("West", pWest);
                    f.add("West", pEast);

                    if(chartpanel != null){
                        f.add("Center", chartpanel);
                    }
                    
                    f.setVisible(true);

                }
                else{
                    // Ocorreu um erro
                    input05Label.setText("ERRO, TENTE NOVAMENTE");
                    input05Label.setForeground(Color.red);
                }
                
            }
        });
    }
}

class Tratador implements WindowListener {
    /** Classe para tratar os eventos
     * 
     * @author Tobias
     * 
     */
    public void windowClosing(WindowEvent we) {
        /** Método para ver se a janela fechou
         * 
         * @author Tobias
         */
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
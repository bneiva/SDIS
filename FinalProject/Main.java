package Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main{

    /* Os nós são criados num vetor Node[]; é possível definir o número máximo de pontos (N), a largura e altura máximas da janela/sala/grafico (roomL, roomW) e o número 
     de colunas diferentes (nCol) para criação dos pontos; Estes pontos podem ser posteriormente definidos a partir do String[] args;
    
    Criação dos pontos:
    A largura da janela é dividida em nCol e são definidos os limites máximos de cada coluna por exemplo:
    Largura da janela (roomL = 500);
    Numero de colunas (nCol = 10;
    Limites (maxLim):
                |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |  10  |
                |     |     |     |     |     |     |     |     |     |      |      
                |     |     |     |     |     |     |     |     |     |      |      
                0    50    100   150   200   250   300   350   400   450   500
    É gerado um número aleatório de pontos por coluna de forma a fazer os N pontos.
    É gerado o nó iniciador (0,0);
    São gerados os outros nós.
    
    Para gerar nós usa-se classe Node.java: 
    Node n = new Node(maxX, minX, maxY, minY, nextIP)
    As coordenadas geradas podem ser obtidas a partir de:
    x-> n.x_coord;  (int)
    y-> n.y_coord;  (int)
    ip -> n.ip;     (string)
    
    Falta restrição de distância minima entre nós, mas isso vai dar bastante trabalho para verificar o que está nas vizinhanças portanto era bom testar primeiro sem essa restrição
    de forma a saber se funciona para o que queremos ou não. Se não funcionar eu faço isso.
    
    A fazer: 
        é necessário verificar os nós que estão na vizinhança para um dado raio e fazer join ao grupo desses nós; méthodo verifyNeig na classe Node.java;
    */
    
    //Para DEBUG: debug= 1; NORMAL: debug = 0;
    public static int debug = 1;
    
    private static final int roomL = 500;
    private static final int roomW = 500;
    
    
    static int raio = 75;
    static int nos = 100;
    
    static String msg = "Mensagem a enviar";
    
    static int contador = 0;
    
    public Main(int raio, int nos, String msg) throws InterruptedException{
        this.raio = raio;
        this.nos = nos;
        this.msg = msg;
        
        main(null);
    }
  

    // Method for getting the maximum value
    public static int getMax(int[] inputArray){ 
        int maxValue = inputArray[0];
        int maxIndex = 0;
        for(int i=1;i < inputArray.length;i++){ 
          if(inputArray[i] > maxValue){ 
             maxValue = inputArray[i];
             maxIndex = i;
            } 
        } 
    return maxIndex; 
  }
    
    public static void main(String[] args) throws InterruptedException{
       
        
        File f = new File("Receivers.csv");
            if(f.exists() && !f.isDirectory()) { 
                f.delete();
            }
        
    //Criar rede
        //N - nodes number
        int N = nos;
        //Definir raio de alcance
        int radius = raio;
        
        Node[] node = new Node[N];    
        
        //Número de colunas
        int nCol = 10;
        //Número médio de pontos por coluna
        int meanPerCol = N/nCol;
        //Número mínimo de pontos por coluna
        int minPerCol = meanPerCol-meanPerCol/2;
        //Número máximo de pontos por coluna        
        int maxPerCol = meanPerCol+meanPerCol/2;
        
        
        //Limites das colunas
        //Valor máximo dos limites das colunas
        int[] maxLim = new int[nCol];
        for(int k = 0; k<nCol; k++){
            maxLim[k] = (k+1)*roomW/nCol;
        }
        //Valor médio dos limites das colunas
        int[] meanLim = new int[nCol];
        meanLim[0] = maxLim[0]/2;
        for(int k = 1; k<nCol; k++){
            meanLim[k] = maxLim[k-1]+(maxLim[k]-maxLim[k-1])/2;
        }

        
        //Pontos em cada coluna
        int[] colPoints = new int[nCol];
        //Garantir que se gera n pontos
        int sum = 0;
        
        //Gerar o número de pontos em cada coluna
        for(int k = 0; k<(nCol-1); k++){
            Random col = new Random();            
            colPoints[k] = col.nextInt(maxPerCol-minPerCol)+minPerCol;
           //Faz somatório de pontos para no final se verificar quantos falta de forma a garantir os n pontos
            sum = sum + colPoints[k];
        }
        colPoints[nCol-1] = (N-1)-sum;
        if(colPoints[nCol-1]<0){
            colPoints[nCol-1] = nCol/4;
            int maxIndex = getMax(colPoints);
            colPoints[maxIndex] = colPoints[maxIndex] + (N-1)-sum - nCol/4;
        }
     //Gerar primeira coluna a ser gerada
        Random colFirst = new Random();
        int firstCol = colFirst.nextInt(nCol);
        int[] colGen = new int[10];
        
        //Criar vetor começado pela primeira coluna gerada aleatoriamente
        int j = 0;
        for(int k = firstCol; k<nCol; k++){
            colGen[j] = colPoints[k];
            j++;
        }
        for(int k = 0; k<firstCol; k++){
            colGen[j] = colPoints[k];
            j++;
        }
        
     int nSer = 1;
        
        //Gerar os nós
        node[0] = new Node(node, 0, 0, 0, 0, 0, roomW, roomL);
        for(int k = 0; k<nCol; k++){
            int nCP = colGen[k];
            for(int g = 0; g<nCP; g ++){
                if(k == 0){
                    node[nSer] = new Node(node, maxLim[k], 0, roomW, 0, nSer, roomW, roomL);
                }else{
                    node[nSer] = new Node(node, maxLim[k], maxLim[k-1], roomW, 0, nSer, roomW, roomL);
                }
                nSer++; 
            }
            
        }
        
        //Verifica vizinhança
        computeNeighbours(node, radius);
        
        //Calcular nível
        getLevel(node);
        
        //Verifica vizinhança tendo em conta os níveis
        computeNeighboursLevel(node, radius);
        
        /*if(debug == 1)
            System.out.println(node[99].x_coord + " " + node[99].y_coord);
        */
        JFrame frame = new JFrame("Glossy simulator");
        
        frame.addWindowListener(new WindowAdapter(){
        
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
    
        });
        
        JTextField msgToSend = new JTextField("Message to Send",20);
        frame.add(msgToSend);
        msgToSend.setSize(350,20);
        msgToSend.setLocation(750, 100);
        
        JTextField raioToWatch = new JTextField("Radius",20);
        frame.add(raioToWatch);
        raioToWatch.setSize(150,20);
        raioToWatch.setLocation(950, 150);
        
        JTextField nodes = new JTextField("Nodes",20);
        frame.add(nodes);
        nodes.setSize(150,20);
        nodes.setLocation(750, 150);
        
        
        JButton startNet = new JButton();
        frame.add(startNet);
        startNet.setSize(200,20);
        startNet.setLocation(800, 200);
        startNet.setText("Start new network");
        startNet.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try {
                    String msg2Send = msgToSend.getText();
                    int raio = Integer.parseInt(raioToWatch.getText());
                    int nos = Integer.parseInt(nodes.getText());
                    
                    new Main(raio, nos, msg2Send);
                    frame.dispose();
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        JButton startSim = new JButton();
        frame.add(startSim);
        startSim.setSize(200,20);
        startSim.setLocation(800, 350);
        startSim.setText("Start simulation");
        startSim.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                
                File fe = new File("Receivers.csv");
                fe.delete();
                    
                contador = 0;
                JFrame z = new JFrame();
                
                int maxLvl = getMaxLevel(node);
                
                z.setSize(700, 700);
                z.setLocation(1200,0);
                z.getContentPane().add(new Circle(N, node, 0));
                z.setVisible(true);
                z.getContentPane().add(new PaintedCircle(node[0], 1, maxLvl));
                z.setVisible(true);
                z.setResizable(false);
                
                Thread[] t = new Thread[N];

                int lvlActual = maxLvl;

                int f = 0;
                for(int i = 0; i<N; i++){   
                    for(int q = 0; q<N; q++){
                        if(node[q].level == lvlActual && lvlActual>=0){
                            t[f] = new Thread(new NodeThread(N, node, q, maxLvl, z, msg));
                            f++;
                        }
                    }
                    lvlActual--;
                }

              for(int i = f-1; i>=0; i--){
                    t[i].start();
                }

                for(int i = f-1; i>=0; i--){
                    try {
                        t[i].join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                JFrame flowNet = new JFrame();
                flowNet.setSize(637, 660);
                flowNet.setResizable(false);
                
               
                
                try {
                    flowNet.setVisible(true);
                    flowNet.getContentPane().add(new ComFlow(node, contador, "Receivers.csv"));
                    flowNet.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                JFrame butaozinho = new JFrame();
                butaozinho.setSize(200, 75);
                butaozinho.setLocation(635,0);
                butaozinho.setResizable(false);
                butaozinho.setVisible(true);
                
                JButton nextLevel = new JButton();
                butaozinho.getContentPane().add(nextLevel);
                nextLevel.setSize(150,50);
                nextLevel.setLocation(750, 330);
                nextLevel.setText("Next Level");
                nextLevel.addActionListener(new ActionListener(){
                   public void actionPerformed(ActionEvent ae){
                        contador ++;
                       try {
                           
                           flowNet.getContentPane().add(new ComFlow(node, contador, "Receivers.csv"));
                           flowNet.setVisible(true);
                       } catch (IOException ex) {
                           Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                       }
                        
                    } 
                 });
                System.out.println("Simulation end");
            }
            
        });
       
        JTextField nodeToWatch = new JTextField(20);
        frame.add(nodeToWatch);
        nodeToWatch.setSize(150,20);
        nodeToWatch.setLocation(750, 500);
        
        
        JButton BnodeToWatch = new JButton();
        frame.add(BnodeToWatch);
        BnodeToWatch.setSize(200,20);
        BnodeToWatch.setLocation(900, 500);
        BnodeToWatch.setText("Node to watch");
        BnodeToWatch.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae){
                int nodeWatching = Integer.parseInt(nodeToWatch.getText());
                
                ShowConnections n = new ShowConnections(node[nodeWatching]);
           } 
        });
        

        
        frame.setSize(1200, 660);
        
        frame.getContentPane().add(new Circle(N, node, 1));
        frame.setResizable(false);
        frame.setVisible(true);
    
    }
    
    public static void computeNeighbours(Node[] node, int radius) {
            double x = 0;
            double y = 0;
            double distance = 0;

            for (int i = 0; i < node.length; i++) {
                    for (int k = 0; k < node.length; k++) {
                            if (i != k) {
                                    x = Math.pow(node[i].getCoordinateX() - node[k].getCoordinateX(), 2);
                                    y = Math.pow(node[i].getCoordinateY() - node[k].getCoordinateY(), 2);
                                    distance = Math.sqrt(x + y);

                                    if (distance <= radius) { // maximum distance allowed between nodes
                                            node[i].updateNeighboursNodes(node[k]);
                                            node[i].updateListOfIPs(node[k].ip);
                                    }

                                    distance = 0;

                            }
                    }
            }
            
    }
    
    public static void computeNeighboursLevel(Node[] node, int radius) {
            double x = 0;
            double y = 0;
            double distance = 0;

            for (int i = 0; i < node.length; i++) {
                    for (int k = 0; k < node.length; k++) {
                            if (i != k) {
                                    x = Math.pow(node[i].getCoordinateX() - node[k].getCoordinateX(), 2);
                                    y = Math.pow(node[i].getCoordinateY() - node[k].getCoordinateY(), 2);
                                    distance = Math.sqrt(x + y);

                                    if ((distance <= radius) && (node[k].level>node[i].level)) { // maximum distance allowed between nodes
                                            node[i].updateNeighboursNodesLevelTx(node[k]);
                                            node[k].updateNeighboursNodesLevelRx(node[i]);
                                            node[i].updateListOfIPsLevelTx(node[k].ip);
                                            node[k].updateListOfIPsLevelRx(node[i].ip);
                                    }

                                    distance = 0;

                            }
                    }
            }
            
    }
    
    private static void getLevel(Node[] node){
        
        int level = 0;
        
        node[0].level = level;
        
        level = 1;
        
        int[] neigID = node[0].getNeigList();
        
        int[] neigIDclear;
    
        for(int i = 0; i<neigID.length; i++){
            node[neigID[i]].level = level;
        }
        
        level = 2;
        
        for(int b = 0; b<node.length; b++){
            for(int i = 0; i<node.length; i++){
                if(node[i].level == (level-1)){
                    
                    neigID = node[i].getNeigList();
                    
                    for(int s = 0; s<neigID.length; s++){
                        if((!((node[neigID[s]].level < level) && (node[neigID[s]].level >= 0))) && !(node[neigID[s]].level >= level)){
                            node[neigID[s]].level = level;
                        }
                  }
                }
            
            }
            level++;
        }
    }
    
    private static int getMaxLevel(Node[] node){
        
        int level_atual = -1;
        for(int i = 0; i<node.length; i++){
            if(node[i].level > level_atual){
                level_atual = node[i].level;
            }
        }
        
        return level_atual;
    }
    
    
    
    
    
}

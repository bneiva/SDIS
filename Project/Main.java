package Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
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
       
    //Criar rede
        //N - nodes number
        int N = 100;
        //Definir raio de alcance
        int radius = 75;
        
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
            if(debug == 1)
                System.out.println("Max Lim ("+k+"): " + maxLim[k]);
        }
        //Valor médio dos limites das colunas
        int[] meanLim = new int[nCol];
        meanLim[0] = maxLim[0]/2;
        if(debug == 1)
                System.out.println("Mean Lim ("+0+"): " + meanLim[0]);
        for(int k = 1; k<nCol; k++){
            meanLim[k] = maxLim[k-1]+(maxLim[k]-maxLim[k-1])/2;
            if(debug == 1)
                System.out.println("Mean Lim ("+k+"): " + meanLim[k]);
        }

        
        //Pontos em cada coluna
        int[] colPoints = new int[nCol];
        //Garantir que se gera n pontos
        int sum = 0;
        
        //Gerar o número de pontos em cada coluna
        for(int k = 0; k<(nCol-1); k++){
            Random col = new Random();            
            colPoints[k] = col.nextInt(maxPerCol-minPerCol)+minPerCol;
            if(debug == 1)
                System.out.println(colPoints[k]);
            //Faz somatório de pontos para no final se verificar quantos falta de forma a garantir os n pontos
            sum = sum + colPoints[k];
        }
        colPoints[nCol-1] = (N-1)-sum;
        if(colPoints[nCol-1]<0){
            colPoints[nCol-1] = nCol/4;
            int maxIndex = getMax(colPoints);
            colPoints[maxIndex] = colPoints[maxIndex] + (N-1)-sum - nCol/4;
        }
        if(debug == 1)
            System.out.println("Final"+colPoints[nCol-1]);
        
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
        
        if(debug == 1){
            System.out.println("First Column:"+firstCol);
            for(int k = 0; k<nCol; k++){
                System.out.println("k="+k+": "+colGen[k]);
            }
            for(int k = 0; k<nCol; k++){
                System.out.println("k="+k+": "+colPoints[k]);
            }
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
        
        if(debug == 1)
            System.out.println(node[99].x_coord + " " + node[99].y_coord);
        
        JFrame frame = new JFrame("Glossy simulator");
        
        frame.addWindowListener(new WindowAdapter(){
        
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
    
        });
          
        JButton startSim = new JButton();
        frame.add(startSim);
        startSim.setSize(200,20);
        startSim.setLocation(800, 100);
        startSim.setText("Start simulation");
       
        
        /*
        JButton newSim = new JButton();
        frame.add(newSim);
        newSim.setSize(150,20);
        newSim.setLocation(950, 100);
        newSim.setText("Restart network");
        newSim.addActionListener(new NewSim());
        */      
        
      
        
        JTextField nodeToWatch = new JTextField(20);
        frame.add(nodeToWatch);
        nodeToWatch.setSize(150,20);
        nodeToWatch.setLocation(750, 300);
        
        
        JButton BnodeToWatch = new JButton();
        frame.add(BnodeToWatch);
        BnodeToWatch.setSize(200,20);
        BnodeToWatch.setLocation(900, 300);
        BnodeToWatch.setText("Node to watch");
        BnodeToWatch.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae){
                int nodeWatching = Integer.parseInt(nodeToWatch.getText());
                
                ShowConnections n = new ShowConnections(node[nodeWatching]);
           } 
        });
        
        JButton Bstats = new JButton();
        frame.add(Bstats);
        Bstats.setSize(200,20);
        Bstats.setLocation(800, 500);
        Bstats.setText("Simulation stats");
      
        
        frame.setSize(1200, 670);
        
        frame.getContentPane().add(new Circle(N, node));
        frame.setResizable(false);
        frame.setVisible(true);
        
        
        System.out.println("");
      /*  
        //Criar Threads de nodes
        Thread[] t = new Thread[N];
        int l = 0;
        for(int i = 0; i<N; i++){
            try { 
               t[i] = new Thread(new NodeThread(N, node, i));
               t[i].start();
               l++;
            } catch (Exception e) {
                System.out.println("Error in thread creation!");
            }
        }
        
        for(int i = 0; i<N; i++){
            t[i].join();
        }
        */
        /*int[] neigTest = node[0].getNeigList();
        
        for(int d = 0; d<neigTest.length; d++)
            System.out.println(neigTest[d]);
        
        */
    
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
                                            // System.out.println("distance between " + node[i].id + " and " + node[k].id +
                                            // " = " + dystance);
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
                                            // System.out.println("distance between " + node[i].id + " and " + node[k].id +
                                            // " = " + dystance);
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
            if(debug == 1)
            System.out.println("Level: "+ node[neigID[i]].level +"; NodeID: "+neigID[i]);
        }
        
        level = 2;
        
        for(int b = 0; b<node.length; b++){
            for(int i = 0; i<node.length; i++){
                if(node[i].level == (level-1)){
                    
                    neigID = node[i].getNeigList();
                    
                    for(int s = 0; s<neigID.length; s++){
                        if((!((node[neigID[s]].level < level) && (node[neigID[s]].level >= 0))) && !(node[neigID[s]].level >= level)){
                            System.out.println(node[neigID[s]].level + " NODE : " + neigID[s]);
                            node[neigID[s]].level = level;
                        }
                  }
                }
            
            }
            level++;
        }
    }
    
    
    
    
}

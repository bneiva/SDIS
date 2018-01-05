package Project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;


public class ShowConnections extends JFrame{

    Node node;
    public ShowConnections(Node node) {
        this.node = node;
        graphicInterface();
    }
    
    private void graphicInterface(){
        
        System.out.println("TOU");
        
        JFrame frame = new JFrame("Node: " + node.id);

        frame.setSize(500, 500);
        
        frame.add(new MainPanel(node));
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
     public void paint(Graphics g){
         
         g.setColor(Color.white);
         g.fillRect(0,0,500,500);
     }
    
}

class MainPanel extends JComponent{
    
    Node node;
    Node[] RXnodes;
    Node[] TXnodes;
    
    public MainPanel(Node n){
         this.node = n;
         System.out.print(node.id);
        if(!node.myNeighboursLevelRx.isEmpty()){
            RXnodes = node.myNeighboursLevelRx.toArray
                (new Node[node.myNeighboursLevelRx.size()]);
        }
        if(!node.myNeighboursLevelTx.isEmpty()){
            TXnodes = node.myNeighboursLevelTx.toArray
                (new Node[node.myNeighboursLevelTx.size()]);
        } 

    }
    
    public void paint(Graphics g){
         
         g.setColor(Color.white);
         g.fillRect(0,0,500,500);
        
         
         
         g.setColor(Color.BLACK);
         for(int i = 0; i<50; i++){
             if(i%2 == 0 && i!=26){
                 g.setColor(Color.BLACK);
                 g.drawLine((i-1)*10,250-5,i*10,250-5);
             } else {
                 g.setColor(Color.white);
                 g.drawLine((i-1)*10,250-5,i*10,250-5);
             }
         }
         g.setColor(Color.BLACK);
         g.drawString(Integer.toString(node.id), 250, 250);
         g.drawOval(250-3, 250-16 , 20, 20);
         
         //ACABAR DE DESENHAR RXS e TXS
         
        
     }
}

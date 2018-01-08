package Project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ShowConnections extends JFrame{

    //tenho de ir buscar o ip dos vizinhos que estão a receber e a enviar 
    // e desenhá-los
    Node node;
    
    private static final int roomL = 500;
    private static final int roomW = 500;
  //  private static  JFrame frame;
   
 //   public  JTextField info_tx;
    
    public ShowConnections(Node node) {
        this.node = node;
        graphicInterface();
    }
    
    private void graphicInterface(){
        
     //   System.out.println("TOU");
         
  
       JFrame frame = new JFrame("Node: " + node.id);  
       
     /*  
        JPanel tx_cena = new JPanel();
       
        JLabel label_tx = new JLabel();
     
        
        tx_cena.add(label_tx);
              
       */
       // JTextField info_tx = new JTextField();        
      //  info_tx.setText("cenas");
          
        frame.setSize(500, 500); 
        frame.add(new MainPanel(node));
     //   frame.add(tx_cena);
     //      label_tx.setText("cenas");
     //   tx_cena.add(label_tx);
        frame.setResizable(false);
        frame.setVisible(true);    
        
      //    frame.add(info_tx);
    }
    
     public void paint(Graphics g){
         
         g.setColor(Color.white);
         g.fillRect(0,0,500,500);
     }
    
}

class MainPanel extends JComponent{
    
    Node node;
    int[] RXnodes;
    int[] TXnodes;
    int rx_lvl;
    int tx_lvl;
 
    JTextField info_tx; 
    
    int debug = 1;
    
    
    public MainPanel(Node n){
        this.node = n;
       // this.info_tx = lvl_tx;
      
        
        System.out.print(node.id);
        
        RXnodes = n.getRxList();
        TXnodes = n.getTxList();
        rx_lvl = n.level -1;
        tx_lvl = n.level;
        
        if(debug == 1){
            for(int i = 0; i< RXnodes.length; i++){
                System.out.println("RX node["+i+"] : " + RXnodes[i] );
            }
            
            
            for(int i = 0; i< TXnodes.length; i++){
                System.out.println("TX node["+i+"] : " + TXnodes[i] );
            }
        }
       
    }

    
    public void paint(Graphics g){
     
        
        
   //     this.node = no;
    //    int nivel;
        
    //    nivel = no.level;
         
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

//        info_tx.setText((Integer.toString(nivel)));
        
        int nrx_colums = RXnodes.length;
        int ntx_colums = TXnodes.length;
        
        int itr_rx = 500/(nrx_colums+1);
        int count_rx = 500/(nrx_colums+1);
        
        
        int itr_tx = 500/(ntx_colums+1);
        int count_tx = 500/(ntx_colums+1);
             
       
        for (int i=0 ; i< RXnodes.length ; i++) { 
            
         //   int[] x_pos = {count_rx+10, count_rx-7+10, count_rx+7+10};
          //  int[] y_pos = { 100+19, 100+28 , 100+28};    
          
            
            int[] x_pos = {250+8, 252+3, 265-3};
            int[] y_pos = { 250-16, 250-26+3, 250-26+3};  
           
         // System.out.println("  count  "  + count_rx );
            g.drawString("Rx nodes, level: " + rx_lvl , 50 , 50);
            g.drawString( Integer.toString(RXnodes[i]) , count_rx+5, 100+15);
            g.drawOval(count_rx, 100, 20, 20);
            g.drawLine( 257,  250-16, count_rx+10, 100+19);
            
             g.fillPolygon(x_pos, y_pos, 3);
            count_rx = itr_rx + count_rx;
         }
        
  
    
         for (int i= 0; i< TXnodes.length ; i++ ) {  
              
        //    int[] x_pos = {250+8, 252, 265};
        //    int[] y_pos = { 250+3, 263, 263};  
        
            int[] x_pos = {count_tx+10, count_tx-7+10+3, count_tx+7+10-3};
            int[] y_pos = { 375, 375-10+3 , 375-10+3};    
          
           
            g.drawString("Tx nodes, level: " + tx_lvl , 50 , 450);
            g.drawString(Integer.toString(TXnodes[i]),count_tx+5 , 375+15);
            g.drawOval(count_tx, 375 , 20, 20);
            g.drawLine(257, 250+2, count_tx+10 , 375);
            g.fillPolygon(x_pos, y_pos, 3);
            count_tx = itr_tx + count_tx;
         }
        
    
     
        
    }
}
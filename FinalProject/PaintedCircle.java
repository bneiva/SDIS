package Project;

import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;


public class PaintedCircle extends JComponent {
    
    Node nodeToDraw;
    
    Color[] c = new Color[15];
    int legend;
    
    int maxLvl;    
    
    //N - nr de nodes; Node[] - vetor de nós criado com respetivas coordenadas, ids e cores
    public PaintedCircle(Node node, int legend, int maxLvl){

        nodeToDraw = node;
        this.legend = legend;
        
        this.maxLvl = maxLvl;
    
        c[0] = Color.black;
        c[1] = Color.blue;
        c[2] = Color.red;
        c[3] = Color.yellow;
        c[4] = Color.green;
        c[5] = Color.gray;
        c[6] = Color.PINK;
        c[7] = Color.ORANGE;
        c[8] = Color.MAGENTA;
        c[9] = new Color(148,0,211);
        c[10] = new Color(95,2,31);
        c[11] = new Color(139,69,19);
        c[12] = new Color(255,218,185);
        c[13] = new Color(145, 255,18);
        c[14] = new Color(18,248,255);
    
    }
   
    @Override
    public void paint(Graphics g) {
       
        if(legend == 0){
        g.setColor(c[0]);
        g.drawString(Integer.toString(nodeToDraw.id), nodeToDraw.x_coord, nodeToDraw.y_coord);
        g.setColor(c[nodeToDraw.level]);
        g.drawOval(nodeToDraw.x_coord-1, nodeToDraw.y_coord-14, 16, 16);
        }
        if(legend == 1){
            for(int i = 0; i<15; i++){
                g.setColor(c[i]);

                g.fillRect(590, (i*20)+20, 10, 10);
                g.setColor(c[0]);
                g.drawString("level: "+ i, 600, i*20+30);
                
                g.drawString("Max Level: " + maxLvl, 550, 650);
                
                
            }
        }
        if(legend == 2){
            g.setColor(Color.white);
            g.fillRect(0,0,600,600);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(0,0,600,600);
            g.drawRect(1,1,598,598);
            g.drawRect(2,2,596,596);
        }
        
    }
      
    
  }

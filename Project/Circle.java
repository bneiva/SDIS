package Project;

import java.awt.*;
import javax.swing.JComponent;


public class Circle extends JComponent {
    
    Node[] nodeToDraw;
    int N;
   
    //N - nr de nodes; Node[] - vetor de nós criado com respetivas coordenadas, ids e cores
    public Circle(int N, Node[] node){
        this.N = N;
        nodeToDraw = new Node[N];
        
        System.arraycopy(node, 0, nodeToDraw, 0, node.length);
    
        for(int i = 0; i<nodeToDraw.length; i++){
            nodeToDraw[i].x_coord += 30;
            nodeToDraw[i].y_coord += 30;
        }
    }
   
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0,0,630,630);
        
        g.setColor(Color.red);
        g.drawOval(-70, -70, 200, 200);
        for(int j=0; j<nodeToDraw.length ; j++) {
            g.setColor(Color.black);
            //g.setColor(nodeToDraw[j].color);
            g.drawString(Integer.toString(nodeToDraw[j].id), nodeToDraw[j].x_coord, nodeToDraw[j].y_coord);
            g.drawOval(nodeToDraw[j].x_coord-1, nodeToDraw[j].y_coord-14, 16, 16);

        }
    }
      
    
  }

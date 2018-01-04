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
    }
   
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0,0,600,600);
        for(int j=0; j<nodeToDraw.length ; j++) {
            g.setColor(Color.black);
            //g.setColor(nodeToDraw[j].color);
            g.drawString(Integer.toString(nodeToDraw[j].id), nodeToDraw[j].x_coord, nodeToDraw[j].y_coord);
            g.drawOval(nodeToDraw[j].x_coord, nodeToDraw[j].y_coord, 15, 15);

        }
    }
      
    
  }

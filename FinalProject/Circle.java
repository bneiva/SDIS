package Project;

import java.awt.*;
import javax.swing.JComponent;


public class Circle extends JComponent {
    
    Node[] nodeToDraw;
    int N;
    
    int nodes;
   
    //N - nr de nodes; Node[] - vetor de nós criado com respetivas coordenadas, ids e cores
    public Circle(int N, Node[] node, int nodes){
        this.N = N;
        nodeToDraw = new Node[N];
        
        this.nodes = nodes;
        
        System.arraycopy(node, 0, nodeToDraw, 0, node.length);
    
        for(int i = 0; i<nodeToDraw.length; i++){
            nodeToDraw[i].x_coord += 30;
            nodeToDraw[i].y_coord += 30;
        }
    }
   
    @Override
    public void paint(Graphics g) {
        if(nodes == 1){
        g.setColor(Color.white);
        g.fillRect(0,0,630,630);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(0,0,630,630);
        g.drawRect(1,1,628,628);
        g.drawRect(2,2,626,626);

        
        g.setColor(Color.black);
        g.drawString(Integer.toString(nodeToDraw[0].id), nodeToDraw[0].x_coord, nodeToDraw[0].y_coord);
        g.fillOval(nodeToDraw[0].x_coord-1, nodeToDraw[0].y_coord-14, 16, 16);
        
        //Desenhar Nodes
        g.setColor(Color.black);
        for(int j=1; j<nodeToDraw.length ; j++) {
        //g.setColor(nodeToDraw[j].color);
        g.drawString(Integer.toString(nodeToDraw[j].id), nodeToDraw[j].x_coord, nodeToDraw[j].y_coord);
        g.drawOval(nodeToDraw[j].x_coord-1, nodeToDraw[j].y_coord-14, 16, 16);

            }
        }    
        
        
        //Desenhar Setas
            g.setColor(Color.gray);
            for(int j = 0; j<nodeToDraw.length; j++){
                Node[] neig = nodeToDraw[j].myNeighboursLevelTx.toArray(new Node[nodeToDraw[j].myNeighboursLevelTx.size()]);
                for(int l = 0; l<neig.length; l++){
                    int d = 3;
                    int h = 3;
                    int x1 = nodeToDraw[j].x_coord+7;
                    int y1 = nodeToDraw[j].y_coord-6;
                    int x2 = neig[l].x_coord+7;
                    int y2 = neig[l].y_coord-6;
                    int dx = x2 - x1, dy = y2 - y1;
                    double D = Math.sqrt(dx*dx + dy*dy);
                    double xm = D - d, xn = xm, ym = h, yn = -h, x;
                    double sin = dy / D, cos = dx / D;

                    x = xm*cos - ym*sin + x1;
                    ym = xm*sin + ym*cos + y1;
                    xm = x;

                    x = xn*cos - yn*sin + x1;
                    yn = xn*sin + yn*cos + y1;
                    xn = x;

                    int[] xpoints = {x2, (int) xm, (int) xn};
                    int[] ypoints = {y2, (int) ym, (int) yn};

                    g.drawLine(x1, y1, x2, y2);

                    g.fillPolygon(xpoints, ypoints, 3);
                }
            }
        
        
    }
      
    
  }

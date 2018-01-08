
package node;

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
    
	private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = D - d, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;

		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;

		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;

		int[] xpoints = { x2, (int) xm, (int) xn };
		int[] ypoints = { y2, (int) ym, (int) yn };

		g.drawLine(x1, y1, x2, y2);
		g.fillPolygon(xpoints, ypoints, 3);
	
	}

}
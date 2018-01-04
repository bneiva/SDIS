/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinestate;

/**
 *
 * @author ilia
 */


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class circle extends Frame {
    private int x, y;
    public static int n_circles;
 
    Shape circle = new Ellipse2D.Float( 100 , 100 , 20.0f, 20.0f);

    public void paint(Graphics g) {
           
    Graphics2D ga = (Graphics2D)g;
    ga.draw(circle);
    ga.setPaint(Color.red);
    ga.fill(circle);  
  }
    
    
    public static void main(String args[]) {
    Frame frame = new circle();
    
    frame.addWindowListener(new WindowAdapter(){
        
    public void windowClosing(WindowEvent we){
        
    System.exit(0);
  }
    
  });
    
    
    frame.setSize(300, 250);
    frame.setVisible(true);
  } 

}


   

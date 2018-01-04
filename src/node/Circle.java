/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package node;

/**
 *
 * @author ilia
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class Circle extends Frame {
   public String node_ip;
  
    public void paint(Graphics g) {
        
        node_ip= "100.0.0.0";
        
        g.setColor(Color.red);
        
        for(int i=0; i<5 ; i++) {
            
            for(int j=0; j<5 ; j++) {
                g.drawString(node_ip, 100, 100*j);
                g.fillOval(100, 100*j, 20, 20);
            }
        }
  }
      
    public static void main(String args[]) {
    Frame frame = new Circle();
    
    frame.addWindowListener(new WindowAdapter(){
        
    public void windowClosing(WindowEvent we){
        
    System.exit(0);
  }
    
  });
    
    
    frame.setSize(1000, 1000);
    frame.setVisible(true);
  } 

}
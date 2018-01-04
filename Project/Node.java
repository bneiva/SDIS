/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.util.Random;

/**
 *
 * @author Nuno Moreira
 */
public class Node {
    
    
    
    int x_coord;
    int y_coord;
    int id;
    
    String ip = new String();
    
    private void verifyNeig(int radius, int id, Node[] node){
        int raio;
        raio = radius;
        
        //Acabar
    }
    
    public Node(Node[] n, int maxX, int minX, int maxY, int minY, int nextIP){
        
        if(maxX == 0 && minX == 0 && maxY == 0 && minY == 0 && nextIP == 0 ){
            x_coord = 0;
            y_coord = 0;
        }else{
        //Criar coordenada x
        Random xRand = new Random();
        x_coord = xRand.nextInt(maxX-minX) + minX;      
        
        //Criar coordenada y
        Random yRand = new Random();
        y_coord = yRand.nextInt(maxY-minY) + minY;
        
        for(int j = 0; j<nextIP; j++){
            while(
                    ((x_coord < (n[j].x_coord+18)) && (x_coord > (n[j].x_coord-18))) &&
                    ((y_coord < (n[j].y_coord+18)) && (y_coord > (n[j].y_coord-18)))
                    ){
                x_coord = xRand.nextInt(maxX-minX) + minX;
                y_coord = yRand.nextInt(maxY-minY) + minY;
                j = 0;
            }
        }
        
        }
        //Atribuir id
        id = nextIP;        
        //Atribuir IP
        ip = "224.1.1."+Integer.toString(nextIP);

    }
    

}

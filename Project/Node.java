/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Nuno Moreira
 */
public class Node {
    
    
    
    int x_coord;
    int y_coord;
    int id;
    int porta;
    
    int level = -1;
    
    String ip = new String();
    
    
    ArrayList<Node> myNeighbours = new ArrayList<Node>();
    ArrayList<String> ipNeighbours = new ArrayList<String>();
    
    ArrayList<Node> myNeighboursLevelTx = new ArrayList<Node>();
    ArrayList<String> ipNeighboursLevelTx = new ArrayList<String>();
    
    //Lista de onde recebe
    ArrayList<Node> myNeighboursLevelRx = new ArrayList<Node>();
    //Lista para quem envia
    ArrayList<String> ipNeighboursLevelRx = new ArrayList<String>();
    
   
    public Node(Node[] n, int maxX, int minX, int maxY, int minY, int nextIP, int roomW, int roomL){
        
        if(maxX == 0 && minX == 0 && maxY == 0 && minY == 0 && nextIP == 0 ){
            x_coord = roomL/2;
            y_coord = roomW/2;
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
        //Atribuir porta
        porta = 9900+(nextIP);

    }
    
    public int getCoordinateX() {
		return this.x_coord;

	}

	public int getCoordinateY() {
		return this.y_coord;

	}

	public void updateNeighboursNodes(Node neighbour) {
		this.myNeighbours.add(neighbour);
	}

	public void updateListOfIPs(String ip) {
		this.ipNeighbours.add(ip);
	}
        
        public void updateNeighboursNodesLevelTx(Node neighbour) {
		this.myNeighboursLevelTx.add(neighbour);
	}
        
        public void updateListOfIPsLevelTx(String ip) {
		this.ipNeighboursLevelTx.add(ip);
	}
        
         public void updateNeighboursNodesLevelRx(Node neighbour) {
		this.myNeighboursLevelRx.add(neighbour);
	}
        
        public void updateListOfIPsLevelRx(String ip) {
		this.ipNeighboursLevelRx.add(ip);
	}
        
        

	public void printListIP() {

		Iterator itr = this.ipNeighbours.iterator();
		while (itr.hasNext())
			System.out.println("node" + this.ip + " and id " + this.id + " has this neighbours " + itr.next());

	}
        
        public int[] getNeigList(){
            
            Node[] neig = this.myNeighbours.toArray(new Node[this.myNeighbours.size()]);
            
            int[] neigID = new int[neig.length] ;
            
            
            for(int d = 0; d<neig.length; d++)
                neigID[d] = neig[d].id;
            
            return neigID;
        }
        
        public int[] getRxList(){
            
            Node[] neig = this.myNeighboursLevelRx.toArray(new Node[this.myNeighboursLevelRx.size()]);
            
            int[] neigID = new int[neig.length] ;
            
            
            for(int d = 0; d<neig.length; d++)
                neigID[d] = neig[d].id;
            
            return neigID;
        }
        
        public int[] getTxList(){
            
            Node[] neig = this.myNeighboursLevelTx.toArray(new Node[this.myNeighboursLevelTx.size()]);
            
            int[] neigID = new int[neig.length] ;
            
            
            for(int d = 0; d<neig.length; d++)
                neigID[d] = neig[d].id;
            
            return neigID;
        }
        
    
}

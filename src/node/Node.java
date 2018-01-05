package node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Nuno Moreira
 */
public class Node extends Thread {

	int x_coord;
	int y_coord;
	int id;
	private ArrayList<Node> myNeighbours = new ArrayList<Node>();
	private ArrayList<String> ipNeighbours = new ArrayList<String>();

	String ip = new String();

	private void verifyNeig(int radius, int id, Node[] node) {
		int raio;
		raio = radius;

		// Acabar
	}

	public Node(Node[] n, int maxX, int minX, int maxY, int minY, int nextIP) {

		if (maxX == 0 && minX == 0 && maxY == 0 && minY == 0 && nextIP == 0) {
			x_coord = 0;
			y_coord = 0;
		} else {
			// Criar coordenada x
			Random xRand = new Random();
			x_coord = xRand.nextInt(maxX - minX) + minX;

			// Criar coordenada y
			Random yRand = new Random();
			y_coord = yRand.nextInt(maxY - minY) + minY;

			for (int j = 0; j < nextIP; j++) {
				while (((x_coord < (n[j].x_coord + 18)) && (x_coord > (n[j].x_coord - 18)))
						&& ((y_coord < (n[j].y_coord + 18)) && (y_coord > (n[j].y_coord - 18)))) {
					x_coord = xRand.nextInt(maxX - minX) + minX;
					y_coord = yRand.nextInt(maxY - minY) + minY;
					j = 0;
				}
			}

		}
		// Atribuir id
		id = nextIP;
		// Atribuir IP
		ip = "224.1.1." + Integer.toString(nextIP);

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

	public void printListIP() {

		Iterator itr = this.ipNeighbours.iterator();
		while (itr.hasNext())
			System.out.println("node" + this.ip + " and id " + this.id + " has this neighbours " + itr.next());

	}
	
	public void run() {
		try {
			startNode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//state machine
	public void startNode() throws Exception {
		if (this.id<1) {
			try {
				Iterator itr = this.ipNeighbours.iterator();
				int i=0;
				while (itr.hasNext()) {
					MulticastPublisher[] Tx = new MulticastPublisher[this.ipNeighbours.size()];
					Tx[i] = new MulticastPublisher(12345,(String)itr.next());
					System.out.println("Eihhhhhhhhhhhhhhhhhhhhhhhhhhhh" + (String)itr.next());
					Tx[i].start();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (this.id!=0 && this.id<100) {
			MulticastReceiver Rx =  new MulticastReceiver(12345,this.ip); 
			Packet packet = new Packet();
			Rx.start();
			System.out.println("packet bytes  " +  Rx.packetMessageBytes());

		}

	}

}

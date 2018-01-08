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
	int alive=0;
	String ip = new String();
	private ArrayList<Node> myNeighbours = new ArrayList<Node>();
	private ArrayList<String> ipNeighbours = new ArrayList<String>();
	private int counterMessage = 0;
	private String Initiator;

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

	public ArrayList<String> getIpNeighboursNodes() {
		return this.ipNeighbours;
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

	// state machine
	public void startNode() throws Exception {
		if (this.id == 12) {
			try {

				MulticastPublisher Tx1 = new MulticastPublisher(12345, this.ip);
				// Packet packet11 = new Packet();
				String messageToSend = " Mesage counter: " + Integer.toString(this.counterMessage);
				Tx1.sendPacketMessage(messageToSend.getBytes());
				Tx1.start();
				// }
				this.counterMessage++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (this.id != 0 && this.id < 100 && this.id != 12) {
			MulticastReceiver[] Rx = new MulticastReceiver[this.ipNeighbours.size()];
			for (int i = 0; i < this.ipNeighbours.size(); i++) {
				Rx[i] = new MulticastReceiver(12345, this.ipNeighbours.get(i));
				Rx[i].start();
				Rx[i].packetMessageBytes();
				System.out.println("packet bytes " + Rx[i].packetMessageBytes() + "waiting with this IP" + this.ip);

			}

		}

	}

}

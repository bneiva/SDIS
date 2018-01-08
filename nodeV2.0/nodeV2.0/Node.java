package node;

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
	int alive = 0;
	String ip = new String();
	private ArrayList<Node> myNeighbours = new ArrayList<Node>();
	private ArrayList<String> ipNeighbours = new ArrayList<String>();
	private int counterMessage = 0;
	private boolean initiator = false;
	public String message;
	ArrayList<String> messageReceived = new ArrayList<String>();
	ArrayList<String> ipToListen = new ArrayList<String>();
	MulticastReceiver[] Rx;
	private String Status = null;
	private String Receive = "receive";
	private String Transmit = "transmit";

	int level = -1;

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

	public ArrayList<Node> getNeighboursNodes() {
		return this.myNeighbours;
	}

	public ArrayList<String> getIpNeighboursNodes() {
		return this.ipNeighbours;
	}

	public void updateListOfIPs(String ip) {
		this.ipNeighbours.add(ip);
	}

	public void setInitiator(boolean initiator) {
		this.initiator = initiator;
	}

	public boolean getStatusInitiator() {
		return this.initiator;
	}

	public void printListIP() {

		Iterator itr = this.ipNeighbours.iterator();
		while (itr.hasNext())
			System.out.println("node" + this.ip + " and id " + this.id + " has this neighbours " + itr.next());

	}

	// state machine
	public void startNode() throws Exception {

		// primeira vez que o initiator executa
		if (this.initiator && this.alive == 0) {
			this.alive = 1; // iniciador esta vivo e envia primeiro pacote
			try {

				MulticastPublisher Tx1 = new MulticastPublisher(12345, this.ip);
				String messageToSend = " Mesage counter: [" + Integer.toString(this.counterMessage) + "]";
				Tx1.sendPacketMessage(messageToSend.getBytes());
				Tx1.start();
				this.Status = this.Receive;
				// }
				// this.counterMessage++; //update conter message
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (this.initiator == false && this.alive == 0) { 
			// primeira vez que um nó acorda recebe um pacote
			Rx = new MulticastReceiver[this.ipNeighbours.size()];

			//System.out.println("WAITING IN .................." + this.ip);
			for (int i = 0; i < this.getNeighboursNodes().size(); i++) {
				if (this.getNeighboursNodes().get(i).level < this.level) {
					Rx[i] = new MulticastReceiver(12345, this.getNeighboursNodes().get(i).ip, (Node) this);
					Rx[i].start();
					// Rx[i].join(2000);
					// Rx[i].packetMessageBytes();
					// System.out.println("packet bytes " + "waiting with this
					// IP "
					// + this.ip);
				}
				this.Status = this.Transmit;

			}

			this.alive = 1; // nos receptores do estao vivos

		} else if (this.alive == 1 && this.messageReceived.size() == 3 && this.initiator == false) { // nós
				// nó que recebeu, vai passar transmissor
			this.counterMessage=updateCounterMessage(this.messageReceived);
			//for (int i = 0; i < this.getNeighboursNodes().size(); i++) {
				//Rx[i].mcSocket.disconnect();
			//}
			
				try {

					MulticastPublisher Tx1 = new MulticastPublisher(12345, this.ip);
					String messageToSend = " Mesage counter: [" + Integer.toString(this.counterMessage) + "]";
					Tx1.sendPacketMessage(messageToSend.getBytes());
					Tx1.start();
					
					//System.out.println("TRANSMIT WITH .................." + this.ip);
					this.Status = this.Receive;

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.messageReceived.remove(2);
				this.messageReceived.remove(1);
				this.messageReceived.remove(0);
			this.Status = this.Transmit;

			//System.out.println("Second Transmission node: " + this.id);
		} else if (this.initiator == true && this.alive == 1 && this.messageReceived.size() != 3) { 
			// caso especial para o iniciador receber um pacote
			
			this.alive = 1; // nos receptores do estao vivos
			Rx = new MulticastReceiver[this.ipNeighbours.size()+100];

			for (int i = 0; i < this.getNeighboursNodes().size(); i++) {
				if (this.getNeighboursNodes().get(i).level > this.level) {
					Rx[i] = new MulticastReceiver(12345, this.getNeighboursNodes().get(i).ip, (Node) this);
					Rx[i].start();
					// Rx[i].join(2000);
					// Rx[i].packetMessageBytes();
					// System.out.println("packet bytes " + "waiting with this
					// IP "
					// + this.ip);
					//System.out.println("WAITING IN .........333333........." + this.ip);
				}
			}
		} else if (this.initiator == true && this.alive == 1 && this.messageReceived.size() == 3) { 
		    //  caso especial em que o inicador  vai voltar a transmitir o pacote
			
			this.alive = 1; // nos receptores do estao vivos
			// Rx = new MulticastReceiver[this.ipNeighbours.size()];

			//System.out.println("WAITING IN ....12211212.............." + this.ip);

		}

	}

	public int updateCounterMessage(ArrayList<String> messageReceived) {
		String counter = null;

		for (int i = 0; i < messageReceived.get(0).length(); i++) {

			messageReceived.get(0).charAt(i);
			if (messageReceived.get(0).charAt(i) == '[') {
				i++;
				counter = Character.toString(messageReceived.get(0).charAt(i));
				while (messageReceived.get(0).charAt(i) != ']') {
					i++;
					if (messageReceived.get(0).charAt(i) != ']')
						counter += messageReceived.get(0).charAt(i);

				}

			}
		}
		counter = Integer.toString((Integer.parseInt(counter) + 1));
		//System.out.println("counterMessage : " + counter);
		return Integer.parseInt(counter);

	}

}

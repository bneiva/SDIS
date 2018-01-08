package node;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TimerNode extends TimerTask {
	private boolean x1 = false;
	Timer timer;
	boolean glossyState;
	private Node nodeTimer;
	private int counter = 0;
	private Node node[];
	private ExcutionTimer handlerTimer;
	int counterCycle = 0;
	int runNetwork = 0;

	TimerNode(boolean glossyState, Node nodeTimer, Node[] node, ExcutionTimer handlerTimer) {
		this.glossyState = glossyState;
		this.nodeTimer = nodeTimer;
		this.node = node;
		this.handlerTimer = handlerTimer;

	}

	@Override
	public void run() {

		System.out.println("Schedualed task");
		// this.glossyState = !glossyState;

		if (this.nodeTimer.alive == 0) {
			ExecuteNode executeInitiartor = new ExecuteNode(this.nodeTimer);
			executeInitiartor.start();
			// this.nodeTimer.start();
			// this.handlerTimer.activateTimer("stop", 0, 0, null, null);
			// this.handlerTimer.activateTimer("start",0,5000, node[12], node);

			// this.nodeTimer.alive = 1;
		} else if (this.nodeTimer.alive == 1) {
			// ExecuteNode[] exNode = null;
			boolean reachEnd = true;
			counterCycle++;
			int counter = 0;
			ExecuteNode[] exNode = new ExecuteNode[1000];
			ArrayList<Integer> nodeThreadAlive = new ArrayList<Integer>();
			// inicializa receptores
			for (int i = 0; i < this.node.length; i++) {
				if (node[i].messageReceived.size() == 3) {
					for (int a = 0; a < node[i].getNeighboursNodes().size(); a++) {
						if (node[i].getNeighboursNodes().get(a).level == (node[i].level + 1)) {
							reachEnd = false;
							if (!nodeThreadAlive.contains(node[i].getNeighboursNodes().get(a).id)) {
								// System.out.println("node wake " +
								// node[i].getNeighboursNodes().get(a).id);
								nodeThreadAlive.add(node[i].getNeighboursNodes().get(a).id);
								exNode[counter] = new ExecuteNode(node[i].getNeighboursNodes().get(a));
								exNode[counter].start();

								if (exNode[counter].isAlive()) {
									// this.nodeThreadAlive.add(a);
									System.out.println(exNode[counter].getId() + "Check if alive  node id: "
											+ node[i].id + " de nivel: " + node[i].level + " acordou no: "
											+ node[i].getNeighboursNodes().get(a).id + "de nivel: "
											+ node[i].getNeighboursNodes().get(a).level);

								}
							}
							counter++;

						}

					}
				}
			}
			//if (reachEnd == true) {
				//System.out.println("Chegou ao fim ........................................." + counterCycle);
				// inicializa transmissores
				//this.handlerTimer.activateTimer("stop", 0, 0, null, null);

			//}
			for (int i = 0; i < this.node.length; i++) {
				ExecuteNode[] TxNode = new ExecuteNode[100];
				if (node[i].messageReceived.size() == 3) {
					TxNode[i] = new ExecuteNode(node[i]);
					TxNode[i].start();
					if (TxNode[i].isAlive()) {
						System.out.println("node TRANSMISSOR " + i + " is alive  ");

					}

				}
			}
			if (counterCycle > 10) {
				this.handlerTimer.activateTimer("stop", 0, 0, null, null);
			}

		}
		System.out.println("Time's up!" + " counter = " + counter++);

	}

	public boolean getglossyState() {
		return this.glossyState;
	}
}

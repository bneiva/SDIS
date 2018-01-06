package node;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.System.arraycopy;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	/*
	 * Os nós são criados num vetor Node[]; é possível definir o número máximo de
	 * pontos (N), a largura e altura máximas da janela/sala/grafico (roomL, roomW)
	 * e o número de colunas diferentes (nCol) para criação dos pontos; Estes pontos
	 * podem ser posteriormente definidos a partir do String[] args;
	 * 
	 * Criação dos pontos: A largura da janela é dividida em nCol e são definidos os
	 * limites máximos de cada coluna por exemplo: Largura da janela (roomL = 500);
	 * Numero de colunas (nCol = 10; Limites (maxLim): | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
	 * 8 | 9 | 10 | | | | | | | | | | | | | | | | | | | | | | | 0 50 100 150 200 250
	 * 300 350 400 450 500 É gerado um número aleatório de pontos por coluna de
	 * forma a fazer os N pontos. É gerado o nó iniciador (0,0); São gerados os
	 * outros nós.
	 * 
	 * Para gerar nós usa-se classe Node.java: Node n = new Node(maxX, minX, maxY,
	 * minY, nextIP) As coordenadas geradas podem ser obtidas a partir de: x->
	 * n.x_coord; (int) y-> n.y_coord; (int) ip -> n.ip; (string)
	 * 
	 * Falta restrição de distância minima entre nós, mas isso vai dar bastante
	 * trabalho para verificar o que está nas vizinhanças portanto era bom testar
	 * primeiro sem essa restrição de forma a saber se funciona para o que queremos
	 * ou não. Se não funcionar eu faço isso.
	 * 
	 * A fazer: é necessário verificar os nós que estão na vizinhança para um dado
	 * raio e fazer join ao grupo desses nós; méthodo verifyNeig na classe
	 * Node.java;
	 */

	// Para DEBUG: debug= 1; NORMAL: debug = 0;
	public static int debug = 0;

	private static int roomL = 500;
	private static int roomW = 500;

	// Method for getting the maximum value
	public static int getMax(int[] inputArray) {
		int maxValue = inputArray[0];
		int maxIndex = 0;
		for (int i = 1; i < inputArray.length; i++) {
			if (inputArray[i] > maxValue) {
				maxValue = inputArray[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	public static void main(String[] args) throws Exception {

		// Criar rede
		// N - nodes number
		int N = 100;

		Node[] node = new Node[N];

		// Número de colunas
		int nCol = 10;
		// Número médio de pontos por coluna
		int meanPerCol = N / nCol;
		// Número mínimo de pontos por coluna
		int minPerCol = meanPerCol - meanPerCol / 2;
		// Número máximo de pontos por coluna
		int maxPerCol = meanPerCol + meanPerCol / 2;

		// Limites das colunas
		// Valor máximo dos limites das colunas
		int[] maxLim = new int[nCol];
		for (int k = 0; k < nCol; k++) {
			maxLim[k] = (k + 1) * roomW / nCol;
			if (debug == 1)
				System.out.println("Max Lim (" + k + "): " + maxLim[k]);
		}
		// Valor médio dos limites das colunas
		int[] meanLim = new int[nCol];
		meanLim[0] = maxLim[0] / 2;
		if (debug == 1)
			System.out.println("Mean Lim (" + 0 + "): " + meanLim[0]);
		for (int k = 1; k < nCol; k++) {
			meanLim[k] = maxLim[k - 1] + (maxLim[k] - maxLim[k - 1]) / 2;
			if (debug == 1)
				System.out.println("Mean Lim (" + k + "): " + meanLim[k]);
		}

		// Pontos em cada coluna
		int[] colPoints = new int[nCol];
		// Garantir que se gera n pontos
		int sum = 0;

		// Gerar o número de pontos em cada coluna
		for (int k = 0; k < (nCol - 1); k++) {
			Random col = new Random();
			colPoints[k] = col.nextInt(maxPerCol - minPerCol) + minPerCol;
			if (debug == 1)
				System.out.println(colPoints[k]);
			// Faz somatório de pontos para no final se verificar quantos falta de forma a
			// garantir os n pontos
			sum = sum + colPoints[k];
		}
		colPoints[nCol - 1] = (N - 1) - sum;
		if (colPoints[nCol - 1] < 0) {
			colPoints[nCol - 1] = nCol / 4;
			int maxIndex = getMax(colPoints);
			colPoints[maxIndex] = colPoints[maxIndex] + (N - 1) - sum - nCol / 4;
		}
		if (debug == 1)
			System.out.println("Final" + colPoints[nCol - 1]);

		// Gerar primeira coluna a ser gerada
		Random colFirst = new Random();
		int firstCol = colFirst.nextInt(nCol);
		int[] colGen = new int[10];

		// Criar vetor começado pela primeira coluna gerada aleatoriamente
		int j = 0;
		for (int k = firstCol; k < nCol; k++) {
			colGen[j] = colPoints[k];
			j++;
		}
		for (int k = 0; k < firstCol; k++) {
			colGen[j] = colPoints[k];
			j++;
		}

		if (debug == 1) {
			System.out.println("First Column:" + firstCol);
			for (int k = 0; k < nCol; k++) {
				System.out.println("k=" + k + ": " + colGen[k]);
			}
			for (int k = 0; k < nCol; k++) {
				System.out.println("k=" + k + ": " + colPoints[k]);
			}
		}
		int nSer = 1;

		// Gerar os nós
		node[0] = new Node(node, 0, 0, 0, 0, 0);
		for (int k = 0; k < nCol; k++) {
			int nCP = colGen[k];
			for (int g = 0; g < nCP; g++) {
				if (k == 0) {
					node[nSer] = new Node(node, maxLim[k], 0, roomW, 0, nSer);
				} else {
					node[nSer] = new Node(node, maxLim[k], maxLim[k - 1], roomW, 0, nSer);
				}
				nSer++;
			}

		}

		/* compute the Neighbours nodes */
		computeNeighbours(node);

		/* start receptors */
		startGlossySimulation(node, node[12]);

		/* start initiator */
		startGlossySimulationRetransmit("start", 10000, 5000, node[12]);

		if (debug == 1)
			System.out.println(node[99].x_coord + " " + node[99].y_coord);

		JFrame frame = new JFrame();

		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}

		});

		frame.setSize(600, 600);

		frame.getContentPane().add(new Circle(N, node));

		frame.setVisible(true);

	}

	public static void computeNeighbours(Node[] node) {
		double x = 0;
		double y = 0;
		double dystance = 0;

		for (int i = 0; i < node.length; i++) {
			for (int k = 0; k < node.length; k++) {
				if (i != k) {
					x = Math.pow(node[i].getCoordinateX() - node[k].getCoordinateX(), 2);
					y = Math.pow(node[i].getCoordinateY() - node[k].getCoordinateY(), 2);
					dystance = Math.sqrt(x + y);

					if (dystance <= 60) { // maximum distance allowed between nodes
						node[i].updateNeighboursNodes(node[k]);
						// System.out.println("distance between " + node[i].id + " and " + node[k].id +
						// " = " + dystance);
						node[i].updateListOfIPs(node[k].ip);
					}

					dystance = 0;

				}
			}
		}
	}

	public static void startGlossySimulation(Node[] node, Node nodeInitiator) throws Exception {
		try {
			for (int i = 0; i < nodeInitiator.getIpNeighboursNodes().size(); i++) {
				for (int k = 0; k < node.length; k++) {
					if (node[k].ip.equals(nodeInitiator.getIpNeighboursNodes().get(i)))
						node[k].start();
				}
			}

			// }
		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	public static void startGlossySimulationRetransmit(String actionTimer, int delayStart, int timerInterval,
			Node nodeTimer) throws Exception {
		ExcutionTimer glossyTimer = new ExcutionTimer();
		try {
			glossyTimer.activateTimer(actionTimer, delayStart, timerInterval, nodeTimer);

		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void checkIfThreadsAreAlive(Node[] node) throws Exception {
		for (int i = 0; i < node.length; i++) {
			if (node[i].isAlive())
				System.out.println("node" + i + " is alive");
			else
				System.out.println("node" + i + " NOT alive");

		}
	}
}

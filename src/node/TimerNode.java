package node;

import java.util.Timer;
import java.util.TimerTask;

public class TimerNode extends TimerTask {
	private boolean x1 = false;
	Timer timer;
	boolean glossyState;
	private Node nodeTimer;
	private int counter = 0;

	TimerNode(boolean glossyState, Node nodeTimer) {
		this.glossyState = glossyState;
		this.nodeTimer = nodeTimer;
	}

	@Override
	public void run() {

		System.out.println("Schedualed task");
		this.glossyState = !glossyState;
		if (this.nodeTimer.alive == 0) {
			this.nodeTimer.start();
			// this.nodeTimer.run();
			//System.out.println("Time's up!" + " counter = " + counter++);
			this.nodeTimer.alive = 1;
		}else if(this.nodeTimer.alive == 1) {
			this.nodeTimer.run();
			//System.out.println("Time's up!" + " counter = " + counter++);
			
		}
		System.out.println("Time's up!" + " counter = " + counter++);

	}

	public boolean getglossyState() {
		return this.glossyState;
	}
}

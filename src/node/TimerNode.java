package node;

import java.util.Timer;
import java.util.TimerTask;


public class TimerNode extends TimerTask {
	private boolean x1 = false;
	Timer timer;
	boolean glossyState;
	
	TimerNode(boolean glossyState){
		this.glossyState=glossyState;
	}
	
	@Override
	public void run() {
		System.out.println("Schedualed task");
		this.glossyState= !glossyState;
		System.out.println("Time's up!");

	}
	public boolean getglossyState() {
		return this.glossyState;
	}
}

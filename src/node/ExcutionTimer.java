package node;

import java.util.Timer;
import java.util.TimerTask;

public class ExcutionTimer {

	private TimerNode tasknew;
	private Timer timer;
	private boolean glossyState = true;
	private Node nodeTimer;

	// creating timer task, timer
	public boolean activateTimer(String actionTimer, int delayStart, int timerInterval, Node nodeTimer) {
			this.nodeTimer=nodeTimer;
		
		if (actionTimer.equals("stop")) {
			this.timer.cancel();
			this.timer.purge();

			System.out.println("timer stop");
			// scheduling the task
			return false;

		}

		this.timer = new Timer();
		this.tasknew = new TimerNode(this.glossyState, this.nodeTimer);

		if (actionTimer.equals("start")) {
			this.timer.schedule(tasknew, delayStart, timerInterval); // tasknew, delayStart, timerInterval

			System.out.println("timer s");
			return true;
		}
		return false;
	}

	public boolean getglossyState() {
		this.glossyState=this.tasknew.getglossyState();
		return this.glossyState;
	}
}

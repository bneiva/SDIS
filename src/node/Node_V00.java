package node;

public class Node_V00 {
	
	 private static ExcutionTimer glossyTimer = new ExcutionTimer();
	 
	 public void schedualeNextPhaseGlossy() {
		 //glossyTimer.activateTimer("start",1000 ,1000); 
		 while(true) {
				System.out.println(glossyTimer.getglossyState());
			 }
		 
	 }

	
	public static  void main(String[] args) throws Exception {
		
		//MulticastReceiver Rx =  new MulticastReceiver(12345,"224.1.1.12"); 
		//Packet packet = new Packet();
		 
		//Rx.start();
		//System.out.println("packet bytes  " +  Rx.packetMessageBytes());
		MulticastReceiver[] Rx = new MulticastReceiver[5];
		for (int i = 0; i < 5; i++) {
			Rx[i] = new MulticastReceiver(12345,"224.1.1.1");
			Rx[i].start();
			Rx[i].packetMessageBytes();
			System.out.println("packet bytes " + Rx[i].packetMessageBytes() + "waiting with this IP");

		}
		
	}
}

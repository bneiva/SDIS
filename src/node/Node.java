package node;

public class Node {
	
	 private static ExcutionTimer glossyTimer = new ExcutionTimer();
	 
	 public void schedualeNextPhaseGlossy() {
		 glossyTimer.activateTimer("start",1000 ,1000); 
		 while(true) {
				System.out.println(glossyTimer.getglossyState());
			 }
		 
	 }

	
	public static  void main(String[] args) throws Exception {
		
		MulticastReceiver Rx =  new MulticastReceiver(12345,"230.1.1.1"); 
		Packet packet = new Packet();
		 
		Rx.start();
		System.out.println("packet bytes  " +  Rx.packetMessageBytes());
		 
		
		 //MulticastReceiver Rx1 =  new MulticastReceiver(12345,"230.1.1.2"); 
		 //Rx1.start();
		//MulticastPublisher Tx = new MulticastPublisher(12345,"230.1.1.1");
		
	}
}

package node;

public class Node2 {
	
	public static void main(String[] args) throws Exception {
		//MulticastReceiver Rx =  new MulticastReceiver(12345,"230.1.1.1"); 
		 //Rx.start();
		 //MulticastReceiver Rx1 =  new MulticastReceiver(12345,"230.1.1.2"); 
		 //Rx1.start();
		MulticastPublisher Tx = new MulticastPublisher(12345,"230.1.1.1");
		Tx.sendPacketMessage(" Mesage from Node2".getBytes());
		Tx.start();
	}

}

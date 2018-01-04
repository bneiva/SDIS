package node;

public class Node2 {
	
	public static void main(String[] args) throws Exception {
		//MulticastReceiver Rx =  new MulticastReceiver(12345,"230.1.1.1"); 
		 //Rx.start();
		 //MulticastReceiver Rx1 =  new MulticastReceiver(12345,"230.1.1.2"); 
		 //Rx1.start();
		MulticastPublisher Tx = new MulticastPublisher(12345,"230.1.1.1");
		Packet packet = new Packet();
		
		Tx.sendPacketMessage(packet.encodeData(10, " Mesage from Node2"));
		Tx.start();
	}

}

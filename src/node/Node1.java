package node;

public class Node1 {
	
	public static void main(String[] args) throws Exception {
		//MulticastReceiver Rx =  new MulticastReceiver(12345,"230.1.1.1"); 
		MulticastPublisher Tx = new MulticastPublisher(9999,"224.1.1.1");
		Tx.sendPacketMessage(" Mesage from Node1".getBytes());
		Tx.start();
		
	}

}

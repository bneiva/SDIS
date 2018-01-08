package node;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

public class MulticastReceiver extends Thread {

	private int mcPort;
	private String mcIPStr;
	private byte[] packetMessage = new byte[1024];
	private Node node;
	private ArrayList<String> message = new ArrayList<String>();
	MulticastSocket mcSocket = null;
	InetAddress mcIPAddress = null;

	MulticastReceiver(int port, String IPGroup, Node node) throws Exception {
		this.mcPort = port;
		this.mcIPStr = IPGroup;
		this.node = node;

	}

	public byte[] packetMessageBytes() {
		return null;// this.packetMessageBytes();

	}

	public void run() {
		//System.out.println("Running " + this.mcIPStr);
		try {
			receiver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void receiver() throws Exception {

		mcIPAddress = InetAddress.getByName(mcIPStr);
		mcSocket = new MulticastSocket(mcPort);
		//System.out.println("Multicast Receiver running at:  " + mcIPAddress.getHostAddress());

		mcSocket.joinGroup(mcIPAddress);

		 //mcSocket.setSoTimeout(10000);
		DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

		// System.out.println("Waiting for a multicast message..." +
		// mcIPAddress.getHostName());
		mcSocket.receive(packet);
		// System.out.println("Start Reception
		// message.........................................................");

		String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
		this.packetMessage = msg.getBytes();
		// this.node.message = msg;
		this.message.add(msg); // menssagem
		this.message.add(this.mcIPStr); // Ip de onde recebeu
		this.message.add(Integer.toString(this.mcPort)); // porta de onde
															// recebeu
		this.node.messageReceived = this.message;
		System.out.println("[Multicast  Receiver] Received:" + msg + " from IP: " + mcIPAddress.getHostName());

		mcSocket.leaveGroup(mcIPAddress);
		mcSocket.close();
	}
}
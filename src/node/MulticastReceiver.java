package node;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver extends Thread {

	private int mcPort;
	private String mcIPStr;
	private byte[] packetMessage = new byte[1024];

	MulticastReceiver(int port, String IPGroup) throws Exception {
		this.mcPort = port;
		this.mcIPStr = IPGroup;

	}

	public byte[] packetMessageBytes() {
		return null;// this.packetMessageBytes();

	}

	public void run() {
		System.out.println("Running " + this.mcIPStr);
		try {
			receiver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void receiver() throws Exception {
		MulticastSocket mcSocket = null;
		InetAddress mcIPAddress = null;

		mcIPAddress = InetAddress.getByName(mcIPStr);
		mcSocket = new MulticastSocket(mcPort);
		System.out.println("Multicast Receiver running at:  " + mcIPAddress.getHostAddress());

		mcSocket.joinGroup(mcIPAddress);

		DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

		System.out.println("Waiting for a  multicast message..." + mcIPAddress.getHostName());
		mcSocket.receive(packet);
		System.out.println("Start Reception message.........................................................");
		String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
		this.packetMessage = msg.getBytes();
		System.out.println("[Multicast  Receiver] Received:" + msg + " from IP: " + mcIPAddress.getHostName());

		System.out.println("End Reception message.........................................................");
		mcSocket.leaveGroup(mcIPAddress);
		mcSocket.close();
	}
}
package node;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastPublisher extends Thread {

	private int mcPort;
	private String mcIPStr;
	private byte[] packetMessage = new byte[1024];

	MulticastPublisher(int port, String IPGroup) throws Exception {
		this.mcPort = port;
		this.mcIPStr = IPGroup;

	}

	public void sendPacketMessage(byte[] packetMessage) {
		this.packetMessage = packetMessage;

	}

	public void run() {
		try {
			sender();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sender() throws Exception {

		DatagramSocket udpSocket = new DatagramSocket();

		InetAddress mcIPAddress = InetAddress.getByName(mcIPStr);

		// byte[] msg = "Hello".getBytes();
		DatagramPacket packet = new DatagramPacket(packetMessage, packetMessage.length);

		packet.setAddress(mcIPAddress);
		packet.setPort(mcPort);

		udpSocket.send(packet);
		String speakTome = new String(packet.getData(), packet.getOffset(), packet.getLength());
		System.out.println("Sent a  multicast message...");

		udpSocket.close();
	}
}
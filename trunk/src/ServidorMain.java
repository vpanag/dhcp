import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class ServidorMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		DatagramSocket datagramSocket = new DatagramSocket();

		byte[] buffer = "0123456789".getBytes();
		InetAddress receiverAddress = InetAddress.getLocalHost();

		DatagramPacket packet = new DatagramPacket(
		        buffer, buffer.length, receiverAddress, 80);
		datagramSocket.send(packet);

	}
}

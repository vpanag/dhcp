/**
 * Clase cliente
 * @author Diego Fernandez Vargas <df@fevarco.com>
 *
 */


import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;


public class Cliente {

	
	
	public Cliente() throws SocketException
	{
		DatagramSocket s = new DatagramSocket(null);
		s.bind(new InetSocketAddress(8888));
	}
}

package comm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Clase Servidor usando puerto 5555 para pruebas
 * @author Diego
 *
 */
public class Servidor {

	private DatagramSocket socketServidor;

	public Servidor() throws IOException
	{
		byte datos[] = new byte[350];
		socketServidor = new DatagramSocket(5555);
		
		while(true)
		{
			DatagramPacket paquete = new DatagramPacket(datos, datos.length);
			socketServidor.receive(paquete);
			String msjRecibido = new String(datos);
			System.out.println(msjRecibido);
		}
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Servidor nServer = new Servidor();
		
		
	}
}
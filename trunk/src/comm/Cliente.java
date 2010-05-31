package comm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Cliente {

	/**
	 * Puerto 68
	 */
	private DatagramSocket socketCliente;
	
	private String dirBroadcast;
	
	public Cliente() throws SocketException
	{
		socketCliente = new DatagramSocket(68);
		dirBroadcast = "255.255.255.255";
	}
	
	public void enviarPaquete() throws IOException
	{
		String msj = "este es un server dhcp si ves esto funciona!!!";
		byte datos[] = msj.getBytes();
		DatagramPacket paquete = new DatagramPacket(datos, datos.length, InetAddress.getByName(dirBroadcast), 5555);
		socketCliente.send(paquete);
	}
	
	private void crearPaquete() 
	{
		
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Cliente nCliente = new Cliente();
		nCliente.enviarPaquete();

	}
}
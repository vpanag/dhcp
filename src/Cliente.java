/**
 * Clase cliente
 * @author Diego Fernandez Vargas <df@fevarco.com>
 *
 */


import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import comm.Mensaje;


public class Cliente {

	private Mensaje mensajeRecibido;
	
	public Cliente() throws SocketException
	{
		DatagramSocket s = new DatagramSocket(null);
		s.bind(new InetSocketAddress(8888));
	}
	
	/**
	 * Solicita direccion ip
	 */
	private void solicitar() 
	{
		//la direccion llega en un mensaje clase Mensaje attrib mnesajeRecibido
		
	}
	
	/**
	 * Valida direccion ip (valida que tenga todos los datos
	 */
	private void valida()
	{
		
	}
	
	/**
	 * Confiura la direccion en el pc (LINUX)
	 */
	private void configuraDireccion()
	{
		//coje info del mensaje
		//establece en el cliente el gateware, dns, ip y mask (WINS)
	}
	
	public static void main(String[] args) {
		try {
			Cliente xCliente = new Cliente();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
}

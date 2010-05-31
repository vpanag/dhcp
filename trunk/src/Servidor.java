/**
 * Clase servidor
 * @author Diego Fernandez Vargas <df@fevarco.com>
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

	
	public Servidor() {
		try {
			DatagramSocket dgs = new DatagramSocket();
			
		} 
                catch (SocketException e)
                {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Envia un mensaje con toda la inf
	 */
	public void enviaMensaje() 
	{
		
	}
	
	/**
	 * 
	 */
	public void recibirMensaje()
	{
		
	}
	
	/**
	 * Carga la informacion de config de un archivo de texto
	 */
	private void cargaInfo()
	{
    

		//cada ves que cargo hay que llamar a writelog?
	}
	
	/**
	 * Escribe en el log la inf
	 */
	private void writeLog()
	{
		
	}
}

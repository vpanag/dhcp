package redes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.HashMap;

public class DHCP implements Observer {

	protected static byte[] ipInicial;
	protected static byte[] ipFinal;
	protected static byte[] mascara;
	protected static byte[] servidor;
	protected static byte[] gateway;
	protected static byte[] dns;
	protected static byte[] tiempo_arriendo;
	protected static byte[] tiempo_renovacion;

	private static DHCP instancia = null;

	private static boolean doExit = false;

	public static DHCP getInstance() {
		if (instancia == null)
			instancia = new DHCP();

		return instancia;
	}

	public static byte[] getIpInicial() {
		return ipInicial;
	}

	public static byte[] getIpFinak() {
		return ipFinal;
	}

	public static byte[] getLeaseTime() {
		return tiempo_arriendo;
	}

	/**
	 * @return mascara
	 */
	public static byte[] getMascara() {
		return mascara;
	}
	
	/**
	 * @return gateway
	 */
	public static byte[] getGateway() {
		return gateway;
	}

	/**
	 * @return tiempo para renovar el lease
	 */
	public static byte[] getRenewalTime() {
		//return intAByte(100000);
		return tiempo_renovacion;
	}

	/**
	 * @return Direccion IP Servidor
	 */
	public static byte[] getServidor() {
		return servidor;
	}

	/**
	 * @return Direccion DNS
	 */
	public static byte[] getDNS() {
		return dns;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getInstance();
		panel2 = new UI(instancia);
		serv = new Servidor();
		serv.addObserver(getInstance());
		serv.inicie();
	}

	/**
	 * 
	 */
	private static Servidor serv = null;

	/**
	 * 
	 */
	private static UI panel2 = null;

	/**
	 * Constructor
	 */
	public DHCP() {
		System.out.println("");
	}

	/**
	 * Sale
	 */
	protected void salir() {
		serv.aborte();
		serv.espereHastaFinal(1000);

		System.exit(0);
	}

	protected boolean doExit() {
		return doExit;
	}

	/**
	 * Muestra la tabla de ips con macs en el GUI
	 */
	protected void showTabla() {
		HashMap<String, Object> list = serv.getTable();
		
		update(null, "|******** Tabla de arrendamiento de IPS ********|");
		for (String mac : list.keySet()) {
			update(null, mac + " ---- " + list.get(mac));
		}
		
		if (list.size() == 0) {
			update(null, "Tabla Vacia");
		}
		
		update(null, "|******************************************|");

		return;
	}
	
	/**
	 * Mostrar configuracion en el gui GUI
	 */
	protected void mostrarConf() {
		update(null, "|********      CONFIGURACION   *****************|");
		update(null, "Rango         : " + byteAIp(ipInicial) + " - " + byteAIp(ipFinal) );
		update(null, "Mascara       : " + byteAIp(mascara));
		update(null, "Gatway        : " + byteAIp(gateway));
		update(null, "Servidor DHCP : " + byteAIp(servidor));
		update(null, "DNS           : " + byteAIp(dns) );
		update(null, "|**********************************************|");
	}
	
	/**
	 * Limpia toda la tabla de datos de ips (HASHtable)
	 */
	protected void limpiaDatos() 
	{
		serv.liberarTodos();
	}

	@Override
	public void update(Observable o, Object arg) {
		synchronized (System.out) {
			if (arg instanceof Throwable) {
				System.out.println("{" + currentDateTime() + "} "
						+ ((Throwable) arg).getLocalizedMessage());
			} else {
				System.out.println("{" + currentDateTime() + "} "
						+ arg.toString());
			}
		}

		if (panel2 != null) {
			panel2.escribeMensaje("{" + currentDateTime() + "} " + arg.toString());
		}
	}

	// ---------------------------------------------//
	// Metodos estaticos de trasnformar valores		//
	// ---------------------------------------------//
	public static int byteAInt(byte[] buffer) {
		int x = (0xFF & buffer[0]) << 24;
		x |= (0xFF & buffer[1]) << 16;
		x |= (0xFF & buffer[2]) << 8;
		x |= (0xFF & buffer[3]);

		return x;
	}

	public static String byteAIp(byte[] b) {
		StringBuilder temp = new StringBuilder();

		for (int i = 0; i < 4; i++) {
			if (i > 0)
			{
				temp.append(".");
			}
			String n = String.valueOf(0xFF & b[i]);
			temp.append(n);		
		}

		return temp.toString();
	}

	public static String byteAMac(byte[] b) {
		StringBuilder temp = new StringBuilder();

		for (int i = 0; i < 6; i++) {
			if (i > 0)
				temp.append(":");
			String n = Integer.toHexString(0xFF & b[i]);
			if (n.length() == 1)
				n = "0" + n;

			temp.append(n);
		}

		return temp.toString();
	}

	public static byte[] intAByte(int val) {
		byte[] buffer = new byte[4];
		buffer[0] = (byte) (val >>> 24);
		buffer[1] = (byte) (val >>> 16);
		buffer[2] = (byte) (val >>> 8);
		buffer[3] = (byte) val;

		return buffer;
	}

	public static byte[] ipAByte(String ip) {
		String[] partes = ip.split("\\.");
		byte[] back = new byte[4];

		if (partes.length != 4)
		{
			return back;
		}
		
		for (int i = 0; i < 4; i++) {
			int valor = Integer.valueOf(partes[i]);
			if (valor < 0 || valor > 255)
			{
				return new byte[4];
			}
			valor -= 256;
			back[i] = (byte) valor;
		}

		return back;
	}

	public static int[] byteAaIntA(byte[] data) {
		int[] temp = new int[data.length];

		for (int i = 0; i < data.length; i++)
			temp[i] = 0xFF & data[i];

		return temp;
	}

	public static void close(Closeable c) {
		if (c == null) {
			return;
		}

		try {
			c.close();
		} catch (IOException e) {
			DHCP.getInstance().update(null, e);
		}
	}
	
	/**
	 * @return dia hora
	 */
	public static String currentDateTime() {
		String FORMATO_HORA = "HH:mm:ss";
		String FORMATO_FECHA = "MM-dd";
		SimpleDateFormat TIME_FORMATER = new SimpleDateFormat(FORMATO_HORA);
		SimpleDateFormat DATE_FORMATER = new SimpleDateFormat(FORMATO_FECHA);			
		
		return DATE_FORMATER.format(new Date()) + " " +	TIME_FORMATER.format(new Date());
	}
}

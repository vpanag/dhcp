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

public class Application implements Observer {

	protected static byte[] ipInicial;
	protected static byte[] ipFinal;
	protected static byte[] mascara;
	protected static byte[] servidor;
	protected static byte[] dns;
	protected static byte[] lease_time;
	protected static byte[] renewal_time;

	private static Application instancia = null;

	private static boolean doExit = false;

	public static Application getInstance() {
		if (instancia == null)
			instancia = new Application();

		return instancia;
	}

	public static byte[] getIpInicial() {
		return ipInicial;
	}

	public static byte[] getIpFinak() {
		return ipFinal;
	}

	public static byte[] getLeaseTime() {
		return lease_time;
	}

	/**
	 * @return mascara
	 */
	public static byte[] getMascara() {
		return mascara;
	}

	/**
	 * @return tiempo para renovar el lease
	 */
	public static byte[] getRenewalTime() {
		return intAByte(100000);
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
	public Application() {
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
	 * Lee comandos
	 */
	private void readConsole() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;

		try {
			while (br != null && (line = br.readLine()) != null && !doExit) {
				//handleCommand(line);
			}
		} catch (IOException e) {
			update(null, e);
		} finally {
			close(br);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		synchronized (System.out) {
			if (arg instanceof Throwable) {
				System.out.println("[" + currentDateTime() + "] "
						+ ((Throwable) arg).getLocalizedMessage());
			} else {
				System.out.println("[" + currentDateTime() + "] "
						+ arg.toString());
			}
		}

		if (panel2 != null) {
			panel2.writeMessage("[" + currentDateTime() + "] " + arg.toString());
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
			Application.getInstance().update(null, e);
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

/**
 * Clase Mensaje 
 * 
 * @author Diego Andres Fernandez Vargas <diegofv@gmail.com>
 * @todo definar el tipo y tamano de options ya que es variable
 */

public class Mensaje {
	
	private byte op;
	private byte htype;
	private byte hlen;
	private byte hops;
	private byte[] secs = new byte[2];
	private byte[] flags = new byte[2];
	private byte[] ciaddr = new byte[4];
	private byte[] yiaddr = new byte[4];
	private byte[] siaddr = new byte[4];
	private byte[] giaddr = new byte[4];
	private byte[] chaddr = new byte[16];
	private byte[] sname = new byte[64];
	private byte[] file = new byte[128];
	private String options;
	
	

}

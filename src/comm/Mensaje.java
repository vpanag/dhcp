package comm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;

/**
 * Clase Mensaje Tam total = 232 bytes
 * 
 * @author Diego Andres Fernandez Vargas <diegofv@gmail.com>
 */

public class Mensaje {

	private byte[] msjTest = new byte[16];
	private byte op;
	private byte htype;
	private byte hlen;
	private byte hops;
	private int xid;
	private short secs;
	private short flags;
	private byte[] ciaddr = new byte[4];
	private byte[] yiaddr = new byte[4];
	private byte[] siaddr = new byte[4];
	private byte[] giaddr = new byte[4];
	private byte[] chaddr = new byte[16];
	private byte[] sname = new byte[64];
	private byte[] file = new byte[128];

	private InetAddress ipDesitno;

	public static final int DISCOVER = 1;
	public static final int OFFER = 2;
	public static final int REQUEST = 3;
	public static final int DECLINE = 4;
	public static final int ACK = 5;
	public static final int NAK = 6;
	public static final int RELEASE = 7;
	public static final int INFORM = 8;

	public Mensaje() {

	}

	public Mensaje(DataInputStream inStream) {
		// Initialize();
		try {
			op = inStream.readByte();
			htype = inStream.readByte();
			hlen = inStream.readByte();
			hops = inStream.readByte();
			xid = inStream.readInt();
			secs = inStream.readShort();
			flags = inStream.readShort();
			inStream.readFully(ciaddr, 0, 4);
			inStream.readFully(yiaddr, 0, 4);
			inStream.readFully(siaddr, 0, 4);
			inStream.readFully(giaddr, 0, 4);
			inStream.readFully(chaddr, 0, 16);
			inStream.readFully(sname, 0, 64);
			inStream.readFully(file, 0, 128);
			byte[] options = new byte[312];
			inStream.readFully(options, 0, 312);
		} catch (IOException e) {
			System.err.println(e);
		} // end catch

	}

	/**
	 * Exporta el objeto a un arreglo de bytes
	 * 
	 * @return arreglo de bytes con todo el msj
	 */
	public synchronized byte[] exportarMsj() {
		ByteArrayOutputStream outBStream = new ByteArrayOutputStream();
		DataOutputStream outStream = new DataOutputStream(outBStream);

		try {
			outStream.writeByte(op);
			outStream.writeByte(htype);
			outStream.writeByte(hlen);
			outStream.writeByte(hops);
			outStream.writeInt(xid);
			outStream.writeShort(secs);
			outStream.writeShort(flags);
			outStream.write(ciaddr, 0, 4);
			outStream.write(yiaddr, 0, 4);
			outStream.write(siaddr, 0, 4);
			outStream.write(giaddr, 0, 4);
			outStream.write(chaddr, 0, 16);
			outStream.write(sname, 0, 64);
			outStream.write(file, 0, 128);
		} catch (IOException e) {
			System.err.println(e);
		} // end catch

		// extract the byte array from the Stream
		byte data[] = outBStream.toByteArray();

		return data;
	}

	/**
	 * Importa arreglo de butes
	 * @param byteArray
	 * @return objeto mensaje armado
	 */
	public synchronized Mensaje internalize(byte[] byteArray) {
		ByteArrayInputStream inBStream = new ByteArrayInputStream(byteArray, 0,
				byteArray.length);
		DataInputStream inStream = new DataInputStream(inBStream);

		try {
			op = inStream.readByte();
			htype = inStream.readByte();
			hlen = inStream.readByte();
			hops = inStream.readByte();
			xid = inStream.readInt();
			secs = inStream.readShort();
			flags = inStream.readShort();
			inStream.readFully(ciaddr, 0, 4);
			inStream.readFully(yiaddr, 0, 4);
			inStream.readFully(siaddr, 0, 4);
			inStream.readFully(giaddr, 0, 4);
			inStream.readFully(chaddr, 0, 16);
			inStream.readFully(sname, 0, 64);
			inStream.readFully(file, 0, 128);
		} catch (IOException e) {
			System.err.println(e);
		} // end catch

		return this;
	}

	/**
	 * @return the msjTest
	 */
	public byte[] getMsjTest() {
		return msjTest;
	}

	/**
	 * @param msjTest the msjTest to set
	 */
	public void setMsjTest(byte[] msjTest) {
		this.msjTest = msjTest;
	}

	/**
	 * @return the op
	 */
	public byte getOp() {
		return op;
	}

	/**
	 * @param op the op to set
	 */
	public void setOp(byte op) {
		this.op = op;
	}

	/**
	 * @return the htype
	 */
	public byte getHtype() {
		return htype;
	}

	/**
	 * @param htype the htype to set
	 */
	public void setHtype(byte htype) {
		this.htype = htype;
	}

	/**
	 * @return the hlen
	 */
	public byte getHlen() {
		return hlen;
	}

	/**
	 * @param hlen the hlen to set
	 */
	public void setHlen(byte hlen) {
		this.hlen = hlen;
	}

	/**
	 * @return the hops
	 */
	public byte getHops() {
		return hops;
	}

	/**
	 * @param hops the hops to set
	 */
	public void setHops(byte hops) {
		this.hops = hops;
	}

	/**
	 * @return the xid
	 */
	public int getXid() {
		return xid;
	}

	/**
	 * @param xid the xid to set
	 */
	public void setXid(int xid) {
		this.xid = xid;
	}

	/**
	 * @return the secs
	 */
	public short getSecs() {
		return secs;
	}

	/**
	 * @param secs the secs to set
	 */
	public void setSecs(short secs) {
		this.secs = secs;
	}

	/**
	 * @return the flags
	 */
	public short getFlags() {
		return flags;
	}

	/**
	 * @param flags the flags to set
	 */
	public void setFlags(short flags) {
		this.flags = flags;
	}

	/**
	 * @return the ciaddr
	 */
	public byte[] getCiaddr() {
		return ciaddr;
	}

	/**
	 * @param ciaddr the ciaddr to set
	 */
	public void setCiaddr(byte[] ciaddr) {
		this.ciaddr = ciaddr;
	}

	/**
	 * @return the yiaddr
	 */
	public byte[] getYiaddr() {
		return yiaddr;
	}

	/**
	 * @param yiaddr the yiaddr to set
	 */
	public void setYiaddr(byte[] yiaddr) {
		this.yiaddr = yiaddr;
	}

	/**
	 * @return the siaddr
	 */
	public byte[] getSiaddr() {
		return siaddr;
	}

	/**
	 * @param siaddr the siaddr to set
	 */
	public void setSiaddr(byte[] siaddr) {
		this.siaddr = siaddr;
	}

	/**
	 * @return the giaddr
	 */
	public byte[] getGiaddr() {
		return giaddr;
	}

	/**
	 * @param giaddr the giaddr to set
	 */
	public void setGiaddr(byte[] giaddr) {
		this.giaddr = giaddr;
	}

	/**
	 * @return the chaddr
	 */
	public byte[] getChaddr() {
		return chaddr;
	}

	/**
	 * @param chaddr the chaddr to set
	 */
	public void setChaddr(byte[] chaddr) {
		this.chaddr = chaddr;
	}

	/**
	 * @return the sname
	 */
	public byte[] getSname() {
		return sname;
	}

	/**
	 * @param sname the sname to set
	 */
	public void setSname(byte[] sname) {
		this.sname = sname;
	}

	/**
	 * @return the file
	 */
	public byte[] getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(byte[] file) {
		this.file = file;
	}

	/**
	 * @return the ipDesitno
	 */
	public InetAddress getIpDesitno() {
		return ipDesitno;
	}

	/**
	 * @param ipDesitno the ipDesitno to set
	 */
	public void setIpDesitno(InetAddress ipDesitno) {
		this.ipDesitno = ipDesitno;
	}

}
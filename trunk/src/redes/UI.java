package redes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class UI implements WindowListener, ActionListener {
	private JFrame window = new JFrame();
	
	//PANELES
	private JPanel pnl_botones = new JPanel(new GridLayout(1,5));
	private JPanel pnl_text = new JPanel(new BorderLayout());
	private JPanel pnl_app = new JPanel(new BorderLayout());
	//TEXTPANE
	private JTextPane gui_log = new JTextPane();
	//BOTONES
	private JButton btn_tabla = new JButton("TABLA");
	private JButton btn_limpiar = new JButton("LIMPIAR");
	
	private DHCP parent = null;
	
	public UI(DHCP parent) {
		this.parent = parent;
		
		// Listeners
		btn_limpiar.addActionListener(this);
		btn_tabla.addActionListener(this);
		window.addWindowListener(this);
		
		// FRAME
		window.setSize(800, 640);
		window.setTitle("SERVIDOR DHCP - Proyecto Redes");
		window.setLayout(new BorderLayout());
		
		// Textpane
		gui_log.setText("{"  + currentDateTime() + "}");
		gui_log.setEditable(false);
		gui_log.setFocusable(false);
		
		// Adds Paneles
		pnl_app.add(pnl_botones, BorderLayout.NORTH);
		pnl_app.add(pnl_text, BorderLayout.CENTER);
		
		pnl_text.add(gui_log, BorderLayout.CENTER);	
				
		pnl_botones.add(btn_limpiar);
		pnl_botones.add(btn_tabla);
		
		
		// Adds
		window.add(new JScrollPane(gui_log), BorderLayout.CENTER);
		window.add(pnl_botones, BorderLayout.NORTH);
		//window.add(pnl_text, BorderLayout.CENTER);
		
		window.setVisible(true);
		
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		window.setVisible(false);
		parent.salir();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource() == btn_tabla)	
		{
			parent.showTabla();
		}
		else if(e.getSource() == btn_limpiar)	
		{
			parent.limpiaDatos();
		}
		
	}
	
	
	protected void escribeMensaje(String msg) {
		synchronized (gui_log) {
			String newText = gui_log.getText() + "\n" + msg;
			gui_log.setText(newText);
			gui_log.setCaretPosition(newText.length());
		}
	}
	
	protected void clearLog() {
		synchronized (gui_log) {
			gui_log.setText("");
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

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

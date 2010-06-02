package redes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
	private JTextPane textpane_log = new JTextPane();
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
		window.setSize(900, 800);
		window.setTitle("SERVIDOR DHCP - Proyecto Redes");
		window.setLayout(new BorderLayout());
		
		// Textpane
		textpane_log.setText("{"  + currentDateTime() + "}");
		Font font = new Font("Serif", Font.PLAIN, 18);
		textpane_log.setFont(font);
		textpane_log.setEditable(false);
		textpane_log.setFocusable(false);
		
		// Adds Paneles
		pnl_app.add(pnl_botones, BorderLayout.NORTH);
		pnl_app.add(pnl_text, BorderLayout.CENTER);
		
		pnl_text.add(textpane_log, BorderLayout.CENTER);	
				
		pnl_botones.add(btn_limpiar);
		pnl_botones.add(btn_tabla);
		
		
		// Adds
		window.add(new JScrollPane(textpane_log), BorderLayout.CENTER);
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
		synchronized (textpane_log) {
			String newText = textpane_log.getText() + "\n" + msg;
			textpane_log.setText(newText);
			textpane_log.setCaretPosition(newText.length());
		}
	}
	
	protected void clearLog() {
		synchronized (textpane_log) {
			textpane_log.setText("");
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

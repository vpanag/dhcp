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
	private JPanel botones_pnl = new JPanel(new GridLayout(1,5));
	private JPanel text_pnl = new JPanel(new BorderLayout());
	private JPanel app_pnl = new JPanel(new BorderLayout());
	//TEXTPANE
	private JTextPane log_textpane = new JTextPane();
	//BOTONES
	private JButton tabla_btn = new JButton("Tabla");
	private JButton limpiar_btn = new JButton("Limpiar");
	private JButton conf_btn = new JButton("Configuracion");
	
	private DHCP parent = null;
	
	public UI(DHCP parent) {
		this.parent = parent;
		
		// Listeners
		limpiar_btn.addActionListener(this);
		tabla_btn.addActionListener(this);
		conf_btn.addActionListener(this);
		window.addWindowListener(this);
		
		// FRAME
		window.setSize(900, 800);
		window.setTitle("SERVIDOR DHCP - Proyecto Redes");
		window.setLayout(new BorderLayout());
		
		// Textpane
		log_textpane.setText("{"  + currentDateTime() + "}");
		Font font = new Font("Serif", Font.PLAIN, 18);
		log_textpane.setFont(font);
		log_textpane.setEditable(false);
		
		
		// Adds Paneles
		app_pnl.add(botones_pnl, BorderLayout.NORTH);
		app_pnl.add(text_pnl, BorderLayout.CENTER);
		
		text_pnl.add(log_textpane, BorderLayout.CENTER);	
				
		botones_pnl.add(limpiar_btn);
		botones_pnl.add(tabla_btn);
		botones_pnl.add(conf_btn);
		
		
		// Adds
		window.add(new JScrollPane(log_textpane), BorderLayout.CENTER);
		window.add(botones_pnl, BorderLayout.NORTH);
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
		if(e.getSource() == tabla_btn)	
		{
			parent.showTabla();
		}
		else if(e.getSource() == limpiar_btn)	
		{
			parent.limpiaDatos();
		}
		else if(e.getSource() == conf_btn)	
		{
			parent.mostrarConf();
		}
	}
	
	
	protected void escribeMensaje(String msg) {
		synchronized (log_textpane) {
			String newText = log_textpane.getText() + "\n" + msg;
			log_textpane.setText(newText);
			log_textpane.setCaretPosition(newText.length());
		}
	}
	
	protected void clearLog() {
		synchronized (log_textpane) {
			log_textpane.setText("");
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

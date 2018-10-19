package frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.TextField;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import backend.BD;

import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.apache.commons.lang3.text.WordUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CambiarContraseña {
	
	public static String Usuario = "";
	public static String Cargo = "";
	public static String Turno = "";
	private static JPasswordField viejacontraseña;
	private static JPasswordField nuevacontraseña;
	private static JPasswordField repetirnueva;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaCambiarContraseña = new JFrame("Hotel Mi Reina - Recuperar Contraseña");
		PantallaCambiarContraseña.setTitle("Hotel Mi Reina - Cambiar contrase\u00F1a\r\n");
		PantallaCambiarContraseña.getContentPane().setBackground(Color.WHITE);
		PantallaCambiarContraseña.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(CambiarContraseña.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 500, 596);
		PantallaCambiarContraseña.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hotel Mi Reina C.A");
		lblNewLabel.setFont(new Font("Script MT Bold", Font.BOLD, 24));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 204, 482, 44);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Vieja contrase\u00F1a");
		lblNewLabel_1.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(6, 260, 482, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(CambiarContraseña.class.getResource("/imagenes/HotelIcon.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(114, 6, 267, 190);
		panel.add(lblNewLabel_2);
		
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFocusPainted(false);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaCambiarContraseña.dispose();
				PantallaPrincipal.main(args);
			}
		});
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setBackground(new Color(0, 128, 128));
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBounds(202, 538, 102, 28);
		panel.add(btnSalir);
		
		JLabel lblNuevaContrasea = new JLabel("Nueva contrase\u00F1a");
		lblNuevaContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevaContrasea.setForeground(Color.WHITE);
		lblNuevaContrasea.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblNuevaContrasea.setBounds(6, 336, 482, 23);
		panel.add(lblNuevaContrasea);
		
		JLabel lblRepetirNuevaContrasea = new JLabel("Repetir nueva contrase\u00F1a");
		lblRepetirNuevaContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepetirNuevaContrasea.setForeground(Color.WHITE);
		lblRepetirNuevaContrasea.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblRepetirNuevaContrasea.setBounds(6, 412, 482, 23);
		panel.add(lblRepetirNuevaContrasea);
		
		viejacontraseña = new JPasswordField();
		viejacontraseña.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(String.valueOf(viejacontraseña.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir su antigua contraseña!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(viejacontraseña.getPassword().length < 10){
					JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					viejacontraseña.requestFocus();
					viejacontraseña.selectAll();
					return;
				}
				nuevacontraseña.requestFocus();
				nuevacontraseña.selectAll();
			}
		});
		viejacontraseña.setForeground(Color.WHITE);
		viejacontraseña.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		viejacontraseña.setBackground(new Color(0, 0, 0, 0));
		viejacontraseña.setEchoChar('x');
		viejacontraseña.setHorizontalAlignment(SwingConstants.CENTER);
		viejacontraseña.setBounds(163, 296, 161, 28);
		panel.add(viejacontraseña);
		
		nuevacontraseña = new JPasswordField();
		nuevacontraseña.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(nuevacontraseña.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir una nueva contraseña!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(nuevacontraseña.getPassword().length < 10){
					JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontraseña.requestFocus();
					nuevacontraseña.selectAll();
					return;
				}
				if(String.valueOf(nuevacontraseña.getPassword()).equals(viejacontraseña.getPassword())){
					JOptionPane.showMessageDialog(null, "Error: La contraseña vieja y nueva no pueden ser identicas!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontraseña.requestFocus();
					nuevacontraseña.selectAll();
					return;
				}
				if(!String.valueOf(nuevacontraseña.getPassword()).matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+$")){
					JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener números y letras!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontraseña.requestFocus();
					nuevacontraseña.selectAll();
					return;
				}
				
				repetirnueva.requestFocus();
				repetirnueva.selectAll();
			}
		});
		nuevacontraseña.setHorizontalAlignment(SwingConstants.CENTER);
		nuevacontraseña.setForeground(Color.WHITE);
		nuevacontraseña.setEchoChar('x');
		nuevacontraseña.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		nuevacontraseña.setBackground(new Color(0, 0, 0, 0));
		nuevacontraseña.setBounds(163, 371, 161, 28);
		panel.add(nuevacontraseña);
		
		repetirnueva = new JPasswordField();
		repetirnueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(viejacontraseña.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir su antigua contraseña!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(viejacontraseña.getPassword().length < 10){
					JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					viejacontraseña.requestFocus();
					viejacontraseña.selectAll();
					return;
				}
				if(String.valueOf(nuevacontraseña.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir una nueva contraseña!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(nuevacontraseña.getPassword().length < 10){
					JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontraseña.requestFocus();
					nuevacontraseña.selectAll();
					return;
				}
				if(String.valueOf(nuevacontraseña.getPassword()).equals(viejacontraseña.getPassword())){
					JOptionPane.showMessageDialog(null, "Error: La contraseña vieja y nueva no pueden ser identicas!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontraseña.requestFocus();
					nuevacontraseña.selectAll();
					return;
				}
				if(!String.valueOf(nuevacontraseña.getPassword()).matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+$")){
					JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener números y letras!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontraseña.requestFocus();
					nuevacontraseña.selectAll();
					return;
				}
				if(String.valueOf(repetirnueva.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe repetir la nueva contraseña!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(!String.valueOf(nuevacontraseña.getPassword()).equals(String.valueOf(repetirnueva.getPassword()))){
					JOptionPane.showMessageDialog(null, "Error: Debe repetir la contraseña en el campo repetir exactamente igual!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					repetirnueva.requestFocus();
					repetirnueva.selectAll();
					return;
				}
				
				BD op = new BD();
				if(op.VerificarContraseña(viejacontraseña.getPassword()) == false){
					JOptionPane.showMessageDialog(null, "Error: Contraseña antigua incorrecta!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					viejacontraseña.requestFocus();
					viejacontraseña.selectAll();
					return;
				}
				try {
					op.ActualizarContraseña(nuevacontraseña.getPassword());
					JOptionPane.showMessageDialog(null, "Contraseña actualizada!", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
					viejacontraseña.setText("");
					nuevacontraseña.setText("");
					repetirnueva.setText("");
					viejacontraseña.requestFocus();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		repetirnueva.setHorizontalAlignment(SwingConstants.CENTER);
		repetirnueva.setForeground(Color.WHITE);
		repetirnueva.setEchoChar('x');
		repetirnueva.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		repetirnueva.setBackground(new Color(0, 0, 0, 0));
		repetirnueva.setBounds(163, 447, 161, 28);
		panel.add(repetirnueva);
		PantallaCambiarContraseña.setIconImage(Toolkit.getDefaultToolkit().getImage(CambiarContraseña.class.getResource("/imagenes/HotelIcon.png")));
		PantallaCambiarContraseña.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaCambiarContraseña.setResizable(false);
		PantallaCambiarContraseña.setSize(500, 624);
		

		JButton btnIngresar = new JButton("Cambiar contrase\u00F1a");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(viejacontraseña.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir su antigua contraseña!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(viejacontraseña.getPassword().length < 10){
					JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					viejacontraseña.requestFocus();
					viejacontraseña.selectAll();
					return;
				}
				if(String.valueOf(nuevacontraseña.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir una nueva contraseña!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(nuevacontraseña.getPassword().length < 10){
					JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontraseña.requestFocus();
					nuevacontraseña.selectAll();
					return;
				}
				if(String.valueOf(nuevacontraseña.getPassword()).equals(viejacontraseña.getPassword())){
					JOptionPane.showMessageDialog(null, "Error: La contraseña vieja y nueva no pueden ser identicas!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontraseña.requestFocus();
					nuevacontraseña.selectAll();
					return;
				}
				if(!String.valueOf(nuevacontraseña.getPassword()).matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+$")){
					JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener números y letras!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontraseña.requestFocus();
					nuevacontraseña.selectAll();
					return;
				}
				if(String.valueOf(repetirnueva.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe repetir la nueva contraseña!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(!String.valueOf(nuevacontraseña.getPassword()).equals(String.valueOf(repetirnueva.getPassword()))){
					JOptionPane.showMessageDialog(null, "Error: Debe repetir la contraseña en el campo repetir exactamente igual!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					repetirnueva.requestFocus();
					repetirnueva.selectAll();
					return;
				}
				
				BD op = new BD();
				if(op.VerificarContraseña(viejacontraseña.getPassword()) == false){
					JOptionPane.showMessageDialog(null, "Error: Contraseña antigua incorrecta!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					viejacontraseña.requestFocus();
					viejacontraseña.selectAll();
					return;
				}
				try {
					op.ActualizarContraseña(nuevacontraseña.getPassword());
					JOptionPane.showMessageDialog(null, "Contraseña actualizada!", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
					viejacontraseña.setText("");
					nuevacontraseña.setText("");
					repetirnueva.setText("");
					viejacontraseña.requestFocus();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnIngresar.setFocusPainted(false);
		btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnIngresar.setBackground(new Color(0, 128, 128));
		btnIngresar.setForeground(new Color(255, 255, 255));
		btnIngresar.setBounds(163, 508, 177, 28);
		panel.add(btnIngresar);
		
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaCambiarContraseña.setLocation(dim.width/2-PantallaCambiarContraseña.getSize().width/2, dim.height/2-PantallaCambiarContraseña.getSize().height/2);
		
		
		
		PantallaCambiarContraseña.setVisible(true);
	}
}




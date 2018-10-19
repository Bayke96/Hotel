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

public class CambiarContrase�a {
	
	public static String Usuario = "";
	public static String Cargo = "";
	public static String Turno = "";
	private static JPasswordField viejacontrase�a;
	private static JPasswordField nuevacontrase�a;
	private static JPasswordField repetirnueva;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaCambiarContrase�a = new JFrame("Hotel Mi Reina - Recuperar Contrase�a");
		PantallaCambiarContrase�a.setTitle("Hotel Mi Reina - Cambiar contrase\u00F1a\r\n");
		PantallaCambiarContrase�a.getContentPane().setBackground(Color.WHITE);
		PantallaCambiarContrase�a.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(CambiarContrase�a.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 500, 596);
		PantallaCambiarContrase�a.getContentPane().add(panel);
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
		lblNewLabel_2.setIcon(new ImageIcon(CambiarContrase�a.class.getResource("/imagenes/HotelIcon.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(114, 6, 267, 190);
		panel.add(lblNewLabel_2);
		
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFocusPainted(false);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaCambiarContrase�a.dispose();
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
		
		viejacontrase�a = new JPasswordField();
		viejacontrase�a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(String.valueOf(viejacontrase�a.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir su antigua contrase�a!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(viejacontrase�a.getPassword().length < 10){
					JOptionPane.showMessageDialog(null, "Error: La contrase�a debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					viejacontrase�a.requestFocus();
					viejacontrase�a.selectAll();
					return;
				}
				nuevacontrase�a.requestFocus();
				nuevacontrase�a.selectAll();
			}
		});
		viejacontrase�a.setForeground(Color.WHITE);
		viejacontrase�a.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		viejacontrase�a.setBackground(new Color(0, 0, 0, 0));
		viejacontrase�a.setEchoChar('x');
		viejacontrase�a.setHorizontalAlignment(SwingConstants.CENTER);
		viejacontrase�a.setBounds(163, 296, 161, 28);
		panel.add(viejacontrase�a);
		
		nuevacontrase�a = new JPasswordField();
		nuevacontrase�a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(nuevacontrase�a.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir una nueva contrase�a!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(nuevacontrase�a.getPassword().length < 10){
					JOptionPane.showMessageDialog(null, "Error: La contrase�a debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontrase�a.requestFocus();
					nuevacontrase�a.selectAll();
					return;
				}
				if(String.valueOf(nuevacontrase�a.getPassword()).equals(viejacontrase�a.getPassword())){
					JOptionPane.showMessageDialog(null, "Error: La contrase�a vieja y nueva no pueden ser identicas!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontrase�a.requestFocus();
					nuevacontrase�a.selectAll();
					return;
				}
				if(!String.valueOf(nuevacontrase�a.getPassword()).matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+$")){
					JOptionPane.showMessageDialog(null, "Error: La contrase�a debe contener n�meros y letras!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontrase�a.requestFocus();
					nuevacontrase�a.selectAll();
					return;
				}
				
				repetirnueva.requestFocus();
				repetirnueva.selectAll();
			}
		});
		nuevacontrase�a.setHorizontalAlignment(SwingConstants.CENTER);
		nuevacontrase�a.setForeground(Color.WHITE);
		nuevacontrase�a.setEchoChar('x');
		nuevacontrase�a.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		nuevacontrase�a.setBackground(new Color(0, 0, 0, 0));
		nuevacontrase�a.setBounds(163, 371, 161, 28);
		panel.add(nuevacontrase�a);
		
		repetirnueva = new JPasswordField();
		repetirnueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(viejacontrase�a.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir su antigua contrase�a!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(viejacontrase�a.getPassword().length < 10){
					JOptionPane.showMessageDialog(null, "Error: La contrase�a debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					viejacontrase�a.requestFocus();
					viejacontrase�a.selectAll();
					return;
				}
				if(String.valueOf(nuevacontrase�a.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir una nueva contrase�a!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(nuevacontrase�a.getPassword().length < 10){
					JOptionPane.showMessageDialog(null, "Error: La contrase�a debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontrase�a.requestFocus();
					nuevacontrase�a.selectAll();
					return;
				}
				if(String.valueOf(nuevacontrase�a.getPassword()).equals(viejacontrase�a.getPassword())){
					JOptionPane.showMessageDialog(null, "Error: La contrase�a vieja y nueva no pueden ser identicas!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontrase�a.requestFocus();
					nuevacontrase�a.selectAll();
					return;
				}
				if(!String.valueOf(nuevacontrase�a.getPassword()).matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+$")){
					JOptionPane.showMessageDialog(null, "Error: La contrase�a debe contener n�meros y letras!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontrase�a.requestFocus();
					nuevacontrase�a.selectAll();
					return;
				}
				if(String.valueOf(repetirnueva.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe repetir la nueva contrase�a!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(!String.valueOf(nuevacontrase�a.getPassword()).equals(String.valueOf(repetirnueva.getPassword()))){
					JOptionPane.showMessageDialog(null, "Error: Debe repetir la contrase�a en el campo repetir exactamente igual!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					repetirnueva.requestFocus();
					repetirnueva.selectAll();
					return;
				}
				
				BD op = new BD();
				if(op.VerificarContrase�a(viejacontrase�a.getPassword()) == false){
					JOptionPane.showMessageDialog(null, "Error: Contrase�a antigua incorrecta!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					viejacontrase�a.requestFocus();
					viejacontrase�a.selectAll();
					return;
				}
				try {
					op.ActualizarContrase�a(nuevacontrase�a.getPassword());
					JOptionPane.showMessageDialog(null, "Contrase�a actualizada!", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
					viejacontrase�a.setText("");
					nuevacontrase�a.setText("");
					repetirnueva.setText("");
					viejacontrase�a.requestFocus();
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
		PantallaCambiarContrase�a.setIconImage(Toolkit.getDefaultToolkit().getImage(CambiarContrase�a.class.getResource("/imagenes/HotelIcon.png")));
		PantallaCambiarContrase�a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaCambiarContrase�a.setResizable(false);
		PantallaCambiarContrase�a.setSize(500, 624);
		

		JButton btnIngresar = new JButton("Cambiar contrase\u00F1a");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(viejacontrase�a.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir su antigua contrase�a!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(viejacontrase�a.getPassword().length < 10){
					JOptionPane.showMessageDialog(null, "Error: La contrase�a debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					viejacontrase�a.requestFocus();
					viejacontrase�a.selectAll();
					return;
				}
				if(String.valueOf(nuevacontrase�a.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir una nueva contrase�a!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(nuevacontrase�a.getPassword().length < 10){
					JOptionPane.showMessageDialog(null, "Error: La contrase�a debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontrase�a.requestFocus();
					nuevacontrase�a.selectAll();
					return;
				}
				if(String.valueOf(nuevacontrase�a.getPassword()).equals(viejacontrase�a.getPassword())){
					JOptionPane.showMessageDialog(null, "Error: La contrase�a vieja y nueva no pueden ser identicas!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontrase�a.requestFocus();
					nuevacontrase�a.selectAll();
					return;
				}
				if(!String.valueOf(nuevacontrase�a.getPassword()).matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+$")){
					JOptionPane.showMessageDialog(null, "Error: La contrase�a debe contener n�meros y letras!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					nuevacontrase�a.requestFocus();
					nuevacontrase�a.selectAll();
					return;
				}
				if(String.valueOf(repetirnueva.getPassword()).trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe repetir la nueva contrase�a!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(!String.valueOf(nuevacontrase�a.getPassword()).equals(String.valueOf(repetirnueva.getPassword()))){
					JOptionPane.showMessageDialog(null, "Error: Debe repetir la contrase�a en el campo repetir exactamente igual!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					repetirnueva.requestFocus();
					repetirnueva.selectAll();
					return;
				}
				
				BD op = new BD();
				if(op.VerificarContrase�a(viejacontrase�a.getPassword()) == false){
					JOptionPane.showMessageDialog(null, "Error: Contrase�a antigua incorrecta!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					viejacontrase�a.requestFocus();
					viejacontrase�a.selectAll();
					return;
				}
				try {
					op.ActualizarContrase�a(nuevacontrase�a.getPassword());
					JOptionPane.showMessageDialog(null, "Contrase�a actualizada!", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
					viejacontrase�a.setText("");
					nuevacontrase�a.setText("");
					repetirnueva.setText("");
					viejacontrase�a.requestFocus();
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
		PantallaCambiarContrase�a.setLocation(dim.width/2-PantallaCambiarContrase�a.getSize().width/2, dim.height/2-PantallaCambiarContrase�a.getSize().height/2);
		
		
		
		PantallaCambiarContrase�a.setVisible(true);
	}
}




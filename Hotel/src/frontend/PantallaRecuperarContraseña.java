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

public class PantallaRecuperarContraseña {
	private static JTextField txtUsuario;
	
	public static String Usuario = "";
	public static String Cargo = "";
	public static String Turno = "";

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaContraseña = new JFrame("Hotel Mi Reina - Recuperar Contraseña");
		PantallaContraseña.getContentPane().setBackground(Color.WHITE);
		PantallaContraseña.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(PantallaRecuperarContraseña.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 500, 534);
		PantallaContraseña.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hotel Mi Reina C.A");
		lblNewLabel.setFont(new Font("Script MT Bold", Font.BOLD, 24));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 204, 482, 44);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre de usuario");
		lblNewLabel_1.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(6, 260, 482, 23);
		panel.add(lblNewLabel_1);
		
		txtUsuario = new JTextField();
		txtUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtUsuario.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir un nombre de usuario!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtUsuario.getText().trim().matches("[0-9A-Za-z\\s-]+")){
					JOptionPane.showMessageDialog(null, "Error: Ha introducido caracteres invalidos en el nombre de usuario!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtUsuario.requestFocus();
					txtUsuario.selectAll();
					return;
				}
				BD op = new BD();
				try {
					if(op.BuscarUsuario(txtUsuario.getText()) == false){
						JOptionPane.showMessageDialog(null, "Error: No existe ningun usuario con ese nombre!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
						txtUsuario.requestFocus();
						txtUsuario.selectAll();
						return;
					}
					op.RecuperarContraseña(txtUsuario.getText());
					
					
					JOptionPane.showMessageDialog(null, "INFO: Su contraseña ha sido reseteada\n Contraseña: mihotelreina+codigopostalelcallao"
							+ "\nEjemplo: hotelmireina7050", "Hotel Mi Reina C.A - NOTA", JOptionPane.INFORMATION_MESSAGE);
					txtUsuario.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			
			}
		});
		txtUsuario.setBackground(new Color(0, 0, 0, 0));
		txtUsuario.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setForeground(new Color(255, 255, 255));
		txtUsuario.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txtUsuario.setBounds(163, 296, 168, 28);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PantallaRecuperarContraseña.class.getResource("/imagenes/HotelIcon.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(114, 6, 267, 190);
		panel.add(lblNewLabel_2);
		
		JButton btnIngresar = new JButton("Recuperar Contrase\u00F1a");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtUsuario.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir un nombre de usuario!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtUsuario.getText().trim().matches("[0-9A-Za-z\\s-]+")){
					JOptionPane.showMessageDialog(null, "Error: Ha introducido caracteres invalidos en el nombre de usuario!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtUsuario.requestFocus();
					txtUsuario.selectAll();
					return;
				}
				BD op = new BD();
				try {
					if(op.BuscarUsuario(txtUsuario.getText()) == false){
						JOptionPane.showMessageDialog(null, "Error: No existe ningun usuario con ese nombre!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
						txtUsuario.requestFocus();
						txtUsuario.selectAll();
						return;
					}
					op.RecuperarContraseña(txtUsuario.getText());
					
					
					JOptionPane.showMessageDialog(null, "INFO: Su contraseña ha sido reseteada\n Contraseña: mihotelreina+codigopostalelcallao"
							+ "\nEjemplo: hotelmireina7050", "Hotel Mi Reina C.A - NOTA", JOptionPane.INFORMATION_MESSAGE);
					txtUsuario.setText("");
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
		btnIngresar.setBounds(163, 353, 177, 28);
		panel.add(btnIngresar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFocusPainted(false);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaContraseña.dispose();
				PantallaAcceso.main(args);
			}
		});
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setBackground(new Color(0, 128, 128));
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBounds(202, 383, 102, 28);
		panel.add(btnSalir);
		PantallaContraseña.setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaRecuperarContraseña.class.getResource("/imagenes/HotelIcon.png")));
		PantallaContraseña.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaContraseña.setResizable(false);
		PantallaContraseña.setSize(500, 524);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaContraseña.setLocation(dim.width/2-PantallaContraseña.getSize().width/2, dim.height/2-PantallaContraseña.getSize().height/2);
		
		
		
		PantallaContraseña.setVisible(true);
	}
}




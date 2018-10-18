package pantallas;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PantallaAgregarUsuario {
	private static JTextField txtUsuario;
	private static JTextField txtContraseña;
	private static JTextField txtNombres;
	private static JTextField txtApellidos;
	private static JTextField txtCedula;
	private static JTextField txtRIF;
	private static JTextField txtCorreo;
	private static JTextField txtTelefono;
	private static JTextField txtDireccion;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaAgregarUsuario = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaAgregarUsuario.setTitle("Hotel Mi Reina C.A - Agregar usuario");
		PantallaAgregarUsuario.getContentPane().setBackground(Color.WHITE);
		PantallaAgregarUsuario.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(PantallaAgregarUsuario.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1012, 640);
		PantallaAgregarUsuario.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(19, 78, 200, 16);
		panel.add(lblNombre);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setBounds(455, 78, 200, 16);
		panel.add(lblCargo);
		
		
		
		JLabel lblHora = new JLabel(new Date().toString());
		lblHora.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblHora.setForeground(Color.WHITE);
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setBounds(19, 707, 139, 16);
		panel.add(lblHora);
		
		
		LocalDateTime ldt = LocalDateTime.now();
		lblHora.setText("Fecha: " + DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(ldt));
		
		JLabel lblNewLabel = new JLabel("Hotel Mi Reina C.A");
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Script MT Bold", Font.BOLD, 29));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(19, 19, 855, 47);
		panel.add(lblNewLabel);
		
		JLabel lblTurno_1 = new JLabel("Turno");
		lblTurno_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurno_1.setForeground(Color.WHITE);
		lblTurno_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTurno_1.setBounds(667, 78, 200, 16);
		panel.add(lblTurno_1);
		
		JButton btnSalir = new JButton(" Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaAgregarUsuario.dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(PantallaAgregarUsuario.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setFocusPainted(false);
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setBounds(887, 584, 113, 37);
		panel.add(btnSalir);
		
		JLabel lblCargos = new JLabel("Agregar nuevo usuario");
		lblCargos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargos.setForeground(Color.WHITE);
		lblCargos.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCargos.setBounds(36, 124, 228, 33);
		panel.add(lblCargos);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeUsuario.setForeground(Color.WHITE);
		lblNombreDeUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNombreDeUsuario.setBounds(102, 170, 228, 33);
		panel.add(lblNombreDeUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtUsuario.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir un nombre de usuario!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtUsuario.getText().trim().matches("[0-9A-Za-z\\s-]+")){
					JOptionPane.showMessageDialog(null, "Error: Ha introducido caracteres invalidos en el nombre de usuario!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtUsuario.selectAll();
					return;
				}
				if(txtUsuario.getText().length() < 4){
					JOptionPane.showMessageDialog(null, "Error: El nombre debe contener minimo 4 letras!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				
				
				txtContraseña.requestFocus();
				txtContraseña.selectAll();
			}
		});
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtUsuario.setForeground(new Color(255, 255, 255));
		txtUsuario.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtUsuario.setBounds(135, 204, 159, 28);
		txtUsuario.setBackground(new Color(0, 0, 0, 0));
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a ( 10+ car\u00E1cteres )");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblContrasea.setBounds(102, 250, 228, 33);
		panel.add(lblContrasea);
		
		txtContraseña = new JTextField();
		txtContraseña.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(txtContraseña.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir una contraseña!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtContraseña.getText().trim().length() < 10){
					JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtContraseña.selectAll();
					return;
				}
				if(!txtContraseña.getText().matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+$")){
					JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener numeros y letras!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtContraseña.selectAll();
					return;
				}
				
				
				
				txtNombres.requestFocus();
				txtNombres.selectAll();
				
			}
		});
		txtContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		txtContraseña.setForeground(Color.WHITE);
		txtContraseña.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtContraseña.setColumns(10);
		txtContraseña.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtContraseña.setBackground(new Color(0, 0, 0, 0));
		txtContraseña.setBounds(135, 284, 159, 28);
		panel.add(txtContraseña);
		
		JLabel lblCargo_1 = new JLabel("Cargo");
		lblCargo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo_1.setForeground(Color.WHITE);
		lblCargo_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCargo_1.setBounds(102, 431, 228, 33);
		panel.add(lblCargo_1);
		
		JComboBox comboboxcargo = new JComboBox();
		comboboxcargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		comboboxcargo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
		comboboxcargo.setBounds(135, 473, 159, 28);
		panel.add(comboboxcargo);
		
		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombres.setForeground(Color.WHITE);
		lblNombres.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNombres.setBounds(402, 170, 228, 33);
		panel.add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblApellidos.setBounds(402, 250, 228, 33);
		panel.add(lblApellidos);
		
		JLabel lblCdulaDeIdentidad = new JLabel("C\u00E9dula de Identidad");
		lblCdulaDeIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCdulaDeIdentidad.setForeground(Color.WHITE);
		lblCdulaDeIdentidad.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCdulaDeIdentidad.setBounds(402, 336, 228, 33);
		panel.add(lblCdulaDeIdentidad);
		
		JLabel lblRif = new JLabel("R.I.F");
		lblRif.setHorizontalAlignment(SwingConstants.CENTER);
		lblRif.setForeground(Color.WHITE);
		lblRif.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblRif.setBounds(402, 428, 228, 33);
		panel.add(lblRif);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccin.setForeground(Color.WHITE);
		lblDireccin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDireccin.setBounds(728, 170, 228, 33);
		panel.add(lblDireccin);
		
		JLabel lblNmeroTelfonico = new JLabel("N\u00FAmero Tel\u00E9fonico");
		lblNmeroTelfonico.setHorizontalAlignment(SwingConstants.CENTER);
		lblNmeroTelfonico.setForeground(Color.WHITE);
		lblNmeroTelfonico.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNmeroTelfonico.setBounds(728, 250, 228, 33);
		panel.add(lblNmeroTelfonico);
		
		JLabel lblCorreoElctronico = new JLabel("Correo el\u00E9ctronico");
		lblCorreoElctronico.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorreoElctronico.setForeground(Color.WHITE);
		lblCorreoElctronico.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCorreoElctronico.setBounds(728, 336, 228, 33);
		panel.add(lblCorreoElctronico);
		
		JLabel lblTurno = new JLabel("Turno");
		lblTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurno.setForeground(Color.WHITE);
		lblTurno.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTurno.setBounds(728, 428, 228, 33);
		panel.add(lblTurno);
		
		txtNombres = new JTextField();
		txtNombres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtNombres.getText().trim().equalsIgnoreCase("") && !StringUtils.stripAccents(txtNombres.getText()).matches("^[ A-Za-z]+$")){
					JOptionPane.showMessageDialog(null, "Error: El campo Nombres solo acepta letras y espacios!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtNombres.selectAll();
					return;
				}
				
				txtApellidos.requestFocus();
				txtApellidos.selectAll();
			}
		});
		txtNombres.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombres.setForeground(Color.WHITE);
		txtNombres.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtNombres.setColumns(10);
		txtNombres.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtNombres.setBackground(new Color(0, 0, 0, 0));
		txtNombres.setBounds(439, 203, 159, 28);
		panel.add(txtNombres);
		
		txtApellidos = new JTextField();
		txtApellidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtApellidos.getText().trim().equalsIgnoreCase("") && !StringUtils.stripAccents(txtApellidos.getText()).matches("^[ A-Za-z]+$")){
					JOptionPane.showMessageDialog(null, "Error: El campo Apellidos solo acepta letras y espacios!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtApellidos.selectAll();
					return;
				}
				
				txtCedula.requestFocus();
				txtCedula.selectAll();
			}
		});
		txtApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		txtApellidos.setForeground(Color.WHITE);
		txtApellidos.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtApellidos.setColumns(10);
		txtApellidos.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtApellidos.setBackground(new Color(0, 0, 0, 0));
		txtApellidos.setBounds(439, 283, 159, 28);
		panel.add(txtApellidos);
		
		txtCedula = new JTextField();
		txtCedula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!txtCedula.getText().trim().equalsIgnoreCase("") && !txtCedula.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "Error: El campo cédula solo acepta números!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtCedula.selectAll();
					return;
				}
				
				txtRIF.requestFocus();
				txtRIF.selectAll();
			}
		});
		txtCedula.setHorizontalAlignment(SwingConstants.CENTER);
		txtCedula.setForeground(Color.WHITE);
		txtCedula.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtCedula.setColumns(10);
		txtCedula.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtCedula.setBackground(new Color(0, 0, 0, 0));
		txtCedula.setBounds(439, 367, 159, 28);
		panel.add(txtCedula);
		
		txtRIF = new JTextField();
		txtRIF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtRIF.getText().trim().equalsIgnoreCase("") && !txtRIF.getText().matches("([A-Za-z0-9--]+)")){
					JOptionPane.showMessageDialog(null, "Error: El campo RIF solo acepta letras, numeros y rayas!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtRIF.selectAll();
					return;
				}
				
				txtDireccion.requestFocus();
				txtDireccion.selectAll();
			}
		});
		txtRIF.setHorizontalAlignment(SwingConstants.CENTER);
		txtRIF.setForeground(Color.WHITE);
		txtRIF.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtRIF.setColumns(10);
		txtRIF.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtRIF.setBackground(new Color(0, 0, 0, 0));
		txtRIF.setBounds(439, 462, 159, 28);
		panel.add(txtRIF);
		
		txtCorreo = new JTextField();
		txtCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtCorreo.getText().trim().equalsIgnoreCase("") && !txtCorreo.getText().contains("@")){
					JOptionPane.showMessageDialog(null, "Error: El correo debe incluir un arroba (@) !", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtCorreo.selectAll();
					return;
				}
				
				
			}
		});
		txtCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCorreo.setForeground(Color.WHITE);
		txtCorreo.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtCorreo.setColumns(10);
		txtCorreo.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtCorreo.setBackground(new Color(0, 0, 0, 0));
		txtCorreo.setBounds(762, 367, 159, 28);
		panel.add(txtCorreo);
		
		txtTelefono = new JTextField();
		txtTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtTelefono.getText().trim().equalsIgnoreCase("") && !txtTelefono.getText().matches("[0-9 --]+")){
					JOptionPane.showMessageDialog(null, "Error: El campo Telefono solo acepta letras, numeros y rayas!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtTelefono.selectAll();
					return;
				}
				
				txtCorreo.requestFocus();
				txtCorreo.selectAll();
			}
		});
		txtTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		txtTelefono.setForeground(Color.WHITE);
		txtTelefono.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtTelefono.setColumns(10);
		txtTelefono.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtTelefono.setBackground(new Color(0, 0, 0, 0));
		txtTelefono.setBounds(762, 283, 159, 28);
		panel.add(txtTelefono);
		
		txtDireccion = new JTextField();
		txtDireccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				txtTelefono.requestFocus();
				txtTelefono.selectAll();
			}
		});
		txtDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		txtDireccion.setForeground(Color.WHITE);
		txtDireccion.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtDireccion.setColumns(10);
		txtDireccion.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtDireccion.setBackground(new Color(0, 0, 0, 0));
		txtDireccion.setBounds(762, 203, 159, 28);
		panel.add(txtDireccion);
		
		JComboBox comboBoxTurno = new JComboBox();
		comboBoxTurno.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Diurno", "Nocturno", "Full"}));
		comboBoxTurno.setFont(new Font("Segoe UI", Font.BOLD, 12));
		comboBoxTurno.setBounds(762, 473, 159, 28);
		panel.add(comboBoxTurno);
		
		JComboBox comboboxdepartamento = new JComboBox();
		comboboxdepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD op = new BD();
				op.Conectar("Hotel");
				try {
					op.ListaCargos(comboboxdepartamento.getSelectedItem().toString(), comboboxcargo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				op.Desconectar();
			}
		});
		
		JButton btnAceptar = new JButton("");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtUsuario.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir un nombre de usuario!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtUsuario.getText().trim().matches("[0-9A-Za-z\\s-]+")){
					JOptionPane.showMessageDialog(null, "Error: Ha introducido caracteres invalidos en el nombre de usuario!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtUsuario.selectAll();
					return;
				}
				if(txtUsuario.getText().length() < 4){
					JOptionPane.showMessageDialog(null, "Error: El nombre debe contener minimo 4 letras!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtContraseña.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir una contraseña!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtContraseña.getText().trim().length() < 10){
					JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtContraseña.selectAll();
					return;
				}
				if(!txtContraseña.getText().matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+$")){
					JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener numeros y letras!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtContraseña.selectAll();
					return;
				}
				if(comboboxdepartamento.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un departamento!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(comboboxcargo.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe elegir un cargo!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(comboBoxTurno.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe elegir un turno!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				BD op = new BD();
				try {
					if(op.BuscarUsuario(txtUsuario.getText().trim().toLowerCase()) == true) {
						JOptionPane.showMessageDialog(null, "Error: Este usuario ya existe dentro del sistema!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
						txtUsuario.requestFocus();
						txtUsuario.selectAll();
						return;
					}
					int cedula = 0;
					if(!txtCedula.getText().trim().equalsIgnoreCase("")) cedula = Integer.parseInt(txtCedula.getText());
					op.CrearUsuario(txtUsuario.getText().trim(), txtContraseña.getText(), comboboxdepartamento.getSelectedItem().toString(),
							comboboxcargo.getSelectedItem().toString(), 
							txtNombres.getText(), txtApellidos.getText(), cedula, txtRIF.getText(), txtDireccion.getText(), 
							txtTelefono.getText(), txtCorreo.getText(), comboBoxTurno.getSelectedItem().toString());
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				JOptionPane.showMessageDialog(null, "Usuario creado exitosamente!", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
				
				txtUsuario.setText("");
				txtContraseña.setText("");
				comboboxcargo.setSelectedItem("Seleccionar");
				txtNombres.setText("");
				txtApellidos.setText("");
				txtCedula.setText("");
				txtRIF.setText("");
				txtDireccion.setText("");
				txtTelefono.setText("");
				txtCorreo.setText("");
				comboBoxTurno.setSelectedItem("Seleccionar");
				comboboxdepartamento.setSelectedItem("Seleccionar");
			}
		});
		btnAceptar.setIcon(new ImageIcon(PantallaAgregarUsuario.class.getResource("/imagenes/AcceptIcon.png")));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBackground(new Color(0, 51, 51));
		btnAceptar.setBounds(762, 584, 61, 37);
		panel.add(btnAceptar);
		
		JButton btnBorrar = new JButton("");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsuario.setText("");
				txtContraseña.setText("");
				comboboxcargo.setSelectedItem("Seleccionar");
				txtNombres.setText("");
				txtApellidos.setText("");
				txtCedula.setText("");
				txtRIF.setText("");
				txtDireccion.setText("");
				txtTelefono.setText("");
				txtCorreo.setText("");
				comboBoxTurno.setSelectedItem("Seleccionar");
				comboboxdepartamento.setSelectedItem("Seleccionar");
			}
		});
		btnBorrar.setIcon(new ImageIcon(PantallaAgregarUsuario.class.getResource("/imagenes/EraserIcon.png")));
		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBorrar.setFocusPainted(false);
		btnBorrar.setBackground(new Color(0, 51, 51));
		btnBorrar.setBounds(823, 584, 61, 37);
		panel.add(btnBorrar);
		
		PantallaAgregarUsuario.setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaAgregarUsuario.class.getResource("/imagenes/HotelIcon.png")));
		PantallaAgregarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaAgregarUsuario.setResizable(false);
		PantallaAgregarUsuario.setSize(1024, 668);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaAgregarUsuario.setLocation(dim.width/2-PantallaAgregarUsuario.getSize().width/2, dim.height/2-PantallaAgregarUsuario.getSize().height/2);
		
		comboboxdepartamento.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
		comboboxdepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		comboboxdepartamento.setBounds(135, 391, 159, 28);
		panel.add(comboboxdepartamento);
		
		BD abc = new BD();
		try {
			abc.Conectar("Hotel");
			abc.ListaDepartamentos(comboboxdepartamento);
			abc.Desconectar();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
		
		lblNombre.setText(WordUtils.capitalizeFully(PantallaAcceso.Usuario));
		lblCargo.setText(WordUtils.capitalizeFully(PantallaAcceso.cargo));
		lblTurno_1.setText(WordUtils.capitalizeFully(PantallaAcceso.Turno));
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartamento.setForeground(Color.WHITE);
		lblDepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDepartamento.setBounds(102, 349, 228, 33);
		panel.add(lblDepartamento);
		
		JLabel label = new JLabel(PantallaAcceso.departamento);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.BOLD, 12));
		label.setBounds(243, 78, 200, 16);
		panel.add(label);
		
		
	
		
		PantallaAgregarUsuario.setVisible(true);
		
		txtUsuario.requestFocus();
	}
}



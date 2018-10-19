package frontend;

import java.awt.Dimension;


import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PantallaModificarUsuario {
	private static JTextField txtUsuario;
	private static JTextField txtNombres;
	private static JTextField txtApellidos;
	private static JTextField txtCedula;
	private static JTextField txtRIF;
	private static JTextField txtCorreo;
	private static JTextField txtTelefono;
	private static JTextField txtDireccion;
	
	public static String ViejoCargo = "";

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaModificarUsuario = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaModificarUsuario.setTitle("Hotel Mi Reina C.A - Actualizar usuario");
		PantallaModificarUsuario.getContentPane().setBackground(Color.WHITE);
		PantallaModificarUsuario.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(PantallaModificarUsuario.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1012, 640);
		PantallaModificarUsuario.getContentPane().add(panel);
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
				PantallaModificarUsuario.dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(PantallaModificarUsuario.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setFocusPainted(false);
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setBounds(893, 584, 113, 37);
		panel.add(btnSalir);
		
		JLabel lblCargos = new JLabel("Actualizar usuario");
		lblCargos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargos.setForeground(Color.WHITE);
		lblCargos.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCargos.setBounds(36, 124, 228, 33);
		panel.add(lblCargos);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeUsuario.setForeground(Color.WHITE);
		lblNombreDeUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNombreDeUsuario.setBounds(102, 262, 228, 33);
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
				
				
				
				txtNombres.requestFocus();
				txtNombres.selectAll();
			}
		});
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtUsuario.setForeground(new Color(255, 255, 255));
		txtUsuario.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtUsuario.setBounds(135, 296, 159, 28);
		txtUsuario.setBackground(new Color(0, 0, 0, 0));
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblcargo = new JLabel("Cargo");
		lblcargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblcargo.setForeground(Color.WHITE);
		lblcargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblcargo.setBounds(102, 428, 228, 33);
		panel.add(lblcargo);
		
		JComboBox comboBoxCargo = new JComboBox();
		comboBoxCargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		comboBoxCargo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
		comboBoxCargo.setBounds(135, 473, 159, 28);
		panel.add(comboBoxCargo);
		
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
		
		JComboBox comboBoxUsuarios = new JComboBox();
		comboBoxUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BD op = new BD();
				
				op.Conectar("Hotel");
				try{
					  String selectSQL = "SELECT Nombres, Apellidos, Cedula, Departamento, Cargo, RIF, Direccion, Correo, Turno, Telefono FROM Usuarios WHERE Nombre_Usuario = ?";
					  PreparedStatement preparedStatement = op.conn.prepareStatement(selectSQL);
					  preparedStatement.setString(1, comboBoxUsuarios.getSelectedItem().toString().toLowerCase());
					  ResultSet rs = preparedStatement.executeQuery();
					  while (rs.next()) {
					  	txtUsuario.setText(comboBoxUsuarios.getSelectedItem().toString());
					  	txtNombres.setText(WordUtils.capitalizeFully(rs.getString("Nombres")));
					  	txtApellidos.setText(WordUtils.capitalizeFully(rs.getString("Apellidos")));
					  	txtCedula.setText(Integer.toString(rs.getInt("Cedula")));
					  	comboboxdepartamento.setSelectedItem(op.DestransformarDepartamento(rs.getInt("Departamento")));
					  	op.ListaCargos(comboboxdepartamento.getSelectedItem().toString(), comboBoxCargo);
					  	comboBoxCargo.setSelectedItem(op.DestransformarCargo(rs.getInt("Departamento"), rs.getInt("Cargo")));
					  	txtRIF.setText(rs.getString("RIF").toUpperCase());
					  	txtDireccion.setText(WordUtils.capitalize(rs.getString("Direccion")));
					  	txtCorreo.setText(rs.getString("Correo"));
					  	txtTelefono.setText(rs.getString("Telefono"));
					  	comboBoxTurno.setSelectedItem(WordUtils.capitalizeFully(rs.getString("Turno")));
					  	ViejoCargo = rs.getString("Cargo");
					  }
					  
				}
				catch(Exception e){
						  System.err.println(e);
					  }
				
			}
		});
		comboBoxUsuarios.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
		comboBoxUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 12));
		comboBoxUsuarios.setBounds(135, 222, 159, 28);
		panel.add(comboBoxUsuarios);
		
		JButton btnAceptar = new JButton("");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxUsuarios.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe elegir un usuario!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
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
				
				if(comboBoxCargo.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe elegir un cargo!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(comboBoxTurno.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe elegir un turno!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				BD op = new BD();
				try {
					if(op.BuscarUsuario(txtUsuario.getText().trim().toLowerCase()) == true && !txtUsuario.getText().equalsIgnoreCase(comboBoxUsuarios.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(null, "Error: Este usuario ya existe dentro del sistema!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
						txtUsuario.requestFocus();
						txtUsuario.selectAll();
						return;
					}
					int cedula = 0;
					if(!txtCedula.getText().trim().equalsIgnoreCase("")) cedula = Integer.parseInt(txtCedula.getText());
					op.ActualizarUsuario(txtUsuario.getText().trim(), comboboxdepartamento.getSelectedItem().toString(), comboBoxCargo.getSelectedItem().toString(), 
							txtNombres.getText(), txtApellidos.getText(), cedula, txtRIF.getText(), txtDireccion.getText(), 
							txtTelefono.getText(), txtCorreo.getText(), comboBoxTurno.getSelectedItem().toString(), comboBoxUsuarios.getSelectedItem().toString());
					op.ListaUsuarios(comboBoxUsuarios);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente!", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
				
				txtUsuario.setText("");
				
				comboBoxCargo.setSelectedItem("Seleccionar");
				comboBoxUsuarios.setSelectedItem("Seleccionar");
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
		btnAceptar.setIcon(new ImageIcon(PantallaModificarUsuario.class.getResource("/imagenes/AcceptIcon.png")));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBackground(new Color(0, 51, 51));
		btnAceptar.setBounds(768, 584, 61, 37);
		panel.add(btnAceptar);
		
		
		
		JButton btnBorrar = new JButton("");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsuario.setText("");
				comboBoxUsuarios.setSelectedItem("Seleccionar");
				comboBoxCargo.setSelectedItem("Seleccionar");
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
		btnBorrar.setIcon(new ImageIcon(PantallaModificarUsuario.class.getResource("/imagenes/EraserIcon.png")));
		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBorrar.setFocusPainted(false);
		btnBorrar.setBackground(new Color(0, 51, 51));
		btnBorrar.setBounds(829, 584, 61, 37);
		panel.add(btnBorrar);
		
		PantallaModificarUsuario.setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaModificarUsuario.class.getResource("/imagenes/HotelIcon.png")));
		PantallaModificarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaModificarUsuario.setResizable(false);
		PantallaModificarUsuario.setSize(1024, 668);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaModificarUsuario.setLocation(dim.width/2-PantallaModificarUsuario.getSize().width/2, dim.height/2-PantallaModificarUsuario.getSize().height/2);
		
		
		comboboxdepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BD op = new BD();
				op.Conectar("Hotel");
				try {
					op.ListaCargos(comboboxdepartamento.getSelectedItem().toString(), comboBoxCargo);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				op.Desconectar();
			}
		});
		comboboxdepartamento.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
		comboboxdepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		comboboxdepartamento.setBounds(127, 377, 167, 28);
		panel.add(comboboxdepartamento);
		
		BD abc = new BD();
		try {
			abc.ListaDepartamentos(comboboxdepartamento);
			abc.ListaUsuarios(comboBoxUsuarios);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		lblNombre.setText(WordUtils.capitalizeFully(PantallaAcceso.Usuario));
		lblCargo.setText(WordUtils.capitalizeFully(PantallaAcceso.cargo));
		lblTurno_1.setText(WordUtils.capitalizeFully(PantallaAcceso.Turno));
		
		JLabel lblSeleccioneUnUsuario = new JLabel("Seleccione un usuario");
		lblSeleccioneUnUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneUnUsuario.setForeground(Color.WHITE);
		lblSeleccioneUnUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSeleccioneUnUsuario.setBounds(102, 180, 228, 33);
		panel.add(lblSeleccioneUnUsuario);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartamento.setForeground(Color.WHITE);
		lblDepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDepartamento.setBounds(102, 336, 228, 33);
		panel.add(lblDepartamento);
		
		JLabel lbldepartamento = new JLabel(PantallaAcceso.departamento);
		lbldepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lbldepartamento.setForeground(Color.WHITE);
		lbldepartamento.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbldepartamento.setBounds(243, 78, 200, 16);
		panel.add(lbldepartamento);
		
		
		
		
		
		
		PantallaModificarUsuario.setVisible(true);
		
		txtUsuario.requestFocus();
	}
}



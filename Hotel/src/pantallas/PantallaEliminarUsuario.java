package pantallas;

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

public class PantallaEliminarUsuario {
	
	public static String ViejoCargo = "";

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaModificarUsuario = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaModificarUsuario.setTitle("Hotel Mi Reina C.A - Eliminar usuario");
		PantallaModificarUsuario.getContentPane().setBackground(Color.WHITE);
		PantallaModificarUsuario.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(PantallaEliminarUsuario.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1012, 640);
		PantallaModificarUsuario.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(19, 78, 201, 16);
		panel.add(lblNombre);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setBounds(456, 78, 201, 16);
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
		lblTurno_1.setBounds(666, 78, 201, 16);
		panel.add(lblTurno_1);
		
		JButton btnSalir = new JButton(" Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaModificarUsuario.dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(PantallaEliminarUsuario.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setFocusPainted(false);
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setBounds(893, 585, 113, 37);
		panel.add(btnSalir);
		
		JLabel lblCargos = new JLabel("Eliminar usuario");
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
		
		JLabel lblCargo_1 = new JLabel("Cargo");
		lblCargo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo_1.setForeground(Color.WHITE);
		lblCargo_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCargo_1.setBounds(102, 428, 228, 33);
		panel.add(lblCargo_1);
		
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
		
		
		
		
		
		
		
		
		
		
		PantallaModificarUsuario.setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaEliminarUsuario.class.getResource("/imagenes/HotelIcon.png")));
		PantallaModificarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaModificarUsuario.setResizable(false);
		PantallaModificarUsuario.setSize(1024, 668);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaModificarUsuario.setLocation(dim.width/2-PantallaModificarUsuario.getSize().width/2, dim.height/2-PantallaModificarUsuario.getSize().height/2);
		
	
		
		
		lblNombre.setText(WordUtils.capitalizeFully(PantallaAcceso.Usuario));
		lblCargo.setText(WordUtils.capitalizeFully(PantallaAcceso.cargo));
		lblTurno_1.setText(WordUtils.capitalizeFully(PantallaAcceso.Turno));
		
		JLabel lblSeleccioneUnUsuario = new JLabel("Seleccione un usuario");
		lblSeleccioneUnUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneUnUsuario.setForeground(Color.WHITE);
		lblSeleccioneUnUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSeleccioneUnUsuario.setBounds(102, 180, 228, 33);
		panel.add(lblSeleccioneUnUsuario);
		
		JLabel lblUNombres = new JLabel("Data");
		lblUNombres.setForeground(Color.LIGHT_GRAY);
		lblUNombres.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblUNombres.setHorizontalAlignment(SwingConstants.CENTER);
		lblUNombres.setBounds(424, 207, 181, 16);
		panel.add(lblUNombres);
		
		JLabel lblUApellidos = new JLabel("Data");
		lblUApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblUApellidos.setForeground(Color.LIGHT_GRAY);
		lblUApellidos.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblUApellidos.setBounds(424, 295, 181, 16);
		panel.add(lblUApellidos);
		
		JLabel lblUCedula = new JLabel("Data");
		lblUCedula.setHorizontalAlignment(SwingConstants.CENTER);
		lblUCedula.setForeground(Color.LIGHT_GRAY);
		lblUCedula.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblUCedula.setBounds(424, 381, 181, 16);
		panel.add(lblUCedula);
		
		JLabel lblURIF = new JLabel("Data");
		lblURIF.setHorizontalAlignment(SwingConstants.CENTER);
		lblURIF.setForeground(Color.LIGHT_GRAY);
		lblURIF.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblURIF.setBounds(424, 461, 181, 16);
		panel.add(lblURIF);
		
		JLabel lblUDireccion = new JLabel("Data");
		lblUDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblUDireccion.setForeground(Color.LIGHT_GRAY);
		lblUDireccion.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblUDireccion.setBounds(753, 215, 181, 16);
		panel.add(lblUDireccion);
		
		JLabel lblUTelefono = new JLabel("Data");
		lblUTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblUTelefono.setForeground(Color.LIGHT_GRAY);
		lblUTelefono.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblUTelefono.setBounds(753, 295, 181, 16);
		panel.add(lblUTelefono);
		
		JLabel lblUCorreo = new JLabel("Data");
		lblUCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUCorreo.setForeground(Color.LIGHT_GRAY);
		lblUCorreo.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblUCorreo.setBounds(753, 381, 181, 16);
		panel.add(lblUCorreo);
		
		JLabel lblUTurno = new JLabel("Data");
		lblUTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblUTurno.setForeground(Color.LIGHT_GRAY);
		lblUTurno.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblUTurno.setBounds(753, 461, 181, 16);
		panel.add(lblUTurno);
		
		JLabel lblUNombre = new JLabel("Data");
		lblUNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblUNombre.setForeground(Color.LIGHT_GRAY);
		lblUNombre.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblUNombre.setBounds(124, 295, 181, 16);
		panel.add(lblUNombre);
		
		JLabel lblUCargo = new JLabel("Data");
		lblUCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUCargo.setForeground(Color.LIGHT_GRAY);
		lblUCargo.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblUCargo.setBounds(124, 461, 181, 16);
		panel.add(lblUCargo);
		
		JLabel lbludepartamento = new JLabel("Data");
		lbludepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lbludepartamento.setForeground(Color.LIGHT_GRAY);
		lbludepartamento.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbludepartamento.setBounds(124, 380, 181, 16);
		panel.add(lbludepartamento);
		
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
					  	lblUNombre.setText(comboBoxUsuarios.getSelectedItem().toString());
					  	lblUNombres.setText(WordUtils.capitalizeFully(rs.getString("Nombres")));
					  	lblUApellidos.setText(WordUtils.capitalizeFully(rs.getString("Apellidos")));
					  	
					  	lblUCedula.setText("V-" + rs.getInt("Cedula"));
					  	if(rs.getInt("Cedula") == 0) lblUCedula.setText("");
					    lbludepartamento.setText(op.DestransformarDepartamento(rs.getInt("Departamento")));
					  	lblUCargo.setText(op.DestransformarCargo(rs.getInt("Departamento"), rs.getInt("Cargo")));
					  	lblURIF.setText(rs.getString("RIF").toUpperCase());
					  	lblUDireccion.setText(WordUtils.capitalize(rs.getString("Direccion")));
					  	lblUCorreo.setText(rs.getString("Correo"));
					  	lblUTelefono.setText(rs.getString("Telefono"));
					  	lblUTurno.setText(StringUtils.capitalize(rs.getString("Turno")));
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
				
				
				BD op = new BD();
				try {
				
					
					op.EliminarUsuario(comboBoxUsuarios.getSelectedItem().toString());
					op.ListaUsuarios(comboBoxUsuarios);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				JOptionPane.showMessageDialog(null, "Usuario eliminado", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
				
				comboBoxUsuarios.setSelectedItem("Seleccionar");
				lblUNombre.setText("");
				lbludepartamento.setText("");
				lblUCargo.setText("");
				lblUNombres.setText("");
				lblUApellidos.setText("");
				lblUDireccion.setText("");
				lblUCedula.setText("");
				lblUCorreo.setText("");
				lblUTurno.setText("");
				lblUTelefono.setText("");
				lblURIF.setText("");
			}
		});
		btnAceptar.setIcon(new ImageIcon(PantallaEliminarUsuario.class.getResource("/imagenes/AcceptIcon.png")));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBackground(new Color(0, 51, 51));
		btnAceptar.setBounds(768, 585, 61, 37);
		panel.add(btnAceptar);
		
		
		BD abc = new BD();
		try {
			abc.ListaUsuarios(comboBoxUsuarios);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnBorrar = new JButton("");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				comboBoxUsuarios.setSelectedItem("Seleccionar");
				lblUNombre.setText("");
				lblUCargo.setText("");
				lblUNombres.setText("");
				lblUApellidos.setText("");
				lblUDireccion.setText("");
				lblUCedula.setText("");
				lblUCorreo.setText("");
				lblUTurno.setText("");
				lblUTelefono.setText("");
				lblURIF.setText("");
				lbludepartamento.setText("");
				
			}
		});
		btnBorrar.setIcon(new ImageIcon(PantallaEliminarUsuario.class.getResource("/imagenes/EraserIcon.png")));
		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBorrar.setFocusPainted(false);
		btnBorrar.setBackground(new Color(0, 51, 51));
		btnBorrar.setBounds(830, 585, 61, 37);
		panel.add(btnBorrar);
		
		JLabel lbldepartamento = new JLabel(PantallaAcceso.departamento);
		lbldepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lbldepartamento.setForeground(Color.WHITE);
		lbldepartamento.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbldepartamento.setBounds(232, 78, 201, 16);
		panel.add(lbldepartamento);
		
		
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartamento.setForeground(Color.WHITE);
		lblDepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDepartamento.setBounds(102, 347, 228, 33);
		panel.add(lblDepartamento);
		
		
		
		
		PantallaModificarUsuario.setVisible(true);
	}
}



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

public class ActualizarPerfil {
	private static JTextField txtNombres;
	private static JTextField txtApellidos;
	private static JTextField txtCedula;
	private static JTextField txtRIF;
	private static JTextField txtDireccion;
	private static JTextField txtTelefono;
	private static JTextField txtCorreo;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaPerfil = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaPerfil.setTitle("Hotel Mi Reina C.A - Actualizar Perfil");
		PantallaPerfil.getContentPane().setBackground(Color.WHITE);
		PantallaPerfil.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ActualizarPerfil.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1012, 700);
		PantallaPerfil.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(877, 19, 129, 16);
		panel.add(lblNombre);
		lblNombre.setText(WordUtils.capitalize(PantallaAcceso.Usuario));
		
		JLabel lblCargo = new JLabel("" + WordUtils.capitalizeFully(PantallaAcceso.cargo));
		lblCargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setBounds(877, 81, 129, 16);
		panel.add(lblCargo);
		
		
		LocalDateTime ldt = LocalDateTime.now();
		
		JLabel lblNewLabel = new JLabel("Hotel Mi Reina C.A");
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Script MT Bold", Font.BOLD, 29));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(19, 19, 855, 47);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombres");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(39, 151, 277, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblApellidos.setBounds(39, 237, 277, 16);
		panel.add(lblApellidos);
		
		JLabel lblCdulaDeIdentidad = new JLabel("C\u00E9dula de Identidad");
		lblCdulaDeIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCdulaDeIdentidad.setForeground(Color.WHITE);
		lblCdulaDeIdentidad.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCdulaDeIdentidad.setBounds(39, 326, 277, 16);
		panel.add(lblCdulaDeIdentidad);
		
		JLabel lblRif = new JLabel("RIF");
		lblRif.setHorizontalAlignment(SwingConstants.CENTER);
		lblRif.setForeground(Color.WHITE);
		lblRif.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblRif.setBounds(39, 404, 277, 16);
		panel.add(lblRif);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccin.setForeground(Color.WHITE);
		lblDireccin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDireccin.setBounds(328, 151, 277, 16);
		panel.add(lblDireccin);
		
		JLabel lblNmeroDeTelfono = new JLabel("N\u00FAmero de Tel\u00E9fono");
		lblNmeroDeTelfono.setHorizontalAlignment(SwingConstants.CENTER);
		lblNmeroDeTelfono.setForeground(Color.WHITE);
		lblNmeroDeTelfono.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNmeroDeTelfono.setBounds(328, 237, 277, 16);
		panel.add(lblNmeroDeTelfono);
		
		JLabel lblCorreoElctronico = new JLabel("Correo el\u00E9ctronico");
		lblCorreoElctronico.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorreoElctronico.setForeground(Color.WHITE);
		lblCorreoElctronico.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCorreoElctronico.setBounds(328, 326, 277, 16);
		panel.add(lblCorreoElctronico);
		
		JLabel lblUTurno = new JLabel("" + WordUtils.capitalizeFully(PantallaAcceso.Turno));
		lblUTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblUTurno.setForeground(Color.WHITE);
		lblUTurno.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblUTurno.setBounds(877, 109, 129, 16);
		panel.add(lblUTurno);
		
		JLabel lblTitulo = new JLabel("DATOS PERSONALES - ");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblTitulo.setBounds(29, 78, 687, 47);
		panel.add(lblTitulo);
		
		lblTitulo.setText("Datos Personales - " + PantallaAcceso.Usuario);
		
		JButton btnSalir = new JButton(" Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPerfil.dispose();
				PantallaPrincipal.main(args);
			}
		});
		btnSalir.setIcon(new ImageIcon(ActualizarPerfil.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setFocusPainted(false);
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setBounds(877, 643, 129, 37);
		panel.add(btnSalir);
		
		JButton btnAceptar = new JButton("");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cedula = 0;
				if(!txtCedula.getText().trim().equalsIgnoreCase("")) cedula = Integer.parseInt(txtCedula.getText());
				BD op = new BD();
				op.Conectar("Hotel");
				try {
					op.ActualizarPerfil(txtNombres.getText(), txtApellidos.getText(), cedula, txtRIF.getText(), txtDireccion.getText(), txtTelefono.getText(), 
							txtCorreo.getText(), PantallaAcceso.Usuario);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				op.Desconectar();
				JOptionPane.showMessageDialog(null, "Perfil actualizado", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
				
				txtNombres.requestFocus();
				txtNombres.selectAll();
			}
		});
		btnAceptar.setIcon(new ImageIcon(ActualizarPerfil.class.getResource("/imagenes/AcceptIcon.png")));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBackground(new Color(0, 51, 51));
		btnAceptar.setBounds(877, 605, 61, 37);
		panel.add(btnAceptar);
		
		JButton btnBorrar = new JButton("");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNombres.setText("");
				txtApellidos.setText("");
				txtDireccion.setText("");
				txtCedula.setText("");
				txtCorreo.setText("");
				txtRIF.setText("");
				txtTelefono.setText("");
			}
		});
		btnBorrar.setIcon(new ImageIcon(ActualizarPerfil.class.getResource("/imagenes/EraserIcon.png")));
		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBorrar.setFocusPainted(false);
		btnBorrar.setBackground(new Color(0, 51, 51));
		btnBorrar.setBounds(942, 605, 61, 37);
		panel.add(btnBorrar);
		
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
		txtNombres.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtNombres.setBackground(new Color(0, 0, 0, 0));
		txtNombres.setForeground(Color.WHITE);
		txtNombres.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtNombres.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombres.setBounds(99, 179, 163, 28);
		panel.add(txtNombres);
		txtNombres.setColumns(10);
		
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
		txtApellidos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtApellidos.setColumns(10);
		txtApellidos.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtApellidos.setBackground(new Color(0, 0, 0, 0));
		txtApellidos.setBounds(99, 265, 163, 28);
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
		txtCedula.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtCedula.setColumns(10);
		txtCedula.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtCedula.setBackground(new Color(0, 0, 0, 0));
		txtCedula.setBounds(99, 354, 163, 28);
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
		txtRIF.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtRIF.setColumns(10);
		txtRIF.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtRIF.setBackground(new Color(0, 0, 0, 0));
		txtRIF.setBounds(99, 432, 163, 28);
		panel.add(txtRIF);
		
		txtDireccion = new JTextField();
		txtDireccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTelefono.requestFocus();
				txtTelefono.selectAll();
			}
		});
		txtDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		txtDireccion.setForeground(Color.WHITE);
		txtDireccion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtDireccion.setColumns(10);
		txtDireccion.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtDireccion.setBackground(new Color(0, 0, 0, 0));
		txtDireccion.setBounds(385, 179, 163, 28);
		panel.add(txtDireccion);
		
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
		txtTelefono.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtTelefono.setColumns(10);
		txtTelefono.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtTelefono.setBackground(new Color(0, 0, 0, 0));
		txtTelefono.setBounds(385, 264, 163, 28);
		panel.add(txtTelefono);
		
		txtCorreo = new JTextField();
		txtCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtCorreo.getText().trim().equalsIgnoreCase("") && !txtCorreo.getText().contains("@")){
					JOptionPane.showMessageDialog(null, "Error: El correo debe incluir un arroba (@) !", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtCorreo.selectAll();
					return;
				}
				
				int cedula = 0;
				if(!txtCedula.getText().trim().equalsIgnoreCase("")) cedula = Integer.parseInt(txtCedula.getText());
				BD op = new BD();
				op.Conectar("Hotel");
				try {
					op.ActualizarPerfil(txtNombres.getText(), txtApellidos.getText(), cedula, txtRIF.getText(), txtDireccion.getText(), txtTelefono.getText(), 
							txtCorreo.getText(), PantallaAcceso.Usuario);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				op.Desconectar();
				JOptionPane.showMessageDialog(null, "Perfil actualizado", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
				
				txtNombres.requestFocus();
				txtNombres.selectAll();
				
			}
		});
		txtCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCorreo.setForeground(Color.WHITE);
		txtCorreo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtCorreo.setColumns(10);
		txtCorreo.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtCorreo.setBackground(new Color(0, 0, 0, 0));
		txtCorreo.setBounds(385, 354, 163, 28);
		panel.add(txtCorreo);
		
		JLabel lbldepartamento = new JLabel("");
		lbldepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lbldepartamento.setForeground(Color.WHITE);
		lbldepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbldepartamento.setBounds(877, 53, 129, 16);
		panel.add(lbldepartamento);
		
		PantallaPerfil.setIconImage(Toolkit.getDefaultToolkit().getImage(ActualizarPerfil.class.getResource("/imagenes/HotelIcon.png")));
		PantallaPerfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaPerfil.setResizable(false);
		PantallaPerfil.setSize(1024, 724);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaPerfil.setLocation(dim.width/2-PantallaPerfil.getSize().width/2, dim.height/2-PantallaPerfil.getSize().height/2);
		
		
		BD op = new BD();
		op.Conectar("Hotel");
		try{
			String RIF = "", nombres = "" , apellidos = "", direccion = "", telefono = "", correo = "", turno = "";
			int cedula = 0, departamento = 0, cargo = 0;
		      //STEP 2: Register JDBC driver
			  ResultSet rs = null;
		      op.Conectar("Hotel");
		      
		     
		      
		      
		     
		     
		      String query = "SELECT Nombres, Apellidos, Cedula, RIF, Direccion, Telefono, Correo, Departamento FROM Usuarios WHERE Nombre_Usuario = ?";
			  PreparedStatement pstmt = op.conn.prepareStatement(query); // create a statement
			  pstmt.setString(1, PantallaAcceso.Usuario.toLowerCase()); // set input parameter
			  rs = pstmt.executeQuery();
			 
			  while (rs.next()) {
			       nombres = rs.getString("Nombres");
			       apellidos = rs.getString("Apellidos");
			       RIF = rs.getString("RIF");
			       direccion = rs.getString("Direccion");
			       telefono = rs.getString("Telefono");
			       correo = rs.getString("Correo");
			       cedula = rs.getInt("Cedula");
			   
			       
			       
			       
			      
			       
			       txtNombres.setText(WordUtils.capitalizeFully(nombres));
			       txtApellidos.setText(WordUtils.capitalizeFully(apellidos));
			       txtRIF.setText(RIF);
			       txtDireccion.setText(WordUtils.capitalize(direccion));
			       txtTelefono.setText(telefono);
			       txtCedula.setText(Integer.toString(cedula));
			       txtCorreo.setText(correo);
			       lbldepartamento.setText(op.DestransformarDepartamento(rs.getInt("Departamento")));
			       
			  }
			  
		      op.Desconectar();
		
		   }
		   catch(Exception e){
			   System.err.println(e);
			  
		   }
		 finally{
			 op.Desconectar();
		 }
		
		
		
		PantallaPerfil.setVisible(true);
		
		txtNombres.requestFocus();
		txtNombres.selectAll();
		
		
	}
}



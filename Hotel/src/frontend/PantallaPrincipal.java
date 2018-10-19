package frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class PantallaPrincipal {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaPrincipal = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaPrincipal.setTitle("Hotel Mi Reina C.A - Menu Principal");
		PantallaPrincipal.getContentPane().setBackground(Color.WHITE);
		PantallaPrincipal.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(PantallaPrincipal.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(6, 6, 1012, 700);
		PantallaPrincipal.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(877, 19, 129, 16);
		panel.add(lblNombre);
		lblNombre.setText(WordUtils.capitalize(PantallaAcceso.Usuario));
		
		JLabel lbltcargo = new JLabel("Cargo");
		lbltcargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbltcargo.setForeground(Color.WHITE);
		lbltcargo.setHorizontalAlignment(SwingConstants.CENTER);
		lbltcargo.setBounds(877, 81, 129, 16);
		panel.add(lbltcargo);
		
		
		
		JLabel lblHora = new JLabel(new Date().toString());
		lblHora.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblHora.setForeground(Color.WHITE);
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setBounds(19, 630, 139, 16);
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
		
		JButton btnUsuarios = new JButton("   Usuarios");
		btnUsuarios.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/imagenes/UserIcon(2)(1).png")));
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaPrincipal.dispose();
				PantallaUsuarios.main(args);
			}
		});
		btnUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnUsuarios.setBackground(new Color(0, 51, 51));
		btnUsuarios.setForeground(Color.WHITE);
		btnUsuarios.setFocusPainted(false);
		btnUsuarios.setBounds(19, 78, 277, 64);
		panel.add(btnUsuarios);
		
		JButton btnReservas = new JButton("   Reservas");
		btnReservas.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/imagenes/ReservasIcon(1).png")));
		btnReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPrincipal.dispose();
				Reservas.main(args);
			}
		});
		btnReservas.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnReservas.setBackground(new Color(0, 51, 51));
		btnReservas.setForeground(new Color(255, 255, 255));
		btnReservas.setFocusPainted(false);
		btnReservas.setBounds(308, 78, 277, 64);
		panel.add(btnReservas);
		
		JButton btnRecepcion = new JButton("   Recepci\u00F3n");
		btnRecepcion.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/imagenes/ReceptionIcon(1).png")));
		btnRecepcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPrincipal.dispose();
				Recepcion.main(args);
			}
		});
		btnRecepcion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnRecepcion.setBackground(new Color(0, 51, 51));
		btnRecepcion.setForeground(Color.WHITE);
		btnRecepcion.setFocusPainted(false);
		btnRecepcion.setBounds(597, 78, 277, 64);
		panel.add(btnRecepcion);
		
		JButton btnHuespedes = new JButton("   Perfiles / Huespedes");
		btnHuespedes.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/imagenes/GuestIcon(1).png")));
		btnHuespedes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaPrincipal.dispose();
				Clientes.main(args);
			}
		});
		btnHuespedes.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnHuespedes.setBackground(new Color(0, 51, 51));
		btnHuespedes.setForeground(Color.WHITE);
		btnHuespedes.setFocusPainted(false);
		btnHuespedes.setBounds(19, 147, 277, 64);
		panel.add(btnHuespedes);
		
		JButton btnAdministracion = new JButton("   Modulo administrativo");
		btnAdministracion.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/imagenes/AdministrationIcon(1).png")));
		btnAdministracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaPrincipal.dispose();
				ModuloAdministrativo.main(args);
			}
		});
		btnAdministracion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAdministracion.setBackground(new Color(0, 51, 51));
		btnAdministracion.setForeground(Color.WHITE);
		btnAdministracion.setFocusPainted(false);
		btnAdministracion.setBounds(308, 147, 277, 64);
		panel.add(btnAdministracion);
		
		JButton btnInformes = new JButton("   Informes");
		btnInformes.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/imagenes/ReportIcon(1).png")));
		btnInformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnInformes.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnInformes.setBackground(new Color(0, 51, 51));
		btnInformes.setForeground(Color.WHITE);
		btnInformes.setFocusPainted(false);
		btnInformes.setBounds(597, 147, 277, 64);
		panel.add(btnInformes);
		
		JLabel lblNewLabel_1 = new JLabel("Nombres");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(19, 282, 277, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblApellidos.setBounds(19, 368, 277, 16);
		panel.add(lblApellidos);
		
		JLabel lblCdulaDeIdentidad = new JLabel("C\u00E9dula de identidad");
		lblCdulaDeIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCdulaDeIdentidad.setForeground(Color.WHITE);
		lblCdulaDeIdentidad.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCdulaDeIdentidad.setBounds(19, 457, 277, 16);
		panel.add(lblCdulaDeIdentidad);
		
		JLabel lblRif = new JLabel("RIF");
		lblRif.setHorizontalAlignment(SwingConstants.CENTER);
		lblRif.setForeground(Color.WHITE);
		lblRif.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblRif.setBounds(19, 535, 277, 16);
		panel.add(lblRif);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccin.setForeground(Color.WHITE);
		lblDireccin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDireccin.setBounds(308, 282, 277, 16);
		panel.add(lblDireccin);
		
		JLabel lblNmeroDeTelfono = new JLabel("N\u00FAmero de tel\u00E9fono");
		lblNmeroDeTelfono.setHorizontalAlignment(SwingConstants.CENTER);
		lblNmeroDeTelfono.setForeground(Color.WHITE);
		lblNmeroDeTelfono.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNmeroDeTelfono.setBounds(308, 368, 277, 16);
		panel.add(lblNmeroDeTelfono);
		
		JLabel lblCorreoElctronico = new JLabel("Correo el\u00E9ctronico");
		lblCorreoElctronico.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorreoElctronico.setForeground(Color.WHITE);
		lblCorreoElctronico.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCorreoElctronico.setBounds(308, 457, 277, 16);
		panel.add(lblCorreoElctronico);
		
		JLabel lblTurno = new JLabel("Turno");
		lblTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurno.setForeground(Color.WHITE);
		lblTurno.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTurno.setBounds(308, 535, 277, 16);
		panel.add(lblTurno);
		
		JLabel lblUTurno = new JLabel("Turno");
		lblUTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblUTurno.setForeground(Color.WHITE);
		lblUTurno.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblUTurno.setBounds(877, 109, 129, 16);
		panel.add(lblUTurno);
		
		JLabel lblTitulo = new JLabel("DATOS PERSONALES - ");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblTitulo.setBounds(47, 223, 687, 47);
		panel.add(lblTitulo);
		
		lblTitulo.setText("Datos Personales - " + PantallaAcceso.Usuario);
		
		JButton btnNewButton = new JButton(" Cambiar contrase\u00F1a");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPrincipal.dispose();
				CambiarContraseña.main(args);
			}
		});
		btnNewButton.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/imagenes/PasswordIcon(1).png")));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 51, 51));
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnNewButton.setBounds(806, 265, 189, 37);
		panel.add(btnNewButton);
		
		JButton btnActualizarPerfil = new JButton("Actualizar perfil");
		btnActualizarPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPrincipal.dispose();
				ActualizarPerfil.main(args);
			}
		});
		btnActualizarPerfil.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/imagenes/ProfileIcon(2).png")));
		btnActualizarPerfil.setFocusPainted(false);
		btnActualizarPerfil.setForeground(new Color(255, 255, 255));
		btnActualizarPerfil.setBackground(new Color(0, 51, 51));
		btnActualizarPerfil.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnActualizarPerfil.setBounds(806, 228, 189, 37);
		panel.add(btnActualizarPerfil);
		
		JButton btnSalir = new JButton(" Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPrincipal.dispose();
				PantallaAcceso.main(args);
			}
		});
		btnSalir.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setFocusPainted(false);
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setBounds(806, 302, 189, 37);
		panel.add(btnSalir);
		
		JLabel lblUNombres = new JLabel("Nombres");
		lblUNombres.setHorizontalAlignment(SwingConstants.CENTER);
		lblUNombres.setForeground(Color.LIGHT_GRAY);
		lblUNombres.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblUNombres.setBounds(19, 310, 277, 16);
		panel.add(lblUNombres);
		
		JLabel lblUApellidos = new JLabel("Nombres");
		lblUApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblUApellidos.setForeground(Color.LIGHT_GRAY);
		lblUApellidos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblUApellidos.setBounds(19, 393, 277, 16);
		panel.add(lblUApellidos);
		
		JLabel lblUCedula = new JLabel("Nombres");
		lblUCedula.setHorizontalAlignment(SwingConstants.CENTER);
		lblUCedula.setForeground(Color.LIGHT_GRAY);
		lblUCedula.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblUCedula.setBounds(19, 485, 277, 16);
		panel.add(lblUCedula);
		
		JLabel lblURIF = new JLabel("lblURIF");
		lblURIF.setHorizontalAlignment(SwingConstants.CENTER);
		lblURIF.setForeground(Color.LIGHT_GRAY);
		lblURIF.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblURIF.setBounds(19, 563, 277, 16);
		panel.add(lblURIF);
		
		JLabel lblUDireccion = new JLabel("Nombres");
		lblUDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblUDireccion.setForeground(Color.LIGHT_GRAY);
		lblUDireccion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblUDireccion.setBounds(308, 310, 277, 16);
		panel.add(lblUDireccion);
		
		JLabel lblUTelefono = new JLabel("Nombres");
		lblUTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblUTelefono.setForeground(Color.LIGHT_GRAY);
		lblUTelefono.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblUTelefono.setBounds(308, 396, 277, 16);
		panel.add(lblUTelefono);
		
		JLabel lblUCorreo = new JLabel("Nombres");
		lblUCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUCorreo.setForeground(Color.LIGHT_GRAY);
		lblUCorreo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblUCorreo.setBounds(308, 485, 277, 16);
		panel.add(lblUCorreo);
		
		JLabel lblUTurno2 = new JLabel("Nombres");
		lblUTurno2.setHorizontalAlignment(SwingConstants.CENTER);
		lblUTurno2.setForeground(Color.LIGHT_GRAY);
		lblUTurno2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblUTurno2.setBounds(308, 563, 277, 16);
		panel.add(lblUTurno2);
		
		JLabel lbldepartamento = new JLabel("Departamento");
		lbldepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lbldepartamento.setForeground(Color.WHITE);
		lbldepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbldepartamento.setBounds(877, 53, 129, 16);
		panel.add(lbldepartamento);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartamento.setForeground(Color.WHITE);
		lblDepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDepartamento.setBounds(570, 282, 209, 16);
		panel.add(lblDepartamento);
		
		JLabel lbludepartamento = new JLabel("Departamento");
		lbludepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lbludepartamento.setForeground(Color.LIGHT_GRAY);
		lbludepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbludepartamento.setBounds(570, 310, 209, 16);
		panel.add(lbludepartamento);
		
		JLabel labelCargo = new JLabel("Cargo");
		labelCargo.setHorizontalAlignment(SwingConstants.CENTER);
		labelCargo.setForeground(Color.WHITE);
		labelCargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		labelCargo.setBounds(570, 368, 209, 16);
		panel.add(labelCargo);
		
		JLabel lblucargo = new JLabel("Cargo");
		lblucargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblucargo.setForeground(Color.LIGHT_GRAY);
		lblucargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblucargo.setBounds(570, 396, 209, 16);
		panel.add(lblucargo);
		
		PantallaPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaPrincipal.class.getResource("/imagenes/HotelIcon.png")));
		PantallaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaPrincipal.setResizable(false);
		PantallaPrincipal.setSize(1024, 724);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaPrincipal.setLocation(dim.width/2-PantallaPrincipal.getSize().width/2, dim.height/2-PantallaPrincipal.getSize().height/2);
		
		
		BD op = new BD();
		op.Conectar("Hotel");
		try{
			String RIF = "", nombres = "" , apellidos = "", direccion = "", telefono = "", correo = "", turno = "";
			int cedula = 0, departamento = 0, cargo = 0;
		      //STEP 2: Register JDBC driver
			  ResultSet rs = null;
		      op.Conectar("Hotel");
		      
		     
		      
		      
		     
		     
		      String query = "SELECT Nombres, Apellidos, Cedula, RIF, Direccion, Telefono, Correo, Turno, Departamento, Cargo FROM Usuarios WHERE Nombre_Usuario = ?";
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
			       turno = rs.getString("Turno");
			       cargo = rs.getInt("Cargo");
			       departamento = rs.getInt("Departamento");
			       cedula = rs.getInt("Cedula");
			       
			       PantallaAcceso.Turno = turno;
			       PantallaAcceso.cargo = op.DestransformarCargo(departamento, cargo);
			       
			       
			       lbltcargo.setText(op.DestransformarCargo(departamento, cargo));
			       lblUTurno.setText(StringUtils.capitalize(turno));
			       
			       lblUNombres.setText(WordUtils.capitalizeFully(nombres));
			       lblUApellidos.setText(WordUtils.capitalizeFully(apellidos));
			       lblURIF.setText(RIF);
			       lblUDireccion.setText(WordUtils.capitalize(direccion));
			       lblUTelefono.setText(telefono);
			       lblUCedula.setText("V-" + cedula);
			       lblUCorreo.setText(correo);
			       lblUTurno2.setText(StringUtils.capitalize(turno));
			       lbludepartamento.setText(op.DestransformarDepartamento(departamento));
			       lbldepartamento.setText(op.DestransformarDepartamento(departamento));
			       lblucargo.setText(op.DestransformarCargo(departamento, cargo));
			       lbltcargo.setText(op.DestransformarCargo(departamento, cargo));
			       
			       PantallaAcceso.departamento = op.DestransformarDepartamento(departamento);
			  }
			 
		      op.Desconectar();
		
		   }
		   catch(Exception e){
			   System.err.println(e);
			  
		   }
		 finally{
			 op.Desconectar();
		 }
		
		
		
		PantallaPrincipal.setVisible(true);
	}
}



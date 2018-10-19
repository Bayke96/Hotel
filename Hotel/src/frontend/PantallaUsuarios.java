package frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
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

import org.apache.commons.text.WordUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaUsuarios {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaUsuarios = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaUsuarios.setTitle("Hotel Mi Reina C.A - Usuarios & Cargos");
		PantallaUsuarios.getContentPane().setBackground(Color.WHITE);
		PantallaUsuarios.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(PantallaUsuarios.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1012, 640);
		PantallaUsuarios.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(19, 78, 200, 16);
		panel.add(lblNombre);
		
		lblNombre.setText(WordUtils.capitalizeFully(PantallaAcceso.Usuario));
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setBounds(455, 78, 200, 16);
		panel.add(lblCargo);
		
		lblCargo.setText(WordUtils.capitalizeFully(PantallaAcceso.cargo));
		
		
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
		
		lblTurno_1.setText(WordUtils.capitalizeFully(PantallaAcceso.Turno));
		
		JButton btnSalir = new JButton(" Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaUsuarios.dispose();
				PantallaPrincipal.main(args);
			}
		});
		btnSalir.setIcon(new ImageIcon(PantallaUsuarios.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setFocusPainted(false);
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setBounds(893, 584, 113, 37);
		panel.add(btnSalir);
		
		JButton btnAgregarUsuario = new JButton("   Agregar usuario");
		btnAgregarUsuario.setIcon(new ImageIcon(PantallaUsuarios.class.getResource("/imagenes/AddUser.png")));
		btnAgregarUsuario.setFocusPainted(false);
		btnAgregarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PantallaAgregarUsuario.main(args);
			}
		});
		btnAgregarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAgregarUsuario.setForeground(new Color(255, 255, 255));
		btnAgregarUsuario.setBackground(new Color(0, 51, 51));
		btnAgregarUsuario.setBounds(29, 151, 839, 47);
		panel.add(btnAgregarUsuario);
		
		JButton btnModificarUsuario = new JButton("   Modificar usuario");
		btnModificarUsuario.setIcon(new ImageIcon(PantallaUsuarios.class.getResource("/imagenes/Edit.png")));
		btnModificarUsuario.setFocusPainted(false);
		btnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaModificarUsuario.main(args);
			}
		});
		btnModificarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnModificarUsuario.setForeground(new Color(255, 255, 255));
		btnModificarUsuario.setBackground(new Color(0, 51, 51));
		btnModificarUsuario.setBounds(29, 210, 839, 47);
		panel.add(btnModificarUsuario);
		
		JButton btnEliminarUsuario = new JButton("   Eliminar usuario");
		btnEliminarUsuario.setIcon(new ImageIcon(PantallaUsuarios.class.getResource("/imagenes/DeleteIcon.png")));
		btnEliminarUsuario.setFocusPainted(false);
		btnEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaEliminarUsuario.main(args);
			}
		});
		btnEliminarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnEliminarUsuario.setForeground(new Color(255, 255, 255));
		btnEliminarUsuario.setBackground(new Color(0, 51, 51));
		btnEliminarUsuario.setBounds(29, 269, 839, 47);
		panel.add(btnEliminarUsuario);
		
		JLabel lblNewLabel_1 = new JLabel("Usuarios");
		lblNewLabel_1.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(29, 106, 838, 33);
		panel.add(lblNewLabel_1);
		
		JLabel lblCargos = new JLabel("Cargos");
		lblCargos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargos.setForeground(Color.WHITE);
		lblCargos.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblCargos.setBounds(512, 405, 271, 33);
		panel.add(lblCargos);
		
		JButton btnAgregarCargo = new JButton("   Agregar cargo");
		btnAgregarCargo.setIcon(new ImageIcon(PantallaUsuarios.class.getResource("/imagenes/AddUser.png")));
		btnAgregarCargo.setFocusPainted(false);
		btnAgregarCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaAgregarCargo.main(args);
			}
		});
		btnAgregarCargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAgregarCargo.setForeground(new Color(255, 255, 255));
		btnAgregarCargo.setBackground(new Color(0, 51, 51));
		btnAgregarCargo.setBounds(512, 450, 271, 37);
		panel.add(btnAgregarCargo);
		
		JButton btnModificarCargo = new JButton("   Modificar cargo");
		btnModificarCargo.setIcon(new ImageIcon(PantallaUsuarios.class.getResource("/imagenes/Edit.png")));
		btnModificarCargo.setFocusPainted(false);
		btnModificarCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaModificarCargo.main(args);
			}
		});
		btnModificarCargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnModificarCargo.setForeground(new Color(255, 255, 255));
		btnModificarCargo.setBackground(new Color(0, 51, 51));
		btnModificarCargo.setBounds(512, 491, 271, 37);
		panel.add(btnModificarCargo);
		
		JButton btnEliminarCargo = new JButton("   Eliminar cargo");
		btnEliminarCargo.setIcon(new ImageIcon(PantallaUsuarios.class.getResource("/imagenes/DeleteIcon.png")));
		btnEliminarCargo.setFocusPainted(false);
		btnEliminarCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaEliminarCargo.main(args);
			}
		});
		btnEliminarCargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnEliminarCargo.setForeground(new Color(255, 255, 255));
		btnEliminarCargo.setBackground(new Color(0, 51, 51));
		btnEliminarCargo.setBounds(512, 529, 271, 37);
		panel.add(btnEliminarCargo);
		
		JButton btnVisualizarUsuario = new JButton("   Visualizar usuarios");
		btnVisualizarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaVisualizarUsuarios.main(args);
			}
		});
		btnVisualizarUsuario.setIcon(new ImageIcon(PantallaUsuarios.class.getResource("/imagenes/ViewIcon.png")));
		btnVisualizarUsuario.setForeground(Color.WHITE);
		btnVisualizarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnVisualizarUsuario.setFocusPainted(false);
		btnVisualizarUsuario.setBackground(new Color(0, 51, 51));
		btnVisualizarUsuario.setBounds(29, 328, 839, 47);
		panel.add(btnVisualizarUsuario);
		
		JButton btnVisualizarCargos = new JButton("   Visualizar cargos");
		btnVisualizarCargos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaVisualizarCargos.main(args);
			}
		});
		btnVisualizarCargos.setIcon(new ImageIcon(PantallaUsuarios.class.getResource("/imagenes/ViewIcon.png")));
		btnVisualizarCargos.setForeground(Color.WHITE);
		btnVisualizarCargos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnVisualizarCargos.setFocusPainted(false);
		btnVisualizarCargos.setBackground(new Color(0, 51, 51));
		btnVisualizarCargos.setBounds(512, 569, 271, 37);
		panel.add(btnVisualizarCargos);
		
		JLabel lblDepartamentos = new JLabel("Departamentos");
		lblDepartamentos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartamentos.setForeground(Color.WHITE);
		lblDepartamentos.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblDepartamentos.setBounds(99, 405, 271, 33);
		panel.add(lblDepartamentos);
		
		JButton btnAgregarDepartamento = new JButton("   Agregar departamento");
		btnAgregarDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarDepartamento.main(args);
			}
		});
		btnAgregarDepartamento.setIcon(new ImageIcon(PantallaUsuarios.class.getResource("/imagenes/Department(1).png")));
		btnAgregarDepartamento.setForeground(Color.WHITE);
		btnAgregarDepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAgregarDepartamento.setFocusPainted(false);
		btnAgregarDepartamento.setBackground(new Color(0, 51, 51));
		btnAgregarDepartamento.setBounds(99, 450, 271, 37);
		panel.add(btnAgregarDepartamento);
		
		JButton btnModificarDepartamento = new JButton("   Modificar departamento");
		btnModificarDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarDepartamento.main(args);
			}
		});
		btnModificarDepartamento.setIcon(new ImageIcon(PantallaUsuarios.class.getResource("/imagenes/Edit.png")));
		btnModificarDepartamento.setForeground(Color.WHITE);
		btnModificarDepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnModificarDepartamento.setFocusPainted(false);
		btnModificarDepartamento.setBackground(new Color(0, 51, 51));
		btnModificarDepartamento.setBounds(99, 491, 271, 37);
		panel.add(btnModificarDepartamento);
		
		JButton btnEliminarDepartamento = new JButton("   Eliminar departamento");
		btnEliminarDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarDepartamento.main(args);
			}
		});
		btnEliminarDepartamento.setIcon(new ImageIcon(PantallaUsuarios.class.getResource("/imagenes/DeleteIcon.png")));
		btnEliminarDepartamento.setForeground(Color.WHITE);
		btnEliminarDepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnEliminarDepartamento.setFocusPainted(false);
		btnEliminarDepartamento.setBackground(new Color(0, 51, 51));
		btnEliminarDepartamento.setBounds(99, 529, 271, 37);
		panel.add(btnEliminarDepartamento);
		
		JButton btnVisualizarDepartamentos = new JButton("   Visualizar departamentos");
		btnVisualizarDepartamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VisualizarDepartamentos.main(args);
			}
		});
		btnVisualizarDepartamentos.setIcon(new ImageIcon(PantallaUsuarios.class.getResource("/imagenes/ViewIcon.png")));
		btnVisualizarDepartamentos.setForeground(Color.WHITE);
		btnVisualizarDepartamentos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnVisualizarDepartamentos.setFocusPainted(false);
		btnVisualizarDepartamentos.setBackground(new Color(0, 51, 51));
		btnVisualizarDepartamentos.setBounds(99, 569, 271, 37);
		panel.add(btnVisualizarDepartamentos);
		
		JLabel lbldepartamento = new JLabel(PantallaAcceso.departamento);
		lbldepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lbldepartamento.setForeground(Color.WHITE);
		lbldepartamento.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbldepartamento.setBounds(243, 78, 200, 16);
		panel.add(lbldepartamento);
		
		PantallaUsuarios.setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaUsuarios.class.getResource("/imagenes/HotelIcon.png")));
		PantallaUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaUsuarios.setResizable(false);
		PantallaUsuarios.setSize(1024, 668);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaUsuarios.setLocation(dim.width/2-PantallaUsuarios.getSize().width/2, dim.height/2-PantallaUsuarios.getSize().height/2);
		
		
		
		PantallaUsuarios.setVisible(true);
	}
}



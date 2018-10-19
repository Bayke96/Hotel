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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Clientes {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaReservas = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaReservas.setTitle("Hotel Mi Reina C.A - Administración de clientes");
		PantallaReservas.getContentPane().setBackground(Color.WHITE);
		PantallaReservas.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1012, 700);
		PantallaReservas.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(877, 19, 129, 16);
		panel.add(lblNombre);
		lblNombre.setText(WordUtils.capitalize(PantallaAcceso.Usuario));
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setBounds(877, 78, 129, 16);
		panel.add(lblCargo);
		
		lblCargo.setText(WordUtils.capitalize(PantallaAcceso.cargo));
		
		
		
		JLabel lblHora = new JLabel(new Date().toString());
		lblHora.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblHora.setForeground(Color.WHITE);
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setBounds(19, 78, 139, 16);
		panel.add(lblHora);
		
		
		LocalDateTime ldt = LocalDateTime.now();
		lblHora.setText("Fecha: " + DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(ldt));
		
		JLabel lblNewLabel = new JLabel("Hotel Mi Reina C.A - Clientes");
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Script MT Bold", Font.BOLD, 29));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(19, 19, 855, 47);
		panel.add(lblNewLabel);
		
		JLabel lblUTurno = new JLabel("Turno");
		lblUTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblUTurno.setForeground(Color.WHITE);
		lblUTurno.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblUTurno.setBounds(877, 106, 129, 16);
		panel.add(lblUTurno);
		
		lblUTurno.setText(WordUtils.capitalize(PantallaAcceso.Turno));
		
		JButton btnSalir = new JButton(" Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaReservas.dispose();
				PantallaPrincipal.main(args);
			}
		});
		btnSalir.setIcon(new ImageIcon(Clientes.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setFocusPainted(false);
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setBounds(758, 78, 106, 37);
		panel.add(btnSalir);
		
		JButton btnregistrarcliente = new JButton("   Registrar Cliente");
		btnregistrarcliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrarCliente.main(args);
			}
		});
		btnregistrarcliente.setIcon(new ImageIcon(Clientes.class.getResource("/imagenes/GuestIcon(1).png")));
		btnregistrarcliente.setForeground(Color.WHITE);
		btnregistrarcliente.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnregistrarcliente.setFocusPainted(false);
		btnregistrarcliente.setBackground(new Color(0, 51, 51));
		btnregistrarcliente.setBounds(337, 224, 241, 75);
		panel.add(btnregistrarcliente);
		
		
		
		JButton btnmodificarcliente = new JButton("   Actualizar cliente");
		btnmodificarcliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizarCliente.main(args);
			}
		});
		btnmodificarcliente.setIcon(new ImageIcon(Clientes.class.getResource("/imagenes/Edit.png")));
		btnmodificarcliente.setForeground(Color.WHITE);
		btnmodificarcliente.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnmodificarcliente.setFocusPainted(false);
		btnmodificarcliente.setBackground(new Color(0, 51, 51));
		btnmodificarcliente.setBounds(337, 311, 241, 75);
		panel.add(btnmodificarcliente);
		
		JLabel lblCuartos = new JLabel("Administraci\u00F3n de Clientes");
		lblCuartos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuartos.setForeground(Color.WHITE);
		lblCuartos.setFont(new Font("Script MT Bold", Font.BOLD, 29));
		lblCuartos.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		lblCuartos.setBounds(149, 148, 605, 47);
		panel.add(lblCuartos);
		
		JButton btneliminarcliente = new JButton("   Eliminar cliente");
		btneliminarcliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EliminarCliente.main(args);
			}
		});
		btneliminarcliente.setIcon(new ImageIcon(Clientes.class.getResource("/imagenes/DeleteIcon.png")));
		btneliminarcliente.setForeground(Color.WHITE);
		btneliminarcliente.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btneliminarcliente.setFocusPainted(false);
		btneliminarcliente.setBackground(new Color(0, 51, 51));
		btneliminarcliente.setBounds(337, 399, 241, 75);
		panel.add(btneliminarcliente);
		
		
		
		
		JLabel lbldepartamento = new JLabel(PantallaAcceso.departamento);
		lbldepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lbldepartamento.setForeground(Color.WHITE);
		lbldepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbldepartamento.setBounds(877, 47, 129, 16);
		panel.add(lbldepartamento);
		
		JButton btnvisualizarclientes = new JButton("   Visualizar clientes");
		btnvisualizarclientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VisualizarClientes.main(args);
			}
		});
		btnvisualizarclientes.setIcon(new ImageIcon(Clientes.class.getResource("/imagenes/ViewIcon.png")));
		btnvisualizarclientes.setForeground(Color.WHITE);
		btnvisualizarclientes.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnvisualizarclientes.setFocusPainted(false);
		btnvisualizarclientes.setBackground(new Color(0, 51, 51));
		btnvisualizarclientes.setBounds(337, 486, 241, 75);
		panel.add(btnvisualizarclientes);
		
		PantallaReservas.setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/imagenes/HotelIcon.png")));
		PantallaReservas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaReservas.setResizable(false);
		PantallaReservas.setSize(1024, 724);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaReservas.setLocation(dim.width/2-PantallaReservas.getSize().width/2, dim.height/2-PantallaReservas.getSize().height/2);
		
		
		
		
		
		PantallaReservas.setVisible(true);
	}
}



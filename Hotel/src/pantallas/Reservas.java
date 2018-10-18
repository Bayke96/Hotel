package pantallas;

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

public class Reservas {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaReservas = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaReservas.setTitle("Hotel Mi Reina C.A - Menú de reservas");
		PantallaReservas.getContentPane().setBackground(Color.WHITE);
		PantallaReservas.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/imagenes/BG.jpg"))).getImage());
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
		
		JLabel lblNewLabel = new JLabel("Hotel Mi Reina C.A - Reservas");
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
		btnSalir.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setFocusPainted(false);
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setBounds(758, 78, 106, 37);
		panel.add(btnSalir);
		
		JButton btnnuevareserva = new JButton("   Nueva reserva");
		btnnuevareserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NuevaReserva.main(args);
			}
		});
		btnnuevareserva.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/ReservasIcon(1).png")));
		btnnuevareserva.setForeground(Color.WHITE);
		btnnuevareserva.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnnuevareserva.setFocusPainted(false);
		btnnuevareserva.setBackground(new Color(0, 51, 51));
		btnnuevareserva.setBounds(340, 230, 241, 75);
		panel.add(btnnuevareserva);
		
		
		
		JButton btneliminarreserva = new JButton("   Eliminar reserva existente");
		btneliminarreserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarReservas.main(args);
			}
		});
		btneliminarreserva.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/DeleteIcon.png")));
		btneliminarreserva.setForeground(Color.WHITE);
		btneliminarreserva.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btneliminarreserva.setFocusPainted(false);
		btneliminarreserva.setBackground(new Color(0, 51, 51));
		btneliminarreserva.setBounds(340, 317, 241, 75);
		panel.add(btneliminarreserva);
		
		JLabel lblCuartos = new JLabel("Reservas de cuartos");
		lblCuartos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuartos.setForeground(Color.WHITE);
		lblCuartos.setFont(new Font("Script MT Bold", Font.BOLD, 29));
		lblCuartos.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		lblCuartos.setBounds(323, 147, 278, 47);
		panel.add(lblCuartos);
		
		JButton btnvisualizarreservas = new JButton("   Visualizar reservas");
		btnvisualizarreservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VisualizarReservas.main(args);
			}
		});
		btnvisualizarreservas.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/ViewIcon.png")));
		btnvisualizarreservas.setForeground(Color.WHITE);
		btnvisualizarreservas.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnvisualizarreservas.setFocusPainted(false);
		btnvisualizarreservas.setBackground(new Color(0, 51, 51));
		btnvisualizarreservas.setBounds(340, 405, 241, 75);
		panel.add(btnvisualizarreservas);
		
		
		
		
		JLabel lbldepartamento = new JLabel(PantallaAcceso.departamento);
		lbldepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lbldepartamento.setForeground(Color.WHITE);
		lbldepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbldepartamento.setBounds(877, 47, 129, 16);
		panel.add(lbldepartamento);
		
		PantallaReservas.setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/imagenes/HotelIcon.png")));
		PantallaReservas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaReservas.setResizable(false);
		PantallaReservas.setSize(1024, 724);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaReservas.setLocation(dim.width/2-PantallaReservas.getSize().width/2, dim.height/2-PantallaReservas.getSize().height/2);
		
		
		
		
		
		PantallaReservas.setVisible(true);
	}
}



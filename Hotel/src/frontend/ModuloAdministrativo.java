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

public class ModuloAdministrativo {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaAdministrativa = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaAdministrativa.setTitle("Hotel Mi Reina C.A - Modulo Administrativo");
		PantallaAdministrativa.getContentPane().setBackground(Color.WHITE);
		PantallaAdministrativa.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ModuloAdministrativo.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1012, 700);
		PantallaAdministrativa.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel(PantallaAcceso.Usuario);
		lblNombre.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(19, 78, 200, 16);
		panel.add(lblNombre);
		
		JLabel lbldepartamento = new JLabel(PantallaAcceso.departamento);
		lbldepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lbldepartamento.setForeground(Color.WHITE);
		lbldepartamento.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbldepartamento.setBounds(231, 78, 200, 16);
		panel.add(lbldepartamento);
		
		JLabel lblCargo = new JLabel(PantallaAcceso.cargo);
		lblCargo.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setBounds(455, 78, 200, 16);
		panel.add(lblCargo);
		
		JLabel lblTurno_1 = new JLabel(PantallaAcceso.Turno);
		lblTurno_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurno_1.setForeground(Color.WHITE);
		lblTurno_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTurno_1.setBounds(667, 78, 200, 16);
		panel.add(lblTurno_1);
		
		lblCargo.setText(WordUtils.capitalize(PantallaAcceso.cargo));
		
		JLabel lblHora = new JLabel(new Date().toString());
		lblHora.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblHora.setForeground(Color.WHITE);
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setBounds(19, 568, 139, 37);
		panel.add(lblHora);
		
		
		LocalDateTime ldt = LocalDateTime.now();
		lblHora.setText("Fecha: " + DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(ldt));
		
		JLabel lblNewLabel = new JLabel("Hotel Mi Reina C.A - Modulo Administrativo");
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Script MT Bold", Font.BOLD, 29));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(19, 19, 855, 47);
		panel.add(lblNewLabel);
		
		
		
		lblTurno_1.setText(WordUtils.capitalize(PantallaAcceso.Turno));
		
		JButton btnSalir = new JButton(" Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaAdministrativa.dispose();
				PantallaPrincipal.main(args);
			}
		});
		btnSalir.setIcon(new ImageIcon(ModuloAdministrativo.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setFocusPainted(false);
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setBounds(900, 645, 106, 37);
		panel.add(btnSalir);
		
		JButton btnAgregarCuarto = new JButton("   Agregar cuarto");
		btnAgregarCuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarCuarto.main(args);
			}
		});
		btnAgregarCuarto.setIcon(new ImageIcon(ModuloAdministrativo.class.getResource("/imagenes/ReservasIcon(1).png")));
		btnAgregarCuarto.setForeground(Color.WHITE);
		btnAgregarCuarto.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAgregarCuarto.setFocusPainted(false);
		btnAgregarCuarto.setBackground(new Color(0, 51, 51));
		btnAgregarCuarto.setBounds(159, 202, 241, 75);
		panel.add(btnAgregarCuarto);
		
		JButton btnModificarCuarto = new JButton("   Modificar cuarto\r\n");
		btnModificarCuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModificarCuarto.main(args);
			}
		});
		btnModificarCuarto.setIcon(new ImageIcon(ModuloAdministrativo.class.getResource("/imagenes/Edit.png")));
		btnModificarCuarto.setForeground(Color.WHITE);
		btnModificarCuarto.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnModificarCuarto.setFocusPainted(false);
		btnModificarCuarto.setBackground(new Color(0, 51, 51));
		btnModificarCuarto.setBounds(159, 289, 241, 75);
		panel.add(btnModificarCuarto);
		
		JButton btnEliminarCuarto = new JButton("   Eliminar cuarto");
		btnEliminarCuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarCuarto.main(args);
			}
		});
		btnEliminarCuarto.setIcon(new ImageIcon(ModuloAdministrativo.class.getResource("/imagenes/DeleteIcon.png")));
		btnEliminarCuarto.setForeground(Color.WHITE);
		btnEliminarCuarto.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnEliminarCuarto.setFocusPainted(false);
		btnEliminarCuarto.setBackground(new Color(0, 51, 51));
		btnEliminarCuarto.setBounds(159, 376, 241, 75);
		panel.add(btnEliminarCuarto);
		
		JLabel lblCuartos = new JLabel("Cuartos");
		lblCuartos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuartos.setForeground(Color.WHITE);
		lblCuartos.setFont(new Font("Script MT Bold", Font.BOLD, 29));
		lblCuartos.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		lblCuartos.setBounds(209, 143, 144, 47);
		panel.add(lblCuartos);
		
		JButton btnVisualizarCuartos = new JButton("   Visualizar cuartos");
		btnVisualizarCuartos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VisualizarCuartos.main(args);
			}
		});
		btnVisualizarCuartos.setIcon(new ImageIcon(ModuloAdministrativo.class.getResource("/imagenes/ViewIcon.png")));
		btnVisualizarCuartos.setForeground(Color.WHITE);
		btnVisualizarCuartos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnVisualizarCuartos.setFocusPainted(false);
		btnVisualizarCuartos.setBackground(new Color(0, 51, 51));
		btnVisualizarCuartos.setBounds(159, 464, 241, 75);
		panel.add(btnVisualizarCuartos);
		
		JLabel lblPrecios = new JLabel("Precios");
		lblPrecios.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecios.setForeground(Color.WHITE);
		lblPrecios.setFont(new Font("Script MT Bold", Font.BOLD, 29));
		lblPrecios.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		lblPrecios.setBounds(632, 143, 144, 47);
		panel.add(lblPrecios);
		
		JButton btnAjustarPrecios = new JButton("   Ajustar precios");
		btnAjustarPrecios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AjustarPrecios.main(args);
			}
		});
		btnAjustarPrecios.setIcon(new ImageIcon(ModuloAdministrativo.class.getResource("/imagenes/PrecioIcon(1).png")));
		btnAjustarPrecios.setForeground(Color.WHITE);
		btnAjustarPrecios.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAjustarPrecios.setFocusPainted(false);
		btnAjustarPrecios.setBackground(new Color(0, 51, 51));
		btnAjustarPrecios.setBounds(590, 202, 241, 75);
		panel.add(btnAjustarPrecios);
		
		JButton btnAjustarHabitaciones = new JButton("   Ajustar habitaciones");
		btnAjustarHabitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AjustarHabitaciones.main(args);
			}
		});
		btnAjustarHabitaciones.setIcon(new ImageIcon(ModuloAdministrativo.class.getResource("/imagenes/ReservasIcon(1).png")));
		btnAjustarHabitaciones.setForeground(Color.WHITE);
		btnAjustarHabitaciones.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAjustarHabitaciones.setFocusPainted(false);
		btnAjustarHabitaciones.setBackground(new Color(0, 51, 51));
		btnAjustarHabitaciones.setBounds(590, 464, 241, 75);
		panel.add(btnAjustarHabitaciones);
		
		JLabel lblHabitaciones = new JLabel("Habitaciones");
		lblHabitaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblHabitaciones.setForeground(Color.WHITE);
		lblHabitaciones.setFont(new Font("Script MT Bold", Font.BOLD, 29));
		lblHabitaciones.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		lblHabitaciones.setBounds(590, 405, 241, 47);
		panel.add(lblHabitaciones);
		
		PantallaAdministrativa.setIconImage(Toolkit.getDefaultToolkit().getImage(ModuloAdministrativo.class.getResource("/imagenes/HotelIcon.png")));
		PantallaAdministrativa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaAdministrativa.setResizable(false);
		PantallaAdministrativa.setSize(1024, 724);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaAdministrativa.setLocation(dim.width/2-PantallaAdministrativa.getSize().width/2, dim.height/2-PantallaAdministrativa.getSize().height/2);
		
		
		
		
		
		PantallaAdministrativa.setVisible(true);
	}
}



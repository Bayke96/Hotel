package frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
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
import com.toedter.calendar.JDateChooser;

import backend.BD;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class Recepcion {
	private static JTable tablareservas;
	public static JComboBox comboboxclientes;
	

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaReservas = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaReservas.setTitle("Hotel Mi Reina C.A - Area de recepción");
		PantallaReservas.getContentPane().setBackground(Color.WHITE);
		PantallaReservas.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Recepcion.class.getResource("/imagenes/BG.jpg"))).getImage());
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate hoy = LocalDate.now( ZoneId.of( "America/Caracas" ) );
		
		JLabel lblHora = new JLabel(hoy.toString());
		lblHora.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblHora.setForeground(Color.WHITE);
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setBounds(29, 78, 139, 16);
		panel.add(lblHora);
		
		
		LocalDateTime ldt = LocalDateTime.now();
		lblHora.setText("Fecha: " + DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(ldt));
		
		JLabel lblNewLabel = new JLabel("Hotel Mi Reina C.A - Recepci\u00F3n");
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
		
		JButton btnSalir = new JButton(" Atras");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaReservas.dispose();
				PantallaPrincipal.main(args);
			}
		});
		btnSalir.setIcon(new ImageIcon(Recepcion.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setFocusPainted(false);
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setBounds(476, 639, 106, 37);
		panel.add(btnSalir);
		
		JLabel lbldepartamento = new JLabel(PantallaAcceso.departamento);
		lbldepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lbldepartamento.setForeground(Color.WHITE);
		lbldepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbldepartamento.setBounds(877, 47, 129, 16);
		panel.add(lbldepartamento);
		
	
		
		PantallaReservas.setIconImage(Toolkit.getDefaultToolkit().getImage(Recepcion.class.getResource("/imagenes/HotelIcon.png")));
		PantallaReservas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaReservas.setResizable(false);
		PantallaReservas.setSize(1024, 724);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaReservas.setLocation(dim.width/2-PantallaReservas.getSize().width/2, dim.height/2-PantallaReservas.getSize().height/2);
		
		JLabel lblReservas = new JLabel("Habitaciones Disponibles - 16/10/2018");
		lblReservas.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		lblReservas.setHorizontalAlignment(SwingConstants.CENTER);
		lblReservas.setForeground(Color.WHITE);
		lblReservas.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblReservas.setBounds(325, 78, 360, 36);
		panel.add(lblReservas);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(29, 224, 964, 408);
		panel.add(scrollPane);
		
		tablareservas = new JTable();
		tablareservas.setFont(new Font("Segoe UI", Font.BOLD, 12));
		tablareservas.setForeground(new Color(255, 255, 255));
		tablareservas.setGridColor(new Color(255, 255, 255));
		tablareservas.setBackground(new Color(0, 51, 51));
		tablareservas.setFillsViewportHeight(true);
		tablareservas.setSelectionBackground(new Color(30, 144, 255));
		tablareservas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0 habitaci\u00F3n", "Disponibilidad", "Cliente", "Dias", "Total"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablareservas.getColumnModel().getColumn(0).setResizable(false);
		tablareservas.getColumnModel().getColumn(1).setResizable(false);
		tablareservas.getColumnModel().getColumn(2).setResizable(false);
		tablareservas.getColumnModel().getColumn(3).setResizable(false);
		tablareservas.getColumnModel().getColumn(4).setResizable(false);
		scrollPane.setViewportView(tablareservas);
		
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		
		headerRenderer.setBackground(new Color(0, 51, 51));
		headerRenderer.setForeground(Color.WHITE);
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);
		headerRenderer.setOpaque(true);
		tablareservas.getTableHeader().setReorderingAllowed(false);

		for (int i = 0; i < tablareservas.getModel().getColumnCount(); i++) {
			tablareservas.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
			tablareservas.getColumnModel().getColumn(i).setCellRenderer( headerRenderer );
		     
		}
		
		JDateChooser fechareservas = new JDateChooser();
		fechareservas.setMinSelectableDate(new Date());
		fechareservas.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				if ("date".equals(arg0.getPropertyName())) {
					lblReservas.setText("Habitaciones Disponibles - " + ((JTextField)fechareservas.getDateEditor().getUiComponent()).getText());
					BD op = new BD();
					op.Conectar("Hotel");
					
					LocalDate localDate = fechareservas.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					try {
						op.CargarReservasFull(tablareservas, localDate);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					op.Desconectar();
		            }
			}
		});
		fechareservas.setBounds(430, 129, 152, 28);
		panel.add(fechareservas);
		
		JButton btnasignarhabitacion = new JButton("  Asignar habitaci\u00F3n");
		btnasignarhabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AsignarHabitacion.main(args);
			}
		});
		btnasignarhabitacion.setIcon(new ImageIcon(Recepcion.class.getResource("/imagenes/ReservasIcon(1).png")));
		btnasignarhabitacion.setForeground(Color.WHITE);
		btnasignarhabitacion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnasignarhabitacion.setFocusPainted(false);
		btnasignarhabitacion.setBackground(new Color(0, 51, 51));
		btnasignarhabitacion.setBounds(303, 175, 197, 37);
		panel.add(btnasignarhabitacion);
		
		JButton btnsalidas = new JButton("  Salidas");
		btnsalidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Salidas.main(args);
			}
		});
		btnsalidas.setEnabled(false);
		btnsalidas.setIcon(new ImageIcon(Recepcion.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnsalidas.setForeground(Color.WHITE);
		btnsalidas.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnsalidas.setFocusPainted(false);
		btnsalidas.setBackground(new Color(128, 0, 0));
		btnsalidas.setBounds(887, 185, 106, 37);
		panel.add(btnsalidas);
		
		JButton btneliminarasignacion = new JButton("  Eliminar asignaci\u00F3n");
		btneliminarasignacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EliminarAsignacion.main(args);
			}
		});
		btneliminarasignacion.setIcon(new ImageIcon(Recepcion.class.getResource("/imagenes/DeleteIcon.png")));
		btneliminarasignacion.setForeground(Color.WHITE);
		btneliminarasignacion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btneliminarasignacion.setFocusPainted(false);
		btneliminarasignacion.setBackground(new Color(0, 51, 51));
		btneliminarasignacion.setBounds(506, 175, 197, 37);
		panel.add(btneliminarasignacion);
		
		BD op = new BD();
		try {
			op.Conectar("Hotel");
			op.CargarReservasFull(tablareservas, hoy);
			int contador = 0;
			String selectSQL = "SELECT Fin FROM Reservas WHERE Fin = ?";
			op.Conectar("Hotel");
			   PreparedStatement preparedStatement = op.conn.prepareStatement(selectSQL);
				preparedStatement.setObject(1, LocalDate.now());
				  ResultSet rs = preparedStatement.executeQuery();
			   
			  //STEP 5: Extract data from result set
			  while(rs.next()){
				contador ++;
			  }
			
			op.Desconectar();
			
			if(contador >= 1) btnsalidas.setEnabled(true);
				
			
			
			
			
		
			
		
			
			
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PantallaReservas.setVisible(true);
	}
}



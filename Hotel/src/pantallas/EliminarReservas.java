package pantallas;

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
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class EliminarReservas {
	private static JTable tablareservas;
	public static JComboBox comboboxclientes;
	

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaReservas = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaReservas.setTitle("Hotel Mi Reina C.A - Eliminar reserva");
		PantallaReservas.getContentPane().setBackground(Color.WHITE);
		PantallaReservas.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(EliminarReservas.class.getResource("/imagenes/BG.jpg"))).getImage());
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
			
			}
		});
		btnSalir.setIcon(new ImageIcon(EliminarReservas.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setFocusPainted(false);
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setBounds(900, 644, 106, 37);
		panel.add(btnSalir);
		
		
		
		JLabel lblCuartos = new JLabel("Eliminar reserva de cuarto");
		lblCuartos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuartos.setForeground(Color.WHITE);
		lblCuartos.setFont(new Font("Script MT Bold", Font.BOLD, 29));
		lblCuartos.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		lblCuartos.setBounds(29, 131, 453, 47);
		panel.add(lblCuartos);
		
		JLabel lbldepartamento = new JLabel(PantallaAcceso.departamento);
		lbldepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lbldepartamento.setForeground(Color.WHITE);
		lbldepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbldepartamento.setBounds(877, 47, 129, 16);
		panel.add(lbldepartamento);
		
		JLabel lblNewLabel_1 = new JLabel("Cliente");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_1.setBounds(19, 190, 116, 16);
		panel.add(lblNewLabel_1);
		
		EliminarReservas.comboboxclientes = new JComboBox();
		
		JLabel lblReservas_1 = new JLabel("Reserva");
		lblReservas_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblReservas_1.setForeground(Color.LIGHT_GRAY);
		lblReservas_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblReservas_1.setBounds(19, 231, 116, 16);
		panel.add(lblReservas_1);
		
		JLabel fechaentrada = new JLabel("");
		fechaentrada.setForeground(new Color(255, 255, 0));
		fechaentrada.setFont(new Font("Segoe UI", Font.BOLD, 12));
		fechaentrada.setHorizontalAlignment(SwingConstants.CENTER);
		fechaentrada.setBounds(168, 315, 152, 16);
		panel.add(fechaentrada);
		
		JLabel fechasalida = new JLabel("");
		fechasalida.setHorizontalAlignment(SwingConstants.CENTER);
		fechasalida.setForeground(Color.YELLOW);
		fechasalida.setFont(new Font("Segoe UI", Font.BOLD, 12));
		fechasalida.setBounds(168, 358, 152, 16);
		panel.add(fechasalida);
		
		JLabel lblnhabitacion = new JLabel("");
		lblnhabitacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblnhabitacion.setForeground(Color.YELLOW);
		lblnhabitacion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblnhabitacion.setBounds(168, 276, 152, 16);
		panel.add(lblnhabitacion);
		
		JComboBox comboboxreservas = new JComboBox();
		
		comboboxreservas.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
		comboboxreservas.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboboxreservas.setBounds(147, 226, 197, 26);
		panel.add(comboboxreservas);
		
		comboboxclientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboboxclientes.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					comboboxreservas.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
					
					return;
				}
				if(!comboboxclientes.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					BD op = new BD();
					op.Conectar("Hotel");
					try {
						op.ListaReservas(comboboxclientes.getSelectedItem().toString(), comboboxreservas);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					op.Desconectar();
				}
			}
		});
		
		
		
		comboboxclientes.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboboxclientes.setBounds(147, 185, 197, 26);
		panel.add(comboboxclientes);
		
		PantallaReservas.setIconImage(Toolkit.getDefaultToolkit().getImage(EliminarReservas.class.getResource("/imagenes/HotelIcon.png")));
		PantallaReservas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaReservas.setResizable(false);
		PantallaReservas.setSize(1024, 724);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaReservas.setLocation(dim.width/2-PantallaReservas.getSize().width/2, dim.height/2-PantallaReservas.getSize().height/2);
		
		
		
		JLabel lblHabitacin = new JLabel("Habitaci\u00F3n");
		lblHabitacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblHabitacin.setForeground(Color.LIGHT_GRAY);
		lblHabitacin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblHabitacin.setBounds(19, 276, 116, 16);
		panel.add(lblHabitacin);
		
		
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de entrada");
		lblFechaDeInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaDeInicio.setForeground(Color.LIGHT_GRAY);
		lblFechaDeInicio.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblFechaDeInicio.setBounds(19, 315, 116, 16);
		panel.add(lblFechaDeInicio);
		
		JLabel lblFechaDeSalida = new JLabel("Fecha de salida");
		lblFechaDeSalida.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaDeSalida.setForeground(Color.LIGHT_GRAY);
		lblFechaDeSalida.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblFechaDeSalida.setBounds(29, 352, 106, 28);
		panel.add(lblFechaDeSalida);
		
		JLabel lbldias = new JLabel("");
		JLabel lblprecio = new JLabel("");
		JLabel lblbss = new JLabel("");
		
		JLabel lblReservas = new JLabel("Reservas - " + hoy.format(formatter));
		lblReservas.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		lblReservas.setHorizontalAlignment(SwingConstants.CENTER);
		lblReservas.setForeground(Color.WHITE);
		lblReservas.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblReservas.setBounds(628, 117, 325, 30);
		panel.add(lblReservas);
		
		
	
		
		
		
		JLabel lblDatosDeLa = new JLabel("Datos de la habitaci\u00F3n");
		lblDatosDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDeLa.setForeground(Color.WHITE);
		lblDatosDeLa.setFont(new Font("Script MT Bold", Font.BOLD, 29));
		lblDatosDeLa.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		lblDatosDeLa.setBounds(29, 392, 779, 47);
		panel.add(lblDatosDeLa);
		
		JLabel lblNDeHabitacin = new JLabel("N\u00B0 de habitaci\u00F3n");
		lblNDeHabitacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNDeHabitacin.setForeground(new Color(255, 255, 255));
		lblNDeHabitacin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNDeHabitacin.setBounds(29, 463, 453, 28);
		panel.add(lblNDeHabitacin);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblModelo.setBounds(29, 530, 453, 28);
		panel.add(lblModelo);
		
		JLabel lblPrecioPorDia = new JLabel("Precio por dia");
		lblPrecioPorDia.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioPorDia.setForeground(Color.WHITE);
		lblPrecioPorDia.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblPrecioPorDia.setBounds(29, 603, 453, 28);
		panel.add(lblPrecioPorDia);
		
		JLabel lblhabitacion = new JLabel("");
		lblhabitacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblhabitacion.setForeground(Color.LIGHT_GRAY);
		lblhabitacion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblhabitacion.setBounds(29, 490, 453, 28);
		panel.add(lblhabitacion);
		
		JLabel lblmodelo = new JLabel("");
		lblmodelo.setHorizontalAlignment(SwingConstants.CENTER);
		lblmodelo.setForeground(Color.LIGHT_GRAY);
		lblmodelo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblmodelo.setBounds(29, 563, 453, 28);
		panel.add(lblmodelo);
		
		
		lblprecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblprecio.setForeground(Color.LIGHT_GRAY);
		lblprecio.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblprecio.setBounds(29, 636, 453, 28);
		panel.add(lblprecio);
		
		JLabel lblDasDeEstancia = new JLabel("D\u00EDas de estancia");
		lblDasDeEstancia.setHorizontalAlignment(SwingConstants.CENTER);
		lblDasDeEstancia.setForeground(Color.WHITE);
		lblDasDeEstancia.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDasDeEstancia.setBounds(446, 463, 362, 28);
		panel.add(lblDasDeEstancia);
		
		
		lbldias.setHorizontalAlignment(SwingConstants.CENTER);
		lbldias.setForeground(Color.LIGHT_GRAY);
		lbldias.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbldias.setBounds(446, 490, 362, 28);
		panel.add(lbldias);
		
		JLabel lblTotalAPagar = new JLabel("Total a pagar");
		lblTotalAPagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalAPagar.setForeground(Color.WHITE);
		lblTotalAPagar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTotalAPagar.setBounds(446, 530, 362, 28);
		panel.add(lblTotalAPagar);
		
		
		lblbss.setHorizontalAlignment(SwingConstants.CENTER);
		lblbss.setForeground(new Color(0, 204, 0));
		lblbss.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblbss.setBounds(446, 557, 362, 56);
		panel.add(lblbss);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(628, 159, 325, 242);
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
				"N\u00B0 habitaci\u00F3n", "Disponibilidad"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablareservas.getColumnModel().getColumn(0).setResizable(false);
		tablareservas.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(tablareservas);
		
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		
		headerRenderer.setBackground(new Color(0, 51, 51));
		headerRenderer.setForeground(Color.WHITE);
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);
		headerRenderer.setOpaque(true);
		tablareservas.getTableHeader().setReorderingAllowed(false);
		
		JButton btnaceptar = new JButton("");
		btnaceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate inicio = LocalDate.parse(fechaentrada.getText(), formatter);
				LocalDate fin = LocalDate.parse(fechasalida.getText(), formatter);
				if(comboboxclientes.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un cliente!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(comboboxreservas.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar una reserva para eliminar!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				BD op = new BD();
				op.Conectar("Hotel");
				try {
					String n = comboboxreservas.getSelectedItem().toString().substring(27);
					op.EliminarReserva(Integer.parseInt(n), inicio, fin);
					op.CargarReservas(tablareservas, LocalDate.now());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				op.Desconectar();
				
				lblhabitacion.setText("");
				lbldias.setText("");
				lblmodelo.setText("");
				lblprecio.setText("");
				lblbss.setText("");
				lblnhabitacion.setText("");
				comboboxclientes.setSelectedItem("Seleccionar");
				comboboxreservas.setSelectedItem("Seleccionar");
				
				fechaentrada.setText("");
				fechasalida.setText("");
				
				JOptionPane.showMessageDialog(null, "Reserva eliminada!", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnaceptar.setIcon(new ImageIcon(EliminarReservas.class.getResource("/imagenes/AcceptIcon.png")));
		btnaceptar.setForeground(Color.WHITE);
		btnaceptar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnaceptar.setFocusPainted(false);
		btnaceptar.setBackground(new Color(0, 51, 51));
		btnaceptar.setBounds(900, 603, 53, 39);
		panel.add(btnaceptar);
		
		JButton btnborrar = new JButton("");
		btnborrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblhabitacion.setText("");
				lbldias.setText("");
				lblmodelo.setText("");
				lblprecio.setText("");
				lblbss.setText("");
				lblnhabitacion.setText("");
				comboboxclientes.setSelectedItem("Seleccionar");
				fechaentrada.setText("");
				fechasalida.setText("");
			}
		});
		
		btnborrar.setIcon(new ImageIcon(EliminarReservas.class.getResource("/imagenes/EraserIcon.png")));
		btnborrar.setForeground(Color.WHITE);
		btnborrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnborrar.setFocusPainted(false);
		btnborrar.setBackground(new Color(0, 51, 51));
		btnborrar.setBounds(956, 603, 50, 39);
		panel.add(btnborrar);

		for (int i = 0; i < tablareservas.getModel().getColumnCount(); i++) {
			tablareservas.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
			tablareservas.getColumnModel().getColumn(i).setCellRenderer( headerRenderer );
		     
		}
		
		JDateChooser fechareservas = new JDateChooser();
		fechareservas.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				if ("date".equals(arg0.getPropertyName())) {
					lblReservas.setText("Reservas - " + ((JTextField)fechareservas.getDateEditor().getUiComponent()).getText());
					BD op = new BD();
					op.Conectar("Hotel");
					
					LocalDate localDate = fechareservas.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					try {
						op.CargarReservas(tablareservas, localDate);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					op.Desconectar();
		            }
			}
		});
		fechareservas.setBounds(713, 88, 152, 28);
		panel.add(fechareservas);
		
		comboboxreservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboboxreservas.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					lblhabitacion.setText("");
					lbldias.setText("");
					lblmodelo.setText("");
					lblprecio.setText("");
					lblbss.setText("");
					lblnhabitacion.setText("");
					comboboxclientes.setSelectedItem("Seleccionar");
					fechaentrada.setText("");
					fechasalida.setText("");
					return;
				}
				String nhabitacion = comboboxreservas.getSelectedItem().toString().substring(23);
				String n = comboboxreservas.getSelectedItem().toString().substring(27);
				String fecha = comboboxreservas.getSelectedItem().toString().substring(0, 10);
				lblnhabitacion.setText(nhabitacion);
				lblhabitacion.setText(nhabitacion);
				fechaentrada.setText(fecha);
				BD op = new BD();
				op.Conectar("Hotel");
				int idcliente = 0;
				
				
				  try {
					  String selectSQL = "SELECT Clientes.ID_Cliente FROM Clientes INNER JOIN Reservas ON Clientes.ID_Cliente = Reservas.Cliente WHERE Clientes.Nombre = ?";
					  PreparedStatement preparedStatement = op.conn.prepareStatement(selectSQL);
					preparedStatement.setString(1, comboboxclientes.getSelectedItem().toString());
					  ResultSet rs = preparedStatement.executeQuery();
					  while (rs.next()) {
					  	idcliente = rs.getInt("Clientes.ID_Cliente");
					  }
					  
					 
					
					selectSQL = "SELECT Inicio, Fin, Costo FROM Reservas WHERE Habitacion = ? AND Cliente = ? AND Inicio = ?";
					  preparedStatement = op.conn.prepareStatement(selectSQL);
					  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					  preparedStatement.setInt(1, Integer.parseInt(n));
					  preparedStatement.setInt(2,  idcliente);
					  preparedStatement.setObject(3, LocalDate.parse(fechaentrada.getText(), formatter));
					  rs = preparedStatement.executeQuery();
					  while (rs.next()) {
						LocalDate entrada = rs.getObject("Inicio", LocalDate.class);
						LocalDate salida = rs.getObject("Fin", LocalDate.class);
						long daysBetween = ChronoUnit.DAYS.between(entrada, salida);
						lbldias.setText(daysBetween + " día(s)");
					  	fechasalida.setText(salida.format(formatter));
					  	lblbss.setText(rs.getDouble("Costo") + " BsS");
					  }
					  
					  int modelo = 0;
					  selectSQL = "SELECT Modelo FROM Habitaciones WHERE ID = ?";
					  preparedStatement = op.conn.prepareStatement(selectSQL);
					  preparedStatement.setInt(1, Integer.parseInt(n));
					  rs = preparedStatement.executeQuery();
					  while (rs.next()) {
						modelo = rs.getInt("Modelo");
					  }
					  String mnombre = "";
					  double mprecio = 0.0;
					  selectSQL = "SELECT Modelos.Nombre, Modelos.Precio FROM Modelos INNER JOIN Habitaciones ON Habitaciones.Modelo = Modelos.ID_Modelo WHERE Modelos.ID_Modelo = ?";
					  preparedStatement = op.conn.prepareStatement(selectSQL);
					  preparedStatement.setInt(1, modelo);
					  rs = preparedStatement.executeQuery();
					  while (rs.next()) {
						mnombre = rs.getString("Modelos.Nombre");
						mprecio = rs.getDouble("Modelos.Precio");
						lblmodelo.setText(StringUtils.capitalize(mnombre));
						lblprecio.setText(mprecio + " BsS");
					  }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				
				
				
				
				op.Desconectar();
			}
		});
		
		BD op = new BD();
		try {
			op.ListaClientes(comboboxclientes);
			op.CargarReservas(tablareservas, hoy);
			
		
			
		
			
			
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PantallaReservas.setVisible(true);
	}
}



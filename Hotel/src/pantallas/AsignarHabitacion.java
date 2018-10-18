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

public class AsignarHabitacion {
	private static JTable tablareservas;
	public static JComboBox comboboxclientes;
	

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaReservas = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaReservas.setTitle("Hotel Mi Reina C.A - Asignar habitación");
		PantallaReservas.getContentPane().setBackground(Color.WHITE);
		PantallaReservas.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(AsignarHabitacion.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1012, 700);
		PantallaReservas.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(180, 78, 129, 16);
		panel.add(lblNombre);
		lblNombre.setText(WordUtils.capitalize(PantallaAcceso.Usuario));
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setBounds(531, 78, 129, 16);
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
		
		JLabel lblNewLabel = new JLabel("Hotel Mi Reina C.A - Asignar habitaci\u00F3n");
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
		lblUTurno.setBounds(739, 78, 129, 16);
		panel.add(lblUTurno);
		
		lblUTurno.setText(WordUtils.capitalize(PantallaAcceso.Turno));
		
		JButton btnSalir = new JButton(" Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaReservas.dispose();
			
			}
		});
		btnSalir.setIcon(new ImageIcon(AsignarHabitacion.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setFocusPainted(false);
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setBounds(900, 644, 106, 37);
		panel.add(btnSalir);
		
		
		
		JLabel lblCuartos = new JLabel("Asignar habitaci\u00F3n");
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
		lbldepartamento.setBounds(321, 78, 129, 16);
		panel.add(lbldepartamento);
		
		JLabel lblNewLabel_1 = new JLabel("Cliente");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_1.setBounds(19, 215, 116, 16);
		panel.add(lblNewLabel_1);
		
		AsignarHabitacion.comboboxclientes = new JComboBox();
		comboboxclientes.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboboxclientes.setBounds(147, 210, 197, 26);
		panel.add(comboboxclientes);
		
		PantallaReservas.setIconImage(Toolkit.getDefaultToolkit().getImage(AsignarHabitacion.class.getResource("/imagenes/HotelIcon.png")));
		PantallaReservas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaReservas.setResizable(false);
		PantallaReservas.setSize(1024, 724);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaReservas.setLocation(dim.width/2-PantallaReservas.getSize().width/2, dim.height/2-PantallaReservas.getSize().height/2);
		
		JButton btnregistarcliente = new JButton("Registrar nuevo cliente");
		btnregistarcliente.setFocusPainted(false);
		btnregistarcliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrarCliente.main(args);
			}
		});
		btnregistarcliente.setBackground(new Color(0, 51, 51));
		btnregistarcliente.setForeground(new Color(255, 255, 255));
		btnregistarcliente.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnregistarcliente.setBounds(356, 209, 158, 28);
		panel.add(btnregistarcliente);
		
		JLabel lblHabitacin = new JLabel("Habitaci\u00F3n");
		lblHabitacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblHabitacin.setForeground(Color.LIGHT_GRAY);
		lblHabitacin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblHabitacin.setBounds(19, 259, 116, 16);
		panel.add(lblHabitacin);
		
		
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de entrada");
		lblFechaDeInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaDeInicio.setForeground(Color.LIGHT_GRAY);
		lblFechaDeInicio.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblFechaDeInicio.setBounds(19, 298, 116, 16);
		panel.add(lblFechaDeInicio);
		
		JLabel lblFechaDeSalida = new JLabel("Fecha de salida");
		lblFechaDeSalida.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaDeSalida.setForeground(Color.LIGHT_GRAY);
		lblFechaDeSalida.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblFechaDeSalida.setBounds(29, 335, 106, 28);
		panel.add(lblFechaDeSalida);
		
		JLabel lbldias = new JLabel("");
		JLabel lblprecio = new JLabel("");
		JLabel lblbss = new JLabel("");
		
		JComboBox comboboxhabitaciones = new JComboBox();
		
		JDateChooser fechasalida = new JDateChooser();
		fechasalida.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				 if ("date".equals(arg0.getPropertyName())) {
					 LocalDateTime from = LocalDate.now().atStartOfDay();
					 LocalDateTime to = LocalDateTime.ofInstant(fechasalida.getDate().toInstant(), ZoneId.systemDefault());

					 Duration d = Duration.between(from, to);
					 lbldias.setText(d.toDays() + " día(s)");
					
					if(!comboboxhabitaciones.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
						double precio = Double.parseDouble(lblprecio.getText().replaceAll("[^\\d.]", ""));
						 double total = precio * d.toDays();
						 DecimalFormat df = new DecimalFormat("#,###.##");
						 lblbss.setText(df.format(total) + " BsS");
					}
					 
					 
					 
		            }
			}
		});
		fechasalida.setFont(new Font("Segoe UI", Font.BOLD, 12));
		fechasalida.setBounds(175, 335, 139, 28);
		fechasalida.setMinSelectableDate(java.sql.Date.valueOf( hoy.plusDays(1) ));
		panel.add(fechasalida);
		
		JLabel lblReservas = new JLabel("Habitaciones Disponibles - 16/10/2018");
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
		
		
		comboboxhabitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboboxhabitaciones.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					lblhabitacion.setText("");
					lbldias.setText("");
					lblmodelo.setText("");
					lblprecio.setText("");
					lblbss.setText("");
					
				
					((JTextField)fechasalida.getDateEditor().getUiComponent()).setText("");
					
					return;
				}
				if(tablareservas.getModel().getValueAt(Integer.parseInt(comboboxhabitaciones.getSelectedItem().toString()) - 1, 1).toString().equalsIgnoreCase("Ocupada")){
					JOptionPane.showMessageDialog(null, "<html><b>Esta habitación ya se encuentrada reservada!<br />"
							+ "<center>Seleccione otra</center></b></html>", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					comboboxhabitaciones.setSelectedItem("Seleccionar");
					return;
				}
				lblhabitacion.setText("N° " + comboboxhabitaciones.getSelectedItem().toString());
				BD op = new BD();
				String modelo = null;
				op.Conectar("Hotel");
				
				if(!comboboxhabitaciones.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					String selectSQL = "SELECT Modelos.Nombre, Modelos.Precio FROM Modelos INNER JOIN habitaciones ON Modelos.ID_Modelo = habitaciones.Modelo WHERE Habitaciones.ID = ?";
					  
					try {
						PreparedStatement preparedStatement = op.conn.prepareStatement(selectSQL);
						preparedStatement.setInt(1, Integer.parseInt(comboboxhabitaciones.getSelectedItem().toString()));
						ResultSet rs = preparedStatement.executeQuery();
					    //STEP 5: Extract data from result set
					    while(rs.next()){
					       modelo = null;
					       modelo = rs.getString("Modelos.Nombre");
					       double precio = rs.getDouble("Modelos.Precio");
					       lblmodelo.setText(StringUtils.capitalize(modelo));
					       lblprecio.setText(precio + " BsS");
					      
					       
					      
					    }
					} catch (NumberFormatException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					
					 
					 if(!((JTextField)fechasalida.getDateEditor().getUiComponent()).getText().equalsIgnoreCase("")){
			 				LocalDateTime from = LocalDateTime.ofInstant(LocalDate.now().atStartOfDay().toInstant(null), ZoneId.systemDefault());
			 				LocalDateTime to = LocalDateTime.ofInstant(fechasalida.getDate().toInstant(), ZoneId.systemDefault());

			 				Duration d = Duration.between(from, to);	
			
			
							double precio = Double.parseDouble(lblprecio.getText().replaceAll("[^\\d.]", ""));
							 double total = precio * d.toDays();
							 DecimalFormat df = new DecimalFormat("#,###.##");
							 lblbss.setText(df.format(total) + " BsS");
						}
					
					
					
				}
				
				
				  
				
			}
		});
		comboboxhabitaciones.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboboxhabitaciones.setBounds(147, 254, 197, 26);
		panel.add(comboboxhabitaciones);
		
		JButton btnaceptar = new JButton("");
		btnaceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboboxclientes.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un cliente!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(comboboxhabitaciones.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar una habitación!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
			
				if(((JTextField)fechasalida.getDateEditor().getUiComponent()).getText().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar una fecha de salida!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				BD op = new BD();
				op.Conectar("Hotel");
				
				
				 LocalDateTime salida = LocalDateTime.ofInstant(fechasalida.getDate().toInstant(), ZoneId.systemDefault());
				try {
					if(op.BuscarReserva(Integer.parseInt(comboboxhabitaciones.getSelectedItem().toString()), LocalDate.now().atStartOfDay(), salida) == true){
						
						return;
					}
					String conversion = lblbss.getText().replaceAll("\\.", "");
					String conv2 = conversion.replace(",", ".");
					String end = conv2.replaceAll("[^\\d.]", "");
				
					
					Double total = Double.parseDouble(end);
				
					op.IngresarReserva(Integer.parseInt(comboboxhabitaciones.getSelectedItem().toString()), comboboxclientes.getSelectedItem().toString(),
							LocalDate.now().atStartOfDay(), salida, total);
					
					op.CargarReservas(tablareservas, hoy);
					
					JOptionPane.showMessageDialog(null, "<html><b><center>Reserva exitosa!</center></b></html>", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
					
					
					
				} catch (SQLException e) {
					op.Desconectar();
					e.printStackTrace();
				}
				
				lblhabitacion.setText("");
				lbldias.setText("");
				lblmodelo.setText("");
				lblprecio.setText("");
				lblbss.setText("");
				comboboxhabitaciones.setSelectedItem("Seleccionar");
				comboboxclientes.setSelectedItem("Seleccionar");
				
				((JTextField)fechasalida.getDateEditor().getUiComponent()).setText("");
			}
		});
		btnaceptar.setIcon(new ImageIcon(AsignarHabitacion.class.getResource("/imagenes/AcceptIcon.png")));
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
				comboboxhabitaciones.setSelectedItem("Seleccionar");
				comboboxclientes.setSelectedItem("Seleccionar");
				
				((JTextField)fechasalida.getDateEditor().getUiComponent()).setText("");
			}
		});
		
		btnborrar.setIcon(new ImageIcon(AsignarHabitacion.class.getResource("/imagenes/EraserIcon.png")));
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
		

		JLabel lblentrada = new JLabel("");
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		lblentrada.setHorizontalAlignment(SwingConstants.CENTER);
		lblentrada.setForeground(Color.YELLOW);
		lblentrada.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblentrada.setBounds(175, 298, 139, 16);
		panel.add(lblentrada);
		
		lblentrada.setText(LocalDate.now().format(formatters));
		
		
		BD op = new BD();
		try {
			op.ListaClientes(comboboxclientes);
			op.ListaHabitaciones(comboboxhabitaciones);
			op.CargarReservas(tablareservas, hoy);
			
			
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PantallaReservas.setVisible(true);
	}
}



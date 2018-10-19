package frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

public class AjustarHabitaciones {
	private static JTextField txthabitaciones;
	private static JTable tablahabitaciones;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaVisualizarCuartos = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaVisualizarCuartos.setTitle("Hotel Mi Reina C.A - Ajustar Habitaciones");
		PantallaVisualizarCuartos.getContentPane().setBackground(Color.WHITE);
		PantallaVisualizarCuartos.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(AjustarHabitaciones.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setDoubleBuffered(false);
		panel.setFocusable(false);
		panel.setFocusTraversalKeysEnabled(false);
		panel.setOpaque(false);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1012, 640);
		PantallaVisualizarCuartos.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
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
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setBounds(455, 78, 200, 16);
		panel.add(lblCargo);
		
		JLabel lblTurno_1 = new JLabel("Turno");
		lblTurno_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurno_1.setForeground(Color.WHITE);
		lblTurno_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTurno_1.setBounds(667, 78, 200, 16);
		panel.add(lblTurno_1);
		
		
		
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
		
		
		
		JButton btnSalir = new JButton(" Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaVisualizarCuartos.dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(AjustarHabitaciones.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setFocusPainted(false);
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setBounds(900, 597, 106, 37);
		panel.add(btnSalir);
		
		
		
		PantallaVisualizarCuartos.setIconImage(Toolkit.getDefaultToolkit().getImage(AjustarHabitaciones.class.getResource("/imagenes/HotelIcon.png")));
		PantallaVisualizarCuartos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaVisualizarCuartos.setResizable(false);
		PantallaVisualizarCuartos.setSize(1024, 668);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaVisualizarCuartos.setLocation(dim.width/2-PantallaVisualizarCuartos.getSize().width/2, dim.height/2-PantallaVisualizarCuartos.getSize().height/2);
		
		
		
		lblNombre.setText(WordUtils.capitalizeFully(PantallaAcceso.Usuario));
		lblCargo.setText(WordUtils.capitalizeFully(PantallaAcceso.cargo));
		lblTurno_1.setText(WordUtils.capitalizeFully(PantallaAcceso.Turno));
		
		JLabel lblAgregarCargo = new JLabel("Ajuste de habitaciones");
		lblAgregarCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarCargo.setForeground(Color.WHITE);
		lblAgregarCargo.setFont(new Font("Script MT Bold", Font.BOLD, 31));
		lblAgregarCargo.setBounds(6, 106, 1000, 44);
		panel.add(lblAgregarCargo);
		
		JLabel lblNewLabel_1 = new JLabel("N\u00B0 Total de habitaciones");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBounds(69, 176, 139, 16);
		panel.add(lblNewLabel_1);
		

		JComboBox comboboxmodelo = new JComboBox();
		comboboxmodelo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
		comboboxmodelo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		comboboxmodelo.setBounds(398, 245, 227, 28);
		panel.add(comboboxmodelo);
		
		
		JComboBox comboboxhabitacion = new JComboBox();
		comboboxhabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboboxhabitacion.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")) return;
				comboboxmodelo.setSelectedItem(tablahabitaciones.getValueAt(Integer.parseInt(comboboxhabitacion.getSelectedItem().toString()) - 1, 1));
				if(tablahabitaciones.getValueAt(Integer.parseInt(comboboxhabitacion.getSelectedItem().toString()) - 1, 1).equals("")){
					comboboxmodelo.setSelectedItem("Seleccionar");
				}
			}
		});
		comboboxhabitacion.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
		comboboxhabitacion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		comboboxhabitacion.setBounds(444, 216, 117, 28);
		panel.add(comboboxhabitacion);
		
		txthabitaciones = new JTextField();
		txthabitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txthabitaciones.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe escribir una cantidad de habitaciones!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txthabitaciones.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "Error: Este campo solo acepta números enteros!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txthabitaciones.requestFocus();
					txthabitaciones.selectAll();
					return;
				}
				BD op = new BD();
				op.Conectar("Hotel");
				try {
					op.CrearHabitaciones(Integer.parseInt(txthabitaciones.getText()));
					JOptionPane.showMessageDialog(null, "Un total de " + txthabitaciones.getText() + " habitaciones han sido creadas para el Hotel Mi Reina", "Hotel Mi Reina C.A", 
							JOptionPane.INFORMATION_MESSAGE);
					txthabitaciones.setText("");
					op.ListaHabitaciones(comboboxhabitacion);
					op.CargarHabitaciones(tablahabitaciones);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		txthabitaciones.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txthabitaciones.setBackground(new Color(0, 0, 0, 0));
		txthabitaciones.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txthabitaciones.setHorizontalAlignment(SwingConstants.CENTER);
		txthabitaciones.setForeground(Color.WHITE);
		txthabitaciones.setBounds(231, 170, 72, 28);
		panel.add(txthabitaciones);
		txthabitaciones.setColumns(10);
		
		JButton btncrear = new JButton("Crear habitaciones");
		btncrear.setFocusPainted(false);
		btncrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txthabitaciones.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe escribir una cantidad de habitaciones!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txthabitaciones.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "Error: Este campo solo acepta números enteros!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txthabitaciones.requestFocus();
					txthabitaciones.selectAll();
					return;
				}
				BD op = new BD();
				op.Conectar("Hotel");
				try {
					op.CrearHabitaciones(Integer.parseInt(txthabitaciones.getText()));
					JOptionPane.showMessageDialog(null, "Un total de " + txthabitaciones.getText() + " habitaciones han sido creadas para el Hotel Mi Reina", "Hotel Mi Reina C.A", 
							JOptionPane.INFORMATION_MESSAGE);
					txthabitaciones.setText("");
					op.ListaHabitaciones(comboboxhabitacion);
					op.CargarHabitaciones(tablahabitaciones);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btncrear.setForeground(new Color(255, 255, 255));
		btncrear.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btncrear.setBackground(new Color(0, 51, 51));
		btncrear.setBounds(315, 170, 149, 28);
		panel.add(btncrear);
		
		JLabel lblElegirModeloDe = new JLabel("Elegir modelo de habitaci\u00F3n");
		lblElegirModeloDe.setForeground(Color.LIGHT_GRAY);
		lblElegirModeloDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblElegirModeloDe.setBounds(69, 250, 164, 16);
		panel.add(lblElegirModeloDe);
		
		JLabel lblNHabitacin = new JLabel("N\u00B0 habitaci\u00F3n");
		lblNHabitacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNHabitacin.setForeground(Color.LIGHT_GRAY);
		lblNHabitacin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNHabitacin.setBounds(244, 222, 139, 16);
		panel.add(lblNHabitacin);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
		lblModelo.setForeground(Color.LIGHT_GRAY);
		lblModelo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblModelo.setBounds(244, 250, 139, 16);
		panel.add(lblModelo);
		
		
		
		
		
		
		
		
		JButton btnactualizar = new JButton("Actualizar");
		btnactualizar.setFocusPainted(false);
		btnactualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboboxhabitacion.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar una habitación para actualizar!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(comboboxmodelo.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un modelo de habitación!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				BD op = new BD();
				op.Conectar("Hotel");
				try {
					op.ActualizarHabitacion(Integer.parseInt(comboboxhabitacion.getSelectedItem().toString()), comboboxmodelo.getSelectedItem().toString());
					op.CargarHabitaciones(tablahabitaciones);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Habitación N° " + comboboxhabitacion.getSelectedItem().toString() + 
						" modificada al modelo " + comboboxmodelo.getSelectedItem().toString(), "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
				
				comboboxhabitacion.setSelectedItem("Seleccionar");
				comboboxmodelo.setSelectedItem("Seleccionar");
				
			}
		});
		btnactualizar.setForeground(Color.WHITE);
		btnactualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnactualizar.setBackground(new Color(0, 51, 51));
		btnactualizar.setBounds(656, 244, 106, 28);
		panel.add(btnactualizar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 292, 843, 342);
		panel.add(scrollPane);
		
		tablahabitaciones = new JTable();
		tablahabitaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				comboboxhabitacion.setSelectedItem(tablahabitaciones.getValueAt(tablahabitaciones.getSelectedRow(), 0));
				comboboxmodelo.setSelectedItem(tablahabitaciones.getValueAt(tablahabitaciones.getSelectedRow(), 1));
				if(tablahabitaciones.getValueAt(tablahabitaciones.getSelectedRow(), 1).equals("")) comboboxmodelo.setSelectedItem("Seleccionar");
			}
		});
		tablahabitaciones.setSelectionForeground(new Color(0, 51, 51));
		tablahabitaciones.setSelectionBackground(Color.WHITE);
		tablahabitaciones.setFont(new Font("SansSerif", Font.BOLD, 12));
		tablahabitaciones.setForeground(Color.WHITE);
		tablahabitaciones.setShowGrid(false);
		tablahabitaciones.setBackground(new Color(0, 51, 51));
		tablahabitaciones.setFillsViewportHeight(true);
		tablahabitaciones.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Modelo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablahabitaciones.getColumnModel().getColumn(0).setResizable(false);
		tablahabitaciones.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(tablahabitaciones);
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		
		headerRenderer.setBackground(new Color(0, 51, 51));
		headerRenderer.setForeground(Color.WHITE);
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);
		headerRenderer.setOpaque(true);
		tablahabitaciones.getTableHeader().setReorderingAllowed(false);

		for (int i = 0; i < tablahabitaciones.getModel().getColumnCount(); i++) {
			tablahabitaciones.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
			tablahabitaciones.getColumnModel().getColumn(i).setCellRenderer( headerRenderer );
		     
		}
		
		BD op = new BD();
		op.Conectar("Hotel");
		try {
			op.ListaCuartos(comboboxmodelo);
			op.CargarHabitaciones(tablahabitaciones);
			op.ListaHabitaciones(comboboxhabitacion);
			op.Conectar("Hotel");
			Statement stmt = op.conn.createStatement();
			
		    String sql = "SELECT Count(*) FROM Habitaciones";
		    ResultSet rs = stmt.executeQuery(sql);
		    //STEP 5: Extract data from result set
		    while(rs.next()){
		       //Retrieve by column name
		      
		       
		     

		       txthabitaciones.setText(Integer.toString(rs.getInt(1)));
		    }
		    rs.close();
		    op.Desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		PantallaVisualizarCuartos.setVisible(true);
		
		comboboxhabitacion.requestFocus();
	}
}



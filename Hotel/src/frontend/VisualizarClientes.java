package frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VisualizarClientes {
	private static JTable tablaclientes;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaPerfil = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaPerfil.setTitle("Hotel Mi Reina C.A - Visualizar Clientes");
		PantallaPerfil.getContentPane().setBackground(Color.WHITE);
		PantallaPerfil.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(VisualizarClientes.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1012, 700);
		PantallaPerfil.getContentPane().add(panel);
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
		
		JLabel lblTurno_1 = new JLabel(StringUtils.capitalize(PantallaAcceso.Turno));
		lblTurno_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurno_1.setForeground(Color.WHITE);
		lblTurno_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTurno_1.setBounds(667, 78, 200, 16);
		panel.add(lblTurno_1);
		
		
		LocalDateTime ldt = LocalDateTime.now();
		
		JLabel lblNewLabel = new JLabel("Hotel Mi Reina C.A");
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Script MT Bold", Font.BOLD, 29));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(19, 19, 855, 47);
		panel.add(lblNewLabel);
		
		
		
		JButton btnSalir = new JButton(" Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPerfil.dispose();
				
			}
		});
		btnSalir.setIcon(new ImageIcon(VisualizarClientes.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setFocusPainted(false);
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setBounds(863, 597, 129, 37);
		panel.add(btnSalir);
		
		JLabel lblNombres = new JLabel("");
		lblNombres.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombres.setForeground(Color.LIGHT_GRAY);
		lblNombres.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNombres.setBounds(78, 233, 195, 16);
		panel.add(lblNombres);
		
		JLabel lblCedula = new JLabel("");
		lblCedula.setHorizontalAlignment(SwingConstants.CENTER);
		lblCedula.setForeground(Color.LIGHT_GRAY);
		lblCedula.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCedula.setBounds(33, 321, 277, 16);
		panel.add(lblCedula);
		
		JLabel lblRIF = new JLabel("");
		lblRIF.setHorizontalAlignment(SwingConstants.CENTER);
		lblRIF.setForeground(Color.LIGHT_GRAY);
		lblRIF.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblRIF.setBounds(33, 405, 277, 16);
		panel.add(lblRIF);
		
		JLabel lblDireccion = new JLabel("");
		lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccion.setForeground(Color.LIGHT_GRAY);
		lblDireccion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDireccion.setBounds(322, 233, 179, 16);
		panel.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setForeground(Color.LIGHT_GRAY);
		lblTelefono.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTelefono.setBounds(322, 321, 179, 16);
		panel.add(lblTelefono);
		
		JLabel lblCorreo = new JLabel("");
		lblCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorreo.setForeground(Color.LIGHT_GRAY);
		lblCorreo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCorreo.setBounds(322, 405, 179, 16);
		panel.add(lblCorreo);
		
		JComboBox comboboxclientes = new JComboBox();
		comboboxclientes.setModel(new DefaultComboBoxModel(new String[] {"Nombre", "Cedula", "Direcci\u00F3n"}));
		comboboxclientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD op = new BD();
				if(comboboxclientes.getSelectedItem().toString().equalsIgnoreCase("Nombre")){
					
					op.Conectar("Hotel");
					Statement stmt;
					try {
						stmt = op.conn.createStatement();
						DefaultTableModel dm = (DefaultTableModel) tablaclientes.getModel();
						int rowCount = dm.getRowCount();
						//Remove rows one by one from the end of the table
						for (int i = rowCount - 1; i >= 0; i--) {
						    dm.removeRow(i);
						}

					    String sql = "SELECT Nombre, Cedula, Telefono, Direccion, Correo, RIF FROM Clientes Order By Nombre Asc";
					    ResultSet rs = stmt.executeQuery(sql);
					    //STEP 5: Extract data from result set
					    while(rs.next()){
					       //Retrieve by column name
					       
					      
					       String nombres = rs.getString("Nombre");
					       int cedula = rs.getInt("Cedula");
					       String telefono = rs.getString("Telefono");
					       String direccion = rs.getString("Direccion");
					       String correo = rs.getString("correo");
					       String rif = rs.getString("RIF");

					       DefaultTableModel model = (DefaultTableModel) tablaclientes.getModel();
							model.addRow(new Object[]{tablaclientes.getRowCount() + 1, WordUtils.capitalizeFully(nombres), "V-" + cedula, telefono, WordUtils.capitalizeFully(direccion), correo, rif.toUpperCase()});
					      
					    }
					    rs.close();
					    op.Desconectar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
					return;
				}
				if(comboboxclientes.getSelectedItem().toString().equalsIgnoreCase("Cedula")){
					op.Conectar("Hotel");
					Statement stmt;
					try {
						stmt = op.conn.createStatement();
						DefaultTableModel dm = (DefaultTableModel) tablaclientes.getModel();
						int rowCount = dm.getRowCount();
						//Remove rows one by one from the end of the table
						for (int i = rowCount - 1; i >= 0; i--) {
						    dm.removeRow(i);
						}

					    String sql = "SELECT Nombre, Cedula, Telefono, Direccion, Correo, RIF FROM Clientes Order By Cedula Desc";
					    ResultSet rs = stmt.executeQuery(sql);
					    //STEP 5: Extract data from result set
					    while(rs.next()){
					       //Retrieve by column name
					       
					      
					       String nombres = rs.getString("Nombre");
					       int cedula = rs.getInt("Cedula");
					       String telefono = rs.getString("Telefono");
					       String direccion = rs.getString("Direccion");
					       String correo = rs.getString("correo");
					       String rif = rs.getString("RIF");

					       DefaultTableModel model = (DefaultTableModel) tablaclientes.getModel();
							model.addRow(new Object[]{tablaclientes.getRowCount() + 1, WordUtils.capitalizeFully(nombres), "V-" + cedula, telefono, WordUtils.capitalizeFully(direccion), correo, rif.toUpperCase()});
					      
					    }
					    rs.close();
					    op.Desconectar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				}
				if(comboboxclientes.getSelectedItem().toString().equalsIgnoreCase("Dirección")){
					op.Conectar("Hotel");
					Statement stmt;
					try {
						stmt = op.conn.createStatement();
						DefaultTableModel dm = (DefaultTableModel) tablaclientes.getModel();
						int rowCount = dm.getRowCount();
						//Remove rows one by one from the end of the table
						for (int i = rowCount - 1; i >= 0; i--) {
						    dm.removeRow(i);
						}

					    String sql = "SELECT Nombre, Cedula, Telefono, Direccion, Correo, RIF FROM Clientes Order By Direccion Asc";
					    ResultSet rs = stmt.executeQuery(sql);
					    //STEP 5: Extract data from result set
					    while(rs.next()){
					       //Retrieve by column name
					       
					    
					       String nombres = rs.getString("Nombre");
					       int cedula = rs.getInt("Cedula");
					       String telefono = rs.getString("Telefono");
					       String direccion = rs.getString("Direccion");
					       String correo = rs.getString("correo");
					       String rif = rs.getString("RIF");

					       DefaultTableModel model = (DefaultTableModel) tablaclientes.getModel();
							model.addRow(new Object[]{tablaclientes.getRowCount() + 1, WordUtils.capitalizeFully(nombres), "V-" + cedula, telefono, WordUtils.capitalizeFully(direccion), correo, rif.toUpperCase()});
					      
					    }
					    rs.close();
					    op.Desconectar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				}
				
			
			}
		});
		comboboxclientes.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboboxclientes.setBounds(808, 171, 179, 28);
		panel.add(comboboxclientes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 211, 962, 374);
		panel.add(scrollPane);
		
		tablaclientes = new JTable();
		tablaclientes.setBackground(new Color(0, 51, 51));
		tablaclientes.setFillsViewportHeight(true);
		tablaclientes.setGridColor(new Color(0, 0, 0, 0));
		tablaclientes.setFont(new Font("SansSerif", Font.BOLD, 12));
		tablaclientes.setShowVerticalLines(true);
		tablaclientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"<html><b>N°</b></html>", "<html><b>Nombre</b></html>", "<html><b>C\u00E9dula</b></html>", "<html><b>Telefono</b></html>", "<html><b>Direcci\u00F3n</b></html>", "<html><b>Correo</b></html>", "<html><b>RIF</b></html>"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaclientes.getColumnModel().getColumn(0).setResizable(false);
		tablaclientes.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablaclientes.getColumnModel().getColumn(0).setMinWidth(40);
		tablaclientes.getColumnModel().getColumn(0).setMaxWidth(77);
		tablaclientes.getColumnModel().getColumn(1).setResizable(false);
		tablaclientes.getColumnModel().getColumn(2).setResizable(false);
		tablaclientes.getColumnModel().getColumn(3).setResizable(false);
		tablaclientes.getColumnModel().getColumn(4).setResizable(false);
		tablaclientes.getColumnModel().getColumn(5).setResizable(false);
		tablaclientes.getColumnModel().getColumn(6).setResizable(false);
		tablaclientes.setShowHorizontalLines(true);
		scrollPane.setViewportView(tablaclientes);
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		
		headerRenderer.setBackground(new Color(0, 51, 51));
		headerRenderer.setForeground(Color.WHITE);
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);
		headerRenderer.setOpaque(true);
		tablaclientes.getTableHeader().setReorderingAllowed(false);

		for (int i = 0; i < tablaclientes.getModel().getColumnCount(); i++) {
			tablaclientes.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
			tablaclientes.getColumnModel().getColumn(i).setCellRenderer( headerRenderer );
		     
		}
		
		JLabel lblListaDeClientes = new JLabel("Lista de Clientes");
		lblListaDeClientes.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		lblListaDeClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeClientes.setForeground(Color.WHITE);
		lblListaDeClientes.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblListaDeClientes.setBounds(347, 152, 308, 47);
		panel.add(lblListaDeClientes);
		
		PantallaPerfil.setIconImage(Toolkit.getDefaultToolkit().getImage(VisualizarClientes.class.getResource("/imagenes/HotelIcon.png")));
		PantallaPerfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaPerfil.setResizable(false);
		PantallaPerfil.setSize(1024, 724);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaPerfil.setLocation(dim.width/2-PantallaPerfil.getSize().width/2, dim.height/2-PantallaPerfil.getSize().height/2);
		
		JLabel lblCliente = new JLabel("Ordenar por");
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCliente.setForeground(Color.WHITE);
		lblCliente.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCliente.setBounds(697, 177, 109, 16);
		panel.add(lblCliente);
		
		
		
		BD op = new BD();
		try {
			op.CargarClientes(tablaclientes);
			
			
			
		
			
		
			
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PantallaPerfil.setVisible(true);
		
		
	}
}



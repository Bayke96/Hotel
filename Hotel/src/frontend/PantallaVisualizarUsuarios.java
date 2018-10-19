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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

public class PantallaVisualizarUsuarios {
	private static JTable tablacargos;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaVisualizarUsuarios = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaVisualizarUsuarios.setTitle("Hotel Mi Reina C.A - Visualizar Usuarios");
		PantallaVisualizarUsuarios.getContentPane().setBackground(Color.WHITE);
		PantallaVisualizarUsuarios.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(PantallaVisualizarUsuarios.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setDoubleBuffered(false);
		panel.setFocusable(false);
		panel.setFocusTraversalKeysEnabled(false);
		panel.setOpaque(false);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1012, 640);
		PantallaVisualizarUsuarios.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(19, 78, 198, 16);
		panel.add(lblNombre);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setBounds(447, 78, 198, 16);
		panel.add(lblCargo);
		
		
		
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
		lblTurno_1.setBounds(669, 78, 198, 16);
		panel.add(lblTurno_1);
		
		JButton btnSalir = new JButton(" Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaVisualizarUsuarios.dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(PantallaVisualizarUsuarios.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setFocusPainted(false);
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setBounds(565, 137, 113, 37);
		panel.add(btnSalir);
		
		

		JComboBox comboBoxOrden = new JComboBox();
		comboBoxOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BD op = new BD();
				
				
				if(comboBoxOrden.getSelectedItem().toString().equalsIgnoreCase("Usuario")){
					
					DefaultTableModel dm = (DefaultTableModel) tablacargos.getModel();
					int rowCount = dm.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					
					try{
						op.Conectar("Hotel");
						Statement stmt = op.conn.createStatement();
						
						String sql = "SELECT Nombre_Usuario, Nombres, Apellidos, Cedula, Direccion, Telefono, "
					    		+ "Correo, Departamento, Cargo, RIF, Turno FROM Usuarios ORDER BY Nombre_Usuario Asc";
					    ResultSet rs = stmt.executeQuery(sql);
					    //STEP 5: Extract data from result set
					    while(rs.next()){
						       //Retrieve by column name
						 
						       String username = rs.getString("Nombre_Usuario");
						       String turno = rs.getString("Turno");
						       int departamento = rs.getInt("Departamento");
						       int cargo = rs.getInt("Cargo");
						       String names = rs.getString("Nombres");
						       String lastnames = rs.getString("Apellidos");
						       int cedula = rs.getInt("Cedula");
						       String direccion = rs.getString("Direccion");
						       String telefono = rs.getString("Telefono");
						       String correo = rs.getString("Correo");
						       
						       String RIF = rs.getString("RIF");
						       
						       

						       DefaultTableModel model = (DefaultTableModel) tablacargos.getModel();
						       if(cedula != 0) model.addRow(new Object[]{tablacargos.getRowCount() + 1, WordUtils.capitalizeFully(username),WordUtils.capitalizeFully(turno),
										op.DestransformarCargo(departamento, cargo), WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "V-" + cedula, 
										WordUtils.capitalizeFully(direccion), telefono, correo, RIF.toUpperCase()});
								if(cedula == 0) model.addRow(new Object[]{tablacargos.getRowCount() + 1, WordUtils.capitalizeFully(username),WordUtils.capitalizeFully(turno),
										op.DestransformarCargo(departamento, cargo) , WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "", 
										WordUtils.capitalizeFully(direccion), telefono, correo, RIF.toUpperCase()});
						      
						    }
					    rs.close();
					    op.Desconectar();
					}
					catch(Exception ae){
						System.err.println(ae);
					}
					
					
					
					
					
					return;
				}
				if(comboBoxOrden.getSelectedItem().toString().equalsIgnoreCase("Nombres")){
					
					DefaultTableModel dm = (DefaultTableModel) tablacargos.getModel();
					int rowCount = dm.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					
					try{
						op.Conectar("Hotel");
						Statement stmt = op.conn.createStatement();
						
						String sql = "SELECT Nombre_Usuario, Nombres, Apellidos, Cedula, Direccion, Telefono, "
					    		+ "Correo, Departamento, Cargo, RIF, Turno FROM Usuarios ORDER BY Nombres Asc";
					    ResultSet rs = stmt.executeQuery(sql);
					    //STEP 5: Extract data from result set
					    while(rs.next()){
						       //Retrieve by column name
						 
						       String username = rs.getString("Nombre_Usuario");
						       String turno = rs.getString("Turno");
						       int departamento = rs.getInt("Departamento");
						       int cargo = rs.getInt("Cargo");
						       String names = rs.getString("Nombres");
						       String lastnames = rs.getString("Apellidos");
						       int cedula = rs.getInt("Cedula");
						       String direccion = rs.getString("Direccion");
						       String telefono = rs.getString("Telefono");
						       String correo = rs.getString("Correo");
						       
						       String RIF = rs.getString("RIF");
						       
						       

						       DefaultTableModel model = (DefaultTableModel) tablacargos.getModel();
						       if(cedula != 0) model.addRow(new Object[]{tablacargos.getRowCount() + 1, WordUtils.capitalizeFully(username),WordUtils.capitalizeFully(turno),
										op.DestransformarCargo(departamento, cargo), WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "V-" + cedula, 
										WordUtils.capitalizeFully(direccion), telefono, correo, RIF.toUpperCase()});
								if(cedula == 0) model.addRow(new Object[]{tablacargos.getRowCount() + 1, WordUtils.capitalizeFully(username),WordUtils.capitalizeFully(turno),
										op.DestransformarCargo(departamento, cargo) , WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "", 
										WordUtils.capitalizeFully(direccion), telefono, correo, RIF.toUpperCase()});
						      
						    }
					    rs.close();
					    op.Desconectar();
					}
					catch(Exception ae){
						System.err.println(ae);
					}
					
					
					
					
					
					return;
				}
				if(comboBoxOrden.getSelectedItem().toString().equalsIgnoreCase("Apellidos")){
					
					DefaultTableModel dm = (DefaultTableModel) tablacargos.getModel();
					int rowCount = dm.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					
					try{
						op.Conectar("Hotel");
						Statement stmt = op.conn.createStatement();
						
						String sql = "SELECT Nombre_Usuario, Nombres, Apellidos, Cedula, Direccion, Telefono, "
					    		+ "Correo, Departamento, Cargo, RIF, Turno FROM Usuarios ORDER BY Apellidos Asc";
					    ResultSet rs = stmt.executeQuery(sql);
					    //STEP 5: Extract data from result set
					    while(rs.next()){
						       //Retrieve by column name
						 
						       String username = rs.getString("Nombre_Usuario");
						       String turno = rs.getString("Turno");
						       int departamento = rs.getInt("Departamento");
						       int cargo = rs.getInt("Cargo");
						       String names = rs.getString("Nombres");
						       String lastnames = rs.getString("Apellidos");
						       int cedula = rs.getInt("Cedula");
						       String direccion = rs.getString("Direccion");
						       String telefono = rs.getString("Telefono");
						       String correo = rs.getString("Correo");
						       
						       String RIF = rs.getString("RIF");
						       
						       

						       DefaultTableModel model = (DefaultTableModel) tablacargos.getModel();
						       if(cedula != 0) model.addRow(new Object[]{tablacargos.getRowCount() + 1, WordUtils.capitalizeFully(username),WordUtils.capitalizeFully(turno),
										op.DestransformarCargo(departamento, cargo), WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "V-" + cedula, 
										WordUtils.capitalizeFully(direccion), telefono, correo, RIF.toUpperCase()});
								if(cedula == 0) model.addRow(new Object[]{tablacargos.getRowCount() + 1, WordUtils.capitalizeFully(username),WordUtils.capitalizeFully(turno),
										op.DestransformarCargo(departamento, cargo) , WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "", 
										WordUtils.capitalizeFully(direccion), telefono, correo, RIF.toUpperCase()});
						      
						    }
					    rs.close();
					    op.Desconectar();
					}
					catch(Exception ae){
						System.err.println(ae);
					}
					
					
					
					
					
					return;
				}
				if(comboBoxOrden.getSelectedItem().toString().equalsIgnoreCase("Cédula")){
					
					DefaultTableModel dm = (DefaultTableModel) tablacargos.getModel();
					int rowCount = dm.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					
					try{
						op.Conectar("Hotel");
						Statement stmt = op.conn.createStatement();
						
						String sql = "SELECT Nombre_Usuario, Nombres, Apellidos, Cedula, Direccion, Telefono, "
					    		+ "Correo, Departamento, Cargo, RIF, Turno FROM Usuarios ORDER BY Cedula Asc";
					    ResultSet rs = stmt.executeQuery(sql);
					    //STEP 5: Extract data from result set
					    while(rs.next()){
						       //Retrieve by column name
						 
						       String username = rs.getString("Nombre_Usuario");
						       String turno = rs.getString("Turno");
						       int departamento = rs.getInt("Departamento");
						       int cargo = rs.getInt("Cargo");
						       String names = rs.getString("Nombres");
						       String lastnames = rs.getString("Apellidos");
						       int cedula = rs.getInt("Cedula");
						       String direccion = rs.getString("Direccion");
						       String telefono = rs.getString("Telefono");
						       String correo = rs.getString("Correo");
						       
						       String RIF = rs.getString("RIF");
						       
						       

						       DefaultTableModel model = (DefaultTableModel) tablacargos.getModel();
						       if(cedula != 0) model.addRow(new Object[]{tablacargos.getRowCount() + 1, WordUtils.capitalizeFully(username),WordUtils.capitalizeFully(turno),
										op.DestransformarCargo(departamento, cargo), WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "V-" + cedula, 
										WordUtils.capitalizeFully(direccion), telefono, correo, RIF.toUpperCase()});
								if(cedula == 0) model.addRow(new Object[]{tablacargos.getRowCount() + 1, WordUtils.capitalizeFully(username),WordUtils.capitalizeFully(turno),
										op.DestransformarCargo(departamento, cargo) , WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "", 
										WordUtils.capitalizeFully(direccion), telefono, correo, RIF.toUpperCase()});
						      
						    }
					    rs.close();
					    op.Desconectar();
					}
					catch(Exception ae){
						System.err.println(ae);
					}
					
					
					
					
					
					return;
				}
				if(comboBoxOrden.getSelectedItem().toString().equalsIgnoreCase("Correo")){
					
					DefaultTableModel dm = (DefaultTableModel) tablacargos.getModel();
					int rowCount = dm.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					
					try{
						op.Conectar("Hotel");
						Statement stmt = op.conn.createStatement();
						
						String sql = "SELECT Nombre_Usuario, Nombres, Apellidos, Cedula, Direccion, Telefono, "
					    		+ "Correo, Departamento, Cargo, RIF, Turno FROM Usuarios ORDER BY Correo Asc";
					    ResultSet rs = stmt.executeQuery(sql);
					    //STEP 5: Extract data from result set
					    while(rs.next()){
						       //Retrieve by column name
						 
						       String username = rs.getString("Nombre_Usuario");
						       String turno = rs.getString("Turno");
						       int departamento = rs.getInt("Departamento");
						       int cargo = rs.getInt("Cargo");
						       String names = rs.getString("Nombres");
						       String lastnames = rs.getString("Apellidos");
						       int cedula = rs.getInt("Cedula");
						       String direccion = rs.getString("Direccion");
						       String telefono = rs.getString("Telefono");
						       String correo = rs.getString("Correo");
						       
						       String RIF = rs.getString("RIF");
						       
						       

						       DefaultTableModel model = (DefaultTableModel) tablacargos.getModel();
						       if(cedula != 0) model.addRow(new Object[]{tablacargos.getRowCount() + 1, WordUtils.capitalizeFully(username),WordUtils.capitalizeFully(turno),
										op.DestransformarCargo(departamento, cargo), WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "V-" + cedula, 
										WordUtils.capitalizeFully(direccion), telefono, correo, RIF.toUpperCase()});
								if(cedula == 0) model.addRow(new Object[]{tablacargos.getRowCount() + 1, WordUtils.capitalizeFully(username),WordUtils.capitalizeFully(turno),
										op.DestransformarCargo(departamento, cargo) , WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "", 
										WordUtils.capitalizeFully(direccion), telefono, correo, RIF.toUpperCase()});
						      
						    }
					    rs.close();
					    op.Desconectar();
					}
					catch(Exception ae){
						System.err.println(ae);
					}
					
					
					
					
					
					return;
				}
				if(comboBoxOrden.getSelectedItem().toString().equalsIgnoreCase("Turno")){
					
					DefaultTableModel dm = (DefaultTableModel) tablacargos.getModel();
					int rowCount = dm.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					
					try{
						op.Conectar("Hotel");
						Statement stmt = op.conn.createStatement();
						
						String sql = "SELECT Nombre_Usuario, Nombres, Apellidos, Cedula, Direccion, Telefono, "
					    		+ "Correo, Departamento, Cargo, RIF, Turno FROM Usuarios ORDER BY Turno Asc";
					    ResultSet rs = stmt.executeQuery(sql);
					    //STEP 5: Extract data from result set
					    while(rs.next()){
						       //Retrieve by column name
						 
						       String username = rs.getString("Nombre_Usuario");
						       String turno = rs.getString("Turno");
						       int departamento = rs.getInt("Departamento");
						       int cargo = rs.getInt("Cargo");
						       String names = rs.getString("Nombres");
						       String lastnames = rs.getString("Apellidos");
						       int cedula = rs.getInt("Cedula");
						       String direccion = rs.getString("Direccion");
						       String telefono = rs.getString("Telefono");
						       String correo = rs.getString("Correo");
						       
						       String RIF = rs.getString("RIF");
						       
						       

						       DefaultTableModel model = (DefaultTableModel) tablacargos.getModel();
						       if(cedula != 0) model.addRow(new Object[]{tablacargos.getRowCount() + 1, WordUtils.capitalizeFully(username),WordUtils.capitalizeFully(turno),
										op.DestransformarCargo(departamento, cargo), WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "V-" + cedula, 
										WordUtils.capitalizeFully(direccion), telefono, correo, RIF.toUpperCase()});
								if(cedula == 0) model.addRow(new Object[]{tablacargos.getRowCount() + 1, WordUtils.capitalizeFully(username),WordUtils.capitalizeFully(turno),
										op.DestransformarCargo(departamento, cargo) , WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "", 
										WordUtils.capitalizeFully(direccion), telefono, correo, RIF.toUpperCase()});
						      
						    }
					    rs.close();
					    op.Desconectar();
					}
					catch(Exception ae){
						System.err.println(ae);
					}
					
					
					
					
					
					return;
				}
				if(comboBoxOrden.getSelectedItem().toString().equalsIgnoreCase("Cargo")){
					
					DefaultTableModel dm = (DefaultTableModel) tablacargos.getModel();
					int rowCount = dm.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					
					try{
						op.Conectar("Hotel");
						Statement stmt = op.conn.createStatement();
						
						String sql = "SELECT Nombre_Usuario, Nombres, Apellidos, Cedula, Direccion, Telefono, "
					    		+ "Correo, Departamento, Cargo, RIF, Turno FROM Usuarios ORDER BY Nombre_Usuario Asc";
					    ResultSet rs = stmt.executeQuery(sql);
					    //STEP 5: Extract data from result set
					    while(rs.next()){
						       //Retrieve by column name
						 
						       String username = rs.getString("Nombre_Usuario");
						       String turno = rs.getString("Turno");
						       int departamento = rs.getInt("Departamento");
						       int cargo = rs.getInt("Cargo");
						       String names = rs.getString("Nombres");
						       String lastnames = rs.getString("Apellidos");
						       int cedula = rs.getInt("Cedula");
						       String direccion = rs.getString("Direccion");
						       String telefono = rs.getString("Telefono");
						       String correo = rs.getString("Correo");
						       
						       String RIF = rs.getString("RIF");
						       
						       

						       DefaultTableModel model = (DefaultTableModel) tablacargos.getModel();
						       if(cedula != 0) model.addRow(new Object[]{tablacargos.getRowCount() + 1, WordUtils.capitalizeFully(username),WordUtils.capitalizeFully(turno),
										op.DestransformarCargo(departamento, cargo), WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "V-" + cedula, 
										WordUtils.capitalizeFully(direccion), telefono, correo, RIF.toUpperCase()});
								if(cedula == 0) model.addRow(new Object[]{tablacargos.getRowCount() + 1, WordUtils.capitalizeFully(username),WordUtils.capitalizeFully(turno),
										op.DestransformarCargo(departamento, cargo) , WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "", 
										WordUtils.capitalizeFully(direccion), telefono, correo, RIF.toUpperCase()});
						      
						    }
					    rs.close();
					    op.Desconectar();
					}
					catch(Exception ae){
						System.err.println(ae);
					}
					
					
					
					
					
					return;
				}
			}
		});
		
		comboBoxOrden.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboBoxOrden.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Usuario", "Nombres", "Apellidos", "C\u00E9dula", "Correo", "Cargo", "Turno"}));
		comboBoxOrden.setBounds(355, 141, 198, 28);
		panel.add(comboBoxOrden);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		scrollPane.setBounds(19, 186, 975, 376);
		panel.add(scrollPane);
		
		tablacargos = new JTable();
		tablacargos.setRowSelectionAllowed(false);
		tablacargos.setFont(new Font("Segoe UI", Font.BOLD, 10));
		tablacargos.setGridColor(new Color(255, 255, 255));
		tablacargos.setForeground(new Color(255, 255, 255));
		tablacargos.setBorder(new LineBorder(new Color(0, 51, 51), 1, true));
		tablacargos.setFillsViewportHeight(true);
		tablacargos.setBackground(new Color(0, 51, 51));
		tablacargos.setShowGrid(false);
		tablacargos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"<html><b>N\u00B0</b></html>", "<html><b>Usuario</b></html>", "<html><b>Turno</b></html>", "<html><b>Cargo</b></html>", "<html><b>Nombres</b></html>", "<html><b>Apellidos</b></html>", "<html><b>C.I</b></html>", "<html><b>Direcci\u00F3n</b></html>", "<html><b>T\u00E9lefono</b></html>", "<html><b>Correo</b></html>", "<html><b>RIF</b></html>"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablacargos.getColumnModel().getColumn(0).setResizable(false);
		tablacargos.getColumnModel().getColumn(0).setMaxWidth(32);
		tablacargos.getColumnModel().getColumn(1).setResizable(false);
		tablacargos.setShowHorizontalLines(true);
		scrollPane.setViewportView(tablacargos);
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		
		headerRenderer.setBackground(new Color(0, 51, 51));
		headerRenderer.setForeground(Color.WHITE);
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);
		headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 12));
		tablacargos.getTableHeader().setReorderingAllowed(false);

		for (int i = 0; i < tablacargos.getModel().getColumnCount(); i++) {
		        tablacargos.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		        tablacargos.getColumnModel().getColumn(i).setCellRenderer( headerRenderer );
		     
		}
		
		PantallaVisualizarUsuarios.setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaVisualizarUsuarios.class.getResource("/imagenes/HotelIcon.png")));
		PantallaVisualizarUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaVisualizarUsuarios.setResizable(false);
		PantallaVisualizarUsuarios.setSize(1024, 668);
		
		
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaVisualizarUsuarios.setLocation(dim.width/2-PantallaVisualizarUsuarios.getSize().width/2, dim.height/2-PantallaVisualizarUsuarios.getSize().height/2);
		
		BD abc = new BD();
		try {
			abc.CargarUsuarios(tablacargos);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lblNombre.setText(WordUtils.capitalizeFully(PantallaAcceso.Usuario));
		lblCargo.setText(WordUtils.capitalizeFully(PantallaAcceso.cargo));
		lblTurno_1.setText(WordUtils.capitalizeFully(PantallaAcceso.Turno));
		
		JLabel lblCargo_1 = new JLabel("Ordenar por");
		lblCargo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo_1.setForeground(Color.WHITE);
		lblCargo_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCargo_1.setBounds(239, 139, 104, 33);
		panel.add(lblCargo_1);
		
		JLabel lbldepartamento = new JLabel(PantallaAcceso.departamento);
		lbldepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lbldepartamento.setForeground(Color.WHITE);
		lbldepartamento.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbldepartamento.setBounds(237, 78, 198, 16);
		panel.add(lbldepartamento);
		
	
		
		
		
		
		
		PantallaVisualizarUsuarios.setVisible(true);
	}
}



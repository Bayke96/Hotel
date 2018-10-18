package pantallas;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class ActualizarCliente {
	private static JTextField txtNombres;
	private static JTextField txtCedula;
	private static JTextField txtRIF;
	private static JTextField txtDireccion;
	private static JTextField txtTelefono;
	private static JTextField txtCorreo;
	private static JTable tablaminiclientes;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaPerfil = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaPerfil.setTitle("Hotel Mi Reina C.A - Actualizar Cliente");
		PantallaPerfil.getContentPane().setBackground(Color.WHITE);
		PantallaPerfil.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ActualizarCliente.class.getResource("/imagenes/BG.jpg"))).getImage());
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
		
		JLabel lblNewLabel_1 = new JLabel("Nombre & Apellido");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(6, 319, 277, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblCdulaDeIdentidad = new JLabel("C\u00E9dula de Identidad");
		lblCdulaDeIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCdulaDeIdentidad.setForeground(Color.WHITE);
		lblCdulaDeIdentidad.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCdulaDeIdentidad.setBounds(6, 405, 277, 16);
		panel.add(lblCdulaDeIdentidad);
		
		JLabel lblRif = new JLabel("RIF");
		lblRif.setHorizontalAlignment(SwingConstants.CENTER);
		lblRif.setForeground(Color.WHITE);
		lblRif.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblRif.setBounds(6, 494, 277, 16);
		panel.add(lblRif);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccin.setForeground(Color.WHITE);
		lblDireccin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDireccin.setBounds(295, 319, 179, 16);
		panel.add(lblDireccin);
		
		JLabel lblNmeroDeTelfono = new JLabel("N\u00FAmero de Tel\u00E9fono");
		lblNmeroDeTelfono.setHorizontalAlignment(SwingConstants.CENTER);
		lblNmeroDeTelfono.setForeground(Color.WHITE);
		lblNmeroDeTelfono.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNmeroDeTelfono.setBounds(241, 405, 277, 16);
		panel.add(lblNmeroDeTelfono);
		
		JLabel lblCorreoElctronico = new JLabel("Correo el\u00E9ctronico");
		lblCorreoElctronico.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorreoElctronico.setForeground(Color.WHITE);
		lblCorreoElctronico.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCorreoElctronico.setBounds(241, 494, 277, 16);
		panel.add(lblCorreoElctronico);
		
		
		
		JLabel lblTitulo = new JLabel("DATOS PERSONALES - ");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblTitulo.setBounds(152, 192, 252, 28);
		panel.add(lblTitulo);
		
		lblTitulo.setText("Modificar Cliente Existente");
		
		JButton btnSalir = new JButton(" Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPerfil.dispose();
				
			}
		});
		btnSalir.setIcon(new ImageIcon(ActualizarCliente.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setFocusPainted(false);
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setBounds(877, 643, 129, 37);
		panel.add(btnSalir);
		
		JComboBox comboboxclientes = new JComboBox();
		comboboxclientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BD op = new BD();
				
				op.Conectar("Hotel");
				try{
					  String selectSQL = "SELECT Nombre, Cedula, Telefono, Direccion, Correo, RIF FROM Clientes WHERE Nombre = ?";
					  PreparedStatement preparedStatement = op.conn.prepareStatement(selectSQL);
					  preparedStatement.setString(1, comboboxclientes.getSelectedItem().toString().toLowerCase());
					  ResultSet rs = preparedStatement.executeQuery();
					  while (rs.next()) {
					  	txtNombres.setText(comboboxclientes.getSelectedItem().toString());
					  	txtCedula.setText(Integer.toString(rs.getInt("Cedula")));
					  	txtTelefono.setText(rs.getString("Telefono"));
					  	txtDireccion.setText(WordUtils.capitalize(rs.getString("Direccion")));
					  	txtCorreo.setText(rs.getString("correo"));
						txtRIF.setText(rs.getString("RIF").toUpperCase());
					  }
					  
				}
				catch(Exception ay){
						  System.err.println(ay);
					  }
				
				txtNombres.requestFocus();
				txtNombres.selectAll();
			}
		});
		comboboxclientes.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboboxclientes.setBounds(188, 264, 179, 28);
		panel.add(comboboxclientes);
		
		
		JButton btnAceptar = new JButton("");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNombres.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe escribir un nombre!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtNombres.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe escribir un nombre!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!StringUtils.stripAccents(txtNombres.getText()).matches("^[ A-Za-z]+$")){
					JOptionPane.showMessageDialog(null, "Error: El nombre solo acepta letras y espacios!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtNombres.requestFocus();
					txtNombres.selectAll();
					return;
				}
				
				int cedula = 0;
				if(!txtCedula.getText().trim().equalsIgnoreCase("")) cedula = Integer.parseInt(txtCedula.getText());
				BD op = new BD();
				op.Conectar("Hotel");
				try {
					if(op.BuscarCliente(txtNombres.getText()) == true && !txtNombres.getText().equalsIgnoreCase(comboboxclientes.getSelectedItem().toString())){
						JOptionPane.showMessageDialog(null, "ERROR: Este nombre ya existe dentro del sistema!", "Hotel Mi Reina C.A", JOptionPane.ERROR_MESSAGE);
						txtNombres.requestFocus();
						txtNombres.selectAll();
						return;
					}
					op.ActualizarCliente(txtNombres.getText(), cedula, txtTelefono.getText(), txtDireccion.getText(), txtCorreo.getText(), txtRIF.getText(), 
							comboboxclientes.getSelectedItem().toString());
					
					
					op.CargarMiniClientes(tablaminiclientes);
					op.ListaClientes(comboboxclientes);
					
					comboboxclientes.setSelectedItem("Seleccionar");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				op.Desconectar();
				JOptionPane.showMessageDialog(null, "Cliente actualizado", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
				
				
				
				txtNombres.setText("");
			
				txtDireccion.setText("");
				txtCedula.setText("");
				txtCorreo.setText("");
				txtRIF.setText("");
				txtTelefono.setText("");
				
				
				txtNombres.requestFocus();
				txtNombres.selectAll();
			}
		});
		btnAceptar.setIcon(new ImageIcon(ActualizarCliente.class.getResource("/imagenes/AcceptIcon.png")));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBackground(new Color(0, 51, 51));
		btnAceptar.setBounds(877, 605, 61, 37);
		panel.add(btnAceptar);
		
		JButton btnBorrar = new JButton("");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNombres.setText("");
				comboboxclientes.setSelectedItem("Seleccionar");
				txtDireccion.setText("");
				txtCedula.setText("");
				txtCorreo.setText("");
				txtRIF.setText("");
				txtTelefono.setText("");
			}
		});
		btnBorrar.setIcon(new ImageIcon(ActualizarCliente.class.getResource("/imagenes/EraserIcon.png")));
		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBorrar.setFocusPainted(false);
		btnBorrar.setBackground(new Color(0, 51, 51));
		btnBorrar.setBounds(942, 605, 61, 37);
		panel.add(btnBorrar);
		
		txtNombres = new JTextField();
		txtNombres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNombres.getText().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Debe introducir un nombre!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtNombres.requestFocus();
					return;
				}
				if(!txtNombres.getText().trim().equalsIgnoreCase("") && !StringUtils.stripAccents(txtNombres.getText()).matches("^[ A-Za-z]+$")){
					JOptionPane.showMessageDialog(null, "Error: El campo Nombres solo acepta letras y espacios!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtNombres.selectAll();
					return;
				}
				
				
				txtCedula.requestFocus();
				txtCedula.selectAll();
			}
		});
		txtNombres.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtNombres.setBackground(new Color(0, 0, 0, 0));
		txtNombres.setForeground(Color.WHITE);
		txtNombres.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtNombres.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombres.setBounds(66, 347, 163, 28);
		panel.add(txtNombres);
		txtNombres.setColumns(10);
		
		txtCedula = new JTextField();
		txtCedula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!txtCedula.getText().trim().equalsIgnoreCase("") && !txtCedula.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "Error: El campo cédula solo acepta números!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtCedula.selectAll();
					return;
				}
				
				txtRIF.requestFocus();
				txtRIF.selectAll();
			}
		});
		txtCedula.setHorizontalAlignment(SwingConstants.CENTER);
		txtCedula.setForeground(Color.WHITE);
		txtCedula.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtCedula.setColumns(10);
		txtCedula.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtCedula.setBackground(new Color(0, 0, 0, 0));
		txtCedula.setBounds(66, 433, 163, 28);
		panel.add(txtCedula);
		
		txtRIF = new JTextField();
		txtRIF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtRIF.getText().trim().equalsIgnoreCase("") && !txtRIF.getText().matches("([A-Za-z0-9--]+)")){
					JOptionPane.showMessageDialog(null, "Error: El campo RIF solo acepta letras, numeros y rayas!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtRIF.selectAll();
					return;
				}
				
				
				txtDireccion.requestFocus();
				txtDireccion.selectAll();
			}
		});
		txtRIF.setHorizontalAlignment(SwingConstants.CENTER);
		txtRIF.setForeground(Color.WHITE);
		txtRIF.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtRIF.setColumns(10);
		txtRIF.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtRIF.setBackground(new Color(0, 0, 0, 0));
		txtRIF.setBounds(66, 522, 163, 28);
		panel.add(txtRIF);
		
		txtDireccion = new JTextField();
		txtDireccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTelefono.requestFocus();
				txtTelefono.selectAll();
			}
		});
		txtDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		txtDireccion.setForeground(Color.WHITE);
		txtDireccion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtDireccion.setColumns(10);
		txtDireccion.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtDireccion.setBackground(new Color(0, 0, 0, 0));
		txtDireccion.setBounds(298, 347, 163, 28);
		panel.add(txtDireccion);
		
		txtTelefono = new JTextField();
		txtTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtTelefono.getText().trim().equalsIgnoreCase("") && !txtTelefono.getText().matches("[0-9 --]+")){
					JOptionPane.showMessageDialog(null, "Error: El campo Telefono solo acepta letras, numeros y rayas!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtTelefono.selectAll();
					return;
				}
				
				
				txtCorreo.requestFocus();
				txtCorreo.selectAll();
			}
		});
		txtTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		txtTelefono.setForeground(Color.WHITE);
		txtTelefono.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtTelefono.setColumns(10);
		txtTelefono.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtTelefono.setBackground(new Color(0, 0, 0, 0));
		txtTelefono.setBounds(298, 432, 163, 28);
		panel.add(txtTelefono);
		
		txtCorreo = new JTextField();
		txtCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtCorreo.getText().trim().equalsIgnoreCase("") && !txtCorreo.getText().contains("@")){
					JOptionPane.showMessageDialog(null, "Error: El correo debe incluir un arroba (@) !", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtCorreo.selectAll();
					return;
				}
				if(txtNombres.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe escribir un nombre!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!StringUtils.stripAccents(txtNombres.getText()).matches("^[ A-Za-z]+$")){
					JOptionPane.showMessageDialog(null, "Error: El nombre solo acepta letras y espacios!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtNombres.requestFocus();
					txtNombres.selectAll();
					return;
				}
				
				int cedula = 0;
				if(!txtCedula.getText().trim().equalsIgnoreCase("")) cedula = Integer.parseInt(txtCedula.getText());
				BD op = new BD();
				op.Conectar("Hotel");
				try {
					if(op.BuscarCliente(txtNombres.getText()) == true && !txtNombres.getText().equalsIgnoreCase(comboboxclientes.getSelectedItem().toString())){
						JOptionPane.showMessageDialog(null, "ERROR: Este nombre ya existe dentro del sistema!", "Hotel Mi Reina C.A", JOptionPane.ERROR_MESSAGE);
						txtNombres.requestFocus();
						txtNombres.selectAll();
						return;
					}
					op.ActualizarCliente(txtNombres.getText(), cedula, txtTelefono.getText(), txtDireccion.getText(), txtCorreo.getText(), txtRIF.getText(), 
							comboboxclientes.getSelectedItem().toString());
					
					
					op.CargarMiniClientes(tablaminiclientes);
					op.ListaClientes(comboboxclientes);
					
					comboboxclientes.setSelectedItem("Seleccionar");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				op.Desconectar();
				JOptionPane.showMessageDialog(null, "Cliente actualizado", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
				
				
				
				txtNombres.setText("");
			
				txtDireccion.setText("");
				txtCedula.setText("");
				txtCorreo.setText("");
				txtRIF.setText("");
				txtTelefono.setText("");
				
				
				txtNombres.requestFocus();
				txtNombres.selectAll();
				
				
				
			}
		});
		txtCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCorreo.setForeground(Color.WHITE);
		txtCorreo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtCorreo.setColumns(10);
		txtCorreo.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtCorreo.setBackground(new Color(0, 0, 0, 0));
		txtCorreo.setBounds(298, 522, 163, 28);
		panel.add(txtCorreo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(513, 195, 477, 374);
		panel.add(scrollPane);
		
		tablaminiclientes = new JTable();
		tablaminiclientes.setBackground(new Color(0, 51, 51));
		tablaminiclientes.setFillsViewportHeight(true);
		tablaminiclientes.setGridColor(new Color(0, 0, 0, 0));
		tablaminiclientes.setFont(new Font("SansSerif", Font.BOLD, 10));
		tablaminiclientes.setShowVerticalLines(true);
		tablaminiclientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"<html><b>Nombre</b></html>", "<html><b>Telefono</b></html>", "<html><b>Correo</b></html>"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaminiclientes.getColumnModel().getColumn(0).setResizable(false);
		tablaminiclientes.getColumnModel().getColumn(1).setResizable(false);
		tablaminiclientes.getColumnModel().getColumn(2).setResizable(false);
		tablaminiclientes.setShowHorizontalLines(true);
		scrollPane.setViewportView(tablaminiclientes);
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		
		headerRenderer.setBackground(new Color(0, 51, 51));
		headerRenderer.setForeground(Color.WHITE);
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);
		headerRenderer.setOpaque(true);
		tablaminiclientes.getTableHeader().setReorderingAllowed(false);

		for (int i = 0; i < tablaminiclientes.getModel().getColumnCount(); i++) {
			tablaminiclientes.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
			tablaminiclientes.getColumnModel().getColumn(i).setCellRenderer( headerRenderer );
		     
		}
		
		JLabel lblListaDeClientes = new JLabel("Lista de Clientes");
		lblListaDeClientes.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		lblListaDeClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeClientes.setForeground(Color.WHITE);
		lblListaDeClientes.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblListaDeClientes.setBounds(601, 135, 308, 47);
		panel.add(lblListaDeClientes);
		
		PantallaPerfil.setIconImage(Toolkit.getDefaultToolkit().getImage(ActualizarCliente.class.getResource("/imagenes/HotelIcon.png")));
		PantallaPerfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaPerfil.setResizable(false);
		PantallaPerfil.setSize(1024, 724);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaPerfil.setLocation(dim.width/2-PantallaPerfil.getSize().width/2, dim.height/2-PantallaPerfil.getSize().height/2);
		
		
		BD op = new BD();
		try {
			op.CargarMiniClientes(tablaminiclientes);
			
			JLabel lblCliente = new JLabel("Cliente");
			lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
			lblCliente.setForeground(Color.WHITE);
			lblCliente.setFont(new Font("Segoe UI", Font.BOLD, 12));
			lblCliente.setBounds(78, 270, 129, 16);
			panel.add(lblCliente);
			
		
			
			op.ListaClientes(comboboxclientes);
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PantallaPerfil.setVisible(true);
		
		txtNombres.requestFocus();
		txtNombres.selectAll();
		
		
	}
}



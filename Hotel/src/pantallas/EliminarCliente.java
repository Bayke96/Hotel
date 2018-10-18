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

public class EliminarCliente {
	private static JTable tablaminiclientes;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaPerfil = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaPerfil.setTitle("Hotel Mi Reina C.A - Eliminar Cliente");
		PantallaPerfil.getContentPane().setBackground(Color.WHITE);
		PantallaPerfil.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(EliminarCliente.class.getResource("/imagenes/BG.jpg"))).getImage());
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
		lblNewLabel_1.setBounds(6, 276, 277, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblCdulaDeIdentidad = new JLabel("C\u00E9dula de Identidad");
		lblCdulaDeIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCdulaDeIdentidad.setForeground(Color.WHITE);
		lblCdulaDeIdentidad.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCdulaDeIdentidad.setBounds(6, 362, 277, 16);
		panel.add(lblCdulaDeIdentidad);
		
		JLabel lblRif = new JLabel("RIF");
		lblRif.setHorizontalAlignment(SwingConstants.CENTER);
		lblRif.setForeground(Color.WHITE);
		lblRif.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblRif.setBounds(6, 451, 277, 16);
		panel.add(lblRif);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccin.setForeground(Color.WHITE);
		lblDireccin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDireccin.setBounds(295, 276, 179, 16);
		panel.add(lblDireccin);
		
		JLabel lblNmeroDeTelfono = new JLabel("N\u00FAmero de Tel\u00E9fono");
		lblNmeroDeTelfono.setHorizontalAlignment(SwingConstants.CENTER);
		lblNmeroDeTelfono.setForeground(Color.WHITE);
		lblNmeroDeTelfono.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNmeroDeTelfono.setBounds(241, 362, 277, 16);
		panel.add(lblNmeroDeTelfono);
		
		JLabel lblCorreoElctronico = new JLabel("Correo el\u00E9ctronico");
		lblCorreoElctronico.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorreoElctronico.setForeground(Color.WHITE);
		lblCorreoElctronico.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCorreoElctronico.setBounds(241, 451, 277, 16);
		panel.add(lblCorreoElctronico);
		
		
		
		JLabel lblTitulo = new JLabel("DATOS PERSONALES - ");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblTitulo.setBounds(154, 144, 239, 37);
		panel.add(lblTitulo);
		
		lblTitulo.setText("Eliminar Cliente Existente");
		
		JButton btnSalir = new JButton(" Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPerfil.dispose();
				
			}
		});
		btnSalir.setIcon(new ImageIcon(EliminarCliente.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setFocusPainted(false);
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setBounds(863, 537, 129, 37);
		panel.add(btnSalir);
		
		JLabel lblNombres = new JLabel("");
		lblNombres.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombres.setForeground(Color.LIGHT_GRAY);
		lblNombres.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNombres.setBounds(51, 304, 195, 16);
		panel.add(lblNombres);
		
		JLabel lblCedula = new JLabel("");
		lblCedula.setHorizontalAlignment(SwingConstants.CENTER);
		lblCedula.setForeground(Color.LIGHT_GRAY);
		lblCedula.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCedula.setBounds(6, 392, 277, 16);
		panel.add(lblCedula);
		
		JLabel lblRIF = new JLabel("");
		lblRIF.setHorizontalAlignment(SwingConstants.CENTER);
		lblRIF.setForeground(Color.LIGHT_GRAY);
		lblRIF.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblRIF.setBounds(6, 476, 277, 16);
		panel.add(lblRIF);
		
		JLabel lblDireccion = new JLabel("");
		lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccion.setForeground(Color.LIGHT_GRAY);
		lblDireccion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDireccion.setBounds(295, 304, 179, 16);
		panel.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setForeground(Color.LIGHT_GRAY);
		lblTelefono.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTelefono.setBounds(295, 392, 179, 16);
		panel.add(lblTelefono);
		
		JLabel lblCorreo = new JLabel("");
		lblCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorreo.setForeground(Color.LIGHT_GRAY);
		lblCorreo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCorreo.setBounds(295, 476, 179, 16);
		panel.add(lblCorreo);
		
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
					  	lblNombres.setText(comboboxclientes.getSelectedItem().toString());
					  	lblCedula.setText("V-" + Integer.toString(rs.getInt("Cedula")));
					  	lblTelefono.setText(rs.getString("Telefono"));
					  	lblDireccion.setText(WordUtils.capitalize(rs.getString("Direccion")));
					  	lblCorreo.setText(rs.getString("correo"));
					  	lblRIF.setText(rs.getString("RIF").toUpperCase());
					  }
					  
				}
				catch(Exception ay){
						  System.err.println(ay);
					  }
				
			
			}
		});
		comboboxclientes.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboboxclientes.setBounds(188, 221, 179, 28);
		panel.add(comboboxclientes);
		
		
		JButton btnAceptar = new JButton("");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboboxclientes.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Debe elegir un cliente a eliminar", "Hotel Mi Reina C.A - ERROR", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				BD op = new BD();
				op.Conectar("Hotel");
				try {
					
					op.EliminarCliente(comboboxclientes.getSelectedItem().toString());
					
					
					op.CargarMiniClientes(tablaminiclientes);
					op.ListaClientes(comboboxclientes);
					
					comboboxclientes.setSelectedItem("Seleccionar");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				op.Desconectar();
				
				lblNombres.setText("");
				
				lblDireccion.setText("");
				lblCedula.setText("");
				lblCorreo.setText("");
				lblRIF.setText("");
				lblTelefono.setText("");
				
				
				JOptionPane.showMessageDialog(null, "Cliente eliminado", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
				
				
				
				
				
				
				
			}
		});
		btnAceptar.setIcon(new ImageIcon(EliminarCliente.class.getResource("/imagenes/AcceptIcon.png")));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBackground(new Color(0, 51, 51));
		btnAceptar.setBounds(801, 537, 61, 37);
		panel.add(btnAceptar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(518, 151, 477, 374);
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
		lblListaDeClientes.setBounds(606, 91, 308, 47);
		panel.add(lblListaDeClientes);
		
		PantallaPerfil.setIconImage(Toolkit.getDefaultToolkit().getImage(EliminarCliente.class.getResource("/imagenes/HotelIcon.png")));
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
			lblCliente.setBounds(78, 227, 129, 16);
			panel.add(lblCliente);
			
		
			
			op.ListaClientes(comboboxclientes);
			
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PantallaPerfil.setVisible(true);
		
		
	}
}



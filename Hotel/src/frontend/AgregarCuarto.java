package frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
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

public class AgregarCuarto {
	private static JTextField txtcuarto;
	private static JTable tablacuartos;
	private static JTextField txtprecio;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaAgregarCuarto = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaAgregarCuarto.setTitle("Hotel Mi Reina C.A - Nuevo cuarto");
		PantallaAgregarCuarto.getContentPane().setBackground(Color.WHITE);
		PantallaAgregarCuarto.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(AgregarCuarto.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setDoubleBuffered(false);
		panel.setFocusable(false);
		panel.setFocusTraversalKeysEnabled(false);
		panel.setOpaque(false);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1012, 640);
		PantallaAgregarCuarto.getContentPane().add(panel);
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
				PantallaAgregarCuarto.dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(AgregarCuarto.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setFocusPainted(false);
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setBounds(900, 586, 106, 37);
		panel.add(btnSalir);
		
		JLabel lblNuevoCargo = new JLabel("Nombre");
		lblNuevoCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoCargo.setForeground(Color.WHITE);
		lblNuevoCargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNuevoCargo.setBounds(44, 208, 122, 33);
		panel.add(lblNuevoCargo);
		
		txtcuarto = new JTextField();
		txtcuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				if(txtcuarto.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir un nombre para el cuarto!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtcuarto.getText().matches("^[ A-Za-z0-9]+$")){
					JOptionPane.showMessageDialog(null, "Error: El campo solo acepta letras y espacios!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtcuarto.requestFocus();
					txtcuarto.selectAll();
					return;
				}
				
				txtprecio.requestFocus();
				txtprecio.selectAll();
				
				
			}
		});
		txtcuarto.setHorizontalAlignment(SwingConstants.CENTER);
		txtcuarto.setForeground(Color.WHITE);
		txtcuarto.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtcuarto.setColumns(10);
		txtcuarto.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtcuarto.setBackground(new Color(0, 0, 0, 0));
		txtcuarto.setBounds(173, 211, 159, 28);
		panel.add(txtcuarto);
		
		JButton btnAceptar = new JButton("");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtcuarto.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir un nombre para el cuarto!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtcuarto.getText().matches("^[ A-Za-z0-9]+$")){
					JOptionPane.showMessageDialog(null, "Error: El campo solo acepta letras y espacios!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtcuarto.requestFocus();
					txtcuarto.selectAll();
					return;
				}
				
				String valor = txtprecio.getText().replaceAll(",", ".");
				if(txtprecio.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Debe introducir un precio!", "Hotel Mi Reina C.A", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!valor.matches("[0-9]+([,.][0-9]{1,2})?")){
					JOptionPane.showMessageDialog(null, "El precio solo acepta números enteros y un maximo de 2 decimales!", "Hotel Mi Reina C.A", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(Double.parseDouble(valor) <= 0){
					JOptionPane.showMessageDialog(null, "Debe introducir un precio mayor a 0!", "Hotel Mi Reina C.A - ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				BD op = new BD();
				try {
					if(op.BuscarCuarto(txtcuarto.getText()) == true){
						JOptionPane.showMessageDialog(null, "Este cuarto ya existe dentro del sistema!", "Hotel Mi Reina C.A", JOptionPane.ERROR_MESSAGE);
						txtcuarto.requestFocus();
						txtcuarto.selectAll();
						return;
					}
					op.InsertarCuarto(txtcuarto.getText(), Double.parseDouble(valor.replaceAll(",", ".")));
					JOptionPane.showMessageDialog(null, "Cuarto agregado!", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
					
					
					
					double iva = Double.parseDouble(valor) * 0.16;
					double sub = Double.parseDouble(valor) - iva;
					DecimalFormat df = new DecimalFormat("#.##");
					
					DefaultTableModel model = (DefaultTableModel) tablacuartos.getModel();
					model.addRow(new Object[]{tablacuartos.getRowCount() + 1, WordUtils.capitalizeFully(txtcuarto.getText()), 
							df.format(sub).replaceAll(",", "."), df.format(iva).replaceAll(",", "."), valor.replaceAll(",", ".") + " BsS"});
					
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtcuarto.setText("");
				txtprecio.setText("");
				
				txtcuarto.requestFocus();
				
				
			}
		});
		btnAceptar.setIcon(new ImageIcon(AgregarCuarto.class.getResource("/imagenes/AcceptIcon.png")));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBackground(new Color(0, 51, 51));
		btnAceptar.setBounds(900, 548, 53, 37);
		panel.add(btnAceptar);
		
		JButton btnBorrar = new JButton("");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtcuarto.setText("");
				txtprecio.setText("");
			}
		});
		btnBorrar.setIcon(new ImageIcon(AgregarCuarto.class.getResource("/imagenes/EraserIcon.png")));
		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBorrar.setFocusPainted(false);
		btnBorrar.setBackground(new Color(0, 51, 51));
		btnBorrar.setBounds(953, 548, 53, 37);
		panel.add(btnBorrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		scrollPane.setBounds(44, 253, 669, 353);
		panel.add(scrollPane);
		
		tablacuartos = new JTable();
		tablacuartos.setRowSelectionAllowed(false);
		tablacuartos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		tablacuartos.setGridColor(new Color(255, 255, 255));
		tablacuartos.setForeground(new Color(255, 255, 255));
		tablacuartos.setBorder(new LineBorder(new Color(0, 51, 51), 1, true));
		tablacuartos.setFillsViewportHeight(true);
		tablacuartos.setBackground(new Color(0, 51, 51));
		tablacuartos.setShowGrid(false);
		tablacuartos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"<html><b>ID</b></html>", "<html><b>Nombre</b></html>", "<html><b>Sub Total</b></html>", "<html><b>I.V.A</b></html>", "<html><b>Total</b></html>"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablacuartos.getColumnModel().getColumn(0).setResizable(false);
		tablacuartos.getColumnModel().getColumn(1).setResizable(false);
		tablacuartos.getColumnModel().getColumn(2).setResizable(false);
		tablacuartos.getColumnModel().getColumn(2).setPreferredWidth(96);
		tablacuartos.getColumnModel().getColumn(3).setResizable(false);
		tablacuartos.getColumnModel().getColumn(4).setResizable(false);
		tablacuartos.setShowHorizontalLines(true);
		scrollPane.setViewportView(tablacuartos);
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		
		headerRenderer.setBackground(new Color(0, 51, 51));
		headerRenderer.setForeground(Color.WHITE);
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);
		headerRenderer.setOpaque(true);
		tablacuartos.getTableHeader().setReorderingAllowed(false);

		for (int i = 0; i < tablacuartos.getModel().getColumnCount(); i++) {
		        tablacuartos.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		        tablacuartos.getColumnModel().getColumn(i).setCellRenderer( headerRenderer );
		     
		}
		
		PantallaAgregarCuarto.setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarCuarto.class.getResource("/imagenes/HotelIcon.png")));
		PantallaAgregarCuarto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaAgregarCuarto.setResizable(false);
		PantallaAgregarCuarto.setSize(1024, 668);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaAgregarCuarto.setLocation(dim.width/2-PantallaAgregarCuarto.getSize().width/2, dim.height/2-PantallaAgregarCuarto.getSize().height/2);
		
		BD abc = new BD();
		try {
			abc.CargarCuartos(tablacuartos);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lblNombre.setText(StringUtils.capitalize(PantallaAcceso.Usuario));
		lblCargo.setText(StringUtils.capitalize(PantallaAcceso.cargo));
		lblTurno_1.setText(StringUtils.capitalize(PantallaAcceso.Turno));
		
		JLabel lblAgregarCargo = new JLabel("Agregar cuarto");
		lblAgregarCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarCargo.setForeground(Color.WHITE);
		lblAgregarCargo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblAgregarCargo.setBounds(76, 132, 267, 33);
		panel.add(lblAgregarCargo);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setForeground(Color.WHITE);
		lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblPrecio.setBounds(355, 208, 122, 33);
		panel.add(lblPrecio);
		
		txtprecio = new JTextField();
		txtprecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtcuarto.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir un nombre para el cuarto!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtcuarto.getText().matches("^[ A-Za-z0-9]+$")){
					JOptionPane.showMessageDialog(null, "Error: El campo solo acepta letras y espacios!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtcuarto.requestFocus();
					txtcuarto.selectAll();
					return;
				}
				
				String valor = txtprecio.getText().replaceAll(",", ".");
				if(txtprecio.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Debe introducir un precio!", "Hotel Mi Reina C.A", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!valor.matches("[0-9]+([,.][0-9]{1,2})?")){
					JOptionPane.showMessageDialog(null, "El precio solo acepta números enteros y un maximo de 2 decimales!", "Hotel Mi Reina C.A", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(Double.parseDouble(valor) <= 0){
					JOptionPane.showMessageDialog(null, "Debe introducir un precio mayor a 0!", "Hotel Mi Reina C.A - ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				BD op = new BD();
				try {
					if(op.BuscarCuarto(txtcuarto.getText()) == true){
						JOptionPane.showMessageDialog(null, "Este cuarto ya existe dentro del sistema!", "Hotel Mi Reina C.A", JOptionPane.ERROR_MESSAGE);
						txtcuarto.requestFocus();
						txtcuarto.selectAll();
						return;
					}
					op.InsertarCuarto(txtcuarto.getText(), Double.parseDouble(valor.replaceAll(",", ".")));
					JOptionPane.showMessageDialog(null, "Cuarto agregado!", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
					
					
					
					double iva = Double.parseDouble(valor) * 0.16;
					double sub = Double.parseDouble(valor) - iva;
					DecimalFormat df = new DecimalFormat("#.##");
					
					DefaultTableModel model = (DefaultTableModel) tablacuartos.getModel();
					model.addRow(new Object[]{tablacuartos.getRowCount() + 1, WordUtils.capitalizeFully(txtcuarto.getText()), 
							df.format(sub).replaceAll(",", "."), df.format(iva).replaceAll(",", "."), valor.replaceAll(",", ".") + " BsS"});
					
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtcuarto.setText("");
				txtprecio.setText("");
				txtcuarto.requestFocus();
				
			}
		});
		txtprecio.setHorizontalAlignment(SwingConstants.CENTER);
		txtprecio.setForeground(Color.WHITE);
		txtprecio.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtprecio.setColumns(10);
		txtprecio.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtprecio.setBackground(new Color(0, 0, 0, 0));
		txtprecio.setBounds(484, 211, 159, 28);
		panel.add(txtprecio);
		
		
		
		PantallaAgregarCuarto.setVisible(true);
	}
}



package pantallas;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

public class PantallaAgregarCargo {
	private static JTextField txtCargo;
	private static JTable tablacargos;
	private static JTextField txtsalario;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaAgregarCargo = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaAgregarCargo.setTitle("Hotel Mi Reina C.A - Nuevo cargo");
		PantallaAgregarCargo.getContentPane().setBackground(Color.WHITE);
		PantallaAgregarCargo.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(PantallaAgregarCargo.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setDoubleBuffered(false);
		panel.setFocusable(false);
		panel.setFocusTraversalKeysEnabled(false);
		panel.setOpaque(false);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1012, 640);
		PantallaAgregarCargo.getContentPane().add(panel);
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
				PantallaAgregarCargo.dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(PantallaAgregarCargo.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setFocusPainted(false);
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setBounds(893, 586, 113, 37);
		panel.add(btnSalir);
		
		JLabel lblNuevoCargo = new JLabel("Nuevo Cargo");
		lblNuevoCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoCargo.setForeground(Color.WHITE);
		lblNuevoCargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNuevoCargo.setBounds(44, 211, 122, 33);
		panel.add(lblNuevoCargo);
		
		JComboBox comboboxdepartamento = new JComboBox();
		comboboxdepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD op = new BD();
				op.Conectar("Hotel");
				
					try {
						if(!comboboxdepartamento.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
							op.CargarCargos(comboboxdepartamento.getSelectedItem().toString(), tablacargos);
						}
						if(comboboxdepartamento.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
							op.CargarCargos(tablacargos);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
				op.Desconectar();
			}
		});
		comboboxdepartamento.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboboxdepartamento.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
		comboboxdepartamento.setBounds(164, 173, 215, 26);
		panel.add(comboboxdepartamento);
		
		txtCargo = new JTextField();
		txtCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboboxdepartamento.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un departamento!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtCargo.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir un cargo!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtCargo.getText().matches("^[ A-Za-z]+$")){
					JOptionPane.showMessageDialog(null, "Error: El campo solo acepta letras y espacios!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtCargo.requestFocus();
					txtCargo.selectAll();
					return;
				}
				
				txtsalario.requestFocus();
				txtsalario.selectAll();
				
				
				
				
				
				
				
				
				
			}
		});
		txtCargo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCargo.setForeground(Color.WHITE);
		txtCargo.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtCargo.setColumns(10);
		txtCargo.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtCargo.setBackground(new Color(0, 0, 0, 0));
		txtCargo.setBounds(173, 214, 159, 28);
		panel.add(txtCargo);
		
		JButton btnAceptar = new JButton("");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboboxdepartamento.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un departamento!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtCargo.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir un cargo!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtCargo.getText().matches("^[ A-Za-z]+$")){
					JOptionPane.showMessageDialog(null, "Error: El campo solo acepta letras y espacios!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtCargo.requestFocus();
					txtCargo.selectAll();
					return;
				}
				
				String valor = txtsalario.getText().replaceAll(",", ".");
				if(txtsalario.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Debe introducir un salario!", "Hotel Mi Reina C.A", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!valor.matches("[0-9]+([,.][0-9]{1,2})?")){
					JOptionPane.showMessageDialog(null, "El salario solo acepta números enteros y un maximo de 2 decimales!", "Hotel Mi Reina C.A", JOptionPane.ERROR_MESSAGE);
					txtsalario.requestFocus();
					txtsalario.selectAll();
					return;
				}
				if(Double.parseDouble(valor) <= 0){
					JOptionPane.showMessageDialog(null, "Debe introducir un precio mayor a 0!", "Hotel Mi Reina C.A - ERROR", JOptionPane.ERROR_MESSAGE);
					txtsalario.requestFocus();
					txtsalario.selectAll();
					return;
				}
				BD op = new BD();
				op.Conectar("Hotel");
				try {
					if(op.BuscarCargo(comboboxdepartamento.getSelectedItem().toString(), txtCargo.getText().trim().toLowerCase()) == true){
						JOptionPane.showMessageDialog(null, "Error: Este cargo ya existe dentro del sistema!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
						txtCargo.requestFocus();
						txtCargo.selectAll();
						return;
					}
					op.InsertarCargo(txtCargo.getText().trim().toLowerCase(), comboboxdepartamento.getSelectedItem().toString(), Double.parseDouble(valor));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				DefaultTableModel model = (DefaultTableModel) tablacargos.getModel();
				model.addRow(new Object[]{tablacargos.getRowCount() + 1, comboboxdepartamento.getSelectedItem().toString(), WordUtils.capitalizeFully(txtCargo.getText()), valor + " BsS", 0 + " empleados"});
				
				
				JOptionPane.showMessageDialog(null, "El cargo ha sido agregado al sistema", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
				txtCargo.setText("");
				txtCargo.requestFocus();
				txtsalario.setText("");
				
			}
		});
		btnAceptar.setIcon(new ImageIcon(PantallaAgregarCargo.class.getResource("/imagenes/AcceptIcon.png")));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBackground(new Color(0, 51, 51));
		btnAceptar.setBounds(697, 169, 61, 37);
		panel.add(btnAceptar);
		
		JButton btnBorrar = new JButton("");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboboxdepartamento.setSelectedItem("Seleccionar");
				txtCargo.setText("");
				txtsalario.setText("");
			}
		});
		btnBorrar.setIcon(new ImageIcon(PantallaAgregarCargo.class.getResource("/imagenes/EraserIcon.png")));
		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBorrar.setFocusPainted(false);
		btnBorrar.setBackground(new Color(0, 51, 51));
		btnBorrar.setBounds(697, 207, 61, 37);
		panel.add(btnBorrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		scrollPane.setBounds(44, 253, 714, 353);
		panel.add(scrollPane);
		
		tablacargos = new JTable();
		tablacargos.setRowSelectionAllowed(false);
		tablacargos.setFont(new Font("Segoe UI", Font.BOLD, 12));
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
					"<html><b>ID</b></html>", "<html><b>Departamento</b></html>","<html><b>Cargo</b></html>","<html><b>Salario</b></html>", "<html><b>N\u00B0 Empleados</b></html>"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablacargos.getColumnModel().getColumn(0).setResizable(false);
		tablacargos.getColumnModel().getColumn(1).setResizable(false);
		tablacargos.getColumnModel().getColumn(2).setResizable(false);
		tablacargos.getColumnModel().getColumn(3).setResizable(false);
		tablacargos.getColumnModel().getColumn(4).setResizable(false);
		tablacargos.getColumnModel().getColumn(2).setPreferredWidth(96);
		tablacargos.setShowHorizontalLines(true);
		scrollPane.setViewportView(tablacargos);
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		
		headerRenderer.setBackground(new Color(0, 51, 51));
		headerRenderer.setForeground(Color.WHITE);
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);
		headerRenderer.setOpaque(true);
		tablacargos.getTableHeader().setReorderingAllowed(false);

		for (int i = 0; i < tablacargos.getModel().getColumnCount(); i++) {
		        tablacargos.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		        tablacargos.getColumnModel().getColumn(i).setCellRenderer( headerRenderer );
		     
		}
		
		PantallaAgregarCargo.setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaAgregarCargo.class.getResource("/imagenes/HotelIcon.png")));
		PantallaAgregarCargo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaAgregarCargo.setResizable(false);
		PantallaAgregarCargo.setSize(1024, 668);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaAgregarCargo.setLocation(dim.width/2-PantallaAgregarCargo.getSize().width/2, dim.height/2-PantallaAgregarCargo.getSize().height/2);
		
		BD abc = new BD();
		try {
			abc.CargarCargos(tablacargos);
			abc.ListaDepartamentos(comboboxdepartamento);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lblNombre.setText(WordUtils.capitalizeFully(PantallaAcceso.Usuario));
		lblCargo.setText(WordUtils.capitalizeFully(PantallaAcceso.cargo));
		lblTurno_1.setText(WordUtils.capitalizeFully(PantallaAcceso.Turno));
		
		JLabel lblAgregarCargo = new JLabel("Agregar cargo");
		lblAgregarCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarCargo.setForeground(Color.WHITE);
		lblAgregarCargo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblAgregarCargo.setBounds(111, 124, 267, 33);
		panel.add(lblAgregarCargo);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartamento.setForeground(Color.WHITE);
		lblDepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDepartamento.setBounds(44, 169, 122, 33);
		panel.add(lblDepartamento);
		
		txtsalario = new JTextField();
		txtsalario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboboxdepartamento.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un departamento!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtCargo.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir un cargo!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtCargo.getText().matches("^[ A-Za-z]+$")){
					JOptionPane.showMessageDialog(null, "Error: El campo solo acepta letras y espacios!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtCargo.requestFocus();
					txtCargo.selectAll();
					return;
				}
				
				String valor = txtsalario.getText().replaceAll(",", ".");
				if(txtsalario.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Debe introducir un salario!", "Hotel Mi Reina C.A", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!valor.matches("[0-9]+([,.][0-9]{1,2})?")){
					JOptionPane.showMessageDialog(null, "El salario solo acepta números enteros y un maximo de 2 decimales!", "Hotel Mi Reina C.A", JOptionPane.ERROR_MESSAGE);
					txtsalario.requestFocus();
					txtsalario.selectAll();
					return;
				}
				if(Double.parseDouble(valor) <= 0){
					JOptionPane.showMessageDialog(null, "Debe introducir un precio mayor a 0!", "Hotel Mi Reina C.A - ERROR", JOptionPane.ERROR_MESSAGE);
					txtsalario.requestFocus();
					txtsalario.selectAll();
					return;
				}
				BD op = new BD();
				op.Conectar("Hotel");
				try {
					if(op.BuscarCargo(comboboxdepartamento.getSelectedItem().toString(), txtCargo.getText().trim().toLowerCase()) == true){
						JOptionPane.showMessageDialog(null, "Error: Este cargo ya existe dentro del sistema!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
						txtCargo.requestFocus();
						txtCargo.selectAll();
						return;
					}
					op.InsertarCargo(txtCargo.getText().trim().toLowerCase(), comboboxdepartamento.getSelectedItem().toString(), Double.parseDouble(valor));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				DefaultTableModel model = (DefaultTableModel) tablacargos.getModel();
				model.addRow(new Object[]{tablacargos.getRowCount() + 1, comboboxdepartamento.getSelectedItem().toString(), WordUtils.capitalizeFully(txtCargo.getText()), valor + " BsS", 0 + " empleados"});
				
				
				JOptionPane.showMessageDialog(null, "El cargo ha sido agregado al sistema", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
				txtCargo.setText("");
				txtCargo.requestFocus();
				txtsalario.setText("");
			
			}
		});
		txtsalario.setHorizontalAlignment(SwingConstants.CENTER);
		txtsalario.setForeground(Color.WHITE);
		txtsalario.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtsalario.setColumns(10);
		txtsalario.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtsalario.setBackground(new Color(0, 0, 0, 0));
		txtsalario.setBounds(493, 214, 159, 28);
		panel.add(txtsalario);
		
		JLabel lblSalario = new JLabel("Salario");
		lblSalario.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalario.setForeground(Color.WHITE);
		lblSalario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSalario.setBounds(364, 211, 122, 33);
		panel.add(lblSalario);
		
		
		
		
		
		PantallaAgregarCargo.setVisible(true);
	}
}



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

public class EliminarDepartamento {
	private static JTable tabladepartamento;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaAgregarCuarto = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaAgregarCuarto.setTitle("Hotel Mi Reina C.A - Eliminar departamento");
		PantallaAgregarCuarto.getContentPane().setBackground(Color.WHITE);
		PantallaAgregarCuarto.getContentPane().setLayout(null);
		
	
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(EliminarDepartamento.class.getResource("/imagenes/BG.jpg"))).getImage());
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
		btnSalir.setIcon(new ImageIcon(EliminarDepartamento.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setFocusPainted(false);
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setBounds(900, 586, 106, 37);
		panel.add(btnSalir);
		
		JLabel lblNuevoCargo = new JLabel("Cargo");
		lblNuevoCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoCargo.setForeground(Color.WHITE);
		lblNuevoCargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNuevoCargo.setBounds(620, 221, 62, 33);
		panel.add(lblNuevoCargo);
		
		JComboBox comboboxnuevocargo = new JComboBox();
		comboboxnuevocargo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
		comboboxnuevocargo.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboboxnuevocargo.setBounds(684, 224, 180, 28);
		panel.add(comboboxnuevocargo);
		
		JComboBox comboboxnuevodepartamento = new JComboBox();
		comboboxnuevodepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BD op = new BD();
				op.Conectar("Hotel");
				try {
					op.ListaCargos(comboboxnuevodepartamento.getSelectedItem().toString(), comboboxnuevocargo);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		comboboxnuevodepartamento.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
		comboboxnuevodepartamento.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboboxnuevodepartamento.setBounds(400, 223, 221, 28);
		panel.add(comboboxnuevodepartamento);
		
		JComboBox comboboxdepartamentos = new JComboBox();
		comboboxdepartamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		comboboxdepartamentos.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboboxdepartamentos.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
		comboboxdepartamentos.setBounds(152, 176, 282, 28);
		panel.add(comboboxdepartamentos);
		
		JButton btnAceptar = new JButton("");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboboxdepartamentos.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un departamento para modificar!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(comboboxnuevodepartamento.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un nuevo departamento!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(comboboxnuevocargo.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un nuevo cargo!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				BD op = new BD();
				try {
					op.Conectar("Hotel");
					op.EliminarDepartamento(comboboxdepartamentos.getSelectedItem().toString(), comboboxnuevodepartamento.getSelectedItem().toString(), 
							comboboxnuevocargo.getSelectedItem().toString());
					
					JOptionPane.showMessageDialog(null, "El departamento ha sido eliminado del sistema", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
					op.ListaDepartamentos(comboboxdepartamentos);
					op.ListaDepartamentos(comboboxnuevodepartamento);
					op.CargarDepartamentos(tabladepartamento);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				comboboxdepartamentos.setSelectedItem("Seleccionar");
				comboboxnuevodepartamento.setSelectedItem("Seleccionar");
				comboboxnuevocargo.setSelectedItem("Seleccionar");
				
				
				
			}
		});
		btnAceptar.setIcon(new ImageIcon(EliminarDepartamento.class.getResource("/imagenes/AcceptIcon.png")));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBackground(new Color(0, 51, 51));
		btnAceptar.setBounds(900, 546, 53, 37);
		panel.add(btnAceptar);
		
		JButton btnBorrar = new JButton("");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				comboboxdepartamentos.setSelectedItem("Seleccionar");
				comboboxnuevocargo.setSelectedItem("Seleccionar");
				comboboxnuevodepartamento.setSelectedItem("Seleccionar");
			}
		});
		btnBorrar.setIcon(new ImageIcon(EliminarDepartamento.class.getResource("/imagenes/EraserIcon.png")));
		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBorrar.setFocusPainted(false);
		btnBorrar.setBackground(new Color(0, 51, 51));
		btnBorrar.setBounds(953, 546, 53, 37);
		panel.add(btnBorrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		scrollPane.setBounds(51, 263, 823, 353);
		panel.add(scrollPane);
		
		tabladepartamento = new JTable();
		tabladepartamento.setRowSelectionAllowed(false);
		tabladepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		tabladepartamento.setGridColor(new Color(255, 255, 255));
		tabladepartamento.setForeground(new Color(255, 255, 255));
		tabladepartamento.setBorder(new LineBorder(new Color(0, 51, 51), 1, true));
		tabladepartamento.setFillsViewportHeight(true);
		tabladepartamento.setBackground(new Color(0, 51, 51));
		tabladepartamento.setShowGrid(false);
		tabladepartamento.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"<html><b>ID</b></html>", "<html><b>Nombre</b></html>", "<html><b>N\u00B0 Empleados</b></html>"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tabladepartamento.getColumnModel().getColumn(0).setResizable(false);
		tabladepartamento.getColumnModel().getColumn(1).setResizable(false);
		tabladepartamento.getColumnModel().getColumn(2).setResizable(false);
		tabladepartamento.getColumnModel().getColumn(2).setPreferredWidth(96);
		tabladepartamento.setShowHorizontalLines(true);
		scrollPane.setViewportView(tabladepartamento);
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		
		headerRenderer.setBackground(new Color(0, 51, 51));
		headerRenderer.setForeground(Color.WHITE);
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);
		headerRenderer.setOpaque(true);
		tabladepartamento.getTableHeader().setReorderingAllowed(false);

		for (int i = 0; i < tabladepartamento.getModel().getColumnCount(); i++) {
		        tabladepartamento.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		        tabladepartamento.getColumnModel().getColumn(i).setCellRenderer( headerRenderer );
		     
		}
		
		PantallaAgregarCuarto.setIconImage(Toolkit.getDefaultToolkit().getImage(EliminarDepartamento.class.getResource("/imagenes/HotelIcon.png")));
		PantallaAgregarCuarto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaAgregarCuarto.setResizable(false);
		PantallaAgregarCuarto.setSize(1024, 668);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaAgregarCuarto.setLocation(dim.width/2-PantallaAgregarCuarto.getSize().width/2, dim.height/2-PantallaAgregarCuarto.getSize().height/2);
		
		BD abc = new BD();
		try {
			abc.CargarDepartamentos(tabladepartamento);
			abc.ListaDepartamentos(comboboxdepartamentos);
			abc.ListaDepartamentos(comboboxnuevodepartamento);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lblNombre.setText(WordUtils.capitalizeFully(PantallaAcceso.Usuario));
		lblCargo.setText(WordUtils.capitalizeFully(PantallaAcceso.cargo));
		lblTurno_1.setText(WordUtils.capitalizeFully(PantallaAcceso.Turno));
		
		JLabel lblAgregarCargo = new JLabel("Eliminar departamento");
		lblAgregarCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarCargo.setForeground(Color.WHITE);
		lblAgregarCargo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblAgregarCargo.setBounds(65, 129, 267, 33);
		panel.add(lblAgregarCargo);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartamento.setForeground(Color.WHITE);
		lblDepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDepartamento.setBounds(51, 173, 91, 33);
		panel.add(lblDepartamento);
		
		
		
		JLabel lblNuevoDepartamento = new JLabel("Nuevo departamento");
		lblNuevoDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoDepartamento.setForeground(Color.WHITE);
		lblNuevoDepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNuevoDepartamento.setBounds(262, 221, 139, 33);
		panel.add(lblNuevoDepartamento);
		
		JLabel lblMoverEmpleadosA = new JLabel("Mover empleados a ------->");
		lblMoverEmpleadosA.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoverEmpleadosA.setForeground(new Color(0, 153, 255));
		lblMoverEmpleadosA.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblMoverEmpleadosA.setBounds(51, 221, 245, 33);
		panel.add(lblMoverEmpleadosA);
		
		
		
		
		
		PantallaAgregarCuarto.setVisible(true);
	}
}



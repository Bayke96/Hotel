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

public class PantallaEliminarCargo {
	private static JTable tablacargos;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaModificarCargo = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaModificarCargo.setTitle("Hotel Mi Reina C.A - Eliminar Cargo");
		PantallaModificarCargo.getContentPane().setBackground(Color.WHITE);
		PantallaModificarCargo.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(PantallaEliminarCargo.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setDoubleBuffered(false);
		panel.setFocusable(false);
		panel.setFocusTraversalKeysEnabled(false);
		panel.setOpaque(false);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1012, 640);
		PantallaModificarCargo.getContentPane().add(panel);
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
				PantallaModificarCargo.dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(PantallaEliminarCargo.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setFocusPainted(false);
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setBounds(893, 586, 113, 37);
		panel.add(btnSalir);
		
		JLabel lblNuevoCargo = new JLabel("Nuevo cargo");
		lblNuevoCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoCargo.setForeground(Color.WHITE);
		lblNuevoCargo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNuevoCargo.setBounds(392, 207, 97, 33);
		panel.add(lblNuevoCargo);
		
		JComboBox comboboxnuevo = new JComboBox();
		JComboBox comboboxnuevocargo = new JComboBox();
		comboboxnuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BD op = new BD();
				try {
					
					op.ListaCargos(comboboxnuevo.getSelectedItem().toString(), comboboxnuevocargo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		comboboxnuevo.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboboxnuevo.setBounds(167, 207, 213, 28);
		panel.add(comboboxnuevo);
		
		JComboBox comboboxdepartamento = new JComboBox();
		JComboBox comboBoxCargos = new JComboBox();
		
		comboboxdepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD op = new BD();
				try {
					if(!comboboxdepartamento.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
						op.CargarCargos(comboboxdepartamento.getSelectedItem().toString(), tablacargos);
					}
					if(comboboxdepartamento.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
						op.CargarCargos(tablacargos);
					}
					op.ListaCargos(comboboxdepartamento.getSelectedItem().toString(), comboBoxCargos);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		comboboxdepartamento.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboboxdepartamento.setBounds(167, 166, 213, 28);
		panel.add(comboboxdepartamento);
		

	
		
		comboBoxCargos.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboBoxCargos.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
		comboBoxCargos.setBounds(485, 166, 212, 28);
		panel.add(comboBoxCargos);
		
		
		JButton btnAceptar = new JButton("");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboboxdepartamento.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")) {
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un departamento!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(comboboxnuevo.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")) {
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un nuevo departamento!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(comboboxdepartamento.getSelectedItem().toString().equalsIgnoreCase(comboboxnuevo.getSelectedItem().toString()) &&
						comboBoxCargos.getSelectedItem().toString().equalsIgnoreCase(comboboxnuevocargo.getSelectedItem().toString())) {
					JOptionPane.showMessageDialog(null, "Error: El cargo a donde va a mover a los empleados no puede ser el mismo que va a eliminar!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(comboBoxCargos.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un cargo!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(comboboxnuevocargo.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")) {
					JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un nuevo cargo!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				BD op = new BD();
				try {
					op.EliminarCargo(comboboxdepartamento.getSelectedItem().toString(), comboBoxCargos.getSelectedItem().toString(), 
							comboboxnuevo.getSelectedItem().toString(), comboboxnuevocargo.getSelectedItem().toString());
					
					op.CargarCargos(tablacargos);
					comboBoxCargos.setSelectedItem("Seleccionar");
					comboboxdepartamento.setSelectedItem("Seleccionar");
					comboboxnuevo.setSelectedItem("Seleccionar");
					
					op.ListaDepartamentos(comboboxdepartamento);
					op.ListaDepartamentos(comboboxnuevo);
					
					op.Desconectar();
					
					JOptionPane.showMessageDialog(null, "Cargo eliminado!", "Hotel Mi Reina C.A", JOptionPane.WARNING_MESSAGE);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
				
			}
		});
		btnAceptar.setIcon(new ImageIcon(PantallaEliminarCargo.class.getResource("/imagenes/AcceptIcon.png")));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBackground(new Color(0, 51, 51));
		btnAceptar.setBounds(813, 166, 61, 37);
		panel.add(btnAceptar);
		
		JButton btnBorrar = new JButton("");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxCargos.setSelectedItem("Seleccionar");
				comboboxnuevo.setSelectedItem("Seleccionar");
				comboboxdepartamento.setSelectedItem("Seleccionar");
				comboboxnuevocargo.setSelectedItem("Seleccionar");
			}
		});
		btnBorrar.setIcon(new ImageIcon(PantallaEliminarCargo.class.getResource("/imagenes/EraserIcon.png")));
		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBorrar.setFocusPainted(false);
		btnBorrar.setBackground(new Color(0, 51, 51));
		btnBorrar.setBounds(813, 203, 61, 37);
		panel.add(btnBorrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		scrollPane.setBounds(48, 249, 826, 353);
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
					"<html><b>ID</b></html>", "<html><b>Departamento</b></html>", "<html><b>Cargo</b></html>", "<html><b>Salario</b></html>", "<html><b>N\u00B0 Empleados</b></html>"
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
		
		PantallaModificarCargo.setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaEliminarCargo.class.getResource("/imagenes/HotelIcon.png")));
		PantallaModificarCargo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaModificarCargo.setResizable(false);
		PantallaModificarCargo.setSize(1024, 668);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaModificarCargo.setLocation(dim.width/2-PantallaModificarCargo.getSize().width/2, dim.height/2-PantallaModificarCargo.getSize().height/2);
		
		BD abc = new BD();
		try {
			abc.CargarCargos(tablacargos);
			abc.ListaDepartamentos(comboboxdepartamento);
			abc.ListaDepartamentos(comboboxnuevo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lblNombre.setText(WordUtils.capitalizeFully(PantallaAcceso.Usuario));
		lblCargo.setText(WordUtils.capitalizeFully(PantallaAcceso.cargo));
		lblTurno_1.setText(WordUtils.capitalizeFully(PantallaAcceso.Turno));
		
		JLabel lblCargo_1 = new JLabel("Cargo");
		lblCargo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo_1.setForeground(Color.WHITE);
		lblCargo_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCargo_1.setBounds(381, 163, 97, 33);
		panel.add(lblCargo_1);
		
		JLabel lblModificarCargo = new JLabel("Eliminar cargo");
		lblModificarCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificarCargo.setForeground(Color.WHITE);
		lblModificarCargo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblModificarCargo.setBounds(113, 117, 267, 33);
		panel.add(lblModificarCargo);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartamento.setForeground(Color.WHITE);
		lblDepartamento.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDepartamento.setBounds(52, 164, 106, 33);
		panel.add(lblDepartamento);
		
		JLabel lblMover = new JLabel("Mover empleados");
		lblMover.setHorizontalAlignment(SwingConstants.CENTER);
		lblMover.setForeground(Color.WHITE);
		lblMover.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblMover.setBounds(52, 204, 106, 33);
		panel.add(lblMover);
		
	
		comboboxnuevocargo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
		comboboxnuevocargo.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboboxnuevocargo.setBounds(495, 210, 202, 28);
		panel.add(comboboxnuevocargo);
		
		
		
		
		
		
		
		
		
		
		
		PantallaModificarCargo.setVisible(true);
	}
}



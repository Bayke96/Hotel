package frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class AjustarPrecios {
	private static JTable tablacuartos;
	private static JTextField txtaumentar;
	private static JTextField txtdisminuir;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaVisualizarCuartos = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaVisualizarCuartos.setTitle("Hotel Mi Reina C.A - Ajustar Precios");
		PantallaVisualizarCuartos.getContentPane().setBackground(Color.WHITE);
		PantallaVisualizarCuartos.getContentPane().setLayout(null);
		
		UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
	            new CustomeBorder(), 
	            new EmptyBorder(new Insets(4,4,4,4))));
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(AjustarPrecios.class.getResource("/imagenes/BG.jpg"))).getImage());
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
		btnSalir.setIcon(new ImageIcon(AjustarPrecios.class.getResource("/imagenes/LogoutIcon(1).png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalir.setFocusPainted(false);
		btnSalir.setBackground(new Color(0, 51, 51));
		btnSalir.setBounds(455, 527, 106, 37);
		panel.add(btnSalir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		scrollPane.setBounds(45, 242, 926, 273);
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
		
		PantallaVisualizarCuartos.setIconImage(Toolkit.getDefaultToolkit().getImage(AjustarPrecios.class.getResource("/imagenes/HotelIcon.png")));
		PantallaVisualizarCuartos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaVisualizarCuartos.setResizable(false);
		PantallaVisualizarCuartos.setSize(1024, 668);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaVisualizarCuartos.setLocation(dim.width/2-PantallaVisualizarCuartos.getSize().width/2, dim.height/2-PantallaVisualizarCuartos.getSize().height/2);
		
		BD abc = new BD();
		try {
			abc.CargarCuartosDesc(tablacuartos);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lblNombre.setText(WordUtils.capitalizeFully(PantallaAcceso.Usuario));
		lblCargo.setText(WordUtils.capitalizeFully(PantallaAcceso.cargo));
		lblTurno_1.setText(WordUtils.capitalizeFully(PantallaAcceso.Turno));
		
		JLabel lblAgregarCargo = new JLabel("Ajuste de precios");
		lblAgregarCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarCargo.setForeground(Color.WHITE);
		lblAgregarCargo.setFont(new Font("Script MT Bold", Font.BOLD, 31));
		lblAgregarCargo.setBounds(6, 117, 1000, 33);
		panel.add(lblAgregarCargo);
		
		JLabel lblNewLabel_1 = new JLabel("Aumentar precios (%)");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(180, 178, 149, 27);
		panel.add(lblNewLabel_1);
		
		txtaumentar = new JTextField();
		txtaumentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!txtaumentar.getText().trim().equalsIgnoreCase("") && !txtdisminuir.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe elegir un solo campo, aumentar o disminuir precios!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtaumentar.getText().trim().equalsIgnoreCase("") && !txtaumentar.getText().matches("[0-9]+")){
					
						JOptionPane.showMessageDialog(null, "Error: Este campo solo acepta números!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
						txtaumentar.requestFocus();
						txtaumentar.selectAll();
						return;
					
					
				}
				
				txtdisminuir.requestFocus();
				txtdisminuir.selectAll();
			}
		});
		txtaumentar.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtaumentar.setBackground(new Color(0, 0, 0, 0));
		txtaumentar.setForeground(new Color(0, 191, 255));
		txtaumentar.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtaumentar.setHorizontalAlignment(SwingConstants.CENTER);
		txtaumentar.setBounds(332, 177, 122, 28);
		panel.add(txtaumentar);
		txtaumentar.setColumns(10);
		
		JLabel lblDisminuirPrecios = new JLabel("Disminuir precios (%)");
		lblDisminuirPrecios.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisminuirPrecios.setForeground(Color.WHITE);
		lblDisminuirPrecios.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDisminuirPrecios.setBounds(470, 178, 149, 27);
		panel.add(lblDisminuirPrecios);
		
		txtdisminuir = new JTextField();
		txtdisminuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!txtaumentar.getText().trim().equalsIgnoreCase("") && !txtdisminuir.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe elegir un solo campo, aumentar o disminuir precios!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtdisminuir.getText().trim().equalsIgnoreCase("") && !txtdisminuir.getText().matches("[0-9]+")){
					
						JOptionPane.showMessageDialog(null, "Error: Este campo solo acepta números!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
						txtdisminuir.requestFocus();
						txtdisminuir.selectAll();
						return;
					
					
				}
				
				txtaumentar.requestFocus();
				txtaumentar.selectAll();
			}
		});
		txtdisminuir.setHorizontalAlignment(SwingConstants.CENTER);
		txtdisminuir.setForeground(new Color(0, 191, 255));
		txtdisminuir.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtdisminuir.setColumns(10);
		txtdisminuir.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtdisminuir.setBackground(new Color(0, 0, 0, 0));
		txtdisminuir.setBounds(622, 177, 122, 28);
		panel.add(txtdisminuir);
		
		JButton btnaceptar = new JButton("");
		btnaceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtaumentar.getText().trim().equalsIgnoreCase("") && txtdisminuir.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe rellenar uno de los 2 campos!", "Hotel Mi Reina C.A - Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!txtaumentar.getText().trim().equalsIgnoreCase("") && !txtdisminuir.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe elegir un solo campo, aumentar o disminuir precios!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtaumentar.setText("");
					txtdisminuir.setText("");
					return;
				}
				if(!txtaumentar.getText().trim().equalsIgnoreCase("") && !txtaumentar.getText().matches("[0-9]+")){
					
					JOptionPane.showMessageDialog(null, "Error: Este campo solo acepta números!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					txtaumentar.requestFocus();
					txtaumentar.selectAll();
					return;
				
				
			}
				if(!txtdisminuir.getText().trim().equalsIgnoreCase("") && !txtdisminuir.getText().matches("[0-9]+")){
					
						JOptionPane.showMessageDialog(null, "Error: Este campo solo acepta números!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
						txtdisminuir.requestFocus();
						txtdisminuir.selectAll();
						return;
					
					
				}
				BD op = new BD();
				op.Conectar("Hotel");
				if(!txtaumentar.getText().trim().equalsIgnoreCase("") && txtaumentar.getText().matches("[0-9]+")){
					try {
						op.AumentarPrecio(Integer.parseInt(txtaumentar.getText()));
						txtaumentar.setText("");
						JOptionPane.showMessageDialog(null, "Precios aumentados!", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
						op.CargarCuartosDesc(tablacuartos);
					} catch (NumberFormatException e1) {
						try {
							op.conn.rollback();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(!txtdisminuir.getText().trim().equalsIgnoreCase("") && txtdisminuir.getText().matches("[0-9]+")){
					try {
						op.DisminuirPrecio(Integer.parseInt(txtdisminuir.getText()));
						txtdisminuir.setText("");
						JOptionPane.showMessageDialog(null, "Precios reducidos!", "Hotel Mi Reina C.A", JOptionPane.INFORMATION_MESSAGE);
						op.CargarCuartosDesc(tablacuartos);
					} catch (NumberFormatException e1) {
						try {
							op.conn.rollback();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnaceptar.setIcon(new ImageIcon(AjustarPrecios.class.getResource("/imagenes/AcceptIcon.png")));
		btnaceptar.setForeground(Color.WHITE);
		btnaceptar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnaceptar.setFocusPainted(false);
		btnaceptar.setBackground(new Color(0, 51, 51));
		btnaceptar.setBounds(863, 201, 53, 37);
		panel.add(btnaceptar);
		
		JButton btnborrar = new JButton("");
		btnborrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtaumentar.setText("");
				txtdisminuir.setText("");
				
				
				txtaumentar.requestFocus();
				txtaumentar.selectAll();
			}
		});
		btnborrar.setIcon(new ImageIcon(AjustarPrecios.class.getResource("/imagenes/EraserIcon.png")));
		btnborrar.setForeground(Color.WHITE);
		btnborrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnborrar.setFocusPainted(false);
		btnborrar.setBackground(new Color(0, 51, 51));
		btnborrar.setBounds(918, 201, 53, 37);
		panel.add(btnborrar);
		
		
		
		
		
		PantallaVisualizarCuartos.setVisible(true);
	}
}



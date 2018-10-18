package pantallas;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;

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
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
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

import org.apache.commons.lang3.text.WordUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaAcceso {
	private static JTextField txtUsuario;
	private static JPasswordField passwordField;
	
	public static double IVA = 0.16;
	
	public static String Usuario = "";
	public static String departamento = "";
	public static String cargo = "";
	public static String Turno = "";

	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JFrame PantallaAcc = new JFrame("Hotel Mi Reina - Autorización de Acceso");
		PantallaAcc.getContentPane().setBackground(Color.WHITE);
		PantallaAcc.getContentPane().setLayout(null);
		
		BD op = new BD();
		try{
			op.Conectar("Hotel");
		}
		catch(Exception x){
			
		}
		
		ImagePanel panel = new ImagePanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(PantallaAcceso.class.getResource("/imagenes/BG.jpg"))).getImage());
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 500, 534);
		PantallaAcc.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hotel Mi Reina C.A");
		lblNewLabel.setFont(new Font("Script MT Bold", Font.BOLD, 24));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 204, 482, 44);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(6, 260, 482, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(new Font("Script MT Bold", Font.BOLD, 20));
		lblContrasea.setBounds(6, 331, 482, 23);
		panel.add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtUsuario.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir un nombre de usuario!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtUsuario.getText().trim().matches("[0-9A-Za-z\\s-]+")){
					JOptionPane.showMessageDialog(null, "Error: Ha introducido caracteres invalidos en el nombre de usuario!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				
				
				passwordField.requestFocus();
				passwordField.selectAll();
			}
		});
		txtUsuario.setBackground(new Color(0, 0, 0, 0));
		txtUsuario.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setForeground(new Color(255, 255, 255));
		txtUsuario.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txtUsuario.setBounds(163, 283, 168, 28);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(passwordField.getPassword()).trim().equals("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir una contraseña!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(passwordField.getPassword().length < 10){
					JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtUsuario.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir un nombre de usuario!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtUsuario.getText().trim().matches("[0-9A-Za-z\\s-]+")){
					JOptionPane.showMessageDialog(null, "Error: Ha introducido caracteres invalidos en el nombre de usuario!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				PantallaAcceso.Usuario = WordUtils.capitalize(txtUsuario.getText());
				
				BD op = new BD();
				op.Conectar("Hotel");
				
				if(op.Acceso(txtUsuario.getText().trim(), passwordField.getPassword()) == true) {
					PantallaAcc.dispose();
					return;
				}
				
				
				
				
				
				
			}
		});
		passwordField.setEchoChar('x');
		passwordField.setBackground(new Color(0, 0, 0, 0));
		passwordField.setFont(new Font("SansSerif", Font.BOLD, 12));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setForeground(new Color(255, 255, 255));
		passwordField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		passwordField.setBounds(174, 354, 144, 28);
		panel.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PantallaAcceso.class.getResource("/imagenes/HotelIcon.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(114, 6, 267, 190);
		panel.add(lblNewLabel_2);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtUsuario.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir un nombre de usuario!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!txtUsuario.getText().trim().matches("[0-9A-Za-z\\s-]+")){
					JOptionPane.showMessageDialog(null, "Error: Ha introducido caracteres invalidos en el nombre de usuario!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(String.valueOf(passwordField.getPassword()).trim().equals("")){
					JOptionPane.showMessageDialog(null, "Error: Debe introducir una contraseña!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(passwordField.getPassword().length < 10){
					JOptionPane.showMessageDialog(null, "Error: La contraseña debe contener un minimo de 10 caracteres!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				PantallaAcceso.Usuario = WordUtils.capitalize(txtUsuario.getText());
				
				BD op = new BD();
				op.Conectar("Hotel");
				
				if(op.Acceso(txtUsuario.getText().trim(), passwordField.getPassword()) == true) {
					PantallaAcc.dispose();
					return;
				}
			}
		});
		btnIngresar.setFocusPainted(false);
		btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnIngresar.setBackground(new Color(0, 128, 128));
		btnIngresar.setForeground(new Color(255, 255, 255));
		btnIngresar.setBounds(141, 416, 102, 28);
		panel.add(btnIngresar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setFocusPainted(false);
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsuario.setText("");
				passwordField.setText("");
			}
		});
		btnBorrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBorrar.setBackground(new Color(0, 128, 128));
		btnBorrar.setForeground(new Color(255, 255, 255));
		btnBorrar.setBounds(245, 416, 102, 28);
		panel.add(btnBorrar);
		
		JLabel lblNewLabel_3 = new JLabel("\u00BFOlvido su contrase\u00F1a? Click aqui");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PantallaAcc.dispose();
				PantallaRecuperarContraseña.main(args);
			}
		});
		lblNewLabel_3.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(141, 456, 206, 16);
		panel.add(lblNewLabel_3);
		PantallaAcc.setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaAcceso.class.getResource("/imagenes/HotelIcon.png")));
		PantallaAcc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PantallaAcc.setResizable(false);
		PantallaAcc.setSize(500, 524);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		PantallaAcc.setLocation(dim.width/2-PantallaAcc.getSize().width/2, dim.height/2-PantallaAcc.getSize().height/2);
		
		
		
		PantallaAcc.setVisible(true);
	}
}


class ImagePanel extends JPanel {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;

	  public ImagePanel(String img) {
	    this(new ImageIcon(img).getImage());
	  }

	  public ImagePanel(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }

	}


@SuppressWarnings("serial") class CustomeBorder extends AbstractBorder{
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y,
            int width, int height) {
        super.paintBorder(c, g, x, y, width, height);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.WHITE);
        Shape shape = new RoundRectangle2D.Float(0, 0, c.getWidth()-1, c.getHeight()-1,9, 9);
        g2d.draw(shape);
    }
}


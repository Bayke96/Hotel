package pantallas;

import java.awt.Color;
import java.awt.Component;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Hashtable;
import java.util.Random;
import java.util.Stack;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.mindrot.jbcrypt.BCrypt;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;


public class BD {
	
	public String Usuario = "root";
	public String Contraseña = "";
	public Connection conn = null;
	
	
	
	
	public void Conectar(String BD){
		
		   
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      
		      conn = DriverManager.getConnection("jdbc:mysql://localhost/" + BD, Usuario, Contraseña);
		      
		   }
		   catch(CommunicationsException x){
			   
			   JOptionPane.showMessageDialog(null, "Error: Debe encender el servidor y MySQL para utilizar este programa!", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
			   System.exit(0);
			   return;
		   }
		   catch(Exception e){
			   try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			   System.err.println(e);
			   
		   }
	}
	
	public void Desconectar(){
		try {
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
	
	
	
public BD(){
		
		String createDB = "CREATE DATABASE IF NOT EXISTS Hotel";
		PreparedStatement preparedStatement = null;
		
		
		
		 try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      
		      conn = DriverManager.getConnection("jdbc:mysql://localhost", Usuario, Contraseña);
		      preparedStatement = conn.prepareStatement(createDB);

		      preparedStatement.executeUpdate();
		      
		      Conectar("Hotel");
		      String sqlCreate = "CREATE TABLE IF NOT EXISTS Usuarios" 
				      + "  (ID_Usuario INTEGER NOT NULL AUTO_INCREMENT,"
				      + "   Nombre_Usuario            VARCHAR(72) NOT NULL UNIQUE,"
				      + "   Contraseña          VARCHAR(128) NOT NULL,"
				      + "   Departamento          INTEGER NOT NULL,"
				      + "   Cargo          INTEGER NOT NULL,"
				      + "   Nombres          VARCHAR(96) NULL,"
				      + "   Apellidos          VARCHAR(96) NULL,"
				      + "   Cedula          INTEGER NULL,"
				      + "   RIF          VARCHAR(30) NULL,"
				      + "   Direccion          VARCHAR(128) NULL,"
				      + "   Telefono          VARCHAR(50) NULL,"
				      + "   Correo          VARCHAR(50) NULL,"
				      + "   Turno          VARCHAR(50) NOT NULL,"
				      + " PRIMARY KEY ( ID_Usuario ),"
				      + "FOREIGN KEY (Departamento) REFERENCES departamentos(ID_Departamento) ON UPDATE CASCADE,"
				      + "FOREIGN KEY (Cargo) REFERENCES Cargos(Cargo_ID) ON UPDATE CASCADE)";

		 	 Statement stmt = conn.createStatement();
		 	 stmt.execute(sqlCreate);
		 	 
		 	sqlCreate = "CREATE TABLE IF NOT EXISTS Departamentos" 
		 		     + "  (ID_Departamento INTEGER NOT NULL AUTO_INCREMENT,"
		 		     + "   Nombre            VARCHAR(128) NOT NULL UNIQUE,"
		 		    + "   Cantidad_Empleados            INTEGER NOT NULL DEFAULT 0,"
		 		     + " PRIMARY KEY ( ID_Departamento ))";

		 			 stmt = conn.createStatement();
		 			 stmt.execute(sqlCreate);
		 	 
		 	sqlCreate = "CREATE TABLE IF NOT EXISTS Cargos" 
		 		     + "  (Cargo_ID INTEGER NOT NULL AUTO_INCREMENT,"
			   		    + "   Departamento            INTEGER NOT NULL,"
			   		     + "   Nombre            VARCHAR(128) NOT NULL,"
			   		  + "   Salario            DOUBLE NOT NULL,"
			   		    + "   Cantidad_Empleados            INTEGER NOT NULL DEFAULT 0,"
			   		     + " PRIMARY KEY ( Cargo_ID ),"
			   		     + "FOREIGN KEY (departamento) REFERENCES departamentos(ID_Departamento) ON DELETE CASCADE ON UPDATE CASCADE)";

		 			 stmt = conn.createStatement();
		 			 stmt.execute(sqlCreate);
		 			 
		 			 
		 			 
		 			sqlCreate = "CREATE TABLE IF NOT EXISTS Clientes" 
				 		     + "  (ID_Cliente INTEGER NOT NULL AUTO_INCREMENT,"
				 		     + "   Nombre            VARCHAR(128) NOT NULL,"
				 		    + "   Cedula            INTEGER NULL,"
				 		   + "   Telefono            VARCHAR(128) NULL,"
				 		  + "   Direccion            VARCHAR(128) NULL,"
				 		 + "   Correo            VARCHAR(128) NULL,"
				 		 + "   RIF            VARCHAR(128) NULL,"
				 		     + " PRIMARY KEY ( ID_Cliente ))";

				 			 stmt = conn.createStatement();
				 			 stmt.execute(sqlCreate);
				 			 
				 			sqlCreate = "CREATE TABLE IF NOT EXISTS Modelos" 
						 		     + "  (ID_Modelo INTEGER NOT NULL AUTO_INCREMENT,"
						 		     + "   Nombre            VARCHAR(128) NOT NULL UNIQUE,"
						 		    + "   SubTotal            DOUBLE NOT NULL DEFAULT 0,"
						 		   + "   IVA            DOUBLE NOT NULL DEFAULT 0,"
						 		    + "   Precio            DOUBLE NOT NULL DEFAULT 0,"
						 		     + " PRIMARY KEY ( ID_Modelo ))";

						 			 stmt = conn.createStatement();
						 			 stmt.execute(sqlCreate);
		 			 
						 			sqlCreate = "CREATE TABLE IF NOT EXISTS Habitaciones" 
								 		     + "  (ID INTEGER NOT NULL AUTO_INCREMENT,"
								 		     + "   Modelo            INTEGER NULL,"
								 		     + " PRIMARY KEY ( ID ),"
								 		     + "FOREIGN KEY (Modelo) REFERENCES Modelos(ID_Modelo) ON UPDATE CASCADE)";

								 			 stmt = conn.createStatement();
								 			 stmt.execute(sqlCreate);
								 			 
								 			sqlCreate = "CREATE TABLE IF NOT EXISTS Reservas" 
										 		     + "  (ID INTEGER NOT NULL AUTO_INCREMENT,"
										 		     + "   Habitacion            INTEGER NOT NULL,"
										 		     + "   Cliente            INTEGER NOT NULL,"
										 		     + "   Inicio            DATE NOT NULL,"
										 		     + "   Fin            DATE NOT NULL,"
										 		     + "   Costo            DOUBLE NOT NULL,"
										 		     + " PRIMARY KEY ( ID ),"
										 		     + "CONSTRAINT check_dates CHECK (Inicio < Fin),"
										 		    + "FOREIGN KEY (Habitacion) REFERENCES Habitaciones(ID) ON UPDATE CASCADE,"
												    + "FOREIGN KEY (Cliente) REFERENCES Clientes(ID_Cliente) ON UPDATE CASCADE)";
												    

										 			 stmt = conn.createStatement();
										 			 stmt.execute(sqlCreate);
		 			
		 			 
		 			 
		      
		      Desconectar();
		   }
		   catch(Exception e){
			   System.err.println(e);
		   }
	      
	}



public boolean Acceso(String usuario, char[] contraseña){
	 try{
		boolean resultado = true;
	      //STEP 2: Register JDBC driver
		  ResultSet rs = null;
	      Conectar("Hotel");
	      
	     
	      
	      
	     
	     
	      String query = "SELECT Nombre_Usuario, Contraseña FROM Usuarios WHERE Nombre_Usuario = ?";
		  PreparedStatement pstmt = conn.prepareStatement(query); // create a statement
		  pstmt.setString(1, usuario.toLowerCase()); // set input parameter
		  rs = pstmt.executeQuery();
		  String User = "", Pass = "", conversion = "";
		  while (rs.next()) {
		        User = rs.getString(1);
		        Pass = rs.getString(2);
		  }
		  for(int i = 0; i < contraseña.length; i++){
			  conversion += contraseña[i];
		  }
		  if(User.equalsIgnoreCase("")){
			  JOptionPane.showMessageDialog(null, "Nombre de usuario invalido", "ERROR: Hotel Mi Reina C.A", JOptionPane.ERROR_MESSAGE);
			  resultado = false;
			  return false;
		  }
		  String codigo = new String(contraseña);
		  
		  if(BCrypt.checkpw(String.valueOf(contraseña), Pass) == false){
			  JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR: Hotel Mi Reina C.A", JOptionPane.ERROR_MESSAGE);
			  resultado = false;
			  return false;
		  }
		 
		  
		
	      Desconectar();
	      
	      
	      
	     
	      PantallaPrincipal.main(null);
	      
	      return resultado;
		  
	   }
	   catch(Exception e){
		   System.err.println(e);
		   return false;
	   }
	 finally{
		 Desconectar();
	 }
    
}



public void CargarPerfil(String usuario){
	String RIF = "", nombres = "" , apellidos = "", direccion = "", telefono = "", correo = "", turno = "", cargo = "";
	 try{
		
	      //STEP 2: Register JDBC driver
		  ResultSet rs = null;
	      Conectar("Hotel");
	      
	     
	      
	      
	     
	     
	      String query = "SELECT Nombres, Apellidos, RIF, Direccion, Telefono, Correo, Turno, Cargo FROM Usuarios WHERE Nombre_Usuario = ?";
		  PreparedStatement pstmt = conn.prepareStatement(query); // create a statement
		  pstmt.setString(1, usuario.toLowerCase()); // set input parameter
		  rs = pstmt.executeQuery();
		 
		  while (rs.next()) {
		       nombres = rs.getString("Nombres");
		       apellidos = rs.getString("Apellidos");
		       RIF = rs.getString("RIF");
		       direccion = rs.getString("Direccion");
		       telefono = rs.getString("Telefono");
		       correo = rs.getString("Correo");
		       turno = rs.getString("Cargo");
		       cargo = rs.getString("Turno");
		  }
		  
	      Desconectar();
	
	   }
	   catch(Exception e){
		   System.err.println(e);
		  
	   }
	 finally{
		 Desconectar();
	 }
  
}



public void CrearUsuario(String usuario, String contraseña, String departamento, String cargo, String nombres, String apellidos, int cedula, String RIF, String direccion
		, String telefono, String correo, String turno) throws SQLException{

	
	String mensaje = "";
	Conectar("Hotel");
	 String sqlCreate = "CREATE TABLE IF NOT EXISTS Usuarios" 
		      + "  (ID_Usuario INTEGER NOT NULL AUTO_INCREMENT,"
		      + "   Nombre_Usuario            VARCHAR(72) NOT NULL UNIQUE,"
		      + "   Contraseña          VARCHAR(128) NOT NULL,"
		      + "   Departamento          INTEGER NOT NULL,"
		      + "   Cargo          INTEGER NOT NULL,"
		      + "   Nombres          VARCHAR(96) NULL,"
		      + "   Apellidos          VARCHAR(96) NULL,"
		      + "   Cedula          INTEGER NULL,"
		      + "   RIF          VARCHAR(30) NULL,"
		      + "   Direccion          VARCHAR(128) NULL,"
		      + "   Telefono          VARCHAR(50) NULL,"
		      + "   Correo          VARCHAR(50) NULL,"
		      + "   Turno          VARCHAR(50) NOT NULL,"
		      + " PRIMARY KEY ( ID_Usuario ),"
		      + "FOREIGN KEY (Departamento) REFERENCES departamentos(ID_Departamento) ON UPDATE CASCADE,"
		      + "FOREIGN KEY (Cargo) REFERENCES Cargos(Cargo_ID) ON UPDATE CASCADE)";

		 	 Statement stmt = conn.createStatement();
		 	 stmt.execute(sqlCreate);
	
	
	 
	 Conectar("Hotel");
	 
	
	 
	 String selectSQL = "SELECT ID_Departamento FROM Departamentos WHERE Nombre = ?";
	  PreparedStatement preparedStatement = null;
	  
	
	 
	 
	 
	 
	 String insertTableSQL = "INSERT INTO Usuarios"
				+ "(Nombre_Usuario, Contraseña, Departamento, Cargo, Nombres, Apellidos, Cedula, RIF, Direccion, Telefono, Correo, Turno) VALUES"
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setString(1, usuario.toLowerCase());
	preparedStatement.setString(2, BCrypt.hashpw(contraseña, BCrypt.gensalt(12)));	
	preparedStatement.setInt(3, TransformarDepartamento(departamento.toLowerCase()));
	preparedStatement.setInt(4, TransformarCargo(departamento.toLowerCase(), cargo.toLowerCase()));
	preparedStatement.setString(5, nombres.toLowerCase());
	preparedStatement.setString(6, apellidos.toLowerCase());
	preparedStatement.setInt(7, cedula);
	preparedStatement.setString(8, RIF.toUpperCase());
	preparedStatement.setString(9, direccion.toLowerCase());
	preparedStatement.setString(10, telefono.toLowerCase());
	preparedStatement.setString(11, correo.toLowerCase());
	preparedStatement.setString(12, turno.toLowerCase());
	
	preparedStatement.executeUpdate();
	
	
	
	Conectar("Hotel");
	
	insertTableSQL = "UPDATE Departamentos SET Cantidad_Empleados = Cantidad_Empleados + 1 WHERE Nombre = ?";
	
	preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setString(1, departamento.toLowerCase());
	preparedStatement.executeUpdate();
	
	
	insertTableSQL = "UPDATE Cargos SET Cantidad_Empleados = Cantidad_Empleados + 1 WHERE Departamento = ? AND Nombre = ?";
	
	preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setInt(1, TransformarDepartamento(departamento.toLowerCase()));
	preparedStatement.setString(2, cargo.toLowerCase());
	preparedStatement.executeUpdate();
	
	
	
	Desconectar();

}

public void ActualizarUsuario(String usuario, String departamento, String cargo, String nombres, String apellidos, int cedula, String RIF, String direccion
		, String telefono, String correo, String turno, String nombreviejo) throws SQLException{

	
	
	
	
	 
	 Conectar("Hotel");
	
	 
	 int depid = 0, cargoid = 0;
	 try{
		 String selectSQL = "SELECT Departamento, Cargo FROM Usuarios WHERE Nombre_Usuario = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, usuario.toLowerCase());
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	depid = rs.getInt("Departamento");
		  	cargoid = rs.getInt("Cargo");
		  }
		 
		 String insertTableSQL = "UPDATE Usuarios SET Nombre_Usuario = ?, Departamento = ?, Cargo = ?, Nombres = ?, Apellidos = ?, Cedula = ?, RIF = ?, Direccion = ?,"
		 		+ "Telefono = ?, Correo = ?, Turno = ? WHERE Nombre_Usuario = ?";
		PreparedStatement ps = conn.prepareStatement(insertTableSQL);
		ps.setString(1, usuario.toLowerCase());
		ps.setInt(2, TransformarDepartamento(departamento));
		ps.setInt(3, TransformarCargo(departamento, cargo));
		ps.setString(4, nombres.toLowerCase());
		ps.setString(5, apellidos.toLowerCase());
		ps.setInt(6, cedula);
		ps.setString(7, RIF.toUpperCase());
		ps.setString(8, direccion.toLowerCase());
		ps.setString(9, telefono.toLowerCase());
		ps.setString(10, correo.toLowerCase());
		ps.setString(11, turno.toLowerCase());
		ps.setString(12, nombreviejo.toLowerCase());
		
		ps.executeUpdate();
		
		 
		Conectar("Hotel");
		
		
		insertTableSQL = "UPDATE Departamentos SET Cantidad_Empleados = Cantidad_Empleados - 1 WHERE ID_Departamento = ? AND Nombre = ?";
		
		ps = conn.prepareStatement(insertTableSQL);
		ps.setInt(1, depid);
		ps.setString(2, DestransformarDepartamento(depid).toLowerCase());
		ps.executeUpdate();
		
		
		Conectar("Hotel");
		
		
		
		insertTableSQL = "UPDATE Departamentos SET Cantidad_Empleados = Cantidad_Empleados + 1 WHERE ID_Departamento = ? AND Nombre = ?";
		
		ps = conn.prepareStatement(insertTableSQL);
		ps.setInt(1, TransformarDepartamento(departamento));
		ps.setString(2, departamento.toLowerCase());
		ps.executeUpdate();
		
		Conectar("Hotel");
		
		
		insertTableSQL = "UPDATE Cargos SET Cantidad_Empleados = Cantidad_Empleados - 1 WHERE Departamento = ? AND Nombre = ?";
		ps = conn.prepareStatement(insertTableSQL);
		ps.setInt(1, depid);
		ps.setString(2, DestransformarCargo(depid, cargoid).toLowerCase());
		ps.executeUpdate();
		
		
		Conectar("Hotel");
		
		insertTableSQL = "UPDATE Cargos SET Cantidad_Empleados = Cantidad_Empleados + 1 WHERE Departamento = ? AND Nombre = ?";
		ps = conn.prepareStatement(insertTableSQL);
		ps.setInt(1, TransformarDepartamento(departamento));
		ps.setString(2, cargo.toLowerCase());
		ps.executeUpdate();
		
		
		
		
		
		
	 }
	 catch(SQLException x){
		 conn.rollback();
		 Desconectar();
		 x.printStackTrace();
		 
	 }
	 finally{
		 Desconectar();
	 }
	 
	 

}


public void ActualizarPerfil(String nombres, String apellidos, int cedula, String RIF, String direccion
		, String telefono, String correo, String usuario) throws SQLException{

	
	
	
	
	 
	 Conectar("Hotel");
	 
	 
	 String insertTableSQL = "UPDATE Usuarios SET Nombres = ?, Apellidos = ?, Cedula = ?, RIF = ?, Direccion = ?,"
	 		+ "Telefono = ?, Correo = ? WHERE Nombre_Usuario = ?";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	
	preparedStatement.setString(1, nombres.toLowerCase());
	preparedStatement.setString(2, apellidos.toLowerCase());
	preparedStatement.setInt(3, cedula);
	preparedStatement.setString(4, RIF.toUpperCase());
	preparedStatement.setString(5, direccion.toLowerCase());
	preparedStatement.setString(6, telefono.toLowerCase());
	preparedStatement.setString(7, correo.toLowerCase());
	preparedStatement.setString(8, usuario.toLowerCase());
	
	preparedStatement.executeUpdate();
	
	
	
	
	
	Desconectar();

}

public void ActualizarCliente(String nombre, int cedula, String telefono
		, String direccion, String correo, String rif, String vnombre) throws SQLException{

	
	
	
	
	 
	 Conectar("Hotel");
	 
	 
	 String insertTableSQL = "UPDATE Clientes SET Nombre = ?, Cedula = ?, Telefono = ?, RIF = ?, Direccion = ?,"
	 		+ "Correo = ? WHERE Nombre = ?";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	
	preparedStatement.setString(1, nombre.toLowerCase());
	preparedStatement.setInt(2, cedula);
	preparedStatement.setString(3, telefono.toLowerCase());
	
	preparedStatement.setString(4, rif.toUpperCase());
	preparedStatement.setString(5, direccion.toLowerCase());
	preparedStatement.setString(6, correo.toLowerCase());
	preparedStatement.setString(7, vnombre.toLowerCase());
	
	
	preparedStatement.executeUpdate();
	
	
	
	
	
	Desconectar();

}

public boolean BuscarUsuario(String nombre) throws SQLException{
	String resultado = "";
	boolean encontrado = false;
	Conectar("Hotel");
	try{
		  String selectSQL = "SELECT Nombre_Usuario FROM Usuarios WHERE Nombre_Usuario = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, nombre.toLowerCase());
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	resultado = rs.getString("Nombre_Usuario");
		  }
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	Desconectar();
	if(resultado == "") encontrado = false;
	if(resultado != "") encontrado = true;
    return encontrado;
}

public boolean BuscarCuarto(String nombre) throws SQLException{
	String resultado = "";
	boolean encontrado = false;
	Conectar("Hotel");
	try{
		  String selectSQL = "SELECT Nombre FROM Modelos WHERE Nombre = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, nombre.toLowerCase());
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	resultado = rs.getString("Nombre");
		  }
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	Desconectar();
	if(resultado == "") encontrado = false;
	if(resultado != "") encontrado = true;
    return encontrado;
}




public boolean VerificarContraseña(char[] password){
	String resultado = "";
	boolean encontrado = false;
	Conectar("Hotel");
	try{
		  String selectSQL = "SELECT Contraseña FROM Usuarios WHERE Nombre_Usuario = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, PantallaAcceso.Usuario.toLowerCase());
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	resultado = rs.getString("Contraseña");
		  }
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	Desconectar();
	if(BCrypt.checkpw(String.valueOf(password), resultado) == false) encontrado = false;
	if(BCrypt.checkpw(String.valueOf(password), resultado) == true) encontrado = true;
    return encontrado;
}

public void AumentarPrecio(int porcentaje) throws SQLException{
	Conectar("Hotel");
	conn.setAutoCommit(false);
	ArrayList<Integer> id = new ArrayList<Integer>();
	ArrayList<Double> precios = new ArrayList<Double>();
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	Statement stmt = conn.createStatement();
	
    String sql = "SELECT ID_Modelo, Precio FROM Modelos Order by ID_Modelo Asc";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
       id.add(rs.getInt("ID_Modelo"));
       precios.add(rs.getDouble("Precio"));
       
    }
    
   
    String por = "";
    if(porcentaje < 10) por = "0.0" + porcentaje;
    if(porcentaje >= 10) por = "0." + porcentaje;
    
    double aumento = 0.0;
    
    for(int i = 0; i < precios.size(); i++){
    	
    	if(porcentaje < 10) aumento = precios.get(i) * Double.parseDouble(por);
    	if(porcentaje >= 10) aumento = precios.get(i) * Double.parseDouble(por);
    	
    	precios.set(i, precios.get(i) + aumento);
    	
    	
    	aumento = 0.0;
    }
  
	
	String insertTableSQL = "";
	PreparedStatement preparedStatement = null;
	
	
	insertTableSQL = "UPDATE Modelos SET Precio = ?, IVA = ?, SubTotal = ? WHERE ID_Modelo = ?";
	preparedStatement = conn.prepareStatement(insertTableSQL);
	
	for(int i = 0; i < id.size(); i++){
		double iva = precios.get(i) * PantallaAcceso.IVA;
		double subtotal = precios.get(i) - iva;
		 
			preparedStatement.setDouble(1, Double.parseDouble(df.format(precios.get(i)).replaceAll(",", ".")));	
			preparedStatement.setDouble(2, Double.parseDouble(df.format(iva).replaceAll(",", ".")));
			preparedStatement.setDouble(3, Double.parseDouble(df.format(subtotal).replaceAll(",", ".")));
			preparedStatement.setInt(4, id.get(i));
		
	preparedStatement.addBatch();
	}
		
	
	preparedStatement.executeBatch();
	
	conn.commit();
	Desconectar();
	
	
}

public void DisminuirPrecio(int porcentaje) throws SQLException{
	Conectar("Hotel");
	conn.setAutoCommit(false);
	ArrayList<Integer> id = new ArrayList<Integer>();
	ArrayList<Double> precios = new ArrayList<Double>();
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	Statement stmt = conn.createStatement();
	
    String sql = "SELECT ID_Modelo, Precio FROM Modelos Order by ID_Modelo Asc";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
       id.add(rs.getInt("ID_Modelo"));
       precios.add(rs.getDouble("Precio"));
       
    }
    
   
    String por = "";
    if(porcentaje < 10) por = "0.0" + porcentaje;
    if(porcentaje >= 10) por = "0." + porcentaje;
    
    double disminucion = 0.0;
    
    for(int i = 0; i < precios.size(); i++){
    	
    	if(porcentaje < 10) disminucion = precios.get(i) * Double.parseDouble(por);
    	if(porcentaje >= 10) disminucion = precios.get(i) * Double.parseDouble(por);
    	
    	precios.set(i, precios.get(i) - disminucion);
    	
    	
    	disminucion = 0.0;
    }
  
	
	String insertTableSQL = "";
	PreparedStatement preparedStatement = null;
	
	
	insertTableSQL = "UPDATE Modelos SET Precio = ?, IVA = ?, SubTotal = ? WHERE ID_Modelo = ?";
	preparedStatement = conn.prepareStatement(insertTableSQL);
	
	for(int i = 0; i < id.size(); i++){
		double iva = precios.get(i) * PantallaAcceso.IVA;
		double subtotal = precios.get(i) - iva;
		 
			preparedStatement.setDouble(1, Double.parseDouble(df.format(precios.get(i)).replaceAll(",", ".")));	
			preparedStatement.setDouble(2, Double.parseDouble(df.format(iva).replaceAll(",", ".")));
			preparedStatement.setDouble(3, Double.parseDouble(df.format(subtotal).replaceAll(",", ".")));
			preparedStatement.setInt(4, id.get(i));
		
	preparedStatement.addBatch();
	}
		
	
	preparedStatement.executeBatch();
	
	conn.commit();
	Desconectar();
	
	
}

public void ActualizarContraseña(char[] password) throws SQLException{
	Conectar("Hotel");
	
	
	
	String insertTableSQL = "";
	PreparedStatement preparedStatement = null;
	
		 insertTableSQL = "UPDATE Usuarios SET Contraseña = ? WHERE Nombre_Usuario = ?";
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, BCrypt.hashpw(String.valueOf(password), BCrypt.gensalt(12)));	
			preparedStatement.setString(2, PantallaAcceso.Usuario.toLowerCase());
			
	
	
		
	preparedStatement.executeUpdate();
	Desconectar();
	
	
}

public void RecuperarContraseña(String usuario) throws SQLException{
	Conectar("Hotel");
	
	
	
	String insertTableSQL = "";
	PreparedStatement preparedStatement = null;
	
		 insertTableSQL = "UPDATE Usuarios SET Contraseña = ? WHERE Nombre_Usuario = ?";
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, BCrypt.hashpw("hotelmireina8056", BCrypt.gensalt(12)));	
			preparedStatement.setString(2, usuario.toLowerCase());
			
	
	
		
	preparedStatement.executeUpdate();
	Desconectar();
	
	
}



public void EliminarUsuario(String nombre) throws SQLException{
	Conectar("Hotel");
	
	int cargoid = 0, departamentoid = 0;
		
		 String selectSQL = "SELECT Departamento, Cargo FROM Usuarios WHERE Nombre_Usuario = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
			  preparedStatement.setString(1, nombre.toLowerCase());
			  ResultSet rs = preparedStatement.executeQuery();
			  while (rs.next()) {
			  	departamentoid = rs.getInt("Departamento");
			  	cargoid = rs.getInt("Cargo");
			  }
			
			  
			  String insertTableSQL = "UPDATE Departamentos SET Cantidad_Empleados = Cantidad_Empleados - 1 where Nombre = ?";
				preparedStatement = conn.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, DestransformarDepartamento(departamentoid).toLowerCase());
				preparedStatement .executeUpdate();
				
				Conectar("Hotel");
				
				insertTableSQL = "UPDATE Cargos SET Cantidad_Empleados = Cantidad_Empleados - 1 WHERE Departamento = ? AND Nombre = ?";
				preparedStatement = conn.prepareStatement(insertTableSQL);
				preparedStatement.setInt(1, departamentoid);
				preparedStatement.setString(2, DestransformarCargo(departamentoid, cargoid).toLowerCase());
				preparedStatement .executeUpdate();
	
				Conectar("Hotel");
	
	insertTableSQL = "DELETE FROM Usuarios where Nombre_Usuario = ?";
	preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setString(1, nombre.toLowerCase());
	preparedStatement .executeUpdate();
	
	
		
	
		
		
		
	Desconectar();
}


public void EliminarCargo(String viejodepartamento, String viejocargo, String nuevodepartamento, String nuevocargo) throws SQLException{
	Conectar("Hotel");
	int resultado = 0;
	
	int viejod = 0, viejoc = 0, nuevod = 0, nuevoc = 0;
	
	 String selectSQL = "SELECT Departamentos.ID_Departamento FROM Departamentos INNER JOIN Cargos ON Departamentos.ID_Departamento = Cargos.Departamento WHERE Departamentos.Nombre = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, viejodepartamento.toLowerCase());
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	viejod = rs.getInt("Departamentos.ID_Departamento");
		  }
		  
		  selectSQL = "SELECT Departamentos.ID_Departamento FROM Departamentos INNER JOIN Cargos ON Departamentos.ID_Departamento = Cargos.Departamento WHERE Departamentos.Nombre = ?";
			preparedStatement = conn.prepareStatement(selectSQL);
			  preparedStatement.setString(1, viejocargo.toLowerCase());
			  rs = preparedStatement.executeQuery();
			  while (rs.next()) {
			  	viejoc = rs.getInt("Departamentos.ID_Departamento");
			  	
			  	
			  }
			  
			  selectSQL = "SELECT Cantidad_Empleados FROM Departamentos WHERE Nombre = ?";
				preparedStatement = conn.prepareStatement(selectSQL);
				  preparedStatement.setString(1, viejodepartamento.toLowerCase());
				  rs = preparedStatement.executeQuery();
				  while (rs.next()) {
				  	resultado = rs.getInt("Cantidad_Empleados");
				  }
			  
			  selectSQL = "SELECT ID_Departamento FROM Departamentos WHERE Nombre = ?";
				preparedStatement = conn.prepareStatement(selectSQL);
				  preparedStatement.setString(1, nuevodepartamento.toLowerCase());
				  rs = preparedStatement.executeQuery();
				  while (rs.next()) {
				  	nuevod = rs.getInt("ID_Departamento");
				  }
				  
				  selectSQL = "SELECT Cargo_ID FROM Cargos WHERE Departamento = ?";
					preparedStatement = conn.prepareStatement(selectSQL);
					  preparedStatement.setInt(1, nuevod);
					  rs = preparedStatement.executeQuery();
					  while (rs.next()) {
					  	nuevoc = rs.getInt("Cargo_ID");
					  }
					  
					
				  
			
			  
			  String insertTableSQL = "UPDATE usuarios SET Departamento = ?, Cargo = ? WHERE Departamento = ? AND Cargo = ?";
				preparedStatement = conn.prepareStatement(insertTableSQL);
					preparedStatement.setInt(1, nuevod);
					preparedStatement.setInt(2, nuevoc);
					preparedStatement.setInt(3, viejod);
					preparedStatement.setInt(4, viejoc);
					preparedStatement .executeUpdate();
		
		
		 
					insertTableSQL = "UPDATE Departamentos SET Cantidad_Empleados = Cantidad_Empleados + ? WHERE ID_Departamento = ?";
					preparedStatement = conn.prepareStatement(insertTableSQL);
					preparedStatement.setInt(1, resultado);
					preparedStatement.setInt(2, nuevod);
					preparedStatement .executeUpdate();
			  
			  insertTableSQL = "UPDATE Cargos SET Cantidad_Empleados = Cantidad_Empleados + ? WHERE Departamento = ? AND Cargo_ID = ?";
				preparedStatement = conn.prepareStatement(insertTableSQL);
				preparedStatement.setInt(1, resultado);
				preparedStatement.setInt(2, nuevod);
				preparedStatement.setInt(3, nuevoc);
				preparedStatement .executeUpdate();
	
	
	
	insertTableSQL = "DELETE FROM Cargos where Nombre = ? AND Departamento = ?";
	preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setString(1, viejocargo.toLowerCase());
	preparedStatement.setInt(2, viejod);
	preparedStatement .executeUpdate();
	
	
		
	
		
		
		
	Desconectar();
}

public void EliminarDepartamento(String departamento, String ndepartamento, String ncargo) throws SQLException{
	Conectar("Hotel");

	int resultado = 0;
	
	try{
		String insertTableSQL = "UPDATE Usuarios SET Departamento = ?, Cargo = ? WHERE Departamento = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
		preparedStatement.setInt(1, TransformarDepartamento(ndepartamento.toLowerCase()));
		preparedStatement.setInt(2, TransformarCargo(ndepartamento.toLowerCase(), ncargo.toLowerCase()));
		preparedStatement.setInt(3, TransformarDepartamento(departamento.toLowerCase()));
		preparedStatement .executeUpdate();
		
		Conectar("Hotel");
	
		
		  String selectSQL = "SELECT Cantidad_Empleados FROM Departamentos WHERE Nombre = ?";
		  preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, departamento.toLowerCase());
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	resultado = rs.getInt("Cantidad_Empleados");
		  }
		  
		  insertTableSQL = "UPDATE Departamentos SET Cantidad_Empleados = Cantidad_Empleados + ? WHERE Nombre = ?";
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, resultado);
			preparedStatement.setString(2, ndepartamento.toLowerCase());
			preparedStatement .executeUpdate();
			
			 insertTableSQL = "UPDATE Cargos SET Cantidad_Empleados = Cantidad_Empleados + ? WHERE Departamento = ? AND Nombre = ?";
				preparedStatement = conn.prepareStatement(insertTableSQL);
				preparedStatement.setInt(1, resultado);
				preparedStatement.setInt(2, TransformarDepartamento(ndepartamento));
				preparedStatement.setString(3, ncargo.toLowerCase());
				preparedStatement .executeUpdate();
				
				Conectar("Hotel");
		
		insertTableSQL = "DELETE FROM Departamentos where Nombre = ?";
		preparedStatement = conn.prepareStatement(insertTableSQL);
		preparedStatement.setString(1, departamento.toLowerCase());
		preparedStatement .executeUpdate();
		
		
	}
	catch(SQLException x){
		
		x.printStackTrace();
		
	}
	finally{
		Desconectar();
	}
	
	
	
		
	
		
		
	
	Desconectar();
}

public void EliminarCuarto(String cuarto) throws SQLException{
	Conectar("Hotel");
	int resultado = 0;
	
	
	
	
	String insertTableSQL = "DELETE FROM Modelos where Nombre = ?";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setString(1, cuarto.toLowerCase());
	preparedStatement .executeUpdate();
	
	
		
	
		
		
		
	Desconectar();
}

// METODOS DE MATERIALES // 

public void IngresarReserva(int habitacion, String cliente, LocalDateTime entrada, LocalDateTime salida, double costo) throws SQLException{
	int clienteid = 0;
	Conectar("Hotel");
	 String sqlCreate = "CREATE TABLE IF NOT EXISTS Reservas" 
 		     + "  (ID INTEGER NOT NULL AUTO_INCREMENT,"
 		     + "   Habitacion            INTEGER NOT NULL,"
 		     + "   Cliente            INTEGER NOT NULL,"
 		     + "   Inicio            DATE NOT NULL,"
 		     + "   Fin            DATE NOT NULL,"
 		     + "   Costo            DOUBLE NOT NULL,"
 		     + " PRIMARY KEY ( ID ),"
 		     + "CONSTRAINT check_dates CHECK (Inicio < Fin),"
 		    + "FOREIGN KEY (Habitacion) REFERENCES Habitaciones(ID) ON UPDATE CASCADE,"
		    + "FOREIGN KEY (Cliente) REFERENCES Clientes(ID_Cliente) ON UPDATE CASCADE)";

 			 

	 Statement stmt = conn.createStatement();
	 stmt.execute(sqlCreate);
	
	
	 
	 Conectar("Hotel");
	 
	  String selectSQL = "SELECT ID_Cliente FROM Clientes WHERE Nombre = ?";
	  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
	  preparedStatement.setString(1, cliente.toLowerCase());
	  ResultSet rs = preparedStatement.executeQuery();
	  while (rs.next()) {
	  	clienteid = rs.getInt("ID_Cliente");
	  }
	 
	 
	 String insertTableSQL = "INSERT INTO Reservas"
				+ "(Habitacion, Cliente, Inicio, Fin, Costo) VALUES"
				+ "(?, ?, ?, ?, ?)";
	preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setInt(1, habitacion);
	preparedStatement.setInt(2, clienteid);
	preparedStatement.setObject(3, entrada);
	preparedStatement.setObject(4, salida);
	preparedStatement.setDouble(5, costo);
	preparedStatement .executeUpdate();
	Desconectar();
	
}


public void ActualizarReserva(int habitacion, String cliente, LocalDateTime entrada, LocalDateTime salida, double costo) throws SQLException{
	int clienteid = 0;
	Conectar("Hotel");
	
	
	 
	  String selectSQL = "SELECT ID_Cliente FROM Clientes WHERE Nombre = ?";
	  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
	  preparedStatement.setString(1, cliente.toLowerCase());
	  ResultSet rs = preparedStatement.executeQuery();
	  while (rs.next()) {
	  	clienteid = rs.getInt("ID_Cliente");
	  }
	 
	 
	 String insertTableSQL = "UPDATE Reservas SET Habitacion = ?, Cliente = ?, Inicio = ?, Fin = ?, Costo = ? WHERE ID = ?";
			
	preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setInt(1, habitacion);
	preparedStatement.setInt(2, clienteid);
	preparedStatement.setObject(3, entrada);
	preparedStatement.setObject(4, salida);
	preparedStatement.setDouble(5, costo);
	preparedStatement.setInt(6, BuscarReservaUnica(cliente.toLowerCase(), habitacion, entrada.toLocalDate()));

	preparedStatement .executeUpdate();
	Desconectar();
	
}

public void InsertarDepartamento(String nombre) throws SQLException{
	String mensaje = "";
	Conectar("Hotel");
	 String sqlCreate = "CREATE TABLE IF NOT EXISTS Departamentos" 
 		     + "  (ID_Departamento INTEGER NOT NULL AUTO_INCREMENT,"
 		     + "   Nombre            VARCHAR(128) NOT NULL UNIQUE,"
 		    + "   Cantidad_Empleados            INTEGER NOT NULL DEFAULT 0,"
 		     + " PRIMARY KEY ( ID_Departamento ))";

 			 

	 Statement stmt = conn.createStatement();
	 stmt.execute(sqlCreate);
	
	
	 
	 Conectar("Hotel");
	 
	 
	 String insertTableSQL = "INSERT INTO Departamentos"
				+ "(Nombre) VALUES"
				+ "(?)";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setString(1, nombre.toLowerCase());
	preparedStatement .executeUpdate();
	Desconectar();
	
}

public void CrearHabitaciones(int cantidad) throws SQLException{
	
	Conectar("Hotel");
	conn.setAutoCommit(false);
	
	String sqlDrop = "DROP TABLE IF EXISTS Habitaciones";
	Statement stmt = conn.createStatement();
	 stmt.execute(sqlDrop);
	
	 String sqlCreate = "CREATE TABLE IF NOT EXISTS Habitaciones" 
 		     + "  (ID INTEGER NOT NULL AUTO_INCREMENT,"
 		     + "   Modelo            INTEGER NULL,"
 		     + " PRIMARY KEY ( ID ),"
 		     + "FOREIGN KEY (Modelo) REFERENCES Modelos(ID_Modelo) ON UPDATE CASCADE)";

 			 

	 stmt = conn.createStatement();
	 stmt.execute(sqlCreate);
	 
	 Statement st = conn.createStatement();
	 
	 String sql = "";
	 for(int i = 0; i < cantidad; i++){
		 sql = "INSERT INTO Habitaciones " +
	             "VALUES ()";
		 st.addBatch(sql);
		 
	 }
	
	 st.executeBatch();
	 
	conn.commit();
	Desconectar();
	
}

public void InsertarCargo(String nombre, String departamento, double salario) throws SQLException{
	String mensaje = "";
	int depid = 0;
	Conectar("Hotel");
	 String sqlCreate = "CREATE TABLE IF NOT EXISTS Cargos" 
 		     + "  (Cargo_ID INTEGER NOT NULL AUTO_INCREMENT,"
	   		    + "   Departamento            INTEGER NOT NULL,"
	   		     + "   Nombre            VARCHAR(128) NOT NULL,"
	   		  + "   Salario            DOUBLE NOT NULL,"
	   		    + "   Cantidad_Empleados            INTEGER NOT NULL DEFAULT 0,"
	   		     + " PRIMARY KEY ( Cargo_ID ),"
	   		     + "FOREIGN KEY (departamento) REFERENCES departamentos(ID_Departamento) ON DELETE CASCADE ON UPDATE CASCADE)";

 			 

	 Statement stmt = conn.createStatement();
	 stmt.execute(sqlCreate);
	 DecimalFormat df = new DecimalFormat("#.##");
	
	 
	 Conectar("Hotel");
	 
	 String selectSQL = "SELECT ID_Departamento FROM Departamentos WHERE Nombre = ?";
	  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
	  preparedStatement.setString(1, departamento.toLowerCase());
	
	  ResultSet rs = preparedStatement.executeQuery();
	  while (rs.next()) {
	  	depid = rs.getInt("ID_Departamento");
	  }
	 
	 
	 String insertTableSQL = "INSERT INTO Cargos"
				+ "(Departamento, Nombre, Salario) VALUES"
				+ "(?, ?, ?)";
	preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setInt(1, depid);
	preparedStatement.setString(2, nombre.toLowerCase());
	preparedStatement.setDouble(3, Double.parseDouble(df.format(salario).replaceAll(",", ".")));
	preparedStatement .executeUpdate();
	Desconectar();
	
}

public void InsertarCuarto(String nombre, double precio) throws SQLException{
	String mensaje = "";
	double iva = precio * PantallaAcceso.IVA;
	double sub = precio - iva;
	DecimalFormat df = new DecimalFormat("#.##");
		
	Conectar("Hotel");
	 String sqlCreate = "CREATE TABLE IF NOT EXISTS Modelos" 
 		     + "  (ID_Modelo INTEGER NOT NULL AUTO_INCREMENT,"
 		     + "   Nombre            VARCHAR(128) NOT NULL UNIQUE,"
 		    + "   SubTotal            DOUBLE NOT NULL DEFAULT 0,"
 		   + "   IVA            DOUBLE NOT NULL DEFAULT 0,"
 		    + "   Precio            DOUBLE NOT NULL DEFAULT 0,"
 		     + " PRIMARY KEY ( ID_Modelo ))";

 			 

	 Statement stmt = conn.createStatement();
	 stmt.execute(sqlCreate);
	
	
	 
	 Conectar("Hotel");
	 
	 
	 String insertTableSQL = "INSERT INTO Modelos"
				+ "(Nombre, SubTotal, IVA, Precio) VALUES"
				+ "(?, ?, ?, ?)";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setString(1, nombre.toLowerCase());
	preparedStatement.setDouble(2, Double.parseDouble(df.format(sub).replaceAll(",", ".")));
	preparedStatement.setDouble(3, Double.parseDouble(df.format(iva).replaceAll(",", ".")));
	preparedStatement.setDouble(4, precio);
	preparedStatement .executeUpdate();
	Desconectar();
	
}
public void InsertarCliente(String nombres, int cedula, String telefono, String direccion, String correo, String rif) throws SQLException{
	String mensaje = "";
	
		
	Conectar("Hotel");
	 String sqlCreate = "CREATE TABLE IF NOT EXISTS Clientes" 
 		     + "  (ID_Cliente INTEGER NOT NULL AUTO_INCREMENT,"
 		     + "   Nombre          VARCHAR(128) NOT NULL,"
 		   + "   Cedula            INT NULL UNIQUE,"
 		   + "   Telefono            VARCHAR(65) NULL,"
 		    + "   Direccion            VARCHAR(128) NULL,"
 		   + "   Correo            VARCHAR(72) NULL,"
 		  + "   RIF            VARCHAR(65) NULL,"
 		     + " PRIMARY KEY ( ID_Cliente ))";

 			 

	 Statement stmt = conn.createStatement();
	 stmt.execute(sqlCreate);
	
	
	 
	 Conectar("Hotel");
	 
	 
	 String insertTableSQL = "INSERT INTO Clientes"
				+ "(Nombre, Cedula, Telefono, Direccion, Correo, RIF) VALUES"
				+ "(?, ?, ?, ?, ?, ?)";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setString(1, nombres.toLowerCase());
	preparedStatement.setInt(2, cedula);
	preparedStatement.setString(3, telefono.toLowerCase());
	preparedStatement.setString(4, direccion.toLowerCase());
	preparedStatement.setString(5, correo.toLowerCase());
	preparedStatement.setString(6, rif.toUpperCase());
	preparedStatement .executeUpdate();
	Desconectar();
	
}

public boolean BuscarCliente(String nombre) throws SQLException{
	String resultado = "";
	boolean encontrado = false;
	Conectar("Hotel");
	try{
		  String selectSQL = "SELECT Nombre FROM Clientes WHERE Nombre = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, nombre.toLowerCase());
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	resultado = rs.getString("Nombre");
		  }
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	Desconectar();
	if(resultado == "") encontrado = false;
	if(resultado != "") encontrado = true;
    return encontrado;
}

public boolean BuscarDepartamento(String nombre) throws SQLException{
	String resultado = "";
	boolean encontrado = false;
	Conectar("Hotel");
	try{
		  String selectSQL = "SELECT Nombre FROM Departamentos WHERE Nombre = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, nombre.toLowerCase());
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	resultado = rs.getString("Nombre");
		  }
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	Desconectar();
	if(resultado == "") encontrado = false;
	if(resultado != "") encontrado = true;
    return encontrado;
}



public int TransformarDepartamento(String departamento){
	int depid = 0;
	Conectar("Hotel");
	
	try{
		
		
		 String selectSQL = "SELECT ID_Departamento FROM Departamentos WHERE Nombre = ?";
		 PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, departamento.toLowerCase());
		
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	depid = rs.getInt("ID_Departamento");
		  }
		
		 
		  
		 
		  
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	
	Desconectar();
	return depid;
}

public int TransformarCargo(String departamento, String cargo){
	int depid = 0, cargoid = 0;
	Conectar("Hotel");
	
	try{
		
		 String selectSQL = "SELECT Departamentos.ID_Departamento FROM Departamentos INNER JOIN Cargos ON Departamentos.ID_Departamento = Cargos.Departamento WHERE Departamentos.Nombre = ?";
		 PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, departamento.toLowerCase());
		 
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	depid = rs.getInt("Departamentos.ID_Departamento");
		  }
		
		
		 selectSQL = "SELECT Cargo_ID FROM Cargos WHERE Departamento = ? AND Nombre = ?";
		 preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setInt(1, depid);
		  preparedStatement.setString(2, cargo.toLowerCase());
		  rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	cargoid = rs.getInt("Cargo_ID");
		  }
		
		 
		  
		 
		  
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	
	Desconectar();
	return cargoid;
}

public String DestransformarDepartamento(int departamento){
	String dep = "";
	Conectar("Hotel");
	
	try{
		
		
		 String selectSQL = "SELECT Nombre FROM Departamentos WHERE ID_Departamento = ?";
		 PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setInt(1, departamento);
		
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	dep = rs.getString("Nombre");
		  }
		
		 
		  
		 
		  
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	
	Desconectar();
	return StringUtils.capitalize(dep);
}

public String DestransformarCargo(int departamento, int cargo){
	
	Conectar("Hotel");
	
	String result = "";
	try{
		
		 String selectSQL = "SELECT Cargos.Nombre FROM Cargos INNER JOIN Departamentos ON Cargos.Departamento = Departamentos.ID_Departamento WHERE Cargos.Cargo_ID = ? AND Cargos.Departamento = ?";
		 PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		 preparedStatement.setInt(1, cargo);
		  preparedStatement.setInt(2, departamento);
		 
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	result = rs.getString("Cargos.Nombre");
		  }
		
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	
	Desconectar();
	return StringUtils.capitalize(result);
}


public boolean BuscarCargo(String departamento, String nombre) throws SQLException{
	
	
	String nombres = "";
	int depid = 0;
	boolean encontrado = false;
	Conectar("Hotel");
	try{
		
		
		 String selectSQL = "SELECT ID_Departamento FROM Departamentos WHERE Nombre = ?";
		 PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, departamento.toLowerCase());
		
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	depid = rs.getInt("ID_Departamento");
		  }
		
		  selectSQL = "SELECT Nombre FROM Cargos WHERE Departamento = ? AND Nombre = ?";
		  preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setInt(1, depid);
		  preparedStatement.setString(2, nombre.toLowerCase());
		  rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	nombres = rs.getString("Nombre");
		  }
		  
		 
		  
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	Desconectar();
	if(nombres == "") encontrado = false;
	if(nombres != "") encontrado = true;
		
	
    return encontrado;
}

public void CargarCuartos(JTable tabla) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	DecimalFormat df = new DecimalFormat("#.##");
	DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
	int rowCount = dm.getRowCount();
	//Remove rows one by one from the end of the table
	for (int i = rowCount - 1; i >= 0; i--) {
	    dm.removeRow(i);
	}

    String sql = "SELECT ID_Modelo, Nombre, SubTotal, IVA, Precio FROM Modelos";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
       int id = rs.getInt("ID_Modelo");
       String nombre = rs.getString("Nombre");
       double sub = rs.getDouble("SubTotal");
       double iva = rs.getDouble("IVA");
       double precio = rs.getDouble("Precio");

       DefaultTableModel model = (DefaultTableModel) tabla.getModel();
		model.addRow(new Object[]{id, StringUtils.capitalize(nombre), df.format(sub).replaceAll(",", "."), df.format(iva).replaceAll(",", "."), df.format(precio).replaceAll(",", ".") + " BsS"});
      
    }
    rs.close();
    Desconectar();
}

public void CargarReservas(JTable tabla, LocalDate Fecha) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	ArrayList<Integer> ids = new ArrayList<Integer>();
	ArrayList<LocalDate> entradas = new ArrayList<LocalDate>();
	ArrayList<LocalDate> salidas = new ArrayList<LocalDate>();
	DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
	int rowCount = dm.getRowCount();
	//Remove rows one by one from the end of the table
	for (int i = rowCount - 1; i >= 0; i--) {
	    dm.removeRow(i);
	}
	DefaultTableModel model = (DefaultTableModel) tabla.getModel();
	LocalDate fecha = null;
	String sql = "SELECT ID FROM habitaciones Order by ID Asc";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       ids.add(rs.getInt("ID"));
     }
    
    sql = "SELECT Inicio, Fin FROM reservas Order by ID Asc";
    rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       entradas.add(rs.getObject("Inicio", LocalDate.class));
       salidas.add(rs.getObject("Fin", LocalDate.class));
     }
    
    int hab = 0;
    
   for(int i = 0; i < ids.size(); i++){
	   hab = 0;
	   String selectSQL = "SELECT Habitacion FROM Reservas WHERE Habitacion = ? AND ? between Inicio AND Fin";
	   PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		preparedStatement.setInt(1, ids.get(i));
		preparedStatement.setObject(2, Fecha);
		  ResultSet rs2 = preparedStatement.executeQuery();
	   
	  //STEP 5: Extract data from result set
	  while(rs2.next()){
		 
	     hab = rs2.getInt("Habitacion");
			
	     
	  }
	  
	  if(hab == 0) model.addRow(new Object[]{ids.get(i), "Disponible"});
	  if(hab != 0) model.addRow(new Object[]{ids.get(i), "Ocupada"});
	  
	  
	  
   }
    

    rs.close();
    Desconectar();
}

public void CargarReservasFull(JTable tabla, LocalDate Fecha) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	ArrayList<Integer> ids = new ArrayList<Integer>();
	ArrayList<LocalDate> entradas = new ArrayList<LocalDate>();
	ArrayList<LocalDate> salidas = new ArrayList<LocalDate>();
	DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
	int rowCount = dm.getRowCount();
	//Remove rows one by one from the end of the table
	for (int i = rowCount - 1; i >= 0; i--) {
	    dm.removeRow(i);
	}
	DefaultTableModel model = (DefaultTableModel) tabla.getModel();
	LocalDate fecha = null;
	String sql = "SELECT ID FROM habitaciones Order by ID Asc";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       ids.add(rs.getInt("ID"));
     }
    
    sql = "SELECT Inicio, Fin FROM reservas Order by ID Asc";
    rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       entradas.add(rs.getObject("Inicio", LocalDate.class));
       salidas.add(rs.getObject("Fin", LocalDate.class));
     }
    
    int hab = 0, clienteid = 0;
    double total = 0.0;
    String nombre = "";
    long d = 0;
    DecimalFormat df = new DecimalFormat("#,###.##");
   for(int i = 0; i < ids.size(); i++){
	  hab = 0;
	   String selectSQL = "SELECT Cliente, Habitacion, Inicio, Fin, Costo FROM Reservas WHERE ? BETWEEN Inicio AND Fin AND Habitacion = ?";
	   PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		preparedStatement.setObject(1, Fecha);
		preparedStatement.setInt(2, ids.get(i));
		  ResultSet rs2 = preparedStatement.executeQuery();
	   
	  //STEP 5: Extract data from result set
	  while(rs2.next()){
		 total = rs2.getDouble("Costo");
	     hab = rs2.getInt("Habitacion");
	     clienteid = rs2.getInt("Cliente");
	     LocalDate inicio = rs2.getObject("Inicio", LocalDate.class);
	     LocalDate fin = rs2.getObject("Fin", LocalDate.class);
		 d = ChronoUnit.DAYS.between(inicio, fin);	
	     
	  }
	  
	  selectSQL = "SELECT Clientes.Nombre FROM Clientes INNER JOIN Reservas ON Reservas.Cliente = Clientes.ID_Cliente WHERE Reservas.Cliente = ?";
	   preparedStatement = conn.prepareStatement(selectSQL);
		preparedStatement.setInt(1, clienteid);
	
		  rs2 = preparedStatement.executeQuery();
	   
	  //STEP 5: Extract data from result set
	  while(rs2.next()){
		nombre = rs2.getString("Clientes.Nombre");
	     
	  }
	  
	  if(hab == 0) model.addRow(new Object[]{ids.get(i), "Disponible"});
	  if(hab != 0) model.addRow(new Object[]{ids.get(i), "Ocupada", WordUtils.capitalizeFully(nombre), d + " dia(s)", df.format(total) + " BsS"});
	  
	  
	  
   }
    

    rs.close();
    Desconectar();
}


public void CargarSalidas(JTable tabla) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
	int rowCount = dm.getRowCount();
	//Remove rows one by one from the end of the table
	for (int i = rowCount - 1; i >= 0; i--) {
	    dm.removeRow(i);
	}
	ArrayList<Integer> idclientes = new ArrayList<Integer>();
	ArrayList<Integer> habitaciones = new ArrayList<Integer>();
	ArrayList<String> clientes = new ArrayList<String>();
	
	
	
	
	String sql = "SELECT Cliente, Habitacion From Reservas WHERE FIN = ? ORDER BY ID Asc";
	 PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setObject(1, LocalDate.now());
	
		 ResultSet rs = preparedStatement.executeQuery();
    //STEP 5: Extract data from result set
    while(rs.next()){
       idclientes.add(rs.getInt("Cliente"));
       habitaciones.add(rs.getInt("Habitacion"));
     }
    
  
    
    for(int i = 0; i < idclientes.size(); i++){
    	
    	
    	  sql = "SELECT Nombre FROM Clientes WHERE ID_Cliente = ?";
    		 preparedStatement = conn.prepareStatement(sql);
    			preparedStatement.setInt(1, idclientes.get(i));
    		
    			rs = preparedStatement.executeQuery();
    	   //STEP 5: Extract data from result set
    	   while(rs.next()){
    	     clientes.add(rs.getString("Nombre"));
    	    }
    	
    	
    }
    
    for(int i = 0; i < clientes.size(); i++){
    	DefaultTableModel model = (DefaultTableModel) tabla.getModel();
    	model.addRow(new Object[]{habitaciones.get(i), WordUtils.capitalizeFully(clientes.get(i))});
    }
    
  
    
    
   
    rs.close();
    Desconectar();
	  
	  
   }
    





public void CargarDepartamentos(JTable tabla) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	
	DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
	int rowCount = dm.getRowCount();
	//Remove rows one by one from the end of the table
	for (int i = rowCount - 1; i >= 0; i--) {
	    dm.removeRow(i);
	}

    String sql = "SELECT ID_Departamento, Nombre, Cantidad_Empleados FROM Departamentos";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
       int id = rs.getInt("ID_Departamento");
       String nombre = rs.getString("Nombre");
       int cantidad = rs.getInt("Cantidad_Empleados");
       

       DefaultTableModel model = (DefaultTableModel) tabla.getModel();
		model.addRow(new Object[]{tabla.getRowCount() + 1, nombre.substring(0, 1).toUpperCase() + nombre.substring(1), cantidad + " empleados"});
      
    }
    rs.close();
    Desconectar();
}

public String TransformarModelo(int modelo){
	String resultado = "";
	Conectar("Hotel");
	
	  try {
		  String selectSQL = "SELECT Nombre FROM Modelos WHERE ID_Modelo = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		preparedStatement.setInt(1, modelo);
		  ResultSet rs = preparedStatement.executeQuery();

		  while (rs.next()) {
		  	resultado = rs.getString("Nombre");
		  
		  }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	Desconectar();
	return StringUtils.capitalize(resultado);
}

public void CargarHabitaciones(JTable tabla) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	
	DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
	int rowCount = dm.getRowCount();
	//Remove rows one by one from the end of the table
	for (int i = rowCount - 1; i >= 0; i--) {
	    dm.removeRow(i);
	}

    String sql = "SELECT ID, Modelo FROM Habitaciones Order by ID Asc";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
       int id = rs.getInt("ID");
       String nombre = TransformarModelo(rs.getInt("Modelo"));
       
       

       DefaultTableModel model = (DefaultTableModel) tabla.getModel();
		model.addRow(new Object[]{id, nombre});
      
    }
    rs.close();
    Desconectar();
}

public void CargarClientes(JTable tabla) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	
	DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
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

       DefaultTableModel model = (DefaultTableModel) tabla.getModel();
		model.addRow(new Object[]{tabla.getRowCount() + 1, WordUtils.capitalizeFully(nombres), "V-" + cedula, telefono, WordUtils.capitalizeFully(direccion), correo, rif.toUpperCase()});
      
    }
    rs.close();
    Desconectar();
}

public void CargarMiniClientes(JTable tabla) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	
	DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
	int rowCount = dm.getRowCount();
	//Remove rows one by one from the end of the table
	for (int i = rowCount - 1; i >= 0; i--) {
	    dm.removeRow(i);
	}

    String sql = "SELECT Nombre, Telefono, Correo FROM Clientes Order By Nombre Asc";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
       
       String nombres = rs.getString("Nombre");
       String telefono = rs.getString("Telefono");
       String correo = rs.getString("correo");

       DefaultTableModel model = (DefaultTableModel) tabla.getModel();
		model.addRow(new Object[]{WordUtils.capitalizeFully(nombres), telefono, correo});
      
    }
    rs.close();
    Desconectar();
}

public void CargarCuartosDesc(JTable tabla) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	DecimalFormat df = new DecimalFormat("#.##");
	DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
	int rowCount = dm.getRowCount();
	//Remove rows one by one from the end of the table
	for (int i = rowCount - 1; i >= 0; i--) {
	    dm.removeRow(i);
	}

    String sql = "SELECT ID_Modelo, Nombre, SubTotal, IVA, Precio FROM Modelos ORDER By Precio DESC";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
       int id = rs.getInt("ID_Modelo");
       String nombre = rs.getString("Nombre");
       double sub = rs.getDouble("SubTotal");
       double iva = rs.getDouble("IVA");
       double precio = rs.getDouble("Precio");

       DefaultTableModel model = (DefaultTableModel) tabla.getModel();
		model.addRow(new Object[]{id, StringUtils.capitalize(nombre), df.format(sub).replaceAll(",", "."), df.format(iva).replaceAll(",", "."), df.format(precio).replaceAll(",", ".") + " BsS"});
      
    }
    rs.close();
    Desconectar();
}



public void CargarCargos(JTable tabla) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	ArrayList<Integer> lista = new ArrayList<Integer>();
	DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
	int rowCount = dm.getRowCount();
	//Remove rows one by one from the end of the table
	for (int i = rowCount - 1; i >= 0; i--) {
	    dm.removeRow(i);
	}
    ArrayList<Double> salarios = new ArrayList<Double>();
	String sql = "SELECT Departamento, Salario FROM Cargos Order by Cargo_ID";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       lista.add(rs.getInt("Departamento"));
       salarios.add(rs.getDouble("Salario"));
    }
    
   

    
    ArrayList<String> nombres = new ArrayList<String>();

    	String selectSQL = "";
    	String resultado = "";
    	PreparedStatement preparedStatement = null;
    	
    	for(int i = 0; i < lista.size(); i++){
    		selectSQL = "SELECT Departamentos.Nombre FROM Departamentos INNER JOIN Cargos ON Departamentos.ID_Departamento = Cargos.Departamento "
            		+ "WHERE Departamentos.ID_Departamento = ?";
        	  preparedStatement = conn.prepareStatement(selectSQL);
        	  preparedStatement.setInt(1, lista.get(i));
        	  rs = preparedStatement.executeQuery();
    	
        	  while (rs.next()) {
          	  	resultado = rs.getString("Departamentos.Nombre");
          	  
          	  }
        	  nombres.add(resultado);
    	}
    	
    
    	
    
    
    
    
    int contador = 0;
    

    sql = "SELECT Cargo_ID, Nombre, Salario, Cantidad_Empleados FROM Cargos";
    rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    DecimalFormat df = new DecimalFormat("#.##");
    while(rs.next()){
       //Retrieve by column name
       int ID = rs.getInt("Cargo_ID");
       String cargo = rs.getString("Nombre");
       int cantidad = rs.getInt("Cantidad_Empleados");
       
      
       	DefaultTableModel model = (DefaultTableModel) tabla.getModel();
       	model.addRow(new Object[]{ID, StringUtils.capitalize(nombres.get(contador)), StringUtils.capitalize(cargo), df.format(salarios.get(contador)) + " BsS", cantidad + " empleados"});
       	contador++;
        
    }
   
    rs.close();
    Desconectar();
}


public void CargarCargos(String departamento, JTable tabla) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	int transformacion = 0;
	ArrayList<Integer> lista = new ArrayList<Integer>();
	ArrayList<Double> salarios = new ArrayList<Double>();
	DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
	int rowCount = dm.getRowCount();
	//Remove rows one by one from the end of the table
	for (int i = rowCount - 1; i >= 0; i--) {
	    dm.removeRow(i);
	}
	
	String selectSQL = "SELECT ID_Departamento FROM Departamentos WHERE Nombre = ?";
	  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
	  preparedStatement.setString(1, departamento.toLowerCase());
	  ResultSet rs = preparedStatement.executeQuery();

	  while (rs.next()) {
  	  	transformacion = rs.getInt("ID_Departamento");
  	  
  	  }
	
	
	String sql = "SELECT Departamento, Salario FROM Cargos WHERE Departamento = ? Order by Cargo_ID";
	preparedStatement = conn.prepareStatement(sql);
	  preparedStatement.setInt(1, transformacion);
	  rs = preparedStatement.executeQuery();
    //STEP 5: Extract data from result set
    while(rs.next()){
       lista.add(rs.getInt("Departamento"));
       salarios.add(rs.getDouble("Salario"));
    }
    
   

    
    ArrayList<String> nombres = new ArrayList<String>();
    
    	selectSQL = "";
    	String resultado = "";
    	preparedStatement = null;
    	
    	for(int i = 0; i < lista.size(); i++){
    		selectSQL = "SELECT Departamentos.Nombre FROM Departamentos INNER JOIN Cargos ON Departamentos.ID_Departamento = Cargos.Departamento "
            		+ "WHERE Departamentos.ID_Departamento = ?";
        	  preparedStatement = conn.prepareStatement(selectSQL);
        	  preparedStatement.setInt(1, lista.get(i));
        	  rs = preparedStatement.executeQuery();
    	
        	  while (rs.next()) {
          	  	resultado = rs.getString("Departamentos.Nombre");
          	  
          	  }
        	  nombres.add(resultado);
    	}
    	
    
    	
    
    
    
    
    int contador = 0;
    

    sql = "SELECT Cargo_ID, Nombre, Cantidad_Empleados FROM Cargos WHERE Departamento = ?";
	preparedStatement = conn.prepareStatement(sql);
	  preparedStatement.setInt(1, transformacion);
	  rs = preparedStatement.executeQuery();
    DecimalFormat df = new DecimalFormat("#.##");
   
    //STEP 5: Extract data from result set
    
    while(rs.next()){
       //Retrieve by column name
       int ID = rs.getInt("Cargo_ID");
       String cargo = rs.getString("Nombre");
       int cantidad = rs.getInt("Cantidad_Empleados");
       
      
       	DefaultTableModel model = (DefaultTableModel) tabla.getModel();
    	model.addRow(new Object[]{ID, StringUtils.capitalize(nombres.get(contador)), StringUtils.capitalize(cargo), df.format(salarios.get(contador)) + " BsS", cantidad + " empleados"});
       	contador++;
        
    }
   
    rs.close();
    Desconectar();
}




public void CargarUsuarios(JTable tabla) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	int cargoid = 0, depid = 0;
	DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
	int rowCount = dm.getRowCount();
	//Remove rows one by one from the end of the table
	for (int i = rowCount - 1; i >= 0; i--) {
	    dm.removeRow(i);
	}

    String sql = "SELECT Nombre_Usuario, Nombres, Apellidos, Cedula, Direccion, Telefono, "
    		+ "Correo, Departamento, Cargo, RIF, Turno FROM Usuarios ORDER BY Nombre_Usuario Asc";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
	       //Retrieve by column name
	 
	       String username = rs.getString("Nombre_Usuario");
	       String turno = rs.getString("Turno");
	       depid = rs.getInt("Departamento");
	       cargoid = rs.getInt("Cargo");
	       String names = rs.getString("Nombres");
	       String lastnames = rs.getString("Apellidos");
	       int cedula = rs.getInt("Cedula");
	       String direccion = rs.getString("Direccion");
	       String telefono = rs.getString("Telefono");
	       String correo = rs.getString("Correo");
	       String cargos = "", departamentos = "";
	       String RIF = rs.getString("RIF");
	       
	      
	       
				  DefaultTableModel model = (DefaultTableModel) tabla.getModel();
			       if(cedula != 0) model.addRow(new Object[]{tabla.getRowCount() + 1, StringUtils.capitalize(username),StringUtils.capitalize(turno),
			    		   DestransformarCargo(depid, cargoid), WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "V-" + cedula, 
			   			StringUtils.capitalize(direccion), telefono, correo, RIF.toUpperCase()});
			   	if(cedula == 0) model.addRow(new Object[]{tabla.getRowCount() + 1, StringUtils.capitalize(username),StringUtils.capitalize(turno),
			   			DestransformarCargo(depid, cargoid), WordUtils.capitalizeFully(names), WordUtils.capitalizeFully(lastnames), "", 
			   			StringUtils.capitalize(direccion), telefono, correo, RIF.toUpperCase()});
	       
	      
	      
	    }
	       
  
	       
	       
    rs.close();
    Desconectar();
}

public void ListaHabitaciones(JComboBox lista) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	lista.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
    String sql = "SELECT ID FROM Habitaciones Order by ID Asc";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
      
       int habitacion = rs.getInt("ID");
      

       lista.addItem(habitacion);
      
    }
    rs.close();
    Desconectar();
}

public void ListaReservas(String cliente, JComboBox lista) throws SQLException{
	Conectar("Hotel");
	int id = 0;
	Statement stmt = conn.createStatement();
	
	String sql = "SELECT Clientes.ID_Cliente FROM Clientes INNER JOIN Reservas ON Clientes.ID_Cliente = Reservas.Cliente WHERE Clientes.Nombre = ?";
	PreparedStatement preparedStatement = conn.prepareStatement(sql);
	  preparedStatement.setString(1, cliente.toLowerCase());
	  ResultSet rs = preparedStatement.executeQuery();
    //STEP 5: Extract data from result set
    while(rs.next()){
      id = rs.getInt("Clientes.ID_Cliente");
    }
	
	stmt = conn.createStatement();
	lista.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
	sql = "SELECT Inicio, Habitacion FROM Reservas WHERE Cliente = ? Order by Inicio Asc";
	preparedStatement = conn.prepareStatement(sql);
	  preparedStatement.setInt(1, id);
	  rs = preparedStatement.executeQuery();
    //STEP 5: Extract data from result set
	  DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    while(rs.next()){
    	LocalDate date = rs.getObject("Inicio", LocalDate.class);
        String text = date.format(formatters);
        int hab = rs.getInt("Habitacion");
       lista.addItem(text + " - Habitación N° " + hab);
       
    }
    rs.close();
    Desconectar();
}

public String BuscarCliente(LocalDate dia, int habitacion, String cliente) throws SQLException{
	Conectar("Hotel");
	String nombre = "";
	int unico = 0;
	
	
	
	unico = BuscarReservaUnica(cliente, habitacion, dia);
	


	String sql = "SELECT Clientes.Nombre From Clientes INNER JOIN Reservas ON Reservas.Cliente = Clientes.ID_Cliente WHERE Reservas.Habitacion = ? AND Reservas.Inicio = ? AND Clientes.Nombre = ?";
	Conectar("Hotel");
	PreparedStatement preparedStatement = conn.prepareStatement(sql);
	preparedStatement.setInt(1, habitacion);
	preparedStatement.setObject(2, dia);
	preparedStatement.setString(3, cliente);
	  ResultSet rs = preparedStatement.executeQuery();
    //STEP 5: Extract data from result set
    while(rs.next()){
      nombre = rs.getString("Clientes.Nombre");
    }
    
   
	
	
    rs.close();
    Desconectar();
    return nombre;
}



public int BuscarReservaUnica(String cliente, int habitacion, LocalDate inicio) throws SQLException{
	Conectar("Hotel");
	int id = 0, clienteid = 0;
	
	
	String sql = "SELECT ID_Cliente From Clientes WHERE Nombre = ?";
	PreparedStatement preparedStatement = conn.prepareStatement(sql);
	  preparedStatement.setString(1, cliente.toLowerCase());
	  ResultSet rs = preparedStatement.executeQuery();
    //STEP 5: Extract data from result set
    while(rs.next()){
      clienteid = rs.getInt("ID_Cliente");
    }
   
  
	
	sql = "SELECT ID From Reservas WHERE Cliente = ? AND Habitacion = ? AND Inicio = ?";
	preparedStatement = conn.prepareStatement(sql);
	  preparedStatement.setInt(1, clienteid);
	  preparedStatement.setInt(2, habitacion);
	  preparedStatement.setObject(3, inicio);
	  rs = preparedStatement.executeQuery();
    //STEP 5: Extract data from result set
    while(rs.next()){
      id = rs.getInt("ID");
    }
	
	
    rs.close();
    Desconectar();
    return id;
}


public void ListaDepartamentos(JComboBox lista) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	lista.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
    String sql = "SELECT Nombre FROM Departamentos Order by Nombre";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
      
       String cargo = rs.getString("Nombre");
      

       lista.addItem(StringUtils.capitalize(cargo));
      
    }
    rs.close();
    Desconectar();
}


public void ListaCargos(String departamento, JComboBox lista) throws SQLException{
	Conectar("Hotel");
	int resultado = 0;
	ResultSet rs = null;
	Statement stmt = conn.createStatement();
	lista.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
	try{
		  String selectSQL = "SELECT Departamentos.ID_Departamento FROM Departamentos INNER JOIN Cargos ON Departamentos.ID_Departamento = Cargos.Departamento WHERE Departamentos.Nombre = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, departamento.toLowerCase());
		  rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	resultado = rs.getInt("Departamentos.ID_Departamento");
		  	
		  }
		  selectSQL = "SELECT Nombre FROM Cargos WHERE Departamento = ?";
		  preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setInt(1, resultado);
		  rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	String cargos = rs.getString("Nombre");
		  	lista.addItem(StringUtils.capitalize(cargos));
		  }
		
		  
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
    rs.close();
    Desconectar();
}

public void ListaSalarios(String departamento, String cargo, JTextField campo) throws SQLException{
	Conectar("Hotel");
	int resultado = 0;
	int cid = 0;
	ResultSet rs = null;
	Statement stmt = conn.createStatement();
	
	try{
		
		  String selectSQL = "SELECT Departamentos.ID_Departamento FROM Departamentos INNER JOIN Cargos ON Departamentos.ID_Departamento = Cargos.Departamento WHERE Departamentos.Nombre = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, departamento.toLowerCase());
		  rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	resultado = rs.getInt("Departamentos.ID_Departamento");
		  	
		  }
		  
		
		  
		  
		  selectSQL = "SELECT Cargo_ID FROM Cargos WHERE Nombre = ? AND Departamento = ?";
		  preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setString(1, cargo.toLowerCase());
		  preparedStatement.setInt(2, cid);
		  rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	cid = rs.getInt("Cargo_ID");
		  	
		  }
		  
		  
		  selectSQL = "SELECT Salario FROM Cargos WHERE Departamento = ? AND Nombre = ?";
		  preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setInt(1, resultado);
		  preparedStatement.setString(2, cargo.toLowerCase());

		  rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	double salario = rs.getDouble("Salario");
		  	campo.setText(Double.toString(salario));
		  }
		
		  
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
    rs.close();
    Desconectar();
}

public void ListaClientes(JComboBox lista) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	lista.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
    String sql = "SELECT Nombre FROM Clientes";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
      
       String cargo = rs.getString("Nombre");
      

       lista.addItem(WordUtils.capitalizeFully(cargo));
      
    }
    rs.close();
    Desconectar();
}

public void ListaCuartos(JComboBox lista) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	lista.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
    String sql = "SELECT Nombre FROM Modelos";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
      
       String cargo = rs.getString("Nombre");
      

       lista.addItem(StringUtils.capitalize(cargo));
      
    }
    rs.close();
    Desconectar();
}

public void ListaUsuarios(JComboBox lista) throws SQLException{
	Conectar("Hotel");
	Statement stmt = conn.createStatement();
	lista.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar"}));
    String sql = "SELECT Nombre_Usuario FROM Usuarios";
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
      
       String username = rs.getString("Nombre_Usuario");
      

       lista.addItem(StringUtils.capitalize(username));
      
    }
    rs.close();
    Desconectar();
}

public boolean BuscarReserva(int habitacion, LocalDateTime entrada, LocalDateTime salida) throws SQLException{
	int resultado = 0;
	int result = 0;
	boolean encontrado = false;
	LocalDate inicio = null, fin = null;
	Conectar("Hotel");
	try{
		
		  String selectSQL = "SELECT Habitacion, Inicio, Fin FROM Reservas WHERE ? BETWEEN Inicio AND Fin + 1 AND Habitacion = ? OR Fin >= ? AND Inicio <= ? AND Habitacion = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setObject(1, entrada);
		  preparedStatement.setInt(2,  habitacion);
		  preparedStatement.setObject(3, entrada);
		  preparedStatement.setObject(4, salida);
		  preparedStatement.setInt(5,  habitacion);
		  ResultSet rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	resultado = rs.getInt("Habitacion");
		  	inicio = rs.getObject("Inicio", LocalDate.class);
		  	fin = rs.getObject("Fin", LocalDate.class);
		  }
		  selectSQL = "SELECT Habitacion, Inicio, Fin FROM Reservas WHERE ? BETWEEN Inicio AND Fin + 1 AND Habitacion = ? OR Fin >= ? AND Fin <= ? AND Habitacion = ?";
		  preparedStatement = conn.prepareStatement(selectSQL);
		  preparedStatement.setObject(1, salida);
		  preparedStatement.setInt(2,  habitacion);
		  preparedStatement.setObject(3, entrada);
		  preparedStatement.setObject(4, salida);
		  preparedStatement.setInt(5,  habitacion);
		  rs = preparedStatement.executeQuery();
		  while (rs.next()) {
		  	result = rs.getInt("Habitacion");
		  	inicio = rs.getObject("Inicio", LocalDate.class);
		  	fin = rs.getObject("Fin", LocalDate.class);
		  }
		  
	}
	catch(Exception e){
			  System.err.println(e);
		  }
	Desconectar();
	if(resultado == 0 && result == 0) encontrado = false;
	
	if(resultado != 0 || result != 0) {
		encontrado = true;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
		JOptionPane.showMessageDialog(null, "<html><b>Error: Esta habitación ya se encuentra reservada para esta fecha!<br />"
				+ "Fue reservada desde el " + inicio.format(formatter) + " hasta el " + fin.format(formatter)+ " </b></html>", "Hotel Mi Reina C.A - Error", JOptionPane.WARNING_MESSAGE);
		return encontrado;
	}
	
    return encontrado;
}











public void EliminarCliente(String nombre) throws SQLException{
	Conectar("Hotel");
	
	 
	 String insertTableSQL = "DELETE FROM Clientes where Nombre = ?";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setString(1, nombre.toLowerCase());
	preparedStatement .executeUpdate();
	Desconectar();
}


public void EliminarReserva(int habitacion, LocalDate inicio, LocalDate fin) throws SQLException{
	Conectar("Hotel");
	
	 
	 String insertTableSQL = "DELETE FROM Reservas where Habitacion = ? AND Inicio = ? AND Fin = ?";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setInt(1, habitacion);
	preparedStatement.setObject(2, inicio);
	preparedStatement.setObject(3, fin);
	preparedStatement .executeUpdate();
	Desconectar();
}


	

// CLIENTES

public void RegistrarCliente(String nombre, String RIF, String Telefono) throws SQLException{
	String mensaje = "";
	Conectar("Hotel");
	
	 
	 String insertTableSQL = "INSERT INTO Clientes"
				+ "(Nombre, RIF, Telefono) VALUES"
				+ "(?, ?, ?)";
	PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setString(1, nombre.toLowerCase());
	preparedStatement.setString(2, RIF.toUpperCase());
	preparedStatement.setString(3, Telefono);
	preparedStatement .executeUpdate();
	Desconectar();
	
}



public void ActualizarCargo(String nnombre, String nombre, String departamento, double salario) throws SQLException{
	Conectar("Hotel");
	
		ResultSet rs = null;
		int resultado = 0;
		String name = "";
	  String selectSQL = "SELECT Departamentos.ID_Departamento FROM Departamentos INNER JOIN Cargos ON Departamentos.ID_Departamento = Cargos.Departamento WHERE Departamentos.Nombre = ?";
	  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
	  preparedStatement.setString(1, departamento.toLowerCase());
	  rs = preparedStatement.executeQuery();
	  while (rs.next()) {
	  	resultado = rs.getInt("Departamentos.ID_Departamento");
	  	
	  }
	  
	  selectSQL = "SELECT Nombre FROM Cargos WHERE Departamento = ?";
	  preparedStatement = conn.prepareStatement(selectSQL);
	  preparedStatement.setInt(1, resultado);
	  rs = preparedStatement.executeQuery();
	  while (rs.next()) {
	  	name = rs.getString("Nombre");
	  	
	  }
	  
	
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	String insertTableSQL = "";
	preparedStatement = null;
	
		 insertTableSQL = "UPDATE Cargos SET Nombre = ?, Salario = ? WHERE Nombre = ? AND Departamento = ?";
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, nnombre.toLowerCase());
			preparedStatement.setDouble(2, Double.parseDouble(df.format(salario).replaceAll(",", ".")));
			preparedStatement.setString(3, name.toLowerCase());
			preparedStatement.setInt(4, resultado);
	preparedStatement.executeUpdate();
	
	 
	
	
	Desconectar();
	
	
}
public void ActualizarCargo(String nnombre, String nombre, String departamento, String nuevodepartamento, double salario) throws SQLException{
	Conectar("Hotel");
	
	ResultSet rs = null;
	int resultado = 0, nuevo = 0;
	int vcargo = 0, ncargo = 0;
  String selectSQL = "SELECT Departamentos.ID_Departamento FROM Departamentos INNER JOIN Cargos ON Departamentos.ID_Departamento = Cargos.Departamento WHERE Departamentos.Nombre = ?";
  PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
  preparedStatement.setString(1, departamento.toLowerCase());
  rs = preparedStatement.executeQuery();
  while (rs.next()) {
  	resultado = rs.getInt("Departamentos.ID_Departamento");
  	
  }
  
  
  // conseguir la ID del departamento nuevo
 
  selectSQL = "SELECT ID_Departamento FROM Departamentos WHERE Nombre = ?";
  preparedStatement = conn.prepareStatement(selectSQL);
  preparedStatement.setString(1, nuevodepartamento.toLowerCase());
  rs = preparedStatement.executeQuery();
  while (rs.next()) {
  	nuevo = rs.getInt("ID_Departamento");
  	
  }
  
  selectSQL = "SELECT Cargo_ID FROM Cargos WHERE Departamento = ?";
  preparedStatement = conn.prepareStatement(selectSQL);
  preparedStatement.setInt(1, resultado);
  rs = preparedStatement.executeQuery();
  while (rs.next()) {
  	vcargo = rs.getInt("Cargo_ID");
  	
  }
  
  selectSQL = "SELECT Cargo_ID FROM Cargos WHERE Departamento = ?";
  preparedStatement = conn.prepareStatement(selectSQL);
  preparedStatement.setInt(1, nuevo);
  rs = preparedStatement.executeQuery();
  while (rs.next()) {
  	ncargo = rs.getInt("Cargo_ID");
  	
  }

  DecimalFormat df = new DecimalFormat("#.##");


String insertTableSQL = "";
preparedStatement = null;

	 insertTableSQL = "UPDATE Cargos SET Nombre = ?, Departamento = ?, Salario = ? WHERE Nombre = ? AND Departamento = ?";
		preparedStatement = conn.prepareStatement(insertTableSQL);
		preparedStatement.setString(1, nnombre.toLowerCase());
		preparedStatement.setInt(2, nuevo);
		preparedStatement.setDouble(3, Double.parseDouble(df.format(salario).replaceAll(",", ".")));
		preparedStatement.setString(4, nombre.toLowerCase());
		preparedStatement.setInt(5, resultado);
preparedStatement.executeUpdate();

 insertTableSQL = "UPDATE Usuarios SET Cargo = ?, Departamento = ? WHERE Cargo = ? AND Departamento = ?";
	preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setInt(1, ncargo);
	preparedStatement.setInt(2, nuevo);
	preparedStatement.setInt(3, vcargo);
	preparedStatement.setInt(4, resultado);
preparedStatement.executeUpdate();


Desconectar();
	
	
}

public void ActualizarDepartamento(String nombre, String viejo) throws SQLException{
	Conectar("Hotel");
	
	
	
	String insertTableSQL = "";
	PreparedStatement preparedStatement = null;
	
		 insertTableSQL = "UPDATE Departamentos SET Nombre = ? WHERE Nombre = ?";
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, nombre.toLowerCase());
			preparedStatement.setString(2, viejo.toLowerCase());
	preparedStatement.executeUpdate();
	
	
	
	
	
	Desconectar();
	
	
}

public void ActualizarHabitacion(int habitacion, String modelo) throws SQLException{
	Conectar("Hotel");
	
	int modeloid = 0;
	
	String insertTableSQL = "";
	PreparedStatement preparedStatement = null;
	
	 String selectSQL = "SELECT ID_Modelo FROM Modelos WHERE Nombre = ?";
	  preparedStatement = conn.prepareStatement(selectSQL);
	  preparedStatement.setString(1, modelo.toLowerCase());
	  ResultSet rs = preparedStatement.executeQuery();
	  while (rs.next()) {
	  	modeloid = rs.getInt("ID_Modelo");
	  	
	  }
	
		 insertTableSQL = "UPDATE Habitaciones SET Modelo = ? WHERE ID = ?";
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, modeloid);
			preparedStatement.setInt(2, habitacion);
	preparedStatement.executeUpdate();
	
	
	
	
	
	Desconectar();
	
	
}

public void ActualizarCuarto(String nnombre, String nombre, double precio) throws SQLException{
	Conectar("Hotel");
	
	double iva = precio * PantallaAcceso.IVA;
	double sub = precio - iva;
	DecimalFormat df = new DecimalFormat("#.##");
	
	String insertTableSQL = "";
	PreparedStatement preparedStatement = null;
	
	 insertTableSQL = "UPDATE Modelos SET Nombre = ?, SubTotal = ?, IVA = ?, Precio = ? WHERE Nombre = ?";
	preparedStatement = conn.prepareStatement(insertTableSQL);
	preparedStatement.setString(1, nnombre.toLowerCase());
	preparedStatement.setDouble(2, Double.parseDouble(df.format(sub).replaceAll(",", ".")));
	preparedStatement.setDouble(3, Double.parseDouble(df.format(iva).replaceAll(",", ".")));
	preparedStatement.setDouble(4, precio);
	preparedStatement.setString(5, nombre.toLowerCase());
	preparedStatement .executeUpdate();
	
	
	
	
	
	Desconectar();
	
	
}


}





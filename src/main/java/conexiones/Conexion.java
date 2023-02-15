package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
     Connection con;
     String uri = "jdbc:mysql://localhost:3306/ventapeliculas";
     String user = "root";
     String password = "";
	 public Connection getConexion() {
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection(uri, user, password);
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
		return con;
	 }
}

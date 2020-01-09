package general;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ConnectionToDatabase {

	private Connection connect;
	private Statement sentencia;

	public ConnectionToDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/howards", "root", "");
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return connect;
	}

	public Statement getSentencia() {
		try {
			sentencia = (Statement) connect.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sentencia;
	}

	public void setConexion(Connection conexion) {
		connect = conexion;
	}

	public void setSentencia(Statement sentencia) {
		this.sentencia = sentencia;
	}

}

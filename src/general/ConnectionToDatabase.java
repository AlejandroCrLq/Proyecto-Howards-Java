package general;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ConnectionToDatabase {


		private Statement sentencia;
		private Connection connect;

		public ConnectionToDatabase()
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				connect =(Connection) DriverManager.getConnection("jdbc:mysql://192.168.11.145/howards", "general", "123");
			} catch (ClassNotFoundException cn)
			{
				cn.printStackTrace();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		public Statement getSentencia()
		{
			try
			{
				sentencia = (Statement) connect.createStatement();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			return sentencia;
		}

		public void setSentencia(Statement sentencia)
		{
			this.sentencia = sentencia;
		}

		public Connection getConexion()
		{
			return connect;
		}

		public void setConexion(Connection conexion)
		{
			this.connect = conexion;
		}

	}

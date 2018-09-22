package login.db;

import java.sql.*;

public class Provider {
	public static Connection getConnection()
	{
		Connection con = null;
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:XE","system","root");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}

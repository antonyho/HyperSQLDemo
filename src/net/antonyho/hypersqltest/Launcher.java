package net.antonyho.hypersqltest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Launcher {
	

	public static void main(String[] args) {
		HyperSQLServer server = new HyperSQLServer();
		server.start();
		
		createTables("demodb");
		
		server.stop();
	}
	
	public static void createTables(String dbName) {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver" );
		} catch (Exception e) {
			System.err.println("ERROR: failed to load HSQLDB JDBC driver.");		// TODO logging
			e.printStackTrace();
			return;
		}
		
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/demodb", "SA", "");
			
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS test (a int, b varchar(10))");
			
		} catch (SQLException e) {
			System.err.println("ERROR: failed to connect to HSQLDB");		// TODO logging
			e.printStackTrace();
			return;
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

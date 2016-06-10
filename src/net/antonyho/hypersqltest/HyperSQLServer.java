package net.antonyho.hypersqltest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.Server;
import org.hsqldb.server.ServerAcl.AclFormatException;

public class HyperSQLServer {

	final static int UNABLE_TO_LOAD_DB_PROPERTIES = -1;
	final static int ERROR_LOADING_DB_PROPERTIES = -2;
	
	private Server hsqlServer;
	
	public HyperSQLServer() {}
	
	// TODO add a constructor to accept properties
	
	public Server getServer() {
		return this.hsqlServer;
	}
	
	public void start() {
		HsqlProperties hsqlProperties = new HsqlProperties();
		String propertiesFilename = "hypersql.properties";
		Properties prop = new Properties();
		try {
			InputStream propIS = getClass().getClassLoader().getResourceAsStream(propertiesFilename);
			prop.load(propIS);
			propIS.close();
		} catch (IOException e) {
			// TODO logging
			e.printStackTrace();
			System.exit(UNABLE_TO_LOAD_DB_PROPERTIES);
		}
		hsqlProperties.addProperties(prop);
		
		 this.hsqlServer = new Server();
		try {
			this.hsqlServer.setProperties(hsqlProperties);
		} catch (IOException | AclFormatException e) {
			// TODO logging
			e.printStackTrace();
			System.exit(ERROR_LOADING_DB_PROPERTIES);
		}
		hsqlServer.setLogWriter(null);		// can use custom writer for logging
		hsqlServer.setErrWriter(null);		// can use custom writer for logging
		hsqlServer.start();
	}
	
	public void stop() {
		hsqlServer.shutdownCatalogs(1);
	}
}

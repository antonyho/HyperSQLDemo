package net.antonyho.hypersqltest;

public class Launcher {
	

	public static void main(String[] args) {
		HyperSQLServer server = new HyperSQLServer();
		server.start();
		
		
		
		server.stop();
	}
	
	private boolean checkTableExistence() {
		boolean tableExist = false;
		
		return tableExist;
	}
	
	private void createTables() {
		
	}

}

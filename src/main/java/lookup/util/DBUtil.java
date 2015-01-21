package lookup.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class DBUtil {
	private Connection connection;
	private StringBuffer queryBuffer;
	private ArrayList<String> args;
	
	private static Logger log = Logger.getLogger("DBUtil");
	
	public StringBuffer getQueryBuffer() {
		return queryBuffer;
	}
	
	public ArrayList<String> getArgs() {
		return args;
	}
	
	/*
	 * Constructor to create a new Connection object every time the class is instantiated
	 * */
	public DBUtil() {
		Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user","root");
        connectionProps.put("password", "root");
        
        try {
        		Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/",
				"root",
				"root");
                
        } catch (Exception e) {
                e.printStackTrace();
                        log.info("Exception Occured");
                        log.info("Class Name: States");
                        log.info("Method: getConnection()");
                        log.info("Message: ");
                        log.info(e.getMessage());
        }
        
        this.connection = conn;
        queryBuffer = new StringBuffer();
		args = new ArrayList<String>();
	}
	
	/*
	 * Method to execute a select query and return the results
	 * Returns null if the result set is empty
	 * */
	public ResultSet executeSelect(String sqlStr, List<String> args){
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
					ps = connection.prepareStatement(sqlStr);
					for(int i = 1; i<=args.size();i++){
						ps.setString(i, args.get(i-1));
					}
					rs = ps.executeQuery();
					
					if(rs.next())
						rs.beforeFirst();
					else
						rs = null;
		} catch (SQLException e) {
					log.info("Exception Occured");
		            log.info("Class Name: " +this.getClass().getName());
		            log.info("Method: executeSelect()");
		            log.info("Message: ");
		            log.info(e.getMessage());
		}
		
		return rs;
	}

	/*
	/*
	 * Method to return a connection object
	 * */
	public Connection getConnection(){
		return this.connection;
	}
	
	/*
	 * Method to close the connection
	 * */
	public void closeConnection(){
        try{
                connection.close();
                connection = null;
                queryBuffer = null;
        		args = null;
        }catch(Exception e){
                log.info("Exception Occured");
                log.info("Class Name: " +this.getClass().getName());
                log.info("Method: closeConnection()");
                log.info("Message: ");
                log.info(e.getMessage());
        }
	}
	
}

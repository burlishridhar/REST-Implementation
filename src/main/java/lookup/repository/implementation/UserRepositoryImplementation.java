package lookup.repository.implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import lookup.domain.User;
import lookup.repository.UserRepository;
import lookup.util.DBUtil;
import lookup.util.TransformUtil;

public class UserRepositoryImplementation implements UserRepository {

  private static Logger log = Logger.getLogger("UserRepositoryImplementation");
  private DBUtil dbUtil;
  
  public List<User> getAllUsers() {
    /*** Instantiate DB Util class ***/
    dbUtil = new DBUtil();
    StringBuffer sbQuery = dbUtil.getQueryBuffer();
    ArrayList<String> args = dbUtil.getArgs();
    ResultSet rs = null;
    List<User> listUser = null;
    
    /*** Get the details of a particular User ***/
        /*** QUERY 1 ***/
        sbQuery.setLength(0);
        sbQuery.append(" SELECT              ");
        sbQuery.append("        id,          ");
        sbQuery.append("        name,        ");
        sbQuery.append("        email,       ");
        sbQuery.append("        phone,       ");
        sbQuery.append("        loginID      ");
        sbQuery.append(" FROM lookup.user    ");
      
        
        /*** Prepare parameters for query ***/
        args.clear();
        
        
    /*** Pass the appropriate arguments to DBUtil class ***/
    rs = dbUtil.executeSelect(sbQuery.toString(), args);
    
    /*** Convert the result set into a User object ***/
    if(rs!=null)
      listUser = TransformUtil.convertToListUser(rs);
    
    dbUtil.closeConnection();
    dbUtil = null;
    
    return listUser;
  }

  public List<User> getUserByName(String name) {
    /*** Instantiate DB Util class ***/
    dbUtil = new DBUtil();
    StringBuffer sbQuery = dbUtil.getQueryBuffer();
    ArrayList<String> args = dbUtil.getArgs();
    ResultSet rs = null;
    List<User> listUser = null;
    
    /*** Get the details of a particular User ***/
        /*** QUERY 2 ***/
        sbQuery.setLength(0);
        sbQuery.append(" SELECT                           ");
        sbQuery.append("        id,                       ");
        sbQuery.append("        name,                     ");
        sbQuery.append("        email,                    ");
        sbQuery.append("        phone,                    ");
        sbQuery.append("        loginID                   ");
        sbQuery.append(" FROM lookup.user u               ");
        sbQuery.append(" WHERE UPPER(u.name) = UPPER(?)   ");
        
        /*** Prepare parameters for query ***/
        args.clear();
        args.add(name);
        
        
    /*** Pass the appropriate arguments to DBUtil class ***/
    rs = dbUtil.executeSelect(sbQuery.toString(), args);
    
    /*** Convert the result set into a User object ***/
    if(rs!=null)
      listUser = TransformUtil.convertToListUser(rs);
    
    dbUtil.closeConnection();
    dbUtil = null;
    
    return listUser;
  }

  public List<User> getUserByLoginID(String loginID) {
    /*** Instantiate DB Util class ***/
    dbUtil = new DBUtil();
    StringBuffer sbQuery = dbUtil.getQueryBuffer();
    ArrayList<String> args = dbUtil.getArgs();
    ResultSet rs = null;
    List<User> listUser = null;
    
    /*** Get the details of a particular User ***/
        /*** QUERY 3 ***/
        sbQuery.setLength(0);
        sbQuery.append(" SELECT                           ");
        sbQuery.append("        id,                       ");
        sbQuery.append("        name,                     ");
        sbQuery.append("        email,                    ");
        sbQuery.append("        phone,                    ");
        sbQuery.append("        loginID                   ");
        sbQuery.append(" FROM lookup.user u               ");
        sbQuery.append(" WHERE UPPER(u.loginID) = UPPER(?)");
      
        
        /*** Prepare parameters for query ***/
        args.clear();
        args.add(loginID);
        
        
    /*** Pass the appropriate arguments to DBUtil class ***/
    rs = dbUtil.executeSelect(sbQuery.toString(), args);
    
    /*** Convert the result set into a User object ***/
    if(rs!=null)
      listUser = TransformUtil.convertToListUser(rs);
    
    dbUtil.closeConnection();
    dbUtil = null;
    
    return listUser;
  }

  public User getUserById(int id) {
    /*** Instantiate DB Util class ***/
    dbUtil = new DBUtil();
    StringBuffer sbQuery = dbUtil.getQueryBuffer();
    ArrayList<String> args = dbUtil.getArgs();
    ResultSet rs = null;
    User found = null;
    
    /*** Get the details of a particular User ***/
        /*** QUERY 4 ***/
        sbQuery.setLength(0);
        sbQuery.append(" SELECT                           ");
        sbQuery.append("        id,                       ");
        sbQuery.append("        name,                     ");
        sbQuery.append("        email,                    ");
        sbQuery.append("        phone,                    ");
        sbQuery.append("        loginID                   ");
        sbQuery.append(" FROM lookup.user u               ");
        sbQuery.append(" WHERE u.id = ?					  ");
        
    /*** Get Connection and Execute  ***/
    try{
      
      PreparedStatement ps = dbUtil.getConnection().prepareStatement(sbQuery.toString());
      
      /*** Add query parameters ***/
      ps.setInt(1, id);
      
      rs = ps.executeQuery();
        
    }catch(Exception e){
        log.info("Exception Occured");
              log.info("Class Name: " +this.getClass().getName());
              log.info("Method: getUserById()");
              log.info(e.getMessage());
    }
    
    /*** Convert the result set into a User object ***/
    if(rs!=null)
      found = TransformUtil.convertToUser(rs);
    
    dbUtil.closeConnection();
    dbUtil = null;
    
    return found;
  }

  public int saveUser(User user, String password) {
      /*** Instantiate DB Util class ***/
      dbUtil = new DBUtil();
      StringBuffer sbQuery = dbUtil.getQueryBuffer();
      int result = -1;
      
      /*** Get the details of a particular User ***/
          /*** QUERY 5 ***/
          sbQuery.setLength(0);
          sbQuery.append( " INSERT INTO lookup.user(name, email, phone, loginID, passwd) " );
          sbQuery.append( " VALUES                                                       " );
          sbQuery.append( " (?,?,?,?,?)                                                  " );

      /*** Get Connection and Execute insert ***/
      try{
        
        PreparedStatement ps = dbUtil.getConnection().prepareStatement(sbQuery.toString());
        
        /*** Add query parameters ***/
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPhone());
        ps.setString(4, user.getLoginID());
        ps.setString(5, password);
        
        result = ps.executeUpdate();
          
      }catch(Exception e){
          log.info("Exception Occured");
                log.info("Class Name: " +this.getClass().getName());
                log.info("Method: saveUser()");
                log.info(e.getMessage());
      }
      
      
      dbUtil.closeConnection();
      dbUtil = null;
      return result;
      
  }

  public int updateUser(User user) {
    /*** Instantiate DB Util class ***/
    dbUtil = new DBUtil();
    StringBuffer sbQuery = dbUtil.getQueryBuffer();
    int result = -1;
    
    /*** Get the details of a particular User ***/
        /*** QUERY 6 ***/
        sbQuery.setLength(0);
        sbQuery.append(" UPDATE lookup.user u                   ");
        sbQuery.append(" SET                                    ");
        sbQuery.append("    u.name = IFNULL(?, u.name),    ");
        sbQuery.append("    u.email = IFNULL(?, u.email),  ");
        sbQuery.append("    u.phone = IFNULL(?, u.phone)   ");
        sbQuery.append(" WHERE u.id = ?                         ");

        
    /*** Get Connection and Execute insert ***/
    try{
      
      PreparedStatement ps = dbUtil.getConnection().prepareStatement(sbQuery.toString());
      
      /*** Add query parameters ***/
      ps.setString(1, user.getName());
      ps.setString(2, user.getEmail());
      ps.setString(3, user.getPhone());
      ps.setInt(4, user.getId());
      
      result = ps.executeUpdate();
        
    }catch(Exception e){
        log.info("Exception Occured");
              log.info("Class Name: " +this.getClass().getName());
              log.info("Method: saveUser()");
              log.info(e.getMessage());
    }
    
    
    dbUtil.closeConnection();
    dbUtil = null;
    return result;

  }

  public User autheticateUser(String loginID, String password){
	  /*** Instantiate DB Util class ***/
	    dbUtil = new DBUtil();
	    StringBuffer sbQuery = dbUtil.getQueryBuffer();
	    ArrayList<String> args = dbUtil.getArgs();
	    ResultSet rs = null;
	    User user = null;
	    
	    /*** Get the details of a particular User ***/
	        /*** QUERY 26 ***/
	        sbQuery.setLength(0);
	        sbQuery.append(" SELECT                       ");
	        sbQuery.append(" id,                          ");
	        sbQuery.append(" name,                        ");
	        sbQuery.append(" email,                       ");
	        sbQuery.append(" phone,                       ");
	        sbQuery.append(" loginID                      ");
	        sbQuery.append(" FROM lookup.user             ");
	        sbQuery.append(" WHERE   loginID = ?          ");
	        sbQuery.append(" AND passwd = ?               ");

	        /*** Prepare parameters for query ***/
	        args.clear();
	        args.add(loginID);
	        args.add(password);
	        
	        
	    /*** Pass the appropriate arguments to DBUtil class ***/
	    rs = dbUtil.executeSelect(sbQuery.toString(), args);
	    
	    /*** Convert the result set into a User object ***/
	    if(rs!=null)
	    	user = TransformUtil.convertToUser(rs);
	    
	    dbUtil.closeConnection();
	    dbUtil = null;
	    
	    if(user!=null && user.getId()!=0)
	    		return user;
	    else
	    		return null;
  }
}

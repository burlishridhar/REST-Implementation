package lookup.repository.implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import lookup.domain.Category;
import lookup.repository.CategoryRepository;
import lookup.util.DBUtil;
import lookup.util.TransformUtil;

public class CategoryRepositoryImplementation implements CategoryRepository {

	private static Logger log = Logger.getLogger("CategoryRepositoryImplementation");
	private DBUtil dbUtil;
	  
	public List<Category> getAllCategories() {
		/*** Instantiate DB Util class ***/
	    dbUtil = new DBUtil();
	    StringBuffer sbQuery = dbUtil.getQueryBuffer();
	    ArrayList<String> args = dbUtil.getArgs();
	    ResultSet rs = null;
	    List<Category> listCategory = null;
	    
	    /*** Get the details of a particular Category ***/
	        /*** QUERY  ***/
	        sbQuery.setLength(0);
	        sbQuery.append(" SELECT                    ");
	        sbQuery.append("        id,                ");
	        sbQuery.append("        name,              ");
	        sbQuery.append("        userid             ");
	        sbQuery.append(" FROM lookup.category      ");
	        
	        /*** Prepare parameters for query ***/
	        args.clear();
	        
	        
	    /*** Pass the appropriate arguments to DBUtil class ***/
	    rs = dbUtil.executeSelect(sbQuery.toString(), args);
	    
	    /*** Convert the result set into a User object ***/
	    if(rs!=null)
	      listCategory = TransformUtil.convertToListCategory(rs);
	    
	    dbUtil.closeConnection();
	    dbUtil = null;
	    
	    return listCategory;
	}

	public List<Category> getCategoryByName(String name) {
		/*** Instantiate DB Util class ***/
	    dbUtil = new DBUtil();
	    StringBuffer sbQuery = dbUtil.getQueryBuffer();
	    ArrayList<String> args = dbUtil.getArgs();
	    ResultSet rs = null;
	    List<Category> listCategory = null;
	    
	    /*** Get the details of a particular User ***/
	        /*** QUERY  ***/
	        sbQuery.setLength(0);
	        sbQuery.append(" SELECT                              ");
	        sbQuery.append("        id,                          ");
	        sbQuery.append("        name,                        ");
	        sbQuery.append("        userid                       ");
	        sbQuery.append(" FROM lookup.category c              ");
	        sbQuery.append(" WHERE UPPER(c.name) = UPPER(?)      ");
	        
	        /*** Prepare parameters for query ***/
	        args.clear();
	        args.add(name);
	        
	    /*** Pass the appropriate arguments to DBUtil class ***/
	    rs = dbUtil.executeSelect(sbQuery.toString(), args);
	    
	    /*** Convert the result set into a User object ***/
	    if(rs!=null)
	      listCategory = TransformUtil.convertToListCategory(rs);
	    
	    dbUtil.closeConnection();
	    dbUtil = null;
	    
	    return listCategory;
	}

	public List<Category> getCategoryByUserID(int userID) {
		 /*** Instantiate DB Util class ***/
	    dbUtil = new DBUtil();
	    StringBuffer sbQuery = dbUtil.getQueryBuffer();
	    ArrayList<String> args = dbUtil.getArgs();
	    ResultSet rs = null;
	    List<Category> listCategory = null;
	    
	    /*** Get the details of a particular User ***/
	        /*** QUERY  ***/
	        sbQuery.setLength(0);
	        sbQuery.append(" SELECT                      ");
	        sbQuery.append("        id,                  ");
	        sbQuery.append("        name,                ");
	        sbQuery.append("        userid               ");
	        sbQuery.append(" FROM lookup.category c      ");
	        sbQuery.append(" WHERE c.userid = ?          ");
	       
	    /*** Get Connection and Execute  ***/
	    try{
	      
	      PreparedStatement ps = dbUtil.getConnection().prepareStatement(sbQuery.toString());
	      
	      /*** Add query parameters ***/
	      ps.setInt(1, userID);
	      
	      rs = ps.executeQuery();
	        
	    }catch(Exception e){
	        log.info("Exception Occured");
	              log.info("Class Name: " +this.getClass().getName());
	              log.info("Method: getUserById()");
	              log.info(e.getMessage());
	    }
	    
	    /*** Convert the result set into a User object ***/
	    if(rs!=null)
	    	listCategory = TransformUtil.convertToListCategory(rs);
	    
	    dbUtil.closeConnection();
	    dbUtil = null;
	    
	    return listCategory;
	}

	public Category getCategorybyID(int id) {
		 /*** Instantiate DB Util class ***/
	    dbUtil = new DBUtil();
	    StringBuffer sbQuery = dbUtil.getQueryBuffer();
	    ArrayList<String> args = dbUtil.getArgs();
	    ResultSet rs = null;
	    Category found = null;
	    
	    /*** Get the details of a particular User ***/
	        /*** QUERY  ***/
	        sbQuery.setLength(0);
	        sbQuery.append(" SELECT                      ");
	        sbQuery.append("        id,                  ");
	        sbQuery.append("        name,                ");
	        sbQuery.append("        userid               ");
	        sbQuery.append(" FROM lookup.category c      ");
	        sbQuery.append(" WHERE c.id = ?              ");
	       
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
	      found = TransformUtil.convertToCategory(rs);
	    
	    dbUtil.closeConnection();
	    dbUtil = null;
	    
	    return found;
	}

	public int saveCategory(Category category) {
		/*** Instantiate DB Util class ***/
	      dbUtil = new DBUtil();
	      StringBuffer sbQuery = dbUtil.getQueryBuffer();
	      int result = -1;
	      
	      /*** Get the details of a particular User ***/
	          /*** QUERY  ***/
	          sbQuery.setLength(0);
	          sbQuery.append(" INSERT INTO lookup.category(name, userid) ");
	          sbQuery.append(" VALUES                                    ");
	          sbQuery.append(" (?, ?)                                    ");

	      /*** Get Connection and Execute insert ***/
	      try{
	        
	        PreparedStatement ps = dbUtil.getConnection().prepareStatement(sbQuery.toString());
	        
	        /*** Add query parameters ***/
	        ps.setString(1, category.getName());
	        ps.setInt(2, category.getUserID());
	        
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

	public int updateCategory(Category category) {
		/*** Instantiate DB Util class ***/
	      dbUtil = new DBUtil();
	      StringBuffer sbQuery = dbUtil.getQueryBuffer();
	      int result = -1;
	      
	      /*** Get the details of a particular User ***/
	          /*** QUERY  ***/
	          sbQuery.setLength(0);
	          sbQuery.append(" UPDATE lookup.category c    ");
	          sbQuery.append(" SET                         ");
	          sbQuery.append(" name = IFNULL(?,c.name),    ");
	          sbQuery.append(" userid = IFNULL(?,c.userid) ");
	          sbQuery.append(" WHERE                       ");
	          sbQuery.append(" c.id = ?                    ");
	         
	      /*** Get Connection and Execute insert ***/
	      try{
	        
	        PreparedStatement ps = dbUtil.getConnection().prepareStatement(sbQuery.toString());
	        
	        /*** Add query parameters ***/
	        ps.setString(1, category.getName());
	        ps.setInt(2, category.getUserID());
	        ps.setInt(3, category.getId());
	        
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

}

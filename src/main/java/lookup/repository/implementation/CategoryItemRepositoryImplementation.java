package lookup.repository.implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import lookup.domain.Category;
import lookup.domain.CategoryItem;
import lookup.repository.CategoryItemRepository;
import lookup.util.DBUtil;
import lookup.util.TransformUtil;

public class CategoryItemRepositoryImplementation implements
		CategoryItemRepository {
	private static Logger log = Logger.getLogger("CategoryItemRepositoryImplementation");
	private DBUtil dbUtil;

	public List<CategoryItem> getAllCategoryItems() {
		/*** Instantiate DB Util class ***/
	    dbUtil = new DBUtil();
	    StringBuffer sbQuery = dbUtil.getQueryBuffer();
	    ArrayList<String> args = dbUtil.getArgs();
	    ResultSet rs = null;
	    List<CategoryItem> listCategory = null;
	    
	    /*** Get the details of a particular Category ***/
	        /*** QUERY  ***/
	        sbQuery.setLength(0);
	        sbQuery.append(" SELECT                        ");
	        sbQuery.append("        id,                    ");
	        sbQuery.append("        categoryId,            ");
	        sbQuery.append("        name,                  ");
	        sbQuery.append("        userid                 ");
	        sbQuery.append(" FROM lookup.categoryitem      ");

	        
	        /*** Prepare parameters for query ***/
	        args.clear();
	        
	        
	    /*** Pass the appropriate arguments to DBUtil class ***/
	    rs = dbUtil.executeSelect(sbQuery.toString(), args);
	    
	    /*** Convert the result set into a User object ***/
	    if(rs!=null)
	      listCategory = TransformUtil.convertToListCategoryItem(rs);
	    
	    dbUtil.closeConnection();
	    dbUtil = null;
	    
	    return listCategory;
	}

	public List<CategoryItem> getCategoryItemByCategory(int categoryID) {
		 /*** Instantiate DB Util class ***/
	    dbUtil = new DBUtil();
	    StringBuffer sbQuery = dbUtil.getQueryBuffer();
	    ArrayList<String> args = dbUtil.getArgs();
	    ResultSet rs = null;
	    List<CategoryItem> listCategory = null;
	    
	    /*** Get the details of a particular User ***/
	        /*** QUERY  ***/
	        sbQuery.setLength(0);
	        sbQuery.append(" SELECT                          ");
	        sbQuery.append("        id,                      ");
	        sbQuery.append("        categoryId,              ");
	        sbQuery.append("        name,                    ");
	        sbQuery.append("        userid                   ");
	        sbQuery.append(" FROM lookup.categoryitem c      ");
	        sbQuery.append(" where c.categoryId = ?          ");
	       
	    /*** Get Connection and Execute  ***/
	    try{
	      
	      PreparedStatement ps = dbUtil.getConnection().prepareStatement(sbQuery.toString());
	      
	      /*** Add query parameters ***/
	      ps.setInt(1, categoryID);
	      
	      rs = ps.executeQuery();
	        
	    }catch(Exception e){
	        log.info("Exception Occured");
	              log.info("Class Name: " +this.getClass().getName());
	              log.info("Method: getUserById()");
	              log.info(e.getMessage());
	    }
	    
	    /*** Convert the result set into a User object ***/
	    if(rs!=null)
	    	listCategory = TransformUtil.convertToListCategoryItem(rs);
	    
	    dbUtil.closeConnection();
	    dbUtil = null;
	    
	    return listCategory;
	}

	public List<CategoryItem> getCategoryItemByName(String categoryItemName) {
		/*** Instantiate DB Util class ***/
	    dbUtil = new DBUtil();
	    StringBuffer sbQuery = dbUtil.getQueryBuffer();
	    ArrayList<String> args = dbUtil.getArgs();
	    ResultSet rs = null;
	    List<CategoryItem> listCategory = null;
	    
	    /*** Get the details of a particular Category ***/
	        /*** QUERY  ***/
	        sbQuery.setLength(0);
	        sbQuery.append(" SELECT                                                 ");
	        sbQuery.append("        id,                                             ");
	        sbQuery.append("        categoryId,                                     ");
	        sbQuery.append("        name,                                           ");
	        sbQuery.append("        userid                                          ");
	        sbQuery.append(" FROM lookup.categoryitem c                             ");
	        sbQuery.append(" WHERE UPPER(c.name) LIKE CONCAT('%',UPPER(?),'%')      ");
	        
	        /*** Prepare parameters for query ***/
	        args.clear();
	        args.add(categoryItemName);
	        
	        
	    /*** Pass the appropriate arguments to DBUtil class ***/
	    rs = dbUtil.executeSelect(sbQuery.toString(), args);
	    
	    /*** Convert the result set into a User object ***/
	    if(rs!=null)
	      listCategory = TransformUtil.convertToListCategoryItem(rs);
	    
	    dbUtil.closeConnection();
	    dbUtil = null;
	    
	    return listCategory;
	}

	public List<CategoryItem> getCategoryItemByUser(int userID) {
		 /*** Instantiate DB Util class ***/
	    dbUtil = new DBUtil();
	    StringBuffer sbQuery = dbUtil.getQueryBuffer();
	    ArrayList<String> args = dbUtil.getArgs();
	    ResultSet rs = null;
	    List<CategoryItem> listCategory = null;
	    
	    /*** Get the details of a particular User ***/
	        /*** QUERY  ***/
	        sbQuery.setLength(0);
	        sbQuery.append(" SELECT                          ");
	        sbQuery.append("        id,                      ");
	        sbQuery.append("        categoryId,              ");
	        sbQuery.append("        name,                    ");
	        sbQuery.append("        userid                   ");
	        sbQuery.append(" FROM lookup.categoryitem c      ");
	        sbQuery.append(" WHERE c.userid = ?              ");
	        
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
	    	listCategory = TransformUtil.convertToListCategoryItem(rs);
	    
	    dbUtil.closeConnection();
	    dbUtil = null;
	    
	    return listCategory;
	}

	public CategoryItem getCategoryItemByID(int categoryItemID) {
		 /*** Instantiate DB Util class ***/
	    dbUtil = new DBUtil();
	    StringBuffer sbQuery = dbUtil.getQueryBuffer();
	    ArrayList<String> args = dbUtil.getArgs();
	    ResultSet rs = null;
	    CategoryItem found = null;
	    
	    /*** Get the details of a particular User ***/
	        /*** QUERY  ***/
	        sbQuery.setLength(0);
	        sbQuery.append(" SELECT                          ");
	        sbQuery.append("        id,                      ");
	        sbQuery.append("        categoryId,              ");
	        sbQuery.append("        name,                    ");
	        sbQuery.append("        userid                   ");
	        sbQuery.append(" FROM lookup.categoryitem c      ");
	        sbQuery.append(" WHERE c.id = ?                  ");
	       
	    /*** Get Connection and Execute  ***/
	    try{
	      
	      PreparedStatement ps = dbUtil.getConnection().prepareStatement(sbQuery.toString());
	      
	      /*** Add query parameters ***/
	      ps.setInt(1, categoryItemID);
	      
	      rs = ps.executeQuery();
	        
	    }catch(Exception e){
	        log.info("Exception Occured");
	              log.info("Class Name: " +this.getClass().getName());
	              log.info("Method: getUserById()");
	              log.info(e.getMessage());
	    }
	    
	    /*** Convert the result set into a User object ***/
	    if(rs!=null)
	      found = TransformUtil.convertToCategoryItem(rs);
	    
	    dbUtil.closeConnection();
	    dbUtil = null;
	    
	    return found;
	}

	public int saveCategoryItem(CategoryItem categoryItem) {
		/*** Instantiate DB Util class ***/
	      dbUtil = new DBUtil();
	      StringBuffer sbQuery = dbUtil.getQueryBuffer();
	      int result = -1;
	      
	      /*** Get the details of a particular User ***/
	          /*** QUERY  ***/
	          sbQuery.setLength(0);
	          sbQuery.append(" INSERT INTO lookup.categoryitem(categoryId, name, userid) ");
	          sbQuery.append(" VALUES                                                    ");
	          sbQuery.append(" (?, ?, ?)                                                 ");

	      /*** Get Connection and Execute insert ***/
	      try{
	        
	        PreparedStatement ps = dbUtil.getConnection().prepareStatement(sbQuery.toString());
	        
	        /*** Add query parameters ***/
	        ps.setInt(1, categoryItem.getCategoryID() );
	        ps.setString(2, categoryItem.getName());
	        ps.setInt(3, categoryItem.getUserID());
	        
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

	public int updateCategoryItem(CategoryItem categoryItem) {
		/*** Instantiate DB Util class ***/
	      dbUtil = new DBUtil();
	      StringBuffer sbQuery = dbUtil.getQueryBuffer();
	      int result = -1;
	      
	      /*** Get the details of a particular User ***/
	          /*** QUERY  ***/
	          sbQuery.setLength(0);
	          sbQuery.append(" UPDATE lookup.categoryitem c               " );
	          sbQuery.append(" SET                                        " );
	          sbQuery.append("    c.categoryId = IFNULL(?,c.categoryId),  " );
	          sbQuery.append("    c.name       = IFNULL(?,c.name),        " );
	          sbQuery.append("    c.userid     = IFNULL(?,c.userid)       " );
	          sbQuery.append(" WHERE                                      " );
	          sbQuery.append("    c.id = ?                                " );

	      /*** Get Connection and Execute insert ***/
	      try{
	        
	        PreparedStatement ps = dbUtil.getConnection().prepareStatement(sbQuery.toString());
	        
	        /*** Add query parameters ***/
	        ps.setInt(1, categoryItem.getCategoryID() );
	        ps.setString(2, categoryItem.getName());
	        ps.setInt(3, categoryItem.getUserID());
	        ps.setInt(4, categoryItem.getId());
	        
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

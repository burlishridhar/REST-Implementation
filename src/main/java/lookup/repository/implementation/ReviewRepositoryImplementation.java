package lookup.repository.implementation;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import lookup.domain.Category;
import lookup.domain.Review;
import lookup.repository.ReviewRepository;
import lookup.util.DBUtil;
import lookup.util.TransformUtil;

public class ReviewRepositoryImplementation implements ReviewRepository {

	private static Logger log = Logger.getLogger("ReviewRepositoryImplementation");
	private DBUtil dbUtil;
	
	public List<Review> getAllReviews() {
		/*** Instantiate DB Util class ***/
	    dbUtil = new DBUtil();
	    StringBuffer sbQuery = dbUtil.getQueryBuffer();
	    ArrayList<String> args = dbUtil.getArgs();
	    ResultSet rs = null;
	    List<Review> listReview = null;
	    
	    /*** Get the details of a particular Review ***/
	        /*** QUERY  ***/
	        sbQuery.setLength(0);
	        sbQuery.append( " SELECT             " );
	        sbQuery.append( " id,                " );
	        sbQuery.append( " categoryitemid,    " );
	        sbQuery.append( " rating,            " );
	        sbQuery.append( " comment,           " );
	        sbQuery.append( " userid,            " );
	        sbQuery.append( " created_date       " );
	        sbQuery.append( " FROM lookup.review " );
	        
	        /*** Prepare parameters for query ***/
	        args.clear();
	        
	    /*** Pass the appropriate arguments to DBUtil class ***/
	    rs = dbUtil.executeSelect(sbQuery.toString(), args);
	    
	    /*** Convert the result set into a User object ***/
	    if(rs!=null)
	    	listReview = TransformUtil.convertToListReview(rs);
	    
	    dbUtil.closeConnection();
	    dbUtil = null;
	    
	    return listReview;
	}

	public List<Review> getReviewByCategoryItem(int categoryItemId) {
		 /*** Instantiate DB Util class ***/
	    dbUtil = new DBUtil();
	    StringBuffer sbQuery = dbUtil.getQueryBuffer();
	    ArrayList<String> args = dbUtil.getArgs();
	    ResultSet rs = null;
	    List<Review> listReview = null;
	    
	    /*** Get the details of a particular User ***/
	        /*** QUERY  ***/
	        sbQuery.setLength(0);
	        sbQuery.append( " SELECT                     " );
	        sbQuery.append( " id,                        " );
	        sbQuery.append( " categoryitemid,            " );
	        sbQuery.append( " rating,                    " );
	        sbQuery.append( " comment,                   " );
	        sbQuery.append( " userid,                    " );
	        sbQuery.append( " created_date               " );
	        sbQuery.append( " FROM lookup.review r       " );
	        sbQuery.append( " WHERE r.categoryitemid = ? " );
	       
	    /*** Get Connection and Execute  ***/
	    try{
	      
	      PreparedStatement ps = dbUtil.getConnection().prepareStatement(sbQuery.toString());
	      
	      /*** Add query parameters ***/
	      ps.setInt(1, categoryItemId);
	      
	      rs = ps.executeQuery();
	        
	    }catch(Exception e){
	        log.info("Exception Occured");
	              log.info("Class Name: " +this.getClass().getName());
	              log.info("Method: getUserById()");
	              log.info(e.getMessage());
	    }
	    
	    /*** Convert the result set into a User object ***/
	    if(rs!=null)
	    	listReview = TransformUtil.convertToListReview(rs);
	    
	    dbUtil.closeConnection();
	    dbUtil = null;
	    
	    return listReview;
	}

	public List<Review> getReviewByKeywords(List<String> keywords) {
		/*** Instantiate DB Util class ***/
	    dbUtil = new DBUtil();
	    StringBuffer sbQuery = dbUtil.getQueryBuffer();
	    ResultSet rs = null;
	    List<Review> listReview = null;
	    
	    /*** Get the details of a particular Review ***/
	        /*** QUERY  ***/
	        sbQuery.setLength(0);
	        sbQuery.append("SELECT ");
	        sbQuery.append("        id, ");
	        sbQuery.append("        categoryitemid, ");
	        sbQuery.append("        rating, ");
	        sbQuery.append("        comment, ");
	        sbQuery.append("        userid, ");
	        sbQuery.append("        created_date ");
	        sbQuery.append("FROM lookup.review r ");
	        sbQuery.append("WHERE ");
	        sbQuery.append("    (   UPPER(r.comment) ");
	        sbQuery.append("        REGEXP ");
	        sbQuery.append("        CONCAT( ");
	        sbQuery.append("                UPPER(?) ");
	        for(int i=1; i<keywords.size();i++)
	        				sbQuery.append("   ,'|',UPPER(?) ");
			sbQuery.append("            ) ");
			sbQuery.append("    ) > 0 ");
	        
	        
	    /*** Pass the appropriate arguments to DBUtil class ***/
	    rs = dbUtil.executeSelect(sbQuery.toString(), keywords);
	    
	    /*** Convert the result set into a User object ***/
	    if(rs!=null)
	    	listReview = TransformUtil.convertToListReview(rs);
	    
	    dbUtil.closeConnection();
	    dbUtil = null;
	    
	    return listReview;
	}

	public Review getReviewByID(int id) {
		/*** Instantiate DB Util class ***/
	    dbUtil = new DBUtil();
	    StringBuffer sbQuery = dbUtil.getQueryBuffer();
	    ArrayList<String> args = dbUtil.getArgs();
	    ResultSet rs = null;
	    Review found = null;
	    
	    /*** Get the details of a particular User ***/
	        /*** QUERY  ***/
	        sbQuery.setLength(0);
	        sbQuery.append( " SELECT               " );
	        sbQuery.append( " id,                  " );
	        sbQuery.append( " categoryitemid,      " );
	        sbQuery.append( " rating,              " );
	        sbQuery.append( " comment,             " );
	        sbQuery.append( " userid,              " );
	        sbQuery.append( " created_date         " );
	        sbQuery.append( " FROM lookup.review r " );
	        sbQuery.append( " WHERE r.id = ?       " );
	       
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
	      found = TransformUtil.convertToReview(rs);
	    
	    dbUtil.closeConnection();
	    dbUtil = null;
	    
	    return found;
	}

	public int saveReview(Review review) {
		/*** Instantiate DB Util class ***/
	      dbUtil = new DBUtil();
	      StringBuffer sbQuery = dbUtil.getQueryBuffer();
	      int result = -1;
	      
	      /*** Get the details of a particular User ***/
	          /*** QUERY  ***/
	          sbQuery.setLength(0);
	          sbQuery.append( " INSERT INTO lookup.review(categoryitemid, rating, comment, userid, created_date) " );
	          sbQuery.append( " VALUES                                                                           " );
	          sbQuery.append( " (?, ?, ?,?,?)                                                                    " );

	      /*** Get Connection and Execute insert ***/
	      try{
	        
	        PreparedStatement ps = dbUtil.getConnection().prepareStatement(sbQuery.toString());
	        
	        /*** Add query parameters ***/
	        ps.setInt(1, review.getCategoryItemID() );
	        ps.setDouble(2, review.getRating());
	        ps.setString(3, review.getComment());
	        ps.setInt(4, review.getUserID());
	        ps.setDate(5, new Date(review.getCreatedDate().getTime()));
	        
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

	public int updateReview(Review review) {
		/*** Instantiate DB Util class ***/
	      dbUtil = new DBUtil();
	      StringBuffer sbQuery = dbUtil.getQueryBuffer();
	      int result = -1;
	      
	      /*** Get the details of a particular User ***/
	          /*** QUERY  ***/
	          sbQuery.setLength(0);
	          sbQuery.append( " UPDATE lookup.review r          " );
	          sbQuery.append( " SET                             " );
	          sbQuery.append( " r.rating = IFNULL(?,r.rating),  " );
	          sbQuery.append( " r.comment = IFNULL(?,r.comment) " );
	          sbQuery.append( " WHERE                           " );
	          sbQuery.append( " r.id = ?                        " );
	          sbQuery.append( " AND r.userid = ?                " );


	      /*** Get Connection and Execute insert ***/
	      try{
	        
	        PreparedStatement ps = dbUtil.getConnection().prepareStatement(sbQuery.toString());
	        
	        /*** Add query parameters ***/
	        ps.setDouble(1, review.getRating());
	        ps.setString(2, review.getComment());
	        ps.setInt(3, review.getId());
	        ps.setInt(4, review.getUserID());
	        
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

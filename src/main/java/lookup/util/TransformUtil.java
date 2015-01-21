package lookup.util;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import lookup.domain.Category;
import lookup.domain.CategoryItem;
import lookup.domain.Review;
import lookup.domain.User;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


public class TransformUtil {
	private static Logger log = Logger.getLogger("TransformUtil");

	/*
	 * Result Set to List of User
	 * */
	public static List<User> convertToListUser(ResultSet rs){
		List<User> list = null;
		try{
			rs.beforeFirst();
			list = new ArrayList<User>();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setLoginID(rs.getString("loginID"));
				
				list.add(user);
			}
		}catch(Exception e){
				log.info("Exception Occured");
	            log.info("Class Name: TransformUtil");
	            log.info("Method: convertToListUser()");
	            log.info("Message: ");
	            log.info(e.getMessage());
		}
		
		return list;
	}

	/*
	 * Result Set to User
	 * */
	public static User convertToUser(ResultSet rs){
		User user = null;
		try{
				rs.beforeFirst(); rs.next();
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setLoginID(rs.getString("loginID"));
								
		}catch(Exception e){
				log.info("Exception Occured");
	            log.info("Class Name: TransformUtil");
	            log.info("Method: convertToUser()");
	            log.info(e.getMessage());
		}
		
		return user;
	}
	
	/*
	 * Result Set to List of Category
	 * */
	public static List<Category> convertToListCategory(ResultSet rs){
		List<Category> list = null;
		try{
			rs.beforeFirst();
			list = new ArrayList<Category>();
			while(rs.next()){
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setUserID(rs.getInt("userid"));
				
				list.add(category);
			}
		}catch(Exception e){
				log.info("Exception Occured");
	            log.info("Class Name: TransformUtil");
	            log.info("Method: convertToListUser()");
	            log.info("Message: ");
	            log.info(e.getMessage());
		}
		
		return list;
	}

	/*
	 * Result Set to Category
	 * */
	public static Category convertToCategory(ResultSet rs){
		Category category = null;
		try{
				rs.beforeFirst(); rs.next();
				category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setUserID(rs.getInt("userid"));
								
		}catch(Exception e){
				log.info("Exception Occured");
	            log.info("Class Name: TransformUtil");
	            log.info("Method: convertToUser()");
	            log.info(e.getMessage());
		}
		
		return category;
	}
	
	/*
	 * Result Set to List of Category Item
	 * */
	public static List<CategoryItem> convertToListCategoryItem(ResultSet rs){
		List<CategoryItem> list = null;
		try{
			rs.beforeFirst();
			list = new ArrayList<CategoryItem>();
			while(rs.next()){
				CategoryItem categoryItem = new CategoryItem();
				categoryItem.setId(rs.getInt("id"));
				categoryItem.setCategoryID(rs.getInt("categoryId"));
				categoryItem.setName(rs.getString("name"));
				categoryItem.setUserID(rs.getInt("userid"));
				
				list.add(categoryItem);
			}
		}catch(Exception e){
				log.info("Exception Occured");
	            log.info("Class Name: TransformUtil");
	            log.info("Method: convertToListUser()");
	            log.info("Message: ");
	            log.info(e.getMessage());
		}
		
		return list;
	}

	/*
	 * Result Set to Category Item
	 * */
	public static CategoryItem convertToCategoryItem(ResultSet rs){
		CategoryItem categoryItem = null;
		try{
				rs.beforeFirst(); rs.next();
				categoryItem = new CategoryItem();
				categoryItem.setId(rs.getInt("id"));
				categoryItem.setCategoryID(rs.getInt("categoryId"));
				categoryItem.setName(rs.getString("name"));
				categoryItem.setUserID(rs.getInt("userid"));
								
		}catch(Exception e){
				log.info("Exception Occured");
	            log.info("Class Name: TransformUtil");
	            log.info("Method: convertToUser()");
	            log.info(e.getMessage());
		}
		
		return categoryItem;
	}

	/*
	 * Result Set to List of Review
	 * */
	public static List<Review> convertToListReview(ResultSet rs){
		List<Review> list = null;
		try{
			rs.beforeFirst();
			list = new ArrayList<Review>();
			while(rs.next()){
				Review review = new Review();
				review.setId(rs.getInt("id"));
				review.setCategoryItemID(rs.getInt("categoryitemid"));
				review.setRating(rs.getInt("rating"));
				review.setComment(rs.getString("comment"));
				review.setUserID(rs.getInt("userid"));
				review.setCreatedDate(rs.getDate("created_date"));
				
				list.add(review);
			}
		}catch(Exception e){
				log.info("Exception Occured");
	            log.info("Class Name: TransformUtil");
	            log.info("Method: convertToListUser()");
	            log.info("Message: ");
	            log.info(e.getMessage());
		}
		
		return list;
	}
	
	/*
	 * Result Set to  Review
	 * */
	public static Review convertToReview(ResultSet rs){
		Review review = null;
		try{
				rs.beforeFirst(); rs.next();
				review = new Review();
				review.setId(rs.getInt("id"));
				review.setCategoryItemID(rs.getInt("categoryitemid"));
				review.setRating(rs.getInt("rating"));
				review.setComment(rs.getString("comment"));
				review.setUserID(rs.getInt("userid"));
				review.setCreatedDate(rs.getDate("created_date"));
								
		}catch(Exception e){
				log.info("Exception Occured");
	            log.info("Class Name: TransformUtil");
	            log.info("Method: convertToUser()");
	            log.info(e.getMessage());
		}
		
		return review;
	}
	
	/*
	 * Category List to JSON Array
	 * */
	public static JSONArray convertCategoryList(List<Category> categoryList){
		JSONArray jsonArray = new JSONArray();
		try{
				for(Category category:categoryList){
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("id", category.getId());
						jsonObject.put("name", category.getName());
						jsonObject.put("userid", category.getUserID());
						
						jsonArray.put(jsonObject);
				}
		}catch(Exception e){
				log.info("Exception Occured");
	            log.info("Class Name: TransformUtil");
	            log.info("Method: convertCategoryList()");
	            log.info(e.getMessage());
		}
		return jsonArray;
	}

	/*
	 * Category Item List to JSON Array
	 * */
	public static JSONArray convertCategoryItemList(List<CategoryItem> categoryItemList){
		JSONArray jsonArray = new JSONArray();
		try{
				for(CategoryItem categoryItem:categoryItemList){
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("id", categoryItem.getId());
						jsonObject.put("categoryid", categoryItem.getCategoryID());
						jsonObject.put("name", categoryItem.getName());
						jsonObject.put("userid", categoryItem.getUserID());
						
						jsonArray.put(jsonObject);
				}
		}catch(Exception e){
				log.info("Exception Occured");
	            log.info("Class Name: TransformUtil");
	            log.info("Method: convertCategoryItemList()");
	            log.info(e.getMessage());
		}
		return jsonArray;
	}

	/*
	 * Review List to JSON Array
	 * */
	public static JSONArray convertReviewList(List<Review> reviewList){
		JSONArray jsonArray = new JSONArray();
		try{
				for(Review review:reviewList){
						JSONObject jsonObject = new JSONObject();
						
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
						
						jsonObject.put("id", review.getId() );
						jsonObject.put("categoryitemid", review.getCategoryItemID());
						jsonObject.put("rating", review.getRating());
						jsonObject.put("comment", review.getComment() );
						jsonObject.put("userid", review.getUserID());
						jsonObject.put("createddate", simpleDateFormat.format(review.getCreatedDate()));
						
						jsonArray.put(jsonObject);
				}
		}catch(Exception e){
				log.info("Exception Occured");
	            log.info("Class Name: TransformUtil");
	            log.info("Method: convertCategoryItemList()");
	            log.info(e.getMessage());
		}
		return jsonArray;
	}


	/*
	 * User List to JSON Array
	 * */
	public static JSONArray convertUserList(List<User> userList){
		JSONArray jsonArray = new JSONArray();
		try{
				for(User user:userList){
						JSONObject jsonObject = new JSONObject();
						
						jsonObject.put("id", user.getId());
						jsonObject.put("name", user.getName());
						jsonObject.put("email", user.getEmail());
						jsonObject.put("phone", user.getPhone());
						jsonObject.put("loginid", user.getLoginID());
						
						jsonArray.put(jsonObject);
				}
		}catch(Exception e){
				log.info("Exception Occured");
	            log.info("Class Name: TransformUtil");
	            log.info("Method: convertUserList()");
	            log.info(e.getMessage());
		}
		return jsonArray;
	}
}

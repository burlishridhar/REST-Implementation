package lookup.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import lookup.domain.Category;
import lookup.domain.CategoryItem;
import lookup.domain.Review;
import lookup.repository.implementation.CategoryItemRepositoryImplementation;
import lookup.repository.implementation.CategoryRepositoryImplementation;
import lookup.repository.implementation.ReviewRepositoryImplementation;
import lookup.util.TransformUtil;

@Path("/review/")
public class ReviewJSON {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllReviews(){
		List<Review> list = new ReviewRepositoryImplementation().getAllReviews();
		JSONArray jsonArray = TransformUtil.convertReviewList(list);
		return Response.ok(jsonArray.toString()).build();
	}
	
	@Path("/item/{itemid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReviewByCategoryItem(@PathParam("itemid")  int itemid){
		List<Review> list = new ReviewRepositoryImplementation().getReviewByCategoryItem(itemid);
		JSONArray jsonArray = TransformUtil.convertReviewList(list);
		return Response.ok(jsonArray.toString()).build();
	}
	
	@Path("/keywords/{keywords}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReviewByKeywords(@PathParam("keywords") String keywords){
		List<String> keywordList = new ArrayList<String>(Arrays.asList(keywords.split("_")));
		List<Review> reviewList = new ReviewRepositoryImplementation().getReviewByKeywords(keywordList);
		JSONArray jsonArray = null;
		if(reviewList!=null && reviewList.size()!=0)
			jsonArray = TransformUtil.convertReviewList(reviewList);
		else
			jsonArray = new JSONArray();
		return Response.ok(jsonArray.toString()).build();
	}
	
	@Path("/id/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReviewByID(@PathParam("id") int id){
		Review review = new ReviewRepositoryImplementation().getReviewByID(id);
			List<Review> list = new ArrayList<Review>();
			list.add(review);
		JSONArray jsonArray = TransformUtil.convertReviewList(list);
		return Response.ok(jsonArray.toString()).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response addReview(String incomingData) throws JSONException{
		JSONObject jsonObject = new JSONObject(incomingData);
		JSONObject jsObj = new JSONObject();
		JSONArray jsArray = new JSONArray();
		
		CategoryItemRepositoryImplementation ci = new CategoryItemRepositoryImplementation();
		ReviewRepositoryImplementation r = new ReviewRepositoryImplementation();
		List<CategoryItem> categoryItemList = null;
		Review review = null;
		
		try{
			categoryItemList = ci.getCategoryItemByName(jsonObject.getString("categoryItem"));
			review = new Review();
			
			review.setCategoryItemID(categoryItemList.get(0).getId());
			review.setRating(jsonObject.getInt("rating"));
			review.setComment(jsonObject.getString("comment"));
			review.setCreatedDate(new Date());
			review.setUserID(1);
			
			int result = r.saveReview(review);
			
			if(result!=-1){
				jsObj.put("HTTP_CODE", "200");
				jsObj.put("MSG", "Review added successfully");
			}else{
				jsObj.put("HTTP_CODE", "200");
				jsObj.put("MSG", "Review could not be added");
			}
			
		}catch(Exception e){
			
		}
		
		
		return Response.ok(jsArray.put(jsObj).toString()).build();
		
	}
	
}

package lookup.rest;

import java.util.ArrayList;
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
import lookup.repository.implementation.CategoryItemRepositoryImplementation;
import lookup.repository.implementation.CategoryRepositoryImplementation;
import lookup.util.TransformUtil;

@Path("/categoryitem/")
public class CategoryItemJSON {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCategoryItems(){
		List<CategoryItem> list = new CategoryItemRepositoryImplementation().getAllCategoryItems();
		JSONArray jsonArray = TransformUtil.convertCategoryItemList(list);
		return Response.ok(jsonArray.toString()).build();
	}
	
	@Path("/category/{category}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryItemByCategory(@PathParam("category") int categoryID){
		List<CategoryItem> list = new CategoryItemRepositoryImplementation().getAllCategoryItems();
		JSONArray jsonArray = TransformUtil.convertCategoryItemList(list);
		return Response.ok(jsonArray.toString()).build();
	}
	
	@Path("/name/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryItemByName(@PathParam("name") String categoryItemName){
		List<CategoryItem> list = new CategoryItemRepositoryImplementation().getCategoryItemByName(categoryItemName);
		JSONArray jsonArray = null;
		if(list!=null && list.size()!=0)
			jsonArray = TransformUtil.convertCategoryItemList(list);
		else
			jsonArray = new JSONArray();
		return Response.ok(jsonArray.toString()).build();
	}
	
	@Path("/userid/{userid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryItemByUser(@PathParam("userid") int userID){
		List<CategoryItem> list = new CategoryItemRepositoryImplementation().getCategoryItemByUser(userID);
		JSONArray jsonArray = TransformUtil.convertCategoryItemList(list);
		return Response.ok(jsonArray.toString()).build();
	}
	
	@Path("/id/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryItemByID(@PathParam("id") int categoryItemID){
		CategoryItem categoryItem= new CategoryItemRepositoryImplementation().getCategoryItemByID(categoryItemID);
			List<CategoryItem> list = new ArrayList<CategoryItem>();
			list.add(categoryItem);
		JSONArray jsonArray = TransformUtil.convertCategoryItemList(list);
		return Response.ok(jsonArray.toString()).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCategoryItem(String incomingData) throws JSONException{
		JSONObject jsonObject = new JSONObject(incomingData);
		JSONObject jsObj = new JSONObject();
		JSONArray jsArray = new JSONArray();
		CategoryItem categoryItem = new CategoryItem();
		
		List<Category> list = null;
		CategoryRepositoryImplementation c = new CategoryRepositoryImplementation();
		CategoryItemRepositoryImplementation ci = new CategoryItemRepositoryImplementation();
		
		try{
			
			list = c.getCategoryByName(jsonObject.getString("categoryId"));
			
			categoryItem.setCategoryID(list.get(0).getId());
			categoryItem.setName(jsonObject.getString("name"));
			categoryItem.setUserID(1);
			
			int result = ci.saveCategoryItem(categoryItem);
			if(result!=-1){
				jsObj.put("HTTP_CODE", "200");
				jsObj.put("MSG", "Category Item added successfully");
			}else{
				jsObj.put("HTTP_CODE", "200");
				jsObj.put("MSG", "Category Item has not been added");
			}
		}catch(Exception e){
			
		}
		
		
		return Response.ok(jsArray.put(jsObj).toString()).build();
		
	}
	
}

package lookup.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lookup.domain.Category;
import lookup.repository.implementation.CategoryRepositoryImplementation;
import lookup.util.TransformUtil;

import org.codehaus.jettison.json.JSONArray;

@Path("/category/")
public class CategoryJSON {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCategories(){
		List<Category> list = new CategoryRepositoryImplementation().getAllCategories(); 
		JSONArray jsonArray = TransformUtil.convertCategoryList(list);
		return Response.ok(jsonArray.toString()).build();
	}
	
	@Path("/name/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryByName(@PathParam("name") String name){
		List<Category> list = new CategoryRepositoryImplementation().getCategoryByName(name);
		JSONArray jsonArray = null;
		if(list!=null && list.size()!=0)
			jsonArray = TransformUtil.convertCategoryList(list);
		else
			jsonArray = new JSONArray();
		return Response.ok(jsonArray.toString()).build();
	}
	
	@Path("/userid/{userid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryByUserID(@PathParam("userid") int userid){
		List<Category> list = new CategoryRepositoryImplementation().getCategoryByUserID(userid);
		JSONArray jsonArray = TransformUtil.convertCategoryList(list);
		return Response.ok(jsonArray.toString()).build();
	}
	
	@Path("/id/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategorybyID(@PathParam("id") int id){
		Category category = new CategoryRepositoryImplementation().getCategorybyID(id);
			List<Category> list = new ArrayList<Category>();
			list.add(category);
		JSONArray jsonArray = TransformUtil.convertCategoryList(list);
		return Response.ok(jsonArray.toString()).build();
	}

}

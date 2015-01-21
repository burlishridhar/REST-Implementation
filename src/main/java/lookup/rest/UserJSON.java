package lookup.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lookup.domain.Category;
import lookup.domain.CategoryItem;
import lookup.domain.User;
import lookup.repository.implementation.CategoryItemRepositoryImplementation;
import lookup.repository.implementation.CategoryRepositoryImplementation;
import lookup.repository.implementation.UserRepositoryImplementation;
import lookup.util.TransformUtil;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/user/")
public class UserJSON {

	@Path("/loginid/{loginid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByLoginId(@PathParam("loginid") String loginid){
		List<User> list = new UserRepositoryImplementation().getUserByLoginID(loginid);
		JSONArray jsonArray = null;
		if(list!=null && list.size()!=0)
			jsonArray = TransformUtil.convertUserList(list);
		else
			jsonArray = new JSONArray();
		return Response.ok(jsonArray.toString()).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response autheticateUser(String incomingData) throws JSONException{
		JSONObject jsonObject = new JSONObject(incomingData);
		JSONObject jsObj = new JSONObject();
		JSONArray jsArray = new JSONArray();

		UserRepositoryImplementation u = new UserRepositoryImplementation();
		User user = null;
		try{
				user = u.autheticateUser(jsonObject.getString("loginid"), jsonObject.getString("password"));
				if(user!=null){
					jsObj.put("HTTP_CODE", "200");
					jsObj.put("MSG", "true");
				}else{
					jsObj.put("HTTP_CODE", "200");
					jsObj.put("MSG", "false");
				}
				
		}catch(Exception e){
			
		}
		
		
		return Response.ok(jsArray.put(jsObj).toString()).build();
		
	}
}

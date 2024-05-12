package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.Map;

//import java.util.HashMap;

import api.payload.User;
import api.payload.*;
import io.restassured.response.Response;

public class NewUserEndPoints {
	
	public static Response createUser(User payLoad,String contentType,Headers allHeaders)
	{
		Response response=given()
			.contentType(contentType)
			.body(payLoad)
			.headers((Map<String, String>) allHeaders)
			
		.when()
			.post(Routes.post_url);
		return response;
	}

	public static Response getUser(String userName,String queryParamName,String queryParam)
	{
		Response response=given()
				.pathParam("username", userName)
				.queryParam(queryParamName, queryParam)

			.when()
				.get(Routes.get_url);
			return response;
	}
	
	public static Response updateUser(User payLoad,String contentType,String headerType,String headerValue,String userName)
	{
		Response response=given()
			.contentType(contentType)
			.body(payLoad)
			.header(headerType, headerValue)
			.pathParam("username",userName)
		.when()
			.put(Routes.update_url);
		return response;
	}

	public static Response deleteUser(String userName,String queryParamName,String queryParam)
	{
		Response response=given()
				.pathParam("username", userName)
				.queryParam(queryParamName, queryParam)
			.when()
				.delete(Routes.delete_url);
			return response;
	}
}


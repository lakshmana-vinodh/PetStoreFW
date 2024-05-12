package api.test;

import org.testng.annotations.*;
//import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import junit.framework.Assert;

public class UserTests {
	
	Faker faker;
	User userPayLoad;
	String contentType=null;
	String headers=null;
	String authorisationToken=null;
	String headerType=null;
	String headerValue=null;
	String queryParam="";
	String queryParamName="";
	
	@BeforeClass
	public void setupData()
	{
		faker=new Faker();
		userPayLoad=new User();
		
		userPayLoad.setId((int)faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password(6, 8));
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@BeforeClass
	public void intializeVariables()
	{
		contentType="application/json";
		headerType="accept";
		headerValue="application/json";
		queryParam="";
		queryParamName="";
	}
	
	@Test(priority=1)
	public void testCreateUser()
	{

		Response response=UserEndPoints.createUser(userPayLoad,contentType,headerValue,headerType);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=2)
	public void testgetUser()
	{
		String UserName=this.userPayLoad.getUsername();
		Response response=UserEndPoints.getUser(UserName,queryParamName,queryParam);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		Response response=UserEndPoints.updateUser(userPayLoad,contentType,this.userPayLoad.getUsername(),headerValue,headerType);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	

	@Test(priority=4)
	public void testDeleteUser()
	{
		String Username=this.userPayLoad.getUsername();
		Response response=UserEndPoints.deleteUser(Username,queryParamName,queryParam);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	
	

}

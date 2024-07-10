package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
//import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userpayload;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		userpayload = new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUserName(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPaddword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		Response response=UserEndPoints.createUser(userpayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Assert.assertEquals(response.getContentType(), "ContentType.JSON");
	}
/*	
	@Test(priority=2)
	public void testGetUserByName()
	{
		Response response=UserEndPoints.readUser(this.userpayload.getUserName());
		response.then().log().all()
		.assertThat().statusCode(200);
		//Assert.assertEquals(response.getStatusCode(), 200);
	
	}
	
	*/
	@Test(priority=2)
	public void testUpdateUserByName()
	{
		//update data using payload
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints.updateUser(this.userpayload.getUserName(), userpayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	/*	//checking data after update
		Response responseafterupdate=UserEndPoints.readUser(this.userpayload.getUserName());
		responseafterupdate.then().log().all()
		.assertThat().statusCode(200);
		
		*/
		
	}
	
}

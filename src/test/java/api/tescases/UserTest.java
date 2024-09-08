package api.tescases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.github.javafaker.Faker;


import api.endpoints.userEndPoints;
import api.payloads.user;
import io.restassured.response.Response;

public class UserTest {
	
	public static Logger logger;
	//method to generate test data
	Faker fakeData;
	user userPayload;
	@BeforeClass
	public void generateTestData()
	{
		fakeData = new Faker();
		userPayload = new user();
		userPayload.setId(fakeData.idNumber().hashCode());
		userPayload.setUsername(fakeData.name().username());
		userPayload.setFirstName(fakeData.name().firstName());
		userPayload.setLastName(fakeData.name().lastName());
		userPayload.setEmail(fakeData.internet().safeEmailAddress());
		userPayload.setPassword(fakeData.internet().password(5,10));
		userPayload.setPhone(fakeData.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger("RestAssuredTraining");
		
	}
	
	//test Method
	//create user
	@Test(priority = 1)
	public void testCreateUser()
	{
		Response response = userEndPoints.createUser(userPayload);
		
		
		// logging the response
		
		response.then().log().all();
		
		//Validation 
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("create user executed");
	}
	@Test(priority = 2)
	public void testGetUser()
	{
		Response response = userEndPoints.GetUser(this.userPayload.getUsername());
		
		
		// logging the response
		
		response.then().log().all();
		
		//Validation 
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("Get user executed");
		
	}
	
	@Test (priority = 3)
	public void testUpdateUser()
	{
		userPayload.setFirstName(fakeData.name().firstName());
		Response response = userEndPoints.UpdateUser(this.userPayload.getUsername(),userPayload);
		
		
		// logging the response
		
		response.then().log().all();
		
		//Validation 
			
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		// check if the first name is updated or not
		System.out.println("Post update");
		Response responsePostUpdate = userEndPoints.GetUser(this.userPayload.getUsername());
		responsePostUpdate.then().log().all();
		Assert.assertNotEquals(response, responsePostUpdate);
		
		logger.info("user updated");
		
	}
	
}

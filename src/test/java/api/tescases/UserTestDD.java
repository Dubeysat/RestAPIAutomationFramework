package api.tescases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndPoints;
import api.payloads.user;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTestDD {

	@Test(priority = 1, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void testCreateUser(String userId, String UserName,  String fname, String lname,  String email, String pwd, String phone)
	{
		user userPayload = new user();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		Response response = userEndPoints.createUser(userPayload);
		
		
		// logging the response
		
		response.then().log().all();
		
		//Validation 
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
}

package Practice;

import  org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetRequest {
	String mainURL="http://reqres.in/";
	@Test
	public  void getUser() {
		String endPont= "api/users?page=2";
		given().when().get(mainURL+endPont).then().statusCode(200).body("page", equalTo(2)).log().all();
	}

}

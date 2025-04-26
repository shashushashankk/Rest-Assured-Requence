package Practice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import  org.testng.annotations.Test;

public class GetRequest {
	Logger log=LogManager.getLogger(this.getClass());
	String mainURL="http://reqres.in/";
	@Test
	public  void getUser() {
		log.info("Get user execution started",true);
		String endPont= "api/users?page=2";
		given().when().get(mainURL+endPont).then().statusCode(200).body("page", equalTo(2)).log().all();
	}

}

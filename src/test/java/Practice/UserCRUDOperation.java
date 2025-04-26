package Practice;

import io.restassured.path.json.JsonPath;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class UserCRUDOperation {
    String url = "https://reqres.in";
    String endPoint;
    int id;

    @Test(priority = 0)
    public void createUser() {
        endPoint = "/api/users";
        LinkedHashMap body = new LinkedHashMap();
        body.put("name", "Shashank");
        body.put("job", "QA Engineer");

        id = given().body(body).contentType("application/json")
                .when().post(url + endPoint).jsonPath().getInt("id");
//         response.getInt("id");
    }

    @Test(priority = 1, dependsOnMethods = "createUser")
    public void getUser() {
       endPoint = "/api/users/"+id;
        Reporter.log(endPoint+" endpoit",true);
       when().get("https://reqres.in"+endPoint).then().statusCode(200).body("name",equalTo("Shashank"));
    }

    @Test(priority = 2)
    public void updatePutUser() {
        endPoint = "/api/users/"+id;
        Reporter.log(endPoint+" endpoit",true);
        LinkedHashMap body = new LinkedHashMap();
        body.put("name", "Shashank");
        body.put("job", "QA Automation Engineer");
        given().body(body).contentType("application/json")
                .when().put(url+endPoint)
                .then().statusCode(200).body("job",equalTo("QA Automation Engineer")).log().all();
    }

    public void updatePatchUser() {

    }

    @Test(priority = 3)
    public void deleteUser() {
        endPoint = "/api/users/"+id;
        when().delete(url+endPoint)
                .then().statusCode(204).log().all();

    }
}

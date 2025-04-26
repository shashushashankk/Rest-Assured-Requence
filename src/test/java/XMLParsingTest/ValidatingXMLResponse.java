package XMLParsingTest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ValidatingXMLResponse {
    String mainURL = "http://localhost:3000/";
    void simpleValidation(){
        when().get(mainURL+"company")
                .then().statusCode(200)
                .time(lessThan(1000L))
                .body("name",equalTo("Shashank"));
    }
}

package Practice;

import static io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;


public class LogsTest {
    @Test
    void printLog(){
        when().get("https://www.google.com").then().log().all();
        when().get("https://www.google.com").then().log().cookies();
        when().get("https://www.google.com").then().log().headers();
        when().get("https://www.google.com").then().log().body();

    }
}

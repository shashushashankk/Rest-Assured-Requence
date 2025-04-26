package Practice;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HeadersValidation {
    @Test
    void getHeader() {
        //validate header
        when().get("https://www.google.com").then().log().headers();

        // get single header
        when().get("https://www.google.com").then().log().headers().contentType("text/html");

        // get all header
        Response response = when().get("https://www.google.com");
        System.out.println("content type is " + response.header("Content-Type"));
        Headers headers = response.getHeaders();
        for (Header header : headers) {
            String headerKey = header.getName();
            String headerValue = header.getValue();
            System.out.println("header key is " + headerKey + " header value is " + headerValue);
        }
    }
}
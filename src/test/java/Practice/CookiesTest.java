package Practice;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class CookiesTest {
//    @Test
    void getCoocki() throws MalformedURLException {
        String urlString = "https://www.google.com";
        URL url = new URL(urlString);
        when().get(url).then().log().cookies();
    }
    @Test
    void validateCookies() throws MalformedURLException {
        String urlString = "https://www.google.com";
        URL url = new URL(urlString);
        when().get(url).then().cookie("AEC").cookie("NID");
    }

    @Test
    void getCookies() throws MalformedURLException {
        String urlString = "https://www.google.com";
        URL url = new URL(urlString);
        Response response=when().get(url);
        String cookie=response.cookie("AEC");
        System.out.println(response.detailedCookie("AEC"));
        System.out.println("cookie AEC value is "+cookie);
        Map cookies= response.cookies();
        Set<String> cookiesKeys=cookies.keySet();
        for(String cookiesKey : cookiesKeys){
            System.out.println("cookie "+cookiesKey+" value is "+cookie);
        }



    }
}

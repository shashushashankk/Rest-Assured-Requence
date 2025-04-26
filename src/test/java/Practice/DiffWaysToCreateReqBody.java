package Practice;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;

import static io.restassured.RestAssured.*;

/*1.	Creating post body:
        a.	HashMap
        b.	using org.json
        c.	using PO-JO (Plain Old Java Object)
        d.	using an external JSON file
*/

public class DiffWaysToCreateReqBody {
    //    @Test
    void usingHashMap() {
        /*"id":2,
		"name" : "Sachin",
		"phoneNumber":5698745255,
		"cource": "B.E",
		"subject":["Java", "Springboot", "Resrt Assure"]*/
        String courses[] = {"Python", "Postman"};
        LinkedHashMap reqBody = new LinkedHashMap();
        reqBody.put("id", "3");
        reqBody.put("name", "Manju");
        reqBody.put("phoneNumber", 49658745);
        reqBody.put("cource", "B.E");
        reqBody.put("subject", courses);
        given().contentType("application/json").body(reqBody)
                .when().post("http://localhost:3000/Student Details")
                .then().statusCode(201).log().all();

    }

    void usingJson() {
        JSONArray courses = new JSONArray();
//        String [] courses = {"Ptfjd","jbdc"};
        courses.put("Python");
        courses.put("Postman");

        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "3");
        reqBody.put("name", "Manju");
        reqBody.put("phoneNumber", 49658745);
        reqBody.put("course", "B.E");  // Corrected "cource" to "course"
        reqBody.put("subject", courses);

        given().contentType("application/json").body(reqBody.toString())
                .when().post("http://localhost:3000/Student Details")
                .then().statusCode(201).log().all();
    }

    @Test(priority = 0)
    void usingPOJO() {
        String[] courses = {"Python", "Postman"};

        StudentDetails reqBody = new StudentDetails();
        reqBody.setId("3");
        reqBody.setName("Manju");
        reqBody.setPhoneNumber(49658745);
        reqBody.setCourse("B.E");  // Corrected "cource" to "course"
        reqBody.setSubject(courses);

        given().contentType("application/json").body(reqBody)
                .when().post("http://localhost:3000/Student Details")
                .then().statusCode(201).log().all();
    }

    //    @Test(priority = 0)
    void usingJSONFile() throws FileNotFoundException {
        FileReader fr = new FileReader(new File("C:\\Users\\ER\\Desktop\\Student Detail.json"));
        JSONTokener jsonTokener = new JSONTokener(fr);
        JSONObject reqBody = new JSONObject(jsonTokener);
        given().contentType("application/json").body(reqBody.toString())
                .when().post("http://localhost:3000/Student Details")
                .then().statusCode(201).log().all();
    }

    @Test(priority = 1)
    void getStudentDetails() {
        when().get("http://localhost:3000/Student Details")
                .then().statusCode(200).log().all();
    }

    @Test(priority = 2)
    void deleteStudentDetails() {
        when().delete("http://localhost:3000/Student Details/3")
                .then().statusCode(200);
    }
}

class StudentDetails {
    static String id;
    static String name;
    static long phoneNumber;
    static String course;
    static String[] subject;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getCourse() {
        return course;
    }

    public String[] getSubject() {
        return subject;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setSubject(String[] subject) {
        this.subject = subject;
    }
}

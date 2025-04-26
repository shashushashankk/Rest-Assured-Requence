package JSONParsing;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponseData {
    String mainUrl = "http://localhost:3000";
//    @Test (priority = 0)
    void simpleJSONValidator(){
        // Validation directly in the request using then()
        given().contentType("application/json")
                .when().get(mainUrl+"/company")
                .then().contentType("application/json")
                .statusCode(200)
                .header("Connection","keep-alive")
                .body("name",equalTo("Tech Solutions Inc."))
                .body("location.branches[0].city",equalTo("New York"));
    }
//    @Test
            void sampleTesst(){
        Response response = given().contentType(ContentType.JSON)
                .when().get(mainUrl+"/company");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.contentType().equals("application/json"));
//        Assert.assertTrue(response.tim);
        System.out.println(response.time());
        Assert.assertTrue(response.time()<10000);
        Assert.assertTrue(response.jsonPath().get("location.city").equals("San Francisco"));

        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        JSONArray employeesDetails = jsonObject.getJSONArray("employees");
    }
    @Test
    void complexJSONValidator(){
        Map <String, EmployeeDetails> map = new HashMap();
        Response response = given().contentType(ContentType.JSON)
                .when().get(mainUrl+"/company");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.contentType().equals("application/json"));
//        Assert.assertTrue(response.tim);
        System.out.println(response.time());
        Assert.assertTrue(response.time()<10000);
        Assert.assertTrue(response.jsonPath().get("location.city").equals("San Francisco"));

        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        JSONArray employeesDetails = jsonObject.getJSONArray("employees");
        String empName="";
        String empRole="";
        int empExperience=0;
        int empId = 0;
        String[] empSkills=null;

        for(int i=0; i<employeesDetails.length(); i++){
            System.out.println(employeesDetails.length());
            JSONObject employeDetails = employeesDetails.getJSONObject(i);
            empId = employeDetails.getInt("id");
            empName = employeDetails.get("name").toString();
            empRole = employeDetails.get("role").toString();
            empExperience = employeDetails.getInt("experience");
            JSONArray empSkillsJSON = employeDetails.getJSONArray("skills");
            empSkills = new String[empSkillsJSON.length()];
            for (int j = 0; j<empSkillsJSON.length(); j++){
                empSkills[j]=empSkillsJSON.get(j).toString();
            }
            JSONArray projectDetails = employeDetails.getJSONArray("projects");
            for(int j =0; j< projectDetails.length(); j++){
                JSONObject projectDetail = projectDetails.getJSONObject(j);
                String projectName = projectDetail.get("name").toString();
                String projectStaus = projectDetail.get("status").toString();
                String deadLine = projectDetail.get("deadline").toString();
                String projectBugdet = projectDetail.getJSONObject("details").get("budget").toString();
                JSONArray teamMembersJSON = projectDetail.getJSONObject("details").getJSONArray("team_members");
                String [] teamMembers = new String[teamMembersJSON.length()];
                for(int s = 0; s<teamMembersJSON.length(); s++){
                    teamMembers[s]=teamMembersJSON.get(s).toString();
                }
            }
            EmployeeDetails employeeDetails = new EmployeeDetails(empName, empId, empRole, empExperience, empSkills);
            map.put(empName,employeeDetails);

        }
        Set<String> names=map.keySet();
        for(String name : names){
            System.out.println(map.get(name));
        }
    }

}

class EmployeeDetails{
    String empName;
    int empId;
    String empRole;
    int empExperience;
    String [] empSkills;

    public EmployeeDetails(String empName, int empId, String empRole, int empExperience, String[] empSkills) {
        this.empName = empName;
        this.empId = empId;
        this.empRole = empRole;
        this.empExperience = empExperience;
        this.empSkills = empSkills;
    }

    public String toString(){
        return "Employee name is "+empName+"\nID "+empId+"\nRole "+empRole+"\nExperience is "+empExperience+"\nSkills"+ Arrays.toString(empSkills);
    }
}
class ProjectDetails{
    String projectName;
    String projectStatus;
    String prjectDeadline;
    long projectBudget;
    String teamMember;

    public ProjectDetails(String projectName, String projectStatus, String prjectDeadline, long projectBudget, String teamMember) {
        this.projectName = projectName;
        this.projectStatus = projectStatus;
        this.prjectDeadline = prjectDeadline;
        this.projectBudget = projectBudget;
        this.teamMember = teamMember;
    }
}
package day2;

import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/*
1) using HashMap
2) using Org.json
3) using POJO (Plain Old Java Object)
4) using external json file
 */

public class DifferentWaysToCreatePostRequestBody {

    @Test(priority = 1)
    public void testPostUsingHashMap(){
        HashMap data  = new HashMap();
        data.put("name", "Alex");
        data.put("phone", "087377373737");
        data.put("location", "France");

        String courseArray[] = {"C", "Java"};
        data.put("courses", courseArray);

        given()
            .contentType("application/json")
            .body(data)
        .when()
            .post("http://localhost:3000/students")
        .then()
            .statusCode(201)
            .body("name", equalTo("Alex"))
            .body("phone", equalTo("087377373737"))
            .body("location", equalTo("France"))
            .body("course[0]", equalTo("C"))
            .body("course[1]", equalTo("C++"))
            .header("Content-Type","application/json; charset=utf-8")
            .log().all();
    }

    @Test(priority = 2)
    void testDelete(){
        given()
        .when()
            .delete("http://localhost:3000/students/4")
        .then()
            .statusCode(200);
    }

}

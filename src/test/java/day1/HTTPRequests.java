package day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;



public class HTTPRequests {

    int id;
    @Test(priority = 1)
    void  getUsers(){
        given()
        .when()
            .get("https://reqres.in/api/users?page=2")
        .then()
            .statusCode(200)
            .body("page", equalTo(2))
            .log().all();
    }

    @Test(priority = 2)
    void  createUser(){
        HashMap<String,String> data = new HashMap();
        data.put("name", "alex");
        data.put("job", "QA Engineer");

        id = given()
                .contentType("application/json")
                .body(data)
            .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");

    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    void updateUser(){
        HashMap<String,String> data = new HashMap();
        data.put("name", "alex");
        data.put("job", "Software Engineer");

        given()
            .contentType("application/json")
            .body(data)
        .when()
            .put("https://reqres.in/api/user/"+id)
        .then()
            .statusCode(200)
            .log().all();
    }

    @Test
    void deleteUser(){
        given()
        .when()
            .delete("https://reqres.in/api/users/"+id)
        .then()
            .statusCode(204)
            .log().all();
    }

}

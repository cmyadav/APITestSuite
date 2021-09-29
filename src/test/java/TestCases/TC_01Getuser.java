package TestCases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class TC_01Getuser {




    @Test(priority = 1)
    public void testAllUser() {

        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);

    }

    @Test(priority = 2)
    public void testCreateUser() {

        HashMap data = new HashMap();
        data.put("name","morpheus");
        data.put( "job", "leader");


        Response res=

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().body()
                .extract().response();

        String json =res.asString();

        Assert.assertEquals(json.contains("createdAt"),true);


    }


    @Test(priority = 3)
    public void testGetById(){

        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log().body()

                .body("data.id",equalTo(2))
                .body("data.first_name",equalTo("Janet"));

    }


        @Test(priority = 4)
    public void testUpdateUser() {

        HashMap data1 = new HashMap();
        data1.put("name","CMyadav");
        data1.put( "job", "leader");




                given()
                        .contentType("application/json")
                        .body(data1)
                        .when()
                        .put("https://reqres.in/api/users/2")
                        .then()
                        .statusCode(200)
                        .log().body()

                        .body("name",equalTo("CMyadav"));





    }
    @Test(priority = 5)
    public void testDeleteUser(){

        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)
                .log().body();
    }
}

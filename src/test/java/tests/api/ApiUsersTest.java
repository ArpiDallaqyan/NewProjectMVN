package tests.api;

import api.models.User;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ApiUsersTest {

    private static final String TOKEN = "0c8a1867c6aca2428ad0efdc0b07a4d44aca7d95547b96898dfd3bd956b71b24";
    private static final String BASE_URL = "https://gorest.co.in/public/v1";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + TOKEN)
                .build();
    }

    @Test
    public void testUserLifecycle() {
        Response initialResponse = RestAssured.given()
                .get("/users");

        Assert.assertEquals(initialResponse.getStatusCode(), 200);
        int initialCount = initialResponse.jsonPath().getInt("meta.pagination.total");
        System.out.println("Initial users count: " + initialCount);

        User newUser = new User();
        newUser.setName("Lucy");
        newUser.setEmail("Lucy125_" + System.currentTimeMillis() + "@gmail.com");
        newUser.setGender("female");
        newUser.setStatus("active");

        Response postResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .post("/users");
        System.out.println("POST status: " + postResponse.getStatusCode());
        System.out.println("POST response:");
        System.out.println(postResponse.asPrettyString());

        Assert.assertEquals(postResponse.getStatusCode(), 201);

        User createdUser = postResponse.jsonPath().getObject("data", User.class);
        int createdUserId = createdUser.getId();
        System.out.println("Created user ID: " + createdUserId);
        Response response = RestAssured.given().get("/users/" + createdUserId );
        System.out.println(response.asPrettyString());

        Response afterAddResponse = RestAssured.given()
                .get("/users");

        Assert.assertEquals(afterAddResponse.getStatusCode(), 200);
        int countAfterAdd = afterAddResponse.jsonPath().getInt("meta.pagination.total");
        System.out.println("Users count after adding: " + countAfterAdd);
        Assert.assertEquals(countAfterAdd, initialCount + 1, "The number of users has not changed.");
        Response deleteResponse = RestAssured.given()
                .delete("/users/" + createdUserId);

        Assert.assertEquals(deleteResponse.getStatusCode(), 204);

        Response afterDeleteResponse = RestAssured.given()
                .get("/users");

        Assert.assertEquals(afterDeleteResponse.getStatusCode(), 200);
        int countAfterDelete = afterDeleteResponse.jsonPath().getInt("meta.pagination.total");
        System.out.println("Users count after deleting: " + countAfterDelete);
        Assert.assertEquals(countAfterDelete, countAfterAdd - 1, "The number of users has not changed.");

        Response getDeletedResponse = RestAssured.given()
                .get("/users/" + createdUserId);

        Assert.assertEquals(getDeletedResponse.getStatusCode(), 404);
        System.out.println("Deleted user test passed with 404 status code successfully!");
    }
}
package tests.api;
import api.ConfigReader;
import api.models.User;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ApiUsersTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.getBaseUrl();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBasePath("/v1")
                .addHeader("Authorization", "Bearer " + ConfigReader.getToken())
                .build();
    }

    @Test
    public void testUserLifecycle() {
        Response initialResponse = RestAssured
                .get("/users");

        Assert.assertEquals(initialResponse.getStatusCode(), 200);

        int initialCount = initialResponse.jsonPath().getInt("meta.pagination.total");
        System.out.println("Initial users count: " + initialCount);

        User newUser = new User();
        newUser.setName("Lucy");
        newUser.setEmail("Lucy125_" + System.currentTimeMillis() + "@gmail.com");
        newUser.setGender("female");
        newUser.setStatus("active");

        User postResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .post("/users").jsonPath().getObject("data", User.class);

        Long createdUserId = postResponse.getId();
        System.out.println("Created user ID: " + createdUserId);
        Response response = RestAssured.given().get("/users/" + createdUserId );
        System.out.println(response.then().log().body().statusCode(200));

        Response afterAddResponse = RestAssured
                .given()
                .get("/users");

        Assert.assertEquals(afterAddResponse.getStatusCode(), 200);

        int countAfterAdd = afterAddResponse.jsonPath().getInt("meta.pagination.total");
        System.out.println("Users count after adding: " + countAfterAdd);

        Assert.assertEquals(countAfterAdd, initialCount + 1, "The number of users has not changed.");

        Response deleteResponse = RestAssured
                .given()
                .delete("/users/" + createdUserId);

        Assert.assertEquals(deleteResponse.getStatusCode(), 204);

        Response afterDeleteResponse = RestAssured
                .given()
                .get("/users");

        Assert.assertEquals(afterDeleteResponse.getStatusCode(), 200);

        int countAfterDelete = afterDeleteResponse.jsonPath().getInt("meta.pagination.total");
        System.out.println("Users count after deleting: " + countAfterDelete);

        Assert.assertEquals(countAfterDelete, countAfterAdd - 1, "The number of users has not changed.");

        Response getDeletedResponse = RestAssured
                .given()
                .get("/users/" + createdUserId);

        Assert.assertEquals(getDeletedResponse.getStatusCode(), 404);

        System.out.println("Deleted user test passed with 404 status code successfully!");
    }
}
package tests.api;

import api.ConfigReader;
import api.models.Post;
import api.models.User;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ApiPostsTest {

    String unique = String.valueOf(System.currentTimeMillis());

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.getBaseUrl();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBasePath("/v1")
                .addHeader("Authorization", "Bearer " + ConfigReader.getToken())
                .build();
    }

    @Test
    public void testPostLifecycle() {
        User user1 = new User("Anna", "anna" + unique + "@gmail.com", "female", "active");
        User createdUser1 = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user1)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().jsonPath().getObject("data", User.class);

        Long userId1 = createdUser1.getId();

        Post post1 = new Post("User1 Post1", "First user's first text", userId1);
        Post post2 = new Post("User1 Post2", "First user's second text", userId1);

        Post createPost1 = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(post1)
                .log().body()
                .when()
                .post("/posts")
                .then()
                .log()
                .body()
                .statusCode(201)
                .extract().jsonPath().getObject("data", Post.class);

        Post createPost2 = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(post2)
                .when()
                .post("/posts")
                .then()
                .log()
                .body()
                .statusCode(201)
                .extract().jsonPath().getObject("data", Post.class);

        Post post1Text = RestAssured
                .given()
                .when()
                .get("/posts/" + createPost1.getId())
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract().jsonPath().getObject("data", Post.class);

        String post1TextBody = post1Text.getBody();
        System.out.println(post1TextBody);

        Post post2Text = RestAssured
                .given()
                .when()
                .get("/posts/" + createPost2.getId())
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract().jsonPath().getObject("data", Post.class);

        String post2TextBody = post2Text.getBody();
        System.out.println(post2TextBody);

        Assert.assertTrue(post1TextBody.contains(post1.getBody()) && (post2TextBody.contains(post2.getBody())),
                "Mismatch in post's text bodies");

        User user2 = new User("Mark", "mark" + unique + "@gmail.com", "male", "active");
        User createdUser2 = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user2)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().jsonPath().getObject("data", User.class);

        Long userId2 = createdUser2.getId();

        Post user2Post1 = new Post("User2 Post1", "Second user's first post", userId2);
        Post user2Post2 = new Post("User2 Post2", "Second user's second post", userId2);

        Post createdPost1 = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user2Post1)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract().jsonPath().getObject("data", Post.class);

        Long postIdForSecondUser = createdPost1.getId();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user2Post2)
                .when()
                .post("/posts")
                .then()
                .statusCode(201);

        RestAssured
                .given()
                .when()
                .delete("/posts/" + postIdForSecondUser)
                .then()
                .statusCode(204);

        RestAssured
                .given()
                .when()
                .delete("/users/" + userId1)
                .then()
                .statusCode(204);

        RestAssured
                .given()
                .when()
                .get("/users/" + userId1 + "/posts")
                .then()
                .statusCode(404);

        RestAssured
                .given()
                .when()
                .delete("/users/" + userId2)
                .then()
                .statusCode(204);

        RestAssured
                .given()
                .when()
                .get("/posts/" + userId2)
                .then()
                .statusCode(404);
    }
}
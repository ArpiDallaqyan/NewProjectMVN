package api.models;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserController {

    public User createUser(User user) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().jsonPath().getObject("data", User.class);
    }

    public void deleteUser(Long userId) {
        RestAssured
                .given()
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(204);
    }

    public Response getUserPosts(Long userId) {
        return RestAssured
                .given()
                .when()
                .get("/users/" + userId + "/posts");
    }
}
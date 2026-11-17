package api.models;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostController {

    public Post createPost(Post post) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(post)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract().jsonPath().getObject("data", Post.class);
    }

    public void createPostWithoutExtract(Post post) {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(post)
                .when()
                .post("/posts")
                .then()
                .statusCode(201);
    }

    public Post getPostById(Long postId) {
        return RestAssured
                .given()
                .when()
                .get("/posts/" + postId)
                .then()
                .statusCode(200)
                .extract().jsonPath().getObject("data", Post.class);
    }

    public Response getPostRaw(Long postId) {
        return RestAssured
                .given()
                .when()
                .get("/posts/" + postId);
    }

    public void deletePost(Long postId) {
        RestAssured
                .given()
                .when()
                .delete("/posts/" + postId)
                .then()
                .statusCode(204);
    }
}
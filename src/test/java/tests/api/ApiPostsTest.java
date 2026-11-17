package tests.api;

import api.ConfigReader;
import api.models.PostController;
import api.models.UserController;
import api.models.Post;
import api.models.User;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ApiPostsTest {

    String unique = String.valueOf(System.currentTimeMillis());
    private UserController userController;
    private PostController postController;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.getBaseUrl();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBasePath("/v1")
                .addHeader("Authorization", "Bearer " + ConfigReader.getToken())
                .build();

        userController = new UserController();
        postController = new PostController();
    }

    @Test
    public void testPostLifecycle() {
        User user1 = new User("Anna", "anna" + unique + "@gmail.com", "female", "active");
        User createdUser1 = userController.createUser(user1);
        Long userId1 = createdUser1.getId();

        Post post1 = new Post("User1 Post1", "First user's first text", userId1);
        Post post2 = new Post("User1 Post2", "First user's second text", userId1);

        Post createdPost1 = postController.createPost(post1);
        Post createdPost2 = postController.createPost(post2);

        Post fetchedPost1 = postController.getPostById(createdPost1.getId());
        String post1TextBody = fetchedPost1.getBody();

        Post fetchedPost2 = postController.getPostById(createdPost2.getId());
        String post2TextBody = fetchedPost2.getBody();

        Assert.assertTrue(post1TextBody.contains(post1.getBody()) && post2TextBody.contains(post2.getBody()),
                "Mismatch in post's text bodies");

        User user2 = new User("Mark", "mark" + unique + "@gmail.com", "male", "active");
        User createdUser2 = userController.createUser(user2);
        Long userId2 = createdUser2.getId();

        Post user2Post1 = new Post("User2 Post1", "Second user's first post", userId2);
        Post user2Post2 = new Post("User2 Post2", "Second user's second post", userId2);

        Post user2CreatedPost1 = postController.createPost(user2Post1);
        Long postIdForSecondUser = user2CreatedPost1.getId();

        postController.createPostWithoutExtract(user2Post2);

        postController.deletePost(postIdForSecondUser);
        userController.deleteUser(userId1);

        userController.getUserPosts(userId1)
                .then()
                .statusCode(404);

        userController.deleteUser(userId2);

        postController.getPostRaw(userId2)
                .then()
                .statusCode(404);
    }
}
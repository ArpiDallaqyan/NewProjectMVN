
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class ApiTest {
    static void main(String[] args) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        String requestBody = """
        {
        "id": 12345,
        "name": "doggie",
        "status": "available"
        }
       """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/pet/12345")
                .then()
                .statusCode(200);
    }
}


package petstore.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import petstore.model.User;

public class UserstoreClient {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    public static Response createUser(User user) {
        return RestAssured.given()
                .contentType("application/json")
                .body(user)
                .post(BASE_URL + "/user");
    }

    public static Response getUserByUsername(String username) {
        return RestAssured.given()
                .get(BASE_URL + "/user/" + username);
    }

    public static Response updateUser(String username, User user) {
        return RestAssured.given()
                .contentType("application/json")
                .body(user)
                .put(BASE_URL + "/user/" + username);
    }

    public static Response deleteUser(String username) {
        return RestAssured.given()
                .delete(BASE_URL + "/user/" + username);
    }
}



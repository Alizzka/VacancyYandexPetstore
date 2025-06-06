package petstore.utils;

import io.restassured.response.Response;
import petstore.model.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserValidator {

    public static void validateUserCreated(Response response) {
        response.then().statusCode(200);
        assertThat(response.jsonPath().getString("message"), not(emptyOrNullString()));
    }

    public static void validateUserFetched(Response response, User expectedUser) {
        response.then().statusCode(200);
        User actualUser = response.as(User.class);
        assertThat(actualUser.getUsername(), equalTo(expectedUser.getUsername()));
        assertThat(actualUser.getFirstName(), equalTo(expectedUser.getFirstName()));
        assertThat(actualUser.getLastName(), equalTo(expectedUser.getLastName()));
        assertThat(actualUser.getEmail(), equalTo(expectedUser.getEmail()));
    }

    public static void validateUserUpdated(Response response) {
        response.then().statusCode(200);
    }

    public static void validateUserDeleted(Response response) {
        response.then().statusCode(404);
    }
}


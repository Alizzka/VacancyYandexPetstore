package petstore.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import petstore.model.Pet;

public class PetstoreClient {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    public static Response createPet(Pet pet) {
        return RestAssured.given()
                .contentType("application/json")
                .body(pet)
                .post(BASE_URL + "/pet");
    }

    public static Response getPetById(long id) {
        return RestAssured.given()
                .get(BASE_URL + "/pet/" + id);
    }

    public static Response deletePetById(long id) {
        return RestAssured.given()
                .delete(BASE_URL + "/pet/" + id);
    }
}

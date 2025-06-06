package petstore.utils;

import io.restassured.response.Response;
import petstore.model.Pet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetValidator {

    public static void validatePetCreated(Response response, Pet expectedPet) {
        response.then().statusCode(200);
        Pet actualPet = response.as(Pet.class);
        assertThat(actualPet.getId(), equalTo(expectedPet.getId()));
        assertThat(actualPet.getName(), equalTo(expectedPet.getName()));
        assertThat(actualPet.getStatus(), equalTo(expectedPet.getStatus()));
    }

    public static void validatePetFetched(Response response, Pet expectedPet) {
        response.then().statusCode(200);
        Pet actualPet = response.as(Pet.class);
        assertThat(actualPet.getId(), equalTo(expectedPet.getId()));
        assertThat(actualPet.getName(), equalTo(expectedPet.getName()));
    }

    public static void validatePetDeleted(Response response) {
        response.then().statusCode(200);
    }
}


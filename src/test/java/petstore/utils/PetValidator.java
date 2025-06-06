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

    //*
    public static void validatePetUpdate(Response response, Pet expectedPet) {
        response.then().statusCode(200);
        Pet actualPet = response.as(Pet.class);
        assertThat(actualPet.getId(), equalTo(expectedPet.getId()));
        assertThat(actualPet.getName(), equalTo(expectedPet.getName()));
        assertThat(actualPet.getStatus(), equalTo(expectedPet.getStatus()));
        // Дополнительно можно проверять категорию и теги, если они обновлялись
        if (expectedPet.getCategory() != null) {
            assertThat(actualPet.getCategory().getName(), equalTo(expectedPet.getCategory().getName()));
        }
        if (expectedPet.getTags() != null && !expectedPet.getTags().isEmpty()) {
            assertThat(actualPet.getTags().size(), equalTo(expectedPet.getTags().size()));
            assertThat(actualPet.getTags().get(0).getName(), equalTo(expectedPet.getTags().get(0).getName()));
        }
    }
}


package petstore.steps;

import io.restassured.response.Response;
import petstore.model.Pet;
import petstore.utils.PetValidator;
import petstore.utils.PetstoreClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetstoreSteps {

    public static void createPet(Pet pet) {
        Response response = PetstoreClient.createPet(pet)
                .then().log().all().extract().response();
        PetValidator.validatePetCreated(response, pet);
    }

    public static void deletePet(long petId) {
        Response response = PetstoreClient.deletePetById(petId)
                .then().log().all().extract().response();
        PetValidator.validatePetDeleted(response);
    }

    public static void verifyPetDeleted(long petId) {
        Response response = PetstoreClient.getPetById(petId)
                .then().log().all().extract().response();
        assertEquals(404, response.getStatusCode());
    }
}



package petstore.tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import petstore.model.Pet;
import petstore.utils.PetstoreClient;
import petstore.utils.PetValidator;
import petstore.utils.PetFactory;

public class CreatePetTest {

    private Long createdPetId; // Сохраняем ID созданного питомца

    @Test
    @Description("Создание питомца")
    public void createPetTest() {
        Pet pet = PetFactory.createPetID2811();
        Response response = PetstoreClient.createPet(pet)
                .then().log().all().extract().response();
        createdPetId = response.jsonPath().getLong("id");
        PetValidator.validatePetCreated(response, pet.toBuilder().id(createdPetId).build());
    }

    @AfterEach
    public void deleteCreatedPet() {
        if (createdPetId != null) {
            PetstoreClient.deletePetById(createdPetId)
                    .then().log().all()
                    .statusCode(200);
        }
    }
}


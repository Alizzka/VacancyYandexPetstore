package petstore.tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import petstore.model.Pet;
import petstore.utils.PetFactory;
import petstore.utils.PetstoreClient;
import petstore.utils.PetValidator;

public class UpdatePetTest {

    @Test
    @Description("Обновление питомца")
    void shouldUpdatePetSuccessfully() {
        // Создание питомца
        Pet originalPet = PetFactory.createDefaultPet();
        Response createResponse = PetstoreClient.createPet(originalPet)
                .then().log().all()
                .extract().response();
        PetValidator.validatePetCreated(createResponse, originalPet);
        // Обновляем питомца
        Pet updatedPet = originalPet.toBuilder()
                .name("UpdatedName")
                .status("sold")
                .build();
        Response updatePetResponse = PetstoreClient.updatePet(updatedPet)
                .then().log().all()
                .extract().response();
        // Проверка обновления
        PetValidator.validatePetUpdate(updatePetResponse, updatedPet);
        // Удаление питомца
        Response deleteResponse = PetstoreClient.deletePetById(updatedPet.getId())
                .then().log().all()
                .extract().response();
        PetValidator.validatePetDeleted(deleteResponse);
    }
}





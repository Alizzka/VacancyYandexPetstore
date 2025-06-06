package petstore.tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import petstore.model.Pet;
import petstore.utils.PetFactory;
import petstore.utils.PetstoreClient;
import petstore.utils.PetValidator;

public class GetPetTest {

    private Long createdPetId;

    @Test
    @Description("Получение питомца по ID")
    public void getPetByIdTest() {
        // Создаем питомца
        Pet petToCreate = PetFactory.createDefaultPet();
        Response createResponse = PetstoreClient.createPet(petToCreate)
                .then().log().all()
                .extract().response();
        // Сохраняем ID созданного питомца
        createdPetId = createResponse.jsonPath().getLong("id");
        // Обновляем объект с фактическим ID
        Pet expectedPet = petToCreate.toBuilder().id(createdPetId).build();
        // Получаем питомца по ID
        Response getResponse = PetstoreClient.getPetById(createdPetId)
                .then().log().all()
                .extract().response();
        // Проверка данных
        PetValidator.validatePetFetched(getResponse, expectedPet);
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

package petstore.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import petstore.model.Pet;
import petstore.steps.PetstoreSteps;
import petstore.utils.PetFactory;

public class DeletePetTest {

    @Test
    @Description("Удаление питомца и проверка, что он отсутствует")
    public void deletePetTest() {
        Pet pet = PetFactory.createMinimalPet();

        PetstoreSteps.createPet(pet);
        PetstoreSteps.deletePet(pet.getId());
        PetstoreSteps.verifyPetDeleted(pet.getId());
    }
}
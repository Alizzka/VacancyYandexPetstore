package petstore.tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import petstore.model.User;
import petstore.utils.UserFactory;
import petstore.utils.UserstoreClient;
import petstore.utils.UserValidator;

public class UserTest {

    private String createdUsername;

    @Test
    @Description("Создание пользователя")
    public void createUserTest() {
        User user = UserFactory.createDefaultUser();
        Response response = UserstoreClient.createUser(user)
                .then().log().all().extract().response();
        createdUsername = user.getUsername();
        UserValidator.validateUserCreated(response);
    }

    @Test
    @Description("Получение пользователя по username")
    public void getUserByUsernameTest() {
        User user = UserFactory.createDefaultUser();
        UserstoreClient.createUser(user);
        // Не создаём локальную переменную, чтобы сработал @AfterEach
        createdUsername = user.getUsername();
        Response getResponse = UserstoreClient.getUserByUsername(createdUsername)
                .then().log().all().extract().response();
        UserValidator.validateUserFetched(getResponse, user);
    }

    @Test
    @Description("Обновление пользователя")
    public void updateUserTest() {
        User originalUser = UserFactory.createDefaultUser();
        createdUsername = originalUser.getUsername();
        UserstoreClient.createUser(originalUser);

        User updatedUser = originalUser.toBuilder()
                .firstName("UpdatedN")
                .lastName("UpdatedLN")
                .email("updated@mail.com")
                .build();
        Response updateResponse = UserstoreClient.updateUser(createdUsername, updatedUser)
                .then().log().all().extract().response();
        UserValidator.validateUserUpdated(updateResponse);
        // Обновляем createdUsername
        createdUsername = updatedUser.getUsername();
        Response getResponse = UserstoreClient.getUserByUsername(createdUsername)
                .then().log().all().extract().response();
        UserValidator.validateUserFetched(getResponse, updatedUser);
    }

    @Test
    @Description("Удаление пользователя")
    public void deleteUserTest() {
        User user = UserFactory.createDefaultUser();
        createdUsername = user.getUsername();
        UserstoreClient.createUser(user);
        Response deleteResponse = UserstoreClient.deleteUser(createdUsername)
                .then().log().all().extract().response();
        UserValidator.validateUserDeleted(deleteResponse);
        // Обнуляем переменную, чтобы @AfterEach не пытался удалить уже удалённого пользователя
        createdUsername = null;
    }

    @AfterEach
    public void cleanupUser() {
        if (createdUsername != null) {
            UserstoreClient.deleteUser(createdUsername);
        }
    }
}


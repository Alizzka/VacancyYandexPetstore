package petstore.utils;

import petstore.model.User;

public class UserFactory {

    public static User createDefaultUser() {
        return User.builder()
                .id(10013)
                .username("testuser" + System.currentTimeMillis())
                .firstName("Testing")
                .lastName("User")
                .email("testuser@bmail.com")
                .password("password123")
                .phone("1234567800")
                .userStatus(1)
                .build();
    }
}



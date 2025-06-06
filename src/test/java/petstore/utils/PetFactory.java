package petstore.utils;

import petstore.model.Pet;
import java.util.List;

public class PetFactory {

    public static Pet createDefaultPet() {
        return Pet.builder()
                .id(2814) //.id(System.currentTimeMillis()) так как не принимает большие значения id для получения в дальнейшем
                .name("Barsik")
                .status("available")
                .category(Pet.Category.builder()
                        .id(2814)
                        .name("cats")
                        .build())
                .photoUrls(List.of("string"))
                .tags(List.of(Pet.Tag.builder()
                        .id(2814)
                        .name("fluffy")
                        .build()))
                .build();
    }

    public static Pet createPetID2811() {
        return Pet.builder()
                .id(2811) // .id(System.currentTimeMillis()) так как есть куча созданных но не удаленных данных
                .name("rigo")
                .status("available")
                .category(Pet.Category.builder()
                        .id(2811)
                        .name("string")
                        .build())
                .photoUrls(List.of("string"))
                .tags(List.of(Pet.Tag.builder()
                        .id(2811)
                        .name("string")
                        .build()))
                .build();
    }

    public static Pet createMinimalPet() {
        return Pet.builder()
                .id(2911) // .id(System.currentTimeMillis()) так как есть куча созданных но не удаленных данных и не принимает большие значения id для получения в дальнейшем
                .name("TempPet")
                .status("pending")
                .build();
    }
}


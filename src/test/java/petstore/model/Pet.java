package petstore.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Pet {
    private long id;
    private String name;
    private String status;
    private Category category;
    private List<String> photoUrls;
    private List<Tag> tags;

    @Data
    @Builder(toBuilder = true)
    public static class Category {
        private long id;
        private String name;
    }

    @Data
    @Builder(toBuilder = true)
    public static class Tag {
        private long id;
        private String name;
    }
}
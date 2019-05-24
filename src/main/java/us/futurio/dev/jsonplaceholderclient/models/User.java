package us.futurio.dev.jsonplaceholderclient.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "username", "email", "posts"})
public class User {
    private long id;
    private String name;
    private String username;
    private String email;
    private List<Post> posts;
}

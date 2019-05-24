package us.futurio.dev.jsonplaceholderclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import us.futurio.dev.jsonplaceholderclient.exceptions.UserNotFoundException;
import us.futurio.dev.jsonplaceholderclient.models.Post;
import us.futurio.dev.jsonplaceholderclient.models.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${operations.serviceURL}")
    String serviceURL;

    private final UserResourceAssembler assembler;

    UserController(UserResourceAssembler assembler) {
        this.assembler = assembler;
    }

    @GetMapping("/users")
    Resources<Resource<User>> all() {
        User[] userArray = restTemplate.getForObject(serviceURL + "/users", User[].class);
        List<Resource<User>> users = Arrays.stream(userArray)
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(users,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    // Single item
    @GetMapping("/users/{userId}")
    Resource<User> one(@PathVariable("userId") long userId) {
        User user;
        try {
            user = restTemplate.getForObject(serviceURL + "/users/{userId}", User.class, userId);
        } catch (RestClientException rce) {
            throw new UserNotFoundException(userId);
        }

        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        return assembler.toResource(user);
    }

    // Single item
    @GetMapping("/users/{userId}/briefly")
    public Resource<User> oneBriefDetail(@PathVariable("userId") long userId) {
        User user;
        try {
            user = restTemplate.getForObject(serviceURL + "/users/{userId}", User.class, userId);
            Post[] posts = restTemplate.getForObject(serviceURL + "/posts/?userId={userId}", Post[].class, userId);
            user.setPosts(Arrays.asList(posts));
        } catch (RestClientException rce) {
            throw new UserNotFoundException(userId);
        }

        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        return assembler.toResource(user);
    }
}

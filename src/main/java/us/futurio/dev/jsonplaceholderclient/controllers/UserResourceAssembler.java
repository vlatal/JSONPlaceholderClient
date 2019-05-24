package us.futurio.dev.jsonplaceholderclient.controllers;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import us.futurio.dev.jsonplaceholderclient.models.User;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {

    /*@Override
    public Resource<User> toResource(User User) {
        return new Resource<>(User,
                linkTo(methodOn(UserController.class).one(User.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("Users"));
    }*/

    @Override
    public Resource<User> toResource(User User) {
        return new Resource<>(User,
                linkTo(methodOn(UserController.class).one(User.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).oneBriefDetail(User.getId())).withRel("BriefDetail"),
                linkTo(methodOn(UserController.class).all()).withRel("Users"));
    }
}
package us.futurio.dev.jsonplaceholderclient.advices;

import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import us.futurio.dev.jsonplaceholderclient.exceptions.UserNotFoundException;
import us.futurio.dev.jsonplaceholderclient.models.Message;

@ControllerAdvice
class UserNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Resource<Message> ClientNotFoundHandler(UserNotFoundException ex) {
        return new Resource<>(Message.createWithNow(ex.getMessage()));
    }
}
package us.futurio.dev.jsonplaceholderclient;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Resource;
import org.springframework.web.client.RestTemplate;
import us.futurio.dev.jsonplaceholderclient.controllers.UserController;
import us.futurio.dev.jsonplaceholderclient.models.User;

@Slf4j
@SpringBootApplication
public class Application {

    @Autowired
    private UserController userController;

    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            long userId = 2;
            if (args.length >= 1) {
                try {
                    userId = Long.parseLong(args[0]);
                } catch (Exception e) {
                }
            }
            ObjectMapper objectMapper = new ObjectMapper();
            Resource<User> userResource = userController.oneBriefDetail(userId);
            objectMapper.writeValue(System.out, userResource.getContent());
        };
    }
}
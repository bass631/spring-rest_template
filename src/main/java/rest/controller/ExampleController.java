package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rest.model.User;

import java.util.List;

@Component
public class ExampleController {

    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://91.241.64.178:7081/api/users";

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate
                .exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return responseEntity.getBody();
    }

    public void saveUser(User user) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, user, String.class);
    }

    public void updateUser(User user) {
        restTemplate.put(URL, user);
    }

    public void deleteUser(int id) {
        restTemplate.delete(URL + "/" + id);

    }

    public void getSessionId() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> forEntity = template.getForEntity(URL, String.class);
        forEntity.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
    }


}

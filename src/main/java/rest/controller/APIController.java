package rest.controller;

import org.springframework.stereotype.Component;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import rest.model.User;

import java.util.List;

@Component
public class APIController {

    private final String URL = "http://91.241.64.178:7081/api/users";
    private final RestTemplate restTemplate;

    public APIController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<List<User>> getAllUsers() {
        return restTemplate
                .exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });
    }

    public String getSessionId() {
        return getAllUsers().getHeaders().getValuesAsList("Set-Cookie").get(0);
    }

    public ResponseEntity<String> addUser(User user, String sessionId) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        return restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
    }

    public ResponseEntity<String> updateUser(User user, String sessionId) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        return restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
    }

    public ResponseEntity<String> deleteUser(User user, String sessionId) {
        String URI_FOR_DELETE = String.format("%s/%d", URL, user.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        return restTemplate.exchange(URI_FOR_DELETE, HttpMethod.DELETE, entity, String.class);
    }
}

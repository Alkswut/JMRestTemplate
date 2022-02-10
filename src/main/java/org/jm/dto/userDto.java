package org.jm.dto;

import org.jm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class userDto {
    private final RestTemplate restTemplate;
    private String cookie;
    private final String url = "http://91.241.64.178:7081/api/users";

    @Autowired
    public userDto(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUsers() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        cookie = responseEntity.getHeaders().getFirst("Set-Cookie");
        ResponseEntity<List<User>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {});
        return response.getBody();
    }

    public void saveUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, getHeaders());
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity,String.class);
        System.out.print(response.getBody());
    }

    public void updateUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, getHeaders());
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        System.out.print(response.getBody());
    }

    public void deleteUser(Long id) {
        HttpEntity<Long> entity = new HttpEntity<>(id, getHeaders());
        ResponseEntity<String> response = restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, entity, String.class);
        System.out.print(response.getBody());
    }

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", cookie);
        return headers;
    }
}

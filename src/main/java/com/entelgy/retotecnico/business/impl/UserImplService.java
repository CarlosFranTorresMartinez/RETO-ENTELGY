package com.entelgy.retotecnico.business.impl;

import com.entelgy.retotecnico.business.UserRepository;
import com.entelgy.retotecnico.model.User;
import jdk.nashorn.internal.parser.JSONParser;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


@Service
public class UserImplService implements UserRepository {

    private final RestTemplate restTemplate;
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public UserImplService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> findUserResponse() {
        final User user = findUser().getBody();
        final JSONObject jsonObject = new JSONObject();
        final JSONArray jsonArray = new JSONArray();

        Objects.requireNonNull(user).getData().forEach(data -> {
            final JSONObject object = new JSONObject();

            object.put("id", data.getId());
            object.put("last_name", data.getLast_name());
            object.put("email", data.getEmail());

            jsonArray.add(object);
        });

        jsonObject.put("operationDate", dateFormat.format(ZonedDateTime.now()));

        jsonObject.put("data", jsonArray);

        return ResponseEntity.ok(jsonObject.toString());
    }

    private HttpEntity<String> headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        return new HttpEntity<>(headers);
    }


    private ResponseEntity<User> findUser() {
        return restTemplate.exchange("https://reqres.in/api/users", HttpMethod.GET, headers(), User.class);
    }
}

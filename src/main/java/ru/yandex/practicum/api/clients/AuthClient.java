package ru.yandex.practicum.api.clients;

import io.restassured.response.Response;
import ru.yandex.practicum.api.models.UserRegisterRequest;

import static io.restassured.RestAssured.given;

public class AuthClient {

    private static final String API_SIGNUP = "/api/signup";

    private final String base_uri;

    public AuthClient(String base_uri) {
        this.base_uri = base_uri;
    }

    public Response registerUser(UserRegisterRequest userRegisterRequest) {
        return given()
                .baseUri(this.base_uri)
                .header("Content-type", "application/json")
                .body(userRegisterRequest)
                .when()
                .post(API_SIGNUP);
    }
}

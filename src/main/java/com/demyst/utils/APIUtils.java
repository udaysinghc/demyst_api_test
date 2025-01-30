package com.demyst.utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class APIUtils {

    public static Response sendLoginRequest(String email, String password) {
        String requestBody = createLoginRequestBody(email, password);

        return given()
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("origin", "https://demyst.com")
                .header("referer", "https://demyst.com/login")
                .body(requestBody)
                .when()
                .post("/console/users/sign_in");
    }

    private static String createLoginRequestBody(String email, String password) {
        return String.format("{\"user\":{\"email\":\"%s\",\"password\":\"%s\"}}",
                email != null ? email : "",
                password != null ? password : "");
    }

    public static void logRequestDetails(RequestSpecification request) {
        System.out.println("Request Headers: " + request.log().headers());
        System.out.println("Request Body: " + request.log().body());
    }
}
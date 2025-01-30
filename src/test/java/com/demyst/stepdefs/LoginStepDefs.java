package com.demyst.stepdefs;

import com.demyst.config.ConfigManager;
import com.demyst.utils.APIUtils;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginStepDefs {
    private String email;
    private String password;
    private Response response;

    @Before
    public void setup() {
        // Set base URI from config
        RestAssured.baseURI = ConfigManager.getBaseUrl();
    }

    @Given("user email {string} and password {string}")
    public void userEmailAndPassword(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @When("user send POST request to login endpoint")
    public void userSendPOSTRequestToLoginEndpoint() {
        // Create request body
        String requestBody = String.format("{\"user\":{\"email\":\"%s\",\"password\":\"%s\"}}", email, password);

        // Send POST request
        response = RestAssured.given()
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("origin", "https://demyst.com")
                .header("referer", "https://demyst.com/login")
                .body(requestBody)
                .when()
                .post("/console/users/sign_in");
    }

    @Then("should get status code {int}")
    public void ShouldGetStatusCode(int expectedStatusCode) {
        assertEquals("Status code is not as expected",
                expectedStatusCode,
                response.getStatusCode());
    }

    @And("response should contain error message {string}")
    public void responseShouldContainErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = response.jsonPath().getString("error");
        assertEquals("Error message is not as expected",
                expectedErrorMessage,
                actualErrorMessage);
    }

    @And("response time should be less than {int}ms")
    public void responseTimeShouldBeLessThanMs(int maxResponseTime) {
        long actualResponseTime = response.getTime();
        assertTrue("Response time exceeded " + maxResponseTime + "ms. Actual: " + actualResponseTime + "ms",
                actualResponseTime < maxResponseTime);
    }

    // Helper methods for validation and error handling
    private void validateResponse(Response response, int expectedStatusCode, String expectedErrorMessage) {
        // Status Code validation
        assertEquals("Status code validation failed",
                expectedStatusCode,
                response.getStatusCode());

        // Error message validation
        String actualErrorMessage = response.jsonPath().getString("error");
        assertEquals("Error message validation failed",
                expectedErrorMessage,
                actualErrorMessage);

        // Response time validation
        assertTrue("Response time exceeded 2000ms",
                response.getTime() < 2000);
    }

    private void logResponseDetails(Response response) {
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Response Time: " + response.getTime() + "ms");
    }

    // Error handling method
    private void handleTestFailure(String message, Response response) {
        logResponseDetails(response);
        Assert.fail(message);
    }
}
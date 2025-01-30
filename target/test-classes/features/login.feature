Feature: Login API Tests

  Scenario Outline: Login with different email and password combinations
    Given user email "<email>" and password "<password>"
    When user send POST request to login endpoint
    Then should get status code <statusCode>
    And response should contain error message "<errorMessage>"
    And response time should be less than 2000ms

    Examples:
      | email          | password  | statusCode | errorMessage                                      |
      |                |           | 401        | You need to sign in or sign up before continuing. |
      | test           |           | 401        | Invalid Email or password.                        |
      | test@gmail.com |           | 401        | Invalid Email or password.                        |
      |                | Test@1234 | 401        | You need to sign in or sign up before continuing. |
      | Test@gmail.com | Test@1234 | 401        | Invalid Email or password.                        |

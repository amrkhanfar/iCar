Feature: Admin Login

  Scenario: Successful Admin Login
    Given the admin is not logged in the app
    When the admin provides valid credentials
      | Username | Password  |
      | amr      | 123456789 |
    And the admin attempts to log in
    Then the admin is successfully logged into the app

  Scenario: Admin Enters Invalid Credentials
    Given the admin is not logged in the app
    When the admin provides invalid credentials
      | Username | Password        |
      | amr      | 123456789123456 |
    And the admin attempts to log in
    Then the admin is unable to log in
    And an error message is displayed to inform the admin of the issue

  Scenario: Admin Enters Empty Credentials
    Given the admin is not logged in the app
    When the admin provides empty credentials
      | Username | Password |
      |          |          |
    And the admin attempts to log in
    Then the admin is unable to log in
    And an error message is displayed to inform the admin of the issue
Feature: User Management

  Background:
    Given the user manager is initialized

  Scenario: Register a new user
    When a user with name "Ayman Khanfar", email "ayman@gmail.com", password "123456789", and role "user" registers
    Then the registration is successful

  Scenario: Authenticate an existing user
    And a user with name "Amr Khanfar", email "ultraakchh@gmail.com", password "123456789", and role "admin" exists
    When the user with email "ultraakchh@gmail.com" and password "123456789" tries to authenticate
    Then the authentication is successful

  Scenario: Authenticate a non-existing user
    When a user with email "fakefake@example.com" and password "invalidpassword" tries to authenticate
    Then the authentication fails

  Scenario: Get user by email
    Given a user with name "Amr Khanfar", email "ultraakch@gmail.com", password "123456789", and role "user" exists
    When a request is made to get the user with email "ultraakch@gmail.com"
    Then the user is found

  Scenario: Delete an existing user
    Given a user with name "ToDelete", email "delete@example.com", password "password123", and role "user" exists
    When the user with email "delete@example.com" is deleted
    Then the deletion is successful

  Scenario: Delete a non-existing user
    When a request is made to delete the user with email "nonexistent@example.com"
    Then the deletion fails

  Scenario: Get user by name
    Given a user with name "Farah Glory", email "farah.g@example.com", password "pass123456789", and role "user" exists
    When a request is made to get the user with name "Farah Glory"
    Then the user is found

  Scenario: Get users by role
    Given a user with name "Yara", email "yar@example.com", password "pass123456", and role "admin" exists
    And a user with name "Nidal", email "Nido@example.com", password "pass123456", and role "user" exists
    When a request is made to get users with role "user"
    Then the users with role "user" are found

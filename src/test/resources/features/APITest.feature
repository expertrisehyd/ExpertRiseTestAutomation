Feature: User API lifecycle
  As a tester
  I want to validate the full lifecycle of a user
  So that I can ensure the API works correctly

  @mockService
  Scenario: Create, retrieve, update, and delete a user
    Given the API service is running
    When I create a new user with firstName "Jane", lastName "Doe", email "jane.doe@test.com", and role "user"
    Then the user is created successfully
    When I retrieve the created user
    Then the user details should match with email "jane.doe@test.com"
    When I update the user email to "jane.updated@test.com"
    Then the user email should be updated successfully
    When I delete the user
    Then the user should not be found

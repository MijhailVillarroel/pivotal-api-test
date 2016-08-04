@CleanEnvironment
Feature: Testing for delete project in Pivotal

  Background: create a Project
    Given  I send a POST request to /projects
      | name   | Test Edit Projects |
      | public | true                   |
    And I expect the status code 200
    Then stored as Project1

  @deleteAllProject
  Scenario: Edit Project
    When I send a PUT request to /projects/[Project1.id]
      | description | totally new |
    Then I expect the status code 200
    And The description field should be equals to totally new

  Scenario: Delete projects
    When I send a DELETE request /projects/[Project1.id]
    Then I expect the status code 204

  @deleteAllProject
  Scenario: Get All Projects
    Given I send a GET request to /projects endpoint
    Then I expect the status code 200
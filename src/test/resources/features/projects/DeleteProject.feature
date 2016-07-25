Feature: Testing for delete project in Pivotal

  Background: create a Project
    Given  I send a POST request to /projects
      | name   | Test Edit Project0|
      | public | true                     |
    Then stored as Project

  Scenario: Delete projects
    Given I have the /projects/[Project.id] endpoint
    When I send a DELETE request
    Then I expect the status code 204

  @deleteProject
  Scenario: Edit Project
    When I send a PUT request to /projects/[Project1.id]
      | description | totally new |
    Then I expect the status code 200
    And The description field should be equals to totally new



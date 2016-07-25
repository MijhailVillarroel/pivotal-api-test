@CleanEnvironment
Feature: Get, Put and Delete Epic

  Background: Create a project
    Given I send a POST request to /projects
      | name   | project for get epics |
      | public | true                  |
    And I expect the status code 200
    And stored as Project1
    And I send a POST request to /projects/[Project1.id]/epics
      | name | Post example hs11h |
    And I expect the status code 200
    And stored as Epic1

  @deleteAllProject
  Scenario: Get all epics
    As a user
    I want to get  information of the epics
    Because i need to know the values the epic info
    Given I send a GET request to /projects/[Project1.id]/epics endpoint
    Then I expect the status code 200

  @deleteAllProject
  Scenario: Get epic by id
    Given I send a GET request to /projects/[Project1.id]/epics/[Epic1.id] endpoint
    Then I expect the status code 200

  @deleteAllProject
  Scenario: Update epic
    As a user
    I want to update information of  epics
    Because i need fix some mistakes or miss spells on field values
    Given I send a PUT request to /projects/[Project1.id]/epics/[Epic1.id]
      | name | epic example updated |
    Then I expect the status code 200
    And The name field should be equal to epic example updated

  @deleteAllProject
  Scenario: delete a  epic
    As a user
    i want to delete epics of my project
    because i need to make it easier to plan and track progress of large features at a high level
    When I send a DELETE request /projects/[Project1.id]/epics/[Epic1.id]
    Then I expect the status code 204

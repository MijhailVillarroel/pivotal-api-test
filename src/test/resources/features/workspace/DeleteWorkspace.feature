@CleanEnvironment
Feature: Delete Works pace

  As a user
  I want to delete a workspace previously created
  so that I expect that workspace selected is deleted

  Background: Create a workspace
    Given I send a POST request to /my/workspaces
      | name | WorkspaceTest1 |
    And I expect the status code 200
    And stored as Workspace1

  Scenario: delete a workspace without projects
    When I send a DELETE request /my/workspaces/[Workspace1.id] endpoint
    Then I expect the status code 204

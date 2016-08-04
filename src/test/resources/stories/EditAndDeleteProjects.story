Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

GivenStories: stories/CreateProjects.story

Scenario: Edit Project
When I send a PUT request to /projects/[Project1.id] :
|name_col| values|
| description | totally new |
Then I expect the status code 200
Then The description field should be equals to totally new

Scenario: Get All Projects
When I send a GET request to /projects endpoint
Then I expect the status code 200


Scenario: Delete projects
When I send a DELETE request /projects/[Project1.id]
Then I expect the status code 204
Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

GivenStories: stories/CreateProjects.story
Scenario: Delete projects
When I send a DELETE request /projects/[Project1.id]
Then I expect the status code 204
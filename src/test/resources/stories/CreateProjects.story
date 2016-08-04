Narrative: Testing for create Project by API
Scenario: Create project
Given I send a POST request to /projects :
|name_col| values             |
| name   | TestCreateProjects |
| public | true               |
When stored as Project1
Then I expect the status code 200
And I validate all setting projects

package org.fundacionjala.pivotal.jbehave.step;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.steps.Steps;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.fundacionjala.pivotal.util.CommonMethods.getStringValueFromMapOfResponses;
import static org.junit.Assert.assertEquals;

/**
 * Created by mijhailvillarroel on 8/3/2016.
 */
public class Asserts extends Steps {

    private static final int INDEX_1 = 0;

    private static final int INDEX_2 = 1;

    private org.fundacionjala.pivotal.jbehave.step.ApiResources api;

    public Asserts(org.fundacionjala.pivotal.jbehave.step.ApiResources api) {
        this.api = api;
    }

    @Then("The $fieldName field should be equals{s|} to $expectedValue")
    public void theProjectShouldBeUpdated(String fieldName, String expectedValue) {
        assertEquals(expectedValue, api.getResponse().path(fieldName));
    }

    @Then("I validate all setting projects")
    public void iValidateAllSettingProjects() {
        String JsonString = api.getResponse().asString();
        //Schema
//        JsonSchemaValidator a =matchesJsonSchemaInClasspath("testValidate.json");
//        assertEquals(JsonString,"");
        // whit validatid class
//        Gson gson = new Gson();
//        Map<ProjectSteps, Object> map = new HashMap<>();
//        map = (Map<ProjectSteps, Object>) gson.fromJson(api.getResponse().print(), map.getClass());
//        ValidateProjects.getAssertionMap(map).values().stream()
//                .forEach((steps) -> assertTrue("The fields is false ", steps));
    }

    @Then("I expect the status code $statusCodeExpected")
    public void iExpectStatusCode(int statusCodeExpected) {
        assertEquals(statusCodeExpected, api.getResponse().statusCode());
    }

    @Then("^I expect that \\[(.*)\\] be (.*)$")
    public void iExpectThatCommentNameBe(String expectedName, String expectedResult) {
        String[] value = expectedName.split("\\.");
        String actualResult = getStringValueFromMapOfResponses(value[INDEX_1], value[INDEX_2]);
        assertEquals(expectedResult, actualResult);
    }

    @Then("I expect that \\[(.*)\\] not be (.*)$")
    public void iExpectThatCommentTextNotBeCommentTest(String expectedName, String expectedResult) {
        String[] value = expectedName.split("\\.");
        String actualResult = getStringValueFromMapOfResponses(value[INDEX_1], value[INDEX_2]);
        assertEquals(expectedResult, actualResult);
    }

    @Then("^I validate fields$")
    public void iValidateFields() {
        final String expected = "newStory";
        assertEquals(expected, api.getResponse().jsonPath().get("name"));
    }
}

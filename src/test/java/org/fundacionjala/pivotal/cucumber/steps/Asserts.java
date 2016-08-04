package org.fundacionjala.pivotal.cucumber.steps;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.sun.xml.internal.ws.policy.AssertionSet;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.fundacionjala.pivotal.ProjectSteps;
import org.fundacionjala.pivotal.ValidateProjects;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.fundacionjala.pivotal.util.CommonMethods.getStringValueFromMapOfResponses;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class Asserts {

    private static final int INDEX_1 = 0;

    private static final int INDEX_2 = 1;

    private ApiResources api;

    public Asserts(ApiResources api) {
        this.api = api;
    }

    @And("^The (.*?) field should be equals? to (.*)$")
    public void theProjectShouldBeUpdated(String fieldName, String expectedValue) {
        assertEquals(expectedValue, api.getResponse().path(fieldName));
    }

    @And("^I validate all setting projects$")
    public void iValidateAllSettingProjects() {
  //      String JsonString = api.getResponse().asString();
 //       JsonSchemaValidator a =matchesJsonSchemaInClasspath("testValidate.json");
//        assertThat(JsonString,matchesJsonSchemaInClasspath("testValidate.json"));

//        Gson gson = new Gson();
//        Map<ProjectSteps, Object> map = new HashMap<>();
//        map = (Map<ProjectSteps, Object>) gson.fromJson(api.getResponse().print(), map.getClass());
//        ValidateProjects.getAssertionMap(map).values().stream()
//                .forEach((steps) -> assertTrue("The fields is false ", steps));
    }

    @Then("^I expect the status code (\\d+)$")
    public void iExpectStatusCode(int statusCodeExpected) {
        assertEquals(statusCodeExpected, api.getResponse().statusCode());
    }

    @Then("^I expect that \\[(.*)\\] be (.*)$")
    public void iExpectThatCommentNameBe(String expectedName, String expectedResult) {
        String[] value = expectedName.split("\\.");
        String actualResult = getStringValueFromMapOfResponses(value[INDEX_1], value[INDEX_2]);
        assertEquals(expectedResult, actualResult);
    }

    @And("^I expect that \\[(.*)\\] not be (.*)$")
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

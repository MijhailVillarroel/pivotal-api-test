package org.fundacionjala.pivotal.cucumber.steps;

import java.util.Map;

import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

import static org.fundacionjala.pivotal.api.Mapper.addResponse;
import static org.fundacionjala.pivotal.api.Mapper.mapEndpoint;
import static org.fundacionjala.pivotal.api.RequestManager.deleteRequest;
import static org.fundacionjala.pivotal.api.RequestManager.getRequest;
import static org.fundacionjala.pivotal.api.RequestManager.postRequest;
import static org.fundacionjala.pivotal.api.RequestManager.putRequest;

/**
 * @author Henrry Salinas.
 *         <p>
 *         This class provide the basic step definitions to work with api rest requests
 */
public class ApiResources {

    private Response response;

    @When("^I send a GET request to (.*) endpoint$")
    public void iSendAGETRequest(String endPoint) {
        response = getRequest(mapEndpoint(endPoint));
    }

    @When("^I sen(?:d|t) a POST request to (.*)$")
    public void iSendAPOSTRequestWith(String endPoint, Map<String, Object> parameters) {
        response = postRequest(mapEndpoint(endPoint), parameters);
    }

    @When("^I send a PUT request to (.*?)$")
    public void iSendAPUTRequest(String endPoint, Map<String, Object> parameters) {
        response = putRequest(mapEndpoint(endPoint), parameters);
    }

    @When("^I send a DELETE request (.*?)$")
    public void iSendADELETERequest(String endPoint) {
        String a = mapEndpoint(endPoint);
        response = deleteRequest(mapEndpoint(endPoint));
        System.out.println(response.prettyPrint());
    }

    @And("^stored as (.*)")
    public void storedAs(String key) {
        addResponse(key, response);
    }

    public Response getResponse() {
        return response;
    }

}

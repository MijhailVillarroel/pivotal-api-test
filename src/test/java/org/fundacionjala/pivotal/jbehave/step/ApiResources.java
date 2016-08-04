package org.fundacionjala.pivotal.jbehave.step;

import java.util.HashMap;
import java.util.Map;

import com.jayway.restassured.response.Response;
import cucumber.api.java.en.And;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Steps;

import static org.fundacionjala.pivotal.api.Mapper.addResponse;
import static org.fundacionjala.pivotal.api.Mapper.mapEndpoint;
import static org.fundacionjala.pivotal.api.RequestManager.deleteRequest;
import static org.fundacionjala.pivotal.api.RequestManager.getRequest;
import static org.fundacionjala.pivotal.api.RequestManager.postRequest;
import static org.fundacionjala.pivotal.api.RequestManager.putRequest;

/**
 * Created by mijhailvillarroel on 8/3/2016.
 */
public class ApiResources extends Steps{

    private Response response;

    @When("I send a GET request to $endPoint endpoint")
    public void iSendAGETRequest(String endPoint) {
        response = getRequest(mapEndpoint(endPoint));
    }

    @Given("I send a POST request to $endPoint : $parameter")
    public void iSendAPOSTRequestWith(String endPoint, ExamplesTable parameter) {
        response = postRequest(mapEndpoint(endPoint), convertExampleTableToMapper(parameter));
    }

    @When("I send a PUT request to $endPoint :$table")
    public void iSendAPUTRequest(String endPoint, ExamplesTable parameter) {
        response = putRequest(mapEndpoint(endPoint), convertExampleTableToMapper(parameter));
    }

    @When("I send a DELETE request $endPoint")
    public void iSendADELETERequest(String endPoint) {
        response = deleteRequest(mapEndpoint(endPoint));
    }

    @When("stored as $key")
    public void storedAs(String key) {
        addResponse(key, response);
    }

    public Response getResponse() {
        return response;
    }

    private Map<String, Object> convertExampleTableToMapper(ExamplesTable parameter) {
        Map<String, Object> parameters = new HashMap<>();
        for (Map<String, String> row : parameter.getRows()) {
            parameters.put(row.get("name_col"), row.get("values"));
        }
        return parameters;
    }
}

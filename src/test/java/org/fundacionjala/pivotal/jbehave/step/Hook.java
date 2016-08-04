package org.fundacionjala.pivotal.jbehave.step;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.jayway.restassured.response.Response;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterScenario.Outcome;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.ScenarioType;

import static org.fundacionjala.pivotal.api.Mapper.mapEndpoint;
import static org.fundacionjala.pivotal.api.RequestManager.postRequest;
import static org.fundacionjala.pivotal.util.CommonMethods.deleteAllProjects;
import static org.junit.Assert.assertEquals;

public class Hook {


    private Response response;

    @BeforeScenario(uponType = ScenarioType.NORMAL)
    public void doSomethingBeforeNormalScenario() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "TestCreateProjects789");
        parameters.put("public", true);
        response = postRequest(mapEndpoint("/projects"), parameters);
        assertEquals(200, response.statusCode());
    }

    @AfterScenario(uponType = ScenarioType.EXAMPLE, uponOutcome = Outcome.ANY)
    public void deleteProjects() {
        deleteAllProjects();
    }

    public Response getResponse() {
        return response;
    }
}

package org.fundacionjala.pivotal.jbehave.step;

import java.util.HashMap;
import java.util.Map;

import com.jayway.restassured.response.Response;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterScenario.Outcome;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.ScenarioType;

import static org.fundacionjala.pivotal.api.Mapper.mapEndpoint;
import static org.fundacionjala.pivotal.api.RequestManager.postRequest;
import static org.fundacionjala.pivotal.util.CommonMethods.deleteAllProjects;
import static org.junit.Assert.assertEquals;

public class HookProjects {


    @AfterScenario(uponType = ScenarioType.NORMAL)
    public void deleteProjects() {
        deleteAllProjects();
    }
}

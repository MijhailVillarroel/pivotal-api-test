package org.fundacionjala.pivotal.jbehave.runner.suite;

import java.util.Arrays;
import java.util.List;

import org.fundacionjala.pivotal.jbehave.runner.utils.JUnitReportingRunner;
import org.fundacionjala.pivotal.jbehave.step.ApiResources;
import org.fundacionjala.pivotal.jbehave.step.Asserts;
import org.fundacionjala.pivotal.jbehave.step.HookProjects;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

/**
 * This class run of the stories file
 *
 * @author Mijhail Villarroel
 * @author Daniel Gonsalez
 */
@RunWith(JUnitReportingRunner.class)
public class ScenarioJUnitStoriesProjects extends JUnitStories {

    public ScenarioJUnitStoriesProjects() {
        JUnitReportingRunner.recommendedControls(configuredEmbedder());
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        ApiResources api =new ApiResources();
        return new InstanceStepsFactory(configuration(), api,new Asserts(api), new HookProjects());
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration();
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays.asList(
                "stories/CreateProjects.story");
    }



}

package org.fundacionjala.pivotal.jbehave.runner.suite;

import java.util.Arrays;
import java.util.List;


import org.fundacionjala.pivotal.jbehave.runner.utils.JUnitReportingRunner;
import org.fundacionjala.pivotal.jbehave.step.ApiResources;
import org.fundacionjala.pivotal.jbehave.step.Asserts;
import org.fundacionjala.pivotal.jbehave.step.Hook;
import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.DelegatingStepMonitor;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.StepMonitor;
import org.junit.runner.RunWith;

/**
 * <p>
 * {@link Embeddable} class to run multiple textual stories via JUnit.
 * </p>
 * <p>
 * Stories are specified in classpath and correspondingly the
 * {@link LoadFromClasspath} story loader is configured.
 * </p>
 * @author Mijhail Villarroel
 * @author Daniel Gonsalez
 */
@RunWith(JUnitReportingRunner.class)
public class ScenarioJUnitStoriesEditProjects extends JUnitStories {

    private Configuration configuration;

    public ScenarioJUnitStoriesEditProjects() {
        //	System.setProperty(Logger.PROP_JJM_LOGLEVEL, "debug");
        JUnitReportingRunner.recommendedControls(configuredEmbedder());

        CrossReference crossReference = new CrossReference("dummy")
                .withJsonOnly().withOutputAfterEachStory(true)
                .excludingStoriesWithNoExecutedScenarios(true);
        StepMonitor stepMonitor = new DelegatingStepMonitor(
                crossReference.getStepMonitor());
        configuration = new MostUsefulConfiguration()
                .useStepMonitor(stepMonitor)
                .usePendingStepStrategy(new FailingUponPendingStep())
                .useStoryReporterBuilder(
                        new StoryReporterBuilder().withDefaultFormats()
                                .withFailureTrace(true)
                                .withFormats(Format.XML, Format.HTML)
                                .withCrossReference(crossReference))
                .useParameterControls(new ParameterControls("<", ">", true));
    }

    @Override
    public Configuration configuration() {
        // when working with CrossReferences, you have to return the same
        // configuration INSTANCE => generate it once (in the constructor)
        // and reuse it here.
        return configuration;
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        ApiResources api = new ApiResources();
        return new InstanceStepsFactory(configuration(), api, new Asserts(api));
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays.asList(
                "stories/EditAndDeleteProjects.story");
    }

}

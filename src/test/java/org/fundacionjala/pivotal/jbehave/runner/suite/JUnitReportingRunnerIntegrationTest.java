package org.fundacionjala.pivotal.jbehave.runner.suite;

import java.util.Arrays;
import java.util.Collection;

import org.fundacionjala.pivotal.jbehave.runner.utils.JUnitReportingRunner;
import org.hamcrest.Matchers;
import org.jbehave.core.ConfigurableEmbedder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(Parameterized.class)
public class JUnitReportingRunnerIntegrationTest {

    @Mock
    private RunNotifier notifier;
    private JUnitReportingRunner runner;
    private String expectedDisplayName;
    private String expectedFirstStoryName;
    private String expectedFirstScenario;
    private String expextedFirstStep;

    @Before
    public void setUp() throws Throwable {
        MockitoAnnotations.initMocks(this);
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] params = {
                {ScenarioJUnitStoriesProjects.class,
                        "CreateProjects.story",
                        "Scenario: Create Project",
                        "When stored as Project1"}};
        return Arrays.asList(params);
    }

    public JUnitReportingRunnerIntegrationTest(
            Class<? extends ConfigurableEmbedder> cls,
            String expectedFirstStoryName, String expectedFirstScenario,
            String expextedFirstStep)
            throws Throwable {
        runner = new JUnitReportingRunner(cls);
        this.expectedDisplayName = cls.getName();
        this.expectedFirstStoryName = expectedFirstStoryName;
        this.expectedFirstScenario = expectedFirstScenario;
        this.expextedFirstStep = expextedFirstStep;
    }

    @Test
    public void runUpExampleScenarioAndCheckNotifications() {
        runner.run(notifier);
        verifyAllChildDescriptionsFired(runner.getDescription(), true);
    }

    private void verifyAllChildDescriptionsFired(Description description,
                                                 boolean onlyChildren) {
        String displayName = description.getDisplayName();
        if (!onlyChildren && considerStepForVerification(description)) {
            verify(notifier).fireTestStarted(description);
            System.out.println("verified start " + displayName);
        }
        for (Description child : description.getChildren()) {
            verifyAllChildDescriptionsFired(child, false);
        }
        if (!onlyChildren && considerStepForVerification(description)) {
            verify(notifier).fireTestFinished(description);
            System.out.println("verified finish " + displayName);
        }
    }

    private boolean considerStepForVerification(Description d) {
        String displayName = d.getDisplayName();
        return Character.isDigit(displayName.charAt(displayName.length() - 1));
    }

    @Test
    public void topLevelDescriptionForExample() {
        assertThat(runner.getDescription().getDisplayName(),
                equalTo(expectedDisplayName));
    }

    @Test
    public void storyDescriptionsForExample() {
        assertThat(getFirstStory().getDisplayName(),
                equalTo(expectedFirstStoryName));
    }

    @Test
    public void scenarioDescriptionsForExample() {
        assertThat(getFirstScenario().getDisplayName(),
                equalTo(expectedFirstScenario));
    }

    @Test
    public void stepDescriptionsForExample() {
        assertThat(getFirstScenario().getChildren().get(0).getDisplayName(),
                Matchers.startsWith(expextedFirstStep));
    }

    private Description getFirstStory() {
        return runner.getDescription().getChildren().get(1);
    }

    private Description getFirstScenario() {
        return getFirstStory().getChildren().get(0);
    }

}

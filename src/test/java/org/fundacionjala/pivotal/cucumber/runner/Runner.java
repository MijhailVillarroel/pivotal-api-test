package org.fundacionjala.pivotal.cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Henrry Salinas.
 *         <p>
 *         This class store settings for the test execution
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        format = {"pretty",
                "html:target/test-report",
                "json:target/test-report.json",
                "junit:target/test-report.xml"},
        features = {"src/test/resources/"},
        glue = {"org.fundacionjala.pivotal.cucumber"},
        snippets = SnippetType.CAMELCASE
)
public class Runner {
}

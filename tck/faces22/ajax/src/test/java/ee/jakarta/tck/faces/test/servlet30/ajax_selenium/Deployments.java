package ee.jakarta.tck.faces.test.servlet30.ajax_selenium;

import ee.jakarta.tck.faces.test.util.selenium.ITBaseWebDriverOnly;
import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * Given Arquilian has no single deployment testsuite
 * mechanism we have to rely on a third party library.
 * This improves the performance in a major area
 */
@ArquillianSuiteDeployment
public class Deployments {
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ITBaseWebDriverOnly.createDeployment();
    }
}

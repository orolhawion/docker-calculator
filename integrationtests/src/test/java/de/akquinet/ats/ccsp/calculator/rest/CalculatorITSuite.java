package de.akquinet.ats.ccsp.calculator.rest;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;
import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.connection.waiting.HealthChecks;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

@RunWith(WildcardPatternSuite.class)
@SuiteClasses({"**/*IT.class"})
public class CalculatorITSuite {

    public static final String URL_BASE = "http://$HOST:$EXTERNAL_PORT/";

    public static final String URL_BASE_CALCULATOR = URL_BASE + "calculator/rest/";
    private static final String URL_HEALTH_CHECK = URL_BASE_CALCULATOR + "health/";

    /**
     * SERVICE_NAME must match the service name in docker-compose.yml
     */
    public static final String SERVICE_NAME = "calculator_it";

    @ClassRule
    public static final DockerComposeRule docker = DockerComposeRule.builder()
            .file(AbstractCalculatorIT.class.getResource("/docker-compose.yml").getFile())
            .waitingForService(SERVICE_NAME, HealthChecks.toRespond2xxOverHttp(
                    8080, port -> port.inFormat(URL_HEALTH_CHECK))
            ).build();
}

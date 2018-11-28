package de.akquinet.ats.ccsp.calculator.rest;

import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.connection.Container;
import com.palantir.docker.compose.connection.waiting.HealthCheck;
import com.palantir.docker.compose.connection.waiting.HealthChecks;
import org.joda.time.Duration;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HealthResourceIT.class,
        CalculationResourceIT.class
})
public class CalculatorITSuite {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static boolean urlLogged = false;

    public static final String URL_BASE = "http://$HOST:$EXTERNAL_PORT/";

    public static final String URL_BASE_CALCULATOR = URL_BASE + "calculator/";
    private static final String URL_HEALTH_CALCULATOR = URL_BASE_CALCULATOR + "health/";

    private static final String CALCULATOR = "docker-calculator-wildfly";

    private static final Duration TIMEOUT = Duration.standardMinutes(2);

    @ClassRule
    public static final DockerComposeRule docker = DockerComposeRule.builder()
            .file(CalculatorITSuite.class.getResource("/docker-compose.yml").getFile())
            .waitingForService(CALCULATOR,
                    container -> check(URL_HEALTH_CALCULATOR).isHealthy(container), TIMEOUT)
            .build();

    private static HealthCheck<Container> check(final String url) {
        return target -> HealthChecks
                .toRespondOverHttp(8080, port -> {
                    final String actualUrl = port.inFormat(url);
                    if (!urlLogged) {
                        urlLogged = true;
                        LOG.info("Using url {}", actualUrl);
                    }
                    return actualUrl;
                }).isHealthy(target);
    }
}

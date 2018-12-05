package de.akquinet.ats.ccsp.calculator.rest;

import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.connection.waiting.HealthChecks;
import org.junit.ClassRule;


public abstract class AbstractCalculatorIT {
    public static final String URL_BASE = "http://$HOST:$EXTERNAL_PORT/";

    public static final String URL_BASE_CALCULATOR = URL_BASE + "calculator/rest/";
    private static final String URL_HEALTH_CHECK = URL_BASE_CALCULATOR + "health/";

    @ClassRule
    public static final DockerComposeRule docker = DockerComposeRule.builder()
            .file(AbstractCalculatorIT.class.getResource("/docker-compose.yml").getFile())
            .waitingForService("docker-calculator",
                HealthChecks.toRespond2xxOverHttp(8080,
                    port -> port.inFormat(URL_HEALTH_CHECK))
            ).build();
}

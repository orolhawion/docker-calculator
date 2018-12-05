FROM jboss/wildfly:11.0.0.Final

COPY ./target/dependency/docker-calculator-war.war /opt/jboss/wildfly/standalone/deployments/

HEALTHCHECK \
        --start-period=20s \
        --interval=5s \
        --timeout=3s \
        --retries=3 \
        CMD curl --fail http://localhost:8080/calculator/rest/health || exit 1

ENTRYPOINT /opt/jboss/wildfly/bin/standalone.sh \
                -c=standalone-full.xml \
                -b=0.0.0.0 \
                -Duser.language=de \
                --start-mode="normal"

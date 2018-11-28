FROM jboss/wildfly:11.0.0.Final

USER root

COPY scripts/sh /root/

COPY jboss-config /opt/jboss/wildfly/

COPY ./target/dependency/mysql-connector-java.jar /opt/jboss/wildfly/modules/com/mysql/main

COPY ./target/dependency/docker-calculator-war.war /opt/jboss/wildfly/standalone/deployments/

RUN chmod u+x /root/*.sh

RUN rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history
RUN chown -R jboss: /opt/jboss/wildfly/

EXPOSE 80
EXPOSE 8080
EXPOSE 9990

ENTRYPOINT /root/startApplication.sh

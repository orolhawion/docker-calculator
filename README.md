# docker-calculator
akquinet::ats::CCSP::Docker Workshop Example App

# Übung
Eine kleine Webanwendung, die die Basisfunktionalitäten eines Taschenrechners zur Verfügung stellt, soll "dockerized" werden.

## Ist-Zustand
Die Anwendung ist lauffähig, beinhaltet bereits Unit- und Integrationstests und ist bei GitHub verfügbar. 
Es gibt drei Branches:
master - Ausgangspunkt für Aufgabe 1. Alle Codeänderungen sollten hier gemacht werden

appDockerized - Ein Lösungsvorschlag für Aufgabe 2, an dem man sich orientieren kann

ideFriendlyIntegrationTests - Ein Lösungsvorschlag für Aufgabe 3 und die Zusatzaufgabe, an dem man sich orientieren kann

Bitte checkt Eure Änderungen nicht bei Github ein.

### Aufgabe 1
Checke das Übungsbeispiel aus und baue es mittels mvn clean install -Pit um sicherzustellen, dass Du eine lauffähige Anwendung hast.

### Aufgabe 2
Das Modul integrationtests baut den JBoss und deployed die Anwendung in diesen hinein (Maven Profil it). Beim Build wird der JBoss mittels wildfly-maven-plugin gestartet und die Tests dagegen ausgeführt.

Ändere den Buildprozess so, dass die Anwendung in einem Docker-Container läuft und die Tests dagegen laufen.
Das Docker-Image kann mit Maven gebaut werden, etwa mit dem dockerfile-maven-plugin von Spotify. Das bereits vorhandene Modul "docker" bietet sich dafür an.

Der Docker-Container kann mit Maven gestartet werden, etwa mit dem docker-maven-plugin von io.fabric8.

Hinweis: Dadurch überflüssige Konfiguration in Maven kann entfernt werden.

### Aufgabe 3
Auf Basis der Ergebnisse aus Aufgabe 3 sollen folgende Änderungen vorgenommen werden:

Ändere die Integrations-Tests so, dass das Starten des Docker-Containers mittels Maven überflüssig wird und die Tests dies selbst übernehmen.

Du kannst dafür die JUnit-Rule docker-compose-rule-junit4 von Palantir benutzen. Diese benutzt docker-compose. Beschreibe also die Testumgebung mit eine YAML-Datei.

Starte die Integrations-Tests anschließend direkt aus der IDE heraus, so wie einen Unit-Test.

### Zusatzaufgabe
Idealerweise muss nicht für jeden Test ein Container gestartet werden, sondern nur einer für alle Tests.

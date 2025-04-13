# Camunda Incident Logger
This library is a Camunda 7 ProcessEngine plugin, with the aim of writing a WARN statement to the log when an incident occurs.

An incident means, Camunda 7 has run out of retries for a failing task and the process just hanged.

The log message format is:

```
Camunda Incident: "${deploymentName}" --> "${processDefinitionName} (version ${processDefinitionVersion})" --> "${taskName}". ${incidentType}, processInstanceId: ${processInstanceId}, and message: ${exceptionMessage}
```

Though not expected, theoretically in case camunda execution entity does not exist for an incident, it will print this message instead:

```
Camunda Incident with no Execution Id.: IncidentType:${incidentType}, ProcessDefinitionId:${processDefinitionId}, FailedActivityId:${failedActivityId}, ActivityId:${activityId}
```

## Installation and Usage
- Including the maven dependency is enough, no further configuration is necessary
- The log level for "org.opentmf.camunda" must be at least WARN.

## Maven Dependency
### Import opentmf-commons-versions
```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.opentmf</groupId>
      <artifactId>opentmf-versions</artifactId>
      <version>RELEASE</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```
### Add Maven Dependency
```xml
<dependency>
  <groupId>org.opentmf.camunda</groupId>
  <artifactId>camunda7-incident-logger</artifactId>
</dependency> 
```
## Version History
### 1.0.0
- Initial Release
### 1.0.1
- Updates to Camunda 7.22
### 1.0.2
- Updates Spring Boot to 3.4.0
### 1.0.3
- Updates Spring Boot to 3.4.4
- Initial Open Source Version

# Camunda Incident Logger
This library is a Camunda 7 ProcessEngine plugin, with the aim of writing a WARN statement to the log when an incident occurs.

An incident means, Camunda 7 has run out of retries for a failing task and the process just hanged.

The log message format is:

```
Camunda Incident: "${deploymentName}" --> "${processDefinitionName} (version ${processDefinitionVersion})" --> "${taskName}". ${incidentType}, processInstanceId: ${processInstanceId}, and message: ${exceptionMessage}
```

Though not expected, theoretically in case camunda execution entity does not exist for an incident, it will print this message:

```
Camunda Incident with no Execution Id.: IncidentType:${incidentType}, ProcessDefinitionId:${processDefinitionId}, FailedActivityId:${failedActivityId}, ActivityId:${activityId}
```

## Installation and Usage
- Including the maven dependency is enough, no further configuration is necessary
- The log level for "com.pia.camunda" must be at least WARN.

#### Maven Dependency
```xml
<dependency>
  <groupId>com.pia.commons</groupId>
  <artifactId>camunda-incident-logger</artifactId>
</dependency> 
```
## Version History
### 1.0.0
- Initial Release
### 1.0.1
- Updates to Camunda 7.22

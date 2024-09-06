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

In order to use this plugin, the following section can be added to the bpm-platform.xml:

```xml

<bpm-platform xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns="http://www.camunda.org/schema/1.0/BpmPlatform"
  xsi:schemaLocation="http://www.camunda.org/schema/1.0/BpmPlatform http://www.camunda.org/schema/1.0/BpmPlatform ">

  <job-executor>
    <job-acquisition name="default"/>
  </job-executor>

  <process-engine name="default">
    <job-acquisition>default</job-acquisition>

    <properties>
      <property name="jobExecutorDeploymentAware">false</property>
    </properties>

    <plugins>
      <plugin>
        <class>com.pia.camunda.IncidentLoggerPlugin</class>
      </plugin>
    </plugins>
  </process-engine>

</bpm-platform>
```
There is no need for any further configuration.

## Installation and Usage
- The log level of com.pia.camunda must be at least WARN.

#### Maven Dependency
```xml
<dependency>
  <groupId>com.pia.commons</groupId>
  <artifactId>camunda-incident-logger</artifactId>
  <version>1.0.0</version>
</dependency> 
```
## Version History
### 1.0.0
- Initial Release

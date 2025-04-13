package org.opentmf.camunda;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.incident.DefaultIncidentHandler;
import org.camunda.bpm.engine.impl.incident.IncidentContext;
import org.camunda.bpm.engine.impl.incident.IncidentHandler;
import org.camunda.bpm.engine.impl.persistence.entity.DeploymentEntity;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.runtime.Incident;

/**
 * @author Cezmi Aslan
 */
@Slf4j
public class IncidentLogger extends DefaultIncidentHandler implements IncidentHandler {

  public IncidentLogger(String type) {
    super(type);
  }

  @Override
  public Incident handleIncident(IncidentContext context, String message) {
    try {
      if (context.getExecutionId() == null) {
        log.warn(
            "Camunda Incident with no Execution Id.: IncidentType:'{}',"
                + " ProcessDefinitionId: '{}',"
                + " FailedActivityId: '{}',"
                + " ActivityId: '{}'",
            this.getIncidentHandlerType(),
            context.getProcessDefinitionId(),
            context.getFailedActivityId(),
            context.getActivityId());
      } else {
        ExecutionEntity execution = Context.getCommandContext().getExecutionManager()
            .findExecutionById(context.getExecutionId());
        List<DeploymentEntity> deployments = Context.getCommandContext().getDeploymentManager()
            .findDeploymentsByIds(execution.getProcessDefinition().getDeploymentId());
        String deploymentName = null;
        if (!deployments.isEmpty()) {
          deploymentName = deployments.get(0).getName();
        }
        log.warn(
            "Camunda Incident: '{}' --> '{} (version {})' --> '{}'."
                + " '{}', processInstanceId: '{}' and message: '{}'",
            deploymentName,
            execution.getProcessDefinition().getName() != null
                ? execution.getProcessDefinition().getName() : context.getActivityId(),
            execution.getProcessDefinition().getVersion(),
            execution.getActivity().getName(),
            this.getIncidentHandlerType(),
            execution.getProcessInstanceId(),
            message
        );
      }
    } catch (Throwable throwable) {
      log.error(
          "Exception while logging camunda incident. Please check incidents"
              + " and fix this code error.", throwable);
    }
    return super.handleIncident(context, message);
  }
}

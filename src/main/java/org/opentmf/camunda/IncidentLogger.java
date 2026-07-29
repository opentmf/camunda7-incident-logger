package org.opentmf.camunda;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.cibseven.bpm.engine.impl.context.Context;
import org.cibseven.bpm.engine.impl.incident.DefaultIncidentHandler;
import org.cibseven.bpm.engine.impl.incident.IncidentContext;
import org.cibseven.bpm.engine.impl.incident.IncidentHandler;
import org.cibseven.bpm.engine.impl.persistence.entity.DeploymentEntity;
import org.cibseven.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.cibseven.bpm.engine.runtime.Incident;

/**
 * @author Cezmi Aslan
 */
@Slf4j
public class IncidentLogger extends DefaultIncidentHandler implements IncidentHandler {

  /**
   * @param type the incident handler type (e.g. {@code failedJob} or {@code failedExternalTask})
   */
  public IncidentLogger(String type) {
    super(type);
  }

  @Override
  public Incident handleIncident(IncidentContext context, String message) {
    // Incidents without an execution (e.g. raised during process instance version
    // migrations) are intentionally not logged - they are not actionable and only
    // produce noise.
    if (context.getExecutionId() == null) {
      return super.handleIncident(context, message);
    }

    try {
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
    } catch (Throwable throwable) {
      log.error(
          "Exception while logging camunda incident. Please check incidents"
              + " and fix this code error.", throwable);
    }
    return super.handleIncident(context, message);
  }
}

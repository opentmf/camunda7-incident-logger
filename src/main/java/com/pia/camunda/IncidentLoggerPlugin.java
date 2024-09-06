package com.pia.camunda;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.runtime.Incident;
import org.springframework.context.annotation.Configuration;

/**
 * @author Cezmi Aslan
 * @author Gokhan Demir
 */
@Configuration
@Slf4j
public class IncidentLoggerPlugin extends AbstractProcessEnginePlugin {

  @Override
  public void preInit(ProcessEngineConfigurationImpl engineConfig) {
    log.info("Initializing Camunda Incident Logger.");
    engineConfig.setCustomIncidentHandlers(
        Arrays.asList(
            new IncidentLogger(Incident.FAILED_JOB_HANDLER_TYPE),
            new IncidentLogger(Incident.EXTERNAL_TASK_HANDLER_TYPE)));
  }

  @Override
  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    log.info("Camunda Incident Logger initialization completed.");
  }
}

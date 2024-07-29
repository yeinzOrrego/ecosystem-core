package com.ada.ecosystem.core.v1.kafka;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Instantiates a new task status.
 */
@Schema(description = "Representa el estado de una tarea de proceso.")
@Data
public class TaskStatus implements Serializable {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  
  /** The task id. */
  @Schema(description = "Identificador de la tarea de proceso.")
  private String taskId;
  
  /** The task name. */
  @Schema(description = "Nombre de la tarea de proceso.")
  private String taskName;
  
  /** The task name. */
  @Schema(description = "Parámetro asociado a la tarea de proceso.")
  private String taskKey;
  
  /** The percentage complete. */
  @Schema(description = "Porcentaje de la tarea de proceso.")
  private Float percentageComplete;
  
  /** The status. */
  @Schema(description = "Estado de la tarea de proceso.")
  private Status status;
}
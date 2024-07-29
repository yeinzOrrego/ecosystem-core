package com.ada.ecosystem.core.v1.kafka;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Instantiates a new task response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse implements Serializable {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  
  /** The task id. */
  private String taskId;
  
  /** The name. */
  private String name;
}
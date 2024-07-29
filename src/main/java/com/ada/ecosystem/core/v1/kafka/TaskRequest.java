package com.ada.ecosystem.core.v1.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Instantiates a new task request.
 *
 * @param name the name
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
  
  /** The name. */
  private String name;
}
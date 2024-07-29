package com.ada.ecosystem.core.v1.process;

import com.ada.ecosystem.core.v1.kafka.Status;
import com.ada.ecosystem.core.v1.kafka.TaskStatus;

public interface NotifyBrokerProcesses {
	
	public void notifyTaskExecutorProgress(TaskStatus taskStatus, Status status, Float percentage);
	public void notifyTaskExecutorProgress(TaskStatus taskStatus, Status status, Float percentage, String message);
}
